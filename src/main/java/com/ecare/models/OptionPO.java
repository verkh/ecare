package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OptionsPO is a class which describes an option represented in database.
 */
@Entity
@Table (name = "options")
@Getter
@Setter
@NoArgsConstructor
public class OptionPO extends AbstractNamedPO {
// FIXME: add equals method
    public OptionPO(Long id) {
        this.setId(id);
    }

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "turn_on_price")
    private Double turnOnPrice;

    @Transient
    private boolean enabled;

    @OneToMany(mappedBy="option", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OptionRestrictionPO> restrictions = new ArrayList<>();
}
