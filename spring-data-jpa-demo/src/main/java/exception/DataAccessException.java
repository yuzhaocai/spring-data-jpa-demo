package exception;
/**
 * 数据访问异常，继承自RuntimeException，抛出时会出发spring事务的回滚
 *
 */
public class DataAccessException extends RuntimeException {

}
