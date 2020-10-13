package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.table.*;
import java.text.*;

class SwingTest extends JFrame
{
    SwingTest(ArrayList<Patient> bandokilist)
    {
        JTextField jTextField1;

        JButton jButton1;

        JCheckBox radioButton1;
        JCheckBox radioButton2;
        JCheckBox radioButton3;
        JCheckBox radioButton4;

        JTable jTable1 = new JTable();
        JTable jTable2 = new JTable();

        JPanel jPanel2=new JPanel();
        JPanel jPanel1=new JPanel();
        JScrollPane jPane2= new JScrollPane(jTable2);
        JScrollPane jPane1= new JScrollPane(jTable1);

        // Column Names
        final String[] columnNames = { "Name", "Age", "Tower", "Reporting Date", "Recovery Date" };
        final String[] columnNames1 = { "Tower", "Active Cases", "Recovered" };
        DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model1 = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        model1.setColumnIdentifiers(columnNames1);

        // set the model to the table
        jTable1.setModel(model);
        jTable2.setModel(model1);
        JLabel jLabel1=new JLabel();
        jLabel1.setText("Date(YYYY/MM/DD): ");
        JLabel jLabel2=new JLabel();
        jLabel2.setText("Select the Tower: ");
        JLabel jLabel3=new JLabel();
        jLabel3.setText("Active Cases: 0");
        JLabel jLabel4=new JLabel();
        jLabel4.setText("Recovered Cases: 0");

        jTextField1=new JTextField(10);

        radioButton1= new JCheckBox("A");
        radioButton2=new JCheckBox("B");
        radioButton3= new JCheckBox("C");
        radioButton4= new JCheckBox("D");

        jButton1=new JButton("Let's Go!");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date start=new Date("2020/03/31");
                Date end=new Date("2020/09/01");
                // Data to be displayed in the JTable
                String[][] data = new String[20][5];
                String[][] data1 = new String[4][3];
                SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
                sdfrmt.setLenient(false);
                boolean f;
                try {
                    sdfrmt.parse(jTextField1.getText());
                    f = true;
                } catch (ParseException err) {
                    f = false;
                }
                if (!f) {
                    JOptionPane.showMessageDialog(null, "Wrong Date Format.");
                } else {
                    Date date = new Date(jTextField1.getText());
                    if(date.before(end) && date.after(start)){
                        boolean a = radioButton1.isSelected();
                        boolean b = radioButton2.isSelected();
                        boolean c = radioButton3.isSelected();
                        boolean d = radioButton4.isSelected();
                        int TotalCases = 0, RecoveredCases = 0;
                        int count = 0;
                        int totala=0,recovereda=0;
                        int totalb=0,recoveredb=0;
                        int totalc=0,recoveredc=0;
                        int totald=0,recoveredd=0;
                        for (Patient B : bandokilist) {
                            boolean flag = false;
                            if (B.tower == 'A' && a) {
                                flag = true;
                                if (B.ReportingDate.before(date) || B.ReportingDate.equals(date)) {
                                    totala++;
                                }
                                if (B.RecoveryDate.before(date)) {
                                    recovereda++;
                                }
                            } else if (B.tower == 'B' && b) {
                                flag = true;
                                if (B.ReportingDate.before(date) || B.ReportingDate.equals(date)) {
                                    totalb++;
                                }
                                if (B.RecoveryDate.before(date)) {
                                    recoveredb++;
                                }
                            } else if (B.tower == 'C' && c) {
                                flag = true;
                                if (B.ReportingDate.before(date) || B.ReportingDate.equals(date)) {
                                    totalc++;
                                }
                                if (B.RecoveryDate.before(date)) {
                                    recoveredc++;
                                }
                            } else if (B.tower == 'D' && d) {
                                flag = true;
                                if (B.ReportingDate.before(date) || B.ReportingDate.equals(date)) {
                                    totald++;
                                }
                                if (B.RecoveryDate.before(date)) {
                                    recoveredd++;
                                }
                            }
                            if (flag) {
                                if (B.RecoveryDate.before(date)) {
                                    RecoveredCases++;
                                }
                                if (B.ReportingDate.before(date) || B.ReportingDate.equals(date)) {
                                    TotalCases++;
                                    data[count][0] = B.name;
                                    data[count][1] = String.valueOf(B.age);
                                    data[count][2] = String.valueOf(B.tower);
                                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                                    data[count][3] = df.format(B.ReportingDate);
                                    data[count][4] = df.format(B.RecoveryDate);
                                    count++;
                                }
                            }
                        }
                        int counter=0;
                        if(a){
                            data1[counter]= new String[]{"A", String.valueOf(totala-recovereda), String.valueOf(recovereda)};
                            counter++;
                        }
                        if(b){
                            data1[counter]= new String[]{"B", String.valueOf(totalb-recoveredb), String.valueOf(recoveredb)};
                            counter++;
                        }
                        if(c){
                            data1[counter]= new String[]{"C", String.valueOf(totalc-recoveredc), String.valueOf(recoveredc)};
                            counter++;
                        }
                        if(d){
                            data1[counter]= new String[]{"D", String.valueOf(totald-recoveredd), String.valueOf(recoveredd)};
                            counter++;
                        }
                        data = Arrays.copyOfRange(data, 0, count);
                        data1 = Arrays.copyOfRange(data1, 0, counter);
//                        columnNames = new String[]{"Name", "Age", "Tower", "Reporting Date", "Recovery Date"};
                        model.setDataVector(data, columnNames);
                        model1.setDataVector(data1, columnNames1);
                        jTable1.setModel(model);
                        jTable2.setModel(model1);
                        int ActiveCases = TotalCases - RecoveredCases;
                        jLabel3.setText("Active Cases: " + ActiveCases);
                        jLabel4.setText("Recovered Cases: " + RecoveredCases);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Date should lie between April 2020 and August 2020.");
                    }
                }
            }
        });

        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
        add(radioButton4);
        add(jButton1);
        add(jLabel3);
        add(jLabel4);
        jPanel2.add(jPane2);
        jPanel1.add(jPane1);
        jPanel2.setPreferredSize(new Dimension(500, 100));
        jPanel1.setPreferredSize(new Dimension(500, 400));

        add(jPanel2);
        add(jPanel1);
        setTitle("Alexa");

        //Flow, Border, Grid
        //Default layout is the Card Layout
        FlowLayout flowLayout=new FlowLayout();

        setLayout(flowLayout);
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class Patient{
    String name;
    int age;
    char tower;
    Date ReportingDate;
    Date RecoveryDate;
    Patient(String name, int age, char tower, String ReportingD)
    {
        this.name=name;
        this.age=age;
        this.tower=tower;
        this.ReportingDate = new Date(ReportingD);
        this.RecoveryDate = new Date(this.ReportingDate.getTime() + (1000 * 60 * 60 * 24 * 21));
    }
}

