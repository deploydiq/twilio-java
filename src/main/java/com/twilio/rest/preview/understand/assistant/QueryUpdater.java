/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.understand.assistant;

import com.twilio.base.Updater;
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
public class QueryUpdater extends Updater<Query> {
    private final String pathAssistantSid;
    private final String pathSid;
    private String sampleSid;
    private String status;

    /**
     * Construct a new QueryUpdater.
     *
     * @param pathAssistantSid The unique ID of the parent Assistant.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     */
    public QueryUpdater(final String pathAssistantSid,
                        final String pathSid) {
        this.pathAssistantSid = pathAssistantSid;
        this.pathSid = pathSid;
    }

    /**
     * An optional reference to the Sample created from this query..
     *
     * @param sampleSid An optional reference to the Sample created from this query.
     * @return this
     */
    public QueryUpdater setSampleSid(final String sampleSid) {
        this.sampleSid = sampleSid;
        return this;
    }

    /**
     * A string that described the query status. The values can be: pending_review,
     * reviewed, discarded.
     *
     * @param status A string that described the query status. The values can be:
     *               pending_review, reviewed, discarded
     * @return this
     */
    public QueryUpdater setStatus(final String status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Query
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Query update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/understand/Assistants/" + this.pathAssistantSid + "/Queries/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Query update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Query.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (sampleSid != null) {
            request.addPostParam("SampleSid", sampleSid);
        }

        if (status != null) {
            request.addPostParam("Status", status);
        }
    }
}