package net;

import json.MyGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Session {
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private File otherSharedDirectory;

    public Session(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.dataInputStream = new DataInputStream(
                    clientSocket.getInputStream());
            this.dataOutputStream = new DataOutputStream(
                    clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void communicate(String message) throws IOException {
        dataOutputStream.writeUTF(message);
        File directory = MyGson.fromJson(
                dataInputStream.readUTF(), File.class);
        this.otherSharedDirectory = directory;
    }

    public File getOtherSharedDirectory() {
        return otherSharedDirectory;
    }

    public void setOtherSharedDirectory(File otherSharedDirectory) {
        this.otherSharedDirectory = otherSharedDirectory;
    }
}
