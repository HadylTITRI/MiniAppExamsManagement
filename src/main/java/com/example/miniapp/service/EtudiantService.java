package com.example.miniapp.service;

import com.example.miniapp.bean.Etudiant;
import com.example.miniapp.generic.GenericService;

public interface EtudiantService extends GenericService<Etudiant> {
    Etudiant findById(Long id);

}
