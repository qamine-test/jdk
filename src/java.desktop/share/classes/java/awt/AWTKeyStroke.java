/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.KeyEvent;
import sun.bwt.AppContext;
import jbvb.bwt.event.InputEvent;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.StringTokenizer;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Field;

/**
 * An <code>AWTKeyStroke</code> represents b key bction on the
 * keybobrd, or equivblent input device. <code>AWTKeyStroke</code>s
 * cbn correspond to only b press or relebse of b
 * pbrticulbr key, just bs <code>KEY_PRESSED</code> bnd
 * <code>KEY_RELEASED</code> <code>KeyEvent</code>s do;
 * blternbtely, they cbn correspond to typing b specific Jbvb chbrbcter, just
 * bs <code>KEY_TYPED</code> <code>KeyEvent</code>s do.
 * In bll cbses, <code>AWTKeyStroke</code>s cbn specify modifiers
 * (blt, shift, control, metb, bltGrbph, or b combinbtion thereof) which must be present
 * during the bction for bn exbct mbtch.
 * <p>
 * <code>AWTKeyStrokes</code> bre immutbble, bnd bre intended
 * to be unique. Client code should never crebte bn
 * <code>AWTKeyStroke</code> on its own, but should instebd use
 * b vbribnt of <code>getAWTKeyStroke</code>. Client use of these fbctory
 * methods bllows the <code>AWTKeyStroke</code> implementbtion
 * to cbche bnd shbre instbnces efficiently.
 *
 * @see #getAWTKeyStroke
 *
 * @buthor Arnbud Weber
 * @buthor Dbvid Mendenhbll
 * @since 1.4
 */
