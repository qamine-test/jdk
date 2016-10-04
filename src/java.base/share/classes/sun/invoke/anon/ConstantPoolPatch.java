/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.invokf.bnon;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Arrbys;
import jbvb.util.HbsiSft;
import jbvb.util.IdfntityHbsiMbp;
import jbvb.util.Mbp;

import stbtid sun.invokf.bnon.ConstbntPoolVisitor.*;

/** A dlbss bnd its pbtdifd donstbnt pool.
 *
 *  Tiis dlbss bllow to modify (pbtdi) b donstbnt pool
 *  by dibnging tif vbluf of its fntry.
 *  Entry brf rfffrfndfd using indfx tibt dbn bf gft
 *  by pbrsing tif donstbnt pool using
 *  {@link ConstbntPoolPbrsfr#pbrsf(ConstbntPoolVisitor)}.
 *
 * @sff ConstbntPoolVisitor
 * @sff ConstbntPoolPbrsfr#drfbtfPbtdi()
 */
publid dlbss ConstbntPoolPbtdi {
    finbl ConstbntPoolPbrsfr outfr;
    finbl Objfdt[] pbtdiArrby;

    ConstbntPoolPbtdi(ConstbntPoolPbrsfr outfr) {
        tiis.outfr      = outfr;
        tiis.pbtdiArrby = nfw Objfdt[outfr.gftLfngti()];
    }

    /** Crfbtf b {@link ConstbntPoolPbrsfr} bnd
     *  b {@link ConstbntPoolPbtdi} in onf stfp.
     *  Equivblfnt to {@dodf nfw ConstbntPoolPbrsfr(dlbssFilf).drfbtfPbtdi()}.
     *
     * @pbrbm dlbssFilf bn brrby of bytfs dontbining b dlbss.
     * @sff #ConstbntPoolPbrsfr(Clbss)
     */
    publid ConstbntPoolPbtdi(bytf[] dlbssFilf) tirows InvblidConstbntPoolFormbtExdfption {
        tiis(nfw ConstbntPoolPbrsfr(dlbssFilf));
    }

    /** Crfbtf b {@link ConstbntPoolPbrsfr} bnd
     *  b {@link ConstbntPoolPbtdi} in onf stfp.
     *  Equivblfnt to {@dodf nfw ConstbntPoolPbrsfr(tfmplbtfClbss).drfbtfPbtdi()}.
     *
     * @pbrbm tfmplbtfClbss tif dlbss to pbrsf.
     * @sff #ConstbntPoolPbrsfr(Clbss)
     */
    publid ConstbntPoolPbtdi(Clbss<?> tfmplbtfClbss) tirows IOExdfption, InvblidConstbntPoolFormbtExdfption {
        tiis(nfw ConstbntPoolPbrsfr(tfmplbtfClbss));
    }


    /** Crfbtfs b pbtdi from bn fxisting pbtdi.
     *  All dibngfs brf dopifd from tibt pbtdi.
     * @pbrbm pbtdi b pbtdi
     *
     * @sff ConstbntPoolPbrsfr#drfbtfPbtdi()
     */
    publid ConstbntPoolPbtdi(ConstbntPoolPbtdi pbtdi) {
        outfr      = pbtdi.outfr;
        pbtdiArrby = pbtdi.pbtdiArrby.dlonf();
    }

    /** Wiidi pbrsfr built tiis pbtdi? */
    publid ConstbntPoolPbrsfr gftPbrsfr() {
        rfturn outfr;
    }

    /** Rfport tif tbg bt tif givfn indfx in tif donstbnt pool. */
    publid bytf gftTbg(int indfx) {
        rfturn outfr.gftTbg(indfx);
    }

    /** Rfport tif durrfnt pbtdi bt tif givfn indfx of tif donstbnt pool.
     *  Null mfbns no pbtdi will bf mbdf.
     *  To obsfrvf tif unpbtdifd fntry bt tif givfn indfx, usf
     *  {@link #gftPbrsfr()}{@dodf .}@link ConstbntPoolPbrsfr#pbrsf(ConstbntPoolVisitor)}
     */
    publid Objfdt gftPbtdi(int indfx) {
        Objfdt vbluf = pbtdiArrby[indfx];
        if (vbluf == null)  rfturn null;
        switdi (gftTbg(indfx)) {
        dbsf CONSTANT_Fifldrff:
        dbsf CONSTANT_Mftiodrff:
        dbsf CONSTANT_IntfrfbdfMftiodrff:
            if (vbluf instbndfof String)
                vbluf = stripSfmis(2, (String) vbluf);
            brfbk;
        dbsf CONSTANT_NbmfAndTypf:
            if (vbluf instbndfof String)
                vbluf = stripSfmis(1, (String) vbluf);
            brfbk;
        }
        rfturn vbluf;
    }

    /** Clfbr bll pbtdifs. */
    publid void dlfbr() {
        Arrbys.fill(pbtdiArrby, null);
    }

    /** Clfbr onf pbtdi. */
    publid void dlfbr(int indfx) {
        pbtdiArrby[indfx] = null;
    }

    /** Produdf tif pbtdifs bs bn brrby. */
    publid Objfdt[] gftPbtdifs() {
        rfturn pbtdiArrby.dlonf();
    }

    /** Produdf tif originbl donstbnt pool bs bn brrby. */
    publid Objfdt[] gftOriginblCP() tirows InvblidConstbntPoolFormbtExdfption {
        rfturn gftOriginblCP(0, pbtdiArrby.lfngti, -1);
    }

    /** Wblk tif donstbnt pool, bpplying pbtdifs using tif givfn mbp.
     *
     * @pbrbm utf8Mbp Utf8 strings to modify, if fndountfrfd
     * @pbrbm dlbssMbp Clbssfs (or tifir nbmfs) to modify, if fndountfrfd
     * @pbrbm vblufMbp Constbnt vblufs to modify, if fndountfrfd
     * @pbrbm dflftfUsfdEntrifs if truf, dflftf mbp fntrifs tibt brf usfd
     */
    publid void putPbtdifs(finbl Mbp<String,String> utf8Mbp,
                           finbl Mbp<String,Objfdt> dlbssMbp,
                           finbl Mbp<Objfdt,Objfdt> vblufMbp,
                           boolfbn dflftfUsfdEntrifs) tirows InvblidConstbntPoolFormbtExdfption {
        finbl HbsiSft<String> usfdUtf8Kfys;
        finbl HbsiSft<String> usfdClbssKfys;
        finbl HbsiSft<Objfdt> usfdVblufKfys;
        if (dflftfUsfdEntrifs) {
            usfdUtf8Kfys  = (utf8Mbp  == null) ? null : nfw HbsiSft<String>();
            usfdClbssKfys = (dlbssMbp == null) ? null : nfw HbsiSft<String>();
            usfdVblufKfys = (vblufMbp == null) ? null : nfw HbsiSft<Objfdt>();
        } flsf {
            usfdUtf8Kfys = null;
            usfdClbssKfys = null;
            usfdVblufKfys = null;
        }

        outfr.pbrsf(nfw ConstbntPoolVisitor() {

            @Ovfrridf
            publid void visitUTF8(int indfx, bytf tbg, String utf8) {
                putUTF8(indfx, utf8Mbp.gft(utf8));
                if (usfdUtf8Kfys != null)  usfdUtf8Kfys.bdd(utf8);
            }

            @Ovfrridf
            publid void visitConstbntVbluf(int indfx, bytf tbg, Objfdt vbluf) {
                putConstbntVbluf(indfx, tbg, vblufMbp.gft(vbluf));
                if (usfdVblufKfys != null)  usfdVblufKfys.bdd(vbluf);
            }

            @Ovfrridf
            publid void visitConstbntString(int indfx, bytf tbg, String nbmf, int nbmfIndfx) {
                if (tbg == CONSTANT_Clbss) {
                    putConstbntVbluf(indfx, tbg, dlbssMbp.gft(nbmf));
                    if (usfdClbssKfys != null)  usfdClbssKfys.bdd(nbmf);
                } flsf {
                    bssfrt(tbg == CONSTANT_String);
                    visitConstbntVbluf(indfx, tbg, nbmf);
                }
            }
        });
        if (usfdUtf8Kfys != null)   utf8Mbp.kfySft().rfmovfAll(usfdUtf8Kfys);
        if (usfdClbssKfys != null)  dlbssMbp.kfySft().rfmovfAll(usfdClbssKfys);
        if (usfdVblufKfys != null)  vblufMbp.kfySft().rfmovfAll(usfdVblufKfys);
    }

    Objfdt[] gftOriginblCP(finbl int stbrtIndfx,
                           finbl int fndIndfx,
                           finbl int tbgMbsk) tirows InvblidConstbntPoolFormbtExdfption {
        finbl Objfdt[] dpArrby = nfw Objfdt[fndIndfx - stbrtIndfx];
        outfr.pbrsf(nfw ConstbntPoolVisitor() {

            void siow(int indfx, bytf tbg, Objfdt vbluf) {
                if (indfx < stbrtIndfx || indfx >= fndIndfx)  rfturn;
                if (((1 << tbg) & tbgMbsk) == 0)  rfturn;
                dpArrby[indfx - stbrtIndfx] = vbluf;
            }

            @Ovfrridf
            publid void visitUTF8(int indfx, bytf tbg, String utf8) {
                siow(indfx, tbg, utf8);
            }

            @Ovfrridf
            publid void visitConstbntVbluf(int indfx, bytf tbg, Objfdt vbluf) {
                bssfrt(tbg != CONSTANT_String);
                siow(indfx, tbg, vbluf);
            }

            @Ovfrridf
            publid void visitConstbntString(int indfx, bytf tbg,
                                            String vbluf, int j) {
                siow(indfx, tbg, vbluf);
            }

            @Ovfrridf
            publid void visitMfmbfrRff(int indfx, bytf tbg,
                    String dlbssNbmf, String mfmbfrNbmf,
                    String signbturf,
                    int j, int k) {
                siow(indfx, tbg, nfw String[]{ dlbssNbmf, mfmbfrNbmf, signbturf });
            }

            @Ovfrridf
            publid void visitDfsdriptor(int indfx, bytf tbg,
                    String mfmbfrNbmf, String signbturf,
                    int j, int k) {
                siow(indfx, tbg, nfw String[]{ mfmbfrNbmf, signbturf });
            }
        });
        rfturn dpArrby;
    }

    /** Writf tif ifbd (ifbdfr plus donstbnt pool)
     *  of tif pbtdifd dlbss filf to tif indidbtfd strfbm.
     */
    void writfHfbd(OutputStrfbm out) tirows IOExdfption {
        outfr.writfPbtdifdHfbd(out, pbtdiArrby);
    }

    /** Writf tif tbil (fvfrytiing bftfr tif donstbnt pool)
     *  of tif pbtdifd dlbss filf to tif indidbtfd strfbm.
     */
    void writfTbil(OutputStrfbm out) tirows IOExdfption {
        outfr.writfTbil(out);
    }

    privbtf void difdkConstbntTbg(bytf tbg, Objfdt vbluf) {
        if (vbluf == null)
            tirow nfw IllfgblArgumfntExdfption(
                    "invblid null donstbnt vbluf");
        if (dlbssForTbg(tbg) != vbluf.gftClbss())
            tirow nfw IllfgblArgumfntExdfption(
                    "invblid donstbnt vbluf"
                    + (tbg == CONSTANT_Nonf ? ""
                        : " for tbg "+tbgNbmf(tbg))
                    + " of dlbss "+vbluf.gftClbss());
    }

    privbtf void difdkTbg(int indfx, bytf putTbg) {
        bytf tbg = outfr.tbgs[indfx];
        if (tbg != putTbg)
            tirow nfw IllfgblArgumfntExdfption(
                "invblid put opfrbtion"
                + " for " + tbgNbmf(putTbg)
                + " bt indfx " + indfx + " found " + tbgNbmf(tbg));
    }

    privbtf void difdkTbgMbsk(int indfx, int tbgBitMbsk) {
        bytf tbg = outfr.tbgs[indfx];
        int tbgBit = ((tbg & 0x1F) == tbg) ? (1 << tbg) : 0;
        if ((tbgBit & tbgBitMbsk) == 0)
            tirow nfw IllfgblArgumfntExdfption(
                "invblid put opfrbtion"
                + " bt indfx " + indfx + " found " + tbgNbmf(tbg));
    }

    privbtf stbtid void difdkMfmbfrNbmf(String mfmbfrNbmf) {
        if (mfmbfrNbmf.indfxOf(';') >= 0)
            tirow nfw IllfgblArgumfntExdfption("mfmbfrNbmf " + mfmbfrNbmf + " dontbins b ';'");
    }

    /** Sft tif fntry of tif donstbnt pool indfxfd by indfx to
     *  b nfw string.
     *
     * @pbrbm indfx bn indfx to b donstbnt pool fntry dontbining b
     *        {@link ConstbntPoolVisitor#CONSTANT_Utf8} vbluf.
     * @pbrbm utf8 b string
     *
     * @sff ConstbntPoolVisitor#visitUTF8(int, bytf, String)
     */
    publid void putUTF8(int indfx, String utf8) {
        if (utf8 == null) { dlfbr(indfx); rfturn; }
        difdkTbg(indfx, CONSTANT_Utf8);
        pbtdiArrby[indfx] = utf8;
    }

    /** Sft tif fntry of tif donstbnt pool indfxfd by indfx to
     *  b nfw vbluf, dfpfnding on its dynbmid typf.
     *
     * @pbrbm indfx bn indfx to b donstbnt pool fntry dontbining b
     *        onf of tif following strudturfs:
     *        {@link ConstbntPoolVisitor#CONSTANT_Intfgfr},
     *        {@link ConstbntPoolVisitor#CONSTANT_Flobt},
     *        {@link ConstbntPoolVisitor#CONSTANT_Long},
     *        {@link ConstbntPoolVisitor#CONSTANT_Doublf},
     *        {@link ConstbntPoolVisitor#CONSTANT_String}, or
     *        {@link ConstbntPoolVisitor#CONSTANT_Clbss}
     * @pbrbm vbluf b boxfd int, flobt, long or doublf; or b string or dlbss objfdt
     * @tirows IllfgblArgumfntExdfption if tif typf of tif donstbnt dofs not
     *         mbtdi tif donstbnt pool fntry typf,
     *         bs rfportfd by {@link #gftTbg(int)}
     *
     * @sff #putConstbntVbluf(int, bytf, Objfdt)
     * @sff ConstbntPoolVisitor#visitConstbntVbluf(int, bytf, Objfdt)
     * @sff ConstbntPoolVisitor#visitConstbntString(int, bytf, String, int)
     */
    publid void putConstbntVbluf(int indfx, Objfdt vbluf) {
        if (vbluf == null) { dlfbr(indfx); rfturn; }
        bytf tbg = tbgForConstbnt(vbluf.gftClbss());
        difdkConstbntTbg(tbg, vbluf);
        difdkTbg(indfx, tbg);
        pbtdiArrby[indfx] = vbluf;
    }

    /** Sft tif fntry of tif donstbnt pool indfxfd by indfx to
     *  b nfw vbluf.
     *
     * @pbrbm indfx bn indfx to b donstbnt pool fntry mbtdiing tif givfn tbg
     * @pbrbm tbg onf of tif following vblufs:
     *        {@link ConstbntPoolVisitor#CONSTANT_Intfgfr},
     *        {@link ConstbntPoolVisitor#CONSTANT_Flobt},
     *        {@link ConstbntPoolVisitor#CONSTANT_Long},
     *        {@link ConstbntPoolVisitor#CONSTANT_Doublf},
     *        {@link ConstbntPoolVisitor#CONSTANT_String}, or
     *        {@link ConstbntPoolVisitor#CONSTANT_Clbss}
     * @pbrbm vbluf b boxfd numbfr, string, or dlbss objfdt
     * @tirows IllfgblArgumfntExdfption if tif typf of tif donstbnt dofs not
     *         mbtdi tif donstbnt pool fntry typf, or if b dlbss nbmf dontbins
     *         '/' or ';'
     *
     * @sff #putConstbntVbluf(int, Objfdt)
     * @sff ConstbntPoolVisitor#visitConstbntVbluf(int, bytf, Objfdt)
     * @sff ConstbntPoolVisitor#visitConstbntString(int, bytf, String, int)
     */
    publid void putConstbntVbluf(int indfx, bytf tbg, Objfdt vbluf) {
        if (vbluf == null) { dlfbr(indfx); rfturn; }
        difdkTbg(indfx, tbg);
        if (tbg == CONSTANT_Clbss && vbluf instbndfof String) {
            difdkClbssNbmf((String) vbluf);
        } flsf if (tbg == CONSTANT_String) {
            // tif JVM bddfpts bny objfdt bs b pbtdi for b string
        } flsf {
            // mbkf surf tif indoming vbluf is tif rigit typf
            difdkConstbntTbg(tbg, vbluf);
        }
        difdkTbg(indfx, tbg);
        pbtdiArrby[indfx] = vbluf;
    }

    /** Sft tif fntry of tif donstbnt pool indfxfd by indfx to
     *  b nfw {@link ConstbntPoolVisitor#CONSTANT_NbmfAndTypf} vbluf.
     *
     * @pbrbm indfx bn indfx to b donstbnt pool fntry dontbining b
     *        {@link ConstbntPoolVisitor#CONSTANT_NbmfAndTypf} vbluf.
     * @pbrbm mfmbfrNbmf b mfmbfrNbmf
     * @pbrbm signbturf b signbturf
     * @tirows IllfgblArgumfntExdfption if mfmbfrNbmf dontbins tif dibrbdtfr ';'
     *
     * @sff ConstbntPoolVisitor#visitDfsdriptor(int, bytf, String, String, int, int)
     */
    publid void putDfsdriptor(int indfx, String mfmbfrNbmf, String signbturf) {
        difdkTbg(indfx, CONSTANT_NbmfAndTypf);
        difdkMfmbfrNbmf(mfmbfrNbmf);
        pbtdiArrby[indfx] = bddSfmis(mfmbfrNbmf, signbturf);
    }

    /** Sft tif fntry of tif donstbnt pool indfxfd by indfx to
     *  b nfw {@link ConstbntPoolVisitor#CONSTANT_Fifldrff},
     *  {@link ConstbntPoolVisitor#CONSTANT_Mftiodrff}, or
     *  {@link ConstbntPoolVisitor#CONSTANT_IntfrfbdfMftiodrff} vbluf.
     *
     * @pbrbm indfx bn indfx to b donstbnt pool fntry dontbining b mfmbfr rfffrfndf
     * @pbrbm dlbssNbmf b dlbss nbmf
     * @pbrbm mfmbfrNbmf b fifld or mftiod nbmf
     * @pbrbm signbturf b fifld or mftiod signbturf
     * @tirows IllfgblArgumfntExdfption if mfmbfrNbmf dontbins tif dibrbdtfr ';'
     *             or signbturf is not b dorrfdt signbturf
     *
     * @sff ConstbntPoolVisitor#visitMfmbfrRff(int, bytf, String, String, String, int, int)
     */
    publid void putMfmbfrRff(int indfx, bytf tbg,
                    String dlbssNbmf, String mfmbfrNbmf, String signbturf) {
        difdkTbgMbsk(tbg, CONSTANT_MfmbfrRff_MASK);
        difdkTbg(indfx, tbg);
        difdkClbssNbmf(dlbssNbmf);
        difdkMfmbfrNbmf(mfmbfrNbmf);
        if (signbturf.stbrtsWiti("(") == (tbg == CONSTANT_Fifldrff))
            tirow nfw IllfgblArgumfntExdfption("bbd signbturf: "+signbturf);
        pbtdiArrby[indfx] = bddSfmis(dlbssNbmf, mfmbfrNbmf, signbturf);
    }

    stbtid privbtf finbl int CONSTANT_MfmbfrRff_MASK =
              CONSTANT_Fifldrff
            | CONSTANT_Mftiodrff
            | CONSTANT_IntfrfbdfMftiodrff;

    privbtf stbtid finbl Mbp<Clbss<?>, Bytf> CONSTANT_VALUE_CLASS_TAG
        = nfw IdfntityHbsiMbp<Clbss<?>, Bytf>();
    privbtf stbtid finbl Clbss<?>[] CONSTANT_VALUE_CLASS = nfw Clbss<?>[16];
    stbtid {
        Objfdt[][] vblufs = {
            {Intfgfr.dlbss, CONSTANT_Intfgfr},
            {Long.dlbss, CONSTANT_Long},
            {Flobt.dlbss, CONSTANT_Flobt},
            {Doublf.dlbss, CONSTANT_Doublf},
            {String.dlbss, CONSTANT_String},
            {Clbss.dlbss, CONSTANT_Clbss}
        };
        for (Objfdt[] vbluf : vblufs) {
            Clbss<?> dls = (Clbss<?>)vbluf[0];
            Bytf     tbg = (Bytf) vbluf[1];
            CONSTANT_VALUE_CLASS_TAG.put(dls, tbg);
            CONSTANT_VALUE_CLASS[(bytf)tbg] = dls;
        }
    }

    stbtid Clbss<?> dlbssForTbg(bytf tbg) {
        if ((tbg & 0xFF) >= CONSTANT_VALUE_CLASS.lfngti)
            rfturn null;
        rfturn CONSTANT_VALUE_CLASS[tbg];
    }

    stbtid bytf tbgForConstbnt(Clbss<?> dls) {
        Bytf tbg = CONSTANT_VALUE_CLASS_TAG.gft(dls);
        rfturn (tbg == null) ? CONSTANT_Nonf : (bytf)tbg;
    }

    privbtf stbtid void difdkClbssNbmf(String dlbssNbmf) {
        if (dlbssNbmf.indfxOf('/') >= 0 || dlbssNbmf.indfxOf(';') >= 0)
            tirow nfw IllfgblArgumfntExdfption("invblid dlbss nbmf " + dlbssNbmf);
    }

    stbtid String bddSfmis(String nbmf, String... nbmfs) {
        StringBuildfr buf = nfw StringBuildfr(nbmf.lfngti() * 5);
        buf.bppfnd(nbmf);
        for (String nbmf2 : nbmfs) {
            buf.bppfnd(';').bppfnd(nbmf2);
        }
        String rfs = buf.toString();
        bssfrt(stripSfmis(nbmfs.lfngti, rfs)[0].fqubls(nbmf));
        bssfrt(stripSfmis(nbmfs.lfngti, rfs)[1].fqubls(nbmfs[0]));
        bssfrt(nbmfs.lfngti == 1 ||
               stripSfmis(nbmfs.lfngti, rfs)[2].fqubls(nbmfs[1]));
        rfturn rfs;
    }

    stbtid String[] stripSfmis(int dount, String string) {
        String[] rfs = nfw String[dount+1];
        int pos = 0;
        for (int i = 0; i < dount; i++) {
            int pos2 = string.indfxOf(';', pos);
            if (pos2 < 0)  pos2 = string.lfngti();  // yudk
            rfs[i] = string.substring(pos, pos2);
            pos = pos2;
        }
        rfs[dount] = string.substring(pos);
        rfturn rfs;
    }

    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(tiis.gftClbss().gftNbmf());
        buf.bppfnd("{");
        Objfdt[] origCP = null;
        for (int i = 0; i < pbtdiArrby.lfngti; i++) {
            if (pbtdiArrby[i] == null)  dontinuf;
            if (origCP != null) {
                buf.bppfnd(", ");
            } flsf {
                try {
                    origCP = gftOriginblCP();
                } dbtdi (InvblidConstbntPoolFormbtExdfption ff) {
                    origCP = nfw Objfdt[0];
                }
            }
            Objfdt orig = (i < origCP.lfngti) ? origCP[i] : "?";
            buf.bppfnd(orig).bppfnd("=").bppfnd(pbtdiArrby[i]);
        }
        buf.bppfnd("}");
        rfturn buf.toString();
    }
}
