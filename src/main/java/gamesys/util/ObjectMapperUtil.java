package gamesys.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gamesys.exception.ObjectMapperException;

import java.io.IOException;

public class ObjectMapperUtil {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    private ObjectMapperUtil() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToPojo(String json, Class<?> clazz) {
        try {
            return (T) jsonMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new ObjectMapperException();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T bytesToPojo(byte[] json, Class<?> clazz) {
        try {
            return (T) jsonMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new ObjectMapperException();
        }
    }

    public static String pojoToJson(Object object) {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ObjectMapperException();
        }
    }

    public static byte[] pojoToBytes(Object object) {
        try {
            return jsonMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new ObjectMapperException();
        }
    }

}
