package com.cgv.CGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DirectorId implements Serializable {
    private static final long serialVersionUID = -7678481821273846682L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "director_name", nullable = false, length = 50)
    private String directorName;

    public DirectorId() {

    }
    public DirectorId(Integer id, String d) {
        this.movieId = id;
        this.directorName = d;
    }


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DirectorId entity = (DirectorId) o;
        return Objects.equals(this.directorName, entity.directorName) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, movieId);
    }

}