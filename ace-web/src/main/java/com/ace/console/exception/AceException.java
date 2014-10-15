/**
 *
 *
 */


package com.ace.console.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Project_Name: ace-erp
 * File: AceException
 * User: denghp
 * Date: 10/18/13
 * Time: 10:08 AM
 */
public abstract class AceException extends Exception {

    public static AceException create(Code code, String message) {
        AceException v = create(code);
        v.message = message;
        return v;
    }

    private Code code;

    private String message;

    public AceException(Code code) {
        this.code = code;
    }

    public AceException(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public static AceException create(Code code) {
        switch (code) {
            case SYSTEM_ERROR:
                return new SystemErrorException();
            case NOT_FOUND:
                return new NotFoundException();
            case NO_AUTH:
                return new NoAuthException();
            case CONNECTION_LOSS:
                return new ConnectionLossException();
            case USER_EXISTS:
                return new UserExistsException();
            case USER_NOT_FOUND:
                return new UserNotFoundException();
            case OPERATION_TIMEOUT:
                return new OperationTimeoutException();
            case BAD_ARGUMENTS:
                return new BadArgumentsException();
            case BAD_REQUEST:
                return new BadRequestException();
            case AUTH_FAILED:
                return new AuthFailedException();
            case SESSION_EXPIRED:
                return new SessionExpiredException();
            case USER_PASSWORD_NOT_MATCH:
                return new UserPasswordNotMatchException();
            case USER_PASSWORD_RETRY_COUNT:
                return new UserPasswordRetryCount();
            case OK:
            default:
                throw new IllegalArgumentException("Invalid exception code");
        }
    }

    static String getCodeMessage(Code code) {
        switch (code) {
            case OK:
                return "OK";
            case SYSTEM_ERROR:
                return "SystemError";
            case NOT_FOUND:
                return "NotFound";
            case NO_AUTH:
                return "NoAuth";
            case CONNECTION_LOSS:
                return "ConnectionLoss";
            case USER_EXISTS:
                return "UserExists";
            case USER_NOT_FOUND:
                return "UserNotFound";
            case OPERATION_TIMEOUT:
                return "OperationTimeout";
            case BAD_ARGUMENTS:
                return "BadArguments";
            case BAD_REQUEST:
                return "BadRequest";
            case AUTH_FAILED:
                return "AuthFailed";
            case SESSION_EXPIRED:
                return "Session expired";
            case USER_PASSWORD_NOT_MATCH:
                return "user password not match";
            case USER_PASSWORD_RETRY_COUNT:
                return "user password retry max count.";
            default:
                return "Unknown error " + code;
        }
    }

    public static enum Code{
        //OK 200 Everything is OK
        OK(200),
        NOT_FOUND(404),
        /** 系统级别的错误状态码 10001 **/
        //系统错误
        SYSTEM_ERROR(10001),
        //服务端资源不可用
        SERVER_RESOUCE_NOT_USE(10002),
        //操作任务超时 10003
        OPERATION_TIMEOUT(10003),
        //非法请求
        BAD_REQUEST(10004),
        //错误:缺失必选参数:%s，请参考API文档
        MISS_REQUIRED_PARAMETER(10006),
        //错误:参数值非法,希望得到 (%s),实际得到 (%s)，请参考API文档
        BAD_ARGUMENTS(10007),
        //session timeout
        SESSION_EXPIRED(10008),
        //链接不稳定
        CONNECTION_LOSS(1009),


        //服务级别的错误状态码 20001
        //该手机号已经被使用
        PHONE_EXISTS(20001),
        //该用户已经绑定手机
        USER_REL_PHONE(20002),
        //该用户没有绑定手机
        USER_NO_REL_PHONE(20003),
        //该用户不存在
        USER_NOT_FOUND(20004),
        //用户存在
        USER_EXISTS(20005),
        //用户封禁
        USER_BLOCKED(2006),


        /** 服务权限验证状态码 21001 **/
        //没有权限  Authentication failed
        NO_AUTH(21301),
        //授权失败 Authorization failed
        AUTH_FAILED(21302),
        //用户密码不正确
        USER_PASSWORD_NOT_MATCH(21303),

        //用户名密码认证超过请求限制
        USER_PASSWORD_RETRY_COUNT(21304),


        //服务暂时无法访问
        SERVICE_TEMPORARILY_UNAVAILABLE(21305),
        //系统繁忙请重试
        SYSTEM_IS_BUSY_TRY_AGEN(21306);


        private static final Map<Integer,Code> lookup
                = new HashMap<Integer,Code>();

        static {
            for(Code c : EnumSet.allOf(Code.class))
                lookup.put(c.code, c);
        }

        private final int code;
        Code(int code) {
            this.code = code;
        }

        /**
         * Get the int value for a particular Code.
         * @return error code as integer
         */
        public int intValue() { return code; }

        /**
         * Get the Code value for a particular integer error code
         * @param code int error code
         * @return Code value corresponding to specified int code, or null
         */
        public static Code get(int code) {
            return lookup.get(code);
        }
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return getCodeMessage(code);
        }
        return message;
    }

