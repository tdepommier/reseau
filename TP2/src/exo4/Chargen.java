package exo4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Chargen {


	ServerSocket ss;
	Socket client;
	InputStream in;
	OutputStream out;



	public void run() throws IOException {

		ss = new ServerSocket(9876);
		
		client = ss.accept();
		out = client.getOutputStream();
		
		while(true) {
			for(int i = 32 ; i<127 ; i++) {
				out.write((char)i);
			}
		}

	}


	public static void main(String[] args) {
		Chargen ch = new Chargen();
		try{ch.run();}catch(Exception e) {e.printStackTrace();}
	}


}