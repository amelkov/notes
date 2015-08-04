package by.amelkov.dao;

import by.amelkov.model.Note;

import java.util.List;

public interface NoteDAO {
    int addNote(Note note);
    int editNote(Note note);
    int deleteNote(Note note);
    Note getNote(int id, String login);

    List<Note> getUserNotes(String user);
    List<Note> getLastUserNotes(String user);
}
