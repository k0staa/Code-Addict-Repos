import 'package:gui/models/json_serializable.dart';

class ServerMessage extends JsonSerializable<ServerMessage> {
  final String message;

  ServerMessage({this.message});

  factory ServerMessage.fromJson(Map<String, dynamic> json) {
    return ServerMessage(message: json['message']);
  }

  @override
  ServerMessage fromJson(Map<String, dynamic> json) {
    return ServerMessage(message: json['message']);
  }
}
