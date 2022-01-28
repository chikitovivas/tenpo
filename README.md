TO RUN APP

Enter to docker directory `cd src/main/docker/`

Clean containers `docker-compose rm -f`

Run app `docker-compose up --build -d`

Which port is attached to app and db `docker container ls`

The app port given in the previous command, change it in the postman collection environment and its done.

With the db port, you could access to the db, there is no auth required.