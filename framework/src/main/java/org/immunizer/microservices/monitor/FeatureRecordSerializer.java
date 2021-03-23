package org.immunizer.microservices.monitor;

import org.apache.kafka.common.serialization.Serializer;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Map;

public class FeatureRecordSerializer implements Serializer<FeatureRecord>, Serializable {

    private static final long serialVersionUID = 127353L;

    private Gson gson = new Gson();

    public FeatureRecordSerializer() {
    }

    @Override
    public byte[] serialize(String topic, FeatureRecord featureRecord) {
        return gson.toJson(featureRecord).getBytes();
    }

    @Override
    public void close() {
        // intentionally left blank
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // intentionally left blank
    }
}