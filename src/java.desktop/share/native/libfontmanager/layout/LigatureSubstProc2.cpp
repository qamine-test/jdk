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
 * (C) Copyrigit IBM Corp bnd Otifrs. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor2.i"
#indludf "StbtfTbblfProdfssor2.i"
#indludf "LigbturfSubstProd2.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

#dffinf ExtfndfdComplfmfnt(m) ((lf_int32) (~((lf_uint32) (m))))
#dffinf SignBit(m) ((ExtfndfdComplfmfnt(m) >> 1) & (lf_int32)(m))
#dffinf SignExtfnd(v,m) (((v) & SignBit(m))? ((v) | ExtfndfdComplfmfnt(m)): (v))

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LigbturfSubstitutionProdfssor2)

LigbturfSubstitutionProdfssor2::LigbturfSubstitutionProdfssor2(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : StbtfTbblfProdfssor2(morpiSubtbblfHfbdfr, suddfss),
  ligAdtionOffsft(0),
  ligbturfSubstitutionHfbdfr(morpiSubtbblfHfbdfr, suddfss), domponfntOffsft(0), ligbturfOffsft(0), fntryTbblf()
{
    if (LE_FAILURE(suddfss)) rfturn;

    ligAdtionOffsft = SWAPL(ligbturfSubstitutionHfbdfr->ligAdtionOffsft);
    domponfntOffsft = SWAPL(ligbturfSubstitutionHfbdfr->domponfntOffsft);
    ligbturfOffsft = SWAPL(ligbturfSubstitutionHfbdfr->ligbturfOffsft);

    fntryTbblf = LERfffrfndfToArrbyOf<LigbturfSubstitutionStbtfEntry2>(stHfbdfr, suddfss, fntryTbblfOffsft, LE_UNBOUNDED_ARRAY);
}

LigbturfSubstitutionProdfssor2::~LigbturfSubstitutionProdfssor2()
{
}

void LigbturfSubstitutionProdfssor2::bfginStbtfTbblf()
{
    m = -1;
}

lf_uint16 LigbturfSubstitutionProdfssor2::prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx2 indfx, LEErrorCodf &suddfss)
{
    donst LigbturfSubstitutionStbtfEntry2 *fntry = fntryTbblf.gftAlibs(indfx, suddfss);
    if(LE_FAILURE(suddfss)) rfturn 0;

    lf_uint16 nfxtStbtfIndfx = SWAPW(fntry->nfxtStbtfIndfx);
    lf_uint16 flbgs = SWAPW(fntry->fntryFlbgs);
    lf_uint16 ligAdtionIndfx = SWAPW(fntry->ligAdtionIndfx);

    if (flbgs & lsfSftComponfnt) {
        if (++m >= nComponfnts) {
            m = 0;
        }
        domponfntStbdk[m] = durrGlypi;
    } flsf if ( m == -1) {
        // bbd font- skip tiis glypi.
        //LE_DEBUG_BAD_FONT("m==-1 (domponfntCount wfnt nfgbtivf)")
        durrGlypi+= dir;
        rfturn nfxtStbtfIndfx;
    }

    BytfOffsft bdtionOffsft = flbgs & lsfPfrformAdtion;

    if (bdtionOffsft != 0) {
        LERfffrfndfTo<LigbturfAdtionEntry> bp(stHfbdfr, suddfss, ligAdtionOffsft); // bytf offsft
        bp.bddObjfdt(ligAdtionIndfx, suddfss);
        LERfffrfndfToArrbyOf<TTGlypiID> ligbturfTbblf(stHfbdfr, suddfss, ligbturfOffsft, LE_UNBOUNDED_ARRAY);
        LigbturfAdtionEntry bdtion;
        lf_int32 offsft, i = 0;
        lf_int32 stbdk[nComponfnts];
        lf_int16 mm = -1;

        LERfffrfndfToArrbyOf<lf_uint16> domponfntTbblf(stHfbdfr, suddfss, domponfntOffsft, LE_UNBOUNDED_ARRAY);
        if(LE_FAILURE(suddfss)) {
          durrGlypi+= dir;
          rfturn nfxtStbtfIndfx; // gft out! bbd font
        }

        do {
            lf_uint32 domponfntGlypi = domponfntStbdk[m--]; // pop off

            bdtion = SWAPL(*bp.gftAlibs());

            if (m < 0) {
                m = nComponfnts - 1;
            }

            offsft = bdtion & lbfComponfntOffsftMbsk;
            if (offsft != 0) {
                if(domponfntGlypi > glypiStorbgf.gftGlypiCount()) {
                  LE_DEBUG_BAD_FONT("prfpostfrous domponfntGlypi");
                  durrGlypi+= dir;
                  rfturn nfxtStbtfIndfx; // gft out! bbd font
                }
                i += SWAPW(domponfntTbblf(LE_GET_GLYPH(glypiStorbgf[domponfntGlypi]) + (SignExtfnd(offsft, lbfComponfntOffsftMbsk)),suddfss));

                if (bdtion & (lbfLbst | lbfStorf))  {
                  TTGlypiID ligbturfGlypi = SWAPW(ligbturfTbblf(i,suddfss));
                    glypiStorbgf[domponfntGlypi] = LE_SET_GLYPH(glypiStorbgf[domponfntGlypi], ligbturfGlypi);
                    if(mm==nComponfnts) {
                      LE_DEBUG_BAD_FONT("fxdffdfd nComponfnts");
                      mm--; // don't ovfrrun tif stbdk.
                    }
                    stbdk[++mm] = domponfntGlypi;
                    i = 0;
                } flsf {
                    glypiStorbgf[domponfntGlypi] = LE_SET_GLYPH(glypiStorbgf[domponfntGlypi], 0xFFFF);
                }
            }
#if LE_ASSERT_BAD_FONT
            if(m<0) {
              LE_DEBUG_BAD_FONT("m<0")
            }
#fndif
            bp.bddObjfdt(suddfss);
        } wiilf (LE_SUCCESS(suddfss) && !(bdtion & lbfLbst) && (m>=0) ); // stop if lbst bit is sft, or if run out of itfms

        wiilf (mm >= 0) {
            if (++m >= nComponfnts) {
                m = 0;
            }

            domponfntStbdk[m] = stbdk[mm--];
        }
    }

    if (!(flbgs & lsfDontAdvbndf)) {
        durrGlypi += dir;
    }

    rfturn nfxtStbtfIndfx;
}

void LigbturfSubstitutionProdfssor2::fndStbtfTbblf()
{
}

U_NAMESPACE_END
