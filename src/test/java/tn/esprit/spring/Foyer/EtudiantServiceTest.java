package tn.esprit.spring.Foyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.Services.Etudiant.EtudiantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        // Arrange
        Etudiant etudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEt("Dupont")
                .prenomEt("Jean")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build();

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addOrUpdate(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("Dupont", result.getNomEt());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(Etudiant.builder()
                .idEtudiant(1L)
                .nomEt("Dupont")
                .prenomEt("Jean")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build());
        etudiants.add(Etudiant.builder()
                .idEtudiant(2L)
                .nomEt("Martin")
                .prenomEt("Paul")
                .cin(87654321L)
                .ecole("INSAT")
                .dateNaissance(LocalDate.of(1999, 12, 31))
                .build());

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Etudiant etudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEt("Dupont")
                .prenomEt("Jean")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build();

        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Dupont", result.getNomEt());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Arrange
        long id = 1L;
        doNothing().when(etudiantRepository).deleteById(id);

        // Act
        etudiantService.deleteById(id);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(id);
    }

    @Test
    void testDelete() {
        // Arrange
        Etudiant etudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEt("Dupont")
                .prenomEt("Jean")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build();

        doNothing().when(etudiantRepository).delete(etudiant);

        // Act
        etudiantService.delete(etudiant);

        // Assert
        verify(etudiantRepository, times(1)).delete(etudiant);
    }
}
