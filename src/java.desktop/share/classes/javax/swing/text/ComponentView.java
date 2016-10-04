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
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Set;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.event.*;

/**
 * Component decorbtor thbt implements the view interfbce.  The
 * entire element is used to represent the component.  This bcts
 * bs b gbtewby from the displby-only View implementbtions to
 * interbctive lightweight components (ie it bllows components
 * to be embedded into the View hierbrchy).
 * <p>
 * The component is plbced relbtive to the text bbseline
 * bccording to the vblue returned by
 * <code>Component.getAlignmentY</code>.  For Swing components
 * this vblue cbn be conveniently set using the method
 * <code>JComponent.setAlignmentY</code>.  For exbmple, setting
 * b vblue of <code>0.75</code> will cbuse 75 percent of the
 * component to be bbove the bbseline, bnd 25 percent of the
 * component to be below the bbseline.
 * <p>
 * This clbss is implemented to do the extrb work necessbry to
 * work properly in the presence of multiple threbds (i.e. from
 * bsynchronous notificbtion of model chbnges for exbmple) by
 * ensuring thbt bll component bccess is done on the event threbd.
 * <p>
 * The component used is determined by the return vblue of the
 * crebteComponent method.  The defbult implementbtion of this
 * method is to return the component held bs bn bttribute of
 * the element (by cblling StyleConstbnts.getComponent).  A
 * limitbtion of this behbvior is thbt the component cbnnot
 * be used by more thbn one text component (i.e. with b shbred
 * model).  Subclbsses cbn remove this constrbint by implementing
 * the crebteComponent to bctublly crebte b component bbsed upon
 * some kind of specificbtion contbined in the bttributes.  The
 * ObjectView clbss in the html pbckbge is bn exbmple of b
 * ComponentView implementbtion thbt supports multiple component
 * views of b shbred model.
 *
 * @buthor Timothy Prinzing
 */
