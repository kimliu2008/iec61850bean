/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class ConfirmedRequestPDU implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private Unsigned32 invokeID = null;
  private ConfirmedServiceRequest service = null;

  public ConfirmedRequestPDU() {}

  public ConfirmedRequestPDU(byte[] code) {
    this.code = code;
  }

  public Unsigned32 getInvokeID() {
    return invokeID;
  }

  public void setInvokeID(Unsigned32 invokeID) {
    this.invokeID = invokeID;
  }

  public ConfirmedServiceRequest getService() {
    return service;
  }

  public void setService(ConfirmedServiceRequest service) {
    this.service = service;
  }

  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      for (int i = code.length - 1; i >= 0; i--) {
        reverseOS.write(code[i]);
      }
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    codeLength += service.encode(reverseOS);

    codeLength += invokeID.encode(reverseOS, true);

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int codeLength = 0;
    int subCodeLength = 0;
    BerTag berTag = new BerTag();

    if (withTag) {
      codeLength += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    codeLength += length.decode(is);

    int totalLength = length.val;
    codeLength += totalLength;

    subCodeLength += berTag.decode(is);
    if (berTag.equals(Unsigned32.tag)) {
      invokeID = new Unsigned32();
      subCodeLength += invokeID.decode(is, false);
      subCodeLength += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match the mandatory sequence element tag.");
    }

    service = new ConfirmedServiceRequest();
    subCodeLength += service.decode(is, berTag);
    if (subCodeLength == totalLength) {
      return codeLength;
    }
    throw new IOException(
        "Unexpected end of sequence, length tag: "
            + totalLength
            + ", actual sequence length: "
            + subCodeLength);
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{");
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (invokeID != null) {
      sb.append("invokeID: ").append(invokeID);
    } else {
      sb.append("invokeID: <empty-required-field>");
    }

    sb.append(",\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (service != null) {
      sb.append("service: ");
      service.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("service: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }
}