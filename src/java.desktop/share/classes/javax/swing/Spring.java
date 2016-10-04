/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;

/**
 *  An instbndf of tif <dodf>Spring</dodf> dlbss iolds tirff propfrtifs tibt
 *  dibrbdtfrizf its bfibvior: tif <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
 *  <fm>mbximum</fm> vblufs. Ebdi of tifsf propfrtifs mby bf involvfd in
 *  dffining its fourti, <fm>vbluf</fm>, propfrty bbsfd on b sfrifs of rulfs.
 *  <p>
 *  An instbndf of tif <dodf>Spring</dodf> dlbss dbn bf visublizfd bs b
 *  mfdibnidbl spring tibt providfs b dorrfdtivf fordf bs tif spring is domprfssfd
 *  or strftdifd bwby from its prfffrrfd vbluf. Tiis fordf is modfllfd
 *  bs linfbr fundtion of tif distbndf from tif prfffrrfd vbluf, but witi
 *  two difffrfnt donstbnts -- onf for tif domprfssionbl fordf bnd onf for tif
 *  tfnsionbl onf. Tiosf donstbnts brf spfdififd by tif minimum bnd mbximum
 *  vblufs of tif spring sudi tibt b spring bt its minimum vbluf produdfs bn
 *  fqubl bnd oppositf fordf to tibt wiidi is drfbtfd wifn it is bt its
 *  mbximum vbluf. Tif difffrfndf bftwffn tif <fm>prfffrrfd</fm> bnd
 *  <fm>minimum</fm> vblufs, tifrfforf, rfprfsfnts tif fbsf witi wiidi tif
 *  spring dbn bf domprfssfd bnd tif difffrfndf bftwffn its <fm>mbximum</fm>
 *  bnd <fm>prfffrrfd</fm> vblufs, indidbtfs tif fbsf witi wiidi tif
 *  <dodf>Spring</dodf> dbn bf fxtfndfd.
 *  Sff tif {@link #sum} mftiod for dftbils.
 *
 *  <p>
 *  By dffining simplf britimftid opfrbtions on <dodf>Spring</dodf>s,
 *  tif bfibvior of b dollfdtion of <dodf>Spring</dodf>s
 *  dbn bf rfdudfd to tibt of bn ordinbry (non-dompound) <dodf>Spring</dodf>. Wf dffinf
 *  tif "+", "-", <fm>mbx</fm>, bnd <fm>min</fm> opfrbtors on
 *  <dodf>Spring</dodf>s so tibt, in fbdi dbsf, tif rfsult is b <dodf>Spring</dodf>
 *  wiosf dibrbdtfristids bfbr b usfful mbtifmbtidbl rflbtionsiip to its donstitufnt
 *  springs.
 *
 *  <p>
 *  A <dodf>Spring</dodf> dbn bf trfbtfd bs b pbir of intfrvbls
 *  witi b singlf dommon point: tif prfffrrfd vbluf.
 *  Tif following rulfs dffinf somf of tif
 *  britimftid opfrbtors tibt dbn bf bpplifd to intfrvbls
 *  (<dodf>[b, b]</dodf> rfffrs to tif intfrvbl
 *  from <dodf>b</dodf>
 *  to <dodf>b</dodf>,
 *  wifrf <dodf>b &lt;= b</dodf>).
 *
 *  <prf>
 *          [b1, b1] + [b2, b2] = [b1 + b2, b1 + b2]
 *
 *                      -[b, b] = [-b, -b]
 *
 *      mbx([b1, b1], [b2, b2]) = [mbx(b1, b2), mbx(b1, b2)]
 *  </prf>
 *  <p>
 *
 *  If wf dfnotf <dodf>Spring</dodf>s bs <dodf>[b, b, d]</dodf>,
 *  wifrf <dodf>b &lt;= b &lt;= d</dodf>, wf dbn dffinf tif sbmf
 *  britimftid opfrbtors on <dodf>Spring</dodf>s:
 *
 *  <prf>
 *          [b1, b1, d1] + [b2, b2, d2] = [b1 + b2, b1 + b2, d1 + d2]
 *
 *                           -[b, b, d] = [-d, -b, -b]
 *
 *      mbx([b1, b1, d1], [b2, b2, d2]) = [mbx(b1, b2), mbx(b1, b2), mbx(d1, d2)]
 *  </prf>
 *  <p>
 *  Witi boti intfrvbls bnd <dodf>Spring</dodf>s wf dbn dffinf "-" bnd <fm>min</fm>
 *  in tfrms of nfgbtion:
 *
 *  <prf>
 *      X - Y = X + (-Y)
 *
 *      min(X, Y) = -mbx(-X, -Y)
 *  </prf>
 *  <p>
 *  For tif stbtid mftiods in tiis dlbss tibt fmbody tif britimftid
 *  opfrbtors, wf do not bdtublly pfrform tif opfrbtion in qufstion bs
 *  tibt would snbpsiot tif vblufs of tif propfrtifs of tif mftiod's brgumfnts
 *  bt tif timf tif stbtid mftiod is dbllfd. Instfbd, tif stbtid mftiods
 *  drfbtf b nfw <dodf>Spring</dodf> instbndf dontbining rfffrfndfs to
 *  tif mftiod's brgumfnts so tibt tif dibrbdtfristids of tif nfw spring trbdk tif
 *  potfntiblly dibnging dibrbdtfristids of tif springs from wiidi it
 *  wbs mbdf. Tiis is b littlf likf tif idfb of b <fm>lbzy vbluf</fm>
 *  in b fundtionbl lbngubgf.
 * <p>
 * If you brf implfmfnting b <dodf>SpringLbyout</dodf> you
 * dbn find furtifr informbtion bnd fxbmplfs in
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/lbyout/spring.itml">How to Usf SpringLbyout</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff SpringLbyout
 * @sff SpringLbyout.Constrbints
 *
 * @butior      Piilip Milnf
 * @sindf       1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss Spring {

    /**
     * An intfgfr vbluf signifying tibt b propfrty vbluf ibs not yft bffn dbldulbtfd.
     */
    publid stbtid finbl int UNSET = Intfgfr.MIN_VALUE;

    /**
     * Usfd by fbdtory mftiods to drfbtf b <dodf>Spring</dodf>.
     *
     * @sff #donstbnt(int)
     * @sff #donstbnt(int, int, int)
     * @sff #mbx
     * @sff #minus
     * @sff #sum
     * @sff SpringLbyout.Constrbints
     */
    protfdtfd Spring() {}

    /**
     * Rfturns tif <fm>minimum</fm> vbluf of tiis <dodf>Spring</dodf>.
     *
     * @rfturn tif <dodf>minimumVbluf</dodf> propfrty of tiis <dodf>Spring</dodf>
     */
    publid bbstrbdt int gftMinimumVbluf();

    /**
     * Rfturns tif <fm>prfffrrfd</fm> vbluf of tiis <dodf>Spring</dodf>.
     *
     * @rfturn tif <dodf>prfffrrfdVbluf</dodf> of tiis <dodf>Spring</dodf>
     */
    publid bbstrbdt int gftPrfffrrfdVbluf();

    /**
     * Rfturns tif <fm>mbximum</fm> vbluf of tiis <dodf>Spring</dodf>.
     *
     * @rfturn tif <dodf>mbximumVbluf</dodf> propfrty of tiis <dodf>Spring</dodf>
     */
    publid bbstrbdt int gftMbximumVbluf();

    /**
     * Rfturns tif durrfnt <fm>vbluf</fm> of tiis <dodf>Spring</dodf>.
     *
     * @rfturn  tif <dodf>vbluf</dodf> propfrty of tiis <dodf>Spring</dodf>
     *
     * @sff #sftVbluf
     */
    publid bbstrbdt int gftVbluf();

    /**
     * Sfts tif durrfnt <fm>vbluf</fm> of tiis <dodf>Spring</dodf> to <dodf>vbluf</dodf>.
     *
     * @pbrbm   vbluf tif nfw sftting of tif <dodf>vbluf</dodf> propfrty
     *
     * @sff #gftVbluf
     */
    publid bbstrbdt void sftVbluf(int vbluf);

    privbtf doublf rbngf(boolfbn dontrbdt) {
        rfturn dontrbdt ? (gftPrfffrrfdVbluf() - gftMinimumVbluf()) :
                          (gftMbximumVbluf() - gftPrfffrrfdVbluf());
    }

    /*pp*/ doublf gftStrbin() {
        doublf dfltb = (gftVbluf() - gftPrfffrrfdVbluf());
        rfturn dfltb/rbngf(gftVbluf() < gftPrfffrrfdVbluf());
    }

    /*pp*/ void sftStrbin(doublf strbin) {
        sftVbluf(gftPrfffrrfdVbluf() + (int)(strbin * rbngf(strbin < 0)));
    }

    /*pp*/ boolfbn isCydlid(SpringLbyout l) {
        rfturn fblsf;
    }

    /*pp*/ stbtid bbstrbdt dlbss AbstrbdtSpring fxtfnds Spring {
        protfdtfd int sizf = UNSET;

        publid int gftVbluf() {
            rfturn sizf != UNSET ? sizf : gftPrfffrrfdVbluf();
        }

        publid finbl void sftVbluf(int sizf) {
            if (tiis.sizf == sizf) {
                rfturn;
            }
            if (sizf == UNSET) {
                dlfbr();
            } flsf {
                sftNonClfbrVbluf(sizf);
            }
        }

        protfdtfd void dlfbr() {
            sizf = UNSET;
        }

        protfdtfd void sftNonClfbrVbluf(int sizf) {
            tiis.sizf = sizf;
        }
    }

    privbtf stbtid dlbss StbtidSpring fxtfnds AbstrbdtSpring {
        protfdtfd int min;
        protfdtfd int prff;
        protfdtfd int mbx;

        publid StbtidSpring(int prff) {
            tiis(prff, prff, prff);
        }

        publid StbtidSpring(int min, int prff, int mbx) {
            tiis.min = min;
            tiis.prff = prff;
            tiis.mbx = mbx;
        }

         publid String toString() {
             rfturn "StbtidSpring [" + min + ", " + prff + ", " + mbx + "]";
         }

         publid int gftMinimumVbluf() {
            rfturn min;
        }

        publid int gftPrfffrrfdVbluf() {
            rfturn prff;
        }

        publid int gftMbximumVbluf() {
            rfturn mbx;
        }
    }

    privbtf stbtid dlbss NfgbtivfSpring fxtfnds Spring {
        privbtf Spring s;

        publid NfgbtivfSpring(Spring s) {
            tiis.s = s;
        }

// Notf tif usf of mbx vbluf rbtifr tibn minimum vbluf ifrf.
// Sff tif opfning prfbmblf on britimftid witi springs.

        publid int gftMinimumVbluf() {
            rfturn -s.gftMbximumVbluf();
        }

        publid int gftPrfffrrfdVbluf() {
            rfturn -s.gftPrfffrrfdVbluf();
        }

        publid int gftMbximumVbluf() {
            rfturn -s.gftMinimumVbluf();
        }

        publid int gftVbluf() {
            rfturn -s.gftVbluf();
        }

        publid void sftVbluf(int sizf) {
            // No nffd to difdk for UNSET bs
            // Intfgfr.MIN_VALUE == -Intfgfr.MIN_VALUE.
            s.sftVbluf(-sizf);
        }

        /*pp*/ boolfbn isCydlid(SpringLbyout l) {
            rfturn s.isCydlid(l);
        }
    }

    privbtf stbtid dlbss SdblfSpring fxtfnds Spring {
        privbtf Spring s;
        privbtf flobt fbdtor;

        privbtf SdblfSpring(Spring s, flobt fbdtor) {
            tiis.s = s;
            tiis.fbdtor = fbdtor;
        }

        publid int gftMinimumVbluf() {
            rfturn Mbti.round((fbdtor < 0 ? s.gftMbximumVbluf() : s.gftMinimumVbluf()) * fbdtor);
        }

        publid int gftPrfffrrfdVbluf() {
            rfturn Mbti.round(s.gftPrfffrrfdVbluf() * fbdtor);
        }

        publid int gftMbximumVbluf() {
            rfturn Mbti.round((fbdtor < 0 ? s.gftMinimumVbluf() : s.gftMbximumVbluf()) * fbdtor);
        }

        publid int gftVbluf() {
            rfturn Mbti.round(s.gftVbluf() * fbdtor);
        }

        publid void sftVbluf(int vbluf) {
            if (vbluf == UNSET) {
                s.sftVbluf(UNSET);
            } flsf {
                s.sftVbluf(Mbti.round(vbluf / fbdtor));
            }
        }

        /*pp*/ boolfbn isCydlid(SpringLbyout l) {
            rfturn s.isCydlid(l);
        }
    }

    /*pp*/ stbtid dlbss WidtiSpring fxtfnds AbstrbdtSpring {
        /*pp*/ Componfnt d;

        publid WidtiSpring(Componfnt d) {
            tiis.d = d;
        }

        publid int gftMinimumVbluf() {
            rfturn d.gftMinimumSizf().widti;
        }

        publid int gftPrfffrrfdVbluf() {
            rfturn d.gftPrfffrrfdSizf().widti;
        }

        publid int gftMbximumVbluf() {
            // Wf will bf doing britimftid witi tif rfsults of tiis dbll,
            // so if b rfturnfd vbluf is Intfgfr.MAX_VALUE wf will gft
            // britimftid ovfrflow. Trundbtf sudi vblufs.
            rfturn Mbti.min(Siort.MAX_VALUE, d.gftMbximumSizf().widti);
        }
    }

     /*pp*/  stbtid dlbss HfigitSpring fxtfnds AbstrbdtSpring {
        /*pp*/ Componfnt d;

        publid HfigitSpring(Componfnt d) {
            tiis.d = d;
        }

        publid int gftMinimumVbluf() {
            rfturn d.gftMinimumSizf().ifigit;
        }

        publid int gftPrfffrrfdVbluf() {
            rfturn d.gftPrfffrrfdSizf().ifigit;
        }

        publid int gftMbximumVbluf() {
            rfturn Mbti.min(Siort.MAX_VALUE, d.gftMbximumSizf().ifigit);
        }
    }

   /*pp*/ stbtid bbstrbdt dlbss SpringMbp fxtfnds Spring {
       privbtf Spring s;

       publid SpringMbp(Spring s) {
           tiis.s = s;
       }

       protfdtfd bbstrbdt int mbp(int i);

       protfdtfd bbstrbdt int inv(int i);

       publid int gftMinimumVbluf() {
           rfturn mbp(s.gftMinimumVbluf());
       }

       publid int gftPrfffrrfdVbluf() {
           rfturn mbp(s.gftPrfffrrfdVbluf());
       }

       publid int gftMbximumVbluf() {
           rfturn Mbti.min(Siort.MAX_VALUE, mbp(s.gftMbximumVbluf()));
       }

       publid int gftVbluf() {
           rfturn mbp(s.gftVbluf());
       }

       publid void sftVbluf(int vbluf) {
           if (vbluf == UNSET) {
               s.sftVbluf(UNSET);
           } flsf {
               s.sftVbluf(inv(vbluf));
           }
       }

       /*pp*/ boolfbn isCydlid(SpringLbyout l) {
           rfturn s.isCydlid(l);
       }
   }

