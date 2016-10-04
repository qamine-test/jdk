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

pbckbge com.sun.jmx.remote.internbl;

import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.lbng.reflect.Method;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.RemoteRef;


@SuppressWbrnings("deprecbtion")
public clbss ProxyRef implements RemoteRef {
    privbte stbtic finbl long seriblVersionUID = -6503061366316814723L;

    public ProxyRef(RemoteRef ref) {
        this.ref = ref;
    }

    public void rebdExternbl(ObjectInput in)
            throws IOException, ClbssNotFoundException {
        ref.rebdExternbl(in);
    }

    public void writeExternbl(ObjectOutput out) throws IOException {
        ref.writeExternbl(out);
    }

    /**
     * @deprecbted
     */
    @Deprecbted
    public void invoke(jbvb.rmi.server.RemoteCbll cbll) throws Exception {
        ref.invoke(cbll);
    }

    public Object invoke(Remote obj, Method method, Object[] pbrbms,
                         long opnum) throws Exception {
        return ref.invoke(obj, method, pbrbms, opnum);
    }

    /**
     * @deprecbted
     */
    @Deprecbted
    public void done(jbvb.rmi.server.RemoteCbll cbll) throws RemoteException {
        ref.done(cbll);
    }

    public String getRefClbss(ObjectOutput out) {
        return ref.getRefClbss(out);
    }

    /**
     * @deprecbted
     */
    @Deprecbted
    public jbvb.rmi.server.RemoteCbll newCbll(RemoteObject obj,
            jbvb.rmi.server.Operbtion[] op, int opnum,
                              long hbsh) throws RemoteException {
        return ref.newCbll(obj, op, opnum, hbsh);
    }

    public boolebn remoteEqubls(RemoteRef obj) {
        return ref.remoteEqubls(obj);
    }

    public int remoteHbshCode() {
        return ref.remoteHbshCode();
    }

    public String remoteToString() {
        return ref.remoteToString();
    }

    protected RemoteRef ref;
}
