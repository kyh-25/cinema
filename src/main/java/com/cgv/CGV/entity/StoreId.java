package com.cgv.CGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoreId implements Serializable {
    private static final long serialVersionUID = -5756552073093635113L;
    @Column(name = "food_id", nullable = false)
    private Integer foodId;

    @Column(name = "theater_id", nullable = false)
    private Integer theaterId;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoreId entity = (StoreId) o;
        return Objects.equals(this.theaterId, entity.theaterId) &&
                Objects.equals(this.foodId, entity.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theaterId, foodId);
    }

}