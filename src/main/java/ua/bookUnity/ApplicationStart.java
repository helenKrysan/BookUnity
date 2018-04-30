package ua.bookUnity;

import java.io.File;
import java.util.List;

import ua.bookUnity.dao.FotoDAO;
import ua.bookUnity.dao.impl.FotoDAOImpl;



/**
 * 
 *
 */
public class ApplicationStart 
{
    public static void main( String[] args )
    {
        
    	FotoDAO foto = new FotoDAOImpl();
    	
        //AccountDAO acc = new AccountDAOImpl();
        //Account res =acc.save("koz.anastazia@gmail.com", "22041999", "жінка", "Анастасія", "Козачук", "Олександрівна", 19, "0501694997", "Васильків", "Київська область", "Україна");
        //Account res = acc.get("koz.anastazia@gmail.com", "22041998");
        //Account res =acc.update("koz.anastazia@gmail.com", "22041999", "жінка", "Анастасія", "Козачук", "Олександрівна", 18, "0501694997", "Васильків", "Київська область", "Україна");
       // System.out.println(res);
       // acc.delete("koz.anastazia@gmail.com", "22041999");
       
    	
    	
        //name for foto
    	//change book money, null
    	//update without pk
    	
        
    }
}
