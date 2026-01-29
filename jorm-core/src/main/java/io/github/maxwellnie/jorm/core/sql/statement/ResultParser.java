package io.github.maxwellnie.jorm.core.sql.statement;

/**
 * @author Maxwell Nie
 */
@FunctionalInterface
public interface ResultParser<O, P, R> {
    R parse(O originalObject, P parameters) throws ResultParserException;
}
