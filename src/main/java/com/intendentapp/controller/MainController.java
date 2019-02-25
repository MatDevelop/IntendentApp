package com.intendentapp.controller;

import com.intendentapp.converter.CardConverter;
import com.intendentapp.converter.DayReportConverter;
import com.intendentapp.dbservices.CardPrzychodService;
import com.intendentapp.dbservices.CardRozchodService;
import com.intendentapp.dbservices.CardService;
import com.intendentapp.dbservices.ConsumerService;
import com.intendentapp.dbservices.DayReportItemService;
import com.intendentapp.dbservices.DayReportService;
import com.intendentapp.dbservices.ProductService;
import com.intendentapp.dtomodel.CardEntity;
import com.intendentapp.dtomodel.CardPrzychodEntity;
import com.intendentapp.dtomodel.CardRozchodEntity;
import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.dtomodel.DayReportItemEntity;
import com.intendentapp.dtomodel.ProductEntity;
import com.intendentapp.exceptions.NoFindCardException;
import com.intendentapp.generator.GenerateCard;
import com.intendentapp.generator.GenerateDayReport;
import com.intendentapp.generator.GenerateMenu;
import com.intendentapp.generator.GenerateSaleReport;
import com.intendentapp.model.*;
import com.intendentapp.staticclasses.Attributes;
import com.intendentapp.staticclasses.FilePaths;
import com.intendentapp.staticclasses.JspFileNames;
import com.intendentapp.staticclasses.Modes;
import com.intendentapp.testutils.CardTestUtils;
import com.intendentapp.testutils.DayReportTestUtils;
import com.intendentapp.utils.DayReportUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author MatDevelop
 */
@Controller
public class MainController {
	
	private final Logger log = LogManager.getLogger(MainController.class);
	
    @Autowired
    ProductService productService;  //obiekt obsługi tabeli products w bazie danych
    
    @Autowired
    ConsumerService consumerService;
    
    @Autowired
    DayReportService dayReportService;
    
    @Autowired
    DayReportItemService dayReportItemService;
    
    @Autowired
    CardService cardService;
    
    @Autowired
    CardRozchodService cardRozchodService;
    
    @Autowired
    CardPrzychodService cardPrzychodService;
    
    private final String ADD_PRODUCT_PARAM = "1";
    
    @GetMapping("/test")
    public void test(HttpServletRequest request) throws NoFindCardException{
    	System.out.println("Start...");
    	throw new NoFindCardException("NoFindException");
    	//System.out.println("Next");
    }
    
    @GetMapping("/card-test")
    public void testCardSave(HttpServletRequest request) throws NoFindCardException{
    	Card cardTestModel = CardTestUtils.createTestCardModel();
    	CardEntity cardEntity2 = new CardEntity();
    	cardTestModel.setCardId(84);
    	GenerateCard generateCard = new GenerateCard(cardTestModel);
    	
    	CardEntity cardEntity = cardService.findCard(cardTestModel.getCardId());
    	cardEntity2.setIdCardDocument(cardTestModel.getCardId());
    	if(cardEntity != null) {
    		List<CardPrzychodEntity> cardPrzychodEntityList = cardEntity.getCardPrzychodList();
    		List<CardRozchodEntity> cardRozchodEntityList = cardEntity.getCardRozchodList();
    		if(!cardPrzychodEntityList.isEmpty() && cardPrzychodEntityList != null) {
	    		for(CardPrzychodEntity cpe : cardPrzychodEntityList) {
	    			cardPrzychodService.delete(cpe.getIdCardPrzychod());
	    		}
    		}
    		if(!cardRozchodEntityList.isEmpty() && cardRozchodEntityList != null) {
	    		for(CardRozchodEntity cre : cardRozchodEntityList) {
	    			cardRozchodService.delete(cre.getIdCardRozchod());
	    		}
    		}
    		cardEntity2 = CardConverter.convert(cardTestModel, cardEntity2, generateCard);
	    	cardService.save(cardEntity2);
    	}else {
    		NoFindCardException nfce = new NoFindCardException("Nie ma takiej karty materiałowej.");
    		log.error(nfce);
    		throw nfce;
    	}
    	
    	/*cardEntity = CardConverter.convert(cardTestModel, cardEntity, generateCard);
    	cardService.save(cardEntity);*/
    }
    
