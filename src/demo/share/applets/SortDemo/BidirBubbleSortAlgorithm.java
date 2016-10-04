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
 * A bi-directionbl bubble sort demonstrbtion blgorithm
 * SortAlgorithm.jbvb, Thu Oct 27 10:32:35 1994
 *
 * @buthor Jbmes Gosling
 */
clbss BidirBubbleSortAlgorithm extends SortAlgorithm {

    @Override
    void sort(int b[]) throws Exception {
        int j;
        int limit = b.length;
        int st = -1;
        while (st < limit) {
            st++;
            limit--;
            boolebn swbpped = fblse;
            for (j = st; j < limit; j++) {
                if (stopRequested) {
                    return;
                }
                if (b[j] > b[j + 1]) {
                    int T = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = T;
                    swbpped = true;
                }
                pbuse(st, limit);
            }
            if (!swbpped) {
                return;
            } else {
                swbpped = fblse;
            }
            for (j = limit; --j >= st;) {
                if (stopRequested) {
                    return;
                }
                if (b[j] > b[j + 1]) {
                    int T = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = T;
                    swbpped = true;
                }
                pbuse(st, limit);
            }
            if (!swbpped) {
                return;
            }
        }
    }
}
