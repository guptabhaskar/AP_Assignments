package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class patient{
    private final String name;
    private final int age;
    private final int oxygen_level;
    private final float temperature;
    private final int id;
    private boolean admission_status;
    private String institute;
    private int recovery_days;

    patient(String name, float temperature, int oxygen_level, int age, int patient_id_count){
        this.name=name;
        this.temperature=temperature;
        this.oxygen_level=oxygen_level;
        this.age=age;
        this.id=patient_id_count;
        this.admission_status=false;
    }

    public String toString() {
        String patient_details;
        patient_details="Patient Name: "+this.getName()+"\n";
        patient_details+="Oxygen Level: "+this.getOxygen_level()+'\n';
        patient_details+="Temperature: "+this.getTemperature()+'\n';
        if(this.isAdmission_status()){
            patient_details+="Admission Status: Admitted\n";
            patient_details+="Admitting Institute: "+this.getInstitute()+'\n';
        }
        else{
            patient_details+="Admission Status: Not Admitted\n";
        }
        return patient_details;
    }

    public String getName() {
        return name;
    }

    public int getOxygen_level() {
        return oxygen_level;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmission_status() {
        return admission_status;
    }

    public String getInstitute() {
        return institute;
    }

    public void setAdmission_status(boolean admission_status) {
        this.admission_status = admission_status;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getRecovery_days() {
        return recovery_days;
    }

    public void setRecovery_days(int recovery_days) {
        this.recovery_days = recovery_days;
    }

    public int getAge() {
        return age;
    }
}

class healthcare_institute{
    private final String name;
    private final int oxygen_level;
    private final float temperature_criteria;
    private int available_beds;
    private String status;
    private ArrayList<patient> patients=new ArrayList<>();

    healthcare_institute(String name, float temperature_criteria, int oxygen_level, int available_beds){
        this.name=name;
        this.temperature_criteria=temperature_criteria;
        this.oxygen_level=oxygen_level;
        this.available_beds=available_beds;
        if(this.available_beds>0) {
            this.status = "OPEN";
        }
        else{
            this.status = "CLOSED";
        }
    }

    public String toString() {
        String institute_details;
        institute_details=this.getName()+"\n";
        institute_details+="Temperature should be <= "+this.getTemperature_criteria()+'\n';
        institute_details+="Oxygen levels should be >= "+this.getOxygen_level()+'\n';
        institute_details+="Number of Available beds – "+this.getAvailable_beds()+'\n';
        institute_details+="Admission Status – "+this.getStatus()+'\n';
        return institute_details;
    }

    public String getName() {
        return name;
    }

    public int getOxygen_level() {
        return oxygen_level;
    }

    public float getTemperature_criteria() {
        return temperature_criteria;
    }

    public int getAvailable_beds() {
        return available_beds;
    }

    public String getStatus() {
        return status;
    }

    public void setAvailable_beds(int available_beds) {
        this.available_beds = available_beds;
        if(this.available_beds==0){
            this.setStatus("CLOSED");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<patient> patients) {
        this.patients = patients;
    }
}

class manager{
    private int patient_id_count;
    private ArrayList<patient> patient_list;
    private ArrayList<healthcare_institute> institute_list;

    manager() {
        this.patient_list = new ArrayList<>();
        this.institute_list = new ArrayList<>();
        this.patient_id_count=1;
    }

    public int getPatient_id_count() {
        return patient_id_count;
    }

    public void setPatient_id_count(int patient_id_count) {
        this.patient_id_count = patient_id_count;
    }

    public void addPatient(String name, float temperature, int oxygen_level, int age) {
        patient_list.add(new patient(name, temperature, oxygen_level, age, getPatient_id_count()));
        setPatient_id_count(getPatient_id_count()+1);
    }

    public void addHealthcareInstitute(healthcare_institute h) {
        institute_list.add(h);
    }

    public void removeAdmitted() {
        System.out.println("Account ID removed of admitted patients");
        ArrayList<patient> temp_list=new ArrayList<>();
        for(patient p: patient_list){
            if(p.isAdmission_status()){
                temp_list.add(p);
                System.out.println(p.getId());
            }
        }
        for(patient p: temp_list){
            patient_list.remove(p);
        }
    }

    public void removeClosed() {
        System.out.println("Accounts removed of Institute whose admission is closed");
        ArrayList<healthcare_institute> temp_list=new ArrayList<>();
        for(healthcare_institute h: institute_list){
            if(h.getStatus().equals("CLOSED")){
                temp_list.add(h);
                System.out.println(h.getName());
            }
        }
        for(healthcare_institute h:temp_list){
            institute_list.remove(h);
        }
    }

    public void countNotAdmitted() {
        int count=0;
        for(patient p: patient_list){
            if(!p.isAdmission_status()){
                count+=1;
            }
        }
        System.out.println(count+" Patients");
    }

    public void displayInstitute(String s) {
        for (healthcare_institute h : institute_list) {
            if (h.getName().equals(s)) {
                System.out.print(h);
                return;
            }
        }
        System.out.println("Not Found");
    }

    public void displayDetails(int id) {
        for (patient p : patient_list) {
            if (p.getId() == id) {
                System.out.print(p);
                break;
            }
        }
    }

    public void diplay_all_patients() {
        for(patient p: patient_list){
            System.out.println(p.getId()+" "+p.getName());
        }
    }

    public void display_institute_recovery(String s) {
        for (healthcare_institute h : institute_list) {
            if (h.getName().equals(s)) {
                for(patient p: h.getPatients()){
                    System.out.println(p.getName()+", recovery time is "+p.getRecovery_days()+" days");
                }
                break;
            }
        }
    }

    public void countOpenInstitute() {
        int count=0;
        for(healthcare_institute h: institute_list){
            if(h.getStatus().equals("OPEN")){
                count+=1;
            }
        }
        System.out.println(count+" institutes are admitting patients currently");
    }

    public void admitPatients(Scanner scan, healthcare_institute h) {
        for(patient p: patient_list){
            if(!p.isAdmission_status() && h.getStatus().equals("OPEN")){
                if(p.getOxygen_level()>=h.getOxygen_level()){
                    p.setAdmission_status(true);
                    p.setInstitute(h.getName());
                    System.out.print("Recovery days for admitted patient ID "+p.getId()+ " – ");
                    p.setRecovery_days(scan.nextInt());
                    ArrayList<patient> temp=h.getPatients();
                    temp.add(p);
                    h.setPatients(temp);
                    int available_beds=h.getAvailable_beds();
                    h.setAvailable_beds(available_beds-1);
                }
            }
        }
        if(h.getStatus().equals("OPEN")){
            for(patient p: patient_list){
                if(!p.isAdmission_status()){
                    if(p.getTemperature()<=h.getTemperature_criteria()){
                        p.setAdmission_status(true);
                        p.setInstitute(h.getName());
                        System.out.println("Recovery days for admitted patient ID "+p.getId()+ " – ");
                        p.setRecovery_days(scan.nextInt());
                        ArrayList<patient> temp=h.getPatients();
                        temp.add(p);
                        h.setPatients(temp);
                        int available_beds=h.getAvailable_beds();
                        h.setAvailable_beds(available_beds-1);
                    }
                }
            }
        }
    }

    public boolean canEnd() {
        for(patient p: patient_list){
            if(!p.isAdmission_status()){
                return false;
            }
        }
        return true;
    }

}

public class Main {
    public static void main(String[] args) {
	// write your code here
        manager m;
        Scanner scan=new Scanner(System.in);
        int patient_count=scan.nextInt();
        m=new manager();
        for(int i=0;i<patient_count;i++)
        {
            String name=scan.next();
            float temperature=scan.nextFloat();
            int oxygen_level= scan.nextInt();
            int age=scan.nextInt();
            m.addPatient(name, temperature, oxygen_level, age);
        }
        do {
            int input;
            input = scan.nextInt();
            switch (input) {
                case 1 -> {
                    String name;
                    name = scan.next();
                    System.out.print("Temperature Criteria - ");
                    float temperature;
                    temperature = scan.nextFloat();
                    System.out.print("Oxygen Levels - ");
                    int oxygen_levels;
                    oxygen_levels = scan.nextInt();
                    int no_of_beds;
                    System.out.print("Number of Available beds – ");
                    no_of_beds = scan.nextInt();
                    healthcare_institute h = new healthcare_institute(name, temperature, oxygen_levels, no_of_beds);
                    System.out.print(h);
                    m.admitPatients(scan, h);
                    m.addHealthcareInstitute(h); //Done
                }
                case 2 -> m.removeAdmitted(); //Done
                case 3 -> m.removeClosed(); //Done
                case 4 -> m.countNotAdmitted(); //Done
                case 5 -> m.countOpenInstitute(); //Done
                case 6 -> {
                    String s;
                    s = scan.next();
                    m.displayInstitute(s); //Done
                }
                case 7 -> {
                    int id;
                    id = scan.nextInt();
                    m.displayDetails(id); //Done
                }
                case 8 -> m.diplay_all_patients(); //Done
                case 9 -> {
                    String s1;
                    s1 = scan.next();
                    m.display_institute_recovery(s1); //Done
                }
            }
        } while (!m.canEnd());
    }

}
