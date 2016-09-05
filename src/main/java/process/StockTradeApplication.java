package process;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

import model.CommonStock;
import model.PreferredStock;
import model.StockMD;
import model.StockTrade;

public class StockTradeApplication {

	private HashMap<String, StockMD> prods;
	private List<StockTrade> trades;
	
	public static void main(String[] args) {

		StockTradeApplication app = new StockTradeApplication();
		app.runExample();

	}

	private void runExample() {
		initProds();
		doSomeTrade();
		BigDecimal v1 = getPeriodStockPrice(getLast15MinStockTrades(prods.get("ale"), trades));
		System.out.println(String.format("Last 15 min. ale price: %.4f", v1.doubleValue()));
		BigDecimal v2 = getAllPricesIndex(prods.values());
		System.out.println(String.format("All prices index: %.4f", v2.doubleValue()));
	}
	
	public boolean addTrade(StockTrade trade) {
		trades.add(trade);
		return true;
	}

	public void initProds() {
		prods = new HashMap<String, StockMD>();

		CommonStock cs = new CommonStock();
		cs.setId(100L);
		cs.setSymbol("tea");
		cs.setValueDividend(BigDecimal.ZERO);
		cs.setParValue(new BigDecimal(100.0));

		prods.put(cs.getSymbol(), cs);

		cs = new CommonStock();
		cs.setId(101L);
		cs.setSymbol("pop");
		cs.setValueDividend(new BigDecimal(8.0));
		cs.setParValue(new BigDecimal(100.0));

		prods.put(cs.getSymbol(), cs);

		cs = new CommonStock();
		cs.setId(101L);
		cs.setSymbol("ale");
		cs.setValueDividend(new BigDecimal(23.0));
		cs.setParValue(new BigDecimal(60.0));

		prods.put(cs.getSymbol(), cs);

		PreferredStock ps = new PreferredStock();
		ps.setId(102L);
		ps.setSymbol("gin");
		ps.setFixedDividendPerc(new BigDecimal(2.0));
		ps.setParValue(new BigDecimal(100.0));

		prods.put(ps.getSymbol(), ps);
		
		cs = new CommonStock();
		cs.setId(101L);
		cs.setSymbol("cs");
		cs.setValueDividend(new BigDecimal(13.0));
		cs.setParValue(new BigDecimal(250.0));

		prods.put(cs.getSymbol(), cs);

	}

	public void doSomeTrade() {

		trades = new ArrayList<StockTrade>();
		
		StockTrade t = new StockTrade();

		t.setPrice(new BigDecimal(104.0));
		t.setTs(LocalDateTime.now().minusMinutes(18));
		t.setStock(prods.get("ale"));
		t.setQty(750);

		trades.add(t);
		t = new StockTrade();
		
		t.setPrice(new BigDecimal(98.0));
		t.setTs(LocalDateTime.now().minusMinutes(12));
		t.setStock(prods.get("ale"));
		t.setQty(1000);

		trades.add(t);
		t = new StockTrade();
		
		t.setPrice(new BigDecimal(98.0));
		t.setTs(LocalDateTime.now().minusMinutes(11));
		t.setStock(prods.get("ale"));
		t.setQty(450);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(102.0));
		t.setTs(LocalDateTime.now().minusMinutes(9));
		t.setStock(prods.get("ale"));
		t.setQty(180);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(98.0));
		t.setTs(LocalDateTime.now().minusMinutes(8));
		t.setStock(prods.get("ale"));
		t.setQty(200);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(97.0));
		t.setTs(LocalDateTime.now().minusMinutes(5));
		t.setStock(prods.get("ale"));
		t.setQty(180);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(100.0));
		t.setTs(LocalDateTime.now().minusMinutes(3));
		t.setStock(prods.get("ale"));
		t.setQty(150);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(103.0));
		t.setTs(LocalDateTime.now().minusMinutes(1));
		t.setStock(prods.get("ale"));
		t.setQty(90);

		trades.add(t);
		t = new StockTrade();

		t.setPrice(new BigDecimal(99.0));
		t.setTs(LocalDateTime.now());
		t.setStock(prods.get("ale"));
		t.setQty(80);

		trades.add(t);
		
		t = new StockTrade();

		t.setPrice(new BigDecimal(103.0));
		t.setTs(LocalDateTime.now().minusSeconds(30));
		t.setStock(prods.get("ale"));
		t.setQty(1500);

		trades.add(t);

		t = new StockTrade();
		
		t.setPrice(new BigDecimal(104.0));
		t.setTs(LocalDateTime.now().minusMinutes(25));
		t.setStock(prods.get("ale"));
		t.setQty(120);

		trades.add(t);

	}

	public List<StockTrade> getLast15MinStockTrades(StockMD stock, List<StockTrade> allTrades) {
		ArrayList<StockTrade> filtTrades = new ArrayList<StockTrade>();

		LocalDateTime t0 = LocalDateTime.now().minusMinutes(15);

		for (StockTrade t : allTrades) {
			if (t.getStock().equals(stock) && t.getTs().isAfter(t0)) {
				filtTrades.add(t);
			}

		}
		return filtTrades;
	}

	public BigDecimal getPeriodStockPrice(List<StockTrade> trades) {

		if (trades.size() > 0) {
			double[] prices = new double[trades.size()];
			double[] qtys = new double[trades.size()];

			int j = 0;
			for (StockTrade t : trades) {
				prices[j] = t.getPrice().doubleValue();
				qtys[j++] = t.getQty();
			}

			Mean mean = new Mean();
			return new BigDecimal(mean.evaluate(prices, qtys));
		}
		return null;
	}
	
	private BigDecimal getAllPricesIndex(Collection<StockMD> values) {
		
		if (values == null || values.isEmpty())
			return null;
		
		SummaryStatistics stat = new SummaryStatistics();
		for (StockMD s: values) {
			stat.addValue(s.getParValue().doubleValue());
		}
		return new BigDecimal(stat.getGeometricMean());
	}
}