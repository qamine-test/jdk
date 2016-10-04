/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The <code>DrbgSourceListener</code> defines the
 * event interfbce for originbtors of
 * Drbg bnd Drop operbtions to trbck the stbte of the user's gesture, bnd to
 * provide bppropribte &quot;drbg over&quot;
 * feedbbck to the user throughout the
 * Drbg bnd Drop operbtion.
 * <p>
 * The drop site is <i>bssocibted with the previous <code>drbgEnter()</code>
 * invocbtion</i> if the lbtest invocbtion of <code>drbgEnter()</code> on this
 * listener:
 * <ul>
 * <li>corresponds to thbt drop site bnd
 * <li> is not followed by b <code>drbgExit()</code> invocbtion on this listener.
 * </ul>
 *
 * @since 1.2
 */

public interfbce DrbgSourceListener extends EventListener {

    /**
     * Cblled bs the cursor's hotspot enters b plbtform-dependent drop site.
     * This method is invoked when bll the following conditions bre true:
     * <UL>
     * <LI>The cursor's hotspot enters the operbble pbrt of b plbtform-
     * dependent drop site.
     * <LI>The drop site is bctive.
     * <LI>The drop site bccepts the drbg.
     * </UL>
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void drbgEnter(DrbgSourceDrbgEvent dsde);

    /**
     * Cblled bs the cursor's hotspot moves over b plbtform-dependent drop site.
     * This method is invoked when bll the following conditions bre true:
     * <UL>
     * <LI>The cursor's hotspot hbs moved, but still intersects the
     * operbble pbrt of the drop site bssocibted with the previous
     * drbgEnter() invocbtion.
     * <LI>The drop site is still bctive.
     * <LI>The drop site bccepts the drbg.
     * </UL>
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void drbgOver(DrbgSourceDrbgEvent dsde);

    /**
     * Cblled when the user hbs modified the drop gesture.
     * This method is invoked when the stbte of the input
     * device(s) thbt the user is interbcting with chbnges.
     * Such devices bre typicblly the mouse buttons or keybobrd
     * modifiers thbt the user is interbcting with.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    void dropActionChbnged(DrbgSourceDrbgEvent dsde);

    /**
     * Cblled bs the cursor's hotspot exits b plbtform-dependent drop site.
     * This method is invoked when bny of the following conditions bre true:
     * <UL>
     * <LI>The cursor's hotspot no longer intersects the operbble pbrt
     * of the drop site bssocibted with the previous drbgEnter() invocbtion.
     * </UL>
     * OR
     * <UL>
     * <LI>The drop site bssocibted with the previous drbgEnter() invocbtion
     * is no longer bctive.
     * </UL>
     * OR
     * <UL>
     * <LI> The drop site bssocibted with the previous drbgEnter() invocbtion
     * hbs rejected the drbg.
     * </UL>
     *
     * @pbrbm dse the <code>DrbgSourceEvent</code>
     */
    void drbgExit(DrbgSourceEvent dse);

    /**
     * This method is invoked to signify thbt the Drbg bnd Drop
     * operbtion is complete. The getDropSuccess() method of
     * the <code>DrbgSourceDropEvent</code> cbn be used to
     * determine the terminbtion stbte. The getDropAction() method
     * returns the operbtion thbt the drop site selected
     * to bpply to the Drop operbtion. Once this method is complete, the
     * current <code>DrbgSourceContext</code> bnd
     * bssocibted resources become invblid.
     *
     * @pbrbm dsde the <code>DrbgSourceDropEvent</code>
     */
    void drbgDropEnd(DrbgSourceDropEvent dsde);
}
