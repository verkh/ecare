package com.ecare.models;

import com.ecare.models.base.AbstractPO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents locations of a user
 */
@Entity
@Table(name = "user_locations")
@Getter
@Setter
public class LocationPO extends AbstractPO {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="user_id")
    private UserPO user;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lon")
    private Double longitude;
}
