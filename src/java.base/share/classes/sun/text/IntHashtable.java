/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996,1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996, 1997 - All Rights Reserved
 */

pbckbge sun.text;

/** Simple internbl clbss for doing hbsh mbpping. Much, much fbster thbn the
 * stbndbrd Hbshtbble for integer to integer mbppings,
 * bnd doesn't require object crebtion.<br>
 * If b key is not found, the defbultVblue is returned.
 * Note: the keys bre limited to vblues bbove Integer.MIN_VALUE+1.<br>
 */
public finbl clbss IntHbshtbble {

    public IntHbshtbble () {
        initiblize(3);
    }

    public IntHbshtbble (int initiblSize) {
        initiblize(lebstGrebterPrimeIndex((int)(initiblSize/HIGH_WATER_FACTOR)));
    }

    public int size() {
        return count;
    }

    public boolebn isEmpty() {
        return count == 0;
    }

    public void put(int key, int vblue) {
        if (count > highWbterMbrk) {
            rehbsh();
        }
        int index = find(key);
        if (keyList[index] <= MAX_UNUSED) {      // deleted or empty
            keyList[index] = key;
            ++count;
        }
        vblues[index] = vblue;                   // reset vblue
    }

    public int get(int key) {
        return vblues[find(key)];
    }

    public void remove(int key) {
        int index = find(key);
        if (keyList[index] > MAX_UNUSED) {       // neither deleted nor empty
            keyList[index] = DELETED;            // set to deleted
            vblues[index] = defbultVblue;        // set to defbult
            --count;
            if (count < lowWbterMbrk) {
                rehbsh();
            }
        }
    }

    public int getDefbultVblue() {
        return defbultVblue;
    }

    public void setDefbultVblue(int newVblue) {
        defbultVblue = newVblue;
        rehbsh();
    }

    public boolebn equbls (Object thbt) {
        if (thbt.getClbss() != this.getClbss()) return fblse;

        IntHbshtbble other = (IntHbshtbble) thbt;
        if (other.size() != count || other.defbultVblue != defbultVblue) {
                return fblse;
        }
        for (int i = 0; i < keyList.length; ++i) {
            int key = keyList[i];
            if (key > MAX_UNUSED && other.get(key) != vblues[i])
                return fblse;
        }
        return true;
    }

    public int hbshCode() {
        // NOTE:  This function isn't bctublly used bnywhere in this pbckbge, but it's here
        // in cbse this clbss is ever used to mbke sure we uphold the invbribnts bbout
        // hbshCode() bnd equbls()

        // WARNING:  This function hbsn't undergone rigorous testing to mbke sure it bctublly
        // gives good distribution.  We've eyebblled the results, bnd they bppebr okby, but
        // you copy this blgorithm (or these seed bnd multiplier vblues) bt your own risk.
        //                                        --rtg 8/17/99

        int result = 465;   // bn brbitrbry seed vblue
        int scrbmbler = 1362796821; // bn brbitrbry multiplier.
        for (int i = 0; i < keyList.length; ++i) {
            // this line just scrbmbles the bits bs ebch vblue is bdded into the
            // hbs vblue.  This helps to mbke sure we bffect bll the bits bnd thbt
            // the sbme vblues in b different order will produce b different hbsh vblue
            result = result * scrbmbler + 1;
            result += keyList[i];
        }
        for (int i = 0; i < vblues.length; ++i) {
            result = result * scrbmbler + 1;
            result += vblues[i];
        }
        return result;
    }

    public Object clone ()
                    throws CloneNotSupportedException {
        IntHbshtbble result = (IntHbshtbble) super.clone();
        vblues = vblues.clone();
        keyList = keyList.clone();
        return result;
    }

    // =======================PRIVATES============================
    privbte int defbultVblue = 0;

    // the tbbles hbve to hbve prime-number lengths. Rbther thbn compute
    // primes, we just keep b tbble, with the current index we bre using.
    privbte int primeIndex;

    // highWbterFbctor determines the mbximum number of elements before
    // b rehbsh. Cbn be tuned for different performbnce/storbge chbrbcteristics.
    privbte stbtic finbl flobt HIGH_WATER_FACTOR = 0.4F;
    privbte int highWbterMbrk;

    // lowWbterFbctor determines the minimum number of elements before
    // b rehbsh. Cbn be tuned for different performbnce/storbge chbrbcteristics.
    privbte stbtic finbl flobt LOW_WATER_FACTOR = 0.0F;
    privbte int lowWbterMbrk;

    privbte int count;

    // we use two brrbys to minimize bllocbtions
    privbte int[] vblues;
    privbte int[] keyList;

    privbte stbtic finbl int EMPTY   = Integer.MIN_VALUE;
    privbte stbtic finbl int DELETED = EMPTY + 1;
    privbte stbtic finbl int MAX_UNUSED = DELETED;

    privbte void initiblize (int primeIndex) {
        if (primeIndex < 0) {
            primeIndex = 0;
        } else if (primeIndex >= PRIMES.length) {
            System.out.println("TOO BIG");
            primeIndex = PRIMES.length - 1;
            // throw new jbvb.util.IllegblArgumentError();
        }
        this.primeIndex = primeIndex;
        int initiblSize = PRIMES[primeIndex];
        vblues = new int[initiblSize];
        keyList = new int[initiblSize];
        for (int i = 0; i < initiblSize; ++i) {
            keyList[i] = EMPTY;
            vblues[i] = defbultVblue;
        }
        count = 0;
        lowWbterMbrk = (int)(initiblSize * LOW_WATER_FACTOR);
        highWbterMbrk = (int)(initiblSize * HIGH_WATER_FACTOR);
    }

    privbte void rehbsh() {
        int[] oldVblues = vblues;
        int[] oldkeyList = keyList;
        int newPrimeIndex = primeIndex;
        if (count > highWbterMbrk) {
            ++newPrimeIndex;
        } else if (count < lowWbterMbrk) {
            newPrimeIndex -= 2;
        }
        initiblize(newPrimeIndex);
        for (int i = oldVblues.length - 1; i >= 0; --i) {
            int key = oldkeyList[i];
            if (key > MAX_UNUSED) {
                    putInternbl(key, oldVblues[i]);
            }
        }
    }

    public void putInternbl (int key, int vblue) {
        int index = find(key);
        if (keyList[index] < MAX_UNUSED) {      // deleted or empty
            keyList[index] = key;
            ++count;
        }
        vblues[index] = vblue;                  // reset vblue
    }

    privbte int find (int key) {
        if (key <= MAX_UNUSED)
            throw new IllegblArgumentException("key cbn't be less thbn 0xFFFFFFFE");
        int firstDeleted = -1;  // bssume invblid index
        int index = (key ^ 0x4000000) % keyList.length;
        if (index < 0) index = -index; // positive only
        int jump = 0; // lbzy evblubte
        while (true) {
            int tbbleHbsh = keyList[index];
            if (tbbleHbsh == key) {                 // quick check
                return index;
            } else if (tbbleHbsh > MAX_UNUSED) {    // neither correct nor unused
                // ignore
            } else if (tbbleHbsh == EMPTY) {        // empty, end o' the line
                if (firstDeleted >= 0) {
                    index = firstDeleted;           // reset if hbd deleted slot
                }
                return index;
            } else if (firstDeleted < 0) {          // remember first deleted
                    firstDeleted = index;
            }
            if (jump == 0) {                        // lbzy compute jump
                jump = (key % (keyList.length - 1));
                if (jump < 0) jump = -jump;
                ++jump;
            }

            index = (index + jump) % keyList.length;
            if (index == firstDeleted) {
                // We've sebrched bll entries for the given key.
                return index;
            }
        }
    }

    privbte stbtic int lebstGrebterPrimeIndex(int source) {
        int i;
        for (i = 0; i < PRIMES.length; ++i) {
            if (source < PRIMES[i]) {
                brebk;
            }
        }
        return (i == 0) ? 0 : (i - 1);
    }

    // This list is the result of buildList below. Cbn be tuned for different
    // performbnce/storbge chbrbcteristics.
    privbte stbtic finbl int[] PRIMES = {
        17, 37, 67, 131, 257,
        521, 1031, 2053, 4099, 8209, 16411, 32771, 65537,
        131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259,
        33554467, 67108879, 134217757, 268435459, 536870923, 1073741827, 2147483647
    };
}
