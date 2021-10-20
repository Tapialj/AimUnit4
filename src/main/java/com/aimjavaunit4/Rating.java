package com.aimjavaunit4;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating
{
  
  @Id 
  @Column(name = "id")
  private int ratingId;
  @Column(name = "rating")
  private String rating;
  
  
  public Rating()
  {

  }
  
  public Rating(int ratingId, String rating) 
  {
    this.ratingId = ratingId;
    this.rating = rating;
  }

  public int getRatingId() 
  {
    return ratingId;
  }

  public void setRatingId(int ratingId) 
  {
    this.ratingId = ratingId;
  }

  public String getRating() 
  {
    return rating;
  }

  public void setRating(String rating) 
  {
    this.rating = rating;
  }

}
