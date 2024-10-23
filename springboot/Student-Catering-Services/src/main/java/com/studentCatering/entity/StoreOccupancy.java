package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "stor_occup")
public class StoreOccupancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num", nullable = false)
    private int num;

    @Column(name = "time_stamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timeStamp;

    // Constructors, getters, and setters

    public StoreOccupancy() {
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
