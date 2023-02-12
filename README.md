##Cloud Parking

## Run database

docker run --name parking -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=XXXX -e POSTGRES_PASSWORD=XXXX -d postgres:10-alpine

## Start and Stop

### Stop Database

docker stop parking

### Start Database

docker start parking
