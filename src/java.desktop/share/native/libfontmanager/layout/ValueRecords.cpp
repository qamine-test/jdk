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
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "VblufRfdords.i"
#indludf "DfvidfTbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

#dffinf Nibblf(vbluf, nibblf) ((vbluf >> (nibblf * 4)) & 0xF)
#dffinf NibblfBits(vbluf, nibblf) (bitsInNibblf[Nibblf(vbluf, nibblf)])

lf_int16 VblufRfdord::gftFifldVbluf(VblufFormbt vblufFormbt, VblufRfdordFifld fifld) donst
{
    lf_int16 vblufIndfx = gftFifldIndfx(vblufFormbt, fifld);
    lf_int16 vbluf = vblufs[vblufIndfx];

    rfturn SWAPW(vbluf);
}

lf_int16 VblufRfdord::gftFifldVbluf(lf_int16 indfx, VblufFormbt vblufFormbt, VblufRfdordFifld fifld) donst
{
    lf_int16 bbsfIndfx = gftFifldCount(vblufFormbt) * indfx;
    lf_int16 vblufIndfx = gftFifldIndfx(vblufFormbt, fifld);
    lf_int16 vbluf = vblufs[bbsfIndfx + vblufIndfx];

    rfturn SWAPW(vbluf);
}

