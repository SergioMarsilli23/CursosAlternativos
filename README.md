# CursosAlternativos

## Requerimientos equipo local

Los requerimientos para poder llevar a cabo la construcción y despliegue del proyecto son:
- [AdotJDK 11](https://adoptopenjdk.net/)
- [docker](https://docs.docker.com/install/)
- [docker-compose](https://docs.docker.com/compose/install/)
- [make](https://www.gnu.org/software/make/)
- [terminal bash]

### Docker
Descargar e instalar Docker.

Para validar versión de Docker:

```bash
$ docker version 
```
```
Client: Docker Engine - Community
 Version:           19.03.3
 API version:       1.40
 Go version:        go1.12.10
 Git commit:        a872fc2f86
 Built:             Tue Oct  8 01:00:44 2019
 OS/Arch:           linux/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          19.03.3
  API version:      1.40 (minimum version 1.12)
  Go version:       go1.12.10
  Git commit:       a872fc2f86
  Built:            Tue Oct  8 00:59:17 2019
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.2.6
  GitCommit:        894b81a4b802e4eb2a91d1ce216b8817763c29fb
 runc:
  Version:          1.0.0-rc8
  GitCommit:        425e105d5a03fabd737a126ad93d62a9eeede87f
 docker-init:
  Version:          0.18.0
  GitCommit:        fec3683
```

### Docker-compose

Para validar versión de docker-compose

```bash
$ docker-compose --version
docker-compose version 1.24.1, build 4667896b
```

#### Swagger ####
El proyecto usa la versión 3 de swagger para documentar los RestControllers.
Una vez lanzda la aplicación podemos acceder al swagger de la aplicación usaremos la siguiente Url:
    [Swagger](http://localhost:8080/swagger-ui/index.html)

Para autenticarse en Swagger, tenemos un usuario de pruebas (tutum/1234), hay que hacerlo a través del AuthController y luego guardar el token
usando el botón Authorize.

#### Seguridad Rest ####

Utilizamos JWT para securizar las llamadas rest, la configuración quedaría dentro del package /shared/infrastructure/config/security, dentro quedarían las siguientes clases:

	- JWTAuthorizationFilter: Filtro encargado de validar el token en cada petición y devolver el status 401 en caso de no tener autorización.
	- JWTConfig: Contiene la configuración de JWT, Incluye los paths que quedan excluidos de la autenticación.
	- JWTTokenGenerator: Se encarga de generar el token a partir del nombre del usuario.
	- SecurityConstant: Contiene la secret key y el tiempo de expiración del token.

	### Inicialización
Para ejecutar la inicialización del proyecto, construcción y despliegue en local, por primera vez debemos ejecutar el comando:

```bash
$ make initialize
```

### Despliegue

Disponemos de comandos para parar 

```bash
$ make stop
```

y/o a arrancar el entorno mediante
```bash
$ make start
```

### Construcción

Para la construcción del proyecto podemos ejecutar:

```bash
$ make build
```
Este comando incluye la validación de unas reglas mínimas de calidad de código y la ejecución de los test.
