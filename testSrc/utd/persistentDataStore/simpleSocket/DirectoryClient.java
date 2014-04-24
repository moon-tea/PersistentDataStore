package utd.persistentDataStore.simpleSocket;

import java.net.InetAddress;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.client.Client;
import utd.persistentDataStore.simpleSocket.server.Server;

public class DirectoryClient
{
	private static Logger logger = Logger.getLogger(DirectoryClient.class);
	
	public static void main(String args[])
	{
		try {
			byte byteAddr[] = {127, 0, 0, 1};
			InetAddress address  = InetAddress.getByAddress(byteAddr);
			int port = Server.port;
			Client client = new Client(address, port);
			
			logger.debug("Sending Request for Directory");
			String reply = client.directory();
			
			logger.debug("Received Response <" + reply + ">");
		}
		catch(Exception ex) {
			logger.error("Exception Thrown " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
