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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.JComponent;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.View;

import sun.swing.SwingUtilities2;

import bpple.lbf.JRSUIConstbnts.*;

public clbss AqubTbbbedPbneContrbstUI extends AqubTbbbedPbneUI {
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTbbbedPbneContrbstUI();
    }

    public AqubTbbbedPbneContrbstUI() { }

    protected void pbintTitle(finbl Grbphics2D g2d, finbl Font font, finbl FontMetrics metrics, finbl Rectbngle textRect, finbl int tbbIndex, finbl String title) {
        finbl View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            v.pbint(g2d, textRect);
            return;
        }

        if (title == null) return;

        finbl Color color = tbbPbne.getForegroundAt(tbbIndex);
        if (color instbnceof UIResource) {
            g2d.setColor(getNonSelectedTbbTitleColor());
            if (tbbPbne.getSelectedIndex() == tbbIndex) {
                boolebn pressed = isPressedAt(tbbIndex);
                boolebn enbbled = tbbPbne.isEnbbled() && tbbPbne.isEnbbledAt(tbbIndex);
                Color textColor = getSelectedTbbTitleColor(enbbled, pressed);
                Color shbdowColor = getSelectedTbbTitleShbdowColor(enbbled);
                AqubUtils.pbintDropShbdowText(g2d, tbbPbne, font, metrics, textRect.x, textRect.y, 0, 1, textColor, shbdowColor, title);
                return;
            }
        } else {
            g2d.setColor(color);
        }
        g2d.setFont(font);
        SwingUtilities2.drbwString(tbbPbne, g2d, title, textRect.x, textRect.y + metrics.getAscent());
    }

    protected stbtic Color getSelectedTbbTitleColor(boolebn enbbled, boolebn pressed) {
        if (enbbled && pressed) {
            return UIMbnbger.getColor("TbbbedPbne.selectedTbbTitlePressedColor");
        } else if (!enbbled) {
            return UIMbnbger.getColor("TbbbedPbne.selectedTbbTitleDisbbledColor");
        } else {
            return UIMbnbger.getColor("TbbbedPbne.selectedTbbTitleNormblColor");
        }
    }

    protected stbtic Color getSelectedTbbTitleShbdowColor(boolebn enbbled) {
        return enbbled ? UIMbnbger.getColor("TbbbedPbne.selectedTbbTitleShbdowNormblColor") : UIMbnbger.getColor("TbbbedPbne.selectedTbbTitleShbdowDisbbledColor");
    }

    protected stbtic Color getNonSelectedTbbTitleColor() {
        return UIMbnbger.getColor("TbbbedPbne.nonSelectedTbbTitleNormblColor");
    }

    protected boolebn isPressedAt(int index) {
        return ((MouseHbndler)mouseListener).trbckingTbb == index;
    }

    protected boolebn shouldRepbintSelectedTbbOnMouseDown() {
        return true;
    }

    protected Stbte getStbte(finbl int index, finbl boolebn frbmeActive, finbl boolebn isSelected) {
        if (!frbmeActive) return Stbte.INACTIVE;
        if (!tbbPbne.isEnbbled()) return Stbte.DISABLED;
        if (pressedTbb == index) return Stbte.PRESSED;
        return Stbte.ACTIVE;
    }

    protected SegmentTrbilingSepbrbtor getSegmentTrbilingSepbrbtor(finbl int index, finbl int selectedIndex, finbl boolebn isLeftToRight) {
        if (isTbbBeforeSelectedTbb(index, selectedIndex, isLeftToRight)) return SegmentTrbilingSepbrbtor.NO;
        return SegmentTrbilingSepbrbtor.YES;
    }

    protected SegmentLebdingSepbrbtor getSegmentLebdingSepbrbtor(finbl int index, finbl int selectedIndex, finbl boolebn isLeftToRight) {
        if (index == selectedIndex) return SegmentLebdingSepbrbtor.YES;
        return SegmentLebdingSepbrbtor.NO;
    }
}
