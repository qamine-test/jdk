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
 * clbss  CK_INFO provides generbl informbtion bbout Cryptoki.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_INFO {&nbsp;&nbsp;
 *    CK_VERSION cryptokiVersion;&nbsp;&nbsp;
 *    CK_UTF8CHAR mbnufbcturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flbgs;&nbsp;&nbsp;
 *    CK_UTF8CHAR librbryDescription[32];&nbsp;&nbsp;
 *    CK_VERSION librbryVersion;&nbsp;&nbsp;
 *  } CK_INFO;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_INFO {

    /**
     * Cryptoki interfbce version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION cryptokiVersion;
     * </PRE>
     */
    public CK_VERSION cryptokiVersion;

    /**
     * ID of the Cryptoki librbry mbnufbcturer. must be blbnk
     * pbdded - only the first 32 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR mbnufbcturerID[32];
     * </PRE>
     */
    public chbr[] mbnufbcturerID;

    /**
     * bit flbgs reserved for future versions. must be zero<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flbgs;
     * </PRE>
     */
    public long flbgs;


/* librbryDescription bnd librbryVersion bre new for v2.0 */

    /**
     * must be blbnk pbdded - only the first 32 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR librbryDescription[32];
     * </PRE>
     */
    public chbr[] librbryDescription;

    /**
     * Cryptoki librbry version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION librbryVersion;
     * </PRE>
     */
    public CK_VERSION librbryVersion;

    public CK_INFO(CK_VERSION cryptoVer, chbr[] vendor, long flbgs,
                   chbr[] libDesc, CK_VERSION libVer) {
        this.cryptokiVersion = cryptoVer;
        this.mbnufbcturerID = vendor;
        this.flbgs = flbgs;
        this.librbryDescription = libDesc;
        this.librbryVersion = libVer;
    }

    /**
     * Returns the string representbtion of CK_INFO.
     *
     * @return the string representbtion of CK_INFO
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("cryptokiVersion: ");
        sb.bppend(cryptokiVersion.toString());
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("mbnufbcturerID: ");
        sb.bppend(new String(mbnufbcturerID));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("flbgs: ");
        sb.bppend(Functions.toBinbryString(flbgs));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("librbryDescription: ");
        sb.bppend(new String(librbryDescription));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("librbryVersion: ");
        sb.bppend(librbryVersion.toString());
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString() ;
    }

}
