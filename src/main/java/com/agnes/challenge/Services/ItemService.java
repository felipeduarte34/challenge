package com.agnes.challenge.Services;

import com.agnes.challenge.DTO.ItemDTO;
import com.agnes.challenge.Entities.*;
import com.agnes.challenge.Repositories.ItemRepository;
import com.agnes.challenge.Repositories.StatusRepository;
import com.agnes.challenge.Repositories.TeamMembersRepository;
import com.agnes.challenge.Repositories.TypeRepository;
import com.agnes.challenge.Repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    final
    ItemRepository itemRepository;

    final
    ProjectRepository projectRepository;

    final
    StatusRepository statusRepository;

    final
    TeamMembersRepository teamMembersRepository;

    final
    TypeRepository typeRepository;

    public ItemService(final ItemRepository itemRepository, final ProjectRepository projectRepository, final StatusRepository statusRepository,
        final TeamMembersRepository teamMembersRepository, final TypeRepository typeRepository) {
        this.itemRepository = itemRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.teamMembersRepository = teamMembersRepository;
        this.typeRepository = typeRepository;
    }

    @Transactional
    public Boolean isItemIdPresent(Integer id) {

        return itemRepository.findById(id).isPresent();
    }

    @Transactional
    public Item findByItemId(Integer id) {

        Optional<Item> byId = itemRepository.findByItemId(id);

        if(byId.isPresent()){
            return byId.get();
        } else {
            throw new RuntimeException("Could not find a project with the id: " + id);
        }
    }

    @Transactional
    public List<Item> findByTitle(String title) {

        return itemRepository.findByTitle(title);
    }

    @Transactional
    public List<ItemDTO> getAllItems() {

        List<Item> allItems = itemRepository.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item :
                allItems) {
            ItemDTO temp = new ItemDTO();
            temp.itemId = item.getItemId();
            temp.title = item.getTitle();
            temp.description = item.getDescription();
            temp.projectId = item.getProject().getProjectId();
            temp.projectName = item.getProject().getName();
            temp.statusOfItem = item.getStatusOfItem().getStatusName();
            temp.typeOfItem = item.getTypeOfItem().getName();
            temp.teamMemberId = item.getTeamMemberOfItem().getMemberId();
            temp.teamMemberOfProjectLastName = item.getTeamMemberOfItem().getLastName();
            temp.teamMemberOfProjectFirstName = item.getTeamMemberOfItem().getFirstName();
            temp.teamMemberOfProjectEmailAddress = item.getTeamMemberOfItem().getEmailAddress();
            itemDTOS.add(temp);
        }
        return itemDTOS;
    }

    @Transactional
    public Item saveNewItem(ItemDTO itemDTO) {

        Project project = projectRepository.findProjectByProjectId(itemDTO.projectId);
        Optional<Status> byStatusName = statusRepository.findByStatusName(itemDTO.statusOfItem);
        Optional<Type> byTypeName = typeRepository.findByName(itemDTO.typeOfItem);
        Optional<TeamMembers> byMemberId = teamMembersRepository.findByMemberId(itemDTO.teamMemberId);


        TeamMembers teamMembers = null;
        if (!byMemberId.isPresent()) {

            TeamMembers newMember = new TeamMembers(itemDTO.teamMemberId);
            teamMembers = teamMembersRepository.save(newMember);
        } else {
            teamMembers = byMemberId.get();
        }

        Status status = null;
        if (!byStatusName.isPresent()) {

            Status newStatus = new Status(itemDTO.statusOfItem);
            status = statusRepository.save(newStatus);
        } else {

            status = byStatusName.get();
        }

        Type type = null;
        if (!byTypeName.isPresent()) {
            Type newType = new Type(itemDTO.typeOfItem);
            newType = typeRepository.save(newType);
        } else {

            type = byTypeName.get();
        }

        Item itemToBeSaved = new Item(itemDTO, project, teamMembers, status, type);
        Item savedItem = itemRepository.save(itemToBeSaved);
        return savedItem;
    }

    @Transactional
    public Item updateItemById(Integer id, ItemDTO itemToBeUpdated) {

        Item item;
        Status status;
        Type type;
        TeamMembers teamMembers;

        Project project = projectRepository.findProjectByProjectId(itemToBeUpdated.projectId);
        Optional<Status> statusOptional = statusRepository.findByStatusName(itemToBeUpdated.statusOfItem);

        Optional<Type> typeOptional = typeRepository.findByName(itemToBeUpdated.typeOfItem);

        Optional<TeamMembers> teamMembersOptional = teamMembersRepository.findByMemberId(itemToBeUpdated.teamMemberId);

        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {

            throw new RuntimeException("Could not find item with the id: " + id);
        } else {

            item = itemOptional.get();
            status = statusOptional.get();
            type = typeOptional.get();
            teamMembers = teamMembersOptional.get();

            status.setStatusName(itemToBeUpdated.statusOfItem);
            type.setName(itemToBeUpdated.typeOfItem);
            teamMembers.setMemberId(itemToBeUpdated.teamMemberId);

            item.setTitle(itemToBeUpdated.title);
            item.setDescription(itemToBeUpdated.description);
            item.setStatusOfItem(status);
            item.setTypeOfItem(type);
            item.setTeamMemberOfItem(teamMembers);
            item.setProject(project);
        }
        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItemById(Integer id) {

        itemRepository.deleteById(id);
    }
}
