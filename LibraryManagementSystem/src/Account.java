public abstract class Account {
    private String id;
    private String password;
    private AccountStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Person person;

    public Account(String id, String password, Person person, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.person = person;
        this.status = status;
    }

    public void resetPassword() {
        // implementation
    }
}
