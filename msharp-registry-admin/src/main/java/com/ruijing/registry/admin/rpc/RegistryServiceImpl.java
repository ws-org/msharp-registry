package com.ruijing.registry.admin.rpc;

import com.ruijing.registry.admin.data.model.RegistryNodeDO;
import com.ruijing.registry.admin.enums.RegistryNodeStatusEnum;
import com.ruijing.registry.admin.service.RegistryManagerService;
import com.ruijing.registry.admin.util.JsonUtil;
import com.ruijing.registry.admin.util.MetaUtil;
import com.ruijing.registry.api.dto.NodeMetaDTO;
import com.ruijing.registry.api.service.RegistryService;
import com.ruijing.registry.api.dto.RegistryNodeDTO;
import com.ruijing.registry.api.dto.RegistryNodeQueryDTO;
import com.ruijing.registry.api.request.Request;
import com.ruijing.registry.api.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@MSharpService(registry = "direct", port = 42000)
@Service
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    private RegistryManagerService registryService;

    @Override
    public Response<List<String>> discovery(RegistryNodeQueryDTO query) {
        Response<List<String>> response = registryService.discovery(query);
        return response;
    }

    @Override
    public Response<Map<String, List<String>>> discovery(Request<RegistryNodeQueryDTO> request) {
        Response<Map<String, List<String>>> result = registryService.discovery(request);
        return result;
    }

    @Override
    public Response<Boolean> renew(Request<RegistryNodeDTO> request) {
        List<RegistryNodeDTO> registryNodeList = request.getList();
        final List<RegistryNodeDO> registryNodeDOList = new ArrayList<>(registryNodeList.size());
        for (int i = 0, size = registryNodeList.size(); i < size; i++) {
            RegistryNodeDTO node = registryNodeList.get(i);
            NodeMetaDTO meta = JsonUtil.fromJson(node.getMeta(), NodeMetaDTO.class);
            RegistryNodeDO registryNodeDO = new RegistryNodeDO();
            registryNodeDO.setServiceName(node.getServiceName());
            registryNodeDO.setStatus(RegistryNodeStatusEnum.NORMAL.getCode());
            registryNodeDO.setValue(MetaUtil.convert(meta));
            registryNodeDO.setAppkey(node.getAppkey());
            registryNodeDO.setEnv(node.getEnv());
            registryNodeDO.setMetric(null == node.getMetric() ? StringUtils.EMPTY : node.getMetric());
            registryNodeDO.setMeta(null == node.getMeta() ? StringUtils.EMPTY : node.getMeta());
            registryNodeDO.setVersion(null == node.getVersion() ? StringUtils.EMPTY : node.getVersion());
            registryNodeDOList.add(registryNodeDO);
        }
        return registryService.registry(registryNodeDOList);
    }
}