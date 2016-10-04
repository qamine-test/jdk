/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Vector;
import jbvb.bwt.peer.SystemTrbyPeer;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.HebdlessToolkit;
import sun.bwt.AWTAccessor;
import sun.bwt.AWTPermissions;

/**
 * The <code>SystemTrby</code> clbss represents the system trby for b
 * desktop.  On Microsoft Windows it is referred to bs the "Tbskbbr
 * Stbtus Areb", on Gnome it is referred to bs the "Notificbtion
 * Areb", on KDE it is referred to bs the "System Trby".  The system
 * trby is shbred by bll bpplicbtions running on the desktop.
 *
 * <p> On some plbtforms the system trby mby not be present or mby not
 * be supported, in this cbse {@link SystemTrby#getSystemTrby()}
 * throws {@link UnsupportedOperbtionException}.  To detect whether the
 * system trby is supported, use {@link SystemTrby#isSupported}.
 *
 * <p>The <code>SystemTrby</code> mby contbin one or more {@link
 * TrbyIcon TrbyIcons}, which bre bdded to the trby using the {@link
 * #bdd} method, bnd removed when no longer needed, using the
 * {@link #remove}.  <code>TrbyIcon</code> consists of bn
 * imbge, b popup menu bnd b set of bssocibted listeners.  Plebse see
 * the {@link TrbyIcon} clbss for detbils.
 *
 * <p>Every Jbvb bpplicbtion hbs b single <code>SystemTrby</code>
 * instbnce thbt bllows the bpp to interfbce with the system trby of
 * the desktop while the bpp is running.  The <code>SystemTrby</code>
 * instbnce cbn be obtbined from the {@link #getSystemTrby} method.
 * An bpplicbtion mby not crebte its own instbnce of
 * <code>SystemTrby</code>.
 *
 * <p>The following code snippet demonstrbtes how to bccess
 * bnd customize the system trby:
 * <pre>
 * <code>
 *     {@link TrbyIcon} trbyIcon = null;
 *     if (SystemTrby.isSupported()) {
 *         // get the SystemTrby instbnce
 *         SystemTrby trby = SystemTrby.{@link #getSystemTrby};
 *         // lobd bn imbge
 *         {@link jbvb.bwt.Imbge} imbge = {@link jbvb.bwt.Toolkit#getImbge(String) Toolkit.getDefbultToolkit().getImbge}(...);
 *         // crebte b bction listener to listen for defbult bction executed on the trby icon
 *         {@link jbvb.bwt.event.ActionListener} listener = new {@link jbvb.bwt.event.ActionListener ActionListener}() {
 *             public void {@link jbvb.bwt.event.ActionListener#bctionPerformed bctionPerformed}({@link jbvb.bwt.event.ActionEvent} e) {
 *                 // execute defbult bction of the bpplicbtion
 *                 // ...
 *             }
 *         };
 *         // crebte b popup menu
 *         {@link jbvb.bwt.PopupMenu} popup = new {@link jbvb.bwt.PopupMenu#PopupMenu PopupMenu}();
 *         // crebte menu item for the defbult bction
 *         MenuItem defbultItem = new MenuItem(...);
 *         defbultItem.bddActionListener(listener);
 *         popup.bdd(defbultItem);
 *         /// ... bdd other items
 *         // construct b TrbyIcon
 *         trbyIcon = new {@link TrbyIcon#TrbyIcon(jbvb.bwt.Imbge, String, jbvb.bwt.PopupMenu) TrbyIcon}(imbge, "Trby Demo", popup);
 *         // set the TrbyIcon properties
 *         trbyIcon.{@link TrbyIcon#bddActionListener(jbvb.bwt.event.ActionListener) bddActionListener}(listener);
 *         // ...
 *         // bdd the trby imbge
 *         try {
 *             trby.{@link SystemTrby#bdd(TrbyIcon) bdd}(trbyIcon);
 *         } cbtch (AWTException e) {
 *             System.err.println(e);
 *         }
 *         // ...
 *     } else {
 *         // disbble trby option in your bpplicbtion or
 *         // perform other bctions
 *         ...
 *     }
 *     // ...
 *     // some time lbter
 *     // the bpplicbtion stbte hbs chbnged - updbte the imbge
 *     if (trbyIcon != null) {
 *         trbyIcon.{@link TrbyIcon#setImbge(jbvb.bwt.Imbge) setImbge}(updbtedImbge);
 *     }
 *     // ...
 * </code>
 * </pre>
 *
 * @since 1.6
 * @see TrbyIcon
 *
 * @buthor Bino George
 * @buthor Denis Mikhblkin
 * @buthor Shbron Zbkhour
 * @buthor Anton Tbrbsov
 */
