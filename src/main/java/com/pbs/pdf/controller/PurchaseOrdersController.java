package com.pbs.pdf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbs.pdf.service.PurchaseOrdersService;

@Controller
@RequestMapping("/purchase-order")
public class PurchaseOrdersController {

	@Autowired
	private PurchaseOrdersService purchaseOrdersService;

	@GetMapping("/pdf")
	public ResponseEntity<ByteArrayResource> getSamplePdf(HttpServletRequest request, HttpServletResponse response) {

		ByteArrayResource byteArrayResource = purchaseOrdersService.getSamplePdf(request, response);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=purchaseOrdersSample.pdf")
				.contentType(MediaType.APPLICATION_PDF).contentLength(byteArrayResource.contentLength())
				.body(byteArrayResource);

	}
}
