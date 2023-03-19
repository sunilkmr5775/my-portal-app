package com.sunil.myportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EVENT_MASTER")
public class EventMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENT_ID")
    private long eventId;

    //  @NotNull
    @Column(name = "MODULE")         // ex: TASK, LOAN
    private String module;

    //  @NotNull
    @Column(name = "EVENT_TYPE")    // ADD/DELETE/TASK-COMPLETED/TAS-PENDING ETC, LOAN, JOB, LIFE INSURANCE
    private String eventType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Notification> notificationSet = new LinkedHashSet<>();


}
