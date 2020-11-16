package com.leyou.order.repository;

import com.leyou.order.pojo.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    public List<Address> findAddressesByUserId(Long userId);

    public Address findByIdAndUserId(Long addressId, Long id);
}
