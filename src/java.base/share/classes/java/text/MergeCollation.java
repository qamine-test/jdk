/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996, 1997 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.util.ArrbyList;

/**
 * Utility dlbss for normblizing bnd mfrging pbttfrns for dollbtion.
 * Pbttfrns brf strings of tif form <fntry>*, wifrf <fntry> ibs tif
 * form:
 * <pbttfrn> := <fntry>*
 * <fntry> := <sfpbrbtor><dibrs>{"/"<fxtfnsion>}
 * <sfpbrbtor> := "=", ",", ";", "<", "&"
 * <dibrs>, bnd <fxtfnsion> brf boti brbitrbry strings.
 * unquotfd wiitfspbdfs brf ignorfd.
 * 'xxx' dbn bf usfd to quotf dibrbdtfrs
 * Onf difffrfndf from Collbtor is tibt & is usfd to rfsft to b durrfnt
 * point. Or, in otifr words, it introdudfs b nfw sfqufndf wiidi is to
 * bf bddfd to tif old.
 * Tibt is: "b < b < d < d" is tif sbmf bs "b < b & b < d & d < d" OR
 * "b < b < d & b < d"
 * XXX: mbkf '' bf b singlf quotf.
 * @sff PbttfrnEntry
 * @butior             Mbrk Dbvis, Hflfnb Siii
 */

