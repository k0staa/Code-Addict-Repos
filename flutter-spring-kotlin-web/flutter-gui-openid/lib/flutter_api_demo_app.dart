import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:gui/constants/api_path.dart';
import 'package:gui/login_page.dart';
import 'package:gui/models/api_response.dart';
import 'package:gui/services/rest_api_service.dart';
import 'models/server_message.dart';
import 'package:keycloak_flutter/keycloak_flutter.dart';
import 'dart:html';

class FlutterApiDemoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter with Keycloak Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: UserHomePage(
        username: null,
      ),
    );
  }
}

class UserHomePage extends StatefulWidget {
  UserHomePage({Key key, this.username}) : super(key: key);
  final String title = 'User Home Page';
  final String username;
  @override
  _UserHomePageState createState() => _UserHomePageState();
}

class _UserHomePageState extends State<UserHomePage> {
  String _serverMessage = '';
  Color _serverMessageStyleColor = Colors.blue;
  KeycloakProfile _keycloakProfile;
  KeycloakService _keycloakService;

  _fetchSecuredServerMessage() async {
    var apiService = await RestApiService.getInstance();
    final response = await apiService.apiGetSecured<ServerMessage>(
        ApiPath.API_SECURED_URI, (json) => ServerMessage.fromJson(json));
    _updateState(response);
  }

  _fetchNotSecuredServerMessage() async {
    var apiService = await RestApiService.getInstance();
    final response = await apiService.apiGetNotSecured<ServerMessage>(
        ApiPath.API_NOT_SECURED_URI, (json) => ServerMessage.fromJson(json));
    _updateState(response);
  }

  void _updateState(ApiResponse<ServerMessage> response) {
    if (response.code == 200) {
      _updateServerMessage(response.body);
      _updateServerMessageStyleColor(Colors.blue);
    } else {
      final requestFailedMsg =
          "Failed to fetch data from: ${ApiPath.API_SECURED_URI}";
      _updateServerMessage(new ServerMessage(message: requestFailedMsg));
      _updateServerMessageStyleColor(Colors.red);
    }
  }

  void _updateServerMessage(ServerMessage serverMessage) {
    setState(() {
      _serverMessage = serverMessage.message;
    });
  }

  void _updateServerMessageStyleColor(MaterialColor newColor) {
    setState(() {
      _serverMessageStyleColor = newColor;
    });
  }

  void _login() async {
    _keycloakService.login(KeycloakLoginOptions(
      redirectUri: "http://localhost:8090/",
    ));
    _keycloakProfile = await _keycloakService.loadUserProfile();
    debugPrint(_keycloakProfile.email);
  }

  @override
  void initState() {
    super.initState();
    _keycloakService = initService();
  }

  // @override
  // void initState() {
  //   // TODO: implement initState
  //   super.initState();
  //   WidgetsBinding.instance?.addPostFrameCallback((timeStamp) async {
  //     _keycloakService.keycloakEventsStream.listen((event) async {
  //       if (event.type == KeycloakEventType.onAuthSuccess) {
  //         _keycloakProfile = await _keycloakService.loadUserProfile();
  //       } else {
  //         _keycloakProfile = null;
  //       }
  //       setState(() {});
  //     });
  //     _keycloakProfile = await _keycloakService.loadUserProfile(false);
  //     setState(() {});
  //   });
  // }

  KeycloakService initService() {
    var keycloakService = KeycloakService(KeycloakConfig(
        url: '${ApiPath.BASE_KEYCLOAK_URL}/auth',
        realm: 'kotlin-flutter-demo-realm',
        clientId: 'login-app'));
    keycloakService.keycloakEventsStream.listen((event) {
      if (event.type == KeycloakEventType.onAuthSuccess) {
        // User is authenticated
        debugPrint("HAHA");
      }
    });
    keycloakService.init(
      initOptions: KeycloakInitOptions(
          enableLogging: true,
          redirectUri: "http://localhost:8090/",
          flow: "implicit"),
    );
    return keycloakService;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
          leading: GestureDetector(
            onTap: () {
              debugPrint("Future menu");
            },
            child: Icon(
              Icons.menu,
            ),
          ),
          actions: <Widget>[
            Padding(
              padding: EdgeInsets.only(top: 20.0),
              child: Text("Username: ${widget.username}"),
            ),
            Padding(
                padding: EdgeInsets.only(right: 40.0),
                child: GestureDetector(
                  onTap: () {
                    _login();
                  },
                  child: Icon(
                    Icons.portrait,
                    size: 26.0,
                  ),
                ))
          ],
        ),
        body: Center(
          child: Container(
            padding: EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Column(
                      children: [
                        ElevatedButton(
                          child: Text('Non secured API'),
                          onPressed: () => _fetchNotSecuredServerMessage(),
                        )
                      ],
                    ),
                    Column(
                      children: [
                        ElevatedButton(
                          child: Text('Secured API'),
                          onPressed: () => _fetchSecuredServerMessage(),
                        )
                      ],
                    )
                  ],
                ),
                Divider(),
                Row(mainAxisAlignment: MainAxisAlignment.center, children: [
                  Text(
                    '$_serverMessage',
                    style: new TextStyle(
                        inherit: true,
                        fontSize: 20.0,
                        fontWeight: FontWeight.bold,
                        decorationStyle: TextDecorationStyle.wavy,
                        color: _serverMessageStyleColor),
                  ),
                ])
              ],
            ),
          ),
        ));
  }
}
