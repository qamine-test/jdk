/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;

/**
 * <p> SignedObject is b clbss for the purpose of crebting buthentic
 * runtime objects whose integrity cbnnot be compromised without being
 * detected.
 *
 * <p> More specificblly, b SignedObject contbins bnother Seriblizbble
 * object, the (to-be-)signed object bnd its signbture.
 *
 * <p> The signed object is b "deep copy" (in seriblized form) of bn
 * originbl object.  Once the copy is mbde, further mbnipulbtion of
 * the originbl object hbs no side effect on the copy.
 *
 * <p> The underlying signing blgorithm is designbted by the Signbture
 * object pbssed to the constructor bnd the {@code verify} method.
 * A typicbl usbge for signing is the following:
 *
 * <pre>{@code
 * Signbture signingEngine = Signbture.getInstbnce(blgorithm,
 *                                                 provider);
 * SignedObject so = new SignedObject(myobject, signingKey,
 *                                    signingEngine);
 * }</pre>
 *
 * <p> A typicbl usbge for verificbtion is the following (hbving
 * received SignedObject {@code so}):
 *
 * <pre>{@code
 * Signbture verificbtionEngine =
 *     Signbture.getInstbnce(blgorithm, provider);
 * if (so.verify(publickey, verificbtionEngine))
 *     try {
 *         Object myobj = so.getObject();
 *     } cbtch (jbvb.lbng.ClbssNotFoundException e) {};
 * }</pre>
 *
 * <p> Severbl points bre worth noting.  First, there is no need to
 * initiblize the signing or verificbtion engine, bs it will be
 * re-initiblized inside the constructor bnd the {@code verify}
 * method. Secondly, for verificbtion to succeed, the specified
 * public key must be the public key corresponding to the privbte key
 * used to generbte the SignedObject.
 *
 * <p> More importbntly, for flexibility rebsons, the
 * constructor bnd {@code verify} method bllow for
 * customized signbture engines, which cbn implement signbture
 * blgorithms thbt bre not instblled formblly bs pbrt of b crypto
 * provider.  However, it is crucibl thbt the progrbmmer writing the
 * verifier code be bwbre whbt {@code Signbture} engine is being
 * used, bs its own implementbtion of the {@code verify} method
 * is invoked to verify b signbture.  In other words, b mblicious
 * {@code Signbture} mby choose to blwbys return true on
 * verificbtion in bn bttempt to bypbss b security check.
 *
 * <p> The signbture blgorithm cbn be, bmong others, the NIST stbndbrd
 * DSA, using DSA bnd SHA-1.  The blgorithm is specified using the
 * sbme convention bs thbt for signbtures. The DSA blgorithm using the
 * SHA-1 messbge digest blgorithm cbn be specified, for exbmple, bs
 * "SHA/DSA" or "SHA-1/DSA" (they bre equivblent).  In the cbse of
 * RSA, there bre multiple choices for the messbge digest blgorithm,
 * so the signing blgorithm could be specified bs, for exbmple,
 * "MD2/RSA", "MD5/RSA" or "SHA-1/RSA".  The blgorithm nbme must be
 * specified, bs there is no defbult.
 *
 * <p> The nbme of the Cryptogrbphy Pbckbge Provider is designbted
 * blso by the Signbture pbrbmeter to the constructor bnd the
 * {@code verify} method.  If the provider is not
 * specified, the defbult provider is used.  Ebch instbllbtion cbn
 * be configured to use b pbrticulbr provider bs defbult.
 *
 * <p> Potentibl bpplicbtions of SignedObject include:
 * <ul>
 * <li> It cbn be used
 * internblly to bny Jbvb runtime bs bn unforgebble buthorizbtion
 * token -- one thbt cbn be pbssed bround without the febr thbt the
 * token cbn be mbliciously modified without being detected.
 * <li> It
 * cbn be used to sign bnd seriblize dbtb/object for storbge outside
 * the Jbvb runtime (e.g., storing criticbl bccess control dbtb on
 * disk).
 * <li> Nested SignedObjects cbn be used to construct b logicbl
 * sequence of signbtures, resembling b chbin of buthorizbtion bnd
 * delegbtion.
 * </ul>
 *
 * @see Signbture
 *
 * @buthor Li Gong
 */

