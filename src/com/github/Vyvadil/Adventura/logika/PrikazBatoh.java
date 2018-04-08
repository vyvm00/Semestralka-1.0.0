package com.github.Vyvadil.Adventura.logika;

/**
 * Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private Batoh batoh;
    
    /**
     * Konstruktor pro třídu PrikazBatoh
     */
    public PrikazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }
    
    /**
     *  Provede příkaz batoh nezávisle na parametru.
     *  
     *  @return seznam věcí v batohu
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
       return batoh.vratObsah().toString();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
        
}
