package Tools;

public class TimeoutControllor extends Thread
{
	private boolean TimeoutFlag = false;
	private int Timeout = 0;
	private int Unit = 0;
	
	public TimeoutControllor() { }
	
	public TimeoutControllor(int _Timeout, int _Unit)
	{
		this.Timeout = _Timeout;
		this.Unit = _Unit;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				if(!this.TimeoutFlag)
				{
					while(this.Timeout > 0 & !this.TimeoutFlag)
					{
						this.Timeout -= 1;
						Thread.sleep(this.Unit); 
					}
					this.TimeoutFlag = true;
				}
				else
				{
					Thread.sleep(100);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void Reset(int _Timeout, int _Unit)
	{
		this.TimeoutFlag = false;
		this.Timeout = _Timeout;
		this.Unit = _Unit;
	}
	
	public boolean GetFlag()
	{ return this.TimeoutFlag; }
}
