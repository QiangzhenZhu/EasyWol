package com.a10835.easywol.common.http.udp;

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

    public static void send(String ip,String port,String mac){
            try {
                String magicPackage = "0xFFFFFFFFFFFF";
                for(int i = 0; i < 16; i++){
                    magicPackage += mac;
                }
                //转换为2进制的魔术包数据
                byte[] command = hexToBinary(magicPackage);
                /*for(Byte b : command){
                    System.out.print(b.byteValue());
                }*/
                InetAddress inetAddress = InetAddress.getByName(ip);
                DatagramPacket  datagramPacket = new DatagramPacket (command,command.length-1,inetAddress,Integer.valueOf(port));
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
    /**
     * 将16进制字符串转换为用byte数组表示的二进制形式
     * @param hexString：16进制字符串
     * @return：用byte数组表示的十六进制数
     */
    private static byte[] hexToBinary(String hexString){
        //1.定义变量：用于存储转换结果的数组
        byte[] result = new byte[hexString.length()/2];

        //2.去除字符串中的16进制标识"0X"并将所有字母转换为大写
        hexString = hexString.toUpperCase().replace("0X", "");

        //3.开始转换
        //3.1.定义两个临时存储数据的变量
        char tmp1 = '0';
        char tmp2 = '0';
        //3.2.开始转换，将每两个十六进制数放进一个byte变量中
        for(int i = 0; i < hexString.length(); i += 2){
            tmp1 = hexString.charAt(i);
            tmp2 = hexString.charAt(i+1);
            result[i/2] = (byte)((hexToDec(tmp1)<<4)|(hexToDec(tmp2)));
        }
        return result;
    }

    /**
     * 用于将16进制的单个字符映射到10进制的方法
     * @param c：16进制数的一个字符
     * @return：对应的十进制数
     */
    private static byte hexToDec(char c){
        int index = "0123456789ABCDEF".indexOf(c);
        return (byte)index;
    }
}
