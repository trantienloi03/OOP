package com.nvtrung.baixe.models;

//@lombok.Getter
//@lombok.Setter
//@lombok.NoArgsConstructor
public class ThongTinXeOto extends ThongTinXe {
	private String biểnSố;
	private String tìnhTrạngKhiVào;
	private String tìnhTrạngKhiRa;

	public String getBiểnSố() {
		return biểnSố;
	}

	public void setBiểnSố(String biểnSố) {
		this.biểnSố = biểnSố;
	}

	public String getTìnhTrạngKhiVào() {
		return tìnhTrạngKhiVào;
	}

	public void setTìnhTrạngKhiVào(String tìnhTrạngKhiVào) {
		this.tìnhTrạngKhiVào = tìnhTrạngKhiVào;
	}

	public String getTìnhTrạngKhiRa() {
		return tìnhTrạngKhiRa;
	}

	public void setTìnhTrạngKhiRa(String tìnhTrạngKhiRa) {
		this.tìnhTrạngKhiRa = tìnhTrạngKhiRa;
	}

	@Override
	public String getBiểnSốHoặcSốVé() {
		return this.getBiểnSố();
	}

	@Override
	public String getLoạiXe() {
		return "Xe Ôtô";
	}

	@Override
	public String getTìnhTrạng1() {
		return this.getTìnhTrạngKhiVào();
	}

	@Override
	public String getTìnhTrạng2() {
		return this.getTìnhTrạngKhiRa();
	}

	@Override
	public Double getGiáGiữXe() {
		if (this.getThoiDiemRa() == null)
			return null;
		
		if (this.tìnhTrạngKhiRa.equals(this.tìnhTrạngKhiVào) == false)
			return null;

		/*
		 * 5k cho mỗi nửa giờ tính xem từ lúc gửi -> lúc nhận có mấy ngày-đêm
		 */
		double tổngSốMs = this.getThoiDiemRa().getTime() - this.getThoiDiemVao().getTime();
		double sốMsMộtNửaGiờ = 30 * 60 * 1000;
		double sốNửaGiờ = Math.ceil(tổngSốMs / sốMsMộtNửaGiờ);

		return sốNửaGiờ * 5000;
	}

	
	@Override
	public String getKey() {
		return String.format("%s;%s;%s", "4", this.getBiểnSố(), "Not Available");
	}

}
