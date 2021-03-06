import 'package:flutter/material.dart';
import 'package:flutter_flavor/flutter_flavor.dart';
import 'package:gui/LoginPage.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'ServerMessage.dart';

class FlutterApiDemoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter API Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _serverMessage = '';
  Color _serverMessageStyleColor = Colors.blue;

  _fetchServerMessage(final String apiUrl) async {
    final baseUrl = FlavorConfig.instance.variables["baseApiUrl"];
    final url = Uri.parse('$baseUrl$apiUrl');
    final response = await http.get(url);
    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      final serverMessage = ServerMessage.fromJson(json);
      _updateServerMessage(serverMessage);
      _updateServerMessageStyleColor(Colors.blue);
    } else {
      final requestFailedMsg = "Failed to fetch data from: $apiUrl";
      debugPrint(requestFailedMsg);
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
                padding: EdgeInsets.only(right: 40.0),
                child: GestureDetector(
                  onTap: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => LoginPage()));
                  },
                  child: Icon(
                    Icons.portrait,
                    size: 26.0,
                  ),
                )),
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
                          onPressed: () => _fetchServerMessage("/not-secured"),
                        )
                      ],
                    ),
                    Column(
                      children: [
                        ElevatedButton(
                          child: Text('Secured API'),
                          onPressed: () => _fetchServerMessage("/secured"),
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
