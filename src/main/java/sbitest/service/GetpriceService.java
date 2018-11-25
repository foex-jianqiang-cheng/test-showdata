/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbitest.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbitest.dao.PriceRepository;
import sbitest.entity.db.Price;
import sbitest.entity.db.ViewPrice;

@Service
@Transactional
public class GetpriceService {

    @Autowired
    private PriceRepository piceRepository;

    public Price create(Price price) {
        return piceRepository.save(price);
    }
    public List<ViewPrice> findPrcie() {
        List<ViewPrice> retlist= new ArrayList<ViewPrice>();

        List<Price> list = piceRepository.findPrcie();
        if (list == null || list.size() == 0) {
            return null;
        }

        try {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            String curMinute = "";
            double curMaxPrice = 0.00;
            double curMinPrice = 0.00;
            List<Price> tmplist= new ArrayList<Price>();
            
            //get the first obj one info
            Price tmpPrice = list.get(0);
            curMinute = sdFormat.format(tmpPrice.getCreateDate());
    
            for(Iterator<Price> itr = list.iterator(); itr.hasNext();){
                Price curPrice = itr.next();
                String itrMinute = sdFormat.format(curPrice.getCreateDate());
                if (itrMinute.equals(curMinute)){
                    tmplist.add(curPrice);
                    continue;
                } else {
                    Optional<Price> min = tmplist.stream().min(Comparator.comparing(x -> x.getBprice()));
                    Optional<Price> max = tmplist.stream().max(Comparator.comparing(x -> x.getBprice()));
                    System.out.print(max.get().getBprice());
                    System.out.print(min.get().getBprice());
                    retlist.add(new ViewPrice(curMinute, max.get().getBprice().subtract(min.get().getBprice())));
                    tmplist.clear();
                    tmplist.add(curPrice);
                    curMinute = itrMinute;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return retlist;
    }

}
