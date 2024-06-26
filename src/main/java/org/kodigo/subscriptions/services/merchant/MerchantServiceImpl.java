package org.kodigo.subscriptions.services.merchant;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.MerchantDTO;
import org.kodigo.subscriptions.entity.MerchantEntity;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.MerchantMapper;
import org.kodigo.subscriptions.repositories.IMerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MerchantServiceImpl implements IMerchantService {

    private IMerchantRepository merchantRepository;

    private MerchantMapper merchantMapper;

    @Override
    public List<MerchantDTO> getAllMerchants() {
        log.info("[Service]: Starting execution getAllMerchants");
        List<MerchantDTO> response = merchantRepository.findAll()
                .stream()
                .map(merchantMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Merchants not found");
        }

        log.info("[Service]: Finishing execution getAllMerchants");
        return response;
    }

    @Override
    public List<MerchantDTO> getMerchantsByName(String name) {
        log.info("[Service]: Starting execution getMerchantsByName");
        List<MerchantDTO> response = merchantRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(merchantMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Merchants not found");
        }

        log.info("[Service]: Finishing execution getMerchantsByName");
        return response;
    }

    @Override
    public List<MerchantDTO> getMerchantByEmail(String email) {
        log.info("[Service]: Starting execution getMerchantByEmail");
        List<MerchantDTO> response = merchantRepository.findByEmail(email)
                .stream()
                .map(merchantMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Merchants not found");
        }
        log.info("[Service]: Finishing execution getMerchantByEmail");
        return response;
    }


    @Override
    public List<MerchantDTO> getMerchantById(Integer merchantId) {

        log.info("[Service]: Starting execution getMerchantById");
        List<MerchantDTO> response = merchantRepository.findById(merchantId.longValue())
                .stream()
                .map(merchantMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Merchants not found");
        }
        log.info("[Service]: Finishing execution getMerchantById");
        return response;
    }

    @Override
    public MerchantEntity getMerchantEntityById(Integer merchantId) {
        log.info("[Service]: Starting execution getMerchantEntityById");
        MerchantEntity response = merchantRepository.findById(merchantId.longValue())
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Merchant not found"));
        log.info("[Service]: Finishing execution getMerchantEntityById");
        return response;
    }

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        log.info("[Service]: Starting execution createMerchant");

        MerchantDTO response = Optional.ofNullable(merchantDTO)
                .map(merchantMapper::dtoToEntity)
                .map(merchantRepository::save)
                .map(merchantMapper::entityToDto)
                .orElseThrow(
                        () -> AppException.internalServerError(ResponseCodeEnum.CODE_500, "Error creating merchant")
                );
        log.info("[Service]: Finishing execution createMerchant");
        return response;
    }

    @Override
    @Transactional
    public MerchantDTO updateMerchant(Integer merchantId, MerchantDTO merchantDTO) {

        log.info("[Service]: Starting execution updateMerchant");
        MerchantDTO response = merchantRepository.findById(merchantId.longValue())
                .map(merchant -> {
                    Optional.ofNullable(merchantDTO.getName()).ifPresent(merchant::setName);
                    Optional.ofNullable(merchantDTO.getEmail()).ifPresent(merchant::setEmail);
                    Optional.ofNullable(merchantDTO.getPhoneNumber()).ifPresent(merchant::setContactNumber);
                    Optional.ofNullable(merchantDTO.getAddress()).ifPresent(merchant::setAddress);
                    return merchant;
                })
                .map(merchantRepository::save)
                .map(merchantMapper::entityToDto)
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Merchant not found"));

        log.info("[Service]: Finishing execution updateMerchant");
        return response;
    }

    @Override
    public void deleteMerchant(Integer merchantId) {
        log.info("[Service]: Starting execution deleteMerchant");
        merchantRepository.findById(merchantId.longValue())
                .map(merchant -> {
                    merchantRepository.deleteById(merchant.getId());
                    return merchant;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Merchant not found"));
        log.info("[Service]: Finishing execution deleteMerchant");
    }
}
