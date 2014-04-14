package utd.persistentDataStore.simpleSocket.server;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.FileUtil;
import utd.persistentDataStore.simpleSocket.StreamUtil;

public class WriteHandler extends Handler
{
	private static Logger logger = Logger.getLogger(WriteHandler.class);

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
		
		//Write Data
		FileUtil.writeData(inMessage, data);

		// Write response
		String outMessage = inMessage + "\n";
		StreamUtil.writeLine(outMessage, outputStream);
		logger.debug("Finished writing file");
	}
}
