package com.cgv.CGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MoviegenreId implements Serializable {
    private static final long serialVersionUID = 1173644943897324763L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "genre", nullable = false, length = 50)
    private String genre;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MoviegenreId entity = (MoviegenreId) o;
        return Objects.equals(this.genre, entity.genre) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, movieId);
    }

}