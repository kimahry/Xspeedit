# Xspeedit - The boxing program


## Version Java
Requires `Java 8`, `maven 3`

To launch the tests
```
mvn test 
```

To launch the program
```
mvn exec:java -Dexec.args=xxxxxxxxxxxxx
```

Where xxxxxxxxxxxxx are number from 1 to 9


## Version Elixir
Requires `Erlang (to run program) and Elixir (to run the tests)` or `Docker`

To launch the tests
```
Elixir: mix test
Docker: docker-compose up
```

To launch the program
```
Erlang: escript xspeedit -c=xxxxxxxxxx
Docker: docker-compose run prog escript xspeedit -c=xxxxxxxxxx
```

Where xxxxxxxxxxxxx are number from 1 to 9
