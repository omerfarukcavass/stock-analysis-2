package aggregators;

import java.io.IOException;
import java.util.List;

import fileprocessors.StockFileReader;



/*	 When creating objects, we have written like:  ArrayList<? extends Employee> ...
 * 	 For class definitions, we don't use "?", use letters,words
 * 	 If we didnt put ".. extends Aggregator", we wouldn't use methods in Aggregator.
 * 
 */

public class AggregatorProcessor<T extends Aggregator> {
	
	T aggregator;
	String path;
	
	public AggregatorProcessor(T aggregator,String path){
		this.aggregator=aggregator;
		this.path=path;
	}
	
	public double runAggregator(int column) throws IOException {
		
		StockFileReader reader=new StockFileReader(path);
		List<String>lines= reader.readFileData();
		for(String line:lines) {
			String[] values=line.split(",");
			aggregator.add(Double.parseDouble(values[column-1]));
		}
		
	
		
		return aggregator.calculate();
	}
	
	
	
	
}
