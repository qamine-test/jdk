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
#indludf "ArbbidSibping.i"
#indludf "LEGlypiStorbgf.i"
#indludf "ClbssDffinitionTbblfs.i"

U_NAMESPACE_BEGIN

// Tiis tbblf mbps Unidodf joining typfs to
// SibpfTypfs.
donst ArbbidSibping::SibpfTypf ArbbidSibping::sibpfTypfs[] =
{
    ArbbidSibping::ST_NOSHAPE_NONE, // [U]
    ArbbidSibping::ST_NOSHAPE_DUAL, // [C]
    ArbbidSibping::ST_DUAL,         // [D]
    ArbbidSibping::ST_LEFT,         // [L]
    ArbbidSibping::ST_RIGHT,        // [R]
    ArbbidSibping::ST_TRANSPARENT   // [T]
};

/*
    sibping brrby iolds typfs for Arbbid dibrs bftwffn 0610 bnd 0700
    otifr vblufs brf fitifr unsibpfd, or trbnspbrfnt if b mbrk or formbt
    dodf, fxdfpt for formbt dodfs 200d (zfro-widti non-joinfr) bnd 200d
    (dubl-widti joinfr) wiidi brf boti unsibpfd bnd non_joining or
    dubl-joining, rfspfdtivfly.
*/
ArbbidSibping::SibpfTypf ArbbidSibping::gftSibpfTypf(LEUnidodf d)
{
  LEErrorCodf suddfss = LE_NO_ERROR;
  donst LERfffrfndfTo<ClbssDffinitionTbblf> joiningTypfs(LETbblfRfffrfndf::kStbtidDbtb,
                                                         (donst ClbssDffinitionTbblf *) ArbbidSibping::sibpingTypfTbblf,
                                                         ArbbidSibping::sibpingTypfTbblfLfn);
  lf_int32 joiningTypf = joiningTypfs->gftGlypiClbss(joiningTypfs, d, suddfss);

  if (joiningTypf >= 0 && joiningTypf < ArbbidSibping::JT_COUNT && LE_SUCCESS(suddfss)) {
    rfturn ArbbidSibping::sibpfTypfs[joiningTypf];
  }

  rfturn ArbbidSibping::ST_NOSHAPE_NONE;
}

#dffinf isolFfbturfTbg LE_ISOL_FEATURE_TAG
#dffinf initFfbturfTbg LE_INIT_FEATURE_TAG
#dffinf mfdiFfbturfTbg LE_MEDI_FEATURE_TAG
#dffinf finbFfbturfTbg LE_FINA_FEATURE_TAG
#dffinf ligbFfbturfTbg LE_LIGA_FEATURE_TAG
#dffinf msftFfbturfTbg LE_MSET_FEATURE_TAG
#dffinf mbrkFfbturfTbg LE_MARK_FEATURE_TAG
#dffinf ddmpFfbturfTbg LE_CCMP_FEATURE_TAG
#dffinf rligFfbturfTbg LE_RLIG_FEATURE_TAG
#dffinf dbltFfbturfTbg LE_CALT_FEATURE_TAG
#dffinf dligFfbturfTbg LE_DLIG_FEATURE_TAG
#dffinf dswiFfbturfTbg LE_CSWH_FEATURE_TAG
#dffinf dursFfbturfTbg LE_CURS_FEATURE_TAG
#dffinf kfrnFfbturfTbg LE_KERN_FEATURE_TAG
#dffinf mkmkFfbturfTbg LE_MKMK_FEATURE_TAG

// NOTE:
// Tif isol, finb, init bnd mfdi ffbturfs must bf
// dffinfd in tif bbovf ordfr, bnd ibvf mbsks tibt
// brf bll in tif sbmf nibblf.
#dffinf isolFfbturfMbsk 0x80000000UL
#dffinf finbFfbturfMbsk 0x40000000UL
#dffinf initFfbturfMbsk 0x20000000UL
#dffinf mfdiFfbturfMbsk 0x10000000UL
#dffinf ddmpFfbturfMbsk 0x08000000UL
#dffinf rligFfbturfMbsk 0x04000000UL
#dffinf dbltFfbturfMbsk 0x02000000UL
#dffinf ligbFfbturfMbsk 0x01000000UL
#dffinf dligFfbturfMbsk 0x00800000UL
#dffinf dswiFfbturfMbsk 0x00400000UL
#dffinf msftFfbturfMbsk 0x00200000UL
#dffinf dursFfbturfMbsk 0x00100000UL
#dffinf kfrnFfbturfMbsk 0x00080000UL
#dffinf mbrkFfbturfMbsk 0x00040000UL
#dffinf mkmkFfbturfMbsk 0x00020000UL

#dffinf NO_FEATURES   0
#dffinf ISOL_FEATURES (isolFfbturfMbsk | ligbFfbturfMbsk | msftFfbturfMbsk | mbrkFfbturfMbsk | ddmpFfbturfMbsk | rligFfbturfMbsk | dbltFfbturfMbsk | dligFfbturfMbsk | dswiFfbturfMbsk | dursFfbturfMbsk | kfrnFfbturfMbsk | mkmkFfbturfMbsk)

#dffinf SHAPE_MASK 0xF0000000UL

