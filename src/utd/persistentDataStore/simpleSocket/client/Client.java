package utd.persistentDataStore.simpleSocket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.log4j.Logger;

import utd.persistentDataStore.simpleSocket.StreamUtil;

public class Client
{
	private static Logger logger = Logger.getLogger(Client.class);
	
	private InetAddress address;
	private int port;

	public Client(InetAddress address, int port)
	{
		this.address = address;
		this.port = port;
	}

	/**
	 * Sends the given string to the server which will echo it back
	 */
	public String echo(String message) throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("echo\n", outputStream);
			StreamUtil.writeLine(message, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			
			return result;
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
		
	/**
	 * Sends the given string to the server which will echo it back
	 */
	public String reverse(String message) throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("reverse\n", outputStream);
			StreamUtil.writeLine(message, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			
			return result;
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
	/**
	 * Sends the given string to the server which will write it to a file
	 */
	public String write(String message, byte[] data, int datasize) throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("write\n", outputStream);
			StreamUtil.writeLine(message, outputStream);
			StreamUtil.writeLine(new String(data, "UTF-8"), outputStream);
			StreamUtil.writeLine(Integer.toString(datasize), outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			
			return result;
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
	/**
	 * Sends the given string to the server which will try to read the file
	 */
	public String read(String message) throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Reading Message");
			StreamUtil.writeLine("read\n", outputStream);
			StreamUtil.writeLine(message, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			String size = StreamUtil.readLine(inputStream);
			logger.debug("Size " + size);
			String data = StreamUtil.readLine(inputStream);
			logger.debug("Data " + data);
			
			return ( result + "\n" + size + "\n" + data);
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
	/**
	 * Sends the given string to the server which will try to delete the file
	 */
	public String delete(String message) throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Reading Message");
			StreamUtil.writeLine("delete\n", outputStream);
			StreamUtil.writeLine(message, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			
			return result;
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
	/**
	 * Sends the given string to the server which will try to give us the dir
	 */
	public String directory() throws ClientException
	{
		try {
			logger.debug("Opening Socket");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Reading Message");
			StreamUtil.writeLine("directory\n", outputStream);
						
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			String size = StreamUtil.readLine(inputStream);
			logger.debug("Number of Files " + size);
			result += ("\n" + size + "\n");
			for(int i = 0; i < Integer.parseInt(size); i++)
			{
				String s = StreamUtil.readLine(inputStream);
				logger.debug(s);
				result += (s + "\n");
			}
			
			return result;
		}
		catch (IOException ex) {
			throw new ClientException(ex.getMessage(), ex);
		}
	}
}
