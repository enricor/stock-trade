package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Enrico
 * 
 * StockMD - stock master data
 * 
 */
public abstract class StockMD {
	
	private Long id;
	private String symbol;
	private boolean active;
	private Date lastUpdate;
	protected BigDecimal parValue;
	protected BigDecimal yieldVal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public BigDecimal getParValue() {
		return parValue;
	}
	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}
	
	public BigDecimal getPeRatio() {
		if (yieldVal != null && !yieldVal.equals(BigDecimal.ZERO))
			return BigDecimal.ONE.divide(yieldVal);
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockMD other = (StockMD) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
}
