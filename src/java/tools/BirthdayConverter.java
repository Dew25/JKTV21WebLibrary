/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Melnikov
 */
public class BirthdayConverter {
    private LocalDate birthDay;
    
    public BirthdayConverter() {
    }

    public int getBirthDay(){
        return this.birthDay.getDayOfMonth()+1;
    }
    
    public int getBirthMonth(){
        return this.birthDay.getMonthValue();
    }
    
    public int getBirthYear(){
        return this.birthDay.getYear();
    }

    public void setBirthday(Date birthDayDate) {
        this.birthDay = birthDayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public Date getDate(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month-1,day,0,0);
        return calendar.getTime();
    }
    
}
