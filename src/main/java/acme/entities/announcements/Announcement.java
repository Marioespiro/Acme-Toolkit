package acme.entities.announcements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity {
	
	// Serialisation identifier

	protected static final long	serialVersionUID = 1L;
	
	// Attributes 
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date CreationDate;
	
	@NotBlank
	@Length(min=1, max=100)
	protected String Title;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String Body;
	
	@NotNull
	protected Boolean flag;
	
	@URL
	protected String Url;
	
	
}
