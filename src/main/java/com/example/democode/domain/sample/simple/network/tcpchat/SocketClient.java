package com.example.democode.domain.sample.simple.network.tcpchat;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    // Fields
    ChatServer chatServer;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String clientIp;
    String chatName;

    // Constructors

    public SocketClient(ChatServer chatServer, Socket socket) {
        try {
            this.chatServer = chatServer;
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            this.clientIp = inetSocketAddress.getHostName();
            receive();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    // Methods
    // Client 가 보낸 JSON 메시지를 읽는 역할
    private void receive() {
        chatServer.threadPool.execute(()->{
            try {
                while (true) {
                    String receiveJson = dataInputStream.readUTF();

                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");

                    switch (command) {
                        case "incoming":
                            this.chatName = jsonObject.getString("data");
                            chatServer.sendToAll(this, "들어오셨습니다.");
                            chatServer.addSocketClient(this);
                            break;
                        case "message":
                            String message = jsonObject.getString("data");
                            chatServer.sendToAll(this, message);
                            break;
                    }
                }
            } catch (IOException e) {
                chatServer.sendToAll(this, "나가셨습니다.");
                chatServer.removeSocketClient(this);
            }
        });
    }

    public void send(String json) {
        try {
            dataOutputStream.writeUTF(json);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 연결 종료
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
