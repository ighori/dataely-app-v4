package com.dataely.app.service.impl;

import com.dataely.app.domain.Contact;
import com.dataely.app.repository.ContactRepository;
import com.dataely.app.service.ContactService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Contact}.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Request to save Contact : {}", contact);
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> partialUpdate(Contact contact) {
        log.debug("Request to partially update Contact : {}", contact);

        return contactRepository
            .findById(contact.getId())
            .map(
                existingContact -> {
                    if (contact.getFirstName() != null) {
                        existingContact.setFirstName(contact.getFirstName());
                    }
                    if (contact.getLastName() != null) {
                        existingContact.setLastName(contact.getLastName());
                    }
                    if (contact.getName() != null) {
                        existingContact.setName(contact.getName());
                    }
                    if (contact.getRole() != null) {
                        existingContact.setRole(contact.getRole());
                    }
                    if (contact.getEmail() != null) {
                        existingContact.setEmail(contact.getEmail());
                    }
                    if (contact.getPhone() != null) {
                        existingContact.setPhone(contact.getPhone());
                    }
                    if (contact.getExtension() != null) {
                        existingContact.setExtension(contact.getExtension());
                    }
                    if (contact.getMobile() != null) {
                        existingContact.setMobile(contact.getMobile());
                    }
                    if (contact.getLocation() != null) {
                        existingContact.setLocation(contact.getLocation());
                    }
                    if (contact.getAddressLine1() != null) {
                        existingContact.setAddressLine1(contact.getAddressLine1());
                    }
                    if (contact.getAddressLine2() != null) {
                        existingContact.setAddressLine2(contact.getAddressLine2());
                    }
                    if (contact.getCity() != null) {
                        existingContact.setCity(contact.getCity());
                    }
                    if (contact.getCountry() != null) {
                        existingContact.setCountry(contact.getCountry());
                    }
                    if (contact.getImage() != null) {
                        existingContact.setImage(contact.getImage());
                    }
                    if (contact.getImageContentType() != null) {
                        existingContact.setImageContentType(contact.getImageContentType());
                    }

                    return existingContact;
                }
            )
            .map(contactRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Contact> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        return contactRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);
        contactRepository.deleteById(id);
    }
}