    @GetMapping("/dayreport-test-method")
    public void testDayReportGenerateAndSave(HttpServletRequest request){
    	DayReport dayReportTest = DayReportTestUtils.createTestDayReport();

    	GenerateDayReport generateDayReport = new GenerateDayReport(dayReportTest);
        List<ProductEntity> productEntityList = new ArrayList<>();
        for(String product : generateDayReport.getInsert().getProducts()){
            productEntityList.addAll(productService.findByName(product.trim()));
        }
        generateDayReport.generate(productEntityList);

        if(generateDayReport.getMessage() != 2) {
	        int lp=0;
	        DecimalFormat df=new java.text.DecimalFormat("0.00");
	        for(ProductEntity product : productEntityList){
	            product.setUnitprice(Double.parseDouble(df.format(Double.parseDouble(generateDayReport.getInsert().getUnitprices().get(lp))).replace(",",".")));
	            productService.save(product);
	            lp++;
	        }	      
	        dayReportTest.setDayReportValue(Double.parseDouble(df.format(generateDayReport.getInsert().getReportCost()).replace(",",".")));
	        dayReportTest.setDayReportAvg(Double.parseDouble(df.format(generateDayReport.getInsert().getAvgCost()).replace(",","."))); 
        	List<DayReportEntity> dayReportEntityList = dayReportService.findByDate(generateDayReport.getInsert().getDateFromString());
        	DayReportEntity dayReportEntity = new DayReportEntity();
	        if(!dayReportEntityList.isEmpty()) {
	        	dayReportEntity.setIdDayReport(dayReportEntityList.get(0).getIdDayReport());
	        	for(DayReportItemEntity drie : dayReportEntityList.get(0).getDayReportItems()) {
	        		dayReportItemService.delete(drie.getIdDayReportItem());
	        	}	        
	        	dayReportEntity = DayReportConverter.convert(dayReportTest, dayReportEntity, generateDayReport);
	        }else {
	        	dayReportEntity = DayReportConverter.convert(dayReportTest, dayReportEntity, generateDayReport);      	
	        }
	        dayReportService.save(dayReportEntity);
        	
        }
    }
    
    @GetMapping("/")
    public String home(HttpServletRequest request){
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority ga : auth.getAuthorities()){
            roles.add(ga.getAuthority());
        }
        //zabezpieczenie przed ponownym zalogowaniem
        if (!(auth instanceof AnonymousAuthenticationToken) && roles.contains("MANCIPLE")) {
        	return ("redirect:/home");
        }
        
