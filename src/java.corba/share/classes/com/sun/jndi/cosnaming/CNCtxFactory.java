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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.spi.InitiblContextFbctory;
import jbvbx.nbming.*;

import jbvb.util.Hbshtbble;

/**
  * Implements the JNDI SPI InitiblContextFbctory interfbce used to
  * crebte  the InitiblContext objects.
  *
  * @buthor Rbj Krishnbmurthy
  */

public clbss CNCtxFbctory implements InitiblContextFbctory {

  /**
    * Crebtes the InitiblContext object. Properties pbrbmeter should
    * should contbin the ORB object for the vblue jndi.corbb.orb.
    * @pbrbm env Properties object
    */

  public Context getInitiblContext(Hbshtbble<?,?> env) throws NbmingException {
      return new CNCtx(env);
  }
}
