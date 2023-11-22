package sample.Main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	  public static void main(String[] args) throws SQLException {
		    // コネクションプールを作成
		    HikariConfig conf = new HikariConfig();
		    conf.setJdbcUrl("jdbc:postgresql://localhost:5432/ITDB");
		    conf.setUsername("postgres");
		    conf.setPassword("root");
		    HikariDataSource ds = new HikariDataSource(conf);
		    // コネクションを取得してSQLを実行
		    String sql = "select current_timestamp as now";
		    try (
		      Connection con = ds.getConnection();
		      PreparedStatement ps = con.prepareStatement(sql);
		      ResultSet rs = ps.executeQuery()
		    ) {
		      // 実行結果を標準出力
		      rs.next();
		      System.out.println(rs.getTimestamp("now"));
		    }
		    ds.close();
	  }
}
