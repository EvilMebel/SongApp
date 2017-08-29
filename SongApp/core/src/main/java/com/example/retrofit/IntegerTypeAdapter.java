package com.example.retrofit;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    public static final int INT_ERROR_CODE = -1;

    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        if (value == null)
            out.nullValue();
        else
            out.value(value);
    }

    @Override
    public Integer read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case NULL:
                in.nextNull();
                return -1;
            case NUMBER:
                return in.nextInt();
            case STRING:
                return getIntegerFromString(in);
            default:
                throw new IllegalStateException("Expected INTEGER or STRING but was " + peek);
        }
    }

    private Integer getIntegerFromString(JsonReader in) throws IOException {
        String string = in.nextString();
        int parseInt = INT_ERROR_CODE;
        if (!string.isEmpty())
            return getInteger(string);
        return parseInt;
    }

    private Integer getInteger(String string) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(string);
            return parseInt;
        } catch (NumberFormatException e) {
            return INT_ERROR_CODE;
        }
    }
}