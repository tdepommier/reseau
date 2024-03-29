

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {


	private DatagramSocket dg;
	private DatagramPacket dp;
	private String addr;
	private int port;


	ClientUDP(int pSrv,String addr) throws IOException {
		this.port = pSrv;
		this.addr = addr;
		try {
			dg = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void connect() {

		byte[] buffer = this.addr.getBytes();
		try {
			dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(addr), port);
			while(true) {
				Scanner sc = new Scanner(System.in);
				dp.setData(sc.nextLine().getBytes());
				dg.send(dp);
				
				DatagramPacket dpr = new DatagramPacket(new byte[5000],5000);
				dg.receive(dpr);
				int taille = Integer.valueOf(new String(dpr.getData()).split("\0")[0]);
				
				dpr = new DatagramPacket(new byte[taille],taille);
				dg.receive(dpr);
				
				byte[] b = dpr.getData();
				System.out.println("Réponse : "+new String(b));

				try{Thread.sleep(2000);}catch(Exception e) {}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) throws IOException {
		int DEFAULT_PORT = 9873;
		String DEFAULT_ADRESS = "localhost";
		if(args.length == 2) {
			DEFAULT_ADRESS = args[0];
			DEFAULT_PORT = Integer.valueOf(args[1]);
		}
		new ClientUDP( DEFAULT_PORT, DEFAULT_ADRESS).connect();
	}

}
