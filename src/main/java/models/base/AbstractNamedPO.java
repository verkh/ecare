package models.base;

import javax.persistence.*;

/**
 * This class describes basic field for every entity in
 * database which has a 'name' column
 */
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
