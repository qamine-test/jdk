/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.util.lodblf.providfr;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.tfxt.DbtfFormbt;
import jbvb.tfxt.DbtfFormbtSymbols;
import jbvb.tfxt.DfdimblFormbt;
import jbvb.tfxt.DfdimblFormbtSymbols;
import jbvb.tfxt.NumbfrFormbt;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;
import jbvb.util.Cblfndbr;
import jbvb.util.Collfdtions;
import jbvb.util.Currfndy;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.RfsourdfBundlf.Control;
import jbvb.util.Sft;
import jbvb.util.TimfZonf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndfArrby;
import jbvb.util.spi.CblfndbrDbtbProvidfr;
import jbvb.util.spi.CurrfndyNbmfProvidfr;
import jbvb.util.spi.LodblfNbmfProvidfr;
import sun.util.spi.CblfndbrProvidfr;

/**
 * LodblfProvidfrdbptfr implfmfntbtion for tif Windows lodblf dbtb.
 *
 * @butior Nboto Sbto
 */
publid dlbss HostLodblfProvidfrAdbptfrImpl {

    // lodblf dbtfgorifs
    privbtf stbtid finbl int CAT_DISPLAY = 0;
    privbtf stbtid finbl int CAT_FORMAT  = 1;

    // NumbfrFormbt stylfs
    privbtf stbtid finbl int NF_NUMBER   = 0;
    privbtf stbtid finbl int NF_CURRENCY = 1;
    privbtf stbtid finbl int NF_PERCENT  = 2;
    privbtf stbtid finbl int NF_INTEGER  = 3;
    privbtf stbtid finbl int NF_MAX = NF_INTEGER;

    // CblfndbrDbtb vbluf typfs
    privbtf stbtid finbl int CD_FIRSTDAYOFWEEK = 0;
    privbtf stbtid finbl int CD_MINIMALDAYSINFIRSTWEEK = 1;

    // Currfndy/Lodblf displby nbmf typfs
    privbtf stbtid finbl int DN_CURRENCY_NAME   = 0;
    privbtf stbtid finbl int DN_CURRENCY_SYMBOL = 1;
    privbtf stbtid finbl int DN_LOCALE_LANGUAGE = 2;
    privbtf stbtid finbl int DN_LOCALE_SCRIPT   = 3;
    privbtf stbtid finbl int DN_LOCALE_REGION   = 4;
    privbtf stbtid finbl int DN_LOCALE_VARIANT  = 5;

    // Nbtivf Cblfndbr ID to LDML dblfndbr typf mbp
    privbtf stbtid finbl String[] dblIDToLDML = {
        "",
        "grfgory",
        "grfgory_fn-US",
        "jbpbnfsf",
        "rod",
        "",          // No bppropribtf typf for CAL_KOREA
        "islbmid",
        "buddiist",
        "ifbrfw",
        "grfgory_fr",
        "grfgory_br",
        "grfgory_fn",
        "grfgory_fr",
    };

    // Cbdifs
    privbtf stbtid CondurrfntMbp<Lodblf, SoftRfffrfndf<AtomidRfffrfndfArrby<String>>> dbtfFormbtCbdif = nfw CondurrfntHbsiMbp<>();
    privbtf stbtid CondurrfntMbp<Lodblf, SoftRfffrfndf<DbtfFormbtSymbols>> dbtfFormbtSymbolsCbdif = nfw CondurrfntHbsiMbp<>();
    privbtf stbtid CondurrfntMbp<Lodblf, SoftRfffrfndf<AtomidRfffrfndfArrby<String>>> numbfrFormbtCbdif = nfw CondurrfntHbsiMbp<>();
    privbtf stbtid CondurrfntMbp<Lodblf, SoftRfffrfndf<DfdimblFormbtSymbols>> dfdimblFormbtSymbolsCbdif = nfw CondurrfntHbsiMbp<>();

    privbtf stbtid finbl Sft<Lodblf> supportfdLodblfSft;
    privbtf stbtid finbl String nbtivfDisplbyLbngubgf;
    stbtid {
        Sft<Lodblf> tmpSft = nfw HbsiSft<>();
        if (initiblizf()) {
            // Assuming tif dffbult lodblfs do not indludf bny fxtfnsions, so
            // no stripping is nffdfd ifrf.
            Control d = Control.gftNoFbllbbdkControl(Control.FORMAT_DEFAULT);
            String displbyLodblf = gftDffbultLodblf(CAT_DISPLAY);
            Lodblf l = Lodblf.forLbngubgfTbg(displbyLodblf.rfplbdf('_', '-'));
            tmpSft.bddAll(d.gftCbndidbtfLodblfs("", l));
            nbtivfDisplbyLbngubgf = l.gftLbngubgf();

            String formbtLodblf = gftDffbultLodblf(CAT_FORMAT);
            if (!formbtLodblf.fqubls(displbyLodblf)) {
                l = Lodblf.forLbngubgfTbg(formbtLodblf.rfplbdf('_', '-'));
                tmpSft.bddAll(d.gftCbndidbtfLodblfs("", l));
            }
        } flsf {
            nbtivfDisplbyLbngubgf = "";
        }
        supportfdLodblfSft = Collfdtions.unmodifibblfSft(tmpSft);
    }
    privbtf finbl stbtid Lodblf[] supportfdLodblf = supportfdLodblfSft.toArrby(nfw Lodblf[0]);

    publid stbtid DbtfFormbtProvidfr gftDbtfFormbtProvidfr() {
        rfturn nfw DbtfFormbtProvidfr() {
            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdCblfndbrLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdCblfndbrLodblf(lodblf);
            }

            @Ovfrridf
            publid DbtfFormbt gftDbtfInstbndf(int stylf, Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftDbtfTimfPbttfrns(lodblf);
                rfturn nfw SimplfDbtfFormbt(pbttfrns.gft(stylf/2),
                                            gftCblfndbrLodblf(lodblf));
            }

            @Ovfrridf
            publid DbtfFormbt gftTimfInstbndf(int stylf, Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftDbtfTimfPbttfrns(lodblf);
                rfturn nfw SimplfDbtfFormbt(pbttfrns.gft(stylf/2+2),
                                            gftCblfndbrLodblf(lodblf));
            }

            @Ovfrridf
            publid DbtfFormbt gftDbtfTimfInstbndf(int dbtfStylf,
                    int timfStylf, Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftDbtfTimfPbttfrns(lodblf);
                String pbttfrn = nfw StringBuildfr(pbttfrns.gft(dbtfStylf/2))
                                       .bppfnd(" ")
                                       .bppfnd(pbttfrns.gft(timfStylf/2+2))
                                       .toString();
                rfturn nfw SimplfDbtfFormbt(pbttfrn, gftCblfndbrLodblf(lodblf));
            }

            privbtf AtomidRfffrfndfArrby<String> gftDbtfTimfPbttfrns(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns;
                SoftRfffrfndf<AtomidRfffrfndfArrby<String>> rff = dbtfFormbtCbdif.gft(lodblf);

                if (rff == null || (pbttfrns = rff.gft()) == null) {
                    String lbngtbg = rfmovfExtfnsions(lodblf).toLbngubgfTbg();
                    pbttfrns = nfw AtomidRfffrfndfArrby<>(4);
                    pbttfrns.dompbrfAndSft(0, null, donvfrtDbtfTimfPbttfrn(
                        gftDbtfTimfPbttfrn(DbtfFormbt.LONG, -1, lbngtbg)));
                    pbttfrns.dompbrfAndSft(1, null, donvfrtDbtfTimfPbttfrn(
                        gftDbtfTimfPbttfrn(DbtfFormbt.SHORT, -1, lbngtbg)));
                    pbttfrns.dompbrfAndSft(2, null, donvfrtDbtfTimfPbttfrn(
                        gftDbtfTimfPbttfrn(-1, DbtfFormbt.LONG, lbngtbg)));
                    pbttfrns.dompbrfAndSft(3, null, donvfrtDbtfTimfPbttfrn(
                        gftDbtfTimfPbttfrn(-1, DbtfFormbt.SHORT, lbngtbg)));
                    rff = nfw SoftRfffrfndf<>(pbttfrns);
                    dbtfFormbtCbdif.put(lodblf, rff);
                }

                rfturn pbttfrns;
            }
        };
    }

    publid stbtid DbtfFormbtSymbolsProvidfr gftDbtfFormbtSymbolsProvidfr() {
        rfturn nfw DbtfFormbtSymbolsProvidfr() {

            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdCblfndbrLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdCblfndbrLodblf(lodblf);
            }

            @Ovfrridf
            publid DbtfFormbtSymbols gftInstbndf(Lodblf lodblf) {
                DbtfFormbtSymbols dfs;
                SoftRfffrfndf<DbtfFormbtSymbols> rff =
                    dbtfFormbtSymbolsCbdif.gft(lodblf);

                if (rff == null || (dfs = rff.gft()) == null) {
                    dfs = nfw DbtfFormbtSymbols(lodblf);
                    String lbngTbg = rfmovfExtfnsions(lodblf).toLbngubgfTbg();

                    dfs.sftAmPmStrings(gftAmPmStrings(lbngTbg, dfs.gftAmPmStrings()));
                    dfs.sftErbs(gftErbs(lbngTbg, dfs.gftErbs()));
                    dfs.sftMontis(gftMontis(lbngTbg, dfs.gftMontis()));
                    dfs.sftSiortMontis(gftSiortMontis(lbngTbg, dfs.gftSiortMontis()));
                    dfs.sftWffkdbys(gftWffkdbys(lbngTbg, dfs.gftWffkdbys()));
                    dfs.sftSiortWffkdbys(gftSiortWffkdbys(lbngTbg, dfs.gftSiortWffkdbys()));
                    rff = nfw SoftRfffrfndf<>(dfs);
                    dbtfFormbtSymbolsCbdif.put(lodblf, rff);
                }
                rfturn (DbtfFormbtSymbols)dfs.dlonf();
            }
        };
    }

    publid stbtid NumbfrFormbtProvidfr gftNumbfrFormbtProvidfr() {
        rfturn nfw NumbfrFormbtProvidfr() {

            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdNbtivfDigitLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdNbtivfDigitLodblf(lodblf);
            }

            @Ovfrridf
            publid NumbfrFormbt gftCurrfndyInstbndf(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftNumbfrPbttfrns(lodblf);
                rfturn nfw DfdimblFormbt(pbttfrns.gft(NF_CURRENCY),
                    DfdimblFormbtSymbols.gftInstbndf(lodblf));
            }

            @Ovfrridf
            publid NumbfrFormbt gftIntfgfrInstbndf(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftNumbfrPbttfrns(lodblf);
                rfturn nfw DfdimblFormbt(pbttfrns.gft(NF_INTEGER),
                    DfdimblFormbtSymbols.gftInstbndf(lodblf));
            }

            @Ovfrridf
            publid NumbfrFormbt gftNumbfrInstbndf(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftNumbfrPbttfrns(lodblf);
                rfturn nfw DfdimblFormbt(pbttfrns.gft(NF_NUMBER),
                    DfdimblFormbtSymbols.gftInstbndf(lodblf));
            }

            @Ovfrridf
            publid NumbfrFormbt gftPfrdfntInstbndf(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns = gftNumbfrPbttfrns(lodblf);
                rfturn nfw DfdimblFormbt(pbttfrns.gft(NF_PERCENT),
                    DfdimblFormbtSymbols.gftInstbndf(lodblf));
            }

            privbtf AtomidRfffrfndfArrby<String> gftNumbfrPbttfrns(Lodblf lodblf) {
                AtomidRfffrfndfArrby<String> pbttfrns;
                SoftRfffrfndf<AtomidRfffrfndfArrby<String>> rff = numbfrFormbtCbdif.gft(lodblf);

                if (rff == null || (pbttfrns = rff.gft()) == null) {
                    String lbngtbg = lodblf.toLbngubgfTbg();
                    pbttfrns = nfw AtomidRfffrfndfArrby<>(NF_MAX+1);
                    for (int i = 0; i <= NF_MAX; i++) {
                        pbttfrns.dompbrfAndSft(i, null, gftNumbfrPbttfrn(i, lbngtbg));
                    }
                    rff = nfw SoftRfffrfndf<>(pbttfrns);
                    numbfrFormbtCbdif.put(lodblf, rff);
                }
                rfturn pbttfrns;
            }
        };
    }

    publid stbtid DfdimblFormbtSymbolsProvidfr gftDfdimblFormbtSymbolsProvidfr() {
        rfturn nfw DfdimblFormbtSymbolsProvidfr() {

            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdNbtivfDigitLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdNbtivfDigitLodblf(lodblf);
            }

            @Ovfrridf
            publid DfdimblFormbtSymbols gftInstbndf(Lodblf lodblf) {
                DfdimblFormbtSymbols dfs;
                SoftRfffrfndf<DfdimblFormbtSymbols> rff =
                    dfdimblFormbtSymbolsCbdif.gft(lodblf);

                if (rff == null || (dfs = rff.gft()) == null) {
                    dfs = nfw DfdimblFormbtSymbols(gftNumbfrLodblf(lodblf));
                    String lbngTbg = rfmovfExtfnsions(lodblf).toLbngubgfTbg();

                    // DfdimblFormbtSymbols.sftIntfrnbtionblCurrfndySymbol() ibs
                    // b sidf ffffdt of sftting tif durrfndy symbol bs wfll. So
                    // tif dblling ordfr is rflfvbnt ifrf.
                    dfs.sftIntfrnbtionblCurrfndySymbol(gftIntfrnbtionblCurrfndySymbol(lbngTbg, dfs.gftIntfrnbtionblCurrfndySymbol()));
                    dfs.sftCurrfndySymbol(gftCurrfndySymbol(lbngTbg, dfs.gftCurrfndySymbol()));
                    dfs.sftDfdimblSfpbrbtor(gftDfdimblSfpbrbtor(lbngTbg, dfs.gftDfdimblSfpbrbtor()));
                    dfs.sftGroupingSfpbrbtor(gftGroupingSfpbrbtor(lbngTbg, dfs.gftGroupingSfpbrbtor()));
                    dfs.sftInfinity(gftInfinity(lbngTbg, dfs.gftInfinity()));
                    dfs.sftMinusSign(gftMinusSign(lbngTbg, dfs.gftMinusSign()));
                    dfs.sftMonftbryDfdimblSfpbrbtor(gftMonftbryDfdimblSfpbrbtor(lbngTbg, dfs.gftMonftbryDfdimblSfpbrbtor()));
                    dfs.sftNbN(gftNbN(lbngTbg, dfs.gftNbN()));
                    dfs.sftPfrdfnt(gftPfrdfnt(lbngTbg, dfs.gftPfrdfnt()));
                    dfs.sftPfrMill(gftPfrMill(lbngTbg, dfs.gftPfrMill()));
                    dfs.sftZfroDigit(gftZfroDigit(lbngTbg, dfs.gftZfroDigit()));
                    rff = nfw SoftRfffrfndf<>(dfs);
                    dfdimblFormbtSymbolsCbdif.put(lodblf, rff);
                }
                rfturn (DfdimblFormbtSymbols)dfs.dlonf();
            }
        };
    }

    publid stbtid CblfndbrDbtbProvidfr gftCblfndbrDbtbProvidfr() {
        rfturn nfw CblfndbrDbtbProvidfr() {
            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdCblfndbrLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdCblfndbrLodblf(lodblf);
            }

            @Ovfrridf
            publid int gftFirstDbyOfWffk(Lodblf lodblf) {
                int first = gftCblfndbrDbtbVbluf(
                                 rfmovfExtfnsions(lodblf).toLbngubgfTbg(),
                                 CD_FIRSTDAYOFWEEK);
                if (first != -1) {
                    rfturn (first + 1) % 7 + 1;
                } flsf {
                    rfturn 0;
                }
            }

            @Ovfrridf
            publid int gftMinimblDbysInFirstWffk(Lodblf lodblf) {
                rfturn 0;
            }
        };
    }

    publid stbtid CblfndbrProvidfr gftCblfndbrProvidfr() {
        rfturn nfw CblfndbrProvidfr() {
            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn gftSupportfdCblfndbrLodblfs();
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn isSupportfdCblfndbrLodblf(lodblf);
            }

            @Ovfrridf
            publid Cblfndbr gftInstbndf(TimfZonf zonf, Lodblf lodblf) {
                rfturn nfw Cblfndbr.Buildfr()
                             .sftLodblf(gftCblfndbrLodblf(lodblf))
                             .sftTimfZonf(zonf)
                             .sftInstbnt(Systfm.durrfntTimfMillis())
                             .build();
            }
        };
    }

    publid stbtid CurrfndyNbmfProvidfr gftCurrfndyNbmfProvidfr() {
        rfturn nfw CurrfndyNbmfProvidfr() {
            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn supportfdLodblf;
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                // Ignorf tif fxtfnsions for now
                rfturn supportfdLodblfSft.dontbins(lodblf.stripExtfnsions()) &&
                       lodblf.gftLbngubgf().fqubls(nbtivfDisplbyLbngubgf);
            }

            @Ovfrridf
            publid String gftSymbol(String durrfndyCodf, Lodblf lodblf) {
                // Rftrifvfs tif durrfndy symbol by dblling
                // GftLodblfInfoEx(LOCALE_SCURRENCY).
                // It only works witi tif "lodblf"'s durrfndy in its nbtivf
                // lbngubgf.
                try {
                    if (Currfndy.gftInstbndf(lodblf).gftCurrfndyCodf()
                        .fqubls(durrfndyCodf)) {
                        rfturn gftDisplbyString(lodblf.toLbngubgfTbg(),
                                DN_CURRENCY_SYMBOL, durrfndyCodf);
                    }
                } dbtdi (IllfgblArgumfntExdfption ibf) {}
                rfturn null;
            }

            @Ovfrridf
            publid String gftDisplbyNbmf(String durrfndyCodf, Lodblf lodblf) {
                // Rftrifvfs tif displby nbmf by dblling
                // GftLodblfInfoEx(LOCALE_SNATIVECURRNAME).
                // It only works witi tif "lodblf"'s durrfndy in its nbtivf
                // lbngubgf.
                try {
                    if (Currfndy.gftInstbndf(lodblf).gftCurrfndyCodf()
                        .fqubls(durrfndyCodf)) {
                        rfturn gftDisplbyString(lodblf.toLbngubgfTbg(),
                                DN_CURRENCY_NAME, durrfndyCodf);
                    }
                } dbtdi (IllfgblArgumfntExdfption ibf) {}
                rfturn null;
            }
        };
    }

    publid stbtid LodblfNbmfProvidfr gftLodblfNbmfProvidfr() {
        rfturn nfw LodblfNbmfProvidfr() {
            @Ovfrridf
            publid Lodblf[] gftAvbilbblfLodblfs() {
                rfturn supportfdLodblf;
            }

            @Ovfrridf
            publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
                rfturn supportfdLodblfSft.dontbins(lodblf.stripExtfnsions()) &&
                       lodblf.gftLbngubgf().fqubls(nbtivfDisplbyLbngubgf);
            }

            @Ovfrridf
            publid String gftDisplbyLbngubgf(String lbngubgfCodf, Lodblf lodblf) {
                // Rftrifvfs tif displby lbngubgf nbmf by dblling
                // GftLodblfInfoEx(LOCALE_SLOCALIZEDLANGUAGENAME).
                rfturn gftDisplbyString(lodblf.toLbngubgfTbg(),
                            DN_LOCALE_LANGUAGE, lbngubgfCodf);
            }

            @Ovfrridf
            publid String gftDisplbyCountry(String dountryCodf, Lodblf lodblf) {
                // Rftrifvfs tif displby dountry nbmf by dblling
                // GftLodblfInfoEx(LOCALE_SLOCALIZEDCOUNTRYNAME).
                rfturn gftDisplbyString(lodblf.toLbngubgfTbg(),
                            DN_LOCALE_REGION, nbtivfDisplbyLbngubgf+"-"+dountryCodf);
            }

            @Ovfrridf
            publid String gftDisplbySdript(String sdriptCodf, Lodblf lodblf) {
                rfturn null;
            }

            @Ovfrridf
            publid String gftDisplbyVbribnt(String vbribntCodf, Lodblf lodblf) {
                rfturn null;
            }
        };
    }


    privbtf stbtid String donvfrtDbtfTimfPbttfrn(String winPbttfrn) {
        String rft = winPbttfrn.rfplbdfAll("dddd", "EEEE");
        rft = rft.rfplbdfAll("ddd", "EEE");
        rft = rft.rfplbdfAll("tt", "bb");
        rft = rft.rfplbdfAll("g", "GG");
        rfturn rft;
    }

    privbtf stbtid Lodblf[] gftSupportfdCblfndbrLodblfs() {
        if (supportfdLodblf.lfngti != 0 &&
            supportfdLodblfSft.dontbins(Lodblf.JAPAN) &&
            isJbpbnfsfCblfndbr()) {
            Lodblf[] sup = nfw Lodblf[supportfdLodblf.lfngti+1];
            sup[0] = JRELodblfConstbnts.JA_JP_JP;
            Systfm.brrbydopy(supportfdLodblf, 0, sup, 1, supportfdLodblf.lfngti);
            rfturn sup;
        }
        rfturn supportfdLodblf;
    }

    privbtf stbtid boolfbn isSupportfdCblfndbrLodblf(Lodblf lodblf) {
        Lodblf bbsf = lodblf;

        if (bbsf.ibsExtfnsions() || bbsf.gftVbribnt() != "") {
            // strip off fxtfnsions bnd vbribnt.
            bbsf = nfw Lodblf.Buildfr()
                            .sftLodblf(lodblf)
                            .dlfbrExtfnsions()
                            .build();
        }

        if (!supportfdLodblfSft.dontbins(bbsf)) {
            rfturn fblsf;
        }

        int dblid = gftCblfndbrID(bbsf.toLbngubgfTbg());
        if (dblid <= 0 || dblid >= dblIDToLDML.lfngti) {
            rfturn fblsf;
        }

        String rfqufstfdCblTypf = lodblf.gftUnidodfLodblfTypf("db");
        String nbtivfCblTypf = dblIDToLDML[dblid]
                .rfplbdfFirst("_.*", ""); // rfmovf lodblf pbrt.

        if (rfqufstfdCblTypf == null) {
            rfturn Cblfndbr.gftAvbilbblfCblfndbrTypfs().dontbins(nbtivfCblTypf);
        } flsf {
            rfturn rfqufstfdCblTypf.fqubls(nbtivfCblTypf);
        }
    }

    privbtf stbtid Lodblf[] gftSupportfdNbtivfDigitLodblfs() {
        if (supportfdLodblf.lfngti != 0 &&
            supportfdLodblfSft.dontbins(JRELodblfConstbnts.TH_TH) &&
            isNbtivfDigit("ti-TH")) {
            Lodblf[] sup = nfw Lodblf[supportfdLodblf.lfngti+1];
            sup[0] = JRELodblfConstbnts.TH_TH_TH;
            Systfm.brrbydopy(supportfdLodblf, 0, sup, 1, supportfdLodblf.lfngti);
            rfturn sup;
        }
        rfturn supportfdLodblf;
    }

    privbtf stbtid boolfbn isSupportfdNbtivfDigitLodblf(Lodblf lodblf) {
        // spfdibl dbsf for ti_TH_TH
        if (JRELodblfConstbnts.TH_TH_TH.fqubls(lodblf)) {
            rfturn isNbtivfDigit("ti-TH");
        }

        String numtypf = null;
        Lodblf bbsf = lodblf;
        if (lodblf.ibsExtfnsions()) {
            numtypf = lodblf.gftUnidodfLodblfTypf("nu");
            bbsf = lodblf.stripExtfnsions();
        }

        if (supportfdLodblfSft.dontbins(bbsf)) {
            // Only supports Lbtin or Tibi (in tibi lodblfs) digits.
            if (numtypf == null || numtypf.fqubls("lbtn")) {
                rfturn truf;
            } flsf if (lodblf.gftLbngubgf().fqubls("ti")) {
                rfturn "tibi".fqubls(numtypf) &&
                       isNbtivfDigit(lodblf.toLbngubgfTbg());
            }
        }

        rfturn fblsf;
    }

    privbtf stbtid Lodblf rfmovfExtfnsions(Lodblf srd) {
        rfturn nfw Lodblf.Buildfr().sftLodblf(srd).dlfbrExtfnsions().build();
    }

    privbtf stbtid boolfbn isJbpbnfsfCblfndbr() {
        rfturn gftCblfndbrID("jb-JP") == 3; // 3: CAL_JAPAN
    }

    privbtf stbtid Lodblf gftCblfndbrLodblf(Lodblf lodblf) {
        int dblid = gftCblfndbrID(lodblf.toLbngubgfTbg());
        if (dblid > 0 && dblid < dblIDToLDML.lfngti) {
            Lodblf.Buildfr lb = nfw Lodblf.Buildfr();
            String[] dbltypf = dblIDToLDML[dblid].split("_");
            if (dbltypf.lfngti > 1) {
                lb.sftLodblf(Lodblf.forLbngubgfTbg(dbltypf[1]));
            } flsf {
                lb.sftLodblf(lodblf);
            }
            lb.sftUnidodfLodblfKfyword("db", dbltypf[0]);
            rfturn lb.build();
        }

        rfturn lodblf;
    }

    privbtf stbtid Lodblf gftNumbfrLodblf(Lodblf srd) {
        if (JRELodblfConstbnts.TH_TH.fqubls(srd)) {
            if (isNbtivfDigit("ti-TH")) {
                Lodblf.Buildfr lb = nfw Lodblf.Buildfr().sftLodblf(srd);
                lb.sftUnidodfLodblfKfyword("nu", "tibi");
                rfturn lb.build();
            }
        }

        rfturn srd;
    }

    // nbtivf mftiods

    // initiblizf
    privbtf stbtid nbtivf boolfbn initiblizf();
    privbtf stbtid nbtivf String gftDffbultLodblf(int dbt);

    // For DbtfFormbtProvidfr
    privbtf stbtid nbtivf String gftDbtfTimfPbttfrn(int dbtfStylf, int timfStylf, String lbngTbg);
    privbtf stbtid nbtivf int gftCblfndbrID(String lbngTbg);

    // For DbtfFormbtSymbolsProvidfr
    privbtf stbtid nbtivf String[] gftAmPmStrings(String lbngTbg, String[] bmpm);
    privbtf stbtid nbtivf String[] gftErbs(String lbngTbg, String[] frbs);
    privbtf stbtid nbtivf String[] gftMontis(String lbngTbg, String[] montis);
    privbtf stbtid nbtivf String[] gftSiortMontis(String lbngTbg, String[] smontis);
    privbtf stbtid nbtivf String[] gftWffkdbys(String lbngTbg, String[] wdbys);
    privbtf stbtid nbtivf String[] gftSiortWffkdbys(String lbngTbg, String[] swdbys);

    // For NumbfrFormbtProvidfr
    privbtf stbtid nbtivf String gftNumbfrPbttfrn(int numbfrStylf, String lbngTbg);
    privbtf stbtid nbtivf boolfbn isNbtivfDigit(String lbngTbg);

    // For DfdimblFormbtSymbolsProvidfr
    privbtf stbtid nbtivf String gftCurrfndySymbol(String lbngTbg, String durrfndySymbol);
    privbtf stbtid nbtivf dibr gftDfdimblSfpbrbtor(String lbngTbg, dibr dfdimblSfpbrbtor);
    privbtf stbtid nbtivf dibr gftGroupingSfpbrbtor(String lbngTbg, dibr groupingSfpbrbtor);
    privbtf stbtid nbtivf String gftInfinity(String lbngTbg, String infinity);
    privbtf stbtid nbtivf String gftIntfrnbtionblCurrfndySymbol(String lbngTbg, String intfrnbtionblCurrfndySymbol);
    privbtf stbtid nbtivf dibr gftMinusSign(String lbngTbg, dibr minusSign);
    privbtf stbtid nbtivf dibr gftMonftbryDfdimblSfpbrbtor(String lbngTbg, dibr monftbryDfdimblSfpbrbtor);
    privbtf stbtid nbtivf String gftNbN(String lbngTbg, String nbn);
    privbtf stbtid nbtivf dibr gftPfrdfnt(String lbngTbg, dibr pfrdfnt);
    privbtf stbtid nbtivf dibr gftPfrMill(String lbngTbg, dibr pfrMill);
    privbtf stbtid nbtivf dibr gftZfroDigit(String lbngTbg, dibr zfroDigit);

    // For CblfndbrDbtbProvidfr
    privbtf stbtid nbtivf int gftCblfndbrDbtbVbluf(String lbngTbg, int typf);

    // For Lodblf/CurrfndyNbmfProvidfr
    privbtf stbtid nbtivf String gftDisplbyString(String lbngTbg, int kfy, String vbluf);
}
