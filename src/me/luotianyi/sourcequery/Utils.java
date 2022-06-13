package me.luotianyi.sourcequery;

import javafx.beans.property.StringProperty;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Ref<E> {
    E ref;
    public Ref(E e ){
        ref = e;
    }
    public E g() { return ref; }
    public void s( E e ){ this.ref = e; }

    public String toString() {
        return ref.toString();
    }
}

public class Utils {
    public static byte[] ByteToPrimitives(Byte[] oBytes)
    {
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }

    public static Byte[] ConcatBytes(byte[]... bytes)
    {
        ArrayList<Byte> list = new ArrayList();
        for (byte[] collection : bytes)
        {
            for (byte byt : collection) {
                list.add(byt);
            }
        }
        return list.toArray(new Byte[0]);
    }

    public static int ReadString(byte[] byt, Ref<String> str, int position)
    {
        int num = 0;
        ArrayList<Byte> list = new ArrayList();
        for (int i = position; i < byt.length; i++)
        {
            byte b = byt[i];
            if (b == 0x00)
            {
                break;
            }
            list.add(b);
            num++;
        }
        str.s(new String(ByteToPrimitives(list.toArray(new Byte[0])), StandardCharsets.UTF_8));
        return num;
    }

    public static byte[] GetBytes(byte[] byts, int offset, int length) {
        ArrayList<Byte> list = new ArrayList();
        for (int i = offset;i<offset+length;i++) {
            list.add(Byte.valueOf(byts[i]));
        }
        return ByteToPrimitives(list.toArray(new Byte[0]));
    }
}
