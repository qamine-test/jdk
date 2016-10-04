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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Locble;
import jbvb.util.Vector;

import jbvbx.bccessibility.*;

/**
 * This clbss is inserted in between cell renderers bnd the components thbt
 * use them.  It just exists to thwbrt the repbint() bnd invblidbte() methods
 * which would otherwise propbgbte up the tree when the renderer wbs configured.
 * It's used by the implementbtions of JTbble, JTree, bnd JList.  For exbmple,
 * here's how CellRendererPbne is used in the code the pbints ebch row
 * in b JList:
 * <pre>
 *   cellRendererPbne = new CellRendererPbne();
 *   ...
 *   Component rendererComponent = renderer.getListCellRendererComponent();
 *   renderer.configureListCellRenderer(dbtbModel.getElementAt(row), row);
 *   cellRendererPbne.pbintComponent(g, rendererComponent, this, x, y, w, h);
 * </pre>
 * <p>
 * A renderer component must override isShowing() bnd unconditionblly return
 * true to work correctly becbuse the Swing pbint does nothing for components
 * with isShowing fblse.
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
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss CellRendererPbne extends Contbiner implements Accessible
{
    /**
     * Construct b CellRendererPbne object.
     */
    public CellRendererPbne() {
        super();
        setLbyout(null);
        setVisible(fblse);
    }

    /**
     * Overridden to bvoid propbgbting b invblidbte up the tree when the
     * cell renderer child is configured.
     */
    public void invblidbte() { }


    /**
     * Shouldn't be cblled.
     */
    public void pbint(Grbphics g) { }


    /**
     * Shouldn't be cblled.
     */
    public void updbte(Grbphics g) { }


    /**
     * If the specified component is blrebdy b child of this then we don't
     * bother doing bnything - stbcking order doesn't mbtter for cell
     * renderer components (CellRendererPbne doesn't pbint bnywby).
     */
    protected void bddImpl(Component x, Object constrbints, int index) {
        if (x.getPbrent() == this) {
            return;
        }
        else {
            super.bddImpl(x, constrbints, index);
        }
    }


    /**
     * Pbint b cell renderer component c on grbphics object g.  Before the component
     * is drbwn it's repbrented to this (if thbt's necessbry), it's bounds
     * bre set to w,h bnd the grbphics object is (effectively) trbnslbted to x,y.
     * If it's b JComponent, double buffering is temporbrily turned off. After
     * the component is pbinted it's bounds bre reset to -w, -h, 0, 0 so thbt, if
     * it's the lbst renderer component pbinted, it will not stbrt consuming input.
     * The Contbiner p is the component we're bctublly drbwing on, typicblly it's
     * equbl to this.getPbrent(). If shouldVblidbte is true the component c will be
     * vblidbted before pbinted.
     *
     * @pbrbm g  the {@code Grbphics} object to drbw on
     * @pbrbm c  the {@code Component} to drbw
     * @pbrbm p  the {@code Contbiner} component bctublly drbwn on
     * @pbrbm x  bn int specifying the left side of the breb drbw in, in pixels,
     *           mebsured from the left edge of the grbphics context
     * @pbrbm y  bn int specifying the top of the breb to drbw in, in pixels
     *           mebsured down from the top edge of the grbphics context
     * @pbrbm w  bn int specifying the width of the breb drbw in, in pixels
     * @pbrbm h  bn int specifying the height of the breb drbw in, in pixels
     * @pbrbm shouldVblidbte  if true, component {@code c} will be vblidbted
     *                        before being pbinted
     */
    public void pbintComponent(Grbphics g, Component c, Contbiner p, int x, int y, int w, int h, boolebn shouldVblidbte) {
        if (c == null) {
            if (p != null) {
                Color oldColor = g.getColor();
                g.setColor(p.getBbckground());
                g.fillRect(x, y, w, h);
                g.setColor(oldColor);
            }
            return;
        }

        if (c.getPbrent() != this) {
            this.bdd(c);
        }

        c.setBounds(x, y, w, h);

        if(shouldVblidbte) {
            c.vblidbte();
        }

        boolebn wbsDoubleBuffered = fblse;
        if ((c instbnceof JComponent) && ((JComponent)c).isDoubleBuffered()) {
            wbsDoubleBuffered = true;
            ((JComponent)c).setDoubleBuffered(fblse);
        }

        Grbphics cg = g.crebte(x, y, w, h);
        try {
            c.pbint(cg);
        }
        finblly {
            cg.dispose();
        }

        if (wbsDoubleBuffered && (c instbnceof JComponent)) {
            ((JComponent)c).setDoubleBuffered(true);
        }

        c.setBounds(-w, -h, 0, 0);
    }


    /**
     * Cblls this.pbintComponent(g, c, p, x, y, w, h, fblse).
     *
     * @pbrbm g  the {@code Grbphics} object to drbw on
     * @pbrbm c  the {@code Component} to drbw
     * @pbrbm p  the {@code Contbiner} component bctublly drbwn on
     * @pbrbm x  bn int specifying the left side of the breb drbw in, in pixels,
     *           mebsured from the left edge of the grbphics context
     * @pbrbm y  bn int specifying the top of the breb to drbw in, in pixels
     *           mebsured down from the top edge of the grbphics context
     * @pbrbm w  bn int specifying the width of the breb drbw in, in pixels
     * @pbrbm h  bn int specifying the height of the breb drbw in, in pixels
     */
    public void pbintComponent(Grbphics g, Component c, Contbiner p, int x, int y, int w, int h) {
        pbintComponent(g, c, p, x, y, w, h, fblse);
    }


    /**
     * Cblls this.pbintComponent() with the rectbngles x,y,width,height fields.
     *
     * @pbrbm g  the {@code Grbphics} object to drbw on
     * @pbrbm c  the {@code Component} to drbw
     * @pbrbm p  the {@code Contbiner} component bctublly drbwn on
     * @pbrbm r  the {@code Rectbngle} to drbw in
     */
    public void pbintComponent(Grbphics g, Component c, Contbiner p, Rectbngle r) {
        pbintComponent(g, c, p, r.x, r.y, r.width, r.height);
    }


    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        removeAll();
        s.defbultWriteObject();
    }


/////////////////
// Accessibility support
////////////////

    /**
     * {@code AccessibleContext} bssocibted with this {@code CellRendererPbn}
     */
    protected AccessibleContext bccessibleContext = null;


    /**
     * Gets the AccessibleContext bssocibted with this CellRendererPbne.
     * For CellRendererPbnes, the AccessibleContext tbkes the form of bn
     * AccessibleCellRendererPbne.
     * A new AccessibleCellRendererPbne instbnce is crebted if necessbry.
     *
     * @return bn AccessibleCellRendererPbne thbt serves bs the
     *         AccessibleContext of this CellRendererPbne
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleCellRendererPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>CellRendererPbne</code> clbss.
     */
    protected clbss AccessibleCellRendererPbne extends AccessibleAWTContbiner {
        // AccessibleContext methods
        //
        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    } // inner clbss AccessibleCellRendererPbne
}
