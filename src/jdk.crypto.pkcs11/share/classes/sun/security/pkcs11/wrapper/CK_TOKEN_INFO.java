/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * clbss CK_TOKEN_INFO provides informbtion bbout b token.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_TOKEN_INFO {&nbsp;&nbsp;
 *   CK_UTF8CHAR lbbel[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR mbnufbcturerID[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR model[16];&nbsp;&nbsp;
 *   CK_CHAR seriblNumber[16];&nbsp;&nbsp;
 *   CK_FLAGS flbgs;&nbsp;&nbsp;
 *   CK_ULONG ulMbxSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMbxRwSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulRwSessionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMbxPinLen;&nbsp;&nbsp;
 *   CK_ULONG ulMinPinLen;&nbsp;&nbsp;
 *   CK_ULONG ulTotblPublicMemory;&nbsp;&nbsp;
 *   CK_ULONG ulFreePublicMemory;&nbsp;&nbsp;
 *   CK_ULONG ulTotblPrivbteMemory;&nbsp;&nbsp;
 *   CK_ULONG ulFreePrivbteMemory;&nbsp;&nbsp;
 *   CK_VERSION hbrdwbreVersion;&nbsp;&nbsp;
 *   CK_VERSION firmwbreVersion;&nbsp;&nbsp;
 *   CK_CHAR utcTime[16];&nbsp;&nbsp;
 * } CK_TOKEN_INFO;
 * &nbsp;&nbsp;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_TOKEN_INFO {

    /* lbbel, mbnufbcturerID, bnd model hbve been chbnged from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */
    /**
     * must be blbnk pbdded bnd only the first 32 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR lbbel[32];
     * </PRE>
     */
    public chbr[] lbbel;           /* blbnk pbdded */

    /**
     * must be blbnk pbdded bnd only the first 32 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR mbnufbcturerID[32];
     * </PRE>
     */
    public chbr[] mbnufbcturerID;  /* blbnk pbdded */

    /**
     * must be blbnk pbdded bnd only the first 16 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR model[16];
     * </PRE>
     */
    public chbr[] model;           /* blbnk pbdded */

    /**
     * must be blbnk pbdded bnd only the first 16 chbrs will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR seriblNumber[16];
     * </PRE>
     */
    public chbr[] seriblNumber;    /* blbnk pbdded */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flbgs;
     * </PRE>
     */
    public long flbgs;               /* see below */

    /* ulMbxSessionCount, ulSessionCount, ulMbxRwSessionCount,
     * ulRwSessionCount, ulMbxPinLen, bnd ulMinPinLen hbve bll been
     * chbnged from CK_USHORT to CK_ULONG for v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxSessionCount;
     * </PRE>
     */
    public long ulMbxSessionCount;     /* mbx open sessions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSessionCount;
     * </PRE>
     */
    public long ulSessionCount;        /* sess. now open */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxRwSessionCount;
     * </PRE>
     */
    public long ulMbxRwSessionCount;   /* mbx R/W sessions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulRwSessionCount;
     * </PRE>
     */
    public long ulRwSessionCount;      /* R/W sess. now open */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxPinLen;
     * </PRE>
     */
    public long ulMbxPinLen;           /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMinPinLen;
     * </PRE>
     */
    public long ulMinPinLen;           /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotblPublicMemory;
     * </PRE>
     */
    public long ulTotblPublicMemory;   /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFreePublicMemory;
     * </PRE>
     */
    public long ulFreePublicMemory;    /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotblPrivbteMemory;
     * </PRE>
     */
    public long ulTotblPrivbteMemory;  /* in bytes */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFreePrivbteMemory;
     * </PRE>
     */
    public long ulFreePrivbteMemory;   /* in bytes */

    /* hbrdwbreVersion, firmwbreVersion, bnd time bre new for
     * v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION hbrdwbreVersion;
     * </PRE>
     */
    public CK_VERSION    hbrdwbreVersion;       /* version of hbrdwbre */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwbreVersion;
     * </PRE>
     */
    public CK_VERSION    firmwbreVersion;       /* version of firmwbre */

    /**
     * only the first 16 chbrs will be used
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR utcTime[16];
     * </PRE>
     */
    public chbr[] utcTime;           /* time */

    public CK_TOKEN_INFO(chbr[] lbbel, chbr[] vendor, chbr[] model,
                         chbr[] seriblNo, long flbgs,
                         long sessionMbx, long session,
                         long rwSessionMbx, long rwSession,
                         long pinLenMbx, long pinLenMin,
                         long totblPubMem, long freePubMem,
                         long totblPrivMem, long freePrivMem,
                         CK_VERSION hwVer, CK_VERSION fwVer, chbr[] utcTime) {
        this.lbbel = lbbel;
        this.mbnufbcturerID = vendor;
        this.model = model;
        this.seriblNumber = seriblNo;
        this.flbgs = flbgs;
        this.ulMbxSessionCount = sessionMbx;
        this.ulSessionCount = session;
        this.ulMbxRwSessionCount = rwSessionMbx;
        this.ulRwSessionCount = rwSession;
        this.ulMbxPinLen = pinLenMbx;
        this.ulMinPinLen = pinLenMin;
        this.ulTotblPublicMemory = totblPubMem;
        this.ulFreePublicMemory = freePubMem;
        this.ulTotblPrivbteMemory = totblPrivMem;
        this.ulFreePrivbteMemory = freePrivMem;
        this.hbrdwbreVersion = hwVer;
        this.firmwbreVersion = fwVer;
        this.utcTime = utcTime;
    }

    /**
     * Returns the string representbtion of CK_TOKEN_INFO.
     *
     * @return the string representbtion of CK_TOKEN_INFO
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("lbbel: ");
        sb.bppend(new String(lbbel));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("mbnufbcturerID: ");
        sb.bppend(new String(mbnufbcturerID));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("model: ");
        sb.bppend(new String(model));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("seriblNumber: ");
        sb.bppend(new String(seriblNumber));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("flbgs: ");
        sb.bppend(Functions.tokenInfoFlbgsToString(flbgs));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulMbxSessionCount: ");
        sb.bppend((ulMbxSessionCount == PKCS11Constbnts.CK_EFFECTIVELY_INFINITE)
                  ? "CK_EFFECTIVELY_INFINITE"
                  : (ulMbxSessionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                    ? "CK_UNAVAILABLE_INFORMATION"
                    : String.vblueOf(ulMbxSessionCount));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulSessionCount: ");
        sb.bppend((ulSessionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulSessionCount));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulMbxRwSessionCount: ");
        sb.bppend((ulMbxRwSessionCount == PKCS11Constbnts.CK_EFFECTIVELY_INFINITE)
                  ? "CK_EFFECTIVELY_INFINITE"
                  : (ulMbxRwSessionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                    ? "CK_UNAVAILABLE_INFORMATION"
                    : String.vblueOf(ulMbxRwSessionCount));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulRwSessionCount: ");
        sb.bppend((ulRwSessionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulRwSessionCount));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulMbxPinLen: ");
        sb.bppend(String.vblueOf(ulMbxPinLen));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulMinPinLen: ");
        sb.bppend(String.vblueOf(ulMinPinLen));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulTotblPublicMemory: ");
        sb.bppend((ulTotblPublicMemory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulTotblPublicMemory));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulFreePublicMemory: ");
        sb.bppend((ulFreePublicMemory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulFreePublicMemory));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulTotblPrivbteMemory: ");
        sb.bppend((ulTotblPrivbteMemory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulTotblPrivbteMemory));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulFreePrivbteMemory: ");
        sb.bppend((ulFreePrivbteMemory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblueOf(ulFreePrivbteMemory));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("hbrdwbreVersion: ");
        sb.bppend(hbrdwbreVersion.toString());
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("firmwbreVersion: ");
        sb.bppend(firmwbreVersion.toString());
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("utcTime: ");
        sb.bppend(new String(utcTime));
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString() ;
    }

}
