package com.studentCatering.repository;

import com.studentCatering.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
	
}