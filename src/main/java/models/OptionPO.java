package models;

import models.base.AbstractNamedPO;

import javax.persistence.*;

@Entity
@Table (name = "options")
public class OptionPO extends AbstractNamedPO {
    @Column(name = "price")
    private Double price;

    @Column(name = "turn_on_price")
    private Boolean isOn;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }
}
