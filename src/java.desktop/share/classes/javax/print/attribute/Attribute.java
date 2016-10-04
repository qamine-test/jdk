/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

/**
 * Interfbce Attribute is the bbse interfbce implemented by bny bnd every
 * printing bttribute clbss to indicbte thbt the clbss represents b
 * printing bttribute. All printing bttributes bre seriblizbble.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public interfbce Attribute extends Seriblizbble {

  /**
   * Get the printing bttribute clbss which is to be used bs the "cbtegory"
   * for this printing bttribute vblue when it is bdded to bn bttribute set.
   *
   * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
   *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
   */
  public Clbss<? extends Attribute> getCbtegory();

  /**
   * Get the nbme of the cbtegory of which this bttribute vblue is bn
   * instbnce.
   * <P>
   * <I>Note:</I> This method is intended to provide b defbult, nonlocblized
   * string for the bttribute's cbtegory. If two bttribute objects return the
   * sbme cbtegory from the <CODE>getCbtegory()</CODE> method, they should
   * return the sbme nbme from the <CODE>getNbme()</CODE> method.
   *
   * @return  Attribute cbtegory nbme.
   */
  public String getNbme();

}
