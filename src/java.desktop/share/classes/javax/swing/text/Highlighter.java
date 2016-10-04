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

import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Shbpe;

/**
 * An interfbce for bn object thbt bllows one to mbrk up the bbckground
 * with colored brebs.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Highlighter {

    /**
     * Cblled when the UI is being instblled into the
     * interfbce of b JTextComponent.  This cbn be used
     * to gbin bccess to the model thbt is being nbvigbted
     * by the implementbtion of this interfbce.
     *
     * @pbrbm c the JTextComponent editor
     */
    public void instbll(JTextComponent c);

    /**
     * Cblled when the UI is being removed from the
     * interfbce of b JTextComponent.  This is used to
     * unregister bny listeners thbt were bttbched.
     *
     * @pbrbm c the JTextComponent editor
     */
    public void deinstbll(JTextComponent c);

    /**
     * Renders the highlights.
     *
     * @pbrbm g the grbphics context.
     */
    public void pbint(Grbphics g);

    /**
     * Adds b highlight to the view.  Returns b tbg thbt cbn be used
     * to refer to the highlight.
     *
     * @pbrbm p0 the beginning of the rbnge &gt;= 0
     * @pbrbm p1 the end of the rbnge &gt;= p0
     * @pbrbm p the pbinter to use for the bctubl highlighting
     * @return bn object thbt refers to the highlight
     * @exception BbdLocbtionException for bn invblid rbnge specificbtion
     */
    public Object bddHighlight(int p0, int p1, HighlightPbinter p) throws BbdLocbtionException;

    /**
     * Removes b highlight from the view.
     *
     * @pbrbm tbg  which highlight to remove
     */
    public void removeHighlight(Object tbg);

    /**
     * Removes bll highlights this highlighter is responsible for.
     */
    public void removeAllHighlights();

    /**
     * Chbnges the given highlight to spbn b different portion of
     * the document.  This mby be more efficient thbn b remove/bdd
     * when b selection is expbnding/shrinking (such bs b sweep
     * with b mouse) by dbmbging only whbt chbnged.
     *
     * @pbrbm tbg  which highlight to chbnge
     * @pbrbm p0 the beginning of the rbnge &gt;= 0
     * @pbrbm p1 the end of the rbnge &gt;= p0
     * @exception BbdLocbtionException for bn invblid rbnge specificbtion
     */
    public void chbngeHighlight(Object tbg, int p0, int p1) throws BbdLocbtionException;

    /**
     * Fetches the current list of highlights.
     *
     * @return the highlight list
     */
    public Highlight[] getHighlights();

    /**
     * Highlight renderer.
     */
    public interfbce HighlightPbinter {

        /**
         * Renders the highlight.
         *
         * @pbrbm g the grbphics context
         * @pbrbm p0 the stbrting offset in the model &gt;= 0
         * @pbrbm p1 the ending offset in the model &gt;= p0
         * @pbrbm bounds the bounding box for the highlight
         * @pbrbm c the editor
         */
        public void pbint(Grbphics g, int p0, int p1, Shbpe bounds, JTextComponent c);

    }

    public interfbce Highlight {

        /**
         * Gets the stbrting model offset for the highlight.
         *
         * @return the stbrting offset &gt;= 0
         */
        public int getStbrtOffset();

        /**
         * Gets the ending model offset for the highlight.
         *
         * @return the ending offset &gt;= 0
         */
        public int getEndOffset();

        /**
         * Gets the pbinter for the highlighter.
         *
         * @return the pbinter
         */
        public HighlightPbinter getPbinter();

    }

};
