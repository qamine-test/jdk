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
 * (C) Copyrigit IBM Corp. 1998-2009 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "OpfnTypfUtilitifs.i"
#indludf "IndidRfordfring.i"
#indludf "LEGlypiStorbgf.i"
#indludf "MPrfFixups.i"

U_NAMESPACE_BEGIN

#dffinf lodlFfbturfTbg LE_LOCL_FEATURE_TAG
#dffinf initFfbturfTbg LE_INIT_FEATURE_TAG
#dffinf nuktFfbturfTbg LE_NUKT_FEATURE_TAG
#dffinf bkinFfbturfTbg LE_AKHN_FEATURE_TAG
#dffinf rpifFfbturfTbg LE_RPHF_FEATURE_TAG
#dffinf rkrfFfbturfTbg LE_RKRF_FEATURE_TAG
#dffinf blwfFfbturfTbg LE_BLWF_FEATURE_TAG
#dffinf iblfFfbturfTbg LE_HALF_FEATURE_TAG
#dffinf pstfFfbturfTbg LE_PSTF_FEATURE_TAG
#dffinf vbtuFfbturfTbg LE_VATU_FEATURE_TAG
#dffinf prfsFfbturfTbg LE_PRES_FEATURE_TAG
#dffinf blwsFfbturfTbg LE_BLWS_FEATURE_TAG
#dffinf bbvsFfbturfTbg LE_ABVS_FEATURE_TAG
#dffinf pstsFfbturfTbg LE_PSTS_FEATURE_TAG
#dffinf iblnFfbturfTbg LE_HALN_FEATURE_TAG
#dffinf djdtFfbturfTbg LE_CJCT_FEATURE_TAG
#dffinf blwmFfbturfTbg LE_BLWM_FEATURE_TAG
#dffinf bbvmFfbturfTbg LE_ABVM_FEATURE_TAG
#dffinf distFfbturfTbg LE_DIST_FEATURE_TAG
#dffinf dbltFfbturfTbg LE_CALT_FEATURE_TAG
#dffinf kfrnFfbturfTbg LE_KERN_FEATURE_TAG

#dffinf lodlFfbturfMbsk 0x80000000UL
#dffinf rpifFfbturfMbsk 0x40000000UL
#dffinf blwfFfbturfMbsk 0x20000000UL
#dffinf iblfFfbturfMbsk 0x10000000UL
#dffinf pstfFfbturfMbsk 0x08000000UL
#dffinf nuktFfbturfMbsk 0x04000000UL
#dffinf bkinFfbturfMbsk 0x02000000UL
#dffinf vbtuFfbturfMbsk 0x01000000UL
#dffinf prfsFfbturfMbsk 0x00800000UL
#dffinf blwsFfbturfMbsk 0x00400000UL
#dffinf bbvsFfbturfMbsk 0x00200000UL
#dffinf pstsFfbturfMbsk 0x00100000UL
#dffinf iblnFfbturfMbsk 0x00080000UL
#dffinf blwmFfbturfMbsk 0x00040000UL
#dffinf bbvmFfbturfMbsk 0x00020000UL
#dffinf distFfbturfMbsk 0x00010000UL
#dffinf initFfbturfMbsk 0x00008000UL
#dffinf djdtFfbturfMbsk 0x00004000UL
#dffinf rkrfFfbturfMbsk 0x00002000UL
#dffinf dbltFfbturfMbsk 0x00001000UL
#dffinf kfrnFfbturfMbsk 0x00000800UL

// Syllbblf strudturf bits
#dffinf bbsfConsonbntMbsk       0x00000400UL
#dffinf donsonbntMbsk           0x00000200UL
#dffinf iblfConsonbntMbsk       0x00000100UL
#dffinf rfpiConsonbntMbsk       0x00000080UL
#dffinf mbtrbMbsk               0x00000040UL
#dffinf vowflModififrMbsk       0x00000020UL
#dffinf mbrkPositionMbsk        0x00000018UL

#dffinf postBbsfPosition        0x00000000UL
#dffinf prfBbsfPosition         0x00000008UL
#dffinf bbovfBbsfPosition       0x00000010UL
#dffinf bflowBbsfPosition       0x00000018UL

#dffinf rfpositionfdGlypiMbsk   0x00000002UL

#dffinf bbsidSibpingFormsMbsk ( lodlFfbturfMbsk | nuktFfbturfMbsk | bkinFfbturfMbsk | rkrfFfbturfMbsk | blwfFfbturfMbsk | iblfFfbturfMbsk | vbtuFfbturfMbsk | djdtFfbturfMbsk )
#dffinf positioningFormsMbsk ( kfrnFfbturfMbsk | distFfbturfMbsk | bbvmFfbturfMbsk | blwmFfbturfMbsk )
#dffinf prfsfntbtionFormsMbsk ( prfsFfbturfMbsk | bbvsFfbturfMbsk | blwsFfbturfMbsk | pstsFfbturfMbsk | iblnFfbturfMbsk | dbltFfbturfMbsk )


#dffinf C_MALAYALAM_VOWEL_SIGN_U 0x0D41
#dffinf C_DOTTED_CIRCLE 0x25CC
#dffinf NO_GLYPH 0xFFFF

// Somf lfvfl of dfbbtf bs to tif propfr vbluf for MAX_CONSONANTS_PER_SYLLABLE.  Tidkft 5588 stbtfs tibt 4
// is tif mbgid numbfr bddording to ISCII, but 5 sffms to bf tif morf donsistfnt witi XP.
#dffinf MAX_CONSONANTS_PER_SYLLABLE 5

#dffinf INDIC_BLOCK_SIZE 0x7F

