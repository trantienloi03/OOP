package com.nvtrung.baixe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nvtrung.baixe.models.ThongTinXe;
import com.nvtrung.baixe.models.ThongTinXeDap;
import com.nvtrung.baixe.models.ThongTinXeMay;
import com.nvtrung.baixe.models.ThongTinXeOto;

public class ChuongTrinh {
	static SimpleDateFormat sdf = new SimpleDateFormat("H:mm dd/mm/yyyy");

	static void test() {
		String hoTen = "Nguyễn Văn Trung"; //
		String st = "Nguyễn Văn Trung";

		if (hoTen == st) // so sánh nội dung vùng nhớ mà hoTen và st đang trỏ đến
			System.out.println("Bằng nhau");
		else
			System.out.println("Khác nhau");
	}
	
	static Date convert(String st) {
		Date result = null;
		try {
			result = sdf.parse(st);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	static List<ThongTinXe> docThongTinXeVaoBai(String filename) throws IOException {
		var lst = new ArrayList<ThongTinXe>();

		BufferedReader br = null;
		br = new BufferedReader(new FileReader(filename));
		
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			
			// phân tích line --> tạo ra dữ liệu thôngTinXe
			// Loại xe;Biển số xe;Số vé xe;Thời gian vào bãi;Tình trạng vào
			var info = line.split(";");
			var loạiXe = info[0];
			var biểnSố = info[1];
			var sốVé = info[2];
			var thoiDiemVao = convert(info[3]);
			var tìnhTrạngKhiVào = info[4];
			
			if (loạiXe.equals("0")) {
				var x = new ThongTinXeDap();
				x.setThoiDiemVao(thoiDiemVao);
				x.setVeXe(sốVé);
				lst.add(x);
			} else if (loạiXe.equals("2")) {
				var x= new ThongTinXeMay();
				x.setBiểnSố(biểnSố);
				x.setThoiDiemVao(thoiDiemVao);
				lst.add(x);
			} else if (loạiXe.equals("4")) {
				var x = new ThongTinXeOto();
				x.setBiểnSố(biểnSố);
				x.setThoiDiemVao(thoiDiemVao);
				x.setTìnhTrạngKhiVào(tìnhTrạngKhiVào);
				lst.add(x);
			}
		}
		
		br.close();

		return lst;
	}

	static void capNhatThongTinXeRaBai(String filename, List<ThongTinXe> lst) throws IOException {

		BufferedReader br = null;
		br = new BufferedReader(new FileReader(filename));
		
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			
			// phân tích line --> tạo ra dữ liệu thôngTinXe
			// Loại xe; Biển số xe; Số vé xe; Thời gian ra bãi; Tình trạng ra
			var info = line.split(";");
			var loạiXe = info[0];
			var biểnSố = info[1];
			var sốVé = info[2];
			var thoiDiemRa = convert(info[3]);
			var tìnhTrạngKhiRa = info[4];
			
			// Tìm xe (loạiXe + biểnSố/Vé) trong lst ==> obj
			// cập nhật obj.setThoiDiemRa, obj.setTìnhTrạngKhiRa
			var key = loạiXe + ";" + biểnSố + ";" + sốVé;
			ThongTinXe obj = null;
			for (var x : lst)
				if (x.getKey().equals(key)) {
					obj = x;
					break;
				}
			
			if (obj != null) {
				obj.setThoiDiemRa(thoiDiemRa);
				if (loạiXe.equals("4")) {
					((ThongTinXeOto)obj).setTìnhTrạngKhiRa(tìnhTrạngKhiRa);
				}
			}
					
		}
		
		br.close();
	}

	static void inGiáGiữXe(List<ThongTinXe> lst) {
		int stt = 0;
		// STT	Thời điểm vào		Xe		Loại xe	Tt1	  Thời điểm ra	Tt2  Giá
		System.out.printf("%-3s %-18s %-12s %-10s %-30s %18s %-30s  %-10s\n", 
				"STT", "Thời điểm vào", "Xe", "Loại xe", "Tình trạng 1", "Thời điểm ra", "Tình trạng 2", "Giá");
		for (ThongTinXe x: lst) {
			if (x.getThoiDiemRa() != null) {
				stt++;
				
				System.out.printf("%-3d %-18s %-12s %-10s %-30s %-18s %-30s %10.0f\n", 
						stt, 
						sdf.format(x.getThoiDiemVao()), 
						x.getBiểnSốHoặcSốVé(), 
						x.getLoạiXe(), 
						x.getTìnhTrạng1(),
						sdf.format(x.getThoiDiemRa()),
						x.getTìnhTrạng2(),
						x.getGiáGiữXe()
					);
			}
		}
	}

	static void inThôngTinXeCònLại(List<ThongTinXe> lst) {
		int stt = 0;
		// STT	Thời điểm vào		Xe		Loại xe	Tình trạng
		System.out.printf("%-3s %-18s %-12s %-10s %-30s\n", 
				"STT", "Thời điểm vào", "Xe", "Loại xe", "Tình trạng");
		for (ThongTinXe x: lst) {
			if (x.getThoiDiemRa() == null) {
				stt++;
				
				System.out.printf("%-3d %-18s %-12s %-10s %-30s\n", 
						stt, 
						sdf.format(x.getThoiDiemVao()), 
						x.getBiểnSốHoặcSốVé(), 
						x.getLoạiXe(), 
						x.getTìnhTrạng1()
					);
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		test();

		var lst = docThongTinXeVaoBai("vao.txt");

		capNhatThongTinXeRaBai("ra.txt", lst);

		inGiáGiữXe(lst);

		inThôngTinXeCònLại(lst);
	}

}
