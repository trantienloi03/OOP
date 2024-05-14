package com.nvtrung.baixe.models;

import java.util.Date;

//@lombok.Getter
//@lombok.Setter
//@lombok.NoArgsConstructor
abstract public class ThongTinXe {
	private Date thoiDiemVao;
	private Date thoiDiemRa;
	
	
	public Date getThoiDiemVao() {
		return thoiDiemVao;
	}
	public void setThoiDiemVao(Date thoiDiemVao) {
		this.thoiDiemVao = thoiDiemVao;
	}
	public Date getThoiDiemRa() {
		return thoiDiemRa;
	}
	public void setThoiDiemRa(Date thoiDiemRa) {
		this.thoiDiemRa = thoiDiemRa;
	}
	abstract public String getBiểnSốHoặcSốVé();
	abstract public String getLoạiXe();
	abstract public String getTìnhTrạng1();
	abstract public String getTìnhTrạng2();
	
	abstract public Double getGiáGiữXe();
	
	abstract public String getKey();
}
