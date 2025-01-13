
package backend;

public class ClassDatabase extends Database<Class> {
    
    public ClassDatabase(String fileName) {
        super(fileName);
    }


    @Override
    public Class createRecordFrom(String line) {
        String[] splitted = line.split(",");
        String classId = splitted[0];
        String className = splitted[1];
        String trainerId = splitted[2];
        int duration = Integer.parseInt(splitted[3]);
        int availableSeats = Integer.parseInt(splitted[4]);

        Class c = new Class(classId, className, trainerId, duration, availableSeats);
        return c;
    }

}
