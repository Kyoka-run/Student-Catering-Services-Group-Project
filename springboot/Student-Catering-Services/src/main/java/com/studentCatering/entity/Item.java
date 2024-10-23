package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "section_id", nullable = false)
    private int sectionId;

    @Column(name = "qnty")
    private Integer quantity;

    @Column(name = "val", precision = 6, scale = 2, nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ItemStatus status;

    @Column(name = "create_date", nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createDate;

    // Constructors, getters, and setters
    // Constructors
    public Item() {
    }

    public Item(String name, int sectionId, Integer quantity, BigDecimal value, ItemStatus status) {
        this.name = name;
        this.sectionId = sectionId;
        this.quantity = quantity;
        this.value = value;
        this.status = status;
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

    // sectionId
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    // quantity
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // value
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    // status
    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    // createDate
    public Timestamp getCreateDate() {
        return createDate;
    }

    // Enums
    public enum ItemStatus {
        IN_STOCK, OUT_OF_STOCK
    }
}