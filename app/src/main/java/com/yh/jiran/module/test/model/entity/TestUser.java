package com.yh.jiran.module.test.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: 闫昊
 * @date: 2018/7/22
 * @function: 1. nameInDb表示该实体对应的数据表的名称,默认为实体名的拼音全大写
 * indexes表示制定查询数据返回的默认排序规则.(@Index中的value制定排序的数据表中的列明加上排序规则即(ASC/DESC)
 * name表示......，unique表示是否唯一即SQL中的去重复
 * 如果按照多个字段来排序可以这样(比如(indexes={@Index(value="ID ASC"),@Index(value="AGE DESC")}或者
 * indexes={@Index(value="ID ASC AGE DESC")})))
 * 2. @Id表示该字段是主键,autoincrement表示是否自增，默认为false.
 * 3. @Property用于描述字段，nameInDb表示该字段在数据表中对应的列名，默认是实体字段名称.
 * 4. @NotNull表示该字段不为null.
 * 5. @Transient 表示在创建数据表时候忽略这个字段，也就是在创建表的时候不会创建这个字段.
 * 6. @ToOne表示一对一的关系，也就是多条这个实体只对应一条标识的实体joinProperty标识这个实体表中的哪个字段和标识的实体表的主键关联.
 * 7. @ToMany标识一对多的关系，也就是一条该实体数据通过指定列名和标识的数据实体的指定列名对应关系(@referencedJoinProperty表示当前标识的实体对应的数据表的
 * 8. 主键,@joinProperties表示当前表和标识的实体对应的数据表的属性对应关系)
 * 9. @Convert定义当前标识的实体和数据表中字段之间装换规则.converter表示转换器.columnType表示对应的数据表列名在表中的数据类型,
 */
@Entity(nameInDb = "test_user", indexes = {@Index(value = "name ASC", unique = true)})
public class TestUser {
    //@Id(autoincrement = true)
    @Id
    @Property(nameInDb = "ID")
    private Long id;

    @NotNull
    @Property(nameInDb = "USER_NAME")
    private String name;

    @NotNull
    @Property(nameInDb = "AGE")
    private int age;

    @Transient
    private int tempId;

    @Generated(hash = 209469355)
    public TestUser(Long id, @NotNull String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 925009630)
    public TestUser() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @ToOne(joinProperty = "ID")
//    @Property(nameInDb = "TO_ONE")
//    private int toOne;

}
