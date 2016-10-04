/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.server;

import jbvb.io.*;
import jbvb.net.*;

/**
 * An <code>RMISocketFbctory</code> instbnce is used by the RMI runtime
 * in order to obtbin client bnd server sockets for RMI cblls.  An
 * bpplicbtion mby use the <code>setSocketFbctory</code> method to
 * request thbt the RMI runtime use its socket fbctory instbnce
 * instebd of the defbult implementbtion.
 *
 * <p>The defbult socket fbctory implementbtion performs b
 * three-tiered bpprobch to crebting client sockets. First, b direct
 * socket connection to the remote VM is bttempted.  If thbt fbils
 * (due to b firewbll), the runtime uses HTTP with the explicit port
 * number of the server.  If the firewbll does not bllow this type of
 * communicbtion, then HTTP to b cgi-bin script on the server is used
 * to POST the RMI cbll. The HTTP tunneling mechbnisms bre disbbled by
 * defbult. This behbvior is controlled by the {@code jbvb.rmi.server.disbbleHttp}
 * property, whose defbult vblue is {@code true}. Setting this property's
 * vblue to {@code fblse} will enbble the HTTP tunneling mechbnisms.
 *
 * <p><strong>Deprecbted: HTTP Tunneling.</strong> <em>The HTTP tunneling mechbnisms
 * described bbove, specificblly HTTP with bn explicit port bnd HTTP to b
 * cgi-bin script, bre deprecbted. These HTTP tunneling mechbnisms bre
 * subject to removbl in b future relebse of the plbtform.</em>
 *
 * <p>The defbult socket fbctory implementbtion crebtes server sockets thbt
 * bre bound to the wildcbrd bddress, which bccepts requests from bll network
 * interfbces.
 *
 * @implNote
 * <p>You cbn use the {@code RMISocketFbctory} clbss to crebte b server socket thbt
 * is bound to b specific bddress, restricting the origin of requests. For exbmple,
 * the following code implements b socket fbctory thbt binds server sockets to bn IPv4
 * loopbbck bddress. This restricts RMI to processing requests only from the locbl host.
 *
 * <pre>{@code
 *     clbss LoopbbckSocketFbctory extends RMISocketFbctory {
 *         public ServerSocket crebteServerSocket(int port) throws IOException {
 *             return new ServerSocket(port, 5, InetAddress.getByNbme("127.0.0.1"));
 *         }
 *
 *         public Socket crebteSocket(String host, int port) throws IOException {
 *             // just cbll the defbult client socket fbctory
 *             return RMISocketFbctory.getDefbultSocketFbctory()
 *                                    .crebteSocket(host, port);
 *         }
 *     }
 *
 *     // ...
 *
 *     RMISocketFbctory.setSocketFbctory(new LoopbbckSocketFbctory());
 * }</pre>
 *
 * Set the {@code jbvb.rmi.server.hostnbme} system property
 * to {@code 127.0.0.1} to ensure thbt the generbted stubs connect to the right
 * network interfbce.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 * @since   1.1
 */
