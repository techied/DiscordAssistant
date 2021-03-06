// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

public interface DeviceActionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.assistant.embedded.v1alpha2.DeviceAction)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * JSON containing the device command response generated from the triggered
   * Device Action grammar. The format is given by the
   * `action.devices.EXECUTE` intent for a given
   * [trait](https://developers.google.com/assistant/sdk/reference/traits/).
   * </pre>
   *
   * <code>string device_request_json = 1;</code>
   */
  String getDeviceRequestJson();
  /**
   * <pre>
   * JSON containing the device command response generated from the triggered
   * Device Action grammar. The format is given by the
   * `action.devices.EXECUTE` intent for a given
   * [trait](https://developers.google.com/assistant/sdk/reference/traits/).
   * </pre>
   *
   * <code>string device_request_json = 1;</code>
   */
  com.google.protobuf.ByteString
      getDeviceRequestJsonBytes();
}
