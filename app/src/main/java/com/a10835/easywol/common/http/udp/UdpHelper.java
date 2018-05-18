package com.a10835.easywol.common.http.udp;

import android.util.Log;

import com.a10835.easywol.view.ToastUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by 10835 on 2018/5/5.
 */

public class UdpHelper {
    private static final String TAG = "UdpHelper";

    public static void send(String ip,String port,String mac){
            try {
                String magicPackage = "FFFFFFFFFFFF";
                for(int i = 0; i < 16; i++){
                    magicPackage += mac;
                }
                //转换为2进制的魔术包数据
                byte[] packet = stringToByte(magicPackage);
                /*for(Byte b : command){
                    System.out.print(b.byteValue());
                }*/
                InetAddress inetAddress = InetAddress.getByName(ip);
                DatagramPacket  datagramPacket = new DatagramPacket (packet,packet.length,inetAddress,Integer.valueOf(port));
                DatagramSocket socket = new DatagramSocket(Integer.valueOf(port));
                socket.send(datagramPacket);
                socket.close();
            } catch (SocketException e) {
                e.printStackTrace();
            }catch (UnknownHostException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

    }


    public static byte[] stringToByte(String s){
        String [] va = new String[s.length()/2];
        int j = 0;
        for (int i = 1; i <=s.length() ; i++) {
            if (i!=0&&i%2==0){
                va[j] = s.substring(i-2,i);
                j++;
            }
        }
          byte vd[]=new byte[va.length];
          for(int i=0; i<va.length; i++) {
              vd[i] = (byte) Integer.parseInt(va[i], 16);
              Log.d(TAG, "stringToByte: "+va[i]+":"+vd[i]);
          }
          return vd;
    }


}
