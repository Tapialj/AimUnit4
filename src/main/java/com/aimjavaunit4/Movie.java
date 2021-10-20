package com.aimjavaunit4;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie 
{
  
  @Id 
  @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
  @Column(name = "movie_id")
  private int movieId;
  @Column(name = "title")
  private String title;
  @Column(name = "year_released")
  private int yearReleased;
  @Column(name = "rating_id")
  private int ratingId;
  @Column(name = "director_id")
  private int directorId;
     
  
  public Movie()
  {
         
  }

  public Movie(String title, int yearReleased, int ratingId, int directorId) 
  {
    this.title = title;
    this.yearReleased = yearReleased;
    this.ratingId = ratingId;
    this.directorId = directorId;
  }



  public Movie(int movieId, String title, int yearReleased, int ratingId, int directorId)
  {
    this.movieId = movieId;
    this.title = title;
    this.yearReleased = yearReleased;
    this.ratingId = ratingId;
    this.directorId = directorId;
  }

  public int getMovieId() 
  {
    return movieId;
  }

  public void setMovieId(int movieId) 
  {
    this.movieId = movieId;
  }

  public String getTitle() 
  {
    return title;
  }

  public void setTitle(String title) 
  {
    this.title = title;
  }

  public int getYearReleased() 
  {
    return yearReleased;
  }

  public void setYearReleased(int yearReleased) 
  {
    this.yearReleased = yearReleased;
  }

  public int getRatingId() 
  {
    return ratingId;
  }

  public void setRatingId(int ratingId) 
  {
    this.ratingId = ratingId;
  }

  public int getDirectorId() 
  {
    return directorId;
  }

  public void setDirectorId(int directorId) 
  {
    this.directorId = directorId;
  }

}