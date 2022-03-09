package com.service;

import com.dao.LoginLogDAO;
import com.dao.OperateLogDAO;


public class LogService {
   private final LoginLogDAO daoLoginLog = new LoginLogDAO();
   private final OperateLogDAO daoOperateLog = new OperateLogDAO();

   public void SLL() {
       System.out.println("登录日志如下：");
       daoLoginLog.ShowLoginLog();
   }

   public void SOL(String s) {
       System.out.println("员工工作日志如下：");
       daoOperateLog.ShowOperLog(s);
   }

   public void ALL(String s) {
       daoLoginLog.AddLoginLog(s);
   }

   public void AOL(String s) {
       daoOperateLog.AddOperLog(s);
   }
}
