package com.swap.ihm.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.UpdateManager;
import com.swap.bo.BOException;
import com.swap.ihm.MotherServlet;

/**
 * Servlet implementation class UpdateAuctionsStatusServlet
 */
@WebServlet("/admin/update")
public class GlobalUpdateServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_PATH = "/WEB-INF/Success.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UpdateManager updateM = new UpdateManager();
			updateM.doGlobalUpdate();
			request.setAttribute("nbOfStarted", updateM.getNbOfStarted());
			request.setAttribute("nbOfEnded", updateM.getNbOfEnded());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendToJSP(SUCCESS_PATH, request, response);
	}
}