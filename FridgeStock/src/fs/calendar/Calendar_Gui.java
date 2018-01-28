package fs.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Calendar_Gui extends JDialog
{
	private JButton prevYear, nextYear, prevMonth, nextMonth;
	private JComboBox year, month;
	private JLabel mon, tue, wed, thu, fri, sat, sun;
	private JButton[][] days;
	
	private int firstYearList = 1990;
	private int lastYearList = 2030;
	private int firstday;
	private int daysprevmonth;
	private int daysthatmonth;
	
	private int tag = new Date().getDate();
	private int monat = new Date().getMonth()+1;
	private int jahr = new Date().getYear();
	
	public Calendar_Gui()
	{
		Date dt = new Date(this.jahr, this.monat-1, this.tag);
		this.firstday = dt.getDay()+5;
		if(dt.getDay() >= 5)
		{
			this.firstday = dt.getDay()-2;
		}
		this.daysprevmonth = this.getDaysIn((this.monat+11)%12, this.jahr); // Kann man so machen
		this.daysthatmonth = this.getDaysIn(this.monat, this.jahr);
		
		String[] years = new String[this.lastYearList-this.firstYearList+1];
		
		for(int i = 0; i < years.length; i++)
		{
			years[i] = String.valueOf(this.firstYearList + i);
		}
		
		this.prevYear = new JButton("<");
		this.prevYear.addActionListener(new Calendar_ActionListener(this, -1, -1));
		this.year = new JComboBox(years);
		this.year.addActionListener(new Calendar_ActionListener(this, -1, 0));
		this.nextYear = new JButton(">");
		this.nextYear.addActionListener(new Calendar_ActionListener(this, -1, 1));
		
		this.prevMonth = new JButton("<");
		this.prevMonth.addActionListener(new Calendar_ActionListener(this, -2, -1));
		this.month = new JComboBox(new String[] {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"});
		this.month.addActionListener(new Calendar_ActionListener(this, -2, 0));
		this.nextMonth = new JButton(">");
		this.nextMonth.addActionListener(new Calendar_ActionListener(this, -2, 1));
		
		this.mon = new JLabel("Mo");
		this.tue = new JLabel("Di");
		this.wed = new JLabel("Mi");
		this.thu = new JLabel("Do");
		this.fri = new JLabel("Fr");
		this.sat = new JLabel("Sa");
		this.sun = new JLabel("So");
		
		this.days = new JButton[6][7];
		
		JPanel yearPanel = new JPanel();
		yearPanel.setLayout(new BorderLayout());
		yearPanel.add(this.year, BorderLayout.CENTER);
		yearPanel.add(this.prevYear, BorderLayout.WEST);
		yearPanel.add(this.nextYear, BorderLayout.EAST);
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(new BorderLayout());
		monthPanel.add(this.month, BorderLayout.CENTER);
		monthPanel.add(this.prevMonth, BorderLayout.WEST);
		monthPanel.add(this.nextMonth, BorderLayout.EAST);
		
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(0, 7));
		
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				this.days[i][j] = new JButton("X");
				this.days[i][j].addActionListener(new Calendar_ActionListener(this, i, j));
				this.days[i][j].setOpaque(true);
				this.days[i][j].setBackground(Color.WHITE);
				dayPanel.add(this.days[i][j]);
			}
		}
		
		// System.out.println(this.tag + "." + this.monat + "." + this.jahr);
		
		this.setDate(this.tag, this.monat, this.jahr);
		
		JPanel weekPanel = new JPanel();
		weekPanel.setLayout(new GridLayout(0, 7));
		weekPanel.add(mon);
		weekPanel.add(tue);
		weekPanel.add(wed);
		weekPanel.add(thu);
		weekPanel.add(fri);
		weekPanel.add(sat);
		weekPanel.add(sun);
		
		JPanel dayweekPanel = new JPanel();
		dayweekPanel.setLayout(new BorderLayout());
		dayweekPanel.add(weekPanel, BorderLayout.NORTH);
		dayweekPanel.add(dayPanel, BorderLayout.CENTER);
		
		JPanel upperPanels = new JPanel();
		upperPanels.setLayout(new GridLayout(0, 1));
		upperPanels.add(yearPanel);
		upperPanels.add(monthPanel);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(upperPanels, BorderLayout.NORTH);
		this.getContentPane().add(dayweekPanel, BorderLayout.CENTER);
		
		this.setModal(true);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setTitle("Kalender");
		this.setVisible(true);
	}
	
	public void setDate(int d, int m, int y)
	{
		this.days[(int) ((this.tag + this.firstday) / 7)][(this.tag + this.firstday) % 7].setBackground(Color.WHITE);
		
		this.jahr = y;
		this.year.setSelectedItem(String.valueOf(1900+this.jahr));
		
		this.monat = m;
		this.month.setSelectedIndex(this.monat-1);
		
		Date virtDate = new Date(this.jahr, this.monat-1, 1);
		// Date dt2 = new Date();
		// System.out.println(virtDate.getDate() + "." + (virtDate.getMonth()+1) + "." + (virtDate.getYear()+1900));
		// System.out.println(dt2.getDate() + "." + (dt2.getMonth()+1) + "." + (dt2.getYear()+1900));
		this.firstday = virtDate.getDay()+5;
		if(virtDate.getDay() >= 5)
		{
			this.firstday = virtDate.getDay() - 2;
		}
		// System.out.println(this.firstday);
		this.daysprevmonth = this.getDaysIn(((this.monat+11)%12), this.jahr); // Kann man so machen
		this.daysthatmonth = this.getDaysIn(this.monat, this.jahr);
		
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				this.days[i][j].setEnabled(true);
				
				if (i*7+j-this.firstday <= 0)
				{
					this.days[i][j].setText(String.valueOf(this.daysprevmonth-this.firstday+i*7+j));
				}
				else if (i*7+j-this.firstday > this.daysthatmonth)
				{
					this.days[i][j].setText(String.valueOf(-this.firstday+i*7+j-this.daysthatmonth));
				}
				else
				{
					this.days[i][j].setText(String.valueOf(-this.firstday+i*7+j));
				}
				
				if(-this.firstday+i*7+j <= 0 || i*7+j-this.firstday > this.daysthatmonth)
				{
					this.days[i][j].setEnabled(false);
				}
			}
		}
		
		this.tag = d;
		this.days[(int) ((this.tag + this.firstday) / 7)][(this.tag + this.firstday) % 7].setBackground(Color.RED);
	}
	
	public int getDay()
	{
		return this.tag;
	}
	
	public int getMonth()
	{
		return this.monat;
	}
	
	public int getYear()
	{
		return this.jahr;
	}
	
	private int getDaysIn(int month, int year)
	{
		if(month == 0)
		{
			return 31;
		}
		else if(month == 1)
		{
			return 31;
		}
		else if(month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
		{
			return 29;
		}
		else if(month == 2)
		{
			return 28;
		}
		else if(month == 3)
		{
			return 31;
		}
		else if(month == 4)
		{
			return 30;
		}
		else if(month == 5)
		{
			return 31;
		}
		else if(month == 6)
		{
			return 30;
		}
		else if(month == 7)
		{
			return 31;
		}
		else if(month == 8)
		{
			return 31;
		}
		else if(month == 9)
		{
			return 30;
		}
		else if(month == 10)
		{
			return 31;
		}
		else if(month == 11)
		{
			return 30;
		}
		else if(month == 12)
		{
			return 31;
		}
		else
		{
			return 0;
		}
	}
}