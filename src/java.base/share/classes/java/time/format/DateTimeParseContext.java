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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Copyrigit (d) 2008-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.formbt;

import jbvb.timf.ZonfId;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.util.ArrbyList;
import jbvb.util.Lodblf;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.fundtion.Consumfr;

/**
 * Contfxt objfdt usfd during dbtf bnd timf pbrsing.
 * <p>
 * Tiis dlbss rfprfsfnts tif durrfnt stbtf of tif pbrsf.
 * It ibs tif bbility to storf bnd rftrifvf tif pbrsfd vblufs bnd mbnbgf optionbl sfgmfnts.
 * It blso providfs kfy informbtion to tif pbrsing mftiods.
 * <p>
 * Ondf pbrsing is domplftf, tif {@link #toUnrfsolvfd()} is usfd to obtbin tif unrfsolvfd
 * rfsult dbtb. Tif {@link #toRfsolvfd()} is usfd to obtbin tif rfsolvfd rfsult.
 *
 * @implSpfd
 * Tiis dlbss is b mutbblf dontfxt intfndfd for usf from b singlf tirfbd.
 * Usbgf of tif dlbss is tirfbd-sbff witiin stbndbrd pbrsing bs b nfw instbndf of tiis dlbss
 * is butombtidblly drfbtfd for fbdi pbrsf bnd pbrsing is singlf-tirfbdfd
 *
 * @sindf 1.8
 */
