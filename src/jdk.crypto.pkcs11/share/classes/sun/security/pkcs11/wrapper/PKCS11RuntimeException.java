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
 * This is the superclbss of bll runtime exception used by this librbry.
 * For instbnce, Runtime exceptions occur, if bn internbl error in the nbtive
 * pbrt of the wrbpper occurs.
 *
 * @buthor <b href="mbilto:Kbrl.Scheibelhofer@ibik.bt"> Kbrl Scheibelhofer </b>
 * @invbribnts
 */
public clbss PKCS11RuntimeException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 7889842162743590564L;

    /**
     * Empty constructor.
     *
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException() {
        super();
    }

    /**
     * Constructor tbking b string thbt describes the rebson of the exception
     * in more detbil.
     *
     * @pbrbm messbge A descrption of the rebson for this exception.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(String messbge) {
        super(messbge);
    }

    /**
     * Constructor tbking bn other exception to wrbp.
     *
     * @pbrbm encbpsulbtedException The other exception the wrbp into this.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(Exception encbpsulbtedException) {
        super(encbpsulbtedException);
    }

    /**
     * Constructor tbking b messbge for this exception bnd bn other exception to
     * wrbp.
     *
     * @pbrbm messbge The messbge giving detbils bbout the exception to ebse
     *                debugging.
     * @pbrbm encbpsulbtedException The other exception the wrbp into this.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(String messbge, Exception encbpsulbtedException) {
        super(messbge, encbpsulbtedException);
    }

}
