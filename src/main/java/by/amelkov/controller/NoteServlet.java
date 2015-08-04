package by.amelkov.controller;

import by.amelkov.dao.NoteDAO;
import by.amelkov.dao.NoteImplement;
import by.amelkov.dao.UserDAO;
import by.amelkov.dao.UserImplement;
import by.amelkov.model.Note;
import by.amelkov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class NoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("command");
        if (flag != null) {
            if (flag.contains("login"))logInRequest(request, response);
            if (flag.contains("logout"))logOutRequest(request, response);
            if (flag.contains("addnote"))addNoteRequest(request, response);
            if (flag.contains("editnote"))editNoteRequest(request, response);
            if (flag.contains("editing"))editingRequest(request, response);
            if (flag.contains("deletenote"))deleteNoteRequest(request, response);
            if (flag.contains("getnotes"))getNotesRequest(request, response);
            if (flag.contains("getallnotes"))getAllNotesRequest(request, response);
        }
    }

    private void logInRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean flag = false;
        UserDAO userDAO = new UserImplement();
        String login = request.getParameter("login_user");
        String password = request.getParameter("password_user");
        User tempUser = userDAO.checkUser(login);
        if (tempUser == null) {
            flag = true;
            response.sendRedirect("login.jsp?message=" + URLEncoder.encode("User not found!", "UTF-8"));
        } else {
            tempUser = userDAO.getUser(login, password);
            if (tempUser == null) {
                flag = true;
                response.sendRedirect("login.jsp?message=" + URLEncoder.encode("Incorrect password!", "UTF-8"));
            } else {
                request.getSession().setAttribute("acptLogin", tempUser.getLogin());
                request.getSession().setAttribute("isAdding",true);
            }
        }
        if (!flag) {
            response.sendRedirect("index.jsp");
        }
    }

    private void logOutRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("acptLogin", null);
        response.sendRedirect("index.jsp");
    }

    private void addNoteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String noteText = request.getParameter("note");
            NoteDAO noteDAO = new NoteImplement();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            Note note = new Note(noteText,(String)request.getSession().getAttribute("acptLogin"), sqlDate);
            noteDAO.addNote(note);
            response.sendRedirect("index.jsp");
        }
    }

    private void editNoteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String noteText = request.getParameter("note");
            int id = Integer.parseInt(request.getParameter("idEdit"));
            String login = (String) request.getSession().getAttribute("acptLogin");
            NoteDAO noteDAO = new NoteImplement();
            Note note = noteDAO.getNote(id, login);
            if(note!=null){
                note.setText(noteText);
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                note.setDateCreate(sqlDate);
                noteDAO.editNote(note);
                request.getSession().setAttribute("isAdding", true);
            }
            response.sendRedirect("index.jsp");
        }
    }

    private void editingRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("login.jsp");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String login = (String) request.getSession().getAttribute("acptLogin");
            NoteDAO noteDAO = new NoteImplement();
            Note note = noteDAO.getNote(id, login);
            if(note!=null){
                request.getSession().setAttribute("isAdding", false);
                request.getSession().setAttribute("editedNote", note);
            }
            response.sendRedirect("index.jsp");
        }
    }

    private void deleteNoteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("login.jsp");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String login = (String) request.getSession().getAttribute("acptLogin");
            NoteDAO noteDAO = new NoteImplement();
            Note note = noteDAO.getNote(id, login);
            if(note!=null){
                noteDAO.deleteNote(note);
            }
            response.sendRedirect("index.jsp");
        }
    }

    private void getNotesRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("login.jsp");
        } else {
            NoteDAO noteDAO = new NoteImplement();
            List<Note> listNote = noteDAO.getLastUserNotes((String) request.getSession().getAttribute("acptLogin"));
            request.getSession().setAttribute("listNote", listNote);
        }
    }

    private void getAllNotesRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            NoteDAO notesDAO = new NoteImplement();
            List<Note> listNote = notesDAO.getUserNotes((String) request.getSession().getAttribute("acptLogin"));
            request.getSession().setAttribute("listNote", listNote);
        }
    }
}