        return JspFileNames.LOGIN;
    }

    //wyświetlenie strony głównej
    @GetMapping("/home")
    public String homeManciple(HttpServletRequest request){
        request.setAttribute(Modes.MODE, Modes.MODE_HOME);
        return JspFileNames.INDEX;
    }
    
    //wybór ilości dni do wprowadzenia do jadłospisu
    @GetMapping("/menu")
    public String setDayCount(HttpServletRequest request){
        request.setAttribute(Modes.MODE, Modes.MODE_DAYSAMOUNT);
        return JspFileNames.MENU;
    }
    
    //metoda obsługująca wyświetlenie formularza utworzenia jadłospisu
    @PostMapping("/menu")
    public String menuPost(@ModelAttribute Menu menu, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute(Attributes.FORMAMOUNTS, Integer.valueOf(menu.getAmount()));
        request.setAttribute(Modes.MODE, Modes.MODE_FORM);
        return JspFileNames.MENU;
    }
    
    //metoda generująca plik z jadłospisem
    @PostMapping("/addmenu")
    public String addMenu(@ModelAttribute Menu menu, BindingResult bindingResult, HttpServletRequest request){
        GenerateMenu generateMenu = new GenerateMenu(menu);
        generateMenu.generate();
        request.setAttribute(Attributes.MESSAGE, generateMenu.getMessage());
        request.setAttribute(Modes.MODE, Modes.MODE_MESSAGE);
        return JspFileNames.MENU;
    }

    //wyświetlenie formularza tworzenia nowego zestawienia sprzedaży
    @GetMapping("/sales")
    public String sales(HttpServletRequest request){
        request.setAttribute(Attributes.CONSUMERS, consumersObjectSortedBySurname());
        request.setAttribute(Modes.MODE, Modes.MODE_FORM);
        return JspFileNames.SALES;
    }
    
    //dodanie zestawienia sprzedaży
    @PostMapping("/addsales")
    public String addSaleReport(@ModelAttribute ConsumerEntity consumer, @ModelAttribute SaleReport saleReport, BindingResult bindingResult, HttpServletRequest request){
        GenerateSaleReport generateSaleReport = new GenerateSaleReport(consumer, saleReport);
        generateSaleReport.generate();
        request.setAttribute(Attributes.MESSAGE, generateSaleReport.getMessage());
        request.setAttribute(Modes.MODE, Modes.MODE_MESSAGE);
        return JspFileNames.SALES;
    }
    
    //wyświetlenie formularza tworzenia nowego raportu
    @GetMapping("/dayreport")
    public String dayReport(HttpServletRequest request){
        request.setAttribute(Attributes.PRODUCT_LIST, productsObjectSortedByName());
        request.setAttribute(Modes.MODE, Modes.MODE_FORM);
        return JspFileNames.DAYREPORT;
    }
    
    @GetMapping("/dane-testowe")
    public String dodajDaneTestowe(HttpServletRequest request) {
    	request.setAttribute(Attributes.DAY_REPORT, DayReportUtils.generateDayReportWithTestData());
    	request.setAttribute(Attributes.PRODUCT_LIST, productsObjectSortedByName());
    	request.setAttribute(Modes.MODE, Modes.MODE_FORM);
        return JspFileNames.DAYREPORT;
    }
    
    //dodanie raportu dziennego
    @PostMapping("/adddayreport")
    public String addDayReport(@ModelAttribute DayReport dayReport, BindingResult bindingResult, HttpServletRequest request){
    	GenerateDayReport generateDayReport = new GenerateDayReport(dayReport);	//TODO przetestować czy kwoty idą z kropką czy z przecinkiem
        List<ProductEntity> productEntityList = new ArrayList<>();
        
        for(String product : generateDayReport.getInsert().getProducts()){
            productEntityList.addAll(productService.findByName(product.trim()));
        }
        
        generateDayReport.generate(productEntityList);

        if(generateDayReport.getMessage() != 2) {
	        int lp = 0;
	        DecimalFormat df = new java.text.DecimalFormat("0.00");
	        for(ProductEntity product : productEntityList){
	            product.setUnitprice(Double.parseDouble(df.format(Double.parseDouble(generateDayReport.getInsert().getUnitprices().get(lp))).replace(",",".")));
	            productService.save(product);
	            lp++;
	        }	      
	        dayReport.setDayReportValue(Double.parseDouble(df.format(generateDayReport.getInsert().getReportCost()).replace(",",".")));
        	dayReport.setDayReportAvg(Double.parseDouble(df.format(generateDayReport.getInsert().getAvgCost()).replace(",","."))); 
        	List<DayReportEntity> dayReportEntityList = dayReportService.findByDate(generateDayReport.getInsert().getDateFromString());
        	DayReportEntity dayReportEntity = new DayReportEntity();
	        if(!dayReportEntityList.isEmpty()) {
	        	dayReportEntity.setIdDayReport(dayReportEntityList.get(0).getIdDayReport());
	        	for(DayReportItemEntity drie : dayReportEntityList.get(0).getDayReportItems()) {
	        		dayReportItemService.delete(drie.getIdDayReportItem());
	        	}	        
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);
	        }else {
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);      	
	        }
	        dayReportService.save(dayReportEntity);
        	
        }
        
        request.setAttribute(Attributes.MESSAGE, generateDayReport.getMessage());
        request.setAttribute(Attributes.MESSAGE2, generateDayReport.getMessage2());
        request.setAttribute(Modes.MODE, Modes.MODE_MESSAGE);
        return JspFileNames.DAYREPORT;
    }
    
    @PostMapping("/save-card")
    public String saveCard(@ModelAttribute Card card, BindingResult bindingResult, HttpServletRequest request) throws NoFindCardException{
    	GenerateCard generateCard = new GenerateCard(card);
    	generateCard.generate();
    	if(generateCard.getMessage() != 0) {
	    	CardEntity cardEntity = cardService.findCard(card.getCardId());
	    	if(cardEntity != null) {
	    		CardEntity newCardEntity = new CardEntity();
	    		newCardEntity.setIdCardDocument(card.getCardId());
	    		List<CardPrzychodEntity> cardPrzychodEntityList = cardEntity.getCardPrzychodList();
	    		List<CardRozchodEntity> cardRozchodEntityList = cardEntity.getCardRozchodList();
	    		if(!cardPrzychodEntityList.isEmpty()) {
		    		for(CardPrzychodEntity cpe : cardPrzychodEntityList) {
		    			cardPrzychodService.delete(cpe.getIdCardPrzychod());
		    		}
	    		}
	    		if(!cardRozchodEntityList.isEmpty()) {
		    		for(CardRozchodEntity cre : cardRozchodEntityList) {
		    			cardRozchodService.delete(cre.getIdCardRozchod());
		    		}
	    		}
	    		newCardEntity = CardConverter.convert(card, newCardEntity, generateCard);
		    	cardService.save(newCardEntity);
	    	}else {
	    		NoFindCardException nfce = new NoFindCardException("Nie ma takiej karty materiałowej.");
	    		log.error(nfce);
	    		throw nfce;
	    	}
    	}
    	request.setAttribute(Attributes.MESSAGE, generateCard.getMessage());
    	request.setAttribute(Modes.MODE, Modes.MODE_MESSAGE);
        return JspFileNames.CARDS;
    }

    @GetMapping("/cards")
    public String viewCards(HttpServletRequest request){
        request.setAttribute(Modes.MODE, Modes.MODE_FORM);
        return JspFileNames.CARDS;
    }

    //przegląd konsumentów
    @GetMapping("/viewconsumers")
    public String viewStudents(HttpServletRequest request){
        request.setAttribute(Attributes.CONSUMERS, consumersObjectSortedBySurname());
        request.setAttribute(Modes.MODE, Modes.MODE_CONSUMERS);
        return JspFileNames.VIEWCONSUMERS;
    }
    
    //wyświetlenie formularza edycji konsumenta
    @GetMapping("/update-consumer")
    public String updateConsumer(@RequestParam Integer id, HttpServletRequest request){
        request.setAttribute(Attributes.CONSUMER, consumerService.findConsumer(id));
        request.setAttribute(Modes.MODE, Modes.MODE_UPDATE);
        return JspFileNames.VIEWCONSUMERS;
    }
    
    //dodanie nowego konsumenta do bazy
    @GetMapping("/add-consumer")
    public String addConsumer(HttpServletRequest request){
        request.setAttribute(Modes.MODE, Modes.MODE_ADD);
        return JspFileNames.VIEWCONSUMERS;
    }
    
    //zapis konsumenta do bazy danych
    @PostMapping("/save-consumer")
    public String saveConsumer(@ModelAttribute ConsumerEntity consumer, BindingResult bindingResult, HttpServletRequest request){
        consumerService.save(consumer);
        request.setAttribute(Attributes.CONSUMERS, consumersObjectSortedBySurname());
        request.setAttribute(Modes.MODE, Modes.MODE_CONSUMERS);
        return JspFileNames.VIEWCONSUMERS;
    }
    
    //usunięcie konsumenta z bazy danych
    @GetMapping("/delete-consumer")
    public String deleteConsumer(@RequestParam Integer id, HttpServletRequest request){
        consumerService.delete(id);
        request.setAttribute(Attributes.CONSUMERS, consumersObjectSortedBySurname());
        request.setAttribute(Modes.MODE, Modes.MODE_CONSUMERS);
        return JspFileNames.VIEWCONSUMERS;
    }

    //przegląd produktów
    @GetMapping("/viewproducts")
    public String viewProducts(HttpServletRequest request) {
        request.setAttribute(Attributes.PRODUCTS, productsObjectSortedByName());
        request.setAttribute(Modes.MODE, Modes.MODE_PRODUCTS);
        return JspFileNames.PRODUCTS;
    }
    
    //wyświetlenie formularza edycji produktu
    @GetMapping("/update-product")
    public String updateProduct(@RequestParam String number, HttpServletRequest request){
        request.setAttribute(Attributes.PRODUCT, productService.findProduct(number));
        request.setAttribute(Modes.MODE, Modes.MODE_UPDATE);
        return JspFileNames.PRODUCTS;
    }
    
    //dodanie nowego produktu do bazy
    @GetMapping("/add-product")
    public String addProduct(HttpServletRequest request){
        request.setAttribute(Modes.MODE, Modes.MODE_ADD);
        return JspFileNames.PRODUCTS;
    }
    
    //zapis produktu do bazy danych
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute ProductEntity productEntity, BindingResult bindingResult, HttpServletRequest request){
        if(request.getParameter(Attributes.ADD_OR_UPDATE).equals(ADD_PRODUCT_PARAM)) {
            List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
            for (ProductEntity p : productEntityList) {
                if (p.getNumber().equals(productEntity.getNumber())) {
                    request.setAttribute(Modes.MODE, Modes.MODE_WRONG_NUMBER);
                    return JspFileNames.PRODUCTS;
                }
            }
        }
        productService.save(productEntity);
        request.setAttribute(Attributes.PRODUCTS, productsObjectSortedByName());
        request.setAttribute(Modes.MODE, Modes.MODE_PRODUCTS);
        return JspFileNames.PRODUCTS;
    }
    
    //usunięcie produktu z bazy danych
    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam String number, HttpServletRequest request){
        productService.delete(number);
        request.setAttribute(Attributes.PRODUCTS, productsObjectSortedByName());
        request.setAttribute(Modes.MODE, Modes.MODE_PRODUCTS);
        return JspFileNames.PRODUCTS;
    }

    @GetMapping("/viewreports")
    public String viewReports(HttpServletRequest request){
        File reportsFolder = new File(FilePaths.REPORTS_FOLDER_PATH);
        File monthReportsFolder = new File(FilePaths.MONTH_REPORTS_FOLDER_PATH);
        File[] listOfReportsFiles = reportsFolder.listFiles();
        File[] listOfMonthReportsFiles = monthReportsFolder.listFiles();
        request.setAttribute(Attributes.FILES, listOfReportsFiles);
        request.setAttribute(Attributes.MONTH_FILES, listOfMonthReportsFiles);
        return JspFileNames.VIEWREPORTS;
    }
    
    @GetMapping("/viewsales")
    public String viewSales(HttpServletRequest request){
        File salesFolder = new File(FilePaths.SALES_FOLDER_PATH);
        File[] listOfSalesFiles = salesFolder.listFiles();
        request.setAttribute(Attributes.FILES, listOfSalesFiles);
        return JspFileNames.VIEWSALES;
    }
    
    @GetMapping("/viewmenus")
    public String viewMenus(HttpServletRequest request){
        File menusFolder = new File(FilePaths.MENUS_FOLDER_PATH);
        File[] listOfMenusFiles = menusFolder.listFiles();
        request.setAttribute(Attributes.FILES, listOfMenusFiles);
        return JspFileNames.VIEWMENUS;
    }

    @GetMapping("/getDayReport")
    public void getDayReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get(FilePaths.REPORTS_FOLDER_PATH, fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
        	log.error(e);
        }
    }
    
    @GetMapping("/getMonthReport")
    public void getMonthReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get(FilePaths.MONTH_REPORTS_FOLDER_PATH, fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
        	log.error(e);
        }
    }
    
    @GetMapping("/getSaleReport")
    public void getSaleReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get(FilePaths.SALES_FOLDER_PATH, fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
        	log.error(e);
        }
    }
    
    @GetMapping("/getMenu")
    public void getMenu(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get(FilePaths.MENUS_FOLDER_PATH, fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
        	log.error(e);
        }
    }

    public List<String> productsNameSortByName(){
        List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
        List<String> productNames = new ArrayList<>();
        for(ProductEntity p : productEntityList){
            productNames.add(p.getName());
        }
        productNames.sort((String o1, String o2) -> o1.compareTo(o2));	
        return productNames;
    }

    public List<ProductEntity> productsObjectSortedByName(){
        List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
        productEntityList.sort((ProductEntity o1, ProductEntity o2) -> o1.getName().compareTo(o2.getName()));
        return productEntityList;
    }

    public List<ConsumerEntity> consumersObjectSortedBySurname(){
        List<ConsumerEntity> consumerEntityList = new ArrayList<>(consumerService.findAll());
        consumerEntityList.sort((ConsumerEntity o1, ConsumerEntity o2) -> o1.getSurname().compareTo(o2.getSurname()));
        return consumerEntityList;
    }
}
