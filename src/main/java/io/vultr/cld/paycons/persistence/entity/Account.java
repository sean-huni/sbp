package io.vultr.cld.paycons.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accNo; //Account-Number
    private String name; //Client-Name
    private String descr; //Description
    //    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Collection<Tx> transactions;
}
