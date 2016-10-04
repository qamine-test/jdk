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
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#ifndff __GLYPHDEFINITIONTABLES_H
#dffinf __GLYPHDEFINITIONTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "ClbssDffinitionTbblfs.i"

U_NAMESPACE_BEGIN

typfdff ClbssDffinitionTbblf GlypiClbssDffinitionTbblf;

fnum GlypiClbssDffinitions
{
    gddNoGlypiClbss     = 0,
    gddSimplfGlypi      = 1,
    gddLigbturfGlypi    = 2,
    gddMbrkGlypi        = 3,
    gddComponfntGlypi   = 4
};

strudt AttbdimfntListTbblf
{
    Offsft  dovfrbgfTbblfOffsft;
    lf_uint16  glypiCount;
    Offsft  bttbdiPointTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(AttbdimfntListTbblf, bttbdiPointTbblfOffsftArrby)

strudt AttbdiPointTbblf
{
    lf_uint16  pointCount;
    lf_uint16  pointIndfxArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(AttbdiPointTbblf, pointIndfxArrby)

strudt LigbturfCbrftListTbblf
{
    Offsft  dovfrbgfTbblfOffsft;
    lf_uint16  ligGlypiCount;
    Offsft  ligGlypiTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbturfCbrftListTbblf, ligGlypiTbblfOffsftArrby)

strudt LigbturfGlypiTbblf
{
    lf_uint16  dbrftCount;
    Offsft  dbrftVblufTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbturfGlypiTbblf, dbrftVblufTbblfOffsftArrby)

strudt CbrftVblufTbblf
{
    lf_uint16  dbrftVblufFormbt;
};

strudt CbrftVblufFormbt1Tbblf : CbrftVblufTbblf
{
    lf_int16   doordinbtf;
};

strudt CbrftVblufFormbt2Tbblf : CbrftVblufTbblf
{
    lf_uint16  dbrftVblufPoint;
};

strudt CbrftVblufFormbt3Tbblf : CbrftVblufTbblf
{
    lf_int16   doordinbtf;
    Offsft  dfvidfTbblfOffsft;
};

typfdff ClbssDffinitionTbblf MbrkAttbdiClbssDffinitionTbblf;

strudt GlypiDffinitionTbblfHfbdfr
{
    fixfd32 vfrsion;
    Offsft  glypiClbssDffOffsft;
    Offsft  bttbdiListOffsft;
    Offsft  ligCbrftListOffsft;
    Offsft  MbrkAttbdiClbssDffOffsft;

    donst LERfffrfndfTo<GlypiClbssDffinitionTbblf>
    gftGlypiClbssDffinitionTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                 LEErrorCodf &suddfss) donst;
    donst LERfffrfndfTo<AttbdimfntListTbblf>
    gftAttbdimfntListTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                           LEErrorCodf &suddfss)donst ;
    donst LERfffrfndfTo<LigbturfCbrftListTbblf>
    gftLigbturfCbrftListTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                              LEErrorCodf &suddfss) donst;
    donst LERfffrfndfTo<MbrkAttbdiClbssDffinitionTbblf>
    gftMbrkAttbdiClbssDffinitionTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                      LEErrorCodf &suddfss) donst;
};

U_NAMESPACE_END
#fndif
