package com.cy;

import com.cy.dao.EmpMapper;
import com.cy.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryTest {

    public static void main(String[] args) throws Exception {
        //insertEmp();
        //findAll();
        //findAll2();
        //findEmpById();
        //findAll4();
        //updateEmpById();
        //findEmpByIds();
        //findEmpByEmpDaoId();
        findAllByEmpDao();
    }

    private static void findAllByEmpDao() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.findAll();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    private static void findEmpByEmpDaoId() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.findById(1);
        System.out.println(emp);
    }

    private static void findEmpByIds() throws Exception {
        //1.获取读取配置文件流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.根据路径以及传入参数执行sql
        ArrayList<Object> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        //int[] ids = {1,2,3};
        List<Emp> empList = sqlSession.selectList("com.cy.mapper.EmpMapper.findByIds", idList);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    private static void updateEmpById() throws Exception {
        //1.获取读取配置文件流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.根据路径和参数执行sql
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("王麻子");
        emp.setJob("金牌讲师");
        emp.setSalary(Double.valueOf("8000"));
        int update = sqlSession.update("com.cy.mapper.EmpMapper.updateEmpById", emp);
        sqlSession.commit();
        if (update > 0){
            System.out.println("修改成功！");
        }
    }

    private static void findAll4() throws Exception {
        //1.获取文件配置流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.根据指定sql路径以及参数执行sql
        Map map = new HashMap<>();
        map.put("minSal",3000);
        map.put("maxSal",4000);
        List<Emp> empList = sqlSession.selectList("com.cy.mapper.EmpMapper.findAll4", map);
        //5.打印结果
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    private static void findEmpById() throws Exception {
        //1.获取文件配置流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.通过配置流得到sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.通过sqlSessionFactory对象获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.根据路径执行SQL
        String sqlPath = "com.cy.mapper.EmpMapper.findEmpById";
        Emp emp = sqlSession.selectOne(sqlPath, 1);
        //5.打印查询结果
        System.out.println(emp);
    }

    private static void findAll2() throws Exception {
        //1.获取读取配置文件流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.根据文件流获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.通过sqlSessionFactory对象获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.根据给出的路径执行sql
        String sqlPath = "com.cy.mapper.EmpMapper.findAll2";
        //用map封装需要查询的字段
        HashMap<String, String> map = new HashMap<>();

        map.put("cols","id,name,job,salary");
        List<Emp> empList = sqlSession.selectList(sqlPath, map);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    private static void insertEmp() throws Exception {
        //1.获取读取配置文件流
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.根据配置文件流，得到sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.通过sqlSessionFactory创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行sql获取结果
        int insert = sqlSession.insert("com.cy.mapper.EmpMapper.insertEmpData");
        //5.提交事务
        sqlSession.commit();
        //5.打印结果
        System.out.println("执行成功："+insert);
    }

    private static void findAll() throws Exception {
        //1.读取配置文件中的配置
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.通过读入配置创建sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.通过sqlSessionFactory对象创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行SQL语句，查询emp表中的所有记录
        String sqlId = "com.cy.mapper.EmpMapper.findAll";
        List<Emp> empList = sqlSession.selectList(sqlId);
        //5.打印list集合
        for (Emp emp : empList) {
            System.out.println(emp);
        }

    }
}
