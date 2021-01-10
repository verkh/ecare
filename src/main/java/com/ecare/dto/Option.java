package com.ecare.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Option {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double turnOnPrice;
}
