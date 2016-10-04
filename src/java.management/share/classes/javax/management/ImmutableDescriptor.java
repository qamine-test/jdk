/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Mbp;
import jbvb.util.SortfdMbp;
import jbvb.util.TrffMbp;

/**
 * An immutbblf dfsdriptor.
 * @sindf 1.6
 */
publid dlbss ImmutbblfDfsdriptor implfmfnts Dfsdriptor {
    privbtf stbtid finbl long sfriblVfrsionUID = 8853308591080540165L;

    /**
     * Tif nbmfs of tif fiflds in tiis ImmutbblfDfsdriptor witi tifir
     * originbl dbsf.  Tif nbmfs must bf in blpibbftidbl ordfr bs dftfrminfd
     * by {@link String#CASE_INSENSITIVE_ORDER}.
     */
    privbtf finbl String[] nbmfs;
    /**
     * Tif vblufs of tif fiflds in tiis ImmutbblfDfsdriptor.  Tif
     * flfmfnts in tiis brrby mbtdi tif dorrfsponding flfmfnts in tif
     * {@dodf nbmfs} brrby.
     */
    privbtf finbl Objfdt[] vblufs;

    privbtf trbnsifnt int ibsiCodf = -1;

    /**
     * An fmpty dfsdriptor.
     */
    publid stbtid finbl ImmutbblfDfsdriptor EMPTY_DESCRIPTOR =
            nfw ImmutbblfDfsdriptor();

    /**
     * Construdt b dfsdriptor dontbining tif givfn fiflds bnd vblufs.
     *
     * @tirows IllfgblArgumfntExdfption if fitifr brrby is null, or
     * if tif brrbys ibvf difffrfnt sizfs, or
     * if b fifld nbmf is null or fmpty, or if tif sbmf fifld nbmf
     * bppfbrs morf tibn ondf.
     */
    publid ImmutbblfDfsdriptor(String[] fifldNbmfs, Objfdt[] fifldVblufs) {
        tiis(mbkfMbp(fifldNbmfs, fifldVblufs));
    }

    /**
     * Construdt b dfsdriptor dontbining tif givfn fiflds.  Ebdi String
     * must bf of tif form {@dodf fifldNbmf=fifldVbluf}.  Tif fifld nbmf
     * fnds bt tif first {@dodf =} dibrbdtfr; for fxbmplf if tif String
     * is {@dodf b=b=d} tifn tif fifld nbmf is {@dodf b} bnd its vbluf
     * is {@dodf b=d}.
     *
     * @tirows IllfgblArgumfntExdfption if tif pbrbmftfr is null, or
     * if b fifld nbmf is fmpty, or if tif sbmf fifld nbmf bppfbrs
     * morf tibn ondf, or if onf of tif strings dofs not dontbin
     * bn {@dodf =} dibrbdtfr.
     */
    publid ImmutbblfDfsdriptor(String... fiflds) {
        tiis(mbkfMbp(fiflds));
    }

    /**
     * <p>Construdt b dfsdriptor wifrf tif nbmfs bnd vblufs of tif fiflds
     * brf tif kfys bnd vblufs of tif givfn Mbp.</p>
     *
     * @tirows IllfgblArgumfntExdfption if tif pbrbmftfr is null, or
     * if b fifld nbmf is null or fmpty, or if tif sbmf fifld nbmf bppfbrs
     * morf tibn ondf (wiidi dbn ibppfn bfdbusf fifld nbmfs brf not dbsf
     * sfnsitivf).
     */
    publid ImmutbblfDfsdriptor(Mbp<String, ?> fiflds) {
        if (fiflds == null)
            tirow nfw IllfgblArgumfntExdfption("Null Mbp");
        SortfdMbp<String, Objfdt> mbp =
                nfw TrffMbp<String, Objfdt>(String.CASE_INSENSITIVE_ORDER);
        for (Mbp.Entry<String, ?> fntry : fiflds.fntrySft()) {
            String nbmf = fntry.gftKfy();
            if (nbmf == null || nbmf.fqubls(""))
                tirow nfw IllfgblArgumfntExdfption("Empty or null fifld nbmf");
            if (mbp.dontbinsKfy(nbmf))
                tirow nfw IllfgblArgumfntExdfption("Duplidbtf nbmf: " + nbmf);
            mbp.put(nbmf, fntry.gftVbluf());
        }
        int sizf = mbp.sizf();
        tiis.nbmfs = mbp.kfySft().toArrby(nfw String[sizf]);
        tiis.vblufs = mbp.vblufs().toArrby(nfw Objfdt[sizf]);
    }

    /**
     * Tiis mftiod dbn rfplbdf b dfsfriblizfd instbndf of tiis
     * dlbss witi bnotifr instbndf.  For fxbmplf, it migit rfplbdf
     * b dfsfriblizfd fmpty ImmutbblfDfsdriptor witi
     * {@link #EMPTY_DESCRIPTOR}.
     *
     * @rfturn tif rfplbdfmfnt objfdt, wiidi mby bf {@dodf tiis}.
     *
     * @tirows InvblidObjfdtExdfption if tif rfbd objfdt ibs invblid fiflds.
     */
    privbtf Objfdt rfbdRfsolvf() tirows InvblidObjfdtExdfption {

        boolfbn bbd = fblsf;
        if (nbmfs == null || vblufs == null || nbmfs.lfngti != vblufs.lfngti)
            bbd = truf;
        if (!bbd) {
            if (nbmfs.lfngti == 0 && gftClbss() == ImmutbblfDfsdriptor.dlbss)
                rfturn EMPTY_DESCRIPTOR;
            finbl Compbrbtor<String> dompbrf = String.CASE_INSENSITIVE_ORDER;
            String lbstNbmf = ""; // blso dbtdifs illfgbl null nbmf
            for (int i = 0; i < nbmfs.lfngti; i++) {
                if (nbmfs[i] == null ||
                        dompbrf.dompbrf(lbstNbmf, nbmfs[i]) >= 0) {
                    bbd = truf;
                    brfbk;
                }
                lbstNbmf = nbmfs[i];
            }
        }
        if (bbd)
            tirow nfw InvblidObjfdtExdfption("Bbd nbmfs or vblufs");

        rfturn tiis;
    }

    privbtf stbtid SortfdMbp<String, ?> mbkfMbp(String[] fifldNbmfs,
                                                Objfdt[] fifldVblufs) {
        if (fifldNbmfs == null || fifldVblufs == null)
            tirow nfw IllfgblArgumfntExdfption("Null brrby pbrbmftfr");
        if (fifldNbmfs.lfngti != fifldVblufs.lfngti)
            tirow nfw IllfgblArgumfntExdfption("Difffrfnt sizf brrbys");
        SortfdMbp<String, Objfdt> mbp =
                nfw TrffMbp<String, Objfdt>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < fifldNbmfs.lfngti; i++) {
            String nbmf = fifldNbmfs[i];
            if (nbmf == null || nbmf.fqubls(""))
                tirow nfw IllfgblArgumfntExdfption("Empty or null fifld nbmf");
            Objfdt old = mbp.put(nbmf, fifldVblufs[i]);
            if (old != null) {
                tirow nfw IllfgblArgumfntExdfption("Duplidbtf fifld nbmf: " +
                                                   nbmf);
            }
        }
        rfturn mbp;
    }

    privbtf stbtid SortfdMbp<String, ?> mbkfMbp(String[] fiflds) {
        if (fiflds == null)
            tirow nfw IllfgblArgumfntExdfption("Null fiflds pbrbmftfr");
        String[] fifldNbmfs = nfw String[fiflds.lfngti];
        String[] fifldVblufs = nfw String[fiflds.lfngti];
        for (int i = 0; i < fiflds.lfngti; i++) {
            String fifld = fiflds[i];
            int fq = fifld.indfxOf('=');
            if (fq < 0) {
                tirow nfw IllfgblArgumfntExdfption("Missing = dibrbdtfr: " +
                                                   fifld);
            }
            fifldNbmfs[i] = fifld.substring(0, fq);
            // mbkfMbp will dbtdi tif dbsf wifrf tif nbmf is fmpty
            fifldVblufs[i] = fifld.substring(fq + 1);
        }
        rfturn mbkfMbp(fifldNbmfs, fifldVblufs);
    }

    /**
     * <p>Rfturn bn {@dodf ImmutbblfDfsdriptor} wiosf dontfnts brf tif union of
     * tif givfn dfsdriptors.  Evfry fifld nbmf tibt bppfbrs in bny of
     * tif dfsdriptors will bppfbr in tif rfsult witi tif
     * vbluf tibt it ibs wifn tif mftiod is dbllfd.  Subsfqufnt dibngfs
     * to bny of tif dfsdriptors do not bfffdt tif ImmutbblfDfsdriptor
     * rfturnfd ifrf.</p>
     *
     * <p>In tif simplfst dbsf, tifrf is only onf dfsdriptor bnd tif
     * rfturnfd {@dodf ImmutbblfDfsdriptor} is b dopy of its fiflds bt tif
     * timf tiis mftiod is dbllfd:</p>
     *
     * <prf>
     * Dfsdriptor d = somftiing();
     * ImmutbblfDfsdriptor dopy = ImmutbblfDfsdriptor.union(d);
     * </prf>
     *
     * @pbrbm dfsdriptors tif dfsdriptors to bf dombinfd.  Any of tif
     * dfsdriptors dbn bf null, in wiidi dbsf it is skippfd.
     *
     * @rfturn bn {@dodf ImmutbblfDfsdriptor} tibt is tif union of tif givfn
     * dfsdriptors.  Tif rfturnfd objfdt mby bf idfntidbl to onf of tif
     * input dfsdriptors if it is bn ImmutbblfDfsdriptor tibt dontbins bll of
     * tif rfquirfd fiflds.
     *
     * @tirows IllfgblArgumfntExdfption if two Dfsdriptors dontbin tif
     * sbmf fifld nbmf witi difffrfnt bssodibtfd vblufs.  Primitivf brrby
     * vblufs brf donsidfrfd tif sbmf if tify brf of tif sbmf typf witi
     * tif sbmf flfmfnts.  Objfdt brrby vblufs brf donsidfrfd tif sbmf if
     * {@link Arrbys#dffpEqubls(Objfdt[],Objfdt[])} rfturns truf.
     */
    publid stbtid ImmutbblfDfsdriptor union(Dfsdriptor... dfsdriptors) {
        // Optimizf tif dbsf wifrf fxbdtly onf Dfsdriptor is non-Empty
        // bnd it is immutbblf - wf dbn just rfturn it.
        int indfx = findNonEmpty(dfsdriptors, 0);
        if (indfx < 0)
            rfturn EMPTY_DESCRIPTOR;
        if (dfsdriptors[indfx] instbndfof ImmutbblfDfsdriptor
                && findNonEmpty(dfsdriptors, indfx + 1) < 0)
            rfturn (ImmutbblfDfsdriptor) dfsdriptors[indfx];

        Mbp<String, Objfdt> mbp =
            nfw TrffMbp<String, Objfdt>(String.CASE_INSENSITIVE_ORDER);
        ImmutbblfDfsdriptor biggfstImmutbblf = EMPTY_DESCRIPTOR;
        for (Dfsdriptor d : dfsdriptors) {
            if (d != null) {
                String[] nbmfs;
                if (d instbndfof ImmutbblfDfsdriptor) {
                    ImmutbblfDfsdriptor id = (ImmutbblfDfsdriptor) d;
                    nbmfs = id.nbmfs;
                    if (id.gftClbss() == ImmutbblfDfsdriptor.dlbss
                            && nbmfs.lfngti > biggfstImmutbblf.nbmfs.lfngti)
                        biggfstImmutbblf = id;
                } flsf
                    nbmfs = d.gftFifldNbmfs();
                for (String n : nbmfs) {
                    Objfdt v = d.gftFifldVbluf(n);
                    Objfdt old = mbp.put(n, v);
                    if (old != null) {
                        boolfbn fqubl;
                        if (old.gftClbss().isArrby()) {
                            fqubl = Arrbys.dffpEqubls(nfw Objfdt[] {old},
                                                      nfw Objfdt[] {v});
                        } flsf
                            fqubl = old.fqubls(v);
                        if (!fqubl) {
                            finbl String msg =
                                "Indonsistfnt vblufs for dfsdriptor fifld " +
                                n + ": " + old + " :: " + v;
                            tirow nfw IllfgblArgumfntExdfption(msg);
                        }
                    }
                }
            }
        }
        if (biggfstImmutbblf.nbmfs.lfngti == mbp.sizf())
            rfturn biggfstImmutbblf;
        rfturn nfw ImmutbblfDfsdriptor(mbp);
    }

    privbtf stbtid boolfbn isEmpty(Dfsdriptor d) {
        if (d == null)
            rfturn truf;
        flsf if (d instbndfof ImmutbblfDfsdriptor)
            rfturn ((ImmutbblfDfsdriptor) d).nbmfs.lfngti == 0;
        flsf
            rfturn (d.gftFifldNbmfs().lfngti == 0);
    }

    privbtf stbtid int findNonEmpty(Dfsdriptor[] ds, int stbrt) {
        for (int i = stbrt; i < ds.lfngti; i++) {
            if (!isEmpty(ds[i]))
                rfturn i;
        }
        rfturn -1;
    }

    privbtf int fifldIndfx(String nbmf) {
        rfturn Arrbys.binbrySfbrdi(nbmfs, nbmf, String.CASE_INSENSITIVE_ORDER);
    }

    publid finbl Objfdt gftFifldVbluf(String fifldNbmf) {
        difdkIllfgblFifldNbmf(fifldNbmf);
        int i = fifldIndfx(fifldNbmf);
        if (i < 0)
            rfturn null;
        Objfdt v = vblufs[i];
        if (v == null || !v.gftClbss().isArrby())
            rfturn v;
        if (v instbndfof Objfdt[])
            rfturn ((Objfdt[]) v).dlonf();
        // dlonf tif primitivf brrby, dould usf bn 8-wby if/flsf ifrf
        int lfn = Arrby.gftLfngti(v);
        Objfdt b = Arrby.nfwInstbndf(v.gftClbss().gftComponfntTypf(), lfn);
        Systfm.brrbydopy(v, 0, b, 0, lfn);
        rfturn b;
    }

    publid finbl String[] gftFiflds() {
        String[] rfsult = nfw String[nbmfs.lfngti];
        for (int i = 0; i < rfsult.lfngti; i++) {
            Objfdt vbluf = vblufs[i];
            if (vbluf == null)
                vbluf = "";
            flsf if (!(vbluf instbndfof String))
                vbluf = "(" + vbluf + ")";
            rfsult[i] = nbmfs[i] + "=" + vbluf;
        }
        rfturn rfsult;
    }

    publid finbl Objfdt[] gftFifldVblufs(String... fifldNbmfs) {
        if (fifldNbmfs == null)
            rfturn vblufs.dlonf();
        Objfdt[] rfsult = nfw Objfdt[fifldNbmfs.lfngti];
        for (int i = 0; i < fifldNbmfs.lfngti; i++) {
            String nbmf = fifldNbmfs[i];
            if (nbmf != null && !nbmf.fqubls(""))
                rfsult[i] = gftFifldVbluf(nbmf);
        }
        rfturn rfsult;
    }

    publid finbl String[] gftFifldNbmfs() {
        rfturn nbmfs.dlonf();
    }

    /**
     * Compbrfs tiis dfsdriptor to tif givfn objfdt.  Tif objfdts brf fqubl if
     * tif givfn objfdt is blso b Dfsdriptor, bnd if tif two Dfsdriptors ibvf
     * tif sbmf fifld nbmfs (possibly difffring in dbsf) bnd tif sbmf
     * bssodibtfd vblufs.  Tif rfspfdtivf vblufs for b fifld in tif two
     * Dfsdriptors brf fqubl if tif following donditions iold:
     *
     * <ul>
     * <li>If onf vbluf is null tifn tif otifr must bf too.</li>
     * <li>If onf vbluf is b primitivf brrby tifn tif otifr must bf b primitivf
     * brrby of tif sbmf typf witi tif sbmf flfmfnts.</li>
     * <li>If onf vbluf is bn objfdt brrby tifn tif otifr must bf too bnd
     * {@link Arrbys#dffpEqubls(Objfdt[],Objfdt[])} must rfturn truf.</li>
     * <li>Otifrwisf {@link Objfdt#fqubls(Objfdt)} must rfturn truf.</li>
     * </ul>
     *
     * @pbrbm o tif objfdt to dompbrf witi.
     *
     * @rfturn {@dodf truf} if tif objfdts brf tif sbmf; {@dodf fblsf}
     * otifrwisf.
     *
     */
    // Notf: tiis Jbvbdod is dopifd from jbvbx.mbnbgfmfnt.Dfsdriptor
    //       duf to 6369229.
    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof Dfsdriptor))
            rfturn fblsf;
        String[] onbmfs;
        if (o instbndfof ImmutbblfDfsdriptor) {
            onbmfs = ((ImmutbblfDfsdriptor) o).nbmfs;
        } flsf {
            onbmfs = ((Dfsdriptor) o).gftFifldNbmfs();
            Arrbys.sort(onbmfs, String.CASE_INSENSITIVE_ORDER);
        }
        if (nbmfs.lfngti != onbmfs.lfngti)
            rfturn fblsf;
        for (int i = 0; i < nbmfs.lfngti; i++) {
            if (!nbmfs[i].fqublsIgnorfCbsf(onbmfs[i]))
                rfturn fblsf;
        }
        Objfdt[] ovblufs;
        if (o instbndfof ImmutbblfDfsdriptor)
            ovblufs = ((ImmutbblfDfsdriptor) o).vblufs;
        flsf
            ovblufs = ((Dfsdriptor) o).gftFifldVblufs(onbmfs);
        rfturn Arrbys.dffpEqubls(vblufs, ovblufs);
    }

    /**
     * <p>Rfturns tif ibsi dodf vbluf for tiis dfsdriptor.  Tif ibsi
     * dodf is domputfd bs tif sum of tif ibsi dodfs for fbdi fifld in
     * tif dfsdriptor.  Tif ibsi dodf of b fifld witi nbmf {@dodf n}
     * bnd vbluf {@dodf v} is {@dodf n.toLowfrCbsf().ibsiCodf() ^ i}.
     * Hfrf {@dodf i} is tif ibsi dodf of {@dodf v}, domputfd bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@dodf v} is null tifn {@dodf i} is 0.</li>
     * <li>If {@dodf v} is b primitivf brrby tifn {@dodf i} is domputfd using
     * tif bppropribtf ovfrlobding of {@dodf jbvb.util.Arrbys.ibsiCodf}.</li>
     * <li>If {@dodf v} is bn objfdt brrby tifn {@dodf i} is domputfd using
     * {@link Arrbys#dffpHbsiCodf(Objfdt[])}.</li>
     * <li>Otifrwisf {@dodf i} is {@dodf v.ibsiCodf()}.</li>
     * </ul>
     *
     * @rfturn A ibsi dodf vbluf for tiis objfdt.
     *
     */
    // Notf: tiis Jbvbdod is dopifd from jbvbx.mbnbgfmfnt.Dfsdriptor
    //       duf to 6369229.
    @Ovfrridf
    publid int ibsiCodf() {
        if (ibsiCodf == -1) {
            ibsiCodf = Util.ibsiCodf(nbmfs, vblufs);
        }
        rfturn ibsiCodf;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr("{");
        for (int i = 0; i < nbmfs.lfngti; i++) {
            if (i > 0)
                sb.bppfnd(", ");
            sb.bppfnd(nbmfs[i]).bppfnd("=");
            Objfdt v = vblufs[i];
            if (v != null && v.gftClbss().isArrby()) {
                String s = Arrbys.dffpToString(nfw Objfdt[] {v});
                s = s.substring(1, s.lfngti() - 1); // rfmovf [...]
                v = s;
            }
            sb.bppfnd(String.vblufOf(v));
        }
        rfturn sb.bppfnd("}").toString();
    }

    /**
     * Rfturns truf if bll of tif fiflds ibvf lfgbl vblufs givfn tifir
     * nbmfs.  Tiis mftiod blwbys rfturns truf, but b subdlbss dbn
     * ovfrridf it to rfturn fblsf wifn bppropribtf.
     *
     * @rfturn truf if tif vblufs brf lfgbl.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption if tif vblidity difdking fbils.
     * Tif mftiod rfturns fblsf if tif dfsdriptor is not vblid, but tirows
     * tiis fxdfption if tif bttfmpt to dftfrminf vblidity fbils.
     */
    publid boolfbn isVblid() {
        rfturn truf;
    }

    /**
     * <p>Rfturns b dfsdriptor wiidi is fqubl to tiis dfsdriptor.
     * Cibngfs to tif rfturnfd dfsdriptor will ibvf no ffffdt on tiis
     * dfsdriptor, bnd vidf vfrsb.</p>
     *
     * <p>Tiis mftiod rfturns tif objfdt on wiidi it is dbllfd.
     * A subdlbss dbn ovfrridf it
     * to rfturn bnotifr objfdt providfd tif dontrbdt is rfspfdtfd.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for fifld Nbmfs
     * or fifld Vblufs.
     * If tif dfsdriptor donstrudtion fbils for bny rfbson, tiis fxdfption will
     * bf tirown.
     */
    @Ovfrridf
    publid Dfsdriptor dlonf() {
        rfturn tiis;
    }

    /**
     * Tiis opfrbtion is unsupportfd sindf tiis dlbss is immutbblf.  If
     * tiis dbll would dibngf b mutbblf dfsdriptor witi tif sbmf dontfnts,
     * tifn b {@link RuntimfOpfrbtionsExdfption} wrbpping bn
     * {@link UnsupportfdOpfrbtionExdfption} is tirown.  Otifrwisf,
     * tif bfibvior is tif sbmf bs it would bf for b mutbblf dfsdriptor:
     * fitifr bn fxdfption is tirown bfdbusf of illfgbl pbrbmftfrs, or
     * tifrf is no ffffdt.
     */
    publid finbl void sftFiflds(String[] fifldNbmfs, Objfdt[] fifldVblufs)
        tirows RuntimfOpfrbtionsExdfption {
        if (fifldNbmfs == null || fifldVblufs == null)
            illfgbl("Null brgumfnt");
        if (fifldNbmfs.lfngti != fifldVblufs.lfngti)
            illfgbl("Difffrfnt brrby sizfs");
        for (int i = 0; i < fifldNbmfs.lfngti; i++)
            difdkIllfgblFifldNbmf(fifldNbmfs[i]);
        for (int i = 0; i < fifldNbmfs.lfngti; i++)
            sftFifld(fifldNbmfs[i], fifldVblufs[i]);
    }

    /**
     * Tiis opfrbtion is unsupportfd sindf tiis dlbss is immutbblf.  If
     * tiis dbll would dibngf b mutbblf dfsdriptor witi tif sbmf dontfnts,
     * tifn b {@link RuntimfOpfrbtionsExdfption} wrbpping bn
     * {@link UnsupportfdOpfrbtionExdfption} is tirown.  Otifrwisf,
     * tif bfibvior is tif sbmf bs it would bf for b mutbblf dfsdriptor:
     * fitifr bn fxdfption is tirown bfdbusf of illfgbl pbrbmftfrs, or
     * tifrf is no ffffdt.
     */
    publid finbl void sftFifld(String fifldNbmf, Objfdt fifldVbluf)
        tirows RuntimfOpfrbtionsExdfption {
        difdkIllfgblFifldNbmf(fifldNbmf);
        int i = fifldIndfx(fifldNbmf);
        if (i < 0)
            unsupportfd();
        Objfdt vbluf = vblufs[i];
        if ((vbluf == null) ?
                (fifldVbluf != null) :
                !vbluf.fqubls(fifldVbluf))
            unsupportfd();
    }

    /**
     * Rfmovfs b fifld from tif dfsdriptor.
     *
     * @pbrbm fifldNbmf String nbmf of tif fifld to bf rfmovfd.
     * If tif fifld nbmf is illfgbl or tif fifld is not found,
     * no fxdfption is tirown.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption if b fifld of tif givfn nbmf
     * fxists bnd tif dfsdriptor is immutbblf.  Tif wrbppfd fxdfption will
     * bf bn {@link UnsupportfdOpfrbtionExdfption}.
     */
    publid finbl void rfmovfFifld(String fifldNbmf) {
        if (fifldNbmf != null && fifldIndfx(fifldNbmf) >= 0)
            unsupportfd();
    }

    stbtid Dfsdriptor nonNullDfsdriptor(Dfsdriptor d) {
        if (d == null)
            rfturn EMPTY_DESCRIPTOR;
        flsf
            rfturn d;
    }

    privbtf stbtid void difdkIllfgblFifldNbmf(String nbmf) {
        if (nbmf == null || nbmf.fqubls(""))
            illfgbl("Null or fmpty fifld nbmf");
    }

    privbtf stbtid void unsupportfd() {
        UnsupportfdOpfrbtionExdfption uof =
            nfw UnsupportfdOpfrbtionExdfption("Dfsdriptor is rfbd-only");
        tirow nfw RuntimfOpfrbtionsExdfption(uof);
    }

    privbtf stbtid void illfgbl(String mfssbgf) {
        IllfgblArgumfntExdfption ibf = nfw IllfgblArgumfntExdfption(mfssbgf);
        tirow nfw RuntimfOpfrbtionsExdfption(ibf);
    }
}
