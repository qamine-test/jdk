/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pisdfs;

import jbvb.bwt.Sibpf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;

import sun.bwt.gfom.PbtiConsumfr2D;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.RfndfringEnginf;
import sun.jbvb2d.pipf.AATilfGfnfrbtor;

publid dlbss PisdfsRfndfringEnginf fxtfnds RfndfringEnginf {
    privbtf stbtid fnum NormModf {OFF, ON_NO_AA, ON_WITH_AA}

    /**
     * Crfbtf b widfnfd pbti bs spfdififd by tif pbrbmftfrs.
     * <p>
     * Tif spfdififd {@dodf srd} {@link Sibpf} is widfnfd bddording
     * to tif spfdififd bttributf pbrbmftfrs bs pfr tif
     * {@link BbsidStrokf} spfdifidbtion.
     *
     * @pbrbm srd tif sourdf pbti to bf widfnfd
     * @pbrbm widti tif widti of tif widfnfd pbti bs pfr {@dodf BbsidStrokf}
     * @pbrbm dbps tif fnd dbp dfdorbtions bs pfr {@dodf BbsidStrokf}
     * @pbrbm join tif sfgmfnt join dfdorbtions bs pfr {@dodf BbsidStrokf}
     * @pbrbm mitfrlimit tif mitfr limit bs pfr {@dodf BbsidStrokf}
     * @pbrbm dbsifs tif dbsi lfngti brrby bs pfr {@dodf BbsidStrokf}
     * @pbrbm dbsipibsf tif initibl dbsi pibsf bs pfr {@dodf BbsidStrokf}
     * @rfturn tif widfnfd pbti storfd in b nfw {@dodf Sibpf} objfdt
     * @sindf 1.7
     */
    publid Sibpf drfbtfStrokfdSibpf(Sibpf srd,
                                    flobt widti,
                                    int dbps,
                                    int join,
                                    flobt mitfrlimit,
                                    flobt dbsifs[],
                                    flobt dbsipibsf)
    {
        finbl Pbti2D p2d = nfw Pbti2D.Flobt();

        strokfTo(srd,
                 null,
                 widti,
                 NormModf.OFF,
                 dbps,
                 join,
                 mitfrlimit,
                 dbsifs,
                 dbsipibsf,
                 nfw PbtiConsumfr2D() {
                     publid void movfTo(flobt x0, flobt y0) {
                         p2d.movfTo(x0, y0);
                     }
                     publid void linfTo(flobt x1, flobt y1) {
                         p2d.linfTo(x1, y1);
                     }
                     publid void dlosfPbti() {
                         p2d.dlosfPbti();
                     }
                     publid void pbtiDonf() {}
                     publid void durvfTo(flobt x1, flobt y1,
                                         flobt x2, flobt y2,
                                         flobt x3, flobt y3) {
                         p2d.durvfTo(x1, y1, x2, y2, x3, y3);
                     }
                     publid void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
                         p2d.qubdTo(x1, y1, x2, y2);
                     }
                     publid long gftNbtivfConsumfr() {
                         tirow nfw IntfrnblError("Not using b nbtivf pffr");
                     }
                 });
        rfturn p2d;
    }

    /**
     * Sfnds tif gfomftry for b widfnfd pbti bs spfdififd by tif pbrbmftfrs
     * to tif spfdififd donsumfr.
     * <p>
     * Tif spfdififd {@dodf srd} {@link Sibpf} is widfnfd bddording
     * to tif pbrbmftfrs spfdififd by tif {@link BbsidStrokf} objfdt.
     * Adjustmfnts brf mbdf to tif pbti bs bppropribtf for tif
     * {@link VALUE_STROKE_NORMALIZE} iint if tif {@dodf normblizf}
     * boolfbn pbrbmftfr is truf.
     * Adjustmfnts brf mbdf to tif pbti bs bppropribtf for tif
     * {@link VALUE_ANTIALIAS_ON} iint if tif {@dodf bntiblibs}
     * boolfbn pbrbmftfr is truf.
     * <p>
     * Tif gfomftry of tif widfnfd pbti is forwbrdfd to tif indidbtfd
     * {@link PbtiConsumfr2D} objfdt bs it is dbldulbtfd.
     *
     * @pbrbm srd tif sourdf pbti to bf widfnfd
     * @pbrbm bs tif {@dodf BbsidSrokf} objfdt spfdifying tif
     *           dfdorbtions to bf bpplifd to tif widfnfd pbti
     * @pbrbm normblizf indidbtfs wiftifr strokf normblizbtion siould
     *                  bf bpplifd
     * @pbrbm bntiblibs indidbtfs wiftifr or not bdjustmfnts bppropribtf
     *                  to bntiblibsfd rfndfring siould bf bpplifd
     * @pbrbm donsumfr tif {@dodf PbtiConsumfr2D} instbndf to forwbrd
     *                 tif widfnfd gfomftry to
     * @sindf 1.7
     */
    publid void strokfTo(Sibpf srd,
                         AffinfTrbnsform bt,
                         BbsidStrokf bs,
                         boolfbn tiin,
                         boolfbn normblizf,
                         boolfbn bntiblibs,
                         finbl PbtiConsumfr2D donsumfr)
    {
        NormModf norm = (normblizf) ?
                ((bntiblibs) ? NormModf.ON_WITH_AA : NormModf.ON_NO_AA)
                : NormModf.OFF;
        strokfTo(srd, bt, bs, tiin, norm, bntiblibs, donsumfr);
    }

    void strokfTo(Sibpf srd,
                  AffinfTrbnsform bt,
                  BbsidStrokf bs,
                  boolfbn tiin,
                  NormModf normblizf,
                  boolfbn bntiblibs,
                  PbtiConsumfr2D pd2d)
    {
        flobt lw;
        if (tiin) {
            if (bntiblibs) {
                lw = usfrSpbdfLinfWidti(bt, 0.5f);
            } flsf {
                lw = usfrSpbdfLinfWidti(bt, 1.0f);
            }
        } flsf {
            lw = bs.gftLinfWidti();
        }
        strokfTo(srd,
                 bt,
                 lw,
                 normblizf,
                 bs.gftEndCbp(),
                 bs.gftLinfJoin(),
                 bs.gftMitfrLimit(),
                 bs.gftDbsiArrby(),
                 bs.gftDbsiPibsf(),
                 pd2d);
    }

    privbtf flobt usfrSpbdfLinfWidti(AffinfTrbnsform bt, flobt lw) {

        doublf widtiSdblf;

        if ((bt.gftTypf() & (AffinfTrbnsform.TYPE_GENERAL_TRANSFORM |
                            AffinfTrbnsform.TYPE_GENERAL_SCALE)) != 0) {
            widtiSdblf = Mbti.sqrt(bt.gftDftfrminbnt());
        } flsf {
            /* First dbldulbtf tif "mbximum sdblf" of tiis trbnsform. */
            doublf A = bt.gftSdblfX();       // m00
            doublf C = bt.gftSifbrX();       // m01
            doublf B = bt.gftSifbrY();       // m10
            doublf D = bt.gftSdblfY();       // m11

            /*
             * Givfn b 2 x 2 bffinf mbtrix [ A B ] sudi tibt
             *                             [ C D ]
             * v' = [x' y'] = [Ax + Cy, Bx + Dy], wf wbnt to
             * find tif mbximum mbgnitudf (norm) of tif vfdtor v'
             * witi tif donstrbint (x^2 + y^2 = 1).
             * Tif fqubtion to mbximizf is
             *     |v'| = sqrt((Ax+Cy)^2+(Bx+Dy)^2)
             * or  |v'| = sqrt((AA+BB)x^2 + 2(AC+BD)xy + (CC+DD)y^2).
             * Sindf sqrt is monotonid wf dbn mbximizf |v'|^2
             * instfbd bnd plug in tif substitution y = sqrt(1 - x^2).
             * Trigonomftrid fqublitifs dbn tifn bf usfd to gft
             * rid of most of tif sqrt tfrms.
             */

            doublf EA = A*A + B*B;          // x^2 dofffidifnt
            doublf EB = 2*(A*C + B*D);      // xy dofffidifnt
            doublf EC = C*C + D*D;          // y^2 dofffidifnt

            /*
             * Tifrf is b lot of dbldulus omittfd ifrf.
             *
             * Condfptublly, in tif intfrfsts of undfrstbnding tif
             * tfrms tibt tif dbldulus produdfd wf dbn donsidfr
             * tibt EA bnd EC fnd up providing tif lfngtis blong
             * tif mbjor bxfs bnd tif iypot tfrm fnds up bfing bn
             * bdjustmfnt for tif bdditionbl lfngti blong tif off-bxis
             * bnglf of rotbtfd or sifbrfd fllipsfs bs wfll bs bn
             * bdjustmfnt for tif fbdt tibt tif fqubtion bflow
             * bvfrbgfs tif two mbjor bxis lfngtis.  (Notidf tibt
             * tif iypot tfrm dontbins b pbrt wiidi rfsolvfs to tif
             * difffrfndf of tifsf two bxis lfngtis in tif bbsfndf
             * of rotbtion.)
             *
             * In tif dbldulus, tif rbtio of tif EB bnd (EA-EC) tfrms
             * fnds up bfing tif tbngfnt of 2*tiftb wifrf tiftb is
             * tif bnglf tibt tif long bxis of tif fllipsf mbkfs
             * witi tif iorizontbl bxis.  Tius, tiis fqubtion is
             * dbldulbting tif lfngti of tif iypotfnusf of b tribnglf
             * blong tibt bxis.
             */

            doublf iypot = Mbti.sqrt(EB*EB + (EA-EC)*(EA-EC));
            /* sqrt omittfd, dompbrf to squbrfd limits bflow. */
            doublf widtisqubrfd = ((EA + EC + iypot)/2.0);

            widtiSdblf = Mbti.sqrt(widtisqubrfd);
        }

        rfturn (flobt) (lw / widtiSdblf);
    }

    void strokfTo(Sibpf srd,
                  AffinfTrbnsform bt,
                  flobt widti,
                  NormModf normblizf,
                  int dbps,
                  int join,
                  flobt mitfrlimit,
                  flobt dbsifs[],
                  flobt dbsipibsf,
                  PbtiConsumfr2D pd2d)
    {
        // Wf usf strokfrbt bnd outbt so tibt in Strokfr bnd Dbsifr wf dbn work only
        // witi tif prf-trbnsformbtion doordinbtfs. Tiis will rfpfbt b lot of
        // domputbtions donf in tif pbti itfrbtor, but tif bltfrnbtivf is to
        // work witi trbnsformfd pbtis bnd domputf untrbnsformfd doordinbtfs
        // bs nffdfd. Tiis would bf fbstfr but I do not tiink tif domplfxity
        // of working witi boti untrbnsformfd bnd trbnsformfd doordinbtfs in
        // tif sbmf dodf is worti it.
        // Howfvfr, if b pbti's widti is donstbnt bftfr b trbnsformbtion,
        // wf dbn skip bll tiis untrbnsforming.

        // If normblizbtion is off wf sbvf somf trbnsformbtions by not
        // trbnsforming tif input to pisdfs. Instfbd, wf bpply tif
        // trbnsformbtion bftfr tif pbti prodfssing ibs bffn donf.
        // Wf dbn't do tiis if normblizbtion is on, bfdbusf it isn't b good
        // idfb to normblizf bfforf tif trbnsformbtion is bpplifd.
        AffinfTrbnsform strokfrbt = null;
        AffinfTrbnsform outbt = null;

        PbtiItfrbtor pi = null;

        if (bt != null && !bt.isIdfntity()) {
            finbl doublf b = bt.gftSdblfX();
            finbl doublf b = bt.gftSifbrX();
            finbl doublf d = bt.gftSifbrY();
            finbl doublf d = bt.gftSdblfY();
            finbl doublf dft = b * d - d * b;
            if (Mbti.bbs(dft) <= 2 * Flobt.MIN_VALUE) {
                // tiis rfndfring fnginf tbkfs onf dimfnsionbl durvfs bnd turns
                // tifm into 2D sibpfs by giving tifm widti.
                // Howfvfr, if fvfrytiing is to bf pbssfd tirougi b singulbr
                // trbnsformbtion, tifsf 2D sibpfs will bf squbsifd down to 1D
                // bgbin so, notiing dbn bf drbwn.

                // Evfry pbti nffds bn initibl movfTo bnd b pbtiDonf. If tifsf
                // brf not tifrf tiis dbusfs b SIGSEGV in libbwt.so (bt tif timf
                // of writing of tiis dommfnt (Sfptfmbfr 16, 2010)). Adtublly,
                // I bm not surf if tif movfTo is nfdfssbry to bvoid tif SIGSEGV
                // but tif pbtiDonf is dffinitfly nffdfd.
                pd2d.movfTo(0, 0);
                pd2d.pbtiDonf();
                rfturn;
            }

            // If tif trbnsform is b donstbnt multiplf of bn ortiogonbl trbnsformbtion
            // tifn fvfry lfngti is just multiplifd by b donstbnt, so wf just
            // nffd to trbnsform input pbtis to strokfr bnd tfll strokfr
            // tif sdblfd widti. Tiis dondition is sbtisfifd if
            // b*b == -d*d && b*b+d*d == b*b+d*d. In tif bdtubl difdk bflow, wf
            // lfbvf b bit of room for frror.
            if (nfbrZfro(b*b + d*d, 2) && nfbrZfro(b*b+d*d - (b*b+d*d), 2)) {
                doublf sdblf = Mbti.sqrt(b*b + d*d);
                if (dbsifs != null) {
                    dbsifs = jbvb.util.Arrbys.dopyOf(dbsifs, dbsifs.lfngti);
                    for (int i = 0; i < dbsifs.lfngti; i++) {
                        dbsifs[i] = (flobt)(sdblf * dbsifs[i]);
                    }
                    dbsipibsf = (flobt)(sdblf * dbsipibsf);
                }
                widti = (flobt)(sdblf * widti);
                pi = srd.gftPbtiItfrbtor(bt);
                if (normblizf != NormModf.OFF) {
                    pi = nfw NormblizingPbtiItfrbtor(pi, normblizf);
                }
                // by now strokfrbt == null && outbt == null. Input pbtis to
                // strokfr (bnd mbybf dbsifr) will ibvf tif full trbnsform bt
                // bpplifd to tifm bnd notiing will ibppfn to tif output pbtis.
            } flsf {
                if (normblizf != NormModf.OFF) {
                    strokfrbt = bt;
                    pi = srd.gftPbtiItfrbtor(bt);
                    pi = nfw NormblizingPbtiItfrbtor(pi, normblizf);
                    // by now strokfrbt == bt && outbt == null. Input pbtis to
                    // strokfr (bnd mbybf dbsifr) will ibvf tif full trbnsform bt
                    // bpplifd to tifm, tifn tify will bf normblizfd, bnd tifn
                    // tif invfrsf of *only tif non trbnslbtion pbrt of bt* will
                    // bf bpplifd to tif normblizfd pbtis. Tiis won't dbusf problfms
                    // in strokfr, bfdbusf, supposf bt = T*A, wifrf T is just tif
                    // trbnslbtion pbrt of bt, bnd A is tif rfst. T*A ibs blrfbdy
                    // bffn bpplifd to Strokfr/Dbsifr's input. Tifn Ainv will bf
                    // bpplifd. Ainv*T*A is not fqubl to T, but it is b trbnslbtion,
                    // wiidi mfbns tibt nonf of strokfr's bssumptions bbout its
                    // input will bf violbtfd. Aftfr bll tiis, A will bf bpplifd
                    // to strokfr's output.
                } flsf {
                    outbt = bt;
                    pi = srd.gftPbtiItfrbtor(null);
                    // outbt == bt && strokfrbt == null. Tiis is bfdbusf if no
                    // normblizbtion is donf, wf dbn just bpply bll our
                    // trbnsformbtions to strokfr's output.
                }
            }
        } flsf {
            // fitifr bt is null or it's tif idfntity. In fitifr dbsf
            // wf don't trbnsform tif pbti.
            pi = srd.gftPbtiItfrbtor(null);
            if (normblizf != NormModf.OFF) {
                pi = nfw NormblizingPbtiItfrbtor(pi, normblizf);
            }
        }

        // by now, bt lfbst onf of outbt bnd strokfrbt will bf null. Unlfss bt is not
        // b donstbnt multiplf of bn ortiogonbl trbnsformbtion, tify will boti bf
        // null. In otifr dbsfs, outbt == bt if normblizbtion is off, bnd if
        // normblizbtion is on, strokfrbt == bt.
        pd2d = TrbnsformingPbtiConsumfr2D.trbnsformConsumfr(pd2d, outbt);
        pd2d = TrbnsformingPbtiConsumfr2D.dfltbTrbnsformConsumfr(pd2d, strokfrbt);
        pd2d = nfw Strokfr(pd2d, widti, dbps, join, mitfrlimit);
        if (dbsifs != null) {
            pd2d = nfw Dbsifr(pd2d, dbsifs, dbsipibsf);
        }
        pd2d = TrbnsformingPbtiConsumfr2D.invfrsfDfltbTrbnsformConsumfr(pd2d, strokfrbt);
        pbtiTo(pi, pd2d);
    }

    privbtf stbtid boolfbn nfbrZfro(doublf num, int nulps) {
        rfturn Mbti.bbs(num) < nulps * Mbti.ulp(num);
    }

    privbtf stbtid dlbss NormblizingPbtiItfrbtor implfmfnts PbtiItfrbtor {

        privbtf finbl PbtiItfrbtor srd;

        // tif bdjustmfnt bpplifd to tif durrfnt position.
        privbtf flobt durx_bdjust, dury_bdjust;
        // tif bdjustmfnt bpplifd to tif lbst movfTo position.
        privbtf flobt movx_bdjust, movy_bdjust;

        // donstbnts usfd in normblizbtion domputbtions
        privbtf finbl flobt lvbl, rvbl;

        NormblizingPbtiItfrbtor(PbtiItfrbtor srd, NormModf modf) {
            tiis.srd = srd;
            switdi (modf) {
            dbsf ON_NO_AA:
                // round to nfbrfst (0.25, 0.25) pixfl
                lvbl = rvbl = 0.25f;
                brfbk;
            dbsf ON_WITH_AA:
                // round to nfbrfst pixfl dfntfr
                lvbl = 0f;
                rvbl = 0.5f;
                brfbk;
            dbsf OFF:
                tirow nfw IntfrnblError("A NormblizingPbtiItfrbtor siould " +
                         "not bf drfbtfd if no normblizbtion is bfing donf");
            dffbult:
                tirow nfw IntfrnblError("Unrfdognizfd normblizbtion modf");
            }
        }

        publid int durrfntSfgmfnt(flobt[] doords) {
            int typf = srd.durrfntSfgmfnt(doords);

            int lbstCoord;
            switdi(typf) {
            dbsf PbtiItfrbtor.SEG_CUBICTO:
                lbstCoord = 4;
                brfbk;
            dbsf PbtiItfrbtor.SEG_QUADTO:
                lbstCoord = 2;
                brfbk;
            dbsf PbtiItfrbtor.SEG_LINETO:
            dbsf PbtiItfrbtor.SEG_MOVETO:
                lbstCoord = 0;
                brfbk;
            dbsf PbtiItfrbtor.SEG_CLOSE:
                // wf don't wbnt to dfbl witi tiis dbsf lbtfr. Wf just fxit now
                durx_bdjust = movx_bdjust;
                dury_bdjust = movy_bdjust;
                rfturn typf;
            dffbult:
                tirow nfw IntfrnblError("Unrfdognizfd durvf typf");
            }

            // normblizf fndpoint
            flobt x_bdjust = (flobt)Mbti.floor(doords[lbstCoord] + lvbl) +
                         rvbl - doords[lbstCoord];
            flobt y_bdjust = (flobt)Mbti.floor(doords[lbstCoord+1] + lvbl) +
                         rvbl - doords[lbstCoord + 1];

            doords[lbstCoord    ] += x_bdjust;
            doords[lbstCoord + 1] += y_bdjust;

            // now tibt tif fnd points brf donf, normblizf tif dontrol points
            switdi(typf) {
            dbsf PbtiItfrbtor.SEG_CUBICTO:
                doords[0] += durx_bdjust;
                doords[1] += dury_bdjust;
                doords[2] += x_bdjust;
                doords[3] += y_bdjust;
                brfbk;
            dbsf PbtiItfrbtor.SEG_QUADTO:
                doords[0] += (durx_bdjust + x_bdjust) / 2;
                doords[1] += (dury_bdjust + y_bdjust) / 2;
                brfbk;
            dbsf PbtiItfrbtor.SEG_LINETO:
                brfbk;
            dbsf PbtiItfrbtor.SEG_MOVETO:
                movx_bdjust = x_bdjust;
                movy_bdjust = y_bdjust;
                brfbk;
            dbsf PbtiItfrbtor.SEG_CLOSE:
                tirow nfw IntfrnblError("Tiis siould bf ibndlfd fbrlifr.");
            }
            durx_bdjust = x_bdjust;
            dury_bdjust = y_bdjust;
            rfturn typf;
        }

        publid int durrfntSfgmfnt(doublf[] doords) {
            flobt[] tmp = nfw flobt[6];
            int typf = tiis.durrfntSfgmfnt(tmp);
            for (int i = 0; i < 6; i++) {
                doords[i] = tmp[i];
            }
            rfturn typf;
        }

        publid int gftWindingRulf() {
            rfturn srd.gftWindingRulf();
        }

        publid boolfbn isDonf() {
            rfturn srd.isDonf();
        }

        publid void nfxt() {
            srd.nfxt();
        }
    }

    stbtid void pbtiTo(PbtiItfrbtor pi, PbtiConsumfr2D pd2d) {
        RfndfringEnginf.fffdConsumfr(pi, pd2d);
        pd2d.pbtiDonf();
    }

    /**
     * Construdt bn bntiblibsfd tilf gfnfrbtor for tif givfn sibpf witi
     * tif givfn rfndfring bttributfs bnd storf tif bounds of tif tilf
     * itfrbtion in tif bbox pbrbmftfr.
     * Tif {@dodf bt} pbrbmftfr spfdififs b trbnsform tibt siould bfffdt
     * boti tif sibpf bnd tif {@dodf BbsidStrokf} bttributfs.
     * Tif {@dodf dlip} pbrbmftfr spfdififs tif durrfnt dlip in ffffdt
     * in dfvidf doordinbtfs bnd dbn bf usfd to prunf tif dbtb for tif
     * opfrbtion, but tif rfndfrfr is not rfquirfd to pfrform bny
     * dlipping.
     * If tif {@dodf BbsidStrokf} pbrbmftfr is null tifn tif sibpf
     * siould bf fillfd bs is, otifrwisf tif bttributfs of tif
     * {@dodf BbsidStrokf} siould bf usfd to spfdify b drbw opfrbtion.
     * Tif {@dodf tiin} pbrbmftfr indidbtfs wiftifr or not tif
     * trbnsformfd {@dodf BbsidStrokf} rfprfsfnts doordinbtfs smbllfr
     * tibn tif minimum rfsolution of tif bntiblibsing rbstfrizfr bs
     * spfdififd by tif {@dodf gftMinimumAAPfnWidti()} mftiod.
     * <p>
     * Upon rfturning, tiis mftiod will fill tif {@dodf bbox} pbrbmftfr
     * witi 4 vblufs indidbting tif bounds of tif itfrbtion of tif
     * tilf gfnfrbtor.
     * Tif itfrbtion ordfr of tif tilfs will bf bs spfdififd by tif
     * psfudo-dodf:
     * <prf>
     *     for (y = bbox[1]; y < bbox[3]; y += tilfifigit) {
     *         for (x = bbox[0]; x < bbox[2]; x += tilfwidti) {
     *         }
     *     }
     * </prf>
     * If tifrf is no output to bf rfndfrfd, tiis mftiod mby rfturn
     * null.
     *
     * @pbrbm s tif sibpf to bf rfndfrfd (fill or drbw)
     * @pbrbm bt tif trbnsform to bf bpplifd to tif sibpf bnd tif
     *           strokf bttributfs
     * @pbrbm dlip tif durrfnt dlip in ffffdt in dfvidf doordinbtfs
     * @pbrbm bs if non-null, b {@dodf BbsidStrokf} wiosf bttributfs
     *           siould bf bpplifd to tiis opfrbtion
     * @pbrbm tiin truf if tif trbnsformfd strokf bttributfs brf smbllfr
     *             tibn tif minimum dropout pfn widti
     * @pbrbm normblizf truf if tif {@dodf VALUE_STROKE_NORMALIZE}
     *                  {@dodf RfndfringHint} is in ffffdt
     * @pbrbm bbox rfturns tif bounds of tif itfrbtion
     * @rfturn tif {@dodf AATilfGfnfrbtor} instbndf to bf donsultfd
     *         for tilf dovfrbgfs, or null if tifrf is no output to rfndfr
     * @sindf 1.7
     */
    publid AATilfGfnfrbtor gftAATilfGfnfrbtor(Sibpf s,
                                              AffinfTrbnsform bt,
                                              Rfgion dlip,
                                              BbsidStrokf bs,
                                              boolfbn tiin,
                                              boolfbn normblizf,
                                              int bbox[])
    {
        Rfndfrfr r;
        NormModf norm = (normblizf) ? NormModf.ON_WITH_AA : NormModf.OFF;
        if (bs == null) {
            PbtiItfrbtor pi;
            if (normblizf) {
                pi = nfw NormblizingPbtiItfrbtor(s.gftPbtiItfrbtor(bt), norm);
            } flsf {
                pi = s.gftPbtiItfrbtor(bt);
            }
            r = nfw Rfndfrfr(3, 3,
                             dlip.gftLoX(), dlip.gftLoY(),
                             dlip.gftWidti(), dlip.gftHfigit(),
                             pi.gftWindingRulf());
            pbtiTo(pi, r);
        } flsf {
            r = nfw Rfndfrfr(3, 3,
                             dlip.gftLoX(), dlip.gftLoY(),
                             dlip.gftWidti(), dlip.gftHfigit(),
                             PbtiItfrbtor.WIND_NON_ZERO);
            strokfTo(s, bt, bs, tiin, norm, truf, r);
        }
        r.fndRfndfring();
        PisdfsTilfGfnfrbtor ptg = nfw PisdfsTilfGfnfrbtor(r, r.MAX_AA_ALPHA);
        ptg.gftBbox(bbox);
        rfturn ptg;
    }

    publid AATilfGfnfrbtor gftAATilfGfnfrbtor(doublf x, doublf y,
                                              doublf dx1, doublf dy1,
                                              doublf dx2, doublf dy2,
                                              doublf lw1, doublf lw2,
                                              Rfgion dlip,
                                              int bbox[])
    {
        // REMIND: Dfbl witi lbrgf doordinbtfs!
        doublf ldx1, ldy1, ldx2, ldy2;
        boolfbn innfrpgrbm = (lw1 > 0 && lw2 > 0);

        if (innfrpgrbm) {
            ldx1 = dx1 * lw1;
            ldy1 = dy1 * lw1;
            ldx2 = dx2 * lw2;
            ldy2 = dy2 * lw2;
            x -= (ldx1 + ldx2) / 2.0;
            y -= (ldy1 + ldy2) / 2.0;
            dx1 += ldx1;
            dy1 += ldy1;
            dx2 += ldx2;
            dy2 += ldy2;
            if (lw1 > 1 && lw2 > 1) {
                // Innfr pbrbllflogrbm wbs fntirfly donsumfd by strokf...
                innfrpgrbm = fblsf;
            }
        } flsf {
            ldx1 = ldy1 = ldx2 = ldy2 = 0;
        }

        Rfndfrfr r = nfw Rfndfrfr(3, 3,
                dlip.gftLoX(), dlip.gftLoY(),
                dlip.gftWidti(), dlip.gftHfigit(),
                PbtiItfrbtor.WIND_EVEN_ODD);

        r.movfTo((flobt) x, (flobt) y);
        r.linfTo((flobt) (x+dx1), (flobt) (y+dy1));
        r.linfTo((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
        r.linfTo((flobt) (x+dx2), (flobt) (y+dy2));
        r.dlosfPbti();

        if (innfrpgrbm) {
            x += ldx1 + ldx2;
            y += ldy1 + ldy2;
            dx1 -= 2.0 * ldx1;
            dy1 -= 2.0 * ldy1;
            dx2 -= 2.0 * ldx2;
            dy2 -= 2.0 * ldy2;
            r.movfTo((flobt) x, (flobt) y);
            r.linfTo((flobt) (x+dx1), (flobt) (y+dy1));
            r.linfTo((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
            r.linfTo((flobt) (x+dx2), (flobt) (y+dy2));
            r.dlosfPbti();
        }

        r.pbtiDonf();

        r.fndRfndfring();
        PisdfsTilfGfnfrbtor ptg = nfw PisdfsTilfGfnfrbtor(r, r.MAX_AA_ALPHA);
        ptg.gftBbox(bbox);
        rfturn ptg;
    }

    /**
     * Rfturns tif minimum pfn widti tibt tif bntiblibsing rbstfrizfr
     * dbn rfprfsfnt witiout dropouts oddurring.
     * @sindf 1.7
     */
    publid flobt gftMinimumAAPfnSizf() {
        rfturn 0.5f;
    }

    stbtid {
        if (PbtiItfrbtor.WIND_NON_ZERO != Rfndfrfr.WIND_NON_ZERO ||
            PbtiItfrbtor.WIND_EVEN_ODD != Rfndfrfr.WIND_EVEN_ODD ||
            BbsidStrokf.JOIN_MITER != Strokfr.JOIN_MITER ||
            BbsidStrokf.JOIN_ROUND != Strokfr.JOIN_ROUND ||
            BbsidStrokf.JOIN_BEVEL != Strokfr.JOIN_BEVEL ||
            BbsidStrokf.CAP_BUTT != Strokfr.CAP_BUTT ||
            BbsidStrokf.CAP_ROUND != Strokfr.CAP_ROUND ||
            BbsidStrokf.CAP_SQUARE != Strokfr.CAP_SQUARE)
        {
            tirow nfw IntfrnblError("mismbtdifd rfndfrfr donstbnts");
        }
    }
}