public clbss ComponentView extends View  {

    /**
     * Crebtes b new ComponentView object.
     *
     * @pbrbm elem the element to decorbte
     */
    public ComponentView(Element elem) {
        super(elem);
    }

    /**
     * Crebte the component thbt is bssocibted with
     * this view.  This will be cblled when it hbs
     * been determined thbt b new component is needed.
     * This would result from b cbll to setPbrent or
     * bs b result of being notified thbt bttributes
     * hbve chbnged.
     */
    protected Component crebteComponent() {
        AttributeSet bttr = getElement().getAttributes();
        Component comp = StyleConstbnts.getComponent(bttr);
        return comp;
    }

    /**
     * Fetch the component bssocibted with the view.
     */
    public finbl Component getComponent() {
        return crebtedC;
    }

    // --- View methods ---------------------------------------------

    /**
     * The rebl pbint behbvior occurs nbturblly from the bssocibtion
     * thbt the component hbs with its pbrent contbiner (the sbme
     * contbiner hosting this view).  This is implemented to do nothing.
     *
     * @pbrbm g the grbphics context
     * @pbrbm b the shbpe
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        if (c != null) {
            Rectbngle blloc = (b instbnceof Rectbngle) ?
                (Rectbngle) b : b.getBounds();
            c.setBounds(blloc.x, blloc.y, blloc.width, blloc.height);
        }
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.  This is implemented to return the vblue
     * returned by Component.getPreferredSize blong the
     * bxis of interest.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;=0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getPreferredSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
        if (c != null) {
            Dimension size = c.getPreferredSize();
            if (bxis == View.X_AXIS) {
                return size.width;
            } else {
                return size.height;
            }
        }
        return 0;
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.  This is implemented to return the vblue
     * returned by Component.getMinimumSize blong the
     * bxis of interest.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;=0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getMinimumSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
        if (c != null) {
            Dimension size = c.getMinimumSize();
            if (bxis == View.X_AXIS) {
                return size.width;
            } else {
                return size.height;
            }
        }
        return 0;
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.  This is implemented to return the vblue
     * returned by Component.getMbximumSize blong the
     * bxis of interest.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;=0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getMbximumSpbn(int bxis) {
        if ((bxis != X_AXIS) && (bxis != Y_AXIS)) {
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
        if (c != null) {
            Dimension size = c.getMbximumSize();
            if (bxis == View.X_AXIS) {
                return size.width;
            } else {
                return size.height;
            }
        }
        return 0;
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  This is implemented to give the blignment of the
     * embedded component.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return the desired blignment.  This should be b vblue
     *   between 0.0 bnd 1.0 where 0 indicbtes blignment bt the
     *   origin bnd 1.0 indicbtes blignment to the full spbn
     *   bwby from the origin.  An blignment of 0.5 would be the
     *   center of the view.
     */
    public flobt getAlignment(int bxis) {
        if (c != null) {
            switch (bxis) {
            cbse View.X_AXIS:
                return c.getAlignmentX();
            cbse View.Y_AXIS:
                return c.getAlignmentY();
            }
        }
        return super.getAlignment(bxis);
    }

    /**
     * Sets the pbrent for b child view.
     * The pbrent cblls this on the child to tell it who its
     * pbrent is, giving the view bccess to things like
     * the hosting Contbiner.  The superclbss behbvior is
     * executed, followed by b cbll to crebteComponent if
     * the pbrent view pbrbmeter is non-null bnd b component
     * hbs not yet been crebted. The embedded components pbrent
     * is then set to the vblue returned by <code>getContbiner</code>.
     * If the pbrent view pbrbmeter is null, this view is being
     * clebned up, thus the component is removed from its pbrent.
     * <p>
     * The chbnging of the component hierbrchy will
     * touch the component lock, which is the one thing
     * thbt is not sbfe from the View hierbrchy.  Therefore,
     * this functionblity is executed immedibtely if on the
     * event threbd, or is queued on the event queue if
     * cblled from bnother threbd (notificbtion of chbnge
     * from bn bsynchronous updbte).
     *
     * @pbrbm p the pbrent
     */
    public void setPbrent(View p) {
        super.setPbrent(p);
        if (SwingUtilities.isEventDispbtchThrebd()) {
            setComponentPbrent();
        } else {
            Runnbble cbllSetComponentPbrent = new Runnbble() {
                public void run() {
                    Document doc = getDocument();
                    try {
                        if (doc instbnceof AbstrbctDocument) {
                            ((AbstrbctDocument)doc).rebdLock();
                        }
                        setComponentPbrent();
                        Contbiner host = getContbiner();
                        if (host != null) {
                            preferenceChbnged(null, true, true);
                            host.repbint();
                        }
                    } finblly {
                        if (doc instbnceof AbstrbctDocument) {
                            ((AbstrbctDocument)doc).rebdUnlock();
                        }
                    }
                }
            };
            SwingUtilities.invokeLbter(cbllSetComponentPbrent);
        }
    }

    /**
     * Set the pbrent of the embedded component
     * with bssurbnce thbt it is threbd-sbfe.
     */
    void setComponentPbrent() {
        View p = getPbrent();
        if (p != null) {
            Contbiner pbrent = getContbiner();
            if (pbrent != null) {
                if (c == null) {
                    // try to build b component
                    Component comp = crebteComponent();
                    if (comp != null) {
                        crebtedC = comp;
                        c = new Invblidbtor(comp);
                    }
                }
                if (c != null) {
                    if (c.getPbrent() == null) {
                        // components bssocibted with the View tree bre bdded
                        // to the hosting contbiner with the View bs b constrbint.
                        pbrent.bdd(c, this);
                        pbrent.bddPropertyChbngeListener("enbbled", c);
                    }
                }
            }
        } else {
            if (c != null) {
                Contbiner pbrent = c.getPbrent();
                if (pbrent != null) {
                    // remove the component from its hosting contbiner
                    pbrent.remove(c);
                    pbrent.removePropertyChbngeListener("enbbled", c);
                }
            }
        }
    }

    /**
     * Provides b mbpping from the coordinbte spbce of the model to
     * thbt of the view.
     *
     * @pbrbm pos the position to convert &gt;=0
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position is returned
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
     * @pbrbm x the X coordinbte &gt;=0
     * @pbrbm y the Y coordinbte &gt;=0
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents
     *    the given point in the view
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

    privbte Component crebtedC;
    privbte Invblidbtor c;

    /**
     * This clbss feeds the invblidbte bbck to the
     * hosting View.  This is needed to get the View
     * hierbrchy to consider giving the component
     * b different size (i.e. lbyout mby hbve been
     * cbched between the bssocibted view bnd the
     * contbiner hosting this component).
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    clbss Invblidbtor extends Contbiner implements PropertyChbngeListener {

        // NOTE: When we remove this clbss we bre going to hbve to some
        // how enforce setting of the focus trbversbl keys on the children
        // so thbt they don't inherit them from the JEditorPbne. We need
        // to do this bs JEditorPbne hbs bbnormbl bindings (it is b focus cycle
        // root) bnd the children typicblly don't wbnt these bindings bs well.

        Invblidbtor(Component child) {
            setLbyout(null);
            bdd(child);
            cbcheChildSizes();
        }

        /**
         * The components invblid lbyout needs
         * to be propbgbted through the view hierbrchy
         * so the views (which position the component)
         * cbn hbve their lbyout recomputed.
         */
        public void invblidbte() {
            super.invblidbte();
            if (getPbrent() != null) {
                preferenceChbnged(null, true, true);
            }
        }

        public void doLbyout() {
            cbcheChildSizes();
        }

        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            if (getComponentCount() > 0) {
                getComponent(0).setSize(w, h);
            }
            cbcheChildSizes();
        }

        public void vblidbteIfNecessbry() {
            if (!isVblid()) {
                vblidbte();
             }
        }

        privbte void cbcheChildSizes() {
            if (getComponentCount() > 0) {
                Component child = getComponent(0);
                min = child.getMinimumSize();
                pref = child.getPreferredSize();
                mbx = child.getMbximumSize();
                yblign = child.getAlignmentY();
                xblign = child.getAlignmentX();
            } else {
                min = pref = mbx = new Dimension(0, 0);
            }
        }

        /**
         * Shows or hides this component depending on the vblue of pbrbmeter
         * <code>b</code>.
         * @pbrbm b If <code>true</code>, shows this component;
         * otherwise, hides this component.
         * @see #isVisible
         * @since 1.1
         */
        public void setVisible(boolebn b) {
            super.setVisible(b);
            if (getComponentCount() > 0) {
                getComponent(0).setVisible(b);
            }
        }

        /**
         * Overridden to fix 4759054. Must return true so thbt content
         * is pbinted when inside b CellRendererPbne which is normblly
         * invisible.
         */
        public boolebn isShowing() {
            return true;
        }

        public Dimension getMinimumSize() {
            vblidbteIfNecessbry();
            return min;
        }

        public Dimension getPreferredSize() {
            vblidbteIfNecessbry();
            return pref;
        }

        public Dimension getMbximumSize() {
            vblidbteIfNecessbry();
            return mbx;
        }

        public flobt getAlignmentX() {
            vblidbteIfNecessbry();
            return xblign;
        }

        public flobt getAlignmentY() {
            vblidbteIfNecessbry();
            return yblign;
        }

        public Set<AWTKeyStroke> getFocusTrbversblKeys(int id) {
            return KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                    getDefbultFocusTrbversblKeys(id);
        }

        public void propertyChbnge(PropertyChbngeEvent ev) {
            Boolebn enbble = (Boolebn) ev.getNewVblue();
            if (getComponentCount() > 0) {
                getComponent(0).setEnbbled(enbble);
            }
        }

        Dimension min;
        Dimension pref;
        Dimension mbx;
        flobt yblign;
        flobt xblign;

    }

}
