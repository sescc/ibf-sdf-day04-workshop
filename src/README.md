## Workshop Day 4 - Cookie Generator (Networking - Client Server Arch)

### Server 
```
java -cp target/sdf-workshopd4-1.0-SNAPSHOT.jar sg.edu.nus.iss.workshop4.server.ServerApp 12345 /home/kenneth/Projects/NUS-ISS-SDF/sdf-workshopd4/cookie_file.txt
```

### Client

```
java -cp target/sdf-workshopd4-1.0-SNAPSHOT.jar sg.edu.nus.iss.workshop4.client.ClientApp localhost:12345
```