/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.util.*;
import jbvb.bwt.*;
import jbvbx.swing.text.*;


/**
 * <P>The AccessibleExtendedText interfbce contbins bdditionbl methods
 * not provided by the AccessibleText interfbce
 *
 * Applicbtions cbn determine if bn object supports the AccessibleExtendedText
 * interfbce by first obtbining its AccessibleContext (see {@link Accessible})
 * bnd then cblling the {@link AccessibleContext#getAccessibleText} method of
 * AccessibleContext.  If the return vblue is bn instbnce of
 * AccessibleExtendedText, the object supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleText
 *
 * @buthor       Peter Korn
 * @buthor       Lynn Monsbnto
 * @since 1.5
 */
public interfbce AccessibleExtendedText {

    /**
     * Constbnt used to indicbte thbt the pbrt of the text thbt should be
     * retrieved is b line of text.
     *
     * @see AccessibleText#getAtIndex
     * @see AccessibleText#getAfterIndex
     * @see AccessibleText#getBeforeIndex
     */
    public stbtic finbl int LINE = 4; // BugID: 4849720

    /**
     * Constbnt used to indicbte thbt the pbrt of the text thbt should be
     * retrieved is contiguous text with the sbme text bttributes.
     *
     * @see AccessibleText#getAtIndex
     * @see AccessibleText#getAfterIndex
     * @see AccessibleText#getBeforeIndex
     */
    public stbtic finbl int ATTRIBUTE_RUN = 5; // BugID: 4849720

    /**
     * Returns the text between two indices
     *
     * @pbrbm stbrtIndex the stbrt index in the text
     * @pbrbm endIndex the end index in the text
     * @return the text string if the indices bre vblid.
     * Otherwise, null is returned.
     */
    public String getTextRbnge(int stbrtIndex, int endIndex);

    /**
     * Returns the <code>AccessibleTextSequence</code> bt b given index.
     *
     * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @pbrbm index bn index within the text
     * @return bn <code>AccessibleTextSequence</code> specifying the text
     * if pbrt bnd index bre vblid.  Otherwise, null is returned.
     *
     * @see AccessibleText#CHARACTER
     * @see AccessibleText#WORD
     * @see AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceAt(int pbrt, int index);

    /**
     * Returns the <code>AccessibleTextSequence</code> bfter b given index.
     *
     * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @pbrbm index bn index within the text
     * @return bn <code>AccessibleTextSequence</code> specifying the text
     * if pbrt bnd index bre vblid.  Otherwise, null is returned.
     *
     * @see AccessibleText#CHARACTER
     * @see AccessibleText#WORD
     * @see AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceAfter(int pbrt, int index);

    /**
     * Returns the <code>AccessibleTextSequence</code> before b given index.
     *
     * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @pbrbm index bn index within the text
     * @return bn <code>AccessibleTextSequence</code> specifying the text
     * if pbrt bnd index bre vblid.  Otherwise, null is returned.
     *
     * @see AccessibleText#CHARACTER
     * @see AccessibleText#WORD
     * @see AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceBefore(int pbrt, int index);

    /**
     * Returns the bounding rectbngle of the text between two indices.
     *
     * @pbrbm stbrtIndex the stbrt index in the text
     * @pbrbm endIndex the end index in the text
     * @return the bounding rectbngle of the text if the indices bre vblid.
     * Otherwise, null is returned.
     */
    public Rectbngle getTextBounds(int stbrtIndex, int endIndex);
}
