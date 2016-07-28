package com.abc.com.memberapp;

import android.content.Context;

import java.util.List;

/**
 * Created by hsh on 2016-07-27.
 */
public class MemberServiceImpl implements MemberService{
    MemberDAO dao;
    MemberBean session;
    public MemberServiceImpl(Context context) {
        dao = new MemberDAO(context);
    }


    @Override
    public String regist(MemberBean mem) {
        String msg = "";
        MemberBean temp = this.findById(mem.getId());
        if (temp == null) {
            System.out.println(mem.getId()+"가 존재하지 않음,가입가능한 ID");
            int result = dao.insert(mem);
            if (result==1) {
                msg = "success";
            } else {
                msg = "fail";
            }
        } else {
            System.out.println(mem.getId()+"가 존재함,가입 불가능한 ID");
            msg = "fail";
        }


        return msg;
    }


    @Override
    public void update(MemberBean mem) {
        int result = dao.update(mem);
        if (result == 1) {
            System.out.println("서비스 수정결과 성공");
			/*session = this.findById(mem.getId());*/
        } else{
            System.out.println("서비스 수정결과 실패");
        }

    }

    @Override
    public String delete(MemberBean member) {
        String result = "";
        System.out.println("id : "+member.getId());
        System.out.println("pw : "+member.getPw());
        System.out.println("dao delet : "+dao.delete(member));
        if (dao.delete(member) != 0) {
            result = "삭제성공";
        } else {
            result = "삭제실패";
        }
        return result;
    }


    @Override
    public int count() {
        // TODO Auto-generated method stub
        return dao.count();
    }


    @Override
    public MemberBean findById(String findID) {
        return dao.findById(findID);
    }


    @Override
    public List<?> list() {

        return dao.list();
    }


    @Override
    public List<?> findBy(String keyword) {
        // TODO Auto-generated method stub
        return (List<?>) dao.findByName(keyword);
    }






    @Override
    public MemberBean show() {

        return session;
    }


    @Override
    public void logout(MemberBean member) {
        if(member.getId().equals(session.getId())
                && member.getPw().equals(session.getPw())){
            session=null;

        }
    }
}
