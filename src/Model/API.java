package Model;

import java.io.IOException;
import java.util.*;

public class API {

    private static ObjectiveCard[] cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    private static Player[] jogadores;

    private static TerritoryCard[] cartasTerritorio = TerritoryCardDeck.getInstance().setCartasEmbaralhadas();
    
    private static int numJogadores = 0;

    private API() {} // Construtor privado para implementar o Singleton

    public static API getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final API INSTANCE = new API();
    }

    public static void setDono(String carta, Player jog) {
        for (ObjectiveCard cart : cartas) {
            if (cart.getDescription().equals(carta))
                cart.setDono(jog);
        }
    }

    public static String getNomeJogador(Player jogador) {
        return jogador.name;
    }

    public static ArrayList<String> getCartasDono(String dono) {
        ArrayList<String> retorno = new ArrayList<>();
        Player jog;
        for (ObjectiveCard card : cartas) {
            jog = ObjectiveCard.getOwnerByCardDescription(cartas, card.getDescription());
            if (jog != null && jog.getName().equals(dono)) {
                retorno.add(card.getDescription());
            }
        }
        return retorno;
    }

    public static Player getDono(String carta) {
        return ObjectiveCard.getOwnerByCardDescription(cartas, carta);
    }
    
    
    //acho que esses dois metodos abaixo estão fazendo a mesma coisa
    public static void setNumJogadores(int i) {
        numJogadores = i;
        return;
    }
    
    public static int getQtdJogadores() {
        return numJogadores;
    }

    public static void restartCartas() {
        ObjectiveCardDeck.getInstance().nullCartas();
        cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    }

    public static ObjectiveCard getCarta(String nome) {
        for (ObjectiveCard card : cartas) {
            if (card.getDescription().equals(nome))
                return card;
        }
        return null;
    }
    
    public static ArrayList<String> getDescricoesCartas() {
    	ArrayList<String> descricoes = new ArrayList<>();
        for (ObjectiveCard cart : cartas) {
            descricoes.add(cart.getDescription());
        }
        return descricoes;
    }

   

    public static void setQtdJogadores(int qtdJogadores) {
        API.numJogadores = qtdJogadores;
    }

    public static void incQtdJogadores(boolean bool) {
        if (bool) {numJogadores++;return;}
        numJogadores--;
        
    }

    public static void rolaDado() {
        Dado.jogaDado();
    }
    
    public static Integer idCarta(String carta) {
    	return ObjectiveCardDeck.getDescricaoIdMap().get(carta);
    }

	public static TerritoryCard[] getCartasTerritorio() {
		return cartasTerritorio;
	}



}
