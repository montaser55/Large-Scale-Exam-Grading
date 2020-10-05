/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfinaldeadline22_12;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Servermain {

  
    public static void main(String[] args) {
        
        
                try {
                    ServerSocket ss=new ServerSocket(3212);
                    System.out.println("Running.....");
                    while(true){
                    Socket s=ss.accept();
                    System.out.println("Accepeted");
                    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                    Data obj=(Data) ois.readObject();
                    String msg=obj.msg;
                    System.out.println(msg);
                    
                    if(msg.equals("send_image")){
                    String path=new String("C:\\Users\\User\\Desktop\\Questionimageserver\\basic.txt");
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(buffer);
                    oos.flush();
                    BufferedReader br=new BufferedReader(new FileReader(path));
                    String a=br.readLine();
                    int num=Integer.parseInt(a);

                    for(int i=0;i<num;i++){
                    path=new String("C:\\Users\\User\\Desktop\\Questionimageserver\\photo-"+(i+1)+".jpg");
                    fis=new FileInputStream(path);
                    buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    oos=new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(buffer);
                    oos.flush();
                    }
                    }
                    else if(msg.equals("receive_criteria")){
                       ois=new ObjectInputStream(s.getInputStream());
                       int fileSize = ois.readInt();
                    
                ArrayList<File>files=new ArrayList<File>(fileSize); 
                                    for (int count=0;count < fileSize;count ++){
                        File ff=new File(ois.readUTF());
                        files.add(ff);
                }
                for (int count=0;count < fileSize;count ++){
                        byte[] buffer=(byte[]) ois.readObject();
                        String path=new String("C:\\Users\\User\\Desktop\\Servercriteria\\" + files.get(count));
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                                    }

                    }
                    else if(msg.equals("send_answer_image")){
                      
                     File myFile = new File("C:\\Users\\User\\Desktop\\ServerCriteria\\");
                     File[] Files = myFile.listFiles();
            
              
                  ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeInt(Files.length);
            
             
            for (int count=0;count<Files.length;count ++){
                  oos.writeUTF(Files[count].getName());
                   
            }
                        System.out.println(Files.length);
            
            for (int count=0;count<Files.length;count ++){
             
                     String path=new String("C:\\Users\\User\\Desktop\\ServerCriteria\\"+(count+1)+".txt");
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
     
            //dos.close();
            }  
           
                    //fos.flush();
                        
                    String path=new String("C:\\Users\\User\\Desktop\\AnswerServer\\basic.txt");
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
                    BufferedReader br=new BufferedReader(new FileReader(path));
                    String a=br.readLine();
                    int num=Integer.parseInt(a);
                    for(int i=0;i<num;i++){
                    path=new String("C:\\Users\\User\\Desktop\\AnswerServer\\answer"+(i+1)+"\\basic.txt");
                    //File f1=new File(path);
                    fis=new FileInputStream(path);
                    buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    oos=new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(buffer);
                    oos.flush();
                    br=new BufferedReader(new FileReader(path));
                    a=br.readLine();
                    int num2=Integer.parseInt(a);
                    for(int j=0;j<num2;j++){
                    path=new String("C:\\Users\\User\\Desktop\\AnswerServer\\answer"+(i+1)+"\\photo-"+(j+1)+".jpg");
                    //File f1=new File(path);
                    fis=new FileInputStream(path);
                    buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                  // oos=new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(buffer);
                    oos.flush();

                    }
                    }
                        
                    }
                    else if(msg.equals("take_answer_mark")){
                       ois=new ObjectInputStream(s.getInputStream());
                       int fileSize = ois.readInt();
                                      ArrayList<File>files=new ArrayList<File>(fileSize); 
                                    for (int count=0;count < fileSize;count ++){
                        File ff=new File(ois.readUTF());
                        files.add(ff);
                }
                for (int count=0;count < fileSize;count ++){
                        byte[] buffer=(byte[]) ois.readObject();
                        String path=new String("C:\\Users\\User\\Desktop\\Servermarks\\" + files.get(count));
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                                    }


                        
 
                    }
                                        else if(msg.equals("send_student_result")){
                                             File myFile = new File("C:\\Users\\User\\Desktop\\Servermarks\\");
            File[] Files = myFile.listFiles();
            
              
                  ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeInt(Files.length);
            
             
            for (int count=0;count<Files.length;count ++){
                  oos.writeUTF(Files[count].getName());
                   
            }
                        System.out.println(Files.length);
            
            for (int count=0;count<Files.length;count ++){
             
                     String path=new String(Files[count].toString());
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
     
            }
            myFile = new File("C:\\Users\\User\\Desktop\\Servercriteria\\");
            Files = myFile.listFiles();
            
              
            oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeInt(Files.length);
            
             
            for (int count=0;count<Files.length;count ++){
                  oos.writeUTF(Files[count].getName());
                   
            }
                        System.out.println(Files.length);
            
            for (int count=0;count<Files.length;count ++){
             
                     String path=new String(Files[count].toString());
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
     
            }  

                    String path=new String("C:\\Users\\User\\Desktop\\Studentid\\Studentid.txt");
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
                    path=new String("C:\\Users\\User\\Desktop\\Questionimageserver\\basic.txt");
                    //File f1=new File(path);
                    fis=new FileInputStream(path);
                    buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
                    }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Servermain.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        }
        
      
    
    

