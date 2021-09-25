/**
This class is the client driver class that takes in user-inputted commands and information
and does functions with them using the Collection class
@author Stephen Juan, David Halim
*/

import java.util.Scanner;
import java.util.StringTokenizer;

public class CollectionManager
{
	/**
	This method creates a new collection and repeatedly takes input with a while loop via Java Scanner and
	uses methods from other classes to add, delete, lend or return them. It also checks to see that the 
	input is in the correct format. 
	*/
    public void run()
    {
        System.out.println("Collection Manager starts running."); 

        Collection collection = new Collection();  
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine(); 
        String[] command = new String(5); 
        command = albumTokenizer(userInput); 

        // loop that repeatly takes in inputs until Q is entered as a command
        while (keyboard.hasNext() && !command[0].equals("Q"))
        {
			// case for invalid date (ignores empty date)
            Date date = new Date(command[4]);
            if (command[4] != "" && !date.isValid()) 
            { 
                System.out.println("Invalid date!"); 
                continue; 
            }
			// case for invalid format/wrong number of tokens
            if (command == null) 
            { 
                System.out.println("Invalid command!"); 
                continue; 
            } 

            Album album = new Album();
            album = albumProcessor(command);
            // does the commands
            switchCommand(command[0], album, collection);
            userInput = keyboard.nextLine();
            command = albumTokenizer(userInput); 
        }
    }

    /**
    This method splits up the user input as a string into separate tokens that represents command, title,
    artist, genre and date or title and artist. If there is only three tokens, it fill the rest of the array
    with empty string "". It returns null for invalid input format and an array of strings of the tokens.
    @param input - string that is to be split up using StringTokenizer
    @return null if it is in improper format, an array of five strings if otherwise
    */
    public String[] albumTokenizer(String input)
    {
        StringTokenizer inputString = new StringTokenizer(input,",");

        int numberOfTokens = inputString.countTokens(); 
		
        // return null for invalid command
        if (numberOfTokens != 3 || numberOfTokens != 5) 
        { 
            return null; 
        }

        String[] arrayOfTokens = new String[5];

        // sets everything to empty string
        for (int i= 0; i < arrayOfTokens.length; i++)
        {	
            arrayOfTokens[i] = "";
        }

        // fills in applicable values and counter the number of tokens
        for (int i = 0; i < numberOfTokens; i++) 
        {
            arrayOfTokens[i] = inputString.nextToken(); 
        }

        return arrayOfTokens;
    }

    /**
    This method creates an Album object using the array of Strings in albumTokenizer method. It returns
    the Album object.
    @param inputArray
    @return Album which has its attributes of title, artist, date and genre
    */
    public Album albumProcessor(String[] inputArray)
    {
        // creates Genre object and set it to the corresponding value in the enum class
        String genreString = inputArray[3];
        Genre genre = new Genre(); 
        if (genreString.equalsIgnoreCase("CLASSICAL")) 
        { 
            genre = Genre.Classical; 
        }
        else if (genreString.equalsIgnoreCase("COUNTRY"))   
        { 
            genre = Genre.Country;   
        }
        else if (genreString.equalsIgnoreCase("JAZZ"))      
        { 
            genre = Genre.Jazz;      
        }
        else if (genreString.equalsIgnoreCase("POP"))       
        { 
            genre = Genre.Pop;       
        }
        else                                                
        { 
            genre = Genre.Unknown;   
        }

        // create album object with correct attributes
        String title = inputArray[1];
        String artist = inputArray[2]; 
        String date = inputArray[3]; 
        // this one may be bad if Album constructor does not have genre as Genre class
        Album album = new Albumn(title, artist, genre, date);

        return album;
    }

    /**
    This method utilizes a command that is identified in the user input string and performs the 
    functions that the user requests. It prints out whether or not the requested action was able
    to be performed.
    @param command    - the command the user inputs - either A, D, L, R, P, PD, PG
    @param album      - the album the user wants to modify in the collection
    @param collection - the collection the user wants to modify
    */
    public void switchCommand(String command, Album album, Collection collection)
    {
        // adds the album if it is not already in the collection (T), otherwise prints that it is in the collection (F)
        if (command.equals("A"))
        {
            if (collection.add(album))
            {
                System.out.println(album.toString() + " >> is added."); 
            }
            else
            {
                System.out.println(album.toString() + " >> is already in the collection.");
            }
        }
        // removes the album if it is in the collection (T), if not, prints out that its not in the collection (F)
        // NOTE: getTitleArtist() is a method to be used for L, R, D that prints only title and artist
        else if (command.equals("D"))
        {
            if (collection.remove(album))
            {
                System.out.println(album.getTitleArtist() + " >> deleted."); 
            }
            else 
            {
                System.out.println(album.getTitleArtist() + " >> is not in the collection."); 
            }
        }
        // lends the album if it is in the collection (T), otherwise prints unavailable (F)
        else if (command.equals("L"))
        {
            if (collection.lendingOut(album))
            {	
                System.out.println(album.getTitleArtist() + " >> lending out and set to not available."); 
            }
            else
            {
                System.out.println(album.getTitleArtist() + " >> is not in the collection.");
            }
        }
        // returns the album if its is on a return list (T), otherwise prints that it cannot be completed (F)
        else if (command.equals("R"))
        {	
            if (collection.returnAlbum(album))
            {
                System.out.println(album.getTitleArtist() + " >> returning and set to available."); 
            }	
            else
            {
                System.out.println(album.getTitleArtist() + " >> return cannot be completed."); 
            }
        }	
        // prints entire collection, no sort
        else if (command.equals("P")) 
        {
            collection.print(); 	
        }
        // prints entire collection, sorted by date
        else if (command.equals("PD"))
        {
            collection.printByReleaseDate();
        }
        // prints entire collection, sorted by genre
        else if (command.equals("PG"))
        {	
            collection.printByGenre(); 
        }
        // if the command is not on the commandlist, print invalid
        else
        {
            System.out.println("Invalid command!"); 
        }
    }
}
