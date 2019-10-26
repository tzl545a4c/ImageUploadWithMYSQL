package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReadWrite 
{
	private File File = null;
	private FileWriter FileW = null;
	private String FileName = "";
	private int    FileSize = 0;
	private byte[] FileData = null;
	private FileInputStream StreamFI = null;
	
	private String FilePath = "";
	private String FileContent = "";
	
	public FileReadWrite() { }
	
	public String Read(String _FilePath)
	{
		this.FilePath = _FilePath;
		this.FileContent = "";
		
		try 
		{
			this.File = new File(_FilePath + this.FileName);
			this.StreamFI = new FileInputStream(this.File);
			this.FileSize = (int) this.File.length();
			this.FileData = new byte[this.FileSize];
			this.StreamFI.read(this.FileData);
			this.StreamFI.close();
			this.FileContent = new String(this.FileData);
			this.FileContent = this.FileContent.replaceAll("\r\n", "\n");
			this.FileContent = this.FileContent.replaceAll("\n", "\r\n");
		} 
		catch(IOException e) { }
		
		return this.FileContent;
	}

	public void Write(String _FilePath, String _FileContent)
	{
		this.FilePath = _FilePath;
		this.FileContent = _FileContent;
		
		try 
		{
	        this.FileW = new FileWriter(this.FilePath, true);
	        this.FileW.write(this.FileContent);
			
	        this.FileW.flush();
	        this.FileW.close();
		} 
		catch(IOException e) { e.printStackTrace(); }
	}
	
	public void Overwrite(String _FilePath, String _FileContent)
	{
		this.FilePath = _FilePath;
		
		try 
		{
			this.File = new File(this.FilePath);
			if(!this.File.exists()) { this.File.createNewFile(); }
			
			this.FileContent = _FileContent;
	        this.FileW = new FileWriter(this.FilePath);
	        this.FileW.write(this.FileContent);
			
	        this.FileW.flush();
	        this.FileW.close();
		} 
		catch(IOException e) { }
	}
}
