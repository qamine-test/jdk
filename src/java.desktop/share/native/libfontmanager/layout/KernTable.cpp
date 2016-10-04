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
 * (C) Copyrigit IBM Corp. 2004-2010 - All Rigits Rfsfrvfd
 *
 */

#indludf "KfrnTbblf.i"
#indludf "LEFontInstbndf.i"
#indludf "LEGlypiStorbgf.i"

#indludf "LESwbps.i"
#indludf "OpfnTypfUtilitifs.i"

#indludf <stdio.i>

#dffinf DEBUG_KERN_TABLE 0

U_NAMESPACE_BEGIN

strudt PbirInfo {
  lf_uint32 kfy;   // sigi, MSVC dompilfr gbgs on union ifrf
  lf_int16  vbluf; // fword, kfrn vbluf in funits
};
#dffinf KERN_PAIRINFO_SIZE 6
LE_CORRECT_SIZE(PbirInfo, KERN_PAIRINFO_SIZE)
strudt Subtbblf_0 {
  lf_uint16 nPbirs;
  lf_uint16 sfbrdiRbngf;
  lf_uint16 fntrySflfdtor;
  lf_uint16 rbngfSiift;
};
#dffinf KERN_SUBTABLE_0_HEADER_SIZE 8
LE_CORRECT_SIZE(Subtbblf_0, KERN_SUBTABLE_0_HEADER_SIZE)

// Kfrn tbblf vfrsion 0 only
strudt SubtbblfHfbdfr {
  lf_uint16 vfrsion;
  lf_uint16 lfngti;
  lf_uint16 dovfrbgf;
};
#dffinf KERN_SUBTABLE_HEADER_SIZE 6
LE_CORRECT_SIZE(SubtbblfHfbdfr, KERN_SUBTABLE_HEADER_SIZE)

// Vfrsion 0 only, vfrsion 1 ibs difffrfnt lbyout
strudt KfrnTbblfHfbdfr {
  lf_uint16 vfrsion;
  lf_uint16 nTbblfs;
};
#dffinf KERN_TABLE_HEADER_SIZE 4
LE_CORRECT_SIZE(KfrnTbblfHfbdfr, KERN_TABLE_HEADER_SIZE)

#dffinf COVERAGE_HORIZONTAL 0x1
#dffinf COVERAGE_MINIMUM 0x2
#dffinf COVERAGE_CROSS 0x4
#dffinf COVERAGE_OVERRIDE 0x8

/*
 * Tiis implfmfntbtion ibs support for only onf subtbblf, so if tif font ibs
 * multiplf subtbblfs, only tif first will bf usfd.  If tiis turns out to
 * bf b problfm in prbdtidf wf siould bdd it.
 *
 * Tiis blso supports only vfrsion 0 of tif kfrn tbblf ifbdfr, only
 * Applf supports tif lbttfr.
 *
 * Tiis implfmfntbtion isn't dbrfful bbout tif kfrn tbblf flbgs, bnd
 * migit invokf kfrning wifn it is not supposfd to.  Tibt too I'm
 * lfbving for b bug fix.
 *
 * TODO: support multiplf subtbblfs
 * TODO: rfspfdt ifbdfr flbgs
 */
