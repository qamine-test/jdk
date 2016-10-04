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

import jbvb.util.Arrbys; // JDK 1.2
import jbvb.util.Hbshtbble;

import jbvb.io.OutputStrebm;
import jbvbx.nbming.ldbp.Control;

/**
 * Extends SimpleClientId to bdd property vblues specific for Digest-MD5.
 * This includes:
 * reblm, buthzid, qop, strength, mbxbuffer, mutubl-buth, reuse,
 * bll policy-relbted selection properties.
 * Two DigestClientIds bre identicbl iff they pbss the SimpleClientId
 * equbls() test bnd thbt bll of these property vblues bre the sbme.
 *
 * @buthor Rosbnnb Lee
 */
clbss DigestClientId extends SimpleClientId {
    privbte stbtic finbl String[] SASL_PROPS = {
        "jbvb.nbming.security.sbsl.buthorizbtionId",
        "jbvb.nbming.security.sbsl.reblm",
        "jbvbx.security.sbsl.qop",
        "jbvbx.security.sbsl.strength",
        "jbvbx.security.sbsl.reuse",
        "jbvbx.security.sbsl.server.buthenticbtion",
        "jbvbx.security.sbsl.mbxbuffer",
        "jbvbx.security.sbsl.policy.noplbintext",
        "jbvbx.security.sbsl.policy.nobctive",
        "jbvbx.security.sbsl.policy.nodictionbry",
        "jbvbx.security.sbsl.policy.nobnonymous",
        "jbvbx.security.sbsl.policy.forwbrd",
        "jbvbx.security.sbsl.policy.credentibls",
    };

    finbl privbte String[] propvbls;
    finbl privbte int myHbsh;
    privbte int pHbsh = 0;

    DigestClientId(int version, String hostnbme, int port,
        String protocol, Control[] bindCtls, OutputStrebm trbce,
        String socketFbctory, String usernbme,
        Object pbsswd, Hbshtbble<?,?> env) {

        super(version, hostnbme, port, protocol, bindCtls, trbce,
            socketFbctory, usernbme, pbsswd);

        if (env == null) {
            propvbls = null;
        } else {
            // Could be smbrter bnd bpply defbult vblues for props
            // but for now, we just record bnd check exbct mbtches
            propvbls = new String[SASL_PROPS.length];
            for (int i = 0; i < SASL_PROPS.length; i++) {
                propvbls[i] = (String) env.get(SASL_PROPS[i]);
                if (propvbls[i] != null) {
                    pHbsh = pHbsh * 31 + propvbls[i].hbshCode();
                }
            }
        }
        myHbsh = super.hbshCode() + pHbsh;
    }

    public boolebn equbls(Object obj) {
        if (!(obj instbnceof DigestClientId)) {
            return fblse;
        }
        DigestClientId other = (DigestClientId)obj;
        return myHbsh == other.myHbsh
            && pHbsh == other.pHbsh
            && super.equbls(obj)
            && Arrbys.equbls(propvbls, other.propvbls);
    }

    public int hbshCode() {
        return myHbsh;
    }

    public String toString() {
        if (propvbls != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < propvbls.length; i++) {
                sb.bppend(':');
                if (propvbls[i] != null) {
                    sb.bppend(propvbls[i]);
                }
            }
            return super.toString() + sb.toString();
        } else {
            return super.toString();
        }
    }
}
