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
    Default constructor initializes size of albums array to DEFAULT_SIZE.
    DEFAULT_SIZE is a static final int = 10.
    Calls the Overloaded constructor passing the value DEFAULT_SIZE.
    Initializes numAlbums = 0 (empty collection).
    */
    /*public Collection()
    {
        this(DEFAULT_SIZE);
    }*/
    
    /**
    Overloaded constructor initializes size of albums array and numAlbums.
    Initializes size of albums array to an accepted value albumsSize.
    Initializes numAlbums = 0 (empty collection).
    @param albumsSize accepted value for size of albums array.
    */
    public Collection()
    {
        albums = new Album[numAlbums];
        //numAlbums = 0;
    }
    
    /**
    find the album index, or return (-1) NOT_FOUND.
    @param album to be found.
    @return index if found, returns -1 otherwise. 
    */
    private int find(Album album)
    {
        for (int i = 0; i < albums.length && albums[i] != null; i++)
        {
            if (album.equals(albums[i]))
            {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
    increase the capacity of the array list by 4
    */
    private void grow()
    {
        int growValue = 4;
    	Album[] albums = new Album[this.albums.length + growValue];
        
        for (int i = 0; i < this.albums.length; i++)
        {
            albums[i] = this.albums[i];
        }
        for (int i = this.albums.length; i < albums.length; i++)
        {
        	albums[i] = null; 
        }
        
        this.albums = albums;
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
    	if (numAlbums == 1)
    	{
            albums[numAlbums-1] = album;
            numAlbums++;
            return true; 
    	}
    	else
    	{
    		//System.out.println("album length: " + albums.length);
    		//System.out.println("num albums: " + numAlbums); 
    		int albumIndex = find(album);
            
            if (albumIndex != -1)
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
        // 
        for (int i = albumIndex; i < numAlbums; i++)
        {
            albums[i] = albums[i + 1];
        }
        
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
        
        if (albumIndex == -1)
            return false;
        
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
        
        if (albumIndex == -1)
            return false;
        
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
            System.out.println(albums);
        }
        
        System.out.println("*End of list");
    }
    
    private void mergeByReleaseDate(int first, int middlePoint, int last)
    {
        int leftArraySize = middlePoint - first + 1;
        int rightArraySize = last - middlePoint;
        
        Album[] leftArray = new Album[leftArraySize];
        Album[] rightArray = new Album[rightArraySize];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < leftArraySize; ++i)
            leftArray[i] = albums[first + i];
        
        for (int j = 0; j < rightArraySize; ++j)
            rightArray[j] = albums[middlePoint + 1 + j];
  
        /* Merge the temp arrays */
        
        int i = 0, j = 0;
        int k = first;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i].getReleaseDate().compareTo(rightArray[j].getReleaseDate()) <= 0)
            {
                albums[k] = leftArray[i];
                i++;
            }
            else
            {
            	albums[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        /* Copy remaining elements if any */
        
        while (i < leftArraySize) {
        	albums[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightArraySize) {
        	albums[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    private void mergeByGenre(int first, int middlePoint, int last)
    {
        int leftArraySize = middlePoint - first + 1;
        int rightArraySize = last - middlePoint;
        
        Album[] leftArray = new Album[leftArraySize];
        Album[] rightArray = new Album[rightArraySize];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < leftArraySize; ++i)
            leftArray[i] = albums[first + i];
        
        for (int j = 0; j < rightArraySize; ++j)
            rightArray[j] = albums[middlePoint + 1 + j];
  
        /* Merge the temp arrays */
        
        int i = 0, j = 0;
        int k = first;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i].getGenre().toString().compareTo(rightArray[j].getGenre().toString()) <= 0)
            {
                albums[k] = leftArray[i];
                i++;
            }
            else
            {
            	albums[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        /* Copy remaining elements if any */
        
        while (i < leftArraySize) {
        	albums[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightArraySize) {
        	albums[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    private void sort(int first, int last, int sortingType)
    {
        if (first < last)
        {
            int middlePoint = first + (last - first) / 2;
            
            // Sort first and second halves
            sort(first, middlePoint, sortingType);
            sort(middlePoint + 1, last, sortingType);
            
            // Merge the sorted halves
            if (sortingType == 0)
                mergeByReleaseDate(first, middlePoint, last);
            else
                mergeByGenre(first, middlePoint, last);
        }
    }
    
    public void printByReleaseDate()
    {
        sort(0, numAlbums - 1, 0);
        
        System.out.println("*Album collection by the release dates.");
        
        for (int i = 0; i < albums.length; i++)
        {
            System.out.println(albums);
        }
        
        System.out.println("*End of list");
    }
    
    public void printByGenre()
    {
        sort(0, numAlbums - 1, 1);
        
        System.out.println("*Album collection by genre.");
        
        for (int i = 0; i < albums.length; i++)
        {
            System.out.println(albums);
        }
        
        System.out.println("*End of list");
    }
}