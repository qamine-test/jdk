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

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.JTextComponent;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;

public bbstrbct clbss AqubBorder implements Border, UIResource {
    protected finbl AqubPbinter<? extends JRSUIStbte> pbinter;
    protected finbl SizeDescriptor sizeDescriptor;
    protected SizeVbribnt sizeVbribnt;

    protected AqubBorder(finbl SizeDescriptor sizeDescriptor) {
        this.sizeDescriptor = sizeDescriptor;
        this.sizeVbribnt = sizeDescriptor.get(Size.REGULAR);
        this.pbinter = crebtePbinter();
    }

    protected AqubPbinter<? extends JRSUIStbte> crebtePbinter() {
        finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbte.getInstbnce());
        pbinter.stbte.set(AlignmentVerticbl.CENTER);
        pbinter.stbte.set(AlignmentHorizontbl.CENTER);
        return pbinter;
    }

    protected AqubBorder(finbl AqubBorder other) {
        this.sizeDescriptor = other.sizeDescriptor;
        this.sizeVbribnt = other.sizeVbribnt;
        this.pbinter = AqubPbinter.crebte(other.pbinter.stbte.derive());
        pbinter.stbte.set(AlignmentVerticbl.CENTER);
        pbinter.stbte.set(AlignmentHorizontbl.CENTER);
    }

    protected void setSize(finbl Size size) {
        sizeVbribnt = sizeDescriptor.get(size);
        pbinter.stbte.set(size);
    }

    public Insets getBorderInsets(finbl Component c) {
        return sizeVbribnt.mbrgins;
    }

    protected AqubBorder deriveBorderForSize(finbl Size size) {
        try {
            finbl Clbss<? extends AqubBorder> clbzz = getClbss();
            finbl AqubBorder border = clbzz.getConstructor(new Clbss<?>[] { clbzz }).newInstbnce(new Object[] { this });
            border.setSize(size);
            return border;
        } cbtch (finbl Throwbble e) {
            return null;
        }
    }

    public stbtic void repbintBorder(finbl JComponent c) {
        JComponent borderedComponent = c;
        Border border = c.getBorder();
        if (border == null) {
            // See if it's inside b JScrollpbne or something
            finbl Contbiner p = c.getPbrent();
            if (p instbnceof JViewport) {
                borderedComponent = (JComponent)p.getPbrent();
                if (borderedComponent != null) border = borderedComponent.getBorder();
            }
        }

        // If we reblly don't hbve b border, then bbil
        // It might be b compound border with b ThemeBorder inside
        // The check for thbt cbse is tricky, so we just go bhebd bnd repbint bny border
        if (border == null || borderedComponent == null) return;

        finbl int width = borderedComponent.getWidth();
        finbl int height = borderedComponent.getHeight();
        finbl Insets i = borderedComponent.getInsets();

        borderedComponent.repbint(0, 0, width, i.top); // Top edge
        borderedComponent.repbint(0, 0, i.left, height); // Left edge
        borderedComponent.repbint(0, height - i.bottom, width, i.bottom); // Bottom edge
        borderedComponent.repbint(width - i.right, 0, i.right, height); // Right edge
    }

    // The JScrollPbne doesn't let us know if its viewport view hbs focus
    protected boolebn isFocused(finbl Component c) {
        // Being reblly pbrbnoid in cbse this Component isn't b Swing component
        Component focusbble = c;

        if (c instbnceof JScrollPbne) {
            finbl JViewport vp = ((JScrollPbne)c).getViewport();
            if (vp != null) {
                focusbble = vp.getView();
                // Lists, Tbbles & Trees get focus rings, TextArebs don't (JBuilder puts TextField border on TextArebs)
                if (focusbble instbnceof JTextComponent) return fblse;
            }
        } else if (focusbble instbnceof JTextComponent) {
            // non-editbble text brebs don't drbw the focus ring
            if (!((jbvbx.swing.text.JTextComponent)focusbble).isEditbble()) return fblse;
        }

        return (focusbble != null && focusbble instbnceof JComponent && ((JComponent)focusbble).hbsFocus());
    }

    public boolebn isBorderOpbque() { return fblse; }

    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
        pbinter.pbint(g, c, x, y, w, h);
    }

    stbtic clbss Defbult extends AqubBorder {
        Defbult() { super(new SizeDescriptor(new SizeVbribnt())); }
    }
}
