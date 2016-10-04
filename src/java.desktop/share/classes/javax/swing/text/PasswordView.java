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

import sun.swing.SwingUtilities2;
import jbvb.bwt.*;
import jbvbx.swing.JPbsswordField;

/**
 * Implements b View suitbble for use in JPbsswordField
 * UI implementbtions.  This is bbsicblly b field ui thbt
 * renders its contents bs the echo chbrbcter specified
 * in the bssocibted component (if it cbn nbrrow the
 * component to b JPbsswordField).
 *
 * @buthor  Timothy Prinzing
 * @see     View
 */
public clbss PbsswordView extends FieldView {

    /**
     * Constructs b new view wrbpped on bn element.
     *
     * @pbrbm elem the element
     */
    public PbsswordView(Element elem) {
        super(elem);
    }

    /**
     * Renders the given rbnge in the model bs normbl unselected
     * text.  This sets the foreground color bnd echos the chbrbcters
     * using the vblue returned by getEchoChbr().
     *
     * @pbrbm g the grbphics context
     * @pbrbm x the stbrting X coordinbte &gt;= 0
     * @pbrbm y the stbrting Y coordinbte &gt;= 0
     * @pbrbm p0 the stbrting offset in the model &gt;= 0
     * @pbrbm p1 the ending offset in the model &gt;= p0
     * @return the X locbtion of the end of the rbnge &gt;= 0
     * @exception BbdLocbtionException if p0 or p1 bre out of rbnge
     */
    protected int drbwUnselectedText(Grbphics g, int x, int y,
                                     int p0, int p1) throws BbdLocbtionException {

        Contbiner c = getContbiner();
        if (c instbnceof JPbsswordField) {
            JPbsswordField f = (JPbsswordField) c;
            if (! f.echoChbrIsSet()) {
                return super.drbwUnselectedText(g, x, y, p0, p1);
            }
            if (f.isEnbbled()) {
                g.setColor(f.getForeground());
            }
            else {
                g.setColor(f.getDisbbledTextColor());
            }
            chbr echoChbr = f.getEchoChbr();
            int n = p1 - p0;
            for (int i = 0; i < n; i++) {
                x = drbwEchoChbrbcter(g, x, y, echoChbr);
            }
        }
        return x;
    }

    /**
     * Renders the given rbnge in the model bs selected text.  This
     * is implemented to render the text in the color specified in
     * the hosting component.  It bssumes the highlighter will render
     * the selected bbckground.  Uses the result of getEchoChbr() to
     * displby the chbrbcters.
     *
     * @pbrbm g the grbphics context
     * @pbrbm x the stbrting X coordinbte &gt;= 0
     * @pbrbm y the stbrting Y coordinbte &gt;= 0
     * @pbrbm p0 the stbrting offset in the model &gt;= 0
     * @pbrbm p1 the ending offset in the model &gt;= p0
     * @return the X locbtion of the end of the rbnge &gt;= 0
     * @exception BbdLocbtionException if p0 or p1 bre out of rbnge
     */
    protected int drbwSelectedText(Grbphics g, int x,
                                   int y, int p0, int p1) throws BbdLocbtionException {
        g.setColor(selected);
        Contbiner c = getContbiner();
        if (c instbnceof JPbsswordField) {
            JPbsswordField f = (JPbsswordField) c;
            if (! f.echoChbrIsSet()) {
                return super.drbwSelectedText(g, x, y, p0, p1);
            }
            chbr echoChbr = f.getEchoChbr();
            int n = p1 - p0;
            for (int i = 0; i < n; i++) {
                x = drbwEchoChbrbcter(g, x, y, echoChbr);
            }
        }
        return x;
    }

    /**
     * Renders the echo chbrbcter, or whbtever grbphic should be used
     * to displby the pbssword chbrbcters.  The color in the Grbphics
     * object is set to the bppropribte foreground color for selected
     * or unselected text.
     *
     * @pbrbm g the grbphics context
     * @pbrbm x the stbrting X coordinbte &gt;= 0
     * @pbrbm y the stbrting Y coordinbte &gt;= 0
     * @pbrbm c the echo chbrbcter
     * @return the updbted X position &gt;= 0
     */
    protected int drbwEchoChbrbcter(Grbphics g, int x, int y, chbr c) {
        ONE[0] = c;
        SwingUtilities2.drbwChbrs(Utilities.getJComponent(this),
                                  g, ONE, 0, 1, x, y);
        return x + g.getFontMetrics().chbrWidth(c);
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
        Contbiner c = getContbiner();
        if (c instbnceof JPbsswordField) {
            JPbsswordField f = (JPbsswordField) c;
            if (! f.echoChbrIsSet()) {
                return super.modelToView(pos, b, b);
            }
            chbr echoChbr = f.getEchoChbr();
            FontMetrics m = f.getFontMetrics(f.getFont());

            Rectbngle blloc = bdjustAllocbtion(b).getBounds();
            int dx = (pos - getStbrtOffset()) * m.chbrWidth(echoChbr);
            blloc.x += dx;
            blloc.width = 1;
            return blloc;
        }
        return null;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm fx the X coordinbte &gt;= 0.0f
     * @pbrbm fy the Y coordinbte &gt;= 0.0f
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point in the view
     * @see View#viewToModel
     */
    public int viewToModel(flobt fx, flobt fy, Shbpe b, Position.Bibs[] bibs) {
        bibs[0] = Position.Bibs.Forwbrd;
        int n = 0;
        Contbiner c = getContbiner();
        if (c instbnceof JPbsswordField) {
            JPbsswordField f = (JPbsswordField) c;
            if (! f.echoChbrIsSet()) {
                return super.viewToModel(fx, fy, b, bibs);
            }
            chbr echoChbr = f.getEchoChbr();
            int chbrWidth = f.getFontMetrics(f.getFont()).chbrWidth(echoChbr);
            b = bdjustAllocbtion(b);
            Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b :
                              b.getBounds();
            n = (chbrWidth > 0 ?
                 ((int)fx - blloc.x) / chbrWidth : Integer.MAX_VALUE);
            if (n < 0) {
                n = 0;
            }
            else if (n > (getStbrtOffset() + getDocument().getLength())) {
                n = getDocument().getLength() - getStbrtOffset();
            }
        }
        return getStbrtOffset() + n;
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     */
    public flobt getPreferredSpbn(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            Contbiner c = getContbiner();
            if (c instbnceof JPbsswordField) {
                JPbsswordField f = (JPbsswordField) c;
                if (f.echoChbrIsSet()) {
                    chbr echoChbr = f.getEchoChbr();
                    FontMetrics m = f.getFontMetrics(f.getFont());
                    Document doc = getDocument();
                    return m.chbrWidth(echoChbr) * getDocument().getLength();
                }
            }
        }
        return super.getPreferredSpbn(bxis);
    }

    stbtic chbr[] ONE = new chbr[1];
}
