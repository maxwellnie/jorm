package io.github.maxwellnie.jorm.test;

import io.github.maxwellnie.jorm.core.sql.statement.IntegratedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Maxwell Nie
 */
public class DBUtils {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hgms?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T extends Statement> void execute(SqlFunc<Connection, IntegratedStatement<T>> func, SqlConsumer<IntegratedStatement<T>> consumer){
        Connection connection = getConnection();
        try {
            IntegratedStatement<T> statement = func.apply(connection);
            consumer.accept(statement);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FunctionalInterface
    public interface SqlFunc<T, R> {
        R apply(T t) throws SQLException;
    }
    @FunctionalInterface
    public interface SqlConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
