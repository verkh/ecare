package com.ecare.models;

import com.ecare.models.base.AbstractPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO describes option restrictions table in database
 */
@Entity
@Table(name = "option_rules")
@Getter
@Setter
@NoArgsConstructor
public class OptionRestrictionPO extends AbstractPO {

    /**
     * Creates restriction rule related to two options
     * @param id1 source option id
     * @param id2 related option id
     */
    public OptionRestrictionPO(Long id1, Long id2) {
        this.optionId1 = id1;
        this.optionId2 = id2;
        this.rule = Type.REQUIRES;
    }

    /**
     * Describes type of restriction
     */
    @Getter
    public enum Type {
        REQUIRES("Requires"),
        INCOMPATIBLE("Incompatible");

        private String typeStr;
        private Type(String value) {
            this.typeStr = value;
        }
    }

    @Column(name = "option_id1")
    private Long optionId1;

    @Column(name = "option_id2")
    private Long optionId2;

    @Column(name = "rule")
    @Enumerated(EnumType.STRING)
    private Type rule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="option_id1", nullable=false, insertable = false, updatable = false)
    private OptionPO option;
}
