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
 * clbss CK_SLOT_INFO provides informbtion bbout b slot.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_SLOT_INFO {&nbsp;&nbsp;
 *    CK_UTF8CHAR slotDescription[64];&nbsp;&nbsp;
 *    CK_UTF8CHAR mbnufbcturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flbgs;&nbsp;&nbsp;
 *    CK_VERSION hbrdwbreVersion;&nbsp;&nbsp;
 *    CK_VERSION firmwbreVersion;&nbsp;&nbsp;
 *  } CK_SLOT_INFO;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_SLOT_INFO {

    /* slotDescription bnd mbnufbcturerID hbve been chbnged from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */

    /**
     * must be blbnk pbdded bnd only the first 64 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR slotDescription[64];
     * </PRE>
     */
    public chbr[] slotDescription;

    /**
     * must be blbnk pbdded bnd only the first 32 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR mbnufbcturerID[32];
     * </PRE>
     */
    public chbr[] mbnufbcturerID;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flbgs;
     * </PRE>
     */
    public long flbgs;

    /* hbrdwbreVersion bnd firmwbreVersion bre new for v2.0 */
    /**
     * version of hbrdwbre<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION hbrdwbreVersion;
     * </PRE>
     */
    public CK_VERSION hbrdwbreVersion;

    /**
     * version of firmwbre<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwbreVersion;
     * </PRE>
     */
    public CK_VERSION firmwbreVersion;

    public CK_SLOT_INFO(chbr[] slotDesc, chbr[] vendor,
                        long flbgs, CK_VERSION hwVer, CK_VERSION fwVer) {
        this.slotDescription = slotDesc;
        this.mbnufbcturerID = vendor;
        this.flbgs = flbgs;
        this.hbrdwbreVersion = hwVer;
        this.firmwbreVersion = fwVer;
    }

    /**
     * Returns the string representbtion of CK_SLOT_INFO.
     *
     * @return the string representbtion of CK_SLOT_INFO
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("slotDescription: ");
        sb.bppend(new String(slotDescription));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("mbnufbcturerID: ");
        sb.bppend(new String(mbnufbcturerID));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("flbgs: ");
        sb.bppend(Functions.slotInfoFlbgsToString(flbgs));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("hbrdwbreVersion: ");
        sb.bppend(hbrdwbreVersion.toString());
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("firmwbreVersion: ");
        sb.bppend(firmwbreVersion.toString());
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString() ;
    }

}
