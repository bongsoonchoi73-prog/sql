package com.kh.plant.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.common.JdbcTemplate;
import com.kh.plant.model.dao.PlantDao;
import com.kh.plant.model.dto.HousePlantDto;

public class Plantservice {
	/*
	 * 비즈니스 로직 작성(의사코드) -> 데이터 가공, 중복체크 트랜잭션 관리, 여러 DAO호출, 예와 처리 및 예외 발생, 보안 및 권한 검사
	 * 
	 */

	public int save(HousePlantDto hpd) {

		Connection conn = JdbcTemplate.getConnection();
		int result = new PlantDao().save(conn, hpd);
		JdbcTemplate.close(conn);
		return result;

	}

	public List<HousePlantDto> view() {
		
		//1. 커넥샨 객체 받아오기
		Connection conn = JdbcTemplate.getConnection();
		
		//2. 컨트롤러로부터 전달받은 인자값과 커넥션 타입의 객체를 DAO로 전달
		List<HousePlantDto> plants = new PlantDao().view(conn);
		
		//3. 할 일이 모두 끝난 Connection 자원 반납
		JdbcTemplate.close(conn);
		
		// 4. 결과값 반환
		return plants;
	}

	public void edit(int plantNo, String plantName, String plantType) {
		HousePlantDto hpd = new HousePlantDto(plantNo, plantType, plantName);

	}

	public int edit(HousePlantDto hpd) {
		Connection conn = JdbcTemplate.getConnection();
		
		int result = new PlantDao().edit(conn, hpd);
		
		JdbcTemplate.close(conn);;
		
		return result;
	}

	public int delete(int plantNo) {
		if (plantNo < 1) {
			throw new RuntimeException("식물번호는 1보다 작을 수 없습니다");
		}
		
		Connection conn = JdbcTemplate.getConnection();
		
		new PlantDao().delete(conn, plantNo); 
		
		JdbcTemplate.close(conn);
		
		return result;
	}

	public HousePlantDto oneView(int plantNo) {
		return new PlantDao().oneView(plantNo);
	}

	public List<HousePlantDto> search(String keyword) {
    	return new PlantDao().search(keyword);
    }
}