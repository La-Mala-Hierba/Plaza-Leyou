package com.leyou.order.service.impl;

import com.leyou.common.pojo.UserInfo;
import com.leyou.order.interceptor.LoginInterceptor;
import com.leyou.order.pojo.Address;
import com.leyou.order.repository.AddressRepository;
import com.leyou.order.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Crear una dirección del envío nueva
     * @return
     */
    @Override
    public void addAddressByUserId(Address address) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        address.setId(null);
        address.setUserId(userInfo.getId());
        this.setDefaultAddress(address);
        this.addressRepository.save(address);
    }

    private void setDefaultAddress(Address address) {
        if (address.isDefaultAddress()) {
            //if this default address exists, then the rest of addresses under this user cannot be default
            List<Address> addressList = this.queryAddressByUserId();
            addressList.forEach(one -> {
                if (one.isDefaultAddress()) {
                    one.setDefaultAddress(false);
                    this.addressRepository.save(one);
                }
            });
        }
    }

    /**
     * Buscar lista de direcciones del envío con userId
     * @return
     */
    @Override
    public List<Address> queryAddressByUserId() {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        return this.addressRepository.findAddressesByUserId(userInfo.getId());
    }

    /**
     * Modificar dirección del envío
     * @param address
     * @return
     */
    @Override
    public void updateAddressByUserId(Address address) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        address.setUserId(userInfo.getId());
        this.setDefaultAddress(address);
        this.addressRepository.save(address);
    }

    /**
     * Eliminar dirección del envío
     * @param addressId
     * @return
     */
    @Override
    public void deleteAddress(Long addressId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        Address address = this.addressRepository.findByIdAndUserId(addressId, userInfo.getId());
        this.addressRepository.delete(address);
    }

    /**
     * Buscar dirección del envío con addressId
     * @param addressId
     * @return
     */
    @Override
    public Address queryAddressById(Long addressId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        return this.addressRepository.findByIdAndUserId(addressId, userInfo.getId());
    }
}
