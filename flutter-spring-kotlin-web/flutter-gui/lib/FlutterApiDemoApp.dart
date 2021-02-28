import 'package:flutter/material.dart';
import 'package:flutter_flavor/flutter_flavor.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'ServerMessage.dart';

class FlutterApiDemoApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
        // This makes the visual density adapt to the platform that you run
        // the app on. For desktop platforms, the controls will be smaller and
        // closer together (more dense) than on mobile platforms.
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter API Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _serverMessage = '';
  Color _serverMessageStyleColor = Colors.blue;

  _fetchServerMessage(final String apiUrl) async {
    final baseUrl = FlavorConfig.instance.variables["baseUrl"];
    final url = '$baseUrl$apiUrl';
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
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
        appBar: AppBar(
          // Here we take the value from the MyHomePage object that was created by
          // the App.build method, and use it to set our appbar title.
          title: Text(widget.title),
        ),
        body: Center(
          // Center is a layout widget. It takes a single child and positions it
          // in the middle of the parent.
          child: Container(
            padding: EdgeInsets.all(20),
            child: Column(
              // Column is also a layout widget. It takes a list of children and
              // arranges them vertically. By default, it sizes itself to fit its
              // children horizontally, and tries to be as tall as its parent.
              //
              // Invoke "debug painting" (press "p" in the console, choose the
              // "Toggle Debug Paint" action from the Flutter Inspector in Android
              // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
              // to see the wireframe for each widget.
              //
              // Column has various properties to control how it sizes itself and
              // how it positions its children. Here we use mainAxisAlignment to
              // center the children vertically; the main axis here is the vertical
              // axis because Columns are vertical (the cross axis would be
              // horizontal).
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
