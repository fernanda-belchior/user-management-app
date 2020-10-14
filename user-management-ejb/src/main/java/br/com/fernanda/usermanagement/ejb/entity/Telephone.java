package br.com.fernanda.usermanagement.ejb.entity;

import br.com.fernanda.usermanagement.ejb.enums.TelephoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Telephone{}";
    }

}
