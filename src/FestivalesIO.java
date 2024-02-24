

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
            sc = new Scanner(FestivalesIO.class.getResourceAsStream("/festivales.csv"));
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
        String nombre2 = "";
        String lugar = "";
        LocalDate fechaInicio = null;
        int duracion = 0;
        HashSet<Estilo> estilo = new HashSet<>();
        String[] festival = lineaFestival.split(":");
        for (int pos = 0;pos<festival.length;pos++)
        {
            if (pos == 0)
            {
                String nombre1 = festival[pos].trim();
                int descartes = 0;
                for (int letra =0;letra<nombre1.length();letra++)
                {
                    if (letra == 0)
                    {
                        nombre2 = nombre2 + nombre1.toUpperCase().charAt(letra);
                    }
                    else if (nombre1.charAt(letra) == ' ')
                    {
                        descartes = letra + 1;
                        nombre2 = nombre2 + " ";
                        nombre2 = nombre2 + nombre1.toUpperCase().charAt(descartes);
                    }
                    else if (letra != descartes)
                    {
                        nombre2 = nombre2 + nombre1.charAt(letra);
                    }
                }
            }
            else if (pos == 1)
            {
                lugar = festival[pos].toUpperCase().trim();
            }
            else if (pos == 2)
            {
                fechaInicio = LocalDate.parse(festival[pos].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
            else if (pos == 3)
            {
                duracion = Integer.parseInt(festival[pos].trim());
            }
            else
            {
                Estilo miEstilo = Estilo.valueOf(festival[pos].toUpperCase().trim());
                estilo.add(miEstilo);
            }
        }
        return new Festival(nombre2, lugar, fechaInicio, duracion, estilo);
    }
    
   
    
    
}
