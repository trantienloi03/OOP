package com.ttl.baixe.models;

//@lombok.Getter
//@lombok.Setter
//@lombok.NoArgsConstructor
public class ThongTinXeDap extends ThongTinXe {
	private String veXe;

	public String getVeXe() {
		return veXe;
	}

	public void setVeXe(String veXe) {
		this.veXe = veXe;
	}

	@Override
	public String getBiểnSốHoặcSốVé() {
		return this.getVeXe();
	}

	@Override
	public String getLoạiXe() {
		return "Xe đạp";
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
		 * 2k cho mỗi ngày-đêm tính xem từ lúc gửi -> lúc nhận có mấy ngày-đêm
		 */
		double tổngSốMs = this.getThoiDiemRa().getTime() - this.getThoiDiemVao().getTime();
		double sốMsMộtNgàyĐêm = 24 * 60 * 60 * 1000;
		double sốNgàyĐêm = Math.ceil(tổngSốMs / sốMsMộtNgàyĐêm);

		return sốNgàyĐêm * 2000;
	}

	@Override
	public String getKey() {
		return String.format("%s;%s;%s", "0", "Not Available", this.getVeXe());
	}

}
