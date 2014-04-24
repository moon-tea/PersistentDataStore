package utd.persistentDataStore.simpleSocket;

import static org.junit.Assert.assertEquals;

import java.net.InetAddress;
import java.nio.charset.Charset;

import org.junit.Test;

import utd.persistentDataStore.simpleSocket.client.Client;
import utd.persistentDataStore.simpleSocket.server.Server;

public class DatastoreClientTestCase
{
	@Test
	public void testEcho() throws Exception
	{
		byte byteAddr[] = { 127, 0, 0, 1 };
		InetAddress address = InetAddress.getByAddress(byteAddr);
		int port = Server.port;
		Client client = new Client(address, port);

		String msg = "Is this the time?";
		String result = client.echo(msg);
		assertEquals(msg, result);
	}

	@Test
	public void testReverse() throws Exception
	{
		byte byteAddr[] = { 127, 0, 0, 1 };
		InetAddress address = InetAddress.getByAddress(byteAddr);
		int port = Server.port;
		Client client = new Client(address, port);

		String result = client.reverse("Is this the time?");
		assertEquals("?emit eht siht sI", result);
		
	}
	
	@Test
	public void testWrite() throws Exception
	{
		byte byteAddr[] = { 127, 0, 0, 1 };
		InetAddress address = InetAddress.getByAddress(byteAddr);
		int port = Server.port;
		Client client = new Client(address, port);
		
		String msg = "Write to file";
		byte[] data = "data".getBytes(Charset.forName("UTF-8"));
		String result = client.write(msg, data, data.length);
		assertEquals(msg, result);
		
	}
	
	@Test
	public void testRead() throws Exception
	{
		byte byteAddr[] = { 127, 0, 0, 1 };
		InetAddress address = InetAddress.getByAddress(byteAddr);
		int port = Server.port;
		Client client = new Client(address, port);
		
		String msg = "Write to file";
		String result = client.read(msg);
		assertEquals(msg, result);
		
	}

}
