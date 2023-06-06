package com.example.Project2;

import com.example.Project2.beans.*;
import com.example.Project2.bl.AdminBL;
import com.example.Project2.bl.CompanyBL;
import com.example.Project2.bl.CustomerBL;
import com.example.Project2.bl.LoginManager;
import com.example.Project2.threads.CouponExpirationDailyJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;


@SpringBootApplication
public class Project2Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Project2Application.class, args);

//        CouponExpirationDailyJob couponExpirationDailyJob = ctx.getBean(CouponExpirationDailyJob.class);
//        Thread thread = new Thread(couponExpirationDailyJob);
//        thread.start();
//
        try {
//            LoginManager loginManager = ctx.getBean(LoginManager.class);
//
//            AdminBL adminBL = (AdminBL) loginManager.login(ClientType.ADMINISTRATOR, "admin@admin.com", "admin");
//            adminBL.login();
//            adminBL.addCompany();
//            adminBL.updateCompany();
//            adminBL.deleteCompany();
//            adminBL.getOneCompany();
//            adminBL.getAllCompanies();
//            adminBL.addCustomer();
//            adminBL.updateCustomer();
//            adminBL.deleteCustomer();
//            adminBL.getOneCustomer();
//            adminBL.getAllCustomers();
//
//            CompanyBL companyBL = (CompanyBL) loginManager.login(ClientType.COMPANY, "email5", "password5");
//            companyBL.login();
//            companyBL.addCoupon();
//            companyBL.updateCoupon();
//            companyBL.deleteCoupon();
//            companyBL.getCompanyCoupons();
//            companyBL.getCompanyCouponsByCategory();
//            companyBL.getCompanyCouponsByMaxPrice();
//            companyBL.getCompanyDetails();
//
//            CustomerBL customerBL = (CustomerBL) loginManager.login(ClientType.CUSTOMER, "", "");
//            customerBL.login();
//            customerBL.purchaseCoupon();
//            customerBL.getCustomersCoupons();
//            customerBL.getCustomerCouponsByCategory();
//            customerBL.getCustomerCouponsByMaxPrice();
//            customerBL.deleteCustomerPurchase();
//            customerBL.getAllCoupons();
//            customerBL.getCustomerDetails();
//
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        couponExpirationDailyJob.stop();
//        thread.interrupt();
    }
}