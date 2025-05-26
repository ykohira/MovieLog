package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TagDeleteService;

@WebServlet("/deleteTag")
public class TagDeleteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagIdStr = request.getParameter("tagId");

        try {
            int tagId = Integer.parseInt(tagIdStr);

            TagDeleteService tagService = new TagDeleteService();
            tagService.deleteTag(tagId);

            response.sendRedirect("/movieLog/createTag");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無効なタグIDです");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "タグの削除に失敗しました");
        }
    }
}
