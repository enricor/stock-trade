package model;

import java.math.BigDecimal;

/**
 * @author Enrico
 * 	
 * class PreferredStock
 */
public class PreferredStock extends StockMD implements IStock {

	private BigDecimal fixedDividendPerc;

	public BigDecimal getFixedDividendPerc() {
		return fixedDividendPerc;
	}

	public void setFixedDividendPerc(BigDecimal fixedDividendPerc) {
		this.fixedDividendPerc = fixedDividendPerc;
	}
		
	public BigDecimal getDivYield(BigDecimal price) {
		if (fixedDividendPerc == null || price == null || price.equals(BigDecimal.ZERO))
			return null;
		
		return fixedDividendPerc.divide(new BigDecimal(100.0).multiply(parValue).divide(price));
		
	}
}
