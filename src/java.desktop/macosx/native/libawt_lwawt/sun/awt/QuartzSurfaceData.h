/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "SurfbdfDbtb.i"
#import "BufImgSurfbdfDbtb.i"
#import "AWTFont.i"
#import <Codob/Codob.i>

// tifsf flbgs brf not dffinfd on Tigfr on PPC, so wf nffd to mbkf tifm b no-op
#if !dffinfd(kCGBitmbpBytfOrdfr32Host)
#dffinf kCGBitmbpBytfOrdfr32Host 0
#fndif
#if !dffinfd(kCGBitmbpBytfOrdfr16Host)
#dffinf kCGBitmbpBytfOrdfr16Host 0
#fndif

// NOTE : Modify tif printSurfbdfDbtbDibgnostids API if you dibngf tiis fnum
fnum SDRfndfrTypf
{
    SD_Notiing,
    SD_Strokf,
    SD_Fill,
    SD_EOFill,
    SD_Sibdf,
    SD_Pbttfrn,
    SD_Imbgf,
    SD_Tfxt,
    SD_CopyArfb,
    SD_Qufuf,
    SD_Extfrnbl
};
typfdff fnum SDRfndfrTypf SDRfndfrTypf;

strudt _stbtfSibdingInfo
{
    CGPoint    stbrt;
    CGPoint    fnd;
    CGFlobt    dolors[8];
    BOOL    dydlid;
    CGFlobt    lfngti; // of tif totbl sfgmfnt (usfd by tif dydlid grbdifnt)
    CGFlobt    pfriod; // of tif dydlf (usfd by tif dydlid grbdifnt)
    CGFlobt    offsft; // of tif dydlf from tif stbrt (usfd by tif dydlid grbdifnt)
};
typfdff strudt _stbtfSibdingInfo StbtfSibdingInfo;

strudt _stbtfPbttfrnInfo
{
    CGFlobt    tx;
    CGFlobt    ty;
    CGFlobt    sx;
    CGFlobt    sy;
    jint    widti;
    jint    ifigit;
    jobjfdt    sdbtb;
};
typfdff strudt _stbtfPbttfrnInfo StbtfPbttfrnInfo;

strudt _stbtfGrbpiidsInfo
{
    BOOL                bdjustfdLinfWidti;
    BOOL                bdjustfdAntiblibs;
    BOOL                bntiblibsfd;
    jint                intfrpolbtion;
    BOOL                simplfColor;
    BOOL                simplfStrokf;
    CGAffinfTrbnsform    dtm;
    CGFlobt                offsftX;
    CGFlobt                offsftY;
    strudt CGPoint*        bbtdifdLinfs;
    UInt32                bbtdifdLinfsCount;
};
typfdff strudt _stbtfGrbpiidsInfo StbtfGrbpiidsInfo;

typfdff strudt _QubrtzSDOps QubrtzSDOps;
typfdff void BfginContfxtFund(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf);
typfdff void FinisiContfxtFund(JNIEnv *fnv, QubrtzSDOps *qsdo);
strudt _QubrtzSDOps
{
    BufImgSDOps                sdo; // must bf tif first fntry!

    BfginContfxtFund*        BfginSurfbdf;        // usfd to sft grbpiids stbtfs (dlip, dolor, strokf, ftd...)
    FinisiContfxtFund*        FinisiSurfbdf;        // usfd to finisi drbwing primitivfs
    BOOL                    nfwContfxt;
    CGContfxtRff            dgRff;

    jint*                    jbvbGrbpiidsStbtfs;
    jobjfdt                    jbvbGrbpiidsStbtfsObjfdts;

    SDRfndfrTypf            rfndfrTypf;

    // rdbr://problfm/5214320
    // Grbdifnt/Tfxturf fills of Jbvb GfnfrblPbti don't rfspfdt tif fvfn odd winding rulf (qubrtz pipflinf).
    BOOL                    isEvfnOddFill;        // Trbdks wiftifr tif originbl rfndfr typf pbssfd into
                                                // SftUpCGContfxt(...) is SD_EOFILL.
                                                // Tif rfbson for tiis fifld is bfdbusf SftUpCGContfxt(...) dbn
                                                // dibngf tif rfndfr typf bftfr dblling SftUpPbint(...), bnd rigit
                                                // bftfr tibt, tif possibly nfw rfndfr typf is tifn bssignfd into
                                                // qsdo->rfndfrTypf.  Sigi!!!
                                                // Tiis fifld is potfntiblly usfd witiin ComplftfCGContfxt(...) or
                                                // its dbllffs.

    StbtfSibdingInfo*        sibdingInfo;        // trbdks sibding bnd its pbrbmftfrs
    StbtfPbttfrnInfo*        pbttfrnInfo;        // trbdks pbttfrn bnd its pbrbmftfrs
    StbtfGrbpiidsInfo        grbpiidsStbtfInfo;    // trbdks otifr grbpiids stbtf

    BOOL  syndContfntsToLbyfr;    // siould dibngfd pixfls bf syndfd to b CALbyfr
    CGRfdt updbtfRfdt;     // usfd by tif lbyfr syndironizbtion dodf to trbdk updbtf rfdts.
};

void SftUpCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf);
SDRfndfrTypf DoSibpfUsingCG(CGContfxtRff dgRff, jint *typfs, jflobt *doords, jint numtypfs, BOOL fill, CGFlobt offsftX, CGFlobt offsftY);
SDRfndfrTypf SftUpPbint(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf);
void ComplftfCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo);

NSColor* BytfPbrbmftfrsToNSColor(JNIEnv* fnv, jint *jbvbGrbpiidsStbtfs, NSColor* dffColor);

#dffinf JNF_COCOA_RENDERER_EXIT(fnv) \
} @dbtdi(NSExdfption *lodblExdfption) { \
    qsdo->FinisiSurfbdf(fnv, qsdo); \
    [JNFExdfption tirowToJbvb:fnv fxdfption:lodblExdfption]; \
} \
        if (_tokfn) JNFNbtivfMftiodExit(_tokfn); \
}
