package br.markenson.com.csvjdbc4metabase.readers;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.relique.io.TableReader;
import org.relique.jdbc.csv.CsvConnection;

public class HttpCSVReader implements TableReader {

	@Override
	public Reader getReader(Statement statement, String tableName) throws SQLException {
	    try
	    {
	      CsvConnection conn = ((CsvConnection) statement.getConnection());
	    	
	      URL url = new URL(conn.getCustomBaseUrl() + tableName + conn.getExtension());
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      InputStreamReader reader = new InputStreamReader(connection.getInputStream());
	      return reader;
	    }
	    catch (Exception e)
	    {
	      throw new SQLException(e.getMessage());
	    }
	}

	@Override
	public List<String> getTableNames(Connection connection) throws SQLException {
    	CsvConnection conn = ((CsvConnection) connection);
    	return conn.getCustomTableList();
	}

}
