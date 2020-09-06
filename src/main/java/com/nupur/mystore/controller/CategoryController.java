package com.nupur.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;

import com.nupur.mystore.dao.CategoryDAO;
import com.nupur.mystore.exception.CategoryException;
import com.nupur.mystore.pojo.Category;
import com.nupur.mystore.validator.CategoryValidator;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.servlet.ModelAndView;



import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Qualifier;
@Controller
@RequestMapping("/category/*")
public class CategoryController {

		@Autowired
		@Qualifier("categoryValidator")
		CategoryValidator categoryValidator;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDAO;

		@InitBinder
		private void initBinder(WebDataBinder binder) {
			binder.setValidator(categoryValidator);
		}

		@RequestMapping(value = "/category/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("category") Category category, BindingResult result) throws Exception {
			
			categoryValidator.validate(category, result);
			
			if (result.hasErrors()) {
				return new ModelAndView("category-form", "category", category);
			}

			try {				
				category = categoryDAO.create(category.getTitle());
			} catch (CategoryException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			return new ModelAndView("category-success", "category", category);
			
		}

		@RequestMapping(value="/category/add", method = RequestMethod.GET)
		public ModelAndView initializeForm() throws Exception {			
			return new ModelAndView("category-form", "category", new Category());
		}


}