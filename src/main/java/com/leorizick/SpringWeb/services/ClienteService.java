package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Cidade;
import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.domain.Endereco;
import com.leorizick.SpringWeb.domain.enums.Perfil;
import com.leorizick.SpringWeb.domain.enums.TipoCliente;
import com.leorizick.SpringWeb.dto.ClienteDTO;
import com.leorizick.SpringWeb.dto.ClienteNewDto;
import com.leorizick.SpringWeb.repositories.ClienteRepository;
import com.leorizick.SpringWeb.repositories.EnderecoRepository;
import com.leorizick.SpringWeb.security.UserSS;
import com.leorizick.SpringWeb.services.exception.AuthorizationException;
import com.leorizick.SpringWeb.services.exception.DataIntegrityException;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado!");
        }
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
    newObj.setName(obj.getName());
    newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir pois há pedidos relacionadas");
        }
    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDto(ClienteNewDto objDto){
        Cliente cli = new Cliente(null, objDto.getName(), objDto.getEmail(), objDto.getDocumento(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cidade);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }


}
