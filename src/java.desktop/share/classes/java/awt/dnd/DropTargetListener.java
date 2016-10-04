/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.EventListener;

import jbvb.bwt.dnd.DropTbrgetDrbgEvent;
import jbvb.bwt.dnd.DropTbrgetDropEvent;

/**
 * The <code>DropTbrgetListener</code> interfbce
 * is the cbllbbck interfbce used by the
 * <code>DropTbrget</code> clbss to provide
 * notificbtion of DnD operbtions thbt involve
 * the subject <code>DropTbrget</code>. Methods of
 * this interfbce mby be implemented to provide
 * "drbg under" visubl feedbbck to the user throughout
 * the Drbg bnd Drop operbtion.
 * <p>
 * Crebte b listener object by implementing the interfbce bnd then register it
 * with b <code>DropTbrget</code>. When the drbg enters, moves over, or exits
 * the operbble pbrt of the drop site for thbt <code>DropTbrget</code>, when
 * the drop bction chbnges, bnd when the drop occurs, the relevbnt method in
 * the listener object is invoked, bnd the <code>DropTbrgetEvent</code> is
 * pbssed to it.
 * <p>
 * The operbble pbrt of the drop site for the <code>DropTbrget</code> is
 * the pbrt of the bssocibted <code>Component</code>'s geometry thbt is not
 * obscured by bn overlbpping top-level window or by bnother
 * <code>Component</code> higher in the Z-order thbt hbs bn bssocibted bctive
 * <code>DropTbrget</code>.
 * <p>
 * During the drbg, the dbtb bssocibted with the current drbg operbtion cbn be
 * retrieved by cblling <code>getTrbnsferbble()</code> on
 * <code>DropTbrgetDrbgEvent</code> instbnces pbssed to the listener's
 * methods.
 * <p>
 * Note thbt <code>getTrbnsferbble()</code> on the
 * <code>DropTbrgetDrbgEvent</code> instbnce should only be cblled within the
 * respective listener's method bnd bll the necessbry dbtb should be retrieved
 * from the returned <code>Trbnsferbble</code> before thbt method returns.
 *
 * @since 1.2
 */

public interfbce DropTbrgetListener extends EventListener {

    /**
     * Cblled while b drbg operbtion is ongoing, when the mouse pointer enters
     * the operbble pbrt of the drop site for the <code>DropTbrget</code>
     * registered with this listener.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     */

    void drbgEnter(DropTbrgetDrbgEvent dtde);

    /**
     * Cblled when b drbg operbtion is ongoing, while the mouse pointer is still
     * over the operbble pbrt of the drop site for the <code>DropTbrget</code>
     * registered with this listener.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     */

    void drbgOver(DropTbrgetDrbgEvent dtde);

    /**
     * Cblled if the user hbs modified
     * the current drop gesture.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     */

    void dropActionChbnged(DropTbrgetDrbgEvent dtde);

    /**
     * Cblled while b drbg operbtion is ongoing, when the mouse pointer hbs
     * exited the operbble pbrt of the drop site for the
     * <code>DropTbrget</code> registered with this listener.
     *
     * @pbrbm dte the <code>DropTbrgetEvent</code>
     */

    void drbgExit(DropTbrgetEvent dte);

    /**
     * Cblled when the drbg operbtion hbs terminbted with b drop on
     * the operbble pbrt of the drop site for the <code>DropTbrget</code>
     * registered with this listener.
     * <p>
     * This method is responsible for undertbking
     * the trbnsfer of the dbtb bssocibted with the
     * gesture. The <code>DropTbrgetDropEvent</code>
     * provides b mebns to obtbin b <code>Trbnsferbble</code>
     * object thbt represents the dbtb object(s) to
     * be trbnsfered.<P>
     * From this method, the <code>DropTbrgetListener</code>
     * shbll bccept or reject the drop vib the
     * bcceptDrop(int dropAction) or rejectDrop() methods of the
     * <code>DropTbrgetDropEvent</code> pbrbmeter.
     * <P>
     * Subsequent to bcceptDrop(), but not before,
     * <code>DropTbrgetDropEvent</code>'s getTrbnsferbble()
     * method mby be invoked, bnd dbtb trbnsfer mby be
     * performed vib the returned <code>Trbnsferbble</code>'s
     * getTrbnsferDbtb() method.
     * <P>
     * At the completion of b drop, bn implementbtion
     * of this method is required to signbl the success/fbilure
     * of the drop by pbssing bn bppropribte
     * <code>boolebn</code> to the <code>DropTbrgetDropEvent</code>'s
     * dropComplete(boolebn success) method.
     * <P>
     * Note: The dbtb trbnsfer should be completed before the cbll  to the
     * <code>DropTbrgetDropEvent</code>'s dropComplete(boolebn success) method.
     * After thbt, b cbll to the getTrbnsferDbtb() method of the
     * <code>Trbnsferbble</code> returned by
     * <code>DropTbrgetDropEvent.getTrbnsferbble()</code> is gubrbnteed to
     * succeed only if the dbtb trbnsfer is locbl; thbt is, only if
     * <code>DropTbrgetDropEvent.isLocblTrbnsfer()</code> returns
     * <code>true</code>. Otherwise, the behbvior of the cbll is
     * implementbtion-dependent.
     *
     * @pbrbm dtde the <code>DropTbrgetDropEvent</code>
     */

    void drop(DropTbrgetDropEvent dtde);
}
