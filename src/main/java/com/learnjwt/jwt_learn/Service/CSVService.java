package com.learnjwt.jwt_learn.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.learnjwt.jwt_learn.Controller.CSVHelper;
import com.learnjwt.jwt_learn.Entity.Projet;
import com.learnjwt.jwt_learn.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    ProjetRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Projet> projet = CSVHelper.csvToDataSet(file.getInputStream());
            repository.saveAll(projet);
        } catch (IOException e) {
            throw new RuntimeException("fallo guardar datos de csv: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Projet> projet = repository.findAll();

        ByteArrayInputStream in = CSVHelper.dataSetModelosToCSV(projet);
        return in;
    }

    public List<Projet> getAllprojet() {
        return repository.findAll();
    }
}

