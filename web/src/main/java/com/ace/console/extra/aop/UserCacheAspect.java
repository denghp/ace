package com.ace.console.extra.aop;

import com.ace.console.cache.BaseCacheAspect;
import com.ace.console.utils.Constants;
import com.ace.core.persistence.sys.entity.User;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户缓存切面
 * 缓存实现
 * 1、username/email/mobile------>id
 * 2、id------->Model
 * @Author: denghp
 * @Date: 10/20/14
 * @Time: 2:47 PM
 */
@Component
@Aspect
public class UserCacheAspect extends BaseCacheAspect {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserCacheAspect() {
        setCacheName(Constants.DEFAULT_MM_CACHE_NAME);
    }

    private static final String ID_KEY_PREFIX = "id_";
    private static final String DT_ID_KEY_PREFIX = "dt_id_";
    private static final String USERNAME_KEY_PREFIX = "username_";
    private static final String EMAIL_KEY_PREFIX = "email_";
    private static final String MOBILE_KEY_PREFIX = "mobile_";

    ////////////////////////////////////////////////////////////////////////////////
    ////切入点
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 匹配用户Service
     */
    @Pointcut(value = "target(com.ace.console.service.sys.UserService)")
    private void userServicePointcut() {
    }

    /**
     * only put
     * 如 新增 修改 登录 改密 改状态  只有涉及修改即可
     */
    @Pointcut(value =
            "execution(* save(..)) " +
                    "|| execution(* saveAndFlush(..)) " +
                    "|| execution(* update(..)) " +
                    "|| execution(* login(..)) " +
                    "|| execution(* changePassword(..)) " +
                    "|| execution(* changeStatus(..))")
    private void cachePutPointcut() {
    }


    /**
     * evict 比如删除
     */
    @Pointcut(value = "(execution(* delete(*))) && args(arg)", argNames = "arg")
    private void cacheEvictPointcut(Object arg) {
    }

    /**
     * put 或 get
     * 比如查询
     */
    @Pointcut(value =
            "(execution(* getByUsername(*)) " +
                    "|| execution(* getUserDetails(*)) " +
                    "|| execution(* getByEmail(*)) " +
                    "|| execution(* getByMobile(*)) " +
                    "|| execution(* selectById(*)))")
    private void cacheablePointcut() {
    }


    ////////////////////////////////////////////////////////////////////////////////
    ////增强实现
    ////////////////////////////////////////////////////////////////////////////////
    @AfterReturning(value = "userServicePointcut() && cachePutPointcut()", returning = "user")
    public void cachePutAdvice(User user) {
        //put cache
        String key = getCacheKey(user.getId().toString(),User.class);
        if (StringUtils.isBlank(key)) {
            LOGGER.warn("getCacheKey is null.");
        } else {
            put(key, user);
        }
    }

    @After(value = "userServicePointcut() && cacheEvictPointcut(arg)", argNames = "arg")
    public void cacheEvictAdvice(Object arg) {
        if (arg == null) {
            return;
        }
        if (arg instanceof Long) {
            //only evict id
            evict(getCacheKey(idKey(String.valueOf(arg)), User.class));
            //only evict user details
            evict(getCacheKey(getDtIdKeyPrefix(String.valueOf(arg)), User.class));
        }
        if (arg instanceof Long[]) {
            for (Long id : (Long[]) arg) {
                //only evict id
                evict(getCacheKey(idKey(String.valueOf(arg)), User.class));
                //only evict user details
                evict(getCacheKey(getDtIdKeyPrefix(String.valueOf(arg)), User.class));
            }
        }

        if (arg instanceof String) {
            //only evict id
            evict(getCacheKey(idKey(String.valueOf(arg)), User.class));
            //only evict user details
            evict(getCacheKey(getDtIdKeyPrefix(String.valueOf(arg)), User.class));
        }
        if (arg instanceof String[]) {
            for (String id : (String[]) arg) {
                //only evict id
                evict(getCacheKey(idKey(String.valueOf(arg)), User.class));
                //only evict user details
                evict(getCacheKey(getDtIdKeyPrefix(String.valueOf(arg)), User.class));
            }
        }
        if (arg instanceof User) {
            //evict user
            evict(getCacheKey(idKey(((User) arg).getId().toString()), User.class));
            //only evict id
            evict(getCacheKey(getDtIdKeyPrefix(((User) arg).getId().toString()), User.class));
        }
    }

    @Around(value = "userServicePointcut() && cacheablePointcut()")
    public Object cacheableAdvice(ProceedingJoinPoint pjp) throws Throwable {

        String methodName = pjp.getSignature().getName();
        Object arg = pjp.getArgs().length >= 1 ? pjp.getArgs()[0] : null;

        String key = "";
        if ("selectById".equals(methodName)) {
            key = getCacheKey(idKey(String.valueOf(arg)), User.class);
        } else if ("getByUsername".equals(methodName)) {
            key = getCacheKey(usernameKey((String) arg), User.class);
        } else if ("getByEmail".equals(methodName)) {
            key = getCacheKey(emailKey((String) arg),User.class);
        } else if ("getByMobilePhone".equals(methodName)) {
            key = getCacheKey(mobilePhoneNumberKey((String) arg), User.class);
        } else if ("getUserDetails".equals(methodName)) {
            key = getCacheKey(getDtIdKeyPrefix((String) arg), User.class);
        }

        User user = get(key);

        //cache hit
        if (user != null) {
            LOGGER.info("cacheName:{}, hit key:{}", cacheName, key);
            return user;
        }
        LOGGER.info("cacheName:{}, miss key:{}", cacheName, key);

        //cache miss
        user = (User) pjp.proceed();

        //put cache
        put(key, user);
        return user;

    }
    private String getDtIdKeyPrefix(String id) {return DT_ID_KEY_PREFIX + id;}

    private String idKey(String id) {
        return ID_KEY_PREFIX + id;
    }

    private String usernameKey(String username) {
        return USERNAME_KEY_PREFIX + username;
    }

    private String emailKey(String email) {
        return EMAIL_KEY_PREFIX + email;
    }

    private String mobilePhoneNumberKey(String mobilePhoneNumber) {
        return MOBILE_KEY_PREFIX + mobilePhoneNumber;
    }

//    ////////////////////////////////////////////////////////////////////////////////
//    ////cache 抽象实现
//    ////////////////////////////////////////////////////////////////////////////////
//    public void put(User user) {
//        if (user == null) {
//            return;
//        }
//        Long id = user.getId();
//        //username email mobilePhoneNumber ---> id
//        put(usernameKey(user.getUsername()), id);
//        put(emailKey(user.getEmail()), id);
//        put(mobilePhoneNumberKey(user.getMobile()), id);
//        // id ---> user
//        put(idKey(String.valueOf(id)), user);
//    }
//
//
//    public void evictId(String id) {
//        evict(idKey(id));
//    }
//
//    public void evict(User user) {
//        if (user == null) {
//            return;
//        }
//        Long id = user.getId();
//        evict(idKey(String.valueOf(id)));
//        evict(usernameKey(user.getUsername()));
//        evict(emailKey(user.getEmail()));
//        evict(mobilePhoneNumberKey(user.getMobile()));
//    }

}
