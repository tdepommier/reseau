package exo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SrvBeg {

	ServerSocket ss;
	Socket client;
	InputStream in;
	OutputStream out;
	
	PrintWriter pw;
	BufferedReader br;

	public void run() throws IOException {

		ss = new ServerSocket(9876);
		
		client = ss.accept();
	
		out = client.getOutputStream();
		in = client.getInputStream();
		
		while(true) {
			String msg = getMessage(in);
			
			String reponse = getReponse(msg);
			out.write(reponse.getBytes());
		}

	}

	/*
	 * Cette methode permet de creer la réponse grace au String passé en paramétre
	 */
	private String getReponse(String msg) {
		String reponse ="";
		int nbr;
		try {
			nbr = Integer.valueOf(msg.split(":")[0]);
			if(nbr == 0)return "0\n";
			msg = msg.split(":")[1];
			String[]tabMsg = msg.split(" ");
			tabMsg[tabMsg.length-1] = tabMsg[tabMsg.length-1].split("\0|\n")[0];
			
			for (int i = 0; i < tabMsg.length; i++) {
				
				for (int j = 0; j < nbr; j++) {
					reponse += tabMsg[i] + " ";
				}
			}	
			
			
		}catch (NumberFormatException e) {
			reponse = "1Erreur : multiplicateur manquant\n";
			return reponse;
		}
		return reponse;
	}


	private String getMessage(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
		try {
			return br.readLine();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "erreur";
	}


	public static void main(String[] args) {
		SrvBeg sb = new SrvBeg();
		try{sb.run();}catch(Exception e) {e.printStackTrace();}
	}
	
	
	
}


/*String msg = new String(dgPacket.getData());
						
			try {
				nbr = Integer.valueOf(msg.split(":")[0]);
				msg = msg.split(":")[1];
				String[]tabMsg = msg.split(" ");
				tabMsg[tabMsg.length-1] = tabMsg[tabMsg.length-1].split("\0|\n")[0];
				
				for (int i = 0; i < tabMsg.length; i++) {
					
					for (int j = 0; j < nbr; j++) {
						reponse = reponse + tabMsg[i] + " ";
					}
				}	
				
				
			}catch (NumberFormatException e) {
				reponse = "1Erreur : multiplicateur manquant";
			}
*/
