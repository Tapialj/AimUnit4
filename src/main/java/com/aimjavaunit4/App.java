package com.aimjavaunit4;

import java.util.*;

import org.hibernate.*;
import org.hibernate.query.*;

public final class App 
{
    
    private App() 
    {
    
    }

    public static void main(String[] args) 
    {
        int     actorCount = 0,
                yearParse,
                movieIdHolder;
        String  sql,
                userInput;
        boolean movieExists = true,
                nextStep = false,
                movieAdded = false;
        NativeQuery<?> query;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        Scanner input = new Scanner(System.in);
        Movie movie = new Movie();
        Director director = new Director();
        Actor actor = new Actor();
        Rating rating = new Rating();
        ArrayList<MovieCast> cast = new ArrayList<MovieCast>();
        ArrayList<Actor> actors = new ArrayList<Actor>();


        System.out.println("\n\nWelcome to the movie db.\n");

        try 
        {
            tx = session.beginTransaction();

            //Check if ratings table is populated, if not populate it.
            sql = "SELECT * FROM ratings WHERE id = '1';";
            query = session.createSQLQuery(sql).addEntity(Rating.class);
            Rating ratingsCheck = (Rating) query.uniqueResult();

            if(ratingsCheck == null)
            {
                for(int i = 0; i < 4; i++)
                {
                    switch(i)
                    {
                        case 0:
                            rating = new Rating(1, "G");
                            break;
                        case 1:
                            rating = new Rating(2, "PG");
                            break;
                        case 2:
                            rating = new Rating(3, "PG-13");
                            break;
                        case 3:
                            rating = new Rating(4, "R");
                            break;
                    }

                    session.save(rating);
                }
            }
            
            //Get Movie name, loop until one that isn't in the DB is given
            do
            {
                System.out.println("Please enter the movie name: ");
                userInput = input.nextLine();
                userInput = userInput.trim();

                sql = "SELECT * FROM movies WHERE title = '" + userInput + "';";
                query = session.createSQLQuery(sql).addEntity(Movie.class);
                Movie movieCheck = (Movie) query.uniqueResult();

                if(movieCheck == null)
                {
                    movieExists = false;
                    movie.setTitle(userInput);
                }
                else
                {
                    System.out.println("This movie already exists in the db, please try another title.");
                }
            }while(movieExists);

            //Take in release year, loop until 4 digit int is entered.
            do
            {
                System.out.println("Please enter the movie release year: ");
                userInput = input.nextLine();
                userInput = userInput.trim();

                if(checkIfInt(userInput))
                {
                    yearParse = Integer.parseInt(userInput);

                    if(String.valueOf(yearParse).length() == 4)
                    {
                        nextStep = true;
                        movie.setYearReleased(yearParse);
                    }
                    else
                    {
                        System.out.println("Invalid input. Please enter a 4 digit year.");
                    }
                }
                else
                {
                    System.out.println("Invalid input. Please enter a 4 digit year.");
                }
            }while(!nextStep);

            nextStep = false;

            //Take in movie rating
            do
            {
                System.out.println("\nPlease choose a rating:\na) G\nb) PG\nc)PG-13\nd) R");
                userInput = input.nextLine();
                userInput = userInput.trim();

                if(userInput.equalsIgnoreCase("a") ||  userInput.equalsIgnoreCase("b") || userInput.equalsIgnoreCase("c") || userInput.equalsIgnoreCase("d"))
                {
                    nextStep = true;
                    switch(userInput)
                    {
                        case "a":
                        case "A":
                            movie.setRatingId(1);
                            break;
                        case "b":
                        case "B":
                            movie.setRatingId(2);
                            break;
                        case "c":
                        case "C":
                            movie.setRatingId(3);
                            break;  
                        case "d":
                        case "D":
                            movie.setRatingId(4);
                            break;
                    }
                }
                else
                {
                    System.out.println("Invalid input, please select a valid input");
                }
            }while(!nextStep);

            nextStep = false;

            //Take in directors Name, loop if empty string.
            do
            {
                System.out.println("\nPlease enter the directors name: ");
                userInput = input.nextLine();
                userInput = userInput.trim();

                if(userInput != "")
                {
                    nextStep = true;
                    director.setName(userInput);
                }
                else
                {
                    System.out.println("Invalid input.");
                }
            }while(!nextStep);

            System.out.println("\nPlease enter 3 actors who were in the movie: ");

            //Take in 3 actors and place them into array.
            do
            {
                userInput = input.nextLine();
                userInput = userInput.trim();

                if(userInput != "")
                {
                    //actors.add(userInput);
                    actors.add(new Actor(userInput));
                    cast.add(new MovieCast());

                    actorCount++;
                }
                else
                {
                    System.out.println("Invalid input. Enter an actor name.");
                }
            }while(actorCount < 3);

            //Check to see if director exists, if so place the id into the movie entity. If not, save director into directors DB and place id into movie entity
            sql = "SELECT * FROM directors WHERE UPPER(director_name) = UPPER('" + director.getName() + "');";
            query = session.createSQLQuery(sql).addEntity(Director.class);
            Director directorCheck = (Director) query.uniqueResult();

            if(directorCheck != null)
            {
                movie.setDirectorId(directorCheck.getId());
            }
            else
            {
                movie.setDirectorId((Integer) session.save(director));
            }

            //Save movie and get movie ID
            movieIdHolder= (Integer) session.save(movie);

            //Loop through 3 actors and check if they exist. If they do, place them into the movie cast with corresponding movie id. If they do not, save actor into DB and place ID with movie ID into move-cast.
            for(int i = 0; i < 3; i++)
            {
                actor = actors.get(i);

                sql = "SELECT * FROM actors WHERE UPPER(actor_name) = UPPER('" + actor.getName() + "');";
                query = session.createSQLQuery(sql).addEntity(Actor.class);
                Actor actorCheck = (Actor) query.uniqueResult();

                cast.get(i).setMovieId(movieIdHolder);

                if(actorCheck != null)
                {
                    cast.get(i).setActorId(actorCheck.getId());
                }
                else
                {
                    cast.get(i).setActorId((Integer) session.save(actor));
                }

                session.save(cast.get(i));
            }

            /**
            query = session.createSQLQuery(sql);
            query.addEntity(Movie.class);
            results = query.list();
            
            Movie movieTest = (Movie) session.get(Movie.class, 1);
            System.out.println("ID= " + movieTest.getMovieId());
            System.out.println("Movie " + movieTest.getTitle());

            for(Object object : results)
            {
                //System.out.println(object);
            }
            */

            tx.commit();

            movieAdded = true;
        }
        catch(Exception e) 
        {
            if (tx!=null)
            {
                tx.rollback();
            }
            
            e.printStackTrace(); 
        } 
        finally 
        {
            if(movieAdded)
            {
                System.out.println("\nMovie added. Thank you.\nGoodbye...");
            }
            else
            {
                System.out.println("\nThere was an issue. Movie not added.\nGoodbye...");
            }

            session.close();
            HibernateUtil.shutdown();
            input.close();
            System.exit(0);
        }
    }

    public static boolean checkIfInt(String check)
	{
		if(check == null)
			return false;
					
		try
		{
			int i = Integer.parseInt(check);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		
		return true;
	}

}
