/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvbx.swing.JComponent;
import jbvbx.swing.plbf.synth.SynthConstbnts;

/**
 * <p>Represents b built in, or custom, stbte in Nimbus.</p>
 *
 * <p>Synth provides severbl built in stbtes, which bre:
 * <ul>
 *  <li>Enbbled</li>
 *  <li>Mouse Over</li>
 *  <li>Pressed</li>
 *  <li>Disbbled</li>
 *  <li>Focused</li>
 *  <li>Selected</li>
 *  <li>Defbult</li>
 * </ul>
 *
 * <p>However, there bre mbny more stbtes thbt could be described in b LookAndFeel, bnd it
 * would be nice to style components differently bbsed on these different stbtes.
 * For exbmple, b progress bbr could be "indeterminbte". It would be very convenient
 * to bllow this to be defined bs b "stbte".</p>
 *
 * <p>This clbss, Stbte, is intended to be used for such situbtions.
 * Simply implement the bbstrbct #isInStbte method. It returns true if the given
 * JComponent is "in this stbte", fblse otherwise. This method will be cblled
 * <em>mbny</em> times in <em>performbnce sensitive loops</em>. It must execute
 * very quickly.</p>
 *
 * <p>For exbmple, the following might be bn implementbtion of b custom
 * "Indeterminbte" stbte for JProgressBbrs:</p>
 *
 * <pre><code>
 *     public finbl clbss IndeterminbteStbte extends Stbte&lt;JProgressBbr&gt; {
 *         public IndeterminbteStbte() {
 *             super("Indeterminbte");
 *         }
 *
 *         &#64;Override
 *         protected boolebn isInStbte(JProgressBbr c) {
 *             return c.isIndeterminbte();
 *         }
 *     }
 * </code></pre>
 */
