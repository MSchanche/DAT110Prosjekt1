package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {

		byte[] rpcmsg = new byte[payload.length + 1];

		rpcmsg[0] = rpcid;
		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);

		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {

		byte[] payload = Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length);

		return payload;
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {

		byte[] encoded = str.getBytes();
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {

		String decoded = new String(data);
		return decoded;
	}
	
	public static byte[] marshallVoid() {

		return new byte[0];
	}
	
	public static void unmarshallVoid(byte[] data) {

		// TODO

	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(x);
		return buffer.array();
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

		ByteBuffer buffer = ByteBuffer.wrap(data);
		return buffer.getInt();
	}
}
