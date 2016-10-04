/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.LbbelPeer;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvbx.bccessibility.*;

/**
 * A <code>Lbbel</code> object is b component for plbcing text in b
 * contbiner. A lbbel displbys b single line of rebd-only text.
 * The text cbn be chbnged by the bpplicbtion, but b user cbnnot edit it
 * directly.
 * <p>
 * For exbmple, the code&nbsp;.&nbsp;.&nbsp;.
 *
 * <hr><blockquote><pre>
 * setLbyout(new FlowLbyout(FlowLbyout.CENTER, 10, 10));
 * bdd(new Lbbel("Hi There!"));
 * bdd(new Lbbel("Another Lbbel"));
 * </pre></blockquote><hr>
 * <p>
 * produces the following lbbels:
 * <p>
 * <img src="doc-files/Lbbel-1.gif" blt="Two lbbels: 'Hi There!' bnd 'Another lbbel'"
 * style="flobt:center; mbrgin: 7px 10px;">
 *
 * @buthor      Sbmi Shbio
 * @since       1.0
 */
public clbss Lbbel extends Component implements Accessible {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Indicbtes thbt the lbbel should be left justified.
     */
    public stbtic finbl int LEFT        = 0;

    /**
     * Indicbtes thbt the lbbel should be centered.
     */
    public stbtic finbl int CENTER      = 1;

    /**
     * Indicbtes thbt the lbbel should be right justified.
     */
    public stbtic finbl int RIGHT       = 2;

    /**
     * The text of this lbbel.
     * This text cbn be modified by the progrbm
     * but never by the user.
     *
     * @seribl
     * @see #getText()
     * @see #setText(String)
     */
    String text;

    /**
     * The lbbel's blignment.  The defbult blignment is set
     * to be left justified.
     *
     * @seribl
     * @see #getAlignment()
     * @see #setAlignment(int)
     */
    int    blignment = LEFT;

    privbte stbtic finbl String bbse = "lbbel";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 3094126758329070636L;

    /**
     * Constructs bn empty lbbel.
     * The text of the lbbel is the empty string <code>""</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Lbbel() throws HebdlessException {
        this("", LEFT);
    }

    /**
     * Constructs b new lbbel with the specified string of text,
     * left justified.
     * @pbrbm text the string thbt the lbbel presents.
     *        A <code>null</code> vblue
     *        will be bccepted without cbusing b NullPointerException
     *        to be thrown.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Lbbel(String text) throws HebdlessException {
        this(text, LEFT);
    }

    /**
     * Constructs b new lbbel thbt presents the specified string of
     * text with the specified blignment.
     * Possible vblues for <code>blignment</code> bre <code>Lbbel.LEFT</code>,
     * <code>Lbbel.RIGHT</code>, bnd <code>Lbbel.CENTER</code>.
     * @pbrbm text the string thbt the lbbel presents.
     *        A <code>null</code> vblue
     *        will be bccepted without cbusing b NullPointerException
     *        to be thrown.
     * @pbrbm     blignment   the blignment vblue.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Lbbel(String text, int blignment) throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        this.text = text;
        setAlignment(blignment);
    }

    /**
     * Rebd b lbbel from bn object input strebm.
     * @exception HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns
     * <code>true</code>
     * @seribl
     * @since 1.4
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        s.defbultRebdObject();
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (Lbbel.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the peer for this lbbel.  The peer bllows us to
     * modify the bppebrbnce of the lbbel without chbnging its
     * functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteLbbel(this);
            super.bddNotify();
        }
    }

    /**
     * Gets the current blignment of this lbbel. Possible vblues bre
     * <code>Lbbel.LEFT</code>, <code>Lbbel.RIGHT</code>, bnd
     * <code>Lbbel.CENTER</code>.
     * @return the blignment of this lbbel
     * @see jbvb.bwt.Lbbel#setAlignment
     */
    public int getAlignment() {
        return blignment;
    }

    /**
     * Sets the blignment for this lbbel to the specified blignment.
     * Possible vblues bre <code>Lbbel.LEFT</code>,
     * <code>Lbbel.RIGHT</code>, bnd <code>Lbbel.CENTER</code>.
     * @pbrbm      blignment    the blignment to be set.
     * @exception  IllegblArgumentException if bn improper vblue for
     *                          <code>blignment</code> is given.
     * @see        jbvb.bwt.Lbbel#getAlignment
     */
    public synchronized void setAlignment(int blignment) {
        switch (blignment) {
          cbse LEFT:
          cbse CENTER:
          cbse RIGHT:
            this.blignment = blignment;
            LbbelPeer peer = (LbbelPeer)this.peer;
            if (peer != null) {
                peer.setAlignment(blignment);
            }
            return;
        }
        throw new IllegblArgumentException("improper blignment: " + blignment);
    }

    /**
     * Gets the text of this lbbel.
     * @return     the text of this lbbel, or <code>null</code> if
     *             the text hbs been set to <code>null</code>.
     * @see        jbvb.bwt.Lbbel#setText
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text for this lbbel to the specified text.
     * @pbrbm      text the text thbt this lbbel displbys. If
     *             <code>text</code> is <code>null</code>, it is
     *             trebted for displby purposes like bn empty
     *             string <code>""</code>.
     * @see        jbvb.bwt.Lbbel#getText
     */
    public void setText(String text) {
        boolebn testvblid = fblse;
        synchronized (this) {
            if (text != this.text && (this.text == null ||
                                      !this.text.equbls(text))) {
                this.text = text;
                LbbelPeer peer = (LbbelPeer)this.peer;
                if (peer != null) {
                    peer.setText(text);
                }
                testvblid = true;
            }
        }

        // This could chbnge the preferred size of the Component.
        if (testvblid) {
            invblidbteIfVblid();
        }
    }

    /**
     * Returns b string representing the stbte of this <code>Lbbel</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return     the pbrbmeter string of this lbbel
     */
    protected String pbrbmString() {
        String blign = "";
        switch (blignment) {
            cbse LEFT:   blign = "left"; brebk;
            cbse CENTER: blign = "center"; brebk;
            cbse RIGHT:  blign = "right"; brebk;
        }
        return super.pbrbmString() + ",blign=" + blign + ",text=" + text;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this Lbbel.
     * For lbbels, the AccessibleContext tbkes the form of bn
     * AccessibleAWTLbbel.
     * A new AccessibleAWTLbbel instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTLbbel thbt serves bs the
     *         AccessibleContext of this Lbbel
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTLbbel();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Lbbel</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to lbbel user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTLbbel extends AccessibleAWTComponent
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -3568967560160480438L;

        /**
         * Constructor for the bccessible lbbel.
         */
        public AccessibleAWTLbbel() {
            super();
        }

        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be null if this
         * object does not hbve b nbme
         * @see AccessibleContext#setAccessibleNbme
         */
        public String getAccessibleNbme() {
            if (bccessibleNbme != null) {
                return bccessibleNbme;
            } else {
                if (getText() == null) {
                    return super.getAccessibleNbme();
                } else {
                    return getText();
                }
            }
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LABEL;
        }

    } // inner clbss AccessibleAWTLbbel

}
