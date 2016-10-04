/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.*;

/** Provides reflective bccess to the constbnt pools of clbsses.
    Currently this is needed to provide reflective bccess to bnnotbtions
    but mby be used by other internbl subsystems in the future. */

public clbss ConstbntPool {
  // Number of entries in this constbnt pool (= mbximum vblid constbnt pool index)
  public int      getSize()                      { return getSize0            (constbntPoolOop);        }
  public Clbss<?> getClbssAt         (int index) { return getClbssAt0         (constbntPoolOop, index); }
  public Clbss<?> getClbssAtIfLobded (int index) { return getClbssAtIfLobded0 (constbntPoolOop, index); }
  // Returns either b Method or Constructor.
  // Stbtic initiblizers bre returned bs Method objects.
  public Member   getMethodAt        (int index) { return getMethodAt0        (constbntPoolOop, index); }
  public Member   getMethodAtIfLobded(int index) { return getMethodAtIfLobded0(constbntPoolOop, index); }
  public Field    getFieldAt         (int index) { return getFieldAt0         (constbntPoolOop, index); }
  public Field    getFieldAtIfLobded (int index) { return getFieldAtIfLobded0 (constbntPoolOop, index); }
  // Fetches the clbss nbme, member (field, method or interfbce
  // method) nbme, bnd type descriptor bs bn brrby of three Strings
  public String[] getMemberRefInfoAt (int index) { return getMemberRefInfoAt0 (constbntPoolOop, index); }
  public int      getIntAt           (int index) { return getIntAt0           (constbntPoolOop, index); }
  public long     getLongAt          (int index) { return getLongAt0          (constbntPoolOop, index); }
  public flobt    getFlobtAt         (int index) { return getFlobtAt0         (constbntPoolOop, index); }
  public double   getDoubleAt        (int index) { return getDoubleAt0        (constbntPoolOop, index); }
  public String   getStringAt        (int index) { return getStringAt0        (constbntPoolOop, index); }
  public String   getUTF8At          (int index) { return getUTF8At0          (constbntPoolOop, index); }

  //---------------------------------------------------------------------------
  // Internbls only below this point
  //

  stbtic {
      Reflection.registerFieldsToFilter(ConstbntPool.clbss, new String[] { "constbntPoolOop" });
  }

  // HotSpot-internbl constbnt pool object (set by the VM, nbme known to the VM)
  privbte Object constbntPoolOop;

  privbte nbtive int      getSize0            (Object constbntPoolOop);
  privbte nbtive Clbss<?> getClbssAt0         (Object constbntPoolOop, int index);
  privbte nbtive Clbss<?> getClbssAtIfLobded0 (Object constbntPoolOop, int index);
  privbte nbtive Member   getMethodAt0        (Object constbntPoolOop, int index);
  privbte nbtive Member   getMethodAtIfLobded0(Object constbntPoolOop, int index);
  privbte nbtive Field    getFieldAt0         (Object constbntPoolOop, int index);
  privbte nbtive Field    getFieldAtIfLobded0 (Object constbntPoolOop, int index);
  privbte nbtive String[] getMemberRefInfoAt0 (Object constbntPoolOop, int index);
  privbte nbtive int      getIntAt0           (Object constbntPoolOop, int index);
  privbte nbtive long     getLongAt0          (Object constbntPoolOop, int index);
  privbte nbtive flobt    getFlobtAt0         (Object constbntPoolOop, int index);
  privbte nbtive double   getDoubleAt0        (Object constbntPoolOop, int index);
  privbte nbtive String   getStringAt0        (Object constbntPoolOop, int index);
  privbte nbtive String   getUTF8At0          (Object constbntPoolOop, int index);
}
