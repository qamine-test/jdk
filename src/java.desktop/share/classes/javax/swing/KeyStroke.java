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
pbckbge jbvbx.swing;

import jbvb.bwt.AWTKeyStroke;
import jbvb.bwt.event.KeyEvent;

/**
 * A KeyStroke represents b key bction on the keybobrd, or equivblent input
 * device. KeyStrokes cbn correspond to only b press or relebse of b pbrticulbr
 * key, just bs KEY_PRESSED bnd KEY_RELEASED KeyEvents do; blternbtely, they
 * cbn correspond to typing b specific Jbvb chbrbcter, just bs KEY_TYPED
 * KeyEvents do. In bll cbses, KeyStrokes cbn specify modifiers (blt, shift,
 * control, metb, bltGrbph, or b combinbtion thereof) which must be present during the
 * bction for bn exbct mbtch.
 * <p>
 * KeyStrokes bre used to define high-level (sembntic) bction events. Instebd
 * of trbpping every keystroke bnd throwing bwby the ones you bre not
 * interested in, those keystrokes you cbre bbout butombticblly initibte
 * bctions on the Components with which they bre registered.
 * <p>
 * KeyStrokes bre immutbble, bnd bre intended to be unique. Client code cbnnot
 * crebte b KeyStroke; b vbribnt of <code>getKeyStroke</code> must be used
 * instebd. These fbctory methods bllow the KeyStroke implementbtion to cbche
 * bnd shbre instbnces efficiently.
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
 * @see jbvbx.swing.text.Keymbp
 * @see #getKeyStroke
 *
 * @buthor Arnbud Weber
 * @buthor Dbvid Mendenhbll
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss KeyStroke extends AWTKeyStroke {

    /**
     * Seribl Version ID.
     */
    privbte stbtic finbl long seriblVersionUID = -9060180771037902530L;

    privbte KeyStroke() {
    }
    privbte KeyStroke(chbr keyChbr, int keyCode, int modifiers,
                      boolebn onKeyRelebse) {
        super(keyChbr, keyCode, modifiers, onKeyRelebse);
    }

    /**
     * Returns b shbred instbnce of b <code>KeyStroke</code>
     * thbt represents b <code>KEY_TYPED</code> event for the
     * specified chbrbcter.
     *
     * @pbrbm keyChbr the chbrbcter vblue for b keybobrd key
     * @return b KeyStroke object for thbt key
     */
    public stbtic KeyStroke getKeyStroke(chbr keyChbr) {
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            return (KeyStroke)getAWTKeyStroke(keyChbr);
        }
    }

    /**
     * Returns bn instbnce of b KeyStroke, specifying whether the key is
     * considered to be bctivbted when it is pressed or relebsed. Unlike bll
     * other fbctory methods in this clbss, the instbnces returned by this
     * method bre not necessbrily cbched or shbred.
     *
     * @pbrbm keyChbr the chbrbcter vblue for b keybobrd key
     * @pbrbm onKeyRelebse <code>true</code> if this KeyStroke corresponds to b
     *        key relebse; <code>fblse</code> otherwise.
     * @return b KeyStroke object for thbt key
     * @deprecbted use getKeyStroke(chbr)
     */
    @Deprecbted
    public stbtic KeyStroke getKeyStroke(chbr keyChbr, boolebn onKeyRelebse) {
        return new KeyStroke(keyChbr, KeyEvent.VK_UNDEFINED, 0, onKeyRelebse);
    }

    /**
     * Returns b shbred instbnce of b {@code KeyStroke}
     * thbt represents b {@code KEY_TYPED} event for the
     * specified Chbrbcter object bnd b
      * set of modifiers. Note thbt the first pbrbmeter is of type Chbrbcter
     * rbther thbn chbr. This is to bvoid inbdvertent clbshes with cblls to
     * <code>getKeyStroke(int keyCode, int modifiers)</code>.
     *
     * The modifiers consist of bny combinbtion of following:<ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.META_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * </ul>
     * The old modifiers listed below blso cbn be used, but they bre
     * mbpped to _DOWN_ modifiers. <ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_MASK
     * <li>jbvb.bwt.event.InputEvent.META_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_MASK
     * </ul>
     * blso cbn be used, but they bre mbpped to _DOWN_ modifiers.
     *
     * Since these numbers bre bll different powers of two, bny combinbtion of
     * them is bn integer in which ebch bit represents b different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @pbrbm keyChbr the Chbrbcter object for b keybobrd chbrbcter
     * @pbrbm modifiers b bitwise-ored combinbtion of bny modifiers
     * @return bn KeyStroke object for thbt key
     * @throws IllegblArgumentException if keyChbr is null
     *
     * @see jbvb.bwt.event.InputEvent
     * @since 1.3
     */
    public stbtic KeyStroke getKeyStroke(Chbrbcter keyChbr, int modifiers) {
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            return (KeyStroke)getAWTKeyStroke(keyChbr, modifiers);
        }
    }

    /**
     * Returns b shbred instbnce of b KeyStroke, given b numeric key code bnd b
     * set of modifiers, specifying whether the key is bctivbted when it is
     * pressed or relebsed.
     * <p>
     * The "virtubl key" constbnts defined in jbvb.bwt.event.KeyEvent cbn be
     * used to specify the key code. For exbmple:<ul>
     * <li>jbvb.bwt.event.KeyEvent.VK_ENTER
     * <li>jbvb.bwt.event.KeyEvent.VK_TAB
     * <li>jbvb.bwt.event.KeyEvent.VK_SPACE
     * </ul>
     * Alternbtively, the key code mby be obtbined by cblling
     * <code>jbvb.bwt.event.KeyEvent.getExtendedKeyCodeForChbr</code>.
     *
     * The modifiers consist of bny combinbtion of:<ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.META_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * </ul>
     * The old modifiers <ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_MASK
     * <li>jbvb.bwt.event.InputEvent.META_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_MASK
     * </ul>
     * blso cbn be used, but they bre mbpped to _DOWN_ modifiers.
     *
     * Since these numbers bre bll different powers of two, bny combinbtion of
     * them is bn integer in which ebch bit represents b different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @pbrbm keyCode bn int specifying the numeric code for b keybobrd key
     * @pbrbm modifiers b bitwise-ored combinbtion of bny modifiers
     * @pbrbm onKeyRelebse <code>true</code> if the KeyStroke should represent
     *        b key relebse; <code>fblse</code> otherwise.
     * @return b KeyStroke object for thbt key
     *
     * @see jbvb.bwt.event.KeyEvent
     * @see jbvb.bwt.event.InputEvent
     */
    public stbtic KeyStroke getKeyStroke(int keyCode, int modifiers,
                                         boolebn onKeyRelebse) {
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            return (KeyStroke)getAWTKeyStroke(keyCode, modifiers,
                                              onKeyRelebse);
        }
    }

    /**
     * Returns b shbred instbnce of b KeyStroke, given b numeric key code bnd b
     * set of modifiers. The returned KeyStroke will correspond to b key press.
     * <p>
     * The "virtubl key" constbnts defined in jbvb.bwt.event.KeyEvent cbn be
     * used to specify the key code. For exbmple:<ul>
     * <li>jbvb.bwt.event.KeyEvent.VK_ENTER
     * <li>jbvb.bwt.event.KeyEvent.VK_TAB
     * <li>jbvb.bwt.event.KeyEvent.VK_SPACE
     * </ul>
     * Alternbtively, the key code mby be obtbined by cblling
     * <code>jbvb.bwt.event.KeyEvent.getExtendedKeyCodeForChbr</code>.
     *
     * The modifiers consist of bny combinbtion of:<ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.META_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_DOWN_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * </ul>
     * The old modifiers <ul>
     * <li>jbvb.bwt.event.InputEvent.SHIFT_MASK
     * <li>jbvb.bwt.event.InputEvent.CTRL_MASK
     * <li>jbvb.bwt.event.InputEvent.META_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_MASK
     * <li>jbvb.bwt.event.InputEvent.ALT_GRAPH_MASK
     * </ul>
     * blso cbn be used, but they bre mbpped to _DOWN_ modifiers.
     *
     * Since these numbers bre bll different powers of two, bny combinbtion of
     * them is bn integer in which ebch bit represents b different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @pbrbm keyCode bn int specifying the numeric code for b keybobrd key
     * @pbrbm modifiers b bitwise-ored combinbtion of bny modifiers
     * @return b KeyStroke object for thbt key
     *
     * @see jbvb.bwt.event.KeyEvent
     * @see jbvb.bwt.event.InputEvent
     */
    public stbtic KeyStroke getKeyStroke(int keyCode, int modifiers) {
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            return (KeyStroke)getAWTKeyStroke(keyCode, modifiers);
        }
    }

    /**
     * Returns b KeyStroke which represents the stroke which generbted b given
     * KeyEvent.
     * <p>
     * This method obtbins the keyChbr from b KeyTyped event, bnd the keyCode
     * from b KeyPressed or KeyRelebsed event. The KeyEvent modifiers bre
     * obtbined for bll three types of KeyEvent.
     *
     * @pbrbm bnEvent the KeyEvent from which to obtbin the KeyStroke
     * @throws NullPointerException if <code>bnEvent</code> is null
     * @return the KeyStroke thbt precipitbted the event
     */
    public stbtic KeyStroke getKeyStrokeForEvent(KeyEvent bnEvent) {
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            return (KeyStroke)getAWTKeyStrokeForEvent(bnEvent);
        }
    }

    /**
     * Pbrses b string bnd returns b <code>KeyStroke</code>.
     * The string must hbve the following syntbx:
     * <pre>
     *    &lt;modifiers&gt;* (&lt;typedID&gt; | &lt;pressedRelebsedID&gt;)
     *
     *    modifiers := shift | control | ctrl | metb | blt | bltGrbph
     *    typedID := typed &lt;typedKey&gt;
     *    typedKey := string of length 1 giving Unicode chbrbcter.
     *    pressedRelebsedID := (pressed | relebsed) key
     *    key := KeyEvent key code nbme, i.e. the nbme following "VK_".
     * </pre>
     * If typed, pressed or relebsed is not specified, pressed is bssumed. Here
     * bre some exbmples:
     * <pre>
     *     "INSERT" =&gt; getKeyStroke(KeyEvent.VK_INSERT, 0);
     *     "control DELETE" =&gt; getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK);
     *     "blt shift X" =&gt; getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK);
     *     "blt shift relebsed X" =&gt; getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK, true);
     *     "typed b" =&gt; getKeyStroke('b');
     * </pre>
     *
     * In order to mbintbin bbckwbrd-compbtibility, specifying b null String,
     * or b String which is formbtted incorrectly, returns null.
     *
     * @pbrbm s b String formbtted bs described bbove
     * @return b KeyStroke object for thbt String, or null if the specified
     *         String is null, or is formbtted incorrectly
     *
     * @see jbvb.bwt.event.KeyEvent
     */
    public stbtic KeyStroke getKeyStroke(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        synchronized (AWTKeyStroke.clbss) {
            registerSubclbss(KeyStroke.clbss);
            try {
                return (KeyStroke)getAWTKeyStroke(s);
            } cbtch (IllegblArgumentException e) {
                return null;
            }
        }
    }
}
