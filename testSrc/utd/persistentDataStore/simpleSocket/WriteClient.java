package utd.persistentDataStore.simpleSocket;

import java.net.InetAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.client.Client;
import utd.persistentDataStore.simpleSocket.server.Server;

public class WriteClient
{
	private static Logger logger = Logger.getLogger(WriteClient.class);
	
	public static void main(String args[])
	{
		try {
			byte byteAddr[] = {127, 0, 0, 1};
			InetAddress address  = InetAddress.getByAddress(byteAddr);
			int port = Server.port;
			Client client = new Client(address, port);
			
			String msg = "Write to file";
			byte[] data = "data".getBytes(Charset.forName("UTF-8"));
			logger.debug("Sending Request " + msg);
			String reply = client.write(msg, data, data.length);
			
			logger.debug("Received Response <" + reply + ">");
		}
		catch(Exception ex) {
			logger.error("Exception Thrown " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
