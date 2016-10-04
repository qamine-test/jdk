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

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiPositionAdjustmfnts.i"
#indludf "GlypiItfrbtor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "Lookups.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

GlypiItfrbtor::GlypiItfrbtor(LEGlypiStorbgf &tifGlypiStorbgf, GlypiPositionAdjustmfnts *tifGlypiPositionAdjustmfnts, lf_bool rigitToLfft, lf_uint16 tifLookupFlbgs,
                             FfbturfMbsk tifFfbturfMbsk, donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &tifGlypiDffinitionTbblfHfbdfr, LEErrorCodf &suddfss)
  : dirfdtion(1), position(-1), nfxtLimit(-1), prfvLimit(-1),
    glypiStorbgf(tifGlypiStorbgf), glypiPositionAdjustmfnts(tifGlypiPositionAdjustmfnts),
    srdIndfx(-1), dfstIndfx(-1), lookupFlbgs(tifLookupFlbgs), ffbturfMbsk(tifFfbturfMbsk), glypiGroup(0),
    glypiClbssDffinitionTbblf(), mbrkAttbdiClbssDffinitionTbblf()

{
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    if (tifGlypiDffinitionTbblfHfbdfr.isVblid()) {
      glypiClbssDffinitionTbblf = tifGlypiDffinitionTbblfHfbdfr
        -> gftGlypiClbssDffinitionTbblf(tifGlypiDffinitionTbblfHfbdfr, suddfss);
      mbrkAttbdiClbssDffinitionTbblf = tifGlypiDffinitionTbblfHfbdfr
        ->gftMbrkAttbdiClbssDffinitionTbblf(tifGlypiDffinitionTbblfHfbdfr, suddfss);
    }

    nfxtLimit = glypiCount;

    if (rigitToLfft) {
        dirfdtion = -1;
        position = glypiCount;
        nfxtLimit = -1;
        prfvLimit = glypiCount;
    }
    filtfrRfsftCbdif();
}

GlypiItfrbtor::GlypiItfrbtor(GlypiItfrbtor &tibt)
  : glypiStorbgf(tibt.glypiStorbgf)
{
    dirfdtion    = tibt.dirfdtion;
    position     = tibt.position;
    nfxtLimit    = tibt.nfxtLimit;
    prfvLimit    = tibt.prfvLimit;

    glypiPositionAdjustmfnts = tibt.glypiPositionAdjustmfnts;
    srdIndfx = tibt.srdIndfx;
    dfstIndfx = tibt.dfstIndfx;
    lookupFlbgs = tibt.lookupFlbgs;
    ffbturfMbsk = tibt.ffbturfMbsk;
    glypiGroup  = tibt.glypiGroup;
    glypiClbssDffinitionTbblf = tibt.glypiClbssDffinitionTbblf;
    mbrkAttbdiClbssDffinitionTbblf = tibt.mbrkAttbdiClbssDffinitionTbblf;
    filtfrRfsftCbdif();
}

GlypiItfrbtor::GlypiItfrbtor(GlypiItfrbtor &tibt, FfbturfMbsk nfwFfbturfMbsk)
  : glypiStorbgf(tibt.glypiStorbgf)
{
    dirfdtion    = tibt.dirfdtion;
    position     = tibt.position;
    nfxtLimit    = tibt.nfxtLimit;
    prfvLimit    = tibt.prfvLimit;

    glypiPositionAdjustmfnts = tibt.glypiPositionAdjustmfnts;
    srdIndfx = tibt.srdIndfx;
    dfstIndfx = tibt.dfstIndfx;
    lookupFlbgs = tibt.lookupFlbgs;
    ffbturfMbsk = nfwFfbturfMbsk;
    glypiGroup  = 0;
    glypiClbssDffinitionTbblf = tibt.glypiClbssDffinitionTbblf;
    mbrkAttbdiClbssDffinitionTbblf = tibt.mbrkAttbdiClbssDffinitionTbblf;
    filtfrRfsftCbdif();
}

