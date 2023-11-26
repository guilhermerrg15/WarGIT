package View;

// import Controller.TabuleiroObservador;

public class ViewAPI {
    private static ViewAPI apiInstance = null;
    private String imagesPath = "resources/imagens";

    // Singleton
    StartView start = StartView.getStartingPanel();

    public ViewAPI() {
    }

    public static ViewAPI getInstance() {
        if (apiInstance == null) {
            apiInstance = new ViewAPI();
        }
        return apiInstance;
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
