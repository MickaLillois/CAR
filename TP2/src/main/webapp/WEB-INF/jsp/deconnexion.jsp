<%
    if(session.getAttribute("idUtilisateur") != null){
        session.setAttribute("idUtilisateur", null);
        response.sendRedirect("/");
    }
%>