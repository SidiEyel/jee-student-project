package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.dao.CompanyAerienneDao;
import com.rimbestprice.rimbestprice.models.CompanyAerienne;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// @WebServlet("/company")
public class CompanyAerienneServlet extends HttpServlet {
    private CompanyAerienneDao companyDao;

    public void init() {
        companyDao = new CompanyAerienneDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addCompany(request, response);
                break;
            case "update":
                updateCompany(request, response);
                break;
            case "delete":
                deleteCompany(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CompanyAerienne> companies = companyDao.getAllCompanyAeriennes();
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("/views/companies.jsp").forward(request, response);
    }

    private void addCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        CompanyAerienne company = new CompanyAerienne();
        company.setNom(nom);
        companyDao.addCompanyAerienne(company);
        response.sendRedirect("company");
    }

    private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        CompanyAerienne company = new CompanyAerienne();
        company.setId(id);
        company.setNom(nom);
        companyDao.updateCompanyAerienne(company);
        response.sendRedirect("company");
    }

    private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        companyDao.deleteCompanyAerienne(id);
        response.sendRedirect("company");
    }
}
