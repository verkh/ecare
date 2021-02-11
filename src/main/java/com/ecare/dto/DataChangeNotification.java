package com.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataChangeNotification implements Serializable {
    final private String key = "ecare.changed";
    private String sender;
}
