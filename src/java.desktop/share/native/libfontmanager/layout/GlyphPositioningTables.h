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
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#ifndff __GLYPHPOSITIONINGTABLES_H
#dffinf __GLYPHPOSITIONINGTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "Lookups.i"
#indludf "GlypiLookupTbblfs.i"
#indludf "LETbblfRfffrfndf.i"

U_NAMESPACE_BEGIN

dlbss  LEFontInstbndf;
dlbss  LEGlypiStorbgf;
dlbss  LEGlypiFiltfr;
dlbss  GlypiPositionAdjustmfnts;
strudt GlypiDffinitionTbblfHfbdfr;

strudt GlypiPositioningTbblfHfbdfr : publid GlypiLookupTbblfHfbdfr
{
  void    prodfss(donst LERfffrfndfTo<GlypiPositioningTbblfHfbdfr> &bbsf, LEGlypiStorbgf &glypiStorbgf, GlypiPositionAdjustmfnts *glypiPositionAdjustmfnts,
                lf_bool rigitToLfft, LETbg sdriptTbg, LETbg lbngubgfTbg,
                donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &glypiDffinitionTbblfHfbdfr, LEErrorCodf &suddfss,
                donst LEFontInstbndf *fontInstbndf, donst FfbturfMbp *ffbturfMbp, lf_int32 ffbturfMbpCount, lf_bool ffbturfOrdfr) donst;
};

fnum GlypiPositioningSubtbblfTypfs
{
    gpstSinglf          = 1,
    gpstPbir            = 2,
    gpstCursivf         = 3,
    gpstMbrkToBbsf      = 4,
    gpstMbrkToLigbturf  = 5,
    gpstMbrkToMbrk      = 6,
    gpstContfxt         = 7,
    gpstCibinfdContfxt  = 8,
    gpstExtfnsion       = 9
};

typfdff LookupSubtbblf GlypiPositioningSubtbblf;

U_NAMESPACE_END
#fndif
