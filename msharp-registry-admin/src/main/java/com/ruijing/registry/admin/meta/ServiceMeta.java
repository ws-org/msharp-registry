package com.ruijing.registry.admin.meta;

/**
 * ServiceMeta
 * 注册中心元数据
 *
 * @author mwup
 * @version 1.0
 * @created 2018/12/03 12:32
 **/
public class ServiceMeta {

    /**
     * 服务端项目名称
     */
    private String appkey;

    /**
     * ip
     */
    private String ip;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 版本号
     */
    private String version;

    /**
     * 版本号
     */
    private String group;

    /**
     * 端口
     */
    private int port;

    /**
     * 状态值
     */
    private int status;

    /**
     * 权重
     */
    private double weight = 10d;

    public ServiceMeta() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceMeta{");
        sb.append("appkey='").append(appkey).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", port=").append(port);
        sb.append(", status=").append(status);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}