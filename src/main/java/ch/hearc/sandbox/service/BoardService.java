package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.Board;
import ch.hearc.sandbox.model.Post;
import ch.hearc.sandbox.repository.BoardRepository;
import ch.hearc.sandbox.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BoardService {
    @Autowired
    private BoardRepository brepo;

    public List<Board> findAll() {
        List<Board> boards = new ArrayList<>();
        brepo.findAll().forEach(boards::add);
        return boards;
    }

    public Board find(Long id) {
        return brepo.findById(id).orElse(new Board());
    }

    public Board save(@Valid Board board) {
        return brepo.save(board);
    }

    public void delete(@Valid Board board) {
        brepo.delete(board);
    }

    public void delete(Long id) {
        this.delete(this.find(id));
    }

    public List<Post> getPosts(@Valid Board board) {
        return board.getPosts();
    }

}