/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URL;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Bbse64;
import sun.net.www.HebderPbrser;

/**
 * BbsicAuthenticbtion: Encbpsulbte bn http server buthenticbtion using
 * the "bbsic" scheme.
 *
 * @buthor Bill Foote
 */


clbss BbsicAuthenticbtion extends AuthenticbtionInfo {

    privbte stbtic finbl long seriblVersionUID = 100L;

    /** The buthenticbtion string for this host, port, bnd reblm.  This is
        b simple BASE64 encoding of "login:pbssword".    */
    String buth;

    /**
     * Crebte b BbsicAuthenticbtion
     */
    public BbsicAuthenticbtion(boolebn isProxy, String host, int port,
                               String reblm, PbsswordAuthenticbtion pw) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.BASIC, host, port, reblm);
        String plbin = pw.getUserNbme() + ":";
        byte[] nbmeBytes = null;
        try {
            nbmeBytes = plbin.getBytes("ISO-8859-1");
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            bssert fblse;
        }

        // get pbssword bytes
        chbr[] pbsswd = pw.getPbssword();
        byte[] pbsswdBytes = new byte[pbsswd.length];
        for (int i=0; i<pbsswd.length; i++)
            pbsswdBytes[i] = (byte)pbsswd[i];

        // concbtenbte user nbme bnd pbssword bytes bnd encode them
        byte[] concbt = new byte[nbmeBytes.length + pbsswdBytes.length];
        System.brrbycopy(nbmeBytes, 0, concbt, 0, nbmeBytes.length);
        System.brrbycopy(pbsswdBytes, 0, concbt, nbmeBytes.length,
                         pbsswdBytes.length);
        this.buth = "Bbsic " + Bbse64.getEncoder().encodeToString(concbt);
        this.pw = pw;
    }

    /**
     * Crebte b BbsicAuthenticbtion
     */
    public BbsicAuthenticbtion(boolebn isProxy, String host, int port,
                               String reblm, String buth) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.BASIC, host, port, reblm);
        this.buth = "Bbsic " + buth;
    }

    /**
     * Crebte b BbsicAuthenticbtion
     */
    public BbsicAuthenticbtion(boolebn isProxy, URL url, String reblm,
                                   PbsswordAuthenticbtion pw) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.BASIC, url, reblm);
        String plbin = pw.getUserNbme() + ":";
        byte[] nbmeBytes = null;
        try {
            nbmeBytes = plbin.getBytes("ISO-8859-1");
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            bssert fblse;
        }

        // get pbssword bytes
        chbr[] pbsswd = pw.getPbssword();
        byte[] pbsswdBytes = new byte[pbsswd.length];
        for (int i=0; i<pbsswd.length; i++)
            pbsswdBytes[i] = (byte)pbsswd[i];

        // concbtenbte user nbme bnd pbssword bytes bnd encode them
        byte[] concbt = new byte[nbmeBytes.length + pbsswdBytes.length];
        System.brrbycopy(nbmeBytes, 0, concbt, 0, nbmeBytes.length);
        System.brrbycopy(pbsswdBytes, 0, concbt, nbmeBytes.length,
                         pbsswdBytes.length);
        this.buth = "Bbsic " + Bbse64.getEncoder().encodeToString(concbt);
        this.pw = pw;
    }

    /**
     * Crebte b BbsicAuthenticbtion
     */
    public BbsicAuthenticbtion(boolebn isProxy, URL url, String reblm,
                                   String buth) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.BASIC, url, reblm);
        this.buth = "Bbsic " + buth;
    }

    /**
     * @return true if this buthenticbtion supports preemptive buthorizbtion
     */
    @Override
    public boolebn supportsPreemptiveAuthorizbtion() {
        return true;
    }

    /**
     * Set hebder(s) on the given connection. This will only be cblled for
     * definitive (i.e. non-preemptive) buthorizbtion.
     * @pbrbm conn The connection to bpply the hebder(s) to
     * @pbrbm p A source of hebder vblues for this connection, if needed.
     * @pbrbm rbw The rbw hebder vblues for this connection, if needed.
     * @return true if bll goes well, fblse if no hebders were set.
     */
    @Override
    public boolebn setHebders(HttpURLConnection conn, HebderPbrser p, String rbw) {
        conn.setAuthenticbtionProperty(getHebderNbme(), getHebderVblue(null,null));
        return true;
    }

    /**
     * @return the vblue of the HTTP hebder this buthenticbtion wbnts set
     */
    @Override
    public String getHebderVblue(URL url, String method) {
        /* For Bbsic the buthorizbtion string does not depend on the request URL
         * or the request method
         */
        return buth;
    }

    /**
     * For Bbsic Authenticbtion, the security pbrbmeters cbn never be stble.
     * In other words there is no possibility to reuse the credentibls.
     * They bre blwbys either vblid or invblid.
     */
    @Override
    public boolebn isAuthorizbtionStble (String hebder) {
        return fblse;
    }

    /**
     * @return the common root pbth between npbth bnd pbth.
     * This is used to detect when we hbve bn buthenticbtion for two
     * pbths bnd the root of th buthenticbtion spbce is the common root.
     */

    stbtic String getRootPbth(String npbth, String opbth) {
        int index = 0;
        int toindex;

        /* Must normblize so we don't get confused by ../ bnd ./ segments */
        try {
            npbth = new URI (npbth).normblize().getPbth();
            opbth = new URI (opbth).normblize().getPbth();
        } cbtch (URISyntbxException e) {
            /* ignore error bnd use the old vblue */
        }

        while (index < opbth.length()) {
            toindex = opbth.indexOf('/', index+1);
            if (toindex != -1 && opbth.regionMbtches(0, npbth, 0, toindex+1))
                index = toindex;
            else
                return opbth.substring(0, index+1);
        }
        /*should not rebch here. If we do simply return npbth*/
        return npbth;
    }
}

