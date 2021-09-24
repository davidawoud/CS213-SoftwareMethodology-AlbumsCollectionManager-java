import java.util.Calander; 

public class Date implements Comparable<Date>
{	
    public static final int NORMAL_YEAR_FEB = 28;
    public static final int LEAP_YEAR_FEB = 29;
    public static final int THIRTY_DAY_MONTH = 30;
    public static final int THIRTY_ONE_DAY_MONTH = 31;
    public static final int NUMBER_MONTHS = 12;
    public static final int THE_EIGHTYS = 1980;
    
    
    private int year;
    private int month;
    private int day;
	
    // gets the date using a string
    // reads in the format mm/dd/yyyy
    // might have to change to accept m/d/yyyy -- see on friday
    public Date(String Date)
    {
        String month_str = "", day_str = "", year_str = ""; 
        int month = 0, day = 0, year = 0; 
        String[] date = new String[3]; 
        for (int i = 0; i < date.length; i++)
        {
            date[i] = "";		
        }
		int stringCounter = 0; 
		for (int i = 0; i < Date.length(); i++)
		{
			if (Date.charAt(i) == '/')
			{
				stringCounter++; 
				continue;
			}
			date[stringCounter] += Date.charAt(i);
		}
		this.month = Integer.valueOf(date[0]);
		this.day = Integer.valueOf(date[1]);
		this.year = Integer.valueOf(date[2]);
	}
	
	// gets the current date using Java Calendar class
	public Date()
	{
		Calendar cal = Calendar.getInstance(); 
		this.month = cal.get(Calendar.MONTH) + 1;
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.year = cal.get(Calendar.YEAR);
	}

	// returns true if date is valid, false if otherwise
	public boolean isValid() 
	{
		int day = this.day; 
		int month = this.month;
		int year = this.year;	
		Calendar cal = Calendar.getInstance();	

		// check for negative day
		if (day < 1) 
		{
			return false; 
		}
		// check for negative month or month over 12
		if (month < 1 || month > NUMBER_MONTHS) 
		{ 
			return false; 
		} 
		// check for a year before 1980
		if (year < THE_EIGHTYS) 
		{ 
			return false; 
		}
		// check for a date beyond todays (use calendar)
		if (month > cal.get(Calendar.MONTH) + 1 || year > cal.get(Calendar.YEAR))
		{ 
			return false;
		}
		// check for invalid date >30, >31 EXCEPT feb
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		{
			if (day > THIRTY_ONE_DAY_MONTH)
			{ 
				return false;
			}	
		}
		else if (month == 4 || month = 6 || month == 9 || month == 11) 
		{
			if (day > THIRTY_DAY_MONTH)
			{
				return true; 
			}
		}
		// check for leap year and feburary 
		// could formate this better...low priority now
		else
		{
			boolean isLeapYear = false;
			if ((year /= 4) == 0)
			{
				if ((year /= 100) == 0)
				{
					if ((year /= 400) == 0)
					{
						isLeapYear = true; 
					}
				}
				else
				{
					isLeapYear = true; 
				}
			}
		}
		// default return is false
		return false;
	}
	
	@Override
	public int compareTo(Date date)	
	{ 
		// if current date is more recent, return 1
		// return 0 if equal
		// return -1 is less
		// could optimize this to be less lines, but unimportant now
		currentDay = this.day;
		currentMonth = this.month;
		currentYear = this.year;
		if (currentYear > date.year)
		{
			return 1;
		}
		else if (currentYear == date.year)
		{
			if (currentMonth > date.month) 
			{
				return 1;
			}
			else if (currentMonth == date.month)
			{
				if (currentDay > date.day)
				{
					return 1; 
				}
				else if (currentDay == date.day)
				{
					return 0;
				}
				else
				{
					return -1;	
				}
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return -1;
		}
	} 
	
	// testbed main
	public static void main(String args[]) 
	{
		// test 1: 01/01/1979 -> invalid
		// test 2: 11/23/1990 -> valid
		// test 3: 08/90/2000 -> invalid
		// test 4: 09/16/2022 -> invalid
		// test 5: 02/29/2021 -> invalid
		// test 6: 02/29/2020 -> valid
		// test 7: 13/06/2001 -> invalid
		// test 8: 09/16/2021 -> valid (for this one test the current day)
	}
}