import javax.microedition.lcdui.*;
import java.util.Random;
//import com.nokia.mid.ui.*;
class MineSweeperCanvas extends Canvas implements Runnable
{
	MineSweeper m_s;
	int[] bomb_pos,related_pos,total_pos,status_pos;
	//Thread th;
	boolean gameState,keyState;
	int fstY,return_f_flag=23,width,height,SHOW=9,FLAG=8,HIDE,clock,temp_clock1,temp_clock2,temp_clock3,digImgWidth,bombCounter,flagCounter,exitWidth;
	int curpos,gameOverControl,happyState;
	Image sticky,mut,bombcount[],happy,happy2,happy3,happy4,sad,decide,bomb,flag,cover,bkgnd[],gOver,yWin,cn[],exit,minus,exploded,right,wrong;
	Thread th;
	boolean smiley_state,gameon;
	public MineSweeperCanvas(MineSweeper m_s)
	{
		
		setFullScreenMode(true);
		width=getWidth();
		height=getHeight();
		this.m_s=m_s;
		bomb_pos=new int[10];
		related_pos=new int[80];
		total_pos=new int[100];
		status_pos=new int[100];
		bombcount =new Image[11];
		bkgnd= new Image[10];
		cn=new Image[10];
		th=new Thread(this);
		
			
		init_res();
		random_positions_of_bombs();
		related_positions_of_bombs();
		set_danger_and_bomb_positons_in_total_pos();
	//	th=new Thread(this);
		temp_show();
		//gameon=true;
		startGame();
		repaint();
		
	}
	
	
	
	
	
	
	public void run()
	{
	while(gameState)
	{
		//System.out.println(" in run");
	implementInputEffect();
	repaint();
	try
	{
		th.sleep(200);
	}
	catch(Exception e)
	{
	}
	}
	}
	public void init_res()
	{
		try
		{
			mut=Image.createImage(width,height);
			bombcount[0]=Image.createImage("/bgTile.png");
			bombcount[1]=Image.createImage("/bg1.png");
			bombcount[2]=Image.createImage("/bg2.png");
			bombcount[3]=Image.createImage("/bg3.png");
			bombcount[4]=Image.createImage("/bg4.png");
			bombcount[5]=Image.createImage("/bg5.png");
			bombcount[6]=Image.createImage("/bg6.png");
			bombcount[7]=Image.createImage("/bomb.png");
			bombcount[8]=Image.createImage("/exploded.png");
			bombcount[9]=Image.createImage("/right.png");
			bombcount[10]=Image.createImage("/wrong.png");
			
			
			cover=Image.createImage("/bgCover.png");
			decide=Image.createImage("/decide.png");
			happy=Image.createImage("/happy.png");
			happy2=Image.createImage("/happy2.png");
			happy3=Image.createImage("/happy3.png");
			happy4=Image.createImage("/happy4.png");
			exit=Image.createImage("/EXIT.png");
			sad=Image.createImage("/sad.png");
			flag=Image.createImage("/bgFlag.png");
			sticky=Image.createImage("/sticky.png");
			bkgnd[0]=Image.createImage("/bkfrnt0.png");
			bkgnd[1]=Image.createImage("/bkfrnt1.png");
			bkgnd[2]=Image.createImage("/bkfrnt2.png");
			bkgnd[3]=Image.createImage("/bkfrnt3.png");
			bkgnd[4]=Image.createImage("/bkfrnt4.png");
			bkgnd[5]=Image.createImage("/bkfrnt5.png");
			bkgnd[6]=Image.createImage("/bkfrnt6.png");
			bkgnd[7]=Image.createImage("/bkfrnt7.png");
			bkgnd[8]=Image.createImage("/bkfrnt8.png");
			bkgnd[9]=Image.createImage("/bkfrnt9.png");
			
			
			
			
			gOver=Image.createImage("/gOver.png");
			yWin=Image.createImage("/yWin.png");
			cn[0]=Image.createImage("/cn0.png");
			cn[1]=Image.createImage("/cn1.png");
			cn[2]=Image.createImage("/cn2.png");
			cn[3]=Image.createImage("/cn3.png");
			cn[4]=Image.createImage("/cn4.png");
			cn[5]=Image.createImage("/cn5.png");
			cn[6]=Image.createImage("/cn6.png");
			cn[7]=Image.createImage("/cn7.png");
			cn[8]=Image.createImage("/cn8.png");
			cn[9]=Image.createImage("/cn9.png");
			minus=Image.createImage("/minus.png");
			
			digImgWidth=cn[9].getWidth();
			exitWidth=width-exit.getWidth();
			
		}
		catch(Exception e)
		{
		}
	
	}
	
	
	
