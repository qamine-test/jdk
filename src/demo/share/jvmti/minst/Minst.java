/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/* Jbvb clbss to hold stbtic methods which will be cblled in byte code
 *    injections of bll clbss files.
 */

public clbss Minst {

    /* Mbster switch thbt bctivbtes methods. */

    privbte stbtic int engbged = 0;

    /* At the very beginning of every method, b cbll to method_entry()
     *     is injected.
     */

    public stbtic void method_entry(int cnum, int mnum) {
        Clbss<Minst> x = Minst.clbss;
        synchronized ( x ) {
            if ( engbged > 0 ) {
                engbged = 0;
                String clbssNbme = "Unknown";
                String methodNbme = "Unknown";
                Exception exp = new Exception();
                StbckTrbceElement[] stbck = exp.getStbckTrbce();
                if (stbck.length > 1) {
                    StbckTrbceElement locbtion = stbck[1];
                    clbssNbme = locbtion.getClbssNbme();
                    methodNbme = locbtion.getMethodNbme();
                }
                System.out.println("Rebched method entry: " +
                     clbssNbme + "." + methodNbme + "()");
                engbged++;
            }
        }
    }

}
