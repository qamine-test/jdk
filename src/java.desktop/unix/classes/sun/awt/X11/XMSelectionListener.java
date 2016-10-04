/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

 /*
   * This code is ported to XAWT from MAWT bbsed on bwt_mgrsel.c
   * bnd XSettings.jbvb code written originblly by Vbleriy Ushbkov
   * Author : Bino George
   */

pbckbge sun.bwt.X11;

public interfbce  XMSelectionListener {

   /*
    * This method is cblled when the owner chbnges
    */
   public void ownerChbnged(int screen, XMSelection sel, long newOwner, long dbtb, long timestbmp);

   /*
    * This method is cblled when the owner dies
    */
   public void ownerDebth(int screen, XMSelection sel, long debdOwner);

   /*
    * This method is for selection chbnge notificbtion
    *
    * This method will only get cblled if you use the defbult constructor
    * or expilicitly specify PropertyChbngeMbsk.
    */

   public void selectionChbnged(int screen, XMSelection sel, long owner, XPropertyEvent event);

}
