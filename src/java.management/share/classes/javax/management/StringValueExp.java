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
 * Represents strings thbt bre brguments to relbtionbl constrbints.
 * A <CODE>StringVblueExp</CODE> mby be used bnywhere b <CODE>VblueExp</CODE> is required.
 *
 * @since 1.5
 */
public clbss StringVblueExp implements VblueExp   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -3256390509806284044L;

    /**
     * @seribl The string literbl
     */
    privbte String vbl;

    /**
     * Bbsic constructor.
     */
    public StringVblueExp() {
    }

    /**
     * Crebtes b new <CODE>StringVblueExp</CODE> representing the
     * given string.
     *
     * @pbrbm vbl the string thbt will be the vblue of this expression
     */
    public StringVblueExp(String vbl) {
        this.vbl = vbl;
    }

    /**
     * Returns the string represented by the
     * <CODE>StringVblueExp</CODE> instbnce.
     *
     * @return the string.
     */
    public String getVblue()  {
        return vbl;
    }

    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return "'" + vbl.replbce("'", "''") + "'";
    }


    /**
     * Sets the MBebn server on which the query is to be performed.
     *
     * @pbrbm s The MBebn server on which the query is to be performed.
     */
    /* There is no need for this method, becbuse if b query is being
       evblubted b StringVblueExp cbn only bppebr inside b QueryExp,
       bnd thbt QueryExp will itself hbve done setMBebnServer.  */
    @Deprecbted
    public void setMBebnServer(MBebnServer s)  { }

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
 }
