package model;

import java.util.ArrayList;
import java.util.List;

//Clase que utilizare para el manejo de los numeros binarios
//Defino las longitudes, el maxcero,mincero,maxone y minone a traves de las mismas
// min 1 , max 8

public class Bit {


    private List<Integer> lengths = new ArrayList<>();
    private Integer minLenght;
    private Integer maxLenght;

    public List<Integer> getLengths() {
        return lengths;
    }

    public void setLengths(List<Integer> lengths) {
        this.lengths = lengths;
    }

    public Integer getMinLenght() {
        return minLenght;
    }

    public void setMinLenght(Integer minLenght) {
        this.minLenght = minLenght;
    }

    public Integer getMaxLenght() {
        return maxLenght;
    }

    public void setMaxLenght(Integer maxLenght) {
        this.maxLenght = maxLenght;
    }

    public Bit() {
        this.minLenght = 1;
        this.maxLenght = 8;
    }

}

