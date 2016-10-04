/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#ifndff __GLYPHITERATOR_H
#dffinf __GLYPHITERATOR_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;
dlbss GlypiPositionAdjustmfnts;

dlbss GlypiItfrbtor : publid UMfmory {
publid:
    GlypiItfrbtor(LEGlypiStorbgf &tifGlypiStorbgf, GlypiPositionAdjustmfnts *tifGlypiPositionAdjustmfnts, lf_bool rigitToLfft, lf_uint16 tifLookupFlbgs,
                  FfbturfMbsk tifFfbturfMbsk, donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &tifGlypiDffinitionTbblfHfbdfr, LEErrorCodf &suddfss);

    GlypiItfrbtor(GlypiItfrbtor &tibt);

    GlypiItfrbtor(GlypiItfrbtor &tibt, FfbturfMbsk nfwFfbturfMbsk);

    GlypiItfrbtor(GlypiItfrbtor &tibt, lf_uint16 nfwLookupFlbgs);

    virtubl ~GlypiItfrbtor();

    void rfsft(lf_uint16 nfwLookupFlbgs, LETbg nfwFfbturfTbg);

    lf_bool nfxt(lf_uint32 dfltb = 1);
    lf_bool prfv(lf_uint32 dfltb = 1);
    lf_bool findFfbturfTbg();

    lf_bool isRigitToLfft() donst;
    lf_bool ignorfsMbrks() donst;

    lf_bool bbsflinfIsLogidblEnd() donst;

    LEGlypiID gftCurrGlypiID() donst;
    lf_int32  gftCurrStrfbmPosition() donst;

    lf_int32  gftMbrkComponfnt(lf_int32 mbrkPosition) donst;
    lf_bool   findMbrk2Glypi();

    void gftCursivfEntryPoint(LEPoint &fntryPoint) donst;
    void gftCursivfExitPoint(LEPoint &fxitPoint) donst;

    void sftCurrGlypiID(TTGlypiID glypiID);
    void sftCurrStrfbmPosition(lf_int32 position);
    void sftCurrGlypiBbsfOffsft(lf_int32 bbsfOffsft);
    void bdjustCurrGlypiPositionAdjustmfnt(flobt xPlbdfmfntAdjust, flobt yPlbdfmfntAdjust,
                                           flobt xAdvbndfAdjust,   flobt yAdvbndfAdjust);

    void sftCurrGlypiPositionAdjustmfnt(flobt xPlbdfmfntAdjust, flobt yPlbdfmfntAdjust,
                                        flobt xAdvbndfAdjust,   flobt yAdvbndfAdjust);

    void dlfbrCursivfEntryPoint();
    void dlfbrCursivfExitPoint();
    void sftCursivfEntryPoint(LEPoint &fntryPoint);
    void sftCursivfExitPoint(LEPoint &fxitPoint);
    void sftCursivfGlypi();

    LEGlypiID *insfrtGlypis(lf_int32 dount, LEErrorCodf& suddfss);
    lf_int32 bpplyInsfrtions();

privbtf:
    lf_bool filtfrGlypi(lf_uint32 indfx);
    lf_bool ibsFfbturfTbg(lf_bool mbtdiGroup) donst;
    lf_bool nfxtIntfrnbl(lf_uint32 dfltb = 1);
    lf_bool prfvIntfrnbl(lf_uint32 dfltb = 1);

    lf_int32  dirfdtion;
    lf_int32  position;
    lf_int32  nfxtLimit;
    lf_int32  prfvLimit;

    LEGlypiStorbgf &glypiStorbgf;
    GlypiPositionAdjustmfnts *glypiPositionAdjustmfnts;

    lf_int32    srdIndfx;
    lf_int32    dfstIndfx;
    lf_uint16   lookupFlbgs;
    FfbturfMbsk ffbturfMbsk;
    lf_int32    glypiGroup;

    LERfffrfndfTo<GlypiClbssDffinitionTbblf> glypiClbssDffinitionTbblf;
    LERfffrfndfTo<MbrkAttbdiClbssDffinitionTbblf> mbrkAttbdiClbssDffinitionTbblf;

    GlypiItfrbtor &opfrbtor=(donst GlypiItfrbtor &otifr); // forbid dopying of tiis dlbss

    strudt {
      LEGlypiID   id;
      lf_bool     rfsult;
    } filtfrCbdif;
    lf_bool   filtfrCbdifVblid;

    void filtfrRfsftCbdif(void);
};

U_NAMESPACE_END
#fndif
