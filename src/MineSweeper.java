import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MineSweeper extends MIDlet
{
	FrontScreenCanvas f_s_c;
	
	public MineSweeper()
	{
		f_s_c= new FrontScreenCanvas(this);
		f_s_c.repaint();
		f_s_c.startThread();
	}
	
	
	public void startApp()
	{
		Display.getDisplay(this).setCurrent(f_s_c);
	}
	
	public void destroyApp(boolean b)
	{
		
		
	}
	public void pauseApp()
	{
	}
}