import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:jdebit_scan/jdebit_scan.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String barcode = "";

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Barcode Scanner Example'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              Container(
                child: MaterialButton(onPressed: scan, child: Text("Scan")),
                padding: const EdgeInsets.all(8.0),
              ),
              Text(barcode),
            ],
          ),
        ),
      ),
    );
  }


  Future scan() async {
    try {
      String barcode = await JdebitScan.scan();
      setState(() => this.barcode = barcode);
    } on PlatformException catch (e) {
      if (e.code == JdebitScan.CameraAccessDenied) {
        setState(() {
          this.barcode = 'The user did not grant the camera permission!';
        });
      } else {
        setState(() => this.barcode = 'Unknown error: $e');
      }
    } on FormatException{
      setState(() => this.barcode = 'null (User returned using the "back"-button before scanning anything. Result)');
    } catch (e) {
      setState(() => this.barcode = 'Unknown error: $e');
    }
  }



}
