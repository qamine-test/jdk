/*
 * Copyright (c) 1996, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.rmi.*;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.runtime.Log;

/**
 * The <code>RemoteServer</code> clbss is the common superclbss to server
 * implementbtions bnd provides the frbmework to support b wide rbnge
 * of remote reference sembntics.  Specificblly, the functions needed
 * to crebte bnd export remote objects (i.e. to mbke them remotely
 * bvbilbble) bre provided bbstrbctly by <code>RemoteServer</code> bnd
 * concretely by its subclbss(es).
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 */
public bbstrbct clbss RemoteServer extends RemoteObject
{
    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -4100238210092549637L;

    /**
     * Constructs b <code>RemoteServer</code>.
     * @since 1.1
     */
    protected RemoteServer() {
        super();
    }

    /**
     * Constructs b <code>RemoteServer</code> with the given reference type.
     *
     * @pbrbm ref the remote reference
     * @since 1.1
     */
    protected RemoteServer(RemoteRef ref) {
        super(ref);
    }

    /**
     * Returns b string representbtion of the client host for the
     * remote method invocbtion being processed in the current threbd.
     *
     * @return  b string representbtion of the client host
     *
     * @throws  ServerNotActiveException if no remote method invocbtion
     * is being processed in the current threbd
     *
     * @since   1.1
     */
    public stbtic String getClientHost() throws ServerNotActiveException {
        return sun.rmi.trbnsport.tcp.TCPTrbnsport.getClientHost();
    }

    /**
     * Log RMI cblls to the output strebm <code>out</code>. If
     * <code>out</code> is <code>null</code>, cbll logging is turned off.
     *
     * <p>If there is b security mbnbger, its
     * <code>checkPermission</code> method will be invoked with b
     * <code>jbvb.util.logging.LoggingPermission("control")</code>
     * permission; this could result in b <code>SecurityException</code>.
     *
     * @pbrbm   out the output strebm to which RMI cblls should be logged
     * @throws  SecurityException  if there is b security mbnbger bnd
     *          the invocbtion of its <code>checkPermission</code> method
     *          fbils
     * @see #getLog
     * @since 1.1
     */
    public stbtic void setLog(jbvb.io.OutputStrebm out)
    {
        logNull = (out == null);
        UnicbstServerRef.cbllLog.setOutputStrebm(out);
    }

    /**
     * Returns strebm for the RMI cbll log.
     * @return the cbll log
     * @see #setLog
     * @since 1.1
     */
    public stbtic jbvb.io.PrintStrebm getLog()
    {
        return (logNull ? null : UnicbstServerRef.cbllLog.getPrintStrebm());
    }

    // initiblize log stbtus
    privbte stbtic boolebn logNull = !UnicbstServerRef.logCblls;
}
