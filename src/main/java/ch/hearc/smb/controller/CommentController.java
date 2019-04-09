package ch.hearc.smb.controller;


import ch.hearc.smb.model.Comment;
import ch.hearc.smb.model.Post;
import ch.hearc.smb.service.CommentService;
import ch.hearc.smb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Secured({ "ROLE_ADMIN", "ROLE_MODO" })
    @GetMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        Comment comment = commentService.find(id);
        Long idPost = comment.getPost().getId();
        commentService.delete(comment);
        return "redirect:/posts/" + idPost;
    }

    @GetMapping(value = "/{boardid}", produces = "application/json")
    public @ResponseBody
    Page<Comment> getComments(@PathVariable Long boardid, @PageableDefault(value = 5, page = 0) Pageable pageable) {
        return commentService.getAllPostByDesc(boardid, pageable);
    }

    @PostMapping("")
    public String createComments(@ModelAttribute @Validated Comment comment, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "redirect:posts/" + comment.getPost().getId() + "?error=1";
        }
        commentService.save(comment);
        Post post = comment.getPost();
        post.setModifiedDate(comment.getCreatedDate());
        postService.save(post);
        return "redirect:posts/" + comment.getPost().getId();
    }
}
