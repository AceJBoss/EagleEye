package com.jboss.eagleeye.service;

import com.jboss.eagleeye.model.Incident;
import com.jboss.eagleeye.model.Municipal;
import com.jboss.eagleeye.model.User;
import com.jboss.eagleeye.repository.IncidentRepository;
import com.jboss.eagleeye.repository.MunicipalRepository;
import com.jboss.eagleeye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

@Service
public class IncidentService {
    private final Path root = Paths.get("uploads");
    private MultipartFile incidentPicture;

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MunicipalRepository municipalRepository;

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (final IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    // Report an Incident
    public ResponseEntity<Incident> reportIncident(Incident incident){
        try {
            if (incident.getPicture() != null) {
                // delete previous picture
                FileSystemUtils.deleteRecursively(root.resolve(incident.getPicture()));
            }
            final Random rand = new Random();
            final String filename = rand.nextInt() + "_" + incidentPicture.getOriginalFilename();
            Files.copy(incidentPicture.getInputStream(), this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            incident.setPicture(filename);
        }catch (Exception e){}

        User user = userRepository.findById(incident.getUser().getId()).orElse(null);
        Municipal municipal = municipalRepository.findById(incident.getUser().getId()).orElse(null);
        incident.setUser(user);
        incident.setStatus(0L);
        incident.setSeen(0L);
        incident.setMunicipal(municipal);
        return new ResponseEntity<>(incidentRepository.save(incident), HttpStatus.OK);
    }

    //View all Incident
    public List<Incident> fetchIncidentsByMunicipal(Long id){
        Municipal municipal = municipalRepository.findById(id).orElse(null);
        if(municipal == null){
            System.out.println("ERROR>>><<<>> " + id);
        }
        return incidentRepository.findByMunicipalId(municipal.getId());
    }

}
