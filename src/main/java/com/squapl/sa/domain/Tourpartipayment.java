package com.squapl.sa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tourpartipayment")
public class Tourpartipayment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	private String refrencecode;
	public String getRefrencecode() {
		return refrencecode;
	}
	public void setRefrencecode(String refrencecode) {
		this.refrencecode = refrencecode;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	private double totalamount;
	private String paymentmode;
	private String paymentstatus;
	private String status;
}
