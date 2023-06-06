package com.example.Project2.bl;

import com.example.Project2.beans.Category;
import com.example.Project2.beans.Company;
import com.example.Project2.beans.Coupon;
import com.example.Project2.exceptions.CompanyIdDoesntMachTheCompanyException;
import com.example.Project2.exceptions.IdDoesNotExistException;
import com.example.Project2.exceptions.TitleAlreadyExistException;
import com.example.Project2.repositories.CompanyRepository;
import com.example.Project2.repositories.CouponRepository;
import com.example.Project2.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CompanyBL class is a class in the BL layer that has the methods that a Company can use, and this class implements the business
 * logic of the program in the methods. the class will be used as an object that lets a Company make actions in the program.
 */
@Service
@Scope("prototype")
public class CompanyBL extends ClientBL {
    private Company theCompany;

    public CompanyBL(CompanyRepository companyRepository, CustomerRepository customerRepository,
                     CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    /**
     * Checks if the login parameters are correct when trying to log in as a Company.
     * if the company was found than the "theCompany" parameter of the class will be the object that
     * companyRepository.findByEmailAndPassword() returns.
     *
     * @param email
     * @param password
     * @return true if the login parameters were valid and were found in the database. otherwise false.
     */
    @Override
    public boolean login(String email, String password) {
        Company company = companyRepository.findByEmailAndPassword(email, password);
        if (company == null)
            return false;
        else {
            theCompany = company;
            return true;
        }
    }

    /**
     * this method is to add a coupon with the business logic of the program.
     *
     * @param coupon is the coupon to add.
     * @throws TitleAlreadyExistException             if another coupon in the same company has the same title.
     * @throws CompanyIdDoesntMachTheCompanyException if the coupon id is not equal to the company id.
     */
    public void addCoupon(Coupon coupon) throws TitleAlreadyExistException, CompanyIdDoesntMachTheCompanyException {
        if (coupon.getCompany().getId() != theCompany.getId()) // a checking just for safety
            throw new CompanyIdDoesntMachTheCompanyException("The coupon.company does not match this company");

        List<Coupon> coupons = getCompanyCoupons();
        if (coupons.stream().anyMatch(c -> c.getTitle().equals(coupon.getTitle())))
            throw new TitleAlreadyExistException("Title already exist");

        couponRepository.save(coupon);

        // to update theCompany after making changes in it and adjusting it to the database
        // no need to throw custom Exception because already logged in
        theCompany = companyRepository.findById(theCompany.getId()).orElseThrow();
    }

    /**
     * this method is to update a coupon with the business logic of the program.
     * the method will check if the title of the coupon is already exist without
     * including the same coupon that wanting to update.
     *
     * @param coupon is the coupon to update
     * @throws TitleAlreadyExistException if another coupon in the same company has the same title.
     */
    public void updateCoupon(Coupon coupon) throws TitleAlreadyExistException {
        List<Coupon> coupons = getCompanyCoupons();
        if (coupons.stream().anyMatch(c -> c.getTitle().equals(coupon.getTitle()) && c.getId() != coupon.getId()))
            throw new TitleAlreadyExistException("Title already exist");
        else {
            couponRepository.save(coupon);

            // to update theCompany after making changes in it and adjusting it to the database
            // no need to throw custom Exception because already logged in
            theCompany = companyRepository.findById(theCompany.getId()).orElseThrow();
        }
    }

    /**
     * this method is to delete a coupon with the business logic of the program.
     * to delete a coupon must need to delete the purchases of the coupon first.
     *
     * @param couponId is the id of the coupon to delete.
     * @throws IdDoesNotExistException if coupon was not found.
     */
    public void deleteCoupon(int couponId) throws IdDoesNotExistException {
        if (getCompanyCoupons().stream().anyMatch(c -> c.getId() == couponId)) {
            couponRepository.deletePurchaseByCouponId(couponId);
            couponRepository.deleteById(couponId);
            // to update theCompany after making changes in it and adjusting it to the database
            // no need to throw custom Exception because already logged in
            theCompany = companyRepository.findById(theCompany.getId()).orElseThrow();
        } else
            throw new IdDoesNotExistException("Coupon was not found");
    }

    /**
     * this method is to get all coupons of the specific company.
     *
     * @return List of Coupon or an empty List.
     */
    public List<Coupon> getCompanyCoupons() {
        return theCompany.getCoupons();
    }

    /**
     * this method is to get all coupons of the specific company by specific category.
     * the method get all the company coupons and checks which is in the specific category and filter the List by that.
     *
     * @param category is the category to filter the coupons by.
     * @return List of Coupon or an empty List.
     */
    public List<Coupon> getCompanyCouponsByCategory(Category category) {
        List<Coupon> coupons = getCompanyCoupons();
        return coupons.stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
    }

    /**
     * this method is to get all coupons of the specific company up to a maximum price.
     * the method get all the company coupons and checks which cost until(including) the maximum price
     * and filter the List by that.
     *
     * @param maxPrice is the price to filter the coupons by.
     * @return List of Coupon or an empty List.
     */
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
        List<Coupon> coupons = getCompanyCoupons();
        return coupons.stream().filter(c -> c.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * this method is to get the company details.
     *
     * @return Company object - the "theCompany" parameter of the class.
     */
    public Company getCompanyDetails() {
        return theCompany;
    }
}
