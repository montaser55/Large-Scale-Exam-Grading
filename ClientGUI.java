/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfinaldeadline22_12;

import javafx.scene.image.Image ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */


public class ClientGUI extends Application {
    int posx=600;
    int posy=300;
    int presentsceneno=-1;  
    int photonum=1;
    int scenetotalint=0;
    int presentscenenoans=-1;  
    int photonumans=1;
    int criteriano;
    int flag =0;
    String abcd;
    int totalques;
     String Studentid[]=new String[0];
    int scenetotalintans=0;
    int[] deductions;
    String[] criterias;
    String criterianos;
    String questionfilepath;
    int y;
    String path;
    Scene[] scenes;
    Scene[] sceneans;
    int submissionflag=0;
    int totalscripts=0;
    int totalmarks=0;
    int marksobtained=0;
    int[] array;
    int possX=300;
    int possY=20;
    int totalstudentnum=0;
    int criteriaarray[]=new int[0];
    int numberofquestions=0;
    int Totalmarks[][]=new int[0][0];
    int markobtained[][]=new int[0][0];
    int hh;
    
         
    
    @Override
    public void start(Stage primaryStage) {
        firstpage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    void firstpage(Stage stage) {
                Stage thisstage=stage;
                Label enteras=new Label("Enter as: ");
                enteras.setLayoutX(posx+12);
                enteras.setLayoutY(posy-30);
                Button questionsetter=new Button("Question Setter");
                questionsetter.setLayoutX(posx-70);
                questionsetter.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        try {                           
                            Socket s=new Socket("127.0.0.1",3212);
                            
                        ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                        String msg="send_image";
                        System.out.println(msg);
                        Data obj=new Data();
                        obj.msg=msg;
                        oos.writeObject(obj);
                        oos.flush();
                        ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                        byte[] buffer=(byte[]) ois.readObject();
                        //boolean success = (new File("C:\\Users\\User\\Desktop\\Questionreceiverclient")).mkdirs();
                        path=new String("C:\\Users\\User\\Desktop\\Questionreceiverclient");
                        String abcd=path+"\\basic.txt";
                        FileOutputStream fos=new FileOutputStream(abcd);
                        fos.write(buffer);
                        fos.flush();
                        
                        BufferedReader br=new BufferedReader(new FileReader(abcd));
                        String a=br.readLine();
                        int num=Integer.parseInt(a);
                       
                        for(int i=0;i<num;i++){
                        ois=new ObjectInputStream(s.getInputStream()); 
                        buffer=(byte[]) ois.readObject();
                        String pathh=new String("C:\\Users\\User\\Desktop\\Questionreceiverclient\\photo-"+(i+1)+".jpg");
                        fos=new FileOutputStream(pathh);
                        fos.write(buffer);
                        fos.flush();
                        }

                        } catch (IOException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            questionsetting(thisstage,path);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                questionsetter.setLayoutY(posy+30);
                Button scriptchecker=new Button("Script Checker");
                scriptchecker.setLayoutX(posx+50);
                scriptchecker.setLayoutY(posy+30);
                scriptchecker.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Socket s=new Socket("127.0.0.1",3212);
                            
                                                  ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                      
                        String msg="send_answer_image";
                        System.out.println(msg);
                        Data obj=new Data();
                        obj.msg=msg;
                        oos.writeObject(obj);
                        oos.flush();
                        ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                       int fileSize = ois.readInt();
                    
                ArrayList<File>files=new ArrayList<File>(fileSize); 
                                    for (int count=0;count < fileSize;count ++){
                        File ff=new File(ois.readUTF());
                        files.add(ff);
                }
                for (int count=0;count < fileSize;count ++){
                        byte[] buffer=(byte[]) ois.readObject();
                        //boolean success = (new File("C:\\Users\\User\\Desktop\\ClientCriteria2")).mkdirs();
                        String path=new String("C:\\Users\\User\\Desktop\\ClientCriteria2\\" + files.get(count));
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                                    }
                        byte[] buffer=(byte[]) ois.readObject();
                        //boolean success = (new File("C:\\Users\\User\\Desktop\\AnswerClient")).mkdirs();
                        abcd=new String("C:\\Users\\User\\Desktop\\AnswerClient");
                        String path=new String("C:\\Users\\User\\Desktop\\AnswerClient\\basic.txt");
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                        
                        BufferedReader br=new BufferedReader(new FileReader(path));
                        String a=br.readLine();
                        int num=Integer.parseInt(a);
                        for(int i=0;i<num;i++){
                           boolean success = (new File("C:\\Users\\User\\Desktop\\AnswerClient\\answer"+(i+1))).mkdirs();
                        }
                        for(int i=0;i<num;i++){
                        ois=new ObjectInputStream(s.getInputStream());
                        buffer=(byte[]) ois.readObject();
                        path=new String("C:\\Users\\User\\Desktop\\AnswerClient\\answer"+(i+1)+"\\basic.txt");
                       
                        fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                        br=new BufferedReader(new FileReader(path));
                        a=br.readLine();
                        int num2=Integer.parseInt(a);
                        for(int j=0;j<num2;j++){
                     //   ois=new ObjectInputStream(s.getInputStream()); 
                        buffer=(byte[]) ois.readObject();
                        path=new String("C:\\Users\\User\\Desktop\\AnswerClient\\answer"+(i+1)+"\\photo-"+(j+1)+".jpg");
                        fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                        }
                        }

                        } catch (IOException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        answerpaster(thisstage,abcd);                           
                    }
                    
                });
                Button showresult=new Button("Show Result");
                showresult.setLayoutX(posx-2);
                showresult.setLayoutY(posy+70);
                showresult.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Socket s=new Socket("127.0.0.1",3212);
                                                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                               
             String msg="send_student_result";
             System.out.println(msg);
             Data obj=new Data();
             obj.msg=msg;
             oos.writeObject(obj);
             oos.flush();
                                    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                       int fileSize = ois.readInt();
                    
