/**
Album class defines each album and its attributes to be put inside the Collection class.
@author David Halim, Stephen Juan
*/
public class Album
{
    private String title;
    private String artist;
    private Genre genre;
    private Date releaseDate;
    private boolean isAvailable;
    
    /**
    Default constructor that sets default values for all attributes of an empty Album
    */
    public Album()
    {
        this("Defult Title", "Defult Artist", Genre.Unknown, "01/01/1980");
    }
    
    /**
    Constructor to be used when attributes of an Album are known. 
    @param title       - title of the album
    @param artist      - the artist who wrote the album
    @param genre       - the genre of the album 
    @param releaseDate - the date the album was released
    */
    public Album(String title, String artist, Genre genre,
                 String releaseDate)
    {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = new Date(releaseDate);
        this.isAvailable = true;
    }
    
    /**
    This method compares two albums and checks if the artist and title are the same. If they both are
    the same, it returns true, otherwise it returns false. 
    @return true, if the albums have the same title and artist, false if otherwise
    */
    @Override
    public boolean equals(Object obj)
    {
        Album albumIn = (Album) obj;
        String title = this.title;
        String artist = this.artist; 
        if (title.equals(albumIn.title) && artist.equals(albumIn.artist))
        { 
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
    This method checks the availability of an album and returns a string, if it is available or not.
    * @return "is available" for an album that is available in the collection, "is not available" if otherwise
    */
    private String isAvailableString()
    {
    	if (this.isAvailable)
    	{
            return "is available";
    	}
        else
        {
            return "is not available";
        }
    }
    
    /**
    This method checks the availability of an album and returns a boolean if it is available or not.
    @return true for an album that is available in the collection, false if otherwise
    */
    public boolean isAvailableBoolean()
    {
    	if (this.isAvailable)
    	{
            return true;	
    	}
        else
        {
            return false;
        }
    }
    
    /**
    Returns an album in the format "title::artist::genre::release date::availability"
    @ return album as a string in the format "title::artist::genre::release date::availability"
    */
    @Override
    public String toString()
    {
        return title + "::" + artist + "::" + genre.toString()
               + "::" + releaseDate.toString() + "::" + isAvailableString();
    }
    
    /**
    This sets the availability of a certain album as available or unavailable.
    @param isAvailable - true if it is available, false if otherwise
    */
    public void setIsAvailable(boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }
    
    /**
    This method returns a new Date instance with the release date in it.
    @return releaseDate in the format of a Date object
    */
    public Date getReleaseDate()
    {
        return new Date (releaseDate);
    }
    
    /**
    This method returns the a genre in the Genre enum class
    * @return genre of the album
    */
    public Genre getGenre()
    {
        return genre;
    }
    
    /**
    This method is to be used as another toString in the CollectionManager class when the user
    requests to delete, lend or remove an album from the collection since there is no need to 
    input the genre or the release date.
    @return a string in the format "title::artist"
    */
    public String getTitleArtist()
    {
        return title + "::" + artist;
    }
}