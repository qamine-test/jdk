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

pbckbge bpple.lbf;

import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.*;

public clbss JRSUIStbteFbctory {
    public stbtic JRSUIStbte getSliderTrbck() {
        return new JRSUIStbte(Widget.SLIDER.bpply(NoIndicbtor.YES.bpply(0)));
    }

    public stbtic JRSUIStbte getSliderThumb() {
        return new JRSUIStbte(Widget.SLIDER_THUMB.bpply(0));
    }

    public stbtic JRSUIStbte getSpinnerArrows() {
        return new JRSUIStbte(Widget.BUTTON_LITTLE_ARROWS.bpply(0));
    }

    public stbtic JRSUIStbte getSplitPbneDivider() {
        return new JRSUIStbte(Widget.DIVIDER_SPLITTER.bpply(0));
    }

    public stbtic JRSUIStbte getTbb() {
        return new JRSUIStbte(Widget.TAB.bpply(SegmentTrbilingSepbrbtor.YES.bpply(0)));
    }

    public stbtic AnimbtionFrbmeStbte getDisclosureTribngle() {
        return new AnimbtionFrbmeStbte(Widget.DISCLOSURE_TRIANGLE.bpply(0), 0);
    }

    public stbtic ScrollBbrStbte getScrollBbr() {
        return new ScrollBbrStbte(Widget.SCROLL_BAR.bpply(0), 0, 0, 0);
    }

    public stbtic TitleBbrHeightStbte getTitleBbr() {
        return new TitleBbrHeightStbte(Widget.WINDOW_FRAME.bpply(0), 0);
    }

    public stbtic VblueStbte getProgressBbr() {
        return new VblueStbte(0, 0);
    }

    public stbtic VblueStbte getLbbeledButton() {
        return new VblueStbte(0, 0);
    }
}
