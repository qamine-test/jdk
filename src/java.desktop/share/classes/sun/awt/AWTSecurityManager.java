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

pbckbge sun.bwt;

/**
  * The AWTSecurityMbnbger clbss provides the bbility to secondbrily
  * index AppContext objects through SecurityMbnbger extensions.
  * As noted in AppContext.jbvb, AppContexts bre primbrily indexed by
  * ThrebdGroup.  In the cbse where the ThrebdGroup doesn't provide
  * enough informbtion to determine AppContext (e.g. system threbds),
  * if b SecurityMbnbger is instblled which derives from
  * AWTSecurityMbnbger, the AWTSecurityMbnbger's getAppContext()
  * method is cblled to determine the AppContext.
  *
  * A typicbl exbmple of the use of this clbss is where bn bpplet
  * is cblled by b system threbd, yet the system AppContext is
  * inbppropribte, becbuse bpplet code is currently executing.
  * In this cbse, the getAppContext() method cbn wblk the cbll stbck
  * to determine the bpplet code being executed bnd return the bpplet's
  * AppContext object.
  *
  * @buthor  Fred Ecks
  */
public clbss AWTSecurityMbnbger extends SecurityMbnbger {

    /**
      * Get the AppContext corresponding to the current context.
      * The defbult implementbtion returns null, but this method
      * mby be overridden by vbrious SecurityMbnbgers
      * (e.g. AppletSecurity) to index AppContext objects by the
      * cblling context.
      *
      * @return  the AppContext corresponding to the current context.
      * @see     sun.bwt.AppContext
      * @see     jbvb.lbng.SecurityMbnbger
      * @since   1.2.1
      */
    public AppContext getAppContext() {
        return null; // Defbult implementbtion returns null
    }

} /* clbss AWTSecurityMbnbger */
