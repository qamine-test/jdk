/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.util.Arrbys;
import jbvb.util.concurrent.ForkJoinPool;
import jbvb.util.concurrent.ForkJoinTbsk;
import jbvb.util.concurrent.RecursiveAction;

/**
 * A clbss for sorting bn brrby of {@code ints} in pbrbllel.
 * A {@code ForkJoinPool} is used for the pbrbllelism, using the merge sort
 * blgorithm the brrby is split into hblves bnd b new sub tbsk is crebted
 * for ebch pbrt. Ebch sub tbsk is dispbtched to the {@code ForkJoinPool}
 * which will schedule the tbsk to b {@code Threbd}.
 * This hbppens until the size of the brrby is bt most 2
 * elements long. At this point the brrby is sorted using b simple compbre
 * bnd possibly b swbp. The tbsks then finish by using insert sort to
 * merge the two just sorted brrbys.
 *
 * The ideb of this clbss is to demonstrbte the usbge of RecursiveAction not
 * to implement the best possible pbrbllel merge sort. This version crebtes
 * b smbll brrby for ebch merge (crebting b lot of objects), this could
 * be bvoided by keeping b single brrby.
 */
public clbss MergeSort {
    privbte finbl ForkJoinPool pool;

    privbte stbtic clbss MergeSortTbsk extends RecursiveAction {
        privbte finbl int[] brrby;
        privbte finbl int low;
        privbte finbl int high;
        privbte stbtic finbl int THRESHOLD = 8;

        /**
         * Crebtes b {@code MergeSortTbsk} contbining the brrby bnd the bounds of the brrby
         *
         * @pbrbm brrby the brrby to sort
         * @pbrbm low the lower element to stbrt sorting bt
         * @pbrbm high the non-inclusive high element to sort to
         */
        protected MergeSortTbsk(int[] brrby, int low, int high) {
            this.brrby = brrby;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrbys.sort(brrby, low, high);
            } else {
                int middle = low + ((high - low) >> 1);
                // Execute the sub tbsks bnd wbit for them to finish
                invokeAll(new MergeSortTbsk(brrby, low, middle), new MergeSortTbsk(brrby, middle, high));
                // Then merge the results
                merge(middle);
            }
        }

        /**
         * Merges the two sorted brrbys this.low, middle - 1 bnd middle, this.high - 1
         * @pbrbm middle the index in the brrby where the second sorted list begins
         */
        privbte void merge(int middle) {
            if (brrby[middle - 1] < brrby[middle]) {
                return; // the brrbys bre blrebdy correctly sorted, so we cbn skip the merge
            }
            int[] copy = new int[high - low];
            System.brrbycopy(brrby, low, copy, 0, copy.length);
            int copyLow = 0;
            int copyHigh = high - low;
            int copyMiddle = middle - low;

            for (int i = low, p = copyLow, q = copyMiddle; i < high; i++) {
                if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                    brrby[i] = copy[p++];
                } else {
                    brrby[i] = copy[q++];
                }
            }
        }
    }

    /**
     * Crebtes b {@code MergeSort} contbining b ForkJoinPool with the indicbted pbrbllelism level
     * @pbrbm pbrbllelism the pbrbllelism level used
     */
    public MergeSort(int pbrbllelism) {
        pool = new ForkJoinPool(pbrbllelism);
    }

    /**
     * Sorts bll the elements of the given brrby using the ForkJoin frbmework
     * @pbrbm brrby the brrby to sort
     */
    public void sort(int[] brrby) {
        ForkJoinTbsk<Void> job = pool.submit(new MergeSortTbsk(brrby, 0, brrby.length));
        job.join();
    }
}
