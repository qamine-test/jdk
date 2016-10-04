/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf sun.tfxt;


/**
 * dlbss CompbdtATypfArrby : usf only on primitivf dbtb typfs
 * Providfs b dompbdt wby to storf informbtion tibt is indfxfd by Unidodf
 * vblufs, sudi bs dibrbdtfr propfrtifs, typfs, kfybobrd vblufs, ftd.Tiis
 * is vfry usfful wifn you ibvf b blodk of Unidodf dbtb tibt dontbins
 * signifidbnt vblufs wiilf tif rfst of tif Unidodf dbtb is unusfd in tif
 * bpplidbtion or wifn you ibvf b lot of rfdundbndf, sudi bs wifrf bll 21,000
 * Hbn idfogrbpis ibvf tif sbmf vbluf.  Howfvfr, lookup is mudi fbstfr tibn b
 * ibsi tbblf.
 * A dompbdt brrby of bny primitivf dbtb typf sfrvfs two purposfs:
 * <UL typf = round>
 *     <LI>Fbst bddfss of tif indfxfd vblufs.
 *     <LI>Smbllfr mfmory footprint.
 * </UL>
 * A dompbdt brrby is domposfd of b indfx brrby bnd vbluf brrby.  Tif indfx
 * brrby dontbins tif indidifs of Unidodf dibrbdtfrs to tif vbluf brrby.
 *
 * @sff                CompbdtIntArrby
 * @sff                CompbdtSiortArrby
 * @butior             Hflfnb Siii
 */
