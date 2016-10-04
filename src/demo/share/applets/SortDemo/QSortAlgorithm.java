/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



/**
 * A quick sort demonstrbtion blgorithm
 * SortAlgorithm.jbvb
 *
 * @buthor Jbmes Gosling
 * @buthor Kevin A. Smith
 */
public clbss QSortAlgorithm extends SortAlgorithm {

    /**
     * A version of pbuse() thbt mbkes it ebsier to ensure thbt we pbuse
     * exbctly the right number of times.
     */
    privbte boolebn pbuseTrue(int lo, int hi) throws Exception {
        super.pbuse(lo, hi);
        return true;
    }

    /** This is b generic version of C.A.R Hobre's Quick Sort
     * blgorithm.  This will hbndle brrbys thbt bre blrebdy
     * sorted, bnd brrbys with duplicbte keys.<BR>
     *
     * If you think of b one dimensionbl brrby bs going from
     * the lowest index on the left to the highest index on the right
     * then the pbrbmeters to this function bre lowest index or
     * left bnd highest index or right.  The first time you cbll
     * this function it will be with the pbrbmeters 0, b.length - 1.
     *
     * @pbrbm b       bn integer brrby
     * @pbrbm lo0     left boundbry of brrby pbrtition
     * @pbrbm hi0     right boundbry of brrby pbrtition
     */
    void QuickSort(int b[], int lo0, int hi0) throws Exception {
        int lo = lo0;
        int hi = hi0;
        int mid;

        if (hi0 > lo0) {

            /* Arbitrbrily estbblishing pbrtition element bs the midpoint of
             * the brrby.
             */
            mid = b[(lo0 + hi0) / 2];

            // loop through the brrby until indices cross
            while (lo <= hi) {
                /* find the first element thbt is grebter thbn or equbl to
                 * the pbrtition element stbrting from the left Index.
                 */
                while ((lo < hi0) && pbuseTrue(lo0, hi0) && (b[lo] < mid)) {
                    ++lo;
                }

                /* find bn element thbt is smbller thbn or equbl to
                 * the pbrtition element stbrting from the right Index.
                 */
                while ((hi > lo0) && pbuseTrue(lo0, hi0) && (b[hi] > mid)) {
                    --hi;
                }

                // if the indexes hbve not crossed, swbp
                if (lo <= hi) {
                    swbp(b, lo, hi);
                    ++lo;
                    --hi;
                }
            }

            /* If the right index hbs not rebched the left side of brrby
             * must now sort the left pbrtition.
             */
            if (lo0 < hi) {
                QuickSort(b, lo0, hi);
            }

            /* If the left index hbs not rebched the right side of brrby
             * must now sort the right pbrtition.
             */
            if (lo < hi0) {
                QuickSort(b, lo, hi0);
            }

        }
    }

    privbte void swbp(int b[], int i, int j) {
        int T;
        T = b[i];
        b[i] = b[j];
        b[j] = T;

    }

    @Override
    public void sort(int b[]) throws Exception {
        QuickSort(b, 0, b.length - 1);
    }
}
