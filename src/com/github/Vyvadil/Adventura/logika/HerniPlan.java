package com.github.Vyvadil.Adventura.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Martin Vyvadil
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan extends Observable{
    
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;
    private Prostor zacarovany_les;
    private Prostor reka;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hrad = new Prostor("Hrad", "Krásný hrad s výhledem na pole,louky a lesy.\n Život zde by byl ideální, kdyby místní obyvatelstvo neohrožoval obáváný drak.",150,0);
        Prostor pole = new Prostor ("Pole", "Pšenice, žito, kukuřici a další zde pěstuje místní obyvatelstvo.",0,0);
        Prostor les = new Prostor ("Les", "Cítíš ten čerstvý vzduch? Tento les doslova chrlí energií a životem.",150,80);
        Prostor louka = new Prostor ("Louka", "Krásna rozkvetlá louka",0,80);
        Prostor poustevnikova_jeskyne = new Prostor ("Poustevníkova_jeskyně", "Říká se, že zde bydleli lidé už pred tísíci lety. \n"
        + "Dnešní dny se zde usadil starý poustevník ,který zná různou magii",250,80);
        reka = new Prostor ("Řeka", "Někdy klidná, při deštích však divoká. Převozník vás za zlatku převeze na druhou stranu.\n"
        + "Dávejte si, ale pozor, za řekou vás nečeká nic dobrého.",150,160);
        Prostor pustina = new Prostor ("Pustina", "Zde bývalo krásné království. Drak však vše zničil a co nezničil tak uteklo.",150,230);
        zacarovany_les = new Prostor("Začarovaný_les", "Nic zde v pořádku, radši odtud prchej ať se ti něco nestane.",150,300);
        Prostor trpaslici_zeme = new Prostor("Trpasličí_země", "V horách se nacházejí tyto zajímavé stvoření.\n"
        + "Když se objeví Drak schovají se v různých skrýších a podzemních chodbách",0,300);
        Prostor rise_elfu = new Prostor ("Říše_elfů", "Elfové jsou lidé hodní, nepustí si k sobě však každého. \n Chovej se k ním slušně a oni tě pohostí.",250,300);
                
        // přiřazují se průchody mezi prostory (sousedící prostory)
        hrad.setVychod(pole);
        hrad.setVychod(les);
        pole.setVychod(louka);
        pole.setVychod(hrad);
        louka.setVychod(pole);
        louka.setVychod(les);
        les.setVychod(hrad);
        les.setVychod(louka);
        les.setVychod(reka);
        les.setVychod(poustevnikova_jeskyne);
        poustevnikova_jeskyne.setVychod(les);
        reka.setVychod(les);
        pustina.setVychod(zacarovany_les);
        pustina.setVychod(reka);
        zacarovany_les.setVychod(pustina);
        zacarovany_les.setVychod(trpaslici_zeme);
        zacarovany_les.setVychod(rise_elfu);
        trpaslici_zeme.setVychod(zacarovany_les);
        rise_elfu.setVychod(zacarovany_les);
             
        // hra začíná v hradě      
        aktualniProstor = hrad; 
        
        // věci
        Vec zlato = new Vec("Zlato", true,"x.jpg");
        Vec zub = new Vec("Dračí_zub", true,"x.jpg");
        Vec sekera = new Vec("Obouruční_sekera", false,"x.jpg");
        Vec zlato1 = new Vec ("Zlato", true,"x.jpg");
        Vec kost = new Vec ("Kost", true,"x.jpg");
        Vec zelezo = new Vec("Kus_železa", true,"x.jpg");
        Vec truhla = new Vec("Truhla", false,"x.jpg");
        Vec ebenoveDrevo = new Vec("Ebenové_dřevo",true,"x.jpg");
        Vec sip = new Vec("Jedovatý_šíp", true,"x.jpg");
        Vec stribro = new Vec("Stříbro",false,"x.jpg");
        Vec chleba = new Vec("Chleba",true,"x.jpg");
        Vec ocel = new Vec("Ocel", true,"x.jpg");
        Vec draci_lektvar = new Vec("Dračí_lektvar", true,"x.jpg");
        Vec kura = new Vec("Posvátná_kůra", true,"x.jpg");
        Vec kouzelne_diamanty = new Vec ("Kouzelné_diamanty", true,"x.jpg");            
                
        // vkládání věcí do místností
        hrad.vlozVec(zelezo);
        hrad.vlozVec(chleba);
        hrad.vlozVec(sekera);
        louka.vlozVec(zlato);        
        les.vlozVec(zlato1);
        les.vlozVec(sekera);
        pole.vlozVec(ocel);
        poustevnikova_jeskyne.vlozVec(kost);
        zacarovany_les.vlozVec(draci_lektvar);
        zacarovany_les.vlozVec(truhla);
        trpaslici_zeme.vlozVec(kura);
        trpaslici_zeme.vlozVec(stribro);
        rise_elfu.vlozVec(kouzelne_diamanty);
        rise_elfu.vlozVec(sip);
        rise_elfu.vlozVec(ebenoveDrevo);
    }
       
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Metoda testující, zdali je výhra. Hráč vyhrál, pokud se dostal do výherního prostoru.
     * 
     * @return je výhra, není výhra
     */
    
    public boolean jeVyhra() {
        if(aktualniProstor.equals(vyherniProstor)) {
            return true;
        }
            return false;
    }
    
    /**
     * Tato metoda otevře výherní prostor hry
     */
    
    public void otevriDoupe() {
        Prostor draciDoupe = new Prostor("Dračí_doupě", "Po dlouhém krvavém boji porážíš Draka!",150,370);
        zacarovany_les.setVychod(draciDoupe);
        vyherniProstor = draciDoupe;        
    }
        
    /**
     * Otevře prostor Pustina, po zaplacení převozníkovi.
     */
    public void otevriProstor() {
        Prostor pustina = new Prostor("Pustina", "Zde bývalo krásné království. Drak však vše zničil a co nezničil tak uteklo.",150,230);
        reka.setVychod(pustina);
        pustina.setVychod(reka);
        pustina.setVychod(zacarovany_les);
    }
    // Metoda která zajištuje, že při upozornění pozorovatele se všichni pozorvatelé změní.
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
}
