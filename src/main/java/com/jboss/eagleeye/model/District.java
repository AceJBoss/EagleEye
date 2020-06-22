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
@Table(name = "districts", uniqueConstraints = {@UniqueConstraint(columnNames = { "district_name" }) })
public class District {
    private Long id;
    private String district_name;
    private double lat;
    private double lon;

    @JsonIgnore
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Municipal> munipals;
}
