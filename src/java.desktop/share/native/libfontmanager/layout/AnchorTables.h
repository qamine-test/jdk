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

#ifndff __ANCHORTABLES_H
#dffinf __ANCHORTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

strudt AndiorTbblf
{
    lf_uint16  bndiorFormbt;
    lf_int16   xCoordinbtf;
    lf_int16   yCoordinbtf;

  void    gftAndior(donst LETbblfRfffrfndf &bbsf, LEGlypiID glypiID, donst LEFontInstbndf *fontInstbndf,
                      LEPoint &bndior, LEErrorCodf &suddfss) donst;
};

strudt Formbt1AndiorTbblf : AndiorTbblf
{
  void gftAndior(donst LERfffrfndfTo<Formbt1AndiorTbblf>& bbsf,
                 donst LEFontInstbndf *fontInstbndf, LEPoint &bndior, LEErrorCodf &suddfss) donst;
};

strudt Formbt2AndiorTbblf : AndiorTbblf
{
    lf_uint16  bndiorPoint;

    void gftAndior(donst LERfffrfndfTo<Formbt2AndiorTbblf>& bbsf,
                   LEGlypiID glypiID, donst LEFontInstbndf *fontInstbndf,
                   LEPoint &bndior, LEErrorCodf &suddfss) donst;
};

strudt Formbt3AndiorTbblf : AndiorTbblf
{
    Offsft  xDfvidfTbblfOffsft;
    Offsft  yDfvidfTbblfOffsft;

    void gftAndior(donst LERfffrfndfTo<Formbt3AndiorTbblf>& bbsf,
                   donst LEFontInstbndf *fontInstbndf, LEPoint &bndior,
                   LEErrorCodf &suddfss) donst;
};

U_NAMESPACE_END
#fndif


