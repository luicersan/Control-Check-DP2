package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Pattern(regexp = "^[0-9]{6}$")
	protected String			code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date creationMoment;

	@NotBlank
	@Length(max = 100)
	protected String			title;

	@NotBlank
	@Length(max = 255)
	protected String			description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishPeriod;

	@NotNull
	@Valid
	protected Money				budget;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne(optional=false)
	protected Item item;
}