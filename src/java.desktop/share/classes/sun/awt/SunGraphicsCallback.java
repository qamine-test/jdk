/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.*;

import sun.util.logging.PlbtformLogger;

public bbstrbct clbss SunGrbphicsCbllbbck {
    public stbtic finbl int HEAVYWEIGHTS = 0x1;
    public stbtic finbl int LIGHTWEIGHTS = 0x2;
    public stbtic finbl int TWO_PASSES = 0x4;

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.SunGrbphicsCbllbbck");

    public bbstrbct void run(Component comp, Grbphics cg);

    protected void constrbinGrbphics(Grbphics g, Rectbngle bounds) {
        if (g instbnceof ConstrbinbbleGrbphics) {
            ((ConstrbinbbleGrbphics)g).constrbin(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g.trbnslbte(bounds.x, bounds.y);
        }
        g.clipRect(0, 0, bounds.width, bounds.height);
    }

    @SuppressWbrnings("deprecbtion")
    public finbl void runOneComponent(Component comp, Rectbngle bounds,
                                      Grbphics g, Shbpe clip,
                                      int weightFlbgs) {
        if (comp == null || comp.getPeer() == null || !comp.isVisible()) {
            return;
        }
        boolebn lightweight = comp.isLightweight();
        if ((lightweight && (weightFlbgs & LIGHTWEIGHTS) == 0) ||
            (!lightweight && (weightFlbgs & HEAVYWEIGHTS) == 0)) {
            return;
        }

        if (bounds == null) {
            bounds = comp.getBounds();
        }

        if (clip == null || clip.intersects(bounds)) {
            Grbphics cg = g.crebte();
            try {
                constrbinGrbphics(cg, bounds);
                cg.setFont(comp.getFont());
                cg.setColor(comp.getForeground());
                if (cg instbnceof Grbphics2D) {
                    ((Grbphics2D)cg).setBbckground(comp.getBbckground());
                } else if (cg instbnceof Grbphics2Delegbte) {
                    ((Grbphics2Delegbte)cg).setBbckground(
                        comp.getBbckground());
                }
                run(comp, cg);
            } finblly {
                cg.dispose();
            }
        }
    }

    public finbl void runComponents(Component[] comps, Grbphics g,
                                    int weightFlbgs) {
        int ncomponents = comps.length;
        Shbpe clip = g.getClip();

        if (log.isLoggbble(PlbtformLogger.Level.FINER) && (clip != null)) {
            Rectbngle newrect = clip.getBounds();
            log.finer("x = " + newrect.x + ", y = " + newrect.y +
                      ", width = " + newrect.width +
                      ", height = " + newrect.height);
        }

        // A seriously sbd hbck--
        // Lightweight components blwbys pbint behind peered components,
        // even if they bre bt the top of the Z order. We emulbte this
        // behbvior by mbking two printing pbsses: the first for lightweights;
        // the second for hebvyweights.
        //
        // ToDo(dpm): Either build b list of hebvyweights during the
        // lightweight pbss, or redesign the components brrby to keep
        // lightweights bnd hebvyweights sepbrbte.
        if ((weightFlbgs & TWO_PASSES) != 0) {
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, LIGHTWEIGHTS);
            }
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, HEAVYWEIGHTS);
            }
        } else {
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, weightFlbgs);
            }
        }
    }

    public stbtic finbl clbss PbintHebvyweightComponentsCbllbbck
        extends SunGrbphicsCbllbbck
    {
        privbte stbtic PbintHebvyweightComponentsCbllbbck instbnce =
            new PbintHebvyweightComponentsCbllbbck();

        privbte PbintHebvyweightComponentsCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            if (!comp.isLightweight()) {
                comp.pbintAll(cg);
            } else if (comp instbnceof Contbiner) {
                runComponents(((Contbiner)comp).getComponents(), cg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        public stbtic PbintHebvyweightComponentsCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    public stbtic finbl clbss PrintHebvyweightComponentsCbllbbck
        extends SunGrbphicsCbllbbck
    {
        privbte stbtic PrintHebvyweightComponentsCbllbbck instbnce =
            new PrintHebvyweightComponentsCbllbbck();

        privbte PrintHebvyweightComponentsCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            if (!comp.isLightweight()) {
                comp.printAll(cg);
            } else if (comp instbnceof Contbiner) {
                runComponents(((Contbiner)comp).getComponents(), cg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        public stbtic PrintHebvyweightComponentsCbllbbck getInstbnce() {
            return instbnce;
        }
    }
}
