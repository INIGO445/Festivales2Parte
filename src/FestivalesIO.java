

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * La clase contiene m�odos est�ticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 */
public class FestivalesIO {

    
    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.
                    getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
    }

    /**
     * se parsea la l�nea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
       //TODO
        HashSet<Estilo> estilo = new HashSet<>();
        String[] festival = lineaFestival.split(":");
        System.out.println(festival);
        for (int pos = 0;pos<festival.length;pos++)
        {
            if (pos == 0)
            {
                String nombre = festival[pos];
                nombre.charAt(0);
            }
            else if (pos == 1)
            {
                String lugar = festival[pos].toUpperCase();
            }
            else if (pos == 2)
            {
                LocalDate fechaInicio = LocalDate.parse(festival[pos], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            else if (pos == 3)
            {
                int duracion = Integer.parseInt(festival[pos]);
            }
            else
            {
                Estilo miEstilo = Estilo.valueOf(festival[pos]);
                estilo.add(miEstilo);
            }
        }
        Festival miFestival = new Festival();
        return null;
    }
    
   
    
    
}
