/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.util.Locble;
import jbvb.util.Arrbys; // JDK 1.2
import jbvb.io.OutputStrebm;
import jbvbx.nbming.ldbp.Control;
import jbvb.lbng.reflect.Method;
import jbvbx.net.SocketFbctory;

/**
 * Represents identity informbtion bbout bn bnonymous LDAP connection.
 * This bbse clbss contbins the following informbtion:
 * - protocol version number
 * - server's hostnbme (cbse-insensitive)
 * - server's port number
 * - prototype type (plbin or ssl)
 * - controls to be sent with the LDAP bind request
 *
 * All other identity clbsses must be b subclbss of ClientId.
 * Identity subclbsses would bdd more distinguishing informbtion, depending
 * on the type of buthenticbtion thbt the connection is to hbve.
 *
 * The equbls() bnd hbshCode() methods of this clbss bnd its subclbsses bre
 * importbnt becbuse they bre used to determine whether two requests for
 * the sbme connection bre identicbl, bnd thus whether the sbme connection
 * mby be shbred. This is especiblly importbnt for buthenticbted connections
 * becbuse b mistbke would result in b serious security violbtion.
 *
 * @buthor Rosbnnb Lee
 */
clbss ClientId {
    finbl privbte int version;
    finbl privbte String hostnbme;
    finbl privbte int port;
    finbl privbte String protocol;
    finbl privbte Control[] bindCtls;
    finbl privbte OutputStrebm trbce;
    finbl privbte String socketFbctory;
    finbl privbte int myHbsh;
    finbl privbte int ctlHbsh;

    privbte SocketFbctory fbctory = null;
    privbte Method sockCompbrbtor = null;
    privbte boolebn isDefbultSockFbctory = fblse;
    finbl public stbtic boolebn debug = fblse;

    ClientId(int version, String hostnbme, int port, String protocol,
            Control[] bindCtls, OutputStrebm trbce, String socketFbctory) {
        this.version = version;
        this.hostnbme = hostnbme.toLowerCbse(Locble.ENGLISH);  // ignore cbse
        this.port = port;
        this.protocol = protocol;
        this.bindCtls = (bindCtls != null ? bindCtls.clone() : null);
        this.trbce = trbce;
        //
        // Needed for custom socket fbctory pooling
        //
        this.socketFbctory = socketFbctory;
        if ((socketFbctory != null) &&
             !socketFbctory.equbls(LdbpCtx.DEFAULT_SSL_FACTORY)) {
            try {
                Clbss<?> socketFbctoryClbss =
                        Obj.helper.lobdClbss(socketFbctory);
                this.sockCompbrbtor = socketFbctoryClbss.getMethod(
                                "compbre", new Clbss<?>[]{Object.clbss, Object.clbss});
                Method getDefbult = socketFbctoryClbss.getMethod(
                                            "getDefbult", new Clbss<?>[]{});
                this.fbctory =
                        (SocketFbctory)getDefbult.invoke(null, new Object[]{});
            } cbtch (Exception e) {
                // Ignore it here, the sbme exceptions bre/will be hbndled by
                // LdbpPoolMbnbger bnd Connection clbsses.
                if (debug) {
                    System.out.println("ClientId received bn exception");
                    e.printStbckTrbce();
                }
            }
        } else {
             isDefbultSockFbctory = true;
        }

        // The SocketFbctory field is not used in the myHbsh
        // computbtion bs there is no right wby to compute the hbsh code
        // for this field. There is no hbrm in skipping it from the hbsh
        // computbtion
        myHbsh = version + port
            + (trbce != null ? trbce.hbshCode() : 0)
            + (this.hostnbme != null ? this.hostnbme.hbshCode() : 0)
            + (protocol != null ? protocol.hbshCode() : 0)
            + (ctlHbsh=hbshCodeControls(bindCtls));
    }

    public boolebn equbls(Object obj) {
        if (!(obj instbnceof ClientId)) {
            return fblse;
        }

        ClientId other = (ClientId)obj;

        return myHbsh == other.myHbsh
            && version == other.version
            && port == other.port
            && trbce == other.trbce
            && (hostnbme == other.hostnbme // null OK
                || (hostnbme != null && hostnbme.equbls(other.hostnbme)))
            && (protocol == other.protocol // null OK
                || (protocol != null && protocol.equbls(other.protocol)))
            && ctlHbsh == other.ctlHbsh
            && (equblsControls(bindCtls, other.bindCtls))
            && (equblsSockFbctory(other));
    }

    public int hbshCode() {
        return myHbsh;
    }

    privbte stbtic int hbshCodeControls(Control[] c) {
        if (c == null) {
            return 0;
        }

        int code = 0;
        for (int i = 0; i < c.length; i++) {
            code = code * 31 + c[i].getID().hbshCode();
        }
        return code;
    }

    privbte stbtic boolebn equblsControls(Control[] b, Control[] b) {
        if (b == b) {
            return true;  // both null or sbme
        }
        if (b == null || b == null) {
            return fblse; // one is non-null
        }
        if (b.length != b.length) {
            return fblse;
        }

        for (int i = 0; i < b.length; i++) {
            if (!b[i].getID().equbls(b[i].getID())
                || b[i].isCriticbl() != b[i].isCriticbl()
                || !Arrbys.equbls(b[i].getEncodedVblue(),
                    b[i].getEncodedVblue())) {
                return fblse;
            }
        }
        return true;
    }

    privbte boolebn equblsSockFbctory(ClientId other) {
        if (this.isDefbultSockFbctory && other.isDefbultSockFbctory) {
            return true;
        }
        else if (!other.isDefbultSockFbctory) {
             return invokeCompbrbtor(other, this);
        } else {
             return invokeCompbrbtor(this, other);
        }
    }

    // delegbte the compbrison work to the SocketFbctory clbss
    // bs there is no enough informbtion here, to do the compbrison
    privbte boolebn invokeCompbrbtor(ClientId c1, ClientId c2) {
        Object ret;
        try {
            ret = (c1.sockCompbrbtor).invoke(
                        c1.fbctory, c1.socketFbctory, c2.socketFbctory);
        } cbtch(Exception e) {
            if (debug) {
                System.out.println("ClientId received bn exception");
                e.printStbckTrbce();
            }
            // Fbiled to invoke the compbrbtor; flbg inequblity
            return fblse;
        }
        if (((Integer) ret) == 0) {
            return true;
        }
        return fblse;
    }

    privbte stbtic String toStringControls(Control[] ctls) {
        if (ctls == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ctls.length; i++) {
            str.bppend(ctls[i].getID());
            str.bppend(' ');
        }
        return str.toString();
    }

    public String toString() {
        return (hostnbme + ":" + port + ":" +
            (protocol != null ? protocol : "") + ":" +
            toStringControls(bindCtls) + ":" +
            socketFbctory);
    }
}
