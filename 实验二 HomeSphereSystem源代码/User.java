package cn.edu.nwpu.homesphere;

/**
 * 用户类，用于封装智能家居系统中的用户信息及权限标识，是系统登录、权限控制的核心数据载体。
 * <p>
 * 该类管理用户的唯一标识、登录凭证（账号/密码）、个人信息（昵称/邮箱）及管理员权限状态，
 * 支持用户信息的查询与更新，通过重写{@code equals}方法实现基于用户ID的身份唯一性判断。
 *
 * @author 余燚
 * @version 1.0
 */
public class User {
    /**
     * 用户唯一编号（系统随机分配，不可修改，用于区分不同用户）
     */
    private int userId;

    /**
     * 用户登录账号（系统内唯一，用于登录验证，可修改）
     */
    private String loginName;

    /**
     * 用户登录密码（用于登录验证，建议存储加密后的值，支持修改）
     */
    private String loginPassword;

    /**
     * 用户昵称（展示用名称，非登录账号，可修改）
     */
    private String userName;

    /**
     * 用户邮箱（用于账号找回、通知推送等，可修改）
     */
    private String email;

    /**
     * 用户管理员权限标识：{@code true}表示拥有管理员权限，{@code false}表示普通用户
     */
    private boolean isAdmin;

    /**
     * 无参构造方法，创建一个默认的用户实例
     * <p>
     * 注意：使用此构造后需通过有参构造或手动设置核心字段（如用户ID、登录账号），否则用户信息不完整。
     */
    public User() {

    }

    /**
     * 有参构造方法，创建包含完整信息的用户实例
     * <p>
     * 管理员权限（isAdmin）默认未设置，需通过{@link #setAdmin(boolean)}手动配置（如家庭设置管理员时调用）。
     *
     * @param userId        用户唯一编号（系统分配）
     * @param loginName     用户登录账号
     * @param loginPassword 用户登录密码
     * @param userName      用户昵称
     * @param email         用户邮箱
     */
    User(int userId, String loginName, String loginPassword, String userName, String email) {
        this.userId = userId;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.userName = userName;
        this.email = email;
    }

    /**
     * 获取用户昵称
     *
     * @return 用户昵称（展示用字符串）
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 修改用户昵称
     *
     * @param userName 新的用户昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户邮箱
     *
     * @return 用户邮箱地址（字符串）
     */
    public String getEmail() {
        return email;
    }

    /**
     * 修改用户邮箱
     *
     * @param email 新的用户邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户登录密码
     * <p>
     * 注意：若密码存储为加密格式，返回的是加密后的值，需解密后使用。
     *
     * @return 用户登录密码（字符串）
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 修改用户登录密码
     * <p>
     * 建议传入加密后的密码字符串，避免明文存储导致安全风险。
     *
     * @param loginPassword 新的登录密码（建议加密后传入）
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * 获取用户登录账号
     *
     * @return 用户登录账号（字符串）
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 修改用户登录账号
     * <p>
     * 需确保新账号在系统内唯一，避免与其他用户账号冲突（需调用者自行判断唯一性）。
     *
     * @param loginName 新的用户登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户唯一编号
     *
     * @return 用户编号（int类型，系统分配，不可修改）
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 判断用户是否拥有管理员权限
     *
     * @return {@code true}表示管理员，{@code false}表示普通用户
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * 设置用户的管理员权限状态
     * <p>
     * 通常由系统管理员或家庭管理员操作，用于分配/回收管理权限（如家庭设置管理员时调用）。
     *
     * @param admin 目标权限状态：{@code true}设为管理员，{@code false}设为普通用户
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * 重写equals方法，判断两个用户是否为同一用户（基于用户唯一编号匹配）
     * <p>
     * 忽略其他字段（如昵称、邮箱）的差异，仅通过userId确保身份唯一性。
     *
     * @param o 待比较的对象
     * @return {@code true}若为同一用户（userId相同），否则返回{@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId;
    }

    /**
     * 重写toString方法，返回用户的完整信息字符串
     * <p>
     * 包含用户ID、登录账号、密码（注意：若为明文密码会存在安全风险，建议实际使用时隐藏或脱敏）、昵称、邮箱及权限状态。
     *
     * @return 包含用户所有字段信息的字符串
     */
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ",loginName='" + loginName + '\'' +
                ",loginPassword='"+ loginPassword + '\'' + ",userName='" + userName + '\'' +
                ",email='" + email + '\'' + "isAdmin="+ isAdmin ;
    }

}
