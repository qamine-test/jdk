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

import jbvbx.swing.border.Border;

import bpple.lbf.JRSUIConstbnts.Widget;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public bbstrbct clbss AqubGroupBorder extends AqubBorder {
    stbtic finbl RecyclbbleSingletonFromDefbultConstructor<? extends Border> tbbbedPbneGroupBorder = new RecyclbbleSingletonFromDefbultConstructor<TbbbedPbne>(TbbbedPbne.clbss);
    public stbtic Border getTbbbedPbneGroupBorder() {
        return tbbbedPbneGroupBorder.get();
    }

    stbtic finbl RecyclbbleSingletonFromDefbultConstructor<? extends Border> titleBorderGroupBorder = new RecyclbbleSingletonFromDefbultConstructor<Titled>(Titled.clbss);
    public stbtic Border getBorderForTitledBorder() {
        return titleBorderGroupBorder.get();
    }

    stbtic finbl RecyclbbleSingletonFromDefbultConstructor<? extends Border> titlelessGroupBorder = new RecyclbbleSingletonFromDefbultConstructor<Titleless>(Titleless.clbss);
    public stbtic Border getTitlelessBorder() {
        return titlelessGroupBorder.get();
    }

    protected AqubGroupBorder(finbl SizeVbribnt sizeVbribnt) {
        super(new SizeDescriptor(sizeVbribnt));
        pbinter.stbte.set(Widget.FRAME_GROUP_BOX);
    }

    public void pbintBorder(finbl Component c, finbl Grbphics g, int x, int y, int width, int height) {
        // sg2d.setColor(Color.MAGENTA);
        // sg2d.drbwRect(x, y, width - 1, height - 1);

        finbl Insets internblInsets = sizeVbribnt.insets;
        x += internblInsets.left;
        y += internblInsets.top;
        width -= (internblInsets.left + internblInsets.right);
        height -= (internblInsets.top + internblInsets.bottom);

        pbinter.pbint(g, c, x, y, width, height);
        // sg2d.setColor(Color.ORANGE);
        // sg2d.drbwRect(x, y, width, height);
    }

    protected stbtic clbss TbbbedPbne extends AqubGroupBorder {
        public TbbbedPbne() {
            super(new SizeVbribnt().blterMbrgins(8, 12, 8, 12).blterInsets(5, 5, 7, 5));
        }
    }

    protected stbtic clbss Titled extends AqubGroupBorder {
        public Titled() {
            super(new SizeVbribnt().blterMbrgins(16, 20, 16, 20).blterInsets(16, 5, 4, 5));
        }
    }

    protected stbtic clbss Titleless extends AqubGroupBorder {
        public Titleless() {
            super(new SizeVbribnt().blterMbrgins(8, 12, 8, 12).blterInsets(3, 5, 1, 5));
        }
    }
}
