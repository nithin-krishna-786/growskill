package com.alippo.growskill.entities;

import java.util.List;

import com.alippo.growskill.exceptions.ForeignKeyConstraintViolationException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student extends User{
	
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",orphanRemoval = true)		
    private List<Enrollment> enrollments;	
    
    private String college;
    
    private String location;
    
    public void addEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }
        if (enrollment.getStudent() != null && !enrollment.getStudent().equals(this)) {
            throw new ForeignKeyConstraintViolationException("Enrollment already associated with another student");
        }
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
    }

}
