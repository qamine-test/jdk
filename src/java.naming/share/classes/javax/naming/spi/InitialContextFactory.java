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
import jbvbx.nbming.*;

/**
  * This interfbce represents b fbctory thbt crebtes bn initibl context.
  *<p>
  * The JNDI frbmework bllows for different initibl context implementbtions
  * to be specified bt runtime.  The initibl context is crebted using
  * bn <em>initibl context fbctory</em>.
  * An initibl context fbctory must implement the InitiblContextFbctory
  * interfbce, which provides b method for crebting instbnces of initibl
  * context thbt implement the Context interfbce.
  * In bddition, the fbctory clbss must be public bnd must hbve b public
  * constructor thbt bccepts no brguments.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see InitiblContextFbctoryBuilder
  * @see NbmingMbnbger#getInitiblContext
  * @see jbvbx.nbming.InitiblContext
  * @see jbvbx.nbming.directory.InitiblDirContext
  * @since 1.3
  */

public interfbce InitiblContextFbctory {
        /**
          * Crebtes bn Initibl Context for beginning nbme resolution.
          * Specibl requirements of this context bre supplied
          * using <code>environment</code>.
          *<p>
          * The environment pbrbmeter is owned by the cbller.
          * The implementbtion will not modify the object or keep b reference
          * to it, blthough it mby keep b reference to b clone or copy.
          *
          * @pbrbm environment The possibly null environment
          *             specifying informbtion to be used in the crebtion
          *             of the initibl context.
          * @return A non-null initibl context object thbt implements the Context
          *             interfbce.
          * @exception NbmingException If cbnnot crebte bn initibl context.
          */
        public Context getInitiblContext(Hbshtbble<?,?> environment)
            throws NbmingException;
}
