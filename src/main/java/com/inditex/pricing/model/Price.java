package com.inditex.pricing.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.inditex.pricing.model.base.AbstractPersistentObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Price extends AbstractPersistentObject {

	private static final long serialVersionUID = 3783020132714541245L;

	@NotNull
	private Long brandId;
	
	@NotNull
	private LocalDateTime startDate;
	
	@NotNull
	private LocalDateTime endDate;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Integer priority;
	
	@NotNull
	private Double priceValue;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private CurrencyISO curr;
	
}
