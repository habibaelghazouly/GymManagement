
package backend;

public class MemberDatabase extends Database <Member> {
   
    public MemberDatabase(String fileName) {
        super(fileName);
    }
    
    @Override
    public Member createRecordFrom(String line) {
        String[] splitted = line.split(",");
        String Id = splitted[0];
        String name = splitted[1];
        String membership = splitted[2];
        String email = splitted[3];
        String phoneNumber = splitted[4];
        String status = splitted[5];

        Member member = new Member(Id, name, membership ,email, phoneNumber , status);

        return member;
    }
    
}
