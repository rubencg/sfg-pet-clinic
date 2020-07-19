package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntitiy;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntitiy, ID extends Long> implements CrudService<T, ID> {
    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    public T findById(ID id){
        return map.get(id);
    }

    public T save(T object){
        if(object != null){
            if(object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    public void deleteById(ID id){
        map.remove(id);
    }

    public void delete(T object){
        map.entrySet().removeIf(e -> e.getValue() == object);
    }

    protected Long getNextId(){
        Set<Long> idSet = map.keySet();
        return idSet.size() == 0 ? 1 : Collections.max(idSet) + 1;
    }
}
