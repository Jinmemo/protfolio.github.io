package odi_project.board.controller.commonBoardController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import odi_project.board.model.service.BoardService;
import odi_project.board.model.service.BoardServiceImpl;
import odi_project.board.model.vo.Board;

/**
 * Servlet implementation class DeleteBoardController
 */
@WebServlet("/boardDelete.bo")
public class DeleteBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        // 폼에서 매개변수 추출
		        int bno = Integer.parseInt(request.getParameter("bno"));

		        // 폼 데이터를 사용하여 Board 객체 생성
		        Board board = new Board();
		        board.setBoardNo(bno);

		        // 서비스를 호출하여 게시글 수정
		        BoardService boardService = new BoardServiceImpl();
		        int result = boardService.deleteBoard(board);

		        // 결과에 따라 적절한 응답 생성 (성공 또는 실패 페이지로 리다이렉션)
		        if (result > 0) {
		            response.sendRedirect("commonlist.bo?bno=1");
		        } else {
		            // 실패 처리
		            HttpSession session = request.getSession();
		            session.setAttribute("alertMsg", "게시글 삭제 실패");
		            response.sendRedirect("commonlist.bo?bno=1");
		        }
		    }

}
