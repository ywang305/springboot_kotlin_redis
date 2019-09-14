# springboot_kotlin_redis
springboot kotlin tech stack in action

#### test via postman
example:
- post http://localhost:8080/redis
  ```
  {
    "date": "2019-01-12",
    "time": "11:05 AM",
    "addressList": ["addr-12", "addr-22"]
  }
  ```
  expect
  ```
  {
      "id": 2
  }
  ```
 
- get http://localhost:8080/redis?id=2
  expect
  ```
  {
    "date": "2019-01-12",
    "time": "11:05 AM",
    "addressList": [
        "addr-12",
        "addr-22"
    ]
  }
  ```
