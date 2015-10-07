package project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dal.GetQuotes;
import project.dal.OrderManager;
import project.dal.OrderManager.OrderResult;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Hello");
        if(request.getParameter("stockAmount")!=null && request.getParameter("stockSymbol")!=null)
        {
        	String stock = request.getParameter("stockSymbol");
        	int amount = Integer.parseInt(request.getParameter("stockAmount"));
        	double ask = 0.0;
			try 
			{
				ask = GetQuotes.returnAskOrBid(stock, "buy");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			if(ask>0)
			{
				OrderResult or = OrderManager.getInstance().buyOrder(stock, ask, amount);
			}
        }
        response.sendRedirect(response.encodeRedirectURL("home.jsp"));
	}
}
