package main.Shared;

/**
 * Created by Dora on 4/20/2017.
 */
public class UrlLoader {

    private static final String RESOURCE_VIEW = "../resource/view/";
    private static final String RESOURCE_ASSET = "../resource/asset/";

    public static String loadView(String viewName) {
        return RESOURCE_VIEW + viewName;
    }

    public static String loadAsset(String assetName) {
        return RESOURCE_ASSET + assetName;
    }

}
