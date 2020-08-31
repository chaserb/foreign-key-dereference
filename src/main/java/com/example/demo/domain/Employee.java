package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "employees")
@IdClass(EmployeeTenantKey.class)
@Data
@NoArgsConstructor
public class Employee {

    static final String ID_DEF = "varchar(36)";
    static final String TID_DEF = "char(15) default 'default'";

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = ID_DEF)
    @Setter(AccessLevel.NONE)
    private String id;

    @Id
    @Column(name = "tid", nullable = false, updatable = false, columnDefinition = TID_DEF)
    @Setter(AccessLevel.NONE)
    private String tid;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "tid", referencedColumnName = "tid")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "managerid", referencedColumnName = "id",
                    columnDefinition = ID_DEF, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)))
    })
    private Employee manager;

    @Builder
    public Employee(String tid, String title) {
        this.id = UUID.randomUUID().toString();
        this.tid = tid;
        this.title = title;
    }

}