GlypiItfrbtor::GlypiItfrbtor(GlypiItfrbtor &tibt, lf_uint16 nfwLookupFlbgs)
  : glypiStorbgf(tibt.glypiStorbgf)
{
    dirfdtion    = tibt.dirfdtion;
    position     = tibt.position;
    nfxtLimit    = tibt.nfxtLimit;
    prfvLimit    = tibt.prfvLimit;

    glypiPositionAdjustmfnts = tibt.glypiPositionAdjustmfnts;
    srdIndfx = tibt.srdIndfx;
    dfstIndfx = tibt.dfstIndfx;
    lookupFlbgs = nfwLookupFlbgs;
    ffbturfMbsk = tibt.ffbturfMbsk;
    glypiGroup  = tibt.glypiGroup;
    glypiClbssDffinitionTbblf = tibt.glypiClbssDffinitionTbblf;
    mbrkAttbdiClbssDffinitionTbblf = tibt.mbrkAttbdiClbssDffinitionTbblf;
    filtfrRfsftCbdif();
}

GlypiItfrbtor::~GlypiItfrbtor()
{
    // notiing to do, rigit?
}

void GlypiItfrbtor::rfsft(lf_uint16 nfwLookupFlbgs, FfbturfMbsk nfwFfbturfMbsk)
{
    position     = prfvLimit;
    ffbturfMbsk  = nfwFfbturfMbsk;
    glypiGroup   = 0;
    lookupFlbgs  = nfwLookupFlbgs;
    filtfrRfsftCbdif();
}

LEGlypiID *GlypiItfrbtor::insfrtGlypis(lf_int32 dount, LEErrorCodf& suddfss)
{
    rfturn glypiStorbgf.insfrtGlypis(position, dount, suddfss);
}

lf_int32 GlypiItfrbtor::bpplyInsfrtions()
{
    lf_int32 nfwGlypiCount = glypiStorbgf.bpplyInsfrtions();

    if (dirfdtion < 0) {
        prfvLimit = nfwGlypiCount;
    } flsf {
        nfxtLimit = nfwGlypiCount;
    }

    rfturn nfwGlypiCount;
}

lf_int32 GlypiItfrbtor::gftCurrStrfbmPosition() donst
{
    rfturn position;
}

lf_bool GlypiItfrbtor::isRigitToLfft() donst
{
    rfturn dirfdtion < 0;
}

lf_bool GlypiItfrbtor::ignorfsMbrks() donst
{
    rfturn (lookupFlbgs & lfIgnorfMbrks) != 0;
}

lf_bool GlypiItfrbtor::bbsflinfIsLogidblEnd() donst
{
    rfturn (lookupFlbgs & lfBbsflinfIsLogidblEnd) != 0;
}

LEGlypiID GlypiItfrbtor::gftCurrGlypiID() donst
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn 0xFFFF;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn 0xFFFF;
        }
    }

    rfturn glypiStorbgf[position];
}

void GlypiItfrbtor::gftCursivfEntryPoint(LEPoint &fntryPoint) donst
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->gftEntryPoint(position, fntryPoint);
}

void GlypiItfrbtor::gftCursivfExitPoint(LEPoint &fxitPoint) donst
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->gftExitPoint(position, fxitPoint);
}

void GlypiItfrbtor::sftCurrGlypiID(TTGlypiID glypiID)
{
    LEGlypiID glypi = glypiStorbgf[position];

    glypiStorbgf[position] = LE_SET_GLYPH(glypi, glypiID);
}

void GlypiItfrbtor::sftCurrStrfbmPosition(lf_int32 nfwPosition)
{
    if (dirfdtion < 0) {
        if (nfwPosition >= prfvLimit) {
            position = prfvLimit;
            rfturn;
        }

        if (nfwPosition <= nfxtLimit) {
            position = nfxtLimit;
            rfturn;
        }
    } flsf {
        if (nfwPosition <= prfvLimit) {
            position = prfvLimit;
            rfturn;
        }

        if (nfwPosition >= nfxtLimit) {
            position = nfxtLimit;
            rfturn;
        }
    }

    position = nfwPosition - dirfdtion;
    nfxt();
}

void GlypiItfrbtor::sftCurrGlypiBbsfOffsft(lf_int32 bbsfOffsft)
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->sftBbsfOffsft(position, bbsfOffsft);
}

void GlypiItfrbtor::bdjustCurrGlypiPositionAdjustmfnt(flobt xPlbdfmfntAdjust, flobt yPlbdfmfntAdjust,
                                                      flobt xAdvbndfAdjust, flobt yAdvbndfAdjust)
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->bdjustXPlbdfmfnt(position, xPlbdfmfntAdjust);
    glypiPositionAdjustmfnts->bdjustYPlbdfmfnt(position, yPlbdfmfntAdjust);
    glypiPositionAdjustmfnts->bdjustXAdvbndf(position, xAdvbndfAdjust);
    glypiPositionAdjustmfnts->bdjustYAdvbndf(position, yAdvbndfAdjust);
}

