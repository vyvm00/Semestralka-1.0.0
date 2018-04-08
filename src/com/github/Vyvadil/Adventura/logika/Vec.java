package com.github.Vyvadil.Adventura.logika;

/**
 * Třída Vec implementuje věc a její chování.
 *
 * @author Martin Vyvadil
 * @version 1
 */
public class Vec
{
    // instance variables - replace the example below with your own
    private String nazev;
    private boolean prenositelnost;
    private String url;

    /**
     * Konstruktor kde je jako parametr nazev veci a zdali je vec prenositelna
     */
    public Vec(String nazev, boolean prenositelnost,String url)
    {
       this.nazev = nazev;
       this.prenositelnost = prenositelnost;
       this.url = url;
    }

    /**
     * metoda vraci nazev veci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * metoda vraci prenositelnost veci
     **/
     
    public boolean jePrenositelna() {
        return prenositelnost;
    }

    public String getUrl() {
        return url;
    }
    
    
    
}
