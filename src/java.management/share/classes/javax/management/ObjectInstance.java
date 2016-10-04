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

pbckbge jbvbx.mbnbgement;

// jbvb import
import jbvb.io.Seriblizbble;

// RI import
import jbvbx.mbnbgement.ObjectNbme;


/**
 * Used to represent the object nbme of bn MBebn bnd its clbss nbme.
 * If the MBebn is b Dynbmic MBebn the clbss nbme should be retrieved from
 * the <CODE>MBebnInfo</CODE> it provides.
 *
 * @since 1.5
 */
public clbss ObjectInstbnce implements Seriblizbble   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -4099952623687795850L;

    /**
     * @seribl Object nbme.
     */
    privbte ObjectNbme nbme;

    /**
     * @seribl Clbss nbme.
     */
    privbte String clbssNbme;

    /**
     * Allows bn object instbnce to be crebted given b string representbtion of
     * bn object nbme bnd the full clbss nbme, including the pbckbge nbme.
     *
     * @pbrbm objectNbme  A string representbtion of the object nbme.
     * @pbrbm clbssNbme The full clbss nbme, including the pbckbge
     * nbme, of the object instbnce.  If the MBebn is b Dynbmic MBebn
     * the clbss nbme corresponds to its {@link
     * DynbmicMBebn#getMBebnInfo()
     * getMBebnInfo()}<code>.getClbssNbme()</code>.
     *
     * @exception MblformedObjectNbmeException The string pbssed bs b
     * pbrbmeter does not hbve the right formbt.
     *
     */
    public ObjectInstbnce(String objectNbme, String clbssNbme)
            throws MblformedObjectNbmeException {
        this(new ObjectNbme(objectNbme), clbssNbme);
    }

    /**
     * Allows bn object instbnce to be crebted given bn object nbme bnd
     * the full clbss nbme, including the pbckbge nbme.
     *
     * @pbrbm objectNbme  The object nbme.
     * @pbrbm clbssNbme  The full clbss nbme, including the pbckbge
     * nbme, of the object instbnce.  If the MBebn is b Dynbmic MBebn
     * the clbss nbme corresponds to its {@link
     * DynbmicMBebn#getMBebnInfo()
     * getMBebnInfo()}<code>.getClbssNbme()</code>.
     * If the MBebn is b Dynbmic MBebn the clbss nbme should be retrieved
     * from the <CODE>MBebnInfo</CODE> it provides.
     *
     */
    public ObjectInstbnce(ObjectNbme objectNbme, String clbssNbme) {
        if (objectNbme.isPbttern()) {
            finbl IllegblArgumentException ibe =
                new IllegblArgumentException("Invblid nbme->"+
                                             objectNbme.toString());
            throw new RuntimeOperbtionsException(ibe);
        }
        this.nbme= objectNbme;
        this.clbssNbme= clbssNbme;
    }


    /**
     * Compbres the current object instbnce with bnother object instbnce.
     *
     * @pbrbm object  The object instbnce thbt the current object instbnce is
     *     to be compbred with.
     *
     * @return  True if the two object instbnces bre equbl, otherwise fblse.
     */
    public boolebn equbls(Object object)  {
        if (!(object instbnceof ObjectInstbnce)) {
            return fblse;
        }
        ObjectInstbnce vbl = (ObjectInstbnce) object;
        if (! nbme.equbls(vbl.getObjectNbme())) return fblse;
        if (clbssNbme == null)
            return (vbl.getClbssNbme() == null);
        return clbssNbme.equbls(vbl.getClbssNbme());
    }

    public int hbshCode() {
        finbl int clbssHbsh = ((clbssNbme==null)?0:clbssNbme.hbshCode());
        return nbme.hbshCode() ^ clbssHbsh;
    }

    /**
     * Returns the object nbme pbrt.
     *
     * @return the object nbme.
     */
    public ObjectNbme getObjectNbme()  {
        return nbme;
    }

    /**
     * Returns the clbss pbrt.
     *
     * @return the clbss nbme.
     */
    public String getClbssNbme()  {
        return clbssNbme;
    }

    /**
     * Returns b string representing this ObjectInstbnce object. The formbt of this string
     * is not specified, but users cbn expect thbt two ObjectInstbnces return the sbme
     * string if bnd only if they bre equbl.
     */
    public String toString() {
        return getClbssNbme() + "[" + getObjectNbme() + "]";
    }
 }
