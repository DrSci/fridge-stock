package fs.structure;

import java.util.Date;

public class Datum 
{
	private int tageSeit1900;
	private int dd;
	private int mm;
	private int yyyy;
	
	private void constructor(int tag, int monat, int jahr)
	{
		int tage = 0;
		this.dd = tag;
		this.mm = monat;
		this.yyyy = jahr;
		
		for(int i = 1900; i < jahr; i++)
		{
			if(i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
			{
				tage += 366;
			}
			else
			{
				tage += 365;
			}
		}
		
		for(int i = 1; i < monat; i++)
		{
			if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
			{
				tage += 31;
			}
			else if(i == 2 && (jahr % 4 == 0 && jahr % 100 != 0 || jahr % 400 == 0))
			{
				tage += 29;
			}
			else if(i == 2)
			{
				tage += 28;
			}
			else
			{
				tage += 30;
			}
		}
		
		tage += tag;
		
		this.tageSeit1900 = tage;
	}
	
	public Datum(int tag, int monat, int jahr)
	{
		this.constructor(tag, monat, jahr);
	}
	
	public Datum(String dat2parse)
	{
		String d = "";
		String m = "";
		String y = "";
		int kommas = 0;
		boolean problem = false;
		
		for(int i = 0; i < dat2parse.length(); i++)
		{
			if(dat2parse.charAt(i) == '.')
			{
				kommas += 1;
			}
			else if(dat2parse.charAt(i) >= 48 && dat2parse.charAt(i) <= 57)
			{
				if(kommas == 0)
				{
					d += dat2parse.charAt(i);
				}
				else if(kommas == 1)
				{
					m += dat2parse.charAt(i);
				}
				else if(kommas == 2)
				{
					y += dat2parse.charAt(i);
				}
				else
				{
					problem = true;
				}
			}
			else
			{
				problem = true;
			}
		}
		
		if(d.length() != 0 && m.length() == 0 && y.length() == 0)
		{
			this.tageSeit1900 = Datum.getHeute() + Integer.valueOf(d);
		}
		else if(d.length() == 0 || m.length() == 0 || y.length() == 0)
		{
			problem = true;
		}
		
		if(! problem)
		{
			this.constructor(Integer.valueOf(d), Integer.valueOf(m), Integer.valueOf(y));
		}
		else
		{
			System.err.println("Parsing Sring to Datum did not work.");
		}
	}
	
	public int getDayCounter()
	{
		return this.tageSeit1900;
	}
	
	public int getTimeLeft()
	{
		return this.tageSeit1900 - getHeute();
	}
	
	public int getYear()
	{
		return this.yyyy;
	}
	
	public int getMonth()
	{
		return this.mm;
	}
	
	public int getDay()
	{
		return this.dd;
	}
	
	public static int getHeute()
	{
		Date dt = new Date();
		
		Datum today = new Datum(Integer.valueOf(dt.toString().substring(8, 10)), Integer.valueOf(monthToNumber(dt.toString().substring(4, 7))), Integer.valueOf(dt.toString().substring(dt.toString().length()-4, dt.toString().length())));
		// System.out.println("Heute ist der " + dt.toString().substring(8, 10) + "." + monthToNumber(dt.toString().substring(4, 7)) + "." + dt.toString().substring(dt.toString().length()-4, dt.toString().length()));
		
		return today.tageSeit1900;
	}
	
	private static String monthToNumber(String month)
	{
		if(month.equals("Jan"))
		{
			return "01";
		}
		else if(month.equals("Feb"))
		{
			return "02";
		}
		else if(month.equals("Mar"))
		{
			return "03";
		}
		else if(month.equals("Apr"))
		{
			return "04";
		}
		else if(month.equals("May"))
		{
			return "05";
		}
		else if(month.equals("Jun"))
		{
			return "06";
		}
		else if(month.equals("Jul"))
		{
			return "07";
		}
		else if(month.equals("Aug"))
		{
			return "08";
		}
		else if(month.equals("Sep"))
		{
			return "09";
		}
		else if(month.equals("Oct"))
		{
			return "10";
		}
		else if(month.equals("Nov"))
		{
			return "11";
		}
		else if(month.equals("Dec"))
		{
			return "12";
		}
		else
		{
			return "00";
		}
	}
}