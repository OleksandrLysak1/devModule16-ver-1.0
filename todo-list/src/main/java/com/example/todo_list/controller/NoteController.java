package com.example.todo_list.controller;

import com.example.todo.service.NoteService;
import com.example.todo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/note/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note_list";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/note/edit")
    public String editNoteForm(@RequestParam Long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note_edit";
    }

    @PostMapping("/note/edit")
    public String editNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}