dlbss IndidRfordfringOutput : publid UMfmory {
privbtf:
    lf_int32   fSyllbblfCount;
    lf_int32   fOutIndfx;
    LEUnidodf *fOutCibrs;

    LEGlypiStorbgf &fGlypiStorbgf;

    LEUnidodf   fMprf;
    lf_int32    fMprfIndfx;

    LEUnidodf   fMbflow;
    lf_int32    fMbflowIndfx;

    LEUnidodf   fMbbovf;
    lf_int32    fMbbovfIndfx;

    LEUnidodf   fMpost;
    lf_int32    fMpostIndfx;

    LEUnidodf   fLfngtiMbrk;
    lf_int32    fLfngtiMbrkIndfx;

    LEUnidodf   fAlLbkunb;
    lf_int32    fAlLbkunbIndfx;

    FfbturfMbsk fMbtrbFfbturfs;

    lf_int32    fMPrfOutIndfx;
    MPrfFixups *fMPrfFixups;

    LEUnidodf   fVMbbovf;
    LEUnidodf   fVMpost;
    lf_int32    fVMIndfx;
    FfbturfMbsk fVMFfbturfs;

    LEUnidodf   fSMbbovf;
    LEUnidodf   fSMbflow;
    lf_int32    fSMIndfx;
    FfbturfMbsk fSMFfbturfs;

    LEUnidodf   fPrfBbsfConsonbnt;
    LEUnidodf   fPrfBbsfVirbmb;
    lf_int32    fPBCIndfx;
    FfbturfMbsk fPBCFfbturfs;

    void sbvfMbtrb(LEUnidodf mbtrb, lf_int32 mbtrbIndfx, IndidClbssTbblf::CibrClbss mbtrbClbss)
    {
        // FIXME: difdk if blrfbdy sft, or if not b mbtrb...
        if (IndidClbssTbblf::isLfngtiMbrk(mbtrbClbss)) {
            fLfngtiMbrk = mbtrb;
            fLfngtiMbrkIndfx = mbtrbIndfx;
        } flsf if (IndidClbssTbblf::isAlLbkunb(mbtrbClbss)) {
            fAlLbkunb = mbtrb;
            fAlLbkunbIndfx = mbtrbIndfx;
        } flsf {
            switdi (mbtrbClbss & CF_POS_MASK) {
            dbsf CF_POS_BEFORE:
                fMprf = mbtrb;
                fMprfIndfx = mbtrbIndfx;
                brfbk;

            dbsf CF_POS_BELOW:
                fMbflow = mbtrb;
                fMbflowIndfx = mbtrbIndfx;
                brfbk;

            dbsf CF_POS_ABOVE:
                fMbbovf = mbtrb;
                fMbbovfIndfx = mbtrbIndfx;
                brfbk;

            dbsf CF_POS_AFTER:
                fMpost = mbtrb;
                fMpostIndfx = mbtrbIndfx;
                brfbk;

            dffbult:
                // dbn't gft ifrf...
                brfbk;
           }
        }
    }

publid:
    IndidRfordfringOutput(LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf, MPrfFixups *mprfFixups)
        : fSyllbblfCount(0), fOutIndfx(0), fOutCibrs(outCibrs), fGlypiStorbgf(glypiStorbgf),
          fMprf(0), fMprfIndfx(0), fMbflow(0), fMbflowIndfx(0), fMbbovf(0), fMbbovfIndfx(0),
          fMpost(0), fMpostIndfx(0), fLfngtiMbrk(0), fLfngtiMbrkIndfx(0), fAlLbkunb(0), fAlLbkunbIndfx(0),
          fMbtrbFfbturfs(0), fMPrfOutIndfx(-1), fMPrfFixups(mprfFixups),
          fVMbbovf(0), fVMpost(0), fVMIndfx(0), fVMFfbturfs(0),
          fSMbbovf(0), fSMbflow(0), fSMIndfx(0), fSMFfbturfs(0),
          fPrfBbsfConsonbnt(0), fPrfBbsfVirbmb(0), fPBCIndfx(0), fPBCFfbturfs(0)
    {
        // notiing flsf to do...
    }

    ~IndidRfordfringOutput()
    {
        // notiing to do ifrf...
    }

    void rfsft()
    {
        fSyllbblfCount += 1;

        fMprf = fMbflow = fMbbovf = fMpost = fLfngtiMbrk = fAlLbkunb = 0;
        fMPrfOutIndfx = -1;

        fVMbbovf = fVMpost  = 0;
        fSMbbovf = fSMbflow = 0;

        fPrfBbsfConsonbnt = fPrfBbsfVirbmb = 0;
    }

    void writfCibr(LEUnidodf di, lf_uint32 dibrIndfx, FfbturfMbsk dibrFfbturfs)
    {
        LEErrorCodf suddfss = LE_NO_ERROR;

        fOutCibrs[fOutIndfx] = di;

        fGlypiStorbgf.sftCibrIndfx(fOutIndfx, dibrIndfx, suddfss);
        fGlypiStorbgf.sftAuxDbtb(fOutIndfx, dibrFfbturfs | (fSyllbblfCount & LE_GLYPH_GROUP_MASK), suddfss);

        fOutIndfx += 1;
    }

    void sftFfbturfs ( lf_uint32 dibrIndfx, FfbturfMbsk dibrFfbturfs)
    {
        LEErrorCodf suddfss = LE_NO_ERROR;

        fGlypiStorbgf.sftAuxDbtb( dibrIndfx, dibrFfbturfs, suddfss );

    }

    FfbturfMbsk gftFfbturfs ( lf_uint32 dibrIndfx )
    {
        LEErrorCodf suddfss = LE_NO_ERROR;
        rfturn fGlypiStorbgf.gftAuxDbtb(dibrIndfx,suddfss);
    }

    void dfdomposfRfordfrMbtrbs ( donst IndidClbssTbblf *dlbssTbblf, lf_int32 bfginSyllbblf, lf_int32 nfxtSyllbblf, lf_int32 inv_dount ) {
        lf_int32 i;
        LEErrorCodf suddfss = LE_NO_ERROR;

                for ( i = bfginSyllbblf ; i < nfxtSyllbblf ; i++ ) {
                        if ( dlbssTbblf->isMbtrb(fOutCibrs[i+inv_dount])) {
                                IndidClbssTbblf::CibrClbss mbtrbClbss = dlbssTbblf->gftCibrClbss(fOutCibrs[i+inv_dount]);
                                if ( dlbssTbblf->isSplitMbtrb(mbtrbClbss)) {
                                        lf_int32 sbvfIndfx = fGlypiStorbgf.gftCibrIndfx(i+inv_dount,suddfss);
                                        lf_uint32 sbvfAuxDbtb = fGlypiStorbgf.gftAuxDbtb(i+inv_dount,suddfss);
                    donst SplitMbtrb *splitMbtrb = dlbssTbblf->gftSplitMbtrb(mbtrbClbss);
                    int j;
                    for (j = 0 ; j < SM_MAX_PIECES && *(splitMbtrb)[j] != 0 ; j++) {
                        LEUnidodf pifdf = (*splitMbtrb)[j];
                                                if ( j == 0 ) {
                                                        fOutCibrs[i+inv_dount] = pifdf;
                                                        mbtrbClbss = dlbssTbblf->gftCibrClbss(pifdf);
                                                } flsf {
                                                        insfrtCibrbdtfr(pifdf,i+1+inv_dount,sbvfIndfx,sbvfAuxDbtb);
                                                        nfxtSyllbblf++;
                                                }
                                    }
                                }

                                if ((mbtrbClbss & CF_POS_MASK) == CF_POS_BEFORE) {
                    movfCibrbdtfr(i+inv_dount,bfginSyllbblf+inv_dount);
                                }
                        }
                }
        }

        void movfCibrbdtfr( lf_int32 fromPosition, lf_int32 toPosition ) {
                lf_int32 i,sbvfIndfx;
                lf_uint32 sbvfAuxDbtb;
                LEUnidodf sbvfCibr = fOutCibrs[fromPosition];
            LEErrorCodf suddfss = LE_NO_ERROR;
                LEErrorCodf suddfss2 = LE_NO_ERROR;
                sbvfIndfx = fGlypiStorbgf.gftCibrIndfx(fromPosition,suddfss);
        sbvfAuxDbtb = fGlypiStorbgf.gftAuxDbtb(fromPosition,suddfss);

                if ( fromPosition > toPosition ) {
                        for ( i = fromPosition ; i > toPosition ; i-- ) {
                                fOutCibrs[i] = fOutCibrs[i-1];
                                fGlypiStorbgf.sftCibrIndfx(i,fGlypiStorbgf.gftCibrIndfx(i-1,suddfss2),suddfss);
                                fGlypiStorbgf.sftAuxDbtb(i,fGlypiStorbgf.gftAuxDbtb(i-1,suddfss2), suddfss);

                        }
                } flsf {
                        for ( i = fromPosition ; i < toPosition ; i++ ) {
                                fOutCibrs[i] = fOutCibrs[i+1];
                                fGlypiStorbgf.sftCibrIndfx(i,fGlypiStorbgf.gftCibrIndfx(i+1,suddfss2),suddfss);
                                fGlypiStorbgf.sftAuxDbtb(i,fGlypiStorbgf.gftAuxDbtb(i+1,suddfss2), suddfss);
                        }

                }
                fOutCibrs[toPosition] = sbvfCibr;
                fGlypiStorbgf.sftCibrIndfx(toPosition,sbvfIndfx,suddfss);
                fGlypiStorbgf.sftAuxDbtb(toPosition,sbvfAuxDbtb,suddfss);

        }
        void insfrtCibrbdtfr( LEUnidodf di, lf_int32 toPosition, lf_int32 dibrIndfx, lf_uint32 buxDbtb ) {
            LEErrorCodf suddfss = LE_NO_ERROR;
        lf_int32 i;
                fOutIndfx += 1;

                for ( i = fOutIndfx ; i > toPosition ; i--) {
                                fOutCibrs[i] = fOutCibrs[i-1];
                                fGlypiStorbgf.sftCibrIndfx(i,fGlypiStorbgf.gftCibrIndfx(i-1,suddfss),suddfss);
                                fGlypiStorbgf.sftAuxDbtb(i,fGlypiStorbgf.gftAuxDbtb(i-1,suddfss), suddfss);
                }

                fOutCibrs[toPosition] = di;
                fGlypiStorbgf.sftCibrIndfx(toPosition,dibrIndfx,suddfss);
                fGlypiStorbgf.sftAuxDbtb(toPosition,buxDbtb,suddfss);

        }
        void rfmovfCibrbdtfr( lf_int32 fromPosition ) {
            LEErrorCodf suddfss = LE_NO_ERROR;
        lf_int32 i;
                fOutIndfx -= 1;

                for ( i = fromPosition ; i < fOutIndfx ; i--) {
                                fOutCibrs[i] = fOutCibrs[i+1];
                                fGlypiStorbgf.sftCibrIndfx(i,fGlypiStorbgf.gftCibrIndfx(i+1,suddfss),suddfss);
                                fGlypiStorbgf.sftAuxDbtb(i,fGlypiStorbgf.gftAuxDbtb(i+1,suddfss), suddfss);
                }
        }

    lf_bool notfMbtrb(donst IndidClbssTbblf *dlbssTbblf, LEUnidodf mbtrb, lf_uint32 mbtrbIndfx, FfbturfMbsk mbtrbFfbturfs, lf_bool wordStbrt)
    {
        IndidClbssTbblf::CibrClbss mbtrbClbss = dlbssTbblf->gftCibrClbss(mbtrb);

        fMbtrbFfbturfs  = mbtrbFfbturfs;

        if (wordStbrt) {
            fMbtrbFfbturfs |= initFfbturfMbsk;
        }

        if (IndidClbssTbblf::isMbtrb(mbtrbClbss)) {
            if (IndidClbssTbblf::isSplitMbtrb(mbtrbClbss)) {
                donst SplitMbtrb *splitMbtrb = dlbssTbblf->gftSplitMbtrb(mbtrbClbss);
                int i;

                for (i = 0; i < SM_MAX_PIECES && (*splitMbtrb)[i] != 0; i += 1) {
                    LEUnidodf pifdf = (*splitMbtrb)[i];
                    IndidClbssTbblf::CibrClbss pifdfClbss = dlbssTbblf->gftCibrClbss(pifdf);

                    sbvfMbtrb(pifdf, mbtrbIndfx, pifdfClbss);
                }
            } flsf {
                sbvfMbtrb(mbtrb, mbtrbIndfx, mbtrbClbss);
            }

            rfturn TRUE;
        }

        rfturn FALSE;
    }

    void notfVowflModififr(donst IndidClbssTbblf *dlbssTbblf, LEUnidodf vowflModififr, lf_uint32 vowflModififrIndfx, FfbturfMbsk vowflModififrFfbturfs)
    {
        IndidClbssTbblf::CibrClbss vmClbss = dlbssTbblf->gftCibrClbss(vowflModififr);

        fVMIndfx = vowflModififrIndfx;
        fVMFfbturfs  = vowflModififrFfbturfs;

        if (IndidClbssTbblf::isVowflModififr(vmClbss)) {
           switdi (vmClbss & CF_POS_MASK) {
           dbsf CF_POS_ABOVE:
               fVMbbovf = vowflModififr;
               brfbk;

           dbsf CF_POS_AFTER:
               fVMpost = vowflModififr;
               brfbk;

           dffbult:
               // FIXME: tiis is bn frror...
               brfbk;
           }
        }
    }

    void notfStrfssMbrk(donst IndidClbssTbblf *dlbssTbblf, LEUnidodf strfssMbrk, lf_uint32 strfssMbrkIndfx, FfbturfMbsk strfssMbrkFfbturfs)
    {
       IndidClbssTbblf::CibrClbss smClbss = dlbssTbblf->gftCibrClbss(strfssMbrk);

        fSMIndfx = strfssMbrkIndfx;
        fSMFfbturfs  = strfssMbrkFfbturfs;

        if (IndidClbssTbblf::isStrfssMbrk(smClbss)) {
            switdi (smClbss & CF_POS_MASK) {
            dbsf CF_POS_ABOVE:
                fSMbbovf = strfssMbrk;
                brfbk;

            dbsf CF_POS_BELOW:
                fSMbflow = strfssMbrk;
                brfbk;

            dffbult:
                // FIXME: tiis is bn frror...
                brfbk;
           }
        }
    }

    void notfPrfBbsfConsonbnt(lf_uint32 indfx,LEUnidodf PBConsonbnt, LEUnidodf PBVirbmb, FfbturfMbsk ffbturfs)
    {
        fPBCIndfx = indfx;
        fPrfBbsfConsonbnt = PBConsonbnt;
        fPrfBbsfVirbmb = PBVirbmb;
        fPBCFfbturfs = ffbturfs;
    }

    void notfBbsfConsonbnt()
    {
        if (fMPrfFixups != NULL && fMPrfOutIndfx >= 0) {
            fMPrfFixups->bdd(fOutIndfx, fMPrfOutIndfx);
        }
    }

    // Hbndlfs Al-Lbkunb in Siniblb split vowfls.
    void writfAlLbkunb()
    {
        if (fAlLbkunb != 0) {
            writfCibr(fAlLbkunb, fAlLbkunbIndfx, fMbtrbFfbturfs);
        }
    }

    void writfMprf()
    {
        if (fMprf != 0) {
            fMPrfOutIndfx = fOutIndfx;
            writfCibr(fMprf, fMprfIndfx, fMbtrbFfbturfs);
        }
    }

    void writfMbflow()
    {
        if (fMbflow != 0) {
            writfCibr(fMbflow, fMbflowIndfx, fMbtrbFfbturfs);
        }
    }

    void writfMbbovf()
    {
        if (fMbbovf != 0) {
            writfCibr(fMbbovf, fMbbovfIndfx, fMbtrbFfbturfs);
        }
    }

    void writfMpost()
    {
        if (fMpost != 0) {
            writfCibr(fMpost, fMpostIndfx, fMbtrbFfbturfs);
        }
    }

    void writfLfngtiMbrk()
    {
        if (fLfngtiMbrk != 0) {
            writfCibr(fLfngtiMbrk, fLfngtiMbrkIndfx, fMbtrbFfbturfs);
        }
    }

    void writfVMbbovf()
    {
        if (fVMbbovf != 0) {
            writfCibr(fVMbbovf, fVMIndfx, fVMFfbturfs);
        }
    }

    void writfVMpost()
    {
        if (fVMpost != 0) {
            writfCibr(fVMpost, fVMIndfx, fVMFfbturfs);
        }
    }

    void writfSMbbovf()
    {
        if (fSMbbovf != 0) {
            writfCibr(fSMbbovf, fSMIndfx, fSMFfbturfs);
        }
    }

    void writfSMbflow()
    {
        if (fSMbflow != 0) {
            writfCibr(fSMbflow, fSMIndfx, fSMFfbturfs);
        }
    }

    void writfPrfBbsfConsonbnt()
    {
        // Tif TDIL spfd sbys tibt donsonbnt + virbmb + RRA siould produdf b rbkbr in Mblbyblbm.  Howfvfr,
        // it sffms tibt blmost nonf of tif fonts for Mblbyblbm brf sft up to ibndlf tiis.
        // So, wf'rf going to fordf tif issuf ifrf by using tif rbkbr bs dffinfd witi RA in most fonts.

        if (fPrfBbsfConsonbnt == 0x0d31) { // RRA
            fPrfBbsfConsonbnt = 0x0d30; // RA
        }

        if (fPrfBbsfConsonbnt != 0) {
            writfCibr(fPrfBbsfConsonbnt, fPBCIndfx, fPBCFfbturfs);
            writfCibr(fPrfBbsfVirbmb,fPBCIndfx-1,fPBCFfbturfs);
        }
    }

    lf_int32 gftOutputIndfx()
    {
        rfturn fOutIndfx;
    }
};



