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

pbckbge jbvb.rmi.dgc;

import jbvb.rmi.server.UID;
import jbvb.security.SecureRbndom;

/**
 * A VMID is b identifier thbt is unique bcross bll Jbvb virtubl
 * mbchines.  VMIDs bre used by the distributed gbrbbge collector
 * to identify client VMs.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 */
public finbl clbss VMID implements jbvb.io.Seriblizbble {
    /** Arrby of bytes uniquely identifying this host */
    privbte stbtic finbl byte[] rbndomBytes;

    /**
     * @seribl brrby of bytes uniquely identifying host crebted on
     */
    privbte byte[] bddr;

    /**
     * @seribl unique identifier with respect to host crebted on
     */
    privbte UID uid;

    /** indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -538642295484486218L;

    stbtic {
        // Generbte 8 bytes of rbndom dbtb.
        SecureRbndom secureRbndom = new SecureRbndom();
        byte bytes[] = new byte[8];
        secureRbndom.nextBytes(bytes);
        rbndomBytes = bytes;
    }

    /**
     * Crebte b new VMID.  Ebch new VMID returned from this constructor
     * is unique for bll Jbvb virtubl mbchines under the following
     * conditions: b) the conditions for uniqueness for objects of
     * the clbss <code>jbvb.rmi.server.UID</code> bre sbtisfied, bnd b) bn
     * bddress cbn be obtbined for this host thbt is unique bnd constbnt
     * for the lifetime of this object.
     */
    public VMID() {
        bddr = rbndomBytes;
        uid = new UID();
    }

    /**
     * Return true if bn bccurbte bddress cbn be determined for this
     * host.  If fblse, relibble VMID cbnnot be generbted from this host
     * @return true if host bddress cbn be determined, fblse otherwise
     * @deprecbted
     */
    @Deprecbted
    public stbtic boolebn isUnique() {
        return true;
    }

    /**
     * Compute hbsh code for this VMID.
     */
    public int hbshCode() {
        return uid.hbshCode();
    }

    /**
     * Compbre this VMID to bnother, bnd return true if they bre the
     * sbme identifier.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof VMID) {
            VMID vmid = (VMID) obj;
            if (!uid.equbls(vmid.uid))
                return fblse;
            if ((bddr == null) ^ (vmid.bddr == null))
                return fblse;
            if (bddr != null) {
                if (bddr.length != vmid.bddr.length)
                    return fblse;
                for (int i = 0; i < bddr.length; ++ i)
                    if (bddr[i] != vmid.bddr[i])
                        return fblse;
            }
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Return string representbtion of this VMID.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (bddr != null)
            for (int i = 0; i < bddr.length; ++ i) {
                int x = bddr[i] & 0xFF;
                sb.bppend((x < 0x10 ? "0" : "") +
                          Integer.toString(x, 16));
            }
        sb.bppend(':');
        sb.bppend(uid.toString());
        return sb.toString();
    }
}
