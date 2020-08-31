/*
 * Copyright (C) 2020 ideiio - All rights reserved.
 */
package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import static com.example.demo.domain.Employee.ID_DEF;
import static com.example.demo.domain.Employee.TID_DEF;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTenantKey implements Serializable {

    @Column(name = "id", nullable = false, columnDefinition = ID_DEF)
    private String id;

    @Column(name = "tid", nullable = false, columnDefinition = TID_DEF)
    private String tid;

}
