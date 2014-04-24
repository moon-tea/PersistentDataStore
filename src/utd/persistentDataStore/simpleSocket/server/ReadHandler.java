package utd.persistentDataStore.simpleSocket.server;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.FileUtil;
import utd.persistentDataStore.simpleSocket.StreamUtil;

public class ReadHandler extends Handler
{
	private static Logger logger = Logger.getLogger(ReadHandler.class);

	public void run() throws IOException
	{
		// Read message
		String inMessage = StreamUtil.readLine(inputStream);
		logger.debug("inMessage: " + inMessage);		
		
		byte[] readData = null;		
		//Write Data
		try { 
			readData = FileUtil.readData(inMessage);			
		}
		catch (ServerException e) {	
			e.printStackTrace();
		}
		String str = "no data read";
		// Write response
		String outMessage = "";
		if( readData == null)
		{
			StreamUtil.writeLine("fileReadError", outputStream); 
		}
		else
		{			
			StreamUtil.writeLine("ok", outputStream);
			StreamUtil.writeLine(Integer.toString(readData.length), outputStream);
			StreamUtil.writeLine(str, outputStream);			
		}		
		logger.debug("Finished reading file, data read: " + str);
	}
}
