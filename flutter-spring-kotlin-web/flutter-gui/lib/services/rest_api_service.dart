import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:gui/models/api_response.dart';
import 'package:gui/services/session_storage_service.dart';
import 'package:http/http.dart' as http;

class RestApiService {
  static RestApiService service;

  static Future<RestApiService> getInstance() async {
    if (service == null) {
      service = RestApiService();
    }
    return service;
  }

  Future<ApiResponse<T>> apiGetSecured<T>(
      Uri uri, T Function(Map<String, dynamic>) fromJson) async {
    final response = await http.get(uri);
    if (response.statusCode == 200) {
      final parsedBody = deserialize<T>(response.body, (x) => fromJson(x));
      return ApiResponse<T>(
          body: parsedBody, code: response.statusCode);
    } else {
      final requestFailedMsg = "Failed to fetch data from: $uri";
      debugPrint(requestFailedMsg);
      return ApiResponse<T>(body: null, code: response.statusCode);
    }
  }

  Future<ApiResponse<T>> apiGetNotSecured<T>(
      Uri uri, T Function(Map<String, dynamic>) fromJson) async {
    final response = await http.get(uri);
    if (response.statusCode == 200) {
      final parsedBody = deserialize<T>(response.body, (x) => fromJson(x));
      return ApiResponse<T>(
          body: parsedBody, code: response.statusCode);
    } else {
      final requestFailedMsg = "Failed to fetch data from: $uri";
      debugPrint(requestFailedMsg);
      return ApiResponse<T>(body: null, code: response.statusCode);
    }
  }

  T deserialize<T>(
    String json,
    T factory(Map<String, dynamic> data),
  ) {
    return factory(jsonDecode(json) as Map<String, dynamic>);
  }
}
