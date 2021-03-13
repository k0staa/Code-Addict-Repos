# gui

The project shows how to easily start a Flutter For Web project using VS Code Remote - Containers

The project rebuilds automatically when the code changes and it is possible to debug it.

The project is multi-platform, but I didn't focus on running it on iOS and Android, so there is no configuration or manual for it.

## Running project
### Running using Flutter installed on your OS. 
To run the project you should first install Flutter using [this](https://flutter.dev/docs/get-started/install) instructions. From Flutter version > 2.0 Web is available in stable but before that you need to choose `beta` version of Flutter because Web was only available in beta:
```
 flutter channel beta
 ```
and now you can upgrade SDK and enable Web:
```
 flutter upgrade
 flutter config --enable-web
 ```

To run application you can use:
```
flutter run -d web-server --web-port $FLUTTER_WEB_PORT 
```
and if you want to debug it you can use prepared launch configuration `./.vscode/launch.json`

### Running using VS Code Remote Containers extension
 There is prepared `Dockerfile_dev` with Flutter configuration needed to run application. You can install VS Code [Remote Containers extension](https://code.visualstudio.com/docs/remote/containers#_forwarding-or-publishing-a-port) and it will automatically use provided docker config `./.devcontainer/devcontainer.json`

To run application you can use:
```
flutter run -d web-server --web-port $FLUTTER_WEB_PORT 
```
and if you want to debug it you can use prepared launch configuration `./.vscode/launch.json`

### Open up application and debugging
After you run the application you can open your browser and use [http://localhost:8090/](http://localhost:8090/) link to view the app. If you are using Chrome you can install `Dart Debug Extension` and debug your application (you need to click on extension to enable debug). Launch configuration for debug is part of this repository (`./.vscode/launch.json`). 

### Building Docker image with application
You can build application docker image using multistage Dockerfile configuration. Just use following command in root directory:
```
docker build -t flutter-kotlin-gui .
```

You can run container using following command:
```
docker run -p 80:80 flutter-kotlin-gui 
```

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://flutter.dev/docs/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://flutter.dev/docs/cookbook)

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.
