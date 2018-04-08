#!/bin/bash
curl http://localhost:8080

#--CUSTOMER--
curl -i -X POST -H "Content-Type:application/json" -d "{  \"name\" : \" Springfield Nuclear Power Plant\" }" http://localhost:8080/customers
curl http://localhost:8080/customers

#--EMPLOYEE--
curl -i -X POST -H "Content-Type:application/json" -d "{  \"name\" : \"Homer Jay\",\"lastName\" : \"Simpson\" }" http://localhost:8080/employees
curl http://localhost:8080/employees

#--ACTIVITY TYPE--
curl -i -X POST -H "Content-Type:application/json" -d "{  \"code\" : \"act_empty\",\"description\" : \"Empty\" }"  http://localhost:8080/activityTypes
curl http://localhost:8080/activityTypes

#--ACTIVITY--
curl -i -X POST -H "Content-Type:application/json" -d "{  \"activityType\" : \"http://localhost:8080/activityTypes/1\",\"customer\" : \"http://localhost:8080/customers/1\",\"employee\" : \"http://localhost:8080/employees/1\" }" http://localhost:8080/activities
curl -i -X PATCH -H "Content-Type:application/json" -d "{  \"rate\" : 300.0 }" http://localhost:8080/activities/1
curl http://localhost:8080/activities


#--DAY--
declare days=0
declare month=1
declare year=2018

days=$(cal $month $year | awk 'NF {DAYS = $NF}; END {print DAYS}')

for ((n=1;n<=$days;n++))
do
	  day=$year"-"$(printf %02d $month)"-"$(printf %02d $n)
	  #echo $day
	  curl -i -X POST -H "Content-Type:application/json" -d "{  \"date\" : \"$day\",\"hours\" : 8,\"activity\" : \"http://localhost:8080/activities/1\" }" http://localhost:8080/days
done

curl http://localhost:8080/days
