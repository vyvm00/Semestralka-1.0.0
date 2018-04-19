package logika;

/**
 * Třída PrikazZahod implementuje pro hru příkaz zahod.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    private Batoh batoh;
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazZahod(HerniPlan plan, Batoh batoh) {
        this.batoh = batoh;
        this.plan = plan;
    }
    
    /**
    *   Příkaz zahod zahodí danou věc do aktuálního prostoru, pokud taková věc v batohu je.
    *   
    *   @param název věci kterou chceme zahodit
    *   @return vrátí hráčovi odpověď
    */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám zahodit?";            
        }
        
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        //Najdi a vyber věc v batohu
        Vec zahazovanaVec = batoh.odeberVec(nazevVeci);
        
        if(zahazovanaVec == null) {
           return "Taková věc v batohu není";
        } else {
            aktualniProstor.vlozVec(zahazovanaVec);
            batoh.odeberVec(nazevVeci);
            return "Z batohu jsi vyhodil " + nazevVeci + " do prostoru. ";
        }
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

