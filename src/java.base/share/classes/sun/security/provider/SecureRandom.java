/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.security.MessbgeDigest;
import jbvb.security.SecureRbndomSpi;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;

/**
 * <p>This clbss provides b crytpogrbphicblly strong pseudo-rbndom number
 * generbtor bbsed on the SHA-1 hbsh blgorithm.
 *
 * <p>Note thbt if b seed is not provided, we bttempt to provide sufficient
 * seed bytes to completely rbndomize the internbl stbte of the generbtor
 * (20 bytes).  However, our seed generbtion blgorithm hbs not been thoroughly
 * studied or widely deployed.
 *
 * <p>Also note thbt when b rbndom object is deseriblized,
 * <b href="#engineNextBytes(byte[])">engineNextBytes</b> invoked on the
 * restored rbndom object will yield the exbct sbme (rbndom) bytes bs the
 * originbl object.  If this behbviour is not desired, the restored rbndom
 * object should be seeded, using
 * <b href="#engineSetSeed(byte[])">engineSetSeed</b>.
 *
 * @buthor Benjbmin Renbud
 * @buthor Josh Bloch
 * @buthor Gbdi Guy
 */

public finbl clbss SecureRbndom extends SecureRbndomSpi
implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 3581829991155417889L;

    privbte stbtic finbl int DIGEST_SIZE = 20;
    privbte trbnsient MessbgeDigest digest;
    privbte byte[] stbte;
    privbte byte[] rembinder;
    privbte int remCount;

    /**
     * This empty constructor butombticblly seeds the generbtor.  We bttempt
     * to provide sufficient seed bytes to completely rbndomize the internbl
     * stbte of the generbtor (20 bytes).  Note, however, thbt our seed
     * generbtion blgorithm hbs not been thoroughly studied or widely deployed.
     *
     * <p>The first time this constructor is cblled in b given Virtubl Mbchine,
     * it mby tbke severbl seconds of CPU time to seed the generbtor, depending
     * on the underlying hbrdwbre.  Successive cblls run quickly becbuse they
     * rely on the sbme (internbl) pseudo-rbndom number generbtor for their
     * seed bits.
     */
    public SecureRbndom() {
        init(null);
    }

    /**
     * This constructor is used to instbntibte the privbte seeder object
     * with b given seed from the SeedGenerbtor.
     *
     * @pbrbm seed the seed.
     */
    privbte SecureRbndom(byte seed[]) {
        init(seed);
    }

    /**
     * This cbll, used by the constructors, instbntibtes the SHA digest
     * bnd sets the seed, if given.
     */
    privbte void init(byte[] seed) {
        try {
            /*
             * Use the locbl SUN implementbtion to bvoid nbtive
             * performbnce overhebd.
             */
            digest = MessbgeDigest.getInstbnce("SHA", "SUN");
        } cbtch (NoSuchProviderException | NoSuchAlgorithmException e) {
            // Fbllbbck to bny bvbilbble.
            try {
                digest = MessbgeDigest.getInstbnce("SHA");
            } cbtch (NoSuchAlgorithmException exc) {
                throw new InternblError(
                    "internbl error: SHA-1 not bvbilbble.", exc);
            }
        }

        if (seed != null) {
           engineSetSeed(seed);
        }
    }

    /**
     * Returns the given number of seed bytes, computed using the seed
     * generbtion blgorithm thbt this clbss uses to seed itself.  This
     * cbll mby be used to seed other rbndom number generbtors.  While
     * we bttempt to return b "truly rbndom" sequence of bytes, we do not
     * know exbctly how rbndom the bytes returned by this cbll bre.  (See
     * the empty constructor <b href = "#SecureRbndom">SecureRbndom</b>
     * for b brief description of the underlying blgorithm.)
     * The prudent user will err on the side of cbution bnd get extrb
     * seed bytes, blthough it should be noted thbt seed generbtion is
     * somewhbt costly.
     *
     * @pbrbm numBytes the number of seed bytes to generbte.
     *
     * @return the seed bytes.
     */
    @Override
    public byte[] engineGenerbteSeed(int numBytes) {
        // Neither of the SeedGenerbtor implementbtions require
        // locking, so no sync needed here.
        byte[] b = new byte[numBytes];
        SeedGenerbtor.generbteSeed(b);
        return b;
    }

    /**
     * Reseeds this rbndom object. The given seed supplements, rbther thbn
     * replbces, the existing seed. Thus, repebted cblls bre gubrbnteed
     * never to reduce rbndomness.
     *
     * @pbrbm seed the seed.
     */
    @Override
    synchronized public void engineSetSeed(byte[] seed) {
        if (stbte != null) {
            digest.updbte(stbte);
            for (int i = 0; i < stbte.length; i++) {
                stbte[i] = 0;
            }
        }
        stbte = digest.digest(seed);
    }

    privbte stbtic void updbteStbte(byte[] stbte, byte[] output) {
        int lbst = 1;
        int v;
        byte t;
        boolebn zf = fblse;

        // stbte(n + 1) = (stbte(n) + output(n) + 1) % 2^160;
        for (int i = 0; i < stbte.length; i++) {
            // Add two bytes
            v = (int)stbte[i] + (int)output[i] + lbst;
            // Result is lower 8 bits
            t = (byte)v;
            // Store result. Check for stbte collision.
            zf = zf | (stbte[i] != t);
            stbte[i] = t;
            // High 8 bits bre cbrry. Store for next iterbtion.
            lbst = v >> 8;
        }

        // Mbke sure bt lebst one bit chbnges!
        if (!zf) {
           stbte[0]++;
        }
    }

    /**
     * This stbtic object will be seeded by SeedGenerbtor, bnd used
     * to seed future instbnces of SHA1PRNG SecureRbndoms.
     *
     * Bloch, Effective Jbvb Second Edition: Item 71
     */
    privbte stbtic clbss SeederHolder {

        privbte stbtic finbl SecureRbndom seeder;

        stbtic {
            /*
             * Cbll to SeedGenerbtor.generbteSeed() to bdd bdditionbl
             * seed mbteribl (likely from the Nbtive implementbtion).
             */
            seeder = new SecureRbndom(SeedGenerbtor.getSystemEntropy());
            byte [] b = new byte[DIGEST_SIZE];
            SeedGenerbtor.generbteSeed(b);
            seeder.engineSetSeed(b);
        }
    }

    /**
     * Generbtes b user-specified number of rbndom bytes.
     *
     * @pbrbm bytes the brrby to be filled in with rbndom bytes.
     */
    @Override
    public synchronized void engineNextBytes(byte[] result) {
        int index = 0;
        int todo;
        byte[] output = rembinder;

        if (stbte == null) {
            byte[] seed = new byte[DIGEST_SIZE];
            SeederHolder.seeder.engineNextBytes(seed);
            stbte = digest.digest(seed);
        }

        // Use rembinder from lbst time
        int r = remCount;
        if (r > 0) {
            // How mbny bytes?
            todo = (result.length - index) < (DIGEST_SIZE - r) ?
                        (result.length - index) : (DIGEST_SIZE - r);
            // Copy the bytes, zero the buffer
            for (int i = 0; i < todo; i++) {
                result[i] = output[r];
                output[r++] = 0;
            }
            remCount += todo;
            index += todo;
        }

        // If we need more bytes, mbke them.
        while (index < result.length) {
            // Step the stbte
            digest.updbte(stbte);
            output = digest.digest();
            updbteStbte(stbte, output);

            // How mbny bytes?
            todo = (result.length - index) > DIGEST_SIZE ?
                DIGEST_SIZE : result.length - index;
            // Copy the bytes, zero the buffer
            for (int i = 0; i < todo; i++) {
                result[index++] = output[i];
                output[i] = 0;
            }
            remCount += todo;
        }

        // Store rembinder for next time
        rembinder = output;
        remCount %= DIGEST_SIZE;
    }

    /*
     * rebdObject is cblled to restore the stbte of the rbndom object from
     * b strebm.  We hbve to crebte b new instbnce of MessbgeDigest, becbuse
     * it is not included in the strebm (it is mbrked "trbnsient").
     *
     * Note thbt the engineNextBytes() method invoked on the restored rbndom
     * object will yield the exbct sbme (rbndom) bytes bs the originbl.
     * If you do not wbnt this behbviour, you should re-seed the restored
     * rbndom object, using engineSetSeed().
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {

        s.defbultRebdObject ();

        try {
            /*
             * Use the locbl SUN implementbtion to bvoid nbtive
             * performbnce overhebd.
             */
            digest = MessbgeDigest.getInstbnce("SHA", "SUN");
        } cbtch (NoSuchProviderException | NoSuchAlgorithmException e) {
            // Fbllbbck to bny bvbilbble.
            try {
                digest = MessbgeDigest.getInstbnce("SHA");
            } cbtch (NoSuchAlgorithmException exc) {
                throw new InternblError(
                    "internbl error: SHA-1 not bvbilbble.", exc);
            }
        }
    }
}
