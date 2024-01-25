package com.example.pessoa.services;

import com.example.pessoa.DTOs.RequestAddressDTO;
import com.example.pessoa.domain.Address;
import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.repositories.AddressRepository;
import com.example.pessoa.repositories.PessoaRepository;
import com.example.pessoa.resources.AddressResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Address> findAll(){
        return repository.findAll();
    }

    public Address findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Address insert(RequestAddressDTO obj) {
        Address newAddress = new Address(obj);
        repository.save(newAddress);

        Long idPessoa = obj.idPessoa();
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoa.setEndereco(newAddress);
        pessoaRepository.save(pessoa);

        return repository.save(newAddress);
    }

    public Address update(Long id, RequestAddressDTO dto) {
        Address enderecoAtual = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não" +
                " encontrado com ID: " + id));

        updateData(enderecoAtual, dto);

        return repository.save(enderecoAtual);
    }

    private void updateData(Address entity, RequestAddressDTO dto) {
        if (dto.nomeDaRua() != null){
            entity.setNomeDaRua(dto.nomeDaRua());
        }
        if (dto.numeroDaCasa() != null){
            entity.setNumeroDaCasa(dto.numeroDaCasa());
        }
        if (dto.bairro() != null) {
            entity.setBairro(dto.bairro());
        }
        if (dto.cidade() != null) {
            entity.setCidade(dto.cidade());
        }
        if (dto.estado() != null) {
            entity.setEstado(dto.estado());
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
