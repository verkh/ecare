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

    @Override
    public boolean equals(Object other) {
        if(other == this) return true;

        if(!(other instanceof DataChangeNotification)) return false;

        DataChangeNotification toCompare = (DataChangeNotification) other;
        return this.key.equals(toCompare.getKey()) &&
                this.sender.equals(toCompare.getSender());
    }
}