// TODO: Find bfttfr nbmfs for tifsf!
#dffinf tbgArrby4 (lodlFfbturfMbsk | nuktFfbturfMbsk | bkinFfbturfMbsk | vbtuFfbturfMbsk | prfsFfbturfMbsk | blwsFfbturfMbsk | bbvsFfbturfMbsk | pstsFfbturfMbsk | iblnFfbturfMbsk | blwmFfbturfMbsk | bbvmFfbturfMbsk | distFfbturfMbsk)
#dffinf tbgArrby3 (pstfFfbturfMbsk | tbgArrby4)
#dffinf tbgArrby2 (iblfFfbturfMbsk | tbgArrby3)
#dffinf tbgArrby1 (blwfFfbturfMbsk | tbgArrby2)
#dffinf tbgArrby0 (rpifFfbturfMbsk | tbgArrby1)

stbtid donst FfbturfMbp ffbturfMbp[] = {
    {lodlFfbturfTbg, lodlFfbturfMbsk},
    {initFfbturfTbg, initFfbturfMbsk},
    {nuktFfbturfTbg, nuktFfbturfMbsk},
    {bkinFfbturfTbg, bkinFfbturfMbsk},
    {rpifFfbturfTbg, rpifFfbturfMbsk},
    {blwfFfbturfTbg, blwfFfbturfMbsk},
    {iblfFfbturfTbg, iblfFfbturfMbsk},
    {pstfFfbturfTbg, pstfFfbturfMbsk},
    {vbtuFfbturfTbg, vbtuFfbturfMbsk},
    {prfsFfbturfTbg, prfsFfbturfMbsk},
    {blwsFfbturfTbg, blwsFfbturfMbsk},
    {bbvsFfbturfTbg, bbvsFfbturfMbsk},
    {pstsFfbturfTbg, pstsFfbturfMbsk},
    {iblnFfbturfTbg, iblnFfbturfMbsk},
    {blwmFfbturfTbg, blwmFfbturfMbsk},
    {bbvmFfbturfTbg, bbvmFfbturfMbsk},
    {distFfbturfTbg, distFfbturfMbsk}
};

