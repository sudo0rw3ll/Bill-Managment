package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Base;
import model.Racun;
import model.Status;
import model.TipRacuna;

public class DBConnection {
	
	public static Connection connect() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_managment","root","password");
		}catch(SQLException e) {
			System.out.println("[!] Greska u konekciji");
		}catch(ClassNotFoundException e) {
			System.out.println("[!] Nepostojeca klasa!");
		}
		
		return conn;
	}
	
	public static void platiRacun(Racun racun) {
		Connection con = connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String update = "UPDATE racuni SET status=? WHERE tip_racuna=? AND datum=?";
			ps = con.prepareStatement(update);
			ps.setString(1, Status.PLACEN.toString());
			ps.setString(2, racun.getTip().toString().toLowerCase());
			java.sql.Date date = new java.sql.Date(racun.getDatum().getTime());
			System.out.println(date);
			ps.setDate(3, date);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("[!] Greska u update");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska update, zatvaranje ps");
				}
			}
			
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska update, zatvaranje rs");
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska update, zatvaranje konekcije");
				}
			}
		}
	}
	
	public static void fetchData() {
		Base.getInstance().getRacuni().clear();
		Connection con = connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM racuni";
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String tip = rs.getString("tip_racuna").toUpperCase();
				String status = rs.getString("status").toUpperCase();
				int iznos = rs.getInt("iznos");
				Date datum = rs.getDate("datum");
				
				TipRacuna tip_racuna = TipRacuna.valueOf(tip);
				Status statusRacuna = Status.valueOf(status);
				
				Racun racun = new Racun(tip_racuna,statusRacuna,iznos,datum);
				Base.getInstance().dodajRacun(racun);
			}
		}catch(SQLException e) {
			System.out.println("[!] Greska u fetch data");
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska fetch, zatvaranje ps");
				}
			}
			
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska fetch, zatvaranje rs");
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					System.out.println("[!] SQL greska zatvaranje konekcije");
				}
			}
		}
	}
}
