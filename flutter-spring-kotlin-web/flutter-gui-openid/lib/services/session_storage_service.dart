import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';
import 'package:gui/models/token_model.dart';

class SessionStorageService {
  static SessionStorageService manager;
  static SharedPreferences _prefs;
  static const String ACCESS_TOKEN_KEY = "ACCESS_TOKEN";

  static Future<SessionStorageService> getInstance() async {
    if (manager == null || _prefs == null) {
      manager = SessionStorageService();
      _prefs = await SharedPreferences.getInstance();
    }
    return manager;
  }

  void saveAccessToken(String accessToken) {
    _prefs.setString(ACCESS_TOKEN_KEY, accessToken);
  }

  String retriveAccessToken() {
    var tokenJson = _prefs.getString(ACCESS_TOKEN_KEY);
    if (tokenJson == null) {
      return null;
    }
    return TokenModel.fromJson(jsonDecode(tokenJson)).accessToken;
  }
}
