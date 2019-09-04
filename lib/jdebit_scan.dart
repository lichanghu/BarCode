import 'dart:async';

import 'package:flutter/services.dart';

class JdebitScan {
  static const CameraAccessDenied = 'PERMISSION_NOT_GRANTED';
  static const UserCanceled = 'USER_CANCELED';

  static const MethodChannel _channel = const MethodChannel('jdebit_scan');

  static Future<String> scan() async => await _channel.invokeMethod('scan');

}
