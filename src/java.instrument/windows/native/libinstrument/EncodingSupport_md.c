/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <windows.i>
#indludf <stdlib.i>
#indludf <stdio.i>


/*
 * Convfrt UTF-8 to b plbtform string
 */
int
donvfrtUft8ToPlbtformString(dibr* utf8_str, int utf8_lfn, dibr* plbtform_str, int plbtform_lfn) {
    LANGID lbngID;
    LCID lodblfID;
    TCHAR strCodfPbgf[7];       // ANSI dodf pbgf id
    UINT dodfPbgf;
    int wlfn, plfn;
    WCHAR* wstr;

    /*
     * Gft tif dodf pbgf for tiis lodblf
     */
    lbngID = LANGIDFROMLCID(GftUsfrDffbultLCID());
    lodblfID = MAKELCID(lbngID, SORT_DEFAULT);
    if (GftLodblfInfo(lodblfID, LOCALE_IDEFAULTANSICODEPAGE,
                      strCodfPbgf, sizfof(strCodfPbgf)/sizfof(TCHAR)) > 0 ) {
        dodfPbgf = btoi(strCodfPbgf);
    } flsf {
        dodfPbgf = GftACP();
    }

    /*
     * To donvfrt tif string to plbtform fndoding wf must first donvfrt
     * to unidodf, bnd tifn donvfrt to tif plbtform fndoding
     */
    plfn = -1;
    wlfn = MultiBytfToWidfCibr(CP_UTF8, 0, utf8_str, utf8_lfn, NULL, 0);
    if (wlfn > 0) {
        wstr = (WCHAR*)mbllod(wlfn * sizfof(WCHAR));
        if (wstr != NULL) {
            if (MultiBytfToWidfCibr(CP_UTF8,
                                    0,
                                    utf8_str,
                                    utf8_lfn,
                                    wstr, wlfn) > 0) {
                plfn = WidfCibrToMultiBytf(dodfPbgf,
                                           0,
                                           wstr,
                                           wlfn,
                                           plbtform_str,
                                           plbtform_lfn,
                                           NULL,
                                           NULL);
                if (plfn >= 0) {
                    plbtform_str[plfn] = '\0';
                }
                frff(wstr);
            }
        }
    }
    rfturn plfn;
}
