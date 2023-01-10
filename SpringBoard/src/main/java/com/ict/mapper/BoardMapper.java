package com.ict.mapper;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList(Criteria cri);
	
	public void insert(BoardVO vo);
	
	public BoardVO select(long bno);
	
	public void delete(long bno);
	
	public void update(BoardVO vo);
	
	public int countPageNum();
}
