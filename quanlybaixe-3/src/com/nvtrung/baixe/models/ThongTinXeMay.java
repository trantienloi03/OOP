package com.nvtrung.baixe.models;

//@lombok.Getter
//@lombok.Setter
//@lombok.NoArgsConstructor

public class ThongTinXeMay extends ThongTinXe {
	private String biểnSố;
	
	public String getBiểnSố() {
		return biểnSố;
	}

	public void setBiểnSố(String biểnSố) {
		this.biểnSố = biểnSố;
	}

	@Override
	public String getBiểnSốHoặcSốVé() {
		return this.getBiểnSố();
	}

	@Override
	public String getLoạiXe() {
		return "Xe máy";
	}

	@Override
	public String getTìnhTrạng1() {
		return "-";
	}

	@Override
	public String getTìnhTrạng2() {
		return "-";
	}

	@Override
	public Double getGiáGiữXe() {
		if (this.getThoiDiemRa() == null)
			return null;

		/*
		 * 3k cho mỗi ngày-đêm tính xem từ lúc gửi -> lúc nhận có mấy ngày-đêm
		 */
		double tổngSốMs = this.getThoiDiemRa().getTime() - this.getThoiDiemVao().getTime();
		double sốMsMộtNgàyĐêm = 24 * 60 * 60 * 1000;
		double sốNgàyĐêm = Math.ceil(tổngSốMs / sốMsMộtNgàyĐêm);

		return sốNgàyĐêm * 3000;
	}

	@Override
	public String getKey() {
		return String.format("%s;%s;%s", "2", this.getBiểnSố(), "Not Available");
	}

}
