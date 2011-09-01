package com.real.utils.sqlbuilder;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.real.utils.sqlbuilder.Function;
import com.real.utils.sqlbuilder.InsertSQLBuilder;

public class InsertSQLBuilderTest {
	private InsertSQLBuilder InsertSQLBuilder;

	@Before
	public void setUp() {
		InsertSQLBuilder = new InsertSQLBuilder("TestTable");
	}

	@Test
	public void testInsertSingleColumn() {
		InsertSQLBuilder.add("real", "networks");
		String insertSql = InsertSQLBuilder.generateInsertStatement();
		Assert.assertEquals("insert into TestTable (" + "real" + ")"
				+ " values ('networks')", insertSql);

	}

	@Test
	public void testMultipleColumns() {
		InsertSQLBuilder.add("real", "networks");
		InsertSQLBuilder.add("status", "approved");
		String insertSql = InsertSQLBuilder.generateInsertStatement();
		System.out.println(insertSql);
		Assert.assertEquals("insert into TestTable (" + "real, status" + ")"
				+ " values ('networks', 'approved')", insertSql);
	}

	@Test
	public void testLongColumns() {
		InsertSQLBuilder.add("real", 1L);
		InsertSQLBuilder.add("status", 2L);
		String insertSql = InsertSQLBuilder.generateInsertStatement();
		System.out.println(insertSql);
		Assert.assertEquals("insert into TestTable (" + "real, status" + ")"
				+ " values (1, 2)", insertSql);

	}

	@Test
	public void testFunctions() {
		InsertSQLBuilder.add("real", new Function("sysdate"));
		InsertSQLBuilder.add("status", new Function("sysdate-300"));
		String insertSql = InsertSQLBuilder.generateInsertStatement();
		System.out.println(insertSql);
		Assert.assertEquals("insert into TestTable (" + "real, status" + ")"
				+ " values (sysdate, sysdate-300)", insertSql);
	}

}
