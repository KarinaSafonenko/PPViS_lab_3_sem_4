import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Admin on 31.05.2017.
 */
public class Points {

    List<Double> xList;
    List<Double> yList;

    public Points (){
        xList = new ArrayList<>();
        yList = new ArrayList<>();
    }

    public void addX(Double point){
        xList.add(point);
    }

    public void addY(Double point){
        yList.add(point);
    }

    public int getSizeX(){
        return xList.size();
    }

    public List<Double> getxList() {
        return xList;
    }

    public List<Double> getyList() {
        return yList;
    }

}