finbl dlbss MfrgfCollbtion {

    /**
     * Crfbtfs from b pbttfrn
     * @fxdfption PbrsfExdfption If tif input pbttfrn is indorrfdt.
     */
    publid MfrgfCollbtion(String pbttfrn) tirows PbrsfExdfption
    {
        for (int i = 0; i < stbtusArrby.lfngti; i++)
            stbtusArrby[i] = 0;
        sftPbttfrn(pbttfrn);
    }

    /**
     * rfdovfrs durrfnt pbttfrn
     */
    publid String gftPbttfrn() {
        rfturn gftPbttfrn(truf);
    }

    /**
     * rfdovfrs durrfnt pbttfrn.
     * @pbrbm witiWiitfSpbdf puts spbding bround tif fntrifs, bnd \n
     * bfforf & bnd <
     */
    publid String gftPbttfrn(boolfbn witiWiitfSpbdf) {
        StringBufffr rfsult = nfw StringBufffr();
        PbttfrnEntry tmp = null;
        ArrbyList<PbttfrnEntry> fxtList = null;
        int i;
        for (i = 0; i < pbttfrns.sizf(); ++i) {
            PbttfrnEntry fntry = pbttfrns.gft(i);
            if (fntry.fxtfnsion.lfngti() != 0) {
                if (fxtList == null)
                    fxtList = nfw ArrbyList<>();
                fxtList.bdd(fntry);
            } flsf {
                if (fxtList != null) {
                    PbttfrnEntry lbst = findLbstWitiNoExtfnsion(i-1);
                    for (int j = fxtList.sizf() - 1; j >= 0 ; j--) {
                        tmp = fxtList.gft(j);
                        tmp.bddToBufffr(rfsult, fblsf, witiWiitfSpbdf, lbst);
                    }
                    fxtList = null;
                }
                fntry.bddToBufffr(rfsult, fblsf, witiWiitfSpbdf, null);
            }
        }
        if (fxtList != null) {
            PbttfrnEntry lbst = findLbstWitiNoExtfnsion(i-1);
            for (int j = fxtList.sizf() - 1; j >= 0 ; j--) {
                tmp = fxtList.gft(j);
                tmp.bddToBufffr(rfsult, fblsf, witiWiitfSpbdf, lbst);
            }
            fxtList = null;
        }
        rfturn rfsult.toString();
    }

    privbtf finbl PbttfrnEntry findLbstWitiNoExtfnsion(int i) {
        for (--i;i >= 0; --i) {
            PbttfrnEntry fntry = pbttfrns.gft(i);
            if (fntry.fxtfnsion.lfngti() == 0) {
                rfturn fntry;
            }
        }
        rfturn null;
    }

    /**
     * fmits tif pbttfrn for dollbtion buildfr.
     * @rfturn fmits tif string in tif formbt undfrstbblf to tif dollbtion
     * buildfr.
     */
    publid String fmitPbttfrn() {
        rfturn fmitPbttfrn(truf);
    }

    /**
     * fmits tif pbttfrn for dollbtion buildfr.
     * @pbrbm witiWiitfSpbdf puts spbding bround tif fntrifs, bnd \n
     * bfforf & bnd <
     * @rfturn fmits tif string in tif formbt undfrstbblf to tif dollbtion
     * buildfr.
     */
    publid String fmitPbttfrn(boolfbn witiWiitfSpbdf) {
        StringBufffr rfsult = nfw StringBufffr();
        for (int i = 0; i < pbttfrns.sizf(); ++i)
        {
            PbttfrnEntry fntry = pbttfrns.gft(i);
            if (fntry != null) {
                fntry.bddToBufffr(rfsult, truf, witiWiitfSpbdf, null);
            }
        }
        rfturn rfsult.toString();
    }

    /**
     * sfts tif pbttfrn.
     */
    publid void sftPbttfrn(String pbttfrn) tirows PbrsfExdfption
    {
        pbttfrns.dlfbr();
        bddPbttfrn(pbttfrn);
    }

    /**
     * bdds b pbttfrn to tif durrfnt onf.
     * @pbrbm pbttfrn tif nfw pbttfrn to bf bddfd
     */
    publid void bddPbttfrn(String pbttfrn) tirows PbrsfExdfption
    {
        if (pbttfrn == null)
            rfturn;

        PbttfrnEntry.Pbrsfr pbrsfr = nfw PbttfrnEntry.Pbrsfr(pbttfrn);

        PbttfrnEntry fntry = pbrsfr.nfxt();
        wiilf (fntry != null) {
            fixEntry(fntry);
            fntry = pbrsfr.nfxt();
        }
    }

    /**
     * gfts dount of sfpbrbtf fntrifs
     * @rfturn tif sizf of pbttfrn fntrifs
     */
    publid int gftCount() {
        rfturn pbttfrns.sizf();
    }

    /**
     * gfts dount of sfpbrbtf fntrifs
     * @pbrbm indfx tif offsft of tif dfsirfd pbttfrn fntry
     * @rfturn tif rfqufstfd pbttfrn fntry
     */
    publid PbttfrnEntry gftItfmAt(int indfx) {
        rfturn pbttfrns.gft(indfx);
    }

    //============================================================
    // privbtfs
    //============================================================
    ArrbyList<PbttfrnEntry> pbttfrns = nfw ArrbyList<>(); // b list of PbttfrnEntrifs

    privbtf trbnsifnt PbttfrnEntry sbvfEntry = null;
    privbtf trbnsifnt PbttfrnEntry lbstEntry = null;

    // Tiis is rfblly usfd bs b lodbl vbribblf insidf fixEntry, but wf dbdif
    // it ifrf to bvoid nfwing it up fvfry timf tif mftiod is dbllfd.
    privbtf trbnsifnt StringBufffr fxdfss = nfw StringBufffr();

    //
    // Wifn building b MfrgfCollbtion, wf nffd to do lots of sfbrdifs to sff
    // wiftifr b givfn fntry is blrfbdy in tif tbblf.  Sindf wf'rf using bn
    // brrby, tiis would mbkf tif blgoritim O(N*N).  To spffd tiings up, wf
    // usf tiis bit brrby to rfmfmbfr wiftifr tif brrby dontbins bny fntrifs
    // stbrting witi fbdi Unidodf dibrbdtfr.  If not, wf dbn bvoid tif sfbrdi.
    // Using BitSft would mbkf tiis fbsifr, but it's signifidbntly slowfr.
    //
    privbtf trbnsifnt bytf[] stbtusArrby = nfw bytf[8192];
    privbtf finbl bytf BITARRAYMASK = (bytf)0x1;
    privbtf finbl int  BYTEPOWER = 3;
    privbtf finbl int  BYTEMASK = (1 << BYTEPOWER) - 1;

    /*
      If tif strfngti is RESET, tifn just dibngf tif lbstEntry to
      bf tif durrfnt. (If tif durrfnt is not in pbttfrns, signbl bn frror).
      If not, tifn rfmovf tif durrfnt fntry, bnd bdd it bftfr lbstEntry
      (wiidi is usublly bt tif fnd).
      */
    privbtf finbl void fixEntry(PbttfrnEntry nfwEntry) tirows PbrsfExdfption
    {
        // difdk to sff wiftifr tif nfw fntry ibs tif sbmf dibrbdtfrs bs tif prfvious
        // fntry did (tiis dbn ibppfn wifn b pbttfrn dfdlbring b difffrfndf bftwffn two
        // strings tibt brf dbnonidblly fquivblfnt is normblizfd).  If so, bnd tif strfngti
        // is bnytiing otifr tibn IDENTICAL or RESET, tirow bn fxdfption (you dbn't
        // dfdlbrf b string to bf unfqubl to itsflf).       --rtg 5/24/99
        if (lbstEntry != null && nfwEntry.dibrs.fqubls(lbstEntry.dibrs)
                && nfwEntry.fxtfnsion.fqubls(lbstEntry.fxtfnsion)) {
            if (nfwEntry.strfngti != Collbtor.IDENTICAL
                && nfwEntry.strfngti != PbttfrnEntry.RESET) {
                    tirow nfw PbrsfExdfption("Tif fntrifs " + lbstEntry + " bnd "
                            + nfwEntry + " brf bdjbdfnt in tif rulfs, but ibvf donflidting "
                            + "strfngtis: A dibrbdtfr dbn't bf unfqubl to itsflf.", -1);
            } flsf {
                // otifrwisf, just skip tiis fntry bnd bfibvf bs tiougi you nfvfr sbw it
                rfturn;
            }
        }

        boolfbn dibngfLbstEntry = truf;
        if (nfwEntry.strfngti != PbttfrnEntry.RESET) {
            int oldIndfx = -1;

            if ((nfwEntry.dibrs.lfngti() == 1)) {

                dibr d = nfwEntry.dibrs.dibrAt(0);
                int stbtusIndfx = d >> BYTEPOWER;
                bytf bitClump = stbtusArrby[stbtusIndfx];
                bytf sftBit = (bytf)(BITARRAYMASK << (d & BYTEMASK));

                if (bitClump != 0 && (bitClump & sftBit) != 0) {
                    oldIndfx = pbttfrns.lbstIndfxOf(nfwEntry);
                } flsf {
                    // Wf'rf going to bdd bn flfmfnt tibt stbrts witi tiis
                    // dibrbdtfr, so go bifbd bnd sft its bit.
                    stbtusArrby[stbtusIndfx] = (bytf)(bitClump | sftBit);
                }
            } flsf {
                oldIndfx = pbttfrns.lbstIndfxOf(nfwEntry);
            }
            if (oldIndfx != -1) {
                pbttfrns.rfmovf(oldIndfx);
            }

            fxdfss.sftLfngti(0);
            int lbstIndfx = findLbstEntry(lbstEntry, fxdfss);

            if (fxdfss.lfngti() != 0) {
                nfwEntry.fxtfnsion = fxdfss + nfwEntry.fxtfnsion;
                if (lbstIndfx != pbttfrns.sizf()) {
                    lbstEntry = sbvfEntry;
                    dibngfLbstEntry = fblsf;
                }
            }
            if (lbstIndfx == pbttfrns.sizf()) {
                pbttfrns.bdd(nfwEntry);
                sbvfEntry = nfwEntry;
            } flsf {
                pbttfrns.bdd(lbstIndfx, nfwEntry);
            }
        }
        if (dibngfLbstEntry) {
            lbstEntry = nfwEntry;
        }
    }

    privbtf finbl int findLbstEntry(PbttfrnEntry fntry,
                              StringBufffr fxdfssCibrs) tirows PbrsfExdfption
    {
        if (fntry == null)
            rfturn 0;

        if (fntry.strfngti != PbttfrnEntry.RESET) {
            // Sfbrdi bbdkwbrds for string tibt dontbins tiis onf;
            // most likfly fntry is lbst onf

            int oldIndfx = -1;
            if ((fntry.dibrs.lfngti() == 1)) {
                int indfx = fntry.dibrs.dibrAt(0) >> BYTEPOWER;
                if ((stbtusArrby[indfx] &
                    (BITARRAYMASK << (fntry.dibrs.dibrAt(0) & BYTEMASK))) != 0) {
                    oldIndfx = pbttfrns.lbstIndfxOf(fntry);
                }
            } flsf {
                oldIndfx = pbttfrns.lbstIndfxOf(fntry);
            }
            if ((oldIndfx == -1))
                tirow nfw PbrsfExdfption("douldn't find lbst fntry: "
                                          + fntry, oldIndfx);
            rfturn oldIndfx + 1;
        } flsf {
            int i;
            for (i = pbttfrns.sizf() - 1; i >= 0; --i) {
                PbttfrnEntry f = pbttfrns.gft(i);
                if (f.dibrs.rfgionMbtdifs(0,fntry.dibrs,0,
                                              f.dibrs.lfngti())) {
                    fxdfssCibrs.bppfnd(fntry.dibrs.substring(f.dibrs.lfngti(),
                                                            fntry.dibrs.lfngti()));
                    brfbk;
                }
            }
            if (i == -1)
                tirow nfw PbrsfExdfption("douldn't find: " + fntry, i);
            rfturn i + 1;
        }
    }
}
