package Main;

import java.util.*;
import java.util.stream.Collectors;

public class SalesDataProcessor {

	public static void main(String[] args) {
		Map<String, List<Sale>> salesDataByModel = SalesDataMapper.readSalesDataFromFiles();

		salesDataByModel.forEach((model, sales) -> {
			// 1. Filter sales data for 2016-2019 for the current model
			List<Sale> filteredSales = sales.stream().filter(sale -> sale.getYear() >= 2016 && sale.getYear() <= 2019)
					.collect(Collectors.toList());

			Map<Integer, Integer> totalSalesByYear = filteredSales.stream()
					.collect(Collectors.groupingBy(Sale::getYear, Collectors.summingInt(Sale::getSales)));

			System.out.println("Total sales for " + model + ": $");
			totalSalesByYear
					.forEach((year, totalSales) -> System.out.println("Total sales for " + year + ": $" + totalSales));

			// 3. Find the best sales month
			 Optional<Map.Entry<Integer, Integer>> bestSalesMonth = filteredSales.stream()
	                    .collect(Collectors.groupingBy(Sale::getMonth, Collectors.summingInt(Sale::getSales)))
	                    .entrySet().stream()
	                    .max(Comparator.comparingInt(Map.Entry::getValue));

	            bestSalesMonth.ifPresent(entry -> {
	                int bestMonth = entry.getKey();
	                int bestYear = filteredSales.stream()
	                        .filter(sale -> sale.getMonth() == bestMonth)
	                        .mapToInt(Sale::getYear)
	                        .findFirst().orElse(0);

	                String formattedBestMonth = String.format("%d-%02d", bestYear, bestMonth);
	                System.out.println("Best sales month: " + formattedBestMonth);
	            });

	            // Find the worst sales month
	            Optional<Map.Entry<Integer, Integer>> worstSalesMonth = filteredSales.stream()
	                    .collect(Collectors.groupingBy(Sale::getMonth, Collectors.summingInt(Sale::getSales)))
	                    .entrySet().stream()
	                    .min(Comparator.comparingInt(Map.Entry::getValue));

	            worstSalesMonth.ifPresent(entry -> {
	                int worstMonth = entry.getKey();
	                int worstYear = filteredSales.stream()
	                        .filter(sale -> sale.getMonth() == worstMonth)
	                        .mapToInt(Sale::getYear)
	                        .findFirst().orElse(0);

	                String formattedWorstMonth = String.format("%d-%02d", worstYear, worstMonth);
	                System.out.println("Worst sales month: " + formattedWorstMonth);
	            });
	        });
	    }
	}