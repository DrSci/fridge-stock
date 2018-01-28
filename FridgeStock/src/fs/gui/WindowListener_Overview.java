package fs.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListener_Overview implements WindowListener
{
	private Gui_Overview g_ov;
	
	public WindowListener_Overview(Gui_Overview g_ov)
	{
		this.g_ov = g_ov;
	}
	
	public void windowActivated(WindowEvent we) 
	{
		
	}
	
	public void windowClosed(WindowEvent we) 
	{
		
	}
	
	public void windowClosing(WindowEvent we) 
	{
		this.g_ov.saveData();
		
		System.exit(0);
	}
	
	public void windowDeactivated(WindowEvent we) 
	{
		
	}
	
	public void windowDeiconified(WindowEvent we) 
	{
		
	}
	
	public void windowIconified(WindowEvent we) 
	{
		
	}
	
	public void windowOpened(WindowEvent we) 
	{
		
	}
}