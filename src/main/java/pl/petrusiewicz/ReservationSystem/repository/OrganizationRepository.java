package pl.petrusiewicz.ReservationSystem.repository;

import pl.petrusiewicz.ReservationSystem.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {

    private List<Organization> organizations = new ArrayList<>();

    public void add(Organization organization){
        organizations.add(organization);
    }

    public List<Organization> getAll(){
        return organizations;
    }

    public Organization get(int id){
        return organizations.get(id);
    }

    public void remove(int id){
        organizations.remove(id);
    }

    public void update(int id, Organization organization){
        organizations.set(id, organization);
    }

    public boolean isExist(String name){
        for(Organization org: organizations){
            if (org.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Organization findByName(String name){
        for(Organization organization: organizations){
            if (organization.getName().equals(name)){
                return organization;
            }
        }
        return null;
    }


}
