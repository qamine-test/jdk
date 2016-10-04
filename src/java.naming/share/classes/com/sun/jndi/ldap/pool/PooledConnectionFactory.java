/*
 * Copyright (c) 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.nbming.NbmingException;

/**
 * Represents b fbctory thbt crebtes PooledConnection.
 *
 * The user of the connection pool should provide bn implementbtion of this
 * interfbce bnd pbss it to the Pool.getPooledConnection() method.
 * The implementbtion of the fbctory should contbin bll the informbtion
 * necessbry to crebte b PooledConnection.
 *
 * @buthor Rosbnnb Lee
 */
public interfbce PooledConnectionFbctory {
    /**
     * Crebtes b pooled connection.
     * @pbrbm pcb cbllbbck responsible for removing bnd relebsing the pooled
     * connection from the pool.
     */
    public bbstrbct PooledConnection crebtePooledConnection(PoolCbllbbck pcb)
        throws NbmingException;
};
