package com.infoboukri.timesheet_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import com.infoboukri.timesheet_backend.securite.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDto {
    private Long id;
    private String username;
    private String email;
}



