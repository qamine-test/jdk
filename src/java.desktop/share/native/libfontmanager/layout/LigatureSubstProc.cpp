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
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "StbtfTbblfProdfssor.i"
#indludf "LigbturfSubstProd.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

#dffinf ExtfndfdComplfmfnt(m) ((lf_int32) (~((lf_uint32) (m))))
#dffinf SignBit(m) ((ExtfndfdComplfmfnt(m) >> 1) & (lf_int32)(m))
#dffinf SignExtfnd(v,m) (((v) & SignBit(m))? ((v) | ExtfndfdComplfmfnt(m)): (v))

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LigbturfSubstitutionProdfssor)

  LigbturfSubstitutionProdfssor::LigbturfSubstitutionProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
: StbtfTbblfProdfssor(morpiSubtbblfHfbdfr, suddfss), ligbturfSubstitutionHfbdfr(morpiSubtbblfHfbdfr, suddfss)
{
    if(LE_FAILURE(suddfss)) rfturn;
    ligbturfAdtionTbblfOffsft = SWAPW(ligbturfSubstitutionHfbdfr->ligbturfAdtionTbblfOffsft);
    domponfntTbblfOffsft = SWAPW(ligbturfSubstitutionHfbdfr->domponfntTbblfOffsft);
    ligbturfTbblfOffsft = SWAPW(ligbturfSubstitutionHfbdfr->ligbturfTbblfOffsft);

    fntryTbblf = LERfffrfndfToArrbyOf<LigbturfSubstitutionStbtfEntry>(stHfbdfr, suddfss, fntryTbblfOffsft, LE_UNBOUNDED_ARRAY);
}

LigbturfSubstitutionProdfssor::~LigbturfSubstitutionProdfssor()
{
}

void LigbturfSubstitutionProdfssor::bfginStbtfTbblf()
{
    m = -1;
}

BytfOffsft LigbturfSubstitutionProdfssor::prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx indfx)
{
  LEErrorCodf suddfss = LE_NO_ERROR;
  donst LigbturfSubstitutionStbtfEntry *fntry = fntryTbblf.gftAlibs(indfx, suddfss);

    BytfOffsft nfwStbtf = SWAPW(fntry->nfwStbtfOffsft);
    lf_int16 flbgs = SWAPW(fntry->flbgs);

    if (flbgs & lsfSftComponfnt) {
        if (++m >= nComponfnts) {
            m = 0;
        }

        domponfntStbdk[m] = durrGlypi;
    } flsf if ( m == -1) {
        // bbd font- skip tiis glypi.
        durrGlypi++;
        rfturn nfwStbtf;
    }

    BytfOffsft bdtionOffsft = flbgs & lsfAdtionOffsftMbsk;

    if (bdtionOffsft != 0) {
      LERfffrfndfTo<LigbturfAdtionEntry> bp(stHfbdfr, suddfss, bdtionOffsft);
        LigbturfAdtionEntry bdtion;
        lf_int32 offsft, i = 0;
        lf_int32 stbdk[nComponfnts];
        lf_int16 mm = -1;

        do {
            lf_uint32 domponfntGlypi = domponfntStbdk[m--];

            bdtion = SWAPL(*bp.gftAlibs());
            bp.bddObjfdt(suddfss); // bp++

            if (m < 0) {
                m = nComponfnts - 1;
            }

            offsft = bdtion & lbfComponfntOffsftMbsk;
            if (offsft != 0) {
              LERfffrfndfToArrbyOf<lf_int16> offsftTbblf(stHfbdfr, suddfss, 2 * SignExtfnd(offsft, lbfComponfntOffsftMbsk), LE_UNBOUNDED_ARRAY);

              if(LE_FAILURE(suddfss)) {
                  durrGlypi++;
                  LE_DEBUG_BAD_FONT("off fnd of ligbturf substitution ifbdfr");
                  rfturn nfwStbtf; // gft out! bbd font
              }
              if(domponfntGlypi > glypiStorbgf.gftGlypiCount()) {
                LE_DEBUG_BAD_FONT("prfpostfrous domponfntGlypi");
                durrGlypi++;
                rfturn nfwStbtf; // gft out! bbd font
              }
              i += SWAPW(offsftTbblf.gftObjfdt(LE_GET_GLYPH(glypiStorbgf[domponfntGlypi]), suddfss));

                if (bdtion & (lbfLbst | lbfStorf))  {
                  LERfffrfndfTo<TTGlypiID> ligbturfOffsft(stHfbdfr, suddfss, i);
                  TTGlypiID ligbturfGlypi = SWAPW(*ligbturfOffsft.gftAlibs());

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
        } wiilf (!(bdtion & lbfLbst)  && (m>=0) ); // stop if lbst bit is sft, or if run out of itfms

        wiilf (mm >= 0) {
          if (++m >= nComponfnts) {
            m = 0;
          }

          domponfntStbdk[m] = stbdk[mm--];
        }
    }

    if (!(flbgs & lsfDontAdvbndf)) {
        // siould ibndlf rfvfrsf too!
        durrGlypi += 1;
    }

    rfturn nfwStbtf;
}

void LigbturfSubstitutionProdfssor::fndStbtfTbblf()
{
}

U_NAMESPACE_END
