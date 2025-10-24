package cn.edu.nwpu.homesphere;

import java.util.List;
import java.util.ArrayList;

/**
 * 家庭类，用于封装智能家居系统中的家庭信息及关联资源，是用户、房间、智能场景的聚合容器。
 * <p>
 * 该类提供家庭资源的管理能力，包括用户增删、房间管理、智能场景维护，以及统一获取家庭所有设备的功能。
 *
 * @author 余燚
 * @version 1.0
 */
public class Household {
    /**
     * 家庭唯一编号（不可修改，用于家庭身份标识）
     */
    private int householdId;

    /**
     * 家庭的具体地址（如“XX市XX区XX小区XX号楼XX单元”）
     */
    private String address;

    /**
     * 家庭管理员用户（拥有家庭资源的最高管理权限）
     */
    private User admin;

    /**
     * 家庭内所有用户的集合（包含管理员和普通成员），初始化为空列表
     */
    private List<User> users = new ArrayList<User>();

    /**
     * 家庭内所有房间的集合（如客厅、卧室、厨房等），初始化为空列表
     */
    private List<Room> rooms = new ArrayList<Room>();

    /**
     * 家庭内所有自动化场景的集合（如“早安场景”“离家模式”等），初始化为空列表
     */
    private List<AutomationScene> autoScenes = new ArrayList<AutomationScene>();

    /**
     * 无参构造方法，创建一个默认的家庭实例
     * <p>
     * 注意：使用此构造后需手动设置家庭编号、地址等核心属性，否则无法完整使用家庭管理功能。
     */
    public Household(){

    }

    /**
     * 有参构造方法，创建指定编号和地址的家庭实例
     *
     * @param householdId 家庭唯一编号
     * @param address     家庭具体地址
     */
    public Household(int householdId, String address) {
        this.householdId = householdId;
        this.address = address;
    }

    /**
     * 获取家庭唯一编号
     *
     * @return 家庭编号（int类型）
     */
    public int getHouseholdId() {
        return householdId;
    }

    /**
     * 获取家庭地址
     *
     * @return 家庭地址（字符串）
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置家庭地址
     *
     * @param address 新的家庭地址（如搬家后更新地址）
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取家庭管理员
     *
     * @return 管理员用户（{@link User}对象），未设置时为null
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * 设置家庭管理员，并自动将该用户的管理员标识设为true
     * <p>
     * 此方法会同步修改参数用户的{@code isAdmin}属性，确保管理员身份与用户状态一致。
     *
     * @param admin 待设为管理员的用户（{@link User}对象）
     */
    public void setAdmin(User admin) {
        this.admin = admin;
        admin.setAdmin(true);
    }

    /**
     * 向家庭添加一个房间
     *
     * @param room 待添加的房间（{@link Room}对象）
     */
    public void addRoom(Room room){
        rooms.add(room);
    }

    /**
     * 根据房间编号从家庭中移除指定房间
     * <p>
     * 通过房间编号匹配目标房间，移除成功则打印提示；未找到对应房间则提示操作失败。
     *
     * @param roomId 待移除房间的编号
     */
    public void removeRoom(int roomId){
        int originalSize = this.rooms.size();
        rooms.removeIf(room -> room.getRoomId() == roomId);

        if (originalSize > rooms.size()) {
            System.out.println("成功移除房间号为" + roomId + "的房间");
        } else {
            System.out.println("未找到房间号为" + roomId + "的设备，操作执行失败");
        }
    }

    /**
     * 获取家庭内所有房间的集合
     * <p>
     * 若家庭内无房间，会打印提示信息并返回null；否则返回房间列表。
     *
     * @return 房间列表（{@link List}<{@link Room}>），无房间时返回null
     */
    public List<Room> getRooms() {
        if(this.rooms.isEmpty())
        {
            System.out.println("家庭内还没有房间，请添加！");
            return null;
        }
        else{
            return this.rooms;
        }
    }

    /**
     * 向家庭添加一个家庭成员（用户）
     *
     * @param user 待添加的家庭成员（{@link User}对象）
     */
    public void addUser(User user){
        users.add(user);
    }

    /**
     * 根据用户编号从家庭中移除指定家庭成员
     * <p>
     * 通过用户编号匹配目标用户，移除成功则打印提示；未找到对应用户则提示操作失败。
     *
     * @param userId 待移除用户的编号
     */
    public void removeUser(int userId){
        int originalSize = this.users.size();
        users.removeIf(user -> user.getUserId() == userId);

        if (originalSize > users.size()) {
            System.out.println("成功移除编号为为" + userId + "的用户");
        } else {
            System.out.println("未找到编号号为" + userId + "的用户，操作执行失败");
        }
    }

    /**
     * 获取家庭内所有用户的集合
     * <p>
     * 若家庭内无用户，会打印提示信息并返回null；否则返回用户列表。
     *
     * @return 用户列表（{@link List}<{@link User}>），无用户时返回null
     */
    public List<User> getUsers() {
        if(this.users.isEmpty()){
            System.out.println("家庭里还没有用户，请添加！");
            return null;
        }
        else{
            return this.users;
        }
    }

    /**
     * 向家庭添加一个自动化场景
     *
     * @param autoScene 待添加的自动化场景（{@link AutomationScene}对象）
     */
    public void addAutoScene(AutomationScene autoScene){
        autoScenes.add(autoScene);
    }

    /**
     * 根据场景编号从家庭中移除指定自动化场景
     * <p>
     * 通过场景编号匹配目标场景，移除成功则打印提示；未找到对应场景则提示操作失败。
     *
     * @param sceneId 待移除场景的编号（参数名原“aceneId”为笔误，建议修正为“sceneId”）
     */
    public void removeAutoScene(int sceneId){
        int originalSize = this.autoScenes.size();
        autoScenes.removeIf(scene -> scene.getSceneId() == sceneId);

        if (originalSize > autoScenes.size()) {
            System.out.println("成功移除编号为为" + sceneId + "的智能场景");
        } else {
            System.out.println("未找到编号号为" + sceneId + "的智能场景，操作执行失败");
        }
    }

    /**
     * 获取家庭内所有自动化场景的集合
     * <p>
     * 若家庭内无自动化场景，会打印提示信息，返回场景列表。
     *
     * @return 自动化场景列表（{@link List}<{@link AutomationScene}>），无场景时返回null
     */
    public List<AutomationScene> getAutoScenes() {
        if(this.autoScenes.isEmpty()){
            System.out.println("家庭里还没有智能场景，请添加！");
        }

            return this.autoScenes;
    }

    /**
     * 汇总并获取家庭内所有房间的设备集合
     * <p>
     * 遍历家庭的所有房间，收集每个房间内的设备，整合为一个统一的设备列表返回（空房间不会影响结果）。
     *
     * @return 家庭内所有设备的列表（{@link List}<{@link Device}>），无设备时返回空列表
     */
    public List<Device> listAllDevices(){
        List<Device> houseDevices = new ArrayList<Device>();

        for(Room room : this.rooms){
            List<Device> roomDevices = room.getDevices();
            houseDevices.addAll(roomDevices);
        }

        return houseDevices;
    }
}
