package model.Input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.Bit;

import javax.validation.constraints.NotNull;

public class InputText {

    @NotNull
    private String text;

    @JsonIgnore
    private Bit spaceStats;

    public InputText () {};
    public InputText (String text) { this.text = text;}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bit getSpaceStats() {
        return spaceStats;
    }

    public void setSpaceStats(Bit spaceStats) {
        this.spaceStats = spaceStats;
    }

    public boolean areSpaceStatsValid() {
        return spaceStats != null && spaceStats.getMinLenght() != null && spaceStats.getMaxLenght() != null;
    }
}
