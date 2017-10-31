package application.component.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapFactory {
    public static Map createMap(int stageNum) {
        readMapDate(MapInfomation.STAGE1);
        return null;
    }

    private static char[][] readMapDate(MapInfomation mi) {
        char[][] mapData;
        String resource = mi.getURL().getPath();  // リソース位置

        List<String> inputString = new ArrayList();

        /// 読み込み準備
        FileReader fReader = null;
        try {
            fReader = new FileReader(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bReader = new BufferedReader(fReader);

        /// ファイル情報の読み込み
        inputString = bReader.lines().collect(Collectors.toList());

        /// 生成マップの情報作成
        // 縦横サイズの算出
        int mapRow = inputString.size();
        int mapCol = inputString.stream().min((a, b) -> Integer.compare(a.length(), b.length())).orElse("").length();

        System.out.println(mapRow + " " + mapCol);
        return null;
    }
}
