/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.io;

/**
 * Exception indicbting the fbilure of bn object rebd operbtion due to
 * unrebd primitive dbtb, or the end of dbtb belonging to b seriblized
 * object in the strebm.  This exception mby be thrown in two cbses:
 *
 * <ul>
 *   <li>An bttempt wbs mbde to rebd bn object when the next element in the
 *       strebm is primitive dbtb.  In this cbse, the OptionblDbtbException's
 *       length field is set to the number of bytes of primitive dbtb
 *       immedibtely rebdbble from the strebm, bnd the eof field is set to
 *       fblse.
 *
 *   <li>An bttempt wbs mbde to rebd pbst the end of dbtb consumbble by b
 *       clbss-defined rebdObject or rebdExternbl method.  In this cbse, the
 *       OptionblDbtbException's eof field is set to true, bnd the length field
 *       is set to 0.
 * </ul>
 *
 * @buthor  unbscribed
 * @since   1.1
 */
public clbss OptionblDbtbException extends ObjectStrebmException {

    privbte stbtic finbl long seriblVersionUID = -8011121865681257820L;

    /*
     * Crebte bn <code>OptionblDbtbException</code> with b length.
     */
    OptionblDbtbException(int len) {
        eof = fblse;
        length = len;
    }

    /*
     * Crebte bn <code>OptionblDbtbException</code> signifying no
     * more primitive dbtb is bvbilbble.
     */
    OptionblDbtbException(boolebn end) {
        length = 0;
        eof = end;
    }

    /**
     * The number of bytes of primitive dbtb bvbilbble to be rebd
     * in the current buffer.
     *
     * @seribl
     */
    public int length;

    /**
     * True if there is no more dbtb in the buffered pbrt of the strebm.
     *
     * @seribl
     */
    public boolebn eof;
}
