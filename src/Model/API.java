package Model;

import java.io.IOException;
import java.util.*;

public class API {

    static ObjectiveCard[] cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    static Player[] jogadores;

    static TerritoryCard[] cartasTerritorio = TerritoryCardDeck.getInstance().setCartasEmbaralhadas();
    

    private static int numJogadores = 0;

    public static void setDono(String carta, Player jog) {
        for (ObjectiveCard cart:cartas) {
            if (cart.description.equals(carta))
                cart.dono = jog;
        }
    }

    public static String getNomeJogador(Player jogador) {
        return jogador.name;
    }

    public static ArrayList<String> getCartasDono(String dono) {
        ArrayList<String> retorno = new ArrayList<String>();
        Player jog;
        for(int i=0;i<21;i++) {
            jog = getDono(cartas[i].description);
            if (jog != null && jog.name.equals(dono)) {
                retorno.add(cartas[i].description);
            }
        }
        return retorno;
    }

    public static Player getDono(String carta) {
        return ObjectiveCard.retornaDono(cartas, carta);
    }

    public static void setNumJogadores(int i) {
        numJogadores = i;
        return;
    }

    public static void restartCartas() {
        ObjectiveCardDeck.getInstance().nullCartas();
        cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    }

    public static ObjectiveCard getCarta(String nome) {
        for (ObjectiveCard cart:API.cartas) {
            if (cart.description.equals(nome))
                return cart;
        }
        return null;
    }

    public static int getQtdJogadores() {
        return numJogadores;
    }

    public static void setQtdJogadores(int qtdJogadores) {
        API.numJogadores = qtdJogadores;
    }

    public static void incQtdJogadores(boolean bool) {
        if (bool) {numJogadores++;return;}
        numJogadores--;
        
    }

    public static int jogaDado() {
        Dado.jogaDado();
        return Dado.dado;
    }
    
    public static Integer idCarta(String carta) {
        return ObjectiveCard.idCarta(carta);
    }



}
