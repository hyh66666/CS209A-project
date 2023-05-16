package com.example.demo.mapper;

import com.example.demo.enity.Owner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OwnerMapper {

    @Select("select * from \"Owner\"")
    public List<Owner> findAllOwner();

    @Select("select * from \"Owner\" where \"userId\" = #{iii}")
    public Owner findOwnerByUserId(String iii);

    @Insert("INSERT INTO \"Owner\" (id, \"accountId\", reputation, \"userId\", \"userType\", \"profileImage\", \"displayName\", link)" +
            " VALUES (DEFAULT, #{accountId}, #{reputation},#{userId},#{userType},#{profileImage},#{displayName}, #{link})")
    public int insert(Owner owner);

}
