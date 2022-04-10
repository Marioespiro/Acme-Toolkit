package acme.entities.patronageReports;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.patronages.Patronage;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {
	
	// Serialisation identifier

	protected static final long	serialVersionUID = 1L;

	// Attributes
		
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String memorandum;
	
	@URL
	protected String link;
	
	// Derived attributes
	
	@NotBlank
	@Pattern(regexp = "〈[0-9]+〉:〈[0-9]{4}〉")
	public String getAutomaticSequenceNumber() {
		return "〈" + this.getPatronage().getId() + "〉:〈" + this.getId() + "〉";
	}
	
	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patronage patronage;
	
}

