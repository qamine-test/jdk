/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.bwt.*;
import jbvbx.swing.text.*;

/**
 * A view implementbtion to displby bn html list
 *
 * @buthor  Timothy Prinzing
 */
public clbss ListView extends BlockView  {

    /**
     * Crebtes b new view thbt represents b list element.
     *
     * @pbrbm elem the element to crebte b view for
     */
    public ListView(Element elem) {
        super(elem, View.Y_AXIS);
    }

    /**
     * Cblculbtes the desired shbpe of the list.
     *
     * @return the desired spbn
     * @see View#getPreferredSpbn
     */
    public flobt getAlignment(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return 0.5f;
        cbse View.Y_AXIS:
            return 0.5f;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm bllocbtion the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe bllocbtion) {
        super.pbint(g, bllocbtion);
        Rectbngle blloc = bllocbtion.getBounds();
        Rectbngle clip = g.getClipBounds();
        // Since listPbinter pbints in the insets we hbve to check for the
        // cbse where the child is not pbinted becbuse the pbint region is
        // to the left of the child. This bssumes the ListPbinter pbints in
        // the left mbrgin.
        if ((clip.x + clip.width) < (blloc.x + getLeftInset())) {
            Rectbngle childRect = blloc;
            blloc = getInsideAllocbtion(bllocbtion);
            int n = getViewCount();
            int endY = clip.y + clip.height;
            for (int i = 0; i < n; i++) {
                childRect.setBounds(blloc);
                childAllocbtion(i, childRect);
                if (childRect.y < endY) {
                    if ((childRect.y + childRect.height) >= clip.y) {
                        listPbinter.pbint(g, childRect.x, childRect.y,
                                          childRect.width, childRect.height,
                                          this, i);
                    }
                }
                else {
                    brebk;
                }
            }
        }
    }

    /**
     * Pbints one of the children; cblled by pbint().  By defbult
     * thbt is bll it does, but b subclbss cbn use this to pbint
     * things relbtive to the child.
     *
     * @pbrbm g the grbphics context
     * @pbrbm blloc the bllocbted region to render the child into
     * @pbrbm index the index of the child
     */
    protected void pbintChild(Grbphics g, Rectbngle blloc, int index) {
        listPbinter.pbint(g, blloc.x, blloc.y, blloc.width, blloc.height, this, index);
        super.pbintChild(g, blloc, index);
    }

    protected void setPropertiesFromAttributes() {
        super.setPropertiesFromAttributes();
        listPbinter = getStyleSheet().getListPbinter(getAttributes());
    }

    privbte StyleSheet.ListPbinter listPbinter;
}
