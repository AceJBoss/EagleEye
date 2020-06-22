package com.jboss.eagleeye.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agencies", uniqueConstraints = {@UniqueConstraint(columnNames = {"agency_name"})})
public class Agency extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agency_name;
    private String email;
    private String password;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipal_id")
    private Municipal municipal;

}
