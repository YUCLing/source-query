package me.luotianyi.sourcequery;

public class Packets
{
    public static byte[] A2S_INFO = new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x54, 0x53, 0x6F, 0x75, 0x72, 0x63, 0x65, 0x20, 0x45, 0x6E, 0x67, 0x69, 0x6E, 0x65, 0x20, 0x51, 0x75, 0x65, 0x72, 0x79, 0x00 };

    public static byte[] A2S_PLAYER = new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x55 };

    public static byte[] A2S_PLAYER_CHALLENGE = new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x55, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
}