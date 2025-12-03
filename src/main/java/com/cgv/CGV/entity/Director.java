package com.cgv.CGV.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "director")
public class Director {
    @EmbeddedId
    private DirectorId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private com.cgv.CGV.entity.Movie movie;

    public DirectorId getId() {
        return id;
    }

    public void setId(DirectorId id) {
        this.id = id;
    }

    public com.cgv.CGV.entity.Movie getMovie() {
        return movie;
    }

    public void setMovie(com.cgv.CGV.entity.Movie movie) {
        this.movie = movie;
    }

}