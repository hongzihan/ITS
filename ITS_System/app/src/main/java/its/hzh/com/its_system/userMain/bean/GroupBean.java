package its.hzh.com.its_system.userMain.bean;

/**
 * 站台数据
 * Created by ken on 2018/3/15.
 */

public class GroupBean {
    private String title ; //部门名称


    /**
     * @param title 部门名称
     */
    public GroupBean(String title) {
        this.title = title;
    }

    //获取部门名称
    public String getTitle() {
        return title;
    }

    //设置部门名称
    public void setTitle(String title) {
        this.title = title;
    }
}
