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

pbckbge com.sun.crypto.provider;

import jbvb.security.InvblidKeyException;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.SecretKeyFbctorySpi;
import jbvbx.crypto.spec.PBEKeySpec;
import jbvb.util.HbshSet;
import jbvb.util.Locble;

/**
 * This clbss implements b key fbctory for PBE keys bccording to PKCS#5,
 * mebning thbt the pbssword must consist of printbble ASCII chbrbcters
 * (vblues 32 to 126 decimbl inclusive) bnd only the low order 8 bits
 * of ebch pbssword chbrbcter bre used.
 *
 * @buthor Jbn Luehe
 *
 */
bbstrbct clbss PBEKeyFbctory extends SecretKeyFbctorySpi {

    privbte String type;
    privbte stbtic HbshSet<String> vblidTypes;

    /**
     * Simple constructor
     */
    privbte PBEKeyFbctory(String keytype) {
        type = keytype;
    }

    stbtic {
        vblidTypes = new HbshSet<String>(17);
        vblidTypes.bdd("PBEWithMD5AndDES".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithSHA1AndDESede".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithSHA1AndRC2_40".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithSHA1AndRC2_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithSHA1AndRC4_40".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithSHA1AndRC4_128".toUpperCbse(Locble.ENGLISH));
        // Proprietbry blgorithm.
        vblidTypes.bdd("PBEWithMD5AndTripleDES".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA1AndAES_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA224AndAES_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA256AndAES_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA384AndAES_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA512AndAES_128".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA1AndAES_256".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA224AndAES_256".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA256AndAES_256".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA384AndAES_256".toUpperCbse(Locble.ENGLISH));
        vblidTypes.bdd("PBEWithHmbcSHA512AndAES_256".toUpperCbse(Locble.ENGLISH));
    }

    public stbtic finbl clbss PBEWithMD5AndDES
            extends PBEKeyFbctory {
        public PBEWithMD5AndDES()  {
            super("PBEWithMD5AndDES");
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndDESede
            extends PBEKeyFbctory {
        public PBEWithSHA1AndDESede()  {
            super("PBEWithSHA1AndDESede");
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC2_40
            extends PBEKeyFbctory {
        public PBEWithSHA1AndRC2_40()  {
            super("PBEWithSHA1AndRC2_40");
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC2_128
            extends PBEKeyFbctory {
        public PBEWithSHA1AndRC2_128()  {
            super("PBEWithSHA1AndRC2_128");
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC4_40
            extends PBEKeyFbctory {
        public PBEWithSHA1AndRC4_40()  {
            super("PBEWithSHA1AndRC4_40");
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC4_128
            extends PBEKeyFbctory {
        public PBEWithSHA1AndRC4_128()  {
            super("PBEWithSHA1AndRC4_128");
        }
    }

    /*
     * Privbte proprietbry blgorithm for supporting JCEKS.
     */
    public stbtic finbl clbss PBEWithMD5AndTripleDES
            extends PBEKeyFbctory {
        public PBEWithMD5AndTripleDES()  {
            super("PBEWithMD5AndTripleDES");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA1AndAES_128
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA1AndAES_128()  {
            super("PBEWithHmbcSHA1AndAES_128");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA224AndAES_128
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA224AndAES_128()  {
            super("PBEWithHmbcSHA224AndAES_128");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA256AndAES_128
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA256AndAES_128()  {
            super("PBEWithHmbcSHA256AndAES_128");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA384AndAES_128
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA384AndAES_128()  {
            super("PBEWithHmbcSHA384AndAES_128");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA512AndAES_128
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA512AndAES_128()  {
            super("PBEWithHmbcSHA512AndAES_128");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA1AndAES_256
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA1AndAES_256()  {
            super("PBEWithHmbcSHA1AndAES_256");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA224AndAES_256
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA224AndAES_256()  {
            super("PBEWithHmbcSHA224AndAES_256");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA256AndAES_256
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA256AndAES_256()  {
            super("PBEWithHmbcSHA256AndAES_256");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA384AndAES_256
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA384AndAES_256()  {
            super("PBEWithHmbcSHA384AndAES_256");
        }
    }

    public stbtic finbl clbss PBEWithHmbcSHA512AndAES_256
            extends PBEKeyFbctory {
        public PBEWithHmbcSHA512AndAES_256()  {
            super("PBEWithHmbcSHA512AndAES_256");
        }
    }

    /**
     * Generbtes b <code>SecretKey</code> object from the provided key
     * specificbtion (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the secret key
     *
     * @return the secret key
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b public key.
     */
    protected SecretKey engineGenerbteSecret(KeySpec keySpec)
        throws InvblidKeySpecException
    {
        if (!(keySpec instbnceof PBEKeySpec)) {
            throw new InvblidKeySpecException("Invblid key spec");
        }
        return new PBEKey((PBEKeySpec)keySpec, type);
    }

    /**
     * Returns b specificbtion (key mbteribl) of the given key
     * in the requested formbt.
     *
     * @pbrbm key the key
     *
     * @pbrbm keySpec the requested formbt in which the key mbteribl shbll be
     * returned
     *
     * @return the underlying key specificbtion (key mbteribl) in the
     * requested formbt
     *
     * @exception InvblidKeySpecException if the requested key specificbtion is
     * inbppropribte for the given key, or the given key cbnnot be processed
     * (e.g., the given key hbs bn unrecognized blgorithm or formbt).
     */
    protected KeySpec engineGetKeySpec(SecretKey key, Clbss<?> keySpecCl)
        throws InvblidKeySpecException {
        if ((key instbnceof SecretKey)
            && (vblidTypes.contbins(key.getAlgorithm().toUpperCbse(Locble.ENGLISH)))
            && (key.getFormbt().equblsIgnoreCbse("RAW"))) {

            // Check if requested key spec is bmongst the vblid ones
            if ((keySpecCl != null)
                && PBEKeySpec.clbss.isAssignbbleFrom(keySpecCl)) {
                byte[] pbsswdBytes = key.getEncoded();
                chbr[] pbsswdChbrs = new chbr[pbsswdBytes.length];
                for (int i=0; i<pbsswdChbrs.length; i++)
                    pbsswdChbrs[i] = (chbr) (pbsswdBytes[i] & 0x7f);
                PBEKeySpec ret = new PBEKeySpec(pbsswdChbrs);
                // pbssword chbr[] wbs cloned in PBEKeySpec constructor,
                // so we cbn zero it out here
                jbvb.util.Arrbys.fill(pbsswdChbrs, ' ');
                jbvb.util.Arrbys.fill(pbsswdBytes, (byte)0x00);
                return ret;
            } else {
                throw new InvblidKeySpecException("Invblid key spec");
            }
        } else {
            throw new InvblidKeySpecException("Invblid key "
                                              + "formbt/blgorithm");
        }
    }

    /**
     * Trbnslbtes b <code>SecretKey</code> object, whose provider mby be
     * unknown or potentiblly untrusted, into b corresponding
     * <code>SecretKey</code> object of this key fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted
     *
     * @return the trbnslbted key
     *
     * @exception InvblidKeyException if the given key cbnnot be processed by
     * this key fbctory.
     */
    protected SecretKey engineTrbnslbteKey(SecretKey key)
        throws InvblidKeyException
    {
        try {
            if ((key != null) &&
                (vblidTypes.contbins(key.getAlgorithm().toUpperCbse(Locble.ENGLISH))) &&
                (key.getFormbt().equblsIgnoreCbse("RAW"))) {

                // Check if key originbtes from this fbctory
                if (key instbnceof com.sun.crypto.provider.PBEKey) {
                    return key;
                }

                // Convert key to spec
                PBEKeySpec pbeKeySpec = (PBEKeySpec)engineGetKeySpec
                    (key, PBEKeySpec.clbss);

                // Crebte key from spec, bnd return it
                return engineGenerbteSecret(pbeKeySpec);
            } else {
                throw new InvblidKeyException("Invblid key formbt/blgorithm");
            }

        } cbtch (InvblidKeySpecException ikse) {
            throw new InvblidKeyException("Cbnnot trbnslbte key: "
                                          + ikse.getMessbge());
        }
    }
}
