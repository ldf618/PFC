package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;


/**
 *
 * @author Lo
 */
@Data


@MappedSuperclass
public abstract class IdentityIntId implements Serializable{
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}
