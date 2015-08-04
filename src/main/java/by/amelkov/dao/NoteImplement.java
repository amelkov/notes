package by.amelkov.dao;

import by.amelkov.model.Note;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteImplement implements NoteDAO {

    public int addNote(Note note) {
        int count = 0;
        Statement statement = null;
        DBConnection connection = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            count = statement.executeUpdate(
                    "INSERT INTO notes (datecreate,login,text) VALUES ('"+note.getDateCreate()+"', '"+note.getLogin()+"', '"+note.getText() +"');");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int editNote(Note note) {
        int count = 0;
        Statement statement = null;
        DBConnection connection = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            count = statement.executeUpdate(
                    "UPDATE notes SET  text = '"+note.getText()+"', datecreate = '"+note.getDateCreate()+"' WHERE id = "+note.getId()+";");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int deleteNote(Note note) {
        int count = 0;
        Statement statement = null;
        DBConnection connection = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            count = statement.executeUpdate("DELETE FROM notes WHERE id = " + note.getId() + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public Note getNote(int id, String login) {
        Statement statement = null;
        DBConnection connection = null;
        ResultSet resultSet = null;
        Note note = null;
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM notes WHERE id = "+id+" AND login = '"+login+"';");
            while (resultSet.next()) {
                note = new Note(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getString("login"),resultSet.getDate("datecreate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)resultSet.close();
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return note;
    }

    public List<Note> getUserNotes(String login) {
        Statement statement = null;
        DBConnection connection = null;
        ResultSet resultSet = null;
        List<Note> notes = new ArrayList<Note>();
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM notes WHERE login = '"+login+"' ORDER BY datecreate;");
            while (resultSet.next()) {
                Note note = new Note(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getString("login"),resultSet.getDate("datecreate"));
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)resultSet.close();
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

    public List<Note> getLastUserNotes(String login) {
        Statement statement = null;
        DBConnection connection = null;
        ResultSet resultSet = null;
        List<Note> notes = new ArrayList<Note>();
        try {
            connection = new DBConnection();
            connection.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM notes WHERE login = '"+login+"' ORDER BY datecreate DESC LIMIT 10;");
            while (resultSet.next()) {
                Note note = new Note(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getString("login"),resultSet.getDate("datecreate"));
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)resultSet.close();
                if(statement != null)statement.close();
                if(connection != null)connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }
}
