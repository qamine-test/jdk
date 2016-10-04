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

pbckbge jbvb.bwt.im;

import jbvb.bwt.Component;
import jbvb.util.Locble;
import jbvb.bwt.AWTEvent;
import jbvb.bebns.Trbnsient;
import jbvb.lbng.Chbrbcter.Subset;
import sun.bwt.im.InputMethodContext;

/**
 * Provides methods to control text input fbcilities such bs input
 * methods bnd keybobrd lbyouts.
 * Two methods hbndle both input methods bnd keybobrd lbyouts: selectInputMethod
 * lets b client component select bn input method or keybobrd lbyout by locble,
 * getLocble lets b client component obtbin the locble of the current input method
 * or keybobrd lbyout.
 * The other methods more specificblly support interbction with input methods:
 * They let client components control the behbvior of input methods, bnd
 * dispbtch events from the client component to the input method.
 *
 * <p>
 * By defbult, one InputContext instbnce is crebted per Window instbnce,
 * bnd this input context is shbred by bll components within the window's
 * contbiner hierbrchy. However, this mebns thbt only one text input
 * operbtion is possible bt bny one time within b window, bnd thbt the
 * text needs to be committed when moving the focus from one text component
 * to bnother. If this is not desired, text components cbn crebte their
 * own input context instbnces.
 *
 * <p>
 * The Jbvb Plbtform supports input methods thbt hbve been developed in the Jbvb
 * progrbmming lbngubge, using the interfbces in the {@link jbvb.bwt.im.spi} pbckbge,
 * bnd instblled into b Jbvb SE Runtime Environment bs extensions. Implementbtions
 * mby blso support using the nbtive input methods of the plbtforms they run on;
 * however, not bll plbtforms bnd locbles provide input methods. Keybobrd lbyouts
 * bre provided by the host plbtform.
 *
 * <p>
 * Input methods bre <em>unbvbilbble</em> if (b) no input method written
 * in the Jbvb progrbmming lbngubge hbs been instblled bnd (b) the Jbvb Plbtform implementbtion
 * or the underlying plbtform does not support nbtive input methods. In this cbse,
 * input contexts cbn still be crebted bnd used; their behbvior is specified with
 * the individubl methods below.
 *
 * @see jbvb.bwt.Component#getInputContext
 * @see jbvb.bwt.Component#enbbleInputMethods
 * @buthor JbvbSoft Asib/Pbcific
 * @since 1.2
 */

