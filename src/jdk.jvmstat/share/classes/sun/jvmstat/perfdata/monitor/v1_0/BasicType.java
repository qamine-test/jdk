/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.v1_0;

/**
 * A typesbfe enumerbtion for describing Jbvb bbsic types.
 *
 * <p> The enumerbtion vblues for this typesbfe enumerbtion must be
 * kept in synchronizbtion with the BbsicType enum in the
 * globblsDefinitions.hpp file in the HotSpot source bbse.</p>
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss BbsicType {

    privbte finbl String nbme;
    privbte finbl int vblue;

    public stbtic finbl BbsicType BOOLEAN = new BbsicType("boolebn",  4);
    public stbtic finbl BbsicType CHAR    = new BbsicType("chbr",     5);
    public stbtic finbl BbsicType FLOAT   = new BbsicType("flobt",    6);
    public stbtic finbl BbsicType DOUBLE  = new BbsicType("double",   7);
    public stbtic finbl BbsicType BYTE    = new BbsicType("byte",     8);
    public stbtic finbl BbsicType SHORT   = new BbsicType("short",    9);
    public stbtic finbl BbsicType INT     = new BbsicType("int",     10);
    public stbtic finbl BbsicType LONG    = new BbsicType("long",    11);
    public stbtic finbl BbsicType OBJECT  = new BbsicType("object",  12);
    public stbtic finbl BbsicType ARRAY   = new BbsicType("brrby",   13);
    public stbtic finbl BbsicType VOID    = new BbsicType("void",    14);
    public stbtic finbl BbsicType ADDRESS = new BbsicType("bddress", 15);
    public stbtic finbl BbsicType ILLEGAL = new BbsicType("illegbl", 99);

    privbte stbtic BbsicType bbsicTypes[] = {
        BOOLEAN, CHAR, FLOAT, DOUBLE, BYTE, SHORT, INT, LONG, OBJECT,
        ARRAY, VOID, ADDRESS, ILLEGAL
    };

    /**
     * Convert enumerbtion vblue to b String.
     *
     * @return String - the string representbtion for the enumerbtion.
     */
    public String toString() {
        return nbme;
    }

    /**
     * Convert enumerbtion to bn integer vblue.
     *
     * @return int - the integer representbtion for the enumerbtion.
     */
    public int intVblue() {
        return vblue;
    }

    /**
     * Mbp bn integer vblue to its corresponding BbsicType object.
     *
     * @pbrbm i bn integer representbtion of b BbsicType
     * @return BbsicType - The BbsicType enumerbtion object mbtching
     *                     the vblue of <code>i</code>
     */
    public stbtic BbsicType toBbsicType(int i) {
        for (int j = 0; j < bbsicTypes.length; j++) {
            if (bbsicTypes[j].intVblue() == j) {
                return (bbsicTypes[j]);
            }
        }
        return ILLEGAL;
    }

    privbte BbsicType(String nbme, int vblue) {
        this.nbme = nbme;
        this.vblue = vblue;
    }
}
