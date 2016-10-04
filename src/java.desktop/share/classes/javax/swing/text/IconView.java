/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.bwt.*;
import jbvbx.swing.Icon;
import jbvbx.swing.event.*;

/**
 * Icon decorbtor thbt implements the view interfbce.  The
 * entire element is used to represent the icon.  This bcts
 * bs b gbtewby from the displby-only View implementbtions to
 * interbctive lightweight icons (thbt is, it bllows icons
 * to be embedded into the View hierbrchy.  The pbrent of the icon
 * is the contbiner thbt is hbnded out by the bssocibted view
 * fbctory.
 *
 * @buthor Timothy Prinzing
 */
public clbss IconView extends View  {

    /**
     * Crebtes b new icon view thbt represents bn element.
     *
     * @pbrbm elem the element to crebte b view for
     */
    public IconView(Element elem) {
        super(elem);
        AttributeSet bttr = elem.getAttributes();
        c = StyleConstbnts.getIcon(bttr);
    }

    // --- View methods ---------------------------------------------

    /**
     * Pbints the icon.
     * The rebl pbint behbvior occurs nbturblly from the bssocibtion
     * thbt the icon hbs with its pbrent contbiner (the sbme
     * contbiner hosting this view), so this simply bllows us to
     * position the icon properly relbtive to the view.  Since
     * the coordinbte system for the view is simply the pbrent
     * contbiners, positioning the child icon is ebsy.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        Rectbngle blloc = b.getBounds();
        c.pbintIcon(getContbiner(), g, blloc.x, blloc.y);
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return  the spbn the view would like to be rendered into
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getPreferredSpbn(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return c.getIconWidth();
        cbse View.Y_AXIS:
            return c.getIconHeight();
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  This is implemented to give the blignment to the
     * bottom of the icon blong the y bxis, bnd the defbult
     * blong the x bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return the desired blignment &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f.  This should be
     *   b vblue between 0.0 bnd 1.0 where 0 indicbtes blignment bt the
     *   origin bnd 1.0 indicbtes blignment to the full spbn
     *   bwby from the origin.  An blignment of 0.5 would be the
     *   center of the view.
     */
    public flobt getAlignment(int bxis) {
        switch (bxis) {
        cbse View.Y_AXIS:
            return 1;
        defbult:
            return super.getAlignment(bxis);
        }
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        if ((pos >= p0) && (pos <= p1)) {
            Rectbngle r = b.getBounds();
            if (pos == p1) {
                r.x += r.width;
            }
            r.width = 0;
            return r;
        }
        throw new BbdLocbtionException(pos + " not in rbnge " + p0 + "," + p1, pos);
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point of view &gt;= 0
     * @see View#viewToModel
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
        Rectbngle blloc = (Rectbngle) b;
        if (x < blloc.x + (blloc.width / 2)) {
            bibs[0] = Position.Bibs.Forwbrd;
            return getStbrtOffset();
        }
        bibs[0] = Position.Bibs.Bbckwbrd;
        return getEndOffset();
    }

    // --- member vbribbles ------------------------------------------------

    privbte Icon c;
}
