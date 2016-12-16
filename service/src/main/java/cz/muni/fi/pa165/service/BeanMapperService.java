package cz.muni.fi.pa165.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Jakub Holy (saintjackie)
 */
public interface BeanMapperService {
    
    Mapper getMapper();    
    <T> T mapTo(Object obj, Class<T> mapToClass);    
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
}
