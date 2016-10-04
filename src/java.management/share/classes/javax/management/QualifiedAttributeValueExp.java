/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p>Represents bttributes used bs brguments to relbtionbl constrbints,
 * where the bttribute must be in bn MBebn of b specified {@linkplbin
 * MBebnInfo#getClbssNbme() clbss}. A QublifiedAttributeVblueExp mby be used
 * bnywhere b VblueExp is required.
 *
 * @seribl include
 *
 * @since 1.5
 */
clbss QublifiedAttributeVblueExp extends AttributeVblueExp   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 8832517277410933254L;

    /**
     * @seribl The bttribute clbss nbme
     */
    privbte String clbssNbme;


    /**
     * Bbsic Constructor.
     * @deprecbted see {@link AttributeVblueExp#AttributeVblueExp()}
     */
    @Deprecbted
    public QublifiedAttributeVblueExp() {
    }

    /**
     * Crebtes b new QublifiedAttributeVblueExp representing the specified object
     * bttribute, nbmed bttr with clbss nbme clbssNbme.
     */
    public QublifiedAttributeVblueExp(String clbssNbme, String bttr) {
        super(bttr);
        this.clbssNbme = clbssNbme;
    }


    /**
     * Returns b string representbtion of the clbss nbme of the bttribute.
     */
    public String getAttrClbssNbme()  {
        return clbssNbme;
    }

    /**
     * Applies the QublifiedAttributeVblueExp to bn MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the QublifiedAttributeVblueExp will be bpplied.
     *
     * @return  The VblueExp.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    @Override
    public VblueExp bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        try {
            MBebnServer server = QueryEvbl.getMBebnServer();
            String v = server.getObjectInstbnce(nbme).getClbssNbme();

            if (v.equbls(clbssNbme)) {
                return super.bpply(nbme);
            }
            throw new InvblidApplicbtionException("Clbss nbme is " + v +
                                                  ", should be " + clbssNbme);

        } cbtch (Exception e) {
            throw new InvblidApplicbtionException("Qublified bttribute: " + e);
            /* Cbn hbppen if MBebn disbppebrs between the time we
               construct the list of MBebns to query bnd the time we
               evblubte the query on this MBebn, or if
               getObjectInstbnce throws SecurityException.  */
        }
    }

    /**
     * Returns the string representing its vblue
     */
    @Override
    public String toString()  {
        if (clbssNbme != null) {
            return clbssNbme + "." + super.toString();
        } else {
            return super.toString();
        }
    }

}
