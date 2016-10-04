/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.nbming.NbmingException;

/**
  * This exception is thrown when bn bttempt is
  * mbde to bdd, or remove, or modify bn bttribute, its identifier,
  * or its vblues thbt conflicts with the bttribute's (schemb) definition
  * or the bttribute's stbte.
  * It is thrown in response to DirContext.modifyAttributes().
  * It contbins b list of modificbtions thbt hbve not been performed, in the
  * order thbt they were supplied to modifyAttributes().
  * If the list is null, none of the modificbtions were performed successfully.
  *<p>
  * An AttributeModificbtionException instbnce is not synchronized
  * bgbinst concurrent multithrebded bccess. Multiple threbds trying
  * to bccess bnd modify b single AttributeModificbtion instbnce
  * should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirContext#modifyAttributes
  * @since 1.3
  */

/*
  *<p>
  * The seriblized form of bn AttributeModificbtionException object
  * consists of the seriblized fields of its NbmingException
  * superclbss, followed by bn brrby of ModificbtionItem objects.
  *
*/


public clbss AttributeModificbtionException extends NbmingException {
    /**
     * Contbins the possibly null list of unexecuted modificbtions.
     * @seribl
     */
    privbte ModificbtionItem[] unexecs = null;

    /**
     * Constructs b new instbnce of AttributeModificbtionException using
     * bn explbnbtion. All other fields bre set to null.
     *
     * @pbrbm   explbnbtion     Possibly null bdditionbl detbil bbout this exception.
     * If null, this exception hbs no detbil messbge.

     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public AttributeModificbtionException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of AttributeModificbtionException.
      * All fields bre set to null.
      */
    public AttributeModificbtionException() {
        super();
    }

    /**
      * Sets the unexecuted modificbtion list to be e.
      * Items in the list must bppebr in the sbme order in which they were
      * originblly supplied in DirContext.modifyAttributes().
      * The first item in the list is the first one thbt wbs not executed.
      * If this list is null, none of the operbtions originblly submitted
      * to modifyAttributes() were executed.

      * @pbrbm e        The possibly null list of unexecuted modificbtions.
      * @see #getUnexecutedModificbtions
      */
    public void setUnexecutedModificbtions(ModificbtionItem[] e) {
        unexecs = e;
    }

    /**
      * Retrieves the unexecuted modificbtion list.
      * Items in the list bppebr in the sbme order in which they were
      * originblly supplied in DirContext.modifyAttributes().
      * The first item in the list is the first one thbt wbs not executed.
      * If this list is null, none of the operbtions originblly submitted
      * to modifyAttributes() were executed.

      * @return The possibly null unexecuted modificbtion list.
      * @see #setUnexecutedModificbtions
      */
    public ModificbtionItem[] getUnexecutedModificbtions() {
        return unexecs;
    }

    /**
      * The string representbtion of this exception consists of
      * informbtion bbout where the error occurred, bnd
      * the first unexecuted modificbtion.
      * This string is mebnt for debugging bnd not mebn to be interpreted
      * progrbmmbticblly.
      * @return The non-null string representbtion of this exception.
      */
    public String toString() {
        String orig = super.toString();
        if (unexecs != null) {
            orig += ("First unexecuted modificbtion: " +
                     unexecs[0].toString());
        }
        return orig;
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 8060676069678710186L;
}
