package cn.sercive;

import cn.domain.CURRENCY;
import cn.domain.TSUtility;
import cn.domain.readerType;
import com.service.LogService;

import java.util.List;

public class PenaltyService {
    /*public static void main(String[] args) {
        set();
    }*/

    public static void set(String s) {
        String sql = "select id,typename from readertype";
        List<readerType> readerTypes = CURRENCY.getForList(readerType.class, sql);
        for (readerType rt : readerTypes) {
            System.out.println("请给'" + rt.getTypename() + "'类型读者设置超过一天的罚金:");
            Double aDouble = TSUtility.readDouble();
            //int id = readerTypes.get(i).getId();
            String name = rt.getTypename();
            String sql1 = "UPDATE readertype SET penalMoney = ? WHERE typename = ?";
            //String sql1 = "insert into readertype set id=?,typename=?,fine=?";
            //CURRENCY.update(sql1, id,name,aDouble);
            CURRENCY.update(sql1, aDouble, name);
            System.out.println("添加成功！");
        }
        LogService.AddOperLog("操作员[" + s + "]修改罚金设置");
    }
}
