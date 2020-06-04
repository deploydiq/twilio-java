/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

import java.util.List;

public class RecordingReader extends Reader<Recording> {
    private Recording.Status status;
    private String sourceSid;
    private List<String> groupingSid;
    private DateTime dateCreatedAfter;
    private DateTime dateCreatedBefore;
    private Recording.Type mediaType;

    /**
     * Read only the recordings that have this status. Can be: `processing`,
     * `completed`, or `deleted`..
     *
     * @param status Read only the recordings that have this status
     * @return this
     */
    public RecordingReader setStatus(final Recording.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Read only the recordings that have this `source_sid`..
     *
     * @param sourceSid Read only the recordings that have this source_sid
     * @return this
     */
    public RecordingReader setSourceSid(final String sourceSid) {
        this.sourceSid = sourceSid;
        return this;
    }

    /**
     * Read only recordings with this `grouping_sid`, which may include a
     * `participant_sid` and/or a `room_sid`..
     *
     * @param groupingSid Read only recordings that have this grouping_sid
     * @return this
     */
    public RecordingReader setGroupingSid(final List<String> groupingSid) {
        this.groupingSid = groupingSid;
        return this;
    }

    /**
     * Read only recordings with this `grouping_sid`, which may include a
     * `participant_sid` and/or a `room_sid`..
     *
     * @param groupingSid Read only recordings that have this grouping_sid
     * @return this
     */
    public RecordingReader setGroupingSid(final String groupingSid) {
        return setGroupingSid(Promoter.listOfOne(groupingSid));
    }

    /**
     * Read only recordings that started on or after this [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601) date-time with time zone..
     *
     * @param dateCreatedAfter Read only recordings that started on or after this
     *                         [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     *                         date-time with time zone
     * @return this
     */
    public RecordingReader setDateCreatedAfter(final DateTime dateCreatedAfter) {
        this.dateCreatedAfter = dateCreatedAfter;
        return this;
    }

    /**
     * Read only recordings that started before this [ISO
     * 8601](https://en.wikipedia.org/wiki/ISO_8601) date-time with time zone, given
     * as `YYYY-MM-DDThh:mm:ss+|-hh:mm` or `YYYY-MM-DDThh:mm:ssZ`..
     *
     * @param dateCreatedBefore Read only recordings that started before this [ISO
     *                          8601](https://en.wikipedia.org/wiki/ISO_8601)
     *                          date-time with time zone
     * @return this
     */
    public RecordingReader setDateCreatedBefore(final DateTime dateCreatedBefore) {
        this.dateCreatedBefore = dateCreatedBefore;
        return this;
    }

    /**
     * Read only recordings that have this media type. Can be either `audio` or
     * `video`..
     *
     * @param mediaType Read only recordings that have this media type
     * @return this
     */
    public RecordingReader setMediaType(final Recording.Type mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    public ResourceSet<Recording> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Recording> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            "/v1/Recordings"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Recording> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Recording> nextPage(final Page<Recording> page,
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.VIDEO.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Recording> previousPage(final Page<Recording> page,
                                        final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.VIDEO.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Recording Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Recording> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Recording read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "recordings",
            response.getContent(),
            Recording.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (sourceSid != null) {
            request.addQueryParam("SourceSid", sourceSid);
        }

        if (groupingSid != null) {
            for (String prop : groupingSid) {
                request.addQueryParam("GroupingSid", prop);
            }
        }

        if (dateCreatedAfter != null) {
            request.addQueryParam("DateCreatedAfter", dateCreatedAfter.toString());
        }

        if (dateCreatedBefore != null) {
            request.addQueryParam("DateCreatedBefore", dateCreatedBefore.toString());
        }

        if (mediaType != null) {
            request.addQueryParam("MediaType", mediaType.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}