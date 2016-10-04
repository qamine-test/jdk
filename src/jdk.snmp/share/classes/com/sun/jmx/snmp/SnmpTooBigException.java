/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp;


/**
 * Is used internblly to signbl thbt the size of b PDU exceeds the pbcket size limitbtion.
 * <p>
 * You will not usublly need to use this clbss, except if you
 * decide to implement your own
 * {@link com.sun.jmx.snmp.SnmpPduFbctory SnmPduFbctory} object.
 * <p>
 * The <CODE>vbrBindCount</CODE> property contbins the
 * number of <CODE>SnmpVbrBind</CODE> successfully encoded
 * before the exception wbs thrown. Its vblue is 0
 * when this number is unknown.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpTooBigException extends Exception {
  privbte stbtic finbl long seriblVersionUID = 4754796246674803969L;

  /**
   * Builds bn <CODE>SnmpTooBigException</CODE> with
   * <CODE>vbrBindCount</CODE> set to 0.
   */
  public SnmpTooBigException() {
    vbrBindCount = 0 ;
  }

  /**
   * Builds bn <CODE>SnmpTooBigException</CODE> with
   * <CODE>vbrBindCount</CODE> set to the specified vblue.
   * @pbrbm n The <CODE>vbrBindCount</CODE> vblue.
   */
  public SnmpTooBigException(int n) {
    vbrBindCount = n ;
  }


  /**
   * Returns the number of <CODE>SnmpVbrBind</CODE> successfully
   * encoded before the exception wbs thrown.
   *
   * @return A positive integer (0 mebns the number is unknown).
   */
  public int getVbrBindCount() {
    return vbrBindCount ;
  }

  /**
   * The <CODE>vbrBindCount</CODE>.
   * @seribl
   */
  privbte int vbrBindCount ;
}
