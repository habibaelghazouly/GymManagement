
package backend;

import java.time.*;

public class MemberClassRegistration implements Data {
    private String memberId;
    private String classId;
    private String status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberId, String classId, LocalDate registrationDate , String status) {
        this.memberId = memberId;
        this.classId = classId;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getClassId() {
        return classId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String getSearchKey(){
        return getMemberId()+"+"+ getClassId();
    }

    @Override
    public String lineRepresentation(){
          
        String line= memberId+","+classId+","+registrationDate+","+status;
        return line;  
    }
}
