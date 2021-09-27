/**
class defines the abstract data type Album.
encapsulates the data fields and methods of an album.
@author David Halim, Stephen Juan.
*/
public class Album
{
    private String title;
    private String artist;
    private Genre genre;
    private Date releaseDate;
    private boolean isAvailable;
    
    /**
    Default constructor initializes title, artist, genre, releaseDate to default values.
    title = "Defult Title".
    artist = "Defult Artist".
    genre = Genre.Unknown.
    releaseDate = "01/01/1980".
    Calls the Overloaded constructor passing the default values.
    Initializes isAvailable = true.
    */
    public Album()
    {
        this("Defult Title", "Defult Artist", Genre.Unknown, "01/01/1980");
    }
    
    /**
    Overloaded constructor initializes title, artist, genre, releaseDate to accepted values.
    Initializes isAvailable = true.
    @param title initial value.
    @param artist initial value.
    @param genre initial value.
    @param releaseDate initial value.
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
    Compares 2 albums if they are equal.
    @param obj - the album to compare to.
    @return true if the titles and artists are the same for the two albums,
            false otherwise.
    */
    @Override
    public boolean equals(Object obj)
    {
        Album albumIn = (Album) obj;
        if (this.title.equals(albumIn.title) && this.artist.equals(albumIn.artist))
            return true;
        else
            return false;
    }
    
    /**
    Gets the string value of isAvailable.
    @return "is available" if isAvailable = true,
            "is not available" otherwise.
    */
    private String isAvailableString()
    {
    	if (this.isAvailable)
            return "is available";
        else
            return "is not available";
    }
    /**
    Gets the string value of Album.
    @return string value in the format title::artist::genre::releaseDate::isAvailable.
    */
    @Override
    public String toString()
    {
        return title + "::" + artist + "::" + genre.toString()
               + "::" + releaseDate.toString() + "::" + isAvailableString();
    }
    
    /**
    Sets isAvailable to an accepted value.
    @param isAvailable - new value of this.isAvailable.
    */
    public void setIsAvailable(boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }
    /**
    @return isAvailable.
    */
    public boolean getIsAvailable()
    {
        return isAvailable;
    }
    
    /**
    @return releaseDate.
    */
    public Date getReleaseDate()
    {
        return new Date (releaseDate);
    }
    
    /**
    @return genre.
    */
    public Genre getGenre()
    {
        return genre;
    }
    
    /**
    Gets the string for only title and artist.
    @return string value in the format title::artist.
    */
    public String getTitleArtist()
    {
        return title + "::" + artist;
    }
}