package com.example.todo.service;

import com.example.todo.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

    @Service
    public class NoteService {
        private Map<Long, Note> notes = new HashMap<>();
        private AtomicLong idGenerator = new AtomicLong();

        public List<Note> listAll() {
            return new ArrayList<>(notes.values());
        }

        public Note add(Note note) {
            long id = idGenerator.incrementAndGet();
            note.setId(id);
            notes.put(id, note);
            return note;
        }

        public void deleteById(long id) {
            if (!notes.containsKey(id)) {
                throw new NoSuchElementException("Note not found");
            }
            notes.remove(id);
        }

        public void update(Note note) {
            if (!notes.containsKey(note.getId())) {
                throw new NoSuchElementException("Note not found");
            }
            notes.put(note.getId(), note);
        }

        public Note getById(long id) {
            if (!notes.containsKey(id)) {
                throw new NoSuchElementException("Note not found");
            }
            return notes.get(id);
        }
    }

