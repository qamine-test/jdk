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
  * This interfbce represents b builder thbt crebtes initibl context fbctories.
  *<p>
  * The JNDI frbmework bllows for different initibl context implementbtions
  * to be specified bt runtime.  An initibl context is crebted using
  * bn initibl context fbctory. A progrbm cbn instbll its own builder
  * thbt crebtes initibl context fbctories, thereby overriding the
  * defbult policies used by the frbmework, by cblling
  * NbmingMbnbger.setInitiblContextFbctoryBuilder().
  * The InitiblContextFbctoryBuilder interfbce must be implemented by
  * such b builder.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see InitiblContextFbctory
  * @see NbmingMbnbger#getInitiblContext
  * @see NbmingMbnbger#setInitiblContextFbctoryBuilder
  * @see NbmingMbnbger#hbsInitiblContextFbctoryBuilder
  * @see jbvbx.nbming.InitiblContext
  * @see jbvbx.nbming.directory.InitiblDirContext
  * @since 1.3
  */
public interfbce InitiblContextFbctoryBuilder {
    /**
      * Crebtes bn initibl context fbctory using the specified
      * environment.
      *<p>
      * The environment pbrbmeter is owned by the cbller.
      * The implementbtion will not modify the object or keep b reference
      * to it, blthough it mby keep b reference to b clone or copy.
      *
      * @pbrbm environment Environment used in crebting bn initibl
      *                 context implementbtion. Cbn be null.
      * @return A non-null initibl context fbctory.
      * @exception NbmingException If bn initibl context fbctory could not be crebted.
      */
    public InitiblContextFbctory
        crebteInitiblContextFbctory(Hbshtbble<?,?> environment)
        throws NbmingException;
}
