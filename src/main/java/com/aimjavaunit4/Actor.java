package com.aimjavaunit4;

import javax.persistence.*;

@Entity
@Table(name = "actors")
public class Actor 
{
  
  @Id 
  @SequenceGenerator(name = "actor_sequence", sequenceName = "actor_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_sequence")
  @Column(name = "actor_id")
  private int id;
  @Column(name = "actor_name")
  private String name;
  
  
  public Actor()
  {

  }
 
  public Actor(String name)
  {
    this.name = name;
  }

  public Actor(int id, String name) 
  {
    this.id = id;
    this.name = name;
  }

  public int getId() {
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
