package com.alippo.growskill.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="certificate")
@Data
public class Certificate {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)	
    @Column(name="certificate_id")	
	private int id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id",referencedColumnName = "enrollment_id")
    private Enrollment enrollment;
    
    @Column(name="download_link")	
	private String downloadLink;

    //hello
}
