package com.mycompany.webapp.dto;

import java.util.Date;

public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private String battachonmae;
	private String battachsavename;
	private String battachtype;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getBhitcount() {
		return bhitcount;
	}
	public void setBhitcount(int bhitcount) {
		this.bhitcount = bhitcount;
	}
	public String getBattachonmae() {
		return battachonmae;
	}
	public void setBattachonmae(String battachonmae) {
		this.battachonmae = battachonmae;
	}
	public String getBattachsname() {
		return battachsavename;
	}
	public void setBattachsname(String battachsname) {
		this.battachsavename = battachsname;
	}
	public String getBattachtype() {
		return battachtype;
	}
	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}
	

}
