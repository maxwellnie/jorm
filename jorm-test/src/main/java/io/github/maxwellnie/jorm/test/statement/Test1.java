package io.github.maxwellnie.jorm.test.statement;

import java.sql.SQLException;

/**
 * @author Maxwell Nie
 */
public class Test1 {
    public static void main(String[] args) throws SQLException {
        //============================SimpleStatementTest=============================
        //SimpleStatementTest.testInsert();
        //SimpleStatementTest.testUpdate();
        //SimpleStatementTest.testDelete();
        //SimpleStatementTest.testQuery();
        //SimpleStatementTest.testBatchInert();
        //SimpleStatementTest.testBatchUpdate();
        //SimpleStatementTest.testBatchDelete();


        //============================PreparedStatementTest=============================
        PreparedStatementTest.testQuery();
        //PreparedStatementTest.testInsert();
        //PreparedStatementTest.testUpdate();
        //PreparedStatementTest.testDelete();
        //PreparedStatementTest.testBatchInsert();
        PreparedStatementTest.testBatchUpdate();
        PreparedStatementTest.testBatchDelete();
        PreparedStatementTest.testQuery();
    }
}
