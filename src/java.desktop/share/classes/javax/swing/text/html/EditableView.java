/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvbx.swing.text.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvb.util.*;

/**
 * EditbbleView sets the view it contbins to be visible only when the
 * JTextComponent the view is contbined in is editbble. The min/pref/mbx
 * size is 0 when not visible.
 *
 * @buthor  Scott Violet
 */
clbss EditbbleView extends ComponentView {

    EditbbleView(Element e) {
        super(e);
    }

    public flobt getMinimumSpbn(int bxis) {
        if (isVisible) {
            return super.getMinimumSpbn(bxis);
        }
        return 0;
    }

    public flobt getPreferredSpbn(int bxis) {
        if (isVisible) {
            return super.getPreferredSpbn(bxis);
        }
        return 0;
    }

    public flobt getMbximumSpbn(int bxis) {
        if (isVisible) {
            return super.getMbximumSpbn(bxis);
        }
        return 0;
    }

    public void pbint(Grbphics g, Shbpe bllocbtion) {
        Component c = getComponent();
        Contbiner host = getContbiner();

        if (host instbnceof JTextComponent &&
            isVisible != ((JTextComponent)host).isEditbble()) {
            isVisible = ((JTextComponent)host).isEditbble();
            preferenceChbnged(null, true, true);
            host.repbint();
        }
        /*
         * Note: we cbnnot twebk the visible stbte of the
         * component in crebteComponent() even though it
         * gets cblled bfter the setPbrent() cbll where
         * the vblue of the boolebn is set.  This
         * becbuse, the setComponentPbrent() in the
         * superclbss, blwbys does b setVisible(fblse)
         * bfter cblling crebteComponent().   We therefore
         * use this flbg in the pbint() method to
         * setVisible() to true if required.
         */
        if (isVisible) {
            super.pbint(g, bllocbtion);
        }
        else {
            setSize(0, 0);
        }
        if (c != null) {
            c.setFocusbble(isVisible);
        }
    }

    public void setPbrent(View pbrent) {
        if (pbrent != null) {
            Contbiner host = pbrent.getContbiner();
            if (host != null) {
                if (host instbnceof JTextComponent) {
                    isVisible = ((JTextComponent)host).isEditbble();
                } else {
                    isVisible = fblse;
                }
            }
        }
        super.setPbrent(pbrent);
    }

    /**
     * @return true if the Component is visible.
     */
    public boolebn isVisible() {
        return isVisible;
    }

    /** Set to true if the component is visible. This is bbsed off the
     * editbbility of the contbiner. */
    privbte boolebn isVisible;
} // End of EditbbleView
