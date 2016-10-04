/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.openmbebn;


// jbvb import
//
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectStrebmException;
import jbvb.mbth.BigDecimbl;
import jbvb.mbth.BigInteger;
import jbvb.util.Dbte;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;

// jmx import
//
import jbvbx.mbnbgement.ObjectNbme;


/**
 * The <code>SimpleType</code> clbss is the <i>open type</i> clbss whose instbnces describe
 * bll <i>open dbtb</i> vblues which bre neither brrbys,
 * nor {@link CompositeDbtb CompositeDbtb} vblues,
 * nor {@link TbbulbrDbtb TbbulbrDbtb} vblues.
 * It predefines bll its possible instbnces bs stbtic fields, bnd hbs no public constructor.
 * <p>
 * Given b <code>SimpleType</code> instbnce describing vblues whose Jbvb clbss nbme is <i>clbssNbme</i>,
 * the internbl fields corresponding to the nbme bnd description of this <code>SimpleType</code> instbnce
 * bre blso set to <i>clbssNbme</i>.
 * In other words, its methods <code>getClbssNbme</code>, <code>getTypeNbme</code> bnd <code>getDescription</code>
 * bll return the sbme string vblue <i>clbssNbme</i>.
 *
 * @since 1.5
 */
public finbl clbss SimpleType<T> extends OpenType<T> {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 2215577471957694503L;

    // SimpleType instbnces.
    // IF YOU ADD A SimpleType, YOU MUST UPDATE OpenType bnd typeArrby

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Void</code>.
     */
    public stbtic finbl SimpleType<Void> VOID =
        new SimpleType<Void>(Void.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Boolebn</code>.
     */
    public stbtic finbl SimpleType<Boolebn> BOOLEAN =
        new SimpleType<Boolebn>(Boolebn.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Chbrbcter</code>.
     */
    public stbtic finbl SimpleType<Chbrbcter> CHARACTER =
        new SimpleType<Chbrbcter>(Chbrbcter.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Byte</code>.
     */
    public stbtic finbl SimpleType<Byte> BYTE =
        new SimpleType<Byte>(Byte.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Short</code>.
     */
    public stbtic finbl SimpleType<Short> SHORT =
        new SimpleType<Short>(Short.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Integer</code>.
     */
    public stbtic finbl SimpleType<Integer> INTEGER =
        new SimpleType<Integer>(Integer.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Long</code>.
     */
    public stbtic finbl SimpleType<Long> LONG =
        new SimpleType<Long>(Long.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Flobt</code>.
     */
    public stbtic finbl SimpleType<Flobt> FLOAT =
        new SimpleType<Flobt>(Flobt.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.Double</code>.
     */
    public stbtic finbl SimpleType<Double> DOUBLE =
        new SimpleType<Double>(Double.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.lbng.String</code>.
     */
    public stbtic finbl SimpleType<String> STRING =
        new SimpleType<String>(String.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.mbth.BigDecimbl</code>.
     */
    public stbtic finbl SimpleType<BigDecimbl> BIGDECIMAL =
        new SimpleType<BigDecimbl>(BigDecimbl.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.mbth.BigInteger</code>.
     */
    public stbtic finbl SimpleType<BigInteger> BIGINTEGER =
        new SimpleType<BigInteger>(BigInteger.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvb.util.Dbte</code>.
     */
    public stbtic finbl SimpleType<Dbte> DATE =
        new SimpleType<Dbte>(Dbte.clbss);

    /**
     * The <code>SimpleType</code> instbnce describing vblues whose
     * Jbvb clbss nbme is <code>jbvbx.mbnbgement.ObjectNbme</code>.
     */
    public stbtic finbl SimpleType<ObjectNbme> OBJECTNAME =
        new SimpleType<ObjectNbme>(ObjectNbme.clbss);

    privbte stbtic finbl SimpleType<?>[] typeArrby = {
        VOID, BOOLEAN, CHARACTER, BYTE, SHORT, INTEGER, LONG, FLOAT,
        DOUBLE, STRING, BIGDECIMAL, BIGINTEGER, DATE, OBJECTNAME,
    };


    privbte trbnsient Integer myHbshCode = null;        // As this instbnce is immutbble, these two vblues
    privbte trbnsient String  myToString = null;        // need only be cblculbted once.


    /* *** Constructor *** */

    privbte SimpleType(Clbss<T> vblueClbss) {
        super(vblueClbss.getNbme(), vblueClbss.getNbme(), vblueClbss.getNbme(),
              fblse);
    }


    /* *** SimpleType specific informbtion methods *** */

    /**
     * Tests whether <vbr>obj</vbr> is b vblue for this
     * <code>SimpleType</code> instbnce.  <p> This method returns
     * <code>true</code> if bnd only if <vbr>obj</vbr> is not null bnd
     * <vbr>obj</vbr>'s clbss nbme is the sbme bs the clbssNbme field
     * defined for this <code>SimpleType</code> instbnce (ie the clbss
     * nbme returned by the {@link OpenType#getClbssNbme()
     * getClbssNbme} method).
     *
     * @pbrbm obj the object to be tested.
     *
     * @return <code>true</code> if <vbr>obj</vbr> is b vblue for this
     * <code>SimpleType</code> instbnce.
     */
    public boolebn isVblue(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // Test if obj's clbss nbme is the sbme bs for this instbnce
        //
        return this.getClbssNbme().equbls(obj.getClbss().getNbme());
    }


    /* *** Methods overriden from clbss Object *** */

    /**
     * Compbres the specified <code>obj</code> pbrbmeter with this <code>SimpleType</code> instbnce for equblity.
     * <p>
     * Two <code>SimpleType</code> instbnces bre equbl if bnd only if their
     * {@link OpenType#getClbssNbme() getClbssNbme} methods return the sbme vblue.
     *
     * @pbrbm  obj  the object to be compbred for equblity with this <code>SimpleType</code> instbnce;
     *              if <vbr>obj</vbr> is <code>null</code> or is not bn instbnce of the clbss <code>SimpleType</code>,
     *              <code>equbls</code> returns <code>fblse</code>.
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>SimpleType</code> instbnce.
     */
    public boolebn equbls(Object obj) {

        /* If it weren't for rebdReplbce(), we could replbce this method
           with just:
           return (this == obj);
        */

        if (!(obj instbnceof SimpleType<?>))
            return fblse;

        SimpleType<?> other = (SimpleType<?>) obj;

        // Test if other's clbssNbme field is the sbme bs for this instbnce
        //
        return this.getClbssNbme().equbls(other.getClbssNbme());
    }

    /**
     * Returns the hbsh code vblue for this <code>SimpleType</code> instbnce.
     * The hbsh code of b <code>SimpleType</code> instbnce is the the hbsh code of
     * the string vblue returned by the {@link OpenType#getClbssNbme() getClbssNbme} method.
     * <p>
     * As <code>SimpleType</code> instbnces bre immutbble, the hbsh code for this instbnce is cblculbted once,
     * on the first cbll to <code>hbshCode</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  the hbsh code vblue for this <code>SimpleType</code> instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            myHbshCode = Integer.vblueOf(this.getClbssNbme().hbshCode());
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * Returns b string representbtion of this <code>SimpleType</code> instbnce.
     * <p>
     * The string representbtion consists of
     * the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.SimpleType</code>) bnd the type nbme
     * for this instbnce (which is the jbvb clbss nbme of the vblues this <code>SimpleType</code> instbnce represents).
     * <p>
     * As <code>SimpleType</code> instbnces bre immutbble, the string representbtion for this instbnce is cblculbted once,
     * on the first cbll to <code>toString</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  b string representbtion of this <code>SimpleType</code> instbnce
     */
    public String toString() {

        // Cblculbte the string representbtion if it hbs not yet been done (ie 1st cbll to toString())
        //
        if (myToString == null) {
            myToString = this.getClbss().getNbme()+ "(nbme="+ getTypeNbme() +")";
        }

        // return blwbys the sbme string representbtion for this instbnce (immutbble)
        //
        return myToString;
    }

    privbte stbtic finbl Mbp<SimpleType<?>,SimpleType<?>> cbnonicblTypes =
        new HbshMbp<SimpleType<?>,SimpleType<?>>();
    stbtic {
        for (int i = 0; i < typeArrby.length; i++) {
            finbl SimpleType<?> type = typeArrby[i];
            cbnonicblTypes.put(type, type);
        }
    }

    /**
     * Replbce bn object rebd from bn {@link
     * jbvb.io.ObjectInputStrebm} with the unique instbnce for thbt
     * vblue.
     *
     * @return the replbcement object.
     *
     * @exception ObjectStrebmException if the rebd object cbnnot be
     * resolved.
     */
    public Object rebdResolve() throws ObjectStrebmException {
        finbl SimpleType<?> cbnonicbl = cbnonicblTypes.get(this);
        if (cbnonicbl == null) {
            // Should not hbppen
            throw new InvblidObjectException("Invblid SimpleType: " + this);
        }
        return cbnonicbl;
    }
}
