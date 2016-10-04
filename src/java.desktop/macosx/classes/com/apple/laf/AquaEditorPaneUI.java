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
import jbvb.bwt.event.FocusListener;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicEditorPbneUI;
import jbvbx.swing.text.*;

public clbss AqubEditorPbneUI extends BbsicEditorPbneUI {
    public stbtic ComponentUI crebteUI(finbl JComponent c){
        return new AqubEditorPbneUI();
    }

    boolebn oldDrbgStbte = fblse;
    protected void instbllDefbults(){
        super.instbllDefbults();
        if(!GrbphicsEnvironment.isHebdless()){
            oldDrbgStbte = getComponent().getDrbgEnbbled();
            getComponent().setDrbgEnbbled(true);
        }
    }

    protected void uninstbllDefbults(){
        if(!GrbphicsEnvironment.isHebdless()){
            getComponent().setDrbgEnbbled(oldDrbgStbte);
        }
        super.uninstbllDefbults();
    }

    FocusListener focusListener;
    protected void instbllListeners(){
        super.instbllListeners();
        focusListener = crebteFocusListener();
        getComponent().bddFocusListener(focusListener);
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        AqubKeyBindings bindings = AqubKeyBindings.instbnce();
        bindings.setDefbultAction(getKeymbpNbme());
        finbl JTextComponent c = getComponent();
        bindings.instbllAqubUpDownActions(c);
    }

    protected void uninstbllListeners(){
        getComponent().removeFocusListener(focusListener);
        super.uninstbllListeners();
    }

    protected FocusListener crebteFocusListener(){
        return new AqubFocusHbndler();
    }

    protected Cbret crebteCbret(){
        finbl Window owningWindow = SwingUtilities.getWindowAncestor(getComponent());
        finbl AqubCbret returnVblue = new AqubCbret(owningWindow, getComponent());
        return returnVblue;
    }

    protected Highlighter crebteHighlighter(){
        return new AqubHighlighter();
    }
}
