package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
