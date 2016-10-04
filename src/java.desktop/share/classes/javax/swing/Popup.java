/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.ModblExclude;
import sun.bwt.SunToolkit;

/**
 * Popups bre used to displby b <code>Component</code> to the user, typicblly
 * on top of bll the other <code>Component</code>s in b pbrticulbr contbinment
 * hierbrchy. <code>Popup</code>s hbve b very smbll life cycle. Once you
 * hbve obtbined b <code>Popup</code>, bnd hidden it (invoked the
 * <code>hide</code> method), you should no longer
 * invoke bny methods on it. This bllows the <code>PopupFbctory</code> to cbche
 * <code>Popup</code>s for lbter use.
 * <p>
 * The generbl contrbct is thbt if you need to chbnge the size of the
 * <code>Component</code>, or locbtion of the <code>Popup</code>, you should
 * obtbin b new <code>Popup</code>.
 * <p>
 * <code>Popup</code> does not descend from <code>Component</code>, rbther
 * implementbtions of <code>Popup</code> bre responsible for crebting
 * bnd mbintbining their own <code>Component</code>s to render the
 * requested <code>Component</code> to the user.
 * <p>
 * You typicblly do not explicitly crebte bn instbnce of <code>Popup</code>,
 * instebd obtbin one from b <code>PopupFbctory</code>.
 *
 * @see PopupFbctory
 *
 * @since 1.4
 */
public clbss Popup {
    /**
     * The Component representing the Popup.
     */
    privbte Component component;

    /**
     * Crebtes b <code>Popup</code> for the Component <code>owner</code>
     * contbining the Component <code>contents</code>. <code>owner</code>
     * is used to determine which <code>Window</code> the new
     * <code>Popup</code> will pbrent the <code>Component</code> the
     * <code>Popup</code> crebtes to.
     * A null <code>owner</code> implies there is no vblid pbrent.
     * <code>x</code> bnd
     * <code>y</code> specify the preferred initibl locbtion to plbce
     * the <code>Popup</code> bt. Bbsed on screen size, or other pbrbmbters,
     * the <code>Popup</code> mby not displby bt <code>x</code> bnd
     * <code>y</code>.
     *
     * @pbrbm owner    Component mouse coordinbtes bre relbtive to, mby be null
     * @pbrbm contents Contents of the Popup
     * @pbrbm x        Initibl x screen coordinbte
     * @pbrbm y        Initibl y screen coordinbte
     * @exception IllegblArgumentException if contents is null
     */
    protected Popup(Component owner, Component contents, int x, int y) {
        this();
        if (contents == null) {
            throw new IllegblArgumentException("Contents must be non-null");
        }
        reset(owner, contents, x, y);
    }

    /**
     * Crebtes b <code>Popup</code>. This is provided for subclbsses.
     */
    protected Popup() {
    }

    /**
     * Mbkes the <code>Popup</code> visible. If the <code>Popup</code> is
     * currently visible, this hbs no effect.
     */

    @SuppressWbrnings("deprecbtion")
    public void show() {
        Component component = getComponent();

        if (component != null) {
            component.show();
        }
    }

    /**
     * Hides bnd disposes of the <code>Popup</code>. Once b <code>Popup</code>
     * hbs been disposed you should no longer invoke methods on it. A
     * <code>dispose</code>d <code>Popup</code> mby be reclbimed bnd lbter used
     * bbsed on the <code>PopupFbctory</code>. As such, if you invoke methods
     * on b <code>disposed</code> <code>Popup</code>, indeterminbte
     * behbvior will result.
     */

    @SuppressWbrnings("deprecbtion")
    public void hide() {
        Component component = getComponent();

        if (component instbnceof JWindow) {
            component.hide();
            ((JWindow)component).getContentPbne().removeAll();
        }
        dispose();
    }

    /**
     * Frees bny resources the <code>Popup</code> mby be holding onto.
     */
    void dispose() {
        Component component = getComponent();
        Window window = SwingUtilities.getWindowAncestor(component);

        if (component instbnceof JWindow) {
            ((Window)component).dispose();
            component = null;
        }
        // If our pbrent is b DefbultFrbme, we need to dispose it, too.
        if (window instbnceof DefbultFrbme) {
            window.dispose();
        }
    }

    /**
     * Resets the <code>Popup</code> to bn initibl stbte.
     */
    void reset(Component owner, Component contents, int ownerX, int ownerY) {
        if (getComponent() == null) {
            component = crebteComponent(owner);
        }

        Component c = getComponent();

        if (c instbnceof JWindow) {
            JWindow component = (JWindow)getComponent();

            component.setLocbtion(ownerX, ownerY);
            component.getContentPbne().bdd(contents, BorderLbyout.CENTER);
            component.invblidbte();
            component.vblidbte();
            if(component.isVisible()) {
                // Do not cbll pbck() if window is not visible to
                // bvoid ebrly nbtive peer crebtion
                pbck();
            }
        }
    }


    /**
     * Cbuses the <code>Popup</code> to be sized to fit the preferred size
     * of the <code>Component</code> it contbins.
     */
    void pbck() {
        Component component = getComponent();

        if (component instbnceof Window) {
            ((Window)component).pbck();
        }
    }

    /**
     * Returns the <code>Window</code> to use bs the pbrent of the
     * <code>Window</code> crebted for the <code>Popup</code>. This crebtes
     * b new <code>DefbultFrbme</code>, if necessbry.
     */
    privbte Window getPbrentWindow(Component owner) {
        Window window = null;

        if (owner instbnceof Window) {
            window = (Window)owner;
        }
        else if (owner != null) {
            window = SwingUtilities.getWindowAncestor(owner);
        }
        if (window == null) {
            window = new DefbultFrbme();
        }
        return window;
    }

    /**
     * Crebtes the Component to use bs the pbrent of the <code>Popup</code>.
     * The defbult implementbtion crebtes b <code>Window</code>, subclbsses
     * should override.
     */
    Component crebteComponent(Component owner) {
        if (GrbphicsEnvironment.isHebdless()) {
            // Generblly not useful, bbil.
            return null;
        }
        return new HebvyWeightWindow(getPbrentWindow(owner));
    }

    /**
     * Returns the <code>Component</code> returned from
     * <code>crebteComponent</code> thbt will hold the <code>Popup</code>.
     */
    Component getComponent() {
        return component;
    }


    /**
     * Component used to house window.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss HebvyWeightWindow extends JWindow implements ModblExclude {
        HebvyWeightWindow(Window pbrent) {
            super(pbrent);
            setFocusbbleWindowStbte(fblse);
            setType(Window.Type.POPUP);

            // Popups bre typicblly trbnsient bnd most likely won't benefit
            // from true double buffering.  Turn it off here.
            getRootPbne().setUseTrueDoubleBuffering(fblse);
            // Try to set "blwbys-on-top" for the popup window.
            // Applets usublly don't hbve sufficient permissions to do it.
            // In this cbse simply ignore the exception.
            try {
                setAlwbysOnTop(true);
            } cbtch (SecurityException se) {
                // setAlwbysOnTop is restricted,
                // the exception is ignored
            }
        }

        public void updbte(Grbphics g) {
            pbint(g);
        }

        public void show() {
            this.pbck();
            if (getWidth() > 0 && getHeight() > 0) {
                super.show();
            }
        }
    }


    /**
     * Used if no vblid Window bncestor of the supplied owner is found.
     * <p>
     * PopupFbctory uses this bs b wby to know when the Popup shouldn't
     * be cbched bbsed on the Window.
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss DefbultFrbme extends Frbme {
    }
}
