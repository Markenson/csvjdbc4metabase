/*
 *  CsvJdbc - a JDBC driver for CSV files
 *  Copyright (C) 2015  Simon Chenery
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.relique.jdbc.csv;

import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * Savepoint implementation that does nothing, simply to satisfy the
 * JDBC savepoint interfaces.
 */
public class CsvSavepoint implements Savepoint
{
	private int id;
	private String name;

	CsvSavepoint(int id)
	{
		this.id = id;
	}

	CsvSavepoint(String name)
	{
		this.name = name;
	}

	@Override
	public int getSavepointId() throws SQLException
	{
		if (name != null)
			throw new SQLException(CsvResources.getString("namedSavepoint"));
		return id;
	}

	@Override
	public String getSavepointName() throws SQLException
	{
		if (name == null)
			throw new SQLException(CsvResources.getString("notNamedSavepoint"));
		return name;
	}
}