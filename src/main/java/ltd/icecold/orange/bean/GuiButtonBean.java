package ltd.icecold.orange.bean;

import com.google.common.collect.Maps;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.List;
import java.util.Map;

public class GuiButtonBean implements ConfigurationSerializable {
    private int id;
    private String name;
    private String url;
    private String url2;
    private int x;
    private int y;
    private int width;
    private int high;
    private List<String> commands;
    private boolean asop;
    private boolean close;
    private String to;
    private String click_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public boolean isAsop() {
        return asop;
    }

    public void setAsop(boolean asop) {
        this.asop = asop;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public static GuiButtonBean deserialize(Map<String, Object> data){
        GuiButtonBean guiButtonBean = new GuiButtonBean();
        guiButtonBean.id=(int)data.get("id");
        guiButtonBean.name=(String)data.get("name");
        guiButtonBean.url=(String)data.get("url");
        guiButtonBean.url2=(String)data.get("url2");
        guiButtonBean.x=(int)data.get("x");
        guiButtonBean.y=(int)data.get("y");
        guiButtonBean.width=(int)data.get("width");
        guiButtonBean.high=(int)data.get("high");
        guiButtonBean.commands=(List<String>)data.get("commands");
        guiButtonBean.asop=(boolean)data.get("asop");
        guiButtonBean.close=(boolean)data.get("close");
        guiButtonBean.to=(String)data.get("to");
        guiButtonBean.click_url=(String)data.get("click_url");
        return guiButtonBean;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> data = Maps.newHashMap();
        data.put("id",id);
        data.put("name",name);
        data.put("url",url);
        data.put("url0",url2);
        data.put("x",x);
        data.put("y",y);
        data.put("width",width);
        data.put("high",high);
        data.put("commands",commands);
        data.put("asop",asop);
        data.put("close",close);
        data.put("to",to);
        data.put("click_url",click_url);
        return null;
    }
}
