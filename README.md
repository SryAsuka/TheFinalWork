# TheFinalWork
A big final job for Java programming


# 仓库管理系统

## 新建数据库
  ### 创建数据库 Warehouse
  
    Create database Warehose;
  
  ### 创建表
  管理员（管理员号(Uid)，账号(Uacc)，密码(Upwd)，姓名(Uname)，电话(Uphone)，等级(Upower)）
  
    Create table if not exists user (
      uid char(4),
      uacc varchar(10),
      upwd varchar(16),
      uname varchar(20)  primary key,
      uphone varchar(11),
      upower int
    );
    
    insert into user values ("U001","admin","123456","张三","12345678901",1);
  
  仓库 （仓库号(Wid)，仓库名(Wname)，仓库地址(Wadd)）
  
    Create table if not exists wh (
      wid char(4),
      wname varchar(20) primary key,
      wadd varchar(50)
    );
    
    insert into wh values ("W001","A","重庆");
  
  货物（货物号(Gid)，货物名(Gname)，货物类型(Gtype)）
  
    Create table if not exists goods (
      gid char(4),
      gname varchar(20) primary key,
      gtype varchar(20)
      );


    insert into goods values ("G001","苹果","水果");
    
  
  库存（编号(Sid),货物名(Gname),仓库名(Wname),库存量(SStocks)）
  
    Create table if not exists stocks (
      sid char(4) primary key,
      gname varchar(20),
      wname varchar(20),
      sstocks int,
      foreign key(gname) references goods(gname),
      foreign key(wname) references wh(wname)
    );
  
    insert into stocks values ("S001","苹果","A",500);
  
  入库（入库号(Iid),货物名(Gname),仓库名(Wname),入库量(Istocks),管理员名(Uname),入库时间(IDate)）
  
    Create table if not exists istocks (
      iid char(4) primary key,
      gname varchar(20),
      wname varchar(20),
      istocks int,
      uname varchar(20),
      idate date,
      foreign key(gname) references goods(gname),
      foreign key(wname) references wh(wname),
      foreign key(uname) references user(uname)
    );
    
    insert into istocks values ("I001","苹果","A",500,"张三",now());
  
  出库（出库号(Oid),货物名(Gname),仓库号(Wname),出库量(Ostocks),管理员编号(Uid),出库时间(ODate)）
  
    Create table if not exists ostocks (
      oid char(4) primary key,
      gname varchar(20),
      wname varchar(20),
      ostocks int,
      uname varchar(20),
      odate date,
      foreign key(gname) references goods(gname),
      foreign key(wname) references wh(wname),
      foreign key(uname) references user(uname)
    );

    insert into ostocks values ("T001","苹果","A",100,"张三",now());

## 分配任务

 ### 管理员表
 
  - 登录账号（允许输入三次），然后返回管理员等级，最高管理员（1），普通仓管（2） （形参账号密码，返回值int）
  - 增加管理员（全部形参，无返回值）
  - 返回全部管理员表信息 (无形参，返回值arraylist)
    
  >PS:(换回值为arraylist,下面是例子)
  >https://blog.csdn.net/weixin_45228360/article/details/119949787
    
 ```ruby
     public ArrayList<String> selectData(){
        ArrayList<String> arrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            student student = new student();
            connection = useSql.getConnection();
            String sql = "SELECT * FROM `studens`";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getString("age"));
                student.setProvince(resultSet.getString("province"));
                student.setPhoneNum(resultSet.getString("phoneNum"));
                arrayList.add(student.getName());
                arrayList.add(student.getAge());
                arrayList.add(student.getProvince());
                arrayList.add(student.getPhoneNum());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            useSql.release(connection,preparedStatement,resultSet);
        }
        return  arrayList;
    }
```
  - 修改管理员信息,根据姓名修改（无法修改管理员等级）  (形参 （旧名 + 新名 + ...） 除等级以外的，无返回值)
  - 根据 管理员id 查找管理员信息（形参（姓名） 返回值arraylist）
  - 删除管理员信息,根据姓名删除 （若等级为1，无法删除）（形参 （姓名） ，无返回值）

>之后的要求差不多都是这样

  ### 仓库表
  
  - 增加仓库
  - 查看全部仓库
  - 根据仓库名删除仓库信息
  - 根据仓库名查找仓库信息
  - 根据仓库名修改仓库信息

  ### 货物
  
  - 增加货物
  - 查看全部货物
  - 根据货物名删除货物信息
  - 根据货物名查找货物信息
  - 根据货物名修改货物信息
  
  ### 库存表
  
  - （增加/减少库存在 入库表/出库表 中）
  - 查看全部库存
  - 根据 货物名/仓库名 查看库存 （分开写两个方法 sql语句变一下）
  - 根据 货物名/仓库名 删除库存 （同上）
  
  ### 入库表
  
  - 增加入库信息（同时库存表 新增列 或 增加库存量 ）（需要点MySQL的知识）
    > https://blog.csdn.net/weixin_43207025/article/details/106380505 [MySQL的触发器原理]
  - 删除入库信息（同时库存表 减少库存量 同时 库存量为0则删除列）
  - 查看全部入库信息
  
  ### 出库表
  
  - 增加出库信息（同时库存表 减少库存量 ）
  - 删除出库信息（同时库存表 恢复库存量）
  - 查看全部出库信息
  
