import javax.microedition.lcdui.*;
//import com.nokia.mid.ui.*;
class FrontScreenCanvas extends Canvas implements Runnable
{

MineSweeper m_s;
MineSweeperCanvas m_s_c;
Thread th;
int width,height;
Image img[],bkgnd;
boolean start;
int fstY,secY;
public  FrontScreenCanvas(MineSweeper m_s)
{
	setFullScreenMode(true);
	this.m_s = m_s;
	m_s_c=new  MineSweeperCanvas(m_s);
	width=getWidth();
	height=getHeight();
	th=new Thread(this);
	img=new Image[2];
	System.out.println("width = "+width);
	System.out.println("height = "+height);
	try
	{
	img[0]=Image.createImage("/myname.png");
	img[1]=Image.createImage("/presents.png");
	bkgnd=Image.createImage("/bkgnd1.png");
	}
	catch(Exception e)
	{
	}
	
	
}
public void startThread()
{
start = true;
fstY=0;
th.start();

}
public void run()
{
	System.out.println("in run");
	while(start==true)
	{
	
	fstY++;
	repaint();	
	if(fstY>img[0].getHeight())
	{
		fstY=img[0].getHeight();
		secY++;
		
	}	
		if(secY>img[1].getHeight())
		{
			start=false;
			Display.getDisplay(this.m_s).setCurrent(m_s_c);
			fstY=0;
		}
			
			
			try
			{
			th.sleep(30);
			}
			catch(Exception e)
			{
			}
		
		
	}
	System.out.println("fstY = "+fstY);
	System.out.println("secY = "+secY);
	

}


public void paint(Graphics g)
{

g.setColor(0,0,255);
//g.fillRect(0,0,width,height);
		g.drawImage(bkgnd,0,0,Graphics.LEFT|Graphics.TOP);

g.setClip(0,(height-img[0].getHeight())/2,width,fstY);
g.drawImage(img[0],(width-img[0].getWidth())/2,(height-img[0].getHeight())/2,Graphics.TOP|Graphics.LEFT);

g.setClip(0,(height-img[1].getHeight())/2+img[0].getHeight(),width,secY);
g.drawImage(img[1],(width-img[1].getWidth())/2,(height-img[1].getHeight())/2+img[1].getHeight(),Graphics.TOP|Graphics.LEFT);

}
}