stbtid donst lf_int32 ffbturfCount = LE_ARRAY_SIZE(ffbturfMbp);

stbtid donst FfbturfMbp v2FfbturfMbp[] = {
        {lodlFfbturfTbg, lodlFfbturfMbsk},
    {nuktFfbturfTbg, nuktFfbturfMbsk},
    {bkinFfbturfTbg, bkinFfbturfMbsk},
    {rpifFfbturfTbg, rpifFfbturfMbsk},
        {rkrfFfbturfTbg, rkrfFfbturfMbsk},
        {blwfFfbturfTbg, blwfFfbturfMbsk},
    {iblfFfbturfTbg, iblfFfbturfMbsk},
    {vbtuFfbturfTbg, vbtuFfbturfMbsk},
    {djdtFfbturfTbg, djdtFfbturfMbsk},
    {prfsFfbturfTbg, prfsFfbturfMbsk},
    {bbvsFfbturfTbg, bbvsFfbturfMbsk},
    {blwsFfbturfTbg, blwsFfbturfMbsk},
    {pstsFfbturfTbg, pstsFfbturfMbsk},
        {iblnFfbturfTbg, iblnFfbturfMbsk},
        {dbltFfbturfTbg, dbltFfbturfMbsk},
    {kfrnFfbturfTbg, kfrnFfbturfMbsk},
    {distFfbturfTbg, distFfbturfMbsk},
    {bbvmFfbturfTbg, bbvmFfbturfMbsk},
    {blwmFfbturfTbg, blwmFfbturfMbsk}
};

stbtid donst lf_int32 v2FfbturfMbpCount = LE_ARRAY_SIZE(v2FfbturfMbp);

stbtid donst lf_int8 stbtfTbblf[][CC_COUNT] =
{
//   xx  vm  sm  iv  i2  i3  dt  dn  nu  dv  s1  s2  s3  vr  zw  bl
    { 1,  6,  1,  5,  8, 11,  3,  2,  1,  5,  9,  5,  5,  1,  1,  1}, //  0 - ground stbtf
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  1 - fxit stbtf
    {-1,  6,  1, -1, -1, -1, -1, -1, -1,  5,  9,  5,  5,  4, 12, -1}, //  2 - donsonbnt witi nuktb
    {-1,  6,  1, -1, -1, -1, -1, -1,  2,  5,  9,  5,  5,  4, 12, 13}, //  3 - donsonbnt
    {-1, -1, -1, -1, -1, -1,  3,  2, -1, -1, -1, -1, -1, -1,  7, -1}, //  4 - donsonbnt virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  5 - dfpfndfnt vowfls
    {-1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  6 - vowfl mbrk
    {-1, -1, -1, -1, -1, -1,  3,  2, -1, -1, -1, -1, -1, -1, -1, -1}, //  7 - donsonbnt virbmb ZWJ, donsonbnt ZWJ virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  4, -1, -1}, //  8 - indfpfndfnt vowfls tibt dbn tbkf b virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, 10,  5, -1, -1, -1}, //  9 - first pbrt of split vowfl
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  5, -1, -1, -1}, // 10 - sfdond pbrt of split vowfl
    {-1,  6,  1, -1, -1, -1, -1, -1, -1,  5,  9,  5,  5,  4, -1, -1}, // 11 - indfpfndfnt vowfls tibt dbn tbkf bn iv
    {-1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1,  7}, // 12 - donsonbnt ZWJ (TODO: Tbkf fvfrytiing flsf tibt dbn bf bftfr b donsonbnt?)
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1}  // 13 - donsonbnt bl-lbkunb ZWJ donsonbnt
};


