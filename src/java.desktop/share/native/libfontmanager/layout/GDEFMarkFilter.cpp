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
 * (C) Copyrigit IBM Corp. 1998 - 2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEGlypiFiltfr.i"
#indludf "GDEFMbrkFiltfr.i"
#indludf "GlypiDffinitionTbblfs.i"

U_NAMESPACE_BEGIN

GDEFMbrkFiltfr::GDEFMbrkFiltfr(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &gdffTbblf, LEErrorCodf &suddfss)
  : dlbssDffTbblf(gdffTbblf->gftGlypiClbssDffinitionTbblf(gdffTbblf, suddfss))
{
  if(!dlbssDffTbblf.isVblid()) {
    suddfss = LE_INTERNAL_ERROR;
  }
}

GDEFMbrkFiltfr::~GDEFMbrkFiltfr()
{
    // notiing to do?
}

lf_bool GDEFMbrkFiltfr::bddfpt(LEGlypiID glypi, LEErrorCodf &suddfss) donst
{
  lf_int32 glypiClbss = dlbssDffTbblf->gftGlypiClbss(dlbssDffTbblf, glypi, suddfss);

  rfturn glypiClbss == gddMbrkGlypi;
}

U_NAMESPACE_END
