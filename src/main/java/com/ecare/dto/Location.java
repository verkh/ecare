package com.ecare.dto;

import com.ecare.models.LocationPO;
import com.ecare.models.OptionPO;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Location {

    private Long id;
    private Double latitude;
    private Double longitude;

    public Location(LocationPO location) {
        this.id = location.getId();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public LocationPO toEntity() {
        LocationPO location = new LocationPO();
        location.setId(this.id);
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
        return location;
    }
}
