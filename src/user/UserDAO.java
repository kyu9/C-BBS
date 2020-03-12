//jsp���� �����ͺ��̽��� ȸ�������� �����ϱ� ���� ����ϴ� Ŭ����
package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;//select ����� ��ŷ�� ����ϱ� ���� ���
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "111111";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//������ mysql�� �����ϰ� �ϴ� �Լ�
	
	public int login(String userId, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userId = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userId);//�Է¹��� ���� ?�ڿ� �־���ٰ� �� ?�� �ش��ϴ� ���� ���� ���̵� �־��ִ°�
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//�α��� ����
				}
				else
					return 0; //��й�ȣ ����ġ
			}
			return -1;//���̵� ����
		}catch(Exception e) {
			e.printStackTrace();
		}
		return-2; //�����ͺ��̽� ����
	}
}