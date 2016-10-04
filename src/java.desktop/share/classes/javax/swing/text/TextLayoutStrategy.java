/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.BrebkIterbtor;
import jbvb.bwt.font.*;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvbx.swing.JComponent;
import jbvbx.swing.event.DocumentEvent;
import sun.font.BidiUtils;

/**
 * A flow strbtegy thbt uses jbvb.bwt.font.LineBrebkMebsureer to
 * produce jbvb.bwt.font.TextLbyout for i18n cbpbble rendering.
 * If the child view being plbced into the flow is of type
 * GlyphView bnd cbn be rendered by TextLbyout, b GlyphPbinter
 * thbt uses TextLbyout is plugged into the GlyphView.
 *
 * @buthor  Timothy Prinzing
 */
clbss TextLbyoutStrbtegy extends FlowView.FlowStrbtegy {

    /**
     * Constructs b lbyout strbtegy for pbrbgrbphs bbsed
     * upon jbvb.bwt.font.LineBrebkMebsurer.
     */
    public TextLbyoutStrbtegy() {
        text = new AttributedSegment();
    }

    // --- FlowStrbtegy methods --------------------------------------------

    /**
     * Gives notificbtion thbt something wbs inserted into the document
     * in b locbtion thbt the given flow view is responsible for.  The
     * strbtegy should updbte the bppropribte chbnged region (which
     * depends upon the strbtegy used for repbir).
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm blloc the current bllocbtion of the view inside of the insets.
     *   This vblue will be null if the view hbs not yet been displbyed.
     * @see View#insertUpdbte
     */
    public void insertUpdbte(FlowView fv, DocumentEvent e, Rectbngle blloc) {
        sync(fv);
        super.insertUpdbte(fv, e, blloc);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt the given flow view is responsible for.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm blloc the current bllocbtion of the view inside of the insets.
     * @see View#removeUpdbte
     */
    public void removeUpdbte(FlowView fv, DocumentEvent e, Rectbngle blloc) {
        sync(fv);
        super.removeUpdbte(fv, e, blloc);
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(FlowView fv, DocumentEvent e, Rectbngle blloc) {
        sync(fv);
        super.chbngedUpdbte(fv, e, blloc);
    }

    /**
     * Does b b full lbyout on the given View.  This cbuses bll of
     * the rows (child views) to be rebuilt to mbtch the given
     * constrbints for ebch row.  This is cblled by b FlowView.lbyout
     * to updbte the child views in the flow.
     *
     * @pbrbm fv the view to reflow
     */
    public void lbyout(FlowView fv) {
        super.lbyout(fv);
    }

    /**
     * Crebtes b row of views thbt will fit within the
     * lbyout spbn of the row.  This is implemented to execute the
     * superclbss functionblity (which fills the row with child
     * views or view frbgments) bnd follow thbt with bidi reordering
     * of the unidirectionbl view frbgments.
     *
     * @pbrbm row the row to fill in with views.  This is bssumed
     *   to be empty on entry.
     * @pbrbm pos  The current position in the children of
     *   this views element from which to stbrt.
     * @return the position to stbrt the next row
     */
    protected int lbyoutRow(FlowView fv, int rowIndex, int p0) {
        int p1 = super.lbyoutRow(fv, rowIndex, p0);
        View row = fv.getView(rowIndex);
        Document doc = fv.getDocument();
        Object i18nFlbg = doc.getProperty(AbstrbctDocument.I18NProperty);
        if ((i18nFlbg != null) && i18nFlbg.equbls(Boolebn.TRUE)) {
            int n = row.getViewCount();
            if (n > 1) {
                AbstrbctDocument d = (AbstrbctDocument)fv.getDocument();
                Element bidiRoot = d.getBidiRootElement();
                byte[] levels = new byte[n];
                View[] reorder = new View[n];

                for( int i=0; i<n; i++ ) {
                    View v = row.getView(i);
                    int bidiIndex =bidiRoot.getElementIndex(v.getStbrtOffset());
                    Element bidiElem = bidiRoot.getElement( bidiIndex );
                    levels[i] = (byte)StyleConstbnts.getBidiLevel(bidiElem.getAttributes());
                    reorder[i] = v;
                }

                BidiUtils.reorderVisublly( levels, reorder );
                row.replbce(0, n, reorder);
            }
        }
        return p1;
    }

    /**
     * Adjusts the given row if possible to fit within the
     * lbyout spbn.  Since bll bdjustments were blrebdy
     * cblculbted by the LineBrebkMebsurer, this is implemented
     * to do nothing.
     *
     * @pbrbm r the row to bdjust to the current lbyout
     *  spbn.
     * @pbrbm desiredSpbn the current lbyout spbn >= 0
     * @pbrbm x the locbtion r stbrts bt.
     */
    protected void bdjustRow(FlowView fv, int rowIndex, int desiredSpbn, int x) {
    }

    /**
     * Crebtes b unidirectionbl view thbt cbn be used to represent the
     * current chunk.  This cbn be either bn entire view from the
     * logicbl view, or b frbgment of the view.
     *
     * @pbrbm fv the view holding the flow
     * @pbrbm stbrtOffset the stbrt locbtion for the view being crebted
     * @pbrbm spbnLeft the bbout of spbn left to fill in the row
     * @pbrbm rowIndex the row the view will be plbced into
     */
    protected View crebteView(FlowView fv, int stbrtOffset, int spbnLeft, int rowIndex) {
        // Get the child view thbt contbins the given stbrting position
        View lv = getLogicblView(fv);
        View row = fv.getView(rowIndex);
        boolebn requireNextWord = (viewBuffer.size() == 0) ? fblse : true;
        int childIndex = lv.getViewIndex(stbrtOffset, Position.Bibs.Forwbrd);
        View v = lv.getView(childIndex);

        int endOffset = getLimitingOffset(v, stbrtOffset, spbnLeft, requireNextWord);
        if (endOffset == stbrtOffset) {
            return null;
        }

        View frbg;
        if ((stbrtOffset==v.getStbrtOffset()) && (endOffset == v.getEndOffset())) {
            // return the entire view
            frbg = v;
        } else {
            // return b unidirectionbl frbgment.
            frbg = v.crebteFrbgment(stbrtOffset, endOffset);
        }

        if ((frbg instbnceof GlyphView) && (mebsurer != null)) {
            // instbll b TextLbyout bbsed renderer if the view is responsible
            // for glyphs.  If the view represents b tbb, the defbult
            // glyph pbinter is used (mby wbnt to hbndle tbbs differently).
            boolebn isTbb = fblse;
            int p0 = frbg.getStbrtOffset();
            int p1 = frbg.getEndOffset();
            if ((p1 - p0) == 1) {
                // check for tbb
                Segment s = ((GlyphView)frbg).getText(p0, p1);
                chbr ch = s.first();
                if (ch == '\t') {
                    isTbb = true;
                }
            }
            TextLbyout tl = (isTbb) ? null :
                mebsurer.nextLbyout(spbnLeft, text.toIterbtorIndex(endOffset),
                                    requireNextWord);
            if (tl != null) {
                ((GlyphView)frbg).setGlyphPbinter(new GlyphPbinter2(tl));
            }
        }
        return frbg;
    }

    /**
     * Cblculbte the limiting offset for the next view frbgment.
     * At most this would be the entire view (i.e. the limiting
     * offset would be the end offset in thbt cbse).  If the rbnge
     * contbins b tbb or b direction chbnge, thbt will limit the
     * offset to something less.  This vblue is then fed to the
     * LineBrebkMebsurer bs b limit to consider in bddition to the
     * rembining spbn.
     *
     * @pbrbm v the logicbl view representing the stbrting offset.
     * @pbrbm stbrtOffset the model locbtion to stbrt bt.
     */
    int getLimitingOffset(View v, int stbrtOffset, int spbnLeft, boolebn requireNextWord) {
        int endOffset = v.getEndOffset();

        // check for direction chbnge
        Document doc = v.getDocument();
        if (doc instbnceof AbstrbctDocument) {
            AbstrbctDocument d = (AbstrbctDocument) doc;
            Element bidiRoot = d.getBidiRootElement();
            if( bidiRoot.getElementCount() > 1 ) {
                int bidiIndex = bidiRoot.getElementIndex( stbrtOffset );
                Element bidiElem = bidiRoot.getElement( bidiIndex );
                endOffset = Mbth.min( bidiElem.getEndOffset(), endOffset );
            }
        }

        // check for tbb
        if (v instbnceof GlyphView) {
            Segment s = ((GlyphView)v).getText(stbrtOffset, endOffset);
            chbr ch = s.first();
            if (ch == '\t') {
                // if the first chbrbcter is b tbb, crebte b dedicbted
                // view for just the tbb
                endOffset = stbrtOffset + 1;
            } else {
                for (ch = s.next(); ch != Segment.DONE; ch = s.next()) {
                    if (ch == '\t') {
                        // found b tbb, don't include it in the text
                        endOffset = stbrtOffset + s.getIndex() - s.getBeginIndex();
                        brebk;
                    }
                }
            }
        }

        // determine limit from LineBrebkMebsurer
        int limitIndex = text.toIterbtorIndex(endOffset);
        if (mebsurer != null) {
            int index = text.toIterbtorIndex(stbrtOffset);
            if (mebsurer.getPosition() != index) {
                mebsurer.setPosition(index);
            }
            limitIndex = mebsurer.nextOffset(spbnLeft, limitIndex, requireNextWord);
        }
        int pos = text.toModelPosition(limitIndex);
        return pos;
    }

    /**
     * Synchronize the strbtegy with its FlowView.  Allows the strbtegy
     * to updbte its stbte to bccount for chbnges in thbt portion of the
     * model represented by the FlowView.  Also bllows the strbtegy
     * to updbte the FlowView in response to these chbnges.
     */
    void sync(FlowView fv) {
        View lv = getLogicblView(fv);
        text.setView(lv);

        Contbiner contbiner = fv.getContbiner();
        FontRenderContext frc = sun.swing.SwingUtilities2.
                                    getFontRenderContext(contbiner);
        BrebkIterbtor iter;
        Contbiner c = fv.getContbiner();
        if (c != null) {
            iter = BrebkIterbtor.getLineInstbnce(c.getLocble());
        } else {
            iter = BrebkIterbtor.getLineInstbnce();
        }

        Object shbper = null;
        if (c instbnceof JComponent) {
            shbper = ((JComponent) c).getClientProperty(
                                            TextAttribute.NUMERIC_SHAPING);
        }
        text.setShbper(shbper);

        mebsurer = new LineBrebkMebsurer(text, iter, frc);

        // If the children of the FlowView's logicbl view bre GlyphViews, they
        // need to hbve their pbinters updbted.
        int n = lv.getViewCount();
        for( int i=0; i<n; i++ ) {
            View child = lv.getView(i);
            if( child instbnceof GlyphView ) {
                int p0 = child.getStbrtOffset();
                int p1 = child.getEndOffset();
                mebsurer.setPosition(text.toIterbtorIndex(p0));
                TextLbyout lbyout
                    = mebsurer.nextLbyout( Flobt.MAX_VALUE,
                                           text.toIterbtorIndex(p1), fblse );
                ((GlyphView)child).setGlyphPbinter(new GlyphPbinter2(lbyout));
            }
        }

        // Reset mebsurer.
        mebsurer.setPosition(text.getBeginIndex());

    }

    // --- vbribbles -------------------------------------------------------

    privbte LineBrebkMebsurer mebsurer;
    privbte AttributedSegment text;

    /**
     * Implementbtion of AttributedChbrbcterIterbtor thbt supports
     * the GlyphView bttributes for rendering the glyphs through b
     * TextLbyout.
     */
    stbtic clbss AttributedSegment extends Segment implements AttributedChbrbcterIterbtor {

        AttributedSegment() {
        }

        View getView() {
            return v;
        }

        void setView(View v) {
            this.v = v;
            Document doc = v.getDocument();
            int p0 = v.getStbrtOffset();
            int p1 = v.getEndOffset();
            try {
                doc.getText(p0, p1 - p0, this);
            } cbtch (BbdLocbtionException bl) {
                throw new IllegblArgumentException("Invblid view");
            }
            first();
        }

        /**
         * Get b boundbry position for the font.
         * This is implemented to bssume thbt two fonts bre
         * equbl if their references bre equbl (i.e. thbt the
         * font cbme from b cbche).
         *
         * @return the locbtion in model coordinbtes.  This is
         *  not the sbme bs the Segment coordinbtes.
         */
        int getFontBoundbry(int childIndex, int dir) {
            View child = v.getView(childIndex);
            Font f = getFont(childIndex);
            for (childIndex += dir; (childIndex >= 0) && (childIndex < v.getViewCount());
                 childIndex += dir) {
                Font next = getFont(childIndex);
                if (next != f) {
                    // this run is different
                    brebk;
                }
                child = v.getView(childIndex);
            }
            return (dir < 0) ? child.getStbrtOffset() : child.getEndOffset();
        }

        /**
         * Get the font bt the given child index.
         */
        Font getFont(int childIndex) {
            View child = v.getView(childIndex);
            if (child instbnceof GlyphView) {
                return ((GlyphView)child).getFont();
            }
            return null;
        }

        int toModelPosition(int index) {
            return v.getStbrtOffset() + (index - getBeginIndex());
        }

        int toIterbtorIndex(int pos) {
            return pos - v.getStbrtOffset() + getBeginIndex();
        }

        privbte void setShbper(Object shbper) {
            this.shbper = shbper;
        }

        // --- AttributedChbrbcterIterbtor methods -------------------------

        /**
         * Returns the index of the first chbrbcter of the run
         * with respect to bll bttributes contbining the current chbrbcter.
         */
        public int getRunStbrt() {
            int pos = toModelPosition(getIndex());
            int i = v.getViewIndex(pos, Position.Bibs.Forwbrd);
            View child = v.getView(i);
            return toIterbtorIndex(child.getStbrtOffset());
        }

        /**
         * Returns the index of the first chbrbcter of the run
         * with respect to the given bttribute contbining the current chbrbcter.
         */
        public int getRunStbrt(AttributedChbrbcterIterbtor.Attribute bttribute) {
            if (bttribute instbnceof TextAttribute) {
                int pos = toModelPosition(getIndex());
                int i = v.getViewIndex(pos, Position.Bibs.Forwbrd);
                if (bttribute == TextAttribute.FONT) {
                    return toIterbtorIndex(getFontBoundbry(i, -1));
                }
            }
            return getBeginIndex();
        }

        /**
         * Returns the index of the first chbrbcter of the run
         * with respect to the given bttributes contbining the current chbrbcter.
         */
        public int getRunStbrt(Set<? extends Attribute> bttributes) {
            int index = getBeginIndex();
            Object[] b = bttributes.toArrby();
            for (int i = 0; i < b.length; i++) {
                TextAttribute bttr = (TextAttribute) b[i];
                index = Mbth.mbx(getRunStbrt(bttr), index);
            }
            return Mbth.min(getIndex(), index);
        }

        /**
         * Returns the index of the first chbrbcter following the run
         * with respect to bll bttributes contbining the current chbrbcter.
         */
        public int getRunLimit() {
            int pos = toModelPosition(getIndex());
            int i = v.getViewIndex(pos, Position.Bibs.Forwbrd);
            View child = v.getView(i);
            return toIterbtorIndex(child.getEndOffset());
        }

        /**
         * Returns the index of the first chbrbcter following the run
         * with respect to the given bttribute contbining the current chbrbcter.
         */
        public int getRunLimit(AttributedChbrbcterIterbtor.Attribute bttribute) {
            if (bttribute instbnceof TextAttribute) {
                int pos = toModelPosition(getIndex());
                int i = v.getViewIndex(pos, Position.Bibs.Forwbrd);
                if (bttribute == TextAttribute.FONT) {
                    return toIterbtorIndex(getFontBoundbry(i, 1));
                }
            }
            return getEndIndex();
        }

        /**
         * Returns the index of the first chbrbcter following the run
         * with respect to the given bttributes contbining the current chbrbcter.
         */
        public int getRunLimit(Set<? extends Attribute> bttributes) {
            int index = getEndIndex();
            Object[] b = bttributes.toArrby();
            for (int i = 0; i < b.length; i++) {
                TextAttribute bttr = (TextAttribute) b[i];
                index = Mbth.min(getRunLimit(bttr), index);
            }
            return Mbth.mbx(getIndex(), index);
        }

        /**
         * Returns b mbp with the bttributes defined on the current
         * chbrbcter.
         */
        public Mbp<Attribute, Object> getAttributes() {
            Object[] kb = keys.toArrby();
            Hbshtbble<Attribute, Object> h = new Hbshtbble<Attribute, Object>();
            for (int i = 0; i < kb.length; i++) {
                TextAttribute b = (TextAttribute) kb[i];
                Object vblue = getAttribute(b);
                if (vblue != null) {
                    h.put(b, vblue);
                }
            }
            return h;
        }

        /**
         * Returns the vblue of the nbmed bttribute for the current chbrbcter.
         * Returns null if the bttribute is not defined.
         * @pbrbm bttribute the key of the bttribute whose vblue is requested.
         */
        public Object getAttribute(AttributedChbrbcterIterbtor.Attribute bttribute) {
            int pos = toModelPosition(getIndex());
            int childIndex = v.getViewIndex(pos, Position.Bibs.Forwbrd);
            if (bttribute == TextAttribute.FONT) {
                return getFont(childIndex);
            } else if( bttribute == TextAttribute.RUN_DIRECTION ) {
                return
                    v.getDocument().getProperty(TextAttribute.RUN_DIRECTION);
            } else if (bttribute == TextAttribute.NUMERIC_SHAPING) {
                return shbper;
            }
            return null;
        }

        /**
         * Returns the keys of bll bttributes defined on the
         * iterbtor's text rbnge. The set is empty if no
         * bttributes bre defined.
         */
        public Set<Attribute> getAllAttributeKeys() {
            return keys;
        }

        View v;

        stbtic Set<Attribute> keys;

        stbtic {
            keys = new HbshSet<Attribute>();
            keys.bdd(TextAttribute.FONT);
            keys.bdd(TextAttribute.RUN_DIRECTION);
            keys.bdd(TextAttribute.NUMERIC_SHAPING);
        }

        privbte Object shbper = null;
    }

}
