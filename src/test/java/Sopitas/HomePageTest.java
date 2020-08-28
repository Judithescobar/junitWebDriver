package Sopitas;
import org.junit.Test;

public class HomePageTest extends ParentSopitas {

    @Test
    public void testHomePage() throws Exception {
        navegarPagina("https://www.sopitas.com");
        verificarPagina();
        verificarHashtag();
        imprimitHashTag();
    }
}