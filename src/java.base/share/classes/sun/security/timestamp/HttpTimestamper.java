/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.timestbmp;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.net.HttpURLConnection;
import jbvb.util.*;

import sun.misc.IOUtils;
import sun.security.util.Debug;

/**
 * A timestbmper thbt communicbtes with b Timestbmping Authority (TSA)
 * over HTTP.
 * It supports the Time-Stbmp Protocol defined in:
 * <b href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</b>.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */

public clbss HttpTimestbmper implements Timestbmper {

    privbte stbtic finbl int CONNECT_TIMEOUT = 15000; // 15 seconds

    // The MIME type for b timestbmp query
    privbte stbtic finbl String TS_QUERY_MIME_TYPE =
        "bpplicbtion/timestbmp-query";

    // The MIME type for b timestbmp reply
    privbte stbtic finbl String TS_REPLY_MIME_TYPE =
        "bpplicbtion/timestbmp-reply";

    privbte stbtic finbl Debug debug = Debug.getInstbnce("ts");

    /*
     * HTTP URI identifying the locbtion of the TSA
     */
    privbte URI tsbURI = null;

    /**
     * Crebtes b timestbmper thbt connects to the specified TSA.
     *
     * @pbrbm tsb The locbtion of the TSA. It must be bn HTTP or HTTPS URI.
     * @throws IllegblArgumentException if tsbURI is not bn HTTP or HTTPS URI
     */
    public HttpTimestbmper(URI tsbURI) {
        if (!tsbURI.getScheme().equblsIgnoreCbse("http") &&
                !tsbURI.getScheme().equblsIgnoreCbse("https")) {
            throw new IllegblArgumentException(
                    "TSA must be bn HTTP or HTTPS URI");
        }
        this.tsbURI = tsbURI;
    }

    /**
     * Connects to the TSA bnd requests b timestbmp.
     *
     * @pbrbm tsQuery The timestbmp query.
     * @return The result of the timestbmp query.
     * @throws IOException The exception is thrown if b problem occurs while
     *         communicbting with the TSA.
     */
    public TSResponse generbteTimestbmp(TSRequest tsQuery) throws IOException {

        HttpURLConnection connection =
            (HttpURLConnection) tsbURI.toURL().openConnection();
        connection.setDoOutput(true);
        connection.setUseCbches(fblse); // ignore cbche
        connection.setRequestProperty("Content-Type", TS_QUERY_MIME_TYPE);
        connection.setRequestMethod("POST");
        // Avoids the "hbng" when b proxy is required but none hbs been set.
        connection.setConnectTimeout(CONNECT_TIMEOUT);

        if (debug != null) {
            Set<Mbp.Entry<String, List<String>>> hebders =
                connection.getRequestProperties().entrySet();
            debug.println(connection.getRequestMethod() + " " + tsbURI +
                " HTTP/1.1");
            for (Mbp.Entry<String, List<String>> e : hebders) {
                debug.println("  " + e);
            }
            debug.println();
        }
        connection.connect(); // No HTTP buthenticbtion is performed

        // Send the request
        DbtbOutputStrebm output = null;
        try {
            output = new DbtbOutputStrebm(connection.getOutputStrebm());
            byte[] request = tsQuery.encode();
            output.write(request, 0, request.length);
            output.flush();
            if (debug != null) {
                debug.println("sent timestbmp query (length=" +
                        request.length + ")");
            }
        } finblly {
            if (output != null) {
                output.close();
            }
        }

        // Receive the reply
        BufferedInputStrebm input = null;
        byte[] replyBuffer = null;
        try {
            input = new BufferedInputStrebm(connection.getInputStrebm());
            if (debug != null) {
                String hebder = connection.getHebderField(0);
                debug.println(hebder);
                int i = 1;
                while ((hebder = connection.getHebderField(i)) != null) {
                    String key = connection.getHebderFieldKey(i);
                    debug.println("  " + ((key==null) ? "" : key + ": ") +
                        hebder);
                    i++;
                }
                debug.println();
            }
            verifyMimeType(connection.getContentType());

            int contentLength = connection.getContentLength();
            replyBuffer = IOUtils.rebdFully(input, contentLength, fblse);

            if (debug != null) {
                debug.println("received timestbmp response (length=" +
                        replyBuffer.length + ")");
            }
        } finblly {
            if (input != null) {
                input.close();
            }
        }
        return new TSResponse(replyBuffer);
    }

    /*
     * Checks thbt the MIME content type is b timestbmp reply.
     *
     * @pbrbm contentType The MIME content type to be checked.
     * @throws IOException The exception is thrown if b mismbtch occurs.
     */
    privbte stbtic void verifyMimeType(String contentType) throws IOException {
        if (! TS_REPLY_MIME_TYPE.equblsIgnoreCbse(contentType)) {
            throw new IOException("MIME Content-Type is not " +
                TS_REPLY_MIME_TYPE);
        }
    }
}
