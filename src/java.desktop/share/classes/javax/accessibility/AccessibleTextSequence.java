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
pbckbge jbvbx.bccessibility;


/**
 * <P>The AccessibleTextSequence provides informbtion bbout
 * b contiguous sequence of text.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleText
 * @see AccessibleAttributeSequence
 *
 * @buthor       Lynn Monsbnto
 */

/**
 * This clbss collects together key detbils of b spbn of text.  It
 * is used by implementors of the clbss <code>AccessibleExtendedText</code> in
 * order to return the requested triplet of b <code>String</code>, bnd the
 * stbrt bnd end indicies/offsets into b lbrger body of text thbt the
 * <code>String</code> comes from.
 *
 * @see jbvbx.bccessibility.AccessibleExtendedText
 */
public clbss AccessibleTextSequence {

    /** The stbrt index of the text sequence */
    public int stbrtIndex;

    /** The end index of the text sequence */
    public int endIndex;

    /** The text */
    public String text;

    /**
     * Constructs bn <code>AccessibleTextSequence</code> with the given
     * pbrbmeters.
     *
     * @pbrbm stbrt the beginning index of the spbn of text
     * @pbrbm end the ending index of the spbn of text
     * @pbrbm txt the <code>String</code> shbred by this text spbn
     *
     * @since 1.6
     */
    public AccessibleTextSequence(int stbrt, int end, String txt) {
        stbrtIndex = stbrt;
        endIndex = end;
        text = txt;
    }
};
