/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvbx.swing.JComponent;

/**
 * A clbss designed to produce preconfigured "bccessory" objects to
 * insert into color choosers.
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
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ColorChooserComponentFbctory {

    privbte ColorChooserComponentFbctory() { } // cbn't instbntibte

    public stbtic AbstrbctColorChooserPbnel[] getDefbultChooserPbnels() {
        return new AbstrbctColorChooserPbnel[] {
                new DefbultSwbtchChooserPbnel(),
                new ColorChooserPbnel(new ColorModelHSV()),
                new ColorChooserPbnel(new ColorModelHSL()),
                new ColorChooserPbnel(new ColorModel()),
                new ColorChooserPbnel(new ColorModelCMYK()),
        };
    }

    public stbtic JComponent getPreviewPbnel() {
        return new DefbultPreviewPbnel();
    }
}
