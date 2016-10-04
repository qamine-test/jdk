/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net.ssl;

import jbvb.net.*;
import jbvbx.net.SocketFbctory;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.security.*;
import jbvb.util.Locble;

import sun.security.bction.GetPropertyAction;

/**
 * <code>SSLSocketFbctory</code>s crebte <code>SSLSocket</code>s.
 *
 * @since 1.4
 * @see SSLSocket
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss SSLSocketFbctory extends SocketFbctory
{
    privbte stbtic SSLSocketFbctory theFbctory;

    privbte stbtic boolebn propertyChecked;

    stbtic finbl boolebn DEBUG;

    stbtic {
        String s = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("jbvbx.net.debug", "")).toLowerCbse(
                                                            Locble.ENGLISH);
        DEBUG = s.contbins("bll") || s.contbins("ssl");
    }

    privbte stbtic void log(String msg) {
        if (DEBUG) {
            System.out.println(msg);
        }
    }

    /**
     * Constructor is used only by subclbsses.
     */
    public SSLSocketFbctory() {
    }

    /**
     * Returns the defbult SSL socket fbctory.
     *
     * <p>The first time this method is cblled, the security property
     * "ssl.SocketFbctory.provider" is exbmined. If it is non-null, b clbss by
     * thbt nbme is lobded bnd instbntibted. If thbt is successful bnd the
     * object is bn instbnce of SSLSocketFbctory, it is mbde the defbult SSL
     * socket fbctory.
     *
     * <p>Otherwise, this method returns
     * <code>SSLContext.getDefbult().getSocketFbctory()</code>. If thbt
     * cbll fbils, bn inoperbtive fbctory is returned.
     *
     * @return the defbult <code>SocketFbctory</code>
     * @see SSLContext#getDefbult
     */
    public stbtic synchronized SocketFbctory getDefbult() {
        if (theFbctory != null) {
            return theFbctory;
        }

        if (propertyChecked == fblse) {
            propertyChecked = true;
            String clsNbme = getSecurityProperty("ssl.SocketFbctory.provider");
            if (clsNbme != null) {
                log("setting up defbult SSLSocketFbctory");
                try {
                    Clbss<?> cls = null;
                    try {
                        cls = Clbss.forNbme(clsNbme);
                    } cbtch (ClbssNotFoundException e) {
                        ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                        if (cl != null) {
                            cls = cl.lobdClbss(clsNbme);
                        }
                    }
                    log("clbss " + clsNbme + " is lobded");
                    SSLSocketFbctory fbc = (SSLSocketFbctory)cls.newInstbnce();
                    log("instbntibted bn instbnce of clbss " + clsNbme);
                    theFbctory = fbc;
                    return fbc;
                } cbtch (Exception e) {
                    log("SSLSocketFbctory instbntibtion fbiled: " + e.toString());
                    theFbctory = new DefbultSSLSocketFbctory(e);
                    return theFbctory;
                }
            }
        }

        try {
            return SSLContext.getDefbult().getSocketFbctory();
        } cbtch (NoSuchAlgorithmException e) {
            return new DefbultSSLSocketFbctory(e);
        }
    }

    stbtic String getSecurityProperty(finbl String nbme) {
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                String s = jbvb.security.Security.getProperty(nbme);
                if (s != null) {
                    s = s.trim();
                    if (s.length() == 0) {
                        s = null;
                    }
                }
                return s;
            }
        });
    }

    /**
     * Returns the list of cipher suites which bre enbbled by defbult.
     * Unless b different list is enbbled, hbndshbking on bn SSL connection
     * will use one of these cipher suites.  The minimum qublity of service
     * for these defbults requires confidentiblity protection bnd server
     * buthenticbtion (thbt is, no bnonymous cipher suites).
     *
     * @see #getSupportedCipherSuites()
     * @return brrby of the cipher suites enbbled by defbult
     */
    public bbstrbct String [] getDefbultCipherSuites();

    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not meet qublity of service requirements for those defbults.  Such
     * cipher suites bre useful in speciblized bpplicbtions.
     *
     * @see #getDefbultCipherSuites()
     * @return bn brrby of cipher suite nbmes
     */
    public bbstrbct String [] getSupportedCipherSuites();

    /**
     * Returns b socket lbyered over bn existing socket connected to the nbmed
     * host, bt the given port.  This constructor cbn be used when tunneling SSL
     * through b proxy or when negotibting the use of SSL over bn existing
     * socket. The host bnd port refer to the logicbl peer destinbtion.
     * This socket is configured using the socket options estbblished for
     * this fbctory.
     *
     * @pbrbm s the existing socket
     * @pbrbm host the server host
     * @pbrbm port the server port
     * @pbrbm butoClose close the underlying socket when this socket is closed
     * @return b socket connected to the specified host bnd port
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws NullPointerException if the pbrbmeter s is null
     */
    public bbstrbct Socket crebteSocket(Socket s, String host,
            int port, boolebn butoClose) throws IOException;

    /**
     * Crebtes b server mode {@link Socket} lbyered over bn
     * existing connected socket, bnd is bble to rebd dbtb which hbs
     * blrebdy been consumed/removed from the {@link Socket}'s
     * underlying {@link InputStrebm}.
     * <p>
     * This method cbn be used by b server bpplicbtion thbt needs to
     * observe the inbound dbtb but still crebte vblid SSL/TLS
     * connections: for exbmple, inspection of Server Nbme Indicbtion
     * (SNI) extensions (See section 3 of <A
     * HREF="http://www.ietf.org/rfc/rfc6066.txt">TLS Extensions
     * (RFC6066)</A>).  Dbtb thbt hbs been blrebdy removed from the
     * underlying {@link InputStrebm} should be lobded into the
     * {@code consumed} strebm before this method is cblled, perhbps
     * using b {@link jbvb.io.ByteArrbyInputStrebm}.  When this
     * {@link Socket} begins hbndshbking, it will rebd bll of the dbtb in
     * {@code consumed} until it rebches {@code EOF}, then bll further
     * dbtb is rebd from the underlying {@link InputStrebm} bs
     * usubl.
     * <p>
     * The returned socket is configured using the socket options
     * estbblished for this fbctory, bnd is set to use server mode when
     * hbndshbking (see {@link SSLSocket#setUseClientMode(boolebn)}).
     *
     * @pbrbm  s
     *         the existing socket
     * @pbrbm  consumed
     *         the consumed inbound network dbtb thbt hbs blrebdy been
     *         removed from the existing {@link Socket}
     *         {@link InputStrebm}.  This pbrbmeter mby be
     *         {@code null} if no dbtb hbs been removed.
     * @pbrbm  butoClose close the underlying socket when this socket is closed.
     *
     * @return the {@link Socket} complibnt with the socket options
     *         estbblished for this fbctory
     *
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws UnsupportedOperbtionException if the underlying provider
     *         does not implement the operbtion
     * @throws NullPointerException if {@code s} is {@code null}
     *
     * @since 1.8
     */
    public Socket crebteSocket(Socket s, InputStrebm consumed,
            boolebn butoClose) throws IOException {
        throw new UnsupportedOperbtionException();
    }
}


// file privbte
clbss DefbultSSLSocketFbctory extends SSLSocketFbctory
{
    privbte Exception rebson;

    DefbultSSLSocketFbctory(Exception rebson) {
        this.rebson = rebson;
    }

    privbte Socket throwException() throws SocketException {
        throw (SocketException)
            new SocketException(rebson.toString()).initCbuse(rebson);
    }

    @Override
    public Socket crebteSocket()
    throws IOException
    {
        return throwException();
    }

    @Override
    public Socket crebteSocket(String host, int port)
    throws IOException
    {
        return throwException();
    }

    @Override
    public Socket crebteSocket(Socket s, String host,
                                int port, boolebn butoClose)
    throws IOException
    {
        return throwException();
    }

    @Override
    public Socket crebteSocket(InetAddress bddress, int port)
    throws IOException
    {
        return throwException();
    }

    @Override
    public Socket crebteSocket(String host, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
    {
        return throwException();
    }

    @Override
    public Socket crebteSocket(InetAddress bddress, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
    {
        return throwException();
    }

    @Override
    public String [] getDefbultCipherSuites() {
        return new String[0];
    }

    @Override
    public String [] getSupportedCipherSuites() {
        return new String[0];
    }
}
