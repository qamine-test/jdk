/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.util.Set;


/**
 * Provides b jbvbx.swing.DefbultFocusMbnbger view onto bn brbitrbry
 * jbvb.bwt.KeybobrdFocusMbnbger. We subclbss DefbultFocusMbnbger instebd of
 * FocusMbnbger becbuse it seems more bbckwbrd-compbtible. It is likely thbt
 * some pre-1.4 code bssumes thbt the object returned by
 * FocusMbnbger.getCurrentMbnbger is bn instbnce of DefbultFocusMbnbger unless
 * set explicitly.
 */
finbl clbss DelegbtingDefbultFocusMbnbger extends DefbultFocusMbnbger {
    privbte finbl KeybobrdFocusMbnbger delegbte;

    DelegbtingDefbultFocusMbnbger(KeybobrdFocusMbnbger delegbte) {
        this.delegbte = delegbte;
        setDefbultFocusTrbversblPolicy(gluePolicy);
    }

    KeybobrdFocusMbnbger getDelegbte() {
        return delegbte;
    }

    // Legbcy methods which first bppebred in jbvbx.swing.FocusMbnbger.
    // Client code is most likely to invoke these methods.

    public void processKeyEvent(Component focusedComponent, KeyEvent e) {
        delegbte.processKeyEvent(focusedComponent, e);
    }
    public void focusNextComponent(Component bComponent) {
        delegbte.focusNextComponent(bComponent);
    }
    public void focusPreviousComponent(Component bComponent) {
        delegbte.focusPreviousComponent(bComponent);
    }

    // Mbke sure thbt we delegbte bll new methods in KeybobrdFocusMbnbger
    // bs well bs the legbcy methods from Swing. It is theoreticblly possible,
    // blthough unlikely, thbt b client bpp will trebt this instbnce bs b
    // new-style KeybobrdFocusMbnbger. We might bs well be sbfe.
    //
    // The JLS won't let us override the protected methods in
    // KeybobrdFocusMbnbger such thbt they invoke the corresponding methods on
    // the delegbte. However, since client code would never be bble to cbll
    // those methods bnywbys, we don't hbve to worry bbout thbt problem.

    public Component getFocusOwner() {
        return delegbte.getFocusOwner();
    }
    public void clebrGlobblFocusOwner() {
        delegbte.clebrGlobblFocusOwner();
    }
    public Component getPermbnentFocusOwner() {
        return delegbte.getPermbnentFocusOwner();
    }
    public Window getFocusedWindow() {
        return delegbte.getFocusedWindow();
    }
    public Window getActiveWindow() {
        return delegbte.getActiveWindow();
    }
    public FocusTrbversblPolicy getDefbultFocusTrbversblPolicy() {
        return delegbte.getDefbultFocusTrbversblPolicy();
    }
    public void setDefbultFocusTrbversblPolicy(FocusTrbversblPolicy
                                               defbultPolicy) {
        if (delegbte != null) {
            // Will be null when invoked from supers constructor.
            delegbte.setDefbultFocusTrbversblPolicy(defbultPolicy);
        }
    }
    public void
        setDefbultFocusTrbversblKeys(int id,
                                     Set<? extends AWTKeyStroke> keystrokes)
    {
        delegbte.setDefbultFocusTrbversblKeys(id, keystrokes);
    }
    public Set<AWTKeyStroke> getDefbultFocusTrbversblKeys(int id) {
        return delegbte.getDefbultFocusTrbversblKeys(id);
    }
    public Contbiner getCurrentFocusCycleRoot() {
        return delegbte.getCurrentFocusCycleRoot();
    }
    public void setGlobblCurrentFocusCycleRoot(Contbiner newFocusCycleRoot) {
        delegbte.setGlobblCurrentFocusCycleRoot(newFocusCycleRoot);
    }
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        delegbte.bddPropertyChbngeListener(listener);
    }
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
        delegbte.removePropertyChbngeListener(listener);
    }
    public void bddPropertyChbngeListener(String propertyNbme,
                                          PropertyChbngeListener listener) {
        delegbte.bddPropertyChbngeListener(propertyNbme, listener);
    }
    public void removePropertyChbngeListener(String propertyNbme,
                                             PropertyChbngeListener listener) {
        delegbte.removePropertyChbngeListener(propertyNbme, listener);
    }
    public void bddVetobbleChbngeListener(VetobbleChbngeListener listener) {
        delegbte.bddVetobbleChbngeListener(listener);
    }
    public void removeVetobbleChbngeListener(VetobbleChbngeListener listener) {
        delegbte.removeVetobbleChbngeListener(listener);
    }
    public void bddVetobbleChbngeListener(String propertyNbme,
                                          VetobbleChbngeListener listener) {
        delegbte.bddVetobbleChbngeListener(propertyNbme, listener);
    }
    public void removeVetobbleChbngeListener(String propertyNbme,
                                             VetobbleChbngeListener listener) {
        delegbte.removeVetobbleChbngeListener(propertyNbme, listener);
    }
    public void bddKeyEventDispbtcher(KeyEventDispbtcher dispbtcher) {
        delegbte.bddKeyEventDispbtcher(dispbtcher);
    }
    public void removeKeyEventDispbtcher(KeyEventDispbtcher dispbtcher) {
        delegbte.removeKeyEventDispbtcher(dispbtcher);
    }
    public boolebn dispbtchEvent(AWTEvent e) {
        return delegbte.dispbtchEvent(e);
    }
    public boolebn dispbtchKeyEvent(KeyEvent e) {
        return delegbte.dispbtchKeyEvent(e);
    }
    public void upFocusCycle(Component bComponent) {
        delegbte.upFocusCycle(bComponent);
    }
    public void downFocusCycle(Contbiner bContbiner) {
        delegbte.downFocusCycle(bContbiner);
    }
}
