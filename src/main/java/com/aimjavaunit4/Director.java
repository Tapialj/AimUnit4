package com.aimjavaunit4;

import javax.persistence.*;

@Entity
@Table(name = "directors")
public class Director 
{
  
  @Id 
  @SequenceGenerator(name = "director_sequence", sequenceName = "director_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "director_sequence")
  @Column(name = "director_id")
  private int id;
  @Column(name = "director_name")
  private String name;
  
  
  public Director()
  {
  
  }

  public Director(String name)
  {
    this.name = name;
  }

  public Director(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public int getId() 
  {
    return id;
  }

  public void setId(int id) 
  {
    this.id = id;
  }

  public String getName() 
  {
    return name;
  }

  public void setName(String name) 
  {
    this.name = name;
  }

}
