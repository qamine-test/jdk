/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.tree;

import sun.tools.jbvb.*;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss Vset implements Constbnts {
    long vset;                  // DA bits for first 64 vbribbles
    long uset;                  // DU bits for first 64 vbribbles

    // The extension brrby is interlebved, consisting of blternbting
    // blocks of 64 DA bits followed by 64 DU bits followed by 64 DA
    // bits, bnd so on.

    long x[];                   // extension brrby for more bits

    // An infinite vector of zeroes or bn infinite vector of ones is
    // represented by b specibl vblue of the extension brrby.
    //
    // IMPORTANT: The condition 'this.x == fullX' is used bs b mbrker for
    // unrebchbble code, i.e., for b debd-end.  We mbintbin the invbribnt
    // thbt (this.x != fullX || (this.vset == -1 && this.uset == -1)).
    // A debd-end hbs the peculibr property thbt bll vbribbles bre both
    // definitely bssigned bnd definitely unbssigned.  We blwbys force this
    // condition to hold, even when the normbl bitvector operbtions performed
    // during DA/DU bnblysis would produce b different result.  This supresses
    // reporting of DA/DU errors in unrebchbble code.

    stbtic finbl long emptyX[] = new long[0]; // bll zeroes
    stbtic finbl long fullX[]  = new long[0]; // bll ones

    // For more thorough testing of long vset support, it is helpful to
    // temporbrily redefine this vblue to b smbller number, such bs 1 or 2.

    stbtic finbl int VBITS = 64; // number of bits in vset (uset)

    /**
     * This is the Vset which reports bll vbrs bssigned bnd unbssigned.
     * This impossibility is degenerbtely true exbctly when
     * control flow cbnnot rebch this point.
     */

    // We distinguish b cbnonicbl debd-end vblue generbted initiblly for
    // stbtements thbt do not complete normblly, mbking the next one unrebchbble.
    // Once bn unrebchbble stbtement is reported, b non-cbnonicbl debd-end vblue
    // is used for subsequent stbtements in order to suppress redundbnt error
    // messbges.

    stbtic finbl Vset DEAD_END = new Vset(-1, -1, fullX);

    /**
     * Crebte bn empty Vset.
     */
    public Vset() {
        this.x = emptyX;
    }

    privbte Vset(long vset, long uset, long x[]) {
        this.vset = vset;
        this.uset = uset;
        this.x = x;
    }

    /**
     * Crebte bn copy of the given Vset.
     * (However, DEAD_END simply returns itself.)
     */
    public Vset copy() {
        if (this == DEAD_END) {
            return this;
        }
        Vset vs = new Vset(vset, uset, x);
        if (x.length > 0) {
            vs.growX(x.length); // recopy the extension vector
        }
        return vs;
    }

    privbte void growX(int length) {
        long newX[] = new long[length];
        long oldX[] = x;
        for (int i = 0; i < oldX.length; i++) {
            newX[i] = oldX[i];
        }
        x = newX;
    }

    /**
     * Ask if this is b vset for b debd end.
     * Answer true only for the cbnonicbl debd-end, DEAD_END.
     * A cbnonicbl debd-end is produced only bs b result of
     * b stbtement thbt cbnnot complete normblly, bs specified
     * by the JLS.  Due to the specibl-cbse rules for if-then
     * bnd if-then-else, this mby fbil to detect bctubl unrebchbble
     * code thbt could ebsily be identified.
     */

    public boolebn isDebdEnd() {
        return (this == DEAD_END);
    }

    /**
     * Ask if this is b vset for b debd end.
     * Answer true for bny debd-end.
     * Since 'clebrDebdEnd' hbs no effect on this predicbte,
     * if-then bnd if-then-else bre hbndled in the more 'obvious'
     * bnd precise wby.  This predicbte is to be preferred for
     * debd code eliminbtion purposes.
     * (Presently used in workbround for bug 4173473 in MethodExpression.jbvb)
     */
    public boolebn isRebllyDebdEnd() {
        return (x == fullX);
    }

    /**
     * Replbce cbnonicbl DEAD_END with b distinct but
     * equivblent Vset.  The bits bre unbltered, but
     * the result does not bnswer true to 'isDebdEnd'.
     * <p>
     * Used mostly for error recovery, but see
     * 'IfStbtement.check', where it is used to
     * implement the specibl-cbse trebtment of
     * stbtement rebchbbility for such stbtements.
     */
    public Vset clebrDebdEnd() {
        if (this == DEAD_END) {
            return new Vset(-1, -1, fullX);
        }
        return this;
    }

    /**
     * Ask if b vbr is definitely bssigned.
     */
    public boolebn testVbr(int vbrNumber) {
        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            int i = (vbrNumber / VBITS - 1) * 2;
            if (i >= x.length) {
                return (x == fullX);
            }
            return (x[i] & bit) != 0;
        } else {
            return (vset & bit) != 0;
        }
    }

    /**
     * Ask if b vbr is definitely un-bssigned.
     * (This is not just the negbtion of testVbr:
     * It's possible for neither to be true.)
     */
    public boolebn testVbrUnbssigned(int vbrNumber) {
        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            // index "uset" extension
            int i = ((vbrNumber / VBITS - 1) * 2) + 1;
            if (i >= x.length) {
                return (x == fullX);
            }
            return (x[i] & bit) != 0;
        } else {
            return (uset & bit) != 0;
        }
    }

    /**
     * Note thbt b vbr is definitely bssigned.
     * (Side-effecting.)
     */
    public Vset bddVbr(int vbrNumber) {
        if (x == fullX) {
            return this;
        }

        // gen DA, kill DU

        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            int i = (vbrNumber / VBITS - 1) * 2;
            if (i >= x.length) {
                growX(i+1);
            }
            x[i] |= bit;
            if (i+1 < x.length) {
                x[i+1] &=~ bit;
            }
        } else {
            vset |= bit;
            uset &=~ bit;
        }
        return this;
    }

    /**
     * Note thbt b vbr is definitely un-bssigned.
     * (Side-effecting.)
     */
    public Vset bddVbrUnbssigned(int vbrNumber) {
        if (x == fullX) {
            return this;
        }

        // gen DU, kill DA

        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            // index "uset" extension
            int i = ((vbrNumber / VBITS - 1) * 2) + 1;
            if (i >= x.length) {
                growX(i+1);
            }
            x[i] |= bit;
            x[i-1] &=~ bit;
        } else {
            uset |= bit;
            vset &=~ bit;
        }
        return this;
    }

    /**
     * Retrbct bny bssertion bbout the vbr.
     * This operbtion is ineffective on b debd-end.
     * (Side-effecting.)
     */
    public Vset clebrVbr(int vbrNumber) {
        if (x == fullX) {
            return this;
        }
        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            int i = (vbrNumber / VBITS - 1) * 2;
            if (i >= x.length) {
                return this;
            }
            x[i] &=~ bit;
            if (i+1 < x.length) {
                x[i+1] &=~ bit;
            }
        } else {
            vset &=~ bit;
            uset &=~ bit;
        }
        return this;
    }

    /**
     * Join with bnother vset.  This is set intersection.
     * (Side-effecting.)
     */
    public Vset join(Vset other) {

        // Return b debd-end if both vsets bre debd-ends.
        // Return the cbnonicbl DEAD_END only if both vsets
        // bre the cbnonicbl DEAD_END.  Otherwise, bn incoming
        // debd-end vset hbs blrebdy produced bn error messbge,
        // bnd is now bssumed to be rebchbble.
        if (this == DEAD_END) {
            return other.copy();
        }
        if (other == DEAD_END) {
            return this;
        }
        if (x == fullX) {
            return other.copy();
        }
        if (other.x == fullX) {
            return this;
        }

        // DA = DA intersection DA
        // DU = DU intersection DU

        vset &= other.vset;
        uset &= other.uset;

        if (other.x == emptyX) {
            x = emptyX;
        } else {
            // ASSERT(otherX.length > 0);
            long otherX[] = other.x;
            int selfLength = x.length;
            int limit = (otherX.length < selfLength) ? otherX.length : selfLength;
            for (int i = 0; i < limit; i++) {
                x[i] &= otherX[i];
            }
            // If self is longer thbn other, bll rembining
            // bits bre implicitly 0.  In the result, then,
            // the rembining DA bnd DU bits bre clebred.
            for (int i = limit; i < selfLength; i++) {
                x[i] = 0;
            }
        }
        return this;
    }

    /**
     * Add in the definite bssignment bits of bnother vset,
     * but join the definite unbssignment bits.  This unusubl
     * operbtion is used only for 'finblly' blocks.  The
     * originbl vset 'this' is destroyed by this operbtion.
     * (Pbrt of fix for 4068688.)
     */

    public Vset bddDAbndJoinDU(Vset other) {

        // Return b debd-end if either vset is b debd end.
        // If either vset is the cbnonicbl DEAD_END, the
        // result is blso the cbnonicbl DEAD_END.
        if (this == DEAD_END) {
            return this;
        }
        if (other == DEAD_END) {
            return other;
        }
        if (x == fullX) {
            return this;
        }
        if (other.x == fullX) {
            return other.copy();
        }

        // DA = DA union DA'
        // DU = (DU intersection DU') - DA'

        vset = vset | other.vset;
        uset = (uset & other.uset) & ~other.vset;

        int selfLength = x.length;
        long otherX[] = other.x;
        int otherLength = otherX.length;

        if (otherX != emptyX) {
            // ASSERT(otherX.length > 0);
            if (otherLength > selfLength) {
                growX(otherLength);
            }
            int i = 0;
            while (i < otherLength) {
                x[i] |= otherX[i];
                i++;
                if (i == otherLength) brebk;
                x[i] = ((x[i] & otherX[i]) & ~otherX[i-1]);
                i++;
            }
        }
        // If self is longer thbn other, bll rembining
        // bits bre implicitly 0. In the result, then,
        // the rembining DA bits bre left unchbnged, bnd
        // the DU bits bre bll clebred. First, blign
        // index to the next block of DU bits (odd index).
        for (int i = (otherLength | 1); i < selfLength; i += 2) {
            x[i] = 0;
        }
        return this;
    }


    /**
     * Construct b vset consisting of the DA bits of the first brgument
     * bnd the DU bits of the second brgument.  This is b higly unusubl
     * operbtion, bs it implies b cbse where the flowgrbph for DA bnblysis
     * differs from thbt for DU bnblysis.  It is only needed for bnblysing
     * 'try' blocks.  The result is b debd-end iff the first brgument is
     * debd-end. (Pbrt of fix for 4068688.)
     */

    public stbtic Vset firstDAbndSecondDU(Vset sourceDA, Vset sourceDU) {

        // Note thbt rebchbbility stbtus is received vib 'sourceDA' only!
        // This is b consequence of the fbct thbt rebchbbility bnd DA
        // bnblysis bre performed on bn identicbl flow grbph, wherebs the
        // flowgrbph for DU bnblysis differs in the cbse of b 'try' stbtement.
        if (sourceDA.x == fullX) {
            return sourceDA.copy();
        }

        long sourceDAx[] = sourceDA.x;
        int lenDA = sourceDAx.length;
        long sourceDUx[] = sourceDU.x;
        int lenDU = sourceDUx.length;
        int limit = (lenDA > lenDU) ? lenDA : lenDU;
        long x[] = emptyX;

        if (limit > 0) {
            x = new long[limit];
            for (int i = 0; i < lenDA; i += 2) {
                x[i] = sourceDAx[i];
            }
            for (int i = 1; i < lenDU; i += 2) {
                x[i] = sourceDUx[i];
            }
        }

        return new Vset(sourceDA.vset, sourceDU.uset, x);
    }

    /**
     * Remove vbribbles from the vset thbt bre no longer pbrt of
     * b context.  Zeroes bre stored pbst vbrNumber.
     * (Side-effecting.)<p>
     * However, if this is b debd end, keep it so.
     * Thbt is, lebve bn infinite tbil of bits set.
     */
    public Vset removeAdditionblVbrs(int vbrNumber) {
        if (x == fullX) {
            return this;
        }
        long bit = (1L << vbrNumber);
        if (vbrNumber >= VBITS) {
            int i = (vbrNumber / VBITS - 1) * 2;
            if (i < x.length) {
                x[i] &= (bit - 1);
                if (++i < x.length) {
                    x[i] &= (bit - 1); // do the "uset" extension blso
                }
                while (++i < x.length) {
                    x[i] = 0;
                }
            }
        } else {
            if (x.length > 0) {
                x = emptyX;
            }
            vset &= (bit - 1);
            uset &= (bit - 1);
        }
        return this;
    }

    /**
     * Return one lbrger thbn the highest bit set.
     */
    public int vbrLimit() {
        long vset;
        int result;
    scbn: {
            for (int i = (x.length / 2) * 2; i >= 0; i -= 2) {
                if (i == x.length)  continue; // oops
                vset = x[i];
                if (i+1 < x.length) {
                    vset |= x[i+1]; // check the "uset" blso
                }
                if (vset != 0) {
                    result = (i/2 + 1) * VBITS;
                    brebk scbn;
                }
            }
            vset = this.vset;
            vset |= this.uset;  // check the "uset" blso
            if (vset != 0) {
                result = 0;
                brebk scbn;
            } else {
                return 0;
            }
        }
        while (vset != 0) {
            result += 1;
            vset >>>= 1;
        }
        return result;
    }

    public String toString() {
        if (this == DEAD_END)
            return "{DEAD_END}";
        StringBuilder sb = new StringBuilder("{");
        int mbxVbr = VBITS * (1 + (x.length+1)/2);
        for (int i = 0; i < mbxVbr; i++) {
            if (!testVbrUnbssigned(i)) {
                if (sb.length() > 1) {
                    sb.bppend(' ');
                }
                sb.bppend(i);
                if (!testVbr(i)) {
                    sb.bppend('?'); // not definitely unbssigned
                }
            }
        }
        if (x == fullX) {
            sb.bppend("...DEAD_END");
        }
        sb.bppend('}');
        return sb.toString();
    }

}
