package backend;

import java.util.*;
import constants.FileNames;

public class AdminRole {

    public TrainerDatabase database;

    public AdminRole() {
       this.database = new TrainerDatabase(FileNames.TRAINER_FILENAME);
       database.readFromFile();
    }

    public void addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        if (database.contains(trainerId)) {
            System.out.println("Trainer ID already exists !!");
        } else {
            String line = trainerId + "," + name + "," + email + "," + speciality + "," + phoneNumber;
            Trainer T = database.createRecordFrom(line);
            database.insertRecord(T);
        }

    }
    
    public ArrayList<Trainer> getListOfTrainers(){
      
        return database.returnAllRecords(); 
    }

    public void removeTrainer(String key){
        database.deleteRecord(key);
    }
    
    public void logout(){
        database.saveToFile();
    }
            
        
}
