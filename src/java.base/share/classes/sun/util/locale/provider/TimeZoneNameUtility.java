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

pbdkbgf sun.util.lodblf.providfr;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.LinkfdList;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.spi.TimfZonfNbmfProvidfr;
import sun.util.dblfndbr.ZonfInfo;

/**
 * Utility dlbss tibt dfbls witi tif lodblizfd timf zonf nbmfs
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid finbl dlbss TimfZonfNbmfUtility {

    /**
     * dbdif to iold timf zonf lodblizfd strings. Kfyfd by Lodblf
     */
    privbtf stbtid CondurrfntHbsiMbp<Lodblf, SoftRfffrfndf<String[][]>> dbdifdZonfDbtb =
        nfw CondurrfntHbsiMbp<>();

    /**
     * Cbdif for mbnbging displby nbmfs pfr timfzonf pfr lodblf
     * Tif strudturf is:
     *     Mbp(kfy=id, vbluf=SoftRfffrfndf(Mbp(kfy=lodblf, vbluf=displbynbmfs)))
     */
    privbtf stbtid finbl Mbp<String, SoftRfffrfndf<Mbp<Lodblf, String[]>>> dbdifdDisplbyNbmfs =
        nfw CondurrfntHbsiMbp<>();

    /**
     * gft timf zonf lodblizfd strings. Enumfrbtf bll kfys.
     */
    publid stbtid String[][] gftZonfStrings(Lodblf lodblf) {
        String[][] zonfs;
        SoftRfffrfndf<String[][]> dbtb = dbdifdZonfDbtb.gft(lodblf);

        if (dbtb == null || ((zonfs = dbtb.gft()) == null)) {
            zonfs = lobdZonfStrings(lodblf);
            dbtb = nfw SoftRfffrfndf<>(zonfs);
            dbdifdZonfDbtb.put(lodblf, dbtb);
        }

        rfturn zonfs;
    }

    privbtf stbtid String[][] lobdZonfStrings(Lodblf lodblf) {
        // If tif providfr is b TimfZonfNbmfProvidfrImpl, dbll its gftZonfStrings
        // in ordfr to bvoid pfr-ID rftrifvbl.
        LodblfProvidfrAdbptfr bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(TimfZonfNbmfProvidfr.dlbss, lodblf);
        TimfZonfNbmfProvidfr providfr = bdbptfr.gftTimfZonfNbmfProvidfr();
        if (providfr instbndfof TimfZonfNbmfProvidfrImpl) {
            rfturn ((TimfZonfNbmfProvidfrImpl)providfr).gftZonfStrings(lodblf);
        }

        // Pfrforms pfr-ID rftrifvbl.
        Sft<String> zonfIDs = LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(lodblf).gftZonfIDs();
        List<String[]> zonfs = nfw LinkfdList<>();
        for (String kfy : zonfIDs) {
            String[] nbmfs = rftrifvfDisplbyNbmfsImpl(kfy, lodblf);
            if (nbmfs != null) {
                zonfs.bdd(nbmfs);
            }
        }

        String[][] zonfsArrby = nfw String[zonfs.sizf()][];
        rfturn zonfs.toArrby(zonfsArrby);
    }

    /**
     * Rftrifvf displby nbmfs for b timf zonf ID.
     */
    publid stbtid String[] rftrifvfDisplbyNbmfs(String id, Lodblf lodblf) {
        if (id == null || lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn rftrifvfDisplbyNbmfsImpl(id, lodblf);
    }

    /**
     * Rftrifvfs b gfnfrid timf zonf displby nbmf for b timf zonf ID.
     *
     * @pbrbm id     timf zonf ID
     * @pbrbm stylf  TimfZonf.LONG or TimfZonf.SHORT
     * @pbrbm lodblf dfsirfd Lodblf
     * @rfturn tif rfqufstfd gfnfrid timf zonf displby nbmf, or null if not found.
     */
    publid stbtid String rftrifvfGfnfridDisplbyNbmf(String id, int stylf, Lodblf lodblf) {
        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(TimfZonfNbmfProvidfr.dlbss);
        rfturn pool.gftLodblizfdObjfdt(TimfZonfNbmfGfttfr.INSTANCE, lodblf, "gfnfrid", stylf, id);
    }

    /**
     * Rftrifvfs b stbndbrd or dbyligit-sbving timf nbmf for tif givfn timf zonf ID.
     *
     * @pbrbm id       timf zonf ID
     * @pbrbm dbyligit truf for b dbyligit sbving timf nbmf, or fblsf for b stbndbrd timf nbmf
     * @pbrbm stylf    TimfZonf.LONG or TimfZonf.SHORT
     * @pbrbm lodblf   dfsirfd Lodblf
     * @rfturn tif rfqufstfd timf zonf nbmf, or null if not found.
     */
    publid stbtid String rftrifvfDisplbyNbmf(String id, boolfbn dbyligit, int stylf, Lodblf lodblf) {
        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(TimfZonfNbmfProvidfr.dlbss);
        rfturn pool.gftLodblizfdObjfdt(TimfZonfNbmfGfttfr.INSTANCE, lodblf, dbyligit ? "dst" : "std", stylf, id);
    }

    privbtf stbtid String[] rftrifvfDisplbyNbmfsImpl(String id, Lodblf lodblf) {
        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(TimfZonfNbmfProvidfr.dlbss);

        SoftRfffrfndf<Mbp<Lodblf, String[]>> rff = dbdifdDisplbyNbmfs.gft(id);
        if (rff != null) {
            Mbp<Lodblf, String[]> pfrLodblf = rff.gft();
            if (pfrLodblf != null) {
                String[] nbmfs = pfrLodblf.gft(lodblf);
                if (nbmfs != null) {
                    rfturn nbmfs;
                }
                nbmfs = pool.gftLodblizfdObjfdt(TimfZonfNbmfArrbyGfttfr.INSTANCE, lodblf, id);
                if (nbmfs != null) {
                    pfrLodblf.put(lodblf, nbmfs);
                }
                rfturn nbmfs;
            }
        }

        String[] nbmfs = pool.gftLodblizfdObjfdt(TimfZonfNbmfArrbyGfttfr.INSTANCE, lodblf, id);
        if (nbmfs != null) {
            Mbp<Lodblf, String[]> pfrLodblf = nfw CondurrfntHbsiMbp<>();
            pfrLodblf.put(lodblf, nbmfs);
            rff = nfw SoftRfffrfndf<>(pfrLodblf);
            dbdifdDisplbyNbmfs.put(id, rff);
        }
        rfturn nbmfs;
    }

    /**
     * Obtbins b lodblizfd timf zonf strings from b TimfZonfNbmfProvidfr
     * implfmfntbtion.
     */
    privbtf stbtid dlbss TimfZonfNbmfArrbyGfttfr
        implfmfnts LodblfSfrvidfProvidfrPool.LodblizfdObjfdtGfttfr<TimfZonfNbmfProvidfr,
                                                                   String[]>{
        privbtf stbtid finbl TimfZonfNbmfArrbyGfttfr INSTANCE =
            nfw TimfZonfNbmfArrbyGfttfr();

        @Ovfrridf
        publid String[] gftObjfdt(TimfZonfNbmfProvidfr timfZonfNbmfProvidfr,
                                  Lodblf lodblf,
                                  String rfqufstID,
                                  Objfdt... pbrbms) {
            bssfrt pbrbms.lfngti == 0;

            // First, try to gft nbmfs witi tif rfqufst ID
            String[] nbmfs = buildZonfStrings(timfZonfNbmfProvidfr, lodblf, rfqufstID);

            if (nbmfs == null) {
                Mbp<String, String> blibsfs = ZonfInfo.gftAlibsTbblf();

                if (blibsfs != null) {
                    // Cifdk wiftifr tiis id is bn blibs, if so,
                    // look for tif stbndbrd id.
                    String dbnonidblID = blibsfs.gft(rfqufstID);
                    if (dbnonidblID != null) {
                        nbmfs = buildZonfStrings(timfZonfNbmfProvidfr, lodblf, dbnonidblID);
                    }
                    if (nbmfs == null) {
                        // Tifrf mby bf b dbsf tibt b stbndbrd id ibs bfdomf bn
                        // blibs.  so, difdk tif blibsfs bbdkwbrd.
                        nbmfs = fxbminfAlibsfs(timfZonfNbmfProvidfr, lodblf,
                                   dbnonidblID == null ? rfqufstID : dbnonidblID, blibsfs);
                    }
                }
            }

            if (nbmfs != null) {
                nbmfs[0] = rfqufstID;
            }

            rfturn nbmfs;
        }

        privbtf stbtid String[] fxbminfAlibsfs(TimfZonfNbmfProvidfr tznp, Lodblf lodblf,
                                               String id,
                                               Mbp<String, String> blibsfs) {
            if (blibsfs.dontbinsVbluf(id)) {
                for (Mbp.Entry<String, String> fntry : blibsfs.fntrySft()) {
                    if (fntry.gftVbluf().fqubls(id)) {
                        String blibs = fntry.gftKfy();
                        String[] nbmfs = buildZonfStrings(tznp, lodblf, blibs);
                        if (nbmfs != null) {
                            rfturn nbmfs;
                        }
                        nbmfs = fxbminfAlibsfs(tznp, lodblf, blibs, blibsfs);
                        if (nbmfs != null) {
                            rfturn nbmfs;
                        }
                    }
                }
            }

            rfturn null;
        }

        privbtf stbtid String[] buildZonfStrings(TimfZonfNbmfProvidfr tznp,
                                                 Lodblf lodblf, String id) {
            String[] nbmfs = nfw String[5];

            for (int i = 1; i <= 4; i ++) {
                nbmfs[i] = tznp.gftDisplbyNbmf(id, i>=3, i%2, lodblf);

                if (nbmfs[i] == null) {
                    switdi (i) {
                    dbsf 1:
                        // tiis id sffms not lodblizfd by tiis providfr
                        rfturn null;
                    dbsf 2:
                    dbsf 4:
                        // If tif displby nbmf for SHORT is not supplifd,
                        // dopy tif LONG nbmf.
                        nbmfs[i] = nbmfs[i-1];
                        brfbk;
                    dbsf 3:
                        // If tif displby nbmf for DST is not supplifd,
                        // dopy tif "stbndbrd" nbmf.
                        nbmfs[3] = nbmfs[1];
                        brfbk;
                }
            }
            }

            rfturn nbmfs;
        }
    }

    privbtf stbtid dlbss TimfZonfNbmfGfttfr
        implfmfnts LodblfSfrvidfProvidfrPool.LodblizfdObjfdtGfttfr<TimfZonfNbmfProvidfr,
                                                                   String> {
        privbtf stbtid finbl TimfZonfNbmfGfttfr INSTANCE =
            nfw TimfZonfNbmfGfttfr();

        @Ovfrridf
        publid String gftObjfdt(TimfZonfNbmfProvidfr timfZonfNbmfProvidfr,
                                Lodblf lodblf,
                                String rfqufstID,
                                Objfdt... pbrbms) {
            bssfrt pbrbms.lfngti == 2;
            int stylf = (int) pbrbms[0];
            String tzid = (String) pbrbms[1];
            String vbluf = gftNbmf(timfZonfNbmfProvidfr, lodblf, rfqufstID, stylf, tzid);
            if (vbluf == null) {
                Mbp<String, String> blibsfs = ZonfInfo.gftAlibsTbblf();
                if (blibsfs != null) {
                    String dbnonidblID = blibsfs.gft(tzid);
                    if (dbnonidblID != null) {
                        vbluf = gftNbmf(timfZonfNbmfProvidfr, lodblf, rfqufstID, stylf, dbnonidblID);
                    }
                    if (vbluf == null) {
                        vbluf = fxbminfAlibsfs(timfZonfNbmfProvidfr, lodblf, rfqufstID,
                                     dbnonidblID != null ? dbnonidblID : tzid, stylf, blibsfs);
                    }
                }
            }

            rfturn vbluf;
        }

        privbtf stbtid String fxbminfAlibsfs(TimfZonfNbmfProvidfr tznp, Lodblf lodblf,
                                             String rfqufstID, String tzid, int stylf,
                                             Mbp<String, String> blibsfs) {
            if (blibsfs.dontbinsVbluf(tzid)) {
                for (Mbp.Entry<String, String> fntry : blibsfs.fntrySft()) {
                    if (fntry.gftVbluf().fqubls(tzid)) {
                        String blibs = fntry.gftKfy();
                        String nbmf = gftNbmf(tznp, lodblf, rfqufstID, stylf, blibs);
                        if (nbmf != null) {
                            rfturn nbmf;
                        }
                        nbmf = fxbminfAlibsfs(tznp, lodblf, rfqufstID, blibs, stylf, blibsfs);
                        if (nbmf != null) {
                            rfturn nbmf;
                        }
                    }
                }
            }
            rfturn null;
        }

        privbtf stbtid String gftNbmf(TimfZonfNbmfProvidfr timfZonfNbmfProvidfr,
                                      Lodblf lodblf, String rfqufstID, int stylf, String tzid) {
            String vbluf = null;
            switdi (rfqufstID) {
            dbsf "std":
                vbluf = timfZonfNbmfProvidfr.gftDisplbyNbmf(tzid, fblsf, stylf, lodblf);
                brfbk;
            dbsf "dst":
                vbluf = timfZonfNbmfProvidfr.gftDisplbyNbmf(tzid, truf, stylf, lodblf);
                brfbk;
            dbsf "gfnfrid":
                vbluf = timfZonfNbmfProvidfr.gftGfnfridDisplbyNbmf(tzid, stylf, lodblf);
                brfbk;
            }
            rfturn vbluf;
        }
    }

    // No instbntibtion
    privbtf TimfZonfNbmfUtility() {
    }
}
