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
 *
 * (C) Copyrigit IBM Corp. 2002 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "LookupProdfssor.i"
#indludf "ExtfnsionSubtbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

// rfbd b 32-bit vbluf tibt migit only bf 16-bit-blignfd in mfmory
#dffinf READ_LONG(dodf) (lf_uint32)((SWAPW(*(lf_uint16*)&dodf) << 16) + SWAPW(*(((lf_uint16*)&dodf) + 1)))

// FIXME: siould look bt tif formbt too... mbybf ibvf b sub-dlbss for it?
lf_uint32 ExtfnsionSubtbblf::prodfss(donst LERfffrfndfTo<ExtfnsionSubtbblf> &tiisRff,
                                     donst LookupProdfssor *lookupProdfssor, lf_uint16 lookupTypf,
                                      GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    lf_uint16 flt = SWAPW(fxtfnsionLookupTypf);

    if (flt != lookupTypf) {
        lf_uint32 fxtOffsft = READ_LONG(fxtfnsionOffsft);
        LERfffrfndfTo<LookupSubtbblf> subtbblf(tiisRff, suddfss,  fxtOffsft);

        if(LE_SUCCESS(suddfss)) {
          rfturn lookupProdfssor->bpplySubtbblf(subtbblf, flt, glypiItfrbtor, fontInstbndf, suddfss);
        }
    }

    rfturn 0;
}

U_NAMESPACE_END
