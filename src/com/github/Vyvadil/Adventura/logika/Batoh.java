package com.github.Vyvadil.Adventura.logika;
import java.util.Map;
import java.util.HashMap;

/**
 * Třída Batoh implementuje hráčův batoh, kam sbírá věci.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class Batoh {
    
    public static final int KAPACITA_BATOHU = 4;
    private Map<String, Vec> veci;

    /**
     * konstruktor třídy Batoh
     */
    public Batoh() {
        veci = new HashMap<>();
    }

    /**
     * přidá věc do batohu pokud je v batohu místo 
     * 
     * @param věc, kterou chcem přidat
     * @return povedlo se(ano, ne)
     */
    
    public boolean pridejVec (Vec vec) {
        if (veci.size() < KAPACITA_BATOHU) {
            veci.put(vec.getNazev(), vec);
            return true;
        }
            return false;
    }

    public Map<String, Vec> getVeci() {
        return veci;
    }
    
    
    
    
    /**
     * odebere věc z batohu a dá je do aktuálního prostoru
     * 
     * @param věc, kterou chcem odebrat
     * @return povedlo se(ano, ne)
     */
      
  
    public Vec odeberVec(String nazevVeci) {
    Vec pomocna;
        if (veci.containsKey(nazevVeci)) {
            pomocna = veci.get(nazevVeci);
            veci.remove(nazevVeci);
            return pomocna;
        }
            return null;          
    }
        
     /**
      *  vrátí obsah batohu (text)
      */
    public String vratObsah() {
        String text = "Obsah batohu: ";
        for(String nazev : veci.keySet()) {
                text += " " + nazev;
        }
        return text;
    }
}
