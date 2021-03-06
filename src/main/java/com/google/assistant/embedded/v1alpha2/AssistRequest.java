// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

/**
 * <pre>
 * The top-level message sent by the client. Clients must send at least two, and
 * typically numerous `AssistRequest` messages. The first message must
 * contain a `config` message and must not contain `audio_in` data. All
 * subsequent messages must contain `audio_in` data and must not contain a
 * `config` message.
 * </pre>
 *
 * Protobuf type {@code google.assistant.embedded.v1alpha2.AssistRequest}
 */
public  final class AssistRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.assistant.embedded.v1alpha2.AssistRequest)
    AssistRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AssistRequest.newBuilder() to construct.
  private AssistRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AssistRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AssistRequest(
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
            AssistConfig.Builder subBuilder = null;
            if (typeCase_ == 1) {
              subBuilder = ((AssistConfig) type_).toBuilder();
            }
            type_ =
                input.readMessage(AssistConfig.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((AssistConfig) type_);
              type_ = subBuilder.buildPartial();
            }
            typeCase_ = 1;
            break;
          }
          case 18: {
            typeCase_ = 2;
            type_ = input.readBytes();
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
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_AssistRequest_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_AssistRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            AssistRequest.class, Builder.class);
  }

  private int typeCase_ = 0;
  private Object type_;
  public enum TypeCase
      implements com.google.protobuf.Internal.EnumLite {
    CONFIG(1),
    AUDIO_IN(2),
    TYPE_NOT_SET(0);
    private final int value;
    private TypeCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static TypeCase valueOf(int value) {
      return forNumber(value);
    }

    public static TypeCase forNumber(int value) {
      switch (value) {
        case 1: return CONFIG;
        case 2: return AUDIO_IN;
        case 0: return TYPE_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  }

  public TypeCase
  getTypeCase() {
    return TypeCase.forNumber(
        typeCase_);
  }

  public static final int CONFIG_FIELD_NUMBER = 1;
  /**
   * <pre>
   * The `config` message provides information to the recognizer that
   * specifies how to process the request.
   * The first `AssistRequest` message must contain a `config` message.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
   */
  public boolean hasConfig() {
    return typeCase_ == 1;
  }
  /**
   * <pre>
   * The `config` message provides information to the recognizer that
   * specifies how to process the request.
   * The first `AssistRequest` message must contain a `config` message.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
   */
  public AssistConfig getConfig() {
    if (typeCase_ == 1) {
       return (AssistConfig) type_;
    }
    return AssistConfig.getDefaultInstance();
  }
  /**
   * <pre>
   * The `config` message provides information to the recognizer that
   * specifies how to process the request.
   * The first `AssistRequest` message must contain a `config` message.
   * </pre>
   *
   * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
   */
  public AssistConfigOrBuilder getConfigOrBuilder() {
    if (typeCase_ == 1) {
       return (AssistConfig) type_;
    }
    return AssistConfig.getDefaultInstance();
  }

  public static final int AUDIO_IN_FIELD_NUMBER = 2;
  /**
   * <pre>
   * The audio data to be recognized. Sequential chunks of audio data are sent
   * in sequential `AssistRequest` messages. The first `AssistRequest`
   * message must not contain `audio_in` data and all subsequent
   * `AssistRequest` messages must contain `audio_in` data. The audio bytes
   * must be encoded as specified in `AudioInConfig`.
   * Audio must be sent at approximately real-time (16000 samples per second).
   * An error will be returned if audio is sent significantly faster or
   * slower.
   * </pre>
   *
   * <code>bytes audio_in = 2;</code>
   */
  public com.google.protobuf.ByteString getAudioIn() {
    if (typeCase_ == 2) {
      return (com.google.protobuf.ByteString) type_;
    }
    return com.google.protobuf.ByteString.EMPTY;
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
    if (typeCase_ == 1) {
      output.writeMessage(1, (AssistConfig) type_);
    }
    if (typeCase_ == 2) {
      output.writeBytes(
          2, (com.google.protobuf.ByteString) type_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (typeCase_ == 1) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, (AssistConfig) type_);
    }
    if (typeCase_ == 2) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(
            2, (com.google.protobuf.ByteString) type_);
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
    if (!(obj instanceof AssistRequest)) {
      return super.equals(obj);
    }
    AssistRequest other = (AssistRequest) obj;

    boolean result = true;
    result = result && getTypeCase().equals(
        other.getTypeCase());
    if (!result) return false;
    switch (typeCase_) {
      case 1:
        result = result && getConfig()
            .equals(other.getConfig());
        break;
      case 2:
        result = result && getAudioIn()
            .equals(other.getAudioIn());
        break;
      case 0:
      default:
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
    switch (typeCase_) {
      case 1:
        hash = (37 * hash) + CONFIG_FIELD_NUMBER;
        hash = (53 * hash) + getConfig().hashCode();
        break;
      case 2:
        hash = (37 * hash) + AUDIO_IN_FIELD_NUMBER;
        hash = (53 * hash) + getAudioIn().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static AssistRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AssistRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AssistRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AssistRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AssistRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AssistRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AssistRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static AssistRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static AssistRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static AssistRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static AssistRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static AssistRequest parseFrom(
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
  public static Builder newBuilder(AssistRequest prototype) {
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
   * The top-level message sent by the client. Clients must send at least two, and
   * typically numerous `AssistRequest` messages. The first message must
   * contain a `config` message and must not contain `audio_in` data. All
   * subsequent messages must contain `audio_in` data and must not contain a
   * `config` message.
   * </pre>
   *
   * Protobuf type {@code google.assistant.embedded.v1alpha2.AssistRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.assistant.embedded.v1alpha2.AssistRequest)
      AssistRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_AssistRequest_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_AssistRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              AssistRequest.class, Builder.class);
    }

    // Construct using com.google.assistant.embedded.v1alpha2.AssistRequest.newBuilder()
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
      typeCase_ = 0;
      type_ = null;
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AssistantProto.internal_static_google_assistant_embedded_v1alpha2_AssistRequest_descriptor;
    }

    public AssistRequest getDefaultInstanceForType() {
      return AssistRequest.getDefaultInstance();
    }

    public AssistRequest build() {
      AssistRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public AssistRequest buildPartial() {
      AssistRequest result = new AssistRequest(this);
      if (typeCase_ == 1) {
        if (configBuilder_ == null) {
          result.type_ = type_;
        } else {
          result.type_ = configBuilder_.build();
        }
      }
      if (typeCase_ == 2) {
        result.type_ = type_;
      }
      result.typeCase_ = typeCase_;
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
      if (other instanceof AssistRequest) {
        return mergeFrom((AssistRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(AssistRequest other) {
      if (other == AssistRequest.getDefaultInstance()) return this;
      switch (other.getTypeCase()) {
        case CONFIG: {
          mergeConfig(other.getConfig());
          break;
        }
        case AUDIO_IN: {
          setAudioIn(other.getAudioIn());
          break;
        }
        case TYPE_NOT_SET: {
          break;
        }
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
      AssistRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (AssistRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int typeCase_ = 0;
    private Object type_;
    public TypeCase
        getTypeCase() {
      return TypeCase.forNumber(
          typeCase_);
    }

    public Builder clearType() {
      typeCase_ = 0;
      type_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        AssistConfig, AssistConfig.Builder, AssistConfigOrBuilder> configBuilder_;
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public boolean hasConfig() {
      return typeCase_ == 1;
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public AssistConfig getConfig() {
      if (configBuilder_ == null) {
        if (typeCase_ == 1) {
          return (AssistConfig) type_;
        }
        return AssistConfig.getDefaultInstance();
      } else {
        if (typeCase_ == 1) {
          return configBuilder_.getMessage();
        }
        return AssistConfig.getDefaultInstance();
      }
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public Builder setConfig(AssistConfig value) {
      if (configBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        type_ = value;
        onChanged();
      } else {
        configBuilder_.setMessage(value);
      }
      typeCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public Builder setConfig(
        AssistConfig.Builder builderForValue) {
      if (configBuilder_ == null) {
        type_ = builderForValue.build();
        onChanged();
      } else {
        configBuilder_.setMessage(builderForValue.build());
      }
      typeCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public Builder mergeConfig(AssistConfig value) {
      if (configBuilder_ == null) {
        if (typeCase_ == 1 &&
            type_ != AssistConfig.getDefaultInstance()) {
          type_ = AssistConfig.newBuilder((AssistConfig) type_)
              .mergeFrom(value).buildPartial();
        } else {
          type_ = value;
        }
        onChanged();
      } else {
        if (typeCase_ == 1) {
          configBuilder_.mergeFrom(value);
        }
        configBuilder_.setMessage(value);
      }
      typeCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public Builder clearConfig() {
      if (configBuilder_ == null) {
        if (typeCase_ == 1) {
          typeCase_ = 0;
          type_ = null;
          onChanged();
        }
      } else {
        if (typeCase_ == 1) {
          typeCase_ = 0;
          type_ = null;
        }
        configBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public AssistConfig.Builder getConfigBuilder() {
      return getConfigFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    public AssistConfigOrBuilder getConfigOrBuilder() {
      if ((typeCase_ == 1) && (configBuilder_ != null)) {
        return configBuilder_.getMessageOrBuilder();
      } else {
        if (typeCase_ == 1) {
          return (AssistConfig) type_;
        }
        return AssistConfig.getDefaultInstance();
      }
    }
    /**
     * <pre>
     * The `config` message provides information to the recognizer that
     * specifies how to process the request.
     * The first `AssistRequest` message must contain a `config` message.
     * </pre>
     *
     * <code>.google.assistant.embedded.v1alpha2.AssistConfig config = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        AssistConfig, AssistConfig.Builder, AssistConfigOrBuilder>
        getConfigFieldBuilder() {
      if (configBuilder_ == null) {
        if (!(typeCase_ == 1)) {
          type_ = AssistConfig.getDefaultInstance();
        }
        configBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            AssistConfig, AssistConfig.Builder, AssistConfigOrBuilder>(
                (AssistConfig) type_,
                getParentForChildren(),
                isClean());
        type_ = null;
      }
      typeCase_ = 1;
      onChanged();
      return configBuilder_;
    }

    /**
     * <pre>
     * The audio data to be recognized. Sequential chunks of audio data are sent
     * in sequential `AssistRequest` messages. The first `AssistRequest`
     * message must not contain `audio_in` data and all subsequent
     * `AssistRequest` messages must contain `audio_in` data. The audio bytes
     * must be encoded as specified in `AudioInConfig`.
     * Audio must be sent at approximately real-time (16000 samples per second).
     * An error will be returned if audio is sent significantly faster or
     * slower.
     * </pre>
     *
     * <code>bytes audio_in = 2;</code>
     */
    public com.google.protobuf.ByteString getAudioIn() {
      if (typeCase_ == 2) {
        return (com.google.protobuf.ByteString) type_;
      }
      return com.google.protobuf.ByteString.EMPTY;
    }
    /**
     * <pre>
     * The audio data to be recognized. Sequential chunks of audio data are sent
     * in sequential `AssistRequest` messages. The first `AssistRequest`
     * message must not contain `audio_in` data and all subsequent
     * `AssistRequest` messages must contain `audio_in` data. The audio bytes
     * must be encoded as specified in `AudioInConfig`.
     * Audio must be sent at approximately real-time (16000 samples per second).
     * An error will be returned if audio is sent significantly faster or
     * slower.
     * </pre>
     *
     * <code>bytes audio_in = 2;</code>
     */
    public Builder setAudioIn(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  typeCase_ = 2;
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The audio data to be recognized. Sequential chunks of audio data are sent
     * in sequential `AssistRequest` messages. The first `AssistRequest`
     * message must not contain `audio_in` data and all subsequent
     * `AssistRequest` messages must contain `audio_in` data. The audio bytes
     * must be encoded as specified in `AudioInConfig`.
     * Audio must be sent at approximately real-time (16000 samples per second).
     * An error will be returned if audio is sent significantly faster or
     * slower.
     * </pre>
     *
     * <code>bytes audio_in = 2;</code>
     */
    public Builder clearAudioIn() {
      if (typeCase_ == 2) {
        typeCase_ = 0;
        type_ = null;
        onChanged();
      }
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


    // @@protoc_insertion_point(builder_scope:google.assistant.embedded.v1alpha2.AssistRequest)
  }

  // @@protoc_insertion_point(class_scope:google.assistant.embedded.v1alpha2.AssistRequest)
  private static final AssistRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new AssistRequest();
  }

  public static AssistRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AssistRequest>
      PARSER = new com.google.protobuf.AbstractParser<AssistRequest>() {
    public AssistRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AssistRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AssistRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<AssistRequest> getParserForType() {
    return PARSER;
  }

  public AssistRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

