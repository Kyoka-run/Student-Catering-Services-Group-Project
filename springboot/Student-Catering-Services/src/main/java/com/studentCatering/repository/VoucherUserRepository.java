package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.VoucherUser;

@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser, Integer> {

	Optional<VoucherUser> findByUserid(Integer userid);
    
}
