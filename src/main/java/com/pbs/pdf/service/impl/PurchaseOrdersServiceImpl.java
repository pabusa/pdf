package com.pbs.pdf.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.pbs.pdf.model.Disclaimer;
import com.pbs.pdf.model.Order;
import com.pbs.pdf.service.PurchaseOrdersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

	@Autowired
	private TemplateEngine templateEngine;
	//TemplateEngine templateEngine = new TemplateEngine();

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ApplicationContext applicationContext;

	String baseUrl = "https://localhost:8080";
	String templateName = "purchase-order";

	public ByteArrayResource getSamplePdf(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orders = getOrders();
		List<Disclaimer> disclaimers = getDisclaimers();

		try {
			Map<String, Object> templateArguments = new HashMap<>();
			templateArguments.put("ClientId", "123456789");
			templateArguments.put("orders", orders);
			templateArguments.put("disclaimers", disclaimers);

			IWebContext ctx = new SpringWebContext(request, response, servletContext, LocaleContextHolder.getLocale(),
					templateArguments, applicationContext);

			String content = templateEngine.process(templateName, ctx); // referencia a purchase-order.html

			ITextRenderer iTextRenderer = new ITextRenderer();
			iTextRenderer.setDocumentFromString(content, baseUrl);
			iTextRenderer.layout();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			iTextRenderer.createPDF(bos, false);
			iTextRenderer.finishPDF();
			
			bos.close();

			return new ByteArrayResource(bos.toByteArray());
			
		} catch (Exception e) {
			System.out.println("Error");
			return null;
		}
	}

	@Override
	public List<Order> getOrders() {

		List<Order> orders = new ArrayList<Order>();

		Order orderBuy = new Order("1", "US0378331005", "APPLE INC US", "BUY", 1.0, 1000.0);
		Order orderSell = new Order("1", "US0378331005", "APPLE INC US", "SELL", 1.0, 1000.0);
		Order orderBuyLimit = new Order("2", "US0378331005", "APPLE INC US", "BUY-LIMIT", 1.0, 1000.0);
		Order orderSellLimit = new Order("2", "US0378331005", "APPLE INC US", "SELL-LIMIT", 1.0, 1000.0);
		Order orderSellStop = new Order("2", "US0378331005", "APPLE INC US", "SELL-STOP", 1.0, 1000.0);
		Order orderCommitment = new Order("3", "US0378331005", "APPLE INC US", "COMMITMENT", 1.0, 1000.0);

		orders.add(orderBuy);
		orders.add(orderSell);
		orders.add(orderBuyLimit);
		orders.add(orderSellLimit);
		orders.add(orderSellStop);
		orders.add(orderCommitment);

		return orders;
	}

	@Override
	public List<Disclaimer> getDisclaimers() {
		List<Disclaimer> disclaimers = new ArrayList<Disclaimer>();

		for (int i = 1; i <= 10; i++) {
			Disclaimer disclaimer = new Disclaimer(i,
					"Integer a viverra est, id aliquam odio. Mauris cursus leo eget vulputate sodales. Phasellus eget sem porttitor, iaculis ipsum quis, pulvinar dui. Nam volutpat finibus sem malesuada fringilla. Suspendisse non dolor vel enim lacinia euismod. In ut velit orci. Mauris et tortor eget nunc iaculis tincidunt. Duis dignissim massa quis mauris tempor tristique. Curabitur interdum erat sit amet vestibulum rhoncus. Aenean porttitor eu felis sed ornare. Morbi ante leo, feugiat id ornare in, dictum ut lorem. Morbi arcu quam, pharetra id convallis vel, luctus ut quam.");
			disclaimers.add(disclaimer);
		}

		return disclaimers;
	}

}
