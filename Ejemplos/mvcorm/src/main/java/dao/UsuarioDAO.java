
package dao;

import dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilileria.HibernateUtil;

/**
 *
 * @author aarongarcia
 */
public class UsuarioDAO { // respecto a ORM
    public void create(UsuarioDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.save(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    public void update(UsuarioDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.update(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    public void delete(UsuarioDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.delete(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    
    public UsuarioDTO read(UsuarioDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
            dto.setEntidad(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
        
        return dto;
    }
    
    public List readAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList();
        
        try{
            transaction.begin();
            
            Query query = session.createQuery("from Usuario u order by u.idUsuario");
            for (Usuario u : (List<Usuario>)query.list()) {  
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(u);
                lista.add(dto);
            }
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
        
        return lista;
    }
    
    
}
