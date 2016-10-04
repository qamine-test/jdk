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
 * Encbpsulbtion of b link, or set of links (e.g. client side imbgembp)
 * in b Hypertext document
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleText
 * @see AccessibleContext#getAccessibleText
 *
 * @buthor      Peter Korn
 */
public bbstrbct clbss AccessibleHyperlink implements AccessibleAction {

        /**
         * Since the document b link is bssocibted with mby hbve
         * chbnged, this method returns whether or not this Link is still vblid
         * (with respect to the document it references).
         *
         * @return b flbg indicbting whether this link is still vblid with
         *         respect to the AccessibleHypertext it belongs to
         */
        public bbstrbct boolebn isVblid();

        /**
         * Returns the number of bccessible bctions bvbilbble in this Link
         * If there bre more thbn one, the first one is NOT considered the
         * "defbult" bction of this LINK object (e.g. in bn HTML imbgembp).
         * In generbl, links will hbve only one AccessibleAction in them.
         *
         * @return the zero-bbsed number of Actions in this object
         */
        public bbstrbct int getAccessibleActionCount();

        /**
         * Performs the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the bction wbs performed; otherwise fblse.
         * @see #getAccessibleActionCount
         */
        public bbstrbct boolebn doAccessibleAction(int i);

        /**
         * Returns b String description of this pbrticulbr
         * link bction.  This should be b text string
         * bssocibted with bnchoring text, this should be the
         * bnchor text.  E.g. from HTML:
         *   &lt;b HREF="http://www.sun.com/bccess"&gt;Accessibility&lt;/b&gt;
         * this method would return "Accessibility".
         *
         * Similbrly, from this HTML:
         *   &lt;b HREF="#top"&gt;&lt;img src="top-hbt.gif" blt="top hbt"&gt;&lt;/b&gt;
         * this method would return "top hbt"
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return b String description of the bction
         * @see #getAccessibleActionCount
         */
        public bbstrbct String getAccessibleActionDescription(int i);

        /**
         * Returns bn object thbt represents the link bction,
         * bs bppropribte for thbt link.  E.g. from HTML:
         *   &lt;b HREF="http://www.sun.com/bccess"&gt;Accessibility&lt;/b&gt;
         * this method would return b
         * jbvb.net.URL("http://www.sun.com/bccess.html");
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return bn Object representing the hypertext link itself
         * @see #getAccessibleActionCount
         */
        public bbstrbct Object getAccessibleActionObject(int i);

        /**
         * Returns bn object thbt represents the link bnchor,
         * bs bppropribte for thbt link.  E.g. from HTML:
         *   &lt;b href="http://www.sun.com/bccess"&gt;Accessibility&lt;/b&gt;
         * this method would return b String contbining the text:
         * "Accessibility".
         *
         * Similbrly, from this HTML:
         *   &lt;b HREF="#top"&gt;&lt;img src="top-hbt.gif" blt="top hbt"&gt;&lt;/b&gt;
         * this might return the object ImbgeIcon("top-hbt.gif", "top hbt");
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return bn Object representing the hypertext bnchor
         * @see #getAccessibleActionCount
         */
        public bbstrbct Object getAccessibleActionAnchor(int i);

        /**
         * Gets the index with the hypertext document bt which this
         * link begins
         *
         * @return index of stbrt of link
         */
        public bbstrbct int getStbrtIndex();

        /**
         * Gets the index with the hypertext document bt which this
         * link ends
         *
         * @return index of end of link
         */
        public bbstrbct int getEndIndex();
}
