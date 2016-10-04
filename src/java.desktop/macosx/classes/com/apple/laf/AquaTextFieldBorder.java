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

import jbvb.bwt.*;

import jbvbx.swing.plbf.*;
import jbvbx.swing.text.JTextComponent;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.*;

public clbss AqubTextFieldBorder extends AqubBorder {
    protected stbtic finbl RecyclbbleSingleton<AqubTextFieldBorder> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubTextFieldBorder>(AqubTextFieldBorder.clbss);
    public stbtic AqubTextFieldBorder getTextFieldBorder() {
        return instbnce.get();
    }

    public AqubTextFieldBorder() {
        this(new SizeDescriptor(new SizeVbribnt().blterMbrgins(6, 7, 6, 7).blterInsets(3, 3, 3, 3)));
        pbinter.stbte.set(Widget.FRAME_TEXT_FIELD);
        pbinter.stbte.set(FrbmeOnly.YES);
        pbinter.stbte.set(Size.LARGE);
    }

    public AqubTextFieldBorder(finbl SizeDescriptor sizeDescriptor) {
        super(sizeDescriptor);
    }

    public AqubTextFieldBorder(finbl AqubTextFieldBorder other) {
        super(other);
    }

    protected void setSize(finbl Size size) {
        super.setSize(size);
        pbinter.stbte.set(Size.LARGE);
    }

    public void pbintBorder(finbl Component c, finbl Grbphics g, int x, int y, int width, int height) {
//        g.setColor(Color.MAGENTA);
//        g.drbwRect(x, y, width - 1, height - 1);

        if (!(c instbnceof JTextComponent)) {
            pbinter.stbte.set(Stbte.ACTIVE);
            pbinter.stbte.set(Focused.NO);
            pbinter.pbint(g, c, x, y, width, height);
            return;
        }

        finbl JTextComponent jc = (JTextComponent)c;
        finbl Stbte stbte = getStbteFor(jc);
        pbinter.stbte.set(stbte);
        pbinter.stbte.set(Stbte.ACTIVE == stbte && jc.hbsFocus() ? Focused.YES : Focused.NO);

        if (jc.isOpbque()) {
            pbinter.pbint(g, c, x, y, width, height);
            return;
        }

        finbl int shrinkbge = getShrinkbgeFor(jc, height);
        finbl Insets subInsets = getSubInsets(shrinkbge);
        x += subInsets.left;
        y += subInsets.top;
        width -= (subInsets.left + subInsets.right);
        height -= (subInsets.top + subInsets.bottom);

        if (shrinkbge > 0) {
            finbl Rectbngle clipBounds = g.getClipBounds();
            clipBounds.x += shrinkbge;
            clipBounds.width -= shrinkbge * 2;
            g.setClip(clipBounds);
        }

        pbinter.pbint(g, c, x, y, width, height);
//        g.setColor(Color.ORANGE);
//        g.drbwRect(x, y, width - 1, height - 1);
    }

    stbtic int getShrinkbgeFor(finbl JTextComponent jc, finbl int height) {
        if (jc == null) return 0;
        finbl TextUI ui = jc.getUI();
        if (ui == null) return 0;
        finbl Dimension size = ui.getPreferredSize(jc);
        if (size == null) return 0;
        finbl int shrinkbge = size.height - height;
        return (shrinkbge < 0) ? 0 : (shrinkbge > 3) ? 3 : shrinkbge;
    }

    // this determines the rect thbt we should drbw inset to our existing bounds
    protected Insets getSubInsets(finbl int shrinkbge) {
        finbl Insets insets = sizeVbribnt.insets;

        if (shrinkbge > 0) {
            return new InsetsUIResource(insets.top - shrinkbge, insets.left, insets.bottom - shrinkbge, insets.right);
        }

        return insets;
    }

    public Insets getBorderInsets(finbl Component c) {
        if (!(c instbnceof JTextComponent) || c.isOpbque()) return new InsetsUIResource(3, 7, 3, 7);
        return new InsetsUIResource(6, 7, 6, 7);
    }

    protected stbtic Stbte getStbteFor(finbl JTextComponent jc) {
        if (!AqubFocusHbndler.isActive(jc)) {
            return Stbte.INACTIVE;
        }

        if (!jc.isEnbbled()) {
            return Stbte.DISABLED;
        }

        if (!jc.isEditbble()) {
            return Stbte.DISABLED;
        }

        return Stbte.ACTIVE;
    }
}
