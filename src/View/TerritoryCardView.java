package View;

import java.awt.*;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import Model.TerritoryCard;

class TerritoryCardView {

	//Construtor
	public TerritoryCardView(){

		
	}

	//Desenha as imagens dos dados
	static public void drawTerritoryCard(Graphics2D g2d, List<TerritoryCard> cards) {

		int dimensionWidth = 150;
        int dimensionHeight = 220;
		int diceSpacing = 20;

        for(int i=0; i<cards.size(); i++){
            g2d.drawImage(getImage(cards.get(i)),100 + (dimensionWidth + diceSpacing)*i,200,dimensionWidth,dimensionHeight,null);
        }
	}

    static private String generateName(TerritoryCard card){
        String continentPath = "";
        if (card.getContinent() != null){
            continentPath = card.getContinent()+"_";
        }
        return "resources/imagens/war_carta_" + continentPath + card.getImageName() + ".png";
    }

    static private Image getImage(TerritoryCard card){
        String imagePath = generateName(card);
        System.err.println(imagePath);
        try {
            return (ImageIO.read(new File(imagePath)));
        } catch (Exception e) {
            System.out.println("Nao foi possivel carregar a imagem dos cartões de territórios");
            return null;
        }
    } 
}
