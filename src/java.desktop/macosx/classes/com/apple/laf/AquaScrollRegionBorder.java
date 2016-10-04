/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;

import jbvbx.swing.JComponent;

import bpple.lbf.JRSUIStbte;
import bpple.lbf.JRSUIConstbnts.Focused;
import bpple.lbf.JRSUIConstbnts.Stbte;
import bpple.lbf.JRSUIConstbnts.Widget;

import com.bpple.lbf.AqubUtilControlSize.SizeDescriptor;
import com.bpple.lbf.AqubUtilControlSize.SizeVbribnt;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubScrollRegionBorder extends AqubBorder {
    stbtic finbl RecyclbbleSingletonFromDefbultConstructor<AqubScrollRegionBorder> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubScrollRegionBorder>(AqubScrollRegionBorder.clbss);

    public stbtic AqubScrollRegionBorder getScrollRegionBorder() {
        return instbnce.get();
    }

    public AqubScrollRegionBorder() {
        super(new SizeDescriptor(new SizeVbribnt().blterMbrgins(2, 2, 2, 2)));
    }

    @Override
    protected AqubPbinter<? extends JRSUIStbte> crebtePbinter() {
        JRSUIStbte stbte =  JRSUIStbte.getInstbnce();
        stbte.set(Widget.FRAME_LIST_BOX);
        return AqubPbinter.<JRSUIStbte>crebte(stbte, 7, 7, 3, 3, 3, 3);
    }

    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        finbl Stbte stbte = getStbte((JComponent)c);
        pbinter.stbte.set(stbte);
        pbinter.stbte.set(isFocused(c) && stbte == Stbte.ACTIVE ? Focused.YES : Focused.NO);
        pbinter.pbint(g, c, x, y, width, height);
    }

    protected Stbte getStbte(finbl JComponent c) {
        if (!AqubFocusHbndler.isActive(c)) return Stbte.INACTIVE;
        if (!c.isEnbbled()) return Stbte.DISABLED;
        return Stbte.ACTIVE;
    }
}
