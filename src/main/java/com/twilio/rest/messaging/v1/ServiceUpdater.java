/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.messaging.v1;

import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class ServiceUpdater extends Updater<Service> {
    private final String pathSid;
    private String friendlyName;
    private URI inboundRequestUrl;
    private HttpMethod inboundMethod;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private Boolean stickySender;
    private Boolean mmsConverter;

    /**
     * Construct a new ServiceUpdater.
     * 
     * @param pathSid The sid
     */
    public ServiceUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public ServiceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The inbound_request_url.
     * 
     * @param inboundRequestUrl The inbound_request_url
     * @return this
     */
    public ServiceUpdater setInboundRequestUrl(final URI inboundRequestUrl) {
        this.inboundRequestUrl = inboundRequestUrl;
        return this;
    }

    /**
     * The inbound_request_url.
     * 
     * @param inboundRequestUrl The inbound_request_url
     * @return this
     */
    public ServiceUpdater setInboundRequestUrl(final String inboundRequestUrl) {
        return setInboundRequestUrl(Promoter.uriFromString(inboundRequestUrl));
    }

    /**
     * The inbound_method.
     * 
     * @param inboundMethod The inbound_method
     * @return this
     */
    public ServiceUpdater setInboundMethod(final HttpMethod inboundMethod) {
        this.inboundMethod = inboundMethod;
        return this;
    }

    /**
     * The fallback_url.
     * 
     * @param fallbackUrl The fallback_url
     * @return this
     */
    public ServiceUpdater setFallbackUrl(final URI fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    /**
     * The fallback_url.
     * 
     * @param fallbackUrl The fallback_url
     * @return this
     */
    public ServiceUpdater setFallbackUrl(final String fallbackUrl) {
        return setFallbackUrl(Promoter.uriFromString(fallbackUrl));
    }

    /**
     * The fallback_method.
     * 
     * @param fallbackMethod The fallback_method
     * @return this
     */
    public ServiceUpdater setFallbackMethod(final HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    /**
     * The status_callback.
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public ServiceUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The status_callback.
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public ServiceUpdater setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The sticky_sender.
     * 
     * @param stickySender The sticky_sender
     * @return this
     */
    public ServiceUpdater setStickySender(final Boolean stickySender) {
        this.stickySender = stickySender;
        return this;
    }

    /**
     * The mms_converter.
     * 
     * @param mmsConverter The mms_converter
     * @return this
     */
    public ServiceUpdater setMmsConverter(final Boolean mmsConverter) {
        this.mmsConverter = mmsConverter;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.MESSAGING.toString(),
            "/v1/Services/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service update failed: Unable to connect to server");
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

        return Service.fromJson(response.getStream(), client.getObjectMapper());
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

        if (inboundRequestUrl != null) {
            request.addPostParam("InboundRequestUrl", inboundRequestUrl.toString());
        }

        if (inboundMethod != null) {
            request.addPostParam("InboundMethod", inboundMethod.toString());
        }

        if (fallbackUrl != null) {
            request.addPostParam("FallbackUrl", fallbackUrl.toString());
        }

        if (fallbackMethod != null) {
            request.addPostParam("FallbackMethod", fallbackMethod.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (stickySender != null) {
            request.addPostParam("StickySender", stickySender.toString());
        }

        if (mmsConverter != null) {
            request.addPostParam("MmsConverter", mmsConverter.toString());
        }
    }
}