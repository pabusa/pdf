package com.pbs.pdf.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;

import com.pbs.pdf.model.Disclaimer;
import com.pbs.pdf.model.Order;

public interface PurchaseOrdersService {

	public ByteArrayResource getSamplePdf(final HttpServletRequest request, final HttpServletResponse response);
	public List<Order> getOrders();
	public List<Disclaimer> getDisclaimers();
	
}
