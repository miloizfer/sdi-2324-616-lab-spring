package com.uniovi.sdi.notaneitor.services;

import com.uniovi.sdi.notaneitor.entities.Mark;
import com.uniovi.sdi.notaneitor.repositories.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    public List<Mark> getMarks() {
        List<Mark> marks = new ArrayList<Mark>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }

    public Mark getMark(Long id) {
        return marksRepository.findById(id).get();
    }

    public void addMark(Mark mark) {
        // Si en Id es null le asignamos el último + 1 de la lista
        marksRepository.save(mark);
    }

    public void deleteMark(Long id) {
        marksRepository.deleteById(id);
    }
}