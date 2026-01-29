package io.github.maxwellnie.jorm.test.statement;

import io.github.maxwellnie.jorm.core.sql.single.SingleSql;
import io.github.maxwellnie.jorm.core.sql.single.SingleSqlBuilder;
import io.github.maxwellnie.jorm.core.sql.statement.IntegratedStatement;
import io.github.maxwellnie.jorm.core.sql.statement.PreparedIntegratedStatement;
import io.github.maxwellnie.jorm.test.DBUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Maxwell Nie
 */
public class PreparedStatementTest {
    public static final IntegratedStatement.Executor<PreparedStatement, Void> RP = (statement, sql) -> {
        System.out.println(statement.executeUpdate());
        return null;
    };
    public static void testInsert() throws SQLException {
        SingleSql singleSql = new SingleSqlBuilder(3)
                .appendSql("insert into tb_user(loginname, `password`, role_id) values (?, ?, ?)")
                .appendSqlParameters("hello jorm!", 112233, 1)
                .build();
        DBUtils.execute(c-> new PreparedIntegratedStatement(c)
                .createStatement(singleSql.getSqlString())
                .parameterize((ps, s) -> {
                    for (int i = 0; i < s.getSqlParameters().size(); i++) {
                        ps.setObject(i + 1, s.getSqlParameters().get(i));
                    }
                }, singleSql)
                .handleStatement((ps) -> {
                    ps.setQueryTimeout(1);
                }), is-> System.out.println(is.execute(RP)));
    }
}
