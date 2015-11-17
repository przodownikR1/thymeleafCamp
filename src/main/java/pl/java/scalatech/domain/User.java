package pl.java.scalatech.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper=true)
public class User extends AbstractEntity{


    private  String firstname;
    private String lastName;
    private  String email;
    private String login;
    private String password;

    @ManyToMany(fetch = LAZY, cascade = { PERSIST, MERGE})
    @JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
    @Valid
    private List<Role> roles;
    @OneToMany
    @JoinColumn(name="userId")
    private List<Invoice> ownInvoice;

    @ManyToOne
    private Company company;
}
