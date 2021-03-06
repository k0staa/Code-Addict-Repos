import 'package:flutter/material.dart';
import 'package:flutter_flavor/flutter_flavor.dart';
import 'FlutterApiDemoApp.dart';

void main() {
   FlavorConfig(
        name: "DEV",
        color: Colors.red,
        location: BannerLocation.bottomStart,
        variables: {
            "baseUrl": "http://localhost:8080",
        },
    );

  runApp(FlutterApiDemoApp());
}