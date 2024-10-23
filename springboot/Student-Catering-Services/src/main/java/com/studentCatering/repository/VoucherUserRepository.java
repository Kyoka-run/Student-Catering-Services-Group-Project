package com.studentCatering.repository;

import java.util.Optional;

import com.studentCatering.entity.VoucherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser, Integer> {

	Optional<VoucherUser> findByUserid(Integer userid);
    
}
