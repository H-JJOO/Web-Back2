package com.koreait.board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //TODO - 화면 나올거 작성하고 (detail과 주소빼고 동일)
        //getParameter 로 받아오는 것은 get 방식이므로 무조건 String(문자열)로 밖에 받아올수 없다.
        String strIboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(strIboard);
        BoardVO param = new BoardVO();
        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);

        req.setAttribute("data", data);

        String path = "/WEB-INF/jsp/mod.jsp";
        req.getRequestDispatcher(path).forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //TODO - form 값 받을거 작성 (write와 비슷, 추가로 iboard)
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");
        String strIboard = req.getParameter("iboard");

        int iboard = Integer.parseInt(strIboard);

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(writer);
        param.setIboard(iboard);

        int result = BoardDAO.updBoard(param);

        switch (result) {
            case 1:
                res.sendRedirect("/detail?iboard=" + strIboard);
                break;
        }

    }
}
