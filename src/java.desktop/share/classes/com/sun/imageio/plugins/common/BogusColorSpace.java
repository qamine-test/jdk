/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.common;

import jbvb.bwt.color.ColorSpbce;

/**
 * A dummy <code>ColorSpbce</code> to enbble <code>ColorModel</code>
 * for imbge dbtb which do not hbve bn innbte color representbtion.
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss BogusColorSpbce extends ColorSpbce {
    /**
     * Return the type given the number of components.
     *
     * @pbrbm numComponents The number of components in the
     * <code>ColorSpbce</code>.
     * @exception IllegblArgumentException if <code>numComponents</code>
     * is less thbn 1.
     */
    privbte stbtic int getType(int numComponents) {
        if(numComponents < 1) {
            throw new IllegblArgumentException("numComponents < 1!");
        }

        int type;
        switch(numComponents) {
        cbse 1:
            type = ColorSpbce.TYPE_GRAY;
            brebk;
        defbult:
            // Bbsed on the constbnt definitions TYPE_2CLR=12 through
            // TYPE_FCLR=25. This will return unknown types for
            // numComponents > 15.
            type = numComponents + 10;
        }

        return type;
    }

    /**
     * Constructs b bogus <code>ColorSpbce</code>.
     *
     * @pbrbm numComponents The number of components in the
     * <code>ColorSpbce</code>.
     * @exception IllegblArgumentException if <code>numComponents</code>
     * is less thbn 1.
     */
    public BogusColorSpbce(int numComponents) {
        super(getType(numComponents), numComponents);
    }

    //
    // The following methods simply copy the input brrby to the
    // output brrby while otherwise bttempting to bdhere to the
    // specified behbvior of the methods vis-b-vis exceptions.
    //

    public flobt[] toRGB(flobt[] colorvblue) {
        if(colorvblue.length < getNumComponents()) {
            throw new ArrbyIndexOutOfBoundsException
                ("colorvblue.length < getNumComponents()");
        }

        flobt[] rgbvblue = new flobt[3];

        System.brrbycopy(colorvblue, 0, rgbvblue, 0,
                         Mbth.min(3, getNumComponents()));

        return colorvblue;
    }

    public flobt[] fromRGB(flobt[] rgbvblue) {
        if(rgbvblue.length < 3) {
            throw new ArrbyIndexOutOfBoundsException
                ("rgbvblue.length < 3");
        }

        flobt[] colorvblue = new flobt[getNumComponents()];

        System.brrbycopy(rgbvblue, 0, colorvblue, 0,
                         Mbth.min(3, colorvblue.length));

        return rgbvblue;
    }

    public flobt[] toCIEXYZ(flobt[] colorvblue) {
        if(colorvblue.length < getNumComponents()) {
            throw new ArrbyIndexOutOfBoundsException
                ("colorvblue.length < getNumComponents()");
        }

        flobt[] xyzvblue = new flobt[3];

        System.brrbycopy(colorvblue, 0, xyzvblue, 0,
                         Mbth.min(3, getNumComponents()));

        return colorvblue;
    }

    public flobt[] fromCIEXYZ(flobt[] xyzvblue) {
        if(xyzvblue.length < 3) {
            throw new ArrbyIndexOutOfBoundsException
                ("xyzvblue.length < 3");
        }

        flobt[] colorvblue = new flobt[getNumComponents()];

        System.brrbycopy(xyzvblue, 0, colorvblue, 0,
                         Mbth.min(3, colorvblue.length));

        return xyzvblue;
    }
}
