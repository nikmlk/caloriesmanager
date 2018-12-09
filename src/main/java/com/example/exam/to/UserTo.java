package com.example.exam.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTo {

    public interface CreateUser{}
    public interface UpdateUser{}

    @Null(groups = CreateUser.class)
    @NotNull(groups = UpdateUser.class)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;
}
