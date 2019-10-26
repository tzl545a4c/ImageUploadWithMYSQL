package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FileBase64Convert 
{
	private String Base64Data = "";
    private String FilePath = "";
    private String FileName = "";
    private int    FileSize = 0;
    private byte[] FileData = null;
    private File File = null;
    private FileInputStream StreamFI = null;
    private FileOutputStream StreamFO = null;

    public FileBase64Convert() { }

    public String ToBase64(String _FilePath, String _FileName, String _FileMark)
    {
        try
        {
        	this.FilePath = _FilePath;
        	this.FileName = _FileName;
            this.File = new File(this.FilePath + this.FileName);
            this.StreamFI = new FileInputStream(this.File);
            this.FileSize = (int) this.File.length();
            this.FileData = new byte[this.FileSize];
            this.StreamFI.read(this.FileData);
            this.StreamFI.close();

            this.Base64Data = Base64.getEncoder().encodeToString(this.FileData);
        }
        catch (IOException e)
        { e.printStackTrace(); }

        return this.Base64Data;
    }

    public boolean ToFile(String _RootPath, String _FilePath, String _FileName, String _Base64Data)
    {
        try
        {
        	this.Base64Data = _Base64Data;
        	this.FileName = _FileName;
            this.FilePath = _RootPath + _FilePath;

            if(!new File(this.FilePath).exists())
            { new File(this.FilePath).mkdirs(); }

            this.FileData = Base64.getDecoder().decode(this.Base64Data);
            this.FileSize = this.FileData.length; 
            this.File = new File(this.FilePath + this.FileName);
            this.StreamFO = new FileOutputStream(this.File);
            this.StreamFO.write(this.FileData, 0, this.FileSize);
            this.StreamFO.flush();
            this.StreamFO.close();

            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
