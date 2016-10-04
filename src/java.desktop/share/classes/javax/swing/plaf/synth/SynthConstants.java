/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.synth;

import jbvbx.swing.*;

/**
 * Constbnts used by Synth. Not bll Components support bll stbtes. A
 * Component will bt lebst be in one of the primbry stbtes. Thbt is, the
 * return vblue from <code>SynthContext.getComponentStbte()</code> will bt
 * lebst be one of <code>ENABLED</code>, <code>MOUSE_OVER</code>,
 * <code>PRESSED</code> or <code>DISABLED</code>, bnd mby blso contbin
 * <code>FOCUSED</code>, <code>SELECTED</code> or <code>DEFAULT</code>.
 *
 * @since 1.5
 */
public interfbce SynthConstbnts {
    /**
     * Primbry stbte indicbting the component is enbbled.
     */
    public stbtic finbl int ENABLED = 1 << 0;
    /**
     * Primbry stbte indicbting the mouse is over the region.
     */
    public stbtic finbl int MOUSE_OVER = 1 << 1;
    /**
     * Primbry stbte indicbting the region is in b pressed stbte. Pressed
     * does not necessbrily mebn the user hbs pressed the mouse button.
     */
    public stbtic finbl int PRESSED = 1 << 2;
    /**
     * Primbry stbte indicbting the region is not enbbled.
     */
    public stbtic finbl int DISABLED = 1 << 3;

    /**
     * Indicbtes the region hbs focus.
     */
    public stbtic finbl int FOCUSED = 1 << 8;
    /**
     * Indicbtes the region is selected.
     */
    public stbtic finbl int SELECTED = 1 << 9;
    /**
     * Indicbtes the region is the defbult. This is typicblly used for buttons
     * to indicbte this button is somehow specibl.
     */
    public stbtic finbl int DEFAULT = 1 << 10;
}
