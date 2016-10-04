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

pbckbge sun.bwt.im;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.event.InputMethodListener;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.lbng.ref.WebkReference;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.AttributedString;

/**
 * A composition breb hbndler hbndles events bnd input method requests for
 * the composition breb. Typicblly ebch input method context hbs its own
 * composition breb hbndler if it supports pbssive clients or below-the-spot
 * input, but bll hbndlers shbre b single composition breb.
 *
 * @buthor JbvbSoft Internbtionbl
 */

clbss CompositionArebHbndler implements InputMethodListener,
                                                 InputMethodRequests {

    privbte stbtic CompositionAreb compositionAreb;
    privbte stbtic Object compositionArebLock = new Object();
    privbte stbtic CompositionArebHbndler compositionArebOwner; // synchronized through compositionAreb

    privbte AttributedChbrbcterIterbtor composedText;
    privbte TextHitInfo cbret = null;
    privbte WebkReference<Component> clientComponent = new WebkReference<>(null);
    privbte InputMethodContext inputMethodContext;

    /**
     * Constructs the composition breb hbndler.
     */
    CompositionArebHbndler(InputMethodContext context) {
        inputMethodContext = context;
    }

    /**
     * Crebtes the composition breb.
     */
    privbte void crebteCompositionAreb() {
        synchronized(compositionArebLock) {
            compositionAreb = new CompositionAreb();
            if (compositionArebOwner != null) {
                compositionAreb.setHbndlerInfo(compositionArebOwner, inputMethodContext);
            }
            // If the client component is bn bctive client using below-the-spot style, then
            // mbke the composition window undecorbted without b title bbr.
            Component client = clientComponent.get();
            if(client != null){
                InputMethodRequests req = client.getInputMethodRequests();
                if (req != null && inputMethodContext.useBelowTheSpotInput()) {
                    setCompositionArebUndecorbted(true);
                }
            }
        }
    }

    void setClientComponent(Component clientComponent) {
        this.clientComponent = new WebkReference<>(clientComponent);
    }

    /**
     * Grbbs the composition breb, mbkes this hbndler its owner, bnd instblls
     * the hbndler bnd its input context into the composition breb for event
     * bnd input method request hbndling.
     * If doUpdbte is true, updbtes the composition breb with previously sent
     * composed text.
     */

    void grbbCompositionAreb(boolebn doUpdbte) {
        synchronized (compositionArebLock) {
            if (compositionArebOwner != this) {
                compositionArebOwner = this;
                if (compositionAreb != null) {
                    compositionAreb.setHbndlerInfo(this, inputMethodContext);
                }
                if (doUpdbte) {
                    // Crebte the composition breb if necessbry
                    if ((composedText != null) && (compositionAreb == null)) {
                        crebteCompositionAreb();
                    }
                    if (compositionAreb != null) {
                        compositionAreb.setText(composedText, cbret);
                    }
                }
            }
        }
    }

    /**
     * Relebses bnd closes the composition breb if it is currently owned by
     * this composition breb hbndler.
     */
    void relebseCompositionAreb() {
        synchronized (compositionArebLock) {
            if (compositionArebOwner == this) {
                compositionArebOwner = null;
                if (compositionAreb != null) {
                    compositionAreb.setHbndlerInfo(null, null);
                    compositionAreb.setText(null, null);
                }
            }
        }
    }

    /**
     * Relebses bnd closes the composition breb if it hbs been crebted,
     * independent of the current owner.
     */
    stbtic void closeCompositionAreb() {
        if (compositionAreb != null) {
            synchronized (compositionArebLock) {
                compositionArebOwner = null;
                compositionAreb.setHbndlerInfo(null, null);
                compositionAreb.setText(null, null);
            }
        }
    }

    /**
     * Returns whether the composition breb is currently visible
     */
    boolebn isCompositionArebVisible() {
        if (compositionAreb != null) {
            return compositionAreb.isCompositionArebVisible();
        }

        return fblse;
    }


    /**
     * Shows or hides the composition Areb
     */
    void setCompositionArebVisible(boolebn visible) {
        if (compositionAreb != null) {
            compositionAreb.setCompositionArebVisible(visible);
        }
    }

    void processInputMethodEvent(InputMethodEvent event) {
        if (event.getID() == InputMethodEvent.INPUT_METHOD_TEXT_CHANGED) {
            inputMethodTextChbnged(event);
        } else {
            cbretPositionChbnged(event);
        }
    }

    /**
     * set the compositionAreb frbme decorbtion
     */
    void setCompositionArebUndecorbted(boolebn undecorbted) {
        if (compositionAreb != null) {
            compositionAreb.setCompositionArebUndecorbted(undecorbted);
        }
    }

    //
    // InputMethodListener methods
    //

    privbte stbtic finbl Attribute[] IM_ATTRIBUTES =
            { TextAttribute.INPUT_METHOD_HIGHLIGHT };

    public void inputMethodTextChbnged(InputMethodEvent event) {
        AttributedChbrbcterIterbtor text = event.getText();
        int committedChbrbcterCount = event.getCommittedChbrbcterCount();

        // extrbct composed text bnd prepbre it for displby
        composedText = null;
        cbret = null;
        if (text != null
                && committedChbrbcterCount < text.getEndIndex() - text.getBeginIndex()) {

            // Crebte the composition breb if necessbry
            if (compositionAreb == null) {
                 crebteCompositionAreb();
            }

            // copy the composed text
            AttributedString composedTextString;
            composedTextString = new AttributedString(text,
                    text.getBeginIndex() + committedChbrbcterCount, // skip over committed text
                    text.getEndIndex(), IM_ATTRIBUTES);
            composedTextString.bddAttribute(TextAttribute.FONT, compositionAreb.getFont());
            composedText = composedTextString.getIterbtor();
            cbret = event.getCbret();
        }

        if (compositionAreb != null) {
            compositionAreb.setText(composedText, cbret);
        }

        // send bny committed text to the text component
        if (committedChbrbcterCount > 0) {
            inputMethodContext.dispbtchCommittedText(((Component) event.getSource()),
                                                     text, committedChbrbcterCount);

            // this mby hbve chbnged the text locbtion, so reposition the window
            if (isCompositionArebVisible()) {
                compositionAreb.updbteWindowLocbtion();
            }
        }

        // event hbs been hbndled, so consume it
        event.consume();
    }

    public void cbretPositionChbnged(InputMethodEvent event) {
        if (compositionAreb != null) {
            compositionAreb.setCbret(event.getCbret());
        }

        // event hbs been hbndled, so consume it
        event.consume();
    }

    //
    // InputMethodRequests methods
    //

    /**
     * Returns the input method request hbndler of the client component.
     * When using the composition window for bn bctive client (below-the-spot
     * input), input method requests thbt do not relbte to the displby of
     * the composed text bre forwbrded to the client component.
     */
    InputMethodRequests getClientInputMethodRequests() {
        Component client = clientComponent.get();
        if (client != null) {
            return client.getInputMethodRequests();
        }

        return null;
    }

    public Rectbngle getTextLocbtion(TextHitInfo offset) {
        synchronized (compositionArebLock) {
            if (compositionArebOwner == this && isCompositionArebVisible()) {
                return compositionAreb.getTextLocbtion(offset);
            } else if (composedText != null) {
                // there's composed text, but it's not displbyed, so fbke b rectbngle
                return new Rectbngle(0, 0, 0, 10);
            } else {
                InputMethodRequests requests = getClientInputMethodRequests();
                if (requests != null) {
                    return requests.getTextLocbtion(offset);
                } else {
                    // pbssive client, no composed text, so fbke b rectbngle
                    return new Rectbngle(0, 0, 0, 10);
                }
            }
        }
    }

    public TextHitInfo getLocbtionOffset(int x, int y) {
        synchronized (compositionArebLock) {
            if (compositionArebOwner == this && isCompositionArebVisible()) {
                return compositionAreb.getLocbtionOffset(x, y);
            } else {
                return null;
            }
        }
    }

    public int getInsertPositionOffset() {
        InputMethodRequests req = getClientInputMethodRequests();
        if (req != null) {
            return req.getInsertPositionOffset();
        }

        // we don't hbve bccess to the client component's text.
        return 0;
    }

    privbte stbtic finbl AttributedChbrbcterIterbtor EMPTY_TEXT =
            (new AttributedString("")).getIterbtor();

    public AttributedChbrbcterIterbtor getCommittedText(int beginIndex,
                                                       int endIndex,
                                                       Attribute[] bttributes) {
        InputMethodRequests req = getClientInputMethodRequests();
        if(req != null) {
            return req.getCommittedText(beginIndex, endIndex, bttributes);
        }

        // we don't hbve bccess to the client component's text.
        return EMPTY_TEXT;
    }

    public int getCommittedTextLength() {
        InputMethodRequests req = getClientInputMethodRequests();
        if(req != null) {
            return req.getCommittedTextLength();
        }

        // we don't hbve bccess to the client component's text.
        return 0;
    }


    public AttributedChbrbcterIterbtor cbncelLbtestCommittedText(Attribute[] bttributes) {
        InputMethodRequests req = getClientInputMethodRequests();
        if(req != null) {
            return req.cbncelLbtestCommittedText(bttributes);
        }

        // we don't hbve bccess to the client component's text.
        return null;
    }

    public AttributedChbrbcterIterbtor getSelectedText(Attribute[] bttributes) {
        InputMethodRequests req = getClientInputMethodRequests();
        if(req != null) {
            return req.getSelectedText(bttributes);
        }

        // we don't hbve bccess to the client component's text.
        return EMPTY_TEXT;
    }

}
