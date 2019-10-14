package paket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main implements Runnable{
	
	char[] keyArray = new char[] {'H','a','l','l','o'};

	public static void main(String[] args) {
		try {
			Thread.sleep(2000);
			new Main();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Main() {
		new Thread(this).start();
	}
	
	public void run() {
		for(int i=0;i<100;i++) {
			try {
				byte[] noKeys = new byte[8];
				Arrays.fill(noKeys, (byte)0);
				for(char key : keyArray) {
					pressKeys(toBytes(key));
					pressKeys(noKeys);
				}
			}
			catch(IOException ignored) {}
		}
	}
	
	public byte[] toBytes(char key) {
		byte[] bytes = new byte[8];
		Arrays.fill(bytes, (byte)0);

		int charInt = (int)key;
		if(charInt<=122 & charInt>=97) { //lower case letter
			bytes[2] = ((byte) (charInt-(96-3))); // a = 4; b = 5; ...
		}
		if(charInt<=90 & charInt>=65) {
			bytes[0] = (byte)32;
			bytes[2] = ((byte) (charInt-(64-3)));
		}
		
		return bytes;
	}
	
	public void pressKeys(byte[] bytes) throws IOException {
        FileOutputStream fileOuputStream = new FileOutputStream(new File("/dev/hidg0"));
        fileOuputStream.write(bytes);
        fileOuputStream.close();
	}

}