```ruby
truncate table stocks;
truncate table istocks;
truncate table ostocks;

alter table warehouse.stocks
    modify sid int  auto_increment;

DELIMITER $$
drop trigger if exists trigger_input;
CREATE  TRIGGER trigger_input AFTER INSERT ON istocks FOR EACH ROW
BEGIN
    SET @a=NEW.gname;
    SET @b=NEW.wname;
    SET @d=NEW.istocks;
    if (select stocks.gname from stocks where stocks.gname = @a AND stocks.wname = @b ) is  null then
        insert into stocks(gname,wname,sstocks) values (@a,@b,@d);
    else
        update stocks
        set stocks.sstocks = stocks.sstocks + @d
        where stocks.gname = @a and stocks.wname = @b;
        update stocks
        set stocks.sid = sid - 1;
    end if;
END;
$$

DELIMITER $$
drop trigger if exists trigger_update_input;
CREATE  TRIGGER trigger_update_input AFTER UPDATE ON istocks FOR EACH ROW
BEGIN
    SET @a=NEW.istocks;
    SET @b=OLD.istocks;
    SET @c=NEW.istocks-OLD.istocks;
    SET @e=OLD.gname;
    SET @f=OLD.wname;
    if (select stocks.gname from stocks where stocks.gname = @e AND stocks.wname = @f ) is not null then
        if(select stocks.sstocks from stocks where stocks.sstocks + @c > 0 and stocks.gname = @e AND stocks.wname = @f ) is not null then
            update stocks
            set stocks.sstocks = stocks.sstocks + @c
            where stocks.gname = @e and stocks.wname = @f;
        end if;
    end if;
END;
$$

DELIMITER $$
drop trigger if exists trigger_delete_input;
CREATE  TRIGGER trigger_delete_input AFTER DELETE ON istocks FOR EACH ROW
BEGIN
    SET @b=OLD.istocks;
    SET @e=OLD.gname;
    SET @f=OLD.wname;

    if (select stocks.gname from stocks where stocks.gname = @e AND stocks.wname = @f ) is not null then
        if(select stocks.sstocks from stocks where stocks.sstocks  > @b and stocks.gname = @e AND stocks.wname = @f ) is not null then
            update stocks
            set stocks.sstocks = stocks.sstocks - @b
            where stocks.gname = @e and stocks.wname = @f;
        end if;
    end if;
END;
$$

DELIMITER $$
drop trigger if exists trigger_output;
CREATE  TRIGGER trigger_output AFTER INSERT ON ostocks FOR EACH ROW
BEGIN
    SET @b=NEW.ostocks;
    SET @e=NEW.gname;
    SET @f=NEW.wname;
    if (select stocks.gname from stocks where stocks.gname = @e AND stocks.wname = @f ) is not null then
        if(select stocks.sstocks from stocks where stocks.sstocks > @b and stocks.gname = @e AND stocks.wname = @f ) is not null then
            update stocks
            set stocks.sstocks = stocks.sstocks - @b
            where stocks.gname = @e and stocks.wname = @f;
        end if;
    end if;
END;
$$

-- 要在方法中检查不能改过头了
DELIMITER $$
drop trigger if exists trigger_update_output;
CREATE  TRIGGER trigger_update_output AFTER UPDATE ON ostocks FOR EACH ROW
BEGIN
    SET @a=NEW.ostocks;
    SET @b=OLD.ostocks;
    SET @c=NEW.ostocks-OLD.ostocks;
    SET @e=OLD.gname;
    SET @f=OLD.wname;
    if (select stocks.gname from stocks where stocks.gname = @e AND stocks.wname = @f ) is not null then
        if(select stocks.sstocks from stocks where stocks.sstocks - @c > 0 and stocks.gname = @e AND stocks.wname = @f ) is not null then
            update stocks
            set stocks.sstocks = stocks.sstocks - @c
            where stocks.gname = @e and stocks.wname = @f;
        end if;
    end if;
END;
$$

DELIMITER $$
drop trigger if exists trigger_delete_output;
CREATE  TRIGGER trigger_delete_output AFTER DELETE ON ostocks FOR EACH ROW
BEGIN
    SET @b=OLD.ostocks;
    SET @e=OLD.gname;
    SET @f=OLD.wname;
    if (select stocks.gname from stocks where stocks.gname = @e AND stocks.wname = @f ) is not null then
            update stocks
            set stocks.sstocks = stocks.sstocks + @b
            where stocks.gname = @e and stocks.wname = @f;
        end if;
END;
$$

insert into istocks values ('I001','苹果','仓库A',5,'张三',now());
insert into istocks values ('I002','苹果','仓库A',10,'张三',now());
insert into istocks values ('I003','香蕉','仓库A',10,'张三',now());
insert into istocks values ('I004','香蕉','仓库A',15,'张三',now());

insert into ostocks values ('T001','苹果','仓库A',1,'张三',now());
insert into ostocks values ('T002','苹果','仓库A',2,'张三',now());
insert into ostocks values ('T003','香蕉','仓库A',1,'张三',now());
insert into ostocks values ('T004','香蕉','仓库A',3,'张三',now());

```
