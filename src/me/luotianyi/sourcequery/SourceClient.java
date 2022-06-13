package me.luotianyi.sourcequery;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class SourceClient
{
    private DatagramSocket client;

    public SourceClient(DatagramSocket client)
    {
        this.client = client;
        try {
            client.setSoTimeout(5000);
        } catch(Exception ex) {}
    }

    public SourceClient(String ip, int port) throws SocketException, UnknownHostException {
        client = new DatagramSocket();
        client.connect(InetAddress.getByName(ip), port);
        try {
            client.setSoTimeout(5000);
        } catch(Exception ex) {}
    }

    public void setTimeout(int time) throws SocketException {
        client.setSoTimeout(time);
    }

    public SourceInfo GetServerInfo() throws IOException {
        client.send(new DatagramPacket(Packets.A2S_INFO, Packets.A2S_INFO.length));
        byte[] array = new byte[1024];
        byte[] challenage;
        do
        {
            if (array[4] == 0x41) {
                challenage = Utils.GetBytes(array, 5, 4);
                array = new byte[1024];
                byte[] array3 = Utils.ByteToPrimitives(Utils.ConcatBytes(Packets.A2S_INFO, challenage));
                client.send(new DatagramPacket(array3, array3.length));
            } else {
                DatagramPacket packet = new DatagramPacket(array, array.length);
                client.receive(packet);
                array = packet.getData();
            }
        }
        while (array[4] != 0x49);
        int num = 5;
        byte protocol = array[num];
        num++;
        Ref<String> str = new Ref<>("");
        num += Utils.ReadString(array, str, num) + 1;
        Ref<String> str2 = new Ref<>("");
        num += Utils.ReadString(array, str2, num) + 1;
        Ref<String> str3 = new Ref<>("");
        num += Utils.ReadString(array, str3, num) + 1;
        Ref<String> str4 = new Ref<>("");
        num += Utils.ReadString(array, str4, num) + 1;
        short num2 = ByteBuffer.wrap(array, num, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        num += 2;
        int players = array[num];
        num++;
        int maxplayers = array[num];
        num++;
        int bots = array[num];
        num++;
        String string = String.valueOf(array[num]);
        num++;
        String string2 = String.valueOf(array[num]);
        num++;
        boolean visible = array[num] == 0;
        num++;
        boolean vac = array[num] == 1;
        num++;
        Ref<String> str5 = new Ref<>("");
        Utils.ReadString(array, str5, num);
        return new SourceInfo(protocol, str.g(), str2.g(), str3.g(), str4.g(), num2, players, maxplayers, bots, string, string2, visible, vac, str5.g());
    }

    public Player[] GetPlayers() throws IOException {
        client.send(new DatagramPacket(Packets.A2S_PLAYER_CHALLENGE, Packets.A2S_PLAYER_CHALLENGE.length));
        byte[] array = new byte[10];
        do
        {
            DatagramPacket packet = new DatagramPacket(array, array.length);
            client.receive(packet);
            array = packet.getData();
        }
        while (array[4] != 0x41);
        byte[] array2 = Utils.GetBytes(array, 5, 4);
        byte[] array3 = Utils.ByteToPrimitives(Utils.ConcatBytes(Packets.A2S_PLAYER, array2));
        client.send(new DatagramPacket(array3, array3.length));
        do
        {
            array = new byte[8192];
            DatagramPacket packet = new DatagramPacket(array, array.length);
            client.receive(packet);
            array = packet.getData();
        }
        while (array[4] != 0x44);
        int num = 5;
        int num2 = array[num];
        num++;
        Player[] array4 = new Player[num2];
        for (int i = 0; i < num2; i++)
        {
            int index = array[num];
            num++;
            Ref<String> str = new Ref<>("");
            num += Utils.ReadString(array, str, num) + 1;
            long score = ByteBuffer.wrap(Utils.GetBytes(array, num, 4)).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
            num += 4;
            float duration = ByteBuffer.wrap(Utils.GetBytes(array, num, 4)).order(java.nio.ByteOrder.LITTLE_ENDIAN).getFloat();
            num += 4;
            array4[i] = new Player(index, str.g(), score, duration);
        }
        return array4;
    }
}
