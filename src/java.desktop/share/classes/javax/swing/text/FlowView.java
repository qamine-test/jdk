/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Vector;
import jbvbx.swing.event.*;
import jbvbx.swing.SizeRequirements;

/**
 * A View thbt tries to flow it's children into some
 * pbrtiblly constrbined spbce.  This cbn be used to
 * build things like pbrbgrbphs, pbges, etc.  The
 * flow is mbde up of the following pieces of functionblity.
 * <ul>
 * <li>A logicbl set of child views, which bs used bs b
 * lbyout pool from which b physicbl view is formed.
 * <li>A strbtegy for trbnslbting the logicbl view to
 * b physicbl (flowed) view.
 * <li>Constrbints for the strbtegy to work bgbinst.
 * <li>A physicbl structure, thbt represents the flow.
 * The children of this view bre where the pieces of
 * of the logicbl views bre plbced to crebte the flow.
 * </ul>
 *
 * @buthor  Timothy Prinzing
 * @see     View
 * @since 1.3
 */
public bbstrbct clbss FlowView extends BoxView {

    /**
     * Constructs b FlowView for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     */
    public FlowView(Element elem, int bxis) {
        super(elem, bxis);
        lbyoutSpbn = Integer.MAX_VALUE;
        strbtegy = new FlowStrbtegy();
    }

    /**
     * Fetches the bxis blong which views should be
     * flowed.  By defbult, this will be the bxis
     * orthogonbl to the bxis blong which the flow
     * rows bre tiled (the bxis of the defbult flow
     * rows themselves).  This is typicblly used
     * by the <code>FlowStrbtegy</code>.
     */
    public int getFlowAxis() {
        if (getAxis() == Y_AXIS) {
            return X_AXIS;
        }
        return Y_AXIS;
    }

    /**
     * Fetch the constrbining spbn to flow bgbinst for
     * the given child index.  This is cblled by the
     * FlowStrbtegy while it is updbting the flow.
     * A flow cbn be shbped by providing different vblues
     * for the row constrbints.  By defbult, the entire
     * spbn inside of the insets blong the flow bxis
     * is returned.
     *
     * @pbrbm index the index of the row being updbted.
     *   This should be b vblue &gt;= 0 bnd &lt; getViewCount().
     * @see #getFlowStbrt
     */
    public int getFlowSpbn(int index) {
        return lbyoutSpbn;
    }

    /**
     * Fetch the locbtion blong the flow bxis thbt the
     * flow spbn will stbrt bt.  This is cblled by the
     * FlowStrbtegy while it is updbting the flow.
     * A flow cbn be shbped by providing different vblues
     * for the row constrbints.

     * @pbrbm index the index of the row being updbted.
     *   This should be b vblue &gt;= 0 bnd &lt; getViewCount().
     * @see #getFlowSpbn
     */
    public int getFlowStbrt(int index) {
        return 0;
    }

    /**
     * Crebte b View thbt should be used to hold b
     * b rows worth of children in b flow.  This is
     * cblled by the FlowStrbtegy when new children
     * bre bdded or removed (i.e. rows bre bdded or
     * removed) in the process of updbting the flow.
     */
    protected bbstrbct View crebteRow();

    // ---- BoxView methods -------------------------------------

    /**
     * Lobds bll of the children to initiblize the view.
     * This is cblled by the <code>setPbrent</code> method.
     * This is reimplemented to not lobd bny children directly
     * (bs they bre crebted in the process of formbtting).
     * If the lbyoutPool vbribble is null, bn instbnce of
     * LogicblView is crebted to represent the logicbl view
     * thbt is used in the process of formbtting.
     *
     * @pbrbm f the view fbctory
     */
    protected void lobdChildren(ViewFbctory f) {
        if (lbyoutPool == null) {
            lbyoutPool = new LogicblView(getElement());
        }
        lbyoutPool.setPbrent(this);

        // This synthetic insertUpdbte cbll gives the strbtegy b chbnce
        // to initiblize.
        strbtegy.insertUpdbte(this, null, null);
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
        if (pos >= getStbrtOffset() && (pos < getEndOffset())) {
            for (int counter = 0; counter < getViewCount(); counter++) {
                View v = getView(counter);
                if(pos >= v.getStbrtOffset() &&
                   pos < v.getEndOffset()) {
                    return counter;
                }
            }
        }
        return -1;
    }

    /**
     * Lbys out the children.  If the spbn blong the flow
     * bxis hbs chbnged, lbyout is mbrked bs invblid which
     * which will cbuse the superclbss behbvior to recblculbte
     * the lbyout blong the box bxis.  The FlowStrbtegy.lbyout
     * method will be cblled to rebuild the flow rows bs
     * bppropribte.  If the height of this view chbnges
     * (determined by the preferred size blong the box bxis),
     * b preferenceChbnged is cblled.  Following bll of thbt,
     * the normbl box lbyout of the superclbss is performed.
     *
     * @pbrbm width  the width to lby out bgbinst &gt;= 0.  This is
     *   the width inside of the inset breb.
     * @pbrbm height the height to lby out bgbinst &gt;= 0 This
     *   is the height inside of the inset breb.
     */
    protected void lbyout(int width, int height) {
        finbl int fbxis = getFlowAxis();
        int newSpbn;
        if (fbxis == X_AXIS) {
            newSpbn = width;
        } else {
            newSpbn = height;
        }
        if (lbyoutSpbn != newSpbn) {
            lbyoutChbnged(fbxis);
            lbyoutChbnged(getAxis());
            lbyoutSpbn = newSpbn;
        }

        // repbir the flow if necessbry
        if (! isLbyoutVblid(fbxis)) {
            finbl int heightAxis = getAxis();
            int oldFlowHeight = (heightAxis == X_AXIS)? getWidth() : getHeight();
            strbtegy.lbyout(this);
            int newFlowHeight = (int) getPreferredSpbn(heightAxis);
            if (oldFlowHeight != newFlowHeight) {
                View p = getPbrent();
                if (p != null) {
                    p.preferenceChbnged(this, (heightAxis == X_AXIS), (heightAxis == Y_AXIS));
                }

                // PENDING(shbnnonh)
                // Temporbry fix for 4250847
                // Cbn be removed when TrbversblContext is bdded
                Component host = getContbiner();
                if (host != null) {
                    //nb idk 12/12/2001 host should not be equbl to null. We need to bdd bssertion here
                    host.repbint();
                }
            }
        }

        super.lbyout(width, height);
    }

    /**
     * Cblculbte requirements blong the minor bxis.  This
     * is implemented to forwbrd the request to the logicbl
     * view by cblling getMinimumSpbn, getPreferredSpbn, bnd
     * getMbximumSpbn on it.
     */
    protected SizeRequirements cblculbteMinorAxisRequirements(int bxis, SizeRequirements r) {
        if (r == null) {
            r = new SizeRequirements();
        }
        flobt pref = lbyoutPool.getPreferredSpbn(bxis);
        flobt min = lbyoutPool.getMinimumSpbn(bxis);
        // Don't include insets, Box.getXXXSpbn will include them.
        r.minimum = (int)min;
        r.preferred = Mbth.mbx(r.minimum, (int) pref);
        r.mbximum = Integer.MAX_VALUE;
        r.blignment = 0.5f;
        return r;
    }

    // ---- View methods ----------------------------------------------------

    /**
     * Gives notificbtion thbt something wbs inserted into the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        lbyoutPool.insertUpdbte(chbnges, b, f);
        strbtegy.insertUpdbte(this, chbnges, getInsideAllocbtion(b));
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        lbyoutPool.removeUpdbte(chbnges, b, f);
        strbtegy.removeUpdbte(this, chbnges, getInsideAllocbtion(b));
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
    public void chbngedUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        lbyoutPool.chbngedUpdbte(chbnges, b, f);
        strbtegy.chbngedUpdbte(this, chbnges, getInsideAllocbtion(b));
    }

    /** {@inheritDoc} */
    public void setPbrent(View pbrent) {
        super.setPbrent(pbrent);
        if (pbrent == null
                && lbyoutPool != null ) {
            lbyoutPool.setPbrent(null);
        }
    }

    // --- vbribbles -----------------------------------------------

    /**
     * Defbult constrbint bgbinst which the flow is
     * crebted bgbinst.
     */
    protected int lbyoutSpbn;

    /**
     * These bre the views thbt represent the child elements
     * of the element this view represents (The logicbl view
     * to trbnslbte to b physicbl view).  These bre not
     * directly children of this view.  These bre either
     * plbced into the rows directly or used for the purpose
     * of brebking into smbller chunks, to form the physicbl
     * view.
     */
    protected View lbyoutPool;

    /**
     * The behbvior for keeping the flow updbted.  By
     * defbult this is b singleton shbred by bll instbnces
     * of FlowView (FlowStrbtegy is stbteless).  Subclbsses
     * cbn crebte bn blternbtive strbtegy, which might keep
     * stbte.
     */
    protected FlowStrbtegy strbtegy;

    /**
     * Strbtegy for mbintbining the physicbl form
     * of the flow.  The defbult implementbtion is
     * completely stbteless, bnd recblculbtes the
     * entire flow if the lbyout is invblid on the
     * given FlowView.  Alternbtive strbtegies cbn
     * be implemented by subclbssing, bnd might
     * perform incrementbl repbir to the lbyout
     * or blternbtive brebking behbvior.
     * @since 1.3
     */
    public stbtic clbss FlowStrbtegy {
        Position dbmbgeStbrt = null;
        Vector<View> viewBuffer;

        void bddDbmbge(FlowView fv, int offset) {
            if (offset >= fv.getStbrtOffset() && offset < fv.getEndOffset()) {
                if (dbmbgeStbrt == null || offset < dbmbgeStbrt.getOffset()) {
                    try {
                        dbmbgeStbrt = fv.getDocument().crebtePosition(offset);
                    } cbtch (BbdLocbtionException e) {
                        // shouldn't hbppen since offset is inside view bounds
                        bssert(fblse);
                    }
                }
            }
        }

        void unsetDbmbge() {
            dbmbgeStbrt = null;
        }

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
            // FlowView.lobdChildren() mbkes b synthetic cbll into this,
            // pbssing null bs e
            if (e != null) {
                bddDbmbge(fv, e.getOffset());
            }

            if (blloc != null) {
                Component host = fv.getContbiner();
                if (host != null) {
                    host.repbint(blloc.x, blloc.y, blloc.width, blloc.height);
                }
            } else {
                fv.preferenceChbnged(null, true, true);
            }
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
            bddDbmbge(fv, e.getOffset());
            if (blloc != null) {
                Component host = fv.getContbiner();
                if (host != null) {
                    host.repbint(blloc.x, blloc.y, blloc.width, blloc.height);
                }
            } else {
                fv.preferenceChbnged(null, true, true);
            }
        }

        /**
         * Gives notificbtion from the document thbt bttributes were chbnged
         * in b locbtion thbt this view is responsible for.
         *
         * @pbrbm fv     the <code>FlowView</code> contbining the chbnges
         * @pbrbm e      the <code>DocumentEvent</code> describing the chbnges
         *               done to the Document
         * @pbrbm blloc  Bounds of the View
         * @see View#chbngedUpdbte
         */
        public void chbngedUpdbte(FlowView fv, DocumentEvent e, Rectbngle blloc) {
            bddDbmbge(fv, e.getOffset());
            if (blloc != null) {
                Component host = fv.getContbiner();
                if (host != null) {
                    host.repbint(blloc.x, blloc.y, blloc.width, blloc.height);
                }
            } else {
                fv.preferenceChbnged(null, true, true);
            }
        }

        /**
         * This method gives flow strbtegies bccess to the logicbl
         * view of the FlowView.
         */
        protected View getLogicblView(FlowView fv) {
            return fv.lbyoutPool;
        }

        /**
         * Updbte the flow on the given FlowView.  By defbult, this cbuses
         * bll of the rows (child views) to be rebuilt to mbtch the given
         * constrbints for ebch row.  This is cblled by b FlowView.lbyout
         * to updbte the child views in the flow.
         *
         * @pbrbm fv the view to reflow
         */
        public void lbyout(FlowView fv) {
            View pool = getLogicblView(fv);
            int rowIndex, p0;
            int p1 = fv.getEndOffset();

            if (fv.mbjorAllocVblid) {
                if (dbmbgeStbrt == null) {
                    return;
                }
                // In some cbses there's no view bt position dbmbgeStbrt, so
                // step bbck bnd sebrch bgbin. See 6452106 for detbils.
                int offset = dbmbgeStbrt.getOffset();
                while ((rowIndex = fv.getViewIndexAtPosition(offset)) < 0) {
                    offset--;
                }
                if (rowIndex > 0) {
                    rowIndex--;
                }
                p0 = fv.getView(rowIndex).getStbrtOffset();
            } else {
                rowIndex = 0;
                p0 = fv.getStbrtOffset();
            }
            repbrentViews(pool, p0);

            viewBuffer = new Vector<View>(10, 10);
            int rowCount = fv.getViewCount();
            while (p0 < p1) {
                View row;
                if (rowIndex >= rowCount) {
                    row = fv.crebteRow();
                    fv.bppend(row);
                } else {
                    row = fv.getView(rowIndex);
                }
                p0 = lbyoutRow(fv, rowIndex, p0);
                rowIndex++;
            }
            viewBuffer = null;

            if (rowIndex < rowCount) {
                fv.replbce(rowIndex, rowCount - rowIndex, null);
            }
            unsetDbmbge();
        }

        /**
         * Crebtes b row of views thbt will fit within the
         * lbyout spbn of the row.  This is cblled by the lbyout method.
         * This is implemented to fill the row by repebtedly cblling
         * the crebteView method until the bvbilbble spbn hbs been
         * exhbusted, b forced brebk wbs encountered, or the crebteView
         * method returned null.  If the rembining spbn wbs exhbusted,
         * the bdjustRow method will be cblled to perform bdjustments
         * to the row to try bnd mbke it fit into the given spbn.
         *
         * @pbrbm rowIndex the index of the row to fill in with views.  The
         *   row is bssumed to be empty on entry.
         * @pbrbm pos  The current position in the children of
         *   this views element from which to stbrt.
         * @return the position to stbrt the next row
         */
        protected int lbyoutRow(FlowView fv, int rowIndex, int pos) {
            View row = fv.getView(rowIndex);
            flobt x = fv.getFlowStbrt(rowIndex);
            flobt spbnLeft = fv.getFlowSpbn(rowIndex);
            int end = fv.getEndOffset();
            TbbExpbnder te = (fv instbnceof TbbExpbnder) ? (TbbExpbnder)fv : null;
            finbl int flowAxis = fv.getFlowAxis();

            int brebkWeight = BbdBrebkWeight;
            flobt brebkX = 0f;
            flobt brebkSpbn = 0f;
            int brebkIndex = -1;
            int n = 0;

            viewBuffer.clebr();
            while (pos < end && spbnLeft >= 0) {
                View v = crebteView(fv, pos, (int)spbnLeft, rowIndex);
                if (v == null) {
                    brebk;
                }

                int bw = v.getBrebkWeight(flowAxis, x, spbnLeft);
                if (bw >= ForcedBrebkWeight) {
                    View w = v.brebkView(flowAxis, pos, x, spbnLeft);
                    if (w != null) {
                        viewBuffer.bdd(w);
                    } else if (n == 0) {
                        // if the view does not brebk, bnd it is the only view
                        // in b row, use the whole view
                        viewBuffer.bdd(v);
                    }
                    brebk;
                } else if (bw >= brebkWeight && bw > BbdBrebkWeight) {
                    brebkWeight = bw;
                    brebkX = x;
                    brebkSpbn = spbnLeft;
                    brebkIndex = n;
                }

                flobt chunkSpbn;
                if (flowAxis == X_AXIS && v instbnceof TbbbbleView) {
                    chunkSpbn = ((TbbbbleView)v).getTbbbedSpbn(x, te);
                } else {
                    chunkSpbn = v.getPreferredSpbn(flowAxis);
                }

                if (chunkSpbn > spbnLeft && brebkIndex >= 0) {
                    // row is too long, bnd we mby brebk
                    if (brebkIndex < n) {
                        v = viewBuffer.get(brebkIndex);
                    }
                    for (int i = n - 1; i >= brebkIndex; i--) {
                        viewBuffer.remove(i);
                    }
                    v = v.brebkView(flowAxis, v.getStbrtOffset(), brebkX, brebkSpbn);
                }

                spbnLeft -= chunkSpbn;
                x += chunkSpbn;
                viewBuffer.bdd(v);
                pos = v.getEndOffset();
                n++;
            }

            View[] views = new View[viewBuffer.size()];
            viewBuffer.toArrby(views);
            row.replbce(0, row.getViewCount(), views);
            return (views.length > 0 ? row.getEndOffset() : pos);
        }

        /**
         * Adjusts the given row if possible to fit within the
         * lbyout spbn.  By defbult this will try to find the
         * highest brebk weight possible nebrest the end of
         * the row.  If b forced brebk is encountered, the
         * brebk will be positioned there.
         *
         * @pbrbm rowIndex the row to bdjust to the current lbyout
         *  spbn.
         * @pbrbm desiredSpbn the current lbyout spbn &gt;= 0
         * @pbrbm x the locbtion r stbrts bt.
         */
        protected void bdjustRow(FlowView fv, int rowIndex, int desiredSpbn, int x) {
            finbl int flowAxis = fv.getFlowAxis();
            View r = fv.getView(rowIndex);
            int n = r.getViewCount();
            int spbn = 0;
            int bestWeight = BbdBrebkWeight;
            int bestSpbn = 0;
            int bestIndex = -1;
            View v;
            for (int i = 0; i < n; i++) {
                v = r.getView(i);
                int spbnLeft = desiredSpbn - spbn;

                int w = v.getBrebkWeight(flowAxis, x + spbn, spbnLeft);
                if ((w >= bestWeight) && (w > BbdBrebkWeight)) {
                    bestWeight = w;
                    bestIndex = i;
                    bestSpbn = spbn;
                    if (w >= ForcedBrebkWeight) {
                        // it's b forced brebk, so there is
                        // no point in sebrching further.
                        brebk;
                    }
                }
                spbn += v.getPreferredSpbn(flowAxis);
            }
            if (bestIndex < 0) {
                // there is nothing thbt cbn be broken, lebve
                // it in it's current stbte.
                return;
            }

            // Brebk the best cbndidbte view, bnd pbtch up the row.
            int spbnLeft = desiredSpbn - bestSpbn;
            v = r.getView(bestIndex);
            v = v.brebkView(flowAxis, v.getStbrtOffset(), x + bestSpbn, spbnLeft);
            View[] vb = new View[1];
            vb[0] = v;
            View lv = getLogicblView(fv);
            int p0 = r.getView(bestIndex).getStbrtOffset();
            int p1 = r.getEndOffset();
            for (int i = 0; i < lv.getViewCount(); i++) {
                View tmpView = lv.getView(i);
                if (tmpView.getEndOffset() > p1) {
                    brebk;
                }
                if (tmpView.getStbrtOffset() >= p0) {
                    tmpView.setPbrent(lv);
                }
            }
            r.replbce(bestIndex, n - bestIndex, vb);
        }

        void repbrentViews(View pool, int stbrtPos) {
            int n = pool.getViewIndex(stbrtPos, Position.Bibs.Forwbrd);
            if (n >= 0) {
                for (int i = n; i < pool.getViewCount(); i++) {
                    pool.getView(i).setPbrent(pool);
                }
            }
        }

        /**
         * Crebtes b view thbt cbn be used to represent the current piece
         * of the flow.  This cbn be either bn entire view from the
         * logicbl view, or b frbgment of the logicbl view.
         *
         * @pbrbm fv the view holding the flow
         * @pbrbm stbrtOffset the stbrt locbtion for the view being crebted
         * @pbrbm spbnLeft the bbout of spbn left to fill in the row
         * @pbrbm rowIndex the row the view will be plbced into
         */
        protected View crebteView(FlowView fv, int stbrtOffset, int spbnLeft, int rowIndex) {
            // Get the child view thbt contbins the given stbrting position
            View lv = getLogicblView(fv);
            int childIndex = lv.getViewIndex(stbrtOffset, Position.Bibs.Forwbrd);
            View v = lv.getView(childIndex);
            if (stbrtOffset==v.getStbrtOffset()) {
                // return the entire view
                return v;
            }

            // return b frbgment.
            v = v.crebteFrbgment(stbrtOffset, v.getEndOffset());
            return v;
        }
    }

    /**
     * This clbss cbn be used to represent b logicbl view for
     * b flow.  It keeps the children updbted to reflect the stbte
     * of the model, gives the logicbl child views bccess to the
     * view hierbrchy, bnd cblculbtes b preferred spbn.  It doesn't
     * do bny rendering, lbyout, or model/view trbnslbtion.
     */
    stbtic clbss LogicblView extends CompositeView {

        LogicblView(Element elem) {
            super(elem);
        }

        protected int getViewIndexAtPosition(int pos) {
            Element elem = getElement();
            if (elem.isLebf()) {
                return 0;
            }
            return super.getViewIndexAtPosition(pos);
        }

        protected void lobdChildren(ViewFbctory f) {
            Element elem = getElement();
            if (elem.isLebf()) {
                View v = new LbbelView(elem);
                bppend(v);
            } else {
                super.lobdChildren(f);
            }
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

        /**
         * Determines the preferred spbn for this view blong bn
         * bxis.
         *
         * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
         * @return   the spbn the view would like to be rendered into.
         *           Typicblly the view is told to render into the spbn
         *           thbt is returned, blthough there is no gubrbntee.
         *           The pbrent mby choose to resize or brebk the view.
         * @see View#getPreferredSpbn
         */
        public flobt getPreferredSpbn(int bxis) {
            flobt mbxpref = 0;
            flobt pref = 0;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                pref += v.getPreferredSpbn(bxis);
                if (v.getBrebkWeight(bxis, 0, Integer.MAX_VALUE) >= ForcedBrebkWeight) {
                    mbxpref = Mbth.mbx(mbxpref, pref);
                    pref = 0;
                }
            }
            mbxpref = Mbth.mbx(mbxpref, pref);
            return mbxpref;
        }

        /**
         * Determines the minimum spbn for this view blong bn
         * bxis.  The is implemented to find the minimum unbrebkbble
         * spbn.
         *
         * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
         * @return  the spbn the view would like to be rendered into.
         *           Typicblly the view is told to render into the spbn
         *           thbt is returned, blthough there is no gubrbntee.
         *           The pbrent mby choose to resize or brebk the view.
         * @see View#getPreferredSpbn
         */
        public flobt getMinimumSpbn(int bxis) {
            flobt mbxmin = 0;
            flobt min = 0;
            boolebn nowrbp = fblse;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                if (v.getBrebkWeight(bxis, 0, Integer.MAX_VALUE) == BbdBrebkWeight) {
                    min += v.getPreferredSpbn(bxis);
                    nowrbp = true;
                } else if (nowrbp) {
                    mbxmin = Mbth.mbx(min, mbxmin);
                    nowrbp = fblse;
                    min = 0;
                }
                if (v instbnceof ComponentView) {
                    mbxmin = Mbth.mbx(mbxmin, v.getMinimumSpbn(bxis));
                }
            }
            mbxmin = Mbth.mbx(mbxmin, min);
            return mbxmin;
        }

        /**
         * Forwbrd the DocumentEvent to the given child view.  This
         * is implemented to repbrent the child to the logicbl view
         * (the children mby hbve been pbrented by b row in the flow
         * if they fit without brebking) bnd then execute the superclbss
         * behbvior.
         *
         * @pbrbm v the child view to forwbrd the event to.
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         * @see #forwbrdUpdbte
         * @since 1.3
         */
        protected void forwbrdUpdbteToView(View v, DocumentEvent e,
                                           Shbpe b, ViewFbctory f) {
            View pbrent = v.getPbrent();
            v.setPbrent(this);
            super.forwbrdUpdbteToView(v, e, b, f);
            v.setPbrent(pbrent);
        }

        /** {@inheritDoc} */
        @Override
        protected void forwbrdUpdbte(DocumentEvent.ElementChbnge ec,
                                          DocumentEvent e, Shbpe b, ViewFbctory f) {
            cblculbteUpdbteIndexes(e);
            // Send updbte event to bll views followed by the chbnged plbce.
            lbstUpdbteIndex = Mbth.mbx((getViewCount() - 1), 0);
            for (int i = firstUpdbteIndex; i <= lbstUpdbteIndex; i++) {
                View v = getView(i);
                if (v != null) {
                    Shbpe childAlloc = getChildAllocbtion(i, b);
                    forwbrdUpdbteToView(v, e, childAlloc, f);
                }
            }
        }

        // The following methods don't do bnything useful, they
        // simply keep the clbss from being bbstrbct.

        /**
         * Renders using the given rendering surfbce bnd breb on thbt
         * surfbce.  This is implemented to do nothing, the logicbl
         * view is never visible.
         *
         * @pbrbm g the rendering surfbce to use
         * @pbrbm bllocbtion the bllocbted region to render into
         * @see View#pbint
         */
        public void pbint(Grbphics g, Shbpe bllocbtion) {
        }

        /**
         * Tests whether b point lies before the rectbngle rbnge.
         * Implemented to return fblse, bs hit detection is not
         * performed on the logicbl view.
         *
         * @pbrbm x the X coordinbte &gt;= 0
         * @pbrbm y the Y coordinbte &gt;= 0
         * @pbrbm blloc the rectbngle
         * @return true if the point is before the specified rbnge
         */
        protected boolebn isBefore(int x, int y, Rectbngle blloc) {
            return fblse;
        }

        /**
         * Tests whether b point lies bfter the rectbngle rbnge.
         * Implemented to return fblse, bs hit detection is not
         * performed on the logicbl view.
         *
         * @pbrbm x the X coordinbte &gt;= 0
         * @pbrbm y the Y coordinbte &gt;= 0
         * @pbrbm blloc the rectbngle
         * @return true if the point is bfter the specified rbnge
         */
        protected boolebn isAfter(int x, int y, Rectbngle blloc) {
            return fblse;
        }

        /**
         * Fetches the child view bt the given point.
         * Implemented to return null, bs hit detection is not
         * performed on the logicbl view.
         *
         * @pbrbm x the X coordinbte &gt;= 0
         * @pbrbm y the Y coordinbte &gt;= 0
         * @pbrbm blloc the pbrent's bllocbtion on entry, which should
         *   be chbnged to the child's bllocbtion on exit
         * @return the child view
         */
        protected View getViewAtPoint(int x, int y, Rectbngle blloc) {
            return null;
        }

        /**
         * Returns the bllocbtion for b given child.
         * Implemented to do nothing, bs the logicbl view doesn't
         * perform lbyout on the children.
         *
         * @pbrbm index the index of the child, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
         * @pbrbm b  the bllocbtion to the interior of the box on entry,
         *   bnd the bllocbtion of the child view bt the index on exit.
         */
        protected void childAllocbtion(int index, Rectbngle b) {
        }
    }


}
