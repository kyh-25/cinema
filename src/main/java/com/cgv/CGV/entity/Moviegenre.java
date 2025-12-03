package com.cgv.CGV.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "moviegenre")
public class Moviegenre {
    @EmbeddedId
    private MoviegenreId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public MoviegenreId getId() {
        return id;
    }

    public void setId(MoviegenreId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}