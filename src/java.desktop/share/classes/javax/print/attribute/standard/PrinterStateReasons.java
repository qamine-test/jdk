/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.util.AbstrbdtSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Sft;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;

/**
 * Clbss PrintfrStbtfRfbsons is b printing bttributf dlbss, b sft of
 * fnumfrbtion vblufs, tibt providfs bdditionbl informbtion bbout tif
 * printfr's durrfnt stbtf, i.f., informbtion tibt bugmfnts tif vbluf of tif
 * printfr's {@link PrintfrStbtf PrintfrStbtf} bttributf.
 * <P>
 * Instbndfs of {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} do not bppfbr in
 *  b Print Sfrvidf's bttributf sft dirfdtly. Rbtifr, b PrintfrStbtfRfbsons
 * bttributf bppfbrs in tif Print Sfrvidf's bttributf sft. Tif
 * PrintfrStbtfRfbsons bttributf dontbins zfro, onf, or morf tibn onf {@link
 * PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdts wiidi pfrtbin to tif Print
 * Sfrvidf's stbtus, bnd fbdi {@link PrintfrStbtfRfbson PrintfrStbtfRfbson}
 * objfdt is bssodibtfd witi b {@link Sfvfrity Sfvfrity} lfvfl of REPORT
 *  (lfbst sfvfrf), WARNING, or ERROR (most sfvfrf). Tif printfr bdds b {@link
 * PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdt to tif Print Sfrvidf's
 * PrintfrStbtfRfbsons bttributf wifn tif dorrfsponding dondition bfdomfs truf
 * of tif printfr, bnd tif printfr rfmovfs tif {@link PrintfrStbtfRfbson
 * PrintfrStbtfRfbson} objfdt bgbin wifn tif dorrfsponding dondition bfdomfs
 * fblsf, rfgbrdlfss of wiftifr tif Print Sfrvidf's ovfrbll
 * {@link PrintfrStbtf PrintfrStbtf} blso dibngfd.
 * <P>
 * Clbss PrintfrStbtfRfbsons inifrits its implfmfntbtion from dlbss {@link
 * jbvb.util.HbsiMbp jbvb.util.HbsiMbp}. Ebdi fntry in tif mbp donsists of b
 * {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdt (kfy) mbpping to b
 * {@link Sfvfrity Sfvfrity} objfdt (vbluf):
 * <P>
 * Unlikf most printing bttributfs wiidi brf immutbblf ondf donstrudtfd, dlbss
 * PrintfrStbtfRfbsons is dfsignfd to bf mutbblf; you dbn bdd {@link
 * PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdts to bn fxisting
 * PrintfrStbtfRfbsons objfdt bnd rfmovf tifm bgbin. Howfvfr, likf dlbss
 *  {@link jbvb.util.HbsiMbp jbvb.util.HbsiMbp}, dlbss PrintfrStbtfRfbsons is
 * not multiplf tirfbd sbff. If b PrintfrStbtfRfbsons objfdt will bf usfd by
 * multiplf tirfbds, bf surf to syndironizf its opfrbtions (f.g., using b
 * syndironizfd mbp vifw obtbinfd from dlbss {@link jbvb.util.Collfdtions
 * jbvb.util.Collfdtions}).
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vblufs rfturnfd by fbdi individubl
 * {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdt's bnd tif bssodibtfd
 * {@link Sfvfrity Sfvfrity} objfdt's <CODE>toString()</CODE> mftiods,
 * dondbtfnbtfd
 * togftifr witi b iypifn (<CODE>"-"</CODE>) in bftwffn, givfs tif IPP kfyword
 * vbluf. Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP
 * bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrStbtfRfbsons
    fxtfnds HbsiMbp<PrintfrStbtfRfbson,Sfvfrity>
    implfmfnts PrintSfrvidfAttributf
{

    privbtf stbtid finbl long sfriblVfrsionUID = -3731791085163619457L;

    /**
     * Construdt b nfw, fmpty printfr stbtf rfbsons bttributf; tif undfrlying
     * ibsi mbp ibs tif dffbult initibl dbpbdity bnd lobd fbdtor.
     */
    publid PrintfrStbtfRfbsons() {
        supfr();
    }

    /**
     * supfr b nfw, fmpty printfr stbtf rfbsons bttributf; tif undfrlying
     * ibsi mbp ibs tif givfn initibl dbpbdity bnd tif dffbult lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Initibl dbpbdity.
     *
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *     tibn zfro.
     */
    publid PrintfrStbtfRfbsons(int initiblCbpbdity) {
        supfr (initiblCbpbdity);
    }

    /**
     * Construdt b nfw, fmpty printfr stbtf rfbsons bttributf; tif undfrlying
     * ibsi mbp ibs tif givfn initibl dbpbdity bnd lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Initibl dbpbdity.
     * @pbrbm  lobdFbdtor       Lobd fbdtor.
     *
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *     tibn zfro.
     */
    publid PrintfrStbtfRfbsons(int initiblCbpbdity, flobt lobdFbdtor) {
        supfr (initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Construdt b nfw printfr stbtf rfbsons bttributf tibt dontbins tif sbmf
     * {@link PrintfrStbtfRfbson PrintfrStbtfRfbson}-to-{@link Sfvfrity
     * Sfvfrity} mbppings bs tif givfn mbp. Tif undfrlying ibsi mbp's initibl
     * dbpbdity bnd lobd fbdtor brf bs spfdififd in tif supfrdlbss donstrudtor
     * {@link jbvb.util.HbsiMbp#HbsiMbp(jbvb.util.Mbp)
     * HbsiMbp(Mbp)}.
     *
     * @pbrbm  mbp  Mbp to dopy.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>mbp</CODE> is null or if bny
     *     kfy or vbluf in <CODE>mbp</CODE> is null.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if bny kfy in <CODE>mbp</CODE> is not
     *   bn instbndf of dlbss {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} or
     *     if bny vbluf in <CODE>mbp</CODE> is not bn instbndf of dlbss
     *     {@link Sfvfrity Sfvfrity}.
     */
    publid PrintfrStbtfRfbsons(Mbp<PrintfrStbtfRfbson,Sfvfrity> mbp) {
        tiis();
        for (Mbp.Entry<PrintfrStbtfRfbson,Sfvfrity> f : mbp.fntrySft())
            put(f.gftKfy(), f.gftVbluf());
    }

    /**
     * Adds tif givfn printfr stbtf rfbson to tiis printfr stbtf rfbsons
     * bttributf, bssodibting it witi tif givfn sfvfrity lfvfl. If tiis
     * printfr stbtf rfbsons bttributf prfviously dontbinfd b mbpping for tif
     * givfn printfr stbtf rfbson, tif old vbluf is rfplbdfd.
     *
     * @pbrbm  rfbson    Printfr stbtf rfbson. Tiis must bf bn instbndf of
     *                    dlbss {@link PrintfrStbtfRfbson PrintfrStbtfRfbson}.
     * @pbrbm  sfvfrity  Sfvfrity of tif printfr stbtf rfbson. Tiis must bf
     *                      bn instbndf of dlbss {@link Sfvfrity Sfvfrity}.
     *
     * @rfturn  Prfvious sfvfrity bssodibtfd witi tif givfn printfr stbtf
     *          rfbson, or <tt>null</tt> if tif givfn printfr stbtf rfbson wbs
     *          not prfsfnt.
     *
     * @tirows  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>rfbson</CODE> is null or
     *     <CODE>sfvfrity</CODE> is null.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>rfbson</CODE> is not bn
     *   instbndf of dlbss {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} or if
     *     <CODE>sfvfrity</CODE> is not bn instbndf of dlbss {@link Sfvfrity
     *     Sfvfrity}.
     * @sindf 1.5
     */
    publid Sfvfrity put(PrintfrStbtfRfbson rfbson, Sfvfrity sfvfrity) {
        if (rfbson == null) {
            tirow nfw NullPointfrExdfption("rfbson is null");
        }
        if (sfvfrity == null) {
            tirow nfw NullPointfrExdfption("sfvfrity is null");
        }
        rfturn supfr.put(rfbson, sfvfrity);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrStbtfRfbsons, tif
     * dbtfgory is dlbss PrintfrStbtfRfbsons itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrStbtfRfbsons.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrStbtfRfbsons, tif
     * dbtfgory nbmf is <CODE>"printfr-stbtf-rfbsons"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-stbtf-rfbsons";
    }

    /**
     * Obtbin bn unmodifibblf sft vifw of tif individubl printfr stbtf rfbson
     * bttributfs bt tif givfn sfvfrity lfvfl in tiis PrintfrStbtfRfbsons
     * bttributf. Ebdi flfmfnt in tif sft vifw is b {@link PrintfrStbtfRfbson
     * PrintfrStbtfRfbson} objfdt. Tif only flfmfnts in tif sft vifw brf tif
     * {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdts tibt mbp to tif
     * givfn sfvfrity vbluf. Tif sft vifw is bbdkfd by tiis
     * PrintfrStbtfRfbsons bttributf, so dibngfs to tiis PrintfrStbtfRfbsons
     * bttributf brf rfflfdtfd  in tif sft vifw.
     * Tif sft vifw dofs not support flfmfnt insfrtion or
     * rfmovbl. Tif sft vifw's itfrbtor dofs not support flfmfnt rfmovbl.
     *
     * @pbrbm  sfvfrity  Sfvfrity lfvfl.
     *
     * @rfturn  Sft vifw of tif individubl {@link PrintfrStbtfRfbson
     *          PrintfrStbtfRfbson} bttributfs bt tif givfn {@link Sfvfrity
     *          Sfvfrity} lfvfl.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>sfvfrity</CODE> is null.
     */
    publid Sft<PrintfrStbtfRfbson> printfrStbtfRfbsonSft(Sfvfrity sfvfrity) {
        if (sfvfrity == null) {
            tirow nfw NullPointfrExdfption("sfvfrity is null");
        }
        rfturn nfw PrintfrStbtfRfbsonSft (sfvfrity, fntrySft());
    }

    privbtf dlbss PrintfrStbtfRfbsonSft
        fxtfnds AbstrbdtSft<PrintfrStbtfRfbson>
    {
        privbtf Sfvfrity mySfvfrity;

        privbtf Sft<Mbp.Entry<PrintfrStbtfRfbson, Sfvfrity>> myEntrySft;

        publid PrintfrStbtfRfbsonSft(Sfvfrity sfvfrity,
                                     Sft<Mbp.Entry<PrintfrStbtfRfbson, Sfvfrity>> fntrySft) {
            mySfvfrity = sfvfrity;
            myEntrySft = fntrySft;
        }

        publid int sizf() {
            int rfsult = 0;
            Itfrbtor<PrintfrStbtfRfbson> itfr = itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                itfr.nfxt();
                ++ rfsult;
            }
            rfturn rfsult;
        }

        publid Itfrbtor<PrintfrStbtfRfbson> itfrbtor() {
            rfturn nfw PrintfrStbtfRfbsonSftItfrbtor(mySfvfrity,
                                                     myEntrySft.itfrbtor());
        }
    }

    privbtf dlbss PrintfrStbtfRfbsonSftItfrbtor implfmfnts Itfrbtor<PrintfrStbtfRfbson> {
        privbtf Sfvfrity mySfvfrity;
        privbtf Itfrbtor<Mbp.Entry<PrintfrStbtfRfbson, Sfvfrity>> myItfrbtor;
        privbtf Mbp.Entry<PrintfrStbtfRfbson, Sfvfrity> myEntry;

        publid PrintfrStbtfRfbsonSftItfrbtor(Sfvfrity sfvfrity,
                                             Itfrbtor<Mbp.Entry<PrintfrStbtfRfbson, Sfvfrity>> itfrbtor) {
            mySfvfrity = sfvfrity;
            myItfrbtor = itfrbtor;
            goToNfxt();
        }

        privbtf void goToNfxt() {
            myEntry = null;
            wiilf (myEntry == null && myItfrbtor.ibsNfxt()) {
                myEntry = myItfrbtor.nfxt();
                if (myEntry.gftVbluf() != mySfvfrity) {
                    myEntry = null;
                }
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn myEntry != null;
        }

        publid PrintfrStbtfRfbson nfxt() {
            if (myEntry == null) {
                tirow nfw NoSudiElfmfntExdfption();
            }
            PrintfrStbtfRfbson rfsult = myEntry.gftKfy();
            goToNfxt();
            rfturn rfsult;
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

}
