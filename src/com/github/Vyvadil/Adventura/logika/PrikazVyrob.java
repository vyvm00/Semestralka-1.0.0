package com.github.Vyvadil.Adventura.logika;

/**
 * Třída PrikazVyrib implementuje pro hru příkaz vyrob.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class PrikazVyrob implements IPrikaz
{
    private static final String NAZEV = "vyrob";
    private Batoh batoh;
    private HerniPlan plan;

    /**
     * Constructor for objects of class PrikazVyrob
     */
    public PrikazVyrob (HerniPlan plan , Batoh batoh)
    {
        this.batoh = batoh;
        this.plan = plan;   
    }

        /**
    *   Hráč se musí nacházet ve správném prostoru a držet v batohu správné věci proto,
    *   aby mohl vytvořit meč. Kouzelný meč je věc, díky které hráč vyhraje. Pokud Meč získá 
    *   otevře se poslední prostor hry a to Dračí_doupě.
    *   
    *   @return textová odpověď hráčovi
    */ 
    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor.getNazev().equals("Poustevníkova_jeskyně")) {
            if (batoh.vratObsah().contains("Ocel")&&
                batoh.vratObsah().contains("Dračí_lektvar")&&
                batoh.vratObsah().contains("Posvátná_kůra")&&
                batoh.vratObsah().contains("Kouzelné_diamanty")) {
                    
                //odebere věci z batohu
                batoh.odeberVec("Ocel"); 
                batoh.odeberVec("Dračí_lektvar");
                batoh.odeberVec("Posvátná_kůra");
                batoh.odeberVec("Kouzelné_diamanty"); 
                //vytvoří meč
                Vec kouzelnyMec = new Vec("Kouzelný_meč", true,"kouzelny_mec.jpg");
                batoh.pridejVec(kouzelnyMec);
                //otevře výherní místnost
                plan.otevriDoupe();
                return "Povedlo se to! Nyní máš u sebe Kouzelný meč se \n kterým můžeš porazit draka a dokončit tuto hru!";
            } else {
                return "Nemáš u sebe suroviny potřebné k vytvoření meče! Hledej dál.";
            }
        } else {
                return "Jen poustevník ti dokáže pomoci.";
        }
        
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
