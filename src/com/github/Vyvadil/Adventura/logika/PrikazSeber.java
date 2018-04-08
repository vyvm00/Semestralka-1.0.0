package com.github.Vyvadil.Adventura.logika;


/**
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class PrikazSeber implements IPrikaz 
{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;

      /**
    *  Konstruktor třídy
    *  @param plan - herní plán, kterým hráč prochází
    *  @param batoh, do kterého se vkládají věci
    */ 
   
    public PrikazSeber(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     *  Provádí příkaz "seber". Testuje zdali je věc možné sebrat(prenositelna), jestli
     *  je věc v daném prostoru. Pokud ano věc odebereme z místnosti a vložíme do batohu.
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                         kterou chceme sebrat.
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat jméno předmětu";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbiranaVec = aktualniProstor.odeberVec(nazevVeci);
            
        //jestliže věc v prostoru není, return...
        if (sbiranaVec == null) {
            return "To tu není!";
        }
        else {
            if(sbiranaVec.jePrenositelna()){
                //je třeba se zeptat, zda se věc vejde do batohu
                //pokud ano vlozi se do batohu, pokud ne tak do prostoru
                if(batoh.pridejVec(sbiranaVec)){
                    batoh.pridejVec(sbiranaVec);
                    return "Věc "+ sbiranaVec.getNazev() + " se přesunula do batohu.";
                }else {
                    aktualniProstor.vlozVec(sbiranaVec);
                    return "Batoh je plný.";
                }
                //jestli že má sbírana věc parametr false, tak se nedá sebrat
            } else {
                aktualniProstor.vlozVec(sbiranaVec);
                return "To neuzvedneš!";
            }
        }
    }
    
         /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @ return nazev prikazu
     */
    @Override 
    public String getNazev() {
        return NAZEV;
    }
 
}
