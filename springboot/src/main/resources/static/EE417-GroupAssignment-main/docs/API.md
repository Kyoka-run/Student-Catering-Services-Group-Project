EE417 Group K Assignment
Backend API document DRAFT

CORS note: For testing purposes, CORS header has been added for domain http://localhost:8080. Please make sure the frontend is running at port 8080 of localhost or otherwise access to the API will be blocked by browsers. 
Authentication guidelines: All endpoints marked as “REQUIRES AUTHENTICATION” will require a login token. After invoking the authentication endpoint, the login token will automatically be written to the browser cookie for further invocations in which the token will be automatically attached. However, this only happens for the same origin case scenario. For testing purposes, “xhr.withCredentials = true;” should be explicitly denoted before invoking any of the above mentioned endpoints to attach the token upon request. 

API for user log in/sign up (MySQL table “users”, “roles”)
(a) Get user information
Endpoint REQUIRES AUTHENTICATION AND ADMIN ROLE: GET /user
Description: This endpoint retrieves a user by their username. 
Query Parameters:
name: String (required) - The username of the user to retrieve.
Response: Returns a JSON object indicating the success or failure of the operation, along with the details of the found user in the “user” field if successful, or an error message if otherwise.
(b) User sign up
Endpoint: POST /user_register
Description: This endpoint allows registering a new user.
Request Body:
JSON object representing the user to be registered. It should include the following fields:
username: String (required) - The username of the user.
password: String (required) - The password of the user.
fname: String(required) - The full name of the user.
email: String(required) - The email address of the user.
phone: String(required) - The phone number of the user.
Response: Returns a JSON object indicating the success or failure of the operation, along with the ID of the created user if successful, or an error message if otherwise.
(c) User log in
Endpoint: POST /user_login
Description: This endpoint allows user authentication. 
Request Body:
JSON object representing the user credentials. It should include the following fields:
username: String (required) - The username of the user.
password: String (required) - The password of the user.
Response: Returns a JSON object indicating the success or failure of the login attempt, along with the ID of the authenticated user if successful, or an error message if otherwise.
Login token will be returned as “SESSION” in the “Set-Cookie” header.
(d) User log out
Endpoint: GET /user_logout
Description: This endpoint logs out the currently authenticated user. 
Response: Returns a JSON object indicating the success or failure of the logout attempt, along with the ID of the logged-out user if successful, or an error message if otherwise.
Due to the JWT token mechanism, the token will still be valid until its expiration. Browsers should delete the token as soon as the empty “SESSION” cookie header is received.

API for Items (MySQL table “items”)(a) Fetch all items/items within specific section or status
Endpoint: GET /items
Description: This endpoint retrieves a list of all items. It supports optional query parameters to filter items based on section ID and status.
Query Parameters:
sectionid (optional): Integer representing the section ID.
status (optional): Enum representing the status of the item.
Response: Returns a JSON array containing all items that match the provided query parameters, or all items if no parameters are provided.
(b) Query a dedicated item
Endpoint: GET /item
Description: This endpoint retrieves information about a specific item based on its ID.
Query Parameters:
id: Integer representing the ID of the item to query.
Response: Returns JSON object representing the item if found, or “null” if no item with the specified ID exists.
(c) Create/update an item
Endpoint REQUIRES AUTHENTICATION: POST /item_update
Description: This endpoint allows creating a new item or updating an existing item.
Request Body:
JSON object representing the item to be created or updated. It should include the following fields:
id: Integer (optional, omit this field or set it to 0 for creation)
name: String
sectionId: Integer
quantity: Integer
value: Double
status: Enum; should be “IN_STOCK” or “OUT_OF_STOCK”
Response: Returns a JSON object indicating the success or failure of the operation, along with the ID of the created or updated item if successful, or an error message if otherwise.
(d) Delete an item
Endpoint REQUIRES AUTHENTICATION: DELETE /item_delete
Description: This endpoint allows deleting an item based on its ID.
Query Parameters:
id: Integer representing the ID of the item to delete.
Response: Returns a JSON object indicating the success or failure of the deletion operation, along with the ID of the deleted item if successful, or an error message if otherwise.
(e) Search items
Endpoint: GET /items_search
Description: This endpoint allows searching for items based on a keyword. It retrieves items whose name contains the specified keyword.
Query Parameters:
keyword: String representing the keyword to search for.
Response: Returns a JSON array containing all items that match the provided keyword, or all items if no keyword is provided.

