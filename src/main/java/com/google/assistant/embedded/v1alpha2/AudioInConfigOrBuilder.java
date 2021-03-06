// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

public interface AudioInConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.assistant.embedded.v1alpha2.AudioInConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * *Required* Encoding of audio data sent in all `audio_in` messages.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.AudioInConfig.Encoding encoding = 1;</code>
   */
  int getEncodingValue();
  /**
   * <pre>
   * *Required* Encoding of audio data sent in all `audio_in` messages.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.AudioInConfig.Encoding encoding = 1;</code>
   */
  AudioInConfig.Encoding getEncoding();

  /**
   * <pre>
   * *Required* Sample rate (in Hertz) of the audio data sent in all `audio_in`
   * messages. Valid values are from 16000-24000, but 16000 is optimal.
   * For best results, set the sampling rate of the audio source to 16000 Hz.
   * If that's not possible, use the native sample rate of the audio source
   * (instead of re-sampling).
   * </pre>
   *
   * <code>int32 sample_rate_hertz = 2;</code>
   */
  int getSampleRateHertz();
}
