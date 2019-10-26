package WebServer.PostRequestHandle;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import FileUpload.Config.FileUploadPathConfig;
import Tools.RandomGenerator;

public class FileUploadCheck {

	private byte[] PostData = null;
	
	public FileUploadCheck() {
		
	}
	
	public FileUploadCheck(byte[] PostData) {
		this.PostData = PostData;
	}
	
	public String doCheck() {
		String FileUploadNameCheck = "";
		String FileUploadName = "";
		String FileUploadPath = FileUploadPathConfig.getUploadFilePath();

		// Decode Data with UTF8
		try {
			FileUploadNameCheck = URLDecoder.decode(new String(this.PostData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
				
		FileUploadName = new RandomGenerator().Get(30) + "." + FileUploadNameCheck.split("\\.")[1];
		
		while (new File(FileUploadPath + FileUploadName).exists()) {
			FileUploadName = new RandomGenerator().Get(30) + "." + FileUploadNameCheck.split("\\.")[1];
		}
		
		return FileUploadName;
	}
}
