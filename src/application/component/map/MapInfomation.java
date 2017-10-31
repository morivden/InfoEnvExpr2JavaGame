package application.component.map;

import java.net.URL;

public enum MapInfomation {
    STAGE1(ClassLoader.getSystemResource("map/stage1.dat"));

    private final URL url;  // ステージのリソース位置

    MapInfomation(URL fileURL) {
        this.url = fileURL;
    }

    public URL getURL() {
        return url;
    }
}
