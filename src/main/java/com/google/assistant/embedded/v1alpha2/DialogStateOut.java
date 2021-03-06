// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

/**
 * <pre>
 * The dialog state resulting from the user's query. Multiple of these messages
 * may be received.
 * </pre>
 *
 * Protobuf type {@code google.assistant.embedded.v1alpha2.DialogStateOut}
 */
public  final class DialogStateOut extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.assistant.embedded.v1alpha2.DialogStateOut)
    DialogStateOutOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DialogStateOut.newBuilder() to construct.
  private DialogStateOut(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DialogStateOut() {
    supplementalDisplayText_ = "";
    conversationState_ = com.google.protobuf.ByteString.EMPTY;
    microphoneMode_ = 0;
    volumePercentage_ = 0;
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DialogStateOut(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            String s = input.readStringRequireUtf8();

            supplementalDisplayText_ = s;
            break;
          }
          case 18: {

            conversationState_ = input.readBytes();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            microphoneMode_ = rawValue;
            break;
          }
          case 32: {

            volumePercentage_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateOut_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateOut_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            DialogStateOut.class, Builder.class);
  }

  /**
   * <pre>
   * Possible states of the microphone after a `Assist` RPC completes.
   * </pre>
   *
   * Protobuf enum {@code google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode}
   */
  public enum MicrophoneMode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * No mode specified.
     * </pre>
     *
     * <code>MICROPHONE_MODE_UNSPECIFIED = 0;</code>
     */
    MICROPHONE_MODE_UNSPECIFIED(0),
    /**
     * <pre>
     * The service is not expecting a follow-on question from the user.
     * The microphone should remain off until the user re-activates it.
     * </pre>
     *
     * <code>CLOSE_MICROPHONE = 1;</code>
     */
    CLOSE_MICROPHONE(1),
    /**
     * <pre>
     * The service is expecting a follow-on question from the user. The
     * microphone should be re-opened when the `AudioOut` playback completes
     * (by starting a new `Assist` RPC call to send the new audio).
     * </pre>
     *
     * <code>DIALOG_FOLLOW_ON = 2;</code>
     */
    DIALOG_FOLLOW_ON(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     * No mode specified.
     * </pre>
     *
     * <code>MICROPHONE_MODE_UNSPECIFIED = 0;</code>
     */
    public static final int MICROPHONE_MODE_UNSPECIFIED_VALUE = 0;
    /**
     * <pre>
     * The service is not expecting a follow-on question from the user.
     * The microphone should remain off until the user re-activates it.
     * </pre>
     *
     * <code>CLOSE_MICROPHONE = 1;</code>
     */
    public static final int CLOSE_MICROPHONE_VALUE = 1;
    /**
     * <pre>
     * The service is expecting a follow-on question from the user. The
     * microphone should be re-opened when the `AudioOut` playback completes
     * (by starting a new `Assist` RPC call to send the new audio).
     * </pre>
     *
     * <code>DIALOG_FOLLOW_ON = 2;</code>
     */
    public static final int DIALOG_FOLLOW_ON_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static MicrophoneMode valueOf(int value) {
      return forNumber(value);
    }

    public static MicrophoneMode forNumber(int value) {
      switch (value) {
        case 0: return MICROPHONE_MODE_UNSPECIFIED;
        case 1: return CLOSE_MICROPHONE;
        case 2: return DIALOG_FOLLOW_ON;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MicrophoneMode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        MicrophoneMode> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MicrophoneMode>() {
            public MicrophoneMode findValueByNumber(int number) {
              return MicrophoneMode.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return DialogStateOut.getDescriptor().getEnumTypes().get(0);
    }

    private static final MicrophoneMode[] VALUES = values();

    public static MicrophoneMode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private MicrophoneMode(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode)
  }

  public static final int SUPPLEMENTAL_DISPLAY_TEXT_FIELD_NUMBER = 1;
  private volatile Object supplementalDisplayText_;
  /**
   * <pre>
   * *Output-only* Supplemental display text from the Assistant. This could be
   * the same as the speech spoken in `AssistResponse.audio_out` or it could
   * be some additional information which aids the user's understanding.
   * </pre>
   *
   * <code>string supplemental_display_text = 1;</code>
   */
  public String getSupplementalDisplayText() {
    Object ref = supplementalDisplayText_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      supplementalDisplayText_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * *Output-only* Supplemental display text from the Assistant. This could be
   * the same as the speech spoken in `AssistResponse.audio_out` or it could
   * be some additional information which aids the user's understanding.
   * </pre>
   *
   * <code>string supplemental_display_text = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSupplementalDisplayTextBytes() {
    Object ref = supplementalDisplayText_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      supplementalDisplayText_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONVERSATION_STATE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString conversationState_;
  /**
   * <pre>
   * *Output-only* State information for the subsequent `Assist` RPC. This
   * value should be saved in the client and returned in the
   * [`DialogStateIn.conversation_state`](#dialogstatein) field with the next
   * `Assist` RPC. (The client does not need to interpret or otherwise use this
   * value.) This information should be saved across device reboots. However,
   * this value should be cleared (not saved in the client) during a
   * factory-default reset.
   * </pre>
   *
   * <code>bytes conversation_state = 2;</code>
   */
  public com.google.protobuf.ByteString getConversationState() {
    return conversationState_;
  }

  public static final int MICROPHONE_MODE_FIELD_NUMBER = 3;
  private int microphoneMode_;
  /**
   * <pre>
   * *Output-only* Specifies the mode of the microphone after this `Assist`
   * RPC is processed.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
   */
  public int getMicrophoneModeValue() {
    return microphoneMode_;
  }
  /**
   * <pre>
   * *Output-only* Specifies the mode of the microphone after this `Assist`
   * RPC is processed.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
   */
  public MicrophoneMode getMicrophoneMode() {
    MicrophoneMode result = MicrophoneMode.valueOf(microphoneMode_);
    return result == null ? MicrophoneMode.UNRECOGNIZED : result;
  }

  public static final int VOLUME_PERCENTAGE_FIELD_NUMBER = 4;
  private int volumePercentage_;
  /**
   * <pre>
   * *Output-only* Updated volume level. The value will be 0 or omitted
   * (indicating no change) unless a voice command such as *Increase the volume*
   * or *Set volume level 4* was recognized, in which case the value will be
   * between 1 and 100 (corresponding to the new volume level of 1% to 100%).
   * Typically, a client should use this volume level when playing the
   * `audio_out` data, and retain this value as the current volume level and
   * supply it in the `AudioOutConfig` of the next `AssistRequest`. (Some
   * clients may also implement other ways to allow the current volume level to
   * be changed, for example, by providing a knob that the user can turn.)
   * </pre>
   *
   * <code>int32 volume_percentage = 4;</code>
   */
  public int getVolumePercentage() {
    return volumePercentage_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getSupplementalDisplayTextBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, supplementalDisplayText_);
    }
    if (!conversationState_.isEmpty()) {
      output.writeBytes(2, conversationState_);
    }
    if (microphoneMode_ != MicrophoneMode.MICROPHONE_MODE_UNSPECIFIED.getNumber()) {
      output.writeEnum(3, microphoneMode_);
    }
    if (volumePercentage_ != 0) {
      output.writeInt32(4, volumePercentage_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSupplementalDisplayTextBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, supplementalDisplayText_);
    }
    if (!conversationState_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, conversationState_);
    }
    if (microphoneMode_ != MicrophoneMode.MICROPHONE_MODE_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, microphoneMode_);
    }
    if (volumePercentage_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, volumePercentage_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof DialogStateOut)) {
      return super.equals(obj);
    }
    DialogStateOut other = (DialogStateOut) obj;

    boolean result = true;
    result = result && getSupplementalDisplayText()
        .equals(other.getSupplementalDisplayText());
    result = result && getConversationState()
        .equals(other.getConversationState());
    result = result && microphoneMode_ == other.microphoneMode_;
    result = result && (getVolumePercentage()
        == other.getVolumePercentage());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SUPPLEMENTAL_DISPLAY_TEXT_FIELD_NUMBER;
    hash = (53 * hash) + getSupplementalDisplayText().hashCode();
    hash = (37 * hash) + CONVERSATION_STATE_FIELD_NUMBER;
    hash = (53 * hash) + getConversationState().hashCode();
    hash = (37 * hash) + MICROPHONE_MODE_FIELD_NUMBER;
    hash = (53 * hash) + microphoneMode_;
    hash = (37 * hash) + VOLUME_PERCENTAGE_FIELD_NUMBER;
    hash = (53 * hash) + getVolumePercentage();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static DialogStateOut parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateOut parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateOut parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateOut parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateOut parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateOut parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateOut parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DialogStateOut parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static DialogStateOut parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static DialogStateOut parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static DialogStateOut parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DialogStateOut parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(DialogStateOut prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The dialog state resulting from the user's query. Multiple of these messages
   * may be received.
   * </pre>
   *
   * Protobuf type {@code google.assistant.embedded.v1alpha2.DialogStateOut}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.assistant.embedded.v1alpha2.DialogStateOut)
      DialogStateOutOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateOut_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateOut_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              DialogStateOut.class, Builder.class);
    }

    // Construct using com.google.assistant.embedded.v1alpha2.DialogStateOut.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      supplementalDisplayText_ = "";

      conversationState_ = com.google.protobuf.ByteString.EMPTY;

      microphoneMode_ = 0;

      volumePercentage_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateOut_descriptor;
    }

    public DialogStateOut getDefaultInstanceForType() {
      return DialogStateOut.getDefaultInstance();
    }

    public DialogStateOut build() {
      DialogStateOut result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public DialogStateOut buildPartial() {
      DialogStateOut result = new DialogStateOut(this);
      result.supplementalDisplayText_ = supplementalDisplayText_;
      result.conversationState_ = conversationState_;
      result.microphoneMode_ = microphoneMode_;
      result.volumePercentage_ = volumePercentage_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof DialogStateOut) {
        return mergeFrom((DialogStateOut)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(DialogStateOut other) {
      if (other == DialogStateOut.getDefaultInstance()) return this;
      if (!other.getSupplementalDisplayText().isEmpty()) {
        supplementalDisplayText_ = other.supplementalDisplayText_;
        onChanged();
      }
      if (other.getConversationState() != com.google.protobuf.ByteString.EMPTY) {
        setConversationState(other.getConversationState());
      }
      if (other.microphoneMode_ != 0) {
        setMicrophoneModeValue(other.getMicrophoneModeValue());
      }
      if (other.getVolumePercentage() != 0) {
        setVolumePercentage(other.getVolumePercentage());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      DialogStateOut parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (DialogStateOut) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object supplementalDisplayText_ = "";
    /**
     * <pre>
     * *Output-only* Supplemental display text from the Assistant. This could be
     * the same as the speech spoken in `AssistResponse.audio_out` or it could
     * be some additional information which aids the user's understanding.
     * </pre>
     *
     * <code>string supplemental_display_text = 1;</code>
     */
    public String getSupplementalDisplayText() {
      Object ref = supplementalDisplayText_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        supplementalDisplayText_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * *Output-only* Supplemental display text from the Assistant. This could be
     * the same as the speech spoken in `AssistResponse.audio_out` or it could
     * be some additional information which aids the user's understanding.
     * </pre>
     *
     * <code>string supplemental_display_text = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSupplementalDisplayTextBytes() {
      Object ref = supplementalDisplayText_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        supplementalDisplayText_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * *Output-only* Supplemental display text from the Assistant. This could be
     * the same as the speech spoken in `AssistResponse.audio_out` or it could
     * be some additional information which aids the user's understanding.
     * </pre>
     *
     * <code>string supplemental_display_text = 1;</code>
     */
    public Builder setSupplementalDisplayText(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      supplementalDisplayText_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* Supplemental display text from the Assistant. This could be
     * the same as the speech spoken in `AssistResponse.audio_out` or it could
     * be some additional information which aids the user's understanding.
     * </pre>
     *
     * <code>string supplemental_display_text = 1;</code>
     */
    public Builder clearSupplementalDisplayText() {
      
      supplementalDisplayText_ = getDefaultInstance().getSupplementalDisplayText();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* Supplemental display text from the Assistant. This could be
     * the same as the speech spoken in `AssistResponse.audio_out` or it could
     * be some additional information which aids the user's understanding.
     * </pre>
     *
     * <code>string supplemental_display_text = 1;</code>
     */
    public Builder setSupplementalDisplayTextBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      supplementalDisplayText_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString conversationState_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * *Output-only* State information for the subsequent `Assist` RPC. This
     * value should be saved in the client and returned in the
     * [`DialogStateIn.conversation_state`](#dialogstatein) field with the next
     * `Assist` RPC. (The client does not need to interpret or otherwise use this
     * value.) This information should be saved across device reboots. However,
     * this value should be cleared (not saved in the client) during a
     * factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 2;</code>
     */
    public com.google.protobuf.ByteString getConversationState() {
      return conversationState_;
    }
    /**
     * <pre>
     * *Output-only* State information for the subsequent `Assist` RPC. This
     * value should be saved in the client and returned in the
     * [`DialogStateIn.conversation_state`](#dialogstatein) field with the next
     * `Assist` RPC. (The client does not need to interpret or otherwise use this
     * value.) This information should be saved across device reboots. However,
     * this value should be cleared (not saved in the client) during a
     * factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 2;</code>
     */
    public Builder setConversationState(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      conversationState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* State information for the subsequent `Assist` RPC. This
     * value should be saved in the client and returned in the
     * [`DialogStateIn.conversation_state`](#dialogstatein) field with the next
     * `Assist` RPC. (The client does not need to interpret or otherwise use this
     * value.) This information should be saved across device reboots. However,
     * this value should be cleared (not saved in the client) during a
     * factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 2;</code>
     */
    public Builder clearConversationState() {
      
      conversationState_ = getDefaultInstance().getConversationState();
      onChanged();
      return this;
    }

    private int microphoneMode_ = 0;
    /**
     * <pre>
     * *Output-only* Specifies the mode of the microphone after this `Assist`
     * RPC is processed.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
     */
    public int getMicrophoneModeValue() {
      return microphoneMode_;
    }
    /**
     * <pre>
     * *Output-only* Specifies the mode of the microphone after this `Assist`
     * RPC is processed.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
     */
    public Builder setMicrophoneModeValue(int value) {
      microphoneMode_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* Specifies the mode of the microphone after this `Assist`
     * RPC is processed.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
     */
    public MicrophoneMode getMicrophoneMode() {
      MicrophoneMode result = MicrophoneMode.valueOf(microphoneMode_);
      return result == null ? MicrophoneMode.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     * *Output-only* Specifies the mode of the microphone after this `Assist`
     * RPC is processed.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
     */
    public Builder setMicrophoneMode(MicrophoneMode value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      microphoneMode_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* Specifies the mode of the microphone after this `Assist`
     * RPC is processed.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DialogStateOut.MicrophoneMode microphone_mode = 3;</code>
     */
    public Builder clearMicrophoneMode() {
      
      microphoneMode_ = 0;
      onChanged();
      return this;
    }

    private int volumePercentage_ ;
    /**
     * <pre>
     * *Output-only* Updated volume level. The value will be 0 or omitted
     * (indicating no change) unless a voice command such as *Increase the volume*
     * or *Set volume level 4* was recognized, in which case the value will be
     * between 1 and 100 (corresponding to the new volume level of 1% to 100%).
     * Typically, a client should use this volume level when playing the
     * `audio_out` data, and retain this value as the current volume level and
     * supply it in the `AudioOutConfig` of the next `AssistRequest`. (Some
     * clients may also implement other ways to allow the current volume level to
     * be changed, for example, by providing a knob that the user can turn.)
     * </pre>
     *
     * <code>int32 volume_percentage = 4;</code>
     */
    public int getVolumePercentage() {
      return volumePercentage_;
    }
    /**
     * <pre>
     * *Output-only* Updated volume level. The value will be 0 or omitted
     * (indicating no change) unless a voice command such as *Increase the volume*
     * or *Set volume level 4* was recognized, in which case the value will be
     * between 1 and 100 (corresponding to the new volume level of 1% to 100%).
     * Typically, a client should use this volume level when playing the
     * `audio_out` data, and retain this value as the current volume level and
     * supply it in the `AudioOutConfig` of the next `AssistRequest`. (Some
     * clients may also implement other ways to allow the current volume level to
     * be changed, for example, by providing a knob that the user can turn.)
     * </pre>
     *
     * <code>int32 volume_percentage = 4;</code>
     */
    public Builder setVolumePercentage(int value) {
      
      volumePercentage_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Output-only* Updated volume level. The value will be 0 or omitted
     * (indicating no change) unless a voice command such as *Increase the volume*
     * or *Set volume level 4* was recognized, in which case the value will be
     * between 1 and 100 (corresponding to the new volume level of 1% to 100%).
     * Typically, a client should use this volume level when playing the
     * `audio_out` data, and retain this value as the current volume level and
     * supply it in the `AudioOutConfig` of the next `AssistRequest`. (Some
     * clients may also implement other ways to allow the current volume level to
     * be changed, for example, by providing a knob that the user can turn.)
     * </pre>
     *
     * <code>int32 volume_percentage = 4;</code>
     */
    public Builder clearVolumePercentage() {
      
      volumePercentage_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.assistant.embedded.v1alpha2.DialogStateOut)
  }

  // @@protoc_insertion_point(class_scope:google.assistant.embedded.v1alpha2.DialogStateOut)
  private static final DialogStateOut DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new DialogStateOut();
  }

  public static DialogStateOut getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DialogStateOut>
      PARSER = new com.google.protobuf.AbstractParser<DialogStateOut>() {
    public DialogStateOut parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DialogStateOut(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DialogStateOut> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<DialogStateOut> getParserForType() {
    return PARSER;
  }

  public DialogStateOut getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

