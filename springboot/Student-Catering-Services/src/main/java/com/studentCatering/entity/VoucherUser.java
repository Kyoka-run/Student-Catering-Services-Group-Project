package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "voucher_users")
public class VoucherUser {
	public enum VoucherStatus {
	    READY,
	    EXPIRED,
	    USED
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userid;

    @Column(name = "voucher_id", nullable = false)
    private int voucherid;

    @Column(name = "code", nullable = false, length = 50, unique = true)
    private String code;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VoucherStatus status;

    @Column(name = "valid_until", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date validUntil;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(int voucherid) {
        this.voucherid = voucherid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public void setStatus(VoucherStatus status) {
        this.status = status;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
