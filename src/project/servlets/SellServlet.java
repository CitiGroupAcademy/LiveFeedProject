package project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import project.dal.GetQuotes;
import project.dal.OrderManager;
import project.dal.OrderManager.OrderResult;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/SellServlet")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if(request.getParameter("stockAmount")!=null && request.getParameter("stockSymbol")!=null)
        {
        	String stock = request.getParameter("stockSymbol");
        	int amount = Integer.parseInt(request.getParameter("stockAmount"));
        	double ask = 0.0;
			try 
			{
				ask = GetQuotes.returnAskOrBid(stock, "sell");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			if(ask>0)
			{
				OrderResult or = OrderManager.getInstance().sellOrder(stock, ask, amount);
				Logger log = Logger.getLogger(this.getClass());
				log.info("INFO MANUAL SELL: " + stock + "ASK: " + ask + "AMOUNT: " + amount);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
	        rd.forward(request, response);
	        request.setAttribute("message", "You have sold " + amount + " shares in: " + stock);
        }
	}
}
