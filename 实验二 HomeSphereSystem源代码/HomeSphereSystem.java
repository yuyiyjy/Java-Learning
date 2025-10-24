package cn.edu.nwpu.homesphere;
import java.util.List;
import java.util.Date;

/**
 * 智能家居核心系统类，负责管理家庭、用户及设备，提供登录注册、信息展示、场景触发等核心功能。
 * <p>
 * 该类作为系统入口，整合家庭（Household）资源，支持用户操作（登录/注册）、设备与场景管理，
 * 以及能耗统计等智能家居核心业务场景。
 *
 * @author 余燚
 * @version 1.0
 */
public class HomeSphereSystem {
    /**
     * 系统当前管理的家庭对象，包含家庭下的用户、房间、设备及场景信息
     */
    private Household household;

    /**
     * 系统当前登录的用户，未登录时为null
     */
    private User currentUser;

    /**
     * 无参构造方法，创建一个默认的智能家居系统实例
     * <p>
     * 注意：使用此构造后需手动关联家庭（Household）对象，否则无法使用用户、设备相关功能
     */
    public HomeSphereSystem() {

    }

    /**
     * 有参构造方法，创建关联指定家庭的智能家居系统实例
     *
     * @param household 系统需管理的家庭对象（{@link Household}）
     */
    public HomeSphereSystem(Household household) {
        this.household = household;
    }

    /**
     * 获取系统当前管理的家庭对象
     *
     * @return 家庭对象（{@link Household}），未关联时为null
     */
    public Household getHousehold() {
        return household;
    }

    /**
     * 获取系统当前登录的用户
     *
     * @return 当前登录用户（{@link User}），未登录时为null
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * 用户登录功能，验证用户名密码并设置当前登录用户
     * <p>
     * 遍历系统管理家庭下的所有用户，匹配用户名（loginName）和密码（loginPassword）；
     * 匹配成功则打印“登录成功”并更新currentUser，匹配失败则打印“系统中没有该用户，请先注册”。
     *
     * @param loginName  用户登录名
     * @param loginPassword 用户登录密码
     */
    public void login(String loginName, String loginPassword) {
        for (User user : household.getUsers()) {
            if (user.getLoginName().equals(loginName) && user.getLoginPassword().equals(loginPassword)) {
                System.out.println("登录成功！");
                currentUser = user;
            } else {
                System.out.println("系统中没有该用户，请先注册！");
            }
        }
    }

    /**
     * 用户退出登录功能，清空当前登录用户
     * <p>
     * 将currentUser设为null，并打印“已退出登录”提示信息。
     */
    public void logoff() {
        this.currentUser = null;
        System.out.println("已退出登录");
    }

    /**
     * 用户注册功能，生成新用户并返回
     * <p>
     * 先获取当前所有用户中最后一位用户的编号，在最后一位用户的编号上加1即为新用户的编号
     * 并将新用户添加到家庭列表中
     *
     * @param loginName  新用户登录名
     * @param loginPassword 新用户登录密码
     * @param userName   新用户昵称/真实姓名
     * @param email      新用户邮箱
     * @return 新创建的用户对象（{@link User}）
     */
    User register(String loginName, String loginPassword, String userName, String email) {

       List<User> users = household.getUsers();
       User lastUser;
       int lastId;

       if(!users.isEmpty()){
           lastUser = users.get(users.size()-1);
           lastId = lastUser.getUserId();
       }
       else{
           lastId = 0;
       }

       int newId = lastId + 1;

        User newUser = new User(newId, loginName, loginPassword, userName, email);
        household.getUsers().add(newUser);

        return newUser;

    }

    /**
     * 展示系统管理家庭下的所有用户信息
     * <p>
     * 遍历家庭的用户列表，打印每个用户的详细信息（依赖{@link User#toString()}方法）。
     */
    void displayUsers(){
        for (User user : household.getUsers()) {
            System.out.println(user);
        }
    }

    /**
     * 展示系统管理家庭下的所有房间信息
     * <p>
     * 遍历家庭的房间列表，打印每个房间的详细信息（依赖{@link Room#toString()}方法）。
     */
    void displayRooms(){
        for (Room room : household.getRooms()) {
            System.out.println(room);
        }
    }

    /**
     * 展示系统管理家庭下的所有设备信息
     * <p>
     * 先通过家庭的{@link Household#listAllDevices()}获取所有设备，再遍历打印每个设备的详细信息（依赖{@link Device#toString()}方法）。
     */
    void displayDevices(){
        List<Device> allDevices = this.household.listAllDevices();
        for (Device device : allDevices) {
            System.out.println(device);
        }
    }

    /**
     * 展示系统管理家庭下的所有自动化场景信息
     * <p>
     * 遍历家庭的自动化场景列表，打印每个场景的详细信息（依赖{@link AutomationScene#toString()}方法）。
     */
    void displayAutoScenes(){
        List<AutomationScene> allScenes = this.household.getAutoScenes();
        for (AutomationScene scene : allScenes) {
            System.out.println(scene);
        }
    }

    /**
     * 展示指定时间段内，家庭中空调（AirConditioner）和灯泡（LightBulb）的能耗情况
     * <p>
     * 遍历家庭所有设备，筛选出空调和灯泡类型的设备，调用其{@link EnergyReporting#getReport(Date, Date)}方法计算能耗并打印。
     *
     * @param startTime 能耗统计的开始时间
     * @param endTime   能耗统计的结束时间
     */
    void displayEnergyReportings(Date startTime, Date endTime){
        List<Device> allDevices = this.household.listAllDevices();
        for (Device device : allDevices) {
            if(device instanceof AirConditioner){
                System.out.println("设备+"+device.getName()+"在这段时间的能耗为："+((AirConditioner)device).getReport(startTime,endTime)+"度");
            }
            else if(device instanceof LightBulb){
                System.out.println("设备+"+device.getName()+"在这段时间的能耗为："+((LightBulb)device).getReport(startTime,endTime)+"度");
            }
        }
    }

    /**
     * 根据场景ID手动触发指定的自动化场景
     * <p>
     * 遍历家庭的自动化场景列表，找到场景ID匹配的场景，调用其{@link AutomationScene#manualTrig()}方法执行场景动作。
     *
     * @param sceneId 需触发的自动化场景编号
     * @throws NoSuchMethodException 若场景执行动作时出现方法调用异常（依赖{@link AutomationScene#manualTrig()}的异常抛出）
     */
    void manualTrigSceneById(int sceneId) throws NoSuchMethodException {
        List<AutomationScene>  allScenes = this.household.getAutoScenes();
        for (AutomationScene scene : allScenes) {
            if(scene.getSceneId() == sceneId){
                scene.manualTrig();
            }
        }
    }

}