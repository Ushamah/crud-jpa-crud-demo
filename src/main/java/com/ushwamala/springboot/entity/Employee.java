package com.ushwamala.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ApiModel(description="Details of the employee")
public class Employee {

    //https://www.youtube.com/watch?v=8s9I1G4tXhA&ab_channel=JavaBrains for the api documentation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    @ApiModelProperty(notes="The unique id of the employee")
    private int id;

    @Column(name ="first_name")
    @NonNull
    @ApiModelProperty(notes="The first namr of the employee")
    private  String firstName;

    @Column(name ="last_name")
    @NonNull
    @ApiModelProperty(notes="The last name of the employee")
    private  String lastName;

    @Column(name ="email")
    @NonNull
    @ApiModelProperty(notes="The email of the employee")
    private  String email;
}
