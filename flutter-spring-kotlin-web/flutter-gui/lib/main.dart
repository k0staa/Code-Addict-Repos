import 'package:flutter/material.dart';
import 'package:flutter_flavor/flutter_flavor.dart';
import 'FlutterApiDemoApp.dart';

void main() {
  FlavorConfig(environment: FlavorEnvironment.DEV, variables: {
    "baseUrl": "http://localhost:8080",
  });

  runApp(FlutterApiDemoApp());
}