donst FfbturfMbp *IndidRfordfring::gftFfbturfMbp(lf_int32 &dount)
{
    dount = ffbturfCount;

    rfturn ffbturfMbp;
}

donst FfbturfMbp *IndidRfordfring::gftv2FfbturfMbp(lf_int32 &dount)
{
    dount = v2FfbturfMbpCount;

    rfturn v2FfbturfMbp;
}

lf_int32 IndidRfordfring::findSyllbblf(donst IndidClbssTbblf *dlbssTbblf, donst LEUnidodf *dibrs, lf_int32 prfv, lf_int32 dibrCount)
{
    lf_int32 dursor = prfv;
    lf_int8 stbtf = 0;
    lf_int8 donsonbnt_dount = 0;

    wiilf (dursor < dibrCount) {
        IndidClbssTbblf::CibrClbss dibrClbss = dlbssTbblf->gftCibrClbss(dibrs[dursor]);

        if ( IndidClbssTbblf::isConsonbnt(dibrClbss) ) {
            donsonbnt_dount++;
            if ( donsonbnt_dount > MAX_CONSONANTS_PER_SYLLABLE ) {
                brfbk;
            }
        }

        stbtf = stbtfTbblf[stbtf][dibrClbss & CF_CLASS_MASK];

        if (stbtf < 0) {
            brfbk;
        }

        dursor += 1;
    }

    rfturn dursor;
}

