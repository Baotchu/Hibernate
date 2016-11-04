package com.baochu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
/**   * Created by baotc on 10/17/2016.  */
public class Stock implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stockId;
    private String stockCode;
    private String stockName;
    public Stock() {
    }
    public Stock(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }
    public Integer getStockId() {
        return this.stockId;
    }
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
    public String getStockCode() {
        return this.stockCode;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    public String getStockName() {
        return this.stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    // Method for deleting data from database
    public void deleteStock(int stockId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Stock stock = (Stock) session.load(Stock.class, new Integer(stockId));
            session.delete(stock);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    // Method updating to database
    public void updateStock(Stock stock) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(stock);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    // Method to retrieve all stock
    public static ArrayList<Stock> getAllStocks() {
        List stocks = new ArrayList<Stock>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            stocks = session.createQuery("from Stock").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return (ArrayList)stocks;
    }
    // Method to retrieve stock id
    public static Stock getStockById(int stockId) {
        Stock stock = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Stock where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", stockId);
            stock = (Stock) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return stock;
    }
}


