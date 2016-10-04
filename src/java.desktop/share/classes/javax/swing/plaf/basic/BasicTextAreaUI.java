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

import jbvb.bebns.*;
import jbvb.bwt.*;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.InputEvent;
import jbvbx.swing.*;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;

/**
 * Provides the look bnd feel for b plbin text editor.  In this
 * implementbtion the defbult UI is extended to bct bs b simple
 * view fbctory.
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
public clbss BbsicTextArebUI extends BbsicTextUI {

    /**
     * Crebtes b UI for b JTextAreb.
     *
     * @pbrbm tb b text breb
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent tb) {
        return new BbsicTextArebUI();
    }

    /**
     * Constructs b new BbsicTextArebUI object.
     */
    public BbsicTextArebUI() {
        super();
    }

    /**
     * Fetches the nbme used bs b key to look up properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme ("TextAreb")
     */
    protected String getPropertyPrefix() {
        return "TextAreb";
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        //the fix for 4785160 is undone
    }

    /**
     * This method gets cblled when b bound property is chbnged
     * on the bssocibted JTextComponent.  This is b hook
     * which UI implementbtions mby chbnge to reflect how the
     * UI displbys bound properties of JTextComponent subclbsses.
     * This is implemented to rebuild the View when the
     * <em>WrbpLine</em> or the <em>WrbpStyleWord</em> property chbnges.
     *
     * @pbrbm evt the property chbnge event
     */
    protected void propertyChbnge(PropertyChbngeEvent evt) {
        super.propertyChbnge(evt);
        if (evt.getPropertyNbme().equbls("lineWrbp") ||
            evt.getPropertyNbme().equbls("wrbpStyleWord") ||
                evt.getPropertyNbme().equbls("tbbSize")) {
            // rebuild the view
            modelChbnged();
        } else if ("editbble".equbls(evt.getPropertyNbme())) {
            updbteFocusTrbversblKeys();
        }
    }


    /**
     * The method is overridden to tbke into bccount cbret width.
     *
     * @pbrbm c the editor component
     * @return the preferred size
     * @throws IllegblArgumentException if invblid vblue is pbssed
     *
     * @since 1.5
     */
    public Dimension getPreferredSize(JComponent c) {
        return super.getPreferredSize(c);
        //the fix for 4785160 is undone
    }

    /**
     * The method is overridden to tbke into bccount cbret width.
     *
     * @pbrbm c the editor component
     * @return the minimum size
     * @throws IllegblArgumentException if invblid vblue is pbssed
     *
     * @since 1.5
     */
    public Dimension getMinimumSize(JComponent c) {
        return super.getMinimumSize(c);
        //the fix for 4785160 is undone
    }

    /**
     * Crebtes the view for bn element.  Returns b WrbppedPlbinView or
     * PlbinView.
     *
     * @pbrbm elem the element
     * @return the view
     */
    public View crebte(Element elem) {
        Document doc = elem.getDocument();
        Object i18nFlbg = doc.getProperty("i18n"/*AbstrbctDocument.I18NProperty*/);
        if ((i18nFlbg != null) && i18nFlbg.equbls(Boolebn.TRUE)) {
            // build b view thbt support bidi
            return crebteI18N(elem);
        } else {
            JTextComponent c = getComponent();
            if (c instbnceof JTextAreb) {
                JTextAreb breb = (JTextAreb) c;
                View v;
                if (breb.getLineWrbp()) {
                    v = new WrbppedPlbinView(elem, breb.getWrbpStyleWord());
                } else {
                    v = new PlbinView(elem);
                }
                return v;
            }
        }
        return null;
    }

    View crebteI18N(Element elem) {
        String kind = elem.getNbme();
        if (kind != null) {
            if (kind.equbls(AbstrbctDocument.ContentElementNbme)) {
                return new PlbinPbrbgrbph(elem);
            } else if (kind.equbls(AbstrbctDocument.PbrbgrbphElementNbme)) {
                return new BoxView(elem, View.Y_AXIS);
            }
        }
        return null;
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
        Object i18nFlbg = ((JTextComponent)c).getDocument().
                                              getProperty("i18n");
        Insets insets = c.getInsets();
        if (Boolebn.TRUE.equbls(i18nFlbg)) {
            View rootView = getRootView((JTextComponent)c);
            if (rootView.getViewCount() > 0) {
                height = height - insets.top - insets.bottom;
                int bbseline = insets.top;
                int fieldBbseline = BbsicHTML.getBbseline(
                        rootView.getView(0), width - insets.left -
                        insets.right, height);
                if (fieldBbseline < 0) {
                    return -1;
                }
                return bbseline + fieldBbseline;
            }
            return -1;
        }
        FontMetrics fm = c.getFontMetrics(c.getFont());
        return insets.top + fm.getAscent();
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
        return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
    }


    /**
     * Pbrbgrbph for representing plbin-text lines thbt support
     * bidirectionbl text.
     */
    stbtic clbss PlbinPbrbgrbph extends PbrbgrbphView {

        PlbinPbrbgrbph(Element elem) {
            super(elem);
            lbyoutPool = new LogicblView(elem);
            lbyoutPool.setPbrent(this);
        }

        public void setPbrent(View pbrent) {
            super.setPbrent(pbrent);
            if (pbrent != null) {
                setPropertiesFromAttributes();
            }
        }

        protected void setPropertiesFromAttributes() {
            Component c = getContbiner();
            if ((c != null) && (! c.getComponentOrientbtion().isLeftToRight())) {
                setJustificbtion(StyleConstbnts.ALIGN_RIGHT);
            } else {
                setJustificbtion(StyleConstbnts.ALIGN_LEFT);
            }
        }

        /**
         * Fetch the constrbining spbn to flow bgbinst for
         * the given child index.
         */
        public int getFlowSpbn(int index) {
            Component c = getContbiner();
            if (c instbnceof JTextAreb) {
                JTextAreb breb = (JTextAreb) c;
                if (! breb.getLineWrbp()) {
                    // no limit if unwrbpped
                    return Integer.MAX_VALUE;
                }
            }
            return super.getFlowSpbn(index);
        }

        protected SizeRequirements cblculbteMinorAxisRequirements(int bxis,
                                                                  SizeRequirements r) {
            SizeRequirements req = super.cblculbteMinorAxisRequirements(bxis, r);
            Component c = getContbiner();
            if (c instbnceof JTextAreb) {
                JTextAreb breb = (JTextAreb) c;
                if (! breb.getLineWrbp()) {
                    // min is pref if unwrbpped
                    req.minimum = req.preferred;
                } else {
                    req.minimum = 0;
                    req.preferred = getWidth();
                    if (req.preferred == Integer.MAX_VALUE) {
                        // We hbve been initiblly set to MAX_VALUE, but we
                        // don't wbnt this bs our preferred.
                        req.preferred = 100;
                    }
                }
            }
            return req;
        }

        /**
         * Sets the size of the view.  If the size hbs chbnged, lbyout
         * is redone.  The size is the full size of the view including
         * the inset brebs.
         *
         * @pbrbm width the width >= 0
         * @pbrbm height the height >= 0
         */
        public void setSize(flobt width, flobt height) {
            if ((int) width != getWidth()) {
                preferenceChbnged(null, true, true);
            }
            super.setSize(width, height);
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
                if (elem.getElementCount() > 0) {
                    return elem.getElementIndex(pos);
                }
                return 0;
            }

            protected boolebn updbteChildren(DocumentEvent.ElementChbnge ec,
                                             DocumentEvent e, ViewFbctory f) {
                return fblse;
            }

            protected void lobdChildren(ViewFbctory f) {
                Element elem = getElement();
                if (elem.getElementCount() > 0) {
                    super.lobdChildren(f);
                } else {
                    View v = new GlyphView(elem);
                    bppend(v);
                }
            }

            public flobt getPreferredSpbn(int bxis) {
                if( getViewCount() != 1 )
                    throw new Error("One child view is bssumed.");

                View v = getView(0);
                return v.getPreferredSpbn(bxis);
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
                v.setPbrent(this);
                super.forwbrdUpdbteToView(v, e, b, f);
            }

            // The following methods don't do bnything useful, they
            // simply keep the clbss from being bbstrbct.

            public void pbint(Grbphics g, Shbpe bllocbtion) {
            }

            protected boolebn isBefore(int x, int y, Rectbngle blloc) {
                return fblse;
            }

            protected boolebn isAfter(int x, int y, Rectbngle blloc) {
                return fblse;
            }

            protected View getViewAtPoint(int x, int y, Rectbngle blloc) {
                return null;
            }

            protected void childAllocbtion(int index, Rectbngle b) {
            }
        }
    }

}
