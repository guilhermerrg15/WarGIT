package View;


import javax.swing.JOptionPane;

import Controller.APIController;
import Model.PlayerColor;

// import Controller.TabuleiroObservador;

public class ViewAPI {
    private static ViewAPI apiInstance = null;
    private String imagesPath = "resources/imagens";

    // Array de nomes dos jogadores
	String[] nomesJogadores = new String[6];

    // Singleton
    StartView start = StartView.getStartView();

    // Singleton
    APIController controller = APIController.getInstance();

    // Pegar primeiro jogador
    public void determinaPrimeiroJogador(String nome, PlayerColor cor){
		MapView.getMapView().determinaPrimeiroJogador(nome, cor);
	}
    //Muda o jogador atual
	public void mudaJogador(String jogador, PlayerColor cor){
		MapView.getMapView().mudaJogador(jogador, cor);
	}

    //Atalixa os territorios atancantes no painel de ataque
	public void atualizaAtacantes(String[] atacantes){
		// MapView.getMapView().mudaParaAtaque();
		MapView.getMapView().atualizaAtacantes(atacantes);
	}

    //Atualiza os territorios defensores no painel de ataque
	public void atualizaDefensores(String[] defensores){
		MapView.getMapView().atualizaDefensores(defensores);
	}

    private ViewAPI() {
    }

    public void exibirNomeTerritorio(String territorioNome, PlayerColor corDoTerritorio) {
        // Converte a cor do território para um formato mais legível
        String corFormatada = corDoTerritorio.toString().toLowerCase();
    
        // Exibe um JOptionPane com o nome e a cor do território
        JOptionPane.showMessageDialog(null, "Território: " + territorioNome + "\nCor: " + corFormatada, "Informação do Território", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void iniciaNovaRodada(int round) {
        // Exibe uma mensagem informando o início de uma nova rodada
        JOptionPane.showMessageDialog(null, "Início da Rodada " + round, "Nova Rodada", JOptionPane.INFORMATION_MESSAGE);

        // Adicione aqui qualquer lógica específica que você deseja executar no início de uma nova rodada na interface gráfica.
        // Isso pode incluir atualizações visuais, reinicialização de contadores, etc.
    }
    public void iniciaAtaque(int round) {
        // Exibe uma mensagem informando o início de uma nova rodada
        JOptionPane.showMessageDialog(null, "Ataque " + round, "Nova Rodada", JOptionPane.INFORMATION_MESSAGE);

        // Adicione aqui qualquer lógica específica que você deseja executar no início de uma nova rodada na interface gráfica.
        // Isso pode incluir atualizações visuais, reinicialização de contadores, etc.
    }

    public void iniciaRealocacao() {
        // Exibe uma mensagem informando o início da fase de realocação
        JOptionPane.showMessageDialog(null, "Início da Fase de Realocação", "Realocação", JOptionPane.INFORMATION_MESSAGE);

        // Adicione aqui qualquer lógica específica que você deseja executar no início da fase de realocação na interface gráfica.
        // Isso pode incluir atualizações visuais, instruções para o jogador, etc.
    }
    

    public static ViewAPI getInstance() {
        if (apiInstance == null) {
            apiInstance = new ViewAPI();
        }
        return apiInstance;
    }

    public Observer getObserver() {
        return MapView.getMapView();
    }

    //Retorna os nomes dos jogadores
	public String[] getNomesJogadores(){
		return nomesJogadores;
	}

    // Fim de jogo
    public void showWin(String nome) {

        JOptionPane.showMessageDialog(null, nome + " ganhou o jogo!", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			APIController.getInstance().reiniciarJogo();
		} else {
			System.exit(0);
		}
    }

    // public void initGame() {
    //     new JanelaInicial();
    // }

    // public String getImagesPath() {
    //     return this.imagesPath;
    // }

    // public void redraw() {
    //     // TODO
    // }
    // public TabuleiroObservador getTabuleiroObservador() {
    //     return null;
    }
