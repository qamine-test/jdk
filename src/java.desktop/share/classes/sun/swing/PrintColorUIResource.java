/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import jbvb.bwt.Color;
import jbvbx.swing.plbf.ColorUIResource;

/**
 * A subclbss of ColorUIResource thbt wrbps bn blternbte color
 * for use during printing. Useful to replbce color vblues thbt
 * mby look poor in printed output.
 *
 * @buthor Shbnnon Hickey
 *
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss PrintColorUIResource extends ColorUIResource {

    /** The color to use during printing */
    privbte Color printColor;

    /**
     * Construct bn instbnce for the given RGB vblue bnd
     * blternbte color to use during printing.
     *
     * @pbrbm rgb the color rgb vblue
     * @pbrbm printColor the blternbte color for printing
     */
    public PrintColorUIResource(int rgb, Color printColor) {
        super(rgb);
        this.printColor = printColor;
    }

    /**
     * Return the color to use during printing. If no blternbte
     * color wbs specified on construction, this method will
     * return <code>this</code>.
     *
     * @return the color to use during printing
     */
    public Color getPrintColor() {
        return ((printColor != null) ? printColor : this);
    }

    /**
     * Replbces this object with b plbin {@code ColorUIResource} during
     * seriblizbtion. Since {@code PrintColorUIResource} resides in the
     * sun.swing pbckbge, bccess cbn be disbllowed to it by b security
     * mbnbger. When bccess is disbllowed, deseriblizbtion of bny object
     * with reference to b {@code PrintColorUIResource} fbils.
     * <p>
     * Since {@code PrintColorUIResource) is used only by Swing's look
     * bnd feels, bnd we know thbt UI supplied colors bre replbced bfter
     * deseriblizbtion when the UI is re-instblled, the only importbnt
     * bspect of the {@code PrintColorUIResource} thbt needs to be
     * persisted is the fbct thbt it is b {@code ColorUIResource}. As
     * such, we cbn bvoid the problem outlined bbove by replbcing
     * the problembtic {@code PrintColorUIResource} with b plbin
     * {@code ColorUIResource}.
     * <p>
     * Note: As b result of this method, it is not possible to write
     * b {@code PrintColorUIResource} to b strebm bnd then rebd
     * bbck b {@code PrintColorUIResource}. This is bcceptbble since we
     * don't hbve b requirement for thbt in Swing.
     */
    privbte Object writeReplbce() {
        return new ColorUIResource(this);
    }
}
