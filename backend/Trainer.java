package backend;

public class Trainer implements Data {
    
    private String trainerId;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;

    public Trainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        this.trainerId = trainerId;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }
    
    
    @Override
    public String lineRepresentation(){
        String line =trainerId+","+name+","+email+","+speciality+","+phoneNumber;
        return line;
    }

    @Override
    public String getSearchKey() {
        return trainerId;
    }
    

}
