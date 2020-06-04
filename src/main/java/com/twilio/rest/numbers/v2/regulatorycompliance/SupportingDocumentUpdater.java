/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance;

import com.twilio.base.Updater;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.Map;

public class SupportingDocumentUpdater extends Updater<SupportingDocument> {
    private final String pathSid;
    private String friendlyName;
    private Map<String, Object> attributes;

    /**
     * Construct a new SupportingDocumentUpdater.
     *
     * @param pathSid The unique string that identifies the resource
     */
    public SupportingDocumentUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The string that you assigned to describe the resource..
     *
     * @param friendlyName The string that you assigned to describe the resource
     * @return this
     */
    public SupportingDocumentUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The set of parameters that are the attributes of the Supporting Document
     * resource which are derived Supporting Document Types..
     *
     * @param attributes The set of parameters that compose the Supporting Document
     *                   resource
     * @return this
     */
    public SupportingDocumentUpdater setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated SupportingDocument
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SupportingDocument update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.NUMBERS.toString(),
            "/v2/RegulatoryCompliance/SupportingDocuments/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SupportingDocument update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SupportingDocument.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", Converter.mapToJson(attributes));
        }
    }
}