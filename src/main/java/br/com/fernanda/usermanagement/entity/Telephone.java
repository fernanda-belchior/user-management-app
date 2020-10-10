package br.com.fernanda.usermanagement.entity;

import br.com.fernanda.usermanagement.constant.TelephoneType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Telephone implements Serializable {

    @Id
    private int id;
    private int ddd;
    private String number;
    private TelephoneType telephoneType;
    @ManyToOne
    private User user;

}
