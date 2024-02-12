package com.sunil.myportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "STATE")
public class State {

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "STATE_CODE")
    private String stateCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @JsonIgnore
    @Column(name = "CREATED_BY")
    private String createdBy;

    @JsonIgnore
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COUNTRY_ID")
    @JsonIgnore
    private Country country;


    public State() {

    }
}
