package com.example.objectmapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.objectmapper.model.Product;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    private final ObjectMapper om;

    public JsonService(ObjectMapper om) {
        this.om = om;
    }

    public String convertToJson(Product p) throws JsonProcessingException {
        return om.writeValueAsString(p);
    }

    public Product convertFromJson(String json) throws JsonProcessingException {
        return om.readValue(json, Product.class);
    }
}
