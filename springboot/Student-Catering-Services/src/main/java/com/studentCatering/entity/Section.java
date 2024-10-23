package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_date", nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createDate;

    // Constructors, getters, and setters
    // Constructors
    public Section() {
    }

    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    // id
    public int getId() {
        return id;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // createDate
    public Timestamp getCreateDate() {
        return createDate;
    }
}