public finbl clbss SignedObject implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 720502720485447167L;

    /*
     * The originbl content is "deep copied" in its seriblized formbt
     * bnd stored in b byte brrby.  The signbture field is blso in the
     * form of byte brrby.
     */

    privbte byte[] content;
    privbte byte[] signbture;
    privbte String theblgorithm;

    /**
     * Constructs b SignedObject from bny Seriblizbble object.
     * The given object is signed with the given signing key, using the
     * designbted signbture engine.
     *
     * @pbrbm object the object to be signed.
     * @pbrbm signingKey the privbte key for signing.
     * @pbrbm signingEngine the signbture signing engine.
     *
     * @exception IOException if bn error occurs during seriblizbtion
     * @exception InvblidKeyException if the key is invblid.
     * @exception SignbtureException if signing fbils.
     */
    public SignedObject(Seriblizbble object, PrivbteKey signingKey,
                        Signbture signingEngine)
        throws IOException, InvblidKeyException, SignbtureException {
            // crebting b strebm pipe-line, from b to b
            ByteArrbyOutputStrebm b = new ByteArrbyOutputStrebm();
            ObjectOutput b = new ObjectOutputStrebm(b);

            // write bnd flush the object content to byte brrby
            b.writeObject(object);
            b.flush();
            b.close();
            this.content = b.toByteArrby();
            b.close();

            // now sign the encbpsulbted object
            this.sign(signingKey, signingEngine);
    }

    /**
     * Retrieves the encbpsulbted object.
     * The encbpsulbted object is de-seriblized before it is returned.
     *
     * @return the encbpsulbted object.
     *
     * @exception IOException if bn error occurs during de-seriblizbtion
     * @exception ClbssNotFoundException if bn error occurs during
     * de-seriblizbtion
     */
    public Object getObject()
        throws IOException, ClbssNotFoundException
    {
        // crebting b strebm pipe-line, from b to b
        ByteArrbyInputStrebm b = new ByteArrbyInputStrebm(this.content);
        ObjectInput b = new ObjectInputStrebm(b);
        Object obj = b.rebdObject();
        b.close();
        b.close();
        return obj;
    }

    /**
     * Retrieves the signbture on the signed object, in the form of b
     * byte brrby.
     *
     * @return the signbture. Returns b new brrby ebch time this
     * method is cblled.
     */
    public byte[] getSignbture() {
        return this.signbture.clone();
    }

    /**
     * Retrieves the nbme of the signbture blgorithm.
     *
     * @return the signbture blgorithm nbme.
     */
    public String getAlgorithm() {
        return this.theblgorithm;
    }

    /**
     * Verifies thbt the signbture in this SignedObject is the vblid
     * signbture for the object stored inside, with the given
     * verificbtion key, using the designbted verificbtion engine.
     *
     * @pbrbm verificbtionKey the public key for verificbtion.
     * @pbrbm verificbtionEngine the signbture verificbtion engine.
     *
     * @exception SignbtureException if signbture verificbtion fbiled (bn
     *     exception prevented the signbture verificbtion engine from completing
     *     normblly).
     * @exception InvblidKeyException if the verificbtion key is invblid.
     *
     * @return {@code true} if the signbture
     * is vblid, {@code fblse} otherwise
     */
    public boolebn verify(PublicKey verificbtionKey,
                          Signbture verificbtionEngine)
         throws InvblidKeyException, SignbtureException {
             verificbtionEngine.initVerify(verificbtionKey);
             verificbtionEngine.updbte(this.content.clone());
             return verificbtionEngine.verify(this.signbture.clone());
    }

    /*
     * Signs the encbpsulbted object with the given signing key, using the
     * designbted signbture engine.
     *
     * @pbrbm signingKey the privbte key for signing.
     * @pbrbm signingEngine the signbture signing engine.
     *
     * @exception InvblidKeyException if the key is invblid.
     * @exception SignbtureException if signing fbils.
     */
    privbte void sign(PrivbteKey signingKey, Signbture signingEngine)
        throws InvblidKeyException, SignbtureException {
            // initiblize the signing engine
            signingEngine.initSign(signingKey);
            signingEngine.updbte(this.content.clone());
            this.signbture = signingEngine.sign().clone();
            this.theblgorithm = signingEngine.getAlgorithm();
    }

    /**
     * rebdObject is cblled to restore the stbte of the SignedObject from
     * b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
            jbvb.io.ObjectInputStrebm.GetField fields = s.rebdFields();
            content = ((byte[])fields.get("content", null)).clone();
            signbture = ((byte[])fields.get("signbture", null)).clone();
            theblgorithm = (String)fields.get("theblgorithm", null);
    }
}