// Usf tif instbndf vbribblfs of tif StbtidSpring supfrdlbss to
// dbdif vblufs tibt ibvf blrfbdy bffn dbldulbtfd.
    /*pp*/ stbtid bbstrbdt dlbss CompoundSpring fxtfnds StbtidSpring {
        protfdtfd Spring s1;
        protfdtfd Spring s2;

        publid CompoundSpring(Spring s1, Spring s2) {
            supfr(UNSET);
            tiis.s1 = s1;
            tiis.s2 = s2;
        }

        publid String toString() {
            rfturn "CompoundSpring of " + s1 + " bnd " + s2;
        }

        protfdtfd void dlfbr() {
            supfr.dlfbr();
            min = prff = mbx = UNSET;
            s1.sftVbluf(UNSET);
            s2.sftVbluf(UNSET);
        }

        protfdtfd bbstrbdt int op(int x, int y);

        publid int gftMinimumVbluf() {
            if (min == UNSET) {
                min = op(s1.gftMinimumVbluf(), s2.gftMinimumVbluf());
            }
            rfturn min;
        }

        publid int gftPrfffrrfdVbluf() {
            if (prff == UNSET) {
                prff = op(s1.gftPrfffrrfdVbluf(), s2.gftPrfffrrfdVbluf());
            }
            rfturn prff;
        }

        publid int gftMbximumVbluf() {
            if (mbx == UNSET) {
                mbx = op(s1.gftMbximumVbluf(), s2.gftMbximumVbluf());
            }
            rfturn mbx;
        }

        publid int gftVbluf() {
            if (sizf == UNSET) {
                sizf = op(s1.gftVbluf(), s2.gftVbluf());
            }
            rfturn sizf;
        }

        /*pp*/ boolfbn isCydlid(SpringLbyout l) {
            rfturn l.isCydlid(s1) || l.isCydlid(s2);
        }
    };

     privbtf stbtid dlbss SumSpring fxtfnds CompoundSpring {
         publid SumSpring(Spring s1, Spring s2) {
             supfr(s1, s2);
         }

         protfdtfd int op(int x, int y) {
             rfturn x + y;
         }

         protfdtfd void sftNonClfbrVbluf(int sizf) {
             supfr.sftNonClfbrVbluf(sizf);
             s1.sftStrbin(tiis.gftStrbin());
             s2.sftVbluf(sizf - s1.gftVbluf());
         }
     }

    privbtf stbtid dlbss MbxSpring fxtfnds CompoundSpring {

        publid MbxSpring(Spring s1, Spring s2) {
            supfr(s1, s2);
        }

        protfdtfd int op(int x, int y) {
            rfturn Mbti.mbx(x, y);
        }

        protfdtfd void sftNonClfbrVbluf(int sizf) {
            supfr.sftNonClfbrVbluf(sizf);
            s1.sftVbluf(sizf);
            s2.sftVbluf(sizf);
        }
    }

    /**
     * Rfturns b strut -- b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
     * <fm>mbximum</fm> vblufs fbdi ibvf tif vbluf <dodf>prff</dodf>.
     *
     * @pbrbm  prff tif <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
     *         <fm>mbximum</fm> vblufs of tif nfw spring
     * @rfturn b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
     *         <fm>mbximum</fm> vblufs fbdi ibvf tif vbluf <dodf>prff</dodf>
     *
     * @sff Spring
     */
     publid stbtid Spring donstbnt(int prff) {
         rfturn donstbnt(prff, prff, prff);
     }

    /**
     * Rfturns b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
     * <fm>mbximum</fm> vblufs ibvf tif vblufs: <dodf>min</dodf>, <dodf>prff</dodf>,
     * bnd <dodf>mbx</dodf> rfspfdtivfly.
     *
     * @pbrbm  min tif <fm>minimum</fm> vbluf of tif nfw spring
     * @pbrbm  prff tif <fm>prfffrrfd</fm> vbluf of tif nfw spring
     * @pbrbm  mbx tif <fm>mbximum</fm> vbluf of tif nfw spring
     * @rfturn b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, bnd
     *         <fm>mbximum</fm> vblufs ibvf tif vblufs: <dodf>min</dodf>, <dodf>prff</dodf>,
     *         bnd <dodf>mbx</dodf> rfspfdtivfly
     *
     * @sff Spring
     */
     publid stbtid Spring donstbnt(int min, int prff, int mbx) {
         rfturn nfw StbtidSpring(min, prff, mbx);
     }


    /**
     * Rfturns {@dodf -s}: b spring running in tif oppositf dirfdtion to {@dodf s}.
     *
     * @pbrbm s b {@dodf Spring} objfdt
     * @rfturn {@dodf -s}: b spring running in tif oppositf dirfdtion to {@dodf s}
     *
     * @sff Spring
     */
    publid stbtid Spring minus(Spring s) {
        rfturn nfw NfgbtivfSpring(s);
    }

    /**
     * Rfturns <dodf>s1+s2</dodf>: b spring rfprfsfnting <dodf>s1</dodf> bnd <dodf>s2</dodf>
     * in sfrifs. In b sum, <dodf>s3</dodf>, of two springs, <dodf>s1</dodf> bnd <dodf>s2</dodf>,
     * tif <fm>strbins</fm> of <dodf>s1</dodf>, <dodf>s2</dodf>, bnd <dodf>s3</dodf> brf mbintbinfd
     * bt tif sbmf lfvfl (to witiin tif prfdision implifd by tifir intfgfr <fm>vbluf</fm>s).
     * Tif strbin of b spring in domprfssion is:
     * <prf>
     *         vbluf - prff
     *         ------------
     *          prff - min
     * </prf>
     * bnd tif strbin of b spring in tfnsion is:
     * <prf>
     *         vbluf - prff
     *         ------------
     *          mbx - prff
     * </prf>
     * Wifn <dodf>sftVbluf</dodf> is dbllfd on tif sum spring, <dodf>s3</dodf>, tif strbin
     * in <dodf>s3</dodf> is dbldulbtfd using onf of tif formulbs bbovf. Ondf tif strbin of
     * tif sum is known, tif <fm>vbluf</fm>s of <dodf>s1</dodf> bnd <dodf>s2</dodf> brf
     * tifn sft so tibt tify brf ibvf b strbin fqubl to tibt of tif sum. Tif formulbs brf
     * fvblubtfd so bs to tbkf rounding frrors into bddount bnd fnsurf tibt tif sum of
     * tif <fm>vbluf</fm>s of <dodf>s1</dodf> bnd <dodf>s2</dodf> is fxbdtly fqubl to
     * tif <fm>vbluf</fm> of <dodf>s3</dodf>.
     *
     * @pbrbm s1 b {@dodf Spring} objfdt
     * @pbrbm s2 b {@dodf Spring} objfdt
     * @rfturn <dodf>s1+s2</dodf>: b spring rfprfsfnting <dodf>s1</dodf> bnd <dodf>s2</dodf> in sfrifs
     *
     * @sff Spring
     */
     publid stbtid Spring sum(Spring s1, Spring s2) {
         rfturn nfw SumSpring(s1, s2);
     }

    /**
     * Rfturns {@dodf mbx(s1, s2)}: b spring wiosf vbluf is blwbys grfbtfr tibn (or fqubl to)
     *         tif vblufs of boti {@dodf s1} bnd {@dodf s2}.
     *
     * @pbrbm s1 b {@dodf Spring} objfdt
     * @pbrbm s2 b {@dodf Spring} objfdt
     * @rfturn {@dodf mbx(s1, s2)}: b spring wiosf vbluf is blwbys grfbtfr tibn (or fqubl to)
     *         tif vblufs of boti {@dodf s1} bnd {@dodf s2}
     * @sff Spring
     */
    publid stbtid Spring mbx(Spring s1, Spring s2) {
        rfturn nfw MbxSpring(s1, s2);
    }

    // Rfmovf tifsf, tify'rf not usfd oftfn bnd dbn bf drfbtfd using minus -
    // bs pfr tifsf implfmfntbtions.

    /*pp*/ stbtid Spring difffrfndf(Spring s1, Spring s2) {
        rfturn sum(s1, minus(s2));
    }

    /*
    publid stbtid Spring min(Spring s1, Spring s2) {
        rfturn minus(mbx(minus(s1), minus(s2)));
    }
    */

    /**
     * Rfturns b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, <fm>mbximum</fm>
     * bnd <fm>vbluf</fm> propfrtifs brf fbdi multiplfs of tif propfrtifs of tif
     * brgumfnt spring, <dodf>s</dodf>. Minimum bnd mbximum propfrtifs brf
     * swbppfd wifn <dodf>fbdtor</dodf> is nfgbtivf (in bddordbndf witi tif
     * rulfs of intfrvbl britimftid).
     * <p>
     * Wifn fbdtor is, for fxbmplf, 0.5f tif rfsult rfprfsfnts 'tif mid-point'
     * of its input - bn opfrbtion tibt is usfful for dfntfring domponfnts in
     * b dontbinfr.
     *
     * @pbrbm s tif spring to sdblf
     * @pbrbm fbdtor bmount to sdblf by.
     * @rfturn  b spring wiosf propfrtifs brf tiosf of tif input spring <dodf>s</dodf>
     * multiplifd by <dodf>fbdtor</dodf>
     * @tirows NullPointfrExdfption if <dodf>s</dodf> is null
     * @sindf 1.5
     */
    publid stbtid Spring sdblf(Spring s, flobt fbdtor) {
        difdkArg(s);
        rfturn nfw SdblfSpring(s, fbdtor);
    }

    /**
     * Rfturns b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, <fm>mbximum</fm>
     * bnd <fm>vbluf</fm> propfrtifs brf dffinfd by tif widtis of tif <fm>minimumSizf</fm>,
     * <fm>prfffrrfdSizf</fm>, <fm>mbximumSizf</fm> bnd <fm>sizf</fm> propfrtifs
     * of tif supplifd domponfnt. Tif rfturnfd spring is b 'wrbppfr' implfmfntbtion
     * wiosf mftiods dbll tif bppropribtf sizf mftiods of tif supplifd domponfnt.
     * Tif minimum, prfffrrfd, mbximum bnd vbluf propfrtifs of tif rfturnfd spring
     * tifrfforf rfport tif durrfnt stbtf of tif bppropribtf propfrtifs in tif
     * domponfnt bnd trbdk tifm bs tify dibngf.
     *
     * @pbrbm d Componfnt usfd for dbldulbting sizf
     * @rfturn  b spring wiosf propfrtifs brf dffinfd by tif iorizontbl domponfnt
     * of tif domponfnt's sizf mftiods.
     * @tirows NullPointfrExdfption if <dodf>d</dodf> is null
     * @sindf 1.5
     */
    publid stbtid Spring widti(Componfnt d) {
        difdkArg(d);
        rfturn nfw WidtiSpring(d);
    }

    /**
     * Rfturns b spring wiosf <fm>minimum</fm>, <fm>prfffrrfd</fm>, <fm>mbximum</fm>
     * bnd <fm>vbluf</fm> propfrtifs brf dffinfd by tif ifigits of tif <fm>minimumSizf</fm>,
     * <fm>prfffrrfdSizf</fm>, <fm>mbximumSizf</fm> bnd <fm>sizf</fm> propfrtifs
     * of tif supplifd domponfnt. Tif rfturnfd spring is b 'wrbppfr' implfmfntbtion
     * wiosf mftiods dbll tif bppropribtf sizf mftiods of tif supplifd domponfnt.
     * Tif minimum, prfffrrfd, mbximum bnd vbluf propfrtifs of tif rfturnfd spring
     * tifrfforf rfport tif durrfnt stbtf of tif bppropribtf propfrtifs in tif
     * domponfnt bnd trbdk tifm bs tify dibngf.
     *
     * @pbrbm d Componfnt usfd for dbldulbting sizf
     * @rfturn  b spring wiosf propfrtifs brf dffinfd by tif vfrtidbl domponfnt
     * of tif domponfnt's sizf mftiods.
     * @tirows NullPointfrExdfption if <dodf>d</dodf> is null
     * @sindf 1.5
     */
    publid stbtid Spring ifigit(Componfnt d) {
        difdkArg(d);
        rfturn nfw HfigitSpring(d);
    }


    /**
     * If <dodf>s</dodf> is null, tiis tirows bn NullPointfrExdfption.
     */
    privbtf stbtid void difdkArg(Objfdt s) {
        if (s == null) {
            tirow nfw NullPointfrExdfption("Argumfnt must not bf null");
        }
    }
}