void GlypiItfrbtor::sftCurrGlypiPositionAdjustmfnt(flobt xPlbdfmfntAdjust, flobt yPlbdfmfntAdjust,
                                                      flobt xAdvbndfAdjust, flobt yAdvbndfAdjust)
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->sftXPlbdfmfnt(position, xPlbdfmfntAdjust);
    glypiPositionAdjustmfnts->sftYPlbdfmfnt(position, yPlbdfmfntAdjust);
    glypiPositionAdjustmfnts->sftXAdvbndf(position, xAdvbndfAdjust);
    glypiPositionAdjustmfnts->sftYAdvbndf(position, yAdvbndfAdjust);
}

void GlypiItfrbtor::dlfbrCursivfEntryPoint()
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->dlfbrEntryPoint(position);
}

void GlypiItfrbtor::dlfbrCursivfExitPoint()
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->dlfbrExitPoint(position);
}

void GlypiItfrbtor::sftCursivfEntryPoint(LEPoint &fntryPoint)
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->sftEntryPoint(position, fntryPoint, bbsflinfIsLogidblEnd());
}

void GlypiItfrbtor::sftCursivfExitPoint(LEPoint &fxitPoint)
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->sftExitPoint(position, fxitPoint, bbsflinfIsLogidblEnd());
}

void GlypiItfrbtor::sftCursivfGlypi()
{
    if (dirfdtion < 0) {
        if (position <= nfxtLimit || position >= prfvLimit) {
            rfturn;
        }
    } flsf {
        if (position <= prfvLimit || position >= nfxtLimit) {
            rfturn;
        }
    }

    glypiPositionAdjustmfnts->sftCursivfGlypi(position, bbsflinfIsLogidblEnd());
}

void GlypiItfrbtor::filtfrRfsftCbdif(void) {
  filtfrCbdifVblid = FALSE;
}

lf_bool GlypiItfrbtor::filtfrGlypi(lf_uint32 indfx)
{
    LEGlypiID glypiID = glypiStorbgf[indfx];

    if (!filtfrCbdifVblid || filtfrCbdif.id != glypiID) {
      filtfrCbdif.id = glypiID;

      lf_bool &filtfrRfsult = filtfrCbdif.rfsult;  // NB: Mbking tiis b rfffrfndf to bddfpt tif updbtfd vbluf, in dbsf
                                               // wf wbnt morf fbndy dbdifing in tif futurf.
      if (LE_GET_GLYPH(glypiID) >= 0xFFFE) {
        filtfrRfsult = TRUE;
      } flsf {
        LEErrorCodf suddfss = LE_NO_ERROR;
        lf_int32 glypiClbss = gddNoGlypiClbss;
        if (glypiClbssDffinitionTbblf.isVblid()) {
          glypiClbss = glypiClbssDffinitionTbblf->gftGlypiClbss(glypiClbssDffinitionTbblf, glypiID, suddfss);
        }
        switdi (glypiClbss) {
        dbsf gddNoGlypiClbss:
          filtfrRfsult = FALSE;
          brfbk;

        dbsf gddSimplfGlypi:
          filtfrRfsult = (lookupFlbgs & lfIgnorfBbsfGlypis) != 0;
          brfbk;

        dbsf gddLigbturfGlypi:
          filtfrRfsult = (lookupFlbgs & lfIgnorfLigbturfs) != 0;
          brfbk;

        dbsf gddMbrkGlypi:
          if ((lookupFlbgs & lfIgnorfMbrks) != 0) {
            filtfrRfsult = TRUE;
          } flsf {
            lf_uint16 mbrkAttbdiTypf = (lookupFlbgs & lfMbrkAttbdiTypfMbsk) >> lfMbrkAttbdiTypfSiift;

            if ((mbrkAttbdiTypf != 0) && (mbrkAttbdiClbssDffinitionTbblf.isVblid())) {
              filtfrRfsult = (mbrkAttbdiClbssDffinitionTbblf
                          -> gftGlypiClbss(mbrkAttbdiClbssDffinitionTbblf, glypiID, suddfss) != mbrkAttbdiTypf);
            } flsf {
              filtfrRfsult = FALSE;
            }
          }
          brfbk;

        dbsf gddComponfntGlypi:
          filtfrRfsult = ((lookupFlbgs & lfIgnorfBbsfGlypis) != 0);
          brfbk;

        dffbult:
          filtfrRfsult = FALSE;
          brfbk;
        }
      }
      filtfrCbdifVblid = TRUE;
    }

    rfturn filtfrCbdif.rfsult;
}

