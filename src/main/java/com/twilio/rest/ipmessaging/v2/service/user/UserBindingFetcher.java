/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service.user;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class UserBindingFetcher extends Fetcher<UserBinding> {
    private final String pathServiceSid;
    private final String pathUserSid;
    private final String pathSid;

    /**
     * Construct a new UserBindingFetcher.
     *
     * @param pathServiceSid The SID of the Service to fetch the resource from
     * @param pathUserSid The SID of the User with the binding
     * @param pathSid The SID of the User Binding resource to fetch
     */
    public UserBindingFetcher(final String pathServiceSid,
                              final String pathUserSid,
                              final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathUserSid = pathUserSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched UserBinding
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public UserBinding fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Users/" + this.pathUserSid + "/Bindings/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UserBinding fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return UserBinding.fromJson(response.getStream(), client.getObjectMapper());
    }
}