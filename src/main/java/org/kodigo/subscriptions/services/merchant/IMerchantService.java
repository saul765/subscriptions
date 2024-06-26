package org.kodigo.subscriptions.services.merchant;

import org.kodigo.subscriptions.dto.MerchantDTO;
import org.kodigo.subscriptions.entity.MerchantEntity;

import java.util.List;

public interface IMerchantService {

    List<MerchantDTO> getAllMerchants();

    List<MerchantDTO> getMerchantsByName(String name);

    List<MerchantDTO> getMerchantByEmail(String email);

    List<MerchantDTO> getMerchantById(Integer merchantId);

    MerchantEntity getMerchantEntityById(Integer merchantId);

    MerchantDTO createMerchant(MerchantDTO merchantDTO);

    MerchantDTO updateMerchant(Integer merchantId, MerchantDTO merchantDTO);

    void deleteMerchant(Integer merchantId);


}
