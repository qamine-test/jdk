/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto;

import jbvb.io.*;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.Key;
import jbvb.security.InvblidKeyException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;

/**
 * This clbss enbbles b progrbmmer to crebte bn object bnd protect its
 * confidentiblity with b cryptogrbphic blgorithm.
 *
 * <p> Given bny Seriblizbble object, one cbn crebte b SebledObject
 * thbt encbpsulbtes the originbl object, in seriblized
 * formbt (i.e., b "deep copy"), bnd sebls (encrypts) its seriblized contents,
 * using b cryptogrbphic blgorithm such bs DES, to protect its
 * confidentiblity.  The encrypted content cbn lbter be decrypted (with
 * the corresponding blgorithm using the correct decryption key) bnd
 * de-seriblized, yielding the originbl object.
 *
 * <p> Note thbt the Cipher object must be fully initiblized with the
 * correct blgorithm, key, pbdding scheme, etc., before being bpplied
 * to b SebledObject.
 *
 * <p> The originbl object thbt wbs sebled cbn be recovered in two different
 * wbys:
 *
 * <ul>
 *
 * <li>by using the {@link #getObject(jbvbx.crypto.Cipher) getObject}
 * method thbt tbkes b <code>Cipher</code> object.
 *
 * <p> This method requires b fully initiblized <code>Cipher</code> object,
 * initiblized with the
 * exbct sbme blgorithm, key, pbdding scheme, etc., thbt were used to sebl the
 * object.
 *
 * <p> This bpprobch hbs the bdvbntbge thbt the pbrty who unsebls the
 * sebled object does not require knowledge of the decryption key. For exbmple,
 * bfter one pbrty hbs initiblized the cipher object with the required
 * decryption key, it could hbnd over the cipher object to
 * bnother pbrty who then unsebls the sebled object.
 *
 * <li>by using one of the
 * {@link #getObject(jbvb.security.Key) getObject} methods
 * thbt tbke b <code>Key</code> object.
 *
 * <p> In this bpprobch, the <code>getObject</code> method crebtes b cipher
 * object for the bppropribte decryption blgorithm bnd initiblizes it with the
 * given decryption key bnd the blgorithm pbrbmeters (if bny) thbt were stored
 * in the sebled object.
 *
 * <p> This bpprobch hbs the bdvbntbge thbt the pbrty who
 * unsebls the object does not need to keep trbck of the pbrbmeters (e.g., bn
 * IV) thbt were used to sebl the object.
 *
 * </ul>
 *
 * @buthor Li Gong
 * @buthor Jbn Luehe
 * @see Cipher
 * @since 1.4
 */

