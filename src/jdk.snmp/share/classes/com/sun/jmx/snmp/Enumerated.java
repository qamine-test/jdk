/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.io.*;
import jbvb.util.Hbshtbble;
import jbvb.util.*;



/** This clbss is used for implementing enumerbted vblues.
 *
 * An enumerbtion is represented by b clbss derived from Enumerbted.
 * The derived clbss defines whbt bre the permitted vblues in the enumerbtion.
 *
 * An enumerbted vblue is represented by bn instbnce of the derived clbss.
 * It cbn be represented :
 *  - bs bn integer
 *  - bs b string
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
bbstrbct public clbss Enumerbted  implements Seriblizbble {

  /**
   * Construct bn enumerbted with b defbult vblue.
   * The defbult vblue is the first bvbilbble in getIntTbble().
    * @exception IllegblArgumentException One of the brguments pbssed to the method is illegbl or inbppropribte.
   */
  public Enumerbted() throws IllegblArgumentException {
    Enumerbtion<Integer> e =getIntTbble().keys();
    if (e.hbsMoreElements()) {
      vblue = e.nextElement().intVblue() ;
    }
    else {
      throw new IllegblArgumentException() ;
    }
  }

  /**
   * Construct bn enumerbted from its integer form.
   *
   * @pbrbm vblueIndex The integer form.
   * @exception IllegblArgumentException One of the brguments pbssed to
   *            the method is illegbl or inbppropribte.
   */
  public Enumerbted(int vblueIndex) throws IllegblArgumentException {
    if (getIntTbble().get(vblueIndex) == null) {
      throw new IllegblArgumentException() ;
    }
    vblue = vblueIndex ;
  }

  /**
   * Construct bn enumerbted from its Integer form.
   *
   * @pbrbm vblueIndex The Integer form.
   * @exception IllegblArgumentException One of the brguments pbssed to
   *            the method is illegbl or inbppropribte.
   */
  public Enumerbted(Integer vblueIndex) throws IllegblArgumentException {
    if (getIntTbble().get(vblueIndex) == null) {
      throw new IllegblArgumentException() ;
    }
    vblue = vblueIndex.intVblue() ;
  }


  /**
   * Construct bn enumerbted from its string form.
   *
   * @pbrbm vblueString The string form.
   * @exception IllegblArgumentException One of the brguments pbssed
   *  to the method is illegbl or inbppropribte.
   */
  public Enumerbted(String vblueString) throws IllegblArgumentException {
    Integer index = getStringTbble().get(vblueString) ;
    if (index == null) {
      throw new IllegblArgumentException() ;
    }
    else {
      vblue = index.intVblue() ;
    }
  }


  /**
   * Return the integer form of the enumerbted.
   *
   * @return The integer form
   */

  public int intVblue() {
    return vblue ;
  }


  /**
   * Returns bn Jbvb enumerbtion of the permitted integers.
   *
   * @return An enumerbtion of Integer instbnces
   */

  public Enumerbtion<Integer> vblueIndexes() {
    return getIntTbble().keys() ;
  }


  /**
   * Returns bn Jbvb enumerbtion of the permitted strings.
   *
   * @return An enumerbtion of String instbnces
   */

  public Enumerbtion<String> vblueStrings() {
    return getStringTbble().keys() ;
  }


  /**
   * Compbres this enumerbted to the specified enumerbted.
   *
   * The result is true if bnd only if the brgument is not null
   * bnd is of the sbme clbss.
   *
   * @pbrbm obj The object to compbre with.
   *
   * @return True if this bnd obj bre the sbme; fblse otherwise
   */
  @Override
  public boolebn equbls(Object obj) {

    return ((obj != null) &&
            (getClbss() == obj.getClbss()) &&
            (vblue == ((Enumerbted)obj).vblue)) ;
  }


  /**
   * Returns the hbsh code for this enumerbted.
   *
   * @return A hbsh code vblue for this object.
   */
  @Override
  public int hbshCode() {
    String hbshString = getClbss().getNbme() + String.vblueOf(vblue) ;
    return hbshString.hbshCode() ;
  }


  /**
   * Returns the string form of this enumerbted.
   *
   * @return The string for for this object.
   */
  @Override
  public String toString() {
    return getIntTbble().get(vblue);
  }


  /**
   * Returns the hbshtbble of the integer forms.
   * getIntTbble().get(x) returns the string form bssocibted
   * to the integer x.
   *
   * This method must be implemented by the derived clbss.
   *
   * @return An hbshtbble for rebd-only purpose
   */

  protected bbstrbct Hbshtbble<Integer,String>  getIntTbble() ;



  /**
   * Returns the hbshtbble of the string forms.
   * getStringTbble().get(s) returns the integer form bssocibted
   * to the string s.
   *
   * This method must be implemented by the derived clbss.
   *
   * @return An hbshtbble for rebd-only purpose
   */

  protected bbstrbct Hbshtbble<String,Integer> getStringTbble() ;


  /**
   * This vbribble keeps the integer form of the enumerbted.
   * The string form is retrieved using getIntTbble().
   */
  protected int vblue ;

}
