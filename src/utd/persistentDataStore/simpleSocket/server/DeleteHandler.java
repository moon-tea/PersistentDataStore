package utd.persistentDataStore.simpleSocket.server;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.FileUtil;
import utd.persistentDataStore.simpleSocket.StreamUtil;

public class DeleteHandler extends Handler
{
	private static Logger logger = Logger.getLogger(DeleteHandler.class);

	public void run() throws IOException
	{
		// Read message
		String inMessage = StreamUtil.readLine(inputStream);
		logger.debug("inMessage: " + inMessage);		
		boolean deleted = false;	
		//Write Data
		try { 
			deleted = FileUtil.deleteData(inMessage);
		}
		catch (ServerException e) {	
			e.printStackTrace();
		}		
		// Write response
		String outMessage = "";
		if(deleted)
		{
			outMessage = "ok\n"; 
		}
		else
		{			
			outMessage = "ErrorFileNotFound\n";
		}
		StreamUtil.writeLine(outMessage, outputStream);
		logger.debug("Finished deleting file.");
	}
}
