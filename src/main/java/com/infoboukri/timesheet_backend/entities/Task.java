package com.infoboukri.timesheet_backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)

    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "task")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TaskOfConsultant> taskOfConsultantList;

}
