package com.gp.learners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gp.learners.entities.FuelPayment;

public interface FuelPaymentRepository extends JpaRepository<FuelPayment,Integer>{
	
	@Query(value="select * from fuel_payment where month = :month",nativeQuery=true)
	public FuelPayment findByMonth(Integer month);
	
	@Query(value="select sum(amount) from fuel_payment where month = :month",nativeQuery=true)
	public Double findPaymentByMonth(@Param("month") Integer month);
	
}