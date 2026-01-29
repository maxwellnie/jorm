package io.github.maxwellnie.jorm.core.sql;

/**
 * @author Maxwell Nie
 */
public interface SqlBuilder<S extends Sql>{
    S build() throws SqlBuildException;
}
