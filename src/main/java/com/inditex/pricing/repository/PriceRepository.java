package com.inditex.pricing.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inditex.pricing.model.Price;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price> {
	
	Optional<Price> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);
	
	
	/**
	 * Busca el Price vigente, con mayor prioridad, para la fecha indicada
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM Price WHERE :date BETWEEN start_date AND end_date"
					+ " AND product_id = :productId AND brand_id = :brandId "
					+ " ORDER BY priority DESC LIMIT 1")
	Optional<Price> getValidPrice(
			@Param("date") LocalDateTime date,
			@Param("productId") Long productId,
			@Param("brandId") Long brandId);
	
}
