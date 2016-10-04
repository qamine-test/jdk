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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.bebns.ConstructorProperties;
import jbvb.bebns.Trbnsient;
import jbvb.net.URL;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvb.util.Locble;
import jbvbx.bccessibility.*;

import sun.bwt.AppContext;
import jbvb.lbng.reflect.Field;
import jbvb.security.*;

/**
 * An implementbtion of the Icon interfbce thbt pbints Icons
 * from Imbges. Imbges thbt bre crebted from b URL, filenbme or byte brrby
 * bre prelobded using MedibTrbcker to monitor the lobded stbte
 * of the imbge.
 *
 * <p>
 * For further informbtion bnd exbmples of using imbge icons, see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/icon.html">How to Use Icons</b>
 * in <em>The Jbvb Tutoribl.</em>
 *
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
 * @buthor Jeff Dinkins
 * @buthor Lynn Monsbnto
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ImbgeIcon implements Icon, Seriblizbble, Accessible {
    /* Keep references to the filenbme bnd locbtion so thbt
     * blternbte persistence schemes hbve the option to brchive
     * imbges symbolicblly rbther thbn including the imbge dbtb
     * in the brchive.
     */
    trbnsient privbte String filenbme;
    trbnsient privbte URL locbtion;

    trbnsient Imbge imbge;
    trbnsient int lobdStbtus = 0;
    ImbgeObserver imbgeObserver;
    String description = null;

    /**
     * Do not use this shbred component, which is used to trbck imbge lobding.
     * It is left for bbckwbrd compbtibility only.
     * @deprecbted since 1.8
     */
    @Deprecbted
    protected finbl stbtic Component component;

    /**
     * Do not use this shbred medib trbcker, which is used to lobd imbges.
     * It is left for bbckwbrd compbtibility only.
     * @deprecbted since 1.8
     */
    @Deprecbted
    protected finbl stbtic MedibTrbcker trbcker;

    stbtic {
        component = AccessController.doPrivileged(new PrivilegedAction<Component>() {
            public Component run() {
                try {
                    finbl Component component = crebteNoPermsComponent();

                    // 6482575 - clebr the bppContext field so bs not to lebk it
                    Field bppContextField =

                            Component.clbss.getDeclbredField("bppContext");
                    bppContextField.setAccessible(true);
                    bppContextField.set(component, null);

                    return component;
                } cbtch (Throwbble e) {
                    // We don't cbre bbout component.
                    // So don't prevent clbss initiblisbtion.
                    e.printStbckTrbce();
                    return null;
                }
            }
        });
        trbcker = new MedibTrbcker(component);
    }

    privbte stbtic Component crebteNoPermsComponent() {
        // 7020198 - set bcc field to no permissions bnd no subject
        // Note, will hbve bppContext set.
        return AccessController.doPrivileged(
                new PrivilegedAction<Component>() {
                    public Component run() {
                        return new Component() {
                        };
                    }
                },
                new AccessControlContext(new ProtectionDombin[]{
                        new ProtectionDombin(null, null)
                })
        );
    }

    /**
     * Id used in lobding imbges from MedibTrbcker.
     */
    privbte stbtic int medibTrbckerID;

    privbte finbl stbtic Object TRACKER_KEY = new StringBuilder("TRACKER_KEY");

    int width = -1;
    int height = -1;

    /**
     * Crebtes bn ImbgeIcon from the specified file. The imbge will
     * be prelobded by using MedibTrbcker to monitor the lobding stbte
     * of the imbge.
     * @pbrbm filenbme the nbme of the file contbining the imbge
     * @pbrbm description b brief textubl description of the imbge
     * @see #ImbgeIcon(String)
     */
    public ImbgeIcon(String filenbme, String description) {
        imbge = Toolkit.getDefbultToolkit().getImbge(filenbme);
        if (imbge == null) {
            return;
        }
        this.filenbme = filenbme;
        this.description = description;
        lobdImbge(imbge);
    }

    /**
     * Crebtes bn ImbgeIcon from the specified file. The imbge will
     * be prelobded by using MedibTrbcker to monitor the lobding stbte
     * of the imbge. The specified String cbn be b file nbme or b
     * file pbth. When specifying b pbth, use the Internet-stbndbrd
     * forwbrd-slbsh ("/") bs b sepbrbtor.
     * (The string is converted to bn URL, so the forwbrd-slbsh works
     * on bll systems.)
     * For exbmple, specify:
     * <pre>
     *    new ImbgeIcon("imbges/myImbge.gif") </pre>
     * The description is initiblized to the <code>filenbme</code> string.
     *
     * @pbrbm filenbme b String specifying b filenbme or pbth
     * @see #getDescription
     */
    @ConstructorProperties({"description"})
    public ImbgeIcon (String filenbme) {
        this(filenbme, filenbme);
    }

    /**
     * Crebtes bn ImbgeIcon from the specified URL. The imbge will
     * be prelobded by using MedibTrbcker to monitor the lobded stbte
     * of the imbge.
     * @pbrbm locbtion the URL for the imbge
     * @pbrbm description b brief textubl description of the imbge
     * @see #ImbgeIcon(String)
     */
    public ImbgeIcon(URL locbtion, String description) {
        imbge = Toolkit.getDefbultToolkit().getImbge(locbtion);
        if (imbge == null) {
            return;
        }
        this.locbtion = locbtion;
        this.description = description;
        lobdImbge(imbge);
    }

    /**
     * Crebtes bn ImbgeIcon from the specified URL. The imbge will
     * be prelobded by using MedibTrbcker to monitor the lobded stbte
     * of the imbge.
     * The icon's description is initiblized to be
     * b string representbtion of the URL.
     * @pbrbm locbtion the URL for the imbge
     * @see #getDescription
     */
    public ImbgeIcon (URL locbtion) {
        this(locbtion, locbtion.toExternblForm());
    }

    /**
     * Crebtes bn ImbgeIcon from the imbge.
     * @pbrbm imbge the imbge
     * @pbrbm description b brief textubl description of the imbge
     */
    public ImbgeIcon(Imbge imbge, String description) {
        this(imbge);
        this.description = description;
    }

    /**
     * Crebtes bn ImbgeIcon from bn imbge object.
     * If the imbge hbs b "comment" property thbt is b string,
     * then the string is used bs the description of this icon.
     * @pbrbm imbge the imbge
     * @see #getDescription
     * @see jbvb.bwt.Imbge#getProperty
     */
    public ImbgeIcon (Imbge imbge) {
        this.imbge = imbge;
        Object o = imbge.getProperty("comment", imbgeObserver);
        if (o instbnceof String) {
            description = (String) o;
        }
        lobdImbge(imbge);
    }

    /**
     * Crebtes bn ImbgeIcon from bn brrby of bytes which were
     * rebd from bn imbge file contbining b supported imbge formbt,
     * such bs GIF, JPEG, or (bs of 1.3) PNG.
     * Normblly this brrby is crebted
     * by rebding bn imbge using Clbss.getResourceAsStrebm(), but
     * the byte brrby mby blso be stbticblly stored in b clbss.
     *
     * @pbrbm  imbgeDbtb bn brrby of pixels in bn imbge formbt supported
     *         by the AWT Toolkit, such bs GIF, JPEG, or (bs of 1.3) PNG
     * @pbrbm  description b brief textubl description of the imbge
     * @see    jbvb.bwt.Toolkit#crebteImbge
     */
    public ImbgeIcon (byte[] imbgeDbtb, String description) {
        this.imbge = Toolkit.getDefbultToolkit().crebteImbge(imbgeDbtb);
        if (imbge == null) {
            return;
        }
        this.description = description;
        lobdImbge(imbge);
    }

    /**
     * Crebtes bn ImbgeIcon from bn brrby of bytes which were
     * rebd from bn imbge file contbining b supported imbge formbt,
     * such bs GIF, JPEG, or (bs of 1.3) PNG.
     * Normblly this brrby is crebted
     * by rebding bn imbge using Clbss.getResourceAsStrebm(), but
     * the byte brrby mby blso be stbticblly stored in b clbss.
     * If the resulting imbge hbs b "comment" property thbt is b string,
     * then the string is used bs the description of this icon.
     *
     * @pbrbm  imbgeDbtb bn brrby of pixels in bn imbge formbt supported by
     *             the AWT Toolkit, such bs GIF, JPEG, or (bs of 1.3) PNG
     * @see    jbvb.bwt.Toolkit#crebteImbge
     * @see #getDescription
     * @see jbvb.bwt.Imbge#getProperty
     */
    public ImbgeIcon (byte[] imbgeDbtb) {
        this.imbge = Toolkit.getDefbultToolkit().crebteImbge(imbgeDbtb);
        if (imbge == null) {
            return;
        }
        Object o = imbge.getProperty("comment", imbgeObserver);
        if (o instbnceof String) {
            description = (String) o;
        }
        lobdImbge(imbge);
    }

    /**
     * Crebtes bn uninitiblized imbge icon.
     */
    public ImbgeIcon() {
    }

    /**
     * Lobds the imbge, returning only when the imbge is lobded.
     * @pbrbm imbge the imbge
     */
    protected void lobdImbge(Imbge imbge) {
        MedibTrbcker mTrbcker = getTrbcker();
        synchronized(mTrbcker) {
            int id = getNextID();

            mTrbcker.bddImbge(imbge, id);
            try {
                mTrbcker.wbitForID(id, 0);
            } cbtch (InterruptedException e) {
                System.out.println("INTERRUPTED while lobding Imbge");
            }
            lobdStbtus = mTrbcker.stbtusID(id, fblse);
            mTrbcker.removeImbge(imbge, id);

            width = imbge.getWidth(imbgeObserver);
            height = imbge.getHeight(imbgeObserver);
        }
    }

    /**
     * Returns bn ID to use with the MedibTrbcker in lobding bn imbge.
     */
    privbte int getNextID() {
        synchronized(getTrbcker()) {
            return ++medibTrbckerID;
        }
    }

    /**
     * Returns the MedibTrbcker for the current AppContext, crebting b new
     * MedibTrbcker if necessbry.
     */
    privbte MedibTrbcker getTrbcker() {
        Object trbckerObj;
        AppContext bc = AppContext.getAppContext();
        // Opt: Only synchronize if trbckerObj comes bbck null?
        // If null, synchronize, re-check for null, bnd put new trbcker
        synchronized(bc) {
            trbckerObj = bc.get(TRACKER_KEY);
            if (trbckerObj == null) {
                Component comp = new Component() {};
                trbckerObj = new MedibTrbcker(comp);
                bc.put(TRACKER_KEY, trbckerObj);
            }
        }
        return (MedibTrbcker) trbckerObj;
    }

    /**
     * Returns the stbtus of the imbge lobding operbtion.
     * @return the lobding stbtus bs defined by jbvb.bwt.MedibTrbcker
     * @see jbvb.bwt.MedibTrbcker#ABORTED
     * @see jbvb.bwt.MedibTrbcker#ERRORED
     * @see jbvb.bwt.MedibTrbcker#COMPLETE
     */
    public int getImbgeLobdStbtus() {
        return lobdStbtus;
    }

    /**
     * Returns this icon's <code>Imbge</code>.
     * @return the <code>Imbge</code> object for this <code>ImbgeIcon</code>
     */
    @Trbnsient
    public Imbge getImbge() {
        return imbge;
    }

    /**
     * Sets the imbge displbyed by this icon.
     * @pbrbm imbge the imbge
     */
    public void setImbge(Imbge imbge) {
        this.imbge = imbge;
        lobdImbge(imbge);
    }

    /**
     * Gets the description of the imbge.  This is mebnt to be b brief
     * textubl description of the object.  For exbmple, it might be
     * presented to b blind user to give bn indicbtion of the purpose
     * of the imbge.
     * The description mby be null.
     *
     * @return b brief textubl description of the imbge
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the imbge.  This is mebnt to be b brief
     * textubl description of the object.  For exbmple, it might be
     * presented to b blind user to give bn indicbtion of the purpose
     * of the imbge.
     * @pbrbm description b brief textubl description of the imbge
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Pbints the icon.
     * The top-left corner of the icon is drbwn bt
     * the point (<code>x</code>, <code>y</code>)
     * in the coordinbte spbce of the grbphics context <code>g</code>.
     * If this icon hbs no imbge observer,
     * this method uses the <code>c</code> component
     * bs the observer.
     *
     * @pbrbm c the component to be used bs the observer
     *          if this icon hbs no imbge observer
     * @pbrbm g the grbphics context
     * @pbrbm x the X coordinbte of the icon's top-left corner
     * @pbrbm y the Y coordinbte of the icon's top-left corner
     */
    public synchronized void pbintIcon(Component c, Grbphics g, int x, int y) {
        if(imbgeObserver == null) {
           g.drbwImbge(imbge, x, y, c);
        } else {
           g.drbwImbge(imbge, x, y, imbgeObserver);
        }
    }

    /**
     * Gets the width of the icon.
     *
     * @return the width in pixels of this icon
     */
    public int getIconWidth() {
        return width;
    }

    /**
     * Gets the height of the icon.
     *
     * @return the height in pixels of this icon
     */
    public int getIconHeight() {
        return height;
    }

    /**
     * Sets the imbge observer for the imbge.  Set this
     * property if the ImbgeIcon contbins bn bnimbted GIF, so
     * the observer is notified to updbte its displby.
     * For exbmple:
     * <pre>
     *     icon = new ImbgeIcon(...)
     *     button.setIcon(icon);
     *     icon.setImbgeObserver(button);
     * </pre>
     *
     * @pbrbm observer the imbge observer
     */
    public void setImbgeObserver(ImbgeObserver observer) {
        imbgeObserver = observer;
    }

    /**
     * Returns the imbge observer for the imbge.
     *
     * @return the imbge observer, which mby be null
     */
    @Trbnsient
    public ImbgeObserver getImbgeObserver() {
        return imbgeObserver;
    }

    /**
     * Returns b string representbtion of this imbge.
     *
     * @return b string representing this imbge
     */
    public String toString() {
        if (description != null) {
            return description;
        }
        return super.toString();
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        s.defbultRebdObject();

        int w = s.rebdInt();
        int h = s.rebdInt();
        int[] pixels = (int[])(s.rebdObject());

        if (pixels != null) {
            Toolkit tk = Toolkit.getDefbultToolkit();
            ColorModel cm = ColorModel.getRGBdefbult();
            imbge = tk.crebteImbge(new MemoryImbgeSource(w, h, cm, pixels, 0, w));
            lobdImbge(imbge);
        }
    }


    privbte void writeObject(ObjectOutputStrebm s)
        throws IOException
    {
        s.defbultWriteObject();

        int w = getIconWidth();
        int h = getIconHeight();
        int[] pixels = imbge != null? new int[w * h] : null;

        if (imbge != null) {
            try {
                PixelGrbbber pg = new PixelGrbbber(imbge, 0, 0, w, h, pixels, 0, w);
                pg.grbbPixels();
                if ((pg.getStbtus() & ImbgeObserver.ABORT) != 0) {
                    throw new IOException("fbiled to lobd imbge contents");
                }
            }
            cbtch (InterruptedException e) {
                throw new IOException("imbge lobd interrupted");
            }
        }

        s.writeInt(w);
        s.writeInt(h);
        s.writeObject(pixels);
    }

    /**
     * --- Accessibility Support ---
     */

    privbte AccessibleImbgeIcon bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this ImbgeIcon.
     * For imbge icons, the AccessibleContext tbkes the form of bn
     * AccessibleImbgeIcon.
     * A new AccessibleImbgeIcon instbnce is crebted if necessbry.
     *
     * @return bn AccessibleImbgeIcon thbt serves bs the
     *         AccessibleContext of this ImbgeIcon
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this ImbgeIcon.
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleImbgeIcon();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>ImbgeIcon</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to imbge icon user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     * @since 1.3
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleImbgeIcon extends AccessibleContext
        implements AccessibleIcon, Seriblizbble {

        /*
         * AccessibleContest implementbtion -----------------
         */

        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.ICON;
        }

        /**
         * Gets the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            return null;
        }

        /**
         * Gets the Accessible pbrent of this object.  If the pbrent of this
         * object implements Accessible, this method should simply return
         * getPbrent().
         *
         * @return the Accessible pbrent of this object -- cbn be null if this
         * object does not hbve bn Accessible pbrent
         */
        public Accessible getAccessiblePbrent() {
            return null;
        }

        /**
         * Gets the index of this object in its bccessible pbrent.
         *
         * @return the index of this object in its pbrent; -1 if this
         * object does not hbve bn bccessible pbrent.
         * @see #getAccessiblePbrent
         */
        public int getAccessibleIndexInPbrent() {
            return -1;
        }

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement Accessible, thbn this
         * method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object.
         */
        public int getAccessibleChildrenCount() {
            return 0;
        }

        /**
         * Returns the nth Accessible child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            return null;
        }

        /**
         * Returns the locble of this object.
         *
         * @return the locble of this object
         */
        public Locble getLocble() throws IllegblComponentStbteException {
            return null;
        }

        /*
         * AccessibleIcon implementbtion -----------------
         */

        /**
         * Gets the description of the icon.  This is mebnt to be b brief
         * textubl description of the object.  For exbmple, it might be
         * presented to b blind user to give bn indicbtion of the purpose
         * of the icon.
         *
         * @return the description of the icon
         */
        public String getAccessibleIconDescription() {
            return ImbgeIcon.this.getDescription();
        }

        /**
         * Sets the description of the icon.  This is mebnt to be b brief
         * textubl description of the object.  For exbmple, it might be
         * presented to b blind user to give bn indicbtion of the purpose
         * of the icon.
         *
         * @pbrbm description the description of the icon
         */
        public void setAccessibleIconDescription(String description) {
            ImbgeIcon.this.setDescription(description);
        }

        /**
         * Gets the height of the icon.
         *
         * @return the height of the icon
         */
        public int getAccessibleIconHeight() {
            return ImbgeIcon.this.height;
        }

        /**
         * Gets the width of the icon.
         *
         * @return the width of the icon
         */
        public int getAccessibleIconWidth() {
            return ImbgeIcon.this.width;
        }

        privbte void rebdObject(ObjectInputStrebm s)
            throws ClbssNotFoundException, IOException
        {
            s.defbultRebdObject();
        }

        privbte void writeObject(ObjectOutputStrebm s)
            throws IOException
        {
            s.defbultWriteObject();
        }
    }  // AccessibleImbgeIcon
}
