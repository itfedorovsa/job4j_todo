# job4j_todo

This application is task list website. Users can post, view, edit, mark as done or delete tasks. Also users can switch
between all, new or finished tasks. Each user sees only their own tasks. The task's creation time is displayed according
to the user's time zone.

# Used technologies

Implemented with:
<ul>
 <li>JDK 17</li>
 <li>Maven 3.8.5</li>
 <li>Lombok 1.18.22</li>
 <li>Spring Boot 2.7.3</li>
 <li>Bootstrap 4.4.1</li>
 <li>Thymeleaf 2.7.3</li>
 <li>JDBC 4</li>
 <li>Hibernate 5.6.11.Final</li>
 <li>PostgreSQL 42.2.9</li>
 <li>Liquibase 4.15.0</li>
</ul>

# Environment requirements

<ul>
 <li>Create db "todo". Login: postgres, password: password</li>
 <li>Create .jar file via maven command "mvn package"</li>
 <li>Go to the Target folder and check the presence of "job4j_todo-1.0-SNAPSHOT.jar" file</li>
 <li>Open the command line, go to the Target folder</li>
 <li>Run this file through "java -jar job4j_todo-1.0-SNAPSHOT.jar" command</li>
 <li>Then go to the "http://localhost:8080/index" page</li>
</ul>

# Screenshots

- Sign up page:
  ![Sign up page](src/main/resources/app_screenshots/1_sign_up.png)
- Successful sign up message:
  ![Successful sign up message](src/main/resources/app_screenshots/2_successful_sign_up.png)
- Failed sign up message:
  ![Failed sign up message](src/main/resources/app_screenshots/3_failed_sign_up.png)
- Log in page:
  ![Log in page](src/main/resources/app_screenshots/4_log_in_page.png)
- Failed log in message:
  ![Failed log in message](src/main/resources/app_screenshots/5_failed_log_in.png)
- Index page:
  ![Index page](src/main/resources/app_screenshots/6_index_page.png)
- All tasks page:
  ![All tasks page](src/main/resources/app_screenshots/7_all_tasks_page.png)
- New task adding:
  ![New task adding](src/main/resources/app_screenshots/8_new_task_adding.png)
- Task showing page:
  ![Task showing page](src/main/resources/app_screenshots/9_task_showing_page.png)
- All tasks view after mark one task as done:
  ![All tasks view after mark one task as done](src/main/resources/app_screenshots/10_all_tasks_after_mark_as_done.png)
- Task editing page:
  ![Task editing page](src/main/resources/app_screenshots/11_task_editing_page.png)
- New tasks page:
  ![New tasks page](src/main/resources/app_screenshots/12_new_tasks_page.png)
- Finished tasks page:
  ![Finished tasks page](src/main/resources/app_screenshots/13_finished_tasks_page.png)
- User profile:
  ![User profile](src/main/resources/app_screenshots/14_user_profile.png)
- User profile editing:
  ![User profile editing](src/main/resources/app_screenshots/15_update_profile.png)
- All tasks view by another user:
  ![All tasks view by another user](src/main/resources/app_screenshots/16_all_tasks_page_by_another_user.png)

Contact me: itfedorovsa@gmail.com

