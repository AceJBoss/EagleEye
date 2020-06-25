package com.jboss.eagleeye.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "municipals", uniqueConstraints = {@UniqueConstraint(columnNames = {"municipal_name"})})
public class Municipal extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String municipal_name;
    private double latitude;
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @JsonIgnore
    @OneToMany(mappedBy = "municipal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "municipal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agency>agencies;

    @JsonIgnore
    @OneToMany(mappedBy = "municipal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incident> incidents;

}
