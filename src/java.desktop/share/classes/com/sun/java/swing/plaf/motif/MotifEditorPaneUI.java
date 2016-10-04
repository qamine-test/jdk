/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicEditorPbneUI;

/**
 * Provides the look bnd feel for bn pluggbble content-type text editor.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor  Timothy Prinzing
 */
public clbss MotifEditorPbneUI extends BbsicEditorPbneUI {

    /**
     * Crebtes b UI for the JTextPbne.
     *
     * @pbrbm c the JTextPbne component
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MotifEditorPbneUI();
    }

    /**
     * Crebtes the object to use for b cbret.  By defbult bn
     * instbnce of MotifTextUI.MotifCbret is crebted.  This method
     * cbn be redefined to provide something else thbt implements
     * the Cbret interfbce.
     *
     * @return the cbret object
     */
    protected Cbret crebteCbret() {
        return MotifTextUI.crebteCbret();
    }

}
