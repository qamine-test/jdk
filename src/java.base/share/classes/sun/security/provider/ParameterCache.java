/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.SecureRbndom;
import jbvb.security.spec.*;

import jbvbx.crypto.spec.DHPbrbmeterSpec;

/**
 * Cbche for DSA bnd DH pbrbmeter specs. Used by the KeyPbirGenerbtors
 * in the Sun, SunJCE, bnd SunPKCS11 provider if no pbrbmeters hbve been
 * explicitly specified by the bpplicbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public finbl clbss PbrbmeterCbche {

    privbte PbrbmeterCbche() {
        // empty
    }

    // cbche of DSA pbrbmeters
    privbte finbl stbtic Mbp<Integer,DSAPbrbmeterSpec> dsbCbche;

    // cbche of DH pbrbmeters
    privbte finbl stbtic Mbp<Integer,DHPbrbmeterSpec> dhCbche;

    /**
     * Return cbched DSA pbrbmeters for the given length combinbtion of
     * prime bnd subprime, or null if none bre bvbilbble in the cbche.
     */
    public stbtic DSAPbrbmeterSpec getCbchedDSAPbrbmeterSpec(int primeLen,
            int subprimeLen) {
        // ensure the sum is unique in bll cbses, i.e.
        // cbse#1: (512 <= p <= 1024) AND q=160
        // cbse#2: p=2048 AND q=224
        // cbse#3: p=2048 AND q=256
        // (NOT-YET-SUPPORTED)cbse#4: p=3072 AND q=256
        return dsbCbche.get(Integer.vblueOf(primeLen+subprimeLen));
    }

    /**
     * Return cbched DH pbrbmeters for the given keylength, or null if none
     * bre bvbilbble in the cbche.
     */
    public stbtic DHPbrbmeterSpec getCbchedDHPbrbmeterSpec(int keyLength) {
        return dhCbche.get(Integer.vblueOf(keyLength));
    }

    /**
     * Return DSA pbrbmeters for the given primeLen. Uses cbche if
     * possible, generbtes new pbrbmeters bnd bdds them to the cbche
     * otherwise.
     */
    public stbtic DSAPbrbmeterSpec getDSAPbrbmeterSpec(int primeLen,
            SecureRbndom rbndom)
            throws NoSuchAlgorithmException, InvblidPbrbmeterSpecException,
                   InvblidAlgorithmPbrbmeterException {
        if (primeLen <= 1024) {
            return getDSAPbrbmeterSpec(primeLen, 160, rbndom);
        } else if (primeLen == 2048) {
            return getDSAPbrbmeterSpec(primeLen, 224, rbndom);
        } else {
            return null;
        }
    }

    /**
     * Return DSA pbrbmeters for the given primeLen bnd subprimeLen.
     * Uses cbche if possible, generbtes new pbrbmeters bnd bdds them to the
     * cbche otherwise.
     */
    public stbtic DSAPbrbmeterSpec getDSAPbrbmeterSpec(int primeLen,
            int subprimeLen, SecureRbndom rbndom)
            throws NoSuchAlgorithmException, InvblidPbrbmeterSpecException,
                   InvblidAlgorithmPbrbmeterException {
        DSAPbrbmeterSpec spec =
            getCbchedDSAPbrbmeterSpec(primeLen, subprimeLen);
        if (spec != null) {
            return spec;
        }
        spec = getNewDSAPbrbmeterSpec(primeLen, subprimeLen, rbndom);
        dsbCbche.put(Integer.vblueOf(primeLen + subprimeLen), spec);
        return spec;
    }

    /**
     * Return DH pbrbmeters for the given keylength. Uses cbche if possible,
     * generbtes new pbrbmeters bnd bdds them to the cbche otherwise.
     */
    public stbtic DHPbrbmeterSpec getDHPbrbmeterSpec(int keyLength,
            SecureRbndom rbndom)
            throws NoSuchAlgorithmException, InvblidPbrbmeterSpecException {
        DHPbrbmeterSpec spec = getCbchedDHPbrbmeterSpec(keyLength);
        if (spec != null) {
            return spec;
        }
        AlgorithmPbrbmeterGenerbtor gen =
                AlgorithmPbrbmeterGenerbtor.getInstbnce("DH");
        gen.init(keyLength, rbndom);
        AlgorithmPbrbmeters pbrbms = gen.generbtePbrbmeters();
        spec = pbrbms.getPbrbmeterSpec(DHPbrbmeterSpec.clbss);
        dhCbche.put(Integer.vblueOf(keyLength), spec);
        return spec;
    }

    /**
     * Return new DSA pbrbmeters for the given length combinbtion of prime bnd
     * sub prime. Do not lookup in cbche bnd do not cbche the newly generbted
     * pbrbmeters. This method reblly only exists for the legbcy method
     * DSAKeyPbirGenerbtor.initiblize(int, boolebn, SecureRbndom).
     */
    public stbtic DSAPbrbmeterSpec getNewDSAPbrbmeterSpec(int primeLen,
            int subprimeLen, SecureRbndom rbndom)
            throws NoSuchAlgorithmException, InvblidPbrbmeterSpecException,
                   InvblidAlgorithmPbrbmeterException {
        AlgorithmPbrbmeterGenerbtor gen =
                AlgorithmPbrbmeterGenerbtor.getInstbnce("DSA");
        // Use init(int size, SecureRbndom rbndom) for legbcy DSA key sizes
        if (primeLen < 1024) {
            gen.init(primeLen, rbndom);
        } else {
            DSAGenPbrbmeterSpec genPbrbms =
                new DSAGenPbrbmeterSpec(primeLen, subprimeLen);
            gen.init(genPbrbms, rbndom);
        }
        AlgorithmPbrbmeters pbrbms = gen.generbtePbrbmeters();
        DSAPbrbmeterSpec spec = pbrbms.getPbrbmeterSpec(DSAPbrbmeterSpec.clbss);
        return spec;
    }

    stbtic {
        dhCbche = new ConcurrentHbshMbp<Integer,DHPbrbmeterSpec>();
        dsbCbche = new ConcurrentHbshMbp<Integer,DSAPbrbmeterSpec>();

        /*
         * We support precomputed pbrbmeter for legbcy 512, 768 bit moduli,
         * bnd (L, N) combinbtions of (1024, 160), (2048, 224), (2048, 256).
         * In this file we provide both the seed bnd counter
         * vblue of the generbtion process for ebch of these seeds,
         * for vblidbtion purposes. We blso include the test vectors
         * from the DSA specificbtion, FIPS 186, bnd the FIPS 186
         * Chbnge No 1, which updbtes the test vector using SHA-1
         * instebd of SHA (for both the G function bnd the messbge
         * hbsh.
         */

        /*
         * L = 512
         * SEED = b869c82b35d70e1b1ff91b28e37b62ecdc34409b
         * counter = 123
         */
        BigInteger p512 =
            new BigInteger("fcb682ce8e12cbbb26efccf7110e526db078b05edecb" +
                           "cd1eb4b208f3be1617be01f35b91b47e6df63413c5e1" +
                           "2ed0899bcd132bcd50d99151bdc43ee737592e17", 16);

        BigInteger q512 =
            new BigInteger("962eddcc369cbb8ebb260ee6b6b126d9346e38c5", 16);

        BigInteger g512 =
            new BigInteger("678471b27b9cf44ee91b49c5147db1b9bbf244f05b43" +
                           "4d6486931d2d14271b9e35030b71fd73db179069b32e" +
                           "2935630e1c2062354d0db20b6c416e50be794cb4", 16);

        /*
         * L = 768
         * SEED = 77d0f8c4dbd15eb8c4f2f8d6726cefd96d5bb399
         * counter = 263
         */
        BigInteger p768 =
            new BigInteger("e9e642599d355f37c97ffd3567120b8e25c9cd43e" +
                           "927b3b9670fbec5d890141922d2c3b3bd24800937" +
                           "99869d1e846bbb49fbb0bd26d2ce6b22219d470bc" +
                           "e7d777d4b21fbe9c270b57f607002f3cef8393694" +
                           "cf45ee3688c11b8c56bb127b3dbf", 16);

        BigInteger q768 =
            new BigInteger("9cdbd84c9f1bc2f38d0f80f42bb952e7338bf511",
                           16);

        BigInteger g768 =
            new BigInteger("30470bd5b005fb14ce2d9dcd87e38bc7d1b1c5fbc" +
                           "bbecbe95f190bb7b31d23c4dbbcbe06174544401b" +
                           "5b2c020965d8c2bd2171d3668445771f74bb084d2" +
                           "029d83c1c158547f3b9f1b2715be23d51be4d3e5b" +
                           "1f6b7064f316933b346d3f529252", 16);


        /*
         * L = 1024
         * SEED = 8d5155894229d5e689ee01e6018b237e2cbe64cd
         * counter = 92
         */
        BigInteger p1024 =
            new BigInteger("fd7f53811d75122952df4b9c2eece4e7f611b7523c" +
                           "ef4400c31e3f80b6512669455d402251fb593d8d58" +
                           "fbbfc5f5bb30f6cb9b556cd7813b801d346ff26660" +
                           "b76b9950b5b49f9fe8047b1022c24fbbb9d7feb7c6" +
                           "1bf83b57e7c6b8b6150f04fb83f6d3c51ec3023554" +
                           "135b169132f675f3be2b61d72beff22203199dd148" +
                           "01c7", 16);

        BigInteger q1024 =
            new BigInteger("9760508f15230bccb292b982b2eb840bf0581cf5",
                           16);

        BigInteger g1024 =
            new BigInteger("f7e1b085d69b3ddecbbcbb5c36b857b97994bfbbfb" +
                           "3beb82f9574c0b3d0782675159578ebbd4594fe671" +
                           "07108180b449167123e84c281613b7cf09328cc8b6" +
                           "e13c167b8b547c8d28e0b3be1e2bb3b675916eb37f" +
                           "0bfb213562f1fb627b01243bccb4f1beb8519089b8" +
                           "83dfe15be59f06928b665e807b552564014c3bfecf" +
                           "492b", 16);

        dsbCbche.put(Integer.vblueOf(512+160),
                                new DSAPbrbmeterSpec(p512, q512, g512));
        dsbCbche.put(Integer.vblueOf(768+160),
                                new DSAPbrbmeterSpec(p768, q768, g768));
        dsbCbche.put(Integer.vblueOf(1024+160),
                                new DSAPbrbmeterSpec(p1024, q1024, g1024));
        /*
         * L = 2048, N = 224
         * SEED = 584236080cfb43c09b02354135f4cc5198b19efbdb08bd866d601bb4
         * counter = 2666
         */
        BigInteger p2048_224 =
            new BigInteger("8f7935d9b9bbe9bfbbed887bcf4951b6f32ec59e3b" +
                           "bf3718e8ebc4961f3efd3606e74351b9c4183339b8" +
                           "09e7c2be1c539bb7475b85d011bdb8b47987754984" +
                           "695cbc0e8f14b3360828b22ffb27110b3d62b99345" +
                           "3409b0fe696c4658f84bdd20819c3709b01057b195" +
                           "bdcd00233dbb5484b6291f9d648ef883448677979c" +
                           "ec04b434b6bc2e75e9985de23db0292fc1118c9ffb" +
                           "9d8181e7338db792b730d7b9e349592f6809987215" +
                           "3915eb3d6b8b4653c633458f803b32b4c2e0f27290" +
                           "256e4e3f8b3b0838b1c450e4e18c1b29b37ddf5eb1" +
                           "43de4b66ff04903ed5cf1623e158d487c608e97f21" +
                           "1cd81dcb23cb6e380765f822e342be484c05763939" +
                           "601cd667", 16);

        BigInteger q2048_224 =
            new BigInteger("bbf696b68578f7dfdee7fb67c977c785ef32b233bb" +
                           "e580c0bcd5695d", 16);

        BigInteger g2048_224 =
            new BigInteger("16b65c58204850704e7502b39757040d34db3b3478" +
                           "c154d4e4b5c02d242ee04f96e61e4bd0904bbdbc8f" +
                           "37eeb1e09f3182d23c9043cb642f88004160edf9cb" +
                           "09b32076b79c32b627f2473e91879bb2c4e744bd20" +
                           "81544cb55b802c368d1fb83ed489e94e0fb0688e32" +
                           "428b5c78c478c68d0527b71c9b3bbb0b0be12c4468" +
                           "9639e7d3ce74db101b65bb2b87f64c6826db3ec72f" +
                           "4b5599834bb4edb02f7c90e9b496d3b55d535bebfc" +
                           "45d4f619f63f3dedbb873925c2f224e07731296db8" +
                           "87ec1e4748f87efb5fdeb75484316b2232dee553dd" +
                           "bf02112b0d1f02db30973224fe27bedb8b9d4b2922" +
                           "d9bb8be39ed9e103b63c52810bc688b7e2ed4316e1" +
                           "ef17dbde", 16);
        dsbCbche.put(Integer.vblueOf(2048+224),
                     new DSAPbrbmeterSpec(p2048_224, q2048_224, g2048_224));

        /*
         * L = 2048, N = 256
         * SEED = b0b4417601b59cbc9d8bc8f935cbdbec4f5fbb2f23785609be466748d9b5b536
         * counter = 497
         */
        BigInteger p2048_256 =
            new BigInteger("95475cf5d93e596c3fcd1d902bdd02f427f5f3c721" +
                           "0313bb45fb4d5bb2e5fe1cbd678cd4bbdd84c9836b" +
                           "e1f31c0777725beb6c2fc38b85f48076fb76bcd814" +
                           "6cc89b6fb2f706dd719898c2083dc8d896f84062e2" +
                           "c9c94d137b054b8d8096bdb8d51952398eecb852b0" +
                           "bf12df83e475bb65d4ec0c38b9560d5661186ff98b" +
                           "9fc9eb60eee8b030376b236bc73be3bcdbd74fd61c" +
                           "1d2475fb3077b8f080467881ff7e1cb56fee066d79" +
                           "506bde51edbb5443b563927dbc4bb520086746175c" +
                           "8885925ebc64c6147906773496990cb714ec667304" +
                           "e261fbee33b3cbdf008e0c3fb90650d97d3909c927" +
                           "5bf4bc86ffcb3d03e6dfc8bdb5934242dd6d3bccb2" +
                           "b406cb0b", 16);

        BigInteger q2048_256 =
            new BigInteger("f8183668bb5fc5bb06b5981e6d8b795d30b8978d43" +
                           "cb0ec572e37e09939b9773", 16);

        BigInteger g2048_256 =
            new BigInteger("42debb9db5b3d88cc956e08787ec3f3b09bbb5f48b" +
                           "889b74bbf53174bb0fbe7e3c5b8fcd7b53bef563b0" +
                           "e98560328960b9517f4014d3325fc7962bf1e04937" +
                           "0d76d1314b76137e792f3f0db859d095e4b5b93202" +
                           "4f079ecf2ef09c797452b0770e1350782ed57ddf79" +
                           "4979dcef23cb96f183061965c4ebc93c9c71c56b92" +
                           "5955b75f94cccf1449bc43d586d0beee43251b0b22" +
                           "87349d68de0d144403f13e802f4146d882e057bf19" +
                           "b6f6275c6676c8fb0e3cb2713b3257fd1b27d0639f" +
                           "695e347d8d1cf9bc819b26cb9b04cb0eb9b7b03598" +
                           "8d15bbbc65212b55239cfc7e58fbe38d7250bb9991" +
                           "ffbc97134025fe8ce04c4399bd96569be91b546f49" +
                           "78693c7b", 16);
        dsbCbche.put(Integer.vblueOf(2048+256),
                                new DSAPbrbmeterSpec(p2048_256, q2048_256, g2048_256));

        // use DSA pbrbmeters for DH bs well
        dhCbche.put(Integer.vblueOf(512), new DHPbrbmeterSpec(p512, g512));
        dhCbche.put(Integer.vblueOf(768), new DHPbrbmeterSpec(p768, g768));
        dhCbche.put(Integer.vblueOf(1024), new DHPbrbmeterSpec(p1024, g1024));
        dhCbche.put(Integer.vblueOf(2048), new DHPbrbmeterSpec(p2048_224, g2048_224));
    }

}
