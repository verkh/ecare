package models.base;

import javax.persistence.*;

@MappedSuperclass
public class AbstractNamedPO extends AbstractPO {
    @Id
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
