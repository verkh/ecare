package com.ecare.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Plan {
    private Long id;
    private String name;
    private Double price;
    private List<Option> options;
}
