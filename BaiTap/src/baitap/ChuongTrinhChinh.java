package baitap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class ChuongTrinhChinh {
	static void inDanhSach(List<SinhVien> lst) {
		for (int i = 0; i < lst.size(); i++) {
			var x = lst.get(i);
			System.out.printf("%3d %6s %30s %5s %s\n",
					i + 1,
					x.getMaSinhVien(),
					x.getHoTen(),
					x.isGioiTinhNam() ? "Nam" : "Nữ",
					x.getNgaySinh());
		}
	}

	static String docChuoiKetNoi() {
//		return "jdbc:sqlserver://localhost;databaseName=QuanLySach;user=sa;password=123;encrypt=true;trustServerCertificate=true";
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\java\\BaiTap\\src\\baitap\\config.txt"));
			String st = br.readLine();
			br.close();
			
			return st;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) throws SQLException {
		// đọc file config.ini để lấy chuỗi kết nối
		String chuoiKetNoi = docChuoiKetNoi();
		System.out.println("CHuỗi kết nối đọc được: [" + chuoiKetNoi + "]");
		
		SinhVienDAO dao = new SinhVienDAO(chuoiKetNoi);

		// 1. đọc dữ liệu & in ds sinh viên
		List<SinhVien> lst = dao.selectAll();
		System.out.println("Danh sách SV:");
		inDanhSach(lst);

		// 2. insert sinh viên & đọc, in ds sinh viên
		SinhVien x = new SinhVien();
		x.setMaSinhVien("sv43567");
		x.setHoTen("Trần Thị Nữ");
		x.setGioiTinhNam(false);
		x.setNgaySinh(new Date());
		try {
			dao.insert(x);
		} catch (Exception exc) {
			System.out.println("Không insert được. Lỗi " + exc.getMessage());
		}

		System.out.println("\n\nDanh sách SV sau khi insert:");
		lst = dao.selectAll();
		inDanhSach(lst);
	}
}
