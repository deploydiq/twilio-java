/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class BrandedCallCreator extends Creator<BrandedCall> {
    private final String from;
    private final String to;
    private final String reason;

    /**
     * Construct a new BrandedCallCreator.
     *
     * @param from Twilio number from which to brand the call
     * @param to The terminating Phone Number
     * @param reason The business reason for this phone call
     */
    public BrandedCallCreator(final String from,
                              final String to,
                              final String reason) {
        this.from = from;
        this.to = to;
        this.reason = reason;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created BrandedCall
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public BrandedCall create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/TrustedComms/Business/BrandedCalls",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("BrandedCall creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return BrandedCall.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (from != null) {
            request.addPostParam("From", from);
        }

        if (to != null) {
            request.addPostParam("To", to);
        }

        if (reason != null) {
            request.addPostParam("Reason", reason);
        }
    }
}