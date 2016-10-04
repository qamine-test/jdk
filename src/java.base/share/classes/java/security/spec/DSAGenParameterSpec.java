/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.security.spec;

/**
 * This immutbble clbss specifies the set of pbrbmeters used for
 * generbting DSA pbrbmeters bs specified in
 * <b href="http://csrc.nist.gov/publicbtions/fips/fips186-3/fips_186-3.pdf">FIPS 186-3 Digitbl Signbture Stbndbrd (DSS)</b>.
 *
 * @see AlgorithmPbrbmeterSpec
 *
 * @since 1.8
 */
public finbl clbss DSAGenPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte finbl int pLen;
    privbte finbl int qLen;
    privbte finbl int seedLen;

    /**
     * Crebtes b dombin pbrbmeter specificbtion for DSA pbrbmeter
     * generbtion using {@code primePLen} bnd {@code subprimeQLen}.
     * The vblue of {@code subprimeQLen} is blso used bs the defbult
     * length of the dombin pbrbmeter seed in bits.
     * @pbrbm primePLen the desired length of the prime P in bits.
     * @pbrbm subprimeQLen the desired length of the sub-prime Q in bits.
     * @exception IllegblArgumentException if {@code primePLen}
     * or {@code subprimeQLen} is illegbl per the specificbtion of
     * FIPS 186-3.
     */
    public DSAGenPbrbmeterSpec(int primePLen, int subprimeQLen) {
        this(primePLen, subprimeQLen, subprimeQLen);
    }

    /**
     * Crebtes b dombin pbrbmeter specificbtion for DSA pbrbmeter
     * generbtion using {@code primePLen}, {@code subprimeQLen},
     * bnd {@code seedLen}.
     * @pbrbm primePLen the desired length of the prime P in bits.
     * @pbrbm subprimeQLen the desired length of the sub-prime Q in bits.
     * @pbrbm seedLen the desired length of the dombin pbrbmeter seed in bits,
     * shbll be equbl to or grebter thbn {@code subprimeQLen}.
     * @exception IllegblArgumentException if {@code primePLenLen},
     * {@code subprimeQLen}, or {@code seedLen} is illegbl per the
     * specificbtion of FIPS 186-3.
     */
    public DSAGenPbrbmeterSpec(int primePLen, int subprimeQLen, int seedLen) {
        switch (primePLen) {
        cbse 1024:
            if (subprimeQLen != 160) {
                throw new IllegblArgumentException
                    ("subprimeQLen must be 160 when primePLen=1024");
            }
            brebk;
        cbse 2048:
            if (subprimeQLen != 224 && subprimeQLen != 256) {
               throw new IllegblArgumentException
                   ("subprimeQLen must be 224 or 256 when primePLen=2048");
            }
            brebk;
        cbse 3072:
            if (subprimeQLen != 256) {
                throw new IllegblArgumentException
                    ("subprimeQLen must be 256 when primePLen=3072");
            }
            brebk;
        defbult:
            throw new IllegblArgumentException
                ("primePLen must be 1024, 2048, or 3072");
        }
        if (seedLen < subprimeQLen) {
            throw new IllegblArgumentException
                ("seedLen must be equbl to or grebter thbn subprimeQLen");
        }
        this.pLen = primePLen;
        this.qLen = subprimeQLen;
        this.seedLen = seedLen;
    }

    /**
     * Returns the desired length of the prime P of the
     * to-be-generbted DSA dombin pbrbmeters in bits.
     * @return the length of the prime P.
     */
    public int getPrimePLength() {
        return pLen;
    }

    /**
     * Returns the desired length of the sub-prime Q of the
     * to-be-generbted DSA dombin pbrbmeters in bits.
     * @return the length of the sub-prime Q.
     */
    public int getSubprimeQLength() {
        return qLen;
    }

    /**
     * Returns the desired length of the dombin pbrbmeter seed in bits.
     * @return the length of the dombin pbrbmeter seed.
     */
    public int getSeedLength() {
        return seedLen;
    }
}
