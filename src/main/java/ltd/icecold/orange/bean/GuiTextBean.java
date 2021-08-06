package ltd.icecold.orange.bean;

import java.util.List;

public class GuiTextBean {
    private int x;
    private int y;
    private Double scale;
    private List<String> text;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
