package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IBoardDao;
import model.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/test/applicationContext.xml"})
public class BoardDaoTest {
    @Autowired
	private IBoardDao boardDao;
    
    @Test
    public void daoTest() {
     Board board = new Board();
     board.setTitle("hello");
     board.setContent("bye");
     board.setWriter("bwow");
     int result = boardDao.insertBoard(board);
     assertEquals(result, 1);
		
    	
    }
}