    public int code() {
        return code.intValue();
    }

    @Override
    public String toString() {
        if (message == null) {
            return "{\"code\": "+ code.intValue() +",\"message\":\""+ getCodeMessage(code) +"\" }";
        }
        return "{\"code\": "+ code.intValue() +",\"message\":\""+ message +"\" }";
    }


    /**
     * @see Code#SYSTEM_ERROR
     */
    public static class SystemErrorException extends AceException {
        public SystemErrorException() {
            super(Code.SYSTEM_ERROR);
        }
    }

    /**
     * @see Code#OPERATION_TIMEOUT
     */
    public static class OperationTimeoutException extends AceException {
        public OperationTimeoutException() {
            super(Code.OPERATION_TIMEOUT);
        }
    }

    /**
     * @see Code#CONNECTION_LOSS
     */
    public static class ConnectionLossException extends AceException {
        public ConnectionLossException() {
            super(Code.CONNECTION_LOSS);
        }
    }


    /**
     * @see Code#SESSION_EXPIRED
     */
    public static class SessionExpiredException extends AceException {
        public SessionExpiredException() {
            super(Code.SESSION_EXPIRED);
        }
    }

    /**
     * @see Code#NO_AUTH
     */
    public static class NoAuthException extends AceException {
        public NoAuthException() {
            super(Code.NO_AUTH);
        }
    }

    /**
     * @see Code#NOT_FOUND
     */
    public static class NotFoundException extends AceException {
        public NotFoundException() {
            super(Code.NOT_FOUND);
        }
    }


    /**
     * @see Code#BAD_ARGUMENTS
     */
    public static class BadArgumentsException extends AceException {
        public BadArgumentsException() {
            super(Code.BAD_ARGUMENTS);
        }
    }

    /**
     * @see Code#BAD_REQUEST
     */
    public static class BadRequestException extends AceException {
        public BadRequestException () {
            super(Code.BAD_REQUEST);
        }
    }

    /**
     * @see Code#USER_NOT_FOUND
     */
    public static class UserNotFoundException extends AceException {
        public UserNotFoundException () {
            super(Code.USER_NOT_FOUND);
        }

        public UserNotFoundException (String message) {
            super(Code.USER_NOT_FOUND,message);
        }
    }

    /**
     * @see Code#USER_PASSWORD_NOT_MATCH
     */
    public static class UserPasswordNotMatchException extends AceException {
        public UserPasswordNotMatchException() {
            super(Code.USER_PASSWORD_NOT_MATCH);
        }

        public UserPasswordNotMatchException(String message) {
            super(Code.USER_PASSWORD_NOT_MATCH);
        }
    }

    /**
     * @see Code#USER_EXISTS
     */
    public static class UserExistsException extends AceException {
        public UserExistsException () {
            super(Code.USER_EXISTS);
        }
    }

    /**
     * @see Code#AUTH_FAILED
     */
    public static class AuthFailedException extends AceException {
        public AuthFailedException () {
            super(Code.AUTH_FAILED);
        }
    }

    /**
     * @see Code#USER_BLOCKED
     */
    public static class UserBlockedException extends AceException {
        public UserBlockedException() {
            super(Code.USER_BLOCKED);
        }
    }
    /**
     * @see Code#USER_PASSWORD_RETRY_COUNT
     */
    public static class UserPasswordRetryCount extends AceException {
        public UserPasswordRetryCount() {
            super(Code.USER_PASSWORD_RETRY_COUNT);
        }
    }


}
