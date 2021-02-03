package com.ecare.models.base;

import com.ecare.models.OptionPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AttachedOption extends AbstractPO {
    @OneToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id")
    OptionPO option;

    @Column(name = "undisablable")
    boolean undisablable;
}
