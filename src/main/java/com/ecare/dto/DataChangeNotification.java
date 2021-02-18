package com.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Communication message which notifies about changes in database
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataChangeNotification {
    final private String key = "ecare.changed";
    private String sender;
}
