package com.example.miniapp.service.Impl;

import com.example.miniapp.bean.Etudiant;
import com.example.miniapp.repository.EtudiantRepository;
import com.example.miniapp.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    private EtudiantRepository userRepository;

    @Override
    public Etudiant save(Etudiant entity){
        return userRepository.save(entity);
    }

    @Override
    public Etudiant update(Etudiant entity){
        return userRepository.save(entity);
    }

    @Override
    public void delete(Etudiant entity){
        return userRepository.delete(entity);
    }

    @Override
    public void delete(Long id){
        return userRepository.delete(id);
    }

    @Override
    public Etudiant find(Long id){
        return userRepository.findOne(id);
    }
    @Override
    public List<Etudiant> findAll(){
        return userRepository.findAll();
    }
    @Override
    public void deleteInBatch(List<Etudiant> etudiants){
        userRepository.deleteAllInBatch(etudiants);
    }

    @Override
    public Etudiant findById(Long id) {
        return userRepository.findById(id);
    }
}
