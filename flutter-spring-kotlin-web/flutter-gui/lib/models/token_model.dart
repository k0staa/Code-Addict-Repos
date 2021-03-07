class TokenModel {
  final String accessToken;
  final String tokenType;
  final String scope;
  final String sessionState;
  final int notBeforePolicy;

  TokenModel(
      {this.accessToken,
      this.tokenType,
      this.scope,
      this.sessionState,
      this.notBeforePolicy});

  factory TokenModel.fromJson(Map<String, dynamic> json) {
    return TokenModel(
      accessToken: json['access_token'],
      tokenType: json['token_type'],
      scope: json['scope'],
      sessionState: json['session_state'],
      notBeforePolicy: json['not-before-policy'],
    );
  }
}
