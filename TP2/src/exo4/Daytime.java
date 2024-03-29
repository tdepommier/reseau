package exo4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Daytime {
	
	ServerSocket ss;
	Socket client;
	InputStream in;
	OutputStream out;

	public void run() throws IOException {
		
		

		ss = new ServerSocket(9876);
		
		client = ss.accept();
		out = client.getOutputStream();
		
		out.write(new Date().toString().getBytes());
		out.write(new String("\n").getBytes());
		out.close();
		client.close();
	}


	public static void main(String[] args) {
		Daytime dt = new Daytime();
		try{dt.run();}catch(Exception e) {e.printStackTrace();}
	}
	
}
