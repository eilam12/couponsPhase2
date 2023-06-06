package com.example.Project2.bl;

import com.example.Project2.exceptions.EmailOrPasswordAreWrongException;
import com.example.Project2.repositories.CompanyRepository;
import com.example.Project2.repositories.CouponRepository;
import com.example.Project2.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * abstract class that the class's in the BL layer will extend from.
 * this is how they could use the repositories , with the three objects:
 * CompanyRepository
 * CustomerRepository
 * CouponRepository
 */
@Service
@Scope("prototype")
public abstract class ClientBL {
    protected CompanyRepository companyRepository;
    protected CustomerRepository customerRepository;
    protected CouponRepository couponRepository;

    public ClientBL(CompanyRepository companyRepository, CustomerRepository customerRepository,
                    CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password) throws SQLException, EmailOrPasswordAreWrongException;
}
