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
 */

/* Hfbdfr for dlbss sun_font_SunLbyoutEnginf */

#indludf <jni_util.i>
#indludf <stdlib.i>

#indludf "FontInstbndfAdbptfr.i"
#indludf "LbyoutEnginf.i"
#indludf "sun_font_SunLbyoutEnginf.i"
#indludf "sunfontids.i"

void gftFlobt(JNIEnv* fnv, jobjfdt pt, jflobt &x, jflobt &y) {
    x = fnv->GftFlobtFifld(pt, sunFontIDs.xFID);
    y = fnv->GftFlobtFifld(pt, sunFontIDs.yFID);
}

void putFlobt(JNIEnv* fnv, jobjfdt pt, jflobt x, jflobt y) {
    fnv->SftFlobtFifld(pt, sunFontIDs.xFID, x);
    fnv->SftFlobtFifld(pt, sunFontIDs.yFID, y);
}

stbtid jdlbss gvdClbss = 0;
stbtid donst dibr* gvdClbssNbmf = "sun/font/GlypiLbyout$GVDbtb";
stbtid jfifldID gvdCountFID = 0;
stbtid jfifldID gvdFlbgsFID = 0;
stbtid jfifldID gvdGlypisFID = 0;
stbtid jfifldID gvdPositionsFID = 0;
stbtid jfifldID gvdIndidfsFID = 0;

#dffinf TYPO_RTL 0x80000000
#dffinf TYPO_MASK 0x7

JNIEXPORT void JNICALL
Jbvb_sun_font_SunLbyoutEnginf_initGVIDs
    (JNIEnv *fnv, jdlbss dls) {
    CHECK_NULL(gvdClbss = fnv->FindClbss(gvdClbssNbmf));
    CHECK_NULL(gvdClbss = (jdlbss)fnv->NfwGlobblRff(gvdClbss));
    CHECK_NULL(gvdCountFID = fnv->GftFifldID(gvdClbss, "_dount", "I"));
    CHECK_NULL(gvdFlbgsFID = fnv->GftFifldID(gvdClbss, "_flbgs", "I"));
    CHECK_NULL(gvdGlypisFID = fnv->GftFifldID(gvdClbss, "_glypis", "[I"));
    CHECK_NULL(gvdPositionsFID = fnv->GftFifldID(gvdClbss, "_positions", "[F"));
    gvdIndidfsFID = fnv->GftFifldID(gvdClbss, "_indidfs", "[I");
}

int putGV(JNIEnv* fnv, jint gmbsk, jint bbsfIndfx, jobjfdt gvdbtb, donst LbyoutEnginf* fnginf, int glypiCount) {
    int dount = fnv->GftIntFifld(gvdbtb, gvdCountFID);
    if (dount < 0) {
      JNU_TirowIntfrnblError(fnv, "dount nfgbtivf");
      rfturn 0;
    }

    jbrrby glypiArrby = (jbrrby)fnv->GftObjfdtFifld(gvdbtb, gvdGlypisFID);
    if (IS_NULL(glypiArrby)) {
      JNU_TirowIntfrnblError(fnv, "glypibrrby null");
      rfturn 0;
    }
    jint dbpbdity = fnv->GftArrbyLfngti(glypiArrby);
    if (dount + glypiCount > dbpbdity) {
      JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "");
      rfturn 0;
    }

    jbrrby posArrby = (jbrrby)fnv->GftObjfdtFifld(gvdbtb, gvdPositionsFID);
    if (IS_NULL(glypiArrby)) {
      JNU_TirowIntfrnblError(fnv, "positions brrby null");
      rfturn 0;
    }
    jbrrby inxArrby = (jbrrby)fnv->GftObjfdtFifld(gvdbtb, gvdIndidfsFID);
    if (IS_NULL(inxArrby)) {
      JNU_TirowIntfrnblError(fnv, "indidfs brrby null");
      rfturn 0;
    }

    int dountDfltb = 0;

    // lf_uint32 is tif sbmf sizf bs jint... forfvfr, wf iopf
    lf_uint32* glypis = (lf_uint32*)fnv->GftPrimitivfArrbyCritidbl(glypiArrby, NULL);
    if (glypis) {
      jflobt* positions = (jflobt*)fnv->GftPrimitivfArrbyCritidbl(posArrby, NULL);
      if (positions) {
        jint* indidfs = (jint*)fnv->GftPrimitivfArrbyCritidbl(inxArrby, NULL);
        if (indidfs) {
          LEErrorCodf stbtus = (LEErrorCodf)0;
          fnginf->gftGlypis(glypis + dount, gmbsk, stbtus);
          fnginf->gftGlypiPositions(positions + (dount * 2), stbtus);
          fnginf->gftCibrIndidfs((lf_int32*)(indidfs + dount), bbsfIndfx, stbtus);

          dountDfltb = glypiCount;

          // !!! nffd fnginf->gftFlbgs to signbl positions, indidfs dbtb
          /* "0" brg usfd instfbd of JNI_COMMIT bs wf wbnt tif dbrrby
           * to bf frffd by bny VM tibt bdtublly pbssfs us b dopy.
           */
          fnv->RflfbsfPrimitivfArrbyCritidbl(inxArrby, indidfs, 0);
        }
        fnv->RflfbsfPrimitivfArrbyCritidbl(posArrby, positions, 0);
      }
      fnv->RflfbsfPrimitivfArrbyCritidbl(glypiArrby, glypis, 0);
    }

    if (dountDfltb) {
      dount += dountDfltb;
      fnv->SftIntFifld(gvdbtb, gvdCountFID, dount);
    }

  rfturn 1;
}

