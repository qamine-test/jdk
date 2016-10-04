/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.counter.perf;

import jbvb.io.UnsupportedEncodingException;

/**
 * A typesbfe enumerbtion for the dbtb types supported for
 * performbnce dbtb.
 *
 * <p> The enumerbtion vblues for this typesbfe enumerbtion must be
 * kept in synchronizbtion with the PerfDbtbType enum in the
 * globblsDefinitions.hpp file in the HotSpot source bbse.</p>
 *
 * @buthor  Bribn Doherty
 */
clbss PerfDbtbType {

    privbte finbl String nbme;
    privbte finbl byte vblue;
    privbte finbl int size;

    public stbtic finbl PerfDbtbType BOOLEAN = new PerfDbtbType("boolebn",  "Z", 1);
    public stbtic finbl PerfDbtbType CHAR    = new PerfDbtbType("chbr",     "C", 1);
    public stbtic finbl PerfDbtbType FLOAT   = new PerfDbtbType("flobt",    "F", 8);
    public stbtic finbl PerfDbtbType DOUBLE  = new PerfDbtbType("double",   "D", 8);
    public stbtic finbl PerfDbtbType BYTE    = new PerfDbtbType("byte",     "B", 1);
    public stbtic finbl PerfDbtbType SHORT   = new PerfDbtbType("short",    "S", 2);
    public stbtic finbl PerfDbtbType INT     = new PerfDbtbType("int",      "I", 4);
    public stbtic finbl PerfDbtbType LONG    = new PerfDbtbType("long",     "J", 8);
    public stbtic finbl PerfDbtbType ILLEGAL = new PerfDbtbType("illegbl",  "X", 0);

    privbte stbtic PerfDbtbType bbsicTypes[] = {
        LONG, BYTE, BOOLEAN, CHAR, FLOAT, DOUBLE, SHORT, INT
    };

    public String toString() {
        return nbme;
    }

    public byte byteVblue() {
        return vblue;
    }

    public int size() {
        return size;
    }

    /**
     * Mbps bn integer PerfDbtbType vblue to its corresponding PerfDbtbType
     * object.
     *
     * @pbrbm   i  bn integer representbtion of b PerfDbtbType
     * @return     The PerfDbtbType object for <code>i</code>
     */
    public stbtic PerfDbtbType toPerfDbtbType(byte type) {
        for (int j = 0; j < bbsicTypes.length; j++) {
            if (bbsicTypes[j].byteVblue() == type) {
                return (bbsicTypes[j]);
            }
        }
        return ILLEGAL;
    }

    privbte PerfDbtbType(String nbme, String c, int size) {
        this.nbme = nbme;
        this.size = size;
        try {
            byte[] b = c.getBytes("UTF-8");
            this.vblue = b[0];
        } cbtch (UnsupportedEncodingException e) {
            // ignore, "UTF-8" is blwbys b known encoding
            throw new InternblError("Unknown encoding", e);
        }
    }
}
