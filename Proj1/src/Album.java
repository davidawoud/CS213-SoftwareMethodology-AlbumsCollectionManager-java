public class Album
{
    private String title;
    private String artist;
    private Genre genre;
    private Date releaseDate;
    private boolean isAvailable;
    
    public Album()
    {
        this("Defult Title", "Defult Artist", Genre.Unknown, "01/01/1980");
    }
    
    public Album(String title, String artist, Genre genre,
                 String releaseDate)
    {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = new Date(releaseDate);
        this.isAvailable = true;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        Album albumIn = (Album) obj;
        String title = this.title;
        String artist = this.artist; 
        //System.out.println("thistitle: " + title); 
        //System.out.println("thisartist: " + artist);
        //System.out.println("albumin title: " + albumIn.title);
        //System.out.println("albumin artist: " + albumIn.artist);
        if (title.equals(albumIn.title) && artist.equals(albumIn.artist))
        { 
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private String isAvailableString()
    {
    	if (this.isAvailable)
            return "is available";
        else
            return "is not available";
    }
    
    @Override
    public String toString()
    {
        return title + "::" + artist + "::" + genre.toString()
               + "::" + releaseDate.toString() + "::" + isAvailableString();
    }
    
    public void setIsAvailable(boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }
    
    public Date getReleaseDate()
    {
        return new Date (releaseDate);
    }
    
    public Genre getGenre()
    {
        return genre;
    }
    
    public String getTitleArtist()
    {
        return title + "::" + artist;
    }
}