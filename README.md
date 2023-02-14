# About the program
The program is a simple forum. In backend uses <em>Spring Boot</em> framework: inversion of control, dependency injection, etc.
For security uses <em>Spring Security</em> framework with jwt authentication.
<em>Spring Data</em> implements with JPA/Hibernate just have more annotation and few query request. 
In frontend uses <em>Angular</em> framework. In forms uses <em>FormBuilder</em> and HttpClient for a http requests. 


## Here is what this application demonstrates:
1. User can registration and authenticated.
2. If user can't authenticated, he can send message to administration. 
3. User can get list theme and go the page of them.
4. User can add theme.
5. User can add comments to a specific theme.
6. The program have role authorities USER_ROLE/ADMIN_ROLE.
7. Admin have access for the admin panel.
8. Admin can blocked/unblocked user, and can get list users.
9. Admin can handle user message, and can get list messages.