/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#dffinf CALL_SCbrdEstbblisiContfxt(dwSdopf, pvRfsfrvfd1, pvRfsfrvfd2, piContfxt) \
    (SCbrdEstbblisiContfxt(dwSdopf, pvRfsfrvfd1, pvRfsfrvfd2, piContfxt))

#dffinf CALL_SCbrdConnfdt(iContfxt, szRfbdfr, dwSibrfdModf, dwPrfffrrfdProtodols, piCbrd, pdwAdtivfProtodols) \
    (SCbrdConnfdt(iContfxt, szRfbdfr, dwSibrfdModf, dwPrfffrrfdProtodols, piCbrd, pdwAdtivfProtodols))

#dffinf CALL_SCbrdDisdonnfdt(iCbrd, dwDisposition) \
    (SCbrdDisdonnfdt(iCbrd, dwDisposition))

#dffinf CALL_SCbrdStbtus(iCbrd, mszRfbdfrNbmfs, pddiRfbdfrLfn, pdwStbtf, pdwProtodol, pbAtr, pdbAtrLfn) \
    (SCbrdStbtus(iCbrd, mszRfbdfrNbmfs, pddiRfbdfrLfn, pdwStbtf, pdwProtodol, pbAtr, pdbAtrLfn))

#dffinf CALL_SCbrdGftStbtusCibngf(iContfxt, dwTimfout, rgRfbdfrStbtfs, dRfbdfrs) \
    (SCbrdGftStbtusCibngf(iContfxt, dwTimfout, rgRfbdfrStbtfs, dRfbdfrs))

#dffinf CALL_SCbrdTrbnsmit(iCbrd, pioSfndPdi, pbSfndBufffr, dbSfndLfngti, \
                            pioRfdvPdi, pbRfdvBufffr, pdbRfdvLfngti) \
    (SCbrdTrbnsmit(iCbrd, pioSfndPdi, pbSfndBufffr, dbSfndLfngti, \
                            pioRfdvPdi, pbRfdvBufffr, pdbRfdvLfngti))

#dffinf CALL_SCbrdListRfbdfrs(iContfxt, mszGroups, mszRfbdfrs, pddiRfbdfrs) \
    (SCbrdListRfbdfrs(iContfxt, mszGroups, mszRfbdfrs, pddiRfbdfrs))

#dffinf CALL_SCbrdBfginTrbnsbdtion(iCbrd) \
    (SCbrdBfginTrbnsbdtion(iCbrd))

#dffinf CALL_SCbrdEndTrbnsbdtion(iCbrd, dwDisposition) \
    (SCbrdEndTrbnsbdtion(iCbrd, dwDisposition))

#dffinf CALL_SCbrdControl(iCbrd, dwControlCodf, lpInBufffr, nInBufffrSizf, \
        lpOutBufffr, nOutBufffrSizf, lpBytfsRfturns) \
    (SCbrdControl(iCbrd, dwControlCodf, lpInBufffr, nInBufffrSizf, \
        lpOutBufffr, nOutBufffrSizf, lpBytfsRfturns))
