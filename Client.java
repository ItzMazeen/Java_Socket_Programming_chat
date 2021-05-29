import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		BufferedReader br = null;

		try {
			/*
			 * Creates a stream socket and connects it to the specified port number at the
			 * specified IP address.
			 */
			socket = new Socket("localhost", 6666);
			din = new DataInputStream(socket.getInputStream());

			/*
			 * returns the OutputStream attached with this socket.
			 */
			OutputStream outputStream = socket.getOutputStream();
			dout = new DataOutputStream(outputStream);

			br = new BufferedReader(new InputStreamReader(System.in));

			String strFromServer = "", strToClient = "";
			while (!strFromServer.equals("stop"))
			/*
			 * Condition D'arret d'application
			 */
			{
				strFromServer = br.readLine();
				dout.writeUTF(strFromServer);
				dout.flush();
				strToClient = din.readUTF();
				System.out.println("Server says: " + strToClient);
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
				}

				if (dout != null) {
					dout.close();
				}
				if (socket != null) {
					/*
					 * closes this socket
					 */
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}