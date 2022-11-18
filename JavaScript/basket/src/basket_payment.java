package com.song;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class basket_payment
 */
@WebServlet("/basket_payment")
public class basket_payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basket_payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		int sum =0;
		List<String> list = (List)session.getAttribute("productList");

		if(list != null){
			for(String product : list){
				if("그래픽 카드".equals(product)){
					sum+=1300000;
				}else if("닌텐도".equals(product)){
					sum+=400000;
				}else if("모니터".equals(product)){
					sum+=500000;
				}else if("멕북".equals(product)){
					sum+=3800000;
				}else if("페라리".equals(product)){
					sum+=320000000;
				}else if("집".equals(product)){
					sum+=800000000;
				}
			}
			
		
			writer.println("<script>if(confirm('"+ sum +"원을 결제하시겠습니까?'))"
					+ "{alert('결제가 성공적으로 완료되었습니다.\\n 장바구니를 초기화합니다.');"
					+ " location.href='basket_invalid';}else{alert('돈이 없군요....'); history.back();}</script>");
		}else {
			writer.println("<script>alert('상품X');history.back();</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	
	}
	}
