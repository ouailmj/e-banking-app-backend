package com.example.ebankingspg.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eb_operators")
public class Operator extends AbstractAuditableEntity<User,Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonIgnore
    private String apiKey;
    @JsonIgnore
    private String host;

    @JsonIgnore
    @ManyToOne
    private Client client;
}
