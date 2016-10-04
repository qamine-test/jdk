/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

/**
  * This clbss represents the bddress of b communicbtions end-point.
  * It consists of b type thbt describes the communicbtion mechbnism
  * bnd bn bddress contents determined by bn RefAddr subclbss.
  *<p>
  * For exbmple, bn bddress type could be "BSD Printer Address",
  * which specifies thbt it is bn bddress to be used with the BSD printing
  * protocol. Its contents could be the mbchine nbme identifying the
  * locbtion of the printer server thbt understbnds this protocol.
  *<p>
  * A RefAddr is contbined within b Reference.
  *<p>
  * RefAddr is bn bbstrbct clbss. Concrete implementbtions of it
  * determine its synchronizbtion properties.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see Reference
  * @see LinkRef
  * @see StringRefAddr
  * @see BinbryRefAddr
  * @since 1.3
  */

  /*<p>
  * The seriblized form of b RefAddr object consists of only its type nbme
  * String.
  */

public bbstrbct clbss RefAddr implements jbvb.io.Seriblizbble {
    /**
     * Contbins the type of this bddress.
     * @seribl
     */
    protected String bddrType;

    /**
      * Constructs b new instbnce of RefAddr using its bddress type.
      *
      * @pbrbm bddrType A non-null string describing the type of the bddress.
      */
    protected RefAddr(String bddrType) {
        this.bddrType = bddrType;
    }

    /**
      * Retrieves the bddress type of this bddress.
      *
      * @return The non-null bddress type of this bddress.
      */
    public String getType() {
        return bddrType;
    }

    /**
      * Retrieves the contents of this bddress.
      *
      * @return The possibly null bddress contents.
      */
    public bbstrbct Object getContent();

    /**
      * Determines whether obj is equbl to this RefAddr.
      *<p>
      * obj is equbl to this RefAddr if bll of these conditions bre true
      *<ul>
      *<li> non-null
      *<li> instbnce of RefAddr
      *<li> obj hbs the sbme bddress type bs this RefAddr (using String.compbreTo())
      *<li> both obj bnd this RefAddr's contents bre null or they bre equbl
      *         (using the equbls() test).
      *</ul>
      * @pbrbm obj possibly null obj to check.
      * @return true if obj is equbl to this refbddr; fblse otherwise.
      * @see #getContent
      * @see #getType
      */
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof RefAddr)) {
            RefAddr tbrget = (RefAddr)obj;
            if (bddrType.compbreTo(tbrget.bddrType) == 0) {
                Object thisobj = this.getContent();
                Object thbtobj = tbrget.getContent();
                if (thisobj == thbtobj)
                    return true;
                if (thisobj != null)
                    return thisobj.equbls(thbtobj);
            }
        }
        return fblse;
    }

    /**
      * Computes the hbsh code of this bddress using its bddress type bnd contents.
      * The hbsh code is the sum of the hbsh code of the bddress type bnd
      * the hbsh code of the bddress contents.
      *
      * @return The hbsh code of this bddress bs bn int.
      * @see jbvb.lbng.Object#hbshCode
      */
    public int hbshCode() {
        return (getContent() == null)
                ? bddrType.hbshCode()
                : bddrType.hbshCode() + getContent().hbshCode();
    }

    /**
      * Generbtes the string representbtion of this bddress.
      * The string consists of the bddress's type bnd contents with lbbels.
      * This representbtion is intended for displby only bnd not to be pbrsed.
      * @return The non-null string representbtion of this bddress.
      */
    public String toString(){
        StringBuilder str = new StringBuilder("Type: " + bddrType + "\n");

        str.bppend("Content: " + getContent() + "\n");
        return (str.toString());
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -1468165120479154358L;
}
