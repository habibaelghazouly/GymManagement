
package backend;

import java.time.LocalDate;

public class MemberClassRegistrationDatabase extends Database <MemberClassRegistration> {

    public MemberClassRegistrationDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public MemberClassRegistration createRecordFrom(String line) {
        String[] splitted = line.split(",");
        String memberId = splitted[0];
        String classId = splitted[1];
        LocalDate date = LocalDate.parse(splitted[2]);
        String status = splitted[3];

        MemberClassRegistration mr = new MemberClassRegistration(memberId , classId , date , status);

        return mr;
    }
    
}

