import java.util.HashMap;

class Catalog extends Search {
    private HashMap<String, String> __book_titles;
    private HashMap<String, String> __book_authors;
    private HashMap<String, String> __book_subjects;
    private HashMap<String, String> __book_publication_dates;

    public Catalog() {
        this.__book_titles = new HashMap<>();
        this.__book_authors = new HashMap<>();
        this.__book_subjects = new HashMap<>();
        this.__book_publication_dates = new HashMap<>();
    }

    @Override
    public void search_by_title(String query) {
        // return all books containing the string query in their title.
        System.out.println(this.__book_titles.get(query));
    }

    @Override
    public void search_by_author(String query) {
        // return all books containing the string query in their author's name.
        System.out.println(this.__book_authors.get(query));
    }

    @Override
    public void search_by_subject(String subject) {
        System.out.println(this.__book_subjects.get(subject));
    }

    @Override
    public void search_by_pub_date(String publish_date) {
        System.out.println(this.__book_publication_dates.get(publish_date));
    }
}