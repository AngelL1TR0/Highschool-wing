package service;

import entity.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupsService {

    private Map<String, Group> groups = new HashMap<>();

    public boolean add(Group group) {
        if(groups.containsKey(group.getId())) {
            return false;
        } else{
            groups.put(group.getId(), group);
            return true;
        }
    }

    public boolean update(Group group) {
        if(groups.containsKey(group.getId())) {
            groups.put(group.getId(), group);
            return true;
        } else{
            return false;
        }
    }

    public List<Group> list() {
        return new ArrayList<>(
                groups.values()
        );
    }

    public boolean deleteGroup(String id) {
        if(groups.containsKey(id)) {
            groups.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