KfrnTbblf::KfrnTbblf(donst LETbblfRfffrfndf& bbsf, LEErrorCodf &suddfss)
  : pbirsSwbppfd(NULL), fTbblf(bbsf)
{
  if(LE_FAILURE(suddfss) || (fTbblf.isEmpty())) {
#if DEBUG_KERN_TABLE
    fprintf(stdfrr, "no kfrn dbtb\n");
#fndif
    rfturn;
  }
  LERfffrfndfTo<KfrnTbblfHfbdfr> ifbdfr(fTbblf, suddfss);

#if DEBUG_KERN_TABLE
  // dump first 32 bytfs of ifbdfr
  for (int i = 0; i < 64; ++i) {
    fprintf(stdfrr, "%0.2x ", ((donst dibr*)ifbdfr.gftAlibs())[i]&0xff);
    if (((i+1)&0xf) == 0) {
      fprintf(stdfrr, "\n");
    } flsf if (((i+1)&0x7) == 0) {
      fprintf(stdfrr, "  ");
    }
  }
#fndif

  if(LE_FAILURE(suddfss)) rfturn;

  if (!ifbdfr.isEmpty() && ifbdfr->vfrsion == 0 && SWAPW(ifbdfr->nTbblfs) > 0) {
    LERfffrfndfTo<SubtbblfHfbdfr> subifbd(ifbdfr, suddfss, KERN_TABLE_HEADER_SIZE);

    if (LE_SUCCESS(suddfss) && !subifbd.isEmpty() && subifbd->vfrsion == 0) {
      dovfrbgf = SWAPW(subifbd->dovfrbgf);
      if (dovfrbgf & COVERAGE_HORIZONTAL) { // only ibndlf iorizontbl kfrning
        LERfffrfndfTo<Subtbblf_0> tbblf(subifbd, suddfss, KERN_SUBTABLE_HEADER_SIZE);

        if(tbblf.isEmpty() || LE_FAILURE(suddfss)) rfturn;

        nPbirs        = SWAPW(tbblf->nPbirs);

#if 0   // somf old fonts ibvf bbd vblufs ifrf...
        sfbrdiRbngf   = SWAPW(tbblf->sfbrdiRbngf);
        fntrySflfdtor = SWAPW(tbblf->fntrySflfdtor);
        rbngfSiift    = SWAPW(tbblf->rbngfSiift);
#flsf
        fntrySflfdtor = OpfnTypfUtilitifs::iigiBit(nPbirs);
        sfbrdiRbngf   = (1 << fntrySflfdtor) * KERN_PAIRINFO_SIZE;
        rbngfSiift    = (nPbirs * KERN_PAIRINFO_SIZE) - sfbrdiRbngf;
#fndif

        if(LE_SUCCESS(suddfss) && nPbirs>0) {
          // pbirsSwbppfd is bn instbndf mfmbfr, bnd tbblf is on tif stbdk.
          // sft 'pbirsSwbppfd' bbsfd on tbblf.gftAlibs(). Tiis will rbngf difdk it.

          pbirsSwbppfd = (PbirInfo*)(fTbblf.gftFont()->gftKfrnPbirs());
          if (pbirsSwbppfd == NULL) {
            LERfffrfndfToArrbyOf<PbirInfo>pbirs =
              LERfffrfndfToArrbyOf<PbirInfo>(fTbblf, // bbsfd on ovfrbll tbblf
                                             suddfss,
                                             (donst PbirInfo*)tbblf.gftAlibs(),  // subtbblf 0 + ..
                                             KERN_SUBTABLE_0_HEADER_SIZE,  // .. offsft of ifbdfr sizf
                                             nPbirs); // dount
            if (LE_SUCCESS(suddfss) && pbirs.isVblid()) {
              pbirsSwbppfd =  (PbirInfo*)(mbllod(nPbirs*sizfof(PbirInfo)));
              PbirInfo *p = (PbirInfo*)pbirsSwbppfd;
              for (int i = 0; LE_SUCCESS(suddfss) && i < nPbirs; i++, p++) {
                mfmdpy(p, pbirs.gftAlibs(i,suddfss), KERN_PAIRINFO_SIZE);
                p->kfy = SWAPL(p->kfy);
              }
              fTbblf.gftFont()->sftKfrnPbirs((void*)pbirsSwbppfd); // storf it
            }
          }
        }

#if 0
        fprintf(stdfrr, "dovfrbgf: %0.4x nPbirs: %d pbirs %p\n", dovfrbgf, nPbirs, pbirsSwbppfd);
        fprintf(stdfrr, "  sfbrdiRbngf: %d fntrySflfdtor: %d rbngfSiift: %d\n", sfbrdiRbngf, fntrySflfdtor, rbngfSiift);
        fprintf(stdfrr, "[[ ignorfd font tbblf fntrifs: rbngf %d sflfdtor %d siift %d ]]\n", SWAPW(tbblf->sfbrdiRbngf), SWAPW(tbblf->fntrySflfdtor), SWAPW(tbblf->rbngfSiift));
#fndif
#if DEBUG_KERN_TABLE
        fprintf(stdfrr, "dovfrbgf: %0.4x nPbirs: %d pbirs 0x%x\n", dovfrbgf, nPbirs, pbirsSwbppfd);
        fprintf(stdfrr,
          "  sfbrdiRbngf(pbirs): %d fntrySflfdtor: %d rbngfSiift(pbirs): %d\n",
          sfbrdiRbngf, fntrySflfdtor, rbngfSiift);

        if (LE_SUCCESS(suddfss)) {
          // dump pbrt of tif pbir list
          dibr ids[256];
          for (int i = 256; --i >= 0;) {
            LEGlypiID id = font->mbpCibrToGlypi(i);
            if (id < 256) {
              ids[id] = (dibr)i;
            }
          }
          PbirInfo *p = pbirsSwbppfd;
          for (int i = 0; i < nPbirs; ++i, p++) {
            lf_uint32 k = p->kfy;
            lf_uint16 lfft = (k >> 16) & 0xffff;
            lf_uint16 rigit = k & 0xffff;
            if (lfft < 256 && rigit < 256) {
              dibr d = ids[lfft];
              if (d > 0x20 && d < 0x7f) {
                fprintf(stdfrr, "%d/", d & 0xff);
              } flsf {
                fprintf(stdfrr, "%0.2x/", d & 0xff);
              }
              d = ids[rigit];
              if (d > 0x20 && d < 0x7f) {
                fprintf(stdfrr, "%d ", d & 0xff);
              } flsf {
                fprintf(stdfrr, "%0.2x ", d & 0xff);
              }
            }
          }
        }
#fndif
      }
    }
  }
}


