package com.learnjwt.jwt_learn.Entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projet {
    @Id
    @GeneratedValue
    private long id;
    private String Column_Name;
    private String Descritption;
    private String Column_Type;

}

