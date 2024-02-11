package com.mjc.school.view;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Invoker;
import com.mjc.school.controller.command.impl.author.*;
import com.mjc.school.controller.command.impl.news.*;
import com.mjc.school.controller.command.impl.tag.*;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exception.InvalidDataException;
import com.mjc.school.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Invoker invoker;


    Scanner input = new Scanner(System.in);

    @Autowired
    public Menu(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController, BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, BaseController<TagDtoRequest, TagDtoResponse, Long> tagController, Invoker invoker) {
        this.authorController = authorController;
        this.newsController = newsController;
        this.tagController = tagController;
        this.invoker = invoker;
    }
    public void menuScreen() {

        while (true) {
            try {
                System.out.println(MenuConstants.START_MENU_TEXT);
                switch (input.nextLine()) {
                    case "1" -> invoker.setCommand(new ReadAllNewsCommand(newsController));
                    case "2" -> invoker.setCommand(new ReadAllAuthorsCommand(authorController));
                    case "3" -> invoker.setCommand(new ReadAllTagsCommand(tagController));
                    case "4" -> invoker.setCommand(new ReadNewsByIdCommand(newsController, input));
                    case "5" -> invoker.setCommand(new ReadAuthorByIdCommand(authorController, input));
                    case "6" -> invoker.setCommand(new ReadTagByIdCommand(tagController, input));
                    case "7" -> invoker.setCommand(new CreateNewsCommand(newsController, input));
                    case "8" -> invoker.setCommand(new CreateAuthorCommand(authorController, input));
                    case "9" -> invoker.setCommand(new CreateTagCommand(tagController, input));
                    case "10" -> invoker.setCommand(new UpdateNewsCommand(newsController, input));
                    case "11" -> invoker.setCommand(new UpdateAuthorCommand(authorController, input));
                    case "12" -> invoker.setCommand(new UpdateTagCommand(tagController, input));
                    case "13" -> invoker.setCommand(new DeleteNewsByIdCommand(newsController, input));
                    case "14" -> invoker.setCommand(new DeleteAuthorByIdCommand(authorController, input));
                    case "15" -> invoker.setCommand(new DeleteTagByIdCommand(tagController, input));
                    case "16" -> invoker.setCommand(new ReadAuthorByNewsId(authorController,input));
                    case "17" -> invoker.setCommand(new ReadTagsByNewsId(tagController, input));
                    case "18" -> invoker.setCommand(new ReadNewsByParam(newsController, input));
                    case "0" -> exitProgram();
                    default -> System.out.println(MenuConstants.OPERATION_ERROR);
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    public void menuScreen() {
//        Scanner sc = new Scanner(System.in);
//        NewsController newsController = context.getBean("newsController",NewsController.class);
//        AuthorController authorController = context.getBean("authorController", AuthorController.class);
//        TagController tagController = context.getBean("tagController", TagController.class);
//
//        boolean status = true;
//        while (status) {
//            System.out.println("click for performing \n" +
//                    "1 -> Create News\n" +
//                    "2 -> Create Author\n" +
//                    "3 -> Create Tag\n" +
//                    "4 -> All News\n" +
//                    "5 -> All Authors\n" +
//                    "6 -> All Tags\n" +
//                    "7 -> Get News by id\n" +
//                    "8 -> Get Author by id\n" +
//                    "9 -> Get Tag by id\n" +
//                    "10 -> Update news \n" +
//                    "11 -> Update author \n" +
//                    "12 -> Update tag \n" +
//                    "13 -> Delete news by id\n" +
//                    "14 -> Delete author by id\n" +
//                    "15 -> Delete tag by id\n" +
//                    "16 -> Get author by news id\n"+
//                    "17 -> Get tags by news id\n"+
//                    "18 -> Get news by all\n"+
//                    "19 -> Exit");
//            int numOfFunction = sc.nextInt();
//            switch (numOfFunction) {
//                case 1 -> {
//                    sc.nextLine();
//                    System.out.println("Enter news title");
//                    String title = sc.nextLine();
//                    System.out.println("Enter news content");
//                    String content = sc.nextLine();
//                    System.out.println("Enter news authorId");
//                    Long authorId = sc.nextLong();
//                    NewsDtoRequest newsDto = new NewsDtoRequest(null , title, content, authorId);
//                    newsController.create(newsDto);
//                }
//                case 2 -> {
//                    sc.nextLine();
//                    System.out.println("Enter author name");
//                    String name = sc.nextLine();
//                    AuthorDtoRequest authorDto = new AuthorDtoRequest(null, name);
//                    authorController.create(authorDto);
//                }
//                case 3 -> {
//                    sc.nextLine();
//                    System.out.println("Enter tag name");
//                    String name = sc.nextLine();
//                    TagDtoRequest tagDto = new TagDtoRequest(null, name);
//                    tagController.create(tagDto);
//                }
//                case 4 -> {
//                    newsController.readAll().forEach(System.out::println);
//
//                }
//                case 5 -> {
//                    authorController.readAll().forEach(System.out::println);
//                }
//                case 6 -> {
//                    tagController.readAll().forEach(System.out::println);
//                }
//                case 7 -> {
//                    System.out.println("Enter news id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(newsController.readById(id));
//                }
//                case 8 -> {
//                    System.out.println("Enter author id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(authorController.readById(id));
//                }
//                case 9 -> {
//                    System.out.println("Enter tag id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(tagController.readById(id));
//                }
//                case 10 -> {
//                    sc.nextLine();
//                    System.out.print("Enter updated title: ");
//                    String title = sc.nextLine();
//                    System.out.print("Enter updated content: ");
//                    String content = sc.nextLine();
//                    System.out.print("Enter updated author ID: ");
//                    Long authorId = sc.nextLong();
//                    NewsDtoRequest newsDto = new NewsDtoRequest(null, title, content, authorId);
//                    System.out.println(newsController.update(newsDto));
//                }
//                case 11 -> {
//                    sc.nextLine();
//                    System.out.print("Enter author name: ");
//                    String name = sc.nextLine();
//                    AuthorDtoRequest authorDto = new AuthorDtoRequest(null, name);
//                    System.out.println(authorController.update(authorDto));
//                }
//                case 12 -> {
//                    sc.nextLine();
//                    System.out.print("Enter tag name: ");
//                    String name = sc.nextLine();
//                    TagDtoRequest tagDto = new TagDtoRequest(null, name);
//                    System.out.println(tagController.update(tagDto));
//                }
//                case 13 -> {
//                    System.out.println("Enter news id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(newsController.deleteById(id));
//                }
//                case 14 -> {
//                    System.out.println("Enter author id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(authorController.deleteById(id));
//                }
//                case 15 -> {
//                    System.out.println("Enter tag id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(tagController.deleteById(id));
//                }
//                case 16 -> {
//                    System.out.println("Enter news id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(newsController.readAuthorByNewsId(id));
//                }
//                case 17 -> {
//                    System.out.println("Enter news id : ");
//                    Long id = sc.nextLong();
//                    System.out.println(newsController.readTagsByNewsId(id));
//                }
//                case 19 -> {
//                    status = false;
//                }
//                default ->
//                        System.out.println("have done .... :):");
//            }
//        }
//        context.close();
//        sc.close();
//
//        }
//    }

    public void exitProgram() {
        System.out.println(MenuConstants.OPERATION_EXIT);
        System.exit(0);
    }
}
