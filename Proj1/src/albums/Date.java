package albums;

/**
This class represents a date and includes methods that checks if the user-input
date is valid and compares if a current date is before, equal to, or after another
date
@author Stephen Juan, David Halim
*/

import java.util.Calendar; 

public class Date implements Comparable<Date>
{
    private int year;
    private int month;
    private int day;
    
    public static final int NORMAL_YEAR_FEB = 28;
    public static final int LEAP_YEAR_FEB = 29;
    public static final int THIRTY_DAY_MONTH = 30;
    public static final int THIRTY_ONE_DAY_MONTH = 31;
    public static final int NUMBER_MONTHS = 12;
    public static final int THE_EIGHTYS = 1980;
    
    /**
    This is a constructor for the Date class that accepts a date as a string and gives values
    for the month, day and year from that string.
    @param dateIn that is to be processed as a string
    */
    public Date(String dateIn)
    {
        String[] date = new String[3]; 
        for (int i = 0; i < date.length; i++)
        {
            date[i] = "";
        }
        int stringCounter = 0; 
        for (int i = 0; i < dateIn.length(); i++)
        {
            if (dateIn.charAt(i) == '/')
            {
                stringCounter++; 
                continue;
            }
            date[stringCounter] += dateIn.charAt(i);
        }
        this.month = Integer.valueOf(date[0]);
        this.day = Integer.valueOf(date[1]);
        this.year = Integer.valueOf(date[2]);
    }
    
    /**
    This is another constructor that gets today's date using the Java Calendar class
    */
    public Date()
    {
        Calendar cal = Calendar.getInstance(); 
        this.month = cal.get(Calendar.MONTH) + 1;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.year = cal.get(Calendar.YEAR);
    }
    
    /**
    This is a copy constructor that copies the Date instance
    @param date - the instance to be copied
    */
    public Date(Date date)
    {
        this.month = date.month;
        this.day = date.day;
        this.year = date.year;
    }
    
    /**
    This method checks if the date is a valid date in a calendar, utilizing some private classes 
    for in-depth checks. It returns true if i the date is valid and false if the date is invalid.
    @return true for a valid date, false for an invalid date
    */
    public boolean isValid() 
    {
        int day = this.day; 
        int month = this.month;
        int year = this.year;
        
        if (!isValid_NegativeOr1980(day, month, year))
        {
            //System.out.println("hi");
            return false;
        }
        if (!isValid_futureDate(day, month, year))
        {
            return false; 
        }
        if (!isValid_monthLeapYear(day, month, year))
        {
            return false; 
        }
        
        return true;
    }
    
    /**
    This method checks for a negative day or month, a month with a number over 12 and if the year is 
    below 1980. It returns false for a negative date or month, a month over 12 or a year below 1980. 
    @param day   - the input day to be checked
    @param month - the input month to be checked
    @param year  - the input year to be checked
    @return true if the date is valid,
            false otherwise.
    */
    private boolean isValid_NegativeOr1980(int day, int month, int year)
    {
        if (day < 1)
        {
            return false;
        }
        
        if (month < 1 || month > NUMBER_MONTHS)
        { 
            return false;
        } 
        
        if (year < THE_EIGHTYS)
        { 
            return false;
        }
        return true; 
    }
    
    /**
    This method checks if a date is a future date and returns false if it is in the future
    @param day   - day to be compared to todays date
    @param month - month to be compared to todays month
    @param year  - year to be compared to todays year
    @return false if the day is in the future, true if the day is the current day or in the past
    */
    private boolean isValid_futureDate(int day, int month, int year)
    {
        Date todaysDate = new Date();
        Date comparedDate = new Date("" + month + "/" + day + "/" + year);
        
        int result = comparedDate.compareTo(todaysDate);
        // if compareDate is future == 1
        // if compareDate is same == 0
        // if compareDate is before == -1
        if (result == -1 || result == 0)
        {
            return true;
        }
        else 
        { 
            return false;
        }
    }
    
    /**
    This method checks if the month has 30/21 days, the days are not over 30/31 days. In the case of 
    February, which has 28 or 29 days, it checks if the current year is a leap year and uses that
    information to check whether the day in Feburary is valid or not. It returns false if the date is
    invalid and true if it is valid. 
    @param day   - the input day to be checked
    @param month - the input month to be checked
    @param year  - the input year to be checked
    @return true if the day is valid, false if the day is not valid
    */
    private boolean isValid_monthLeapYear(int day, int month, int year)
    {
        // checks months with 31 days
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            if (day > THIRTY_ONE_DAY_MONTH)
            { 
                return false;
            }	
        }
        // checks months with 30 days
        else if (month == 4 || month == 6 || month == 9 || month == 11) 
        { 
            if (day > THIRTY_DAY_MONTH)
            {
                return false; 
            }
        }
		// checks for february and leap years
        else
        {
            boolean isLeapYear = false;
            if ((year %= 4) == 0)
            {
                if ((year %= 100) == 0)
                {
                    if ((year %= 400) == 0)
                    {
                    	isLeapYear = true;
                    }
                }
                else
                {
                	isLeapYear = true;
                }
            }
            
            if (day == 29 && isLeapYear)
            {
                return true;
            }
            else if (day == 29 && !isLeapYear)
            {
                return false;
            }
            else if (day > 28)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
    This method compares a current Date object to another Date object. It returns 1 is the current Date object 
    is more, 0 if it is equal and -1 if it is before. 
    @param date - Date object that is being used as a reference for comparison
    @return 1 the parameter is less than the compared month
            0 if both dates are the same
            -1 if the parameter is greater
    */
    @Override
    public int compareTo(Date date)	
    { 
        int currentDay = this.day;
        int currentMonth = this.month;
        int currentYear = this.year;
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
        else // if currentYear < date.year
        {
            return -1;
        }
    } 
    
    @Override
    public String toString()
    {
        return "" + month + "/" + day + "/" + year;
    }
    
    /**
    This method is a testbed main used to test the isValid() method using a wide range of test dates
    @param args - string used as input
    */
    public static void main(String args[]) 
    { 
        // invalid because less than 1979
        Date dateTest1 = new Date("1/1/1979");
        boolean result = dateTest1.isValid();
        System.out.println("1/1/1979: " + result);
        
        // valid
        Date dateTest2 = new Date("11/23/1990");
        result = dateTest2.isValid() ? true : false;
        System.out.println("11/23/1990: " + result);
        
        // invalid because there are >= 31 days in August
        Date dateTest3 = new Date("8/90/2000");
        result = dateTest3.isValid(); 
        System.out.println("8/90/2000: " + result);
        
        // invalid because it is a future date
        Date dateTest4 = new Date("12/2/2022"); 
        result = dateTest4.isValid(); 
        System.out.println("12/2/2022: " + result);
        
        // invalid because it is not a leap year
        Date dateTest5 = new Date("2/29/2021");
        result = dateTest5.isValid();
        System.out.println("2/29/2021: " + result);
        
        // valid because it is a leap year
        Date dateTest6 = new Date("2/29/2020");
        result = dateTest6.isValid();
        System.out.println("2/29/2020: " + result);
        
        // invalid because there is no month after December
        Date dateTest7 = new Date("13/6/2002");
        result = dateTest7.isValid();
        System.out.println("13/6/2002: " + result);
        
        // valid [THIS IS THE CURRENT DATE]
        Date dateTest8 = new Date();
        result = dateTest8.isValid();
        System.out.println("CURRENT DATE: " + result);
    }
}