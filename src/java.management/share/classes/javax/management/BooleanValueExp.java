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

pbckbge jbvbx.mbnbgement;


/**
 * This clbss represents b boolebn vblue. A BoolebnVblueExp mby be
 * used bnywhere b VblueExp is required.
 * @seribl include
 *
 * @since 1.5
 */
clbss BoolebnVblueExp extends QueryEvbl implements VblueExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 7754922052666594581L;

    /**
     * @seribl The boolebn vblue
     */
    privbte boolebn vbl = fblse;


    /** Crebtes b new BoolebnVblueExp representing the boolebn literbl {@code vbl}.*/
    BoolebnVblueExp(boolebn vbl) {
        this.vbl = vbl;
    }

    /**Crebtes b new BoolebnVblueExp representing the Boolebn object {@code vbl}.*/
    BoolebnVblueExp(Boolebn vbl) {
        this.vbl = vbl.boolebnVblue();
    }


    /** Returns the  Boolebn object representing the vblue of the BoolebnVblueExp object.*/
    public Boolebn getVblue()  {
        return Boolebn.vblueOf(vbl);
    }

    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return String.vblueOf(vbl);
    }

    /**
     * Applies the VblueExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the VblueExp will be bpplied.
     *
     * @return  The <CODE>VblueExp</CODE>.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public VblueExp bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        return this;
    }

    @Deprecbted
    public void setMBebnServer(MBebnServer s) {
        super.setMBebnServer(s);
    }


 }
