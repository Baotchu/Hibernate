package com.baochu;
/**  Created by baotc on 10/17/2016.    */
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
public class NewApp
{
    public static void main(String[] args)
    {
        try {
            System.out.println("Maven + Hibernate + MySQL");
            Session session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Stock stock = new Stock();
           /******  Insert data into database ******/
           stock.setStockCode("1080");
           stock.setStockName("Dr Chu Thai Bao");
           System.out.println("Data Info Insert Successfull!");

            /*Delete data from database */
           stock.deleteStock(22);
           System.out.println("Data Info Delete Successfull!");

            /****** Update data to database   ******/

           stock.setStockCode("4401");
           stock.setStockName("SCIS ");
           stock.setStockId(8);
           stock.updateStock(stock);
           System.out.println("Data Info Update Successfull!");
           session.save(stock);
           session.getTransaction().commit();
        } catch (Exception ex) {
            String tt= "";  }
    }
}
