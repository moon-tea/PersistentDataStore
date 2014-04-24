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
		String inData = StreamUtil.readLine(inputStream);
		logger.debug("inData: " + inData);		
		
		//Get byte data from string
		byte[] data = inData.getBytes(Charset.forName("UTF-8"));
		
		//Write Data
		FileUtil.writeData(inMessage, data);

		// Write response
		String outMessage = "ok\n";
		StreamUtil.writeLine(outMessage, outputStream);
		logger.debug("Finished writing file");
	}
}
