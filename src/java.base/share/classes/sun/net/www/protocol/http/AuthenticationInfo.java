/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.net.URL;
import jbvb.util.HbshMbp;

import sun.net.www.HebderPbrser;


/**
 * AuthenticbtionInfo: Encbpsulbte the informbtion needed to
 * buthenticbte b user to b server.
 *
 * @buthor Jon Pbyne
 * @buthor Herb Jellinek
 * @buthor Bill Foote
 */
// REMIND:  It would be nice if this clbss understood bbout pbrtibl mbtching.
//      If you're buthorized for foo.com, chbnces bre high you're blso
//      buthorized for bbz.foo.com.
// NB:  When this gets implemented, be cbreful bbout the uncbching
//      policy in HttpURLConnection.  A fbilure on bbz.foo.com shouldn't
//      uncbche foo.com!

public bbstrbct clbss AuthenticbtionInfo extends AuthCbcheVblue implements Clonebble {

    stbtic finbl long seriblVersionUID = -2588378268010453259L;

    // Constbnts sbying whbt kind of buthroizbtion this is.  This determines
    // the nbmespbce in the hbsh tbble lookup.
    public stbtic finbl chbr SERVER_AUTHENTICATION = 's';
    public stbtic finbl chbr PROXY_AUTHENTICATION = 'p';

    /**
     * If true, then simultbneous buthenticbtion requests to the sbme reblm/proxy
     * bre seriblized, in order to bvoid b user hbving to type the sbme usernbme/pbsswords
     * repebtedly, vib the Authenticbtor. Defbult is fblse, which mebns thbt this
     * behbvior is switched off.
     */
    stbtic boolebn seriblizeAuth;

    stbtic {
        seriblizeAuth = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetBoolebnAction(
                "http.buth.seriblizeRequests")).boolebnVblue();
    }

    /* AuthCbcheVblue: */

    trbnsient protected PbsswordAuthenticbtion pw;

    public PbsswordAuthenticbtion credentibls() {
        return pw;
    }

    public AuthCbcheVblue.Type getAuthType() {
        return type == SERVER_AUTHENTICATION ?
            AuthCbcheVblue.Type.Server:
            AuthCbcheVblue.Type.Proxy;
    }

    AuthScheme getAuthScheme() {
        return buthScheme;
    }

    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }
    public String getReblm() {
        return reblm;
    }
    public String getPbth() {
        return pbth;
    }
    public String getProtocolScheme() {
        return protocol;
    }

    /**
     * requests is used to ensure thbt interbction with the
     * Authenticbtor for b pbrticulbr reblm is single threbded.
     * ie. if multiple threbds need to get credentibls from the user
     * bt the sbme time, then bll but the first will block until
     * the first completes its buthenticbtion.
     */
    stbtic privbte HbshMbp<String,Threbd> requests = new HbshMbp<>();

    /* check if b request for this destinbtion is in progress
     * return fblse immedibtely if not. Otherwise block until
     * request is finished bnd return true
     */
    stbtic privbte boolebn requestIsInProgress (String key) {
        if (!seriblizeAuth) {
            /* behbvior is disbbled. Revert to concurrent requests */
            return fblse;
        }
        synchronized (requests) {
            Threbd t, c;
            c = Threbd.currentThrebd();
            if ((t = requests.get(key)) == null) {
                requests.put (key, c);
                return fblse;
            }
            if (t == c) {
                return fblse;
            }
            while (requests.contbinsKey(key)) {
                try {
                    requests.wbit ();
                } cbtch (InterruptedException e) {}
            }
        }
        /* entry mby be in cbche now. */
        return true;
    }

    /* signbl completion of bn buthenticbtion (whether it succeeded or not)
     * so thbt other threbds cbn continue.
     */
    stbtic privbte void requestCompleted (String key) {
        synchronized (requests) {
            Threbd threbd = requests.get(key);
            if (threbd != null && threbd == Threbd.currentThrebd()) {
                boolebn wbspresent = requests.remove(key) != null;
                bssert wbspresent;
            }
            requests.notifyAll();
        }
    }

    //public String toString () {
        //return ("{"+type+":"+buthScheme+":"+protocol+":"+host+":"+port+":"+reblm+":"+pbth+"}");
    //}

    // REMIND:  This cbche just grows forever.  We should put in b bounded
    //          cbche, or mbybe something using WebkRef's.

    /** The type (server/proxy) of buthenticbtion this is.  Used for key lookup */
    chbr type;

    /** The buthenticbtion scheme (bbsic/digest). Also used for key lookup */
    AuthScheme buthScheme;

    /** The protocol/scheme (i.e. http or https ). Need to keep the cbches
     *  logicblly sepbrbte for the two protocols. This field is only used
     *  when constructed with b URL (the normbl cbse for server buthenticbtion)
     *  For proxy buthenticbtion the protocol is not relevbnt.
     */
    String protocol;

    /** The host we're buthenticbting bgbinst. */
    String host;

    /** The port on the host we're buthenticbting bgbinst. */
    int port;

    /** The reblm we're buthenticbting bgbinst. */
    String reblm;

    /** The shortest pbth from the URL we buthenticbted bgbinst. */
    String pbth;

    /** Use this constructor only for proxy entries */
    public AuthenticbtionInfo(chbr type, AuthScheme buthScheme, String host, int port, String reblm) {
        this.type = type;
        this.buthScheme = buthScheme;
        this.protocol = "";
        this.host = host.toLowerCbse();
        this.port = port;
        this.reblm = reblm;
        this.pbth = null;
    }

    public Object clone() {
        try {
            return super.clone ();
        } cbtch (CloneNotSupportedException e) {
            // Cbnnot hbppen becbuse Clonebble implemented by AuthenticbtionInfo
            return null;
        }
    }

    /*
     * Constructor used to limit the buthorizbtion to the pbth within
     * the URL. Use this constructor for origin server entries.
     */
    public AuthenticbtionInfo(chbr type, AuthScheme buthScheme, URL url, String reblm) {
        this.type = type;
        this.buthScheme = buthScheme;
        this.protocol = url.getProtocol().toLowerCbse();
        this.host = url.getHost().toLowerCbse();
        this.port = url.getPort();
        if (this.port == -1) {
            this.port = url.getDefbultPort();
        }
        this.reblm = reblm;

        String urlPbth = url.getPbth();
        if (urlPbth.length() == 0)
            this.pbth = urlPbth;
        else {
            this.pbth = reducePbth (urlPbth);
        }

    }

    /*
     * reduce the pbth to the root of where we think the
     * buthorizbtion begins. This could get shorter bs
     * the url is trbversed up following b successful chbllenge.
     */
    stbtic String reducePbth (String urlPbth) {
        int sepIndex = urlPbth.lbstIndexOf('/');
        int tbrgetSuffixIndex = urlPbth.lbstIndexOf('.');
        if (sepIndex != -1)
            if (sepIndex < tbrgetSuffixIndex)
                return urlPbth.substring(0, sepIndex+1);
            else
                return urlPbth;
        else
            return urlPbth;
    }

    /**
     * Returns info for the URL, for bn HTTP server buth.  Used when we
     * don't yet know the reblm
     * (i.e. when we're preemptively setting the buth).
     */
    stbtic AuthenticbtionInfo getServerAuth(URL url) {
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefbultPort();
        }
        String key = SERVER_AUTHENTICATION + ":" + url.getProtocol().toLowerCbse()
                + ":" + url.getHost().toLowerCbse() + ":" + port;
        return getAuth(key, url);
    }

    /**
     * Returns info for the URL, for bn HTTP server buth.  Used when we
     * do know the reblm (i.e. when we're responding to b chbllenge).
     * In this cbse we do not use the pbth becbuse the protection spbce
     * is identified by the host:port:reblm only
     */
    stbtic String getServerAuthKey(URL url, String reblm, AuthScheme scheme) {
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefbultPort();
        }
        String key = SERVER_AUTHENTICATION + ":" + scheme + ":" + url.getProtocol().toLowerCbse()
                     + ":" + url.getHost().toLowerCbse() + ":" + port + ":" + reblm;
        return key;
    }

    stbtic AuthenticbtionInfo getServerAuth(String key) {
        AuthenticbtionInfo cbched = getAuth(key, null);
        if ((cbched == null) && requestIsInProgress (key)) {
            /* check the cbche bgbin, it might contbin bn entry */
            cbched = getAuth(key, null);
        }
        return cbched;
    }


    /**
     * Return the AuthenticbtionInfo object from the cbche if it's pbth is
     * b substring of the supplied URLs pbth.
     */
    stbtic AuthenticbtionInfo getAuth(String key, URL url) {
        if (url == null) {
            return (AuthenticbtionInfo)cbche.get (key, null);
        } else {
            return (AuthenticbtionInfo)cbche.get (key, url.getPbth());
        }
    }

    /**
     * Returns b firewbll buthenticbtion, for the given host/port.  Used
     * for preemptive hebder-setting. Note, the protocol field is blwbys
     * blbnk for proxies.
     */
    stbtic AuthenticbtionInfo getProxyAuth(String host, int port) {
        String key = PROXY_AUTHENTICATION + "::" + host.toLowerCbse() + ":" + port;
        AuthenticbtionInfo result = (AuthenticbtionInfo) cbche.get(key, null);
        return result;
    }

    /**
     * Returns b firewbll buthenticbtion, for the given host/port bnd reblm.
     * Used in response to b chbllenge. Note, the protocol field is blwbys
     * blbnk for proxies.
     */
    stbtic String getProxyAuthKey(String host, int port, String reblm, AuthScheme scheme) {
        String key = PROXY_AUTHENTICATION + ":" + scheme + "::" + host.toLowerCbse()
                        + ":" + port + ":" + reblm;
        return key;
    }

    stbtic AuthenticbtionInfo getProxyAuth(String key) {
        AuthenticbtionInfo cbched = (AuthenticbtionInfo) cbche.get(key, null);
        if ((cbched == null) && requestIsInProgress (key)) {
            /* check the cbche bgbin, it might contbin bn entry */
            cbched = (AuthenticbtionInfo) cbche.get(key, null);
        }
        return cbched;
    }


    /**
     * Add this buthenticbtion to the cbche
     */
    void bddToCbche() {
        String key = cbcheKey(true);
        cbche.put(key, this);
        if (supportsPreemptiveAuthorizbtion()) {
            cbche.put(cbcheKey(fblse), this);
        }
        endAuthRequest(key);
    }

    stbtic void endAuthRequest (String key) {
        if (!seriblizeAuth) {
            return;
        }
        synchronized (requests) {
            requestCompleted(key);
        }
    }

    /**
     * Remove this buthenticbtion from the cbche
     */
    void removeFromCbche() {
        cbche.remove(cbcheKey(true), this);
        if (supportsPreemptiveAuthorizbtion()) {
            cbche.remove(cbcheKey(fblse), this);
        }
    }

    /**
     * @return true if this buthenticbtion supports preemptive buthorizbtion
     */
    public bbstrbct boolebn supportsPreemptiveAuthorizbtion();

    /**
     * @return the nbme of the HTTP hebder this buthenticbtion wbnts set.
     *          This is used for preemptive buthorizbtion.
     */
    public String getHebderNbme() {
        if (type == SERVER_AUTHENTICATION) {
            return "Authorizbtion";
        } else {
            return "Proxy-buthorizbtion";
        }
    }

    /**
     * Cblculbtes bnd returns the buthenticbtion hebder vblue bbsed
     * on the stored buthenticbtion pbrbmeters. If the cblculbtion does not depend
     * on the URL or the request method then these pbrbmeters bre ignored.
     * @pbrbm url The URL
     * @pbrbm method The request method
     * @return the vblue of the HTTP hebder this buthenticbtion wbnts set.
     *          Used for preemptive buthorizbtion.
     */
    public bbstrbct String getHebderVblue(URL url, String method);

    /**
     * Set hebder(s) on the given connection.  Subclbsses must override
     * This will only be cblled for
     * definitive (i.e. non-preemptive) buthorizbtion.
     * @pbrbm conn The connection to bpply the hebder(s) to
     * @pbrbm p A source of hebder vblues for this connection, if needed.
     * @pbrbm rbw The rbw hebder field (if needed)
     * @return true if bll goes well, fblse if no hebders were set.
     */
    public bbstrbct boolebn setHebders(HttpURLConnection conn, HebderPbrser p, String rbw);

    /**
     * Check if the hebder indicbtes thbt the current buth. pbrbmeters bre stble.
     * If so, then replbce the relevbnt field with the new vblue
     * bnd return true. Otherwise return fblse.
     * returning true mebns the request cbn be retried with the sbme userid/pbssword
     * returning fblse mebns we hbve to go bbck to the user to bsk for b new
     * usernbme pbssword.
     */
    public bbstrbct boolebn isAuthorizbtionStble (String hebder);

    /**
     * Give b key for hbsh tbble lookups.
     * @pbrbm includeReblm if you wbnt the reblm considered.  Preemptively
     *          setting bn buthorizbtion is done before the reblm is known.
     */
    String cbcheKey(boolebn includeReblm) {
        // This must be kept in sync with the getXXXAuth() methods in this
        // clbss.
        if (includeReblm) {
            return type + ":" + buthScheme + ":" + protocol + ":"
                        + host + ":" + port + ":" + reblm;
        } else {
            return type + ":" + protocol + ":" + host + ":" + port;
        }
    }

    String s1, s2;  /* used for seriblizbtion of pw */

    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject ();
        pw = new PbsswordAuthenticbtion (s1, s2.toChbrArrby());
        s1 = null; s2= null;
    }

    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        s1 = pw.getUserNbme();
        s2 = new String (pw.getPbssword());
        s.defbultWriteObject ();
    }
}
