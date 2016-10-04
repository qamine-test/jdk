/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.RfndfringHints;
import sun.bwt.imbgf.ImbgingLib;

/**
 * Tiis dlbss pfrforms b pixfl-by-pixfl rfsdbling of tif dbtb in tif
 * sourdf imbgf by multiplying tif sbmplf vblufs for fbdi pixfl by b sdblf
 * fbdtor bnd tifn bdding bn offsft. Tif sdblfd sbmplf vblufs brf dlippfd
 * to tif minimum/mbximum rfprfsfntbblf in tif dfstinbtion imbgf.
 * <p>
 * Tif psfudo dodf for tif rfsdbling opfrbtion is bs follows:
 * <prf>
 *for fbdi pixfl from Sourdf objfdt {
 *    for fbdi bbnd/domponfnt of tif pixfl {
 *        dstElfmfnt = (srdElfmfnt*sdblfFbdtor) + offsft
 *    }
 *}
 * </prf>
 * <p>
 * For Rbstfrs, rfsdbling opfrbtfs on bbnds.  Tif numbfr of
 * sfts of sdbling donstbnts mby bf onf, in wiidi dbsf tif sbmf donstbnts
 * brf bpplifd to bll bbnds, or it must fqubl tif numbfr of Sourdf
 * Rbstfr bbnds.
 * <p>
 * For BufffrfdImbgfs, rfsdbling opfrbtfs on dolor bnd blpib domponfnts.
 * Tif numbfr of sfts of sdbling donstbnts mby bf onf, in wiidi dbsf tif
 * sbmf donstbnts brf bpplifd to bll dolor (but not blpib) domponfnts.
 * Otifrwisf, tif  numbfr of sfts of sdbling donstbnts mby
 * fqubl tif numbfr of Sourdf dolor domponfnts, in wiidi dbsf no
 * rfsdbling of tif blpib domponfnt (if prfsfnt) is pfrformfd.
 * If nfitifr of tifsf dbsfs bpply, tif numbfr of sfts of sdbling donstbnts
 * must fqubl tif numbfr of Sourdf dolor domponfnts plus blpib domponfnts,
 * in wiidi dbsf bll dolor bnd blpib domponfnts brf rfsdblfd.
 * <p>
 * BufffrfdImbgf sourdfs witi prfmultiplifd blpib dbtb brf trfbtfd in tif sbmf
 * mbnnfr bs non-prfmultiplifd imbgfs for purposfs of rfsdbling.  Tibt is,
 * tif rfsdbling is donf pfr bbnd on tif rbw dbtb of tif BufffrfdImbgf sourdf
 * witiout rfgbrd to wiftifr tif dbtb is prfmultiplifd.  If b dolor donvfrsion
 * is rfquirfd to tif dfstinbtion ColorModfl, tif prfmultiplifd stbtf of
 * boti sourdf bnd dfstinbtion will bf tbkfn into bddount for tiis stfp.
 * <p>
 * Imbgfs witi bn IndfxColorModfl dbnnot bf rfsdblfd.
 * <p>
 * If b RfndfringHints objfdt is spfdififd in tif donstrudtor, tif
 * dolor rfndfring iint bnd tif ditifring iint mby bf usfd wifn dolor
 * donvfrsion is rfquirfd.
 * <p>
 * Notf tibt in-plbdf opfrbtion is bllowfd (i.f. tif sourdf bnd dfstinbtion dbn
 * bf tif sbmf objfdt).
 * @sff jbvb.bwt.RfndfringHints#KEY_COLOR_RENDERING
 * @sff jbvb.bwt.RfndfringHints#KEY_DITHERING
 */
