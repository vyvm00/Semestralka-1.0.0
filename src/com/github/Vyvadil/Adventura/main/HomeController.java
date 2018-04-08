package com.github.Vyvadil.Adventura.main;

import com.github.Vyvadil.Adventura.logika.Hra;
import java.util.Observable;
import java.util.Observer;

import com.github.Vyvadil.Adventura.logika.Prostor;
import com.github.Vyvadil.Adventura.logika.Vec;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class HomeController extends GridPane implements Observer, Initializable {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Object> seznamVeciMistnost = new ListView<>();
        @FXML private ListView<Object> seznamVeciBatoh = new ListView<>();
	@FXML private ListView<Object> seznamVychodu = new ListView<>();
	@FXML private ImageView uzivatel;
	
	private Hra hra;
        private ObservableList<Object> veciMistnost = FXCollections.observableArrayList();
        private ObservableList<Object> veciBatoh = FXCollections.observableArrayList();
        private ObservableList<Object> vychody = FXCollections.observableArrayList();
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() 
        {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if(hra.konecHry()) 
                {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
                hra.getHerniPlan().notifyObservers();
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	@Override
        public void initialize(URL url, ResourceBundle rb)  
        {
                hra = new Hra();
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
                
                
                seznamVeciMistnost.setItems(veciMistnost);
                seznamVeciBatoh.setItems(veciBatoh);
                seznamVychodu.setItems(vychody);
                
		hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
	}
        
        @FXML public void klikBatoh() 
        {
                    Map<String,Vec> seznam;
                    seznam = hra.getBatoh().getVeci();
                    int index = seznamVeciBatoh.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String pom : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = pom;
                       }
                       pomocna++;
                    }

            if(!hra.konecHry())
            {
            vstupniText.setText("zahod " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void klikMistnost() 
        {
            String nazev = seznamVychodu.getSelectionModel().getSelectedItem().toString();
            if(!hra.konecHry())
            {
            vstupniText.setText("jdi " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void novaHra() 
        {
                hra = new Hra();
		vystup.setText(hra.vratUvitani());
		vstupniText.setDisable(false);
                hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
        }
        
        @FXML public void konecHry() 
        {
            vstupniText.setText("konec");
            odesliPrikaz();
        }
        
         @FXML public void Napoveda() 
        {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            
            WebView webView = new WebView();               
            webView.getEngine().load(com.github.Vyvadil.Adventura.main.GuiAdventura.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 1200, 650));
            stage.show();
        }
        
        @FXML public void klikVecMistnost() 
        {
                    Map<String,Vec> seznam;
                    seznam = hra.getHerniPlan().getAktualniProstor().getVeci();
                    int index = seznamVeciMistnost.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String pom : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = pom;
                       }
                       pomocna++;
                    }
            if(!hra.konecHry())
            {
            vstupniText.setText("seber " + nazev);
            odesliPrikaz();
            }
        }


	@Override
	public void update(Observable arg0, Object arg1) 
        {
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
            
                veciMistnost.clear();
                veciBatoh.clear();
                vychody.clear();
		String sVychody = hra.getHerniPlan().getAktualniProstor().seznamVychodu();
                String[] oddeleneVychody = sVychody.split(" ");
                for (int i = 1; i < oddeleneVychody.length; i++) 
                {
                    vychody.add(oddeleneVychody[i]);
                }
                
                Map<String,Vec> sBatoh = hra.getBatoh().getVeci();
                for (String pom : sBatoh.keySet()) 
                {
                    Vec pomocna = sBatoh.get(pom);
                    ImageView obrazek = new ImageView(new Image(com.github.Vyvadil.Adventura.main.GuiAdventura.class.getResourceAsStream("/zdroje/"+pomocna.getUrl()), 100, 100, false, false));
                    veciBatoh.add(obrazek);
                }
                
                Map<String,Vec> sVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
                for (String pom : sVeci.keySet()) 
                {
                    Vec pomocna = sVeci.get(pom);
                    ImageView obrazek = new ImageView(new Image(com.github.Vyvadil.Adventura.main.GuiAdventura.class.getResourceAsStream("/zdroje/"+pomocna.getUrl()), 100, 100, false, false));
                    veciMistnost.add(obrazek);
                }
	}

}
