package com.nupur.mystore.dao;

import com.nupur.mystore.exception.CartException;
import com.nupur.mystore.exception.CategoryException;
import com.nupur.mystore.pojo.Advert;
import com.nupur.mystore.pojo.Book;
import com.nupur.mystore.pojo.Category;
import com.nupur.mystore.pojo.Customer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

public class BookDAO extends DAO {
	
	public BookDAO(){
	
	}

	public Book insert(Book book) throws CartException {
		try{
			begin();            
			getSession().save(book);     
            commit();
            return book;
		} catch (HibernateException e){
			rollback();
            throw new CartException("book cannot be saved", e);
		}
	}
	
	public void update(Book book) throws CategoryException {
        try {
            begin();
            getSession().update(book);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
    }
	
	public Customer update(Customer customer) throws CategoryException {
        try {
            begin();
            getSession().update(customer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
        return customer;
    }
	
	public List<Book> list(){
		begin();
		Query q = getSession().createQuery("from Book");
		List<Book> cart1 = q.list();
		commit();
		return cart1;
	}
	
	public Book updateCart(Book book) throws CategoryException {
        try {
            begin();
            getSession().update(book);
            commit();
            return book;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
    }
	

}
