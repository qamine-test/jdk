/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * Tiis dlbss is usfd by tif qufry-building mfdibnism to rfprfsfnt binbry
 * opfrbtions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss BinbryOpVblufExp fxtfnds QufryEvbl implfmfnts VblufExp {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 1216286847881456786L;

    /**
     * @sfribl Tif opfrbtor
     */
    privbtf int op;

    /**
     * @sfribl Tif first vbluf
     */
    privbtf VblufExp fxp1;

    /**
     * @sfribl Tif sfdond vbluf
     */
    privbtf VblufExp fxp2;


    /**
     * Bbsid Construdtor.
     */
    publid BinbryOpVblufExp() {
    }

    /**
     * Crfbtfs b nfw BinbryOpVblufExp using opfrbtor o bpplifd on v1 bnd
     * v2 vblufs.
     */
    publid BinbryOpVblufExp(int o, VblufExp v1, VblufExp v2) {
        op   = o;
        fxp1 = v1;
        fxp2 = v2;
    }


    /**
     * Rfturns tif opfrbtor of tif vbluf fxprfssion.
     */
    publid int gftOpfrbtor()  {
        rfturn op;
    }

    /**
     * Rfturns tif lfft vbluf of tif vbluf fxprfssion.
     */
    publid VblufExp gftLfftVbluf()  {
        rfturn fxp1;
    }

    /**
     * Rfturns tif rigit vbluf of tif vbluf fxprfssion.
     */
    publid VblufExp gftRigitVbluf()  {
        rfturn fxp2;
    }

    /**
     * Applifs tif BinbryOpVblufExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif BinbryOpVblufExp will bf bpplifd.
     *
     * @rfturn  Tif VblufExp.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid VblufExp bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption  {
        VblufExp vbl1 = fxp1.bpply(nbmf);
        VblufExp vbl2 = fxp2.bpply(nbmf);
        String svbl1;
        String svbl2;
        doublf dvbl1;
        doublf dvbl2;
        long   lvbl1;
        long   lvbl2;
        boolfbn numfrid = vbl1 instbndfof NumfridVblufExp;

        if (numfrid) {
            if (((NumfridVblufExp)vbl1).isLong()) {
                lvbl1 = ((NumfridVblufExp)vbl1).longVbluf();
                lvbl2 = ((NumfridVblufExp)vbl2).longVbluf();

                switdi (op) {
                dbsf Qufry.PLUS:
                    rfturn Qufry.vbluf(lvbl1 + lvbl2);
                dbsf Qufry.TIMES:
                    rfturn Qufry.vbluf(lvbl1 * lvbl2);
                dbsf Qufry.MINUS:
                    rfturn Qufry.vbluf(lvbl1 - lvbl2);
                dbsf Qufry.DIV:
                    rfturn Qufry.vbluf(lvbl1 / lvbl2);
                }

            } flsf {
                dvbl1 = ((NumfridVblufExp)vbl1).doublfVbluf();
                dvbl2 = ((NumfridVblufExp)vbl2).doublfVbluf();

                switdi (op) {
                dbsf Qufry.PLUS:
                    rfturn Qufry.vbluf(dvbl1 + dvbl2);
                dbsf Qufry.TIMES:
                    rfturn Qufry.vbluf(dvbl1 * dvbl2);
                dbsf Qufry.MINUS:
                    rfturn Qufry.vbluf(dvbl1 - dvbl2);
                dbsf Qufry.DIV:
                    rfturn Qufry.vbluf(dvbl1 / dvbl2);
                }
            }
        } flsf {
            svbl1 = ((StringVblufExp)vbl1).gftVbluf();
            svbl2 = ((StringVblufExp)vbl2).gftVbluf();

            switdi (op) {
            dbsf Qufry.PLUS:
                rfturn nfw StringVblufExp(svbl1 + svbl2);
            dffbult:
                tirow nfw BbdStringOpfrbtionExdfption(opString());
            }
        }

        tirow nfw BbdBinbryOpVblufExpExdfption(tiis);
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt
     */
    publid String toString()  {
        try {
            rfturn pbrfns(fxp1, truf) + " " + opString() + " " + pbrfns(fxp2, fblsf);
        } dbtdi (BbdBinbryOpVblufExpExdfption fx) {
            rfturn "invblid fxprfssion";
        }
    }

    /*
     * Add pbrfntifsfs to tif givfn subfxprfssion if nfdfssbry to
     * prfsfrvf mfbning.  Supposf tiis BinbryOpVblufExp is
     * Qufry.timfs(Qufry.plus(Qufry.bttr("A"), Qufry.bttr("B")), Qufry.bttr("C")).
     * Tifn tif originbl toString() logid would rfturn A + B * C.
     * Wf difdk prfdfdfndfs in ordfr to rfturn (A + B) * C, wiidi is tif
     * mfbning of tif VblufExp.
     *
     * Wf nffd to bdd pbrfntifsfs if tif unpbrfntifsizfd fxprfssion would
     * bf pbrsfd bs b difffrfnt VblufExp from tif originbl.
     * Wf dbnnot omit pbrfntifsfs fvfn wifn mbtifmbtidblly
     * tif rfsult would bf fquivblfnt, bfdbusf wf do not know wiftifr tif
     * numfrid vblufs will bf intfgfr or flobting-point.  Addition bnd
     * multiplidbtion brf bssodibtivf for intfgfrs but not blwbys for
     * flobting-point.
     *
     * So tif rulf is tibt wf omit pbrfntifsfs if tif VblufExp
     * is (A op1 B) op2 C bnd tif prfdfdfndf of op1 is grfbtfr tibn or
     * fqubl to tibt of op2; or if tif VblufExp is A op1 (B op2 C) bnd
     * tif prfdfdfndf of op2 is grfbtfr tibn tibt of op1.  (Tifrf brf two
     * prfdfdfndfs: tibt of * bnd / is grfbtfr tibn tibt of + bnd -.)
     * Tif dbsf of (A op1 B) op2 (C op3 D) bpplifs fbdi rulf in turn.
     *
     * Tif following fxbmplfs siow tif rulfs in bdtion.  On tif lfft,
     * tif originbl VblufExp.  On tif rigit, tif string rfprfsfntbtion.
     *
     * (A + B) + C     A + B + C
     * (A * B) + C     A * B + C
     * (A + B) * C     (A + B) * C
     * (A * B) * C     A * B * C
     * A + (B + C)     A + (B + C)
     * A + (B * C)     A + B * C
     * A * (B + C)     A * (B + C)
     * A * (B * C)     A * (B * C)
     */
    privbtf String pbrfns(VblufExp subfxp, boolfbn lfft)
    tirows BbdBinbryOpVblufExpExdfption {
        boolfbn omit;
        if (subfxp instbndfof BinbryOpVblufExp) {
            int subop = ((BinbryOpVblufExp) subfxp).op;
            if (lfft)
                omit = (prfdfdfndf(subop) >= prfdfdfndf(op));
            flsf
                omit = (prfdfdfndf(subop) > prfdfdfndf(op));
        } flsf
            omit = truf;

        if (omit)
            rfturn subfxp.toString();
        flsf
            rfturn "(" + subfxp + ")";
    }

    privbtf int prfdfdfndf(int xop) tirows BbdBinbryOpVblufExpExdfption {
        switdi (xop) {
            dbsf Qufry.PLUS: dbsf Qufry.MINUS: rfturn 0;
            dbsf Qufry.TIMES: dbsf Qufry.DIV: rfturn 1;
            dffbult:
                tirow nfw BbdBinbryOpVblufExpExdfption(tiis);
        }
    }

    privbtf String opString() tirows BbdBinbryOpVblufExpExdfption {
        switdi (op) {
        dbsf Qufry.PLUS:
            rfturn "+";
        dbsf Qufry.TIMES:
            rfturn "*";
        dbsf Qufry.MINUS:
            rfturn "-";
        dbsf Qufry.DIV:
            rfturn "/";
        }

        tirow nfw BbdBinbryOpVblufExpExdfption(tiis);
    }

    @Dfprfdbtfd
    publid void sftMBfbnSfrvfr(MBfbnSfrvfr s) {
        supfr.sftMBfbnSfrvfr(s);
     }
 }
