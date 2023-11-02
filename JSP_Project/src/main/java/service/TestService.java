package service;

import java.util.ArrayList;

import dto.testDto;

public class TestService {

	public ArrayList<testDto> getBoardList() {
		//1. select >> DB접속 >> Dao 클래스
		ArrayList<testDto> list = new ArrayList<testDto>();  //dao.selectBoard();
		for(int i = 0; i < 5; i++) {
			testDto tdto = new testDto();
			tdto.setNum(i+1);
			tdto.setTitle("제목"+(i+1));
			tdto.setWriter("작성자"+(i+1));
			list.add(tdto);
		}
		
		return list;
	}

}
