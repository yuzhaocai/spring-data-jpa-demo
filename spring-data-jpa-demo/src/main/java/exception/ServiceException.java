package exception;
/**
 * 业务逻辑层异常，继承自RuntimeException,当抛出时会出发spring事务的回滚
 *
 */
public class ServiceException extends RuntimeException {

}
