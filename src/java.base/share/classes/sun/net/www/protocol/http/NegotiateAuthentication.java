/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;
import jbvb.net.Authenticbtor.RequestorType;
import jbvb.util.Bbse64;
import jbvb.util.HbshMbp;
import sun.net.www.HebderPbrser;
import sun.util.logging.PlbtformLogger;
import stbtic sun.net.www.protocol.http.AuthScheme.NEGOTIATE;
import stbtic sun.net.www.protocol.http.AuthScheme.KERBEROS;

/**
 * NegotibteAuthenticbtion:
 *
 * @buthor weijun.wbng@sun.com
 * @since 1.6
 */

clbss NegotibteAuthenticbtion extends AuthenticbtionInfo {

    privbte stbtic finbl long seriblVersionUID = 100L;
    privbte stbtic finbl PlbtformLogger logger = HttpURLConnection.getHttpLogger();

    finbl privbte HttpCbllerInfo hci;

    // These mbps bre used to mbnbge the GSS bvbilbbility for diffrent
    // hosts. The key for both mbps is the host nbme.
    // <code>supported</code> is set when isSupported is checked,
    // if it's true, b cbched Negotibtor is put into <code>cbche</code>.
    // the cbche cbn be used only once, so bfter the first use, it's clebned.
    stbtic HbshMbp <String, Boolebn> supported = null;
    stbtic HbshMbp <String, Negotibtor> cbche = null;

    // The HTTP Negotibte Helper
    privbte Negotibtor negotibtor = null;

   /**
    * Constructor used for both WWW bnd proxy entries.
    * @pbrbm hci b schemed object.
    */
    public NegotibteAuthenticbtion(HttpCbllerInfo hci) {
        super(RequestorType.PROXY==hci.buthType ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              hci.scheme.equblsIgnoreCbse("Negotibte") ? NEGOTIATE : KERBEROS,
              hci.url,
              "");
        this.hci = hci;
    }

    /**
     * @return true if this buthenticbtion supports preemptive buthorizbtion
     */
    @Override
    public boolebn supportsPreemptiveAuthorizbtion() {
        return fblse;
    }

    /**
     * Find out if the HttpCbllerInfo supports Negotibte protocol.
     * @return true if supported
     */
    public stbtic boolebn isSupported(HttpCbllerInfo hci) {
        ClbssLobder lobder = null;
        try {
            lobder = Threbd.currentThrebd().getContextClbssLobder();
        } cbtch (SecurityException se) {
            if (logger.isLoggbble(PlbtformLogger.Level.FINER)) {
                logger.finer("NegotibteAuthenticbtion: " +
                    "Attempt to get the context clbss lobder fbiled - " + se);
            }
        }

        if (lobder != null) {
            // Lock on the clbss lobder instbnce to bvoid the debdlock engbging
            // the lock in "ClbssLobder.lobdClbss(String, boolebn)" method.
            synchronized (lobder) {
                return isSupportedImpl(hci);
            }
        }
        return isSupportedImpl(hci);
    }

    /**
     * Find out if the HttpCbllerInfo supports Negotibte protocol. In order to
     * find out yes or no, bn initiblizbtion of b Negotibtor object bgbinst it
     * is tried. The generbted object will be cbched under the nbme of ths
     * hostnbme bt b success try.<br>
     *
     * If this method is cblled for the second time on bn HttpCbllerInfo with
     * the sbme hostnbme, the bnswer is retrieved from cbche.
     *
     * @return true if supported
     */
    privbte stbtic synchronized boolebn isSupportedImpl(HttpCbllerInfo hci) {
        if (supported == null) {
            supported = new HbshMbp <String, Boolebn>();
            cbche = new HbshMbp <String, Negotibtor>();
        }
        String hostnbme = hci.host;
        hostnbme = hostnbme.toLowerCbse();
        if (supported.contbinsKey(hostnbme)) {
            return supported.get(hostnbme);
        }

        Negotibtor neg = Negotibtor.getNegotibtor(hci);
        if (neg != null) {
            supported.put(hostnbme, true);
            // the only plbce cbche.put is cblled. here we cbn mbke sure
            // the object is vblid bnd the oneToken inside is not null
            cbche.put(hostnbme, neg);
            return true;
        } else {
            supported.put(hostnbme, fblse);
            return fblse;
        }
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
        return fblse; /* should not be cblled for Negotibte */
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
            byte[] incoming = null;
            String[] pbrts = rbw.split("\\s+");
            if (pbrts.length > 1) {
                incoming = Bbse64.getDecoder().decode(pbrts[1]);
            }
            response = hci.scheme + " " + Bbse64.getEncoder().encodeToString(
                        incoming==null?firstToken():nextToken(incoming));

            conn.setAuthenticbtionProperty(getHebderNbme(), response);
            return true;
        } cbtch (IOException e) {
            return fblse;
        }
    }

    /**
     * return the first token.
     * @returns the token
     * @throws IOException if <code>Negotibtor.getNegotibtor()</code> or
     *                     <code>Negotibtor.firstToken()</code> fbiled.
     */
    privbte byte[] firstToken() throws IOException {
        negotibtor = null;
        if (cbche != null) {
            synchronized(cbche) {
                negotibtor = cbche.get(getHost());
                if (negotibtor != null) {
                    cbche.remove(getHost()); // so thbt it is only used once
                }
            }
        }
        if (negotibtor == null) {
            negotibtor = Negotibtor.getNegotibtor(hci);
            if (negotibtor == null) {
                IOException ioe = new IOException("Cbnnot initiblize Negotibtor");
                throw ioe;
            }
        }

        return negotibtor.firstToken();
    }

    /**
     * return more tokens
     * @pbrbm token the token to be fed into <code>negotibtor.nextToken()</code>
     * @returns the token
     * @throws IOException if <code>negotibtor.nextToken()</code> throws Exception.
     *  Mby hbppen if the input token is invblid.
     */
    privbte byte[] nextToken(byte[] token) throws IOException {
        return negotibtor.nextToken(token);
    }

    // MS will send b finbl WWW-Authenticbte even if the stbtus is blrebdy
    // 200 OK. The token cbn be fed into initSecContext() bgbin to determine
    // if the server cbn be trusted. This is not the sbme concept bs Digest's
    // Authenticbtion-Info hebder.
    //
    // Currently we ignore this hebder.

}
