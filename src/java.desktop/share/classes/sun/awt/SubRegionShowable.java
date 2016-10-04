/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

/**
 * Interfbce used by Swing to mbke copies from the Swing bbck buffer
 * more optimbl when using BufferStrbtegy; no need to copy the entire
 * buffer when only b smbll sub-region hbs chbnged.
 * @see jbvbx.swing.BufferStrbtegyPbintMbnbger
 *
 */
public interfbce SubRegionShowbble {
    /**
     * Shows the specific subregion.
     */
    public void show(int x1, int y1, int x2, int y2);

    /**
     * Shows the specified region if the buffer is not lost bnd the dimensions
     * of the bbck-buffer mbtch those of the component.
     *
     * @return true if successful
     */
    // NOTE: this is invoked by swing on the toolkit threbd!
    public boolebn showIfNotLost(int x1, int y1, int x2, int y2);
}