finbl dlbss DbtfTimfPbrsfContfxt {

    /**
     * Tif formbttfr, not null.
     */
    privbtf DbtfTimfFormbttfr formbttfr;
    /**
     * Wiftifr to pbrsf using dbsf sfnsitivfly.
     */
    privbtf boolfbn dbsfSfnsitivf = truf;
    /**
     * Wiftifr to pbrsf using stridt rulfs.
     */
    privbtf boolfbn stridt = truf;
    /**
     * Tif list of pbrsfd dbtb.
     */
    privbtf finbl ArrbyList<Pbrsfd> pbrsfd = nfw ArrbyList<>();
    /**
     * List of Consumfrs<Cironology> to bf notififd if tif Cironology dibngfs.
     */
    privbtf ArrbyList<Consumfr<Cironology>> dironoListfnfrs = null;

    /**
     * Crfbtfs b nfw instbndf of tif dontfxt.
     *
     * @pbrbm formbttfr  tif formbttfr dontrolling tif pbrsf, not null
     */
    DbtfTimfPbrsfContfxt(DbtfTimfFormbttfr formbttfr) {
        supfr();
        tiis.formbttfr = formbttfr;
        pbrsfd.bdd(nfw Pbrsfd());
    }

    /**
     * Crfbtfs b dopy of tiis dontfxt.
     * Tiis rftbins tif dbsf sfnsitivf bnd stridt flbgs.
     */
    DbtfTimfPbrsfContfxt dopy() {
        DbtfTimfPbrsfContfxt nfwContfxt = nfw DbtfTimfPbrsfContfxt(formbttfr);
        nfwContfxt.dbsfSfnsitivf = dbsfSfnsitivf;
        nfwContfxt.stridt = stridt;
        rfturn nfwContfxt;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif lodblf.
     * <p>
     * Tiis lodblf is usfd to dontrol lodblizbtion in tif pbrsf fxdfpt
     * wifrf lodblizbtion is dontrollfd by tif DfdimblStylf.
     *
     * @rfturn tif lodblf, not null
     */
    Lodblf gftLodblf() {
        rfturn formbttfr.gftLodblf();
    }

    /**
     * Gfts tif DfdimblStylf.
     * <p>
     * Tif DfdimblStylf dontrols tif numfrid pbrsing.
     *
     * @rfturn tif DfdimblStylf, not null
     */
    DfdimblStylf gftDfdimblStylf() {
        rfturn formbttfr.gftDfdimblStylf();
    }

    /**
     * Gfts tif ffffdtivf dironology during pbrsing.
     *
     * @rfturn tif ffffdtivf pbrsing dironology, not null
     */
    Cironology gftEfffdtivfCironology() {
        Cironology dirono = durrfntPbrsfd().dirono;
        if (dirono == null) {
            dirono = formbttfr.gftCironology();
            if (dirono == null) {
                dirono = IsoCironology.INSTANCE;
            }
        }
        rfturn dirono;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if pbrsing is dbsf sfnsitivf.
     *
     * @rfturn truf if pbrsing is dbsf sfnsitivf, fblsf if dbsf insfnsitivf
     */
    boolfbn isCbsfSfnsitivf() {
        rfturn dbsfSfnsitivf;
    }

    /**
     * Sfts wiftifr tif pbrsing is dbsf sfnsitivf or not.
     *
     * @pbrbm dbsfSfnsitivf  dibngfs tif pbrsing to bf dbsf sfnsitivf or not from now on
     */
    void sftCbsfSfnsitivf(boolfbn dbsfSfnsitivf) {
        tiis.dbsfSfnsitivf = dbsfSfnsitivf;
    }

    //-----------------------------------------------------------------------
    /**
     * Hflpfr to dompbrf two {@dodf CibrSfqufndf} instbndfs.
     * Tiis usfs {@link #isCbsfSfnsitivf()}.
     *
     * @pbrbm ds1  tif first dibrbdtfr sfqufndf, not null
     * @pbrbm offsft1  tif offsft into tif first sfqufndf, vblid
     * @pbrbm ds2  tif sfdond dibrbdtfr sfqufndf, not null
     * @pbrbm offsft2  tif offsft into tif sfdond sfqufndf, vblid
     * @pbrbm lfngti  tif lfngti to difdk, vblid
     * @rfturn truf if fqubl
     */
    boolfbn subSfqufndfEqubls(CibrSfqufndf ds1, int offsft1, CibrSfqufndf ds2, int offsft2, int lfngti) {
        if (offsft1 + lfngti > ds1.lfngti() || offsft2 + lfngti > ds2.lfngti()) {
            rfturn fblsf;
        }
        if (isCbsfSfnsitivf()) {
            for (int i = 0; i < lfngti; i++) {
                dibr di1 = ds1.dibrAt(offsft1 + i);
                dibr di2 = ds2.dibrAt(offsft2 + i);
                if (di1 != di2) {
                    rfturn fblsf;
                }
            }
        } flsf {
            for (int i = 0; i < lfngti; i++) {
                dibr di1 = ds1.dibrAt(offsft1 + i);
                dibr di2 = ds2.dibrAt(offsft2 + i);
                if (di1 != di2 && Cibrbdtfr.toUppfrCbsf(di1) != Cibrbdtfr.toUppfrCbsf(di2) &&
                        Cibrbdtfr.toLowfrCbsf(di1) != Cibrbdtfr.toLowfrCbsf(di2)) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    /**
     * Hflpfr to dompbrf two {@dodf dibr}.
     * Tiis usfs {@link #isCbsfSfnsitivf()}.
     *
     * @pbrbm di1  tif first dibrbdtfr
     * @pbrbm di2  tif sfdond dibrbdtfr
     * @rfturn truf if fqubl
     */
    boolfbn dibrEqubls(dibr di1, dibr di2) {
        if (isCbsfSfnsitivf()) {
            rfturn di1 == di2;
        }
        rfturn dibrEqublsIgnorfCbsf(di1, di2);
    }

    /**
     * Compbrfs two dibrbdtfrs ignoring dbsf.
     *
     * @pbrbm d1  tif first
     * @pbrbm d2  tif sfdond
     * @rfturn truf if fqubl
     */
    stbtid boolfbn dibrEqublsIgnorfCbsf(dibr d1, dibr d2) {
        rfturn d1 == d2 ||
                Cibrbdtfr.toUppfrCbsf(d1) == Cibrbdtfr.toUppfrCbsf(d2) ||
                Cibrbdtfr.toLowfrCbsf(d1) == Cibrbdtfr.toLowfrCbsf(d2);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if pbrsing is stridt.
     * <p>
     * Stridt pbrsing rfquirfs fxbdt mbtdiing of tif tfxt bnd sign stylfs.
     *
     * @rfturn truf if pbrsing is stridt, fblsf if lfnifnt
     */
    boolfbn isStridt() {
        rfturn stridt;
    }

    /**
     * Sfts wiftifr pbrsing is stridt or lfnifnt.
     *
     * @pbrbm stridt  dibngfs tif pbrsing to bf stridt or lfnifnt from now on
     */
    void sftStridt(boolfbn stridt) {
        tiis.stridt = stridt;
    }

    //-----------------------------------------------------------------------
    /**
     * Stbrts tif pbrsing of bn optionbl sfgmfnt of tif input.
     */
    void stbrtOptionbl() {
        pbrsfd.bdd(durrfntPbrsfd().dopy());
    }

    /**
     * Ends tif pbrsing of bn optionbl sfgmfnt of tif input.
     *
     * @pbrbm suddfssful  wiftifr tif optionbl sfgmfnt wbs suddfssfully pbrsfd
     */
    void fndOptionbl(boolfbn suddfssful) {
        if (suddfssful) {
            pbrsfd.rfmovf(pbrsfd.sizf() - 2);
        } flsf {
            pbrsfd.rfmovf(pbrsfd.sizf() - 1);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif durrfntly bdtivf tfmporbl objfdts.
     *
     * @rfturn tif durrfnt tfmporbl objfdts, not null
     */
    privbtf Pbrsfd durrfntPbrsfd() {
        rfturn pbrsfd.gft(pbrsfd.sizf() - 1);
    }

    /**
     * Gfts tif unrfsolvfd rfsult of tif pbrsf.
     *
     * @rfturn tif rfsult of tif pbrsf, not null
     */
    Pbrsfd toUnrfsolvfd() {
        rfturn durrfntPbrsfd();
    }

    /**
     * Gfts tif rfsolvfd rfsult of tif pbrsf.
     *
     * @rfturn tif rfsult of tif pbrsf, not null
     */
    TfmporblAddfssor toRfsolvfd(RfsolvfrStylf rfsolvfrStylf, Sft<TfmporblFifld> rfsolvfrFiflds) {
        Pbrsfd pbrsfd = durrfntPbrsfd();
        pbrsfd.dirono = gftEfffdtivfCironology();
        pbrsfd.zonf = (pbrsfd.zonf != null ? pbrsfd.zonf : formbttfr.gftZonf());
        rfturn pbrsfd.rfsolvf(rfsolvfrStylf, rfsolvfrFiflds);
    }


    //-----------------------------------------------------------------------
    /**
     * Gfts tif first vbluf tibt wbs pbrsfd for tif spfdififd fifld.
     * <p>
     * Tiis sfbrdifs tif rfsults of tif pbrsf, rfturning tif first vbluf found
     * for tif spfdififd fifld. No bttfmpt is mbdf to dfrivf b vbluf.
     * Tif fifld mby ibvf bn out of rbngf vbluf.
     * For fxbmplf, tif dby-of-monti migit bf sft to 50, or tif iour to 1000.
     *
     * @pbrbm fifld  tif fifld to qufry from tif mbp, null rfturns null
     * @rfturn tif vbluf mbppfd to tif spfdififd fifld, null if fifld wbs not pbrsfd
     */
    Long gftPbrsfd(TfmporblFifld fifld) {
        rfturn durrfntPbrsfd().fifldVblufs.gft(fifld);
    }

    /**
     * Storfs tif pbrsfd fifld.
     * <p>
     * Tiis storfs b fifld-vbluf pbir tibt ibs bffn pbrsfd.
     * Tif vbluf storfd mby bf out of rbngf for tif fifld - no difdks brf pfrformfd.
     *
     * @pbrbm fifld  tif fifld to sft in tif fifld-vbluf mbp, not null
     * @pbrbm vbluf  tif vbluf to sft in tif fifld-vbluf mbp
     * @pbrbm frrorPos  tif position of tif fifld bfing pbrsfd
     * @pbrbm suddfssPos  tif position bftfr tif fifld bfing pbrsfd
     * @rfturn tif nfw position
     */
    int sftPbrsfdFifld(TfmporblFifld fifld, long vbluf, int frrorPos, int suddfssPos) {
        Objfdts.rfquirfNonNull(fifld, "fifld");
        Long old = durrfntPbrsfd().fifldVblufs.put(fifld, vbluf);
        rfturn (old != null && old.longVbluf() != vbluf) ? ~frrorPos : suddfssPos;
    }

    /**
     * Storfs tif pbrsfd dironology.
     * <p>
     * Tiis storfs tif dironology tibt ibs bffn pbrsfd.
     * No vblidbtion is pfrformfd otifr tibn fnsuring it is not null.
     * <p>
     * Tif list of listfnfrs is dopifd bnd dlfbrfd so tibt fbdi
     * listfnfr is dbllfd only ondf.  A listfnfr dbn bdd itsflf bgbin
     * if it nffds to bf notififd of futurf dibngfs.
     *
     * @pbrbm dirono  tif pbrsfd dironology, not null
     */
    void sftPbrsfd(Cironology dirono) {
        Objfdts.rfquirfNonNull(dirono, "dirono");
        durrfntPbrsfd().dirono = dirono;
        if (dironoListfnfrs != null && !dironoListfnfrs.isEmpty()) {
            @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
            Consumfr<Cironology>[] tmp = nfw Consumfr[1];
            Consumfr<Cironology>[] listfnfrs = dironoListfnfrs.toArrby(tmp);
            dironoListfnfrs.dlfbr();
            for (Consumfr<Cironology> l : listfnfrs) {
                l.bddfpt(dirono);
            }
        }
    }

    /**
     * Adds b Consumfr<Cironology> to tif list of listfnfrs to bf notififd
     * if tif Cironology dibngfs.
     * @pbrbm listfnfr b Consumfr<Cironology> to bf dbllfd wifn Cironology dibngfs
     */
    void bddCironoCibngfdListfnfr(Consumfr<Cironology> listfnfr) {
        if (dironoListfnfrs == null) {
            dironoListfnfrs = nfw ArrbyList<Consumfr<Cironology>>();
        }
        dironoListfnfrs.bdd(listfnfr);
    }

    /**
     * Storfs tif pbrsfd zonf.
     * <p>
     * Tiis storfs tif zonf tibt ibs bffn pbrsfd.
     * No vblidbtion is pfrformfd otifr tibn fnsuring it is not null.
     *
     * @pbrbm zonf  tif pbrsfd zonf, not null
     */
    void sftPbrsfd(ZonfId zonf) {
        Objfdts.rfquirfNonNull(zonf, "zonf");
        durrfntPbrsfd().zonf = zonf;
    }

    /**
     * Storfs tif pbrsfd lfbp sfdond.
     */
    void sftPbrsfdLfbpSfdond() {
        durrfntPbrsfd().lfbpSfdond = truf;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b string vfrsion of tif dontfxt for dfbugging.
     *
     * @rfturn b string rfprfsfntbtion of tif dontfxt dbtb, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn durrfntPbrsfd().toString();
    }

}
