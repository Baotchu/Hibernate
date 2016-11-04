package com.baochu;
import org.hibernate.Session;
import java.util.ArrayList;
/**   Created by baotc on 10/24/2016.    */
public class RetrieveStock {
    public static void main(String[] args)
    {
        try {
            System.out.println("Maven + Hibernate + MySQL");
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            /****** Retrieve data // Get all stocks ******/
            ArrayList<Stock> outputs = Stock.getAllStocks();
            for (int i = 0; i < outputs.size(); i ++ ) {
                System.out.println(outputs.get(i).getStockCode() + " " + outputs.get(i).getStockName() + " " + outputs.get(i).getStockId());
            }
            /****** Retrieve data // Get stockId ******/
            Stock output = Stock.getStockById(8);
             System.out.println(output.getStockId() + " " + output.getStockCode() + " " + output.getStockName());
             System.out.println("Data Info Retrieve Successfull!");

        } catch (Exception ex) {
            String tt= "";
        }
    }
}
