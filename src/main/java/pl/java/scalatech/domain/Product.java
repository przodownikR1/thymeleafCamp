package pl.java.scalatech.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect
@ToString(callSuper=true)

public class Product extends AbstractEntity{

    private String name;
    private BigDecimal price;
    private boolean availability;


}

