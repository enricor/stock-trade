package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Enrico
 * 
 *         StockSummary - represents stock data
 */
public class StockTrade {

	private StockMD stock;
	private int qty;
	private LocalDateTime ts;
	private boolean sell;
	private BigDecimal price;
	
	public StockMD getStock() {
		return stock;
	}

	public void setStock(StockMD stock) {
		this.stock = stock;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public LocalDateTime getTs() {
		return ts;
	}

	public void setTs(LocalDateTime ts) {
		this.ts = ts;
	}

	public boolean isSell() {
		return sell;
	}

	public void setSell(boolean sell) {
		this.sell = sell;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
