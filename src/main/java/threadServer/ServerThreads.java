package threadServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import db.pojos.Questionary;

public class ServerThreads {

	public static void main(String args[]) throws IOException {

		ServerSocket serverSocket = new ServerSocket(9000);
		try {

			while (true) {
				// Thie executes when we have a client
				Socket socket = serverSocket.accept();
				new Thread(new ServerThreadsClient(socket)).start();
			}
		} finally {
			releaseResourcesServer(serverSocket);
		}

	}

	private static void releaseResourcesServer(ServerSocket serverSocket) {
		try {
			serverSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
