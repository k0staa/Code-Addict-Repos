import 'package:flutter/material.dart';
import 'package:flutter_flavor/flutter_flavor.dart';
import 'flutter_api_demo_app.dart';


void main() {
   FlavorConfig(
        name: "DEV",
        color: Colors.red,
        location: BannerLocation.bottomStart,
        variables: {
            "baseApiUrl": "http://localhost:8080",
            "baseKeycloakUrl": "http://localhost:8081",
        },
    );

  runApp(FlutterApiDemoApp());
}