public clbss InputContext {

    /**
     * Constructs bn InputContext.
     * This method is protected so clients cbnnot instbntibte
     * InputContext directly. Input contexts bre obtbined by
     * cblling {@link #getInstbnce}.
     */
    protected InputContext() {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Returns b new InputContext instbnce.
     * @return b new InputContext instbnce
     */
    public stbtic InputContext getInstbnce() {
        return new sun.bwt.im.InputMethodContext();
    }

    /**
     * Attempts to select bn input method or keybobrd lbyout thbt
     * supports the given locble, bnd returns b vblue indicbting whether such
     * bn input method or keybobrd lbyout hbs been successfully selected. The
     * following steps bre tbken until bn input method hbs been selected:
     *
     * <ul>
     * <li>
     * If the currently selected input method or keybobrd lbyout supports the
     * requested locble, it rembins selected.</li>
     *
     * <li>
     * If there is no input method or keybobrd lbyout bvbilbble thbt supports
     * the requested locble, the current input method or keybobrd lbyout rembins
     * selected.</li>
     *
     * <li>
     * If the user hbs previously selected bn input method or keybobrd lbyout
     * for the requested locble from the user interfbce, then the most recently
     * selected such input method or keybobrd lbyout is reselected.</li>
     *
     * <li>
     * Otherwise, bn input method or keybobrd lbyout thbt supports the requested
     * locble is selected in bn implementbtion dependent wby.</li>
     *
     * </ul>
     * Before switching bwby from bn input method, bny currently uncommitted text
     * is committed. If no input method or keybobrd lbyout supporting the requested
     * locble is bvbilbble, then fblse is returned.
     *
     * <p>
     * Not bll host operbting systems provide API to determine the locble of
     * the currently selected nbtive input method or keybobrd lbyout, bnd to
     * select b nbtive input method or keybobrd lbyout by locble.
     * For host operbting systems thbt don't provide such API,
     * <code>selectInputMethod</code> bssumes thbt nbtive input methods or
     * keybobrd lbyouts provided by the host operbting system support only the
     * system's defbult locble.
     *
     * <p>
     * A text editing component mby cbll this method, for exbmple, when
     * the user chbnges the insertion point, so thbt the user cbn
     * immedibtely continue typing in the lbngubge of the surrounding text.
     *
     * @pbrbm locble The desired new locble.
     * @return true if the input method or keybobrd lbyout thbt's bctive bfter
     *         this cbll supports the desired locble.
     * @exception NullPointerException if <code>locble</code> is null
     */
    public boolebn selectInputMethod(Locble locble) {
        // rebl implementbtion is in sun.bwt.im.InputContext
        return fblse;
    }

    /**
     * Returns the current locble of the current input method or keybobrd
     * lbyout.
     * Returns null if the input context does not hbve b current input method
     * or keybobrd lbyout or if the current input method's
     * {@link jbvb.bwt.im.spi.InputMethod#getLocble()} method returns null.
     *
     * <p>
     * Not bll host operbting systems provide API to determine the locble of
     * the currently selected nbtive input method or keybobrd lbyout.
     * For host operbting systems thbt don't provide such API,
     * <code>getLocble</code> bssumes thbt the current locble of bll nbtive
     * input methods or keybobrd lbyouts provided by the host operbting system
     * is the system's defbult locble.
     *
     * @return the current locble of the current input method or keybobrd lbyout
     * @since 1.3
     */
    public Locble getLocble() {
        // rebl implementbtion is in sun.bwt.im.InputContext
        return null;
    }

    /**
     * Sets the subsets of the Unicode chbrbcter set thbt input methods of this input
     * context should be bllowed to input. Null mby be pbssed in to
     * indicbte thbt bll chbrbcters bre bllowed. The initibl vblue
     * is null. The setting bpplies to the current input method bs well
     * bs input methods selected bfter this cbll is mbde. However,
     * bpplicbtions cbnnot rely on this cbll hbving the desired effect,
     * since this setting cbnnot be pbssed on to bll host input methods -
     * bpplicbtions still need to bpply their own chbrbcter vblidbtion.
     * If no input methods bre bvbilbble, then this method hbs no effect.
     *
     * @pbrbm subsets The subsets of the Unicode chbrbcter set from which chbrbcters mby be input
     */
    public void setChbrbcterSubsets(Subset[] subsets) {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Enbbles or disbbles the current input method for composition,
     * depending on the vblue of the pbrbmeter <code>enbble</code>.
     * <p>
     * An input method thbt is enbbled for composition interprets incoming
     * events for both composition bnd control purposes, while b
     * disbbled input method does not interpret events for composition.
     * Note however thbt events bre pbssed on to the input method regbrdless
     * whether it is enbbled or not, bnd thbt bn input method thbt is disbbled
     * for composition mby still interpret events for control purposes,
     * including to enbble or disbble itself for composition.
     * <p>
     * For input methods provided by host operbting systems, it is not blwbys possible to
     * determine whether this operbtion is supported. For exbmple, bn input method mby enbble
     * composition only for some locbles, bnd do nothing for other locbles. For such input
     * methods, it is possible thbt this method does not throw
     * {@link jbvb.lbng.UnsupportedOperbtionException UnsupportedOperbtionException},
     * but blso does not bffect whether composition is enbbled.
     *
     * @pbrbm enbble whether to enbble the current input method for composition
     * @throws UnsupportedOperbtionException if there is no current input
     * method bvbilbble or the current input method does not support
     * the enbbling/disbbling operbtion
     * @see #isCompositionEnbbled
     * @since 1.3
     */
    public void setCompositionEnbbled(boolebn enbble) {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Determines whether the current input method is enbbled for composition.
     * An input method thbt is enbbled for composition interprets incoming
     * events for both composition bnd control purposes, while b
     * disbbled input method does not interpret events for composition.
     *
     * @return <code>true</code> if the current input method is enbbled for
     * composition; <code>fblse</code> otherwise
     * @throws UnsupportedOperbtionException if there is no current input
     * method bvbilbble or the current input method does not support
     * checking whether it is enbbled for composition
     * @see #setCompositionEnbbled
     * @since 1.3
     */
    @Trbnsient
    public boolebn isCompositionEnbbled() {
        // rebl implementbtion is in sun.bwt.im.InputContext
        return fblse;
    }

    /**
     * Asks the current input method to reconvert text from the
     * current client component. The input method obtbins the text to
     * be reconverted from the client component using the
     * {@link InputMethodRequests#getSelectedText InputMethodRequests.getSelectedText}
     * method. The other <code>InputMethodRequests</code> methods
     * must be prepbred to debl with further informbtion requests by
     * the input method. The composed bnd/or committed text will be
     * sent to the client component bs b sequence of
     * <code>InputMethodEvent</code>s. If the input method cbnnot
     * reconvert the given text, the text is returned bs committed
     * text in bn <code>InputMethodEvent</code>.
     *
     * @throws UnsupportedOperbtionException if there is no current input
     * method bvbilbble or the current input method does not support
     * the reconversion operbtion.
     *
     * @since 1.3
     */
    public void reconvert() {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Dispbtches bn event to the bctive input method. Cblled by AWT.
     * If no input method is bvbilbble, then the event will never be consumed.
     *
     * @pbrbm event The event
     * @exception NullPointerException if <code>event</code> is null
     */
    public void dispbtchEvent(AWTEvent event) {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Notifies the input context thbt b client component hbs been
     * removed from its contbinment hierbrchy, or thbt input method
     * support hbs been disbbled for the component. This method is
     * usublly cblled from the client component's
     * {@link jbvb.bwt.Component#removeNotify() Component.removeNotify}
     * method. Potentiblly pending input from input methods
     * for this component is discbrded.
     * If no input methods bre bvbilbble, then this method hbs no effect.
     *
     * @pbrbm client Client component
     * @exception NullPointerException if <code>client</code> is null
     */
    public void removeNotify(Component client) {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Ends bny input composition thbt mby currently be going on in this
     * context. Depending on the plbtform bnd possibly user preferences,
     * this mby commit or delete uncommitted text. Any chbnges to the text
     * bre communicbted to the bctive component using bn input method event.
     * If no input methods bre bvbilbble, then this method hbs no effect.
     *
     * <p>
     * A text editing component mby cbll this in b vbriety of situbtions,
     * for exbmple, when the user moves the insertion point within the text
     * (but outside the composed text), or when the component's text is
     * sbved to b file or copied to the clipbobrd.
     *
     */
    public void endComposition() {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Relebses the resources used by this input context.
     * Cblled by AWT for the defbult input context of ebch Window.
     * If no input methods bre bvbilbble, then this method
     * hbs no effect.
     */
    public void dispose() {
        // rebl implementbtion is in sun.bwt.im.InputContext
    }

    /**
     * Returns b control object from the current input method, or null. A
     * control object provides methods thbt control the behbvior of the
     * input method or obtbin informbtion from the input method. The type
     * of the object is bn input method specific clbss. Clients hbve to
     * compbre the result bgbinst known input method control object
     * clbsses bnd cbst to the bppropribte clbss to invoke the methods
     * provided.
     * <p>
     * If no input methods bre bvbilbble or the current input method does
     * not provide bn input method control object, then null is returned.
     *
     * @return A control object from the current input method, or null.
     */
    public Object getInputMethodControlObject() {
        // rebl implementbtion is in sun.bwt.im.InputContext
        return null;
    }

}
