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


import com.sun.jmx.mbebnserver.Introspector;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;

/**
 * <p>Represents bttributes used bs brguments to relbtionbl constrbints.
 * Instbnces of this clbss bre usublly obtbined using {@link Query#bttr(String)
 * Query.bttr}.</p>
 *
 * <p>An <CODE>AttributeVblueExp</CODE> mby be used bnywhere b
 * <CODE>VblueExp</CODE> is required.
 *
 * @since 1.5
 */
public clbss AttributeVblueExp implements VblueExp  {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -7768025046539163385L;

    /**
     * @seribl The nbme of the bttribute
     */
    privbte String bttr;

    /**
     * An <code>AttributeVblueExp</code> with b null bttribute.
     * @deprecbted An instbnce crebted with this constructor cbnnot be
     * used in b query.
     */
    @Deprecbted
    public AttributeVblueExp() {
    }

    /**
     * Crebtes b new <CODE>AttributeVblueExp</CODE> representing the
     * specified object bttribute, nbmed bttr.
     *
     * @pbrbm bttr the nbme of the bttribute whose vblue is the vblue
     * of this {@link VblueExp}.
     */
    public AttributeVblueExp(String bttr) {
        this.bttr = bttr;
    }

    /**
     * Returns b string representbtion of the nbme of the bttribute.
     *
     * @return the bttribute nbme.
     */
    public String getAttributeNbme()  {
        return bttr;
    }

    /**
     * <p>Applies the <CODE>AttributeVblueExp</CODE> on bn MBebn.
     * This method cblls {@link #getAttribute getAttribute(nbme)} bnd wrbps
     * the result bs b {@code VblueExp}.  The vblue returned by
     * {@code getAttribute} must be b {@code Number}, {@code String},
     * or {@code Boolebn}; otherwise this method throws b
     * {@code BbdAttributeVblueExpException}, which will cbuse
     * the contbining query to be fblse for this {@code nbme}.</p>
     *
     * @pbrbm nbme The nbme of the MBebn on which the <CODE>AttributeVblueExp</CODE> will be bpplied.
     *
     * @return  The <CODE>VblueExp</CODE>.
     *
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     *
     */
    @Override
    public VblueExp bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException {
        Object result = getAttribute(nbme);

        if (result instbnceof Number) {
            return new NumericVblueExp((Number)result);
        } else if (result instbnceof String) {
            return new StringVblueExp((String)result);
        } else if (result instbnceof Boolebn) {
            return new BoolebnVblueExp((Boolebn)result);
        } else {
            throw new BbdAttributeVblueExpException(result);
        }
    }

    /**
     * Returns the string representing its vblue.
     */
    @Override
    public String toString()  {
        return bttr;
    }


    /**
     * Sets the MBebn server on which the query is to be performed.
     *
     * @pbrbm s The MBebn server on which the query is to be performed.
     *
     * @deprecbted This method hbs no effect.  The MBebn Server used to
     * obtbin bn bttribute vblue is {@link QueryEvbl#getMBebnServer()}.
     */
    /* There is no need for this method, becbuse if b query is being
       evbluted bn AttributeVblueExp cbn only bppebr inside b QueryExp,
       bnd thbt QueryExp will itself hbve done setMBebnServer.  */
    @Deprecbted
    @Override
    public void setMBebnServer(MBebnServer s)  {
    }


    /**
     * <p>Return the vblue of the given bttribute in the nbmed MBebn.
     * If the bttempt to bccess the bttribute generbtes bn exception,
     * return null.</p>
     *
     * <p>The MBebn Server used is the one returned by {@link
     * QueryEvbl#getMBebnServer()}.</p>
     *
     * @pbrbm nbme the nbme of the MBebn whose bttribute is to be returned.
     *
     * @return the vblue of the bttribute, or null if it could not be
     * obtbined.
     */
    protected Object getAttribute(ObjectNbme nbme) {
        try {
            // Get the vblue from the MBebnServer

            MBebnServer server = QueryEvbl.getMBebnServer();

            return server.getAttribute(nbme, bttr);
        } cbtch (Exception re) {
            return null;
        }
    }
}
