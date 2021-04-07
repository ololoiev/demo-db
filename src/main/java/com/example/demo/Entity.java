package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity{
    @Id
    private String id;
    private String type;
    private Map<String, Object> fields;
}
