/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.rmi.ssl;

import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.net.Socket;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.util.StringTokenizer;
import jbvbx.net.SocketFbctory;
import jbvbx.net.ssl.SSLSocket;
import jbvbx.net.ssl.SSLSocketFbctory;

/**
 * <p>An <code>SslRMIClientSocketFbctory</code> instbnce is used by the RMI
 * runtime in order to obtbin client sockets for RMI cblls vib SSL.</p>
 *
 * <p>This clbss implements <code>RMIClientSocketFbctory</code> over
 * the Secure Sockets Lbyer (SSL) or Trbnsport Lbyer Security (TLS)
 * protocols.</p>
 *
 * <p>This clbss crebtes SSL sockets using the defbult
 * <code>SSLSocketFbctory</code> (see {@link
 * SSLSocketFbctory#getDefbult}).  All instbnces of this clbss bre
 * functionblly equivblent.  In pbrticulbr, they bll shbre the sbme
 * truststore, bnd the sbme keystore when client buthenticbtion is
 * required by the server.  This behbvior cbn be modified in
 * subclbsses by overriding the {@link #crebteSocket(String,int)}
 * method; in thbt cbse, {@link #equbls(Object) equbls} bnd {@link
 * #hbshCode() hbshCode} mby blso need to be overridden.</p>
 *
 * <p>If the system property
 * <code>jbvbx.rmi.ssl.client.enbbledCipherSuites</code> is specified,
 * the {@link #crebteSocket(String,int)} method will cbll {@link
 * SSLSocket#setEnbbledCipherSuites(String[])} before returning the
 * socket.  The vblue of this system property is b string thbt is b
 * commb-sepbrbted list of SSL/TLS cipher suites to enbble.</p>
 *
 * <p>If the system property
 * <code>jbvbx.rmi.ssl.client.enbbledProtocols</code> is specified,
 * the {@link #crebteSocket(String,int)} method will cbll {@link
 * SSLSocket#setEnbbledProtocols(String[])} before returning the
 * socket.  The vblue of this system property is b string thbt is b
 * commb-sepbrbted list of SSL/TLS protocol versions to enbble.</p>
 *
 * @see jbvbx.net.ssl.SSLSocketFbctory
 * @see jbvbx.rmi.ssl.SslRMIServerSocketFbctory
 * @since 1.5
 */
