package tn.esprit.rh.achat;


import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import static org.mockito.BDDMockito.*;

import static org.mockito.Mockito.times;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CategorieProduitTest {

@Mock	
CategorieProduitRepository categorieProduitRepository;
@InjectMocks
CategorieProduitServiceImpl categorieProduit;

CategorieProduit c1 = new CategorieProduit (1L,"codeTest1","categTest1",null);
CategorieProduit c2 = new CategorieProduit (2L,"codeTest2","categTest2",null);

@Test
public void testRetrievecategorie() {
Mockito.when(categorieProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(c1));
CategorieProduit categorie1 = categorieProduit.retrieveCategorieProduit(1L);
Assertions.assertNotNull(categorie1);
}

@Test
public void createcategorieproduitTest()
{
	CategorieProduit cat1 = new CategorieProduit(1L,"codeTest1","categTest1",null);

	categorieProduit.addCategorieProduit(cat1);

	verify(categorieProduitRepository, times(1)).save(cat1);
}
@Test
public void testDeleteStock() {
    long catid =1L;
    categorieProduit.deleteCategorieProduit(catid);
    Assertions.assertNull(categorieProduit.retrieveCategorieProduit(catid));
}
}	