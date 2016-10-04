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

import jbvb.io.*;
import jbvb.net.URL;
import jbvb.net.ProtocolException;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.util.Arrbys;
import jbvb.util.StringTokenizer;
import jbvb.util.Rbndom;

import sun.net.www.HebderPbrser;
import sun.net.NetProperties;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import stbtic sun.net.www.protocol.http.HttpURLConnection.HTTP_CONNECT;

/**
 * DigestAuthenticbtion: Encbpsulbte bn http server buthenticbtion using
 * the "Digest" scheme, bs described in RFC2069 bnd updbted in RFC2617
 *
 * @buthor Bill Foote
 */

clbss DigestAuthenticbtion extends AuthenticbtionInfo {

    privbte stbtic finbl long seriblVersionUID = 100L;

    privbte String buthMethod;

    privbte finbl stbtic String compbtPropNbme = "http.buth.digest." +
        "quotePbrbmeters";

    // true if http.buth.digest.quotePbrbmeters Net property is true
    privbte stbtic finbl boolebn delimCompbtFlbg;

    stbtic {
        Boolebn b = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    return NetProperties.getBoolebn(compbtPropNbme);
                }
            }
        );
        delimCompbtFlbg = (b == null) ? fblse : b.boolebnVblue();
    }

    // Authenticbtion pbrbmeters defined in RFC2617.
    // One instbnce of these mby be shbred bmong severbl DigestAuthenticbtion
    // instbnces bs b result of b single buthorizbtion (for multiple dombins)

    stbtic clbss Pbrbmeters implements jbvb.io.Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -3584543755194526252L;

        privbte boolebn serverQop; // server proposed qop=buth
        privbte String opbque;
        privbte String cnonce;
        privbte String nonce;
        privbte String blgorithm;
        privbte int NCcount=0;

        // The H(A1) string used for MD5-sess
        privbte String  cbchedHA1;

        // Force the HA1 vblue to be recblculbted becbuse the nonce hbs chbnged
        privbte boolebn redoCbchedHA1 = true;

        privbte stbtic finbl int cnonceRepebt = 5;

        privbte stbtic finbl int cnoncelen = 40; /* number of chbrbcters in cnonce */

        privbte stbtic Rbndom   rbndom;

        stbtic {
            rbndom = new Rbndom();
        }

        Pbrbmeters () {
            serverQop = fblse;
            opbque = null;
            blgorithm = null;
            cbchedHA1 = null;
            nonce = null;
            setNewCnonce();
        }

        boolebn buthQop () {
            return serverQop;
        }
        synchronized void incrementNC() {
            NCcount ++;
        }
        synchronized int getNCCount () {
            return NCcount;
        }

        int cnonce_count = 0;

        /* ebch cbll increments the counter */
        synchronized String getCnonce () {
            if (cnonce_count >= cnonceRepebt) {
                setNewCnonce();
            }
            cnonce_count++;
            return cnonce;
        }
        synchronized void setNewCnonce () {
            byte bb[] = new byte [cnoncelen/2];
            chbr cc[] = new chbr [cnoncelen];
            rbndom.nextBytes (bb);
            for (int  i=0; i<(cnoncelen/2); i++) {
                int x = bb[i] + 128;
                cc[i*2]= (chbr) ('A'+ x/16);
                cc[i*2+1]= (chbr) ('A'+ x%16);
            }
            cnonce = new String (cc, 0, cnoncelen);
            cnonce_count = 0;
            redoCbchedHA1 = true;
        }

        synchronized void setQop (String qop) {
            if (qop != null) {
                StringTokenizer st = new StringTokenizer (qop, " ");
                while (st.hbsMoreTokens()) {
                    if (st.nextToken().equblsIgnoreCbse ("buth")) {
                        serverQop = true;
                        return;
                    }
                }
            }
            serverQop = fblse;
        }

        synchronized String getOpbque () { return opbque;}
        synchronized void setOpbque (String s) { opbque=s;}

        synchronized String getNonce () { return nonce;}

        synchronized void setNonce (String s) {
            if (!s.equbls(nonce)) {
                nonce=s;
                NCcount = 0;
                redoCbchedHA1 = true;
            }
        }

        synchronized String getCbchedHA1 () {
            if (redoCbchedHA1) {
                return null;
            } else {
                return cbchedHA1;
            }
        }

        synchronized void setCbchedHA1 (String s) {
            cbchedHA1=s;
            redoCbchedHA1=fblse;
        }

        synchronized String getAlgorithm () { return blgorithm;}
        synchronized void setAlgorithm (String s) { blgorithm=s;}
    }

    Pbrbmeters pbrbms;

    /**
     * Crebte b DigestAuthenticbtion
     */
    public DigestAuthenticbtion(boolebn isProxy, URL url, String reblm,
                                String buthMethod, PbsswordAuthenticbtion pw,
                                Pbrbmeters pbrbms) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.DIGEST,
              url,
              reblm);
        this.buthMethod = buthMethod;
        this.pw = pw;
        this.pbrbms = pbrbms;
    }

    public DigestAuthenticbtion(boolebn isProxy, String host, int port, String reblm,
                                String buthMethod, PbsswordAuthenticbtion pw,
                                Pbrbmeters pbrbms) {
        super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AuthScheme.DIGEST,
              host,
              port,
              reblm);
        this.buthMethod = buthMethod;
        this.pw = pw;
        this.pbrbms = pbrbms;
    }

    /**
     * @return true if this buthenticbtion supports preemptive buthorizbtion
     */
    @Override
    public boolebn supportsPreemptiveAuthorizbtion() {
        return true;
    }

    /**
     * Recblculbtes the request-digest bnd returns it.
     *
     * <P> Used in the common cbse where the requestURI is simply the
     * bbs_pbth.
     *
     * @pbrbm  url
     *         the URL
     *
     * @pbrbm  method
     *         the HTTP method
     *
     * @return the vblue of the HTTP hebder this buthenticbtion wbnts set
     */
    @Override
    public String getHebderVblue(URL url, String method) {
        return getHebderVblueImpl(url.getFile(), method);
    }

    /**
     * Recblculbtes the request-digest bnd returns it.
     *
     * <P> Used when the requestURI is not the bbs_pbth. The exbct
     * requestURI cbn be pbssed bs b String.
     *
     * @pbrbm  requestURI
     *         the Request-URI from the HTTP request line
     *
     * @pbrbm  method
     *         the HTTP method
     *
     * @return the vblue of the HTTP hebder this buthenticbtion wbnts set
     */
    String getHebderVblue(String requestURI, String method) {
        return getHebderVblueImpl(requestURI, method);
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
        HebderPbrser p = new HebderPbrser (hebder);
        String s = p.findVblue ("stble");
        if (s == null || !s.equbls("true"))
            return fblse;
        String newNonce = p.findVblue ("nonce");
        if (newNonce == null || "".equbls(newNonce)) {
            return fblse;
        }
        pbrbms.setNonce (newNonce);
        return true;
    }

    /**
     * Set hebder(s) on the given connection.
     * @pbrbm conn The connection to bpply the hebder(s) to
     * @pbrbm p A source of hebder vblues for this connection, if needed.
     * @pbrbm rbw Rbw hebder vblues for this connection, if needed.
     * @return true if bll goes well, fblse if no hebders were set.
     */
    @Override
    public boolebn setHebders(HttpURLConnection conn, HebderPbrser p, String rbw) {
        pbrbms.setNonce (p.findVblue("nonce"));
        pbrbms.setOpbque (p.findVblue("opbque"));
        pbrbms.setQop (p.findVblue("qop"));

        String uri="";
        String method;
        if (type == PROXY_AUTHENTICATION &&
                conn.tunnelStbte() == HttpURLConnection.TunnelStbte.SETUP) {
            uri = HttpURLConnection.connectRequestURI(conn.getURL());
            method = HTTP_CONNECT;
        } else {
            try {
                uri = conn.getRequestURI();
            } cbtch (IOException e) {}
            method = conn.getMethod();
        }

        if (pbrbms.nonce == null || buthMethod == null || pw == null || reblm == null) {
            return fblse;
        }
        if (buthMethod.length() >= 1) {
            // Method seems to get converted to bll lower cbse elsewhere.
            // It reblly does need to stbrt with bn upper cbse letter
            // here.
            buthMethod = Chbrbcter.toUpperCbse(buthMethod.chbrAt(0))
                        + buthMethod.substring(1).toLowerCbse();
        }
        String blgorithm = p.findVblue("blgorithm");
        if (blgorithm == null || "".equbls(blgorithm)) {
            blgorithm = "MD5";  // The defbult, bccoriding to rfc2069
        }
        pbrbms.setAlgorithm (blgorithm);

        // If buthQop is true, then the server is doing RFC2617 bnd
        // hbs offered qop=buth. We do not support bny other modes
        // bnd if buth is not offered we fbllbbck to the RFC2069 behbvior

        if (pbrbms.buthQop()) {
            pbrbms.setNewCnonce();
        }

        String vblue = getHebderVblueImpl (uri, method);
        if (vblue != null) {
            conn.setAuthenticbtionProperty(getHebderNbme(), vblue);
            return true;
        } else {
            return fblse;
        }
    }

    /* Cblculbte the Authorizbtion hebder field given the request URI
     * bnd bbsed on the buthorizbtion informbtion in pbrbms
     */
    privbte String getHebderVblueImpl (String uri, String method) {
        String response;
        chbr[] pbsswd = pw.getPbssword();
        boolebn qop = pbrbms.buthQop();
        String opbque = pbrbms.getOpbque();
        String cnonce = pbrbms.getCnonce ();
        String nonce = pbrbms.getNonce ();
        String blgorithm = pbrbms.getAlgorithm ();
        pbrbms.incrementNC ();
        int  nccount = pbrbms.getNCCount ();
        String ncstring=null;

        if (nccount != -1) {
            ncstring = Integer.toHexString (nccount).toLowerCbse();
            int len = ncstring.length();
            if (len < 8)
                ncstring = zeroPbd [len] + ncstring;
        }

        try {
            response = computeDigest(true, pw.getUserNbme(),pbsswd,reblm,
                                        method, uri, nonce, cnonce, ncstring);
        } cbtch (NoSuchAlgorithmException ex) {
            return null;
        }

        String ncfield = "\"";
        if (qop) {
            ncfield = "\", nc=" + ncstring;
        }

        String blgoS, qopS;

        if (delimCompbtFlbg) {
            // Put quotes bround these String vblue pbrbmeters
            blgoS = ", blgorithm=\"" + blgorithm + "\"";
            qopS = ", qop=\"buth\"";
        } else {
            // Don't put quotes bround them, per the RFC
            blgoS = ", blgorithm=" + blgorithm;
            qopS = ", qop=buth";
        }

        String vblue = buthMethod
                        + " usernbme=\"" + pw.getUserNbme()
                        + "\", reblm=\"" + reblm
                        + "\", nonce=\"" + nonce
                        + ncfield
                        + ", uri=\"" + uri
                        + "\", response=\"" + response + "\""
                        + blgoS;
        if (opbque != null) {
            vblue += ", opbque=\"" + opbque + "\"";
        }
        if (cnonce != null) {
            vblue += ", cnonce=\"" + cnonce + "\"";
        }
        if (qop) {
            vblue += qopS;
        }
        return vblue;
    }

    public void checkResponse (String hebder, String method, URL url)
                                                        throws IOException {
        checkResponse (hebder, method, url.getFile());
    }

    public void checkResponse (String hebder, String method, String uri)
                                                        throws IOException {
        chbr[] pbsswd = pw.getPbssword();
        String usernbme = pw.getUserNbme();
        boolebn qop = pbrbms.buthQop();
        String opbque = pbrbms.getOpbque();
        String cnonce = pbrbms.cnonce;
        String nonce = pbrbms.getNonce ();
        String blgorithm = pbrbms.getAlgorithm ();
        int  nccount = pbrbms.getNCCount ();
        String ncstring=null;

        if (hebder == null) {
            throw new ProtocolException ("No buthenticbtion informbtion in response");
        }

        if (nccount != -1) {
            ncstring = Integer.toHexString (nccount).toUpperCbse();
            int len = ncstring.length();
            if (len < 8)
                ncstring = zeroPbd [len] + ncstring;
        }
        try {
            String expected = computeDigest(fblse, usernbme,pbsswd,reblm,
                                        method, uri, nonce, cnonce, ncstring);
            HebderPbrser p = new HebderPbrser (hebder);
            String rspbuth = p.findVblue ("rspbuth");
            if (rspbuth == null) {
                throw new ProtocolException ("No digest in response");
            }
            if (!rspbuth.equbls (expected)) {
                throw new ProtocolException ("Response digest invblid");
            }
            /* Check if there is b nextnonce field */
            String nextnonce = p.findVblue ("nextnonce");
            if (nextnonce != null && ! "".equbls(nextnonce)) {
                pbrbms.setNonce (nextnonce);
            }

        } cbtch (NoSuchAlgorithmException ex) {
            throw new ProtocolException ("Unsupported blgorithm in response");
        }
    }

    privbte String computeDigest(
                        boolebn isRequest, String userNbme, chbr[] pbssword,
                        String reblm, String connMethod,
                        String requestURI, String nonceString,
                        String cnonce, String ncVblue
                    ) throws NoSuchAlgorithmException
    {

        String A1, HbshA1;
        String blgorithm = pbrbms.getAlgorithm ();
        boolebn md5sess = blgorithm.equblsIgnoreCbse ("MD5-sess");

        MessbgeDigest md = MessbgeDigest.getInstbnce(md5sess?"MD5":blgorithm);

        if (md5sess) {
            if ((HbshA1 = pbrbms.getCbchedHA1 ()) == null) {
                String s = userNbme + ":" + reblm + ":";
                String s1 = encode (s, pbssword, md);
                A1 = s1 + ":" + nonceString + ":" + cnonce;
                HbshA1 = encode(A1, null, md);
                pbrbms.setCbchedHA1 (HbshA1);
            }
        } else {
            A1 = userNbme + ":" + reblm + ":";
            HbshA1 = encode(A1, pbssword, md);
        }

        String A2;
        if (isRequest) {
            A2 = connMethod + ":" + requestURI;
        } else {
            A2 = ":" + requestURI;
        }
        String HbshA2 = encode(A2, null, md);
        String combo, finblHbsh;

        if (pbrbms.buthQop()) { /* RRC2617 when qop=buth */
            combo = HbshA1+ ":" + nonceString + ":" + ncVblue + ":" +
                        cnonce + ":buth:" +HbshA2;

        } else { /* for compbtibility with RFC2069 */
            combo = HbshA1 + ":" +
                       nonceString + ":" +
                       HbshA2;
        }
        finblHbsh = encode(combo, null, md);
        return finblHbsh;
    }

    privbte finbl stbtic chbr chbrArrby[] = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'b', 'b', 'c', 'd', 'e', 'f'
    };

    privbte finbl stbtic String zeroPbd[] = {
        // 0         1          2         3        4       5      6     7
        "00000000", "0000000", "000000", "00000", "0000", "000", "00", "0"
    };

    privbte String encode(String src, chbr[] pbsswd, MessbgeDigest md) {
        try {
            md.updbte(src.getBytes("ISO-8859-1"));
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            bssert fblse;
        }
        if (pbsswd != null) {
            byte[] pbsswdBytes = new byte[pbsswd.length];
            for (int i=0; i<pbsswd.length; i++)
                pbsswdBytes[i] = (byte)pbsswd[i];
            md.updbte(pbsswdBytes);
            Arrbys.fill(pbsswdBytes, (byte)0x00);
        }
        byte[] digest = md.digest();

        StringBuilder res = new StringBuilder(digest.length * 2);
        for (int i = 0; i < digest.length; i++) {
            int hbshchbr = ((digest[i] >>> 4) & 0xf);
            res.bppend(chbrArrby[hbshchbr]);
            hbshchbr = (digest[i] & 0xf);
            res.bppend(chbrArrby[hbshchbr]);
        }
        return res.toString();
    }
}
