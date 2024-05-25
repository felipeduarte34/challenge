package com.agnes.challenge.Entities;

import com.agnes.challenge.DTO.ItemDTO;
import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    Integer itemId;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status statusOfItem;

    @ManyToOne
    @JoinColumn(name = "type_Id")
    Type typeOfItem;


    @ManyToOne
    @JoinColumn(name = "member_id")
    TeamMembers teamMemberOfItem;

    public Item(ItemDTO itemDTO, Project project, TeamMembers teamMembers, Status status, Type type) {

        this.title = itemDTO.title;
        this.description = itemDTO.description;
        this.project = project;
        this.teamMemberOfItem = teamMembers;
        this.statusOfItem = status;
        this.typeOfItem = type;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatusOfItem() {
        return statusOfItem;
    }

    public void setStatusOfItem(Status statusOfItem) {
        this.statusOfItem = statusOfItem;
    }

    public Type getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(Type typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    public TeamMembers getTeamMemberOfItem() {
        return teamMemberOfItem;
    }

    public void setTeamMemberOfItem(TeamMembers teamMemberOfItem) {
        this.teamMemberOfItem = teamMemberOfItem;
    }

    public Item() {
    }

    public Item(ItemDTO itemDTO) {

        this.title = itemDTO.title;
        this.description = itemDTO.description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectId=" + project +
                ", statusId=" + statusOfItem +
                ", typeId=" + typeOfItem +
                '}';
    }
}
