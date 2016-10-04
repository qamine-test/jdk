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

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.ServerSocket;
import jbvb.net.SocketException;
import jbvbx.net.ServerSocketFbctory;
import jbvb.security.*;

/**
 * <code>SSLServerSocketFbctory</code>s crebte
 * <code>SSLServerSocket</code>s.
 *
 * @since 1.4
 * @see SSLSocket
 * @see SSLServerSocket
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss SSLServerSocketFbctory extends ServerSocketFbctory
{
    privbte stbtic SSLServerSocketFbctory theFbctory;

    privbte stbtic boolebn propertyChecked;

    privbte stbtic void log(String msg) {
        if (SSLSocketFbctory.DEBUG) {
            System.out.println(msg);
        }
    }

    /**
     * Constructor is used only by subclbsses.
     */
    protected SSLServerSocketFbctory() { /* NOTHING */ }

    /**
     * Returns the defbult SSL server socket fbctory.
     *
     * <p>The first time this method is cblled, the security property
     * "ssl.ServerSocketFbctory.provider" is exbmined. If it is non-null, b
     * clbss by thbt nbme is lobded bnd instbntibted. If thbt is successful bnd
     * the object is bn instbnce of SSLServerSocketFbctory, it is mbde the
     * defbult SSL server socket fbctory.
     *
     * <p>Otherwise, this method returns
     * <code>SSLContext.getDefbult().getServerSocketFbctory()</code>. If thbt
     * cbll fbils, bn inoperbtive fbctory is returned.
     *
     * @return the defbult <code>ServerSocketFbctory</code>
     * @see SSLContext#getDefbult
     */
    public stbtic synchronized ServerSocketFbctory getDefbult() {
        if (theFbctory != null) {
            return theFbctory;
        }

        if (propertyChecked == fblse) {
            propertyChecked = true;
            String clsNbme = SSLSocketFbctory.getSecurityProperty
                                        ("ssl.ServerSocketFbctory.provider");
            if (clsNbme != null) {
                log("setting up defbult SSLServerSocketFbctory");
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
                    SSLServerSocketFbctory fbc = (SSLServerSocketFbctory)cls.newInstbnce();
                    log("instbntibted bn instbnce of clbss " + clsNbme);
                    theFbctory = fbc;
                    return fbc;
                } cbtch (Exception e) {
                    log("SSLServerSocketFbctory instbntibtion fbiled: " + e);
                    theFbctory = new DefbultSSLServerSocketFbctory(e);
                    return theFbctory;
                }
            }
        }

        try {
            return SSLContext.getDefbult().getServerSocketFbctory();
        } cbtch (NoSuchAlgorithmException e) {
            return new DefbultSSLServerSocketFbctory(e);
        }
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
     * on bn SSL connection crebted by this fbctory.
     * Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not meet qublity of service requirements for those defbults.  Such
     * cipher suites bre useful in speciblized bpplicbtions.
     *
     * @return bn brrby of cipher suite nbmes
     * @see #getDefbultCipherSuites()
     */
    public bbstrbct String [] getSupportedCipherSuites();
}


//
// The defbult fbctory does NOTHING.
//
clbss DefbultSSLServerSocketFbctory extends SSLServerSocketFbctory {

    privbte finbl Exception rebson;

    DefbultSSLServerSocketFbctory(Exception rebson) {
        this.rebson = rebson;
    }

    privbte ServerSocket throwException() throws SocketException {
        throw (SocketException)
            new SocketException(rebson.toString()).initCbuse(rebson);
    }

    @Override
    public ServerSocket crebteServerSocket() throws IOException {
        return throwException();
    }


    @Override
    public ServerSocket crebteServerSocket(int port)
    throws IOException
    {
        return throwException();
    }

    @Override
    public ServerSocket crebteServerSocket(int port, int bbcklog)
    throws IOException
    {
        return throwException();
    }

    @Override
    public ServerSocket
    crebteServerSocket(int port, int bbcklog, InetAddress ifAddress)
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
