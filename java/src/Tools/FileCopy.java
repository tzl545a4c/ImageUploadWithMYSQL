package Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopy {

	private String FileSourcePath = "";
	private String FileDestinationPath = "";
	
	public FileCopy() {
		
	}

	public FileCopy(String FileSourcePath, String FileDestinationPath) {
		this.FileSourcePath = FileSourcePath;
		this.FileDestinationPath = FileDestinationPath;
	}
	
	@SuppressWarnings("resource")
	public void doCopy() {
		try {
			FileChannel FileInputChannel = new FileInputStream(this.FileSourcePath).getChannel();
			FileChannel FileOutputChannel = new FileOutputStream(this.FileDestinationPath).getChannel();
			FileOutputChannel.transferFrom(FileInputChannel, 0, FileInputChannel.size());
			FileInputChannel.close();
			FileOutputChannel.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
