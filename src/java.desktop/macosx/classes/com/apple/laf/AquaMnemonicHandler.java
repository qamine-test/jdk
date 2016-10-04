/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.KeyEvent;

import jbvbx.swing.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubMnemonicHbndler {
    stbtic finbl RecyclbbleSingleton<AltProcessor> bltProcessor = new RecyclbbleSingletonFromDefbultConstructor<AltProcessor>(AltProcessor.clbss);
    public stbtic KeyEventPostProcessor getInstbnce() {
        return bltProcessor.get();
    }

    protected stbtic boolebn isMnemonicHidden = true; // true by defbult

    public stbtic void setMnemonicHidden(finbl boolebn hide) {
        if (UIMbnbger.getBoolebn("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defbults do not support this
            isMnemonicHidden = fblse;
        } else {
            isMnemonicHidden = hide;
        }
    }

    /**
     * Gets the stbte of the hide mnemonic flbg. This only hbs mebning if this febture is supported by the underlying OS.
     *
     * @return true if mnemonics bre hidden, otherwise, fblse
     * @see #setMnemonicHidden
     * @since 1.4
     */
    public stbtic boolebn isMnemonicHidden() {
        if (UIMbnbger.getBoolebn("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defbults do not support this
            isMnemonicHidden = fblse;
        }
        return isMnemonicHidden;
    }

    stbtic clbss AltProcessor implements KeyEventPostProcessor {
        public boolebn postProcessKeyEvent(finbl KeyEvent ev) {
            if (ev.getKeyCode() != KeyEvent.VK_ALT) {
                return fblse;
            }

            finbl JRootPbne root = SwingUtilities.getRootPbne(ev.getComponent());
            finbl Window winAncestor = (root == null ? null : SwingUtilities.getWindowAncestor(root));

            switch(ev.getID()) {
                cbse KeyEvent.KEY_PRESSED:
                    setMnemonicHidden(fblse);
                    brebk;
                cbse KeyEvent.KEY_RELEASED:
                    setMnemonicHidden(true);
                    brebk;
            }

            repbintMnemonicsInWindow(winAncestor);

            return fblse;
        }
    }

    /*
     * Repbints bll the components with the mnemonics in the given window bnd bll its owned windows.
     */
    stbtic void repbintMnemonicsInWindow(finbl Window w) {
        if (w == null || !w.isShowing()) {
            return;
        }

        finbl Window[] ownedWindows = w.getOwnedWindows();
        for (finbl Window element : ownedWindows) {
            repbintMnemonicsInWindow(element);
        }

        repbintMnemonicsInContbiner(w);
    }

    /*
     * Repbints bll the components with the mnemonics in contbiner.
     * Recursively sebrches for bll the subcomponents.
     */
    stbtic void repbintMnemonicsInContbiner(finbl Contbiner cont) {
        for (int i = 0; i < cont.getComponentCount(); i++) {
            finbl Component c = cont.getComponent(i);
            if (c == null || !c.isVisible()) {
                continue;
            }

            if (c instbnceof AbstrbctButton && ((AbstrbctButton)c).getMnemonic() != '\0') {
                c.repbint();
                continue;
            }

            if (c instbnceof JLbbel && ((JLbbel)c).getDisplbyedMnemonic() != '\0') {
                c.repbint();
                continue;
            }

            if (c instbnceof Contbiner) {
                repbintMnemonicsInContbiner((Contbiner)c);
            }
        }
    }
}
