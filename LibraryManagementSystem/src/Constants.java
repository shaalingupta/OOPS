import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    public static final int MAX_LENDING_DAYS = 10;

    public static List<Member> libraryMembers = new ArrayList<>();

    public static Member getLibraryMember(String memberId) throws Exception {
        for(Member member : libraryMembers){
            if(member.getId().equals(memberId)){
                return member;
            }
        }
        throw new Exception("Member Id invalud");
    }
}
