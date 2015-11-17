package pl.java.scalatech.domain;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@ToString
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Getter
    @Setter
    protected Long id;

}
