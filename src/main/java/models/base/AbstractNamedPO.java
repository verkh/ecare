package models.base;

import javax.persistence.*;

@MappedSuperclass
public class AbstractNamedPO extends AbstractPO {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
