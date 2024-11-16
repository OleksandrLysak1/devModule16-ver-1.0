package com.example.todo.service;

import com.example.todo.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    private NoteService noteService;

    @BeforeEach
    void setUp() {
        noteService = new NoteService();
    }

    @Test
    void listAll() {
        Note note1 = new Note();
        note1.setTitle("Note 1");
        noteService.add(note1);

        Note note2 = new Note();
        note2.setTitle("Note 2");
        noteService.add(note2);

        List<Note> notes = noteService.listAll();
        assertEquals(2, notes.size());
        assertEquals("Note 1", notes.get(0).getTitle());
        assertEquals("Note 2", notes.get(1).getTitle());
    }

    @Test
    void add() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");

        Note addedNote = noteService.add(note);
        assertNotNull(addedNote.getId());
        assertEquals("Test Note", addedNote.getTitle());
        assertEquals("Test Content", addedNote.getContent());
    }

    @Test
    void deleteById() {
        Note note = new Note();
        note.setTitle("To Be Deleted");
        Note addedNote = noteService.add(note);

        noteService.deleteById(addedNote.getId());

        assertThrows(NoSuchElementException.class, () -> noteService.getById(addedNote.getId()));
    }

    @Test
    void update() {
        Note note = new Note();
        note.setTitle("Initial Title");
        note.setContent("Initial Content");
        Note addedNote = noteService.add(note);

        addedNote.setTitle("Updated Title");
        addedNote.setContent("Updated Content");
        noteService.update(addedNote);

        Note updatedNote = noteService.getById(addedNote.getId());
        assertEquals("Updated Title", updatedNote.getTitle());
        assertEquals("Updated Content", updatedNote.getContent());
    }

    @Test
    void getById() {
        Note note = new Note();
        note.setTitle("Get Test");
        Note addedNote = noteService.add(note);

        Note fetchedNote = noteService.getById(addedNote.getId());
        assertNotNull(fetchedNote);
        assertEquals("Get Test", fetchedNote.getTitle());

        assertThrows(NoSuchElementException.class, () -> noteService.getById(999L));
    }
}
