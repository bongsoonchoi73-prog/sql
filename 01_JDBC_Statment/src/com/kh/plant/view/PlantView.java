package com.kh.plant.view;

import java.util.List;
import java.util.Scanner;
import com.kh.plant.controller.Plantcontroller;
import com.kh.plant.model.dto.HousePlantDto;

public class PlantView {
	private static final int plantNo = 0;
	private Scanner sc = new Scanner(System.in);
	private Plantcontroller pc = new Plantcontroller();

	public void mainMenu() {
		while (true) {
			System.out.println("=== 화초 관리 프로그램===");
			System.out.println("1.화초 추가하기");
			System.out.println("2.전체 조회하기");
			System.out.println("3. 화초 수정하기");
			System.out.println("4. 화초 삭제하기");
			System.out.println("5. 화초 하나만 조회하기");
			System.out.println("6. 화초 검색하기");
			System.out.println("0.프로그램 종료");
			System.out.print("메뉴를 선택하세요");
			String menu = sc.nextLine();

			switch (menu) {
			case "1":
				save();
				break;
			case "2":
				view();
				break;
			case "3":
				edit();
				break;
			case "4":
				delete(); break;
			case "5" :
			    oneView(); break;
			case "6" :
				search();
				break;
			case "0":
				sc.close();
				return;
			default:
				System.out.println("존재하지 않은 메뉴입니다");
			}
		}
	}

	private void save() {
		System.out.println("/n화초 추가 서비스입니다");
		System.out.print("화초 이름을 입력하세요 > ");
		String plantName = sc.nextLine();
		System.out.print("화초 종류를 입력하세요 > ");
		String plantType = sc.nextLine();

		// View의 할 일 끝 -> 컨트롤러 요청
		int result = pc.save(plantName, plantType);

		if (result > 0) {
			System.out.println("화초 추가에 성공했습니다");
		} else {
			System.out.println("화초 추가에 실패했습니다");
		}
	}

	private void delete() {
		System.out.println("화초 삭제 서비스입니다");
		System.out.println("삭제하실 식물의 번호를 입력하세요 > ");
		int plantNo = sc.nextInt();
		sc.nextLine();

		try {
			int result = pc.delete(plantNo);

			if (result > 0) {
				System.out.println("화초 삭제에 성공");
			} else {
				System.out.println("화초 삭제 실패");
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	private void view() {
		System.out.println("화초 목록입니다");
		// 화초데이터 21개

		// Controller에게 전체 데이터 요청
		List<HousePlantDto> plants = pc.view();

		if (plants.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다");

		} else {
			System.out.println("\n조회된 화초는 총 " + plants.size() + "개");
			for (HousePlantDto h : plants) {
				System.out.println("=====================================");
				System.out.print("식물번호 : " + h.getPlantNo() + ", ");
				System.out.print("식물이름 : " + h.getPlantName() + ".");
				System.out.print("식물종류 : " + h.getPlantType());
				System.out.println();
			}
		}
	}

	private void edit() {
		// PLANT_NO 입력
		// PLANT_NAME과 PLANT_TYPE 수정하는 기능
		System.out.println("화초 수정 기능입니다");
		System.out.println("수정할 화초의 번호를 입력하세요 > ");
		int plantNo = sc.nextInt();
		sc.nextLine();
		System.out.print("새로운 화초의 이름을 입력하세요 > ");
		String plantName = sc.nextLine();
		System.out.print("새로운 화초의 분유를 입력하세요 > ");
		String plantType = sc.nextLine();

		int result = pc.edit(plantNo, plantName, plantType);

		// 성공 / 실패 여부를 판단하여 결과를 출력
		if (result > 0) {
			System.out.println("화초 정보 변경에 성공했습니다");
		} else {
			System.out.println("화초 정보 변경에 실패했습니다");
		}
	}
	private void oneView() {
		// 사용자에게 PLANT_NO를 입력받아서 조회된 결과 출력
		System.out.println("화초 조회 서비스입니다");
		System.out.println("검색하실 화초번호 입력");
		int plantNo = sc.nextInt();
		sc.nextLine();
		
		HousePlantDto hpd = pc.oneView(plantNo);
		
		if(hpd != null) {
			System.out.println("검색결과 !");
			System.out.println("식물이름 :" + hpd.getPlantName());
			System.out.println("식물분류 :" + hpd.getPlantType());
		} else {
			System.out.println("존재하지 않은 식물 번호입니다");
		}
		
	}
	
	private void search() {	
		System.out.print("검색하고 싶은 키워드를 입력하세요 > ");
		String keyword = sc.nextLine();
		
		List<HousePlantDto> plants = pc.search(keyword);
		
		if(plants.isEmpty()) {
			System.out.println("검색결과가 존재하지 않습니다");
		} else {
			for(HousePlantDto p : plants) {
				System.out.println(p);
			}
		}
	}
}