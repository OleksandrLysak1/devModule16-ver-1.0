package com.example.todo.service;

import com.example.todo.model.Note;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    @Test
    void addNote() {
        NoteService service = new NoteService();
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note.");

        Note addedNote = service.add(note);

        assertNotNull(addedNote.getId());
        assertEquals("Test Note", addedNote.getTitle());
    }

    @Test
    void listAllNotes() {
        NoteService service = new NoteService();

        Note note1 = new Note();
        note1.setTitle("First Note");
        service.add(note1);

        Note note2 = new Note();
        note2.setTitle("Second Note");
        service.add(note2);

        List<Note> notes = service.listAll();
        assertEquals(2, notes.size());
    }
}
