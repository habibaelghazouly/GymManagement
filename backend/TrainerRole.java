package backend;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import constants.FileNames;


public class TrainerRole {

    public MemberDatabase memberDatabase;
    public ClassDatabase classDatabase;
    public MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole() {
        this.memberDatabase = new MemberDatabase(FileNames.MEMBER_FILENAME);
        this.classDatabase = new ClassDatabase(FileNames.CLASS_FILENAME);
        this.registrationDatabase = new MemberClassRegistrationDatabase(FileNames.REGISTRATION_FILENAME);
        memberDatabase.readFromFile();
        classDatabase.readFromFile();
        registrationDatabase.readFromFile();
    }

    public void addMember(String memberId, String name, String membershipType, String email, String phoneNumber, String status) {
        if (memberDatabase.contains(memberId)) {
            System.out.println("Member Id already exists");
        } else {
            String line = memberId + "," + name + "," + membershipType + "," + email + "," + phoneNumber + "," + status;
            Member member = memberDatabase.createRecordFrom(line);
            memberDatabase.insertRecord(member);
        }
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();

    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        AdminRole ar = new AdminRole();
        if (ar.database.contains(trainerID)) {

            String line = classID + "," + className + "," + trainerID + "," + duration + "," + maxParticipants;
            Class c = classDatabase.createRecordFrom(line);
            classDatabase.insertRecord(c);
        }
    }

    public ArrayList<Class> getListOfClasses() {

        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        if (memberDatabase.contains(memberID) && classDatabase.contains(classID)) {

            if (classDatabase.getRecord(classID).getAvailableSeats() > 0) {
                // if (registrationDate.isEqual(LocalDate.now())) {

                String line = memberID + "," + classID + "," + registrationDate + "," + "active";
                MemberClassRegistration reg = registrationDatabase.createRecordFrom(line);
                registrationDatabase.insertRecord(reg);

                int seats = (classDatabase.getRecord(classID).getAvailableSeats()) - 1;
                classDatabase.getRecord(classID).setAvailableSeats(seats);
                return true;

//                } else {
//                    System.out.println("Wrong Date !!!");
//                    return false;
//                }
            } else {
                System.out.println("No available seats!!!");
                return false;
            }

        } else {
            System.out.println("Member or Class not found!!!");
            return false;
        }
        // return true;
    }

    public boolean cancelRegistration(String memberID, String classID) {

        if (memberDatabase.contains(memberID) && classDatabase.contains(classID)) {
            MemberClassRegistration reg = registrationDatabase.getRecord(memberID + "+" + classID);
            if (reg != null) {
                LocalDate registrationDate = reg.getRegistrationDate();
                LocalDate currentDate = LocalDate.now();
                long daysDifference = ChronoUnit.DAYS.between(registrationDate, currentDate);
                if (daysDifference < 3) {
                    String line = reg.lineRepresentation();
                    String[] splitted = line.split(",");
                    String st = splitted[3];
                    if (st.equalsIgnoreCase("cancelled")) {
                        System.out.println("Registration is already cancelled !!");
                        return false;
                    } else {
                        reg.setRegistrationStatus("cancelled");
                        int seats = (classDatabase.getRecord(classID).getAvailableSeats()) + 1;
                        classDatabase.getRecord(classID).setAvailableSeats(seats);
                        registrationDatabase.deleteRecord(memberID + "+" + classID);
                        return true;
                    }
                } else {
                    System.out.println("No refund in more than 3 days !!");
                    return false;
                }

            } else {
                System.out.println("Registration not found !!");
                return false;
            }

        } else {
            System.out.println("Member or Class not found!!!");
            return false;
        }
        //return true;
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {

        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
    }
}
