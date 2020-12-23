package models.base;

import javax.persistence.*;

/**
 * This class describes basic field for every entity in database
 */
@MappedSuperclass
public class AbstractPO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

