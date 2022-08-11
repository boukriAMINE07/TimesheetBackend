package com.infoboukri.timesheet_backend.entities;

import com.infoboukri.timesheet_backend.enums.State;
import com.infoboukri.timesheet_backend.securite.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskOfConsultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Task task;
    private int duration;
    @Enumerated(EnumType.STRING)
    private State state;
}
