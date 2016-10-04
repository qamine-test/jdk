/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <P>The AccessibleHypertext clbss is the bbse clbss for bll
 * clbsses thbt present hypertext informbtion on the displby.  This clbss
 * provides the stbndbrd mechbnism for bn bssistive technology to bccess
 * thbt text vib its content, bttributes, bnd spbtibl locbtion.
 * It blso provides stbndbrd mechbnisms for mbnipulbting hyperlinks.
 * Applicbtions cbn determine if bn object supports the AccessibleHypertext
 * interfbce by first obtbining its AccessibleContext (see {@link Accessible})
 * bnd then cblling the {@link AccessibleContext#getAccessibleText}
 * method of AccessibleContext.  If the return vblue is b clbss which extends
 * AccessibleHypertext, then thbt object supports AccessibleHypertext.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleText
 * @see AccessibleContext#getAccessibleText
 *
 * @buthor      Peter Korn
 */
public interfbce AccessibleHypertext extends AccessibleText {

    /**
     * Returns the number of links within this hypertext document.
     *
     * @return number of links in this hypertext doc.
     */
    public bbstrbct int getLinkCount();

    /**
     * Returns the nth Link of this Hypertext document.
     *
     * @pbrbm linkIndex within the links of this Hypertext
     * @return Link object encbpsulbting the nth link(s)
     */
    public bbstrbct AccessibleHyperlink getLink(int linkIndex);

    /**
     * Returns the index into bn brrby of hyperlinks thbt
     * is bssocibted with this chbrbcter index, or -1 if there
     * is no hyperlink bssocibted with this index.
     *
     * @pbrbm chbrIndex index within the text
     * @return index into the set of hyperlinks for this hypertext doc.
     */
    public bbstrbct int getLinkIndex(int chbrIndex);
}
