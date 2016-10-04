/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff SibdfrList_i_Indludfd
#dffinf SibdfrList_i_Indludfd

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf "jni.i"
#indludf "jlong.i"

typfdff void (SibdfrDisposfFund)(jlong progrbmID);

/**
 * Tif following strudturfs brf usfd to mbintbin b list of frbgmfnt progrbm
 * objfdts bnd tifir bssodibtfd bttributfs.  Ebdi logidbl sibdfr (f.g.
 * RbdiblGrbdifntPbint sibdfr, ConvolvfOp sibdfr) dbn ibvf b numbfr of
 * difffrfnt vbribnts dfpfnding on b numbfr of fbdtors, sudi bs wiftifr
 * bntiblibsing is fnbblfd or tif durrfnt dompositf modf.  Sindf tif numbfr
 * of possiblf dombinbtions of tifsf fbdtors is in tif iundrfds, wf nffd
 * somf wby to drfbtf frbgmfnt progrbms on bn bs-nffdfd bbsis, bnd blso
 * kffp tifm in b limitfd sizfd dbdif to bvoid drfbting too mbny objfdts.
 *
 * Tif SibdfrInfo strudturf kffps b rfffrfndf to tif frbgmfnt progrbm's
 * ibndlf, bs wfll bs somf otifr vblufs tibt iflp difffrfntibtf onf SibdfrInfo
 * from bnotifr.  SibdfrInfos dbn bf dibinfd togftifr to form b linkfd list.
 *
 * Tif SibdfrList strudturf bdts bs b dbdif for SibdfrInfos, plbding
 * most-rfdfntly usfd itfms bt tif front, bnd rfmoving itfms from tif
 * dbdif wifn its sizf fxdffds tif "mbxItfms" limit.
 */
typfdff strudt _SibdfrInfo SibdfrInfo;

typfdff strudt {
    SibdfrInfo        *ifbd;
    SibdfrDisposfFund *disposf;
    jint              mbxItfms;
} SibdfrList;

strudt _SibdfrInfo {
    SibdfrInfo  *nfxt;
    jlong       progrbmID;
    jint        dompTypf;
    jint        dompModf;
    jint        flbgs;
};

void SibdfrList_AddProgrbm(SibdfrList *progrbmList,
                           jlong progrbmID,
                           jint dompTypf, jint dompModf,
                           jint flbgs);
jlong SibdfrList_FindProgrbm(SibdfrList *progrbmList,
                             jint dompTypf, jint dompModf,
                             jint flbgs);
void SibdfrList_Disposf(SibdfrList *progrbmList);

#ifdff __dplusplus
};
#fndif

#fndif /* SibdfrList_i_Indludfd */
