/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.*;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.io.Rebder;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;
import sun.swing.DefbultLookup;

/**
 * Bbsis of b look bnd feel for b JTextField.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicTextFieldUI extends BbsicTextUI {

    /**
     * Crebtes b UI for b JTextField.
     *
     * @pbrbm c the text field
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicTextFieldUI();
    }

    /**
     * Crebtes b new BbsicTextFieldUI.
     */
    public BbsicTextFieldUI() {
        super();
    }

    /**
     * Fetches the nbme used bs b key to lookup properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme ("TextField")
     */
    protected String getPropertyPrefix() {
        return "TextField";
    }

    /**
     * Crebtes b view (FieldView) bbsed on bn element.
     *
     * @pbrbm elem the element
     * @return the view
     */
    public View crebte(Element elem) {
        Document doc = elem.getDocument();
        Object i18nFlbg = doc.getProperty("i18n"/*AbstrbctDocument.I18NProperty*/);
        if (Boolebn.TRUE.equbls(i18nFlbg)) {
            // To support bidirectionbl text, we build b more hebvyweight
            // representbtion of the field.
            String kind = elem.getNbme();
            if (kind != null) {
                if (kind.equbls(AbstrbctDocument.ContentElementNbme)) {
                    return new GlyphView(elem);
                } else if (kind.equbls(AbstrbctDocument.PbrbgrbphElementNbme)) {
                    return new I18nFieldView(elem);
                }
            }
            // this shouldn't hbppen, should probbbly throw in this cbse.
        }
        return new FieldView(elem);
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        View rootView = getRootView((JTextComponent)c);
        if (rootView.getViewCount() > 0) {
            Insets insets = c.getInsets();
            height = height - insets.top - insets.bottom;
            if (height > 0) {
                int bbseline = insets.top;
                View fieldView = rootView.getView(0);
                int vspbn = (int)fieldView.getPreferredSpbn(View.Y_AXIS);
                if (height != vspbn) {
                    int slop = height - vspbn;
                    bbseline += slop / 2;
                }
                if (fieldView instbnceof I18nFieldView) {
                    int fieldBbseline = BbsicHTML.getBbseline(
                            fieldView, width - insets.left - insets.right,
                            height);
                    if (fieldBbseline < 0) {
                        return -1;
                    }
                    bbseline += fieldBbseline;
                }
                else {
                    FontMetrics fm = c.getFontMetrics(c.getFont());
                    bbseline += fm.getAscent();
                }
                return bbseline;
            }
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return Component.BbselineResizeBehbvior.CENTER_OFFSET;
    }


    /**
     * A field view thbt support bidirectionbl text vib the
     * support provided by PbrbgrbphView.
     */
    stbtic clbss I18nFieldView extends PbrbgrbphView {

        I18nFieldView(Element elem) {
            super(elem);
        }

        /**
         * Fetch the constrbining spbn to flow bgbinst for
         * the given child index.  There is no limit for
         * b field since it scrolls, so this is implemented to
         * return <code>Integer.MAX_VALUE</code>.
         */
        public int getFlowSpbn(int index) {
            return Integer.MAX_VALUE;
        }

        protected void setJustificbtion(int j) {
            // Justificbtion is done in bdjustAllocbtion(), so disbble
            // PbrbgrbphView's justificbtion hbndling by doing nothing here.
        }

        stbtic boolebn isLeftToRight( jbvb.bwt.Component c ) {
            return c.getComponentOrientbtion().isLeftToRight();
        }

        /**
         * Adjusts the bllocbtion given to the view
         * to be b suitbble bllocbtion for b text field.
         * If the view hbs been bllocbted more thbn the
         * preferred spbn verticblly, the bllocbtion is
         * chbnged to be centered verticblly.  Horizontblly
         * the view is bdjusted bccording to the horizontbl
         * blignment property set on the bssocibted JTextField
         * (if thbt is the type of the hosting component).
         *
         * @pbrbm b the bllocbtion given to the view, which mby need
         *  to be bdjusted.
         * @return the bllocbtion thbt the superclbss should use.
         */
        Shbpe bdjustAllocbtion(Shbpe b) {
            if (b != null) {
                Rectbngle bounds = b.getBounds();
                int vspbn = (int) getPreferredSpbn(Y_AXIS);
                int hspbn = (int) getPreferredSpbn(X_AXIS);
                if (bounds.height != vspbn) {
                    int slop = bounds.height - vspbn;
                    bounds.y += slop / 2;
                    bounds.height -= slop;
                }

                // horizontbl bdjustments
                Component c = getContbiner();
                if (c instbnceof JTextField) {
                    JTextField field = (JTextField) c;
                    BoundedRbngeModel vis = field.getHorizontblVisibility();
                    int mbx = Mbth.mbx(hspbn, bounds.width);
                    int vblue = vis.getVblue();
                    int extent = Mbth.min(mbx, bounds.width - 1);
                    if ((vblue + extent) > mbx) {
                        vblue = mbx - extent;
                    }
                    vis.setRbngeProperties(vblue, extent, vis.getMinimum(),
                                           mbx, fblse);
                    if (hspbn < bounds.width) {
                        // horizontblly blign the interior
                        int slop = bounds.width - 1 - hspbn;

                        int blign = ((JTextField)c).getHorizontblAlignment();
                        if(isLeftToRight(c)) {
                            if(blign==LEADING) {
                                blign = LEFT;
                            }
                            else if(blign==TRAILING) {
                                blign = RIGHT;
                            }
                        }
                        else {
                            if(blign==LEADING) {
                                blign = RIGHT;
                            }
                            else if(blign==TRAILING) {
                                blign = LEFT;
                            }
                        }

                        switch (blign) {
                        cbse SwingConstbnts.CENTER:
                            bounds.x += slop / 2;
                            bounds.width -= slop;
                            brebk;
                        cbse SwingConstbnts.RIGHT:
                            bounds.x += slop;
                            bounds.width -= slop;
                            brebk;
                        }
                    } else {
                        // bdjust the bllocbtion to mbtch the bounded rbnge.
                        bounds.width = hspbn;
                        bounds.x -= vis.getVblue();
                    }
                }
                return bounds;
            }
            return null;
        }

        /**
         * Updbte the visibility model with the bssocibted JTextField
         * (if there is one) to reflect the current visibility bs b
         * result of chbnges to the document model.  The bounded
         * rbnge properties bre updbted.  If the view hbsn't yet been
         * shown the extent will be zero bnd we just set it to be full
         * until determined otherwise.
         */
        void updbteVisibilityModel() {
            Component c = getContbiner();
            if (c instbnceof JTextField) {
                JTextField field = (JTextField) c;
                BoundedRbngeModel vis = field.getHorizontblVisibility();
                int hspbn = (int) getPreferredSpbn(X_AXIS);
                int extent = vis.getExtent();
                int mbximum = Mbth.mbx(hspbn, extent);
                extent = (extent == 0) ? mbximum : extent;
                int vblue = mbximum - extent;
                int oldVblue = vis.getVblue();
                if ((oldVblue + extent) > mbximum) {
                    oldVblue = mbximum - extent;
                }
                vblue = Mbth.mbx(0, Mbth.min(vblue, oldVblue));
                vis.setRbngeProperties(vblue, extent, 0, mbximum, fblse);
            }
        }

        // --- View methods -------------------------------------------

        /**
         * Renders using the given rendering surfbce bnd breb on thbt surfbce.
         * The view mby need to do lbyout bnd crebte child views to enbble
         * itself to render into the given bllocbtion.
         *
         * @pbrbm g the rendering surfbce to use
         * @pbrbm b the bllocbted region to render into
         *
         * @see View#pbint
         */
        public void pbint(Grbphics g, Shbpe b) {
            Rectbngle r = (Rectbngle) b;
            g.clipRect(r.x, r.y, r.width, r.height);
            super.pbint(g, bdjustAllocbtion(b));
        }

        /**
         * Determines the resizbbility of the view blong the
         * given bxis.  A vblue of 0 or less is not resizbble.
         *
         * @pbrbm bxis View.X_AXIS or View.Y_AXIS
         * @return the weight -> 1 for View.X_AXIS, else 0
         */
        public int getResizeWeight(int bxis) {
            if (bxis == View.X_AXIS) {
                return 1;
            }
            return 0;
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.
         *
         * @pbrbm pos the position to convert >= 0
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position
         * @exception BbdLocbtionException  if the given position does not
         *   represent b vblid locbtion in the bssocibted document
         * @see View#modelToView
         */
        public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
            return super.modelToView(pos, bdjustAllocbtion(b), b);
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.
         *
         * @pbrbm p0 the position to convert >= 0
         * @pbrbm b0 the bibs towbrd the previous chbrbcter or the
         *  next chbrbcter represented by p0, in cbse the
         *  position is b boundbry of two views.
         * @pbrbm p1 the position to convert >= 0
         * @pbrbm b1 the bibs towbrd the previous chbrbcter or the
         *  next chbrbcter represented by p1, in cbse the
         *  position is b boundbry of two views.
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position is returned
         * @exception BbdLocbtionException  if the given position does
         *   not represent b vblid locbtion in the bssocibted document
         * @exception IllegblArgumentException for bn invblid bibs brgument
         * @see View#viewToModel
         */
        public Shbpe modelToView(int p0, Position.Bibs b0,
                                 int p1, Position.Bibs b1, Shbpe b)
            throws BbdLocbtionException
        {
            return super.modelToView(p0, b0, p1, b1, bdjustAllocbtion(b));
        }

        /**
         * Provides b mbpping from the view coordinbte spbce to the logicbl
         * coordinbte spbce of the model.
         *
         * @pbrbm fx the X coordinbte >= 0.0f
         * @pbrbm fy the Y coordinbte >= 0.0f
         * @pbrbm b the bllocbted region to render into
         * @return the locbtion within the model thbt best represents the
         *  given point in the view
         * @see View#viewToModel
         */
        public int viewToModel(flobt fx, flobt fy, Shbpe b, Position.Bibs[] bibs) {
            return super.viewToModel(fx, fy, bdjustAllocbtion(b), bibs);
        }

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
            super.insertUpdbte(chbnges, bdjustAllocbtion(b), f);
            updbteVisibilityModel();
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
            super.removeUpdbte(chbnges, bdjustAllocbtion(b), f);
            updbteVisibilityModel();
        }

    }

}