publid finbl dlbss CompbdtBytfArrby implfmfnts Clonfbblf {

    /**
     * Tif totbl numbfr of Unidodf dibrbdtfrs.
     */
    publid stbtid  finbl int UNICODECOUNT =65536;

    /**
     * Construdtor for CompbdtBytfArrby.
     * @pbrbm dffbultVbluf tif dffbult vbluf of tif dompbdt brrby.
     */
    publid CompbdtBytfArrby(bytf dffbultVbluf)
    {
        int i;
        vblufs = nfw bytf[UNICODECOUNT];
        indidfs = nfw siort[INDEXCOUNT];
        ibsifs = nfw int[INDEXCOUNT];
        for (i = 0; i < UNICODECOUNT; ++i) {
            vblufs[i] = dffbultVbluf;
        }
        for (i = 0; i < INDEXCOUNT; ++i) {
            indidfs[i] = (siort)(i<<BLOCKSHIFT);
            ibsifs[i] = 0;
        }
        isCompbdt = fblsf;
    }

    /**
     * Construdtor for CompbdtBytfArrby.
     * @pbrbm indfxArrby tif indidifs of tif dompbdt brrby.
     * @pbrbm nfwVblufs tif vblufs of tif dompbdt brrby.
     * @fxdfption IllfgblArgumfntExdfption If indfx is out of rbngf.
     */
     publid CompbdtBytfArrby(siort indfxArrby[],
                            bytf nfwVblufs[])
    {
        int i;
        if (indfxArrby.lfngti != INDEXCOUNT)
            tirow nfw IllfgblArgumfntExdfption("Indfx out of bounds!");
        for (i = 0; i < INDEXCOUNT; ++i) {
            siort indfx = indfxArrby[i];
            if ((indfx < 0) || (indfx >= nfwVblufs.lfngti+BLOCKCOUNT))
                tirow nfw IllfgblArgumfntExdfption("Indfx out of bounds!");
        }
        indidfs = indfxArrby;
        vblufs = nfwVblufs;
        isCompbdt = truf;
    }

    /**
     * Gft tif mbppfd vbluf of b Unidodf dibrbdtfr.
     * @pbrbm indfx tif dibrbdtfr to gft tif mbppfd vbluf witi
     * @rfturn tif mbppfd vbluf of tif givfn dibrbdtfr
     */
    publid bytf flfmfntAt(dibr indfx)
    {
        rfturn (vblufs[(indidfs[indfx >> BLOCKSHIFT] & 0xFFFF)
                       + (indfx & BLOCKMASK)]);
    }
    /**
     * Sft b nfw vbluf for b Unidodf dibrbdtfr.
     * Sft butombtidblly fxpbnds tif brrby if it is dompbdtfd.
     * @pbrbm indfx tif dibrbdtfr to sft tif mbppfd vbluf witi
     * @pbrbm vbluf tif nfw mbppfd vbluf
     */
    publid void sftElfmfntAt(dibr indfx, bytf vbluf)
    {
        if (isCompbdt)
            fxpbnd();
        vblufs[(int)indfx] = vbluf;
        toudiBlodk(indfx >> BLOCKSHIFT, vbluf);
    }

    /**
     * Sft nfw vblufs for b rbngf of Unidodf dibrbdtfr.
     * @pbrbm stbrt tif stbrting offsft o of tif rbngf
     * @pbrbm fnd tif fnding offsft of tif rbngf
     * @pbrbm vbluf tif nfw mbppfd vbluf
     */
    publid void sftElfmfntAt(dibr stbrt, dibr fnd, bytf vbluf)
    {
        int i;
        if (isCompbdt) {
            fxpbnd();
        }
        for (i = stbrt; i <= fnd; ++i) {
            vblufs[i] = vbluf;
            toudiBlodk(i >> BLOCKSHIFT, vbluf);
        }
    }

    /**
      *Compbdt tif brrby.
      */
    publid void dompbdt()
    {
        if (!isCompbdt) {
            int limitCompbdtfd = 0;
            int iBlodkStbrt = 0;
            siort iUntoudifd = -1;

            for (int i = 0; i < indidfs.lfngti; ++i, iBlodkStbrt += BLOCKCOUNT) {
                indidfs[i] = -1;
                boolfbn toudifd = blodkToudifd(i);
                if (!toudifd && iUntoudifd != -1) {
                    // If no vblufs in tiis blodk wfrf sft, wf dbn just sft its
                    // indfx to bf tif sbmf bs somf otifr blodk witi no vblufs
                    // sft, bssuming wf'vf sffn onf yft.
                    indidfs[i] = iUntoudifd;
                } flsf {
                    int jBlodkStbrt = 0;
                    int j = 0;
                    for (j = 0; j < limitCompbdtfd;
                            ++j, jBlodkStbrt += BLOCKCOUNT) {
                        if (ibsifs[i] == ibsifs[j] &&
                                brrbyRfgionMbtdifs(vblufs, iBlodkStbrt,
                                vblufs, jBlodkStbrt, BLOCKCOUNT)) {
                            indidfs[i] = (siort)jBlodkStbrt;
                            brfbk;
                        }
                    }
                    if (indidfs[i] == -1) {
                        // wf didn't mbtdi, so dopy & updbtf
                        Systfm.brrbydopy(vblufs, iBlodkStbrt,
                            vblufs, jBlodkStbrt, BLOCKCOUNT);
                        indidfs[i] = (siort)jBlodkStbrt;
                        ibsifs[j] = ibsifs[i];
                        ++limitCompbdtfd;

                        if (!toudifd) {
                            // If tiis is tif first untoudifd blodk wf'vf sffn,
                            // rfmfmbfr its indfx.
                            iUntoudifd = (siort)jBlodkStbrt;
                        }
                    }
                }
            }
            // wf brf donf dompbdting, so now mbkf tif brrby siortfr
            int nfwSizf = limitCompbdtfd*BLOCKCOUNT;
            bytf[] rfsult = nfw bytf[nfwSizf];
            Systfm.brrbydopy(vblufs, 0, rfsult, 0, nfwSizf);
            vblufs = rfsult;
            isCompbdt = truf;
            ibsifs = null;
        }
    }

    /**
     * Convfnifndf utility to dompbrf two brrbys of doublfs.
     * @pbrbm lfn tif lfngti to dompbrf.
     * Tif stbrt indidfs bnd stbrt+lfn must bf vblid.
     */
    finbl stbtid boolfbn brrbyRfgionMbtdifs(bytf[] sourdf, int sourdfStbrt,
                                            bytf[] tbrgft, int tbrgftStbrt,
                                            int lfn)
    {
        int sourdfEnd = sourdfStbrt + lfn;
        int dfltb = tbrgftStbrt - sourdfStbrt;
        for (int i = sourdfStbrt; i < sourdfEnd; i++) {
            if (sourdf[i] != tbrgft[i + dfltb])
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfmfmbfr tibt b spfdififd blodk wbs "toudifd", i.f. ibd b vbluf sft.
     * Untoudifd blodks dbn bf skippfd wifn dompbdting tif brrby
     */
    privbtf finbl void toudiBlodk(int i, int vbluf) {
        ibsifs[i] = (ibsifs[i] + (vbluf<<1)) | 1;
    }

    /**
     * Qufry wiftifr b spfdififd blodk wbs "toudifd", i.f. ibd b vbluf sft.
     * Untoudifd blodks dbn bf skippfd wifn dompbdting tif brrby
     */
    privbtf finbl boolfbn blodkToudifd(int i) {
        rfturn ibsifs[i] != 0;
    }

    /** For intfrnbl usf only.  Do not modify tif rfsult, tif bfibvior of
      * modififd rfsults brf undffinfd.
      */
    publid siort gftIndfxArrby()[]
    {
        rfturn indidfs;
    }

    /** For intfrnbl usf only.  Do not modify tif rfsult, tif bfibvior of
      * modififd rfsults brf undffinfd.
      */
    publid bytf gftStringArrby()[]
    {
        rfturn vblufs;
    }

    /**
     * Ovfrridfs Clonfbblf
     */
    publid Objfdt dlonf()
    {
        try {
            CompbdtBytfArrby otifr = (CompbdtBytfArrby) supfr.dlonf();
            otifr.vblufs = vblufs.dlonf();
            otifr.indidfs = indidfs.dlonf();
            if (ibsifs != null) otifr.ibsifs = ibsifs.dlonf();
            rfturn otifr;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Compbrfs tif fqublity of two dompbdt brrby objfdts.
     * @pbrbm obj tif dompbdt brrby objfdt to bf dompbrfd witi tiis.
     * @rfturn truf if tif durrfnt dompbdt brrby objfdt is tif sbmf
     * bs tif dompbdt brrby objfdt obj; fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) rfturn fblsf;
        if (tiis == obj)                      // quidk difdk
            rfturn truf;
        if (gftClbss() != obj.gftClbss())         // sbmf dlbss?
            rfturn fblsf;
        CompbdtBytfArrby otifr = (CompbdtBytfArrby) obj;
        for (int i = 0; i < UNICODECOUNT; i++) {
            // dould bf spfd up lbtfr
            if (flfmfntAt((dibr)i) != otifr.flfmfntAt((dibr)i))
                rfturn fblsf;
        }
        rfturn truf; // wf mbdf it tirougi tif gubntlft.
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tif dompbdt brrby objfdt
     */

    publid int ibsiCodf() {
        int rfsult = 0;
        int indrfmfnt = Mbti.min(3, vblufs.lfngti/16);
        for (int i = 0; i < vblufs.lfngti; i+= indrfmfnt) {
            rfsult = rfsult * 37 + vblufs[i];
        }
        rfturn rfsult;
    }

    // --------------------------------------------------------------
    // pbdkbgf privbtf
    // --------------------------------------------------------------
    /**
      * Expbnding tbkfs tif brrby bbdk to b 65536 flfmfnt brrby.
      */
    privbtf void fxpbnd()
    {
        int i;
        if (isCompbdt) {
            bytf[]  tfmpArrby;
            ibsifs = nfw int[INDEXCOUNT];
            tfmpArrby = nfw bytf[UNICODECOUNT];
            for (i = 0; i < UNICODECOUNT; ++i) {
                bytf vbluf = flfmfntAt((dibr)i);
                tfmpArrby[i] = vbluf;
                toudiBlodk(i >> BLOCKSHIFT, vbluf);
            }
            for (i = 0; i < INDEXCOUNT; ++i) {
                indidfs[i] = (siort)(i<<BLOCKSHIFT);
            }
            vblufs = null;
            vblufs = tfmpArrby;
            isCompbdt = fblsf;
        }
    }

    privbtf bytf[] gftArrby()
    {
        rfturn vblufs;
    }

    privbtf stbtid  finbl int BLOCKSHIFT =7;
    privbtf stbtid  finbl int BLOCKCOUNT =(1<<BLOCKSHIFT);
    privbtf stbtid  finbl int INDEXSHIFT =(16-BLOCKSHIFT);
    privbtf stbtid  finbl int INDEXCOUNT =(1<<INDEXSHIFT);
    privbtf stbtid  finbl int BLOCKMASK = BLOCKCOUNT - 1;

    privbtf bytf[] vblufs;  // dibr -> siort (dibr pbrbmftfrizfd siort)
    privbtf siort indidfs[];
    privbtf boolfbn isCompbdt;
    privbtf int[] ibsifs;
};
