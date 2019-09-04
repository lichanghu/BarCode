#import "JdebitScanPlugin.h"
#import <jdebit_scan/jdebit_scan-Swift.h>

@implementation JdebitScanPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftJdebitScanPlugin registerWithRegistrar:registrar];
}
@end