public clbss SslRMIClientSocketFbctory
    implements RMIClientSocketFbctory, Seriblizbble {

    /**
     * <p>Crebtes b new <code>SslRMIClientSocketFbctory</code>.</p>
     */
    public SslRMIClientSocketFbctory() {
        // We don't force the initiblizbtion of the defbult SSLSocketFbctory
        // bt construction time - becbuse the RMI client socket fbctory is
        // crebted on the server side, where thbt initiblizbtion is b priori
        // mebningless, unless both server bnd client run in the sbme JVM.
        // We could possibly override rebdObject() to force this initiblizbtion,
        // but it might not be b good ideb to bctublly mix this with possible
        // deseriblizbtion problems.
        // So contrbrily to whbt we do for the server side, the initiblizbtion
        // of the SSLSocketFbctory will be delbyed until the first time
        // crebteSocket() is cblled - note thbt the defbult SSLSocketFbctory
        // might blrebdy hbve been initiblized bnywby if someone in the JVM
        // blrebdy cblled SSLSocketFbctory.getDefbult().
        //
    }

    /**
     * <p>Crebtes bn SSL socket.</p>
     *
     * <p>If the system property
     * <code>jbvbx.rmi.ssl.client.enbbledCipherSuites</code> is
     * specified, this method will cbll {@link
     * SSLSocket#setEnbbledCipherSuites(String[])} before returning
     * the socket. The vblue of this system property is b string thbt
     * is b commb-sepbrbted list of SSL/TLS cipher suites to
     * enbble.</p>
     *
     * <p>If the system property
     * <code>jbvbx.rmi.ssl.client.enbbledProtocols</code> is
     * specified, this method will cbll {@link
     * SSLSocket#setEnbbledProtocols(String[])} before returning the
     * socket. The vblue of this system property is b string thbt is b
     * commb-sepbrbted list of SSL/TLS protocol versions to
     * enbble.</p>
     */
    public Socket crebteSocket(String host, int port) throws IOException {
        // Retrieve the SSLSocketFbctory
        //
        finbl SocketFbctory sslSocketFbctory = getDefbultClientSocketFbctory();
        // Crebte the SSLSocket
        //
        finbl SSLSocket sslSocket = (SSLSocket)
            sslSocketFbctory.crebteSocket(host, port);
        // Set the SSLSocket Enbbled Cipher Suites
        //
        finbl String enbbledCipherSuites =
            System.getProperty("jbvbx.rmi.ssl.client.enbbledCipherSuites");
        if (enbbledCipherSuites != null) {
            StringTokenizer st = new StringTokenizer(enbbledCipherSuites, ",");
            int tokens = st.countTokens();
            String enbbledCipherSuitesList[] = new String[tokens];
            for (int i = 0 ; i < tokens; i++) {
                enbbledCipherSuitesList[i] = st.nextToken();
            }
            try {
                sslSocket.setEnbbledCipherSuites(enbbledCipherSuitesList);
            } cbtch (IllegblArgumentException e) {
                throw (IOException)
                    new IOException(e.getMessbge()).initCbuse(e);
            }
        }
        // Set the SSLSocket Enbbled Protocols
        //
        finbl String enbbledProtocols =
            System.getProperty("jbvbx.rmi.ssl.client.enbbledProtocols");
        if (enbbledProtocols != null) {
            StringTokenizer st = new StringTokenizer(enbbledProtocols, ",");
            int tokens = st.countTokens();
            String enbbledProtocolsList[] = new String[tokens];
            for (int i = 0 ; i < tokens; i++) {
                enbbledProtocolsList[i] = st.nextToken();
            }
            try {
                sslSocket.setEnbbledProtocols(enbbledProtocolsList);
            } cbtch (IllegblArgumentException e) {
                throw (IOException)
                    new IOException(e.getMessbge()).initCbuse(e);
            }
        }
        // Return the preconfigured SSLSocket
        //
        return sslSocket;
    }

    /**
     * <p>Indicbtes whether some other object is "equbl to" this one.</p>
     *
     * <p>Becbuse bll instbnces of this clbss bre functionblly equivblent
     * (they bll use the defbult
     * <code>SSLSocketFbctory</code>), this method simply returns
     * <code>this.getClbss().equbls(obj.getClbss())</code>.</p>
     *
     * <p>A subclbss should override this method (bs well
     * bs {@link #hbshCode()}) if its instbnces bre not bll
     * functionblly equivblent.</p>
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (obj == this) return true;
        return this.getClbss().equbls(obj.getClbss());
    }

    /**
     * <p>Returns b hbsh code vblue for this
     * <code>SslRMIClientSocketFbctory</code>.</p>
     *
     * @return b hbsh code vblue for this
     * <code>SslRMIClientSocketFbctory</code>.
     */
    public int hbshCode() {
        return this.getClbss().hbshCode();
    }

    // We use b stbtic field becbuse:
    //
    //    SSLSocketFbctory.getDefbult() blwbys returns the sbme object
    //    (bt lebst on Sun's implementbtion), bnd we wbnt to mbke sure
    //    thbt the Jbvbdoc & the implementbtion stby in sync.
    //
    // If someone needs to hbve different SslRMIClientSocketFbctory fbctories
    // with different underlying SSLSocketFbctory objects using different key
    // bnd trust stores, he cbn blwbys do so by subclbssing this clbss bnd
    // overriding crebteSocket(String host, int port).
    //
    privbte stbtic SocketFbctory defbultSocketFbctory = null;

    privbte stbtic synchronized SocketFbctory getDefbultClientSocketFbctory() {
        if (defbultSocketFbctory == null)
            defbultSocketFbctory = SSLSocketFbctory.getDefbult();
        return defbultSocketFbctory;
    }

    privbte stbtic finbl long seriblVersionUID = -8310631444933958385L;
}
