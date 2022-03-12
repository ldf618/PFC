package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 *
 * @author Lo
 */

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class IdentityIntId implements Serializable{
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}
