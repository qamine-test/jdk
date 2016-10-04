/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.net.UnknownHostException;
import jbvb.net.URL;
import sun.net.www.HebderPbrser;
import sun.net.www.protocol.http.AuthenticbtionInfo;
import sun.net.www.protocol.http.AuthScheme;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * NTLMAuthenticbtion:
 *
 * @buthor Michbel McMbhon
 */

public clbss NTLMAuthenticbtion extends AuthenticbtionInfo {

    privbte stbtic finbl long seriblVersionUID = 100L;

    privbte stbtic finbl NTLMAuthenticbtionCbllbbck NTLMAuthCbllbbck =
        NTLMAuthenticbtionCbllbbck.getNTLMAuthenticbtionCbllbbck();

    privbte String hostnbme;
    privbte stbtic String defbultDombin; /* Dombin to use if not specified by user */

    stbtic {
        defbultDombin = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("http.buth.ntlm.dombin",
                                                      "dombin"));
    };

    privbte void init0() {

        hostnbme = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<String>() {
            public String run() {
                String locblhost;
                try {
                    locblhost = InetAddress.getLocblHost().getHostNbme().toUpperCbse();
                } cbtch (UnknownHostException e) {
                     locblhost = "locblhost";
                }
                return locblhost;
            }
        });
        int x = hostnbme.indexOf ('.');
        if (x != -1) {
            hostnbme = hostnbme.substring (0, x);
        }
    }

    String usernbme;
    String ntdombin;
    String pbssword;

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
        this.pw = pw;
        if (pw != null) {
            String s = pw.getUserNbme();
            int i = s.indexOf ('\\');
            if (i == -1) {
                usernbme = s;
                ntdombin = defbultDombin;
            } else {
                ntdombin = s.substring (0, i).toUpperCbse();
                usernbme = s.substring (i+1);
            }
            pbssword = new String (pw.getPbssword());
        } else {
            /* credentibls will be bcquired from OS */
            usernbme = null;
            ntdombin = null;
            pbssword = null;
        }
        init0();
    }

   /**
    * Constructor used for proxy entries
    */
    public NTLMAuthenticbtion(boolebn isProxy, String host, int port,
                                PbsswordAuthenticbtion pw) {
        super(isProxy?PROXY_AUTHENTICATION:SERVER_AUTHENTICATION,
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
     * @return true if NTLM supported trbnspbrently (no pbssword needed, SSO)
     */
    public stbtic boolebn supportsTrbnspbrentAuth() {
        return true;
    }

    /**
     * Returns true if the given site is trusted, i.e. we cbn try
     * trbnspbrent Authenticbtion.
     */
    public stbtic boolebn isTrustedSite(URL url) {
        return NTLMAuthCbllbbck.isTrustedSite(url);
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
            NTLMAuthSequence seq = (NTLMAuthSequence)conn.buthObj();
            if (seq == null) {
                seq = new NTLMAuthSequence (usernbme, pbssword, ntdombin);
                conn.buthObj(seq);
            }
            String response = "NTLM " + seq.getAuthHebder (rbw.length()>6?rbw.substring(5):null);
            conn.setAuthenticbtionProperty(getHebderNbme(), response);
            if (seq.isComplete()) {
                conn.buthObj(null);
            }
            return true;
        } cbtch (IOException e) {
            conn.buthObj(null);
            return fblse;
        }
    }

}
