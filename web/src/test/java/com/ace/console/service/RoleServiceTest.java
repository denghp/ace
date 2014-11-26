package com.ace.console.service;

import com.ace.commons.json.JsonUtils;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.RoleService;
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author: denghp
 * @Date: 14-11-26
 * @Time: 下午5:11
 * @Description:
 */
public class RoleServiceTest extends AbstractTest {

    private Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);

    @Resource
    private RoleService roleService;

    @Test
    public void selectUserPages() throws JsonProcessingException {
        int pageNum = 1;
        int limit = 3;
        while(true) {
            PageList<Role> userPageList = roleService.page(null, pageNum, limit);
            if (userPageList == null || userPageList.isEmpty()) {
                break;
            }
            logger.info("totalCount : " + userPageList.getPaginator().getTotalCount());
            for (Role role : userPageList) {
                logger.info("user1 : {}", JsonUtils.getObjectMapper().writeValueAsString(role));
            }

            pageNum = userPageList.getPaginator().getNextPage();
            logger.info("page : " + pageNum);
            if (!userPageList.getPaginator().isHasNextPage()) {
                break;
            }
        }
    }

    @Test
    public void saveRRP() throws AceException {
        Long[] resourceIds = new Long[] {47l,48l,49l};
        Role role = new Role();
        role.setId(6l);
        roleService.updateWithResourcePermission(resourceIds,role);
    }

}