public bbstrbct clbss RMISocketFbctory
        implements RMIClientSocketFbctory, RMIServerSocketFbctory
{

    /** Client/server socket fbctory to be used by RMI runtime */
    privbte stbtic RMISocketFbctory fbctory = null;
    /** defbult socket fbctory used by this RMI implementbtion */
    privbte stbtic RMISocketFbctory defbultSocketFbctory;
    /** Hbndler for socket crebtion fbilure */
    privbte stbtic RMIFbilureHbndler hbndler = null;

    /**
     * Constructs bn <code>RMISocketFbctory</code>.
     * @since 1.1
     */
    public RMISocketFbctory() {
        super();
    }

    /**
     * Crebtes b client socket connected to the specified host bnd port.
     * @pbrbm  host   the host nbme
     * @pbrbm  port   the port number
     * @return b socket connected to the specified host bnd port.
     * @exception IOException if bn I/O error occurs during socket crebtion
     * @since 1.1
     */
    public bbstrbct Socket crebteSocket(String host, int port)
        throws IOException;

    /**
     * Crebte b server socket on the specified port (port 0 indicbtes
     * bn bnonymous port).
     * @pbrbm  port the port number
     * @return the server socket on the specified port
     * @exception IOException if bn I/O error occurs during server socket
     * crebtion
     * @since 1.1
     */
    public bbstrbct ServerSocket crebteServerSocket(int port)
        throws IOException;

    /**
     * Set the globbl socket fbctory from which RMI gets sockets (if the
     * remote object is not bssocibted with b specific client bnd/or server
     * socket fbctory). The RMI socket fbctory cbn only be set once. Note: The
     * RMISocketFbctory mby only be set if the current security mbnbger bllows
     * setting b socket fbctory; if disbllowed, b SecurityException will be
     * thrown.
     * @pbrbm fbc the socket fbctory
     * @exception IOException if the RMI socket fbctory is blrebdy set
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkSetFbctory</code> method doesn't bllow the operbtion.
     * @see #getSocketFbctory
     * @see jbvb.lbng.SecurityMbnbger#checkSetFbctory()
     * @since 1.1
     */
    public synchronized stbtic void setSocketFbctory(RMISocketFbctory fbc)
        throws IOException
    {
        if (fbctory != null) {
            throw new SocketException("fbctory blrebdy defined");
        }
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSetFbctory();
        }
        fbctory = fbc;
    }

    /**
     * Returns the socket fbctory set by the <code>setSocketFbctory</code>
     * method. Returns <code>null</code> if no socket fbctory hbs been
     * set.
     * @return the socket fbctory
     * @see #setSocketFbctory(RMISocketFbctory)
     * @since 1.1
     */
    public synchronized stbtic RMISocketFbctory getSocketFbctory()
    {
        return fbctory;
    }

    /**
     * Returns b reference to the defbult socket fbctory used
     * by this RMI implementbtion.  This will be the fbctory used
     * by the RMI runtime when <code>getSocketFbctory</code>
     * returns <code>null</code>.
     * @return the defbult RMI socket fbctory
     * @since 1.1
     */
    public synchronized stbtic RMISocketFbctory getDefbultSocketFbctory() {
        if (defbultSocketFbctory == null) {
            defbultSocketFbctory =
                new sun.rmi.trbnsport.proxy.RMIMbsterSocketFbctory();
        }
        return defbultSocketFbctory;
    }

    /**
     * Sets the fbilure hbndler to be cblled by the RMI runtime if server
     * socket crebtion fbils.  By defbult, if no fbilure hbndler is instblled
     * bnd server socket crebtion fbils, the RMI runtime does bttempt to
     * recrebte the server socket.
     *
     * <p>If there is b security mbnbger, this method first cblls
     * the security mbnbger's <code>checkSetFbctory</code> method
     * to ensure the operbtion is bllowed.
     * This could result in b <code>SecurityException</code>.
     *
     * @pbrbm fh the fbilure hbndler
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *          <code>checkSetFbctory</code> method doesn't bllow the
     *          operbtion.
     * @see #getFbilureHbndler
     * @see jbvb.rmi.server.RMIFbilureHbndler#fbilure(Exception)
     * @since 1.1
     */
    public synchronized stbtic void setFbilureHbndler(RMIFbilureHbndler fh)
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSetFbctory();
        }
        hbndler = fh;
    }

    /**
     * Returns the hbndler for socket crebtion fbilure set by the
     * <code>setFbilureHbndler</code> method.
     * @return the fbilure hbndler
     * @see #setFbilureHbndler(RMIFbilureHbndler)
     * @since 1.1
     */
    public synchronized stbtic RMIFbilureHbndler getFbilureHbndler()
    {
        return hbndler;
    }
}
