package exo3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientEcho {

	
	public void run() {
		
		Socket sc = null; 
		InputStream in = null;
		OutputStream out = null;
		Scanner scanner = new Scanner(System.in);
		String msg;
		
		try {
			
			sc = new Socket ("localhost", 7);
			in = sc.getInputStream();
			out = sc.getOutputStream();
			byte[] buf = new byte[2048];
			
			
			do {
				msg=scanner.nextLine();
				out.write(msg.getBytes());
				
				in.read(buf);
				System.out.println(new String(buf));
				buf = new byte[2048];
			} while (!check(msg));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				sc.close();
			}catch (Exception e) {}
		
		}
		
	}
	
	
	private boolean check(String msg) {
		msg = msg.toLowerCase();
		if(msg.charAt(0) == '.') msg = msg.substring(1);
		
		return msg.equals("fin");
	}


	public static void main(String[] args) {
		ClientEcho cl = new ClientEcho();
		cl.run();
	}
	
}
