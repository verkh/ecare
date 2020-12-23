package models;

import models.base.AbstractNamedPO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "plans")
public class PlanPO extends AbstractNamedPO {

    @Column(name = "price")
    private Double price;

    @JoinTable(
            name="plan_options",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    @OneToMany
    public List<OptionPO> options = new ArrayList<>();

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
