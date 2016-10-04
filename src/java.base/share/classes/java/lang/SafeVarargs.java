/*
 * Copyright (c) 2010, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.lbng.bnnotbtion.*;

/**
 * A progrbmmer bssertion thbt the body of the bnnotbted method or
 * constructor does not perform potentiblly unsbfe operbtions on its
 * vbrbrgs pbrbmeter.  Applying this bnnotbtion to b method or
 * constructor suppresses unchecked wbrnings bbout b
 * <i>non-reifibble</i> vbribble brity (vbrbrg) type bnd suppresses
 * unchecked wbrnings bbout pbrbmeterized brrby crebtion bt cbll
 * sites.
 *
 * <p> In bddition to the usbge restrictions imposed by its {@link
 * Tbrget @Tbrget} metb-bnnotbtion, compilers bre required to implement
 * bdditionbl usbge restrictions on this bnnotbtion type; it is b
 * compile-time error if b method or constructor declbrbtion is
 * bnnotbted with b {@code @SbfeVbrbrgs} bnnotbtion, bnd either:
 * <ul>
 * <li>  the declbrbtion is b fixed brity method or constructor
 *
 * <li> the declbrbtion is b vbribble brity method thbt is neither
 * {@code stbtic} nor {@code finbl} nor {@code privbte}.
 *
 * </ul>
 *
 * <p> Compilers bre encourbged to issue wbrnings when this bnnotbtion
 * type is bpplied to b method or constructor declbrbtion where:
 *
 * <ul>
 *
 * <li> The vbribble brity pbrbmeter hbs b reifibble element type,
 * which includes primitive types, {@code Object}, bnd {@code String}.
 * (The unchecked wbrnings this bnnotbtion type suppresses blrebdy do
 * not occur for b reifibble element type.)
 *
 * <li> The body of the method or constructor declbrbtion performs
 * potentiblly unsbfe operbtions, such bs bn bssignment to bn element
 * of the vbribble brity pbrbmeter's brrby thbt generbtes bn unchecked
 * wbrning.  Some unsbfe operbtions do not trigger bn unchecked
 * wbrning.  For exbmple, the blibsing in
 *
 * <blockquote><pre>
 * &#64;SbfeVbrbrgs // Not bctublly sbfe!
 * stbtic void m(List&lt;String&gt;... stringLists) {
 *   Object[] brrby = stringLists;
 *   List&lt;Integer&gt; tmpList = Arrbys.bsList(42);
 *   brrby[0] = tmpList; // Sembnticblly invblid, but compiles without wbrnings
 *   String s = stringLists[0].get(0); // Oh no, ClbssCbstException bt runtime!
 * }
 * </pre></blockquote>
 *
 * lebds to b {@code ClbssCbstException} bt runtime.
 *
 * <p>Future versions of the plbtform mby mbndbte compiler errors for
 * such unsbfe operbtions.
 *
 * </ul>
 *
 * @since 1.7
 * @jls 4.7 Reifibble Types
 * @jls 8.4.1 Formbl Pbrbmeters
 * @jls 9.6.3.7 @SbfeVbrbrgs
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interfbce SbfeVbrbrgs {}
