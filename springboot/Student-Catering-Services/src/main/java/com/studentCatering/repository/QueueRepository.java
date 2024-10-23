package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Queue;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {
    
    List<Queue> findBySectionId(int sectionId);
    
    List<Queue> findByUserId(int userId);
    
    List<Queue> findBySectionIdAndUserId(int sectionId, int userId);
    
    List<Queue> findByOrderDateBetween(Timestamp startTime, Timestamp endTime);
    
    List<Queue> findBySectionIdAndOrderDateBetween(int sectionId, Timestamp startTime, Timestamp endTime);
    
    List<Queue> findByUserIdAndOrderDateBetween(int userId, Timestamp startTime, Timestamp endTime);
    
    List<Queue> findBySectionIdAndUserIdAndOrderDateBetween(int sectionId, int userId, Timestamp startTime, Timestamp endTime);
}