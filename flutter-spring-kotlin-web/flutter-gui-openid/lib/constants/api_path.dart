import 'package:flutter_flavor/flutter_flavor.dart';

class ApiPath {
  static final BASE_KEYCLOAK_URL =
      FlavorConfig.instance.variables["baseKeycloakUrl"];
  static final BASE_API_URL = FlavorConfig.instance.variables["baseApiUrl"];

  static final BASE_KEYCLOAK_URI =
      Uri.parse('$BASE_KEYCLOAK_URL/auth/realms/kotlin-flutter-demo-realm');
  static final Uri KEYCLOAK_AUTH_URI = Uri.parse(
      '$BASE_KEYCLOAK_URL/auth/realms/kotlin-flutter-demo-realm/protocol/openid-connect/token');
  static final Uri API_SECURED_URI = Uri.parse('$BASE_API_URL/secured');
  static final Uri API_NOT_SECURED_URI = Uri.parse('$BASE_API_URL/not-secured');
}
