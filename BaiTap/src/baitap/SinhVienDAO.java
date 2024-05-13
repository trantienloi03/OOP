package baitap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SinhVienDAO {
	private String connectionString;

	public SinhVienDAO(String st) {
		this.connectionString = st;
	}

	private Connection _cnn = null;
	private Connection getConnection() throws SQLException {
		if (_cnn == null) {
			try {
				_cnn = DriverManager.getConnection(connectionString);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return _cnn;
		
	}
	
	public List<SinhVien> selectAll() throws SQLException {
		List<SinhVien> lst = new ArrayList<>();

		// 1. tạo connection
		Connection cnn = this.getConnection();

		// 2. tạo stmt
		Statement stmt = cnn.createStatement();

		// 3. thực thi stmt --> render danh sách
		String query = "SELECT MaSinhVien, HoTen, GioiTinhNam, NgaySinh FROM SinhVien";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			String maSinhVien = rs.getString(1);
			String hoTen = rs.getString(2);
			boolean gioiTinhNam = rs.getBoolean(3);
			Date ngaySinh = rs.getDate(4);

			SinhVien sv = new SinhVien(maSinhVien, hoTen, gioiTinhNam, ngaySinh);
			lst.add(sv);
		}

		// 4. giải phóng rsc
		rs.close();
		stmt.close();
//		cnn.close(); // <--- 

		return lst;
	}

	public boolean insert(SinhVien x) throws SQLException {

		// 1. tạo connection
		Connection cnn = this.getConnection();

		// 2. tạo stmt
		Statement stmt = cnn.createStatement();

		// 3. thực thi stmt
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String stNgaySinh = sdf.format(x.getNgaySinh());
		String query = String.format(
				"INSERT INTO SinhVien (MaSinhVien, HoTen, GioiTinhNam, NgaySinh) "
						+ "VALUES ('%s', N'%s', %d, '%s')",
				x.getMaSinhVien(),
				x.getHoTen(),
				x.isGioiTinhNam() ? 1 : 0,
				stNgaySinh// x.getNgaySinh()
		);
		int n = stmt.executeUpdate(query);

		// 4. giải phóng rsc
		stmt.close();
//		cnn.close();

		return n > 0;
	}
}
