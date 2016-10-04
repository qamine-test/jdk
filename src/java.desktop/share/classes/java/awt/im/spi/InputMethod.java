/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.im.spi;

import jbvb.util.Locble;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.Rectbngle;
import jbvb.lbng.Chbrbcter.Subset;


/**
 * Defines the interfbce for bn input method thbt supports complex text input.
 * Input methods trbditionblly support text input for lbngubges thbt hbve
 * more chbrbcters thbn cbn be represented on b stbndbrd-size keybobrd,
 * such bs Chinese, Jbpbnese, bnd Korebn. However, they mby blso be used to
 * support phonetic text input for English or chbrbcter reordering for Thbi.
 * <p>
 * Subclbsses of InputMethod cbn be lobded by the input method frbmework; they
 * cbn then be selected either through the API
 * ({@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod})
 * or the user interfbce (the input method selection menu).
 *
 * @since 1.3
 *
 * @buthor JbvbSoft Internbtionbl
 */

public interfbce InputMethod {

    /**
     * Sets the input method context, which is used to dispbtch input method
     * events to the client component bnd to request informbtion from
     * the client component.
     * <p>
     * This method is cblled once immedibtely bfter instbntibting this input
     * method.
     *
     * @pbrbm context the input method context for this input method
     * @exception NullPointerException if <code>context</code> is null
     */
    public void setInputMethodContext(InputMethodContext context);

    /**
     * Attempts to set the input locble. If the input method supports the
     * desired locble, it chbnges its behbvior to support input for the locble
     * bnd returns true.
     * Otherwise, it returns fblse bnd does not chbnge its behbvior.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod},
     * <li>when switching to this input method through the user interfbce if the user
     *     specified b locble or if the previously selected input method's
     *     {@link jbvb.bwt.im.spi.InputMethod#getLocble getLocble} method
     *     returns b non-null vblue.
     * </ul>
     *
     * @pbrbm locble locble to input
     * @return whether the specified locble is supported
     * @exception NullPointerException if <code>locble</code> is null
     */
    public boolebn setLocble(Locble locble);

    /**
     * Returns the current input locble. Might return null in exceptionbl cbses.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#getLocble InputContext.getLocble} bnd
     * <li>when switching from this input method to b different one through the
     *     user interfbce.
     * </ul>
     *
     * @return the current input locble, or null
     */
    public Locble getLocble();

    /**
     * Sets the subsets of the Unicode chbrbcter set thbt this input method
     * is bllowed to input. Null mby be pbssed in to indicbte thbt bll
     * chbrbcters bre bllowed.
     * <p>
     * This method is cblled
     * <ul>
     * <li>immedibtely bfter instbntibting this input method,
     * <li>when switching to this input method from b different one, bnd
     * <li>by {@link jbvb.bwt.im.InputContext#setChbrbcterSubsets InputContext.setChbrbcterSubsets}.
     * </ul>
     *
     * @pbrbm subsets the subsets of the Unicode chbrbcter set from which
     * chbrbcters mby be input
     */
    public void setChbrbcterSubsets(Subset[] subsets);

    /**
     * Enbbles or disbbles this input method for composition,
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
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#setCompositionEnbbled InputContext.setCompositionEnbbled},
     * <li>when switching to this input method from b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod},
     *     if the previously selected input method's
     *     {@link jbvb.bwt.im.spi.InputMethod#isCompositionEnbbled isCompositionEnbbled}
     *     method returns without throwing bn exception.
     * </ul>
     *
     * @pbrbm enbble whether to enbble the input method for composition
     * @throws UnsupportedOperbtionException if this input method does not
     * support the enbbling/disbbling operbtion
     * @see #isCompositionEnbbled
     */
    public void setCompositionEnbbled(boolebn enbble);

    /**
     * Determines whether this input method is enbbled.
     * An input method thbt is enbbled for composition interprets incoming
     * events for both composition bnd control purposes, while b
     * disbbled input method does not interpret events for composition.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#isCompositionEnbbled InputContext.isCompositionEnbbled} bnd
     * <li>when switching from this input method to b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod}.
     * </ul>
     *
     * @return <code>true</code> if this input method is enbbled for
     * composition; <code>fblse</code> otherwise.
     * @throws UnsupportedOperbtionException if this input method does not
     * support checking whether it is enbbled for composition
     * @see #setCompositionEnbbled
     */
    public boolebn isCompositionEnbbled();

    /**
     * Stbrts the reconversion operbtion. The input method obtbins the
     * text to be reconverted from the current client component using the
     * {@link jbvb.bwt.im.InputMethodRequests#getSelectedText InputMethodRequests.getSelectedText}
     * method. It cbn use other <code>InputMethodRequests</code>
     * methods to request bdditionbl informbtion required for the
     * reconversion operbtion. The composed bnd committed text
     * produced by the operbtion is sent to the client component bs b
     * sequence of <code>InputMethodEvent</code>s. If the given text
     * cbnnot be reconverted, the sbme text should be sent to the
     * client component bs committed text.
     * <p>
     * This method is cblled by
     * {@link jbvb.bwt.im.InputContext#reconvert() InputContext.reconvert}.
     *
     * @throws UnsupportedOperbtionException if the input method does not
     * support the reconversion operbtion.
     */
    public void reconvert();

    /**
     * Dispbtches the event to the input method. If input method support is
     * enbbled for the focussed component, incoming events of certbin types
     * bre dispbtched to the current input method for this component before
     * they bre dispbtched to the component's methods or event listeners.
     * The input method decides whether it needs to hbndle the event. If it
     * does, it blso cblls the event's <code>consume</code> method; this
     * cbuses the event to not get dispbtched to the component's event
     * processing methods or event listeners.
     * <p>
     * Events bre dispbtched if they bre instbnces of InputEvent or its
     * subclbsses.
     * This includes instbnces of the AWT clbsses KeyEvent bnd MouseEvent.
     * <p>
     * This method is cblled by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}.
     *
     * @pbrbm event the event being dispbtched to the input method
     * @exception NullPointerException if <code>event</code> is null
     */
    public void dispbtchEvent(AWTEvent event);

    /**
     * Notifies this input method of chbnges in the client window
     * locbtion or stbte. This method is cblled while this input
     * method is the current input method of its input context bnd
     * notificbtions for it bre enbbled (see {@link
     * InputMethodContext#enbbleClientWindowNotificbtion
     * InputMethodContext.enbbleClientWindowNotificbtion}). Cblls
     * to this method bre temporbrily suspended if the input context's
     * {@link jbvb.bwt.im.InputContext#removeNotify removeNotify}
     * method is cblled, bnd resume when the input method is bctivbted
     * for b new client component. It is cblled in the following
     * situbtions:
     * <ul>
     * <li>
     * when the window contbining the current client component chbnges
     * in locbtion, size, visibility, iconificbtion stbte, or when the
     * window is closed.</li>
     * <li>
     * from <code> enbbleClientWindowNotificbtion(inputMethod,
     * true)</code> if the current client component exists,</li>
     * <li>
     * when bctivbting the input method for the first time bfter it
     * cblled
     * <code>enbbleClientWindowNotificbtion(inputMethod,
     * true)</code> if during the cbll no current client component wbs
     * bvbilbble,</li>
     * <li>
     * when bctivbting the input method for b new client component
     * bfter the input context's removeNotify method hbs been
     * cblled.</li>
     * </ul>
     * @pbrbm bounds client window's {@link
     * jbvb.bwt.Component#getBounds bounds} on the screen; or null if
     * the client window is iconified or invisible
     */
    public void notifyClientWindowChbnge(Rectbngle bounds);

    /**
     * Activbtes the input method for immedibte input processing.
     * <p>
     * If bn input method provides its own windows, it should mbke sure
     * bt this point thbt bll necessbry windows bre open bnd visible.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}
     *     when b client component receives b FOCUS_GAINED event,
     * <li>when switching to this input method from b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod}.
     * </ul>
     * The method is only cblled when the input method is inbctive.
     * A newly instbntibted input method is bssumed to be inbctive.
     */
    public void bctivbte();

    /**
     * Debctivbtes the input method.
     * The isTemporbry brgument hbs the sbme mebning bs in
     * {@link jbvb.bwt.event.FocusEvent#isTemporbry FocusEvent.isTemporbry}.
     * <p>
     * If bn input method provides its own windows, only windows thbt relbte
     * to the current composition (such bs b lookup choice window) should be
     * closed bt this point.
     * It is possible thbt the input method will be immedibtely bctivbted bgbin
     * for b different client component, bnd closing bnd reopening more
     * persistent windows (such bs b control pbnel) would crebte unnecessbry
     * screen flicker.
     * Before bn instbnce of b different input method clbss is bctivbted,
     * {@link #hideWindows} is cblled on the current input method.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}
     *     when b client component receives b FOCUS_LOST event,
     * <li>when switching from this input method to b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod},
     * <li>before {@link #removeNotify removeNotify} if the current client component is
     *     removed.
     * </ul>
     * The method is only cblled when the input method is bctive.
     *
     * @pbrbm isTemporbry whether the focus chbnge is temporbry
     */
    public void debctivbte(boolebn isTemporbry);

    /**
     * Closes or hides bll windows opened by this input method instbnce or
     * its clbss.
     * <p>
     * This method is cblled
     * <ul>
     * <li>before cblling {@link #bctivbte bctivbte} on bn instbnce of b different input
     *     method clbss,
     * <li>before cblling {@link #dispose dispose} on this input method.
     * </ul>
     * The method is only cblled when the input method is inbctive.
     */
    public void hideWindows();

    /**
     * Notifies the input method thbt b client component hbs been
     * removed from its contbinment hierbrchy, or thbt input method
     * support hbs been disbbled for the component.
     * <p>
     * This method is cblled by {@link jbvb.bwt.im.InputContext#removeNotify InputContext.removeNotify}.
     * <p>
     * The method is only cblled when the input method is inbctive.
     */
    public void removeNotify();

    /**
     * Ends bny input composition thbt mby currently be going on in this
     * context. Depending on the plbtform bnd possibly user preferences,
     * this mby commit or delete uncommitted text. Any chbnges to the text
     * bre communicbted to the bctive component using bn input method event.
     *
     * <p>
     * A text editing component mby cbll this in b vbriety of situbtions,
     * for exbmple, when the user moves the insertion point within the text
     * (but outside the composed text), or when the component's text is
     * sbved to b file or copied to the clipbobrd.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#endComposition InputContext.endComposition},
     * <li>by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}
     *     when switching to b different client component
     * <li>when switching from this input method to b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod}.
     * </ul>
     */
    public void endComposition();

    /**
     * Relebses the resources used by this input method.
     * In pbrticulbr, the input method should dispose windows bnd close files thbt bre no
     * longer needed.
     * <p>
     * This method is cblled by {@link jbvb.bwt.im.InputContext#dispose InputContext.dispose}.
     * <p>
     * The method is only cblled when the input method is inbctive.
     * No method of this interfbce is cblled on this instbnce bfter dispose.
     */
    public void dispose();

    /**
     * Returns b control object from this input method, or null. A
     * control object provides methods thbt control the behbvior of the
     * input method or obtbin informbtion from the input method. The type
     * of the object is bn input method specific clbss. Clients hbve to
     * compbre the result bgbinst known input method control object
     * clbsses bnd cbst to the bppropribte clbss to invoke the methods
     * provided.
     * <p>
     * This method is cblled by
     * {@link jbvb.bwt.im.InputContext#getInputMethodControlObject InputContext.getInputMethodControlObject}.
     *
     * @return b control object from this input method, or null
     */
    public Object getControlObject();

}
