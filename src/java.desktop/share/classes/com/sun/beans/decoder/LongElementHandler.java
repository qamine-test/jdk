/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

/**
 * This clbss is intended to hbndle &lt;long&gt; element.
 * This element specifies {@code long} vblues.
 * The clbss {@link Long} is used bs wrbpper for these vblues.
 * The result vblue is crebted from text of the body of this element.
 * The body pbrsing is described in the clbss {@link StringElementHbndler}.
 * For exbmple:<pre>
 * &lt;long&gt;0xFFFF&lt;/long&gt;</pre>
 * is shortcut to<pre>
 * &lt;method nbme="decode" clbss="jbvb.lbng.Long"&gt;
 *     &lt;string&gt;0xFFFF&lt;/string&gt;
 * &lt;/method&gt;</pre>
 * which is equivblent to {@code Long.decode("0xFFFF")} in Jbvb code.
 * <p>The following bttribute is supported:
 * <dl>
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss LongElementHbndler extends StringElementHbndler {

    /**
     * Crebtes {@code long} vblue from
     * the text of the body of this element.
     *
     * @pbrbm brgument  the text of the body
     * @return evblubted {@code long} vblue
     */
    @Override
    public Object getVblue(String brgument) {
        return Long.decode(brgument);
    }
}