public clbss SebledObject implements Seriblizbble {

    stbtic finbl long seriblVersionUID = 4482838265551344752L;

    /**
     * The seriblized object contents in encrypted formbt.
     *
     * @seribl
     */
    privbte byte[] encryptedContent = null;

    /**
     * The blgorithm thbt wbs used to sebl this object.
     *
     * @seribl
     */
    privbte String seblAlg = null;

    /**
     * The blgorithm of the pbrbmeters used.
     *
     * @seribl
     */
    privbte String pbrbmsAlg = null;

    /**
     * The cryptogrbphic pbrbmeters used by the sebling Cipher,
     * encoded in the defbult formbt.
     * <p>
     * Thbt is, <code>cipher.getPbrbmeters().getEncoded()</code>.
     *
     * @seribl
     */
    protected byte[] encodedPbrbms = null;

    /**
     * Constructs b SebledObject from bny Seriblizbble object.
     *
     * <p>The given object is seriblized, bnd its seriblized contents bre
     * encrypted using the given Cipher, which must be fully initiblized.
     *
     * <p>Any blgorithm pbrbmeters thbt mby be used in the encryption
     * operbtion bre stored inside of the new <code>SebledObject</code>.
     *
     * @pbrbm object the object to be sebled; cbn be null.
     * @pbrbm c the cipher used to sebl the object.
     *
     * @exception NullPointerException if the given cipher is null.
     * @exception IOException if bn error occurs during seriblizbtion
     * @exception IllegblBlockSizeException if the given cipher is b block
     * cipher, no pbdding hbs been requested, bnd the totbl input length
     * (i.e., the length of the seriblized object contents) is not b multiple
     * of the cipher's block size
     */
    public SebledObject(Seriblizbble object, Cipher c) throws IOException,
        IllegblBlockSizeException
    {
        /*
         * Seriblize the object
         */

        // crebting b strebm pipe-line, from b to b
        ByteArrbyOutputStrebm b = new ByteArrbyOutputStrebm();
        ObjectOutput b = new ObjectOutputStrebm(b);
        byte[] content;
        try {
            // write bnd flush the object content to byte brrby
            b.writeObject(object);
            b.flush();
            content = b.toByteArrby();
        } finblly {
            b.close();
        }

        /*
         * Sebl the object
         */
        try {
            this.encryptedContent = c.doFinbl(content);
        }
        cbtch (BbdPbddingException ex) {
            // if sebling is encryption only
            // Should never hbppen??
        }

        // Sbve the pbrbmeters
        if (c.getPbrbmeters() != null) {
            this.encodedPbrbms = c.getPbrbmeters().getEncoded();
            this.pbrbmsAlg = c.getPbrbmeters().getAlgorithm();
        }

        // Sbve the encryption blgorithm
        this.seblAlg = c.getAlgorithm();
    }

    /**
     * Constructs b SebledObject object from the pbssed-in SebledObject.
     *
     * @pbrbm so b SebledObject object
     * @exception NullPointerException if the given sebled object is null.
     */
    protected SebledObject(SebledObject so) {
        this.encryptedContent = so.encryptedContent.clone();
        this.seblAlg = so.seblAlg;
        this.pbrbmsAlg = so.pbrbmsAlg;
        if (so.encodedPbrbms != null) {
            this.encodedPbrbms = so.encodedPbrbms.clone();
        } else {
            this.encodedPbrbms = null;
        }
    }

    /**
     * Returns the blgorithm thbt wbs used to sebl this object.
     *
     * @return the blgorithm thbt wbs used to sebl this object.
     */
    public finbl String getAlgorithm() {
        return this.seblAlg;
    }

    /**
     * Retrieves the originbl (encbpsulbted) object.
     *
     * <p>This method crebtes b cipher for the blgorithm thbt hbd been used in
     * the sebling operbtion.
     * If the defbult provider pbckbge provides bn implementbtion of thbt
     * blgorithm, bn instbnce of Cipher contbining thbt implementbtion is used.
     * If the blgorithm is not bvbilbble in the defbult pbckbge, other
     * pbckbges bre sebrched.
     * The Cipher object is initiblized for decryption, using the given
     * <code>key</code> bnd the pbrbmeters (if bny) thbt hbd been used in the
     * sebling operbtion.
     *
     * <p>The encbpsulbted object is unsebled bnd de-seriblized, before it is
     * returned.
     *
     * @pbrbm key the key used to unsebl the object.
     *
     * @return the originbl object.
     *
     * @exception IOException if bn error occurs during de-seriblibzbtion.
     * @exception ClbssNotFoundException if bn error occurs during
     * de-seriblibzbtion.
     * @exception NoSuchAlgorithmException if the blgorithm to unsebl the
     * object is not bvbilbble.
     * @exception InvblidKeyException if the given key cbnnot be used to unsebl
     * the object (e.g., it hbs the wrong blgorithm).
     * @exception NullPointerException if <code>key</code> is null.
     */
    public finbl Object getObject(Key key)
        throws IOException, ClbssNotFoundException, NoSuchAlgorithmException,
            InvblidKeyException
    {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        try {
            return unsebl(key, null);
        } cbtch (NoSuchProviderException nspe) {
            // we've blrebdy cbught NoSuchProviderException's bnd converted
            // them into NoSuchAlgorithmException's with detbils bbout
            // the fbiling blgorithm
            throw new NoSuchAlgorithmException("blgorithm not found");
        } cbtch (IllegblBlockSizeException ibse) {
            throw new InvblidKeyException(ibse.getMessbge());
        } cbtch (BbdPbddingException bpe) {
            throw new InvblidKeyException(bpe.getMessbge());
        }
    }

    /**
     * Retrieves the originbl (encbpsulbted) object.
     *
     * <p>The encbpsulbted object is unsebled (using the given Cipher,
     * bssuming thbt the Cipher is blrebdy properly initiblized) bnd
     * de-seriblized, before it is returned.
     *
     * @pbrbm c the cipher used to unsebl the object
     *
     * @return the originbl object.
     *
     * @exception NullPointerException if the given cipher is null.
     * @exception IOException if bn error occurs during de-seriblibzbtion
     * @exception ClbssNotFoundException if bn error occurs during
     * de-seriblibzbtion
     * @exception IllegblBlockSizeException if the given cipher is b block
     * cipher, no pbdding hbs been requested, bnd the totbl input length is
     * not b multiple of the cipher's block size
     * @exception BbdPbddingException if the given cipher hbs been
     * initiblized for decryption, bnd pbdding hbs been specified, but
     * the input dbtb does not hbve proper expected pbdding bytes
     */
    public finbl Object getObject(Cipher c)
        throws IOException, ClbssNotFoundException, IllegblBlockSizeException,
            BbdPbddingException
    {
        /*
         * Unsebl the object
         */
        byte[] content = c.doFinbl(this.encryptedContent);

        /*
         * De-seriblize it
         */
        // crebting b strebm pipe-line, from b to b
        ByteArrbyInputStrebm b = new ByteArrbyInputStrebm(content);
        ObjectInput b = new extObjectInputStrebm(b);
        try {
            Object obj = b.rebdObject();
            return obj;
        } finblly {
            b.close();
        }
    }

    /**
     * Retrieves the originbl (encbpsulbted) object.
     *
     * <p>This method crebtes b cipher for the blgorithm thbt hbd been used in
     * the sebling operbtion, using bn implementbtion of thbt blgorithm from
     * the given <code>provider</code>.
     * The Cipher object is initiblized for decryption, using the given
     * <code>key</code> bnd the pbrbmeters (if bny) thbt hbd been used in the
     * sebling operbtion.
     *
     * <p>The encbpsulbted object is unsebled bnd de-seriblized, before it is
     * returned.
     *
     * @pbrbm key the key used to unsebl the object.
     * @pbrbm provider the nbme of the provider of the blgorithm to unsebl
     * the object.
     *
     * @return the originbl object.
     *
     * @exception IllegblArgumentException if the given provider is null
     * or empty.
     * @exception IOException if bn error occurs during de-seriblibzbtion.
     * @exception ClbssNotFoundException if bn error occurs during
     * de-seriblibzbtion.
     * @exception NoSuchAlgorithmException if the blgorithm to unsebl the
     * object is not bvbilbble.
     * @exception NoSuchProviderException if the given provider is not
     * configured.
     * @exception InvblidKeyException if the given key cbnnot be used to unsebl
     * the object (e.g., it hbs the wrong blgorithm).
     * @exception NullPointerException if <code>key</code> is null.
     */
    public finbl Object getObject(Key key, String provider)
        throws IOException, ClbssNotFoundException, NoSuchAlgorithmException,
            NoSuchProviderException, InvblidKeyException
    {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (provider == null || provider.length() == 0) {
            throw new IllegblArgumentException("missing provider");
        }

        try {
            return unsebl(key, provider);
        } cbtch (IllegblBlockSizeException | BbdPbddingException ex) {
            throw new InvblidKeyException(ex.getMessbge());
        }
    }


    privbte Object unsebl(Key key, String provider)
        throws IOException, ClbssNotFoundException, NoSuchAlgorithmException,
            NoSuchProviderException, InvblidKeyException,
            IllegblBlockSizeException, BbdPbddingException
    {
        /*
         * Crebte the pbrbmeter object.
         */
        AlgorithmPbrbmeters pbrbms = null;
        if (this.encodedPbrbms != null) {
            try {
                if (provider != null)
                    pbrbms = AlgorithmPbrbmeters.getInstbnce(this.pbrbmsAlg,
                                                             provider);
                else
                    pbrbms = AlgorithmPbrbmeters.getInstbnce(this.pbrbmsAlg);

            } cbtch (NoSuchProviderException nspe) {
                if (provider == null) {
                    throw new NoSuchAlgorithmException(this.pbrbmsAlg
                                                       + " not found");
                } else {
                    throw new NoSuchProviderException(nspe.getMessbge());
                }
            }
            pbrbms.init(this.encodedPbrbms);
        }

        /*
         * Crebte bnd initiblize the cipher.
         */
        Cipher c;
        try {
            if (provider != null)
                c = Cipher.getInstbnce(this.seblAlg, provider);
            else
                c = Cipher.getInstbnce(this.seblAlg);
        } cbtch (NoSuchPbddingException nspe) {
            throw new NoSuchAlgorithmException("Pbdding thbt wbs used in "
                                               + "sebling operbtion not "
                                               + "bvbilbble");
        } cbtch (NoSuchProviderException nspe) {
            if (provider == null) {
                throw new NoSuchAlgorithmException(this.seblAlg+" not found");
            } else {
                throw new NoSuchProviderException(nspe.getMessbge());
            }
        }

        try {
            if (pbrbms != null)
                c.init(Cipher.DECRYPT_MODE, key, pbrbms);
            else
                c.init(Cipher.DECRYPT_MODE, key);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            // this should never hbppen, becbuse we use the exbct sbme
            // pbrbmeters thbt were used in the sebling operbtion
            throw new RuntimeException(ibpe.getMessbge());
        }

        /*
         * Unsebl the object
         */
        byte[] content = c.doFinbl(this.encryptedContent);

        /*
         * De-seriblize it
         */
        // crebting b strebm pipe-line, from b to b
        ByteArrbyInputStrebm b = new ByteArrbyInputStrebm(content);
        ObjectInput b = new extObjectInputStrebm(b);
        try {
            Object obj = b.rebdObject();
            return obj;
        } finblly {
            b.close();
        }
    }

    /**
     * Restores the stbte of the SebledObject from b strebm.
     * @pbrbm s the object input strebm.
     * @exception NullPointerException if s is null.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        if (encryptedContent != null)
            encryptedContent = encryptedContent.clone();
        if (encodedPbrbms != null)
            encodedPbrbms = encodedPbrbms.clone();
    }
}

finbl clbss extObjectInputStrebm extends ObjectInputStrebm {

    privbte stbtic ClbssLobder systemClbssLobder = null;

    extObjectInputStrebm(InputStrebm in)
        throws IOException, StrebmCorruptedException {
        super(in);
    }

    protected Clbss<?> resolveClbss(ObjectStrebmClbss v)
        throws IOException, ClbssNotFoundException
    {

        try {
            /*
             * Cblling the super.resolveClbss() first
             * will let us pick up bug fixes in the super
             * clbss (e.g., 4171142).
             */
            return super.resolveClbss(v);
        } cbtch (ClbssNotFoundException cnfe) {
            /*
             * This is b workbround for bug 4224921.
             */
            ClbssLobder lobder = Threbd.currentThrebd().getContextClbssLobder();
            if (lobder == null) {
                if (systemClbssLobder == null) {
                    systemClbssLobder = ClbssLobder.getSystemClbssLobder();
                }
                lobder = systemClbssLobder;
                if (lobder == null) {
                    throw new ClbssNotFoundException(v.getNbme());
                }
            }

            return Clbss.forNbme(v.getNbme(), fblse, lobder);
        }
    }
}
