package com.dh.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class BioClient {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("127.0.0.1", 8000);

			try {
				socket.getOutputStream().write((new Date() + ": hello world").getBytes());
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
