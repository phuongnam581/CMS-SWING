package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import object.Content;


//import com.mysql.cj.importxdevapi.PreparableStatement;

public class ModelContent {
	public List<Content> getListContent() throws SQLException
	{
		Connection connection =DBConnect.getConnection();
		List<Content>list = new ArrayList<Content>();
		String sql="Select * from content ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet resultSet =ps.executeQuery();
		while(resultSet.next())
		{
			object.Content content = new object.Content();
			content.setAuthorId(resultSet.getInt("authorId"));
			content.setBrief(resultSet.getString("brief"));
			content.setContent(resultSet.getString("content"));
			content.setCreatedDate(resultSet.getDate("createdDate"));
			content.setUpdateTime(resultSet.getDate("updateTime"));
			content.setId(resultSet.getInt("id"));
			content.setTitle(resultSet.getString("title"));
			list.add(content);
			}
		return list;
	}
	public boolean editContent(object.Content content) throws SQLException
	{
		Connection connection =DBConnect.getConnection();
		String sql="Update content set   brief=?,title=? ,updateTime=? where id=?";
		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setString(1,content.getBrief() );
		ps.setString(2,content.getTitle() );
		//ps.setString(3,content.getTitle() );
		java.util.Date uDate = new java.util.Date();
		java.sql.Date sDate = convertUtilToSql( uDate);
		ps.setDate(3,  sDate );
		ps.setInt(4,content.getId());
		ps.executeUpdate();
		return true;
	}
	private Date convertUtilToSql(java.util.Date uDate) {
		// TODO Auto-generated method stub
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;	
	}
	public boolean deleteContent(object.Content content) throws SQLException
	{
		Connection connection =DBConnect.getConnection();
		String sql = "delete from content where id=?";
		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setInt(1, content.getId());
		ps.executeUpdate();
		return true;
	}
	public boolean addContent(Content content) {
		Connection connection = DBConnect.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO content(title, brief, content, createdDate, updateTime, authorId) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, content.getTitle());
			ps.setString(2, content.getBrief());
			ps.setString(3, content.getContent());
			ps.setDate(4, (Date) content.getCreatedDate());
			ps.setDate(5, (Date) content.getUpdateTime());
			ps.setInt(6, content.getAuthorId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public ArrayList<Content> getListcontentByTitle(String keyword) throws SQLException {
        Connection conect = DBConnect.getConnection();
        String sql = "SELECT * FROM content WHERE  title LIKE'%" + keyword + "%'";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Content> list = new ArrayList<>();
        while (rs.next()) {
        	Content content = new Content();
            content.setTitle(rs.getString("title"));
            content.setBrief(rs.getString("brief"));
            content.setContent(rs.getString("content"));
            content.setCreatedDate(rs.getDate("createdDate"));;
            list.add(content);
        }
        return list;
    }

//	public static void main(String[] args) {
//		List<Content>list= new ArrayList<Content>();
//		ModelContent model = new ModelContent();
//		try {
//			list = model.getListContent();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(list.get(0).getContent());
//	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ModelContent model= new ModelContent();
		for (Content content : model.getListcontentByTitle("a")) {
			System.out.println(content.getBrief());
		}
	}
	
}
