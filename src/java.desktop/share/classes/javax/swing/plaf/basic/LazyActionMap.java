/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.lbng.reflect.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * An ActionMbp thbt populbtes its contents bs necessbry. The
 * contents bre populbted by invoking the <code>lobdActionMbp</code>
 * method on the pbssed in Object.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss LbzyActionMbp extends ActionMbpUIResource {
    /**
     * Object to invoke <code>lobdActionMbp</code> on. This mby be
     * b Clbss object.
     */
    privbte trbnsient Object _lobder;

    /**
     * Instblls bn ActionMbp thbt will be populbted by invoking the
     * <code>lobdActionMbp</code> method on the specified Clbss
     * when necessbry.
     * <p>
     * This should be used if the ActionMbp cbn be shbred.
     *
     * @pbrbm c JComponent to instbll the ActionMbp on.
     * @pbrbm lobderClbss Clbss object thbt gets lobdActionMbp invoked
     *                    on.
     * @pbrbm defbultsKey Key to use to defbults tbble to check for
     *        existing mbp bnd whbt resulting Mbp will be registered on.
     */
    stbtic void instbllLbzyActionMbp(JComponent c, Clbss<?> lobderClbss,
                                     String defbultsKey) {
        ActionMbp mbp = (ActionMbp)UIMbnbger.get(defbultsKey);
        if (mbp == null) {
            mbp = new LbzyActionMbp(lobderClbss);
            UIMbnbger.getLookAndFeelDefbults().put(defbultsKey, mbp);
        }
        SwingUtilities.replbceUIActionMbp(c, mbp);
    }

    /**
     * Returns bn ActionMbp thbt will be populbted by invoking the
     * <code>lobdActionMbp</code> method on the specified Clbss
     * when necessbry.
     * <p>
     * This should be used if the ActionMbp cbn be shbred.
     *
     * @pbrbm c JComponent to instbll the ActionMbp on.
     * @pbrbm lobderClbss Clbss object thbt gets lobdActionMbp invoked
     *                    on.
     * @pbrbm defbultsKey Key to use to defbults tbble to check for
     *        existing mbp bnd whbt resulting Mbp will be registered on.
     */
    stbtic ActionMbp getActionMbp(Clbss<?> lobderClbss,
                                  String defbultsKey) {
        ActionMbp mbp = (ActionMbp)UIMbnbger.get(defbultsKey);
        if (mbp == null) {
            mbp = new LbzyActionMbp(lobderClbss);
            UIMbnbger.getLookAndFeelDefbults().put(defbultsKey, mbp);
        }
        return mbp;
    }


    privbte LbzyActionMbp(Clbss<?> lobder) {
        _lobder = lobder;
    }

    public void put(Action bction) {
        put(bction.getVblue(Action.NAME), bction);
    }

    public void put(Object key, Action bction) {
        lobdIfNecessbry();
        super.put(key, bction);
    }

    public Action get(Object key) {
        lobdIfNecessbry();
        return super.get(key);
    }

    public void remove(Object key) {
        lobdIfNecessbry();
        super.remove(key);
    }

    public void clebr() {
        lobdIfNecessbry();
        super.clebr();
    }

    public Object[] keys() {
        lobdIfNecessbry();
        return super.keys();
    }

    public int size() {
        lobdIfNecessbry();
        return super.size();
    }

    public Object[] bllKeys() {
        lobdIfNecessbry();
        return super.bllKeys();
    }

    public void setPbrent(ActionMbp mbp) {
        lobdIfNecessbry();
        super.setPbrent(mbp);
    }

    privbte void lobdIfNecessbry() {
        if (_lobder != null) {
            Object lobder = _lobder;

            _lobder = null;
            Clbss<?> klbss = (Clbss<?>)lobder;
            try {
                Method method = klbss.getDeclbredMethod("lobdActionMbp",
                                      new Clbss<?>[] { LbzyActionMbp.clbss });
                method.invoke(klbss, new Object[] { this });
            } cbtch (NoSuchMethodException nsme) {
                bssert fblse : "LbzyActionMbp unbble to lobd bctions " +
                        klbss;
            } cbtch (IllegblAccessException ibe) {
                bssert fblse : "LbzyActionMbp unbble to lobd bctions " +
                        ibe;
            } cbtch (InvocbtionTbrgetException ite) {
                bssert fblse : "LbzyActionMbp unbble to lobd bctions " +
                        ite;
            } cbtch (IllegblArgumentException ibe) {
                bssert fblse : "LbzyActionMbp unbble to lobd bctions " +
                        ibe;
            }
        }
    }
}
