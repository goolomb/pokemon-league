package cz.muni.fi.pa165.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
/**
 *
 * @author Jakub Holy (saintjackie)
 */

@Service
public class BeanMapperServiceImpl implements BeanMapperService{
    
    @Inject
    private Mapper mapper;

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public <T> T mapTo(Object obj, Class<T> mapToClass) {        
        return mapper.map(obj,mapToClass);
    }

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> col = new ArrayList<T>();
        for(Object obj:objects){
            col.add(mapper.map(obj,mapToClass));
        }
        return col;        
    }
    
}
