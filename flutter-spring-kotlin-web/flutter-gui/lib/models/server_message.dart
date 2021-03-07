class ServerMessage {
  final String message;

  ServerMessage({this.message});

  factory ServerMessage.fromJson(Map<String, dynamic> json) {
    return ServerMessage(message: json['message']);
  }
}
