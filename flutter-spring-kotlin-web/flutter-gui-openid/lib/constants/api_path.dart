import 'package:flutter_flavor/flutter_flavor.dart';

class ApiPath {
  static final baseKeycloakUrl =
      FlavorConfig.instance.variables["baseKeycloakUrl"];
  static final baseApiUrl = FlavorConfig.instance.variables["baseApiUrl"];

  static final Uri KEYCLOAK_AUTH = Uri.parse(
      '$baseKeycloakUrl/auth/realms/kotlin-flutter-demo-realm/protocol/openid-connect/token');
  static final Uri API_SECURED = Uri.parse('$baseApiUrl/secured');
  static final Uri API_NOT_SECURED = Uri.parse('$baseApiUrl/not-secured');
}
