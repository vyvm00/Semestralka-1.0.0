package logika;


/**
 *  Třída PrikazZaplat implementuje pro hru příkaz zaplat.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class PrikazZaplat implements IPrikaz
{
    private static final String NAZEV = "zaplat";
    private Batoh batoh;
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     */
    public PrikazZaplat (HerniPlan plan , Batoh batoh)
    {
        this.batoh = batoh;
        this.plan = plan;   
    }

        /**
    *   Tato metoda slouží k tomu, aby se hráč mohl dostat přes řeku.
    *   Musí za to zaplatit převozníkovi.
    *   
    *   @return textová odpověď hráčovi
    */ 
    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor.getNazev().equals("Řeka")) {
            if (batoh.vratObsah().contains("Zlato"))
             {
                //odebere Zlato
                batoh.odeberVec("Zlato"); 

                //otevře prostor za řekou                            
                plan.otevriProstor();
                return "Zaplatil jsi převozníkovi. Nyní se už můžeš pohybovat po řece, \n" +
                "po celou dobu hry. (Odemkl se ti prostor Pustina.) \n"
                + "východy: Pustina";
            } else {
                return "Nemáš u sebe žádné zlato.";
            }
            } else {
                return "Nejsi ve správném prostoru.";
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


