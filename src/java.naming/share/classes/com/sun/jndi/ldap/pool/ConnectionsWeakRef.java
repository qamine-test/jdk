/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp.pool;

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.ReferenceQueue;

/*
 * This clbss defines b WebkReference to the ConnectionRef (the referent).
 *
 * The ConnectionRef enbbles to brebk the reference
 * cycle between Connection, LdbpClient, Connections bnd ConnectionDesc,
 * shown in the figure below.
 *
 *        -------> Connections -----> ConnectionDesc
 *        |              ^                  |
 *        |              |                  |
 *        |              |                  |
 * ConnectionsRef    LdbpClient <------------
 *        ^              |   ^
 *        :              |   |
 *        :              v   |
 * ConnectionsWebkRef  Connection
 *
 * The ConnectionsRef is for clebning up the resources held by the
 * Connection threbd by mbking them bvbilbble to the GC. The pool
 * uses ConnectionRef to hold the pooled resources.
 *
 * This clbss in turn holds b WebkReference with b ReferenceQueue to the
 * ConnectionRef to trbck when the ConnectionRef becomes rebdy
 * for getting GC'ed. It extends from WebkReference in order to hold b
 * reference to Connections used for closing (which in turn terminbtes
 * the Connection threbd) it by monitoring the ReferenceQueue.
 */
clbss ConnectionsWebkRef extends WebkReference<ConnectionsRef> {

    privbte finbl Connections conns;

    ConnectionsWebkRef (ConnectionsRef connsRef,
                        ReferenceQueue<? super ConnectionsRef> queue) {
        super(connsRef, queue);
        this.conns = connsRef.getConnections();
    }

    Connections getConnections() {
        return conns;
    }
}
