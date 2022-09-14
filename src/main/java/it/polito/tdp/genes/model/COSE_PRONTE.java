package it.polito.tdp.genes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.itunes.db.ItunesDAO;
import it.polito.tdp.itunes.model.Track;
import it.polito.tdp.nyc.model.City;
import it.polito.tdp.yelp.db.DBConnect;
import it.polito.tdp.yelp.db.YelpDao;
import it.polito.tdp.yelp.model.ArcoGrafo;
import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Model;
import javafx.event.ActionEvent;

public class COSE_PRONTE {
	
		*RIEMPIMENTO TENDINE*
	
		/* DAO
		public List<String> getAllCities() {
			String sql = "SELECT DISTINCT city "
					+ "FROM business "
					+ "ORDER BY city" ;
			List<String> result = new ArrayList<>() ;
			
			Connection conn = DBConnect.getConnection();
	
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while (res.next()) {
					result.add(res.getString("city")) ;
				}
				conn.close();
				return result;
	
			} catch(SQLException ex) {
				throw new RuntimeException("Error in DB", ex) ;
			}
		}
	
		// MODEL
		public List<String> getAllCities() {
			YelpDao dao = new YelpDao() ;
			return dao.getAllCities() ;
		}

		// FXMLController
		public void setModel(Model model) {
	    	this.model = model;
	    	
	    	cmbCitta.getItems().addAll(model.getAllCities()) ;
	    	
	    	for(int anno=2005; anno<=2013; anno++) {
	    		cmbAnno.getItems().add(Year.of(anno)) ;
	    	}
		} */
	
		*DA METTERE SEMPRE NEL MODEL*
	 
	 
	 	/*rivate ItunesDAO dao;	
		private Graph<Track, DefaultWeightedEdge> grafo;
	
		
		public Model() {	
			dao = new ItunesDAO();
		}
	
		public boolean grafoCreato() {
			if(this.grafo == null)
				return false;
			else
				return true;
		}
		
		public int nVertici() {
			return this.grafo.vertexSet().size();
		}

		public int nArchi() {
			return this.grafo.edgeSet().size();
		}*/
	
		*CREAZIONE GRAFO*
		
		// SEMPLICE, NON ORIENTATO E PESATO
		 /*public void creaGrafo(String provider) {
			// CREO IL GRAFO
			this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
			// AGGIUNGO I VERTICI
			if(this.dao.getVertici(provider) != null)
				Graphs.addAllVertices(grafo, this.dao.getVertici(provider));
			// AGGIUNGO GLI ARCHI
			for(City c1 : this.grafo.vertexSet()) {
				for(City c2 : this.grafo.vertexSet()) {
					if(!c1.equals(c2))
					Graphs.addEdgeWithVertices(this.grafo, c1, c2, LatLngTool.distance(c1.getPosizione(), c2.getPosizione(), LengthUnit.KILOMETER));
				}
			}
			
			System.out.println("Grafo Creato!");
			System.out.println(String.format("# VERTICI: %d", this.grafo.vertexSet().size()));
			System.out.println(String.format("# ARCHI: %d", this.grafo.edgeSet().size()));
		}
		
		
		// SEMPLICE, ORIENTATO E PESATO
		public String creaGrafo(String city, Year anno) {
			// CREO IL GRAFO
			this.grafo = new SimpleDirectedWeightedGraph<Business, DefaultWeightedEdge>(DefaultWeightedEdge.class) ;
			YelpDao dao = new YelpDao() ;
			
			// AGGIUNGO I VERTICI
			this.vertici = dao.getBusinessByCityAndYear(city, anno) ;
			this.verticiIdMap = new HashMap<>() ;
			for(Business b : this.vertici)
				this.verticiIdMap.put(b.getBusinessId(), b) ;
			
			Graphs.addAllVertices(this.grafo, this.vertici) ;
			
			List<ArcoGrafo> archi = dao.calcolaArchi(city, anno) ;
			for(ArcoGrafo arco : archi) {
				Graphs.addEdge(this.grafo,
						this.verticiIdMap.get(arco.getBusinessId1()),
						this.verticiIdMap.get(arco.getBusinessId2()), 
						arco.getPeso()) ;
			}
	
			System.out.println("Grafo Creato!");
			System.out.println(String.format("# VERTICI: %d", this.grafo.vertexSet().size()));
			System.out.println(String.format("# ARCHI: %d", this.grafo.edgeSet().size()));
		} */
		
		*CONTROLLO SELEZIONAMENTO TENDINA*
		
		String s = this.cmbProvider.getValue();
		if(s == null) {
			txtResult.appendText("Seleziona un Provider!");
			return;
		}
		
		CONTROLLO INSERIMENTO VALORE NUMERICO
		
		/*
		try {		 
			x = Integer.parseInt(txtN.getText());
		} catch(NumberFormatException e) {
			txtResult.appendText("Inserire valore numerico");
			return;
		}
		
		double soglia = -1 ;
    	try {
    		soglia = Double.parseDouble(txtX.getText()) ;
    	} catch(NumberFormatException ex) {
    		txtResult.appendText("ERRORE: Il campo soglia deve essere numerico\n");
    		return ;
    	}
    	
    	// CONTROLLO VALORE INSERITO COMPRESO
    	
    	if(soglia<0.0 || soglia>1.0) {
    		txtResult.appendText("ERRORE: Il campo soglia deve compreso tra 0 e 1\n");
    		return ;

    	}
    	*/
    	
    	*CREAZIONE GRAFO (FXML)*
    	
    	/*void doCreaGrafo(ActionEvent event) {
        	txtResult.clear();
        	
        	// INSERIRE CONTROLLO INSERIMENTO
    		
    		this.model.creaGrafo(x);
    		txtResult.appendText("Grafo Creato!\n");
        	txtResult.appendText("# VERTICI: " +this.model.nVertici() +"\n");
        	txtResult.appendText("# ARCHI: " +this.model.nArchi() +"\n");
	 	*/

    	
    	*CREAZIONE TABELLA*
    	
    	// FXMLController 2022-05-31-simulazione
}
