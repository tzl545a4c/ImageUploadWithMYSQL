package Tools;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;

public class FileObjectConvert
{
    private Map<String, Object> FileInfo = new HashMap<String, Object>();
    private String FilePath = "";
    private String FileName = "";
    private String FileMark = "";
    private int    FileSize = 0;
    private byte[] FileData = null;
    private File File = null;
    private FileInputStream StreamFI = null;
    private FileOutputStream StreamFO = null;

    public FileObjectConvert() { }

    public Map<String, Object> ToObject(String _FilePath, String _FileName, String _FileMark)
    {
        try
        {
        	this.FilePath = _FilePath;
        	this.FileName = _FileName;
        	this.FileMark = _FileMark;
            this.File = new File(this.FilePath + this.FileName);
            this.StreamFI = new FileInputStream(this.File);
            this.FileSize = (int) this.File.length();
            this.FileData = new byte[this.FileSize];
            this.StreamFI.read(this.FileData);
            this.StreamFI.close();

            this.FileInfo.put("FilePath", this.FilePath);
            this.FileInfo.put("FileName", this.FileName);
            this.FileInfo.put("FileMark", this.FileMark);
            this.FileInfo.put("FileSize", this.FileSize);
            this.FileInfo.put("FileData", this.FileData);
        }
        catch (IOException e)
        { e.printStackTrace(); }

        return this.FileInfo;
    }

    public boolean ToFile(String _RootPath, String _FilePath, Map<String, Object> _FileInfo)
    {
        try
        {
        	this.FileInfo = _FileInfo;
        	this.FileName = this.FileInfo.get("FileName").toString();
        	
        	if(!_FilePath.equals(""))
            { this.FilePath = _RootPath + _FilePath; }
        	else
        	{ this.FilePath = _RootPath + this.FileInfo.get("FilePath").toString(); }

            if(!new File(this.FilePath).exists())
            { new File(this.FilePath).mkdirs(); }

            this.FileSize = (int) this.FileInfo.get("FileSize");
            this.FileData = new byte[this.FileSize];
            this.FileData = (byte[]) this.FileInfo.get("FileData");
            this.File = new File(this.FilePath + this.FileName);
            
            this.StreamFO = new FileOutputStream(this.File);
            this.StreamFO.write(this.FileData, 0, this.FileSize);
            this.StreamFO.flush();
            this.StreamFO.close();

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean AddToFile(String _RootPath, String _FilePath, Map<String, Object> _FileInfo)
    {
        try
        {
        	this.FileInfo = _FileInfo;
        	this.FileName = this.FileInfo.get("FileName").toString();
        	
        	if(!_FilePath.equals(""))
            { this.FilePath = _RootPath + _FilePath; }
        	else
        	{ this.FilePath = _RootPath + this.FileInfo.get("FilePath").toString(); }

            if(!new File(this.FilePath).exists())
            { new File(this.FilePath).mkdirs(); }

            this.FileSize = (int) this.FileInfo.get("FileSize");
            this.FileData = new byte[this.FileSize];
            this.FileData = (byte[]) this.FileInfo.get("FileData");
            this.File = new File(this.FilePath + this.FileName);
            
            this.StreamFO = new FileOutputStream(this.File, true);
            this.StreamFO.write(this.FileData, 0, this.FileSize);
            this.StreamFO.flush();
            this.StreamFO.close();

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
