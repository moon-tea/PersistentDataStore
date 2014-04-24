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
		
		//Get byte data from string
		byte[] data = inMessage.getBytes(Charset.forName("UTF-8"));
		
		//Process message
		//get tokens
		String[] tokens = inMessage.split("\n");
		for(int i = 0; i < tokens.length; i++)
		{
			System.out.println(i + ": " + tokens[i]);
			logger.debug(i + ": " + tokens[i]);
		}
		byte[] readData = null;
		
		//Write Data
		try { 
			readData = FileUtil.readData(inMessage);
		}
		catch (ServerException e) {	
			e.printStackTrace();
		}
		String str = new String(readData, "UTF-8"); // for UTF-8 encoding
		// Write response
		String outMessage = inMessage + "\n";
		StreamUtil.writeLine(outMessage, outputStream);
		logger.debug("Finished reading file, data read: " + str);
	}
}