public class Main {
    static void initialise(ArrayList<Patient> bandokilist)
    {
        bandokilist.add(new Patient("Flora",6,'A',"2020/04/01"));
        bandokilist.add(new Patient("Denys",24,'B',"2020/04/01"));
        bandokilist.add(new Patient("Jim",42,'C',"2020/05/18"));
        bandokilist.add(new Patient("Hazel",87,'D',"2020/06/23"));
        bandokilist.add(new Patient("Caery",72,'A',"2020/06/01"));
        bandokilist.add(new Patient("David",7,'B',"2020/06/14"));
        bandokilist.add(new Patient("Kevim",37,'D',"2020/06/05"));
        bandokilist.add(new Patient("Tom",67,'D',"2020/06/20"));
        bandokilist.add(new Patient("Bob",74,'A',"2020/07/04"));
        bandokilist.add(new Patient("Rachel",48,'C',"2020/07/24"));
        bandokilist.add(new Patient("Thomas",21,'C',"2020/06/11"));
        bandokilist.add(new Patient("Mary",17,'D',"2020/06/21"));
        bandokilist.add(new Patient("Smith",89,'A',"2020/08/07"));
        bandokilist.add(new Patient("Pearson",47,'B',"2020/06/04"));
        bandokilist.add(new Patient("Anderson",62,'B',"2020/07/27"));
        bandokilist.add(new Patient("Johnson",10,'D',"2020/08/01"));
        bandokilist.add(new Patient("Robertz",50,'A',"2020/08/09"));
        bandokilist.add(new Patient("Julie",86,'B',"2020/05/02"));
        bandokilist.add(new Patient("Edith",42,'D',"2020/06/07"));
        bandokilist.add(new Patient("John",95,'D',"2020/06/01"));
    }
    public static void main(String[] args) {
	// write your code here
        ArrayList<Patient> bandokilist=new ArrayList<>();
        initialise(bandokilist);
        SwingTest obj=new SwingTest(bandokilist);
    }
}

