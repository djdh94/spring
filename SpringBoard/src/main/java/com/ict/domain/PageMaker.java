package com.ict.domain;

import lombok.Data;

@Data
public class PageMaker {

	private int totalBoard;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum;
	
	private SearchCriteria cri;
	
	public void calcData() {
		//한페이지당 깔고싶은 버튼갯수
		this.displayPageNum=10;
		
		// 현재페이지 (cri.getpageNum())을 근거로 페이지 그룹중 끝나는 페이지를 구함
		this.endPage = (int)(Math.ceil(cri.getPageNum()/(double)displayPageNum)*displayPageNum);
		
		// 끝나는 페이지를 토대로 페이지 그룹의 시작페이지를 구함
		this.startPage = (endPage - displayPageNum)+1;
		
		//위의 endpage는 명목상의(단순 그룹계산으로)끝나는 페이지이기때문에 실질적인 글 개수를 통해 보장해줘야함
		int tempEndPage = (int)(Math.ceil(totalBoard/(double)cri.getNumber()));
		
		// 실 마무리 페이지 보정
		if(endPage>tempEndPage) {
			endPage = tempEndPage;
		}
		
		// newxt,preve 버튼 여부처리
		// prev는 startPage가 1인 경우에만 비활성화 이므로 삼항연산자로 간단히 처리
		prev= startPage ==1?false:true;
		
		//next는 여태 출력한 페이지에 속한 글 개수보다 db내 전체 글이 더많은경우 활성화
		next = endPage * cri.getNumber() >= totalBoard ? false:true;
		
		
		}//calcData()
		public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
		calcData(); //prev,next,endPage,startPage 다 구함
				
	}
}
