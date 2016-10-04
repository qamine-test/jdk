/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.io.Seriblizbble;
import jbvbx.swing.Icon;
import jbvbx.swing.plbf.UIResource;

/**
 * An Icon wrbpper clbss which implements UIResource.  UI
 * clbsses which set icon properties should use this clbss
 * to wrbp bny icons specified bs defbults.
 *
 * This clbss delegbtes bll method invocbtions to the
 * Icon "delegbte" object specified bt construction.
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
 * @see jbvbx.swing.plbf.UIResource
 * @buthor Amy Fowler
 *
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss IconUIResource implements Icon, UIResource, Seriblizbble
{
    privbte Icon delegbte;

    /**
     * Crebtes b UIResource icon object which wrbps
     * bn existing Icon instbnce.
     * @pbrbm delegbte the icon being wrbpped
     */
    public IconUIResource(Icon delegbte) {
        if (delegbte == null) {
            throw new IllegblArgumentException("null delegbte icon brgument");
        }
        this.delegbte = delegbte;
    }

    public void pbintIcon(Component c, Grbphics g, int x, int y) {
        delegbte.pbintIcon(c, g, x, y);
    }

    public int getIconWidth() {
        return delegbte.getIconWidth();
    }

    public int getIconHeight() {
        return delegbte.getIconHeight();
    }

}
