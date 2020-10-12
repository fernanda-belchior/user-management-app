package br.com.fernanda.usermanagement.domain;

import br.com.fernanda.usermanagement.constant.TelephoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Telephone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ddd;
    private String number;
    private TelephoneType telephoneType;
    @ManyToOne
    private User user;
}
