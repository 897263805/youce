package com.yonyou.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yonyou.pojo.UploadFileResponse;



@Mapper
public interface UploadMapper {
	@Insert("insert into upload (filename,filetype,size,filedownloaduri,uploaduri,userid,uploadDate,flag) values (#{fileName},#{fileType},#{size},#{fileDownloadUri},#{uploadUri},#{userId},#{uploadDate},#{flag})")
	//@Insert("insert into upload (upload_name,upload_type,upload_size,download_uri) values ('1','2','3','4')")
    void Insert(UploadFileResponse file);//插入
	 @Select("select * from upload where user_id=#{userid}")
	 UploadFileResponse findByUserID( String userid);//如果不是类的话需要加注解，其中的param（�?�token”）是添加的注解
	 @Select("select * from upload where filename=#{fileName}")//根据文件名查询
	 UploadFileResponse findByUserFileName(String fileName);//如果不是类的话需要加注解，其中的param（�?�token”）是添加的注解
	 @Select("select * from upload where filename=#{fileName} and userid = #{userId}")//查询当前用户是否存在同名文件
	 UploadFileResponse findByUserFileNameAndUserID(@Param("fileName")String fileName,@Param("userId")int userId);//如果不是类的话需要加注解，其中的param（�?�token”）是添加的注解
	 @Select("select * from upload where userid = #{userid}")//获取当前用户的全部文档
	 List<UploadFileResponse> findAllCase(@Param("userid") int userid);
	 @Delete("delete from upload where id = #{fileid}")//删除文件
	 void deleteCaseById(@Param("fileid") int fileId);
	 @Select("select * from upload where userid = #{userid} and flag = #{flag} ")
	 List<UploadFileResponse> findFile(@Param("userid") int userid,@Param("flag") int flag);
	 @Select("select * from upload where userid = #{userid} and filetype = #{filetype} ")
	 List<UploadFileResponse> findRep(@Param("userid") int userid,@Param("filetype") String flag);
	 @Delete("delete from upload where filename = #{fielname}")//删除文件
	 void deleteCaseByName(@Param("fielname") String fielName);

}
