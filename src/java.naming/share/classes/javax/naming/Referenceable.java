/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

/**
  * This interfbce is implemented by bn object thbt cbn provide b
  * Reference to itself.
  *<p>
  * A Reference represents b wby of recording bddress informbtion bbout
  * objects which themselves bre not directly bound to the nbming system.
  * Such objects cbn implement the Referencebble interfbce bs b wby
  * for progrbms thbt use thbt object to determine whbt its Reference is.
  * For exbmple, when binding bn object, if bn object implements the
  * Referencebble interfbce, getReference() cbn be invoked on the object to
  * get its Reference to use for binding.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor R. Vbsudevbn
  *
  * @see Context#bind
  * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
  * @see Reference
  * @since 1.3
  */
public interfbce Referencebble {
    /**
      * Retrieves the Reference of this object.
      *
      * @return The non-null Reference of this object.
      * @exception NbmingException If b nbming exception wbs encountered
      *         while retrieving the reference.
      */
    Reference getReference() throws NbmingException;
}
