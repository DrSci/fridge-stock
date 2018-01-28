package fs.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListener_Overview implements ActionListener
{
	public static boolean oneIsChangeable = false;
	
	private int buttonType;
	private int buttonId;
	
	private Gui_Overview g_ov;
	
	public ActionListener_Overview(Gui_Overview g_ov, int buttonType, int buttonId)
	{
		this.buttonType = buttonType;
		this.buttonId = buttonId;
		this.g_ov = g_ov;
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		System.out.println(ae);
		
		if(this.buttonType == 0)
		{
			this.g_ov.addFood();
			
			this.g_ov.refresh();
		}
		else if(this.buttonType == 1)
		{
			if(g_ov.getFood(this.buttonId).isOpen())
			{
				g_ov.closeFood(this.buttonId);
			}
			else
			{
				g_ov.openFood(this.buttonId);
			}
			
			g_ov.refresh();
		}
		else if(this.buttonType == 2)
		{
			if(ae.getActionCommand().equals("CHANGE") && ! oneIsChangeable)
			{
				g_ov.editFood(this.buttonId);
				
				oneIsChangeable = true;
			}
			else if(ae.getActionCommand().equals("SAVE") && oneIsChangeable)
			{
				g_ov.saveFood(this.buttonId);
				
				oneIsChangeable = false;
			}
		}
		else if(this.buttonType == 3)
		{
			g_ov.removeFood(this.buttonId);
			
			this.g_ov.refresh();
		}
	}
}