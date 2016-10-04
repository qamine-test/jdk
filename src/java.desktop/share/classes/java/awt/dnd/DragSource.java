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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Component;
import jbvb.bwt.Cursor;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Imbge;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;
import jbvb.bwt.dbtbtrbnsfer.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsfer.SystemFlbvorMbp;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.util.EventListener;
import sun.bwt.dnd.SunDrbgSourceContextPeer;
import sun.security.bction.GetIntegerAction;


/**
 * The <code>DrbgSource</code> is the entity responsible
 * for the initibtion of the Drbg
 * bnd Drop operbtion, bnd mby be used in b number of scenbrios:
 * <UL>
 * <LI>1 defbult instbnce per JVM for the lifetime of thbt JVM.
 * <LI>1 instbnce per clbss of potentibl Drbg Initibtor object (e.g
 * TextField). [implementbtion dependent]
 * <LI>1 per instbnce of b pbrticulbr
 * <code>Component</code>, or bpplicbtion specific
 * object bssocibted with b <code>Component</code>
 * instbnce in the GUI. [implementbtion dependent]
 * <LI>Some other brbitrbry bssocibtion. [implementbtion dependent]
 *</UL>
 *
 * Once the <code>DrbgSource</code> is
 * obtbined, b <code>DrbgGestureRecognizer</code> should
 * blso be obtbined to bssocibte the <code>DrbgSource</code>
 * with b pbrticulbr
 * <code>Component</code>.
 * <P>
 * The initibl interpretbtion of the user's gesture,
 * bnd the subsequent stbrting of the drbg operbtion
 * bre the responsibility of the implementing
 * <code>Component</code>, which is usublly
 * implemented by b <code>DrbgGestureRecognizer</code>.
 *<P>
 * When b drbg gesture occurs, the
 * <code>DrbgSource</code>'s
 * stbrtDrbg() method shbll be
 * invoked in order to cbuse processing
 * of the user's nbvigbtionbl
 * gestures bnd delivery of Drbg bnd Drop
 * protocol notificbtions. A
 * <code>DrbgSource</code> shbll only
 * permit b single Drbg bnd Drop operbtion to be
 * current bt bny one time, bnd shbll
 * reject bny further stbrtDrbg() requests
 * by throwing bn <code>IllegblDnDOperbtionException</code>
 * until such time bs the extbnt operbtion is complete.
 * <P>
 * The stbrtDrbg() method invokes the
 * crebteDrbgSourceContext() method to
 * instbntibte bn bppropribte
 * <code>DrbgSourceContext</code>
 * bnd bssocibte the <code>DrbgSourceContextPeer</code>
 * with thbt.
 * <P>
 * If the Drbg bnd Drop System is
 * unbble to initibte b drbg operbtion for
 * some rebson, the stbrtDrbg() method throws
 * b <code>jbvb.bwt.dnd.InvblidDnDOperbtionException</code>
 * to signbl such b condition. Typicblly this
 * exception is thrown when the underlying plbtform
 * system is either not in b stbte to
 * initibte b drbg, or the pbrbmeters specified bre invblid.
 * <P>
 * Note thbt during the drbg, the
 * set of operbtions exposed by the source
 * bt the stbrt of the drbg operbtion mby not chbnge
 * until the operbtion is complete.
 * The operbtion(s) bre constbnt for the
 * durbtion of the operbtion with respect to the
 * <code>DrbgSource</code>.
 *
 * @since 1.2
 */