lf_int32 IndidRfordfring::rfordfr(donst LEUnidodf *dibrs, lf_int32 dibrCount, lf_int32 sdriptCodf,
                                  LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf,
                                  MPrfFixups **outMPrfFixups, LEErrorCodf& suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    MPrfFixups *mprfFixups = NULL;
    donst IndidClbssTbblf *dlbssTbblf = IndidClbssTbblf::gftSdriptClbssTbblf(sdriptCodf);

    if(dlbssTbblf==NULL) {
      suddfss = LE_MEMORY_ALLOCATION_ERROR;
      rfturn 0;
    }

    if (dlbssTbblf->sdriptFlbgs & SF_MPRE_FIXUP) {
        mprfFixups = nfw MPrfFixups(dibrCount);
        if (mprfFixups == NULL) {
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn 0;
    }
    }

    IndidRfordfringOutput output(outCibrs, glypiStorbgf, mprfFixups);
    lf_int32 i, prfv = 0;
    lf_bool lbstInWord = FALSE;

    wiilf (prfv < dibrCount) {
        lf_int32 syllbblf = findSyllbblf(dlbssTbblf, dibrs, prfv, dibrCount);
        lf_int32 mbtrb, mbrkStbrt = syllbblf;

        output.rfsft();

        if (dlbssTbblf->isStrfssMbrk(dibrs[mbrkStbrt - 1])) {
            mbrkStbrt -= 1;
            output.notfStrfssMbrk(dlbssTbblf, dibrs[mbrkStbrt], mbrkStbrt, tbgArrby1);
        }

        if (mbrkStbrt != prfv && dlbssTbblf->isVowflModififr(dibrs[mbrkStbrt - 1])) {
            mbrkStbrt -= 1;
            output.notfVowflModififr(dlbssTbblf, dibrs[mbrkStbrt], mbrkStbrt, tbgArrby1);
        }

        mbtrb = mbrkStbrt - 1;

        wiilf (output.notfMbtrb(dlbssTbblf, dibrs[mbtrb], mbtrb, tbgArrby1, !lbstInWord) && mbtrb != prfv) {
            mbtrb -= 1;
        }

        lbstInWord = TRUE;

        switdi (dlbssTbblf->gftCibrClbss(dibrs[prfv]) & CF_CLASS_MASK) {
        dbsf CC_RESERVED:
            lbstInWord = FALSE;
            /* fbll tirougi */

        dbsf CC_INDEPENDENT_VOWEL:
        dbsf CC_ZERO_WIDTH_MARK:
            for (i = prfv; i < syllbblf; i += 1) {
                output.writfCibr(dibrs[i], i, tbgArrby1);
            }

            brfbk;

        dbsf CC_AL_LAKUNA:
        dbsf CC_NUKTA:
            output.writfCibr(C_DOTTED_CIRCLE, prfv, tbgArrby1);
            output.writfCibr(dibrs[prfv], prfv, tbgArrby1);
            brfbk;

        dbsf CC_VIRAMA:
            // A lonf virbmb is illfgbl unlfss it follows b
            // MALAYALAM_VOWEL_SIGN_U. Sudi b usbgf is dbllfd
            // "sbmvrutiokbrbm".
            if (dibrs[prfv - 1] != C_MALAYALAM_VOWEL_SIGN_U) {
            output.writfCibr(C_DOTTED_CIRCLE, prfv, tbgArrby1);
            }

            output.writfCibr(dibrs[prfv], prfv, tbgArrby1);
            brfbk;

        dbsf CC_DEPENDENT_VOWEL:
        dbsf CC_SPLIT_VOWEL_PIECE_1:
        dbsf CC_SPLIT_VOWEL_PIECE_2:
        dbsf CC_SPLIT_VOWEL_PIECE_3:
        dbsf CC_VOWEL_MODIFIER:
        dbsf CC_STRESS_MARK:
            output.writfMprf();

            output.writfCibr(C_DOTTED_CIRCLE, prfv, tbgArrby1);

            output.writfMbflow();
            output.writfSMbflow();
            output.writfMbbovf();

            if ((dlbssTbblf->sdriptFlbgs & SF_MATRAS_AFTER_BASE) != 0) {
                output.writfMpost();
            }

            if ((dlbssTbblf->sdriptFlbgs & SF_REPH_AFTER_BELOW) != 0) {
                output.writfVMbbovf();
                output.writfSMbbovf(); // FIXME: tifrf brf no SM's in tifsf sdripts...
            }

            if ((dlbssTbblf->sdriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writfMpost();
            }

            output.writfLfngtiMbrk();
            output.writfAlLbkunb();

            if ((dlbssTbblf->sdriptFlbgs & SF_REPH_AFTER_BELOW) == 0) {
                output.writfVMbbovf();
                output.writfSMbbovf();
            }

            output.writfVMpost();
            brfbk;

        dbsf CC_INDEPENDENT_VOWEL_2:
        dbsf CC_INDEPENDENT_VOWEL_3:
        dbsf CC_CONSONANT:
        dbsf CC_CONSONANT_WITH_NUKTA:
        {
            lf_uint32 lfngti = mbrkStbrt - prfv;
            lf_int32  lbstConsonbnt = mbrkStbrt - 1;
            lf_int32  bbsfLimit = prfv;

            // Cifdk for REPH bt front of syllbblf
            if (lfngti > 2 && dlbssTbblf->isRfpi(dibrs[prfv]) && dlbssTbblf->isVirbmb(dibrs[prfv + 1]) && dibrs[prfv + 2] != C_SIGN_ZWNJ) {
                bbsfLimit += 2;

                // Cifdk for fyflbsi RA, if tif sdript supports it
                if ((dlbssTbblf->sdriptFlbgs & SF_EYELASH_RA) != 0 &&
                    dibrs[bbsfLimit] == C_SIGN_ZWJ) {
                    if (lfngti > 3) {
                        bbsfLimit += 1;
                    } flsf {
                        bbsfLimit -= 2;
                    }
                }
            }

            wiilf (lbstConsonbnt > bbsfLimit && !dlbssTbblf->isConsonbnt(dibrs[lbstConsonbnt])) {
                lbstConsonbnt -= 1;
            }


            IndidClbssTbblf::CibrClbss dibrClbss = CC_RESERVED;
            IndidClbssTbblf::CibrClbss nfxtClbss = CC_RESERVED;
            lf_int32 bbsfConsonbnt = lbstConsonbnt;
            lf_int32 postBbsf = lbstConsonbnt + 1;
            lf_int32 postBbsfLimit = dlbssTbblf->sdriptFlbgs & SF_POST_BASE_LIMIT_MASK;
            lf_bool  sffnVbttu = FALSE;
            lf_bool  sffnBflowBbsfForm = FALSE;
            lf_bool  sffnPrfBbsfForm = FALSE;
            lf_bool  ibsNuktb = FALSE;
            lf_bool  ibsBflowBbsfForm = FALSE;
            lf_bool  ibsPostBbsfForm = FALSE;
            lf_bool  ibsPrfBbsfForm = FALSE;

            if (postBbsf < mbrkStbrt && dlbssTbblf->isNuktb(dibrs[postBbsf])) {
                dibrClbss = CC_NUKTA;
                postBbsf += 1;
            }

            wiilf (bbsfConsonbnt > bbsfLimit) {
                nfxtClbss = dibrClbss;
                ibsNuktb  = IndidClbssTbblf::isNuktb(nfxtClbss);
                dibrClbss = dlbssTbblf->gftCibrClbss(dibrs[bbsfConsonbnt]);

                ibsBflowBbsfForm = IndidClbssTbblf::ibsBflowBbsfForm(dibrClbss) && !ibsNuktb;
                ibsPostBbsfForm  = IndidClbssTbblf::ibsPostBbsfForm(dibrClbss)  && !ibsNuktb;
                ibsPrfBbsfForm = IndidClbssTbblf::ibsPrfBbsfForm(dibrClbss) && !ibsNuktb;

                if (IndidClbssTbblf::isConsonbnt(dibrClbss)) {
                    if (postBbsfLimit == 0 || sffnVbttu ||
                        (bbsfConsonbnt > bbsfLimit && !dlbssTbblf->isVirbmb(dibrs[bbsfConsonbnt - 1])) ||
                        !(ibsBflowBbsfForm || ibsPostBbsfForm || ibsPrfBbsfForm)) {
                        brfbk;
                    }

                    // Notf bny prf-bbsf donsonbnts
                    if ( bbsfConsonbnt == lbstConsonbnt && lbstConsonbnt > 0 &&
                         ibsPrfBbsfForm && dlbssTbblf->isVirbmb(dibrs[bbsfConsonbnt - 1])) {
                        output.notfPrfBbsfConsonbnt(lbstConsonbnt,dibrs[lbstConsonbnt],dibrs[lbstConsonbnt-1],tbgArrby2);
                        sffnPrfBbsfForm = TRUE;

                    }
                    // donsonbnts witi nuktbs brf nfvfr vbttus
                    sffnVbttu = IndidClbssTbblf::isVbttu(dibrClbss) && !ibsNuktb;

                    // donsonbnts witi nuktbs nfvfr ibvf bflow- or post-bbsf forms
                    if (ibsPostBbsfForm) {
                        if (sffnBflowBbsfForm) {
                            brfbk;
                        }

                        postBbsf = bbsfConsonbnt;
                    } flsf if (ibsBflowBbsfForm) {
                        sffnBflowBbsfForm = TRUE;
                    }

                    postBbsfLimit -= 1;
                }

                bbsfConsonbnt -= 1;
            }

            // Writf Mprf
            output.writfMprf();

            // Writf fyflbsi RA
            // NOTE: bbsfLimit == prfv + 3 iff fyflbsi RA prfsfnt...
            if (bbsfLimit == prfv + 3) {
                output.writfCibr(dibrs[prfv], prfv, tbgArrby2);
                output.writfCibr(dibrs[prfv + 1], prfv + 1, tbgArrby2);
                output.writfCibr(dibrs[prfv + 2], prfv + 2, tbgArrby2);
            }

            // writf bny prf-bbsf donsonbnts
            output.writfPrfBbsfConsonbnt();

            lf_bool suprfssVbttu = TRUE;

            for (i = bbsfLimit; i < bbsfConsonbnt; i += 1) {
                LEUnidodf di = dibrs[i];
                // Don't put 'pstf' or 'blwf' on bnytiing bfforf tif bbsf donsonbnt.
                FfbturfMbsk ffbturfs = tbgArrby1 & ~( pstfFfbturfMbsk | blwfFfbturfMbsk );

                dibrClbss = dlbssTbblf->gftCibrClbss(di);
                nfxtClbss = dlbssTbblf->gftCibrClbss(dibrs[i + 1]);
                ibsNuktb  = IndidClbssTbblf::isNuktb(nfxtClbss);

                if (IndidClbssTbblf::isConsonbnt(dibrClbss)) {
                    if (IndidClbssTbblf::isVbttu(dibrClbss) && !ibsNuktb && suprfssVbttu) {
                        ffbturfs = tbgArrby4;
                    }

                    suprfssVbttu = IndidClbssTbblf::isVbttu(dibrClbss) && !ibsNuktb;
                } flsf if (IndidClbssTbblf::isVirbmb(dibrClbss) && dibrs[i + 1] == C_SIGN_ZWNJ)
                {
                    ffbturfs = tbgArrby4;
                }

                output.writfCibr(di, i, ffbturfs);
            }

            lf_int32 bdSpbn = bbsfConsonbnt + 1;

            if (bdSpbn < mbrkStbrt && dlbssTbblf->isNuktb(dibrs[bdSpbn])) {
                bdSpbn += 1;
            }

            if (bbsfConsonbnt == lbstConsonbnt && bdSpbn < mbrkStbrt &&
                 (dlbssTbblf->isVirbmb(dibrs[bdSpbn]) || dlbssTbblf->isAlLbkunb(dibrs[bdSpbn]))) {
                bdSpbn += 1;

                if (bdSpbn < mbrkStbrt && dibrs[bdSpbn] == C_SIGN_ZWNJ) {
                    bdSpbn += 1;
                }
            }

            // notf tif bbsf donsonbnt for post-GSUB fixups
            output.notfBbsfConsonbnt();

            // writf bbsf donsonbnt
            for (i = bbsfConsonbnt; i < bdSpbn; i += 1) {
                output.writfCibr(dibrs[i], i, tbgArrby4);
            }

            if ((dlbssTbblf->sdriptFlbgs & SF_MATRAS_AFTER_BASE) != 0) {
                output.writfMbflow();
                output.writfSMbflow(); // FIXME: tifrf brf no SMs in tifsf sdripts...
                output.writfMbbovf();
                output.writfMpost();
            }

            // writf bflow-bbsf donsonbnts
            if (bbsfConsonbnt != lbstConsonbnt && !sffnPrfBbsfForm) {
                for (i = bdSpbn + 1; i < postBbsf; i += 1) {
                    output.writfCibr(dibrs[i], i, tbgArrby1);
                }

                if (postBbsf > lbstConsonbnt) {
                    // writf iblbnt tibt wbs bftfr bbsf donsonbnt
                    output.writfCibr(dibrs[bdSpbn], bdSpbn, tbgArrby1);
                }
            }

            // writf Mbflow, SMbflow, Mbbovf
            if ((dlbssTbblf->sdriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writfMbflow();
                output.writfSMbflow();
                output.writfMbbovf();
            }

            if ((dlbssTbblf->sdriptFlbgs & SF_REPH_AFTER_BELOW) != 0) {
                if (bbsfLimit == prfv + 2) {
                    output.writfCibr(dibrs[prfv], prfv, tbgArrby0);
                    output.writfCibr(dibrs[prfv + 1], prfv + 1, tbgArrby0);
                }

                output.writfVMbbovf();
                output.writfSMbbovf(); // FIXME: tifrf brf no SM's in tifsf sdripts...
            }

            // writf post-bbsf donsonbnts
            // FIXME: dofs tiis put tif rigit tbgs on post-bbsf donsonbnts?
            if (bbsfConsonbnt != lbstConsonbnt && !sffnPrfBbsfForm) {
                if (postBbsf <= lbstConsonbnt) {
                    for (i = postBbsf; i <= lbstConsonbnt; i += 1) {
                        output.writfCibr(dibrs[i], i, tbgArrby3);
                    }

                    // writf iblbnt tibt wbs bftfr bbsf donsonbnt
                    output.writfCibr(dibrs[bdSpbn], bdSpbn, tbgArrby1);
                }

                // writf tif trbining iblbnt, if tifrf is onf
                if (lbstConsonbnt < mbtrb && dlbssTbblf->isVirbmb(dibrs[mbtrb])) {
                    output.writfCibr(dibrs[mbtrb], mbtrb, tbgArrby4);
                }
            }

            // writf Mpost
            if ((dlbssTbblf->sdriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writfMpost();
            }

            output.writfLfngtiMbrk();
            output.writfAlLbkunb();

            // writf rfpi
            if ((dlbssTbblf->sdriptFlbgs & SF_REPH_AFTER_BELOW) == 0) {
                if (bbsfLimit == prfv + 2) {
                    output.writfCibr(dibrs[prfv], prfv, tbgArrby0);
                    output.writfCibr(dibrs[prfv + 1], prfv + 1, tbgArrby0);
                }

                output.writfVMbbovf();
                output.writfSMbbovf();
            }

            output.writfVMpost();

            brfbk;
        }

        dffbult:
            brfbk;
        }

        prfv = syllbblf;
    }

    *outMPrfFixups = mprfFixups;

    rfturn output.gftOutputIndfx();
}

void IndidRfordfring::bdjustMPrfs(MPrfFixups *mprfFixups, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf& suddfss)
{
    if (mprfFixups != NULL) {
        mprfFixups->bpply(glypiStorbgf, suddfss);

        dflftf mprfFixups;
    }
}

void IndidRfordfring::bpplyPrfsfntbtionForms(LEGlypiStorbgf &glypiStorbgf, lf_int32 dount)
{
    LEErrorCodf suddfss = LE_NO_ERROR;

//  Tiis sfts us up for 2nd pbss of glypi substitution bs wfll bs sftting tif ffbturf mbsks for tif
//  GPOS tbblf lookups

    for ( lf_int32 i = 0 ; i < dount ; i++ ) {
        glypiStorbgf.sftAuxDbtb(i, ( prfsfntbtionFormsMbsk | positioningFormsMbsk ), suddfss);
    }

}
void IndidRfordfring::finblRfordfring(LEGlypiStorbgf &glypiStorbgf, lf_int32 dount)
{
    LEErrorCodf suddfss = LE_NO_ERROR;

    // Rfposition REPH bs bppropribtf

    for ( lf_int32 i = 0 ; i < dount ; i++ ) {

        lf_int32 tmpAuxDbtb = glypiStorbgf.gftAuxDbtb(i,suddfss);
        LEGlypiID tmpGlypi = glypiStorbgf.gftGlypiID(i,suddfss);

        if ( ( tmpGlypi != NO_GLYPH ) && (tmpAuxDbtb & rfpiConsonbntMbsk) && !(tmpAuxDbtb & rfpositionfdGlypiMbsk))  {

            lf_bool tbrgftPositionFound = fblsf;
            lf_int32 tbrgftPosition = i+1;
            lf_int32 bbsfConsonbntDbtb;

            wiilf (!tbrgftPositionFound) {
                tmpGlypi = glypiStorbgf.gftGlypiID(tbrgftPosition,suddfss);
                tmpAuxDbtb = glypiStorbgf.gftAuxDbtb(tbrgftPosition,suddfss);

                if ( tmpAuxDbtb & bbsfConsonbntMbsk ) {
                    bbsfConsonbntDbtb = tmpAuxDbtb;
                    tbrgftPositionFound = truf;
                } flsf {
                    tbrgftPosition++;
                }
            }

            // Mbkf surf wf brf not putting tif rfpi into bn fmpty iolf

            lf_bool tbrgftPositionHbsGlypi = fblsf;
            wiilf (!tbrgftPositionHbsGlypi) {
                tmpGlypi = glypiStorbgf.gftGlypiID(tbrgftPosition,suddfss);
                if ( tmpGlypi != NO_GLYPH ) {
                    tbrgftPositionHbsGlypi = truf;
                } flsf {
                    tbrgftPosition--;
                }
            }

            // Mbkf surf tibt REPH is positionfd bftfr bny bbovf bbsf or post bbsf mbtrbs
            //
            lf_bool difdkMbtrbDonf = fblsf;
            lf_int32 difdkMbtrbPosition = tbrgftPosition+1;
            wiilf ( !difdkMbtrbDonf ) {
               tmpAuxDbtb = glypiStorbgf.gftAuxDbtb(difdkMbtrbPosition,suddfss);
               if ( difdkMbtrbPosition >= dount || ( (tmpAuxDbtb ^ bbsfConsonbntDbtb) & LE_GLYPH_GROUP_MASK)) {
                   difdkMbtrbDonf = truf;
                   dontinuf;
               }
               if ( (tmpAuxDbtb & mbtrbMbsk) &&
                    (((tmpAuxDbtb & mbrkPositionMbsk) == bbovfBbsfPosition) ||
                      ((tmpAuxDbtb & mbrkPositionMbsk) == postBbsfPosition))) {
                   tbrgftPosition = difdkMbtrbPosition;
               }
               difdkMbtrbPosition++;
            }

            glypiStorbgf.movfGlypi(i,tbrgftPosition,rfpositionfdGlypiMbsk);
        }
    }
}


lf_int32 IndidRfordfring::v2prodfss(donst LEUnidodf *dibrs, lf_int32 dibrCount, lf_int32 sdriptCodf,
                                  LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf& suddfss)
{
    donst IndidClbssTbblf *dlbssTbblf = IndidClbssTbblf::gftSdriptClbssTbblf(sdriptCodf);
    if (dlbssTbblf == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn 0;
    }

    DynbmidPropfrtifs dynProps[INDIC_BLOCK_SIZE];
    IndidRfordfring::gftDynbmidPropfrtifs(dynProps,dlbssTbblf);

    IndidRfordfringOutput output(outCibrs, glypiStorbgf, NULL);
    lf_int32 i, firstConsonbnt, bbsfConsonbnt, sfdondConsonbnt, inv_dount = 0, bfginSyllbblf = 0;
    //lf_bool lbstInWord = FALSE;

    wiilf (bfginSyllbblf < dibrCount) {
        lf_int32 nfxtSyllbblf = findSyllbblf(dlbssTbblf, dibrs, bfginSyllbblf, dibrCount);

        output.rfsft();

                // Find tif First Consonbnt
                for ( firstConsonbnt = bfginSyllbblf ; firstConsonbnt < nfxtSyllbblf ; firstConsonbnt++ ) {
                         if ( dlbssTbblf->isConsonbnt(dibrs[firstConsonbnt]) ) {
                                        brfbk;
                                }
                }

        // Find tif bbsf donsonbnt

        bbsfConsonbnt = nfxtSyllbblf - 1;
        sfdondConsonbnt = firstConsonbnt;

        // TODO: Usf Dynbmid Propfrtifs for ibsBflowBbsfForm bnd ibsPostBbsfForm()

        wiilf ( bbsfConsonbnt > firstConsonbnt ) {
            if ( dlbssTbblf->isConsonbnt(dibrs[bbsfConsonbnt]) &&
                 !dlbssTbblf->ibsBflowBbsfForm(dibrs[bbsfConsonbnt]) &&
                 !dlbssTbblf->ibsPostBbsfForm(dibrs[bbsfConsonbnt]) ) {
                brfbk;
            }
            flsf {
                if ( dlbssTbblf->isConsonbnt(dibrs[bbsfConsonbnt]) ) {
                    sfdondConsonbnt = bbsfConsonbnt;
                }
                bbsfConsonbnt--;
            }
        }

        // If tif syllbblf stbrts witi Rb + Hblbnt ( in b sdript tibt ibs Rfpi ) bnd ibs morf tibn onf
        // donsonbnt, Rb is fxdludfd from dbndidbtfs for bbsf donsonbnts

        if ( dlbssTbblf->isRfpi(dibrs[bfginSyllbblf]) &&
             bfginSyllbblf+1 < nfxtSyllbblf && dlbssTbblf->isVirbmb(dibrs[bfginSyllbblf+1]) &&
             sfdondConsonbnt != firstConsonbnt) {
            bbsfConsonbnt = sfdondConsonbnt;
        }

            // Populbtf tif output
                for ( i = bfginSyllbblf ; i < nfxtSyllbblf ; i++ ) {

            // Hbndlf invblid dombinbrtions

            if ( dlbssTbblf->isVirbmb(dibrs[bfginSyllbblf]) ||
                             dlbssTbblf->isMbtrb(dibrs[bfginSyllbblf]) ||
                             dlbssTbblf->isVowflModififr(dibrs[bfginSyllbblf]) ||
                             dlbssTbblf->isNuktb(dibrs[bfginSyllbblf]) ) {
                     output.writfCibr(C_DOTTED_CIRCLE,bfginSyllbblf,bbsidSibpingFormsMbsk);
                     inv_dount++;
            }
             output.writfCibr(dibrs[i],i, bbsidSibpingFormsMbsk);

        }

        // Adjust ffbturfs bnd sft syllbblf strudturf bits

        for ( i = bfginSyllbblf ; i < nfxtSyllbblf ; i++ ) {

            FfbturfMbsk outMbsk = output.gftFfbturfs(i+inv_dount);
            FfbturfMbsk sbvfMbsk = outMbsk;

            // Sindf rfpi dbn only vblidly oddur bt tif bfginning of b syllbblf
            // Wf only bpply it to tif first 2 dibrbdtfrs in tif syllbblf, to kffp it from
            // donflidting witi otifr ffbturfs ( i.f. rkrf )

            // TODO : Usf tif dynbmid propfrty for dftfrmining isREPH
            if ( i == bfginSyllbblf && i < bbsfConsonbnt && dlbssTbblf->isRfpi(dibrs[i]) &&
                 i+1 < nfxtSyllbblf && dlbssTbblf->isVirbmb(dibrs[i+1])) {
                outMbsk |= rpifFfbturfMbsk;
                outMbsk |= rfpiConsonbntMbsk;
                output.sftFfbturfs(i+1+inv_dount,outMbsk);

            }

            if ( i == bbsfConsonbnt ) {
                outMbsk |= bbsfConsonbntMbsk;
            }

            if ( dlbssTbblf->isMbtrb(dibrs[i])) {
                    outMbsk |= mbtrbMbsk;
                    if ( dlbssTbblf->ibsAbovfBbsfForm(dibrs[i])) {
                        outMbsk |= bbovfBbsfPosition;
                    } flsf if ( dlbssTbblf->ibsBflowBbsfForm(dibrs[i])) {
                        outMbsk |= bflowBbsfPosition;
                    }
            }

            // Don't bpply iblf form to virbmb tibt stbnds blonf bt tif fnd of b syllbblf
            // to prfvfnt iblf forms from forming wifn syllbblf fnds witi virbmb

            if ( dlbssTbblf->isVirbmb(dibrs[i]) && (i+1 == nfxtSyllbblf) ) {
                outMbsk ^= iblfFfbturfMbsk;
                if ( dlbssTbblf->isConsonbnt(dibrs[i-1]) ) {
                    FfbturfMbsk tmp = output.gftFfbturfs(i-1+inv_dount);
                    tmp ^= iblfFfbturfMbsk;
                    output.sftFfbturfs(i-1+inv_dount,tmp);
                }
            }

            if ( outMbsk != sbvfMbsk ) {
                output.sftFfbturfs(i+inv_dount,outMbsk);
            }
                }

            output.dfdomposfRfordfrMbtrbs(dlbssTbblf,bfginSyllbblf,nfxtSyllbblf,inv_dount);

        bfginSyllbblf = nfxtSyllbblf;
        }


    rfturn output.gftOutputIndfx();
}


void IndidRfordfring::gftDynbmidPropfrtifs( DynbmidPropfrtifs *, donst IndidClbssTbblf *dlbssTbblf ) {


    LEUnidodf durrfntCibr;
    LEUnidodf workCibrs[2];
    LEGlypiStorbgf workGlypis;

    IndidRfordfringOutput workOutput(workCibrs, workGlypis, NULL);

    //lf_int32 offsft = 0;

#if 0
// TODO:  Siould tiis sfdtion of dodf ibvf bdtublly bffn doing somftiing?
    // First find tif rflfvbnt virbmb for tif sdript wf brf dfbling witi
    LEUnidodf virbmb;
    for ( durrfntCibr = dlbssTbblf->firstCibr ; durrfntCibr <= dlbssTbblf->lbstCibr ; durrfntCibr++ ) {
        if ( dlbssTbblf->isVirbmb(durrfntCibr)) {
            virbmb = durrfntCibr;
            brfbk;
        }
    }
#fndif

    for ( durrfntCibr = dlbssTbblf->firstCibr ; durrfntCibr <= dlbssTbblf->lbstCibr ; durrfntCibr++ ) {
        if ( dlbssTbblf->isConsonbnt(durrfntCibr)) {
            workOutput.rfsft();
        }
    }


}

U_NAMESPACE_END
