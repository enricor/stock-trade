package model;

import java.math.BigDecimal;

/**
 * @author Enrico
 * class CommonStock
 */
public class CommonStock extends StockMD implements IStock{
	private BigDecimal valueDividend;

	public BigDecimal getValueDividend() {
		return valueDividend;
	}

	public void setValueDividend(BigDecimal valueDividend) {
		this.valueDividend = valueDividend;
	}
	
	public BigDecimal getDivYield(BigDecimal price) {
		if (valueDividend == null || price == null || price.equals(BigDecimal.ZERO))
			return null;
		
		return valueDividend.divide(price);
	}

}
