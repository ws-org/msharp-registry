package com.ruijing.registry.admin.util;

import com.ruijing.fundamental.cat.Cat;
import com.ruijing.registry.admin.data.query.RegistryQuery;
import com.ruijing.registry.admin.request.Request;
import com.ruijing.registry.client.model.server.RegistryNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RequestUtil
 *
 * @author mwup
 * @version 1.0
 * @created 2019/07/23 17:03
 **/
public class RequestUtil {

    public static Request<RegistryQuery> getClientRequest(String json) {
        final Request request = getRequest(json);
        if (request == null || CollectionUtils.isEmpty(request.getList())) {
            return request;
        }
        final List<Map<String, String>> mapList = request.getList();
        final List<RegistryQuery> queryList = new ArrayList<>(mapList.size());
        for (int i = 0, size = mapList.size(); i < size; i++) {
            final RegistryQuery query = new RegistryQuery();
            final Map<String, String> map = mapList.get(i);
            query.setAppkey(map.get("biz"));
            query.setClientAppkey(map.get("clientAppkey"));
            query.setEnv(map.get("env"));
            query.setServiceName(map.get("key"));
            queryList.add(query);
        }
        request.setList(queryList);
        return request;
    }

    private static Request<RegistryQuery> getRequest(String json) {
        Request request = null;
        try {
            request = JsonUtils.fromJson(json, Request.class);
        } catch (Exception e) {
            Cat.logError("ClientRequestUtil.renew,data:" + json, e);
        }
        return request;
    }

    public static Request<RegistryNode> getServerRequest(String json) {
        final Request request = getRequest(json);
        if (request == null || CollectionUtils.isEmpty(request.getList())) {
            return null;
        }
        List<Map<String, String>> mapList = request.getList();
        List<RegistryNode> nodeList = new ArrayList<>(mapList.size());
        for (int i = 0, size = mapList.size(); i < size; i++) {
            final Map<String, String> map = mapList.get(i);
            final RegistryNode node = new RegistryNode();
            node.setAppkey(map.get("biz"));
            node.setEnv(map.get("env"));
            node.setServiceName(map.get("key"));
            node.setMeta(map.get("meta"));
            node.setValue(map.get("value"));
            node.setClientAppkey(map.get("clientAppkey"));
            nodeList.add(node);
        }
        request.setList(nodeList);
        return request;
    }
}