package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	private ItunesDAO dao;
	private Graph<Album, DefaultEdge> grafo;
	private Map<Integer, Album> mappa;
	private List<Album> best;
	private int num;
	
	public Model() {
	this.dao = new ItunesDAO();
	this.grafo = new SimpleGraph<Album, DefaultEdge>(DefaultEdge.class);
	this.mappa = new HashMap<>();
	}
	
	public void creaGrafo(double durata) {
		for (Album a: dao.getAllAlbums()) {
			mappa.put(a.getAlbumId(), a);
		}
		List<Album> vertici = dao.getVertici(durata, mappa);
		Graphs.addAllVertices(this.grafo, vertici);
		System.out.println("#Vertici: "+ vertici.size());
		
		List<Coppie> coppie = dao.getCoppie(mappa);
		
		for (Coppie c : coppie) {
			if (grafo.vertexSet().contains(c.getA1()) && grafo.vertexSet().contains(c.getA2()) && !c.getA1().equals(c.getA2())) {
				Graphs.addEdgeWithVertices(this.grafo, c.getA1(), c.getA2());
			}
		}
		System.out.println("#Archi: "+ grafo.edgeSet().size());
	}
	
	public int getVertici() {
		return grafo.vertexSet().size();
	}
	
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}

	
	public List<Album> getAlbum(){
		List<Album> album = new ArrayList<>();
		for (Album a: grafo.vertexSet()) {
			album.add(a);
		}
		return album;
	}
	
	public Result calcolaConnessa(Album a1) {
		ConnectivityInspector<Album, DefaultEdge> ci = new ConnectivityInspector<Album, DefaultEdge>(grafo);
		Set<Album> set = ci.connectedSetOf(a1);
		double somma =0.0;
		for (Album a : set) {
			
			somma+=a.getDurata();
		}
		Result r = new Result (set.size(), somma);
		return r;
	}
	
	public Set<Album > trovaSet (Album a1, double d) {
		 if (a1.getDurata() > d) {
	            return null;
	        }
	        
	        List<Album> parziale = new ArrayList<>();
	        parziale.add(a1);
	        this.best = new ArrayList<>();
	        
	        ricorsione(a1, d, parziale);
	        
	        return new HashSet<>(best);
	}


		
	 private void ricorsione(Album a1, double dTOT, List<Album> parziale) {
	        ConnectivityInspector<Album, DefaultEdge> ci = new ConnectivityInspector<>(grafo);
	        Set<Album> set = new HashSet<>(ci.connectedSetOf(a1));
	        set.remove(a1);

	        // Condizione di uscita: se la durata della lista parziale supera il limite dTOT
	        if (getDurata(parziale) > dTOT) {
	            return;
	        }

	        // Aggiorna la soluzione migliore se la lista parziale è migliore della corrente best
	        if (parziale.size() > best.size()) {
	            this.best = new ArrayList<>(parziale);
	        }

	        // Esplora i vicini ricorsivamente
	        for (Album a : set) {
	            if (!parziale.contains(a) && (getDurata(parziale) + a.getDurata()) <= dTOT) {
	                parziale.add(a);
	                ricorsione(a, dTOT, parziale);  // Usa a come nuovo punto di partenza
	                parziale.remove(parziale.size() - 1);
	            }
	        }
	    }

	private double getDurata(List<Album> parziale) {
		double somma =0;
		for (Album a : parziale) {
			somma+=a.getDurata();
		}
		return somma;
	}

	
	
	
	
	
}
