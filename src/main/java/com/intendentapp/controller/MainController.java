package com.intendentapp.controller;

import com.intendentapp.MainTest;
import com.intendentapp.converter.CardConverter;
import com.intendentapp.converter.DayReportConverter;
import com.intendentapp.dbservices.CardPrzychodService;
import com.intendentapp.dbservices.CardRozchodService;
import com.intendentapp.dbservices.CardService;
import com.intendentapp.dbservices.ConsumerService;
import com.intendentapp.dbservices.DayReportItemService;
import com.intendentapp.dbservices.DayReportService;
import com.intendentapp.dbservices.ProductService;
import com.intendentapp.dbservices.StatsService;
import com.intendentapp.dtomodel.CardEntity;
import com.intendentapp.dtomodel.CardPrzychodEntity;
import com.intendentapp.dtomodel.CardRozchodEntity;
import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.dtomodel.DayReportItemEntity;
import com.intendentapp.dtomodel.ProductEntity;
import com.intendentapp.dtomodel.StatsEntity;
import com.intendentapp.generator.GenerateCard;
import com.intendentapp.generator.GenerateDayReport;
import com.intendentapp.generator.GenerateMenu;
import com.intendentapp.generator.GenerateSaleReport;
import com.intendentapp.model.*;

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
import java.net.MalformedURLException;
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
    @Autowired
    ProductService productService;  //obiekt obsługi tabeli products w bazie danych

    @Autowired
    ConsumerService consumerService;

    @Autowired
    StatsService statsService;
    
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
    
    private final Logger log = LogManager.getLogger(MainController.class);
    
    @GetMapping("/test-method")
    public String testMethod(HttpServletRequest request) throws MalformedURLException{
    	MainTest mt = new MainTest();
    	DayReport dayReport = mt.metodka();
    	
    	GenerateDayReport generateDayReport = new GenerateDayReport(dayReport);
        List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        for(String product : generateDayReport.getInsert().getProducts()){
            productEntityList.addAll(productService.findByName(product.trim()));
        }
        generateDayReport.generate(productEntityList);
        /*StatsEntity statsEntity = generateDayReport.getStatsEntity(statsService.findByMonth_year(generateDayReport.getGenerateMonthReport().getMonth()+" "+generateDayReport.getGenerateMonthReport().getYear()));
        statsService.save(statsEntity);*/

        if(generateDayReport.getMessage() != 2) {
	        int lp=0;
	        DecimalFormat df=new java.text.DecimalFormat("0.00");
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
	        	List<DayReportItemEntity> dayReportItems = dayReportItemService.findByIdDayReport(dayReportEntityList.get(0).getIdDayReport());
	        	for(DayReportItemEntity drie : dayReportItems) {
	        		dayReportItemService.delete(drie.getIdDayReportItem());
	        	}	        
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);
	        }else {
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);      	
	        }
	        dayReportService.save(dayReportEntity);
        	
        }

    	return "Poszlo";
    }
    
    
    

    @GetMapping("/")
    public String home(HttpServletRequest request){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority ga : auth.getAuthorities()){
            roles.add(ga.getAuthority());
        }
        //zabezpieczenie przed ponownym zalogowaniem
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            if(roles.contains("MANCIPLE")){
                return ("redirect:/home");
            }
        }
        return "login";
    }

    //wyświetlenie strony głównej
    @GetMapping("/home")
    public String homeManciple(HttpServletRequest request){
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }
    //wybór ilości dni do wprowadzenia do jadłospisu
    @GetMapping("/menu")
    public String setDayCount(HttpServletRequest request){
        request.setAttribute("mode", "MODE_DAYSAMOUNT");
        return "menu";
    }
    //metoda obsługująca wyświetlenie formularza utworzenia jadłospisu
    @PostMapping("/menu")
    public String menuPost(@ModelAttribute Menu menu, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute("formamounts", Integer.valueOf(menu.getAmount()));
        request.setAttribute("mode", "MODE_FORMS");
        return "menu";
    }
    //metoda generująca plik z jadłospisem
    @PostMapping("/addmenu")
    public String addMenu(@ModelAttribute Menu menu, BindingResult bindingResult, HttpServletRequest request){
        GenerateMenu generateMenu = new GenerateMenu(menu);
        generateMenu.generate();
        request.setAttribute("message", generateMenu.getMessage());
        request.setAttribute("mode", "MODE_MESSAGE");
        return "menu";
    }

    //wyświetlenie formularza tworzenia nowego zestawienia sprzedaży
    @GetMapping("/sales")
    public String sales(HttpServletRequest request){
        request.setAttribute("consumers", consumersObjectSortedBySurname());
        request.setAttribute("mode", "MODE_FORM");
        return "sales";
    }
    //dodanie zestawienia sprzedaży
    @PostMapping("/addsales")
    public String addSaleReport(@ModelAttribute ConsumerEntity consumer, @ModelAttribute SaleReport saleReport, BindingResult bindingResult, HttpServletRequest request){
        GenerateSaleReport generateSaleReport = new GenerateSaleReport(consumer, saleReport);
        generateSaleReport.generate();
        StatsEntity statsEntity = generateSaleReport.getStatsEntity(statsService.findByMonth_year(generateSaleReport.getMonth()+" "+ generateSaleReport.getYear()));
        statsService.save(statsEntity);
        request.setAttribute("message", generateSaleReport.getMessage());
        request.setAttribute("mode", "MODE_MESSAGE");
        return "sales";
    }
    //wyświetlenie formularza tworzenia nowego raportu
    @GetMapping("/dayreport")
    public String dayReport(HttpServletRequest request){
        request.setAttribute("productlist", productsObjectSortedByName());
        request.setAttribute("mode", "MODE_FORM");
        return "dayreport";
    }
    
    @GetMapping("/dane-testowe")
    public String dodajDaneTestowe(HttpServletRequest request) {
    	DayReport dr = new DayReport();
    	dr.setDinner1("Obiad 1");
    	dr.setDinner2("Obiad 2");
    	dr.setDinner3("Obiad 3");
    	dr.setDinner4("Obiad 4");
    	dr.setDate("2000-01-01");
    	dr.setReportNumber("1");
    	dr.setPodstawowa("10");
    	dr.setPrzedszkole("6");
    	dr.setNauczyciele("5");
    	dr.setPodzialPodstawowa("5+5");
    	dr.setPodzialPrzedszkole("3+3");
    	request.setAttribute("dayReport", dr);
    	request.setAttribute("productlist", productsObjectSortedByName());
    	request.setAttribute("mode", "MODE_FORM");
        return "dayreport";
    }
    
    //dodanie raportu dziennego
    @PostMapping("/adddayreport")
    public String addDayReport(@ModelAttribute DayReport dayReport, BindingResult bindingResult, HttpServletRequest request){
    	GenerateDayReport generateDayReport = new GenerateDayReport(dayReport);
        List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        for(String product : generateDayReport.getInsert().getProducts()){
            productEntityList.addAll(productService.findByName(product.trim()));
        }
        generateDayReport.generate(productEntityList);
        /*StatsEntity statsEntity = generateDayReport.getStatsEntity(statsService.findByMonth_year(generateDayReport.getGenerateMonthReport().getMonth()+" "+generateDayReport.getGenerateMonthReport().getYear()));
        statsService.save(statsEntity);*/

        if(generateDayReport.getMessage() != 2) {
	        int lp=0;
	        DecimalFormat df=new java.text.DecimalFormat("0.00");
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
	        	List<DayReportItemEntity> dayReportItems = dayReportItemService.findByIdDayReport(dayReportEntityList.get(0).getIdDayReport());
	        	//TODO przetestować dayReportEntityList.get(0).getDayReportItems() zamiast pobierać z bazy
	        	for(DayReportItemEntity drie : dayReportItems) {
	        		dayReportItemService.delete(drie.getIdDayReportItem());
	        	}	        
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);
	        }else {
	        	dayReportEntity = DayReportConverter.convert(dayReport, dayReportEntity, generateDayReport);      	
	        }
	        dayReportService.save(dayReportEntity);
        	
        }
        request.setAttribute("message", generateDayReport.getMessage());
        request.setAttribute("message2", generateDayReport.getMessage2());
        request.setAttribute("mode", "MODE_MESSAGE");
        return "dayreport";
    }
    
    @PostMapping("/save-card")
    public String saveCard(@ModelAttribute Card card, BindingResult bindingResult, HttpServletRequest request) throws Exception{
    	GenerateCard generateCard = new GenerateCard(card);
    	generateCard.generate();
    	if(generateCard.getMessage() != 0) {
	    	CardEntity cardEntity = cardService.findCard(card.getCardId());
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
	    		cardEntity = CardConverter.convert(card, cardEntity, generateCard);
		    	cardService.save(cardEntity);
	    	}else {
	    		throw new Exception("Nie ma takiej karty materiałowej.");
	    	}
    	}
    	request.setAttribute("message", generateCard.getMessage());
    	request.setAttribute("mode", "MODE_MESSAGE");
        return "cards";
    }

    @GetMapping("/cards")
    public String viewCards(HttpServletRequest request){
        request.setAttribute("mode", "MODE_FORM");
        return "cards";
    }

    //przegląd konsumentów
    @GetMapping("/viewconsumers")
    public String viewStudents(HttpServletRequest request){
        request.setAttribute("consumers", consumersObjectSortedBySurname());
        request.setAttribute("mode", "MODE_CONSUMERS");
        return "viewconsumers";
    }
    //wyświetlenie formularza edycji konsumenta
    @GetMapping("/update-consumer")
    public String updateConsumer(@RequestParam Integer id, HttpServletRequest request){
        request.setAttribute("consumer", consumerService.findConsumer(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "viewconsumers";
    }
    //dodanie nowego konsumenta do bazy
    @GetMapping("/add-consumer")
    public String addConsumer(HttpServletRequest request){
        request.setAttribute("mode", "MODE_ADD");
        return "viewconsumers";
    }
    //zapis konsumenta do bazy danych
    @PostMapping("/save-consumer")
    public String saveConsumer(@ModelAttribute ConsumerEntity consumer, BindingResult bindingResult, HttpServletRequest request){
        consumerService.save(consumer);
        request.setAttribute("consumers", consumersObjectSortedBySurname());
        request.setAttribute("mode", "MODE_CONSUMERS");
        return "viewconsumers";
    }
    //usunięcie konsumenta z bazy danych
    @GetMapping("/delete-consumer")
    public String deleteConsumer(@RequestParam Integer id, HttpServletRequest request){
        consumerService.delete(id);
        request.setAttribute("consumers", consumersObjectSortedBySurname());
        request.setAttribute("mode", "MODE_CONSUMERS");
        return "viewconsumers";
    }

    //przegląd produktów
    @GetMapping("/viewproducts")
    public String viewProducts(HttpServletRequest request) {
        request.setAttribute("products", productsObjectSortedByName());
        request.setAttribute("mode", "MODE_PRODUCTS");
        return "products";
    }
    //wyświetlenie formularza edycji produktu
    @GetMapping("/update-product")
    public String updateProduct(@RequestParam String number, HttpServletRequest request){
        request.setAttribute("product", productService.findProduct(number));
        request.setAttribute("mode", "MODE_UPDATE");
        return "products";
    }
    //dodanie nowego produktu do bazy
    @GetMapping("/add-product")
    public String addProduct(HttpServletRequest request){
        request.setAttribute("mode", "MODE_ADD");
        return "products";
    }
    //zapis produktu do bazy danych
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute ProductEntity product, BindingResult bindingResult, HttpServletRequest request){
        if(request.getParameter("addorupdate").equals("1")) {
            List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
            for (ProductEntity p : productEntityList) {
                if (p.getNumber().equals(product.getNumber())) {
                    request.setAttribute("mode", "MODE_WRONG_NUMBER");
                    return "products";
                }
            }
        }
        productService.save(product);
        request.setAttribute("products", productsObjectSortedByName());
        request.setAttribute("mode", "MODE_PRODUCTS");
        return "products";
    }
    //usunięcie produktu z bazy danych
    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam String number, HttpServletRequest request){
        productService.delete(number);
        request.setAttribute("products", productsObjectSortedByName());
        request.setAttribute("mode", "MODE_PRODUCTS");
        return "products";
    }

    @GetMapping("/viewreports")
    public String viewReports(HttpServletRequest request){
        File reportsFolder = new File("src/main/webapp/static/reports");
        File monthReportsFolder = new File("src/main/webapp/static/monthreports");
        File[] listOfReportsFiles = reportsFolder.listFiles();
        File[] listOfMonthReportsFiles = monthReportsFolder.listFiles();
        request.setAttribute("files", listOfReportsFiles);
        request.setAttribute("monthfiles", listOfMonthReportsFiles);
        return "viewreports";
    }
    @GetMapping("/viewsales")
    public String viewSales(HttpServletRequest request){
        File salesFolder = new File("src/main/webapp/static/sales");
        File[] listOfSalesFiles = salesFolder.listFiles();
        request.setAttribute("files", listOfSalesFiles);
        return "viewsales";
    }
    @GetMapping("/viewmenus")
    public String viewMenus(HttpServletRequest request){
        File salesFolder = new File("src/main/webapp/static/menus");
        File[] listOfSalesFiles = salesFolder.listFiles();
        request.setAttribute("files", listOfSalesFiles);
        return "viewmenus";
    }

    @GetMapping("/getDayReport")
    public void getDayReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get("src/main/webapp/static/reports", fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" +fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @GetMapping("/getMonthReport")
    public void getMonthReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get("src/main/webapp/static/monthreports", fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" +fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @GetMapping("/getSaleReport")
    public void getSaleReport(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get("src/main/webapp/static/sales", fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" +fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @GetMapping("/getMenu")
    public void getSMenu(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
        Path path = Paths.get("src/main/webapp/static/menus", fileName);
        try{
            response.setContentType("xlsx");
            response.setHeader("Content-Disposition","attachment; filename=\"" +fileName +"\"");
            response.getOutputStream().write(Files.readAllBytes(path));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @GetMapping("/stats")
    public String stats(HttpServletRequest request){
        request.setAttribute("stats", statsService.findAll());
        return "stats";
    }
    @GetMapping("/resetstats")
    public String resetStats(HttpServletRequest request){
        List<StatsEntity> statsEntityList = statsService.findAll();
        for (StatsEntity se : statsEntityList){
            statsService.delete(se.getStatid());
        }
        return "stats";
    }





    public List<String> productsNameSortByName(){
        List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
        List<String> products = new ArrayList<>();
        for(ProductEntity p : productEntityList){
            products.add(p.getName());
        }
        products.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return products;
    }

    public List<ProductEntity> productsObjectSortedByName(){
        List<ProductEntity> productEntityList = new ArrayList<>(productService.findAll());
        Collections.sort(productEntityList, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity o1, ProductEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return productEntityList;
    }

    public List<ConsumerEntity> consumersObjectSortedBySurname(){
        List<ConsumerEntity> consumerEntityList = new ArrayList<>(consumerService.findAll());
        Collections.sort(consumerEntityList, new Comparator<ConsumerEntity>() {
            @Override
            public int compare(ConsumerEntity o1, ConsumerEntity o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        return consumerEntityList;
    }






}
