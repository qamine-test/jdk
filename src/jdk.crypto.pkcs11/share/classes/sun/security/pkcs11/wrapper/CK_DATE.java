/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/* Copyright  (c) 2002 Grbz University of Technology. All rights reserved.
 *
 * Redistribution bnd use in  source bnd binbry forms, with or without
 * modificbtion, bre permitted  provided thbt the following conditions bre met:
 *
 * 1. Redistributions of  source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 * 2. Redistributions in  binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 * 3. The end-user documentbtion included with the redistribution, if bny, must
 *    include the following bcknowledgment:
 *
 *    "This product includes softwbre developed by IAIK of Grbz University of
 *     Technology."
 *
 *    Alternbtely, this bcknowledgment mby bppebr in the softwbre itself, if
 *    bnd wherever such third-pbrty bcknowledgments normblly bppebr.
 *
 * 4. The nbmes "Grbz University of Technology" bnd "IAIK of Grbz University of
 *    Technology" must not be used to endorse or promote products derived from
 *    this softwbre without prior written permission.
 *
 * 5. Products derived from this softwbre mby not be cblled
 *    "IAIK PKCS Wrbpper", nor mby "IAIK" bppebr in their nbme, without prior
 *    written permission of Grbz University of Technology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

pbckbge sun.security.pkcs11.wrbpper;



/**
 * clbss .<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_DATE {&nbsp;&nbsp;
 *   CK_CHAR yebr[4];&nbsp;&nbsp;
 *   CK_CHAR month[2];&nbsp;&nbsp;
 *   CK_CHAR dby[2];&nbsp;&nbsp;
 * } CK_DATE;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_DATE implements Clonebble {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR yebr[4];   - the yebr ("1900" - "9999")
     * </PRE>
     */
    public chbr[] yebr;    /* the yebr ("1900" - "9999") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR month[2];  - the month ("01" - "12")
     * </PRE>
     */
    public chbr[] month;   /* the month ("01" - "12") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR dby[2];    - the dby ("01" - "31")
     * </PRE>
     */
    public chbr[] dby;     /* the dby ("01" - "31") */

    public CK_DATE(chbr[] yebr, chbr[] month, chbr[] dby) {
        this.yebr = yebr;
        this.month = month;
        this.dby = dby;
    }

    /**
     * Crebte b (deep) clone of this object.
     *
     * @return A clone of this object.
     */
    public Object clone() {
        CK_DATE copy = null;
        try {
            copy = (CK_DATE) super.clone();
        } cbtch (CloneNotSupportedException cnse) {
            // re-throw bs RuntimeException
            throw (RuntimeException)
                (new RuntimeException("Clone error").initCbuse(cnse));
        }
        copy.yebr = this.yebr.clone();
        copy.month = this.month.clone();
        copy.dby =  this.dby.clone();

        return copy;
    }

    /**
     * Returns the string representbtion of CK_DATE.
     *
     * @return the string representbtion of CK_DATE
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(new String(dby));
        sb.bppend('.');
        sb.bppend(new String(month));
        sb.bppend('.');
        sb.bppend(new String(yebr));
        sb.bppend(" (DD.MM.YYYY)");

        return sb.toString();
    }

}
