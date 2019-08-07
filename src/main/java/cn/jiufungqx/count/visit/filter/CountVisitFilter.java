package cn.jiufungqx.count.visit.filter;

import cn.jiufungqx.count.visit.service.VisitCountService;
import cn.jiufungqx.count.visit.service.impl.VisitCountServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: jiufung
 * @create: 2019/7/20 17:31
 */
@Log4j2
@Component
@WebFilter(filterName = "testFilter", urlPatterns = "/*")
public class CountVisitFilter implements Filter {

    @Autowired
    private VisitCountService visitCountService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean isNewSession = request.getSession().isNew();
        String ipv4 = servletRequest.getRemoteAddr();
        filterChain.doFilter(servletRequest, servletResponse);
        if(isNewSession){
            visitCountService.insetVisitRecode(ipv4);
        }

    }
}
