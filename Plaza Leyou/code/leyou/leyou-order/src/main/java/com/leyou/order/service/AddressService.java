package com.leyou.order.service;

import com.leyou.order.pojo.Address;

import java.util.List;

public interface AddressService {

    public void addAddressByUserId(Address address);

    public List<Address> queryAddressByUserId();

    public void updateAddressByUserId(Address address);

    public void deleteAddress(Long addressId);

    public Address queryAddressById(Long addressId);
}
