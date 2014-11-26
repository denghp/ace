package com.ace.console.service;

import com.ace.commons.json.JsonUtils;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.UserAuthService;
import com.ace.console.service.sys.UserService;
import com.ace.console.utils.Constants;
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/16/14
 * @Time: 11:50 PM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class UserServiceTest {
    private Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    private UserService userService;

    @Resource
    private UserAuthService userAuthService;

    protected String password = "123456";
    protected User user;

    @Before
    public void init() {
        user = new User("denghp", password);

    }

    @Test
    public void saveUser() throws AceException, IOException {
       User en = userService.save(user);
       logger.info("user : {}", JsonUtils.getObjectMapper().writeValueAsString(en));
       Assert.assertNotNull(user);
    }

    @Test
    public void deleteUser() throws AceException {
        User en = userService.save(user);
        userService.delete(en.getId());
    }

    @Test
    public void selectUser() throws IOException {
        User user = userService.selectById(10004l);
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        Assert.assertNotNull(user);
    }

    @Test    public void selectDetails() throws IOException {
        User user = userService.getUserDetails(1l);
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        Assert.assertNotNull(user);
    }

    @Test
    public void findByUsername() throws IOException {
        User user = userService.getByUsername("admin");
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        User user1 = userService.getByUsername("admin");
        logger.info("user1 : {}",JsonUtils.getObjectMapper().writeValueAsString(user1));
        Assert.assertNotNull(user);
    }

    @Test
    public void getRoleIds() {
        user = new User();
        user.setId(1l);
        Set<Role> roles = userAuthService.findRoles(user);
        logger.info("roles : {}", roles);
        roles = userAuthService.findRoles(user);
        logger.info("roles : {}", roles);
        Assert.assertNotNull(roles);
    }

    @Test
    public void findPermissions() {
        user = new User();
        user.setId(1l);
        Set<String> permissions = userAuthService.findStringPermissions(user);
        logger.info("permissions : {}", permissions);
        Assert.assertNotNull(permissions);
    }


    @Test
    public void selectUserPages() throws JsonProcessingException {
        int pageNum = 1;
        int limit = 3;
        while(true) {
            PageList<User> userPageList = userService.page(null, pageNum, limit);
            if (userPageList == null || userPageList.isEmpty()) {
                logger.info("not found users");
                break;
            }
            logger.info("totalCount : " + userPageList.getPaginator().getTotalCount());
            for (User user : userPageList) {
                logger.info("user1 : {}", JsonUtils.getObjectMapper().writeValueAsString(user));
            }

            pageNum = userPageList.getPaginator().getNextPage();
            logger.info("page : " + pageNum);
            if (!userPageList.getPaginator().isHasNextPage()) {
                break;
            }
        }
    }

    @Test
    public void timeTest() {
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm:ss");
        DateTime time = DateTime.parse("00:00:01", format);
        DateTime time1 = DateTime.parse("01:00:00", format);
        DateTime time2 = DateTime.parse("23:59:59", format);
        DateTime time3 = DateTime.parse("14:00:00", format);
        DateTime time4 = DateTime.parse("15:00:00", format);

        System.out.println(" time : "+ time.toString() + " millis : " + time.getMillisOfDay());
        System.out.println(" time1 : "+ time1.toString() + " millis : " + time1.getMillisOfDay());
        System.out.println(" time2 : "+ time2.toString() + " millis : " + time2.getMillisOfDay());
        System.out.println(" time3 : "+ time3.toString() + " millis : " + time3.getMillisOfDay() +  time3.getMillis());
        System.out.println(" time4 : "+ time4.toString() + " millis : " + time4.getMillisOfDay());
        System.out.println(" startDateMillis : "+ DateTime.parse("1970-01-01 00:00:00", DateTimeFormat.forPattern(Constants.DATETIME_NORMAL_FORMAT_NEW)).getMillisOfDay());
        System.out.println(" endDateMillis : "+ DateTime.parse("2100-01-01").getMillis() );
        System.out.println(new DateTime("2014-11-04"));
        System.out.println("dateFormat : " + new DateTime(DateTime.now().toString(Constants.DATETIME_DATE_NORMAL_FORMAT)));
        System.out.println("dateTimeFormat : " + DateFormat.getDateTimeInstance().format(new Date()));
        DateTime nowDate = new DateTime();
        System.out.println( "millisOfDay : "+ nowDate.getMillisOfDay() + " millis : " + nowDate.getMillis());
    }

}
