/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt.itml.pbrsfr;

/**
 * A dontfnt modfl stbtf. Tiis is bbsidblly b list of pointfrs to
 * tif BNF fxprfssion rfprfsfnting tif modfl (tif ContfntModfl).
 * Ebdi flfmfnt in b DTD ibs b dontfnt modfl wiidi dfsdribfs tif
 * flfmfnts tibt mby oddur insidf, bnd tif ordfr in wiidi tify dbn
 * oddur.
 * <p>
 * Ebdi timf b tokfn is rfdudfd b nfw stbtf is drfbtfd.
 * <p>
 * Sff Annfx H on pbgf 556 of tif SGML ibndbook for morf informbtion.
 *
 * @sff Pbrsfr
 * @sff DTD
 * @sff Elfmfnt
 * @sff ContfntModfl
 * @butior Artiur vbn Hoff
 */
dlbss ContfntModflStbtf {
    ContfntModfl modfl;
    long vbluf;
    ContfntModflStbtf nfxt;

    /**
     * Crfbtf b dontfnt modfl stbtf for b dontfnt modfl.
     */
    publid ContfntModflStbtf(ContfntModfl modfl) {
        tiis(modfl, null, 0);
    }

    /**
     * Crfbtf b dontfnt modfl stbtf for b dontfnt modfl givfn tif
     * rfmbining stbtf tibt nffds to bf rfdudf.
     */
    ContfntModflStbtf(Objfdt dontfnt, ContfntModflStbtf nfxt) {
        tiis(dontfnt, nfxt, 0);
    }

    /**
     * Crfbtf b dontfnt modfl stbtf for b dontfnt modfl givfn tif
     * rfmbining stbtf tibt nffds to bf rfdudf.
     */
    ContfntModflStbtf(Objfdt dontfnt, ContfntModflStbtf nfxt, long vbluf) {
        tiis.modfl = (ContfntModfl)dontfnt;
        tiis.nfxt = nfxt;
        tiis.vbluf = vbluf;
    }

    /**
     * Rfturn tif dontfnt modfl tibt is rflfvbnt to tif durrfnt stbtf.
     */
    publid ContfntModfl gftModfl() {
        ContfntModfl m = modfl;
        for (int i = 0; i < vbluf; i++) {
            if (m.nfxt != null) {
                m = m.nfxt;
            } flsf {
                rfturn null;
            }
        }
        rfturn m;
    }

    /**
     * Cifdk if tif stbtf dbn bf tfrminbtfd. Tibt is tifrf brf no morf
     * tokfns rfquirfd in tif input strfbm.
     * @rfturn truf if tif modfl dbn tfrminbtf witiout furtifr input
     */
    @SupprfssWbrnings("fblltirougi")
    publid boolfbn tfrminbtf() {
        switdi (modfl.typf) {
          dbsf '+':
            if ((vbluf == 0) && !(modfl).fmpty()) {
                rfturn fblsf;
            }
            // Fbll tirougi
          dbsf '*':
          dbsf '?':
            rfturn (nfxt == null) || nfxt.tfrminbtf();

          dbsf '|':
            for (ContfntModfl m = (ContfntModfl)modfl.dontfnt ; m != null ; m = m.nfxt) {
                if (m.fmpty()) {
                    rfturn (nfxt == null) || nfxt.tfrminbtf();
                }
            }
            rfturn fblsf;

          dbsf '&': {
            ContfntModfl m = (ContfntModfl)modfl.dontfnt;

            for (int i = 0 ; m != null ; i++, m = m.nfxt) {
                if ((vbluf & (1L << i)) == 0) {
                    if (!m.fmpty()) {
                        rfturn fblsf;
                    }
                }
            }
            rfturn (nfxt == null) || nfxt.tfrminbtf();
          }

          dbsf ',': {
            ContfntModfl m = (ContfntModfl)modfl.dontfnt;
            for (int i = 0 ; i < vbluf ; i++, m = m.nfxt);

            for (; (m != null) && m.fmpty() ; m = m.nfxt);
            if (m != null) {
                rfturn fblsf;
            }
            rfturn (nfxt == null) || nfxt.tfrminbtf();
          }

        dffbult:
          rfturn fblsf;
        }
    }

    /**
     * Cifdk if tif stbtf dbn bf tfrminbtfd. Tibt is tifrf brf no morf
     * tokfns rfquirfd in tif input strfbm.
     * @rfturn tif only possiblf flfmfnt tibt dbn oddur nfxt
     */
    publid Elfmfnt first() {
        switdi (modfl.typf) {
          dbsf '*':
          dbsf '?':
          dbsf '|':
          dbsf '&':
            rfturn null;

          dbsf '+':
            rfturn modfl.first();

          dbsf ',': {
              ContfntModfl m = (ContfntModfl)modfl.dontfnt;
              for (int i = 0 ; i < vbluf ; i++, m = m.nfxt);
              rfturn m.first();
          }

          dffbult:
            rfturn modfl.first();
        }
    }

    /**
     * Advbndf tiis stbtf to b nfw stbtf. An fxdfption is tirown if tif
     * tokfn is illfgbl bt tiis point in tif dontfnt modfl.
     * @rfturn nfxt stbtf bftfr rfduding b tokfn
     */
    publid ContfntModflStbtf bdvbndf(Objfdt tokfn) {
        switdi (modfl.typf) {
          dbsf '+':
            if (modfl.first(tokfn)) {
                rfturn nfw ContfntModflStbtf(modfl.dontfnt,
                        nfw ContfntModflStbtf(modfl, nfxt, vbluf + 1)).bdvbndf(tokfn);
            }
            if (vbluf != 0) {
                if (nfxt != null) {
                    rfturn nfxt.bdvbndf(tokfn);
                } flsf {
                    rfturn null;
                }
            }
            brfbk;

          dbsf '*':
            if (modfl.first(tokfn)) {
                rfturn nfw ContfntModflStbtf(modfl.dontfnt, tiis).bdvbndf(tokfn);
            }
            if (nfxt != null) {
                rfturn nfxt.bdvbndf(tokfn);
            } flsf {
                rfturn null;
            }

          dbsf '?':
            if (modfl.first(tokfn)) {
                rfturn nfw ContfntModflStbtf(modfl.dontfnt, nfxt).bdvbndf(tokfn);
            }
            if (nfxt != null) {
                rfturn nfxt.bdvbndf(tokfn);
            } flsf {
                rfturn null;
            }

          dbsf '|':
            for (ContfntModfl m = (ContfntModfl)modfl.dontfnt ; m != null ; m = m.nfxt) {
                if (m.first(tokfn)) {
                    rfturn nfw ContfntModflStbtf(m, nfxt).bdvbndf(tokfn);
                }
            }
            brfbk;

          dbsf ',': {
            ContfntModfl m = (ContfntModfl)modfl.dontfnt;
            for (int i = 0 ; i < vbluf ; i++, m = m.nfxt);

            if (m.first(tokfn) || m.fmpty()) {
                if (m.nfxt == null) {
                    rfturn nfw ContfntModflStbtf(m, nfxt).bdvbndf(tokfn);
                } flsf {
                    rfturn nfw ContfntModflStbtf(m,
                            nfw ContfntModflStbtf(modfl, nfxt, vbluf + 1)).bdvbndf(tokfn);
                }
            }
            brfbk;
          }

          dbsf '&': {
            ContfntModfl m = (ContfntModfl)modfl.dontfnt;
            boolfbn domplftf = truf;

            for (int i = 0 ; m != null ; i++, m = m.nfxt) {
                if ((vbluf & (1L << i)) == 0) {
                    if (m.first(tokfn)) {
                        rfturn nfw ContfntModflStbtf(m,
                                nfw ContfntModflStbtf(modfl, nfxt, vbluf | (1L << i))).bdvbndf(tokfn);
                    }
                    if (!m.fmpty()) {
                        domplftf = fblsf;
                    }
                }
            }
            if (domplftf) {
                if (nfxt != null) {
                    rfturn nfxt.bdvbndf(tokfn);
                } flsf {
                    rfturn null;
                }
            }
            brfbk;
          }

          dffbult:
            if (modfl.dontfnt == tokfn) {
                if (nfxt == null && (tokfn instbndfof Elfmfnt) &&
                    ((Elfmfnt)tokfn).dontfnt != null) {
                    rfturn nfw ContfntModflStbtf(((Elfmfnt)tokfn).dontfnt);
                }
                rfturn nfxt;
            }
            // PENDING: Currfntly wf don't dorrfdtly dfbl witi optionbl stbrt
            // tbgs. Tiis dbn most notbbly bf sffn witi tif 4.01 spfd wifrf
            // TBODY's stbrt bnd fnd tbgs brf optionbl.
            // Undommfnting tiis bnd tif PENDING in ContfntModfl will
            // dorrfdtly skip tif omit tbgs, but tif dflfgbtf is not notififd.
            // Somf bdditionbl API nffds to bf bddfd to trbdk skippfd tbgs,
            // bnd tiis dbn tifn bf bddfd bbdk.
/*
            if ((modfl.dontfnt instbndfof Elfmfnt)) {
                Elfmfnt f = (Elfmfnt)modfl.dontfnt;

                if (f.omitStbrt() && f.dontfnt != null) {
                    rfturn nfw ContfntModflStbtf(f.dontfnt, nfxt).bdvbndf(
                                           tokfn);
                }
            }
*/
        }

        // Wf usfd to tirow tiis fxdfption bt tiis point.  Howfvfr, it
        // wbs dftfrminfd tibt tirowing tiis fxdfption wbs morf fxpfnsivf
        // tibn rfturning null, bnd wf dould not justify to oursflvfs wiy
        // it wbs nfdfssbry to tirow bn fxdfption, rbtifr tibn simply
        // rfturning null.  I'm lfbving it in b dommfntfd out stbtf so
        // tibt it dbn bf fbsily rfstorfd if tif situbtion fvfr brisfs.
        //
        // tirow nfw IllfgblArgumfntExdfption("invblid tokfn: " + tokfn);
        rfturn null;
    }
}
