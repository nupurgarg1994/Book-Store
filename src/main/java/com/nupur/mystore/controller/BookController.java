package com.nupur.mystore.controller;



import javax.servlet.ServletContext;

import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nupur.mystore.dao.AdvertDAO;
import com.nupur.mystore.dao.BookDAO;
import com.nupur.mystore.dao.CategoryDAO;
import com.nupur.mystore.dao.DAO;
import com.nupur.mystore.dao.CustomerDAO;
import com.nupur.mystore.pojo.Advert;
import com.nupur.mystore.pojo.Book;
import com.nupur.mystore.pojo.Customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/cart/*")
public class BookController extends DAO{

	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO bookDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("book") Book book, BindingResult result, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		Customer u = (Customer)session.getAttribute("customer");
		book.setUser(u);
		u.setBook(book);
		Book cd=null;
//		Cart c = cartDao.insert(cart);
//		User user1 = cartDao.update(u);
//		 getSession().update(u);
		 List<Book> c=bookDao.list();
		 int i=0;
		 Book cw = null;
		 for(Book cc:c){
			 if(u.getPersonID()==cc.getId()){
				 cw = bookDao.updateCart(cc);
				 i=1;
				 return new ModelAndView("user-cart","c",cw);
			 }
		 }
	 if (i==0){
		  cd = bookDao.insert(book);
	 }
	
		return new ModelAndView("user-cart","c",cd);
	}

}
