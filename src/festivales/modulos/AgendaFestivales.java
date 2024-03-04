package festivales.modulos;

import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.festivales.modulos.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen algún festival
 *
 * Las claves se recuperan en orden alfabéico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;

    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * añade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se creará una nueva entrada
     * con dicha clave y la colección formada por ese único festival
     *
     * Si la clave (el mes) ya existe se añade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insertándolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el método de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        //TODO
        ArrayList<Festival> f = new ArrayList<>();
        Mes m = festival.getMes();
        if (!this.agenda.containsKey(festival.getMes()))
        {
            f.add(festival);
            agenda.put(m, f);
        }
        else
        {
            agenda.get(m).add(obtenerPosicionDeInsercion(f, festival), festival);
        }

    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {
       //TODO
        int anadir = 0;
        for (Festival festivale : festivales) {
            if (festivale.getNombre().compareTo(festival.getNombre()) > 0) {
                anadir++;
            }
        }
        return anadir;

    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas para recorrer el map
     */
    @Override
    public String toString() {
        //TODO
        String miString = "";
        ArrayList<Mes> misMeses = new ArrayList<>(agenda.keySet());
        ArrayList<Festival> miFestival;
        for (Mes miMes : misMeses)
        {
            miFestival = new ArrayList<>(agenda.get(miMes));
            miString = miString.concat("\n\n" + miMes + " (" + this.festivalesEnMes(miMes) + " festival/es)\n");
            for (Festival festival : miFestival)
            {
                miString = miString.concat(festival.toString());
            }
        }
        return miString;
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
       //TODO
        if (!agenda.containsKey(mes))
        {
            return -1;
        }
        else
        {
            return agenda.get(mes).size();
        }
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colección
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public  TreeMap<Estilo,TreeSet<String>>   festivalesPorEstilo() {
       //TODO
        TreeMap<Estilo,TreeSet<String>> agrupa = new TreeMap<>();
        for (ArrayList<Festival> festivals : agenda.values()) {
            for (Festival festival : festivals) {
                HashSet<Estilo> d = festival.getEstilos();
                for (Estilo estilo : d) {

                    if (!agrupa.containsKey(estilo)) {
                        agrupa.put(estilo, new TreeSet<>());
                    }
                    else {
                        agrupa.get(estilo).add(festival.getNombre());
                    }
                }
            }
        }
        return agrupa;
        }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
       //TODO
        int lugBor = 0;
        if (!agenda.containsKey(mes))
        {
            return -1;
        }
        else
        {
            for (int pos1 = 0; pos1 < agenda.get(mes).size(); pos1++) {
                String lugarActual = agenda.get(mes).get(pos1).getLugar();

                Iterator<String> lu = lugares.iterator();
                while (lu.hasNext()) {
                    String lugar = lu.next();
                    if (Objects.equals(lugarActual, lugar)) {
                        lugBor++;
                        lu.remove();
                        agenda.get(mes).remove(pos1);
                        pos1--;
                    }
                }
            }
            if (agenda.get(mes).isEmpty()) {
                agenda.remove(mes);
            }
        }
        return lugBor;
    }
}
