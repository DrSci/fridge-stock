package fs.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calendar_ActionListener implements ActionListener
{
	private Calendar_Gui c_g;
	private int idX, idY;
	
	public Calendar_ActionListener(Calendar_Gui c_g, int x, int y)
	{
		this.c_g = c_g;
		this.idX = x;
		this.idY = y;
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		// System.out.println(ae);
		
		if(this.idX == -1)
		{
			if(this.idY == -1)
			{
				this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth(), this.c_g.getYear()-1);
			}
			else if(this.idY == 0)
			{
				// this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth(), Integer.valueOf(ae.getActionCommand())+1900);
			}
			else if(this.idY == 1)
			{
				this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth(), this.c_g.getYear()+1);
			}
		}
		else if(this.idX == -2)
		{
			if(this.idY == -1)
			{
				if(this.c_g.getMonth() == 1)
				{
					this.c_g.setDate(this.c_g.getDay(), 12, this.c_g.getYear()-1);
				}
				else
				{
					this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth()-1, this.c_g.getYear());
				}
			}
			else if(this.idY == 0)
			{
				// this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth(), this.c_g.getYear());
			}
			else if(this.idY == 1)
			{
				if(this.c_g.getMonth() == 12)
				{
					this.c_g.setDate(this.c_g.getDay(), 1, this.c_g.getYear()+1);
				}
				else
				{
					this.c_g.setDate(this.c_g.getDay(), this.c_g.getMonth()+1, this.c_g.getYear());
				}
			}
		}
		else if(this.idX >= 0 && this.idY >= 0)
		{
			this.c_g.setDate(Integer.valueOf(ae.getActionCommand()), this.c_g.getMonth(), this.c_g.getYear());
		}
	}
}