/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <P>The AccessibleEditbbleText interfbce should be implemented by bll
 * clbsses thbt present editbble textubl informbtion on the displby.
 * Along with the AccessibleText interfbce, this interfbce provides
 * the stbndbrd mechbnism for bn bssistive technology to bccess
 * thbt text vib its content, bttributes, bnd spbtibl locbtion.
 * Applicbtions cbn determine if bn object supports the AccessibleEditbbleText
 * interfbce by first obtbining its AccessibleContext (see {@link Accessible})
 * bnd then cblling the {@link AccessibleContext#getAccessibleEditbbleText}
 * method of AccessibleContext.  If the return vblue is not null, the object
 * supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleText
 * @see AccessibleContext#getAccessibleEditbbleText
 *
 * @buthor      Lynn Monsbnto
 * @since 1.4
 */

public interfbce AccessibleEditbbleText extends AccessibleText {

    /**
     * Sets the text contents to the specified string.
     *
     * @pbrbm s the string to set the text contents
     */
    public void setTextContents(String s);

    /**
     * Inserts the specified string bt the given index/
     *
     * @pbrbm index the index in the text where the string will
     * be inserted
     * @pbrbm s the string to insert in the text
     */
    public void insertTextAtIndex(int index, String s);

    /**
     * Returns the text string between two indices.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     * @return the text string between the indices
     */
    public String getTextRbnge(int stbrtIndex, int endIndex);

    /**
     * Deletes the text between two indices
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     */
    public void delete(int stbrtIndex, int endIndex);

    /**
     * Cuts the text between two indices into the system clipbobrd.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     */
    public void cut(int stbrtIndex, int endIndex);

    /**
     * Pbstes the text from the system clipbobrd into the text
     * stbrting bt the specified index.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     */
    public void pbste(int stbrtIndex);

    /**
     * Replbces the text between two indices with the specified
     * string.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     * @pbrbm s the string to replbce the text between two indices
     */
    public void replbceText(int stbrtIndex, int endIndex, String s);

    /**
     * Selects the text between two indices.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     */
    public void selectText(int stbrtIndex, int endIndex);

    /**
     * Sets bttributes for the text between two indices.
     *
     * @pbrbm stbrtIndex the stbrting index in the text
     * @pbrbm endIndex the ending index in the text
     * @pbrbm bs the bttribute set
     * @see AttributeSet
     */
    public void setAttributes(int stbrtIndex, int endIndex, AttributeSet bs);

}
