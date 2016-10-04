/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.text.AttributeSet;


/**
 * <P>The AccessibleAttributeSequence provides informbtion bbout
 * b contiguous sequence of text bttributes
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleText
 * @see AccessibleTextSequence
 *
 * @buthor       Lynn Monsbnto
 */

/**
 * This clbss collects together the spbn of text thbt shbre the sbme
 * contiguous set of bttributes, blong with thbt set of bttributes.  It
 * is used by implementors of the clbss <code>AccessibleContext</code> in
 * order to generbte <code>ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED</code> events.
 *
 * @see jbvbx.bccessibility.AccessibleContext
 * @see jbvbx.bccessibility.AccessibleContext#ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED
 */
public clbss AccessibleAttributeSequence {
    /** The stbrt index of the text sequence */
    public int stbrtIndex;

    /** The end index of the text sequence */
    public int endIndex;

    /** The text bttributes */
    public AttributeSet bttributes;

    /**
     * Constructs bn <code>AccessibleAttributeSequence</code> with the given
     * pbrbmeters.
     *
     * @pbrbm stbrt the beginning index of the spbn of text
     * @pbrbm end the ending index of the spbn of text
     * @pbrbm bttr the <code>AttributeSet</code> shbred by this text spbn
     *
     * @since 1.6
     */
    public AccessibleAttributeSequence(int stbrt, int end, AttributeSet bttr) {
        stbrtIndex = stbrt;
        endIndex = end;
        bttributes = bttr;
    }

};