                ArrayList<File>files=new ArrayList<File>(fileSize); 
                                    for (int count=0;count < fileSize;count ++){
                        File ff=new File(ois.readUTF());
                        files.add(ff);
                }
                for (int count=0;count < fileSize;count ++){
                        byte[] buffer=(byte[]) ois.readObject();
                        String path=new String("C:\\Users\\User\\Desktop\\Clientmarks2\\" + files.get(count));
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                                    }
                           ois=new ObjectInputStream(s.getInputStream());
                       fileSize = ois.readInt();
                    
                files=new ArrayList<File>(fileSize); 
                                    for (int count=0;count < fileSize;count ++){
                        File ff=new File(ois.readUTF());
                        files.add(ff);
                }
                for (int count=0;count < fileSize;count ++){
                        byte[] buffer=(byte[]) ois.readObject();
                        String path=new String("C:\\Users\\User\\Desktop\\Clientcriteria3\\" + files.get(count));
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                                    }

                        byte[] buffer=(byte[]) ois.readObject();
                        String path=new String("C:\\Users\\User\\Desktop\\Clientstudentid\\Studentid.txt");
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                        buffer=(byte[]) ois.readObject();
                     path=new String("C:\\Users\\User\\Desktop\\Clientstudentid\\basic.txt");
                        fos=new FileOutputStream(path);
                        fos.write(buffer);
                        fos.flush();
                        resultshowing(thisstage);

             
             
                        } catch (IOException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                Group root=new Group();
               root.getChildren().addAll(enteras,questionsetter,scriptchecker,showresult);
               Scene scene=new Scene(root,1400,700);
                thisstage.setTitle("Decision");
                thisstage.setScene(scene);
                thisstage.show();
        
    }
    void resultshowing(Stage stage){
         
          Stage thisstage=stage;
                                
                                
                              path=new String("C:\\Users\\User\\Desktop\\Clientstudentid\\Studentid.txt");
                                          Label Studentidlabel=new Label("Student id");
            Studentidlabel.setLayoutX(possX);
            Studentidlabel.setLayoutY(possY);
            possX+=300;
            Label enterstudentid=new Label("Enter Student id:");
            enterstudentid.setLayoutX(540+12);
            enterstudentid.setLayoutY(470-3);
            TextField stdidtf=new TextField();
            stdidtf.setLayoutX(532);
            stdidtf.setLayoutY(488);
             Label enterquestionno=new Label("Enter Qustion no:");
            enterquestionno.setLayoutX(543+10);
            enterquestionno.setLayoutY(505+10);
            TextField quesnotf=new TextField();
            quesnotf.setLayoutX(532);
            quesnotf.setLayoutY(518+15);
            Button ok=new Button("Ok");
            ok.setLayoutX(651);
            ok.setLayoutY(518+43);
            Label Qusetionnolabel=new Label("Question no");
            Qusetionnolabel.setLayoutX(possX);
            Qusetionnolabel.setLayoutY(possY);
            possX+=300;
            
            Label obtain=new Label("Obtainedmark/Totalmark");
            obtain.setLayoutX(possX);
            System.out.println(possX);
            System.out.println(possY);
            obtain.setLayoutY(possY);
            possX+=300;
            Label Deductioncause=new Label("Cause of deduction");
            Deductioncause.setLayoutX(possX);
            Deductioncause.setLayoutY(possY);
            possX+=300;
            Label Deductionno=new Label("Deducted no");
            Deductionno.setLayoutX(possX);
            Deductionno.setLayoutY(possY);
            possY+=50;
            Group root123=new Group();
root123.getChildren().addAll(Studentidlabel,Qusetionnolabel,obtain,enterstudentid,stdidtf,ok,quesnotf,enterquestionno);
                        
                                    BufferedReader br;
        try {
            
            
            br = new BufferedReader(new FileReader(path));
               String a=br.readLine();
                                totalstudentnum=Integer.parseInt(a);
                                System.out.println(totalstudentnum);
                                Studentid=new String[totalstudentnum];
                                   for(int i=0;i<totalstudentnum;i++){
                    a=br.readLine();
                    a=br.readLine();
                    Studentid[i]=a;
                    System.out.println(Studentid[i]);
                                   }
                                   
                                    path=new String("C:\\Users\\User\\Desktop\\Clientstudentid\\basic.txt");
                       br=new BufferedReader(new FileReader(path));
                       a=br.readLine();
                                numberofquestions=Integer.parseInt(a);
                                criteriaarray=new int[numberofquestions];
                                markobtained=new int[totalstudentnum][numberofquestions];
                                Totalmarks=new int[totalstudentnum][numberofquestions];
                                for(int i=0;i<numberofquestions;i++){
                                path=new String("C:\\Users\\User\\Desktop\\Clientcriteria3\\"+(i+1)+".txt");
                                br=new BufferedReader(new FileReader(path));
                              a=br.readLine();
                       a=br.readLine();
                       criteriaarray[i]=Integer.parseInt(a);
                                }
                                
            
                    for(int j=0;j<totalstudentnum;j++){
                                                 //possX=50;
                                 //possY=50;
    /*                             Label stid=new Label(Studentid[j]);
                                 stid.setLayoutX(possX);
                                 stid.setLayoutY(possY);
                                 root123.getChildren().addAll(stid);*/
        
                        for(int i=0;i<numberofquestions;i++){
                            
                            
                                 path=new String("C:\\Users\\User\\Desktop\\Clientmarks2\\"+(i+1)+"."+(j+1)+".txt");
                                 br=new BufferedReader(new FileReader(path));
                                 a=br.readLine();
                                 int ab=Integer.parseInt(a);
                                 markobtained[j][i]=ab;
                                 a=br.readLine();
                                 ab=Integer.parseInt(a);
                                 Totalmarks[j][i]=ab;
                                 //String quesno=Integer.toString(i);
                               /*  Label Qno=new Label(quesno);
                                 possX+=300;
                                 Qno.setLayoutX(possX);
                                 Qno.setLayoutY(possY);
                                 possX+=300;
                                 Label marks=new Label(markobtained+"/"+totalmarks);
                                 marks.setLayoutX(possX);
                                 marks.setLayoutY(possY);
                                 Label[] deductionlabel=new Label[criteriaarray[i]];
                                 Label[] deductionmarklabel=new Label[criteriaarray[i]]; 
                                 System.out.println((i+1)+"."+(j+1));
                                 root123.getChildren().addAll(Qno,marks);*/
                                 //while ((a= br.readLine()) != null) {
                                   //  int hh=0;
                                   //  String causeofdeduction=a;
                                     // a=br.readLine();
                                    /* possX+=300;
                                     deductionlabel[hh]=new Label(causeofdeduction);
                                     deductionlabel[hh].setLayoutX(possX);
                                     deductionlabel[hh].setLayoutY(possY);
                                   
                                     possX+=300;
                                     deductionmarklabel[hh]=new Label(a);*/
                                     //String deductedmarks=a;
                                     /*deductionmarklabel[hh].setLayoutX(possX);
                                     deductionmarklabel[hh].setLayoutY(possY);
                                     System.out.println(causeofdeduction);
                                     System.out.println(deductedmarks);
                                     hh++;
                                     possX-=600;
                                     possY+=30;
                                     root123.getChildren().addAll(deductionlabel[hh],deductionmarklabel[hh]);*/
                                    //}       
                            }
                                            
               
               
                                                                 

                        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ab;
        for(int j=0;j<totalstudentnum;j++){
                                    possX=300;
                               
                               Label stid=new Label(Studentid[j]);
                                 stid.setLayoutX(possX);
                                 stid.setLayoutY(possY);
                                 root123.getChildren().addAll(stid);
               ab=possX;                  
            for(int i=0;i<numberofquestions;i++){
                int y=i+1;
                String quesno=Integer.toString(y);
                                possX=ab;
                                 Label Qno=new Label(quesno);
                                 possX+=320;
                                 Qno.setLayoutX(possX);
                                 Qno.setLayoutY(possY);
                                 possX+=310;
                                 Label marks=new Label(markobtained[j][i]+"/"+Totalmarks[j][i]);
                                 marks.setLayoutX(possX);
                                 marks.setLayoutY(possY);
                                 Label[] deductionlabel=new Label[criteriaarray[i]];
                                 Label[] deductionmarklabel=new Label[criteriaarray[i]]; 
                                 System.out.println((i+1)+"."+(j+1));
                                 root123.getChildren().addAll(Qno,marks);
                                 possY+=50;
            }
        }
                    ok.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                  String stid=stdidtf.getText();
                  String qno=quesnotf.getText();
                  System.out.println(qno);
                  int nno=Integer.parseInt(qno);
                  for(int i=0;i<totalstudentnum;i++){
                      System.out.println(Studentid[i]);
                  }
                  //int bb=totalstudentnum;
                  for(int i=0;i<totalstudentnum;i++){
                      if(Studentid[i].equals(stid)){
                          System.out.println((i+1));
                          for(int j=1;j<=numberofquestions;j++){
                              System.out.println(j);
                              if(nno==j){
                                  possX=300;
                                  possY=20;
                                  System.out.println("sudentid="+(i+1)+"quesno="+j);
                                  causeshow(thisstage,(i+1),j);
                              }
                          }
                          
                      }
                  }
              }
                
            });

        
        Scene scene34=new Scene(root123,1400,700);
               
                thisstage.setTitle("Result");
                thisstage.setScene(scene34);
                thisstage.show();

                 
                        }   
    void causeshow(Stage stage,int y,int z){
        BufferedReader br=null;
        
            Stage thisstage=stage;
            int studentno=y;
            int qno=z;
            String a;
            String b;
            int totalcriteriano=0;
            int obtainedm=0;
            int totalm=0;
            int ppx=160;
            int ppy=30;
            Label obtainedmark=new Label("Obtained Mark");
            obtainedmark.setLayoutX(250);
            obtainedmark.setLayoutY(30);
            Label totalmark=new Label("Total Mark");
            ppx+=300;
            totalmark.setLayoutX(ppx);
            totalmark.setLayoutY(ppy);
            Label deductioncausee=new Label("Deduction Cause");
            ppx+=300;
            deductioncausee.setLayoutX(ppx);
            deductioncausee.setLayoutY(ppy);
            Label deductionnoo=new Label("Deducted no");
            ppx+=300;
            deductionnoo.setLayoutX(ppx);
            deductionnoo.setLayoutY(ppy);

            
            Group rooty=new Group();
            rooty.getChildren().addAll(obtainedmark,totalmark,deductioncausee,deductionnoo);
            String Path=new String("C:\\Users\\User\\Desktop\\Clientcriteria3\\"+qno+".txt");
        try {
            br = new BufferedReader(new FileReader(path));
           a=br.readLine();
           a=br.readLine();
           totalcriteriano=Integer.parseInt(a);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Label deductioncause[]=new Label[totalcriteriano];
        Label deductedno[]=new Label[totalcriteriano];
        String deductionstring[]=new String[totalcriteriano];
        String deductionnostring[]=new String[totalcriteriano];
        System.out.println("stdno="+studentno+"qno="+qno);
        path=new String("C:\\Users\\User\\Desktop\\Clientmarks\\"+qno+"."+studentno+".txt");
        try {
            br = new BufferedReader(new FileReader(path));
            a=br.readLine();
            obtainedm=Integer.parseInt(a);
            a=br.readLine();
            totalm=Integer.parseInt(a);
            hh=0;
            while ((a= br.readLine()) != null) {
                
                deductionstring[hh]=a;
                a=br.readLine();
                deductionnostring[hh]=a;
                hh++;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ppx=280;
        ppy+=30;
        String rr=Integer.toString(obtainedm);
        Label obtainednoshow=new Label(rr);
        obtainednoshow.setLayoutX(ppx);
        obtainednoshow.setLayoutY(ppy);
        rr=Integer.toString(totalm);
        ppx+=210;
        int yyy=ppx;
        Label totalmarkshow=new Label(rr);
        totalmarkshow.setLayoutX(ppx);
        totalmarkshow.setLayoutY(ppy);
        for(int i=0;i<totalcriteriano;i++){
            deductioncause[i]=new Label(" ");
            deductedno[i]=new Label(" ");
        }
        System.out.println("hh="+hh);
        for(int i=0;i<hh;i++){
            deductioncause[i]=new Label(deductionstring[i]);
            deductedno[i]=new Label(deductionnostring[i]);
        }
        for(int i=0;i<totalcriteriano;i++){
            ppx+=280;
            deductioncause[i].setLayoutX(ppx);
            deductioncause[i].setLayoutY(ppy);
            ppx+=300;
            deductedno[i].setLayoutX(ppx);
            deductedno[i].setLayoutY(ppy);
            ppy+=30;
            ppx=yyy;
            rooty.getChildren().addAll(deductioncause[i],deductedno[i]);
            
        }
        Button back=new Button("Back");
        back.setLayoutX(698);
        back.setLayoutY(500);
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                resultshowing(thisstage);
            }
            
        });
         rooty.getChildren().addAll(obtainednoshow,totalmarkshow,back);
        Scene scene334=new Scene(rooty,1400,700);
               
                thisstage.setTitle("Result");
                thisstage.setScene(scene334);
                thisstage.show();
         
    }
    void questionsetter(Stage stage){
        Stage thisstage=stage;
                        Label pastequestion=new Label("Paste the question source file: ");
                       pastequestion.setLayoutX(posx-100);
                       pastequestion.setLayoutY(posy-50);
                       TextField questions1=new TextField();
                       questions1.setLayoutX(posx);
                       questions1.setLayoutY(posy);
                       
                       
                       Button start=new Button("Start");
                       start.setLayoutX(posx+100);
                       start.setLayoutY(posy+50);
                       start.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String path=questions1.getText();
                            //questionfilepath=path;
                            try { 
                                questionsetting(thisstage,path);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                           
                       });
                       Group root=new Group();
                       root.getChildren().addAll(pastequestion,questions1,start);
                       Scene scene=new Scene(root,1400,700);
                       thisstage.setTitle("Questions");
                       thisstage.setScene(scene);
                       thisstage.show();
    }
    void answerpaster(Stage stage,String p){
        Stage thisstage=stage;
        //int total=questotal;
        //Label pastequestion=new Label("Paste the question source file: ");
          //             pastequestion.setLayoutX(posx-100);
            //           pastequestion.setLayoutY(posy-50);
              //         TextField questions1=new TextField();
                //       questions1.setLayoutX(posx);
                  //     questions1.setLayoutY(posy);
                    //   Button start=new Button("Start");
                     //  start.setLayoutX(posx+100);
                      // start.setLayoutY(posy+50);
                       //start.setOnAction(new EventHandler<ActionEvent>() {
                        //@Override
                        //public void handle(ActionEvent event) {
                            path=p;
                            questionfilepath=path;
                            try { 
                                answersetting(thisstage,path);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                       // }
                       //});
                       //Group root=new Group();
                       //root.getChildren().addAll(pastequestion,questions1,start);
                       //Scene scene=new Scene(root,1400,700);
                       //thisstage.setTitle("Criteria setter");
                       //thisstage.setScene(scene);
                       //thisstage.show();
    }
    void answersetting(Stage stage,String path) throws IOException{
        Stage thisstage=stage;
        String pathnew=path;
        pathnew=pathnew+"\\basic.txt";
        System.out.println(pathnew);
        FileReader fr=null;
        BufferedReader br=null;
        
        try {
            br=new BufferedReader(new FileReader(pathnew));
            String scenetotal;
            scenetotal = br.readLine();
            scenetotalint=Integer.parseInt(scenetotal);
            //scenes=new Scene[scenetotalint];
            scriptchecker(stage,path,scenetotalint);
            //br.close();
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    void scriptchecker(Stage stage,String pathh,int questotal){
        Stage thisstage=stage;
        totalques=questotal;
        //Label pastequestion=new Label("Paste the source of answer file: ");
          //             pastequestion.setLayoutX(posx-100);
            //           pastequestion.setLayoutY(posy-50);
              //         TextField answers1=new TextField();
                //       answers1.setLayoutX(posx);
                  //     answers1.setLayoutY(posy);
                    //   Button start=new Button("Start");
                     //  start.setLayoutX(posx+100);
                    //   start.setLayoutY(posy+50);
                     //  start.setOnAction(new EventHandler<ActionEvent>() {
                       // @Override
                       // public void handle(ActionEvent event) {
                           // path=answers1.getText();
                                answercheck(thisstage,abcd,totalques);
                       // }
                       //});
                       //Group root=new Group();
                      // root.getChildren().addAll(pastequestion,answers1,start);
                      // Scene scene=new Scene(root,1400,700);
                      // thisstage.setTitle("Criteria setter");
                      // thisstage.setScene(scene);
                       //thisstage.show();
    }
    void answercheck(Stage stage,String p,int questotal){
        Stage thisstage=stage;
        int totalques=questotal;
        String path=p;
        int quesno=0;
        int px=570;
        int py=200;
        Label surity=new Label("Answers of the question you want to check: ");
        surity.setLayoutX(px);
        surity.setLayoutY(py);
     Label[] answers=new Label[totalques];
     Group root10=new Group();
            px+=80;
     for(int i=0;i<totalques;i++){
         py=py+30;
         answers[i]=new Label("Question "+(i+1));
         answers[i].setLayoutX(px);
         answers[i].setLayoutY(py);
         root10.getChildren().addAll(answers[i]);
         
     }
      TextField tf=new TextField();
      tf.setLayoutX(px-40);
      tf.setLayoutY(py+30);
      Button ok=new Button ("Ok");
      ok.setLayoutX(px);
      ok.setLayoutY(py+60);
     ok.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               String txt=tf.getText();
               y=Integer.parseInt(txt);
                try {
                    answerstart(thisstage,path,y);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
         
     });
     root10.getChildren().addAll(surity,tf,ok);
     Scene scene=new Scene(root10,1400,700);
     thisstage.setScene(scene);
     thisstage.show();
     
    }
    void answerstart(Stage stage,String path,int folderno) throws IOException{
        Stage thisstage=stage;
        y=folderno;
        String pathnew=path+"\\answer"+y;
        
        String pathneww=pathnew+"\\basic.txt";
        System.out.println(pathnew);
        FileReader fr=null;
        BufferedReader br=null;
        
        try {
            br=new BufferedReader(new FileReader(pathneww));
            String scenetotal;
            scenetotal = br.readLine();
            totalscripts=Integer.parseInt(scenetotal);
            //scenes=new Scene[scenetotalint];
            answercontinue(thisstage,pathnew,totalscripts,presentscenenoans,photonumans,y);
            //br.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
        
    }
    void questionsetting(Stage stage,String path ) throws IOException{
        Stage thisstage=stage;
        String pathnew=path;
        pathnew=pathnew+"\\basic.txt";
        System.out.println(pathnew);
        FileReader fr=null;
        BufferedReader br=null;
        
        try {
            br=new BufferedReader(new FileReader(pathnew));
            String scenetotal;
            scenetotal = br.readLine();
            scenetotalint=Integer.parseInt(scenetotal);
            //scenes=new Scene[scenetotalint];
            scenebuilder(stage,path,scenetotalint,presentsceneno,photonum);
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
        void answercontinue(Stage stage,String pathh,int totalscript,int scnno,int phno,int folderno) throws IOException{
        Stage thisstage=stage;
        int no=folderno;
        
        int nocriteria;
        presentscenenoans=scnno;
        photonumans=phno;
        totalscripts=totalscript;
        if(presentscenenoans==-1){
        sceneans=new Scene[totalscripts];
         presentscenenoans=0;
        } 
        String imageurl=pathh;
         imageurl=imageurl+"\\photo-"+photonumans+".jpg";
                                File f1=new File(imageurl);
                                Image image = new Image(f1.toURI().toString());
                                ImageView imageview = new ImageView(image);
                                imageview.setLayoutX(10);
                                imageview.setLayoutY(100);
                                imageview.setFitHeight(250);
                                imageview.setFitWidth(1000);
                                Button next=new Button("Next");
                                next.setLayoutX(1000);
                                next.setLayoutY(500);
                                Button previous=new Button("Previous");
                                previous.setLayoutX(850);
                                previous.setLayoutY(500);
                                Button mainpage=new Button("Main Page");
                                       mainpage.setLayoutX(1030);
                                mainpage.setLayoutY(30);
                                mainpage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                firstpage(thisstage);
            }});
                         
                                
        String pathnew="C:\\Users\\User\\Desktop\\ClientCriteria2\\"+no+".txt";
        System.out.println(pathnew);
        FileReader fr=null;
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(pathnew));
            String a=br.readLine();
            totalmarks=Integer.parseInt(a);
            marksobtained=totalmarks;
            a=br.readLine();
            
            criteriano=Integer.parseInt(a);
            deductions=new int[criteriano];
            criterias=new String[criteriano];
            for(int i=0;i<criteriano;i++){
                a=br.readLine();
                deductions[i]=Integer.parseInt(a);
               
            }
            for(int i=0;i<criteriano;i++){
                criterias[i]=br.readLine();
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        array=new int[criteriano];        
        

        
        
                                next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                               photonumans++;
                               presentscenenoans++;
                               System.out.println("photonumans="+photonumans+"\npresentscenenoans="+presentscenenoans+"\n");
                               
                try {
                    answercontinue(stage,pathh,totalscripts,presentscenenoans,photonumans,no);
                } catch (IOException ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                                });
                                previous.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                               photonumans--;
                               presentscenenoans--;
                                System.out.println("photonumans="+photonumans+"\npresentscenenoans="+presentscenenoans+"\n");
                try {
                    answercontinue(stage,pathh,totalscripts,presentscenenoans,photonumans,no);
                } catch (IOException ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                                });
                                Button back=new Button("Back to question choice");
                                back.setLayoutX(650);
                                back.setLayoutY(500);
                                Button submit=new Button("Submit");
                                submit.setLayoutX(1150);
                                submit.setLayoutY(500);
                                Group root5=new Group();
        int pox=400;
        int poy=450;
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                photonumans=1;
                presentscenenoans=-1;
                answercheck(thisstage,path,totalques);
            }
            
        });
        Button sendtoserver=new Button("Send to Server");
        sendtoserver.setLayoutX(1147);
        sendtoserver.setLayoutY(30);
        sendtoserver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {  
                    Socket s=new Socket("127.0.0.1",3212);
                                             ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                      
             String msg="take_answer_mark";
             System.out.println(msg);
             Data obj=new Data();
             obj.msg=msg;
             oos.writeObject(obj);
             oos.flush();
                                  File myFile = new File("C:\\Users\\User\\Desktop\\Clientmarks\\");
            File[] Files = myFile.listFiles();
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
     
            //dos.close();
            }  

                } catch (IOException ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        CheckBox[] criteriacheckbox=new CheckBox[criteriano];
        for(int i=0;i<criteriano;i++){
            criteriacheckbox[i]=new CheckBox(criterias[i]);
            criteriacheckbox[i].setLayoutX(pox);
            criteriacheckbox[i].setLayoutY(poy);
            poy+=30;
           
            root5.getChildren().addAll(criteriacheckbox[i]);
        }
        Label markobtained=new Label("Mark obtained: "+marksobtained+"/"+totalmarks);
        //markobtained.setText("Mark obtained: "+marksobtained+"/"+totalmarks);
        for(int i=0;i<criteriano;i++){
            final CheckBox cb=criteriacheckbox[i];
            int c=deductions[i];
            int need=i;
            criteriacheckbox[i].setOnAction(new EventHandler<ActionEvent>(){
                

                @Override
                public void handle(ActionEvent event) {
                     if(cb.isSelected()){
                          marksobtained-=c;
                          array[need]=1;
                     }              
                     else {marksobtained+=c;array[need]=0;}    
                     markobtained.setText("Mark obtained: "+marksobtained+"/"+totalmarks);
                }
            });
        }
        
                submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String FILENAME="C:\\Users\\User\\Desktop\\Clientmarks\\"+no+"."+photonumans+".txt";
                                                System.out.println(FILENAME);
                                                BufferedWriter bw = null;
                                                FileWriter fw = null;

		try {

		//	String content = "This is the content to write into file\n\n";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
                        String mark=Integer.toString(marksobtained);
                        bw.write(mark);
                        bw.newLine();
                        mark=Integer.toString(totalmarks);
                        bw.write(mark);
                        bw.newLine();
    
                        for(int i=0;i<criteriano;i++){
                            if(array[i]==1){
                                String m=Integer.toString(deductions[i]);
                                bw.write(criterias[i]);
                                bw.newLine();
                                bw.write(m);
                                bw.newLine();
                            }
                        }    
//			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

                                                
                                                
                                            } finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
                
                
            }
                                            
                        
        });

        markobtained.setLayoutX(360);
        markobtained.setLayoutY(410);
        root5.getChildren().addAll(markobtained,back,submit);
        
                                
                                sceneans[presentscenenoans]=new Scene(root5,1400,700);
                                if((presentscenenoans+1)==totalscripts){
                                    root5.getChildren().addAll(imageview,previous,sendtoserver,mainpage);
                                     System.out.println("PREVIOUS\nphotonumans="+photonumans+"\npresentscenenoans="+presentscenenoans+"\n");
                                }
                                else if(presentscenenoans==0){
                                    root5.getChildren().addAll(imageview,next,sendtoserver,mainpage);
                                     System.out.println("NEXT\nphotonumans="+photonumans+"\npresentscenenoans="+presentscenenoans+"\n");
                                }
                                else{
                                root5.getChildren().addAll(imageview,next,previous,sendtoserver,mainpage);
                                 System.out.println("ALL\nphotonumans="+photonumans+"\npresentscenenoans="+presentscenenoans+"\n");
                                }
                                thisstage.setTitle("Criteria setter");
                                thisstage.setScene(sceneans[presentscenenoans]);
                                thisstage.show();


    }

    
    void scenebuilder(Stage stage,String path,int scenetotal,int scnno,int phno){
        Stage thisstage=stage;
        presentsceneno=scnno;
        photonum=phno;
        if(presentsceneno==-1){
        scenes=new Scene[scenetotalint];
         presentsceneno=0;
        }
        
         String imageurl=path;
         imageurl=imageurl+"\\photo-"+photonum+".jpg";
                                File f1=new File(imageurl);
                                Image image = new Image(f1.toURI().toString());
                                ImageView imageview = new ImageView(image);
                                imageview.setLayoutX(10);
                                imageview.setLayoutY(100);
                                imageview.setFitHeight(250);
                                imageview.setFitWidth(1000);
                                Label criterialabel=new Label("Set no of criterias for this ques: ");
                                criterialabel.setLayoutX(1040);
                                criterialabel.setLayoutY(230);
                                TextField criteriano=new TextField();
                                criteriano.setLayoutX(1040);
                                criteriano.setLayoutY(250);
                                Button ok=new Button("OK");
                                ok.setLayoutX(1160);
                                ok.setLayoutY(290);
                                Button next=new Button("Next");
                                next.setLayoutX(1000);
                                next.setLayoutY(500);
                                Button previous=new Button("Previous");
                                previous.setLayoutX(850);
                                previous.setLayoutY(500);
                                Button mainpage=new Button("Main Page");
                                mainpage.setLayoutX(1030);
                                mainpage.setLayoutY(30);
                                mainpage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                firstpage(thisstage);
            }
                                    
                                });
                                ok.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        int posX=380;
                                        int posY=450;
                                        Group root9=new Group();
                                        Button mainpage=new Button("Main Page");
                                                                        mainpage.setLayoutX(1030);
                                mainpage.setLayoutY(30);
                                mainpage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                firstpage(thisstage);
            }
                                    
                                });
                                Button senttoserver=new Button("Send to Server");
                                                                        senttoserver.setLayoutX(1147);
                               senttoserver.setLayoutY(30);
                               senttoserver.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override
                                            public void handle(ActionEvent event) {
                                                try {
                                                    Socket s=new Socket("127.0.0.1",3212);
                                                             ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                      
             String msg="receive_criteria";
             System.out.println(msg);
             Data obj=new Data();
             obj.msg=msg;
             oos.writeObject(obj);
             oos.flush();
                                  File myFile = new File("C:\\Users\\User\\Desktop\\clientcriteria\\");
            File[] Files = myFile.listFiles();
            oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeInt(Files.length);
            
             
            for (int count=0;count<Files.length;count ++){
                  oos.writeUTF(Files[count].getName());
                   
            }
                        System.out.println(Files.length);
            
            for (int count=0;count<Files.length;count ++){
             
                     String path=new String("C:\\Users\\User\\Desktop\\clientcriteria\\"+(count+1)+".txt");
                    //File f1=new File(path);
                    FileInputStream fis=new FileInputStream(path);
                    byte[] buffer=new byte[fis.available()];
                    fis.read(buffer);
                    
                    
                    oos.writeObject(buffer);
                    oos.flush();
     
            //dos.close();
            }  
    
                                                    
                                                    
                                                } catch (IOException ex) {
                                                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                            }
                                   
                               });
                                

                                        criterianos=criteriano.getText();
                                        int criteriaint=Integer.parseInt(criterianos);
                                        TextField[] textfieldcriteria=new TextField[criteriaint];
                                        Label criterias=new Label("Cause of deduction: ");
                                        criterias.setLayoutX(posX);
                                        criterias.setLayoutY(posY-40);
                                        
                                        Label deduction=new Label("Deducted marks: ");
                                        deduction.setLayoutX(posX+200);
                                        deduction.setLayoutY(posY-40);
                                        TextField[] textfieldint=new TextField[criteriaint];
                                        
                                        for(int i=0;i<criteriaint;i++){
                                            textfieldint[i]=new TextField();
                                            textfieldint[i].setLayoutX(posX+200);
                                            textfieldint[i].setLayoutY(posY);
                                            textfieldcriteria[i]=new TextField();
                                            textfieldcriteria[i].setLayoutX(posX);
                                            textfieldcriteria[i].setLayoutY(posY);
                                            root9.getChildren().addAll(textfieldcriteria[i],textfieldint[i]);
                                            posY=posY+30;
                                        }
                                        
                                        Button submit=new Button("Submit");
                                        submit.setLayoutX(1150);
                                        submit.setLayoutY(500);
                                            
                                        Label totalmark=new Label("Total marks: ");
                                        TextField totalmarktf=new TextField();
                                        totalmark.setLayoutX(posX);
                                        totalmark.setLayoutY(posY+20);
                                        totalmarktf.setLayoutX(posX+100);
                                        totalmarktf.setLayoutY(posY+20);
                                        root9.getChildren().addAll(criterias,deduction,submit,totalmark,totalmarktf,mainpage,senttoserver);
                                        submit.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override
                                            public void handle(ActionEvent event) {
                                                String FILENAME="C:\\Users\\User\\Desktop\\clientcriteria\\"+photonum+".txt";
                                                System.out.println(FILENAME);
                                                BufferedWriter bw = null;
                                                FileWriter fw = null;

		try {

		//	String content = "This is the content to write into file\n\n";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
                        bw.write(totalmarktf.getText());
			bw.newLine();
                        
                        bw.write(criterianos);
                        bw.newLine();
                        int m=Integer.parseInt(criterianos);
                        for(int i=0;i<m;i++){
                        bw.write(textfieldint[i].getText());
                        bw.newLine();
                        }
                        for(int i=0;i<m;i++){
                        bw.write(textfieldcriteria[i].getText());
                        bw.newLine();
                        }
//			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

                                                
                                                
                                            } finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
                
                                            }
                                            //submissionflag=1;
                                            
                                        });
                                        Label submissioncomplete=new Label("Submission complete");
                                        submissioncomplete.setLayoutX(700);
                                        submissioncomplete.setLayoutY(30);        
                                        if((presentsceneno+1)==scenetotalint){
                                    root9.getChildren().addAll(imageview,previous);
                                }
                                else if(presentsceneno==0){
                                    root9.getChildren().addAll(imageview,next);
                                }
                                else{
                                    root9.getChildren().addAll(imageview,next,previous);
                                }
                                        scenes[presentsceneno]=new Scene(root9,1400,700);
                                        thisstage.setScene(scenes[presentsceneno]);
                                        thisstage.show();
                                    }
                                    
                                });
                                next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                               photonum++;
                               presentsceneno++;
                               submissionflag=0;
                               scenebuilder(stage,path,scenetotalint,presentsceneno,photonum);
            }
                                });
                                previous.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                               photonum--;
                               presentsceneno--;
                               submissionflag=0;
                               scenebuilder(stage,path,scenetotalint,presentsceneno,photonum);
            }
            
                
                                    
                                });
                                
                                Group root5=new Group();
                                scenes[presentsceneno]=new Scene(root5,1400,700);
                                if((presentsceneno+1)==scenetotalint){
                                    root5.getChildren().addAll(imageview,criterialabel,criteriano,ok,previous,mainpage);
                                }
                                else if(presentsceneno==0){
                                    root5.getChildren().addAll(imageview,criterialabel,criteriano,ok,next,mainpage);
                                }
                                else{
                                root5.getChildren().addAll(imageview,criterialabel,criteriano,ok,next,previous,mainpage);
                                }
                                thisstage.setTitle("Criteria setter");
                                thisstage.setScene(scenes[presentsceneno]);
                                thisstage.show();
    
        
    }
    
}