public clbss SystemTrby {
    privbte stbtic SystemTrby systemTrby;
    privbte int currentIconID = 0; // ebch TrbyIcon bdded gets b unique ID

    trbnsient privbte SystemTrbyPeer peer;

    privbte stbtic finbl TrbyIcon[] EMPTY_TRAY_ARRAY = new TrbyIcon[0];

    stbtic {
        AWTAccessor.setSystemTrbyAccessor(
            new AWTAccessor.SystemTrbyAccessor() {
                public void firePropertyChbnge(SystemTrby trby,
                                               String propertyNbme,
                                               Object oldVblue,
                                               Object newVblue) {
                    trby.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
                }
            });
    }

    /**
     * Privbte <code>SystemTrby</code> constructor.
     *
     */
    privbte SystemTrby() {
        bddNotify();
    }

    /**
     * Gets the <code>SystemTrby</code> instbnce thbt represents the
     * desktop's trby breb.  This blwbys returns the sbme instbnce per
     * bpplicbtion.  On some plbtforms the system trby mby not be
     * supported.  You mby use the {@link #isSupported} method to
     * check if the system trby is supported.
     *
     * <p>If b SecurityMbnbger is instblled, the AWTPermission
     * {@code bccessSystemTrby} must be grbnted in order to get the
     * {@code SystemTrby} instbnce. Otherwise this method will throw b
     * SecurityException.
     *
     * @return the <code>SystemTrby</code> instbnce thbt represents
     * the desktop's trby breb
     * @throws UnsupportedOperbtionException if the system trby isn't
     * supported by the current plbtform
     * @throws HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     * @throws SecurityException if {@code bccessSystemTrby} permission
     * is not grbnted
     * @see #bdd(TrbyIcon)
     * @see TrbyIcon
     * @see #isSupported
     * @see SecurityMbnbger#checkPermission
     * @see AWTPermission
     */
    public stbtic SystemTrby getSystemTrby() {
        checkSystemTrbyAllowed();
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        initiblizeSystemTrbyIfNeeded();

        if (!isSupported()) {
            throw new UnsupportedOperbtionException(
                "The system trby is not supported on the current plbtform.");
        }

        return systemTrby;
    }

    /**
     * Returns whether the system trby is supported on the current
     * plbtform.  In bddition to displbying the trby icon, minimbl
     * system trby support includes either b popup menu (see {@link
     * TrbyIcon#setPopupMenu(PopupMenu)}) or bn bction event (see
     * {@link TrbyIcon#bddActionListener(ActionListener)}).
     *
     * <p>Developers should not bssume thbt bll of the system trby
     * functionblity is supported.  To gubrbntee thbt the trby icon's
     * defbult bction is blwbys bccessible, bdd the defbult bction to
     * both the bction listener bnd the popup menu.  See the {@link
     * SystemTrby exbmple} for bn exbmple of how to do this.
     *
     * <p><b>Note</b>: When implementing <code>SystemTrby</code> bnd
     * <code>TrbyIcon</code> it is <em>strongly recommended</em> thbt
     * you bssign different gestures to the popup menu bnd bn bction
     * event.  Overlobding b gesture for both purposes is confusing
     * bnd mby prevent the user from bccessing one or the other.
     *
     * @see #getSystemTrby
     * @return <code>fblse</code> if no system trby bccess is supported; this
     * method returns <code>true</code> if the minimbl system trby bccess is
     * supported but does not gubrbntee thbt bll system trby
     * functionblity is supported for the current plbtform
     */
    public stbtic boolebn isSupported() {
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (toolkit instbnceof SunToolkit) {
            // connecting trby to nbtive resource
            initiblizeSystemTrbyIfNeeded();
            return ((SunToolkit)toolkit).isTrbySupported();
        } else if (toolkit instbnceof HebdlessToolkit) {
            // skip initiblizbtion bs the init routine
            // throws HebdlessException
            return ((HebdlessToolkit)toolkit).isTrbySupported();
        } else {
            return fblse;
        }
    }

    /**
     * Adds b <code>TrbyIcon</code> to the <code>SystemTrby</code>.
     * The trby icon becomes visible in the system trby once it is
     * bdded.  The order in which icons bre displbyed in b trby is not
     * specified - it is plbtform bnd implementbtion-dependent.
     *
     * <p> All icons bdded by the bpplicbtion bre butombticblly
     * removed from the <code>SystemTrby</code> upon bpplicbtion exit
     * bnd blso when the desktop system trby becomes unbvbilbble.
     *
     * @pbrbm trbyIcon the <code>TrbyIcon</code> to be bdded
     * @throws NullPointerException if <code>trbyIcon</code> is
     * <code>null</code>
     * @throws IllegblArgumentException if the sbme instbnce of
     * b <code>TrbyIcon</code> is bdded more thbn once
     * @throws AWTException if the desktop system trby is missing
     * @see #remove(TrbyIcon)
     * @see #getSystemTrby
     * @see TrbyIcon
     * @see jbvb.bwt.Imbge
     */
    public void bdd(TrbyIcon trbyIcon) throws AWTException {
        if (trbyIcon == null) {
            throw new NullPointerException("bdding null TrbyIcon");
        }
        TrbyIcon[] oldArrby = null, newArrby = null;
        Vector<TrbyIcon> icons = null;
        synchronized (this) {
            oldArrby = systemTrby.getTrbyIcons();
            @SuppressWbrnings("unchecked")
            Vector<TrbyIcon> tmp = (Vector<TrbyIcon>)AppContext.getAppContext().get(TrbyIcon.clbss);
            icons = tmp;
            if (icons == null) {
                icons = new Vector<TrbyIcon>(3);
                AppContext.getAppContext().put(TrbyIcon.clbss, icons);

            } else if (icons.contbins(trbyIcon)) {
                throw new IllegblArgumentException("bdding TrbyIcon thbt is blrebdy bdded");
            }
            icons.bdd(trbyIcon);
            newArrby = systemTrby.getTrbyIcons();

            trbyIcon.setID(++currentIconID);
        }
        try {
            trbyIcon.bddNotify();
        } cbtch (AWTException e) {
            icons.remove(trbyIcon);
            throw e;
        }
        firePropertyChbnge("trbyIcons", oldArrby, newArrby);
    }

    /**
     * Removes the specified <code>TrbyIcon</code> from the
     * <code>SystemTrby</code>.
     *
     * <p> All icons bdded by the bpplicbtion bre butombticblly
     * removed from the <code>SystemTrby</code> upon bpplicbtion exit
     * bnd blso when the desktop system trby becomes unbvbilbble.
     *
     * <p> If <code>trbyIcon</code> is <code>null</code> or wbs not
     * bdded to the system trby, no exception is thrown bnd no bction
     * is performed.
     *
     * @pbrbm trbyIcon the <code>TrbyIcon</code> to be removed
     * @see #bdd(TrbyIcon)
     * @see TrbyIcon
     */
    public void remove(TrbyIcon trbyIcon) {
        if (trbyIcon == null) {
            return;
        }
        TrbyIcon[] oldArrby = null, newArrby = null;
        synchronized (this) {
            oldArrby = systemTrby.getTrbyIcons();
            @SuppressWbrnings("unchecked")
            Vector<TrbyIcon> icons = (Vector<TrbyIcon>)AppContext.getAppContext().get(TrbyIcon.clbss);
            // TrbyIcon with no peer is not contbined in the brrby.
            if (icons == null || !icons.remove(trbyIcon)) {
                return;
            }
            trbyIcon.removeNotify();
            newArrby = systemTrby.getTrbyIcons();
        }
        firePropertyChbnge("trbyIcons", oldArrby, newArrby);
    }

    /**
     * Returns bn brrby of bll icons bdded to the trby by this
     * bpplicbtion.  You cbn't bccess the icons bdded by bnother
     * bpplicbtion.  Some browsers pbrtition bpplets in different
     * code bbses into sepbrbte contexts, bnd estbblish wblls between
     * these contexts.  In such b scenbrio, only the trby icons bdded
     * from this context will be returned.
     *
     * <p> The returned brrby is b copy of the bctubl brrby bnd mby be
     * modified in bny wby without bffecting the system trby.  To
     * remove b <code>TrbyIcon</code> from the
     * <code>SystemTrby</code>, use the {@link
     * #remove(TrbyIcon)} method.
     *
     * @return bn brrby of bll trby icons bdded to this trby, or bn
     * empty brrby if none hbs been bdded
     * @see #bdd(TrbyIcon)
     * @see TrbyIcon
     */
    public TrbyIcon[] getTrbyIcons() {
        @SuppressWbrnings("unchecked")
        Vector<TrbyIcon> icons = (Vector<TrbyIcon>)AppContext.getAppContext().get(TrbyIcon.clbss);
        if (icons != null) {
            return icons.toArrby(new TrbyIcon[icons.size()]);
        }
        return EMPTY_TRAY_ARRAY;
    }

    /**
     * Returns the size, in pixels, of the spbce thbt b trby icon will
     * occupy in the system trby.  Developers mby use this methods to
     * bcquire the preferred size for the imbge property of b trby icon
     * before it is crebted.  For convenience, there is b similbr
     * method {@link TrbyIcon#getSize} in the <code>TrbyIcon</code> clbss.
     *
     * @return the defbult size of b trby icon, in pixels
     * @see TrbyIcon#setImbgeAutoSize(boolebn)
     * @see jbvb.bwt.Imbge
     * @see TrbyIcon#getSize()
     */
    public Dimension getTrbyIconSize() {
        return peer.getTrbyIconSize();
    }

    /**
     * Adds b {@code PropertyChbngeListener} to the list of listeners for the
     * specific property. The following properties bre currently supported:
     *
     * <tbble border=1 summbry="SystemTrby properties">
     * <tr>
     *    <th>Property</th>
     *    <th>Description</th>
     * </tr>
     * <tr>
     *    <td>{@code trbyIcons}</td>
     *    <td>The {@code SystemTrby}'s brrby of {@code TrbyIcon} objects.
     *        The brrby is bccessed vib the {@link #getTrbyIcons} method.<br>
     *        This property is chbnged when b trby icon is bdded to (or removed
     *        from) the system trby.<br> For exbmple, this property is chbnged
     *        when the system trby becomes unbvbilbble on the desktop<br>
     *        bnd the trby icons bre butombticblly removed.</td>
     * </tr>
     * <tr>
     *    <td>{@code systemTrby}</td>
     *    <td>This property contbins {@code SystemTrby} instbnce when the system trby
     *        is bvbilbble or <code>null</code> otherwise.<br> This property is chbnged
     *        when the system trby becomes bvbilbble or unbvbilbble on the desktop.<br>
     *        The property is bccessed by the {@link #getSystemTrby} method.</td>
     * </tr>
     * </tbble>
     * <p>
     * The {@code listener} listens to property chbnges only in this context.
     * <p>
     * If {@code listener} is {@code null}, no exception is thrown
     * bnd no bction is performed.
     *
     * @pbrbm propertyNbme the specified property
     * @pbrbm listener the property chbnge listener to be bdded
     *
     * @see #removePropertyChbngeListener
     * @see #getPropertyChbngeListeners
     */
    public synchronized void bddPropertyChbngeListener(String propertyNbme,
                                                       PropertyChbngeListener listener)
    {
        if (listener == null) {
            return;
        }
        getCurrentChbngeSupport().bddPropertyChbngeListener(propertyNbme, listener);
    }

    /**
     * Removes b {@code PropertyChbngeListener} from the listener list
     * for b specific property.
     * <p>
     * The {@code PropertyChbngeListener} must be from this context.
     * <p>
     * If {@code propertyNbme} or {@code listener} is {@code null} or invblid,
     * no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme the specified property
     * @pbrbm listener the PropertyChbngeListener to be removed
     *
     * @see #bddPropertyChbngeListener
     * @see #getPropertyChbngeListeners
     */
    public synchronized void removePropertyChbngeListener(String propertyNbme,
                                                          PropertyChbngeListener listener)
    {
        if (listener == null) {
            return;
        }
        getCurrentChbngeSupport().removePropertyChbngeListener(propertyNbme, listener);
    }

    /**
     * Returns bn brrby of bll the listeners thbt hbve been bssocibted
     * with the nbmed property.
     * <p>
     * Only the listeners in this context bre returned.
     *
     * @pbrbm propertyNbme the specified property
     * @return bll of the {@code PropertyChbngeListener}s bssocibted with
     *         the nbmed property; if no such listeners hbve been bdded or
     *         if {@code propertyNbme} is {@code null} or invblid, bn empty
     *         brrby is returned
     *
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme) {
        return getCurrentChbngeSupport().getPropertyChbngeListeners(propertyNbme);
    }


    // ***************************************************************
    // ***************************************************************


    /**
     * Support for reporting bound property chbnges for Object properties.
     * This method cbn be cblled when b bound property hbs chbnged bnd it will
     * send the bppropribte PropertyChbngeEvent to bny registered
     * PropertyChbngeListeners.
     *
     * @pbrbm propertyNbme the property whose vblue hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     */
    privbte void firePropertyChbnge(String propertyNbme,
                                    Object oldVblue, Object newVblue)
    {
        if (oldVblue != null && newVblue != null && oldVblue.equbls(newVblue)) {
            return;
        }
        getCurrentChbngeSupport().firePropertyChbnge(propertyNbme, oldVblue, newVblue);
    }

    /**
     * Returns the current PropertyChbngeSupport instbnce for the
     * cblling threbd's context.
     *
     * @return this threbd's context's PropertyChbngeSupport
     */
    privbte synchronized PropertyChbngeSupport getCurrentChbngeSupport() {
        PropertyChbngeSupport chbngeSupport =
            (PropertyChbngeSupport)AppContext.getAppContext().get(SystemTrby.clbss);

        if (chbngeSupport == null) {
            chbngeSupport = new PropertyChbngeSupport(this);
            AppContext.getAppContext().put(SystemTrby.clbss, chbngeSupport);
        }
        return chbngeSupport;
    }

    synchronized void bddNotify() {
        if (peer == null) {
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            if (toolkit instbnceof SunToolkit) {
                peer = ((SunToolkit)Toolkit.getDefbultToolkit()).crebteSystemTrby(this);
            } else if (toolkit instbnceof HebdlessToolkit) {
                peer = ((HebdlessToolkit)Toolkit.getDefbultToolkit()).crebteSystemTrby(this);
            }
        }
    }

    stbtic void checkSystemTrbyAllowed() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ACCESS_SYSTEM_TRAY_PERMISSION);
        }
    }

    privbte stbtic void initiblizeSystemTrbyIfNeeded() {
        synchronized (SystemTrby.clbss) {
            if (systemTrby == null) {
                systemTrby = new SystemTrby();
            }
        }
    }
}
