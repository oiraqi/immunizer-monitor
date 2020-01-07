package org.immunizer.acquisition;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.immunizer.acquisition.Invocation;

import java.time.Duration;

public class AcquisitionApplication {

    public static void main(String[] args) {
        
        InvocationConsumer consumer = new InvocationConsumer();
        FeatureExtractor featureExtractor = FeatureExtractor.getSingleton();

        try {            
            while (true) {
                ConsumerRecords<String, Invocation> records = consumer.poll(Duration.ofSeconds(5));
                System.out.println(records.count());
                for (ConsumerRecord<String, Invocation> record : records){
                    System.out.printf("offset = %d, key = %s, value = %s%n",
                        record.offset(), record.key(), record.value());
                    FeatureRecord featureRecord = featureExtractor.extract(record.value());
                    if (featureRecord != null) {
                        featureExtractor.log(featureRecord);
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }

}