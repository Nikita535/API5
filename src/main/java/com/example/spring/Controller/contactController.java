package com.example.spring.Controller;


import com.example.spring.Entity.Contact;
import com.example.spring.Repository.ContactRepository;
import com.example.spring.Service.contactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class contactController {

    @Autowired
    contactService contactService;

    @PostMapping("/addContact")
    @ResponseBody
    @Operation(
            tags = "Сохранить контакт",
            summary = "Контакт сохранится в базу данных",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание нашего контакта",
                        content = @Content(
                                schemaProperties = {
                                    @SchemaProperty(name = "number",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "surname",schema = @Schema(name = "contact"))
                        }
                            )),
            responses = {@ApiResponse(responseCode = "200",
                        content = @Content(schema = @Schema(implementation = Contact.class),mediaType = MediaType.APPLICATION_JSON_VALUE
                        ,examples = {@ExampleObject(name = "Пример",value ="Объект контакта")}),description = "Контакт сохранён"),
                        @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())}
    )
    public Contact saveContact(@RequestBody Contact contact){
        contactService.save(contact);
        return contact;
    }

    @Operation(
            tags = "Удалить контакт",
            summary = "Контакт удалится из базы данных",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте, который хотите удалить",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание нашего контакта",
                    content = @Content(
                            schemaProperties = {
                                    @SchemaProperty(name = "number",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "surname",schema = @Schema(name = "contact"))
                            }
                    )),
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Contact.class),mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,examples = {@ExampleObject(name = "Пример",value ="Объект контакта")}),description = "Контакт успешно удалён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())},
            parameters = {@Parameter(name="ContactID",description = "ID контакта в базе данных",example = "1")}
    )
    @PostMapping("/deleteContact/{id}")
    @ResponseBody
    public Long deleteContact(@PathVariable Long id){
        contactService.deleteContactById(id);
        return id;
    }

    @Operation(
            tags = "Обновить данные о контакте",
            summary = "Данные о контакте будут удалены",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте, который хотите изменить",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание нашего контакта",
                    content = @Content(
                            schemaProperties = {
                                    @SchemaProperty(name = "number",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "surname",schema = @Schema(name = "contact"))
                            }
                    )),
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Contact.class),mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,examples = {@ExampleObject(name = "Пример",value ="Объект контакта")}),description = "Контакт успешно изменён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content()),
                    @ApiResponse(responseCode = "203",description = "Контакт успешно изменён",content = @Content())},
            parameters = {@Parameter(name="ContactID",description = "ID контакта в базе данных",example = "1")}
    )
    @PostMapping("/updateContact/{id}")
    @ResponseBody
    public Contact updateContact(@RequestBody Contact contact, @PathVariable long id){
        contactService.changeContact(contact, id);
        return contactService.getContactById(id);
    }

    @PostMapping("/getContact/{id}")
    @ResponseBody
    @Operation(
            tags = "Получить данные о контакте",
            summary = "Данные о контакте будут получены",
            description = "Нажмите кнопку \"Try it out\" и вводите данные о контакте, который хотите получить",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Описание нашего контакта",
                    content = @Content(
                            schemaProperties = {
                                    @SchemaProperty(name = "number",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "name",schema = @Schema(name = "contact")),
                                    @SchemaProperty(name = "surname",schema = @Schema(name = "contact"))
                            }
                    )),
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Contact.class),mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,examples = {@ExampleObject(name = "Пример",value ="Объект контакта")}),description = "Контакт успешно изменён"),
                    @ApiResponse(responseCode = "500",description = "Ошибка сервера",content = @Content())},
            parameters = {@Parameter(name="ContactID",description = "ID контакта в базе данных",example = "1")}
    )
    public Contact getContact(@PathVariable Long id){
        return contactService.getContactById(id);
    }
}
