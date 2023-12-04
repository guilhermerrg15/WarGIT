package Controller;

import java.util.ArrayList;
import Model.API;
import Model.PlayerColor;
import View.ViewAPI;

public class APIController {
    public static APIController controller = null;

    private boolean firstRound = true;
    // private int state = 0;
    private int continent;
    private int turn = 0;
    private boolean podeSalvar = true;

    private static final int STATE_POSICIONAMENTO = 0;
    private static final int STATE_ATAQUE = 1;
    private static final int STATE_REALOCACAO = 2;

    private int state = STATE_POSICIONAMENTO;
    private int currentRound = 1;
    

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API api = API.getInstance();

    public boolean startMatch(ArrayList<String> nomes, ArrayList<PlayerColor> cores) {
        int numPlayers = nomes.size();
        for(int i = 0; i < numPlayers; i++){
            // Verificar se há um nome igual a null
            if(nomes.get(i) == null || nomes.get(i).equals("")) {
                api.resetPlayers();
                return false;
            }
            if(api.addPlayer(nomes.get(i), cores.get(i)) == false){
                api.resetPlayers();
                return false;
            };
            // cont++;
        }
        if(api.startGame()) {
            view.determinaPrimeiroJogador(api.getNomeJogadorVez(0), api.getCorJogadorVez(0));
            return true;
        }

        return false;
    }

    //init deck cartas obj(chamar na view do map)
    public void showObjCards() {
        api.initDeckObjective();
        api.shuffleObjectives(api.getAllPlayers(), api.getDeckCardObjective());
        System.out.println("CLICKED");
        
    }

    public int getQuantidadeTerritoriosJogador(PlayerColor corDoJogador) {
        return API.getInstance().getQuantidadeTerritoriosJogador(corDoJogador);
    }


    // Pegar o nome dos jogadores
    public String[] getNomesJogadores(){
        return api.getNomesJogadores();
    }

    // Método que retorna a lista de territórios do jogo
    public String[] getTerritoriosLista(){
        return api.getTerritoriosLista();
    }

    
     // Método chamado quando ocorre o clique na bolinha
     public void incrementarExercitos(String territorio, int count) {
        api.incrementarQntExTerritorio(territorio, count);
    }

    public Integer getQtdExercitos(String t){
        return api.getQntExTerritorio(t);
    }

    // Método que retorna a cor de um território
    public PlayerColor getCorTerritorio(String t){
        return api.getCorTerritorio(t);
    }

    public void verifyWin() {
        if (api.verifica_vez_jogador_objetivo()) {
            view.showWin(api.getNomeJogadorVez(turn));
        }
    }

    // public void clicouPosicionar(String territory, Integer qtd){
    //     if (state == 0) {
    //         this.podeSalvar = true;

    //         api.placeArmy(state, territory); //verificar essa func
    //         verifyWin();
    //         Integer qtdExercitos = api.getQtdExercitosPosic(turn);
    //         if(qtdExercitos == 0){
    //             if (continent < 6) {
    //                 // Atualiza a view para posicionar no próximo continente se for dominado
    //                 return;
    //             }
    //             if (continent == 6) {
    //                 // Se já posicionou em todos os continentes, posiciona no resto dos territórios
    //                 return;
    //             }
    //             // Se não tiver mais exércitos para posicionar
    //             // Zera contador de continentes
    //             continent = 0;
    //             if (firstRound) {
    //                 // turn = (turn + 1) % api.getQtdPlayers(); 
    //                 // Se for a primeira rodada, só pode posicionamento para todos
    //                 return;
    //             }
    //             // Atualiza a view para ataque (implementar codigo embaixo)
    //             return;
    //         }
    //         // Se ainda tiver exércitos para posicionar
    //         // apiView.atualizaQtdPosic(qtdEx);
    //         return;
    //     }
    // }

    // public void clicouFinalizarJogada() {
    //     if (firstRound) {
    //         turn = (turn + 1) % api.getNumPlayers();
    //         view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));

    //         if (turn == 0){
    //             firstRound = false;
    //         }
    //     }
    // }

    public void clicouContinuar() {
        if (firstRound) {
            turn = (turn + 1) % api.getNumPlayers();
            view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));

            if (turn == 0){
                firstRound = false;
            }
        }
    }

    // public void clicouFinalizarJogada() {
    //     // Verifica se é a primeira rodada
    //     if (firstRound) {
    //         // Muda para o próximo jogador na primeira rodada
    //         turn = (turn + 1) % api.getNumPlayers();
    //         view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
    
    //         // Se todos os jogadores já posicionaram na primeira rodada, inicia a segunda rodada
    //         if (turn == 0) {
    //             firstRound = false;
    //             state = STATE_POSICIONAMENTO;  // Inicia a segunda rodada de posicionamento
    //             view.iniciaNovaRodada(turn + 1);
    //         }
    //     } else {
    //         if (state == STATE_POSICIONAMENTO) {
    //             // Muda para o próximo jogador na fase de posicionamento
    //             turn = (turn + 1) % api.getNumPlayers();
    //             view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
    
    //             // Se todos os jogadores posicionaram na rodada atual, inicia o estado de ataque
    //             if (turn == 0) {
    //                 state = STATE_ATAQUE;
    //                 view.iniciaAtaque(currentRound);  // Inicia a rodada de ataques
    //             }
    //         } else if (state == STATE_ATAQUE) {
    //             // Muda para o próximo jogador na fase de ataque
    //             turn = (turn + 1) % api.getNumPlayers();
    //             view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
    
    //             // Se todos os jogadores realizaram os ataques, inicia o estado de realocação
    //             if (turn == 0) {
    //                 state = STATE_REALOCACAO;
    //                 view.iniciaRealocacao();
    //             }
    //         } else if (state == STATE_REALOCACAO) {
    //             // Muda para o próximo jogador na fase de realocação
    //             turn = (turn + 1) % api.getNumPlayers();
    //             view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
    
    //             // Se todos os jogadores realizaram a realocação, inicia uma nova rodada de posicionamento
    //             if (turn == 0) {
    //                 state = STATE_POSICIONAMENTO;
    //                 view.iniciaNovaRodada(turn + 1);
    //             }
    //         }
    //     }
    // }
    
    
    
    

    // Singleton
    public static APIController getInstance(){  
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }   
}
