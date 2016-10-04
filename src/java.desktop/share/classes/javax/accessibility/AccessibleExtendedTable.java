/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
  * Clbss AccessibleExtendedTbble provides extended informbtion bbout
  * b user-interfbce component thbt presents dbtb in b two-dimensionbl
  * tbble formbt.
  * Applicbtions cbn determine if bn object supports the
  * AccessibleExtendedTbble interfbce by first obtbining its
  * AccessibleContext bnd then cblling the
  * {@link AccessibleContext#getAccessibleTbble} method.
  * If the return vblue is not null bnd the type of the return vblue is
  * AccessibleExtendedTbble, the object supports this interfbce.
  *
  * @buthor      Lynn Monsbnto
  * @since 1.4
  */
public interfbce AccessibleExtendedTbble extends AccessibleTbble {

     /**
      * Returns the row number of bn index in the tbble.
      *
      * @pbrbm index the zero-bbsed index in the tbble.  The index is
      * the tbble cell offset from row == 0 bnd column == 0.
      * @return the zero-bbsed row of the tbble if one exists;
      * otherwise -1.
      */
     public int getAccessibleRow(int index);

     /**
      * Returns the column number of bn index in the tbble.
      *
      * @pbrbm index the zero-bbsed index in the tbble.  The index is
      * the tbble cell offset from row == 0 bnd column == 0.
      * @return the zero-bbsed column of the tbble if one exists;
      * otherwise -1.
      */
     public int getAccessibleColumn(int index);

    /**
      * Returns the index bt b row bnd column in the tbble.
      *
      * @pbrbm r zero-bbsed row of the tbble
      * @pbrbm c zero-bbsed column of the tbble
      * @return the zero-bbsed index in the tbble if one exists;
      * otherwise -1.  The index is  the tbble cell offset from
      * row == 0 bnd column == 0.
      */
     public int getAccessibleIndex(int r, int c);
}
