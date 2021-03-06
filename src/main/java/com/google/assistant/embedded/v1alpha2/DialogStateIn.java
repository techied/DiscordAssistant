// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

/**
 * <pre>
 * Provides information about the current dialog state.
 * </pre>
 *
 * Protobuf type {@code google.assistant.embedded.v1alpha2.DialogStateIn}
 */
public  final class DialogStateIn extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.assistant.embedded.v1alpha2.DialogStateIn)
    DialogStateInOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DialogStateIn.newBuilder() to construct.
  private DialogStateIn(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DialogStateIn() {
    conversationState_ = com.google.protobuf.ByteString.EMPTY;
    languageCode_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DialogStateIn(
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

            conversationState_ = input.readBytes();
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            languageCode_ = s;
            break;
          }
          case 42: {
            DeviceLocation.Builder subBuilder = null;
            if (deviceLocation_ != null) {
              subBuilder = deviceLocation_.toBuilder();
            }
            deviceLocation_ = input.readMessage(DeviceLocation.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(deviceLocation_);
              deviceLocation_ = subBuilder.buildPartial();
            }

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
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateIn_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateIn_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            DialogStateIn.class, Builder.class);
  }

  public static final int CONVERSATION_STATE_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString conversationState_;
  /**
   * <pre>
   * *Required* This field must always be set to the
   * [DialogStateOut.conversation_state][google.assistant.embedded.v1alpha2.DialogStateOut.conversation_state] value that was returned in the prior
   * `Assist` RPC. It should only be omitted (field not set) if there was no
   * prior `Assist` RPC because this is the first `Assist` RPC made by this
   * device after it was first setup and/or a factory-default reset.
   * </pre>
   *
   * <code>bytes conversation_state = 1;</code>
   */
  public com.google.protobuf.ByteString getConversationState() {
    return conversationState_;
  }

  public static final int LANGUAGE_CODE_FIELD_NUMBER = 2;
  private volatile Object languageCode_;
  /**
   * <pre>
   * *Required* Language of the request in
   * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
   * "en-US". If you have selected a language for this `device_id` using the
   * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
   * menu in your phone's Google Assistant app, that selection will override
   * this value.
   * </pre>
   *
   * <code>string language_code = 2;</code>
   */
  public String getLanguageCode() {
    Object ref = languageCode_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      languageCode_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * *Required* Language of the request in
   * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
   * "en-US". If you have selected a language for this `device_id` using the
   * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
   * menu in your phone's Google Assistant app, that selection will override
   * this value.
   * </pre>
   *
   * <code>string language_code = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLanguageCodeBytes() {
    Object ref = languageCode_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      languageCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEVICE_LOCATION_FIELD_NUMBER = 5;
  private DeviceLocation deviceLocation_;
  /**
   * <pre>
   * *Optional* Location of the device where the query originated.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
   */
  public boolean hasDeviceLocation() {
    return deviceLocation_ != null;
  }
  /**
   * <pre>
   * *Optional* Location of the device where the query originated.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
   */
  public DeviceLocation getDeviceLocation() {
    return deviceLocation_ == null ? DeviceLocation.getDefaultInstance() : deviceLocation_;
  }
  /**
   * <pre>
   * *Optional* Location of the device where the query originated.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
   */
  public DeviceLocationOrBuilder getDeviceLocationOrBuilder() {
    return getDeviceLocation();
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
    if (!conversationState_.isEmpty()) {
      output.writeBytes(1, conversationState_);
    }
    if (!getLanguageCodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, languageCode_);
    }
    if (deviceLocation_ != null) {
      output.writeMessage(5, getDeviceLocation());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!conversationState_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, conversationState_);
    }
    if (!getLanguageCodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, languageCode_);
    }
    if (deviceLocation_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getDeviceLocation());
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
    if (!(obj instanceof DialogStateIn)) {
      return super.equals(obj);
    }
    DialogStateIn other = (DialogStateIn) obj;

    boolean result = true;
    result = result && getConversationState()
        .equals(other.getConversationState());
    result = result && getLanguageCode()
        .equals(other.getLanguageCode());
    result = result && (hasDeviceLocation() == other.hasDeviceLocation());
    if (hasDeviceLocation()) {
      result = result && getDeviceLocation()
          .equals(other.getDeviceLocation());
    }
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
    hash = (37 * hash) + CONVERSATION_STATE_FIELD_NUMBER;
    hash = (53 * hash) + getConversationState().hashCode();
    hash = (37 * hash) + LANGUAGE_CODE_FIELD_NUMBER;
    hash = (53 * hash) + getLanguageCode().hashCode();
    if (hasDeviceLocation()) {
      hash = (37 * hash) + DEVICE_LOCATION_FIELD_NUMBER;
      hash = (53 * hash) + getDeviceLocation().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static DialogStateIn parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateIn parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateIn parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateIn parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateIn parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DialogStateIn parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DialogStateIn parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DialogStateIn parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static DialogStateIn parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static DialogStateIn parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static DialogStateIn parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DialogStateIn parseFrom(
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
  public static Builder newBuilder(DialogStateIn prototype) {
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
   * Provides information about the current dialog state.
   * </pre>
   *
   * Protobuf type {@code google.assistant.embedded.v1alpha2.DialogStateIn}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.assistant.embedded.v1alpha2.DialogStateIn)
      DialogStateInOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateIn_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateIn_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              DialogStateIn.class, Builder.class);
    }

    // Construct using com.google.assistant.embedded.v1alpha2.DialogStateIn.newBuilder()
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
      conversationState_ = com.google.protobuf.ByteString.EMPTY;

      languageCode_ = "";

      if (deviceLocationBuilder_ == null) {
        deviceLocation_ = null;
      } else {
        deviceLocation_ = null;
        deviceLocationBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_DialogStateIn_descriptor;
    }

    public DialogStateIn getDefaultInstanceForType() {
      return DialogStateIn.getDefaultInstance();
    }

    public DialogStateIn build() {
      DialogStateIn result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public DialogStateIn buildPartial() {
      DialogStateIn result = new DialogStateIn(this);
      result.conversationState_ = conversationState_;
      result.languageCode_ = languageCode_;
      if (deviceLocationBuilder_ == null) {
        result.deviceLocation_ = deviceLocation_;
      } else {
        result.deviceLocation_ = deviceLocationBuilder_.build();
      }
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
      if (other instanceof DialogStateIn) {
        return mergeFrom((DialogStateIn)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(DialogStateIn other) {
      if (other == DialogStateIn.getDefaultInstance()) return this;
      if (other.getConversationState() != com.google.protobuf.ByteString.EMPTY) {
        setConversationState(other.getConversationState());
      }
      if (!other.getLanguageCode().isEmpty()) {
        languageCode_ = other.languageCode_;
        onChanged();
      }
      if (other.hasDeviceLocation()) {
        mergeDeviceLocation(other.getDeviceLocation());
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
      DialogStateIn parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (DialogStateIn) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString conversationState_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * *Required* This field must always be set to the
     * [DialogStateOut.conversation_state][google.assistant.embedded.v1alpha2.DialogStateOut.conversation_state] value that was returned in the prior
     * `Assist` RPC. It should only be omitted (field not set) if there was no
     * prior `Assist` RPC because this is the first `Assist` RPC made by this
     * device after it was first setup and/or a factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 1;</code>
     */
    public com.google.protobuf.ByteString getConversationState() {
      return conversationState_;
    }
    /**
     * <pre>
     * *Required* This field must always be set to the
     * [DialogStateOut.conversation_state][google.assistant.embedded.v1alpha2.DialogStateOut.conversation_state] value that was returned in the prior
     * `Assist` RPC. It should only be omitted (field not set) if there was no
     * prior `Assist` RPC because this is the first `Assist` RPC made by this
     * device after it was first setup and/or a factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 1;</code>
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
     * *Required* This field must always be set to the
     * [DialogStateOut.conversation_state][google.assistant.embedded.v1alpha2.DialogStateOut.conversation_state] value that was returned in the prior
     * `Assist` RPC. It should only be omitted (field not set) if there was no
     * prior `Assist` RPC because this is the first `Assist` RPC made by this
     * device after it was first setup and/or a factory-default reset.
     * </pre>
     *
     * <code>bytes conversation_state = 1;</code>
     */
    public Builder clearConversationState() {
      
      conversationState_ = getDefaultInstance().getConversationState();
      onChanged();
      return this;
    }

    private Object languageCode_ = "";
    /**
     * <pre>
     * *Required* Language of the request in
     * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
     * "en-US". If you have selected a language for this `device_id` using the
     * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
     * menu in your phone's Google Assistant app, that selection will override
     * this value.
     * </pre>
     *
     * <code>string language_code = 2;</code>
     */
    public String getLanguageCode() {
      Object ref = languageCode_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        languageCode_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * *Required* Language of the request in
     * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
     * "en-US". If you have selected a language for this `device_id` using the
     * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
     * menu in your phone's Google Assistant app, that selection will override
     * this value.
     * </pre>
     *
     * <code>string language_code = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLanguageCodeBytes() {
      Object ref = languageCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        languageCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * *Required* Language of the request in
     * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
     * "en-US". If you have selected a language for this `device_id` using the
     * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
     * menu in your phone's Google Assistant app, that selection will override
     * this value.
     * </pre>
     *
     * <code>string language_code = 2;</code>
     */
    public Builder setLanguageCode(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      languageCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Required* Language of the request in
     * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
     * "en-US". If you have selected a language for this `device_id` using the
     * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
     * menu in your phone's Google Assistant app, that selection will override
     * this value.
     * </pre>
     *
     * <code>string language_code = 2;</code>
     */
    public Builder clearLanguageCode() {
      
      languageCode_ = getDefaultInstance().getLanguageCode();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * *Required* Language of the request in
     * [IETF BCP 47 syntax](https://tools.ietf.org/html/bcp47). For example:
     * "en-US". If you have selected a language for this `device_id` using the
     * [Settings](https://developers.google.com/assistant/sdk/guides/assistant-settings)
     * menu in your phone's Google Assistant app, that selection will override
     * this value.
     * </pre>
     *
     * <code>string language_code = 2;</code>
     */
    public Builder setLanguageCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      languageCode_ = value;
      onChanged();
      return this;
    }

    private DeviceLocation deviceLocation_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        DeviceLocation, DeviceLocation.Builder, DeviceLocationOrBuilder> deviceLocationBuilder_;
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public boolean hasDeviceLocation() {
      return deviceLocationBuilder_ != null || deviceLocation_ != null;
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public DeviceLocation getDeviceLocation() {
      if (deviceLocationBuilder_ == null) {
        return deviceLocation_ == null ? DeviceLocation.getDefaultInstance() : deviceLocation_;
      } else {
        return deviceLocationBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public Builder setDeviceLocation(DeviceLocation value) {
      if (deviceLocationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        deviceLocation_ = value;
        onChanged();
      } else {
        deviceLocationBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public Builder setDeviceLocation(
        DeviceLocation.Builder builderForValue) {
      if (deviceLocationBuilder_ == null) {
        deviceLocation_ = builderForValue.build();
        onChanged();
      } else {
        deviceLocationBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public Builder mergeDeviceLocation(DeviceLocation value) {
      if (deviceLocationBuilder_ == null) {
        if (deviceLocation_ != null) {
          deviceLocation_ =
            DeviceLocation.newBuilder(deviceLocation_).mergeFrom(value).buildPartial();
        } else {
          deviceLocation_ = value;
        }
        onChanged();
      } else {
        deviceLocationBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public Builder clearDeviceLocation() {
      if (deviceLocationBuilder_ == null) {
        deviceLocation_ = null;
        onChanged();
      } else {
        deviceLocation_ = null;
        deviceLocationBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public DeviceLocation.Builder getDeviceLocationBuilder() {
      
      onChanged();
      return getDeviceLocationFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    public DeviceLocationOrBuilder getDeviceLocationOrBuilder() {
      if (deviceLocationBuilder_ != null) {
        return deviceLocationBuilder_.getMessageOrBuilder();
      } else {
        return deviceLocation_ == null ?
            DeviceLocation.getDefaultInstance() : deviceLocation_;
      }
    }
    /**
     * <pre>
     * *Optional* Location of the device where the query originated.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.DeviceLocation device_location = 5;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        DeviceLocation, DeviceLocation.Builder, DeviceLocationOrBuilder>
        getDeviceLocationFieldBuilder() {
      if (deviceLocationBuilder_ == null) {
        deviceLocationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            DeviceLocation, DeviceLocation.Builder, DeviceLocationOrBuilder>(
                getDeviceLocation(),
                getParentForChildren(),
                isClean());
        deviceLocation_ = null;
      }
      return deviceLocationBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.assistant.embedded.v1alpha2.DialogStateIn)
  }

  // @@protoc_insertion_point(class_scope:google.assistant.embedded.v1alpha2.DialogStateIn)
  private static final DialogStateIn DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new DialogStateIn();
  }

  public static DialogStateIn getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DialogStateIn>
      PARSER = new com.google.protobuf.AbstractParser<DialogStateIn>() {
    public DialogStateIn parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DialogStateIn(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DialogStateIn> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<DialogStateIn> getParserForType() {
    return PARSER;
  }

  public DialogStateIn getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

