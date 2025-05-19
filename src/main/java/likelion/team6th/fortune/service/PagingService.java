package likelion.team6th.fortune.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class PagingService {
	
	//화면에 보여줄 페이지 num 개수
	private final static int PAGE_LENGTH = 10;
	
	//화면에 출력 페이지 정의
	public List<Integer> getPageNumbers(int pageNumber, int totalPages) {
		
		int startPage = Math.max((pageNumber - (PAGE_LENGTH/2)), 0);
		int endPage = Math.min((startPage + PAGE_LENGTH), totalPages);
		
		return IntStream.range(startPage, endPage).boxed().toList();

	}
}