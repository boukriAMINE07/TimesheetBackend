package com.infoboukri.timesheet_backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultant_id;
    private String name;
    private String email;
    private Long phone;
    private String password;
/*    @OneToMany(mappedBy = "consultant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TaskOfConsultant> taskOfConsultantList;*/
}
