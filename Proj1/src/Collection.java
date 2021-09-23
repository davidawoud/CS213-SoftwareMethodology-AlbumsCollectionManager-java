public class Collection
{
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
    
    public Collection()
    {
        this(10);
    }
    
    public Collection(int albumsSize)
    {
        albums = new Album[albumsSize];
        numAlbums = 0;
    }
    
    private int find(Album album)
    {
        for (int i = 0; i < albums.length; i++)
        {
            if (album.equals(albums[i]))
                return i;
        }
        
        return -1;
    }
    
    private void grow()
    {
        Album[] albums = new Album[this.albums.length + 4];
        
        for (int i = 0; i < this.albums.length; i++)
        {
            albums[i] = this.albums[i];
        }
        
        this.albums = albums;
    }
    
    public boolean add(Album album)
    {
        if (numAlbums >= albums.length)
            grow();
        
        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }
    
    public boolean remove(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1)
            return false;
        
        albums[albumIndex] = albums[numAlbums - 1];
        albums[numAlbums - 1] = null;
        return true;
    }
    
    public boolean lendingOut(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1)
            return false;
        
        albums[albumIndex].setIsAvailable(false);
        return true;
    }
    
    public boolean returnAlbum(Album album)
    {
        int albumIndex = find(album);
        
        if (albumIndex == -1)
            return false;
        
        albums[albumIndex].setIsAvailable(true);
        return true;
    }
    
    public void print()
    {
        for (int i = 0; i < albums.length; i++)
        {
            System.out.println(albums);
        }
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
            if (leftArray[i].getReleaseDate().compareto(rightArray[j].getReleaseDate()) <= 0)
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
            if (leftArray[i].getGenre().compareto(rightArray[j].getGenre()) <= 0)
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
            int middlePoint = first + (last - l) / 2;
            
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
        
    }
    
    public void printByGenre()
    {
        
    }
}