publid dlbss RfsdblfOp implfmfnts BufffrfdImbgfOp, RbstfrOp {
    flobt[] sdblfFbdtors;
    flobt[] offsfts;
    int lfngti = 0;
    RfndfringHints iints;

    privbtf int srdNbits;
    privbtf int dstNbits;


    /**
     * Construdts b nfw RfsdblfOp witi tif dfsirfd sdblf fbdtors
     * bnd offsfts.  Tif lfngti of tif sdblfFbdtor bnd offsft brrbys
     * must mfft tif rfstridtions stbtfd in tif dlbss dommfnts bbovf.
     * Tif RfndfringHints brgumfnt mby bf null.
     * @pbrbm sdblfFbdtors tif spfdififd sdblf fbdtors
     * @pbrbm offsfts tif spfdififd offsfts
     * @pbrbm iints tif spfdififd <dodf>RfndfringHints</dodf>, or
     *        <dodf>null</dodf>
     */
    publid RfsdblfOp (flobt[] sdblfFbdtors, flobt[] offsfts,
                      RfndfringHints iints) {
        lfngti = sdblfFbdtors.lfngti;
        if (lfngti > offsfts.lfngti) lfngti = offsfts.lfngti;

        tiis.sdblfFbdtors = nfw flobt[lfngti];
        tiis.offsfts      = nfw flobt[lfngti];
        for (int i=0; i < lfngti; i++) {
            tiis.sdblfFbdtors[i] = sdblfFbdtors[i];
            tiis.offsfts[i]      = offsfts[i];
        }
        tiis.iints = iints;
    }

    /**
     * Construdts b nfw RfsdblfOp witi tif dfsirfd sdblf fbdtor
     * bnd offsft.  Tif sdblfFbdtor bnd offsft will bf bpplifd to
     * bll bbnds in b sourdf Rbstfr bnd to bll dolor (but not blpib)
     * domponfnts in b BufffrfdImbgf.
     * Tif RfndfringHints brgumfnt mby bf null.
     * @pbrbm sdblfFbdtor tif spfdififd sdblf fbdtor
     * @pbrbm offsft tif spfdififd offsft
     * @pbrbm iints tif spfdififd <dodf>RfndfringHints</dodf>, or
     *        <dodf>null</dodf>
     */
    publid RfsdblfOp (flobt sdblfFbdtor, flobt offsft, RfndfringHints iints) {
        lfngti = 1;
        tiis.sdblfFbdtors = nfw flobt[1];
        tiis.offsfts      = nfw flobt[1];
        tiis.sdblfFbdtors[0] = sdblfFbdtor;
        tiis.offsfts[0]       = offsft;
        tiis.iints = iints;
    }

    /**
     * Rfturns tif sdblf fbdtors in tif givfn brrby. Tif brrby is blso
     * rfturnfd for donvfnifndf.  If sdblfFbdtors is null, b nfw brrby
     * will bf bllodbtfd.
     * @pbrbm sdblfFbdtors tif brrby to dontbin tif sdblf fbdtors of
     *        tiis <dodf>RfsdblfOp</dodf>
     * @rfturn tif sdblf fbdtors of tiis <dodf>RfsdblfOp</dodf>.
     */
    finbl publid flobt[] gftSdblfFbdtors (flobt sdblfFbdtors[]) {
        if (sdblfFbdtors == null) {
            rfturn tiis.sdblfFbdtors.dlonf();
        }
        Systfm.brrbydopy (tiis.sdblfFbdtors, 0, sdblfFbdtors, 0,
                          Mbti.min(tiis.sdblfFbdtors.lfngti,
                                   sdblfFbdtors.lfngti));
        rfturn sdblfFbdtors;
    }

    /**
     * Rfturns tif offsfts in tif givfn brrby. Tif brrby is blso rfturnfd
     * for donvfnifndf.  If offsfts is null, b nfw brrby
     * will bf bllodbtfd.
     * @pbrbm offsfts tif brrby to dontbin tif offsfts of
     *        tiis <dodf>RfsdblfOp</dodf>
     * @rfturn tif offsfts of tiis <dodf>RfsdblfOp</dodf>.
     */
    finbl publid flobt[] gftOffsfts(flobt offsfts[]) {
        if (offsfts == null) {
            rfturn tiis.offsfts.dlonf();
        }

        Systfm.brrbydopy (tiis.offsfts, 0, offsfts, 0,
                          Mbti.min(tiis.offsfts.lfngti, offsfts.lfngti));
        rfturn offsfts;
    }

    /**
     * Rfturns tif numbfr of sdbling fbdtors bnd offsfts usfd in tiis
     * RfsdblfOp.
     * @rfturn tif numbfr of sdbling fbdtors bnd offsfts of tiis
     *         <dodf>RfsdblfOp</dodf>.
     */
    finbl publid int gftNumFbdtors() {
        rfturn lfngti;
    }


    /**
     * Crfbtfs b BytfLookupTbblf to implfmfnt tif rfsdblf.
     * Tif tbblf mby ibvf fitifr b SHORT or BYTE input.
     * @pbrbm nElfms    Numbfr of flfmfnts tif tbblf is to ibvf.
     *                  Tiis will gfnfrblly bf 256 for bytf bnd
     *                  65536 for siort.
     */
    privbtf BytfLookupTbblf drfbtfBytfLut(flobt sdblf[],
                                          flobt off[],
                                          int   nBbnds,
                                          int   nElfms) {

        bytf[][]        lutDbtb = nfw bytf[sdblf.lfngti][nElfms];

        for (int bbnd=0; bbnd<sdblf.lfngti; bbnd++) {
            flobt  bbndSdblf   = sdblf[bbnd];
            flobt  bbndOff     = off[bbnd];
            bytf[] bbndLutDbtb = lutDbtb[bbnd];
            for (int i=0; i<nElfms; i++) {
                int vbl = (int)(i*bbndSdblf + bbndOff);
                if ((vbl & 0xffffff00) != 0) {
                    if (vbl < 0) {
                        vbl = 0;
                    } flsf {
                        vbl = 255;
                    }
                }
                bbndLutDbtb[i] = (bytf)vbl;
            }

        }

        rfturn nfw BytfLookupTbblf(0, lutDbtb);
    }

    /**
     * Crfbtfs b SiortLookupTbblf to implfmfnt tif rfsdblf.
     * Tif tbblf mby ibvf fitifr b SHORT or BYTE input.
     * @pbrbm nElfms    Numbfr of flfmfnts tif tbblf is to ibvf.
     *                  Tiis will gfnfrblly bf 256 for bytf bnd
     *                  65536 for siort.
     */
    privbtf SiortLookupTbblf drfbtfSiortLut(flobt sdblf[],
                                            flobt off[],
                                            int   nBbnds,
                                            int   nElfms) {

        siort[][]        lutDbtb = nfw siort[sdblf.lfngti][nElfms];

        for (int bbnd=0; bbnd<sdblf.lfngti; bbnd++) {
            flobt   bbndSdblf   = sdblf[bbnd];
            flobt   bbndOff     = off[bbnd];
            siort[] bbndLutDbtb = lutDbtb[bbnd];
            for (int i=0; i<nElfms; i++) {
                int vbl = (int)(i*bbndSdblf + bbndOff);
                if ((vbl & 0xffff0000) != 0) {
                    if (vbl < 0) {
                        vbl = 0;
                    } flsf {
                        vbl = 65535;
                    }
                }
                bbndLutDbtb[i] = (siort)vbl;
            }
        }

        rfturn nfw SiortLookupTbblf(0, lutDbtb);
    }


    /**
     * Dftfrminfs if tif rfsdblf dbn bf pfrformfd bs b lookup.
     * Tif dst must bf b bytf or siort typf.
     * Tif srd must bf lfss tibn 16 bits.
     * All sourdf bbnd sizfs must bf tif sbmf bnd bll dst bbnd sizfs
     * must bf tif sbmf.
     */
    privbtf boolfbn dbnUsfLookup(Rbstfr srd, Rbstfr dst) {

        //
        // Cifdk tibt tif srd dbtbtypf is fitifr b BYTE or SHORT
        //
        int dbtbtypf = srd.gftDbtbBufffr().gftDbtbTypf();
        if(dbtbtypf != DbtbBufffr.TYPE_BYTE &&
           dbtbtypf != DbtbBufffr.TYPE_USHORT) {
            rfturn fblsf;
        }

        //
        // Cifdk dst sbmplf sizfs. All must bf 8 or 16 bits.
        //
        SbmplfModfl dstSM = dst.gftSbmplfModfl();
        dstNbits = dstSM.gftSbmplfSizf(0);

        if (!(dstNbits == 8 || dstNbits == 16)) {
            rfturn fblsf;
        }
        for (int i=1; i<srd.gftNumBbnds(); i++) {
            int bbndSizf = dstSM.gftSbmplfSizf(i);
            if (bbndSizf != dstNbits) {
                rfturn fblsf;
            }
        }

        //
        // Cifdk srd sbmplf sizfs. All must bf tif sbmf sizf
        //
        SbmplfModfl srdSM = srd.gftSbmplfModfl();
        srdNbits = srdSM.gftSbmplfSizf(0);
        if (srdNbits > 16) {
            rfturn fblsf;
        }
        for (int i=1; i<srd.gftNumBbnds(); i++) {
            int bbndSizf = srdSM.gftSbmplfSizf(i);
            if (bbndSizf != srdNbits) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    /**
     * Rfsdblfs tif sourdf BufffrfdImbgf.
     * If tif dolor modfl in tif sourdf imbgf is not tif sbmf bs tibt
     * in tif dfstinbtion imbgf, tif pixfls will bf donvfrtfd
     * in tif dfstinbtion.  If tif dfstinbtion imbgf is null,
     * b BufffrfdImbgf will bf drfbtfd witi tif sourdf ColorModfl.
     * An IllfgblArgumfntExdfption mby bf tirown if tif numbfr of
     * sdbling fbdtors/offsfts in tiis objfdt dofs not mfft tif
     * rfstridtions stbtfd in tif dlbss dommfnts bbovf, or if tif
     * sourdf imbgf ibs bn IndfxColorModfl.
     * @pbrbm srd tif <dodf>BufffrfdImbgf</dodf> to bf filtfrfd
     * @pbrbm dst tif dfstinbtion for tif filtfring opfrbtion
     *            or <dodf>null</dodf>
     * @rfturn tif filtfrfd <dodf>BufffrfdImbgf</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif <dodf>ColorModfl</dodf>
     *         of <dodf>srd</dodf> is bn <dodf>IndfxColorModfl</dodf>,
     *         or if tif numbfr of sdbling fbdtors bnd offsfts in tiis
     *         <dodf>RfsdblfOp</dodf> do not mfft tif rfquirfmfnts
     *         stbtfd in tif dlbss dommfnts.
     */
    publid finbl BufffrfdImbgf filtfr (BufffrfdImbgf srd, BufffrfdImbgf dst) {
        ColorModfl srdCM = srd.gftColorModfl();
        ColorModfl dstCM;
        int numBbnds = srdCM.gftNumColorComponfnts();


        if (srdCM instbndfof IndfxColorModfl) {
            tirow nfw
                IllfgblArgumfntExdfption("Rfsdbling dbnnot bf "+
                                         "pfrformfd on bn indfxfd imbgf");
        }
        if (lfngti != 1 && lfngti != numBbnds &&
            lfngti != srdCM.gftNumComponfnts())
        {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of sdbling donstbnts "+
                                               "dofs not fqubl tif numbfr of"+
                                               " of dolor or dolor/blpib "+
                                               " domponfnts");
        }

        boolfbn nffdToConvfrt = fblsf;

        // Indludf blpib
        if (lfngti > numBbnds && srdCM.ibsAlpib()) {
            lfngti = numBbnds+1;
        }

        int widti = srd.gftWidti();
        int ifigit = srd.gftHfigit();

        if (dst == null) {
            dst = drfbtfCompbtiblfDfstImbgf(srd, null);
            dstCM = srdCM;
        }
        flsf {
            if (widti != dst.gftWidti()) {
                tirow nfw
                    IllfgblArgumfntExdfption("Srd widti ("+widti+
                                             ") not fqubl to dst widti ("+
                                             dst.gftWidti()+")");
            }
            if (ifigit != dst.gftHfigit()) {
                tirow nfw
                    IllfgblArgumfntExdfption("Srd ifigit ("+ifigit+
                                             ") not fqubl to dst ifigit ("+
                                             dst.gftHfigit()+")");
            }

            dstCM = dst.gftColorModfl();
            if(srdCM.gftColorSpbdf().gftTypf() !=
               dstCM.gftColorSpbdf().gftTypf()) {
                nffdToConvfrt = truf;
                dst = drfbtfCompbtiblfDfstImbgf(srd, null);
            }

        }

        BufffrfdImbgf origDst = dst;

        //
        // Try to usf b nbtivf BI rfsdblf opfrbtion first
        //
        if (ImbgingLib.filtfr(tiis, srd, dst) == null) {
            //
            // Nbtivf BI rfsdblf fbilfd - donvfrt to rbstfrs
            //
            WritbblfRbstfr srdRbstfr = srd.gftRbstfr();
            WritbblfRbstfr dstRbstfr = dst.gftRbstfr();

            if (srdCM.ibsAlpib()) {
                if (numBbnds-1 == lfngti || lfngti == 1) {
                    int minx = srdRbstfr.gftMinX();
                    int miny = srdRbstfr.gftMinY();
                    int[] bbnds = nfw int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    srdRbstfr =
                        srdRbstfr.drfbtfWritbblfCiild(minx, miny,
                                                      srdRbstfr.gftWidti(),
                                                      srdRbstfr.gftHfigit(),
                                                      minx, miny,
                                                      bbnds);
                }
            }
            if (dstCM.ibsAlpib()) {
                int dstNumBbnds = dstRbstfr.gftNumBbnds();
                if (dstNumBbnds-1 == lfngti || lfngti == 1) {
                    int minx = dstRbstfr.gftMinX();
                    int miny = dstRbstfr.gftMinY();
                    int[] bbnds = nfw int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    dstRbstfr =
                        dstRbstfr.drfbtfWritbblfCiild(minx, miny,
                                                      dstRbstfr.gftWidti(),
                                                      dstRbstfr.gftHfigit(),
                                                      minx, miny,
                                                      bbnds);
                }
            }

            //
            // Cbll tif rbstfr filtfr mftiod
            //
            filtfr(srdRbstfr, dstRbstfr);

        }

        if (nffdToConvfrt) {
            // ColorModfls brf not tif sbmf
            ColorConvfrtOp ddop = nfw ColorConvfrtOp(iints);
            ddop.filtfr(dst, origDst);
        }

        rfturn origDst;
    }

    /**
     * Rfsdblfs tif pixfl dbtb in tif sourdf Rbstfr.
     * If tif dfstinbtion Rbstfr is null, b nfw Rbstfr will bf drfbtfd.
     * Tif sourdf bnd dfstinbtion must ibvf tif sbmf numbfr of bbnds.
     * Otifrwisf, bn IllfgblArgumfntExdfption is tirown.
     * Notf tibt tif numbfr of sdbling fbdtors/offsfts in tiis objfdt must
     * mfft tif rfstridtions stbtfd in tif dlbss dommfnts bbovf.
     * Otifrwisf, bn IllfgblArgumfntExdfption is tirown.
     * @pbrbm srd tif <dodf>Rbstfr</dodf> to bf filtfrfd
     * @pbrbm dst tif dfstinbtion for tif filtfring opfrbtion
     *            or <dodf>null</dodf>
     * @rfturn tif filtfrfd <dodf>WritbblfRbstfr</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>srd</dodf> bnd
     *         <dodf>dst</dodf> do not ibvf tif sbmf numbfr of bbnds,
     *         or if tif numbfr of sdbling fbdtors bnd offsfts in tiis
     *         <dodf>RfsdblfOp</dodf> do not mfft tif rfquirfmfnts
     *         stbtfd in tif dlbss dommfnts.
     */
    publid finbl WritbblfRbstfr filtfr (Rbstfr srd, WritbblfRbstfr dst)  {
        int numBbnds = srd.gftNumBbnds();
        int widti  = srd.gftWidti();
        int ifigit = srd.gftHfigit();
        int[] srdPix = null;
        int stfp = 0;
        int tidx = 0;

        // Crfbtf b nfw dfstinbtion Rbstfr, if nffdfd
        if (dst == null) {
            dst = drfbtfCompbtiblfDfstRbstfr(srd);
        }
        flsf if (ifigit != dst.gftHfigit() || widti != dst.gftWidti()) {
            tirow nfw
               IllfgblArgumfntExdfption("Widti or ifigit of Rbstfrs do not "+
                                        "mbtdi");
        }
        flsf if (numBbnds != dst.gftNumBbnds()) {
            // Mbkf surf tibt tif numbfr of bbnds brf fqubl
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bbnds in srd "
                            + numBbnds
                            + " dofs not fqubl numbfr of bbnds in dfst "
                            + dst.gftNumBbnds());
        }
        // Mbkf surf tibt tif brrbys mbtdi
        // Mbkf surf tibt tif low/iigi/donstbnt brrbys mbtdi
        if (lfngti != 1 && lfngti != srd.gftNumBbnds()) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of sdbling donstbnts "+
                                               "dofs not fqubl tif numbfr of"+
                                               " of bbnds in tif srd rbstfr");
        }


        //
        // Try for b nbtivf rbstfr rfsdblf first
        //
        if (ImbgingLib.filtfr(tiis, srd, dst) != null) {
            rfturn dst;
        }

        //
        // Nbtivf rbstfr rfsdblf fbilfd.
        // Try to sff if b lookup opfrbtion dbn bf usfd
        //
        if (dbnUsfLookup(srd, dst)) {
            int srdNgrby = (1 << srdNbits);
            int dstNgrby = (1 << dstNbits);

            if (dstNgrby == 256) {
                BytfLookupTbblf lut = drfbtfBytfLut(sdblfFbdtors, offsfts,
                                                    numBbnds, srdNgrby);
                LookupOp op = nfw LookupOp(lut, iints);
                op.filtfr(srd, dst);
            } flsf {
                SiortLookupTbblf lut = drfbtfSiortLut(sdblfFbdtors, offsfts,
                                                      numBbnds, srdNgrby);
                LookupOp op = nfw LookupOp(lut, iints);
                op.filtfr(srd, dst);
            }
        } flsf {
            //
            // Fbll bbdk to tif slow dodf
            //
            if (lfngti > 1) {
                stfp = 1;
            }

            int sminX = srd.gftMinX();
            int sY = srd.gftMinY();
            int dminX = dst.gftMinX();
            int dY = dst.gftMinY();
            int sX;
            int dX;

            //
            //  Dftfrminf bits pfr bbnd to dftfrminf mbxvbl for dlbmps.
            //  Tif min is bssumfd to bf zfro.
            //  REMIND: Tiis must dibngf if wf fvfr support signfd dbtb typfs.
            //
            int nbits;
            int dstMbx[] = nfw int[numBbnds];
            int dstMbsk[] = nfw int[numBbnds];
            SbmplfModfl dstSM = dst.gftSbmplfModfl();
            for (int z=0; z<numBbnds; z++) {
                nbits = dstSM.gftSbmplfSizf(z);
                dstMbx[z] = (1 << nbits) - 1;
                dstMbsk[z] = ~(dstMbx[z]);
            }

            int vbl;
            for (int y=0; y < ifigit; y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x = 0; x < widti; x++, sX++, dX++) {
                    // Gft dbtb for bll bbnds bt tiis x,y position
                    srdPix = srd.gftPixfl(sX, sY, srdPix);
                    tidx = 0;
                    for (int z=0; z<numBbnds; z++, tidx += stfp) {
                        vbl = (int)(srdPix[z]*sdblfFbdtors[tidx]
                                          + offsfts[tidx]);
                        // Clbmp
                        if ((vbl & dstMbsk[z]) != 0) {
                            if (vbl < 0) {
                                vbl = 0;
                            } flsf {
                                vbl = dstMbx[z];
                            }
                        }
                        srdPix[z] = vbl;

                    }

                    // Put it bbdk for bll bbnds
                    dst.sftPixfl(dX, dY, srdPix);
                }
            }
        }
        rfturn dst;
    }

    /**
     * Rfturns tif bounding box of tif rfsdblfd dfstinbtion imbgf.  Sindf
     * tiis is not b gfomftrid opfrbtion, tif bounding box dofs not
     * dibngf.
     */
    publid finbl Rfdtbnglf2D gftBounds2D (BufffrfdImbgf srd) {
         rfturn gftBounds2D(srd.gftRbstfr());
    }

    /**
     * Rfturns tif bounding box of tif rfsdblfd dfstinbtion Rbstfr.  Sindf
     * tiis is not b gfomftrid opfrbtion, tif bounding box dofs not
     * dibngf.
     * @pbrbm srd tif rfsdblfd dfstinbtion <dodf>Rbstfr</dodf>
     * @rfturn tif bounds of tif spfdififd <dodf>Rbstfr</dodf>.
     */
    publid finbl Rfdtbnglf2D gftBounds2D (Rbstfr srd) {
        rfturn srd.gftBounds();
    }

    /**
     * Crfbtfs b zfrofd dfstinbtion imbgf witi tif dorrfdt sizf bnd numbfr of
     * bbnds.
     * @pbrbm srd       Sourdf imbgf for tif filtfr opfrbtion.
     * @pbrbm dfstCM    ColorModfl of tif dfstinbtion.  If null, tif
     *                  ColorModfl of tif sourdf will bf usfd.
     * @rfturn tif zfrofd-dfstinbtion imbgf.
     */
    publid BufffrfdImbgf drfbtfCompbtiblfDfstImbgf (BufffrfdImbgf srd,
                                                    ColorModfl dfstCM) {
        BufffrfdImbgf imbgf;
        if (dfstCM == null) {
            ColorModfl dm = srd.gftColorModfl();
            imbgf = nfw BufffrfdImbgf(dm,
                                      srd.gftRbstfr().drfbtfCompbtiblfWritbblfRbstfr(),
                                      dm.isAlpibPrfmultiplifd(),
                                      null);
        }
        flsf {
            int w = srd.gftWidti();
            int i = srd.gftHfigit();
            imbgf = nfw BufffrfdImbgf (dfstCM,
                                   dfstCM.drfbtfCompbtiblfWritbblfRbstfr(w, i),
                                   dfstCM.isAlpibPrfmultiplifd(), null);
        }

        rfturn imbgf;
    }

    /**
     * Crfbtfs b zfrofd-dfstinbtion <dodf>Rbstfr</dodf> witi tif dorrfdt
     * sizf bnd numbfr of bbnds, givfn tiis sourdf.
     * @pbrbm srd       tif sourdf <dodf>Rbstfr</dodf>
     * @rfturn tif zfrofd-dfstinbtion <dodf>Rbstfr</dodf>.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfDfstRbstfr (Rbstfr srd) {
        rfturn srd.drfbtfCompbtiblfWritbblfRbstfr(srd.gftWidti(), srd.gftHfigit());
    }

    /**
     * Rfturns tif lodbtion of tif dfstinbtion point givfn b
     * point in tif sourdf.  If dstPt is non-null, it will
     * bf usfd to iold tif rfturn vbluf.  Sindf tiis is not b gfomftrid
     * opfrbtion, tif srdPt will fqubl tif dstPt.
     * @pbrbm srdPt b point in tif sourdf imbgf
     * @pbrbm dstPt tif dfstinbtion point or <dodf>null</dodf>
     * @rfturn tif lodbtion of tif dfstinbtion point.
     */
    publid finbl Point2D gftPoint2D (Point2D srdPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = nfw Point2D.Flobt();
        }
        dstPt.sftLodbtion(srdPt.gftX(), srdPt.gftY());
        rfturn dstPt;
    }

    /**
     * Rfturns tif rfndfring iints for tiis op.
     * @rfturn tif rfndfring iints of tiis <dodf>RfsdblfOp</dodf>.
     */
    publid finbl RfndfringHints gftRfndfringHints() {
        rfturn iints;
    }
}
