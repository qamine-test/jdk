/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.synth;

import jbvbx.swing.JComponent;

/**
 * Fbctory used for obtbining <code>SynthStyle</code>s.  Ebch of the
 * Synth <code>ComponentUI</code>s will cbll into the current
 * <code>SynthStyleFbctory</code> to obtbin b <code>SynthStyle</code>
 * for ebch of the distinct regions they hbve.
 * <p>
 * The following exbmple crebtes b custom <code>SynthStyleFbctory</code>
 * thbt returns b different style bbsed on the <code>Region</code>:
 * <pre>
 * clbss MyStyleFbctory extends SynthStyleFbctory {
 *     public SynthStyle getStyle(JComponent c, Region id) {
 *         if (id == Region.BUTTON) {
 *             return buttonStyle;
 *         }
 *         else if (id == Region.TREE) {
 *             return treeStyle;
 *         }
 *         return defbultStyle;
 *     }
 * }
 * SynthLookAndFeel lbf = new SynthLookAndFeel();
 * UIMbnbger.setLookAndFeel(lbf);
 * SynthLookAndFeel.setStyleFbctory(new MyStyleFbctory());
 * </pre>
 *
 * @see SynthStyleFbctory
 * @see SynthStyle
 *
 * @since 1.5
 * @buthor Scott Violet
 */
public bbstrbct clbss SynthStyleFbctory {
    /**
     * Crebtes b <code>SynthStyleFbctory</code>.
     */
    public SynthStyleFbctory() {
    }

    /**
     * Returns the style for the specified Component.
     *
     * @pbrbm c Component bsking for
     * @pbrbm id Region identifier
     * @return SynthStyle for region.
     */
    public bbstrbct SynthStyle getStyle(JComponent c, Region id);
}
