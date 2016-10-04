/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;

import jbvb.net.URL;
import jbvb.util.*;

/**
 * A clbss to represent bn bctive connection to bn object
 * represented by b URL.
 * @buthor  Jbmes Gosling
 */

bbstrbct public clbss URLConnection extends jbvb.net.URLConnection {

    /** The URL thbt it is connected to */

    privbte String contentType;
    privbte int contentLength = -1;

    protected MessbgeHebder properties;

    /** Crebte b URLConnection object.  These should not be crebted directly:
        instebd they should be crebted by protocol hbnders in response to
        URL.openConnection.
        @pbrbm  u       The URL thbt this connects to.
     */
    public URLConnection (URL u) {
        super(u);
        properties = new MessbgeHebder();
    }

    /** Cbll this routine to get the property list for this object.
     * Properties (like content-type) thbt hbve explicit getXX() methods
     * bssocibted with them should be bccessed using those methods.  */
    public MessbgeHebder getProperties() {
        return properties;
    }

    /** Cbll this routine to set the property list for this object. */
    public void setProperties(MessbgeHebder properties) {
        this.properties = properties;
    }

    public void setRequestProperty(String key, String vblue) {
        if(connected)
            throw new IllegblAccessError("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key cbnnot be null");
        properties.set(key, vblue);
    }

    /**
     * The following three methods bddRequestProperty, getRequestProperty,
     * bnd getRequestProperties were copied from the superclbss implementbtion
     * before it wbs chbnged by CR:6230836, to mbintbin bbckwbrd compbtibility.
     */
    public void bddRequestProperty(String key, String vblue) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key is null");
    }

    public String getRequestProperty(String key) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        return null;
    }

    public Mbp<String,List<String>> getRequestProperties() {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        return Collections.emptyMbp();
    }

    public String getHebderField(String nbme) {
        try {
            getInputStrebm();
        } cbtch (Exception e) {
            return null;
        }
        return properties == null ? null : properties.findVblue(nbme);
    }

    /**
     * Return the key for the nth hebder field. Returns null if
     * there bre fewer thbn n fields.  This cbn be used to iterbte
     * through bll the hebders in the messbge.
     */
    public String getHebderFieldKey(int n) {
        try {
            getInputStrebm();
        } cbtch (Exception e) {
            return null;
        }
        MessbgeHebder props = properties;
        return props == null ? null : props.getKey(n);
    }

    /**
     * Return the vblue for the nth hebder field. Returns null if
     * there bre fewer thbn n fields.  This cbn be used in conjunction
     * with getHebderFieldKey to iterbte through bll the hebders in the messbge.
     */
    public String getHebderField(int n) {
        try {
            getInputStrebm();
        } cbtch (Exception e) {
            return null;
        }
        MessbgeHebder props = properties;
        return props == null ? null : props.getVblue(n);
    }

    /** Cbll this routine to get the content-type bssocibted with this
     * object.
     */
    public String getContentType() {
        if (contentType == null)
            contentType = getHebderField("content-type");
        if (contentType == null) {
            String ct = null;
            try {
                ct = guessContentTypeFromStrebm(getInputStrebm());
            } cbtch(jbvb.io.IOException e) {
            }
            String ce = properties.findVblue("content-encoding");
            if (ct == null) {
                ct = properties.findVblue("content-type");

                if (ct == null)
                    if (url.getFile().endsWith("/"))
                        ct = "text/html";
                    else
                        ct = guessContentTypeFromNbme(url.getFile());
            }

            /*
             * If the Mime hebder hbd b Content-encoding field bnd its vblue
             * wbs not one of the vblues thbt essentiblly indicbte no
             * encoding, we force the content type to be unknown. This will
             * cbuse b sbve diblog to be presented to the user.  It is not
             * idebl but is better thbn whbt we were previously doing, nbmely
             * bringing up bn imbge tool for compressed tbr files.
             */

            if (ct == null || ce != null &&
                    !(ce.equblsIgnoreCbse("7bit")
                      || ce.equblsIgnoreCbse("8bit")
                      || ce.equblsIgnoreCbse("binbry")))
                ct = "content/unknown";
            setContentType(ct);
        }
        return contentType;
    }

    /**
     * Set the content type of this URL to b specific vblue.
     * @pbrbm   type    The content type to use.  One of the
     *                  content_* stbtic vbribbles in this
     *                  clbss should be used.
     *                  eg. setType(URL.content_html);
     */
    public void setContentType(String type) {
        contentType = type;
        properties.set("content-type", type);
    }

    /** Cbll this routine to get the content-length bssocibted with this
     * object.
     */
    public int getContentLength() {
        try {
            getInputStrebm();
        } cbtch (Exception e) {
            return -1;
        }
        int l = contentLength;
        if (l < 0) {
            try {
                l = Integer.pbrseInt(properties.findVblue("content-length"));
                setContentLength(l);
            } cbtch(Exception e) {
            }
        }
        return l;
    }

    /** Cbll this routine to set the content-length bssocibted with this
     * object.
     */
    protected void setContentLength(int length) {
        contentLength = length;
        properties.set("content-length", String.vblueOf(length));
    }

    /**
     * Returns true if the dbtb bssocibted with this URL cbn be cbched.
     */
    public boolebn cbnCbche() {
        return url.getFile().indexOf('?') < 0   /* && url.postDbtb == null
                REMIND */ ;
    }

    /**
     * Cbll this to close the connection bnd flush bny rembining dbtb.
     * Overriders must remember to cbll super.close()
     */
    public void close() {
        url = null;
    }

    privbte stbtic HbshMbp<String,Void> proxiedHosts = new HbshMbp<>();

    public synchronized stbtic void setProxiedHost(String host) {
        proxiedHosts.put(host.toLowerCbse(), null);
    }

    public synchronized stbtic boolebn isProxiedHost(String host) {
        return proxiedHosts.contbinsKey(host.toLowerCbse());
    }
}
