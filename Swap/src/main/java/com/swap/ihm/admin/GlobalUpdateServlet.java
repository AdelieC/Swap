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
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static final String SUCCESS_TITLE = "Successful global update!";
	private static final String FAILURE_TITLE = "Global update failed...";

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
			setSuccess(request, updateM);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOException e) {
			setFailure(request);
			e.printStackTrace();
		}
		sendToJSP(OUTCOME_JSP, request, response);
	}

	private void setFailure(HttpServletRequest request) {
		String message = "Sorry, something went wrong. Auctions couldn't be updated. Try again?";
		request.setAttribute("message", message);
		request.setAttribute("title", FAILURE_TITLE);
	}

	private void setSuccess(HttpServletRequest request, UpdateManager updateM) {
		String message = "Auctions were updated and users were notified! " + updateM.getNbOfStarted()
				+ " created auctions just started, and " + updateM.getNbOfEnded() + " ongoing auctions just closed.";
		request.setAttribute("message", message);
		request.setAttribute("title", SUCCESS_TITLE);
	}
}