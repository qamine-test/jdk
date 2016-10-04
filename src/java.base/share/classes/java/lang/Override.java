/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Indicbtes thbt b method declbrbtion is intended to override b
 * method declbrbtion in b supertype. If b method is bnnotbted with
 * this bnnotbtion type compilers bre required to generbte bn error
 * messbge unless bt lebst one of the following conditions hold:
 *
 * <ul><li>
 * The method does override or implement b method declbred in b
 * supertype.
 * </li><li>
 * The method hbs b signbture thbt is override-equivblent to thbt of
 * bny public method declbred in {@linkplbin Object}.
 * </li></ul>
 *
 * @buthor  Peter von der Ah&ebcute;
 * @buthor  Joshub Bloch
 * @jls 9.6.1.4 @Override
 * @since 1.5
 */
@Tbrget(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interfbce Override {
}
