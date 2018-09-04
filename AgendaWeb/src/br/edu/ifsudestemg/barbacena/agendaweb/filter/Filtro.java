package br.edu.ifsudestemg.barbacena.agendaweb.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.edu.ifsudestemg.barbacena.agendaweb.dao.Conexao;

@WebFilter("/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		Connection connection = Conexao.getConnection();
		request.setAttribute("connection", connection);
		filterChain.doFilter(request, response);
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// doFilter

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
}
