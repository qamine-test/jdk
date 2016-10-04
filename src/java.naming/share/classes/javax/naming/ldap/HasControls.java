/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingException;

/**
  * This interfbce is for returning controls with objects returned
  * in NbmingEnumerbtions.
  * For exbmple, suppose b server sends bbck controls with the results
  * of b sebrch operbtion, the service provider would return b NbmingEnumerbtion of
  * objects thbt bre both SebrchResult bnd implement HbsControls.
  *<blockquote><pre>
  *   NbmingEnumerbtion elts = ectx.sebrch((Nbme)nbme, filter, sctls);
  *   while (elts.hbsMore()) {
  *     Object entry = elts.next();
  *
  *     // Get sebrch result
  *     SebrchResult res = (SebrchResult)entry;
  *     // do something with it
  *
  *     // Get entry controls
  *     if (entry instbnceof HbsControls) {
  *         Control[] entryCtls = ((HbsControls)entry).getControls();
  *         // do something with controls
  *     }
  *   }
  *</pre></blockquote>
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  * @since 1.3
  *
  */

public interfbce HbsControls {

    /**
      * Retrieves bn brrby of <tt>Control</tt>s from the object thbt
      * implements this interfbce. It is null if there bre no controls.
      *
      * @return A possibly null brrby of <tt>Control</tt> objects.
      * @throws NbmingException If cbnnot return controls due to bn error.
      */
    public Control[] getControls() throws NbmingException;
}
