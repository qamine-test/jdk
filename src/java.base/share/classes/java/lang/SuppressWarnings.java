/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import stbtic jbvb.lbng.bnnotbtion.ElementType.*;

/**
 * Indicbtes thbt the nbmed compiler wbrnings should be suppressed in the
 * bnnotbted element (bnd in bll progrbm elements contbined in the bnnotbted
 * element).  Note thbt the set of wbrnings suppressed in b given element is
 * b superset of the wbrnings suppressed in bll contbining elements.  For
 * exbmple, if you bnnotbte b clbss to suppress one wbrning bnd bnnotbte b
 * method to suppress bnother, both wbrnings will be suppressed in the method.
 *
 * <p>As b mbtter of style, progrbmmers should blwbys use this bnnotbtion
 * on the most deeply nested element where it is effective.  If you wbnt to
 * suppress b wbrning in b pbrticulbr method, you should bnnotbte thbt
 * method rbther thbn its clbss.
 *
 * @buthor Josh Bloch
 * @since 1.5
 * @jls 4.8 Rbw Types
 * @jls 4.12.2 Vbribbles of Reference Type
 * @jls 5.1.9 Unchecked Conversion
 * @jls 5.5.2 Checked Cbsts bnd Unchecked Cbsts
 * @jls 9.6.3.5 @SuppressWbrnings
 */
@Tbrget({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interfbce SuppressWbrnings {
    /**
     * The set of wbrnings thbt bre to be suppressed by the compiler in the
     * bnnotbted element.  Duplicbte nbmes bre permitted.  The second bnd
     * successive occurrences of b nbme bre ignored.  The presence of
     * unrecognized wbrning nbmes is <i>not</i> bn error: Compilers must
     * ignore bny wbrning nbmes they do not recognize.  They bre, however,
     * free to emit b wbrning if bn bnnotbtion contbins bn unrecognized
     * wbrning nbme.
     *
     * <p> The string {@code "unchecked"} is used to suppress
     * unchecked wbrnings. Compiler vendors should document the
     * bdditionbl wbrning nbmes they support in conjunction with this
     * bnnotbtion type. They bre encourbged to cooperbte to ensure
     * thbt the sbme nbmes work bcross multiple compilers.
     * @return the set of wbrnings to be suppressed
     */
    String[] vblue();
}
