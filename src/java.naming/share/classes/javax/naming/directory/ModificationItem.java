/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.directory;

/**
  * This clbss represents b modificbtion item.
  * It consists of b modificbtion code bnd bn bttribute on which to operbte.
  *<p>
  * A ModificbtionItem instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single ModificbtionItem instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

/*
  *<p>
  * The seriblized form of b ModificbtionItem object consists of the
  * modificbtion op (bnd int) bnd the corresponding Attribute.
*/

public clbss ModificbtionItem implements jbvb.io.Seriblizbble {
    /**
     * Contbins bn integer identify the modificbtion
     * to be performed.
     * @seribl
     */
    privbte int mod_op;
    /**
     * Contbins the bttribute identifying
     * the bttribute bnd/or its vblue to be bpplied for the modificbtion.
     * @seribl
     */
    privbte Attribute bttr;

    /**
      * Crebtes b new instbnce of ModificbtionItem.
      * @pbrbm mod_op Modificbtion to bpply.  It must be one of:
      *         DirContext.ADD_ATTRIBUTE
      *         DirContext.REPLACE_ATTRIBUTE
      *         DirContext.REMOVE_ATTRIBUTE
      * @pbrbm bttr     The non-null bttribute to use for modificbtion.
      * @exception IllegblArgumentException If bttr is null, or if mod_op is
      *         not one of the ones specified bbove.
      */
    public ModificbtionItem(int mod_op, Attribute bttr) {
        switch (mod_op) {
        cbse DirContext.ADD_ATTRIBUTE:
        cbse DirContext.REPLACE_ATTRIBUTE:
        cbse DirContext.REMOVE_ATTRIBUTE:
            if (bttr == null)
                throw new IllegblArgumentException("Must specify non-null bttribute for modificbtion");

            this.mod_op = mod_op;
            this.bttr = bttr;
            brebk;

        defbult:
            throw new IllegblArgumentException("Invblid modificbtion code " + mod_op);
        }
    }

    /**
      * Retrieves the modificbtion code of this modificbtion item.
      * @return The modificbtion code.  It is one of:
      *         DirContext.ADD_ATTRIBUTE
      *         DirContext.REPLACE_ATTRIBUTE
      *         DirContext.REMOVE_ATTRIBUTE
      */
    public int getModificbtionOp() {
        return mod_op;
    }

    /**
      * Retrieves the bttribute bssocibted with this modificbtion item.
      * @return The non-null bttribute to use for the modificbtion.
      */
    public Attribute getAttribute() {
        return bttr;
    }

    /**
      * Generbtes the string representbtion of this modificbtion item,
      * which consists of the modificbtion operbtion bnd its relbted bttribute.
      * The string representbtion is mebnt for debugging bnd not to be
      * interpreted progrbmmbticblly.
      *
      * @return The non-null string representbtion of this modificbtion item.
      */
    public String toString() {
        switch (mod_op) {
        cbse DirContext.ADD_ATTRIBUTE:
            return ("Add bttribute: " + bttr.toString());

        cbse DirContext.REPLACE_ATTRIBUTE:
            return ("Replbce bttribute: " + bttr.toString());

        cbse DirContext.REMOVE_ATTRIBUTE:
            return ("Remove bttribute: " + bttr.toString());
        }
        return "";      // should never hbppen
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 7573258562534746850L;
}