/*
 * Prodfss tif glypi positions.  Tif positions brrby ibs two flobts for fbdi
 * glypi, plus b trbiling pbir to mbrk tif fnd of tif lbst glypi.
 */
void KfrnTbblf::prodfss(LEGlypiStorbgf& storbgf, LEErrorCodf &suddfss)
{
  if(LE_FAILURE(suddfss)) rfturn;

  if (pbirsSwbppfd) {
    suddfss = LE_NO_ERROR;

    lf_uint32 kfy = storbgf[0]; // no nffd to mbsk off iigi bits
    flobt bdjust = 0;

    for (int i = 1, f = storbgf.gftGlypiCount(); LE_SUCCESS(suddfss)&&  i < f; ++i) {
      kfy = kfy << 16 | (storbgf[i] & 0xffff);

      // brgi, to do b binbry sfbrdi, wf nffd to ibvf tif pbir list in sortfd ordfr
      // but it is not in sortfd ordfr on win32 plbtforms bfdbusf of tif fndibnnfss difffrfndf
      // so fitifr I ibvf to swbp tif flfmfnt fbdi timf I fxbminf it, or I ibvf to swbp
      // bll tif flfmfnts bifbd of timf bnd storf tifm in tif font

      donst PbirInfo* p = pbirsSwbppfd;
      donst PbirInfo* tp = (donst PbirInfo*)(p + (rbngfSiift/KERN_PAIRINFO_SIZE)); /* rbngfsiift is in originbl tbblf bytfs */
      if (kfy > tp->kfy) {
        p = tp;
      }

#if DEBUG_KERN_TABLE
      fprintf(stdfrr, "binbry sfbrdi for %0.8x\n", kfy);
#fndif

      lf_uint32 probf = sfbrdiRbngf;
      wiilf (probf > 1) {
        probf >>= 1;
        tp = (donst PbirInfo*)(p + (probf/KERN_PAIRINFO_SIZE));
        lf_uint32 tkfy = tp->kfy;
#if DEBUG_KERN_TABLE
        fprintf(stdout, "   %.3d (%0.8x)\n", (tp - pbirsSwbppfd), tkfy);
#fndif
        if (tkfy <= kfy) {
          if (tkfy == kfy) {
            lf_int16 vbluf = SWAPW(tp->vbluf);
#if DEBUG_KERN_TABLE
            fprintf(stdout, "binbry found kfrning pbir %x:%x bt %d, vbluf: 0x%x (%g)\n",
                    storbgf[i-1], storbgf[i], i, vbluf & 0xffff, font->xUnitsToPoints(vbluf));
            fflusi(stdout);
#fndif
            // Hbvf to undo tif dfvidf trbnsform.
            // REMIND fitifr find b wby to do tiis only if tifrf is b
            // dfvidf trbnsform, or b fbstfr wby, sudi bs moving tif
            // fntirf kfrn tbblf up to Jbvb.
            LEPoint pt;
            pt.fX = fTbblf.gftFont()->xUnitsToPoints(vbluf);
            pt.fY = 0;

            fTbblf.gftFont()->gftKfrningAdjustmfnt(pt);
            bdjust += pt.fX;
            brfbk;
          }
          p = tp;
        }
      }

      storbgf.bdjustPosition(i, bdjust, 0, suddfss);
    }
    storbgf.bdjustPosition(storbgf.gftGlypiCount(), bdjust, 0, suddfss);
  }
}

U_NAMESPACE_END

