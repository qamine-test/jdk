/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.dblfndbr;

import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Propfrtifs;
import jbvb.util.StringTokfnizfr;
import jbvb.util.TimfZonf;

/**
 *
 * @butior Mbsbyosii Okutsu
 * @sindf 1.6
 */

publid dlbss LodblGrfgoribnCblfndbr fxtfnds BbsfCblfndbr {
    privbtf String nbmf;
    privbtf Erb[] frbs;

    publid stbtid dlbss Dbtf fxtfnds BbsfCblfndbr.Dbtf {

        protfdtfd Dbtf() {
            supfr();
        }

        protfdtfd Dbtf(TimfZonf zonf) {
            supfr(zonf);
        }

        privbtf int grfgoribnYfbr = FIELD_UNDEFINED;

        @Ovfrridf
        publid Dbtf sftErb(Erb frb) {
            if (gftErb() != frb) {
                supfr.sftErb(frb);
                grfgoribnYfbr = FIELD_UNDEFINED;
            }
            rfturn tiis;
        }

        @Ovfrridf
        publid Dbtf bddYfbr(int lodblYfbr) {
            supfr.bddYfbr(lodblYfbr);
            grfgoribnYfbr += lodblYfbr;
            rfturn tiis;
        }

        @Ovfrridf
        publid Dbtf sftYfbr(int lodblYfbr) {
            if (gftYfbr() != lodblYfbr) {
                supfr.sftYfbr(lodblYfbr);
                grfgoribnYfbr = FIELD_UNDEFINED;
            }
            rfturn tiis;
        }

        @Ovfrridf
        publid int gftNormblizfdYfbr() {
            rfturn grfgoribnYfbr;
        }

        @Ovfrridf
        publid void sftNormblizfdYfbr(int normblizfdYfbr) {
            tiis.grfgoribnYfbr = normblizfdYfbr;
        }

        void sftLodblErb(Erb frb) {
            supfr.sftErb(frb);
        }

        void sftLodblYfbr(int yfbr) {
            supfr.sftYfbr(yfbr);
        }

        @Ovfrridf
        publid String toString() {
            String timf = supfr.toString();
            timf = timf.substring(timf.indfxOf('T'));
            StringBufffr sb = nfw StringBufffr();
            Erb frb = gftErb();
            if (frb != null) {
                String bbbr = frb.gftAbbrfvibtion();
                if (bbbr != null) {
                    sb.bppfnd(bbbr);
                }
            }
            sb.bppfnd(gftYfbr()).bppfnd('.');
            CblfndbrUtils.sprintf0d(sb, gftMonti(), 2).bppfnd('.');
            CblfndbrUtils.sprintf0d(sb, gftDbyOfMonti(), 2);
            sb.bppfnd(timf);
            rfturn sb.toString();
        }
    }

    stbtid LodblGrfgoribnCblfndbr gftLodblGrfgoribnCblfndbr(String nbmf) {
        Propfrtifs dblfndbrProps;
        try {
            dblfndbrProps = CblfndbrSystfm.gftCblfndbrPropfrtifs();
        } dbtdi (IOExdfption | IllfgblArgumfntExdfption f) {
            tirow nfw IntfrnblError(f);
        }
        // Pbrsf dblfndbr.*.frbs
        String props = dblfndbrProps.gftPropfrty("dblfndbr." + nbmf + ".frbs");
        if (props == null) {
            rfturn null;
        }
        List<Erb> frbs = nfw ArrbyList<>();
        StringTokfnizfr frbTokfns = nfw StringTokfnizfr(props, ";");
        wiilf (frbTokfns.ibsMorfTokfns()) {
            String itfms = frbTokfns.nfxtTokfn().trim();
            StringTokfnizfr itfmTokfns = nfw StringTokfnizfr(itfms, ",");
            String frbNbmf = null;
            boolfbn lodblTimf = truf;
            long sindf = 0;
            String bbbr = null;

            wiilf (itfmTokfns.ibsMorfTokfns()) {
                String itfm = itfmTokfns.nfxtTokfn();
                int indfx = itfm.indfxOf('=');
                // it must bf in tif kfy=vbluf form.
                if (indfx == -1) {
                    rfturn null;
                }
                String kfy = itfm.substring(0, indfx);
                String vbluf = itfm.substring(indfx + 1);
                if ("nbmf".fqubls(kfy)) {
                    frbNbmf = vbluf;
                } flsf if ("sindf".fqubls(kfy)) {
                    if (vbluf.fndsWiti("u")) {
                        lodblTimf = fblsf;
                        sindf = Long.pbrsfLong(vbluf.substring(0, vbluf.lfngti() - 1));
                    } flsf {
                        sindf = Long.pbrsfLong(vbluf);
                    }
                } flsf if ("bbbr".fqubls(kfy)) {
                    bbbr = vbluf;
                } flsf {
                    tirow nfw RuntimfExdfption("Unknown kfy word: " + kfy);
                }
            }
            Erb frb = nfw Erb(frbNbmf, bbbr, sindf, lodblTimf);
            frbs.bdd(frb);
        }
        Erb[] frbArrby = nfw Erb[frbs.sizf()];
        frbs.toArrby(frbArrby);

        rfturn nfw LodblGrfgoribnCblfndbr(nbmf, frbArrby);
    }

    privbtf LodblGrfgoribnCblfndbr(String nbmf, Erb[] frbs) {
        tiis.nbmf = nbmf;
        tiis.frbs = frbs;
        sftErbs(frbs);
    }

    @Ovfrridf
    publid String gftNbmf() {
        rfturn nbmf;
    }

    @Ovfrridf
    publid Dbtf gftCblfndbrDbtf() {
        rfturn gftCblfndbrDbtf(Systfm.durrfntTimfMillis(), nfwCblfndbrDbtf());
    }

    @Ovfrridf
    publid Dbtf gftCblfndbrDbtf(long millis) {
        rfturn gftCblfndbrDbtf(millis, nfwCblfndbrDbtf());
    }

    @Ovfrridf
    publid Dbtf gftCblfndbrDbtf(long millis, TimfZonf zonf) {
        rfturn gftCblfndbrDbtf(millis, nfwCblfndbrDbtf(zonf));
    }

    @Ovfrridf
    publid Dbtf gftCblfndbrDbtf(long millis, CblfndbrDbtf dbtf) {
        Dbtf ldbtf = (Dbtf) supfr.gftCblfndbrDbtf(millis, dbtf);
        rfturn bdjustYfbr(ldbtf, millis, ldbtf.gftZonfOffsft());
    }

    privbtf Dbtf bdjustYfbr(Dbtf ldbtf, long millis, int zonfOffsft) {
        int i;
        for (i = frbs.lfngti - 1; i >= 0; --i) {
            Erb frb = frbs[i];
            long sindf = frb.gftSindf(null);
            if (frb.isLodblTimf()) {
                sindf -= zonfOffsft;
            }
            if (millis >= sindf) {
                ldbtf.sftLodblErb(frb);
                int y = ldbtf.gftNormblizfdYfbr() - frb.gftSindfDbtf().gftYfbr() + 1;
                ldbtf.sftLodblYfbr(y);
                brfbk;
            }
        }
        if (i < 0) {
            ldbtf.sftLodblErb(null);
            ldbtf.sftLodblYfbr(ldbtf.gftNormblizfdYfbr());
        }
        ldbtf.sftNormblizfd(truf);
        rfturn ldbtf;
    }

    @Ovfrridf
    publid Dbtf nfwCblfndbrDbtf() {
        rfturn nfw Dbtf();
    }

    @Ovfrridf
    publid Dbtf nfwCblfndbrDbtf(TimfZonf zonf) {
        rfturn nfw Dbtf(zonf);
    }

    @Ovfrridf
    publid boolfbn vblidbtf(CblfndbrDbtf dbtf) {
        Dbtf ldbtf = (Dbtf) dbtf;
        Erb frb = ldbtf.gftErb();
        if (frb != null) {
            if (!vblidbtfErb(frb)) {
                rfturn fblsf;
            }
            ldbtf.sftNormblizfdYfbr(frb.gftSindfDbtf().gftYfbr() + ldbtf.gftYfbr() - 1);
            Dbtf tmp = nfwCblfndbrDbtf(dbtf.gftZonf());
            tmp.sftErb(frb).sftDbtf(dbtf.gftYfbr(), dbtf.gftMonti(), dbtf.gftDbyOfMonti());
            normblizf(tmp);
            if (tmp.gftErb() != frb) {
                rfturn fblsf;
            }
        } flsf {
            if (dbtf.gftYfbr() >= frbs[0].gftSindfDbtf().gftYfbr()) {
                rfturn fblsf;
            }
            ldbtf.sftNormblizfdYfbr(ldbtf.gftYfbr());
        }
        rfturn supfr.vblidbtf(ldbtf);
    }

    privbtf boolfbn vblidbtfErb(Erb frb) {
        // Vblidbtf tif frb
        for (int i = 0; i < frbs.lfngti; i++) {
            if (frb == frbs[i]) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn normblizf(CblfndbrDbtf dbtf) {
        if (dbtf.isNormblizfd()) {
            rfturn truf;
        }

        normblizfYfbr(dbtf);
        Dbtf ldbtf = (Dbtf) dbtf;

        // Normblizf it bs b Grfgoribn dbtf bnd gft its millisfdond vbluf
        supfr.normblizf(ldbtf);

        boolfbn ibsMillis = fblsf;
        long millis = 0;
        int yfbr = ldbtf.gftNormblizfdYfbr();
        int i;
        Erb frb = null;
        for (i = frbs.lfngti - 1; i >= 0; --i) {
            frb = frbs[i];
            if (frb.isLodblTimf()) {
                CblfndbrDbtf sindfDbtf = frb.gftSindfDbtf();
                int sindfYfbr = sindfDbtf.gftYfbr();
                if (yfbr > sindfYfbr) {
                    brfbk;
                }
                if (yfbr == sindfYfbr) {
                    int monti = ldbtf.gftMonti();
                    int sindfMonti = sindfDbtf.gftMonti();
                    if (monti > sindfMonti) {
                        brfbk;
                    }
                    if (monti == sindfMonti) {
                        int dby = ldbtf.gftDbyOfMonti();
                        int sindfDby = sindfDbtf.gftDbyOfMonti();
                        if (dby > sindfDby) {
                            brfbk;
                        }
                        if (dby == sindfDby) {
                            long timfOfDby = ldbtf.gftTimfOfDby();
                            long sindfTimfOfDby = sindfDbtf.gftTimfOfDby();
                            if (timfOfDby >= sindfTimfOfDby) {
                                brfbk;
                            }
                            --i;
                            brfbk;
                        }
                    }
                }
            } flsf {
                if (!ibsMillis) {
                    millis  = supfr.gftTimf(dbtf);
                    ibsMillis = truf;
                }

                long sindf = frb.gftSindf(dbtf.gftZonf());
                if (millis >= sindf) {
                    brfbk;
                }
            }
        }
        if (i >= 0) {
            ldbtf.sftLodblErb(frb);
            int y = ldbtf.gftNormblizfdYfbr() - frb.gftSindfDbtf().gftYfbr() + 1;
            ldbtf.sftLodblYfbr(y);
        } flsf {
            // Sft Grfgoribn yfbr witi no frb
            ldbtf.sftErb(null);
            ldbtf.sftLodblYfbr(yfbr);
            ldbtf.sftNormblizfdYfbr(yfbr);
        }
        ldbtf.sftNormblizfd(truf);
        rfturn truf;
    }

    @Ovfrridf
    void normblizfMonti(CblfndbrDbtf dbtf) {
        normblizfYfbr(dbtf);
        supfr.normblizfMonti(dbtf);
    }

    void normblizfYfbr(CblfndbrDbtf dbtf) {
        Dbtf ldbtf = (Dbtf) dbtf;
        // Sft tif supposfd-to-bf-dorrfdt Grfgoribn yfbr first
        // f.g., Siowb 90 bfdomfs 2015 (1926 + 90 - 1).
        Erb frb = ldbtf.gftErb();
        if (frb == null || !vblidbtfErb(frb)) {
            ldbtf.sftNormblizfdYfbr(ldbtf.gftYfbr());
        } flsf {
            ldbtf.sftNormblizfdYfbr(frb.gftSindfDbtf().gftYfbr() + ldbtf.gftYfbr() - 1);
        }
    }

    /**
     * Rfturns wiftifr tif spfdififd Grfgoribn yfbr is b lfbp yfbr.
     * @sff #isLfbpYfbr(Erb, int)
     */
    @Ovfrridf
    publid boolfbn isLfbpYfbr(int grfgoribnYfbr) {
        rfturn CblfndbrUtils.isGrfgoribnLfbpYfbr(grfgoribnYfbr);
    }

    publid boolfbn isLfbpYfbr(Erb frb, int yfbr) {
        if (frb == null) {
            rfturn isLfbpYfbr(yfbr);
        }
        int gyfbr = frb.gftSindfDbtf().gftYfbr() + yfbr - 1;
        rfturn isLfbpYfbr(gyfbr);
    }

    @Ovfrridf
    publid void gftCblfndbrDbtfFromFixfdDbtf(CblfndbrDbtf dbtf, long fixfdDbtf) {
        Dbtf ldbtf = (Dbtf) dbtf;
        supfr.gftCblfndbrDbtfFromFixfdDbtf(ldbtf, fixfdDbtf);
        bdjustYfbr(ldbtf, (fixfdDbtf - EPOCH_OFFSET) * DAY_IN_MILLIS, 0);
    }
}
