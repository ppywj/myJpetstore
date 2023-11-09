package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author pp
 */
public class recommend implements Serializable {
    private ArrayList<String> recommendList;

    public recommend(ArrayList<String> recommendList) {
        this.recommendList = recommendList;
    }

    public recommend() {
    }


    public ArrayList<String> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(ArrayList<String> recommendList) {
        this.recommendList = recommendList;
    }
}