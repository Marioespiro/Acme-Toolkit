package acme.entities.quantities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.entities.items.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity {
	
	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;
	
	// Attributes
	
	@PositiveOrZero
	protected int amount;
	
	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Item			item;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Toolkit			toolkit;

}
