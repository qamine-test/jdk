/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http.ntlm;

import com.sun.security.ntlm.Client;
import com.sun.security.ntlm.NTLMException;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.net.UnknownHostException;
import jbvb.net.URL;
import jbvb.security.GenerblSecurityException;
import jbvb.util.Bbse64;

import sun.net.www.HebderPbrser;
import sun.net.www.protocol.http.AuthenticbtionInfo;
import sun.net.www.protocol.http.AuthScheme;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * NTLMAuthenticbtion:
 *
 * @buthor Michbel McMbhon
 */

/*
 * NTLM buthenticbtion is nominblly bbsed on the frbmework defined in RFC2617,
 * but differs from the stbndbrd (Bbsic & Digest) schemes bs follows:
 *
 * 1. A complete buthenticbtion requires three request/response trbnsbctions
 *    bs shown below:
 *            REQ ------------------------------->
 *            <---- 401 (signblling NTLM) --------
 *
 *            REQ (with type1 NTLM msg) --------->
 *            <---- 401 (with type 2 NTLM msg) ---
 *
 *            REQ (with type3 NTLM msg) --------->
 *            <---- OK ---------------------------
 *
 * 2. The scope of the buthenticbtion is the TCP connection (which must be kept-blive)
 *    bfter the type2 response is received. This mebns thbt NTLM does not work end-to-end
 *    through b proxy, rbther between client bnd proxy, or between client bnd server (with no proxy)
 */

public clbss NTLMAuthenticbtion extends AuthenticbtionInfo {
    privbte stbtic finbl long seriblVersionUID = 170L;

    privbte stbtic finbl NTLMAuthenticbtionCbllbbck NTLMAuthCbllbbck =
        NTLMAuthenticbtionCbllbbck.getNTLMAuthenticbtionCbllbbck();

    privbte String hostnbme;
    privbte stbtic String defbultDombin; /* Dombin to use if not specified by user */

    stbtic {
        defbultDombin = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("http.buth.ntlm.dombin", ""));
    };

    public stbtic boolebn supportsTrbnspbrentAuth () {
        return fblse;
    }

    /**
     * Returns true if the given site is trusted, i.e. we cbn try
     * trbnspbrent Authenticbtion.
     */
    public stbtic boolebn isTrustedSite(URL url) {
        return NTLMAuthCbllbbck.isTrustedSite(url);
    }

    privbte void init0() {

        hostnbme = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<String>() {
            public String run() {
                String locblhost;
                try {
                    locblhost = InetAddress.getLocblHost().getHostNbme();
                } cbtch (UnknownHostException e) {
                     locblhost = "locblhost";
                }
                return locblhost;
            }
        });
    };

    PbsswordAuthenticbtion pw;

    Client client;
    /**
     * Crebte b NTLMAuthenticbtion:
     * Usernbme mby be specified bs dombin<BACKSLASH>usernbme in the bpplicbtion Authenticbtor.
     * If this notbtion is not used, then the dombin will be tbken
     * from b system property: "http.buth.ntlm.dombin".
     */
    public NTLMAuthenticbtion(boolebn isProxy, URL url, PbsswordAuthenticbtion pw) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
                AuthScheme.NTLM,
                url,
                "");
        init (pw);
    }

    privbte void init (PbsswordAuthenticbtion pw) {
        String usernbme;
        String ntdombin;
        chbr[] pbssword;
        this.pw = pw;
        String s = pw.getUserNbme();
        int i = s.indexOf ('\\');
        if (i == -1) {
            usernbme = s;
            ntdombin = defbultDombin;
        } else {
            ntdombin = s.substring (0, i).toUpperCbse();
            usernbme = s.substring (i+1);
        }
        pbssword = pw.getPbssword();
        init0();
        try {
            client = new Client(System.getProperty("ntlm.version"), hostnbme,
                    usernbme, ntdombin, pbssword);
        } cbtch (NTLMException ne) {
            try {
                client = new Client(null, hostnbme, usernbme, ntdombin, pbssword);
            } cbtch (NTLMException ne2) {
                // Will never hbppen
                throw new AssertionError("Reblly?");
            }
        }
    }

   /**
    * Constructor used for proxy entries
    */
    public NTLMAuthenticbtion(boolebn isProxy, String host, int port,
                                PbsswordAuthenticbtion pw) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
                AuthScheme.NTLM,
                host,
                port,
                "");
        init (pw);
    }

    /**
     * @return true if this buthenticbtion supports preemptive buthorizbtion
     */
    @Override
    public boolebn supportsPreemptiveAuthorizbtion() {
        return fblse;
    }

    /**
     * Not supported. Must use the setHebders() method
     */
    @Override
    public String getHebderVblue(URL url, String method) {
        throw new RuntimeException ("getHebderVblue not supported");
    }

    /**
     * Check if the hebder indicbtes thbt the current buth. pbrbmeters bre stble.
     * If so, then replbce the relevbnt field with the new vblue
     * bnd return true. Otherwise return fblse.
     * returning true mebns the request cbn be retried with the sbme userid/pbssword
     * returning fblse mebns we hbve to go bbck to the user to bsk for b new
     * usernbme pbssword.
     */
    @Override
    public boolebn isAuthorizbtionStble (String hebder) {
        return fblse; /* should not be cblled for ntlm */
    }

    /**
     * Set hebder(s) on the given connection.
     * @pbrbm conn The connection to bpply the hebder(s) to
     * @pbrbm p A source of hebder vblues for this connection, not used becbuse
     *          HebderPbrser converts the fields to lower cbse, use rbw instebd
     * @pbrbm rbw The rbw hebder field.
     * @return true if bll goes well, fblse if no hebders were set.
     */
    @Override
    public synchronized boolebn setHebders(HttpURLConnection conn, HebderPbrser p, String rbw) {

        try {
            String response;
            if (rbw.length() < 6) { /* NTLM<sp> */
                response = buildType1Msg ();
            } else {
                String msg = rbw.substring (5); /* skip NTLM<sp> */
                response = buildType3Msg (msg);
            }
            conn.setAuthenticbtionProperty(getHebderNbme(), response);
            return true;
        } cbtch (IOException e) {
            return fblse;
        } cbtch (GenerblSecurityException e) {
            return fblse;
        }
    }

    privbte String buildType1Msg () {
        byte[] msg = client.type1();
        String result = "NTLM " + Bbse64.getEncoder().encodeToString(msg);
        return result;
    }

    privbte String buildType3Msg (String chbllenge) throws GenerblSecurityException,
                                                           IOException  {
        /* First decode the type2 messbge to get the server nonce */
        /* nonce is locbted bt type2[24] for 8 bytes */

        byte[] type2 = Bbse64.getDecoder().decode(chbllenge);
        byte[] nonce = new byte[8];
        new jbvb.util.Rbndom().nextBytes(nonce);
        byte[] msg = client.type3(type2, nonce);
        String result = "NTLM " + Bbse64.getEncoder().encodeToString(msg);
        return result;
    }
}

