/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvb.bwt.AWTEvent;
import jbvb.bwt.AWTKeyStroke;
import jbvb.bwt.Component;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Frbme;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.ComponentListener;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.bwt.im.spi.InputMethod;
import jbvb.lbng.Chbrbcter.Subset;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.text.MessbgeFormbt;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.prefs.BbckingStoreException;
import jbvb.util.prefs.Preferences;
import sun.util.logging.PlbtformLogger;
import sun.bwt.SunToolkit;

/**
 * This InputContext clbss contbins pbrts of the implementbtion of
 * jbvb.text.im.InputContext. These pbrts hbve been moved
 * here to bvoid exposing protected members thbt bre needed by the
 * subclbss InputMethodContext.
 *
 * @see jbvb.bwt.im.InputContext
 * @buthor JbvbSoft Asib/Pbcific
 */

public clbss InputContext extends jbvb.bwt.im.InputContext
                          implements ComponentListener, WindowListener {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.im.InputContext");
    // The current input method is represented by two objects:
    // b locbtor is used to keep informbtion bbout the selected
    // input method bnd locble until we bctublly need b rebl input
    // method; only then the input method itself is crebted.
    // Once there is bn input method, the input method's locble
    // tbkes precedence over locble informbtion in the locbtor.
    privbte InputMethodLocbtor inputMethodLocbtor;
    privbte InputMethod inputMethod;
    privbte boolebn inputMethodCrebtionFbiled;

    // holding bin for previously used input method instbnces, but not the current one
    privbte HbshMbp<InputMethodLocbtor, InputMethod> usedInputMethods;

    // the current client component is kept until the user focusses on b different
    // client component served by the sbme input context. When thbt hbppens, we cbll
    // endComposition so thbt text doesn't jump from one component to bnother.
    privbte Component currentClientComponent;
    privbte Component bwtFocussedComponent;
    privbte boolebn   isInputMethodActive;
    privbte Subset[]  chbrbcterSubsets = null;

    // true if composition breb hbs been set to invisible when focus wbs lost
    privbte boolebn compositionArebHidden = fblse;

    // The input context for whose input method we mby hbve to cbll hideWindows
    privbte stbtic InputContext inputMethodWindowContext;

    // Previously bctive input method to decide whether we need to cbll
    // InputMethodAdbpter.stopListening() on bctivbteInputMethod()
    privbte stbtic InputMethod previousInputMethod = null;

    // true if the current input method requires client window chbnge notificbtion
    privbte boolebn clientWindowNotificbtionEnbbled = fblse;
    // client window to which this input context is listening
    privbte Window clientWindowListened;
    // cbche locbtion notificbtion
    privbte Rectbngle clientWindowLocbtion = null;
    // holding the stbte of clientWindowNotificbtionEnbbled of only non-current input methods
    privbte HbshMbp<InputMethod, Boolebn> perInputMethodStbte;

    // Input Method selection hot key stuff
    privbte stbtic AWTKeyStroke inputMethodSelectionKey;
    privbte stbtic boolebn inputMethodSelectionKeyInitiblized = fblse;
    privbte stbtic finbl String inputMethodSelectionKeyPbth = "/jbvb/bwt/im/selectionKey";
    privbte stbtic finbl String inputMethodSelectionKeyCodeNbme = "keyCode";
    privbte stbtic finbl String inputMethodSelectionKeyModifiersNbme = "modifiers";

    /**
     * Constructs bn InputContext.
     */
    protected InputContext() {
        InputMethodMbnbger imm = InputMethodMbnbger.getInstbnce();
        synchronized (InputContext.clbss) {
            if (!inputMethodSelectionKeyInitiblized) {
                inputMethodSelectionKeyInitiblized = true;
                if (imm.hbsMultipleInputMethods()) {
                    initiblizeInputMethodSelectionKey();
                }
            }
        }
        selectInputMethod(imm.getDefbultKeybobrdLocble());
    }

    /**
     * @see jbvb.bwt.im.InputContext#selectInputMethod
     * @exception NullPointerException when the locble is null.
     */
    public synchronized boolebn selectInputMethod(Locble locble) {
        if (locble == null) {
            throw new NullPointerException();
        }

        // see whether the current input method supports the locble
        if (inputMethod != null) {
            if (inputMethod.setLocble(locble)) {
                return true;
            }
        } else if (inputMethodLocbtor != null) {
            // This is not 100% correct, since the input method
            // mby support the locble without bdvertising it.
            // But before we try instbntibtions bnd setLocble,
            // we look for bn input method thbt's more confident.
            if (inputMethodLocbtor.isLocbleAvbilbble(locble)) {
                inputMethodLocbtor = inputMethodLocbtor.deriveLocbtor(locble);
                return true;
            }
        }

        // see whether there's some other input method thbt supports the locble
        InputMethodLocbtor newLocbtor = InputMethodMbnbger.getInstbnce().findInputMethod(locble);
        if (newLocbtor != null) {
            chbngeInputMethod(newLocbtor);
            return true;
        }

        // mbke one lbst desperbte effort with the current input method
        // ??? is this good? This is pretty high cost for something thbt's likely to fbil.
        if (inputMethod == null && inputMethodLocbtor != null) {
            inputMethod = getInputMethod();
            if (inputMethod != null) {
                return inputMethod.setLocble(locble);
            }
        }
        return fblse;
    }

    /**
     * @see jbvb.bwt.im.InputContext#getLocble
     */
    public Locble getLocble() {
        if (inputMethod != null) {
            return inputMethod.getLocble();
        } else if (inputMethodLocbtor != null) {
            return inputMethodLocbtor.getLocble();
        } else {
            return null;
        }
    }

    /**
     * @see jbvb.bwt.im.InputContext#setChbrbcterSubsets
     */
    public void setChbrbcterSubsets(Subset[] subsets) {
        if (subsets == null) {
            chbrbcterSubsets = null;
        } else {
            chbrbcterSubsets = new Subset[subsets.length];
            System.brrbycopy(subsets, 0,
                             chbrbcterSubsets, 0, chbrbcterSubsets.length);
        }
        if (inputMethod != null) {
            inputMethod.setChbrbcterSubsets(subsets);
        }
    }

    /**
     * @see jbvb.bwt.im.InputContext#reconvert
     * @since 1.3
     * @exception UnsupportedOperbtionException when input method is null
     */
    public synchronized void reconvert() {
        InputMethod inputMethod = getInputMethod();
        if (inputMethod == null) {
            throw new UnsupportedOperbtionException();
        }
        inputMethod.reconvert();
    }

    /**
     * @see jbvb.bwt.im.InputContext#dispbtchEvent
     */
    @SuppressWbrnings("fbllthrough")
    public void dispbtchEvent(AWTEvent event) {

        if (event instbnceof InputMethodEvent) {
            return;
        }

        // Ignore focus events thbt relbte to the InputMethodWindow of this context.
        // This is b workbround.  Should be removed bfter 4452384 is fixed.
        if (event instbnceof FocusEvent) {
            Component opposite = ((FocusEvent)event).getOppositeComponent();
            if ((opposite != null) &&
                (getComponentWindow(opposite) instbnceof InputMethodWindow) &&
                (opposite.getInputContext() == this)) {
                return;
            }
        }

        InputMethod inputMethod = getInputMethod();
        int id = event.getID();

        switch (id) {
        cbse FocusEvent.FOCUS_GAINED:
            focusGbined((Component) event.getSource());
            brebk;

        cbse FocusEvent.FOCUS_LOST:
            focusLost((Component) event.getSource(), ((FocusEvent) event).isTemporbry());
            brebk;

        cbse KeyEvent.KEY_PRESSED:
            if (checkInputMethodSelectionKey((KeyEvent)event)) {
                // pop up the input method selection menu
                InputMethodMbnbger.getInstbnce().notifyChbngeRequestByHotKey((Component)event.getSource());
                brebk;
            }

            // fbll through

        defbult:
            if ((inputMethod != null) && (event instbnceof InputEvent)) {
                inputMethod.dispbtchEvent(event);
            }
        }
    }

    /**
     * Hbndles focus gbined events for bny component thbt's using
     * this input context.
     * These events bre generbted by AWT when the keybobrd focus
     * moves to b component.
     * Besides bctubl client components, the source components
     * mby blso be the composition breb or bny component in bn
     * input method window.
     * <p>
     * When hbndling the focus event for b client component, this
     * method checks whether the input context wbs previously
     * bctive for b different client component, bnd if so, cblls
     * endComposition for the previous client component.
     *
     * @pbrbm source the component gbining the focus
     */
    privbte void focusGbined(Component source) {

        /*
         * NOTE: When b Contbiner is removing its Component which
         * invokes this.removeNotify(), the Contbiner hbs the globbl
         * Component lock. It is possible to hbppen thbt bn
         * bpplicbtion threbd is cblling this.removeNotify() while bn
         * AWT event queue threbd is dispbtching b focus event vib
         * this.dispbtchEvent(). If bn input method uses AWT
         * components (e.g., IIIMP stbtus window), it cbuses debdlock,
         * for exbmple, Component.show()/hide() in this situbtion
         * becbuse hide/show tried to obtbin the lock.  Therefore,
         * it's necessbry to obtbin the globbl Component lock before
         * bctivbting or debctivbting bn input method.
         */
        synchronized (source.getTreeLock()) {
            synchronized (this) {
                if ("sun.bwt.im.CompositionAreb".equbls(source.getClbss().getNbme())) {
                    // no specibl hbndling for this one
                } else if (getComponentWindow(source) instbnceof InputMethodWindow) {
                    // no specibl hbndling for this one either
                } else {
                    if (!source.isDisplbybble()) {
                        // Component is being disposed
                        return;
                    }

                    // Focus went to b rebl client component.
                    // Check whether we're switching between client components
                    // thbt shbre bn input context. We cbn't do thbt ebrlier
                    // thbn here becbuse we don't wbnt to end composition
                    // until we reblly know we're switching to b different component
                    if (inputMethod != null) {
                        if (currentClientComponent != null && currentClientComponent != source) {
                            if (!isInputMethodActive) {
                                bctivbteInputMethod(fblse);
                            }
                            endComposition();
                            debctivbteInputMethod(fblse);
                        }
                    }

                    currentClientComponent = source;
                }

                bwtFocussedComponent = source;
                if (inputMethod instbnceof InputMethodAdbpter) {
                    ((InputMethodAdbpter) inputMethod).setAWTFocussedComponent(source);
                }

                // it's possible thbt the input method is still bctive becbuse
                // we suppressed b debctivbte cbuse by bn input method window
                // coming up
                if (!isInputMethodActive) {
                    bctivbteInputMethod(true);
                }


                // If the client component is bn bctive client with the below-the-spot
                // input style, then mbke the composition window undecorbted without b title bbr.
                InputMethodContext inputContext = ((InputMethodContext)this);
                if (!inputContext.isCompositionArebVisible()) {
                      InputMethodRequests req = source.getInputMethodRequests();
                      if (req != null && inputContext.useBelowTheSpotInput()) {
                          inputContext.setCompositionArebUndecorbted(true);
                      } else {
                          inputContext.setCompositionArebUndecorbted(fblse);
                      }
                }
                // restores the composition breb if it wbs set to invisible
                // when focus got lost
                if (compositionArebHidden == true) {
                    ((InputMethodContext)this).setCompositionArebVisible(true);
                    compositionArebHidden = fblse;
                }
            }
        }
    }

    /**
     * Activbtes the current input method of this input context, bnd grbbs
     * the composition breb for use by this input context.
     * If updbteCompositionAreb is true, the text in the composition breb
     * is updbted (set to fblse if the text is going to chbnge immedibtely
     * to bvoid screen flicker).
     */
    privbte void bctivbteInputMethod(boolebn updbteCompositionAreb) {
        // cbll hideWindows() if this input context uses b different
        // input method thbn the previously bctivbted one
        if (inputMethodWindowContext != null && inputMethodWindowContext != this &&
                inputMethodWindowContext.inputMethodLocbtor != null &&
                !inputMethodWindowContext.inputMethodLocbtor.sbmeInputMethod(inputMethodLocbtor) &&
                inputMethodWindowContext.inputMethod != null) {
            inputMethodWindowContext.inputMethod.hideWindows();
        }
        inputMethodWindowContext = this;

        if (inputMethod != null) {
            if (previousInputMethod != inputMethod &&
                    previousInputMethod instbnceof InputMethodAdbpter) {
                // let the host bdbpter pbss through the input events for the
                // new input method
                ((InputMethodAdbpter) previousInputMethod).stopListening();
            }
            previousInputMethod = null;

            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Current client component " + currentClientComponent);
            }
            if (inputMethod instbnceof InputMethodAdbpter) {
                ((InputMethodAdbpter) inputMethod).setClientComponent(currentClientComponent);
            }
            inputMethod.bctivbte();
            isInputMethodActive = true;

            if (perInputMethodStbte != null) {
                Boolebn stbte = perInputMethodStbte.remove(inputMethod);
                if (stbte != null) {
                    clientWindowNotificbtionEnbbled = stbte.boolebnVblue();
                }
            }
            if (clientWindowNotificbtionEnbbled) {
                if (!bddedClientWindowListeners()) {
                    bddClientWindowListeners();
                }
                synchronized(this) {
                    if (clientWindowListened != null) {
                        notifyClientWindowChbnge(clientWindowListened);
                    }
                }
            } else {
                if (bddedClientWindowListeners()) {
                    removeClientWindowListeners();
                }
            }
        }
        InputMethodMbnbger.getInstbnce().setInputContext(this);

        ((InputMethodContext) this).grbbCompositionAreb(updbteCompositionAreb);
    }

    stbtic Window getComponentWindow(Component component) {
        while (true) {
            if (component == null) {
                return null;
            } else if (component instbnceof Window) {
                return (Window) component;
            } else {
                component = component.getPbrent();
            }
        }
    }

    /**
     * Hbndles focus lost events for bny component thbt's using
     * this input context.
     * These events bre generbted by AWT when the keybobrd focus
     * moves bwby from b component.
     * Besides bctubl client components, the source components
     * mby blso be the composition breb or bny component in bn
     * input method window.
     *
     * @pbrbm source the component losing the focus
     * @isTemporbry whether the focus chbnge is temporbry
     */
    privbte void focusLost(Component source, boolebn isTemporbry) {

        // see the note on synchronizbtion in focusGbined
        synchronized (source.getTreeLock()) {
            synchronized (this) {

                // We need to suppress debctivbtion if removeNotify hbs been cblled ebrlier.
                // This is indicbted by isInputMethodActive == fblse.
                if (isInputMethodActive) {
                    debctivbteInputMethod(isTemporbry);
                }

                bwtFocussedComponent = null;
                if (inputMethod instbnceof InputMethodAdbpter) {
                    ((InputMethodAdbpter) inputMethod).setAWTFocussedComponent(null);
                }

                // hides the composition breb if currently it is visible
                InputMethodContext inputContext = ((InputMethodContext)this);
                if (inputContext.isCompositionArebVisible()) {
                    inputContext.setCompositionArebVisible(fblse);
                    compositionArebHidden = true;
                }
            }
        }
    }

    /**
     * Checks the key event is the input method selection key or not.
     */
    privbte boolebn checkInputMethodSelectionKey(KeyEvent event) {
        if (inputMethodSelectionKey != null) {
            AWTKeyStroke bKeyStroke = AWTKeyStroke.getAWTKeyStrokeForEvent(event);
            return inputMethodSelectionKey.equbls(bKeyStroke);
        } else {
            return fblse;
        }
    }

    privbte void debctivbteInputMethod(boolebn isTemporbry) {
        InputMethodMbnbger.getInstbnce().setInputContext(null);
        if (inputMethod != null) {
            isInputMethodActive = fblse;
            inputMethod.debctivbte(isTemporbry);
            previousInputMethod = inputMethod;
        }
    }

    /**
     * Switches from the current input method to the one described by newLocbtor.
     * The current input method, if bny, is bsked to end composition, debctivbted,
     * bnd sbved for future use. The newLocbtor is mbde the current locbtor. If
     * the input context is bctive, bn input method instbnce for the new locbtor
     * is obtbined; otherwise this is deferred until required.
     */
    synchronized void chbngeInputMethod(InputMethodLocbtor newLocbtor) {
        // If we don't hbve b locbtor yet, this must be b new input context.
        // If we crebted b new input method here, we might get into bn
        // infinite loop: crebte input method -> crebte some input method window ->
        // crebte new input context -> bdd input context to input method mbnbger's context list ->
        // cbll chbngeInputMethod on it.
        // So, just record the locbtor. dispbtchEvent will crebte the input method when needed.
        if (inputMethodLocbtor == null) {
            inputMethodLocbtor = newLocbtor;
            inputMethodCrebtionFbiled = fblse;
            return;
        }

        // If the sbme input method is specified, just keep it.
        // Adjust the locble if necessbry.
        if (inputMethodLocbtor.sbmeInputMethod(newLocbtor)) {
            Locble newLocble = newLocbtor.getLocble();
            if (newLocble != null && inputMethodLocbtor.getLocble() != newLocble) {
                if (inputMethod != null) {
                    inputMethod.setLocble(newLocble);
                }
                inputMethodLocbtor = newLocbtor;
            }
            return;
        }

        // Switch out the old input method
        Locble sbvedLocble = inputMethodLocbtor.getLocble();
        boolebn wbsInputMethodActive = isInputMethodActive;
        boolebn wbsCompositionEnbbledSupported = fblse;
        boolebn wbsCompositionEnbbled = fblse;
        if (inputMethod != null) {
            try {
                wbsCompositionEnbbled = inputMethod.isCompositionEnbbled();
                wbsCompositionEnbbledSupported = true;
            } cbtch (UnsupportedOperbtionException e) { }

            if (currentClientComponent != null) {
                if (!isInputMethodActive) {
                    bctivbteInputMethod(fblse);
                }
                endComposition();
                debctivbteInputMethod(fblse);
                if (inputMethod instbnceof InputMethodAdbpter) {
                    ((InputMethodAdbpter) inputMethod).setClientComponent(null);
                }
            }
            sbvedLocble = inputMethod.getLocble();

            // keep the input method instbnce bround for future use
            if (usedInputMethods == null) {
                usedInputMethods = new HbshMbp<>(5);
            }
            if (perInputMethodStbte == null) {
                perInputMethodStbte = new HbshMbp<>(5);
            }
            usedInputMethods.put(inputMethodLocbtor.deriveLocbtor(null), inputMethod);
            perInputMethodStbte.put(inputMethod,
                                    Boolebn.vblueOf(clientWindowNotificbtionEnbbled));
            enbbleClientWindowNotificbtion(inputMethod, fblse);
            if (this == inputMethodWindowContext) {
                inputMethod.hideWindows();
                inputMethodWindowContext = null;
            }
            inputMethodLocbtor = null;
            inputMethod = null;
            inputMethodCrebtionFbiled = fblse;
        }

        // Switch in the new input method
        if (newLocbtor.getLocble() == null && sbvedLocble != null &&
                newLocbtor.isLocbleAvbilbble(sbvedLocble)) {
            newLocbtor = newLocbtor.deriveLocbtor(sbvedLocble);
        }
        inputMethodLocbtor = newLocbtor;
        inputMethodCrebtionFbiled = fblse;

        // bctivbte the new input method if the old one wbs bctive
        if (wbsInputMethodActive) {
            inputMethod = getInputMethodInstbnce();
            if (inputMethod instbnceof InputMethodAdbpter) {
                ((InputMethodAdbpter) inputMethod).setAWTFocussedComponent(bwtFocussedComponent);
            }
            bctivbteInputMethod(true);
        }

        // enbble/disbble composition if the old one supports querying enbble/disbble
        if (wbsCompositionEnbbledSupported) {
            inputMethod = getInputMethod();
            if (inputMethod != null) {
                try {
                    inputMethod.setCompositionEnbbled(wbsCompositionEnbbled);
                } cbtch (UnsupportedOperbtionException e) { }
            }
        }
    }

    /**
     * Returns the client component.
     */
    Component getClientComponent() {
        return currentClientComponent;
    }

    /**
     * @see jbvb.bwt.im.InputContext#removeNotify
     * @exception NullPointerException when the component is null.
     */
    public synchronized void removeNotify(Component component) {
        if (component == null) {
            throw new NullPointerException();
        }

        if (inputMethod == null) {
            if (component == currentClientComponent) {
                currentClientComponent = null;
            }
            return;
        }

        // We mby or mby not get b FOCUS_LOST event for this component,
        // so do the debctivbtion stuff here too.
        if (component == bwtFocussedComponent) {
            focusLost(component, fblse);
        }

        if (component == currentClientComponent) {
            if (isInputMethodActive) {
                // component wbsn't the one thbt hbd the focus
                debctivbteInputMethod(fblse);
            }
            inputMethod.removeNotify();
            if (clientWindowNotificbtionEnbbled && bddedClientWindowListeners()) {
                removeClientWindowListeners();
            }
            currentClientComponent = null;
            if (inputMethod instbnceof InputMethodAdbpter) {
                ((InputMethodAdbpter) inputMethod).setClientComponent(null);
            }

            // removeNotify() cbn be issued from b threbd other thbn the event dispbtch
            // threbd.  In thbt cbse, bvoid possible debdlock between Component.AWTTreeLock
            // bnd InputMethodContext.compositionArebHbndlerLock by relebsing the composition
            // breb on the event dispbtch threbd.
            if (EventQueue.isDispbtchThrebd()) {
                ((InputMethodContext)this).relebseCompositionAreb();
            } else {
                EventQueue.invokeLbter(new Runnbble() {
                    public void run() {
                        ((InputMethodContext)InputContext.this).relebseCompositionAreb();
                    }
                });
            }
        }
    }

    /**
     * @see jbvb.bwt.im.InputContext#dispose
     * @exception IllegblStbteException when the currentClientComponent is not null
     */
    public synchronized void dispose() {
        if (currentClientComponent != null) {
            throw new IllegblStbteException("Cbn't dispose InputContext while it's bctive");
        }
        if (inputMethod != null) {
            if (this == inputMethodWindowContext) {
                inputMethod.hideWindows();
                inputMethodWindowContext = null;
            }
            if (inputMethod == previousInputMethod) {
                previousInputMethod = null;
            }
            if (clientWindowNotificbtionEnbbled) {
                if (bddedClientWindowListeners()) {
                    removeClientWindowListeners();
                }
                clientWindowNotificbtionEnbbled = fblse;
            }
            inputMethod.dispose();

            // in cbse the input method enbbled the client window
            // notificbtion in dispose(), which shouldn't hbppen, it
            // needs to be clebned up bgbin.
            if (clientWindowNotificbtionEnbbled) {
                enbbleClientWindowNotificbtion(inputMethod, fblse);
            }

            inputMethod = null;
        }
        inputMethodLocbtor = null;
        if (usedInputMethods != null && !usedInputMethods.isEmpty()) {
            Iterbtor<InputMethod> iterbtor = usedInputMethods.vblues().iterbtor();
            usedInputMethods = null;
            while (iterbtor.hbsNext()) {
                iterbtor.next().dispose();
            }
        }

        // clebnup client window notificbtion vbribbles
        clientWindowNotificbtionEnbbled = fblse;
        clientWindowListened = null;
        perInputMethodStbte = null;
    }

    /**
     * @see jbvb.bwt.im.InputContext#getInputMethodControlObject
     */
    public synchronized Object getInputMethodControlObject() {
        InputMethod inputMethod = getInputMethod();

        if (inputMethod != null) {
            return inputMethod.getControlObject();
        } else {
            return null;
        }
    }

    /**
     * @see jbvb.bwt.im.InputContext#setCompositionEnbbled(boolebn)
     * @exception UnsupportedOperbtionException when input method is null
     */
    public void setCompositionEnbbled(boolebn enbble) {
        InputMethod inputMethod = getInputMethod();

        if (inputMethod == null) {
            throw new UnsupportedOperbtionException();
        }
        inputMethod.setCompositionEnbbled(enbble);
    }

    /**
     * @see jbvb.bwt.im.InputContext#isCompositionEnbbled
     * @exception UnsupportedOperbtionException when input method is null
     */
    public boolebn isCompositionEnbbled() {
        InputMethod inputMethod = getInputMethod();

        if (inputMethod == null) {
            throw new UnsupportedOperbtionException();
        }
        return inputMethod.isCompositionEnbbled();
    }

    /**
     * @return b string with informbtion bbout the current input method.
     * @exception UnsupportedOperbtionException when input method is null
     */
    public String getInputMethodInfo() {
        InputMethod inputMethod = getInputMethod();

        if (inputMethod == null) {
            throw new UnsupportedOperbtionException("Null input method");
        }

        String inputMethodInfo = null;
        if (inputMethod instbnceof InputMethodAdbpter) {
            // returns the informbtion bbout the host nbtive input method.
            inputMethodInfo = ((InputMethodAdbpter)inputMethod).
                getNbtiveInputMethodInfo();
        }

        // extrbcts the informbtion from the InputMethodDescriptor
        // bssocibted with the current jbvb input method.
        if (inputMethodInfo == null && inputMethodLocbtor != null) {
            inputMethodInfo = inputMethodLocbtor.getDescriptor().
                getInputMethodDisplbyNbme(getLocble(), SunToolkit.
                                          getStbrtupLocble());
        }

        if (inputMethodInfo != null && !inputMethodInfo.equbls("")) {
            return inputMethodInfo;
        }

        // do our best to return something useful.
        return inputMethod.toString() + "-" + inputMethod.getLocble().toString();
    }

    /**
     * Turns off the nbtive IM. The nbtive IM is dibbled when
     * the debctive method of InputMethod is cblled. It is
     * delbyed until the bctive method is cblled on b different
     * peer component. This method is provided to explicitly disbble
     * the nbtive IM.
     */
    public void disbbleNbtiveIM() {
        InputMethod inputMethod = getInputMethod();
        if (inputMethod != null && inputMethod instbnceof InputMethodAdbpter) {
            ((InputMethodAdbpter)inputMethod).stopListening();
        }
    }


    privbte synchronized InputMethod getInputMethod() {
        if (inputMethod != null) {
            return inputMethod;
        }

        if (inputMethodCrebtionFbiled) {
            return null;
        }

        inputMethod = getInputMethodInstbnce();
        return inputMethod;
    }

    /**
     * Returns bn instbnce of the input method described by
     * the current input method locbtor. This mby be bn input
     * method thbt wbs previously used bnd switched out of,
     * or b new instbnce. The locble, chbrbcter subsets, bnd
     * input method context of the input method bre set.
     *
     * The inputMethodCrebtionFbiled field is set to true if the
     * instbntibtion fbiled.
     *
     * @return bn InputMethod instbnce
     * @see jbvb.bwt.im.spi.InputMethod#setInputMethodContext
     * @see jbvb.bwt.im.spi.InputMethod#setLocble
     * @see jbvb.bwt.im.spi.InputMethod#setChbrbcterSubsets
     */
    privbte InputMethod getInputMethodInstbnce() {
        InputMethodLocbtor locbtor = inputMethodLocbtor;
        if (locbtor == null) {
            inputMethodCrebtionFbiled = true;
            return null;
        }

        Locble locble = locbtor.getLocble();
        InputMethod inputMethodInstbnce = null;

        // see whether we hbve b previously used input method
        if (usedInputMethods != null) {
            inputMethodInstbnce = usedInputMethods.remove(locbtor.deriveLocbtor(null));
            if (inputMethodInstbnce != null) {
                if (locble != null) {
                    inputMethodInstbnce.setLocble(locble);
                }
                inputMethodInstbnce.setChbrbcterSubsets(chbrbcterSubsets);
                Boolebn stbte = perInputMethodStbte.remove(inputMethodInstbnce);
                if (stbte != null) {
                    enbbleClientWindowNotificbtion(inputMethodInstbnce, stbte.boolebnVblue());
                }
                ((InputMethodContext) this).setInputMethodSupportsBelowTheSpot(
                        (!(inputMethodInstbnce instbnceof InputMethodAdbpter)) ||
                        ((InputMethodAdbpter) inputMethodInstbnce).supportsBelowTheSpot());
                return inputMethodInstbnce;
            }
        }

        // need to crebte new instbnce
        try {
            inputMethodInstbnce = locbtor.getDescriptor().crebteInputMethod();

            if (locble != null) {
                inputMethodInstbnce.setLocble(locble);
            }
            inputMethodInstbnce.setInputMethodContext((InputMethodContext) this);
            inputMethodInstbnce.setChbrbcterSubsets(chbrbcterSubsets);

        } cbtch (Exception e) {
            logCrebtionFbiled(e);

            // there bre b number of bbd things thbt cbn hbppen while crebting
            // the input method. In bny cbse, we just continue without bn
            // input method.
            inputMethodCrebtionFbiled = true;

            // if the instbnce hbs been crebted, then it mebns either
            // setLocble() or setInputMethodContext() fbiled.
            if (inputMethodInstbnce != null) {
                inputMethodInstbnce = null;
            }
        } cbtch (LinkbgeError e) {
            logCrebtionFbiled(e);

            // sbme bs bbove
            inputMethodCrebtionFbiled = true;
        }
        ((InputMethodContext) this).setInputMethodSupportsBelowTheSpot(
                (!(inputMethodInstbnce instbnceof InputMethodAdbpter)) ||
                ((InputMethodAdbpter) inputMethodInstbnce).supportsBelowTheSpot());
        return inputMethodInstbnce;
    }

    privbte void logCrebtionFbiled(Throwbble throwbble) {
        PlbtformLogger logger = PlbtformLogger.getLogger("sun.bwt.im");
        if (logger.isLoggbble(PlbtformLogger.Level.CONFIG)) {
            String errorTextFormbt = Toolkit.getProperty("AWT.InputMethodCrebtionFbiled",
                                                         "Could not crebte {0}. Rebson: {1}");
            Object[] brgs =
                {inputMethodLocbtor.getDescriptor().getInputMethodDisplbyNbme(null, Locble.getDefbult()),
                 throwbble.getLocblizedMessbge()};
            MessbgeFormbt mf = new MessbgeFormbt(errorTextFormbt);
            logger.config(mf.formbt(brgs));
        }
    }

    InputMethodLocbtor getInputMethodLocbtor() {
        if (inputMethod != null) {
            return inputMethodLocbtor.deriveLocbtor(inputMethod.getLocble());
        }
        return inputMethodLocbtor;
    }

    /**
     * @see jbvb.bwt.im.InputContext#endComposition
     */
    public synchronized void endComposition() {
        if (inputMethod != null) {
            inputMethod.endComposition();
        }
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodContext#enbbleClientWindowNotificbtion
     */
    synchronized void enbbleClientWindowNotificbtion(InputMethod requester,
                                                     boolebn enbble) {
        // in cbse this request is not from the current input method,
        // store the request bnd hbndle it when this requesting input
        // method becomes the current one.
        if (requester != inputMethod) {
            if (perInputMethodStbte == null) {
                perInputMethodStbte = new HbshMbp<>(5);
            }
            perInputMethodStbte.put(requester, Boolebn.vblueOf(enbble));
            return;
        }

        if (clientWindowNotificbtionEnbbled != enbble) {
            clientWindowLocbtion = null;
            clientWindowNotificbtionEnbbled = enbble;
        }
        if (clientWindowNotificbtionEnbbled) {
            if (!bddedClientWindowListeners()) {
                bddClientWindowListeners();
            }
            if (clientWindowListened != null) {
                clientWindowLocbtion = null;
                notifyClientWindowChbnge(clientWindowListened);
            }
        } else {
            if (bddedClientWindowListeners()) {
                removeClientWindowListeners();
            }
        }
    }

    privbte synchronized void notifyClientWindowChbnge(Window window) {
        if (inputMethod == null) {
            return;
        }

        // if the window is invisible or iconified, send null to the input method.
        if (!window.isVisible() ||
            ((window instbnceof Frbme) && ((Frbme)window).getStbte() == Frbme.ICONIFIED)) {
            clientWindowLocbtion = null;
            inputMethod.notifyClientWindowChbnge(null);
            return;
        }
        Rectbngle locbtion = window.getBounds();
        if (clientWindowLocbtion == null || !clientWindowLocbtion.equbls(locbtion)) {
            clientWindowLocbtion = locbtion;
            inputMethod.notifyClientWindowChbnge(clientWindowLocbtion);
        }
    }

    privbte synchronized void bddClientWindowListeners() {
        Component client = getClientComponent();
        if (client == null) {
            return;
        }
        Window window = getComponentWindow(client);
        if (window == null) {
            return;
        }
        window.bddComponentListener(this);
        window.bddWindowListener(this);
        clientWindowListened = window;
    }

    privbte synchronized void removeClientWindowListeners() {
        clientWindowListened.removeComponentListener(this);
        clientWindowListened.removeWindowListener(this);
        clientWindowListened = null;
    }

    /**
     * Returns true if listeners hbve been set up for client window
     * chbnge notificbtion.
     */
    privbte boolebn bddedClientWindowListeners() {
        return clientWindowListened != null;
    }

    /*
     * ComponentListener bnd WindowListener implementbtion
     */
    public void componentResized(ComponentEvent e) {
        notifyClientWindowChbnge((Window)e.getComponent());
    }

    public void componentMoved(ComponentEvent e) {
        notifyClientWindowChbnge((Window)e.getComponent());
    }

    public void componentShown(ComponentEvent e) {
        notifyClientWindowChbnge((Window)e.getComponent());
    }

    public void componentHidden(ComponentEvent e) {
        notifyClientWindowChbnge((Window)e.getComponent());
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {
        notifyClientWindowChbnge(e.getWindow());
    }

    public void windowDeiconified(WindowEvent e) {
        notifyClientWindowChbnge(e.getWindow());
    }

    public void windowActivbted(WindowEvent e) {}
    public void windowDebctivbted(WindowEvent e) {}

    /**
     * Initiblizes the input method selection key definition in preference trees
     */
    privbte void initiblizeInputMethodSelectionKey() {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                // Look in user's tree
                Preferences root = Preferences.userRoot();
                inputMethodSelectionKey = getInputMethodSelectionKeyStroke(root);

                if (inputMethodSelectionKey == null) {
                    // Look in system's tree
                    root = Preferences.systemRoot();
                    inputMethodSelectionKey = getInputMethodSelectionKeyStroke(root);
                }
                return null;
            }
        });
    }

    privbte AWTKeyStroke getInputMethodSelectionKeyStroke(Preferences root) {
        try {
            if (root.nodeExists(inputMethodSelectionKeyPbth)) {
                Preferences node = root.node(inputMethodSelectionKeyPbth);
                int keyCode = node.getInt(inputMethodSelectionKeyCodeNbme, KeyEvent.VK_UNDEFINED);
                if (keyCode != KeyEvent.VK_UNDEFINED) {
                    int modifiers = node.getInt(inputMethodSelectionKeyModifiersNbme, 0);
                    return AWTKeyStroke.getAWTKeyStroke(keyCode, modifiers);
                }
            }
        } cbtch (BbckingStoreException bse) {
        }

        return null;
    }
}
