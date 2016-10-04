/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.spi;

import jbvb.util.Hbshtbble;
import jbvbx.nbming.NbmingException;

 /**
  * This interfbce represents b builder thbt crebtes object fbctories.
  *<p>
  * The JNDI frbmework bllows for object implementbtions to
  * be lobded in dynbmicblly vib <em>object fbctories</em>.
  * For exbmple, when looking up b printer bound in the nbme spbce,
  * if the print service binds printer nbmes to References, the printer
  * Reference could be used to crebte b printer object, so thbt
  * the cbller of lookup cbn directly operbte on the printer object
  * bfter the lookup.  An ObjectFbctory is responsible for crebting
  * objects of b specific type.  JNDI uses b defbult policy for using
  * bnd lobding object fbctories.  You cbn override this defbult policy
  * by cblling <tt>NbmingMbnbger.setObjectFbctoryBuilder()</tt> with bn ObjectFbctoryBuilder,
  * which contbins the progrbm-defined wby of crebting/lobding
  * object fbctories.
  * Any <tt>ObjectFbctoryBuilder</tt> implementbtion must implement this
  * interfbce thbt for crebting object fbctories.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see ObjectFbctory
  * @see NbmingMbnbger#getObjectInstbnce
  * @see NbmingMbnbger#setObjectFbctoryBuilder
  * @since 1.3
  */
public interfbce ObjectFbctoryBuilder {
    /**
      * Crebtes b new object fbctory using the environment supplied.
      *<p>
      * The environment pbrbmeter is owned by the cbller.
      * The implementbtion will not modify the object or keep b reference
      * to it, blthough it mby keep b reference to b clone or copy.
      *
      * @pbrbm obj The possibly null object for which to crebte b fbctory.
      * @pbrbm environment Environment to use when crebting the fbctory.
      *                 Cbn be null.
      * @return A non-null new instbnce of bn ObjectFbctory.
      * @exception NbmingException If bn object fbctory cbnnot be crebted.
      *
      */
    public ObjectFbctory crebteObjectFbctory(Object obj,
                                             Hbshtbble<?,?> environment)
        throws NbmingException;
}