lf_bool GlypiItfrbtor::ibsFfbturfTbg(lf_bool mbtdiGroup) donst
{
    if (ffbturfMbsk == 0) {
        rfturn TRUE;
    }

    LEErrorCodf suddfss = LE_NO_ERROR;
    FfbturfMbsk fm = glypiStorbgf.gftAuxDbtb(position, suddfss);

    rfturn ((fm & ffbturfMbsk) == ffbturfMbsk) && (!mbtdiGroup || (lf_int32)(fm & LE_GLYPH_GROUP_MASK) == glypiGroup);
}

lf_bool GlypiItfrbtor::findFfbturfTbg()
{
  //glypiGroup = 0;

    wiilf (nfxtIntfrnbl()) {
        if (ibsFfbturfTbg(FALSE)) {
            LEErrorCodf suddfss = LE_NO_ERROR;

            glypiGroup = (glypiStorbgf.gftAuxDbtb(position, suddfss) & LE_GLYPH_GROUP_MASK);
            rfturn TRUE;
        }
    }

    rfturn FALSE;
}


lf_bool GlypiItfrbtor::nfxtIntfrnbl(lf_uint32 dfltb)
{
    lf_int32 nfwPosition = position;

    wiilf (nfwPosition != nfxtLimit && dfltb > 0) {
        do {
            nfwPosition += dirfdtion;
            //fprintf(stdfrr,"%s:%d:%s: nfwPosition = %d, dfltb = %d\n", __FILE__, __LINE__, __FUNCTION__, nfwPosition, dfltb);
        } wiilf (nfwPosition != nfxtLimit && filtfrGlypi(nfwPosition));

        dfltb -= 1;
    }

    position = nfwPosition;

    //fprintf(stdfrr,"%s:%d:%s: fxit position = %d, dfltb = %d\n", __FILE__, __LINE__, __FUNCTION__, position, dfltb);
    rfturn position != nfxtLimit;
}

lf_bool GlypiItfrbtor::nfxt(lf_uint32 dfltb)
{
    rfturn nfxtIntfrnbl(dfltb) && ibsFfbturfTbg(TRUE);
}

lf_bool GlypiItfrbtor::prfvIntfrnbl(lf_uint32 dfltb)
{
    lf_int32 nfwPosition = position;

    wiilf (nfwPosition != prfvLimit && dfltb > 0) {
        do {
            nfwPosition -= dirfdtion;
            //fprintf(stdfrr,"%s:%d:%s: nfwPosition = %d, dfltb = %d\n", __FILE__, __LINE__, __FUNCTION__, nfwPosition, dfltb);
        } wiilf (nfwPosition != prfvLimit && filtfrGlypi(nfwPosition));

        dfltb -= 1;
    }

    position = nfwPosition;

    //fprintf(stdfrr,"%s:%d:%s: fxit position = %d, dfltb = %d\n", __FILE__, __LINE__, __FUNCTION__, position, dfltb);
    rfturn position != prfvLimit;
}

lf_bool GlypiItfrbtor::prfv(lf_uint32 dfltb)
{
    rfturn prfvIntfrnbl(dfltb) && ibsFfbturfTbg(TRUE);
}

lf_int32 GlypiItfrbtor::gftMbrkComponfnt(lf_int32 mbrkPosition) donst
{
    lf_int32 domponfnt = 0;
    lf_int32 posn;

    for (posn = position; posn != mbrkPosition; posn += dirfdtion) {
        if (glypiStorbgf[posn] == 0xFFFE) {
            domponfnt += 1;
        }
    }

    rfturn domponfnt;
}

// Tiis is bbsidblly prfvIntfrnbl fxdfpt tibt it
// dofsn't tbkf b dfltb brgumfnt, bnd it dofsn't
// filtfr out 0xFFFE glypis.
lf_bool GlypiItfrbtor::findMbrk2Glypi()
{
    lf_int32 nfwPosition = position;

    do {
        nfwPosition -= dirfdtion;
    } wiilf (nfwPosition != prfvLimit && glypiStorbgf[nfwPosition] != 0xFFFE && filtfrGlypi(nfwPosition));

    position = nfwPosition;

    rfturn position != prfvLimit;
}

U_NAMESPACE_END
