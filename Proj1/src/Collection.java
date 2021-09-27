/**
Container class defines the array list data structure to hold the album collection.
@author David Halim, Stephen Juan
*/
public class Collection
{
    private static final int DEFAULT_SIZE = 10;
	private Album[] albums;
    private int numAlbums = 1; //number of albums currently in the collection
    
    /**
    Overloaded constructor initializes size of albums array and numAlbums.
    Initializes size of albums array to an accepted value albumsSize.
    Initializes numAlbums = 0 (empty collection).
    @param albumsSize accepted value for size of albums array.
    */
    public Collection()
    {
        albums = new Album[numAlbums];
    }
    
    /**
    find the album index, or return (-1) NOT_FOUND.
    @param album to be found.
    @return index if found, returns -1 otherwise. 
    */
    private int find(Album album)
    {
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null) 
            { 
                continue; 
            }
            if (album.equals(albums[i]))
            {
                return i; // return the index of the album if it exists in the collection
            }
        }
        
        return -1; // return -1 if the album does not exist in the collection
    }
    
    /**
    increase the capacity of the array list by 4
    */
    private void grow()
    {
        int growValue = 4;
    	Album[] newAlbums = new Album[albums.length + growValue];
        
        for (int i = 0; i < albums.length; i++)
        {
            newAlbums[i] = albums[i];
        }
        for (int i = albums.length; i < newAlbums.length; i++)
        {
        	newAlbums[i] = null; 
        }
        
        albums = newAlbums;
    }
    
    /**
    Adds an album to the collection.
    Calls find(album) to make sure the new does not
    exist in the array to eliminate duplicates.
    @param album to add to the collection
    @return true if the add is successful, 
            false if the album already exists in the collection.
     */
    public boolean add(Album album)
    {
        if (numAlbums == 0) // base case when first album is added to the collection albums.length = 1
        {
            albums[0] = album;
            numAlbums++;
            return true; 
        }
        else
        {
    	    int albumIndex = find(album);
            
            if (albumIndex != -1) // if the album is already in the collection
            {
                return false;
            }
            
            if (numAlbums >= albums.length)
            {
                grow();
            }
            
            albums[numAlbums] = album;
            numAlbums++;
            return true;
        }
    }
    
    /**
    Removes an album from the collection.
    Calls find(album) to get the index to remove.
    Maintains the current sequence of albums
    in the array after the deletion.
    @param album to remove from the collection.
    @return true if the remove is successful,
            false if the album does not exist in the class.
    */
    public boolean remove(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1) // if its unable to be found
        {
            return false;
        }
        
        albums[albumIndex] = null;
        for (int i = albumIndex; i < numAlbums; i++)
        {
            albums[i] = albums[i+1];
        }
       // albums[numAlbums+1] = null; 
        numAlbums--;
        
        return true;
    }
    
    /**
    Sets an album in the collection to not available.
    @param album to set to not available.
    @return true if the lendingOut is successful,
            false if the album does not exist in the class.
    */
    public boolean lendingOut(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1 || !albums[albumIndex].isAvailableBoolean())
        {
            return false;
        }
        
        albums[albumIndex].setIsAvailable(false);
        return true;
    }
    
    /**
    Sets an album in the collection to available.
    @param album to set to available.
    @return true if the returnAlbum is successful,
            false if the album does not exist in the class.
    */
    public boolean returnAlbum(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1 || albums[albumIndex].isAvailableBoolean())
        {
            return false;
        }
        
        albums[albumIndex].setIsAvailable(true);
        return true;
    }
    
    /**
    Prints out the list without specifying the order.
    */
    public void print()
    {
        System.out.println("*List of albums in the collection.");
        
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            } 
            System.out.println(albums[i].toString());
        }
        
        System.out.println("*End of list");
    }
    
    /**
    This method uses selection sort to sort the album by its release date, from oldest to youngest. It leaves the album 
    in a sorted form. 
    */    
    public void sortByReleaseDate()
    {
        // use selection sort
        int indexOfSmallest = 0; 
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null) { continue; }
            Album smallest = albums[i]; // we compare the first to everything else
            for (int j = i; j < albums.length; j++)
            {
                if (albums[j] == null) 
                { 
                    continue; 
                }
                if (smallest.getReleaseDate().compareTo(albums[j].getReleaseDate()) == 1) // if albums[j] date is less than smallest
                {
                    // set smallest to album[j] and continue
                    smallest = albums[j];
                    indexOfSmallest = j; 
                }
            }
            // swap smallest with album[i]
            Album temp = new Album();
            temp = albums[i];
            albums[i] = albums[indexOfSmallest]; 
            albums[indexOfSmallest] = temp; 
        }
    }
    
    /**
    This method calls the sortByReleaseDate method and prints out the contents of the collection. 
    */
    public void printByReleaseDate()
    {
        System.out.println("*Album collection by the release dates.");
        
        sortByReleaseDate(); 
        
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null) { continue; }
            System.out.println(albums[i].toString());
        }
        
        System.out.println("*End of list");
    }
    
    /**
    This method finds all the instances of Classical, Country, Jazz, Pop and Unknown albums in the collection
    and prints them out by genre, in that order mentioned above. 
    */
    public void sortByGenre()
    {	
        // search for classical Genre and put them in sortedAlbum
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            }
            Genre currentGenre = albums[i].getGenre();
            if (currentGenre.toString().contentEquals("Classical"))
            {
                System.out.println(albums[i].toString());
            }
        }
        // search for country Genre and put them in sortedAlbum
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            }
    	    Genre currentGenre = albums[i].getGenre();
    	    if (currentGenre.toString().contentEquals("Country"))
    	    {
        	    System.out.println(albums[i].toString());
        	}
    	}
    	// search for jazz Genre and put them in sortedAlbum
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            }
            Genre currentGenre = albums[i].getGenre();
            if (currentGenre.toString().contentEquals("Jazz"))
            {
                System.out.println(albums[i].toString());
            }
        }
    	// search for pop Genre and put them in sortedAlbum
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            }
            Genre currentGenre = albums[i].getGenre();
            if (currentGenre.toString().contentEquals("Pop"))
            {
                System.out.println(albums[i].toString());
            }
        }
        // search for unknown Genre and put them in sortedAlbum
        for (int i = 0; i < albums.length; i++)
        {
            if (albums[i] == null)
            { 
                continue; 
            }
            Genre currentGenre = albums[i].getGenre();
            if (currentGenre.toString().contentEquals("Unknown"))
            {
                System.out.println(albums[i].toString());
            }
        }
    }
    
    /**
    This method calls sortByGenre, which, in turns, prints out the collection sorted by genre.
    */
    public void printByGenre()
    {     
        System.out.println("*Album collection by genre.");
        
        sortByGenre(); 
        
        System.out.println("*End of list");
    }
    
    /**
    This method returns the number of albums in the collection.
    * @return numAlbums - 1 - actual number of Albums in the collection
    */
    public int numberOfAlbums()
    {
        return numAlbums - 1; 
    }
}