	public void startGame()
	{
		//if(gameState==false)
		        
		        	fstY=0;
		           gameState=true;
	//	if(keyState=false)
			keyState=true;
		th.start();
	
	
	
	}
	public void stopGame()
	{
		gameState=false;
		keyState=false;
		bomb_pos=null;
		related_pos=null;
		total_pos=null;
	//	th=null;
	}
	
	
	
	
	
	
	
	public void paint(Graphics g)
	{
		
		Graphics g1=mut.getGraphics();
		int i=0;
		//System.out.println("width = "+width);
	//	System.out.println("height = "+height);
	  g1.setColor(0,255,0);
		//g1.fillRect(0,0,width,height);
	//	g1.drawImage(bkgnd,0,0,Graphics.TOP|Graphics.LEFT);
		
	

			for(int x=0;x<11;x++)
			{
				for(int j=0;j<13;j++)
					g1.drawImage(bkgnd[bomb_pos[0]%10],x*16,j*16,Graphics.LEFT|Graphics.TOP);
		
				//System.out.println("bkgnd img pos= "+(bomb_pos[0]%10));
			}
		
		
		
		
		
		
		
		clock++;
		int temp_clock=clock/5;
		String temp=String.valueOf(temp_clock);
		if(temp.length()==1)
			temp="00"+temp;
		if(temp.length()==2)
			temp="0"+temp;
			int fst,sec,thr;
			fst=temp.charAt(0)-48;
			sec=temp.charAt(1)-48;
			thr=temp.charAt(2)-48;
			g1.drawImage(cn[thr],width-digImgWidth,0,Graphics.LEFT|Graphics.TOP);
			g1.drawImage(cn[sec],width-digImgWidth*2,0,Graphics.LEFT|Graphics.TOP);
			g1.drawImage(cn[fst],width-digImgWidth*3,0,Graphics.LEFT|Graphics.TOP);
			
		
		
		
		
		
		
		
		
			int tempflagCounter=10-flagCounter;
			temp=String.valueOf(Math.abs(tempflagCounter));
			if(temp.length()==1)
				temp="0"+temp;
			fst=temp.charAt(0)-48;
			sec=temp.charAt(1)-48;
			if(tempflagCounter<0)
				g1.drawImage(minus,0,0,Graphics.LEFT|Graphics.TOP);
			g1.drawImage(cn[sec],digImgWidth*2,0,Graphics.LEFT|Graphics.TOP);
			g1.drawImage(cn[fst],digImgWidth,0,Graphics.LEFT|Graphics.TOP);
		
		
		
		
		
		
		if(smiley_state==true)
			g1.drawImage(decide,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
		else
		{
			happyState++;
			if(happyState>=0&&happyState<=10)
							g1.drawImage(happy,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
			
			if(happyState>=11&&happyState<=20)
							g1.drawImage(happy2,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
			
			if(happyState>=21&&happyState<=30)
							g1.drawImage(happy3,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
			
			if(happyState==30)
							happyState=0;
		}
		if(gameOverControl>0)
			g1.drawImage(sad,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for(i=0;i<100;i++)
		{
			//if(i%10==0)
			//	System.out.println("");
			//System.out.print(total_pos[i]+" ");
			if(status_pos[i]==FLAG)
				g1.drawImage(flag,i%10*16+8,i/10*16+32,Graphics.LEFT|Graphics.TOP);
			
			if(status_pos[i]==FLAG&&total_pos[i]!=7&&gameOverControl>=1)
			{
			
				g1.drawImage(bombcount[10],i%10*16+8,i/10*16+32,Graphics.LEFT|Graphics.TOP);
					
			}		
			
			if(status_pos[i]==0)//for ret
			{
			
							g1.drawImage(cover,i%10*16+8,i/10*16+32,Graphics.LEFT|Graphics.TOP);
			
			}
			if(status_pos[i]==return_f_flag)//for ret
			{
			
							g1.drawImage(sticky,i%10*16+8,i/10*16+32,Graphics.LEFT|Graphics.TOP);
			
			}
			
			
			
			if(status_pos[i]==9)
			{
			if(total_pos[i]>=0&&total_pos[i]<=10)
							g1.drawImage(bombcount[total_pos[i]],i%10*16+8,i/10*16+32,Graphics.LEFT|Graphics.TOP);
			
			}
			
			
		
		}
	
		
		
			
	
	
	g1.drawRect(curpos%10*16+8,curpos/10*16+32,16,16);
	
	
		
	
	
	
	if((total_pos[curpos]==7||total_pos[curpos]==8)&&status_pos[curpos]==9&&gameOverControl<=20)
	{
		if(gameOverControl==0)
		{
			for(int j=0;j<10;j++)
			{
		
			if(status_pos[bomb_pos[j]]==FLAG)
			{
				System.out.println("entered in flag check");
				total_pos[bomb_pos[j]]=9;
			}
				status_pos[bomb_pos[j]]=9;
			}
			
			total_pos[curpos]=8;
		}
		
		
		
		gameOverControl++;
		if(gameOverControl==20)
		{
			g1.drawImage(gOver,0,0,Graphics.TOP|Graphics.LEFT);
			gameOverControl=19;
			//gameState=false;
		}
		clock--;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	if(bombCounter==10&&flagCounter==10)
	{
		
		
		gameOverControl++;
		g1.drawImage(happy4,(width-sad.getWidth())/2,0,Graphics.LEFT|Graphics.TOP);
		if(gameOverControl==20)
		{
			g1.drawImage(yWin,0,0,Graphics.TOP|Graphics.LEFT);
			gameOverControl=19;
			gameState=false;
			
		}
		clock--;
	}
	
	
	
	
	
	
	g1.drawImage(exit,exitWidth,height-16,Graphics.TOP|Graphics.LEFT);
	
	
	
	
	
	g.drawImage(mut,0,0,Graphics.LEFT|Graphics.TOP);
		
	
	
	
	
	
	
	}
	
	public void keyReleased(int KeyCode)
	{
	smiley_state=false;
	repaint();
	}
	
	public void keyPressed(int keyCode)
	{
		
		System.out.println("key = "+keyCode);
		if(keyCode==-7)/*KEY_SOFTKEY2*/
				m_s.notifyDestroyed();
		
		
		if(keyState==true)
		{
		
		 smiley_state=true;
		  if((keyCode==-3||keyCode==KEY_NUM4)&&curpos%10>0)/*KEY_LEFT_ARROW*/
		  {
		  	curpos--;
		  	System.out.println("coord Positions = "+curpos);
				
		  
		  }
		  if((keyCode==-4||keyCode==KEY_NUM6)&&curpos%10<9)/*KEY_RIGHT_ARROW*/
		  {
		  	curpos++;
		  	System.out.println("coord Positions = "+curpos);
			repaint();	
		  
		  }
		  if((keyCode==-1||keyCode==KEY_NUM2)&&curpos>=10)/*KEY_UP_ARROW*/
		  {
		  	curpos-=10;	
		  	System.out.println("coord Positions = "+curpos);
			
		  }
		  if((keyCode==-2||keyCode==KEY_NUM8)&&curpos<90)/*KEY_DOWN_ARROW*/
		  {
		  	curpos+=10;	
		  	System.out.println("coord Positions = "+curpos);
			
		  }
		  if(keyCode==KEY_NUM1)
		  {
		  	 System.out.println("coord Positions = "+curpos);
			 System.out.println("status Positions = "+status_pos[curpos]);
			
		  	
		  	if(status_pos[curpos]!=FLAG)
			{
		  		  	status_pos[curpos]=SHOW;
			
			 if(total_pos[curpos]==7)
					keyState=false;
			 
			 }
			 System.out.println("coord Positions = "+curpos);
			 System.out.println("status Positions = "+status_pos[curpos]);
		//	implementInputEffect();
		  
		  }
		  
		  
		  if(keyCode==KEY_NUM3)
		  {
		 	 System.out.println("PREVcoord Positions = "+curpos);
			 System.out.println("PREVstatus Positions = "+status_pos[curpos]);
			
		 	
		 	if(status_pos[curpos]!=SHOW&&status_pos[curpos]!=FLAG)
			{
		 	
				status_pos[curpos]=FLAG;
				flagCounter++;
				if(total_pos[curpos]==7)
					 bombCounter++;
				
				
			}		 
		 	else 
		 	if(status_pos[curpos]!=SHOW&&status_pos[curpos]==FLAG)
			{
		 			 //status_pos[curpos]=HIDE;changed for ret
		 			 status_pos[curpos]=return_f_flag;
					 flagCounter--;
					 if(total_pos[curpos]==7)
					 bombCounter--;
		 	}		
		  
		  	 System.out.println("Bombs = "+bombCounter);
			 System.out.println("Flags = "+flagCounter);
		   	 System.out.println("CURR coord Positions = "+curpos);
			 System.out.println("CURR status Positions = "+status_pos[curpos]);
			
		  
		  }
		  
		  
		  
		  
		}  
		
		  
	}


	void implementInputEffect()
	{
		for(int i=0;i<100;i++)
		{
			 
			if(status_pos[i]==SHOW && total_pos[i]==0 && i%10!=0 && i%10!=9)
			{
				
				int[] temp_status={i-1,i+1,i-9,i-10,i-11,i+11,i+10,i+9};	
				for(int j=0;j<8;j++)
				{
					if(temp_status[j]<0||temp_status[j]>99)
						temp_status[j]=i;
					
					
				}
				
				for(int k=0;k<8;k++)
				{
					if(status_pos[temp_status[k]]  != FLAG&&status_pos[temp_status[k]]!=return_f_flag)
					status_pos[temp_status[k]]  =SHOW;
					
				}
				/*
				status_pos[temp_status[0]]  =SHOW;
				status_pos[temp_status[1]]  =SHOW;
				status_pos[temp_status[2]] =SHOW;
				status_pos[temp_status[3]] =SHOW;
				status_pos[temp_status[4]]  =SHOW;
				status_pos[temp_status[5]]  =SHOW;
				status_pos[temp_status[6]] =SHOW;
				status_pos[temp_status[7]] =SHOW;
				*/
				
				
				
			}
			if(status_pos[i]==SHOW && total_pos[i]==0 && i%10==0 && i%10!=9)
			{
				
			
			
				int[] temp_status={i+1,i-9,i-10,i+11,i+10};	
				for(int j=0;j<5;j++)
				{
					if(temp_status[j]<0||temp_status[j]>99)
						temp_status[j]=i;
					
					
				}
				/*status_pos[temp_status[0]]  =SHOW;
				status_pos[temp_status[1]]  =SHOW;
				status_pos[temp_status[2]] =SHOW;
				status_pos[temp_status[3]] =SHOW;
				status_pos[temp_status[4]]  =SHOW;	
				*/
				for(int k=0;k<5;k++)
				{
					if(status_pos[temp_status[k]]  != FLAG&&status_pos[temp_status[k]]!=return_f_flag)
					status_pos[temp_status[k]]  =SHOW;
					
				}
			
			
			
			
				
			
			}
			
			if(status_pos[i]==SHOW && total_pos[i]==0 && i%10!=0 && i%10==9)
			{
				
				
				
				int[] temp_status={i-1,i-11,i-10,i+9,i+10};	
				for(int j=0;j<5;j++)
				{
					if(temp_status[j]<0||temp_status[j]>99)
						temp_status[j]=i;
					
					
				}
				/*status_pos[temp_status[0]]  =SHOW;
				status_pos[temp_status[1]]  =SHOW;
				status_pos[temp_status[2]] =SHOW;
				status_pos[temp_status[3]] =SHOW;
				status_pos[temp_status[4]]  =SHOW;	
				*/
				for(int k=0;k<5;k++)
					{
						if(status_pos[temp_status[k]]  != FLAG&&status_pos[temp_status[k]]!=return_f_flag)
							status_pos[temp_status[k]]  =SHOW;
					
					}
			}
			
			
			
			
		
		
		
		}
		int i=0;
		/*for(i=0;i<100;i++)
		{
			if(i%10==0)
				System.out.println("");
			System.out.print(status_pos[i]+" ");
	
		}
	
		if(i==100)
			System.out.println("");
		
		*/
	
	
	
	}
	
	










	public void random_positions_of_bombs()
	{
		
		Random rm= new Random(System.currentTimeMillis());
		for(int i=0;i<=9;i++)
		{
			bomb_pos[i]=Math.abs(rm.nextInt()%100);
			
			for(int j=0;j<i;j++)
				if(bomb_pos[j]==bomb_pos[i])
				{
					i=i-1;
					
					
				}
		
		
			
		}
		
		
		for(int i=0;i<10;i++)
		{
			
			System.out.println("Bomb Positions = "+bomb_pos[i]);
			
		}
		rm=null;



	}		
		
	public void related_positions_of_bombs()
	{
		
		
		for(int i=0;i<10;i++)
		{
		
			if(bomb_pos[i]%10!=0&&bomb_pos[i]%10!=9)
			{
				
				related_pos[i*8+0]=bomb_pos[i]-1;	
				related_pos[i*8+1]=bomb_pos[i]+1;	
				related_pos[i*8+2]=bomb_pos[i]-11;	
				related_pos[i*8+3]=bomb_pos[i]-10;	
				related_pos[i*8+4]=bomb_pos[i]-9;	
				related_pos[i*8+5]=bomb_pos[i]+9;	
				related_pos[i*8+6]=bomb_pos[i]+10;	
				related_pos[i*8+7]=bomb_pos[i]+11;	
				
				
			}
			
			
			if(bomb_pos[i]%10==0)
			{
				
				related_pos[i*8+0]=bomb_pos[i]+1;	
				related_pos[i*8+1]=bomb_pos[i]-9;	
				related_pos[i*8+2]=bomb_pos[i]-10;	
				related_pos[i*8+3]=bomb_pos[i]+10;	
				related_pos[i*8+4]=bomb_pos[i]+11;	
				related_pos[i*8+5]=-10;	
				related_pos[i*8+6]=-10;	
				related_pos[i*8+7]=-10;	
				
				
			}
			
			
			if(bomb_pos[i]%10==9)
			{
				
				related_pos[i*8+0]=bomb_pos[i]-1;	
				related_pos[i*8+1]=bomb_pos[i]-10;	
				related_pos[i*8+2]=bomb_pos[i]+10;	
				related_pos[i*8+3]=bomb_pos[i]-11;	
				related_pos[i*8+4]=bomb_pos[i]+9;	
				related_pos[i*8+5]=-10;	
				related_pos[i*8+6]=-10;	
				related_pos[i*8+7]=-10;	
				
				
			}
		
		
		
		
		}
	}	
		
	void set_danger_and_bomb_positons_in_total_pos()
	{
		
		for(int i=0 ; i<80 ; i++)
		{
			if(related_pos[i]>=0&&related_pos[i]<=99)
			{
					
				total_pos[related_pos[i]] =  ++total_pos[related_pos[i]];
			
			
			
			}
				
				
			
		}
		
		for(int i=0;i<10;i++)
		{
			total_pos[bomb_pos[i]]=7;
		
		}		
		
		
		
	}
	
	
	void temp_show()
	{
		int i=0;
		System.out.println("width = "+width);
		System.out.println("height = "+height);
	
		for(i=0;i<100;i++)
		{
			if(i%10==0)
				System.out.println("");
			System.out.print(total_pos[i]+" ");
	
		}
	
		if(i==100)
			System.out.println("");
	
	
	
	
	
	
	
	}
}































			
		
		
		
		
		
		
		
		
		























	











