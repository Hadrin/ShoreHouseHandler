/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/googleapis/google-api-java-client-services/
 * Modify at your own risk.
 */

package com.google.api.services.calendar.model;

/**
 * Model definition for ConferenceData.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Calendar API. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ConferenceData extends com.google.api.client.json.GenericJson {

  /**
   * The ID of the conference. Can be used by developers to keep track of conferences, should not be
   * displayed to users. Values for solution types:   - "eventHangout": unset. -
   * "eventNamedHangout": the name of the Hangout. - "hangoutsMeet": the 10-letter meeting code, for
   * example "aaa-bbbb-ccc". - "addOn": defined by 3P conference provider.  Optional.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String conferenceId;

  /**
   * The conference solution, such as Hangouts or Google Meet. Unset for a conference with a failed
   * create request. Either conferenceSolution and at least one entryPoint, or createRequest is
   * required.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ConferenceSolution conferenceSolution;

  /**
   * A request to generate a new conference and attach it to the event. The data is generated
   * asynchronously. To see whether the data is present check the status field. Either
   * conferenceSolution and at least one entryPoint, or createRequest is required.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private CreateConferenceRequest createRequest;

  /**
   * Information about individual conference entry points, such as URLs or phone numbers. All of
   * them must belong to the same conference. Either conferenceSolution and at least one entryPoint,
   * or createRequest is required.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<EntryPoint> entryPoints;

  /**
   * Additional notes (such as instructions from the domain administrator, legal notices) to display
   * to the user. Can contain HTML. The maximum length is 2048 characters. Optional.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String notes;

  /**
   * Additional properties related to a conference. An example would be a solution-specific setting
   * for enabling video streaming.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ConferenceParameters parameters;

  /**
   * The signature of the conference data. Generated on server side. Must be preserved while copying
   * the conference data between events, otherwise the conference data will not be copied. Unset for
   * a conference with a failed create request. Optional for a conference with a pending create
   * request.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String signature;

  /**
   * The ID of the conference. Can be used by developers to keep track of conferences, should not be
   * displayed to users. Values for solution types:   - "eventHangout": unset. -
   * "eventNamedHangout": the name of the Hangout. - "hangoutsMeet": the 10-letter meeting code, for
   * example "aaa-bbbb-ccc". - "addOn": defined by 3P conference provider.  Optional.
   * @return value or {@code null} for none
   */
  public java.lang.String getConferenceId() {
    return conferenceId;
  }

  /**
   * The ID of the conference. Can be used by developers to keep track of conferences, should not be
   * displayed to users. Values for solution types:   - "eventHangout": unset. -
   * "eventNamedHangout": the name of the Hangout. - "hangoutsMeet": the 10-letter meeting code, for
   * example "aaa-bbbb-ccc". - "addOn": defined by 3P conference provider.  Optional.
   * @param conferenceId conferenceId or {@code null} for none
   */
  public ConferenceData setConferenceId(java.lang.String conferenceId) {
    this.conferenceId = conferenceId;
    return this;
  }

  /**
   * The conference solution, such as Hangouts or Google Meet. Unset for a conference with a failed
   * create request. Either conferenceSolution and at least one entryPoint, or createRequest is
   * required.
   * @return value or {@code null} for none
   */
  public ConferenceSolution getConferenceSolution() {
    return conferenceSolution;
  }

  /**
   * The conference solution, such as Hangouts or Google Meet. Unset for a conference with a failed
   * create request. Either conferenceSolution and at least one entryPoint, or createRequest is
   * required.
   * @param conferenceSolution conferenceSolution or {@code null} for none
   */
  public ConferenceData setConferenceSolution(ConferenceSolution conferenceSolution) {
    this.conferenceSolution = conferenceSolution;
    return this;
  }

  /**
   * A request to generate a new conference and attach it to the event. The data is generated
   * asynchronously. To see whether the data is present check the status field. Either
   * conferenceSolution and at least one entryPoint, or createRequest is required.
   * @return value or {@code null} for none
   */
  public CreateConferenceRequest getCreateRequest() {
    return createRequest;
  }

  /**
   * A request to generate a new conference and attach it to the event. The data is generated
   * asynchronously. To see whether the data is present check the status field. Either
   * conferenceSolution and at least one entryPoint, or createRequest is required.
   * @param createRequest createRequest or {@code null} for none
   */
  public ConferenceData setCreateRequest(CreateConferenceRequest createRequest) {
    this.createRequest = createRequest;
    return this;
  }

  /**
   * Information about individual conference entry points, such as URLs or phone numbers. All of
   * them must belong to the same conference. Either conferenceSolution and at least one entryPoint,
   * or createRequest is required.
   * @return value or {@code null} for none
   */
  public java.util.List<EntryPoint> getEntryPoints() {
    return entryPoints;
  }

  /**
   * Information about individual conference entry points, such as URLs or phone numbers. All of
   * them must belong to the same conference. Either conferenceSolution and at least one entryPoint,
   * or createRequest is required.
   * @param entryPoints entryPoints or {@code null} for none
   */
  public ConferenceData setEntryPoints(java.util.List<EntryPoint> entryPoints) {
    this.entryPoints = entryPoints;
    return this;
  }

  /**
   * Additional notes (such as instructions from the domain administrator, legal notices) to display
   * to the user. Can contain HTML. The maximum length is 2048 characters. Optional.
   * @return value or {@code null} for none
   */
  public java.lang.String getNotes() {
    return notes;
  }

  /**
   * Additional notes (such as instructions from the domain administrator, legal notices) to display
   * to the user. Can contain HTML. The maximum length is 2048 characters. Optional.
   * @param notes notes or {@code null} for none
   */
  public ConferenceData setNotes(java.lang.String notes) {
    this.notes = notes;
    return this;
  }

  /**
   * Additional properties related to a conference. An example would be a solution-specific setting
   * for enabling video streaming.
   * @return value or {@code null} for none
   */
  public ConferenceParameters getParameters() {
    return parameters;
  }

  /**
   * Additional properties related to a conference. An example would be a solution-specific setting
   * for enabling video streaming.
   * @param parameters parameters or {@code null} for none
   */
  public ConferenceData setParameters(ConferenceParameters parameters) {
    this.parameters = parameters;
    return this;
  }

  /**
   * The signature of the conference data. Generated on server side. Must be preserved while copying
   * the conference data between events, otherwise the conference data will not be copied. Unset for
   * a conference with a failed create request. Optional for a conference with a pending create
   * request.
   * @return value or {@code null} for none
   */
  public java.lang.String getSignature() {
    return signature;
  }

  /**
   * The signature of the conference data. Generated on server side. Must be preserved while copying
   * the conference data between events, otherwise the conference data will not be copied. Unset for
   * a conference with a failed create request. Optional for a conference with a pending create
   * request.
   * @param signature signature or {@code null} for none
   */
  public ConferenceData setSignature(java.lang.String signature) {
    this.signature = signature;
    return this;
  }

  @Override
  public ConferenceData set(String fieldName, Object value) {
    return (ConferenceData) super.set(fieldName, value);
  }

  @Override
  public ConferenceData clone() {
    return (ConferenceData) super.clone();
  }

}