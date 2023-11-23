package Model;

import java.util.ArrayList;
import java.util.List;

abstract class ObjectiveCard {
    protected Player dono;
    protected Continent asia, europa, americasul, americanorte, africa, oceania;
    protected List<Player> todos_jogadores;
    protected String nome;
    protected String corAlvo;

    // construtor
    public ObjectiveCard(List<Player> todos_jogadores, List<Continent> mapa, String name, String corAlvo) {
        this.todos_jogadores = new ArrayList<>();
        for (Player el : todos_jogadores) {
            this.todos_jogadores.add(el);
        }
        for (Continent reg : mapa) {
        	if(reg.getName().equals("África")) {
    			this.africa= reg; 
    		}
    		if(reg.getName().equals("Ásia")) {
    			this.asia = reg;
    		}
    		if(reg.getName().equals("América do Norte")) {
    			this.americanorte = reg;
    		}
    		if(reg.getName().equals("América do Sul")) {
    			this.americasul = reg;
    		}
    		if(reg.getName().equals("Europa")) {
    			this.europa = reg;
    		}
    		if(reg.getName().equals("Oceania")) {
    			this.oceania = reg;
    		}
        }
        this.nome = name;
        this.corAlvo = corAlvo;
    }

    public String get_nome() {
        return this.nome;
    }

    public abstract boolean verifica_status();

    public void ganha_dono(Player dono) {
        this.dono = dono;
    }
}






