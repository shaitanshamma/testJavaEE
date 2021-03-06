package ru.shamma.controller;


import ru.shamma.persist.Category;
import ru.shamma.persist.CategoryRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

@RequestScoped
@Named
public class CategoryConverter implements Converter<Category> {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        return categoryRepository.findById(Long.parseLong(value))
                .orElseThrow(() -> new ConverterException(new FacesMessage(String.format("%s is not correct id", value))));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
}
