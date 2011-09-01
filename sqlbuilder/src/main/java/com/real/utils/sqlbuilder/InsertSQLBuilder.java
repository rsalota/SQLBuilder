package com.real.utils.sqlbuilder;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.Validate;

/**
 * Allows for easy building of SQL insert statements.
 * 
 * @author rsalota
 * 
 */
public class InsertSQLBuilder {
	private final Map<String, String> columnValueMap = new TreeMap<String, String>();
	private final String tableName;

	public InsertSQLBuilder(String tableName) {
		Validate.notEmpty(tableName);
		this.tableName = tableName;
	}

	public InsertSQLBuilder add(String column, String value) {
		Validate.notEmpty(column);
		Validate.notEmpty(value);
		columnValueMap.put(column, "'" + value + "'");
		return this;
	}

	public InsertSQLBuilder add(String column, Long value) {
		Validate.notEmpty(column);
		Validate.notNull(value);
		columnValueMap.put(column, value.toString());
		return this;
	}

	public InsertSQLBuilder add(String column, Function f) {
		Validate.notNull(column);
		columnValueMap.put(column, f.getFunction());
		return this;
	}

	public String generateInsertStatement() {
		StringBuilder InsertSQLBuilder = new StringBuilder("insert into "
				+ tableName + " (");
		StringBuilder valueBuilder = new StringBuilder("(");
		boolean first = true;
		for (Map.Entry<String, String> entry : columnValueMap.entrySet()) {
			if (first) {
				InsertSQLBuilder.append(entry.getKey());
				valueBuilder.append(entry.getValue());
				first = false;
			} else {
				InsertSQLBuilder.append(", ").append(entry.getKey());
				valueBuilder.append(", ").append(entry.getValue());
			}
		}
		InsertSQLBuilder.append(")");
		valueBuilder.append(")");
		return InsertSQLBuilder.toString() + " values "
				+ valueBuilder.toString();
	}
}
