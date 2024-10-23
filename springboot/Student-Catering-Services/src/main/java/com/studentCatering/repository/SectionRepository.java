package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    @Query(value = "SELECT * FROM section i WHERE i.name LIKE %:keyword% OR i.description LIKE %:keyword%", nativeQuery = true)
    List<Section> searchSections(@Param("keyword") String keyword);
}