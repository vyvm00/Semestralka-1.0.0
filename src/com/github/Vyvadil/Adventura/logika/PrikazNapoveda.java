package com.github.Vyvadil.Adventura.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Martin Vyvadil
 *@version    pro školní rok 2016/2017
 *  
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;
        
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "===================================== \n" 
        + " Tvým úkolem je vyrobit kouzelný meč a porazit draka."
        + " Na výrobu meče bude potřeba nasbírat potřebné materiály \n"
        + " a něco na směnu pro poustevníka. (Dračí_lektvar Posvátná_kůra Kouzelné_diamanty Ocel) \n"
        + " Aby ses dostal za řeku, musíš zaplatit převozníkovi. \n" 
        + " Můžeš zadat tyto příkazy: \n"
        + platnePrikazy.vratNazvyPrikazu();
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
