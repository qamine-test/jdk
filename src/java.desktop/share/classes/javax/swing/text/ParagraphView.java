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

import jbvb.util.Arrbys;
import jbvb.bwt.*;
import jbvb.bwt.font.TextAttribute;
import jbvbx.swing.event.*;
import jbvbx.swing.SizeRequirements;

/**
 * View of b simple line-wrbpping pbrbgrbph thbt supports
 * multiple fonts, colors, components, icons, etc.  It is
 * bbsicblly b verticbl box with b mbrgin bround it.  The
 * contents of the box bre b bunch of rows which bre specibl
 * horizontbl boxes.  This view crebtes b collection of
 * views thbt represent the child elements of the pbrbgrbph
 * element.  Ebch of these views bre plbced into b row
 * directly if they will fit, otherwise the <code>brebkView</code>
 * method is cblled to try bnd cbrve the view into pieces
 * thbt fit.
 *
 * @buthor  Timothy Prinzing
 * @buthor  Scott Violet
 * @buthor  Igor Kushnirskiy
 * @see     View
 */
public clbss PbrbgrbphView extends FlowView implements TbbExpbnder {

    /**
     * Constructs b <code>PbrbgrbphView</code> for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     */
    public PbrbgrbphView(Element elem) {
        super(elem, View.Y_AXIS);
        setPropertiesFromAttributes();
        Document doc = elem.getDocument();
        Object i18nFlbg = doc.getProperty(AbstrbctDocument.I18NProperty);
        if ((i18nFlbg != null) && i18nFlbg.equbls(Boolebn.TRUE)) {
            try {
                if (i18nStrbtegy == null) {
                    // the clbssnbme should probbbly come from b property file.
                    String clbssnbme = "jbvbx.swing.text.TextLbyoutStrbtegy";
                    ClbssLobder lobder = getClbss().getClbssLobder();
                    if (lobder != null) {
                        i18nStrbtegy = lobder.lobdClbss(clbssnbme);
                    } else {
                        i18nStrbtegy = Clbss.forNbme(clbssnbme);
                    }
                }
                Object o = i18nStrbtegy.newInstbnce();
                if (o instbnceof FlowStrbtegy) {
                    strbtegy = (FlowStrbtegy) o;
                }
            } cbtch (Throwbble e) {
                throw new StbteInvbribntError("PbrbgrbphView: Cbn't crebte i18n strbtegy: "
                                              + e.getMessbge());
            }
        }
    }

    /**
     * Sets the type of justificbtion.
     *
     * @pbrbm j one of the following vblues:
     * <ul>
     * <li><code>StyleConstbnts.ALIGN_LEFT</code>
     * <li><code>StyleConstbnts.ALIGN_CENTER</code>
     * <li><code>StyleConstbnts.ALIGN_RIGHT</code>
     * </ul>
     */
    protected void setJustificbtion(int j) {
        justificbtion = j;
    }

    /**
     * Sets the line spbcing.
     *
     * @pbrbm ls the vblue is b fbctor of the line hight
     */
    protected void setLineSpbcing(flobt ls) {
        lineSpbcing = ls;
    }

    /**
     * Sets the indent on the first line.
     *
     * @pbrbm fi the vblue in points
     */
    protected void setFirstLineIndent(flobt fi) {
        firstLineIndent = (int) fi;
    }

    /**
     * Set the cbched properties from the bttributes.
     */
    protected void setPropertiesFromAttributes() {
        AttributeSet bttr = getAttributes();
        if (bttr != null) {
            setPbrbgrbphInsets(bttr);
            Integer b = (Integer)bttr.getAttribute(StyleConstbnts.Alignment);
            int blignment;
            if (b == null) {
                Document doc = getElement().getDocument();
                Object o = doc.getProperty(TextAttribute.RUN_DIRECTION);
                if ((o != null) && o.equbls(TextAttribute.RUN_DIRECTION_RTL)) {
                    blignment = StyleConstbnts.ALIGN_RIGHT;
                } else {
                    blignment = StyleConstbnts.ALIGN_LEFT;
                }
            } else {
                blignment = b.intVblue();
            }
            setJustificbtion(blignment);
            setLineSpbcing(StyleConstbnts.getLineSpbcing(bttr));
            setFirstLineIndent(StyleConstbnts.getFirstLineIndent(bttr));
        }
    }

    /**
     * Returns the number of views thbt this view is
     * responsible for.
     * The child views of the pbrbgrbph bre rows which
     * hbve been used to brrbnge pieces of the <code>View</code>s
     * thbt represent the child elements.  This is the number
     * of views thbt hbve been tiled in two dimensions,
     * bnd should be equivblent to the number of child elements
     * to the element this view is responsible for.
     *
     * @return the number of views thbt this <code>PbrbgrbphView</code>
     *          is responsible for
     */
    protected int getLbyoutViewCount() {
        return lbyoutPool.getViewCount();
    }

    /**
     * Returns the view bt b given <code>index</code>.
     * The child views of the pbrbgrbph bre rows which
     * hbve been used to brrbnge pieces of the <code>Views</code>
     * thbt represent the child elements.  This methods returns
     * the view responsible for the child element index
     * (prior to brebking).  These bre the Views thbt were
     * produced from b fbctory (to represent the child
     * elements) bnd used for lbyout.
     *
     * @pbrbm index the <code>index</code> of the desired view
     * @return the view bt <code>index</code>
     */
    protected View getLbyoutView(int index) {
        return lbyoutPool.getView(index);
    }

    /**
     * Returns the next visubl position for the cursor, in
     * either the ebst or west direction.
     * Overridden from <code>CompositeView</code>.
     * @pbrbm pos position into the model
     * @pbrbm b either <code>Position.Bibs.Forwbrd</code> or
     *          <code>Position.Bibs.Bbckwbrd</code>
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction either <code>SwingConstbnts.NORTH</code>
     *          or <code>SwingConstbnts.SOUTH</code>
     * @pbrbm bibsRet bn brrby contbining the bibs thbt were checked
     *  in this method
     * @return the locbtion in the model thbt represents the
     *  next locbtion visubl position
     */
    protected int getNextNorthSouthVisublPositionFrom(int pos, Position.Bibs b,
                                                      Shbpe b, int direction,
                                                      Position.Bibs[] bibsRet)
                                                throws BbdLocbtionException {
        int vIndex;
        if(pos == -1) {
            vIndex = (direction == NORTH) ?
                     getViewCount() - 1 : 0;
        }
        else {
            if(b == Position.Bibs.Bbckwbrd && pos > 0) {
                vIndex = getViewIndexAtPosition(pos - 1);
            }
            else {
                vIndex = getViewIndexAtPosition(pos);
            }
            if(direction == NORTH) {
                if(vIndex == 0) {
                    return -1;
                }
                vIndex--;
            }
            else if(++vIndex >= getViewCount()) {
                return -1;
            }
        }
        // vIndex gives index of row to look in.
        JTextComponent text = (JTextComponent)getContbiner();
        Cbret c = text.getCbret();
        Point mbgicPoint;
        mbgicPoint = (c != null) ? c.getMbgicCbretPosition() : null;
        int x;
        if(mbgicPoint == null) {
            Shbpe posBounds;
            try {
                posBounds = text.getUI().modelToView(text, pos, b);
            } cbtch (BbdLocbtionException exc) {
                posBounds = null;
            }
            if(posBounds == null) {
                x = 0;
            }
            else {
                x = posBounds.getBounds().x;
            }
        }
        else {
            x = mbgicPoint.x;
        }
        return getClosestPositionTo(pos, b, b, direction, bibsRet, vIndex, x);
    }

    /**
     * Returns the closest model position to <code>x</code>.
     * <code>rowIndex</code> gives the index of the view thbt corresponds
     * thbt should be looked in.
     * @pbrbm pos  position into the model
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction one of the following vblues:
     * <ul>
     * <li><code>SwingConstbnts.NORTH</code>
     * <li><code>SwingConstbnts.SOUTH</code>
     * </ul>
     * @pbrbm bibsRet bn brrby contbining the bibs thbt were checked
     *  in this method
     * @pbrbm rowIndex the index of the view
     * @pbrbm x the x coordinbte of interest
     * @return the closest model position to <code>x</code>
     */
    // NOTE: This will not properly work if PbrbgrbphView contbins
    // other PbrbgrbphViews. It won't rbise, but this does not messbge
    // the children views with getNextVisublPositionFrom.
    protected int getClosestPositionTo(int pos, Position.Bibs b, Shbpe b,
                                       int direction, Position.Bibs[] bibsRet,
                                       int rowIndex, int x)
              throws BbdLocbtionException {
        JTextComponent text = (JTextComponent)getContbiner();
        Document doc = getDocument();
        View row = getView(rowIndex);
        int lbstPos = -1;
        // This could be mbde better to check bbckwbrd positions too.
        bibsRet[0] = Position.Bibs.Forwbrd;
        for(int vc = 0, numViews = row.getViewCount(); vc < numViews; vc++) {
            View v = row.getView(vc);
            int stbrt = v.getStbrtOffset();
            boolebn ltr = AbstrbctDocument.isLeftToRight(doc, stbrt, stbrt + 1);
            if(ltr) {
                lbstPos = stbrt;
                for(int end = v.getEndOffset(); lbstPos < end; lbstPos++) {
                    flobt xx = text.modelToView(lbstPos).getBounds().x;
                    if(xx >= x) {
                        while (++lbstPos < end &&
                               text.modelToView(lbstPos).getBounds().x == xx) {
                        }
                        return --lbstPos;
                    }
                }
                lbstPos--;
            }
            else {
                for(lbstPos = v.getEndOffset() - 1; lbstPos >= stbrt;
                    lbstPos--) {
                    flobt xx = text.modelToView(lbstPos).getBounds().x;
                    if(xx >= x) {
                        while (--lbstPos >= stbrt &&
                               text.modelToView(lbstPos).getBounds().x == xx) {
                        }
                        return ++lbstPos;
                    }
                }
                lbstPos++;
            }
        }
        if(lbstPos == -1) {
            return getStbrtOffset();
        }
        return lbstPos;
    }

    /**
     * Determines in which direction the next view lbys.
     * Consider the <code>View</code> bt index n.
     * Typicblly the <code>View</code>s bre lbyed out
     * from left to right, so thbt the <code>View</code>
     * to the EAST will be bt index n + 1, bnd the
     * <code>View</code> to the WEST will be bt index n - 1.
     * In certbin situbtions, such bs with bidirectionbl text,
     * it is possible thbt the <code>View</code> to EAST is not
     * bt index n + 1, but rbther bt index n - 1,
     * or thbt the <code>View</code> to the WEST is not bt
     * index n - 1, but index n + 1.  In this cbse this method
     * would return true, indicbting the <code>View</code>s bre
     * lbyed out in descending order.
     * <p>
     * This will return true if the text is lbyed out right
     * to left bt position, otherwise fblse.
     *
     * @pbrbm position position into the model
     * @pbrbm bibs either <code>Position.Bibs.Forwbrd</code> or
     *          <code>Position.Bibs.Bbckwbrd</code>
     * @return true if the text is lbyed out right to left bt
     *         position, otherwise fblse.
     */
    protected boolebn flipEbstAndWestAtEnds(int position,
                                            Position.Bibs bibs) {
        Document doc = getDocument();
        position = getStbrtOffset();
        return !AbstrbctDocument.isLeftToRight(doc, position, position + 1);
    }

    // --- FlowView methods ---------------------------------------------

    /**
     * Fetches the constrbining spbn to flow bgbinst for
     * the given child index.
     * @pbrbm index the index of the view being queried
     * @return the constrbining spbn for the given view bt
     *  <code>index</code>
     * @since 1.3
     */
    public int getFlowSpbn(int index) {
        View child = getView(index);
        int bdjust = 0;
        if (child instbnceof Row) {
            Row row = (Row) child;
            bdjust = row.getLeftInset() + row.getRightInset();
        }
        return (lbyoutSpbn == Integer.MAX_VALUE) ? lbyoutSpbn
                                                 : (lbyoutSpbn - bdjust);
    }

    /**
     * Fetches the locbtion blong the flow bxis thbt the
     * flow spbn will stbrt bt.
     * @pbrbm index the index of the view being queried
     * @return the locbtion for the given view bt
     *  <code>index</code>
     * @since 1.3
     */
    public int getFlowStbrt(int index) {
        View child = getView(index);
        int bdjust = 0;
        if (child instbnceof Row) {
            Row row = (Row) child;
            bdjust = row.getLeftInset();
        }
        return tbbBbse + bdjust;
    }

    /**
     * Crebte b <code>View</code> thbt should be used to hold b
     * b row's worth of children in b flow.
     * @return the new <code>View</code>
     * @since 1.3
     */
    protected View crebteRow() {
        return new Row(getElement());
    }

    // --- TbbExpbnder methods ------------------------------------------

    /**
     * Returns the next tbb stop position given b reference position.
     * This view implements the tbb coordinbte system, bnd cblls
     * <code>getTbbbedSpbn</code> on the logicbl children in the process
     * of lbyout to determine the desired spbn of the children.  The
     * logicbl children cbn delegbte their tbb expbnsion upwbrd to
     * the pbrbgrbph which knows how to expbnd tbbs.
     * <code>LbbelView</code> is bn exbmple of b view thbt delegbtes
     * its tbb expbnsion needs upwbrd to the pbrbgrbph.
     * <p>
     * This is implemented to try bnd locbte b <code>TbbSet</code>
     * in the pbrbgrbph element's bttribute set.  If one cbn be
     * found, its settings will be used, otherwise b defbult expbnsion
     * will be provided.  The bbse locbtion for for tbb expbnsion
     * is the left inset from the pbrbgrbphs most recent bllocbtion
     * (which is whbt the lbyout of the children is bbsed upon).
     *
     * @pbrbm x the X reference position
     * @pbrbm tbbOffset the position within the text strebm
     *   thbt the tbb occurred bt &gt;= 0
     * @return the trbiling end of the tbb expbnsion &gt;= 0
     * @see TbbSet
     * @see TbbStop
     * @see LbbelView
     */
    public flobt nextTbbStop(flobt x, int tbbOffset) {
        // If the text isn't left justified, offset by 10 pixels!
        if(justificbtion != StyleConstbnts.ALIGN_LEFT)
            return x + 10.0f;
        x -= tbbBbse;
        TbbSet tbbs = getTbbSet();
        if(tbbs == null) {
            // b tbb every 72 pixels.
            return (flobt)(tbbBbse + (((int)x / 72 + 1) * 72));
        }
        TbbStop tbb = tbbs.getTbbAfter(x + .01f);
        if(tbb == null) {
            // no tbb, do b defbult of 5 pixels.
            // Should this cbuse b wrbpping of the line?
            return tbbBbse + x + 5.0f;
        }
        int blignment = tbb.getAlignment();
        int offset;
        switch(blignment) {
        defbult:
        cbse TbbStop.ALIGN_LEFT:
            // Simple cbse, left tbb.
            return tbbBbse + tbb.getPosition();
        cbse TbbStop.ALIGN_BAR:
            // PENDING: whbt does this mebn?
            return tbbBbse + tbb.getPosition();
        cbse TbbStop.ALIGN_RIGHT:
        cbse TbbStop.ALIGN_CENTER:
            offset = findOffsetToChbrbctersInString(tbbChbrs,
                                                    tbbOffset + 1);
            brebk;
        cbse TbbStop.ALIGN_DECIMAL:
            offset = findOffsetToChbrbctersInString(tbbDecimblChbrs,
                                                    tbbOffset + 1);
            brebk;
        }
        if (offset == -1) {
            offset = getEndOffset();
        }
        flobt chbrsSize = getPbrtiblSize(tbbOffset + 1, offset);
        switch(blignment) {
        cbse TbbStop.ALIGN_RIGHT:
        cbse TbbStop.ALIGN_DECIMAL:
            // right bnd decimbl bre trebted the sbme wby, the new
            // position will be the locbtion of the tbb less the
            // pbrtiblSize.
            return tbbBbse + Mbth.mbx(x, tbb.getPosition() - chbrsSize);
        cbse TbbStop.ALIGN_CENTER:
            // Similbr to right, but hblf the pbrtiblSize.
            return tbbBbse + Mbth.mbx(x, tbb.getPosition() - chbrsSize / 2.0f);
        }
        // will never get here!
        return x;
    }

    /**
     * Gets the <code>Tbbset</code> to be used in cblculbting tbbs.
     *
     * @return the <code>TbbSet</code>
     */
    protected TbbSet getTbbSet() {
        return StyleConstbnts.getTbbSet(getElement().getAttributes());
    }

    /**
     * Returns the size used by the views between
     * <code>stbrtOffset</code> bnd <code>endOffset</code>.
     * This uses <code>getPbrtiblView</code> to cblculbte the
     * size if the child view implements the
     * <code>TbbbbleView</code> interfbce. If b
     * size is needed bnd b <code>View</code> does not implement
     * the <code>TbbbbleView</code> interfbce,
     * the <code>preferredSpbn</code> will be used.
     *
     * @pbrbm stbrtOffset the stbrting document offset &gt;= 0
     * @pbrbm endOffset the ending document offset &gt;= stbrtOffset
     * @return the size &gt;= 0
     */
    protected flobt getPbrtiblSize(int stbrtOffset, int endOffset) {
        flobt size = 0.0f;
        int viewIndex;
        int numViews = getViewCount();
        View view;
        int viewEnd;
        int tempEnd;

        // Hbve to sebrch lbyoutPool!
        // PENDING: when PbrbgrbphView supports brebking locbtion
        // into lbyoutPool will hbve to chbnge!
        viewIndex = getElement().getElementIndex(stbrtOffset);
        numViews = lbyoutPool.getViewCount();
        while(stbrtOffset < endOffset && viewIndex < numViews) {
            view = lbyoutPool.getView(viewIndex++);
            viewEnd = view.getEndOffset();
            tempEnd = Mbth.min(endOffset, viewEnd);
            if(view instbnceof TbbbbleView)
                size += ((TbbbbleView)view).getPbrtiblSpbn(stbrtOffset, tempEnd);
            else if(stbrtOffset == view.getStbrtOffset() &&
                    tempEnd == view.getEndOffset())
                size += view.getPreferredSpbn(View.X_AXIS);
            else
                // PENDING: should we hbndle this better?
                return 0.0f;
            stbrtOffset = viewEnd;
        }
        return size;
    }

    /**
     * Finds the next chbrbcter in the document with b chbrbcter in
     * <code>string</code>, stbrting bt offset <code>stbrt</code>. If
     * there bre no chbrbcters found, -1 will be returned.
     *
     * @pbrbm string the string of chbrbcters
     * @pbrbm stbrt where to stbrt in the model &gt;= 0
     * @return the document offset, or -1 if no chbrbcters found
     */
    protected int findOffsetToChbrbctersInString(chbr[] string,
                                                 int stbrt) {
        int stringLength = string.length;
        int end = getEndOffset();
        Segment seg = new Segment();
        try {
            getDocument().getText(stbrt, end - stbrt, seg);
        } cbtch (BbdLocbtionException ble) {
            return -1;
        }
        for(int counter = seg.offset, mbxCounter = seg.offset + seg.count;
            counter < mbxCounter; counter++) {
            chbr currentChbr = seg.brrby[counter];
            for(int subCounter = 0; subCounter < stringLength;
                subCounter++) {
                if(currentChbr == string[subCounter])
                    return counter - seg.offset + stbrt;
            }
        }
        // No mbtch.
        return -1;
    }

    /**
     * Returns where the tbbs bre cblculbted from.
     * @return where tbbs bre cblculbted from
     */
    protected flobt getTbbBbse() {
        return (flobt)tbbBbse;
    }

    // ---- View methods ----------------------------------------------------

    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.  This is implemented to delegbte to the superclbss
     * bfter stbshing the bbse coordinbte for tbb cblculbtions.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();
        tbbBbse = blloc.x + getLeftInset();
        super.pbint(g, b);

        // line with the negbtive firstLineIndent vblue needs
        // specibl hbndling
        if (firstLineIndent < 0) {
            Shbpe sh = getChildAllocbtion(0, b);
            if ((sh != null) &&  sh.intersects(blloc)) {
                int x = blloc.x + getLeftInset() + firstLineIndent;
                int y = blloc.y + getTopInset();

                Rectbngle clip = g.getClipBounds();
                tempRect.x = x + getOffset(X_AXIS, 0);
                tempRect.y = y + getOffset(Y_AXIS, 0);
                tempRect.width = getSpbn(X_AXIS, 0) - firstLineIndent;
                tempRect.height = getSpbn(Y_AXIS, 0);
                if (tempRect.intersects(clip)) {
                    tempRect.x = tempRect.x - firstLineIndent;
                    pbintChild(g, tempRect, 0);
                }
            }
        }
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  This is implemented to give the blignment to the
     * center of the first row blong the y bxis, bnd the defbult
     * blong the x bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *   <code>View.Y_AXIS</code>
     * @return the desired blignment.  This should be b vblue
     *   between 0.0 bnd 1.0 inclusive, where 0 indicbtes blignment bt the
     *   origin bnd 1.0 indicbtes blignment to the full spbn
     *   bwby from the origin.  An blignment of 0.5 would be the
     *   center of the view.
     */
    public flobt getAlignment(int bxis) {
        switch (bxis) {
        cbse Y_AXIS:
            flobt b = 0.5f;
            if (getViewCount() != 0) {
                int pbrbgrbphSpbn = (int) getPreferredSpbn(View.Y_AXIS);
                View v = getView(0);
                int rowSpbn = (int) v.getPreferredSpbn(View.Y_AXIS);
                b = (pbrbgrbphSpbn != 0) ? ((flobt)(rowSpbn / 2)) / pbrbgrbphSpbn : 0;
            }
            return b;
        cbse X_AXIS:
            return 0.5f;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Brebks this view on the given bxis bt the given length.
     * <p>
     * <code>PbrbgrbphView</code> instbnces bre brebkbble
     * blong the <code>Y_AXIS</code> only, bnd only if
     * <code>len</code> is bfter the first line.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code>
     *  or <code>View.Y_AXIS</code>
     * @pbrbm len specifies where b potentibl brebk is desired
     *  blong the given bxis &gt;= 0
     * @pbrbm b the current bllocbtion of the view
     * @return the frbgment of the view thbt represents the
     *  given spbn, if the view cbn be broken; if the view
     *  doesn't support brebking behbvior, the view itself is
     *  returned
     * @see View#brebkView
     */
    public View brebkView(int bxis, flobt len, Shbpe b) {
        if(bxis == View.Y_AXIS) {
            if(b != null) {
                Rectbngle blloc = b.getBounds();
                setSize(blloc.width, blloc.height);
            }
            // Determine whbt row to brebk on.

            // PENDING(prinz) bdd brebk support
            return this;
        }
        return this;
    }

    /**
     * Gets the brebk weight for b given locbtion.
     * <p>
     * <code>PbrbgrbphView</code> instbnces bre brebkbble
     * blong the <code>Y_AXIS</code> only, bnd only if
     * <code>len</code> is bfter the first row.  If the length
     * is less thbn one row, b vblue of <code>BbdBrebkWeight</code>
     * is returned.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code>
     *  or <code>View.Y_AXIS</code>
     * @pbrbm len specifies where b potentibl brebk is desired &gt;= 0
     * @return b vblue indicbting the bttrbctiveness of brebking here;
     *  either <code>GoodBrebkWeight</code> or <code>BbdBrebkWeight</code>
     * @see View#getBrebkWeight
     */
    public int getBrebkWeight(int bxis, flobt len) {
        if(bxis == View.Y_AXIS) {
            // PENDING(prinz) mbke this return b rebsonbble vblue
            // when pbrbgrbph brebking support is re-implemented.
            // If less thbn one row, bbd weight vblue should be
            // returned.
            //return GoodBrebkWeight;
            return BbdBrebkWeight;
        }
        return BbdBrebkWeight;
    }

    /**
     * Cblculbte the needs for the pbrbgrbph blong the minor bxis.
     *
     * <p>This uses size requirements of the superclbss, modified to tbke into
     * bccount the non-brebkbble brebs bt the bdjbcent views edges.  The minimbl
     * size requirements for such views should be no less thbn the sum of bll
     * bdjbcent frbgments.</p>
     *
     * <p>If the {@code bxis} pbrbmeter is neither {@code View.X_AXIS} nor
     * {@code View.Y_AXIS}, {@link IllegblArgumentException} is thrown.  If the
     * {@code r} pbrbmeter is {@code null,} b new {@code SizeRequirements}
     * object is crebted, otherwise the supplied {@code SizeRequirements}
     * object is returned.</p>
     *
     * @pbrbm bxis  the minor bxis
     * @pbrbm r     the input {@code SizeRequirements} object
     * @return      the new or bdjusted {@code SizeRequirements} object
     * @throws IllegblArgumentException  if the {@code bxis} pbrbmeter is invblid
     */
    @Override
    protected SizeRequirements cblculbteMinorAxisRequirements(int bxis,
                                                        SizeRequirements r) {
        r = super.cblculbteMinorAxisRequirements(bxis, r);

        flobt min = 0;
        flobt glue = 0;
        int n = getLbyoutViewCount();
        for (int i = 0; i < n; i++) {
            View v = getLbyoutView(i);
            flobt spbn = v.getMinimumSpbn(bxis);
            if (v.getBrebkWeight(bxis, 0, v.getMbximumSpbn(bxis)) > View.BbdBrebkWeight) {
                // find the longest non-brebkbble frbgments bt the view edges
                int p0 = v.getStbrtOffset();
                int p1 = v.getEndOffset();
                flobt stbrt = findEdgeSpbn(v, bxis, p0, p0, p1);
                flobt end = findEdgeSpbn(v, bxis, p1, p0, p1);
                glue += stbrt;
                min = Mbth.mbx(min, Mbth.mbx(spbn, glue));
                glue = end;
            } else {
                // non-brebkbble view
                glue += spbn;
                min = Mbth.mbx(min, glue);
            }
        }
        r.minimum = Mbth.mbx(r.minimum, (int) min);
        r.preferred = Mbth.mbx(r.minimum, r.preferred);
        r.mbximum = Mbth.mbx(r.preferred, r.mbximum);

        return r;
    }

    /**
     * Binbry sebrch for the longest non-brebkbble frbgment bt the view edge.
     */
    privbte flobt findEdgeSpbn(View v, int bxis, int fp, int p0, int p1) {
        int len = p1 - p0;
        if (len <= 1) {
            // further frbgmentbtion is not possible
            return v.getMinimumSpbn(bxis);
        } else {
            int mid = p0 + len / 2;
            boolebn stbrtEdge = mid > fp;
            // initibl view is brebkbble hence must support frbgmentbtion
            View f = stbrtEdge ?
                v.crebteFrbgment(fp, mid) : v.crebteFrbgment(mid, fp);
            boolebn brebkbble = f.getBrebkWeight(
                    bxis, 0, f.getMbximumSpbn(bxis)) > View.BbdBrebkWeight;
            if (brebkbble == stbrtEdge) {
                p1 = mid;
            } else {
                p0 = mid;
            }
            return findEdgeSpbn(f, bxis, fp, p0, p1);
        }
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the
     *  bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        // updbte bny property settings stored, bnd lbyout should be
        // recomputed
        setPropertiesFromAttributes();
        lbyoutChbnged(X_AXIS);
        lbyoutChbnged(Y_AXIS);
        super.chbngedUpdbte(chbnges, b, f);
    }


    // --- vbribbles -----------------------------------------------

    privbte int justificbtion;
    privbte flobt lineSpbcing;
    /** Indentbtion for the first line, from the left inset. */
    protected int firstLineIndent = 0;

    /**
     * Used by the TbbExpbnder functionblity to determine
     * where to bbse the tbb cblculbtions.  This is bbsicblly
     * the locbtion of the left side of the pbrbgrbph.
     */
    privbte int tbbBbse;

    /**
     * Used to crebte bn i18n-bbsed lbyout strbtegy
     */
    stbtic Clbss<?> i18nStrbtegy;

    /** Used for sebrching for b tbb. */
    stbtic chbr[] tbbChbrs;
    /** Used for sebrching for b tbb or decimbl chbrbcter. */
    stbtic chbr[] tbbDecimblChbrs;

    stbtic {
        tbbChbrs = new chbr[1];
        tbbChbrs[0] = '\t';
        tbbDecimblChbrs = new chbr[2];
        tbbDecimblChbrs[0] = '\t';
        tbbDecimblChbrs[1] = '.';
    }

    /**
     * Internblly crebted view thbt hbs the purpose of holding
     * the views thbt represent the children of the pbrbgrbph
     * thbt hbve been brrbnged in rows.
     */
    clbss Row extends BoxView {

        Row(Element elem) {
            super(elem, View.X_AXIS);
        }

        /**
         * This is reimplemented to do nothing since the
         * pbrbgrbph fills in the row with its needed
         * children.
         */
        protected void lobdChildren(ViewFbctory f) {
        }

        /**
         * Fetches the bttributes to use when rendering.  This view
         * isn't directly responsible for bn element so it returns
         * the outer clbsses bttributes.
         */
        public AttributeSet getAttributes() {
            View p = getPbrent();
            return (p != null) ? p.getAttributes() : null;
        }

        public flobt getAlignment(int bxis) {
            if (bxis == View.X_AXIS) {
                switch (justificbtion) {
                cbse StyleConstbnts.ALIGN_LEFT:
                    return 0;
                cbse StyleConstbnts.ALIGN_RIGHT:
                    return 1;
                cbse StyleConstbnts.ALIGN_CENTER:
                    return 0.5f;
                cbse StyleConstbnts.ALIGN_JUSTIFIED:
                    flobt rv = 0.5f;
                    //if we cbn justifiy the content blwbys blign to
                    //the left.
                    if (isJustifibbleDocument()) {
                        rv = 0f;
                    }
                    return rv;
                }
            }
            return super.getAlignment(bxis);
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.  This is
         * implemented to let the superclbss find the position blong
         * the mbjor bxis bnd the bllocbtion of the row is used
         * blong the minor bxis, so thbt even though the children
         * bre different heights they bll get the sbme cbret height.
         *
         * @pbrbm pos the position to convert
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position
         * @exception BbdLocbtionException  if the given position does not represent b
         *   vblid locbtion in the bssocibted document
         * @see View#modelToView
         */
        public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
            Rectbngle r = b.getBounds();
            View v = getViewAtPosition(pos, r);
            if ((v != null) && (!v.getElement().isLebf())) {
                // Don't bdjust the height if the view represents b brbnch.
                return super.modelToView(pos, b, b);
            }
            r = b.getBounds();
            int height = r.height;
            int y = r.y;
            Shbpe loc = super.modelToView(pos, b, b);
            r = loc.getBounds();
            r.height = height;
            r.y = y;
            return r;
        }

        /**
         * Rbnge represented by b row in the pbrbgrbph is only
         * b subset of the totbl rbnge of the pbrbgrbph element.
         * @see View#getRbnge
         */
        public int getStbrtOffset() {
            int offs = Integer.MAX_VALUE;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                offs = Mbth.min(offs, v.getStbrtOffset());
            }
            return offs;
        }

        public int getEndOffset() {
            int offs = 0;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                offs = Mbth.mbx(offs, v.getEndOffset());
            }
            return offs;
        }

        /**
         * Perform lbyout for the minor bxis of the box (i.e. the
         * bxis orthogonbl to the bxis thbt it represents).  The results
         * of the lbyout should be plbced in the given brrbys which represent
         * the bllocbtions to the children blong the minor bxis.
         * <p>
         * This is implemented to do b bbseline lbyout of the children
         * by cblling BoxView.bbselineLbyout.
         *
         * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
         *  would be used to lbyout the children.
         * @pbrbm bxis the bxis being lbyed out.
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views.  This is b return vblue bnd is
         *  filled in by the implementbtion of this method.
         * @pbrbm spbns the spbn of ebch child view.  This is b return
         *  vblue bnd is filled in by the implementbtion of this method.
         * @return the offset bnd spbn for ebch child view in the
         *  offsets bnd spbns pbrbmeters
         */
        protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
            bbselineLbyout(tbrgetSpbn, bxis, offsets, spbns);
        }

        protected SizeRequirements cblculbteMinorAxisRequirements(int bxis,
                                                                  SizeRequirements r) {
            return bbselineRequirements(bxis, r);
        }


        privbte boolebn isLbstRow() {
            View pbrent;
            return ((pbrent = getPbrent()) == null
                    || this == pbrent.getView(pbrent.getViewCount() - 1));
        }

        privbte boolebn isBrokenRow() {
            boolebn rv = fblse;
            int viewsCount = getViewCount();
            if (viewsCount > 0) {
                View lbstView = getView(viewsCount - 1);
                if (lbstView.getBrebkWeight(X_AXIS, 0, 0) >=
                      ForcedBrebkWeight) {
                    rv = true;
                }
            }
            return rv;
        }

        privbte boolebn isJustifibbleDocument() {
            return (! Boolebn.TRUE.equbls(getDocument().getProperty(
                          AbstrbctDocument.I18NProperty)));
        }

        /**
         * Whether we need to justify this {@code Row}.
         * At this time (jdk1.6) we support justificbtion on for non
         * 18n text.
         *
         * @return {@code true} if this {@code Row} should be justified.
         */
        privbte boolebn isJustifyEnbbled() {
            boolebn ret = (justificbtion == StyleConstbnts.ALIGN_JUSTIFIED);

            //no justificbtion for i18n documents
            ret = ret && isJustifibbleDocument();

            //no justificbtion for the lbst row
            ret = ret && ! isLbstRow();

            //no justificbtion for the broken rows
            ret = ret && ! isBrokenRow();

            return ret;
        }


        //Cblls super method bfter setting spbceAddon to 0.
        //Justificbtion should not bffect MbjorAxisRequirements
        @Override
        protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis,
                SizeRequirements r) {
            int oldJustficbtionDbtb[] = justificbtionDbtb;
            justificbtionDbtb = null;
            SizeRequirements ret = super.cblculbteMbjorAxisRequirements(bxis, r);
            if (isJustifyEnbbled()) {
                justificbtionDbtb = oldJustficbtionDbtb;
            }
            return ret;
        }

        @Override
        protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis,
                                       int[] offsets, int[] spbns) {
            int oldJustficbtionDbtb[] = justificbtionDbtb;
            justificbtionDbtb = null;
            super.lbyoutMbjorAxis(tbrgetSpbn, bxis, offsets, spbns);
            if (! isJustifyEnbbled()) {
                return;
            }

            int currentSpbn = 0;
            for (int spbn : spbns) {
                currentSpbn += spbn;
            }
            if (currentSpbn == tbrgetSpbn) {
                //no need to justify
                return;
            }

            // we justify text by enlbrging spbces by the {@code spbceAddon}.
            // justificbtion is stbrted to the right of the rightmost TAB.
            // lebding bnd trbiling spbces bre not extendbble.
            //
            // GlyphPbinter1 uses
            // justificbtionDbtb
            // for bll pbinting bnd mebsurement.

            int extendbbleSpbces = 0;
            int stbrtJustifibbleContent = -1;
            int endJustifibbleContent = -1;
            int lbstLebdingSpbces = 0;

            int rowStbrtOffset = getStbrtOffset();
            int rowEndOffset = getEndOffset();
            int spbceMbp[] = new int[rowEndOffset - rowStbrtOffset];
            Arrbys.fill(spbceMbp, 0);
            for (int i = getViewCount() - 1; i >= 0 ; i--) {
                View view = getView(i);
                if (view instbnceof GlyphView) {
                    GlyphView.JustificbtionInfo justificbtionInfo =
                        ((GlyphView) view).getJustificbtionInfo(rowStbrtOffset);
                    finbl int viewStbrtOffset = view.getStbrtOffset();
                    finbl int offset = viewStbrtOffset - rowStbrtOffset;
                    for (int j = 0; j < justificbtionInfo.spbceMbp.length(); j++) {
                        if (justificbtionInfo.spbceMbp.get(j)) {
                            spbceMbp[j + offset] = 1;
                        }
                    }
                    if (stbrtJustifibbleContent > 0) {
                        if (justificbtionInfo.end >= 0) {
                            extendbbleSpbces += justificbtionInfo.trbilingSpbces;
                        } else {
                            lbstLebdingSpbces += justificbtionInfo.trbilingSpbces;
                        }
                    }
                    if (justificbtionInfo.stbrt >= 0) {
                        stbrtJustifibbleContent =
                            justificbtionInfo.stbrt + viewStbrtOffset;
                        extendbbleSpbces += lbstLebdingSpbces;
                    }
                    if (justificbtionInfo.end >= 0
                          && endJustifibbleContent < 0) {
                        endJustifibbleContent =
                            justificbtionInfo.end + viewStbrtOffset;
                    }
                    extendbbleSpbces += justificbtionInfo.contentSpbces;
                    lbstLebdingSpbces = justificbtionInfo.lebdingSpbces;
                    if (justificbtionInfo.hbsTbb) {
                        brebk;
                    }
                }
            }
            if (extendbbleSpbces <= 0) {
                //there is nothing we cbn do to justify
                return;
            }
            int bdjustment = (tbrgetSpbn - currentSpbn);
            int spbceAddon = (extendbbleSpbces > 0)
                ?  bdjustment / extendbbleSpbces
                : 0;
            int spbceAddonLeftoverEnd = -1;
            for (int i = stbrtJustifibbleContent - rowStbrtOffset,
                     leftover = bdjustment - spbceAddon * extendbbleSpbces;
                     leftover > 0;
                     leftover -= spbceMbp[i],
                     i++) {
                spbceAddonLeftoverEnd = i;
            }
            if (spbceAddon > 0 || spbceAddonLeftoverEnd >= 0) {
                justificbtionDbtb = (oldJustficbtionDbtb != null)
                    ? oldJustficbtionDbtb
                    : new int[END_JUSTIFIABLE + 1];
                justificbtionDbtb[SPACE_ADDON] = spbceAddon;
                justificbtionDbtb[SPACE_ADDON_LEFTOVER_END] =
                    spbceAddonLeftoverEnd;
                justificbtionDbtb[START_JUSTIFIABLE] =
                    stbrtJustifibbleContent - rowStbrtOffset;
                justificbtionDbtb[END_JUSTIFIABLE] =
                    endJustifibbleContent - rowStbrtOffset;
                super.lbyoutMbjorAxis(tbrgetSpbn, bxis, offsets, spbns);
            }
        }

        //for justified row we bssume the mbximum horizontbl spbn
        //is MAX_VALUE.
        @Override
        public flobt getMbximumSpbn(int bxis) {
            flobt ret;
            if (View.X_AXIS == bxis
                  && isJustifyEnbbled()) {
                ret = Flobt.MAX_VALUE;
            } else {
              ret = super.getMbximumSpbn(bxis);
            }
            return ret;
        }

        /**
         * Fetches the child view index representing the given position in
         * the model.
         *
         * @pbrbm pos the position &gt;= 0
         * @return  index of the view representing the given position, or
         *   -1 if no view represents thbt position
         */
        protected int getViewIndexAtPosition(int pos) {
            // This is expensive, but bre views bre not necessbrily lbyed
            // out in model order.
            if(pos < getStbrtOffset() || pos >= getEndOffset())
                return -1;
            for(int counter = getViewCount() - 1; counter >= 0; counter--) {
                View v = getView(counter);
                if(pos >= v.getStbrtOffset() &&
                   pos < v.getEndOffset()) {
                    return counter;
                }
            }
            return -1;
        }

        /**
         * Gets the left inset.
         *
         * @return the inset
         */
        protected short getLeftInset() {
            View pbrentView;
            int bdjustment = 0;
            if ((pbrentView = getPbrent()) != null) { //use firstLineIdent for the first row
                if (this == pbrentView.getView(0)) {
                    bdjustment = firstLineIndent;
                }
            }
            return (short)(super.getLeftInset() + bdjustment);
        }

        protected short getBottomInset() {
            return (short)(super.getBottomInset() +
                           ((minorRequest != null) ? minorRequest.preferred : 0) *
                           lineSpbcing);
        }

        finbl stbtic int SPACE_ADDON = 0;
        finbl stbtic int SPACE_ADDON_LEFTOVER_END = 1;
        finbl stbtic int START_JUSTIFIABLE = 2;
        //this should be the lbst index in justificbtionDbtb
        finbl stbtic int END_JUSTIFIABLE = 3;

        int justificbtionDbtb[] = null;
    }

}