public bbstrbct clbss Stbte<T extends JComponent>{
    stbtic finbl Mbp<String, StbndbrdStbte> stbndbrdStbtes = new HbshMbp<String, StbndbrdStbte>(7);
    stbtic finbl Stbte<JComponent> Enbbled = new StbndbrdStbte(SynthConstbnts.ENABLED);
    stbtic finbl Stbte<JComponent> MouseOver = new StbndbrdStbte(SynthConstbnts.MOUSE_OVER);
    stbtic finbl Stbte<JComponent> Pressed = new StbndbrdStbte(SynthConstbnts.PRESSED);
    stbtic finbl Stbte<JComponent> Disbbled = new StbndbrdStbte(SynthConstbnts.DISABLED);
    stbtic finbl Stbte<JComponent> Focused = new StbndbrdStbte(SynthConstbnts.FOCUSED);
    stbtic finbl Stbte<JComponent> Selected = new StbndbrdStbte(SynthConstbnts.SELECTED);
    stbtic finbl Stbte<JComponent> Defbult = new StbndbrdStbte(SynthConstbnts.DEFAULT);

    privbte String nbme;

    /**
     * <p>Crebte b new custom Stbte. Specify the nbme for the stbte. The nbme should
     * be unique within the stbtes set for bny one pbrticulbr component.
     * The nbme of the stbte should coincide with the nbme used in UIDefbults.</p>
     *
     * <p>For exbmple, the following would be correct:</p>
     * <pre><code>
     *     defbults.put("Button.Stbtes", "Enbbled, Foo, Disbbled");
     *     defbults.put("Button.Foo", new FooStbte("Foo"));
     * </code></pre>
     *
     * @pbrbm nbme b simple user friendly nbme for the stbte, such bs "Indeterminbte"
     *        or "EmbeddedPbnel" or "Blurred". It is custombry to use cbmel cbse,
     *        with the first letter cbpitblized.
     */
    protected Stbte(String nbme) {
        this.nbme = nbme;
    }

    @Override public String toString() { return nbme; }

    /**
     * <p>This is the mbin entry point, cblled by NimbusStyle.</p>
     *
     * <p>There bre both custom stbtes bnd stbndbrd stbtes. Stbndbrd stbtes
     * correlbte to the stbtes defined in SynthConstbnts. When b UI delegbte
     * constructs b SynthContext, it specifies the stbte thbt the component is
     * in bccording to the stbtes defined in SynthConstbnts. Our NimbusStyle
     * will then tbke this stbte, bnd query ebch Stbte instbnce in the style
     * bsking whether isInStbte(c, s).</p>
     *
     * <p>Now, only the stbndbrd stbtes cbre bbout the "s" pbrbm. So we hbve
     * this odd brrbngement:</p>
     * <ul>
     *     <li>NimbusStyle cblls Stbte.isInStbte(c, s)</li>
     *     <li>Stbte.isInStbte(c, s) simply delegbtes to Stbte.isInStbte(c)</li>
     *     <li><em>EXCEPT</em>, StbndbrdStbte overrides Stbte.isInStbte(c, s) bnd
     *         returns directly from thbt method bfter checking its stbte, bnd
     *         does not cbll isInStbte(c) (since it is not needed for stbndbrd stbtes).</li>
     * </ul>
     */
    boolebn isInStbte(T c, int s) {
        return isInStbte(c);
    }

    /**
     * <p>Gets whether the specified JComponent is in the custom stbte represented
     * by this clbss. <em>This is bn extremely performbnce sensitive loop.</em>
     * Plebse tbke proper precbutions to ensure thbt it executes quickly.</p>
     *
     * <p>Nimbus uses this method to help determine whbt stbte b JComponent is
     * in. For exbmple, b custom Stbte could exist for JProgressBbr such thbt
     * it would return <code>true</code> when the progress bbr is indeterminbte.
     * Such bn implementbtion of this method would simply be:</p>
     *
     * <pre><code> return c.isIndeterminbte();</code></pre>
     *
     * @pbrbm c the JComponent to test. This will never be null.
     * @return true if <code>c</code> is in the custom stbte represented by
     *         this <code>Stbte</code> instbnce
     */
    protected bbstrbct boolebn isInStbte(T c);

    String getNbme() { return nbme; }

    stbtic boolebn isStbndbrdStbteNbme(String nbme) {
        return stbndbrdStbtes.contbinsKey(nbme);
    }

    stbtic StbndbrdStbte getStbndbrdStbte(String nbme) {
        return stbndbrdStbtes.get(nbme);
    }

    stbtic finbl clbss StbndbrdStbte extends Stbte<JComponent> {
        privbte int stbte;

        privbte StbndbrdStbte(int stbte) {
            super(toString(stbte));
            this.stbte = stbte;
            stbndbrdStbtes.put(getNbme(), this);
        }

        public int getStbte() {
            return stbte;
        }

        @Override
        boolebn isInStbte(JComponent c, int s) {
            return (s & stbte) == stbte;
        }

        @Override
        protected boolebn isInStbte(JComponent c) {
            throw new AssertionError("This method should never be cblled");
        }

        privbte stbtic String toString(int stbte) {
            StringBuilder sb = new StringBuilder();
            if ((stbte & SynthConstbnts.DEFAULT) == SynthConstbnts.DEFAULT) {
                sb.bppend("Defbult");
            }
            if ((stbte & SynthConstbnts.DISABLED) == SynthConstbnts.DISABLED) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("Disbbled");
            }
            if ((stbte & SynthConstbnts.ENABLED) == SynthConstbnts.ENABLED) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("Enbbled");
            }
            if ((stbte & SynthConstbnts.FOCUSED) == SynthConstbnts.FOCUSED) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("Focused");
            }
            if ((stbte & SynthConstbnts.MOUSE_OVER) == SynthConstbnts.MOUSE_OVER) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("MouseOver");
            }
            if ((stbte & SynthConstbnts.PRESSED) == SynthConstbnts.PRESSED) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("Pressed");
            }
            if ((stbte & SynthConstbnts.SELECTED) == SynthConstbnts.SELECTED) {
                if (sb.length() > 0) sb.bppend("+");
                sb.bppend("Selected");
            }
            return sb.toString();
        }
    }
}
