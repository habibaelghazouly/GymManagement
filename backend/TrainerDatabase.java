package backend;

public class TrainerDatabase extends Database <Trainer>{

   
    public TrainerDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public Trainer createRecordFrom(String line) {
        String[] splitted = line.split(",");
        String Id = splitted[0];
        String name = splitted[1];
        String email = splitted[2];
        String speciality = splitted[3];
        String phoneNumber = splitted[4];

        Trainer trainer = new Trainer(Id, name, email, speciality, phoneNumber);

        return trainer;
    }
}
