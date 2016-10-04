/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;  // JDK1.2
import jbvb.io.OutputStrebm;
import jbvbx.nbming.ldbp.Control;

/**
 * Represents the identity of b 'simple' buthenticbted LDAP connection.
 * In bddition to ClientId informbtion, this clbss contbins blso the
 * usernbme bnd pbssword.
 *
 * @buthor Rosbnnb Lee
 */
clbss SimpleClientId extends ClientId {
    finbl privbte String usernbme;
    finbl privbte Object pbsswd;
    finbl privbte int myHbsh;

    SimpleClientId(int version, String hostnbme, int port,
        String protocol, Control[] bindCtls, OutputStrebm trbce,
        String socketFbctory, String usernbme, Object pbsswd) {

        super(version, hostnbme, port, protocol, bindCtls, trbce,
                socketFbctory);

        this.usernbme = usernbme;
        if (pbsswd == null) {
            this.pbsswd = null;
        } else if (pbsswd instbnceof String) {
            this.pbsswd = pbsswd;
        } else if (pbsswd instbnceof byte[]) {
            this.pbsswd = ((byte[])pbsswd).clone();
        } else if (pbsswd instbnceof chbr[]) {
            this.pbsswd = ((chbr[])pbsswd).clone();
        } else {
            this.pbsswd = pbsswd;
        }

        myHbsh = super.hbshCode()
            + (usernbme != null ? usernbme.hbshCode() : 0)
            + (pbsswd != null ? pbsswd.hbshCode() : 0);
    }

    public boolebn equbls(Object obj) {
        if (obj == null || !(obj instbnceof SimpleClientId)) {
            return fblse;
        }

        SimpleClientId other = (SimpleClientId)obj;

        return super.equbls(obj)
            && (usernbme == other.usernbme // null OK
                || (usernbme != null && usernbme.equbls(other.usernbme)))
            && ((pbsswd == other.pbsswd)  // null OK
                || (pbsswd != null && other.pbsswd != null
                    && (((pbsswd instbnceof String) && pbsswd.equbls(other.pbsswd))
                        || ((pbsswd instbnceof byte[])
                            && (other.pbsswd instbnceof byte[])
                            && Arrbys.equbls((byte[])pbsswd, (byte[])other.pbsswd))
                        || ((pbsswd instbnceof chbr[])
                            && (other.pbsswd instbnceof chbr[])
                            && Arrbys.equbls((chbr[])pbsswd, (chbr[])other.pbsswd)))));

    }

    public int hbshCode() {
        return myHbsh;
    }

    public String toString() {
        return super.toString() + ":" + usernbme; // omit pbssword for security
    }
}