API for Sections (MySQL table “section”)
(a) Fetch all sections
Endpoint: GET /sections
Description: This endpoint retrieves a list of all sections.
Response: Returns a JSON array containing all sections.
(b) Query a dedicated section
Endpoint: GET /section
Description: This endpoint retrieves information about a specific section based on its ID.
Query Parameters:
id: Integer representing the ID of the section to query.
Response: Returns JSON object representing the section if found, or “null” if no section with the specified ID exists.
(c) Create/update a section
Endpoint REQUIRES AUTHENTICATION: POST /section_update
Description: This endpoint allows creating a new section or updating an existing section.
Request Body:
JSON object representing the section to be created or updated. It should include the following fields:
id: Integer (optional, omit this field or set to 0 for creation)
name: String
description: String
Response: Returns a JSON object indicating the success or failure of the operation, along with the ID of the created or updated section if successful, or an error message if otherwise.
(d) Delete a section
Endpoint REQUIRES AUTHENTICATION: DELETE /section_delete
Description: This endpoint allows deleting a section based on its ID.
Query Parameters:
id: Integer representing the ID of the section to delete.
Response: Returns a JSON object indicating the success or failure of the deletion operation, along with the ID of the deleted section if successful, or an error message if otherwise.

API for Queue (MySQL table “queue”)
(a) Fetch queue records between a given timespan
Endpoint: GET /queues
Description: This endpoint retrieves a list of queue records within a specified time range. Optionally, it can filter queue records based on section ID and/or user ID.
Query Parameters:
start: String representing the start timestamp (format: "yyyy-MM-dd HH:mm:ss").
end: String representing the end timestamp (format: "yyyy-MM-dd HH:mm:ss").
sectionid (optional): Integer representing the ID of the section.
userid (optional): Integer representing the ID of the user.
Response: Returns a JSON array containing queue records that fall within the specified time range and meet the optional filter criteria.
(b) Get queue record by id
Endpoint: GET /queue
Description: This endpoint retrieves information about a specific queue record based on its ID.
Query Parameters:
id: Integer representing the ID of the queue record to retrieve.
Response: Returns JSON object representing the queue record if found, or “null” if no record with the specified ID exists.
(c) Create a queue record
Endpoint REQUIRES AUTHENTICATION: POST /queue_add
Description: This endpoint allows adding a new queue for a dedicated user.
Request Body:
JSON object representing the queue to be added. It should include the following fields:
sectionId: Integer (required) - The ID of the section to which the queue entry belongs.
userId: Integer (required) - The ID of the user associated with the queue entry.
value: Integer - The value associated with the queue entry.
Response: Returns a JSON object indicating the success or failure of the operation, along with the ID of the created or updated section if successful, or an error message if otherwise.
(d) Delete a queue record
Endpoint REQUIRES AUTHENTICATION: DELETE /queue_delete
Description: This endpoint allows deleting a queue record based on its ID.
Query Parameters:
id: Integer representing the ID of the queue record to delete.
Response: Returns a JSON object indicating the success or failure of the deletion operation, along with the ID of the deleted queue record if successful, or an error message if otherwise.

Store Occupancy Reporting (MySQL table “stor_occup”)
(a) Reporting a measure of store occupancy
Endpoint REQUIRES AUTHENTICATION: POST /stor_occup_report
Description: This endpoint allows reporting a measure of store occupancy. It creates a new record in the store occupancy database.
Request Parameters:
stor_occup: Object representing the store occupancy to be reported. It should include the following field:
occupancy: Integer (representing the measure of store occupancy)
Response: Returns a JSON object indicating the success or failure of the reporting operation, along with the ID of the created store occupancy record if successful, or an error message if otherwise.
(b) Get the latest reported measure of store occupancy
Endpoint: GET /stor_occup
Description: This endpoint retrieves the latest reported measure of store occupancy.
Query Parameters:
id (optional): Integer representing the ID of the store occupancy record to retrieve. If not provided, the latest record will be fetched.
Response: Returns JSON object representing the latest reported measure of store occupancy if found, or “null” if otherwise.
Voucher management (MySQL table “voucher”, “voucher_users”)
(a) Voucher enumeration
Endpoint: GET /vouchers
Description: This endpoint retrieves available voucher types (if userid is not provided) or vouchers currently holding by a specific user based on the provided userid.
Query Parameters:
userid (optional): Integer - ID of the user whose vouchers are to be retrieved.
Response: Returns a JSON array containing corresponding voucher records if successful, or error message otherwise.
(b) Voucher awarding
Endpoint REQUIRES AUTHENTICATION: POST /voucher_add
Description: This endpoint adds a voucher to a user account for a specified duration.
Request Body:
JSON object representing the voucher and user to be added. It should include the following fields:
userid: Integer - ID of the user to whom the voucher is to be added.
voucherid: Integer - ID of the voucher to be added.
days: Integer - Duration for which the voucher is to be added (in days).
Response: Returns a JSON object indicating the success or failure of the operation, along with the added voucher details if successful, or an error message if otherwise.
(c) Voucher revoke
Endpoint REQUIRES AUTHENTICATION: POST /voucher_delete
Description: This endpoint adds a voucher to a user account for a specified duration.
Query Parameters:
voucherId: Integer - ID of the voucher to be deleted.
Response: Returns a JSON object indicating the success or failure of the operation, along with the ID of the deleted voucher if successful, or an error message if otherwise.