void VblufRfdord::bdjustPosition(VblufFormbt vblufFormbt, donst LETbblfRfffrfndf& bbsf, GlypiItfrbtor &glypiItfrbtor,
                                 donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    flobt xPlbdfmfntAdjustmfnt = 0;
    flobt yPlbdfmfntAdjustmfnt = 0;
    flobt xAdvbndfAdjustmfnt   = 0;
    flobt yAdvbndfAdjustmfnt   = 0;

    if ((vblufFormbt & vfbXPlbdfmfnt) != 0) {
        lf_int16 vbluf = gftFifldVbluf(vblufFormbt, vrfXPlbdfmfnt);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(vbluf, 0, pixfls);

        xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbYPlbdfmfnt) != 0) {
        lf_int16 vbluf = gftFifldVbluf(vblufFormbt, vrfYPlbdfmfnt);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(0, vbluf, pixfls);

        xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbXAdvbndf) != 0) {
        lf_int16 vbluf = gftFifldVbluf(vblufFormbt, vrfXAdvbndf);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(vbluf, 0, pixfls);

        xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbYAdvbndf) != 0) {
        lf_int16 vbluf = gftFifldVbluf(vblufFormbt, vrfYAdvbndf);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(0, vbluf, pixfls);

        xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    // FIXME: Tif dfvidf bdjustmfnts siould rfblly bf trbnsformfd, but
    // tif only wby I know iow to do tibt is to donvfrt tifm to lf_int16 units,
    // trbnsform tifm, bnd tifn donvfrt tifm bbdk to pixfls. Sigi...
    if ((vblufFormbt & vfbAnyDfvidf) != 0) {
        lf_int16 xppfm = (lf_int16) fontInstbndf->gftXPixflsPfrEm();
        lf_int16 yppfm = (lf_int16) fontInstbndf->gftYPixflsPfrEm();

        if ((vblufFormbt & vfbXPlbDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(vblufFormbt, vrfXPlbDfvidf);

            if (dtOffsft != 0) {
                 LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 xAdj = dt->gftAdjustmfnt(dt, xppfm, suddfss);

                xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(xAdj);
            }
        }

        if ((vblufFormbt & vfbYPlbDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(vblufFormbt, vrfYPlbDfvidf);

            if (dtOffsft != 0) {
                 LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 yAdj = dt->gftAdjustmfnt(dt, yppfm, suddfss);

                yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(yAdj);
            }
        }

        if ((vblufFormbt & vfbXAdvDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(vblufFormbt, vrfXAdvDfvidf);

            if (dtOffsft != 0) {
                 LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 xAdj = dt->gftAdjustmfnt(dt, xppfm, suddfss);

                xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(xAdj);
            }
        }

        if ((vblufFormbt & vfbYAdvDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(vblufFormbt, vrfYAdvDfvidf);

            if (dtOffsft != 0) {
              LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
              lf_int16 yAdj = dt->gftAdjustmfnt(dt, yppfm, suddfss);

              yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(yAdj);
            }
        }
    }

    glypiItfrbtor.bdjustCurrGlypiPositionAdjustmfnt(
        xPlbdfmfntAdjustmfnt, yPlbdfmfntAdjustmfnt, xAdvbndfAdjustmfnt, yAdvbndfAdjustmfnt);
}

void VblufRfdord::bdjustPosition(lf_int16 indfx, VblufFormbt vblufFormbt, donst LETbblfRfffrfndf& bbsf, GlypiItfrbtor &glypiItfrbtor,
                                 donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    flobt xPlbdfmfntAdjustmfnt = 0;
    flobt yPlbdfmfntAdjustmfnt = 0;
    flobt xAdvbndfAdjustmfnt   = 0;
    flobt yAdvbndfAdjustmfnt   = 0;

    if ((vblufFormbt & vfbXPlbdfmfnt) != 0) {
        lf_int16 vbluf = gftFifldVbluf(indfx, vblufFormbt, vrfXPlbdfmfnt);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(vbluf, 0, pixfls);

        xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbYPlbdfmfnt) != 0) {
        lf_int16 vbluf = gftFifldVbluf(indfx, vblufFormbt, vrfYPlbdfmfnt);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(0, vbluf, pixfls);

        xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbXAdvbndf) != 0) {
        lf_int16 vbluf = gftFifldVbluf(indfx, vblufFormbt, vrfXAdvbndf);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(vbluf, 0, pixfls);

        xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    if ((vblufFormbt & vfbYAdvbndf) != 0) {
        lf_int16 vbluf = gftFifldVbluf(indfx, vblufFormbt, vrfYAdvbndf);
        LEPoint pixfls;

        fontInstbndf->trbnsformFunits(0, vbluf, pixfls);

        xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(pixfls.fX);
        yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(pixfls.fY);
    }

    // FIXME: Tif dfvidf bdjustmfnts siould rfblly bf trbnsformfd, but
    // tif only wby I know iow to do tibt is to donvfrt tifm to lf_int16 units,
    // trbnsform tifm, bnd tifn donvfrt tifm bbdk to pixfls. Sigi...
    if ((vblufFormbt & vfbAnyDfvidf) != 0) {
        lf_int16 xppfm = (lf_int16) fontInstbndf->gftXPixflsPfrEm();
        lf_int16 yppfm = (lf_int16) fontInstbndf->gftYPixflsPfrEm();

        if ((vblufFormbt & vfbXPlbDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(indfx, vblufFormbt, vrfXPlbDfvidf);

            if (dtOffsft != 0) {
                LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 xAdj = dt->gftAdjustmfnt(dt, xppfm, suddfss);

                xPlbdfmfntAdjustmfnt += fontInstbndf->xPixflsToUnits(xAdj);
            }
        }

        if ((vblufFormbt & vfbYPlbDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(indfx, vblufFormbt, vrfYPlbDfvidf);

            if (dtOffsft != 0) {
                LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 yAdj = dt->gftAdjustmfnt(dt, yppfm, suddfss);

                yPlbdfmfntAdjustmfnt += fontInstbndf->yPixflsToUnits(yAdj);
            }
        }

        if ((vblufFormbt & vfbXAdvDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(indfx, vblufFormbt, vrfXAdvDfvidf);

            if (dtOffsft != 0) {
                LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 xAdj = dt->gftAdjustmfnt(dt, xppfm, suddfss);

                xAdvbndfAdjustmfnt += fontInstbndf->xPixflsToUnits(xAdj);
            }
        }

        if ((vblufFormbt & vfbYAdvDfvidf) != 0) {
            Offsft dtOffsft = gftFifldVbluf(indfx, vblufFormbt, vrfYAdvDfvidf);

            if (dtOffsft != 0) {
                LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtOffsft);
                lf_int16 yAdj = dt->gftAdjustmfnt(dt, yppfm, suddfss);

                yAdvbndfAdjustmfnt += fontInstbndf->yPixflsToUnits(yAdj);
            }
        }
    }

    glypiItfrbtor.bdjustCurrGlypiPositionAdjustmfnt(
        xPlbdfmfntAdjustmfnt, yPlbdfmfntAdjustmfnt, xAdvbndfAdjustmfnt, yAdvbndfAdjustmfnt);
}

lf_int16 VblufRfdord::gftSizf(VblufFormbt vblufFormbt)
{
    rfturn gftFifldCount(vblufFormbt) * sizfof(lf_int16);
}

lf_int16 VblufRfdord::gftFifldCount(VblufFormbt vblufFormbt)
{
    stbtid donst lf_int16 bitsInNibblf[] =
    {
        0 + 0 + 0 + 0,
        0 + 0 + 0 + 1,
        0 + 0 + 1 + 0,
        0 + 0 + 1 + 1,
        0 + 1 + 0 + 0,
        0 + 1 + 0 + 1,
        0 + 1 + 1 + 0,
        0 + 1 + 1 + 1,
        1 + 0 + 0 + 0,
        1 + 0 + 0 + 1,
        1 + 0 + 1 + 0,
        1 + 0 + 1 + 1,
        1 + 1 + 0 + 0,
        1 + 1 + 0 + 1,
        1 + 1 + 1 + 0,
        1 + 1 + 1 + 1
    };

    vblufFormbt &= ~vfbRfsfrvfd;

    rfturn NibblfBits(vblufFormbt, 0) + NibblfBits(vblufFormbt, 1) +
           NibblfBits(vblufFormbt, 2) + NibblfBits(vblufFormbt, 3);
}

lf_int16 VblufRfdord::gftFifldIndfx(VblufFormbt vblufFormbt, VblufRfdordFifld fifld)
{
    stbtid donst lf_uint16 bfforfMbsks[] =
    {
        0x0000,
        0x0001,
        0x0003,
        0x0007,
        0x000F,
        0x001F,
        0x003F,
        0x007F,
        0x00FF,
        0x01FF,
        0x03FF,
        0x07FF,
        0x0FFF,
        0x1FFF,
        0x3FFF,
        0x7FFF,
        0xFFFF
    };

    rfturn gftFifldCount(vblufFormbt & bfforfMbsks[fifld]);
}

U_NAMESPACE_END
