package utd.persistentDataStore.simpleSocket.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.FileUtil;
import utd.persistentDataStore.simpleSocket.StreamUtil;

public class DirectoryHandler extends Handler
{
	private static Logger logger = Logger.getLogger(DirectoryHandler.class);

	public void run() throws IOException
	{
		// Read message				
		List<String> dir = new ArrayList<String>();
		//Write Data
		try { 
			dir = FileUtil.directory();			
		}
		catch (ServerException e) {	
			e.printStackTrace();
		}
		
		StreamUtil.writeLine("ok", outputStream);
		StreamUtil.writeLine(Integer.toString(dir.size()), outputStream);
		for(String file: dir) {
			StreamUtil.writeLine(file, outputStream);			
		}		
		logger.debug("Finished getting file list");
	}
}