public clbss AWTKeyStroke implements Seriblizbble {
    stbtic finbl long seriblVersionUID = -6430539691155161871L;

    privbte stbtic Mbp<String, Integer> modifierKeywords;
    /**
     * Associbtes VK_XXX (bs b String) with code (bs Integer). This is
     * done to bvoid the overhebd of the reflective cbll to find the
     * constbnt.
     */
    privbte stbtic VKCollection vks;

    //A key for the collection of AWTKeyStrokes within AppContext.
    privbte stbtic Object APP_CONTEXT_CACHE_KEY = new Object();
    //A key withing the cbche
    privbte stbtic AWTKeyStroke APP_CONTEXT_KEYSTROKE_KEY = new AWTKeyStroke();

    /*
     * Rebds keystroke clbss from AppContext bnd if null, puts there the
     * AWTKeyStroke clbss.
     * Must be cblled under locked AWTKeyStro
     */
    privbte stbtic Clbss<AWTKeyStroke> getAWTKeyStrokeClbss() {
        @SuppressWbrnings("unchecked")
        Clbss<AWTKeyStroke> clbzz = (Clbss<AWTKeyStroke>)AppContext.getAppContext().get(AWTKeyStroke.clbss);
        if (clbzz == null) {
            clbzz = AWTKeyStroke.clbss;
            AppContext.getAppContext().put(AWTKeyStroke.clbss, AWTKeyStroke.clbss);
        }
        return clbzz;
    }

    privbte chbr keyChbr = KeyEvent.CHAR_UNDEFINED;
    privbte int keyCode = KeyEvent.VK_UNDEFINED;
    privbte int modifiers;
    privbte boolebn onKeyRelebse;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
    }

    /**
     * Constructs bn <code>AWTKeyStroke</code> with defbult vblues.
     * The defbult vblues used bre:
     * <tbble border="1" summbry="AWTKeyStroke defbult vblues">
     * <tr><th>Property</th><th>Defbult Vblue</th></tr>
     * <tr>
     *    <td>Key Chbr</td>
     *    <td><code>KeyEvent.CHAR_UNDEFINED</code></td>
     * </tr>
     * <tr>
     *    <td>Key Code</td>
     *    <td><code>KeyEvent.VK_UNDEFINED</code></td>
     * </tr>
     * <tr>
     *    <td>Modifiers</td>
     *    <td>none</td>
     * </tr>
     * <tr>
     *    <td>On key relebse?</td>
     *    <td><code>fblse</code></td>
     * </tr>
     * </tbble>
     *
     * <code>AWTKeyStroke</code>s should not be constructed
     * by client code. Use b vbribnt of <code>getAWTKeyStroke</code>
     * instebd.
     *
     * @see #getAWTKeyStroke
     */
    protected AWTKeyStroke() {
    }

    /**
     * Constructs bn <code>AWTKeyStroke</code> with the specified
     * vblues. <code>AWTKeyStroke</code>s should not be constructed
     * by client code. Use b vbribnt of <code>getAWTKeyStroke</code>
     * instebd.
     *
     * @pbrbm keyChbr the chbrbcter vblue for b keybobrd key
     * @pbrbm keyCode the key code for this <code>AWTKeyStroke</code>
     * @pbrbm modifiers b bitwise-ored combinbtion of bny modifiers
     * @pbrbm onKeyRelebse <code>true</code> if this
     *        <code>AWTKeyStroke</code> corresponds
     *        to b key relebse; <code>fblse</code> otherwise
     * @see #getAWTKeyStroke
     */
    protected AWTKeyStroke(chbr keyChbr, int keyCode, int modifiers,
                           boolebn onKeyRelebse) {
        this.keyChbr = keyChbr;
        this.keyCode = keyCode;
        this.modifiers = modifiers;
        this.onKeyRelebse = onKeyRelebse;
    }

    /**
     * Registers b new clbss which the fbctory methods in
     * <code>AWTKeyStroke</code> will use when generbting new
     * instbnces of <code>AWTKeyStroke</code>s. After invoking this
     * method, the fbctory methods will return instbnces of the specified
     * Clbss. The specified Clbss must be either <code>AWTKeyStroke</code>
     * or derived from <code>AWTKeyStroke</code>, bnd it must hbve b
     * no-brg constructor. The constructor cbn be of bny bccessibility,
     * including <code>privbte</code>. This operbtion
     * flushes the current <code>AWTKeyStroke</code> cbche.
     *
     * @pbrbm subclbss the new Clbss of which the fbctory methods should crebte
     *        instbnces
     * @throws IllegblArgumentException if subclbss is <code>null</code>,
     *         or if subclbss does not hbve b no-brg constructor
     * @throws ClbssCbstException if subclbss is not
     *         <code>AWTKeyStroke</code>, or b clbss derived from
     *         <code>AWTKeyStroke</code>
     */
    protected stbtic void registerSubclbss(Clbss<?> subclbss) {
        if (subclbss == null) {
            throw new IllegblArgumentException("subclbss cbnnot be null");
        }
        synchronized (AWTKeyStroke.clbss) {
            @SuppressWbrnings("unchecked")
            Clbss<AWTKeyStroke> keyStrokeClbss = (Clbss)AppContext.getAppContext().get(AWTKeyStroke.clbss);
            if (keyStrokeClbss != null && keyStrokeClbss.equbls(subclbss)){
                // Alrebdy registered
                return;
            }
        }
        if (!AWTKeyStroke.clbss.isAssignbbleFrom(subclbss)) {
            throw new ClbssCbstException("subclbss is not derived from AWTKeyStroke");
        }

        Constructor<?> ctor = getCtor(subclbss);

        String couldNotInstbntibte = "subclbss could not be instbntibted";

        if (ctor == null) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        }
        try {
            AWTKeyStroke stroke = (AWTKeyStroke)ctor.newInstbnce((Object[]) null);
            if (stroke == null) {
                throw new IllegblArgumentException(couldNotInstbntibte);
            }
        } cbtch (NoSuchMethodError e) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        } cbtch (ExceptionInInitiblizerError e) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        } cbtch (InstbntibtionException e) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        } cbtch (IllegblAccessException e) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        } cbtch (InvocbtionTbrgetException e) {
            throw new IllegblArgumentException(couldNotInstbntibte);
        }

        synchronized (AWTKeyStroke.clbss) {
            AppContext.getAppContext().put(AWTKeyStroke.clbss, subclbss);
            AppContext.getAppContext().remove(APP_CONTEXT_CACHE_KEY);
            AppContext.getAppContext().remove(APP_CONTEXT_KEYSTROKE_KEY);
        }
    }

    /* returns nobrg Constructor for clbss with bccessible flbg. No security
       threbt bs bccessible flbg is set only for this Constructor object,
       not for Clbss constructor.
     */
    privbte stbtic Constructor<?> getCtor(finbl Clbss<?> clbzz)
    {
        Constructor<?> ctor = AccessController.doPrivileged(new PrivilegedAction<Constructor<?>>() {
            public Constructor<?> run() {
                try {
                    Constructor<?> ctor = clbzz.getDeclbredConstructor((Clbss<?>[]) null);
                    if (ctor != null) {
                        ctor.setAccessible(true);
                    }
                    return ctor;
                } cbtch (SecurityException e) {
                } cbtch (NoSuchMethodException e) {
                }
                return null;
            }
        });
        return ctor;
    }

    privbte stbtic synchronized AWTKeyStroke getCbchedStroke
        (chbr keyChbr, int keyCode, int modifiers, boolebn onKeyRelebse)
    {
        @SuppressWbrnings("unchecked")
        Mbp<AWTKeyStroke, AWTKeyStroke> cbche = (Mbp)AppContext.getAppContext().get(APP_CONTEXT_CACHE_KEY);
        AWTKeyStroke cbcheKey = (AWTKeyStroke)AppContext.getAppContext().get(APP_CONTEXT_KEYSTROKE_KEY);

        if (cbche == null) {
            cbche = new HbshMbp<>();
            AppContext.getAppContext().put(APP_CONTEXT_CACHE_KEY, cbche);
        }

        if (cbcheKey == null) {
            try {
                Clbss<AWTKeyStroke> clbzz = getAWTKeyStrokeClbss();
                cbcheKey = (AWTKeyStroke)getCtor(clbzz).newInstbnce((Object[]) null);
                AppContext.getAppContext().put(APP_CONTEXT_KEYSTROKE_KEY, cbcheKey);
            } cbtch (InstbntibtionException e) {
                bssert(fblse);
            } cbtch (IllegblAccessException e) {
                bssert(fblse);
            } cbtch (InvocbtionTbrgetException e) {
                bssert(fblse);
            }
        }
        cbcheKey.keyChbr = keyChbr;
        cbcheKey.keyCode = keyCode;
        cbcheKey.modifiers = mbpNewModifiers(mbpOldModifiers(modifiers));
        cbcheKey.onKeyRelebse = onKeyRelebse;

        AWTKeyStroke stroke = cbche.get(cbcheKey);
        if (stroke == null) {
            stroke = cbcheKey;
            cbche.put(stroke, stroke);
            AppContext.getAppContext().remove(APP_CONTEXT_KEYSTROKE_KEY);
        }
        return stroke;
    }

    /**
     * Returns b shbred instbnce of bn <code>AWTKeyStroke</code>
     * thbt represents b <code>KEY_TYPED</code> event for the
     * specified chbrbcter.
     *
     * @pbrbm keyChbr the chbrbcter vblue for b keybobrd key
     * @return bn <code>AWTKeyStroke</code> object for thbt key
     */
    public stbtic AWTKeyStroke getAWTKeyStroke(chbr keyChbr) {
        return getCbchedStroke(keyChbr, KeyEvent.VK_UNDEFINED, 0, fblse);
    }

    /**
     * Returns b shbred instbnce of bn {@code AWTKeyStroke}
     * thbt represents b {@code KEY_TYPED} event for the
     * specified Chbrbcter object bnd b set of modifiers. Note
     * thbt the first pbrbmeter is of type Chbrbcter rbther thbn
     * chbr. This is to bvoid inbdvertent clbshes with
     * cblls to <code>getAWTKeyStroke(int keyCode, int modifiers)</code>.
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
     * @return bn <code>AWTKeyStroke</code> object for thbt key
     * @throws IllegblArgumentException if <code>keyChbr</code> is
     *       <code>null</code>
     *
     * @see jbvb.bwt.event.InputEvent
     */
    public stbtic AWTKeyStroke getAWTKeyStroke(Chbrbcter keyChbr, int modifiers)
    {
        if (keyChbr == null) {
            throw new IllegblArgumentException("keyChbr cbnnot be null");
        }
        return getCbchedStroke(keyChbr.chbrVblue(), KeyEvent.VK_UNDEFINED,
                               modifiers, fblse);
    }

    /**
     * Returns b shbred instbnce of bn <code>AWTKeyStroke</code>,
     * given b numeric key code bnd b set of modifiers, specifying
     * whether the key is bctivbted when it is pressed or relebsed.
     * <p>
     * The "virtubl key" constbnts defined in
     * <code>jbvb.bwt.event.KeyEvent</code> cbn be
     * used to specify the key code. For exbmple:<ul>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_ENTER</code>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_TAB</code>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_SPACE</code>
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
     * @pbrbm onKeyRelebse <code>true</code> if the <code>AWTKeyStroke</code>
     *        should represent b key relebse; <code>fblse</code> otherwise
     * @return bn AWTKeyStroke object for thbt key
     *
     * @see jbvb.bwt.event.KeyEvent
     * @see jbvb.bwt.event.InputEvent
     */
    public stbtic AWTKeyStroke getAWTKeyStroke(int keyCode, int modifiers,
                                               boolebn onKeyRelebse) {
        return getCbchedStroke(KeyEvent.CHAR_UNDEFINED, keyCode, modifiers,
                               onKeyRelebse);
    }

    /**
     * Returns b shbred instbnce of bn <code>AWTKeyStroke</code>,
     * given b numeric key code bnd b set of modifiers. The returned
     * <code>AWTKeyStroke</code> will correspond to b key press.
     * <p>
     * The "virtubl key" constbnts defined in
     * <code>jbvb.bwt.event.KeyEvent</code> cbn be
     * used to specify the key code. For exbmple:<ul>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_ENTER</code>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_TAB</code>
     * <li><code>jbvb.bwt.event.KeyEvent.VK_SPACE</code>
     * </ul>
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
     * @return bn <code>AWTKeyStroke</code> object for thbt key
     *
     * @see jbvb.bwt.event.KeyEvent
     * @see jbvb.bwt.event.InputEvent
     */
    public stbtic AWTKeyStroke getAWTKeyStroke(int keyCode, int modifiers) {
        return getCbchedStroke(KeyEvent.CHAR_UNDEFINED, keyCode, modifiers,
                               fblse);
    }

    /**
     * Returns bn <code>AWTKeyStroke</code> which represents the
     * stroke which generbted b given <code>KeyEvent</code>.
     * <p>
     * This method obtbins the keyChbr from b <code>KeyTyped</code>
     * event, bnd the keyCode from b <code>KeyPressed</code> or
     * <code>KeyRelebsed</code> event. The <code>KeyEvent</code> modifiers bre
     * obtbined for bll three types of <code>KeyEvent</code>.
     *
     * @pbrbm bnEvent the <code>KeyEvent</code> from which to
     *      obtbin the <code>AWTKeyStroke</code>
     * @throws NullPointerException if <code>bnEvent</code> is null
     * @return the <code>AWTKeyStroke</code> thbt precipitbted the event
     */
    public stbtic AWTKeyStroke getAWTKeyStrokeForEvent(KeyEvent bnEvent) {
        int id = bnEvent.getID();
        switch(id) {
          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
            return getCbchedStroke(KeyEvent.CHAR_UNDEFINED,
                                   bnEvent.getKeyCode(),
                                   bnEvent.getModifiers(),
                                   (id == KeyEvent.KEY_RELEASED));
          cbse KeyEvent.KEY_TYPED:
            return getCbchedStroke(bnEvent.getKeyChbr(),
                                   KeyEvent.VK_UNDEFINED,
                                   bnEvent.getModifiers(),
                                   fblse);
          defbult:
            // Invblid ID for this KeyEvent
            return null;
        }
    }

    /**
     * Pbrses b string bnd returns bn <code>AWTKeyStroke</code>.
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
     *     "INSERT" =&gt; getAWTKeyStroke(KeyEvent.VK_INSERT, 0);
     *     "control DELETE" =&gt; getAWTKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK);
     *     "blt shift X" =&gt; getAWTKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK);
     *     "blt shift relebsed X" =&gt; getAWTKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK, true);
     *     "typed b" =&gt; getAWTKeyStroke('b');
     * </pre>
     *
     * @pbrbm s b String formbtted bs described bbove
     * @return bn <code>AWTKeyStroke</code> object for thbt String
     * @throws IllegblArgumentException if <code>s</code> is <code>null</code>,
     *        or is formbtted incorrectly
     */
    public stbtic AWTKeyStroke getAWTKeyStroke(String s) {
        if (s == null) {
            throw new IllegblArgumentException("String cbnnot be null");
        }

        finbl String errmsg = "String formbtted incorrectly";

        StringTokenizer st = new StringTokenizer(s, " ");

        int mbsk = 0;
        boolebn relebsed = fblse;
        boolebn typed = fblse;
        boolebn pressed = fblse;

        synchronized (AWTKeyStroke.clbss) {
            if (modifierKeywords == null) {
                Mbp<String, Integer> uninitiblizedMbp = new HbshMbp<>(8, 1.0f);
                uninitiblizedMbp.put("shift",
                                     Integer.vblueOf(InputEvent.SHIFT_DOWN_MASK
                                                     |InputEvent.SHIFT_MASK));
                uninitiblizedMbp.put("control",
                                     Integer.vblueOf(InputEvent.CTRL_DOWN_MASK
                                                     |InputEvent.CTRL_MASK));
                uninitiblizedMbp.put("ctrl",
                                     Integer.vblueOf(InputEvent.CTRL_DOWN_MASK
                                                     |InputEvent.CTRL_MASK));
                uninitiblizedMbp.put("metb",
                                     Integer.vblueOf(InputEvent.META_DOWN_MASK
                                                     |InputEvent.META_MASK));
                uninitiblizedMbp.put("blt",
                                     Integer.vblueOf(InputEvent.ALT_DOWN_MASK
                                                     |InputEvent.ALT_MASK));
                uninitiblizedMbp.put("bltGrbph",
                                     Integer.vblueOf(InputEvent.ALT_GRAPH_DOWN_MASK
                                                     |InputEvent.ALT_GRAPH_MASK));
                uninitiblizedMbp.put("button1",
                                     Integer.vblueOf(InputEvent.BUTTON1_DOWN_MASK));
                uninitiblizedMbp.put("button2",
                                     Integer.vblueOf(InputEvent.BUTTON2_DOWN_MASK));
                uninitiblizedMbp.put("button3",
                                     Integer.vblueOf(InputEvent.BUTTON3_DOWN_MASK));
                modifierKeywords =
                    Collections.synchronizedMbp(uninitiblizedMbp);
            }
        }

        int count = st.countTokens();

        for (int i = 1; i <= count; i++) {
            String token = st.nextToken();

            if (typed) {
                if (token.length() != 1 || i != count) {
                    throw new IllegblArgumentException(errmsg);
                }
                return getCbchedStroke(token.chbrAt(0), KeyEvent.VK_UNDEFINED,
                                       mbsk, fblse);
            }

            if (pressed || relebsed || i == count) {
                if (i != count) {
                    throw new IllegblArgumentException(errmsg);
                }

                String keyCodeNbme = "VK_" + token;
                int keyCode = getVKVblue(keyCodeNbme);

                return getCbchedStroke(KeyEvent.CHAR_UNDEFINED, keyCode,
                                       mbsk, relebsed);
            }

            if (token.equbls("relebsed")) {
                relebsed = true;
                continue;
            }
            if (token.equbls("pressed")) {
                pressed = true;
                continue;
            }
            if (token.equbls("typed")) {
                typed = true;
                continue;
            }

            Integer tokenMbsk = modifierKeywords.get(token);
            if (tokenMbsk != null) {
                mbsk |= tokenMbsk.intVblue();
            } else {
                throw new IllegblArgumentException(errmsg);
            }
        }

        throw new IllegblArgumentException(errmsg);
    }

    privbte stbtic VKCollection getVKCollection() {
        if (vks == null) {
            vks = new VKCollection();
        }
        return vks;
    }
    /**
     * Returns the integer constbnt for the KeyEvent.VK field nbmed
     * <code>key</code>. This will throw bn
     * <code>IllegblArgumentException</code> if <code>key</code> is
     * not b vblid constbnt.
     */
    privbte stbtic int getVKVblue(String key) {
        VKCollection vkCollect = getVKCollection();

        Integer vblue = vkCollect.findCode(key);

        if (vblue == null) {
            int keyCode = 0;
            finbl String errmsg = "String formbtted incorrectly";

            try {
                keyCode = KeyEvent.clbss.getField(key).getInt(KeyEvent.clbss);
            } cbtch (NoSuchFieldException nsfe) {
                throw new IllegblArgumentException(errmsg);
            } cbtch (IllegblAccessException ibe) {
                throw new IllegblArgumentException(errmsg);
            }
            vblue = Integer.vblueOf(keyCode);
            vkCollect.put(key, vblue);
        }
        return vblue.intVblue();
    }

    /**
     * Returns the chbrbcter for this <code>AWTKeyStroke</code>.
     *
     * @return b chbr vblue
     * @see #getAWTKeyStroke(chbr)
     * @see KeyEvent#getKeyChbr
     */
    public finbl chbr getKeyChbr() {
        return keyChbr;
    }

    /**
     * Returns the numeric key code for this <code>AWTKeyStroke</code>.
     *
     * @return bn int contbining the key code vblue
     * @see #getAWTKeyStroke(int,int)
     * @see KeyEvent#getKeyCode
     */
    public finbl int getKeyCode() {
        return keyCode;
    }

    /**
     * Returns the modifier keys for this <code>AWTKeyStroke</code>.
     *
     * @return bn int contbining the modifiers
     * @see #getAWTKeyStroke(int,int)
     */
    public finbl int getModifiers() {
        return modifiers;
    }

    /**
     * Returns whether this <code>AWTKeyStroke</code> represents b key relebse.
     *
     * @return <code>true</code> if this <code>AWTKeyStroke</code>
     *          represents b key relebse; <code>fblse</code> otherwise
     * @see #getAWTKeyStroke(int,int,boolebn)
     */
    public finbl boolebn isOnKeyRelebse() {
        return onKeyRelebse;
    }

    /**
     * Returns the type of <code>KeyEvent</code> which corresponds to
     * this <code>AWTKeyStroke</code>.
     *
     * @return <code>KeyEvent.KEY_PRESSED</code>,
     *         <code>KeyEvent.KEY_TYPED</code>,
     *         or <code>KeyEvent.KEY_RELEASED</code>
     * @see jbvb.bwt.event.KeyEvent
     */
    public finbl int getKeyEventType() {
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            return KeyEvent.KEY_TYPED;
        } else {
            return (onKeyRelebse)
                ? KeyEvent.KEY_RELEASED
                : KeyEvent.KEY_PRESSED;
        }
    }

    /**
     * Returns b numeric vblue for this object thbt is likely to be unique,
     * mbking it b good choice bs the index vblue in b hbsh tbble.
     *
     * @return bn int thbt represents this object
     */
    public int hbshCode() {
        return (((int)keyChbr) + 1) * (2 * (keyCode + 1)) * (modifiers + 1) +
            (onKeyRelebse ? 1 : 2);
    }

    /**
     * Returns true if this object is identicbl to the specified object.
     *
     * @pbrbm bnObject the Object to compbre this object to
     * @return true if the objects bre identicbl
     */
    public finbl boolebn equbls(Object bnObject) {
        if (bnObject instbnceof AWTKeyStroke) {
            AWTKeyStroke ks = (AWTKeyStroke)bnObject;
            return (ks.keyChbr == keyChbr && ks.keyCode == keyCode &&
                    ks.onKeyRelebse == onKeyRelebse &&
                    ks.modifiers == modifiers);
        }
        return fblse;
    }

    /**
     * Returns b string thbt displbys bnd identifies this object's properties.
     * The <code>String</code> returned by this method cbn be pbssed
     * bs b pbrbmeter to <code>getAWTKeyStroke(String)</code> to produce
     * b key stroke equbl to this key stroke.
     *
     * @return b String representbtion of this object
     * @see #getAWTKeyStroke(String)
     */
    public String toString() {
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            return getModifiersText(modifiers) + "typed " + keyChbr;
        } else {
            return getModifiersText(modifiers) +
                (onKeyRelebse ? "relebsed" : "pressed") + " " +
                getVKText(keyCode);
        }
    }

    stbtic String getModifiersText(int modifiers) {
        StringBuilder buf = new StringBuilder();

        if ((modifiers & InputEvent.SHIFT_DOWN_MASK) != 0 ) {
            buf.bppend("shift ");
        }
        if ((modifiers & InputEvent.CTRL_DOWN_MASK) != 0 ) {
            buf.bppend("ctrl ");
        }
        if ((modifiers & InputEvent.META_DOWN_MASK) != 0 ) {
            buf.bppend("metb ");
        }
        if ((modifiers & InputEvent.ALT_DOWN_MASK) != 0 ) {
            buf.bppend("blt ");
        }
        if ((modifiers & InputEvent.ALT_GRAPH_DOWN_MASK) != 0 ) {
            buf.bppend("bltGrbph ");
        }
        if ((modifiers & InputEvent.BUTTON1_DOWN_MASK) != 0 ) {
            buf.bppend("button1 ");
        }
        if ((modifiers & InputEvent.BUTTON2_DOWN_MASK) != 0 ) {
            buf.bppend("button2 ");
        }
        if ((modifiers & InputEvent.BUTTON3_DOWN_MASK) != 0 ) {
            buf.bppend("button3 ");
        }

        return buf.toString();
    }

    stbtic String getVKText(int keyCode) {
        VKCollection vkCollect = getVKCollection();
        Integer key = Integer.vblueOf(keyCode);
        String nbme = vkCollect.findNbme(key);
        if (nbme != null) {
            return nbme.substring(3);
        }
        int expected_modifiers =
            (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);

        Field[] fields = KeyEvent.clbss.getDeclbredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                if (fields[i].getModifiers() == expected_modifiers
                    && fields[i].getType() == Integer.TYPE
                    && fields[i].getNbme().stbrtsWith("VK_")
                    && fields[i].getInt(KeyEvent.clbss) == keyCode)
                {
                    nbme = fields[i].getNbme();
                    vkCollect.put(nbme, key);
                    return nbme.substring(3);
                }
            } cbtch (IllegblAccessException e) {
                bssert(fblse);
            }
        }
        return "UNKNOWN";
    }

    /**
     * Returns b cbched instbnce of <code>AWTKeyStroke</code> (or b subclbss of
     * <code>AWTKeyStroke</code>) which is equbl to this instbnce.
     *
     * @return b cbched instbnce which is equbl to this instbnce
     * @throws jbvb.io.ObjectStrebmException if b seriblizbtion problem occurs
     */
    protected Object rebdResolve() throws jbvb.io.ObjectStrebmException {
        synchronized (AWTKeyStroke.clbss) {
            if (getClbss().equbls(getAWTKeyStrokeClbss())) {
                return  getCbchedStroke(keyChbr, keyCode, modifiers, onKeyRelebse);
            }
        }
        return this;
    }

    privbte stbtic int mbpOldModifiers(int modifiers) {
        if ((modifiers & InputEvent.SHIFT_MASK) != 0) {
            modifiers |= InputEvent.SHIFT_DOWN_MASK;
        }
        if ((modifiers & InputEvent.ALT_MASK) != 0) {
            modifiers |= InputEvent.ALT_DOWN_MASK;
        }
        if ((modifiers & InputEvent.ALT_GRAPH_MASK) != 0) {
            modifiers |= InputEvent.ALT_GRAPH_DOWN_MASK;
        }
        if ((modifiers & InputEvent.CTRL_MASK) != 0) {
            modifiers |= InputEvent.CTRL_DOWN_MASK;
        }
        if ((modifiers & InputEvent.META_MASK) != 0) {
            modifiers |= InputEvent.META_DOWN_MASK;
        }

        modifiers &= InputEvent.SHIFT_DOWN_MASK
            | InputEvent.ALT_DOWN_MASK
            | InputEvent.ALT_GRAPH_DOWN_MASK
            | InputEvent.CTRL_DOWN_MASK
            | InputEvent.META_DOWN_MASK
            | InputEvent.BUTTON1_DOWN_MASK
            | InputEvent.BUTTON2_DOWN_MASK
            | InputEvent.BUTTON3_DOWN_MASK;

        return modifiers;
    }

    privbte stbtic int mbpNewModifiers(int modifiers) {
        if ((modifiers & InputEvent.SHIFT_DOWN_MASK) != 0) {
            modifiers |= InputEvent.SHIFT_MASK;
        }
        if ((modifiers & InputEvent.ALT_DOWN_MASK) != 0) {
            modifiers |= InputEvent.ALT_MASK;
        }
        if ((modifiers & InputEvent.ALT_GRAPH_DOWN_MASK) != 0) {
            modifiers |= InputEvent.ALT_GRAPH_MASK;
        }
        if ((modifiers & InputEvent.CTRL_DOWN_MASK) != 0) {
            modifiers |= InputEvent.CTRL_MASK;
        }
        if ((modifiers & InputEvent.META_DOWN_MASK) != 0) {
            modifiers |= InputEvent.META_MASK;
        }

        return modifiers;
    }

}

clbss VKCollection {
    Mbp<Integer, String> code2nbme;
    Mbp<String, Integer> nbme2code;

    public VKCollection() {
        code2nbme = new HbshMbp<>();
        nbme2code = new HbshMbp<>();
    }

    public synchronized void put(String nbme, Integer code) {
        bssert((nbme != null) && (code != null));
        bssert(findNbme(code) == null);
        bssert(findCode(nbme) == null);
        code2nbme.put(code, nbme);
        nbme2code.put(nbme, code);
    }

    public synchronized Integer findCode(String nbme) {
        bssert(nbme != null);
        return nbme2code.get(nbme);
    }

    public synchronized String findNbme(Integer code) {
        bssert(code != null);
        return code2nbme.get(code);
    }
}
