package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesDataMapper {
	public static List<Sale> mapCSVToSalesData(String filePath) {
		List<Sale> salesData = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 2) {
					String dateString = data[0];
					int sales = Integer.parseInt(data[1]);

					Date date = dateFormat.parse(dateString);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH) + 1;

					salesData.add(new Sale(year, month, sales));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salesData;
	}

	public static Map<String, List<Sale>> readSalesDataFromFiles() {
		Map<String, List<Sale>> salesByModel = new HashMap<>();

		salesByModel.put("Model3", mapCSVToSalesData("model3.csv"));
		salesByModel.put("ModelS", mapCSVToSalesData("modelS.csv"));
		salesByModel.put("ModelX", mapCSVToSalesData("modelX.csv"));

		return salesByModel;
	}
}
