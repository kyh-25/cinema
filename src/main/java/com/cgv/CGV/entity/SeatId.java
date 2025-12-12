package com.cgv.CGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatId implements Serializable {
    private static final long serialVersionUID = 8011360022151454319L;
    @Column(name = "screen_id", nullable = false)
    private Integer screenId;

    @Column(name = "row_no", nullable = false)
    private Integer rowNo;

    @Column(name = "col_no", nullable = false)
    private Integer colNo;

    public SeatId() {

    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getColNo() {
        return colNo;
    }

    public void setColNo(Integer colNo) {
        this.colNo = colNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeatId entity = (SeatId) o;
        return Objects.equals(this.screenId, entity.screenId) &&
                Objects.equals(this.rowNo, entity.rowNo) &&
                Objects.equals(this.colNo, entity.colNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screenId, rowNo, colNo);
    }

    public SeatId(Integer rowNo, Integer colNo, Integer screenId) {
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.screenId = screenId;
    }

}