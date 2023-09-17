public class Librarian extends Account {
    public Librarian(String id, String password, Person person, AccountStatus status) {
        super(id, password, person, status);
    }

    public void addBookItem(BookItem bookItem) {
        // implementation
    }

    public void blockMember(Member member) {
        // implementation
    }

    public void unblockMember(Member member) {
        // implementation
    }

    public void addMember(Member member){
      Constants.libraryMembers.add(member);
    }
}
