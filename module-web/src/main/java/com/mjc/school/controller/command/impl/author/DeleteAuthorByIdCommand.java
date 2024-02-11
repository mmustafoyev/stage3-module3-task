package com.mjc.school.controller.command.impl.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import java.util.Scanner;

public class DeleteAuthorByIdCommand extends AbstractCommand<AuthorDtoRequest, AuthorDtoResponse, Long> implements Command {


    public DeleteAuthorByIdCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_AUTHOR_ID);
        System.out.println(getController().deleteById(Utils.inputLongNumber(getInput())));
    }
}
