/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;
import java.util.List;
/**
 *
 * @author Administrator
 */
public class DataSourceResult {
    private int Total;
	  private List<?> Data;
	  public int getTotal() {
	    return Total;
	  }
	  public void setTotal(int total) {
	    Total = total;
	  }
	  public List<?> getData() {
	    return Data;
	  }
	  public void setData(List<?> data) {
	    Data = data;
	  }
}
