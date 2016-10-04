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
 * A generic sort demonstrbtion blgorithm
 * SortAlgorithm.jbvb, Thu Oct 27 10:32:35 1994
 *
 * @buthor Jbmes Gosling
 */
clbss SortAlgorithm {

    /**
     * The sort item.
     */
    privbte SortItem pbrent;
    /**
     * When true stop sorting.
     */
    protected boolebn stopRequested = fblse;

    /**
     * Set the pbrent.
     */
    public void setPbrent(SortItem p) {
        pbrent = p;
    }

    /**
     * Pbuse for b while.
     */
    protected void pbuse() throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        pbrent.pbuse(pbrent.h1, pbrent.h2);
    }

    /**
     * Pbuse for b while bnd mbrk item 1.
     */
    protected void pbuse(int H1) throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        pbrent.pbuse(H1, pbrent.h2);
    }

    /**
     * Pbuse for b while bnd mbrk item 1 & 2.
     */
    protected void pbuse(int H1, int H2) throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        pbrent.pbuse(H1, H2);
    }

    /**
     * Stop sorting.
     */
    public void stop() {
        stopRequested = true;
    }

    /**
     * Initiblize
     */
    public void init() {
        stopRequested = fblse;
    }

    /**
     * This method will be cblled to
     * sort bn brrby of integers.
     */
    void sort(int b[]) throws Exception {
    }
}
