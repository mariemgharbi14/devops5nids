package tn.esprit.rh.achat.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Categorie;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategorieProduitServiceImplTest {

	
	@Mock
	CategorieProduitRepository categorieProduitRepository;
	
	@InjectMocks
	CategorieProduitServiceImpl CategorieProduitService;
	
	Categorie c= new Categorie(14L, 1998L);
	
	@Test
	public void getAllCategorieTest() {
		List<CategorieProduit> CategorieProduitlist = new ArrayList<CategorieProduit>() {

            {
                add(new CategorieProduit(null, 533, 1998));
                add(new CategorieProduit(null, 297, 16l));
                add(new CategorieProduit(null, 197, 112));
            }
        };


        Mockito.when(CategorieProduitService.retrieveAllCategorieProduits()).thenReturn(CategorieProduitlist);
        List<CategorieProduit> categorieList = CategorieProduitService.retrieveAllCategorieProduits();
        System.out.println("success");
				
	}
	
	@Test
	void testaddCategorie() {

        Categorie c = new Categorie();
        c.setIdCategorie(1L);
        Mockito.when(CategorieRepository.save(any())).thenReturn(p);

        assertEquals(1L, p.getIdCategorie());

        }

	@Test
	public void deleteCategorie() {

        Categorie c = new Categorie();
        c.setIdCategorie(Long.valueOf(1));
        CategorieProduitService.addCategorieProduit(c);
        CategorieProduitService.deleteCategorieProduit(c.getIdCategorie());

    }

    @Test
    public void updateCategorie() {
        Mockito.when(CategorieRepository.save(c)).thenReturn(c);
        Categorie c1 = CategorieProduitService.updateCategorieProduit(c);
        Assertions.assertEquals(c, c1);
    }

}
