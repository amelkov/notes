package by.amelkov.model;
import java.sql.Date;

public class Note {

    private int id;
    private String text;
    private String login;
    private Date dateCreate;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getLogin() {
        return login;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Note(int id, String text, String login, Date dateCreate) {
        this.id = id;
        this.text = text;
        this.login = login;
        this.dateCreate = dateCreate;
    }

    public Note(String text, String login, Date dateCreate) {
        this.text = text;
        this.login = login;
        this.dateCreate = dateCreate;
    }
}
