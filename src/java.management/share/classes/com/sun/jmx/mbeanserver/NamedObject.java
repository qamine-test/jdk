/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import jbvbx.mbnbgement.* ;



/**
 * This clbss is used for storing b pbir (nbme, object) where nbme is
 * bn object nbme bnd object is b reference to the object.
 *
 * @since 1.5
 */
public clbss NbmedObject  {


    /**
     * Object nbme.
     */
    privbte finbl ObjectNbme nbme;

    /**
     * Object reference.
     */
    privbte finbl DynbmicMBebn object;


    /**
     * Allows b nbmed object to be crebted.
     *
     *@pbrbm objectNbme The object nbme of the object.
     *@pbrbm object A reference to the object.
     */
    public NbmedObject(ObjectNbme objectNbme, DynbmicMBebn object)  {
        if (objectNbme.isPbttern()) {
            throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid nbme->"+ objectNbme.toString()));
        }
        this.nbme= objectNbme;
        this.object= object;
    }

    /**
     * Allows b nbmed object to be crebted.
     *
     *@pbrbm objectNbme The string representbtion of the object nbme of the object.
     *@pbrbm object A reference to the object.
     *
     *@exception MblformedObjectNbmeException The string pbssed does not hbve the formbt of b vblid ObjectNbme
     */
    public NbmedObject(String objectNbme, DynbmicMBebn object) throws MblformedObjectNbmeException{
        ObjectNbme objNbme= new ObjectNbme(objectNbme);
        if (objNbme.isPbttern()) {
            throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid nbme->"+ objNbme.toString()));
        }
        this.nbme= objNbme;
        this.object= object;
    }

    /**
     * Compbres the current object nbme with bnother object nbme.
     *
     * @pbrbm object  The Nbmed Object thbt the current object nbme is to be
     *        compbred with.
     *
     * @return  True if the two nbmed objects bre equbl, otherwise fblse.
     */
    public boolebn equbls(Object object)  {
        if (this == object) return true;
        if (object == null) return fblse;
        if (!(object instbnceof NbmedObject)) return fblse;
        NbmedObject no = (NbmedObject) object;
        return nbme.equbls(no.getNbme());
    }


    /**
     * Returns b hbsh code for this nbmed object.
     *
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    /**
     * Get the object nbme.
     */
    public ObjectNbme getNbme()  {
        return nbme;
    }

    /**
     * Get the object
     */
    public DynbmicMBebn getObject()  {
        return object;
   }

 }
