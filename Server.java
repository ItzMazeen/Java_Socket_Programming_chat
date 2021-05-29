import java.io.BufferedReader; /* lire le texte reçu à partir de l'émetteur */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket; /* accepte les connexions venues des clients */
import java.net.Socket; /* permet de se connecter à la machine distante */

public class Server {

	public static void main(String[] args) {
		DataInputStream din = null;
		ServerSocket serverSocket = null;
		DataOutputStream dout = null;
		BufferedReader br = null;
		try {
			/*
			 * on va creer de socket server.
			 */
			serverSocket = new ServerSocket(6666);
			System.out.println("Server is Waiting for client request... ");

			/*
			 * creation du port 6666 Listens for a connection to be made to this socket and
			 * accepts it. The method blocks until a connection is made.
			 */
			Socket socket = serverSocket.accept();
			din = new DataInputStream(socket.getInputStream());
			/*
			 * convertir l object input
			 */
			OutputStream outputStream = socket.getOutputStream();
			dout = new DataOutputStream(outputStream);
			/*
			 * convertir l object output
			 */
			br = new BufferedReader(new InputStreamReader(System.in));

			String strFromClient = "", strToClient = "";
			while (!strFromClient.equals("stop"))
			/*
			 * Condition D'arret d'application
			 */
			{
				strFromClient = din.readUTF();
				System.out.println("client says: " + strFromClient);
				strToClient = br.readLine();
				dout.writeUTF(strToClient);
				dout.flush();
			}
		} catch (Exception exe) {
			exe.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (din != null) {
					din.close();
					/*
					 * closes the input.
					 */

				}

				if (dout != null) {
					dout.close();
					/*
					 * closes the output.
					 */
				}
				if (serverSocket != null) {
					/*
					 * closes the server socket.
					 */
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
