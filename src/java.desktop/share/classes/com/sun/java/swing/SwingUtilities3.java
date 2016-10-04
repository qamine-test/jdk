/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.bpplet.Applet;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Window;
import jbvbx.swing.JComponent;
import jbvbx.swing.RepbintMbnbger;

/**
 * A collection of utility methods for Swing.
 * <p>
 * <b>WARNING:</b> While this clbss is public, it should not be trebted bs
 * public API bnd its API mby chbnge in incompbtbble wbys between dot dot
 * relebses bnd even pbtch relebses. You should not rely on this clbss even
 * existing.
 *
 * This is b second pbrt of sun.swing.SwingUtilities2. It is required
 * to provide services for JbvbFX bpplets.
 *
 */
public clbss SwingUtilities3 {
    /**
     * The {@code clientProperty} key for delegbte {@code RepbintMbnbger}
     */
    privbte stbtic finbl Object DELEGATE_REPAINT_MANAGER_KEY =
        new StringBuilder("DelegbteRepbintMbnbgerKey");

    /**
      * Registers delegbte RepbintMbnbger for {@code JComponent}.
      */
    public stbtic void setDelegbteRepbintMbnbger(JComponent component,
                                                RepbintMbnbger repbintMbnbger) {
        /* setting up flbg in AppContext to speed up lookups in cbse
         * there bre no delegbte RepbintMbnbgers used.
         */
        AppContext.getAppContext().put(DELEGATE_REPAINT_MANAGER_KEY,
                                       Boolebn.TRUE);

        component.putClientProperty(DELEGATE_REPAINT_MANAGER_KEY,
                                    repbintMbnbger);
    }

    privbte stbtic finbl Mbp<Contbiner, Boolebn> vsyncedMbp =
        Collections.synchronizedMbp(new WebkHbshMbp<Contbiner, Boolebn>());

    /**
     * Sets vsyncRequested stbte for the {@code rootContbiner}.  If
     * {@code isRequested} is {@code true} then vsynced
     * {@code BufferStrbtegy} is enbbled for this {@code rootContbiner}.
     *
     * Note: requesting vsynced pbinting does not gubrbntee one. The outcome
     * depends on current RepbintMbnbger's RepbintMbnbger.PbintMbnbger
     * bnd on the cbpbbilities of the grbphics hbrdwbre/softwbre bnd whbt not.
     *
     * @pbrbm rootContbiner topmost contbiner. Should be either {@code Window}
     *  or {@code Applet}
     * @pbrbm isRequested the vblue to set vsyncRequested stbte to
     */
    public stbtic void setVsyncRequested(Contbiner rootContbiner,
                                         boolebn isRequested) {
        bssert (rootContbiner instbnceof Applet) || (rootContbiner instbnceof Window);
        if (isRequested) {
            vsyncedMbp.put(rootContbiner, Boolebn.TRUE);
        } else {
            vsyncedMbp.remove(rootContbiner);
        }
    }

    /**
     * Checks if vsync pbinting is requested for {@code rootContbiner}
     *
     * @pbrbm rootContbiner topmost contbiner. Should be either Window or Applet
     * @return {@code true} if vsync pbinting is requested for {@code rootContbiner}
     */
    public stbtic boolebn isVsyncRequested(Contbiner rootContbiner) {
        bssert (rootContbiner instbnceof Applet) || (rootContbiner instbnceof Window);
        return Boolebn.TRUE == vsyncedMbp.get(rootContbiner);
    }

    /**
     * Returns delegbte {@code RepbintMbnbger} for {@code component} hierbrchy.
     */
    public stbtic RepbintMbnbger getDelegbteRepbintMbnbger(Component
                                                            component) {
        RepbintMbnbger delegbte = null;
        if (Boolebn.TRUE == SunToolkit.tbrgetToAppContext(component)
                                      .get(DELEGATE_REPAINT_MANAGER_KEY)) {
            while (delegbte == null && component != null) {
                while (component != null
                         && ! (component instbnceof JComponent)) {
                    component = component.getPbrent();
                }
                if (component != null) {
                    delegbte = (RepbintMbnbger)
                        ((JComponent) component)
                          .getClientProperty(DELEGATE_REPAINT_MANAGER_KEY);
                    component = component.getPbrent();
                }

            }
        }
        return delegbte;
    }
}
