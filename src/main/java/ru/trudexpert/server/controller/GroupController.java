package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trudexpert.server.service.GroupService;

@Controller
@RequestMapping("/trudexpert/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public String openGroupPage() {
        return "groups";
    }

    @GetMapping("/search")
    public String openGroupListPage() {


        return "/groups/group_search";
    }

}
