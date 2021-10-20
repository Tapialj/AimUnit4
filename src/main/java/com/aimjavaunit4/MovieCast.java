package com.aimjavaunit4;

import javax.persistence.*;

@Entity
@Table(name = "movie_cast")
public class MovieCast 
{

  @Id 
  @SequenceGenerator(name = "cast_sequence", sequenceName = "cast_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cast_sequence")
  @Column(name = "cast_id")
  private int castId;
  @Column(name = "actor_id")
  private int actorId;
  @Column(name = "movie_id")
  private int movieId;
  
  
  public MovieCast()
  {

  }

  public MovieCast(int actorId, int movieId)
  {
    this.actorId = actorId;
    this.movieId = movieId;
  }
  
  public MovieCast(int castId, int actorId, int movieId) 
  {
    this.castId = castId;
    this.actorId = actorId;
    this.movieId = movieId;
  }

  public int getCastId() 
  {
    return castId;
  }

  public void setCastId(int castId) 
  {
  
    this.castId = castId;
  }

  public int getActorId() 
  {
    return actorId;
  }

  public void setActorId(int actorId) 
  {
    this.actorId = actorId;
  }

  public int getMovieId() 
  {
    return movieId;
  }

  public void setMovieId(int movieId) 
  {
    this.movieId = movieId;
  }

}