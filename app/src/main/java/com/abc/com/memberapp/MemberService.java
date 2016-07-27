package com.abc.com.memberapp;

import java.util.List;

/**
 * Created by hsh on 2016-07-27.
 */
public interface MemberService {
    public String regist(MemberBean mem);
    public MemberBean findById(String findID);
    public void update(MemberBean stu2);
    public String delete(MemberBean member);
    public void logout(MemberBean member);
    public int count();
    public List<?> list();
    public List<?> findBy(String keyword);
    public MemberBean show();
}
