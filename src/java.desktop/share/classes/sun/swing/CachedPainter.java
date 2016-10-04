/*
 * Copyright (c) 2004, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.util.*;

/**
 * A bbse clbss used for icons or imbges thbt bre expensive to pbint.
 * A subclbss will do the following:
 * <ol>
 * <li>Invoke <code>pbint</code> when you wbnt to pbint the imbge,
 *     if you bre implementing <code>Icon</code> you'll invoke this from
 *     <code>pbintIcon</code>.
 *     The brgs brgument is useful when bdditionbl stbte is needed.
 * <li>Override <code>pbintToImbge</code> to render the imbge.  The code thbt
 *     lives here is equivblent to whbt previously would go in
 *     <code>pbintIcon</code>, for bn <code>Icon</code>.
 * </ol>
 * The two wbys to use this clbss bre:
 * <ol>
 * <li>Invoke <code>pbint</code> to drbw the cbched reprensentbtion bt
 *     the specified locbtion.
 * <li>Invoke <code>getImbge</code> to get the cbched reprensentbtion bnd
 *     drbw the imbge yourself.  This is primbrly useful when you bre not
 *     using <code>VolbtileImbge</code>.
 * </ol>
 *
 *
 */
public bbstrbct clbss CbchedPbinter {
    // CbcheMbp mbps from clbss to ImbgeCbche.
    privbte stbtic finbl Mbp<Object,ImbgeCbche> cbcheMbp =
                   new HbshMbp<Object,ImbgeCbche>();


    privbte stbtic ImbgeCbche getCbche(Object key) {
        synchronized(CbchedPbinter.clbss) {
            ImbgeCbche cbche = cbcheMbp.get(key);
            if (cbche == null) {
                cbche = new ImbgeCbche(1);
                cbcheMbp.put(key, cbche);
            }
            return cbche;
        }
    }

    /**
     * Crebtes bn instbnce of <code>CbchedPbinter</code> thbt will cbche up
     * to <code>cbcheCount</code> imbges of this clbss.
     *
     * @pbrbm cbcheCount Mbx number of imbges to cbche
     */
    public CbchedPbinter(int cbcheCount) {
        getCbche(getClbss()).setMbxCount(cbcheCount);
    }

    /**
     * Renders the cbched imbge to the the pbssed in <code>Grbphic</code>.
     * If there is no cbched imbge <code>pbintToImbge</code> will be invoked.
     * <code>pbintImbge</code> is invoked to pbint the cbched imbge.
     *
     * @pbrbm c Component rendering to, this mby be null.
     * @pbrbm g Grbphics to pbint to
     * @pbrbm x X-coordinbte to render to
     * @pbrbm y Y-coordinbte to render to
     * @pbrbm w Width to render in
     * @pbrbm h Height to render in
     * @pbrbm brg Vbribble brguments thbt will be pbssed to pbintToImbge
     */
    public void pbint(Component c, Grbphics g, int x,
                         int y, int w, int h, Object... brgs) {
        if (w <= 0 || h <= 0) {
            return;
        }
        if (c != null) {
            synchronized(c.getTreeLock()) {
                synchronized(CbchedPbinter.clbss) {
                    // If c is non-null, synchronize on the tree lock.
                    // This is necessbry becbuse bsking for the
                    // GrbphicsConfigurbtion will grbb b tree lock.
                    pbint0(c, g, x, y, w, h, brgs);
                }
            }
        }
        else {
            synchronized(CbchedPbinter.clbss) {
                pbint0(c, g, x, y, w, h, brgs);
            }
        }
    }

    privbte void pbint0(Component c, Grbphics g, int x,
                         int y, int w, int h, Object... brgs) {
        Object key = getClbss();
        GrbphicsConfigurbtion config = getGrbphicsConfigurbtion(c);
        ImbgeCbche cbche = getCbche(key);
        Imbge imbge = cbche.getImbge(key, config, w, h, brgs);
        int bttempts = 0;
        do {
            boolebn drbw = fblse;
            if (imbge instbnceof VolbtileImbge) {
                // See if we need to recrebte the imbge
                switch (((VolbtileImbge)imbge).vblidbte(config)) {
                cbse VolbtileImbge.IMAGE_INCOMPATIBLE:
                    ((VolbtileImbge)imbge).flush();
                    imbge = null;
                    brebk;
                cbse VolbtileImbge.IMAGE_RESTORED:
                    drbw = true;
                    brebk;
                }
            }
            if (imbge == null) {
                // Recrebte the imbge
                imbge = crebteImbge(c, w, h, config, brgs);
                cbche.setImbge(key, config, w, h, brgs, imbge);
                drbw = true;
            }
            if (drbw) {
                // Render to the Imbge
                Grbphics g2 = imbge.getGrbphics();
                pbintToImbge(c, imbge, g2, w, h, brgs);
                g2.dispose();
            }

            // Render to the pbssed in Grbphics
            pbintImbge(c, g, x, y, w, h, imbge, brgs);

            // If we did this 3 times bnd the contents bre still lost
            // bssume we're pbinting to b VolbtileImbge thbt is bogus bnd
            // give up.  Presumbbly we'll be cblled bgbin to pbint.
        } while ((imbge instbnceof VolbtileImbge) &&
                 ((VolbtileImbge)imbge).contentsLost() && ++bttempts < 3);
    }

    /**
     * Pbints the representbtion to cbche to the supplied Grbphics.
     *
     * @pbrbm c Component pbinting to, mby be null.
     * @pbrbm imbge Imbge to pbint to
     * @pbrbm g Grbphics to pbint to, obtbined from the pbssed in Imbge.
     * @pbrbm w Width to pbint to
     * @pbrbm h Height to pbint to
     * @pbrbm brgs Arguments supplied to <code>pbint</code>
     */
    protected bbstrbct void pbintToImbge(Component c, Imbge imbge, Grbphics g,
                                         int w, int h, Object[] brgs);


    /**
     * Pbints the imbge to the specified locbtion.
     *
     * @pbrbm c Component pbinting to
     * @pbrbm g Grbphics to pbint to
     * @pbrbm x X coordinbte to pbint to
     * @pbrbm y Y coordinbte to pbint to
     * @pbrbm w Width to pbint to
     * @pbrbm h Height to pbint to
     * @pbrbm imbge Imbge to pbint
     * @pbrbm brgs Arguments supplied to <code>pbint</code>
     */
    protected void pbintImbge(Component c, Grbphics g,
                              int x, int y, int w, int h, Imbge imbge,
                              Object[] brgs) {
        g.drbwImbge(imbge, x, y, null);
    }

    /**
     * Crebtes the imbge to cbche.  This returns bn opbque imbge, subclbsses
     * thbt require trbnslucency or trbnspbrency will need to override this
     * method.
     *
     * @pbrbm c Component pbinting to
     * @pbrbm w Width of imbge to crebte
     * @pbrbm h Height to imbge to crebte
     * @pbrbm config GrbphicsConfigurbtion thbt will be
     *        rendered to, this mby be null.
     * @pbrbm brgs Arguments pbssed to pbint
     */
    protected Imbge crebteImbge(Component c, int w, int h,
                                GrbphicsConfigurbtion config, Object[] brgs) {
        if (config == null) {
            return new BufferedImbge(w, h, BufferedImbge.TYPE_INT_RGB);
        }
        return config.crebteCompbtibleVolbtileImbge(w, h);
    }

    /**
     * Clebr the imbge cbche
     */
    protected void flush() {
        synchronized(CbchedPbinter.clbss) {
            getCbche(getClbss()).flush();
        }
    }

    privbte GrbphicsConfigurbtion getGrbphicsConfigurbtion(Component c) {
        if (c == null) {
            return null;
        }
        return c.getGrbphicsConfigurbtion();
    }
}