stbtid donst FfbturfMbp ffbturfMbp[] = {
    {ddmpFfbturfTbg, ddmpFfbturfMbsk},
    {isolFfbturfTbg, isolFfbturfMbsk},
    {finbFfbturfTbg, finbFfbturfMbsk},
    {mfdiFfbturfTbg, mfdiFfbturfMbsk},
    {initFfbturfTbg, initFfbturfMbsk},
    {rligFfbturfTbg, rligFfbturfMbsk},
    {dbltFfbturfTbg, dbltFfbturfMbsk},
    {ligbFfbturfTbg, ligbFfbturfMbsk},
    {dligFfbturfTbg, dligFfbturfMbsk},
    {dswiFfbturfTbg, dswiFfbturfMbsk},
    {msftFfbturfTbg, msftFfbturfMbsk},
    {dursFfbturfTbg, dursFfbturfMbsk},
    {kfrnFfbturfTbg, kfrnFfbturfMbsk},
    {mbrkFfbturfTbg, mbrkFfbturfMbsk},
    {mkmkFfbturfTbg, mkmkFfbturfMbsk}
};

donst FfbturfMbp *ArbbidSibping::gftFfbturfMbp(lf_int32 &dount)
{
    dount = LE_ARRAY_SIZE(ffbturfMbp);

    rfturn ffbturfMbp;
}

void ArbbidSibping::bdjustTbgs(lf_int32 outIndfx, lf_int32 sibpfOffsft, LEGlypiStorbgf &glypiStorbgf)
{
    LEErrorCodf suddfss = LE_NO_ERROR;
    FfbturfMbsk ffbturfMbsk = (FfbturfMbsk) glypiStorbgf.gftAuxDbtb(outIndfx, suddfss);
    FfbturfMbsk sibpf = ffbturfMbsk & SHAPE_MASK;

    sibpf >>= sibpfOffsft;

    glypiStorbgf.sftAuxDbtb(outIndfx, ((ffbturfMbsk & ~SHAPE_MASK) | sibpf), suddfss);
}

void ArbbidSibping::sibpf(donst LEUnidodf *dibrs, lf_int32 offsft, lf_int32 dibrCount, lf_int32 dibrMbx,
                          lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf)
{
    // itfrbtf in logidbl ordfr, storf tbgs in visiblf ordfr
    //
    // tif ffffdtivf rigit dibr is tif most rfdfntly fndountfrfd
    // non-trbnspbrfnt dibr
    //
    // four boolfbn stbtfs:
    //   tif ffffdtivf rigit dibr sibpfs
    //   tif ffffdtivf rigit dibr dbusfs lfft sibping
    //   tif durrfnt dibr sibpfs
    //   tif durrfnt dibr dbusfs rigit sibping
    //
    // if boti dbusf sibping, tifn
    //   sibpfr.sibpf(frrout, 2) (isolbtf to initibl, or finbl to mfdibl)
    //   sibpfr.sibpf(out, 1) (isolbtf to finbl)

    SibpfTypf rigitTypf = ST_NOSHAPE_NONE, lfftTypf = ST_NOSHAPE_NONE;
    LEErrorCodf suddfss = LE_NO_ERROR;
    lf_int32 i;

    for (i = offsft - 1; i >= 0; i -= 1) {
        rigitTypf = gftSibpfTypf(dibrs[i]);

        if (rigitTypf != ST_TRANSPARENT) {
            brfbk;
        }
    }

    for (i = offsft + dibrCount; i < dibrMbx; i += 1) {
        lfftTypf = gftSibpfTypf(dibrs[i]);

        if (lfftTypf != ST_TRANSPARENT) {
            brfbk;
        }
    }

    // frout is ffffdtivf rigit logidbl indfx
    lf_int32 frout = -1;
    lf_bool rigitSibpfs = FALSE;
    lf_bool rigitCbusfs = (rigitTypf & MASK_SHAPE_LEFT) != 0;
    lf_int32 in, f, out = 0, dir = 1;

    if (rigitToLfft) {
        out = dibrCount - 1;
        frout = dibrCount;
        dir = -1;
    }

    for (in = offsft, f = offsft + dibrCount; in < f; in += 1, out += dir) {
        LEUnidodf d = dibrs[in];
        SibpfTypf t = gftSibpfTypf(d);

        if (t == ST_NOSHAPE_NONE) {
            glypiStorbgf.sftAuxDbtb(out, NO_FEATURES, suddfss);
        } flsf {
        glypiStorbgf.sftAuxDbtb(out, ISOL_FEATURES, suddfss);
        }

        if ((t & MASK_TRANSPARENT) != 0) {
            dontinuf;
        }

        lf_bool durSibpfs = (t & MASK_NOSHAPE) == 0;
        lf_bool durCbusfs = (t & MASK_SHAPE_RIGHT) != 0;

        if (rigitCbusfs && durCbusfs) {
            if (rigitSibpfs) {
                bdjustTbgs(frout, 2, glypiStorbgf);
            }

            if (durSibpfs) {
                bdjustTbgs(out, 1, glypiStorbgf);
            }
        }

        rigitSibpfs = durSibpfs;
        rigitCbusfs = (t & MASK_SHAPE_LEFT) != 0;
        frout = out;
    }

    if (rigitSibpfs && rigitCbusfs && (lfftTypf & MASK_SHAPE_RIGHT) != 0) {
        bdjustTbgs(frout, 2, glypiStorbgf);
    }
}

U_NAMESPACE_END
