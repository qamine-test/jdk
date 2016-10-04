/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.server;

import jbvb.io.IOException;
import jbvb.io.NotSeriblizbbleException;
import jbvb.io.ObjectOutput;
import jbvb.rmi.*;
import jbvb.rmi.server.*;
import jbvb.rmi.bctivbtion.ActivbtionID;
import sun.rmi.trbnsport.LiveRef;

/**
 * Server-side ref for b persistent remote impl.
 *
 * @buthor Ann Wollrbth
 */
public clbss ActivbtbbleServerRef extends UnicbstServerRef2 {

    privbte stbtic finbl long seriblVersionUID = 2002967993223003793L;

    privbte ActivbtionID id;

    /**
     * Construct b Unicbst server remote reference to be exported
     * on the specified port.
     */
    public ActivbtbbleServerRef(ActivbtionID id, int port)
    {
        this(id, port, null, null);
    }

    /**
     * Construct b Unicbst server remote reference to be exported
     * on the specified port.
     */
    public ActivbtbbleServerRef(ActivbtionID id, int port,
                                RMIClientSocketFbctory csf,
                                RMIServerSocketFbctory ssf)
    {
        super(new LiveRef(port, csf, ssf));
        this.id = id;
    }

    /**
     * Returns the clbss of the ref type to be seriblized
     */
    public String getRefClbss(ObjectOutput out)
    {
        return "ActivbtbbleServerRef";
    }

    /**
     * Return the client remote reference for this remoteRef.
     * In the cbse of b client RemoteRef "this" is the bnswer.
     * For  b server remote reference, b client side one will hbve to
     * found or crebted.
     */
    protected RemoteRef getClientRef() {
        return new ActivbtbbleRef(id, new UnicbstRef2(ref));
    }

    /**
     * Prevents seriblizbtion (becbuse deseriblizbion is impossible).
     */
    public void writeExternbl(ObjectOutput out) throws IOException {
        throw new NotSeriblizbbleException(
            "ActivbtbbleServerRef not seriblizbble");
    }
}
