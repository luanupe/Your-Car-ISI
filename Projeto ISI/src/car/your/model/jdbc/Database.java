package car.your.model.jdbc;

import jdbchelper.JdbcHelper;
import jdbchelper.SimpleDataSource;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 11:36:22
 *
 */
public class Database {

	private JdbcHelper jdbc;
	private String driver;
	private String url;
	private String user;
	private String pass;
	
	public Database() {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost/your_car?autoReconnect=true&failOverReadOnly=false&maxReconnects=1";
		this.user = "root";
		this.pass = "";
	}
	
	public synchronized void connect() {
		SimpleDataSource source = new SimpleDataSource(this.driver, this.url, this.user, this.pass);
		this.jdbc = new JdbcHelper(source);
	}
	
	public JdbcHelper getJdbc() {
		return this.jdbc;
	}
	
}
