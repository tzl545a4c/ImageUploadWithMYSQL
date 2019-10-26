package Tools;

import java.util.Date;
import java.text.SimpleDateFormat;

public class GetSystemDateTime 
{
	private String CurrentTime = "";
	private Date DateTime = null;
	private SimpleDateFormat TimeFormat = null;
	
	public GetSystemDateTime() { }
	
	public String Get()
	{
		this.DateTime = new Date();
		this.TimeFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		this.CurrentTime = this.TimeFormat.format(this.DateTime);
		return this.CurrentTime;
	}
	
	public String CustomGet(String _Role)
	{
		this.DateTime = new Date();
		this.TimeFormat = new SimpleDateFormat(_Role);
		this.CurrentTime = this.TimeFormat.format(this.DateTime);
		return this.CurrentTime;
	}
	
	public String GetTime()
	{
		this.DateTime = new Date();
		this.TimeFormat = new SimpleDateFormat("HH-mm-ss");
		this.CurrentTime = this.TimeFormat.format(this.DateTime);
		return this.CurrentTime;
	}
	
	public String GetDate()
	{
		this.DateTime = new Date();
		this.TimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.CurrentTime = this.TimeFormat.format(this.DateTime);
		return this.CurrentTime;
	}

	public long GetValue()
	{ return new Date().getTime(); }
	
	public long GetDataValue()
	{ return ((new Date().getTime() / 1000 + 28800) / 86400 * 86400 - 28800) * 1000; }
}