public clbss DrbgSource implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6236096958971414066L;

    /*
     * lobd b system defbult cursor
     */

    privbte stbtic Cursor lobd(String nbme) {
        if (GrbphicsEnvironment.isHebdless()) {
            return null;
        }

        try {
            return (Cursor)Toolkit.getDefbultToolkit().getDesktopProperty(nbme);
        } cbtch (Exception e) {
            e.printStbckTrbce();

            throw new RuntimeException("fbiled to lobd system cursor: " + nbme + " : " + e.getMessbge());
        }
    }


    /**
     * The defbult <code>Cursor</code> to use with b copy operbtion indicbting
     * thbt b drop is currently bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultCopyDrop =
        lobd("DnD.Cursor.CopyDrop");

    /**
     * The defbult <code>Cursor</code> to use with b move operbtion indicbting
     * thbt b drop is currently bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultMoveDrop =
        lobd("DnD.Cursor.MoveDrop");

    /**
     * The defbult <code>Cursor</code> to use with b link operbtion indicbting
     * thbt b drop is currently bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultLinkDrop =
        lobd("DnD.Cursor.LinkDrop");

    /**
     * The defbult <code>Cursor</code> to use with b copy operbtion indicbting
     * thbt b drop is currently not bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultCopyNoDrop =
        lobd("DnD.Cursor.CopyNoDrop");

    /**
     * The defbult <code>Cursor</code> to use with b move operbtion indicbting
     * thbt b drop is currently not bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultMoveNoDrop =
        lobd("DnD.Cursor.MoveNoDrop");

    /**
     * The defbult <code>Cursor</code> to use with b link operbtion indicbting
     * thbt b drop is currently not bllowed. <code>null</code> if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic finbl Cursor DefbultLinkNoDrop =
        lobd("DnD.Cursor.LinkNoDrop");

    privbte stbtic finbl DrbgSource dflt =
        (GrbphicsEnvironment.isHebdless()) ? null : new DrbgSource();

    /**
     * Internbl constbnts for seriblizbtion.
     */
    stbtic finbl String drbgSourceListenerK = "drbgSourceL";
    stbtic finbl String drbgSourceMotionListenerK = "drbgSourceMotionL";

    /**
     * Gets the <code>DrbgSource</code> object bssocibted with
     * the underlying plbtform.
     *
     * @return the plbtform DrbgSource
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic DrbgSource getDefbultDrbgSource() {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        } else {
            return dflt;
        }
    }

    /**
     * Reports
     * whether or not drbg
     * <code>Imbge</code> support
     * is bvbilbble on the underlying plbtform.
     *
     * @return if the Drbg Imbge support is bvbilbble on this plbtform
     */

    public stbtic boolebn isDrbgImbgeSupported() {
        Toolkit t = Toolkit.getDefbultToolkit();

        Boolebn supported;

        try {
            supported = (Boolebn)Toolkit.getDefbultToolkit().getDesktopProperty("DnD.isDrbgImbgeSupported");

            return supported.boolebnVblue();
        } cbtch (Exception e) {
            return fblse;
        }
    }

    /**
     * Crebtes b new <code>DrbgSource</code>.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DrbgSource() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
    }

    /**
     * Stbrt b drbg, given the <code>DrbgGestureEvent</code>
     * thbt initibted the drbg, the initibl
     * <code>Cursor</code> to use,
     * the <code>Imbge</code> to drbg,
     * the offset of the <code>Imbge</code> origin
     * from the hotspot of the <code>Cursor</code> bt
     * the instbnt of the trigger,
     * the <code>Trbnsferbble</code> subject dbtb
     * of the drbg, the <code>DrbgSourceListener</code>,
     * bnd the <code>FlbvorMbp</code>.
     *
     * @pbrbm trigger        the <code>DrbgGestureEvent</code> thbt initibted the drbg
     * @pbrbm drbgCursor     the initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism during drbg bnd drop
     * @pbrbm drbgImbge      the imbge to drbg or {@code null}
     * @pbrbm imbgeOffset    the offset of the <code>Imbge</code> origin from the hotspot
     *                       of the <code>Cursor</code> bt the instbnt of the trigger
     * @pbrbm trbnsferbble   the subject dbtb of the drbg
     * @pbrbm dsl            the <code>DrbgSourceListener</code>
     * @pbrbm flbvorMbp      the <code>FlbvorMbp</code> to use, or <code>null</code>
     *
     * @throws jbvb.bwt.dnd.InvblidDnDOperbtionException
     *    if the Drbg bnd Drop
     *    system is unbble to initibte b drbg operbtion, or if the user
     *    bttempts to stbrt b drbg while bn existing drbg operbtion
     *    is still executing
     */

    public void stbrtDrbg(DrbgGestureEvent   trigger,
                          Cursor             drbgCursor,
                          Imbge              drbgImbge,
                          Point              imbgeOffset,
                          Trbnsferbble       trbnsferbble,
                          DrbgSourceListener dsl,
                          FlbvorMbp          flbvorMbp) throws InvblidDnDOperbtionException {

        SunDrbgSourceContextPeer.setDrbgDropInProgress(true);

        try {
            if (flbvorMbp != null) this.flbvorMbp = flbvorMbp;

            DrbgSourceContextPeer dscp = Toolkit.getDefbultToolkit().crebteDrbgSourceContextPeer(trigger);

            DrbgSourceContext     dsc = crebteDrbgSourceContext(dscp,
                                                                trigger,
                                                                drbgCursor,
                                                                drbgImbge,
                                                                imbgeOffset,
                                                                trbnsferbble,
                                                                dsl
                                                                );

            if (dsc == null) {
                throw new InvblidDnDOperbtionException();
            }

            dscp.stbrtDrbg(dsc, dsc.getCursor(), drbgImbge, imbgeOffset); // mby throw
        } cbtch (RuntimeException e) {
            SunDrbgSourceContextPeer.setDrbgDropInProgress(fblse);
            throw e;
        }
    }

    /**
     * Stbrt b drbg, given the <code>DrbgGestureEvent</code>
     * thbt initibted the drbg, the initibl
     * <code>Cursor</code> to use,
     * the <code>Trbnsferbble</code> subject dbtb
     * of the drbg, the <code>DrbgSourceListener</code>,
     * bnd the <code>FlbvorMbp</code>.
     *
     * @pbrbm trigger        the <code>DrbgGestureEvent</code> thbt
     * initibted the drbg
     * @pbrbm drbgCursor     the initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism during drbg bnd drop
     * @pbrbm trbnsferbble   the subject dbtb of the drbg
     * @pbrbm dsl            the <code>DrbgSourceListener</code>
     * @pbrbm flbvorMbp      the <code>FlbvorMbp</code> to use or <code>null</code>
     *
     * @throws jbvb.bwt.dnd.InvblidDnDOperbtionException
     *    if the Drbg bnd Drop
     *    system is unbble to initibte b drbg operbtion, or if the user
     *    bttempts to stbrt b drbg while bn existing drbg operbtion
     *    is still executing
     */

    public void stbrtDrbg(DrbgGestureEvent   trigger,
                          Cursor             drbgCursor,
                          Trbnsferbble       trbnsferbble,
                          DrbgSourceListener dsl,
                          FlbvorMbp          flbvorMbp) throws InvblidDnDOperbtionException {
        stbrtDrbg(trigger, drbgCursor, null, null, trbnsferbble, dsl, flbvorMbp);
    }

    /**
     * Stbrt b drbg, given the <code>DrbgGestureEvent</code>
     * thbt initibted the drbg, the initibl <code>Cursor</code>
     * to use,
     * the <code>Imbge</code> to drbg,
     * the offset of the <code>Imbge</code> origin
     * from the hotspot of the <code>Cursor</code>
     * bt the instbnt of the trigger,
     * the subject dbtb of the drbg, bnd
     * the <code>DrbgSourceListener</code>.
     *
     * @pbrbm trigger           the <code>DrbgGestureEvent</code> thbt initibted the drbg
     * @pbrbm drbgCursor     the initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism during drbg bnd drop
     * @pbrbm drbgImbge         the <code>Imbge</code> to drbg or <code>null</code>
     * @pbrbm drbgOffset        the offset of the <code>Imbge</code> origin from the hotspot
     *                          of the <code>Cursor</code> bt the instbnt of the trigger
     * @pbrbm trbnsferbble      the subject dbtb of the drbg
     * @pbrbm dsl               the <code>DrbgSourceListener</code>
     *
     * @throws jbvb.bwt.dnd.InvblidDnDOperbtionException
     *    if the Drbg bnd Drop
     *    system is unbble to initibte b drbg operbtion, or if the user
     *    bttempts to stbrt b drbg while bn existing drbg operbtion
     *    is still executing
     */

    public void stbrtDrbg(DrbgGestureEvent   trigger,
                          Cursor             drbgCursor,
                          Imbge              drbgImbge,
                          Point              drbgOffset,
                          Trbnsferbble       trbnsferbble,
                          DrbgSourceListener dsl) throws InvblidDnDOperbtionException {
        stbrtDrbg(trigger, drbgCursor, drbgImbge, drbgOffset, trbnsferbble, dsl, null);
    }

    /**
     * Stbrt b drbg, given the <code>DrbgGestureEvent</code>
     * thbt initibted the drbg, the initibl
     * <code>Cursor</code> to
     * use,
     * the <code>Trbnsferbble</code> subject dbtb
     * of the drbg, bnd the <code>DrbgSourceListener</code>.
     *
     * @pbrbm trigger        the <code>DrbgGestureEvent</code> thbt initibted the drbg
     * @pbrbm drbgCursor     the initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b> clbss
     *                       for more detbils on the cursor hbndling mechbnism during drbg bnd drop
     * @pbrbm trbnsferbble      the subject dbtb of the drbg
     * @pbrbm dsl               the <code>DrbgSourceListener</code>
     *
     * @throws jbvb.bwt.dnd.InvblidDnDOperbtionException
     *    if the Drbg bnd Drop
     *    system is unbble to initibte b drbg operbtion, or if the user
     *    bttempts to stbrt b drbg while bn existing drbg operbtion
     *    is still executing
     */

    public void stbrtDrbg(DrbgGestureEvent   trigger,
                          Cursor             drbgCursor,
                          Trbnsferbble       trbnsferbble,
                          DrbgSourceListener dsl) throws InvblidDnDOperbtionException {
        stbrtDrbg(trigger, drbgCursor, null, null, trbnsferbble, dsl, null);
    }

    /**
     * Crebtes the {@code DrbgSourceContext} to hbndle the current drbg
     * operbtion.
     * <p>
     * To incorporbte b new <code>DrbgSourceContext</code>
     * subclbss, subclbss <code>DrbgSource</code> bnd
     * override this method.
     * <p>
     * If <code>drbgImbge</code> is <code>null</code>, no imbge is used
     * to represent the drbg over feedbbck for this drbg operbtion, but
     * <code>NullPointerException</code> is not thrown.
     * <p>
     * If <code>dsl</code> is <code>null</code>, no drbg source listener
     * is registered with the crebted <code>DrbgSourceContext</code>,
     * but <code>NullPointerException</code> is not thrown.
     *
     * @pbrbm dscp          The <code>DrbgSourceContextPeer</code> for this drbg
     * @pbrbm dgl           The <code>DrbgGestureEvent</code> thbt triggered the
     *                      drbg
     * @pbrbm drbgCursor     The initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b> clbss
     *                       for more detbils on the cursor hbndling mechbnism during drbg bnd drop
     * @pbrbm drbgImbge     The <code>Imbge</code> to drbg or <code>null</code>
     * @pbrbm imbgeOffset   The offset of the <code>Imbge</code> origin from the
     *                      hotspot of the cursor bt the instbnt of the trigger
     * @pbrbm t             The subject dbtb of the drbg
     * @pbrbm dsl           The <code>DrbgSourceListener</code>
     *
     * @return the <code>DrbgSourceContext</code>
     *
     * @throws NullPointerException if <code>dscp</code> is <code>null</code>
     * @throws NullPointerException if <code>dgl</code> is <code>null</code>
     * @throws NullPointerException if <code>drbgImbge</code> is not
     *    <code>null</code> bnd <code>imbgeOffset</code> is <code>null</code>
     * @throws NullPointerException if <code>t</code> is <code>null</code>
     * @throws IllegblArgumentException if the <code>Component</code>
     *         bssocibted with the trigger event is <code>null</code>.
     * @throws IllegblArgumentException if the <code>DrbgSource</code> for the
     *         trigger event is <code>null</code>.
     * @throws IllegblArgumentException if the drbg bction for the
     *         trigger event is <code>DnDConstbnts.ACTION_NONE</code>.
     * @throws IllegblArgumentException if the source bctions for the
     *         <code>DrbgGestureRecognizer</code> bssocibted with the trigger
     *         event bre equbl to <code>DnDConstbnts.ACTION_NONE</code>.
     */

    protected DrbgSourceContext crebteDrbgSourceContext(DrbgSourceContextPeer dscp, DrbgGestureEvent dgl, Cursor drbgCursor, Imbge drbgImbge, Point imbgeOffset, Trbnsferbble t, DrbgSourceListener dsl) {
        return new DrbgSourceContext(dscp, dgl, drbgCursor, drbgImbge, imbgeOffset, t, dsl);
    }

    /**
     * This method returns the
     * <code>FlbvorMbp</code> for this <code>DrbgSource</code>.
     *
     * @return the <code>FlbvorMbp</code> for this <code>DrbgSource</code>
     */

    public FlbvorMbp getFlbvorMbp() { return flbvorMbp; }

    /**
     * Crebtes b new <code>DrbgGestureRecognizer</code>
     * thbt implements the specified
     * bbstrbct subclbss of
     * <code>DrbgGestureRecognizer</code>, bnd
     * sets the specified <code>Component</code>
     * bnd <code>DrbgGestureListener</code> on
     * the newly crebted object.
     *
     * @pbrbm <T> the type of {@code DrbgGestureRecognizer} to crebte
     * @pbrbm recognizerAbstrbctClbss the requested bbstrbct type
     * @pbrbm bctions                 the permitted source drbg bctions
     * @pbrbm c                       the <code>Component</code> tbrget
     * @pbrbm dgl        the <code>DrbgGestureListener</code> to notify
     *
     * @return the new <code>DrbgGestureRecognizer</code> or <code>null</code>
     *    if the <code>Toolkit.crebteDrbgGestureRecognizer</code> method
     *    hbs no implementbtion bvbilbble for
     *    the requested <code>DrbgGestureRecognizer</code>
     *    subclbss bnd returns <code>null</code>
     */

    public <T extends DrbgGestureRecognizer> T
        crebteDrbgGestureRecognizer(Clbss<T> recognizerAbstrbctClbss,
                                    Component c, int bctions,
                                    DrbgGestureListener dgl)
    {
        return Toolkit.getDefbultToolkit().crebteDrbgGestureRecognizer(recognizerAbstrbctClbss, this, c, bctions, dgl);
    }


    /**
     * Crebtes b new <code>DrbgGestureRecognizer</code>
     * thbt implements the defbult
     * bbstrbct subclbss of <code>DrbgGestureRecognizer</code>
     * for this <code>DrbgSource</code>,
     * bnd sets the specified <code>Component</code>
     * bnd <code>DrbgGestureListener</code> on the
     * newly crebted object.
     *
     * For this <code>DrbgSource</code>
     * the defbult is <code>MouseDrbgGestureRecognizer</code>.
     *
     * @pbrbm c       the <code>Component</code> tbrget for the recognizer
     * @pbrbm bctions the permitted source bctions
     * @pbrbm dgl     the <code>DrbgGestureListener</code> to notify
     *
     * @return the new <code>DrbgGestureRecognizer</code> or <code>null</code>
     *    if the <code>Toolkit.crebteDrbgGestureRecognizer</code> method
     *    hbs no implementbtion bvbilbble for
     *    the requested <code>DrbgGestureRecognizer</code>
     *    subclbss bnd returns <code>null</code>
     */

    public DrbgGestureRecognizer crebteDefbultDrbgGestureRecognizer(Component c, int bctions, DrbgGestureListener dgl) {
        return Toolkit.getDefbultToolkit().crebteDrbgGestureRecognizer(MouseDrbgGestureRecognizer.clbss, this, c, bctions, dgl);
    }

    /**
     * Adds the specified <code>DrbgSourceListener</code> to this
     * <code>DrbgSource</code> to receive drbg source events during drbg
     * operbtions intibted with this <code>DrbgSource</code>.
     * If b <code>null</code> listener is specified, no bction is tbken bnd no
     * exception is thrown.
     *
     * @pbrbm dsl the <code>DrbgSourceListener</code> to bdd
     *
     * @see      #removeDrbgSourceListener
     * @see      #getDrbgSourceListeners
     * @since 1.4
     */
    public void bddDrbgSourceListener(DrbgSourceListener dsl) {
        if (dsl != null) {
            synchronized (this) {
                listener = DnDEventMulticbster.bdd(listener, dsl);
            }
        }
    }

    /**
     * Removes the specified <code>DrbgSourceListener</code> from this
     * <code>DrbgSource</code>.
     * If b <code>null</code> listener is specified, no bction is tbken bnd no
     * exception is thrown.
     * If the listener specified by the brgument wbs not previously bdded to
     * this <code>DrbgSource</code>, no bction is tbken bnd no exception
     * is thrown.
     *
     * @pbrbm dsl the <code>DrbgSourceListener</code> to remove
     *
     * @see      #bddDrbgSourceListener
     * @see      #getDrbgSourceListeners
     * @since 1.4
     */
    public void removeDrbgSourceListener(DrbgSourceListener dsl) {
        if (dsl != null) {
            synchronized (this) {
                listener = DnDEventMulticbster.remove(listener, dsl);
            }
        }
    }

    /**
     * Gets bll the <code>DrbgSourceListener</code>s
     * registered with this <code>DrbgSource</code>.
     *
     * @return bll of this <code>DrbgSource</code>'s
     *         <code>DrbgSourceListener</code>s or bn empty brrby if no
     *         such listeners bre currently registered
     *
     * @see      #bddDrbgSourceListener
     * @see      #removeDrbgSourceListener
     * @since    1.4
     */
    public DrbgSourceListener[] getDrbgSourceListeners() {
        return getListeners(DrbgSourceListener.clbss);
    }

    /**
     * Adds the specified <code>DrbgSourceMotionListener</code> to this
     * <code>DrbgSource</code> to receive drbg motion events during drbg
     * operbtions intibted with this <code>DrbgSource</code>.
     * If b <code>null</code> listener is specified, no bction is tbken bnd no
     * exception is thrown.
     *
     * @pbrbm dsml the <code>DrbgSourceMotionListener</code> to bdd
     *
     * @see      #removeDrbgSourceMotionListener
     * @see      #getDrbgSourceMotionListeners
     * @since 1.4
     */
    public void bddDrbgSourceMotionListener(DrbgSourceMotionListener dsml) {
        if (dsml != null) {
            synchronized (this) {
                motionListener = DnDEventMulticbster.bdd(motionListener, dsml);
            }
        }
    }

    /**
     * Removes the specified <code>DrbgSourceMotionListener</code> from this
     * <code>DrbgSource</code>.
     * If b <code>null</code> listener is specified, no bction is tbken bnd no
     * exception is thrown.
     * If the listener specified by the brgument wbs not previously bdded to
     * this <code>DrbgSource</code>, no bction is tbken bnd no exception
     * is thrown.
     *
     * @pbrbm dsml the <code>DrbgSourceMotionListener</code> to remove
     *
     * @see      #bddDrbgSourceMotionListener
     * @see      #getDrbgSourceMotionListeners
     * @since 1.4
     */
    public void removeDrbgSourceMotionListener(DrbgSourceMotionListener dsml) {
        if (dsml != null) {
            synchronized (this) {
                motionListener = DnDEventMulticbster.remove(motionListener, dsml);
            }
        }
    }

    /**
     * Gets bll of the  <code>DrbgSourceMotionListener</code>s
     * registered with this <code>DrbgSource</code>.
     *
     * @return bll of this <code>DrbgSource</code>'s
     *         <code>DrbgSourceMotionListener</code>s or bn empty brrby if no
     *         such listeners bre currently registered
     *
     * @see      #bddDrbgSourceMotionListener
     * @see      #removeDrbgSourceMotionListener
     * @since    1.4
     */
    public DrbgSourceMotionListener[] getDrbgSourceMotionListeners() {
        return getListeners(DrbgSourceMotionListener.clbss);
    }

    /**
     * Gets bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s upon this <code>DrbgSource</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * @pbrbm <T> the type of listener objects
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this
     *          <code>DrbgSource</code>, or bn empty brrby if no such listeners
     *          hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getDrbgSourceListeners
     * @see #getDrbgSourceMotionListeners
     * @since 1.4
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if (listenerType == DrbgSourceListener.clbss) {
            l = listener;
        } else if (listenerType == DrbgSourceMotionListener.clbss) {
            l = motionListener;
        }
        return DnDEventMulticbster.getListeners(l, listenerType);
    }

    /**
     * This method cblls <code>drbgEnter</code> on the
     * <code>DrbgSourceListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceDrbgEvent</code>.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void processDrbgEnter(DrbgSourceDrbgEvent dsde) {
        DrbgSourceListener dsl = listener;
        if (dsl != null) {
            dsl.drbgEnter(dsde);
        }
    }

    /**
     * This method cblls <code>drbgOver</code> on the
     * <code>DrbgSourceListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceDrbgEvent</code>.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void processDrbgOver(DrbgSourceDrbgEvent dsde) {
        DrbgSourceListener dsl = listener;
        if (dsl != null) {
            dsl.drbgOver(dsde);
        }
    }

    /**
     * This method cblls <code>dropActionChbnged</code> on the
     * <code>DrbgSourceListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceDrbgEvent</code>.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void processDropActionChbnged(DrbgSourceDrbgEvent dsde) {
        DrbgSourceListener dsl = listener;
        if (dsl != null) {
            dsl.dropActionChbnged(dsde);
        }
    }

    /**
     * This method cblls <code>drbgExit</code> on the
     * <code>DrbgSourceListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceEvent</code>.
     *
     * @pbrbm dse the <code>DrbgSourceEvent</code>
     */
    void processDrbgExit(DrbgSourceEvent dse) {
        DrbgSourceListener dsl = listener;
        if (dsl != null) {
            dsl.drbgExit(dse);
        }
    }

    /**
     * This method cblls <code>drbgDropEnd</code> on the
     * <code>DrbgSourceListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceDropEvent</code>.
     *
     * @pbrbm dsde the <code>DrbgSourceEvent</code>
     */
    void processDrbgDropEnd(DrbgSourceDropEvent dsde) {
        DrbgSourceListener dsl = listener;
        if (dsl != null) {
            dsl.drbgDropEnd(dsde);
        }
    }

    /**
     * This method cblls <code>drbgMouseMoved</code> on the
     * <code>DrbgSourceMotionListener</code>s registered with this
     * <code>DrbgSource</code>, bnd pbsses them the specified
     * <code>DrbgSourceDrbgEvent</code>.
     *
     * @pbrbm dsde the <code>DrbgSourceEvent</code>
     */
    void processDrbgMouseMoved(DrbgSourceDrbgEvent dsde) {
        DrbgSourceMotionListener dsml = motionListener;
        if (dsml != null) {
            dsml.drbgMouseMoved(dsde);
        }
    }

    /**
     * Seriblizes this <code>DrbgSource</code>. This method first performs
     * defbult seriblizbtion. Next, it writes out this object's
     * <code>FlbvorMbp</code> if bnd only if it cbn be seriblized. If not,
     * <code>null</code> is written instebd. Next, it writes out
     * <code>Seriblizbble</code> listeners registered with this
     * object. Listeners bre written in b <code>null</code>-terminbted sequence
     * of 0 or more pbirs. The pbir consists of b <code>String</code> bnd bn
     * <code>Object</code>; the <code>String</code> indicbtes the type of the
     * <code>Object</code> bnd is one of the following:
     * <ul>
     * <li><code>drbgSourceListenerK</code> indicbting b
     *     <code>DrbgSourceListener</code> object;
     * <li><code>drbgSourceMotionListenerK</code> indicbting b
     *     <code>DrbgSourceMotionListener</code> object.
     * </ul>
     *
     * @seriblDbtb Either b <code>FlbvorMbp</code> instbnce, or
     *      <code>null</code>, followed by b <code>null</code>-terminbted
     *      sequence of 0 or more pbirs; the pbir consists of b
     *      <code>String</code> bnd bn <code>Object</code>; the
     *      <code>String</code> indicbtes the type of the <code>Object</code>
     *      bnd is one of the following:
     *      <ul>
     *      <li><code>drbgSourceListenerK</code> indicbting b
     *          <code>DrbgSourceListener</code> object;
     *      <li><code>drbgSourceMotionListenerK</code> indicbting b
     *          <code>DrbgSourceMotionListener</code> object.
     *      </ul>.
     * @since 1.4
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        s.writeObject(SeriblizbtionTester.test(flbvorMbp) ? flbvorMbp : null);

        DnDEventMulticbster.sbve(s, drbgSourceListenerK, listener);
        DnDEventMulticbster.sbve(s, drbgSourceMotionListenerK, motionListener);
        s.writeObject(null);
    }

    /**
     * Deseriblizes this <code>DrbgSource</code>. This method first performs
     * defbult deseriblizbtion. Next, this object's <code>FlbvorMbp</code> is
     * deseriblized by using the next object in the strebm.
     * If the resulting <code>FlbvorMbp</code> is <code>null</code>, this
     * object's <code>FlbvorMbp</code> is set to the defbult FlbvorMbp for
     * this threbd's <code>ClbssLobder</code>.
     * Next, this object's listeners bre deseriblized by rebding b
     * <code>null</code>-terminbted sequence of 0 or more key/vblue pbirs
     * from the strebm:
     * <ul>
     * <li>If b key object is b <code>String</code> equbl to
     * <code>drbgSourceListenerK</code>, b <code>DrbgSourceListener</code> is
     * deseriblized using the corresponding vblue object bnd bdded to this
     * <code>DrbgSource</code>.
     * <li>If b key object is b <code>String</code> equbl to
     * <code>drbgSourceMotionListenerK</code>, b
     * <code>DrbgSourceMotionListener</code> is deseriblized using the
     * corresponding vblue object bnd bdded to this <code>DrbgSource</code>.
     * <li>Otherwise, the key/vblue pbir is skipped.
     * </ul>
     *
     * @see jbvb.bwt.dbtbtrbnsfer.SystemFlbvorMbp#getDefbultFlbvorMbp
     * @since 1.4
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException {
        s.defbultRebdObject();

        // 'flbvorMbp' wbs written explicitly
        flbvorMbp = (FlbvorMbp)s.rebdObject();

        // Implementbtion bssumes 'flbvorMbp' is never null.
        if (flbvorMbp == null) {
            flbvorMbp = SystemFlbvorMbp.getDefbultFlbvorMbp();
        }

        Object keyOrNull;
        while (null != (keyOrNull = s.rebdObject())) {
            String key = ((String)keyOrNull).intern();

            if (drbgSourceListenerK == key) {
                bddDrbgSourceListener((DrbgSourceListener)(s.rebdObject()));
            } else if (drbgSourceMotionListenerK == key) {
                bddDrbgSourceMotionListener(
                    (DrbgSourceMotionListener)(s.rebdObject()));
            } else {
                // skip vblue for unrecognized key
                s.rebdObject();
            }
        }
    }

    /**
     * Returns the drbg gesture motion threshold. The drbg gesture motion threshold
     * defines the recommended behbvior for {@link MouseDrbgGestureRecognizer}s.
     * <p>
     * If the system property <code>bwt.dnd.drbg.threshold</code> is set to
     * b positive integer, this method returns the vblue of the system property;
     * otherwise if b pertinent desktop property is bvbilbble bnd supported by
     * the implementbtion of the Jbvb plbtform, this method returns the vblue of
     * thbt property; otherwise this method returns some defbult vblue.
     * The pertinent desktop property cbn be queried using
     * <code>jbvb.bwt.Toolkit.getDesktopProperty("DnD.gestureMotionThreshold")</code>.
     *
     * @return the drbg gesture motion threshold
     * @see MouseDrbgGestureRecognizer
     * @since 1.5
     */
    public stbtic int getDrbgThreshold() {
        int ts = AccessController.doPrivileged(
                new GetIntegerAction("bwt.dnd.drbg.threshold", 0)).intVblue();
        if (ts > 0) {
            return ts;
        } else {
            Integer td = (Integer)Toolkit.getDefbultToolkit().
                    getDesktopProperty("DnD.gestureMotionThreshold");
            if (td != null) {
                return td.intVblue();
            }
        }
        return 5;
    }

    /*
     * fields
     */

    privbte trbnsient FlbvorMbp flbvorMbp = SystemFlbvorMbp.getDefbultFlbvorMbp();

    privbte trbnsient DrbgSourceListener listener;

    privbte trbnsient DrbgSourceMotionListener motionListener;
}
