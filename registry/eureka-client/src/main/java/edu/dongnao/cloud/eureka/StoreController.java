package edu.dongnao.cloud.eureka;

import com.google.common.collect.Lists;
import edu.dongnao.cloud.commons.Store;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Profile("provider")
public class StoreController {
    List<Store> stores = new ArrayList<>();
    {
        stores.add(new Store(1, "武汉5号仓", "武汉市"));
        stores.add(new Store(2, "南昌3号仓", "南昌市"));
        stores.add(new Store(3, "广州6号仓", "广州市"));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    public List<Store> getStores(){
        return stores;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/store/{storeId}")
    public Store getStore(@PathVariable("storeId") Integer storeId){
        Optional<Store> storeOptional = stores.stream().filter(e->e.getId().equals(storeId)).findAny();
        Store s = storeOptional.isPresent() ? 
                storeOptional.get() : 
                new Store(0, "虚无仓库", "您要找的仓库不存在");
        return s;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}")
    public Store update(@PathVariable("storeId") Integer storeId, Store store){
        
        return store;
    }
}
