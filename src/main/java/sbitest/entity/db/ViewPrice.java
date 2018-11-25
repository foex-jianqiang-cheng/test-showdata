/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbitest.entity.db;

import java.math.BigDecimal;

import lombok.Data;

//@Entity
@Data
//@AllArgsConstructor
public class ViewPrice {

    private String time;
    private BigDecimal fluctuation;

    public ViewPrice(String time, BigDecimal fluctuation) {
        this.time = time;
        this.fluctuation = fluctuation;
    }
}
