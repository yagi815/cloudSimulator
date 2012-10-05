package multiSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	public final static int ECHO_PORT = 3000;

	public static void main(String args[]) {
		ServerSocket serverSock;
		Socket sock;
		ConThread ct;
		try {
			serverSock = new ServerSocket(ECHO_PORT);
			try {
				while (true) {
					sock = serverSock.accept();
					ct = new ConThread(sock);
					ct.start();

					System.out.println("접속이 들어왔습니다." + ct);

				}

			} catch (IOException e) {
				serverSock.close();
				System.err.println(e);

			}

		} catch (IOException e) {

			System.err.println(e);

		}
	}
}

class ConThread extends Thread{
	Socket sock;
	BufferedReader br;
	PrintWriter pw;

	public ConThread(Socket s) {
		sock = s;
		try {
			br = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {

			System.err.println("Error:" + e);

		}
	}

	public void run() {

		String str;

		try {
			while (true) {
				str = br.readLine();
				if (str != null) {
					if (str.equals("quit"))
						break;
					pw.println(str);
					pw.flush();

				} else
					break;
			}
			sock.close();
		}

		catch (IOException e) {
			System.err.println(e);
		}
	}
}
	
