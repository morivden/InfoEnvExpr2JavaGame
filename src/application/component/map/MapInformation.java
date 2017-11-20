package application.component.map;

import java.net.URL;

public enum MapInformation {
    STAGE1(ClassLoader.getSystemResource("map/stage1.dat")),
    STAGE2(ClassLoader.getSystemResource("map/stage2.dat")),
    STAGE3(ClassLoader.getSystemResource("map/stage3.dat"));

    private final URL url;  // ステージのリソース位置

    MapInformation(URL fileURL) {
        this.url = fileURL;
    }

    /**
     * リソースのURLを取得
     *
     * @return
     */
    public URL getURL() {
        return url;
    }

    /**
     * 対応する番号を取得
     *
     * @return 連番
     */
    public int getNumber() {
        return ordinal() + 1;
    }
}
