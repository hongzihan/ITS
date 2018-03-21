package its.hzh.com.its_system.userMain.bean;

/**
 * 公交数据
 * Created by ken on 2018/3/15.
 */

public class ItemBean {

    private String content ;//bus id
    private int img; //图像id
    private String contentTwo;//公交距离显示

    public ItemBean(String content, String contentTwo, int imgId) {
        this.content = content;
        this.contentTwo = contentTwo;
        this.img = imgId;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContentTwo() {
        return contentTwo;
    }

    public void setContentTwo(String contentTwo) {
        this.contentTwo = contentTwo;
    }





    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
