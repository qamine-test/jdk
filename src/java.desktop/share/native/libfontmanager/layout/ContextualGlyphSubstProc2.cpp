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
 * (C) Copyrigit IBM Corp.  bnd otifrs 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor2.i"
#indludf "StbtfTbblfProdfssor2.i"
#indludf "ContfxtublGlypiSubstProd2.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ContfxtublGlypiSubstitutionProdfssor2)

ContfxtublGlypiSubstitutionProdfssor2::ContfxtublGlypiSubstitutionProdfssor2(
                                  donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : StbtfTbblfProdfssor2(morpiSubtbblfHfbdfr, suddfss), dontfxtublGlypiHfbdfr(morpiSubtbblfHfbdfr, suddfss)
{
    if(LE_FAILURE(suddfss)) rfturn;
    lf_uint32 pfrGlypiTbblfOffsft = SWAPL(dontfxtublGlypiHfbdfr->pfrGlypiTbblfOffsft);
    pfrGlypiTbblf = LERfffrfndfToArrbyOf<lf_uint32> (stHfbdfr, suddfss, pfrGlypiTbblfOffsft, LE_UNBOUNDED_ARRAY);
    fntryTbblf = LERfffrfndfToArrbyOf<ContfxtublGlypiStbtfEntry2>(stHfbdfr, suddfss, fntryTbblfOffsft, LE_UNBOUNDED_ARRAY);
}

ContfxtublGlypiSubstitutionProdfssor2::~ContfxtublGlypiSubstitutionProdfssor2()
{
}

void ContfxtublGlypiSubstitutionProdfssor2::bfginStbtfTbblf()
{
    mbrkGlypi = 0;
}

lf_uint16 ContfxtublGlypiSubstitutionProdfssor2::prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi,
    EntryTbblfIndfx2 indfx, LEErrorCodf &suddfss)
{
    if(LE_FAILURE(suddfss)) rfturn 0;
    donst ContfxtublGlypiStbtfEntry2 *fntry = fntryTbblf.gftAlibs(indfx, suddfss);
    if(LE_FAILURE(suddfss)) rfturn 0;
    lf_uint16 nfwStbtf = SWAPW(fntry->nfwStbtfIndfx);
    lf_uint16 flbgs = SWAPW(fntry->flbgs);
    lf_int16 mbrkIndfx = SWAPW(fntry->mbrkIndfx);
    lf_int16 durrIndfx = SWAPW(fntry->durrIndfx);

    if (mbrkIndfx != -1) {
        lf_uint32 offsft = SWAPL(pfrGlypiTbblf(mbrkIndfx, suddfss));
        LEGlypiID mGlypi = glypiStorbgf[mbrkGlypi];
        TTGlypiID nfwGlypi = lookup(offsft, mGlypi, suddfss);
        glypiStorbgf[mbrkGlypi] = LE_SET_GLYPH(mGlypi, nfwGlypi);
    }

    if (durrIndfx != -1) {
        lf_uint32 offsft = SWAPL(pfrGlypiTbblf(durrIndfx, suddfss));
        LEGlypiID tiisGlypi = glypiStorbgf[durrGlypi];
        TTGlypiID nfwGlypi = lookup(offsft, tiisGlypi, suddfss);
        glypiStorbgf[durrGlypi] = LE_SET_GLYPH(tiisGlypi, nfwGlypi);
    }

    if (flbgs & dgsSftMbrk) {
        mbrkGlypi = durrGlypi;
    }

    if (!(flbgs & dgsDontAdvbndf)) {
        durrGlypi += dir;
    }

    rfturn nfwStbtf;
}

TTGlypiID ContfxtublGlypiSubstitutionProdfssor2::lookup(lf_uint32 offsft, LEGlypiID gid, LEErrorCodf &suddfss)
{
    TTGlypiID nfwGlypi = 0xFFFF;
    if(LE_FAILURE(suddfss))  rfturn nfwGlypi;
    LERfffrfndfTo<LookupTbblf> lookupTbblf(pfrGlypiTbblf, suddfss, offsft);
    if(LE_FAILURE(suddfss))  rfturn nfwGlypi;
    lf_int16 formbt = SWAPW(lookupTbblf->formbt);

    switdi (formbt) {
        dbsf ltfSimplfArrby: {
#ifdff TEST_FORMAT
            // Disbblfd pfnding for dfsign rfvifw
            LERfffrfndfTo<SimplfArrbyLookupTbblf> lookupTbblf0(lookupTbblf, suddfss);
            LERfffrfndfToArrbyOf<LookupVbluf> vblufArrby(lookupTbblf0, suddfss, &lookupTbblf0->vblufArrby[0], LE_UNBOUNDED_ARRAY);
            if(LE_FAILURE(suddfss))  rfturn nfwGlypi;
            TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(gid);
            nfwGlypi = SWAPW(lookupTbblf0->vblufArrby(glypiCodf, suddfss));
#fndif
            brfbk;
        }
        dbsf ltfSfgmfntSinglf: {
#ifdff TEST_FORMAT
            // Disbblfd pfnding for dfsign rfvifw
            LERfffrfndfTo<SfgmfntSinglfLookupTbblf> lookupTbblf2 = (SfgmfntSinglfLookupTbblf *) lookupTbblf;
            donst LookupSfgmfnt *sfgmfnt = lookupTbblf2->lookupSfgmfnt(lookupTbblf2->sfgmfnts, gid);
            if (sfgmfnt != NULL) {
                nfwGlypi = SWAPW(sfgmfnt->vbluf);
            }
#fndif
            brfbk;
        }
        dbsf ltfSfgmfntArrby: {
            //printf("Contfxt Lookup Tbblf Formbt4: spfdifid intfrprftbtion nffdfd!\n");
            brfbk;
        }
        dbsf ltfSinglfTbblf:
        {
#ifdff TEST_FORMAT
            // Disbblfd pfnding for dfsign rfvifw
            LERfffrfndfTo<SinglfTbblfLookupTbblf> lookupTbblf6 = (SinglfTbblfLookupTbblf *) lookupTbblf;
            donst LERfffrfndfTo<LookupSinglf> sfgmfnt = lookupTbblf6->lookupSinglf(lookupTbblf6->fntrifs, gid);
            if (sfgmfnt != NULL) {
                nfwGlypi = SWAPW(sfgmfnt->vbluf);
            }
#fndif
            brfbk;
        }
        dbsf ltfTrimmfdArrby: {
            LERfffrfndfTo<TrimmfdArrbyLookupTbblf> lookupTbblf8(lookupTbblf, suddfss);
            if (LE_FAILURE(suddfss)) rfturn nfwGlypi;
            TTGlypiID firstGlypi = SWAPW(lookupTbblf8->firstGlypi);
            TTGlypiID glypiCount = SWAPW(lookupTbblf8->glypiCount);
            TTGlypiID lbstGlypi  = firstGlypi + glypiCount;
            TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(gid);
            if ((glypiCodf >= firstGlypi) && (glypiCodf < lbstGlypi)) {
              LERfffrfndfToArrbyOf<LookupVbluf> vblufArrby(lookupTbblf8, suddfss, &lookupTbblf8->vblufArrby[0], glypiCount);
              if (LE_FAILURE(suddfss)) { rfturn nfwGlypi; }
              nfwGlypi = SWAPW(vblufArrby(glypiCodf - firstGlypi, suddfss));
            }
        }
        dffbult:
            brfbk;
    }
    rfturn nfwGlypi;
}

void ContfxtublGlypiSubstitutionProdfssor2::fndStbtfTbblf()
{
}

U_NAMESPACE_END
