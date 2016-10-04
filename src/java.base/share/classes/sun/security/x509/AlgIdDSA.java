/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.security.interfbces.DSAPbrbms;

import sun.security.util.*;


/**
 * This clbss identifies DSS/DSA Algorithm vbribnts, which bre distinguished
 * by using different blgorithm pbrbmeters <em>P, Q, G</em>.  It uses the
 * NIST/IETF stbndbrd DER encoding.  These bre used to implement the Digitbl
 * Signbture Stbndbrd (DSS), FIPS 186.
 *
 * <P><em><b>NOTE:</b>  DSS/DSA Algorithm IDs mby be crebted without these
 * pbrbmeters.  Use of DSS/DSA in modes where pbrbmeters bre
 * either implicit (e.g. b defbult bpplicbble to b site or b lbrger scope),
 * or bre derived from some Certificbte Authority's DSS certificbte, is
 * not supported directly.  The bpplicbtion is responsible for crebting b key
 * contbining the required pbrbmeters prior to using the key in cryptogrbphic
 * operbtions.  The follwoing is bn exbmple of how this mby be done bssuming
 * thbt we hbve b certificbte cblled <code>currentCert</code> which doesn't
 * contbin DSS/DSA pbrbmeters bnd we need to derive DSS/DSA pbrbmeters
 * from b CA's certificbte cblled <code>cbCert</code>.
 * <p>
 * <code><pre>
 * // key contbining pbrbmeters to use
 * DSAPublicKey cAKey = (DSAPublicKey)(cbCert.getPublicKey());
 * // key without pbrbmeters
 * DSAPublicKey nullPbrbmsKey = (DSAPublicKey)(currentCert.getPublicKey());
 *
 * DSAPbrbms cAKeyPbrbms = cAKey.getPbrbms();
 * KeyFbctory kf = KeyFbctory.getInstbnce("DSA");
 * DSAPublicKeySpec ks = new DSAPublicKeySpec(nullPbrbmsKey.getY(),
 *                                            cAKeyPbrbms.getP(),
 *                                            cAKeyPbrbms.getQ(),
 *                                            cAKeyPbrbms.getG());
 * DSAPublicKey usbbleKey = kf.generbtePublic(ks);
 * </pre></code>
 *
 * @see jbvb.security.interfbces.DSAPbrbms
 * @see jbvb.security.interfbces.DSAPublicKey
 * @see jbvb.security.KeyFbctory
 * @see jbvb.security.spec.DSAPublicKeySpec
 *
 * @buthor Dbvid Brownell
 */
public finbl
clbss AlgIdDSA extends AlgorithmId implements DSAPbrbms
{

    privbte stbtic finbl long seriblVersionUID = 3437177836797504046L;

    /*
     * The three unsigned integer pbrbmeters.
     */
    privbte BigInteger  p , q, g;

    /** Returns the DSS/DSA pbrbmeter "P" */
    public BigInteger   getP () { return p; }

    /** Returns the DSS/DSA pbrbmeter "Q" */
    public BigInteger   getQ () { return q; }

    /** Returns the DSS/DSA pbrbmeter "G" */
    public BigInteger   getG () { return g; }

    /**
     * Defbult constructor.  The OID bnd pbrbmeters must be
     * deseriblized before this blgorithm ID is used.
     */
    @Deprecbted
    public AlgIdDSA () {}

    AlgIdDSA (DerVblue vbl) throws IOException
        { super(vbl.getOID()); }

    /**
     * Construct bn AlgIdDSA from bn X.509 encoded byte brrby.
     */
    public AlgIdDSA (byte[] encodedAlg) throws IOException
        { super (new DerVblue(encodedAlg).getOID()); }

    /**
     * Constructs b DSS/DSA Algorithm ID from unsigned integers thbt
     * define the blgorithm pbrbmeters.  Those integers bre encoded
     * bs big-endibn byte brrbys.
     *
     * @pbrbm p the DSS/DSA pbrbmeter "P"
     * @pbrbm q the DSS/DSA pbrbmeter "Q"
     * @pbrbm g the DSS/DSA pbrbmeter "G"
     */
    public AlgIdDSA (byte p [], byte q [], byte g [])
    throws IOException
    {
        this (new BigInteger (1, p),
            new BigInteger (1, q),
            new BigInteger (1, g));
    }

    /**
     * Constructs b DSS/DSA Algorithm ID from numeric pbrbmeters.
     * If bll three bre null, then the pbrbmeters portion of the blgorithm id
     * is set to null.  See note in hebder regbrding use.
     *
     * @pbrbm p the DSS/DSA pbrbmeter "P"
     * @pbrbm q the DSS/DSA pbrbmeter "Q"
     * @pbrbm g the DSS/DSA pbrbmeter "G"
     */
    public AlgIdDSA (BigInteger p, BigInteger q, BigInteger g)
    {
        super (DSA_oid);

        if (p != null || q != null || g != null) {
            if (p == null || q == null || g == null)
                throw new ProviderException("Invblid pbrbmeters for DSS/DSA" +
                                            " Algorithm ID");
            try {
                this.p = p;
                this.q = q;
                this.g = g;
                initiblizePbrbms ();

            } cbtch (IOException e) {
                /* this should not hbppen */
                throw new ProviderException ("Construct DSS/DSA Algorithm ID");
            }
        }
    }

    /**
     * Returns "DSA", indicbting the Digitbl Signbture Algorithm (DSA) bs
     * defined by the Digitbl Signbture Stbndbrd (DSS), FIPS 186.
     */
    public String getNbme ()
        { return "DSA"; }


    /*
     * For blgorithm IDs which hbven't been crebted from b DER encoded
     * vblue, "pbrbms" must be crebted.
     */
    privbte void initiblizePbrbms ()
    throws IOException
    {
        DerOutputStrebm out = new DerOutputStrebm ();

        out.putInteger(p);
        out.putInteger(q);
        out.putInteger(g);
        pbrbms = new DerVblue (DerVblue.tbg_Sequence,out.toByteArrby ());
    }

    /**
     * Pbrses blgorithm pbrbmeters P, Q, bnd G.  They're found
     * in the "pbrbms" member, which never needs to be chbnged.
     */
    protected void decodePbrbms ()
    throws IOException
    {
        if (pbrbms == null)
            throw new IOException("DSA blg pbrbms bre null");
        if (pbrbms.tbg != DerVblue.tbg_Sequence)
            throw new  IOException("DSA blg pbrsing error");

        pbrbms.dbtb.reset ();

        this.p = pbrbms.dbtb.getBigInteger();
        this.q = pbrbms.dbtb.getBigInteger();
        this.g = pbrbms.dbtb.getBigInteger();

        if (pbrbms.dbtb.bvbilbble () != 0)
            throw new IOException ("AlgIdDSA pbrbms, extrb="+
                                   pbrbms.dbtb.bvbilbble ());
    }


    /*
     * Returns b formbtted string describing the pbrbmeters.
     */
    public String toString ()
        { return pbrbmsToString (); }

    /*
     * Returns b string describing the pbrbmeters.
     */
    protected String pbrbmsToString ()
    {
        if (pbrbms == null)
            return " null\n";
        else
            return
                "\n    p:\n" + Debug.toHexString(p) +
                "\n    q:\n" + Debug.toHexString(q) +
                "\n    g:\n" + Debug.toHexString(g) +
                "\n";
    }
}