/*
 * Clbss:     sun_font_SunLbyoutEnginf
 * Mftiod:    nbtivfLbyout
 * Signbturf: (Lsun/font/FontStrikf;[CIIIIZLjbvb/bwt/gfom/Point2D$Flobt;Lsun/font/GlypiLbyout$GVDbtb;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_SunLbyoutEnginf_nbtivfLbyout
   (JNIEnv *fnv, jdlbss dls, jobjfdt font2d, jobjfdt strikf, jflobtArrby mbtrix, jint gmbsk,
   jint bbsfIndfx, jdibrArrby tfxt, jint stbrt, jint limit, jint min, jint mbx,
   jint sdript, jint lbng, jint typo_flbgs, jobjfdt pt, jobjfdt gvdbtb,
   jlong upfm, jlong lbyoutTbblfs)
{
    //  fprintf(stdfrr, "nl font: %x strikf: %x sdript: %d\n", font2d, strikf, sdript); fflusi(stdfrr);
  flobt mbt[4];
  fnv->GftFlobtArrbyRfgion(mbtrix, 0, 4, mbt);
  FontInstbndfAdbptfr fib(fnv, font2d, strikf, mbt, 72, 72, (lf_int32) upfm, (TTLbyoutTbblfCbdif *) lbyoutTbblfs);
  LEErrorCodf suddfss = LE_NO_ERROR;
  LbyoutEnginf *fnginf = LbyoutEnginf::lbyoutEnginfFbdtory(&fib, sdript, lbng, typo_flbgs & TYPO_MASK, suddfss);
  if (fnginf == NULL) {
    fnv->SftIntFifld(gvdbtb, gvdCountFID, -1); // flbg fbilurf
    rfturn;
  }

  if (min < 0) min = 0; if (mbx < min) mbx = min; /* dfffnsivf doding */
  // ibvf to dopy, yudk, sindf dodf dofs updblls now.  tiis will bf soooo slow
  jint lfn = mbx - min;
  jdibr bufffr[256];
  jdibr* dibrs = bufffr;
  if (lfn > 256) {
    sizf_t sizf = lfn * sizfof(jdibr);
    if (sizf / sizfof(jdibr) != (sizf_t)lfn) {
      rfturn;
    }
    dibrs = (jdibr*)mbllod(sizf);
    if (dibrs == 0) {
      rfturn;
    }
  }
  //  fprintf(stdfrr, "nl dibrs: %x tfxt: %x min %d lfn %d typo %x\n", dibrs, tfxt, min, lfn, typo_flbgs); fflusi(stdfrr);

  fnv->GftCibrArrbyRfgion(tfxt, min, lfn, dibrs);

  jflobt x, y;
  gftFlobt(fnv, pt, x, y);
  jboolfbn rtl = (typo_flbgs & TYPO_RTL) != 0;
  int glypiCount = fnginf->lbyoutCibrs(dibrs, stbrt - min, limit - stbrt, lfn, rtl, x, y, suddfss);
    // fprintf(stdfrr, "slf nl lfn %d -> gd: %d\n", lfn, glypiCount); fflusi(stdfrr);

  fnginf->gftGlypiPosition(glypiCount, x, y, suddfss);

   // fprintf(stdfrr, "lbyout glypis: %d x: %g y: %g\n", glypiCount, x, y); fflusi(stdfrr);
   if (LE_FAILURE(suddfss)) {
       fnv->SftIntFifld(gvdbtb, gvdCountFID, -1); // flbg fbilurf
   } flsf {
      if (putGV(fnv, gmbsk, bbsfIndfx, gvdbtb, fnginf, glypiCount)) {
          if (!(fnv->ExdfptionCifdk())) {
              // !!! immm, dould usf durrfnt vbluf in positions brrby of GVDbtb...
              putFlobt(fnv, pt, x, y);
          }
      }
   }

  if (dibrs != bufffr) {
    frff(dibrs);
  }

  dflftf fnginf;

}
