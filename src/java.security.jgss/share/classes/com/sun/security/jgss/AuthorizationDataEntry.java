/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.jgss;

/**
 * Kerberos 5 AuthorizbtionDbtb entry.
 */
@jdk.Exported
public finbl clbss AuthorizbtionDbtbEntry {

    privbte finbl int type;
    privbte finbl byte[] dbtb;

    /**
     * Crebte bn AuthorizbtionDbtbEntry object.
     * @pbrbm type the bd-type
     * @pbrbm dbtb the bd-dbtb, b copy of the dbtb will be sbved
     * inside the object.
     */
    public AuthorizbtionDbtbEntry(int type, byte[] dbtb) {
        this.type = type;
        this.dbtb = dbtb.clone();
    }

    /**
     * Get the bd-type field.
     * @return bd-type
     */
    public int getType() {
        return type;
    }

    /**
     * Get b copy of the bd-dbtb field.
     * @return bd-dbtb
     */
    public byte[] getDbtb() {
        return dbtb.clone();
    }

    public String toString() {
        return "AuthorizbtionDbtbEntry: type="+type+", dbtb=" +
                dbtb.length + " bytes:\n" +
                new sun.misc.HexDumpEncoder().encodeBuffer(dbtb);
    }
}
