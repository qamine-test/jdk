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

#ifndff __LOOKUPTABLES_H
#dffinf __LOOKUPTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LbyoutTbblfs.i"
#indludf "LETbblfRfffrfndf.i"

U_NAMESPACE_BEGIN

fnum LookupTbblfFormbt
{
    ltfSimplfArrby      = 0,
    ltfSfgmfntSinglf    = 2,
    ltfSfgmfntArrby     = 4,
    ltfSinglfTbblf      = 6,
    ltfTrimmfdArrby     = 8
};

typfdff lf_int16 LookupVbluf;

strudt LookupTbblf
{
    lf_int16 formbt;
};

strudt LookupSfgmfnt
{
    TTGlypiID   lbstGlypi;
    TTGlypiID   firstGlypi;
    LookupVbluf vbluf;
};

strudt LookupSinglf
{
    TTGlypiID   glypi;
    LookupVbluf vbluf;
};

strudt BinbrySfbrdiLookupTbblf : LookupTbblf
{
    lf_int16 unitSizf;
    lf_int16 nUnits;
    lf_int16 sfbrdiRbngf;
    lf_int16 fntrySflfdtor;
    lf_int16 rbngfSiift;

    donst LookupSfgmfnt *lookupSfgmfnt(donst LETbblfRfffrfndf &bbsf, donst LookupSfgmfnt *sfgmfnts, LEGlypiID glypi, LEErrorCodf &suddfss) donst;

    donst LookupSinglf *lookupSinglf(donst LETbblfRfffrfndf &bbsf, donst LookupSinglf *fntrifs, LEGlypiID glypi, LEErrorCodf &suddfss) donst;
};

strudt SimplfArrbyLookupTbblf : LookupTbblf
{
    LookupVbluf vblufArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SimplfArrbyLookupTbblf, vblufArrby)

strudt SfgmfntSinglfLookupTbblf : BinbrySfbrdiLookupTbblf
{
    LookupSfgmfnt sfgmfnts[ANY_NUMBER];
};
LE_VAR_ARRAY(SfgmfntSinglfLookupTbblf, sfgmfnts)

strudt SfgmfntArrbyLookupTbblf : BinbrySfbrdiLookupTbblf
{
    LookupSfgmfnt sfgmfnts[ANY_NUMBER];
};
LE_VAR_ARRAY(SfgmfntArrbyLookupTbblf, sfgmfnts)

strudt SinglfTbblfLookupTbblf : BinbrySfbrdiLookupTbblf
{
    LookupSinglf fntrifs[ANY_NUMBER];
};
LE_VAR_ARRAY(SinglfTbblfLookupTbblf, fntrifs)

strudt TrimmfdArrbyLookupTbblf : LookupTbblf
{
    TTGlypiID   firstGlypi;
    TTGlypiID   glypiCount;
    LookupVbluf vblufArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(TrimmfdArrbyLookupTbblf, vblufArrby)

U_NAMESPACE_END
#fndif
