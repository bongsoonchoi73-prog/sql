package com.kh.plant.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.plant.model.dto.HousePlantDto;

public class PlantDao {
	/*
	 * JDBC용 인터페이스
	 * 
	 * - Connection : 데이터베이스와의 세션(연결)을 나타내는 인처페이스 -> 데이터베이스와의 통신 채널 연결 -> SQL문을
	 * 실행하기위한 Statement 객체 생성 -> 트랜잭션 관리
	 * 
	 * - Statement : SQL문을 실행학 결과를 받아오기 위한 인터페이스 1. Statement : 정적 SQL문(완성된 SQL문) 실행
	 * 2. PreparedStatement : 파라미터화된 SQL문 실행 3. CallableStatement : 저장 프로시저 호출
	 * 
	 * - ResultSet : SELECT문 수행결과를 담은 테이블 형태의 데이터 셋 -> 커서라는 개념을 이용래서 데이터에 접근 -> 다양한
	 * 데이터타입을 반환해주는 메소드 제공
	 */

	public int save(HousePlantDto hpd) {

		Connection conn = null;
		Statement stmt = null;
		int result = 0;

		String sql = "INSERT " + "INTO " + "HOUSEPLANT " + "VALUES " + "( " + "SEQ_PLANT_NO.NEXTVAL" + ", '"
				+ hpd.getPlantType() + "'" + ", '" + hpd.getPlantName() + "'" + ")";
		// SQL문에 문법적인 문제사 존재한다
		// SQLSyntaxErrorException이 발생

		try {
			// 1) JDBC 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1. 패키지 경로 / 클래스 식별자에 오타 발생 경우
			// 2. 프로젝트에 라이브러리를 추가하지 않아서 진짜 없는 경우
			// -> ClassnotFoundException 발생

			// 2) Connection 타입 객체 생성(URL, username, password)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL", "oracle");
			// 1. URL을 잘 못적었을 수도 있음
			// 2. 사용자 이름을 잘못적었을 수 있음
			// 3. 비밀번호를 잘못적었을 수 있음
			// ----------------------------------
			// 4. 서버가 안 열려(켜져)있을 수 잇음
			// 5. 접속권한을 부여받지 못햇을 경우
			// 6. 비밀번호 갱신을 안했을 수 있음
			// SQLException이 발생

			// 3) Statement 타입의 객체
			stmt = conn.createStatement();

			// 4) SQL문 실행 요청 및 응답 반한
			result = stmt.executeUpdate(sql);
			// INSERT 시 값에 문제가 있을 수 있음
			// 자료형이 문제
			// 제약조건 위배
			// 데이터의 크기가 컬ㄻ의 크기보다 큼
			// DB서버에 문제
			// SQLException이 발생

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5) 자원반납
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// DML 수행결과 반환
		return result;

	}

	public List<HousePlantDto> view() {

		// SELECT
		// 1. Connection
		// 2. Statement
		// 3. ResultSet
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<HousePlantDto> list = new ArrayList<>();
		String sql = """
				SELECT
				      PLANT_NO
				    , PLANT_NAME
				    , PLANT_TYPE
				 FROM
				     HOUSEPLANT
				 ORDER
				    BY
				      PLANT_NO DESC
				""";
		// 1)Class.forName("oracle.jdbc.driver.OracleDriver");

		try {
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL", "oracle");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4) SQL문(SELECT) 실행요청 후 응답반환받기
			rset = stmt.executeQuery(sql);

			// 5) SELECT문의 핵심
			// 1 관엽식물 금전수
			// PLANT의 값에 담을 경우 PLANT모양에 담아야겠다
			// RDBMS == 테이블형태
			// Mapping 매핑한다
			// 서로 다른 형태의 데이터모델간의 연결을 정의하는 과정
			// JAVA == 객체형태

			// rset.next()
			// 조회결과를 담은 ResultSet이 들어옴
			// => 한 행씩 접근해서 데이터를 뽑아내고 => DTO/VO 필드에 담기
			// => 커서를 한 줄 아래로 옮기고 존재한다 true / falst

			while (rset.next()) {

				// 현재 rest의 가리키고 있는 행의 데이터를
				// 하나씩 뽑아서 HousePlantDTO객체의 필드의 대입
				HousePlantDto h = new HousePlantDto();
				// ResultSet객체로부터
				// 어떤 컬럼의 값을 뽑을건지 메소드를 호출 인자값으로
				// 컬럼명을 전달
				// rset.getInt("컬럼명");
				// 컬럼값이 정수연서 int로 매핑해야할 때
				// rset.getString("컬럼명");
				// 컬럼값이 문자열이어서 String 매핑할 때
				// rest.getDate("컬럼명");
				// 컬럼값이 날짜값이어서 java.sql.Date로 매핑할 때
				h.setPlantNo(rset.getInt("PLANT_NO"));
				h.setPlantName(rset.getString("PLANT_NAME"));
				h.setPlantType(rset.getString("PLANT_TYPE"));

				// List<HousePlantDto> list = new ArrayList();
				list.add(h);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// List 값 반환
		return list;
	}

	public int edit(HousePlantDto hpd) {
		int result = 0;
		// UPDATE -> updatedRows
		String sql = "UPDATE " + "HOUSEPLANT " + "SET " + "PLANT_NAME  = '" + hpd.getPlantName() + "'"
				+ ", PLANT_TYPE  = '" + hpd.getPlantType() + "' " + "WHERE " + "PLANT_NO = " + hpd.getPlantNo();
		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL",
				"oracle"); Statement stmt = conn.createStatement()) {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public int delete(int plantNo) {
		int result = 0;
		String sql = """
				 DELETE
				   FROM
				       HOUSEPLANT
				   WHERE
				        PLANT_NO =
				""";
		sql += plantNo;
		HousePlantDto hpd = null;

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL",
				"oracle"); Statement stmt = conn.createStatement()) {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HousePlantDto oneView(int plantNo) {
		HousePlantDto hpd = null;
		String sql = """
				SELECT
				      PLANT_NO
				     ,PLANT_NAME
				     ,PLANT_TYPE
				  FROM
				      HOUSEPLANT
				  WHERE
				      PLANT_NO =
				  """;
		sql += plantNo;
		try (Connection conn = DriverManager
				.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL", "oracle");

				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(sql)) {

			if (rset.next()) {
				// PK를 가지고 조회했을 때 결과 존재
				// 매핑
				hpd = new HousePlantDto(rset.getInt("PLANT_NO"), rset.getString("PLANT_TYPE"),
						rset.getString("PLANT_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hpd;
	}

	public List<HousePlantDto> search(String keyword) {
		List<HousePlantDto> plants = new ArrayList();

		String sql = """
				SELECT
				       PLANT_NO
				      , PLANT_NAME
				      , PLANT_TYPE
				FROM
				     HOUSEPLANT
				WHERE

				 """;
		sql += "PLANT_TYPE LIKE '%" + keyword + "%' ORDER BY PLANT_NO DESC";
		try (Connection conn = DriverManager
				.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL", "oracle");

				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(sql)) {

			while (rset.next()) {

				plants.add(new HousePlantDto(rset.getInt("PLANT_NO"), rset.getString("PLANT_TYPE"),
						rset.getString("PLANT_NAME")));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return plants;

	}
}
