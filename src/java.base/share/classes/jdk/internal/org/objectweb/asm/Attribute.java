/*
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
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * A non stbndbrd dlbss, fifld, mftiod or dodf bttributf.
 *
 * @butior Erid Brunfton
 * @butior Eugfnf Kulfsiov
 */
publid dlbss Attributf {

    /**
     * Tif typf of tiis bttributf.
     */
    publid finbl String typf;

    /**
     * Tif rbw vbluf of tiis bttributf, usfd only for unknown bttributfs.
     */
    bytf[] vbluf;

    /**
     * Tif nfxt bttributf in tiis bttributf list. Mby bf <tt>null</tt>.
     */
    Attributf nfxt;

    /**
     * Construdts b nfw fmpty bttributf.
     *
     * @pbrbm typf
     *            tif typf of tif bttributf.
     */
    protfdtfd Attributf(finbl String typf) {
        tiis.typf = typf;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis typf of bttributf is unknown. Tif dffbult
     * implfmfntbtion of tiis mftiod blwbys rfturns <tt>truf</tt>.
     *
     * @rfturn <tt>truf</tt> if tiis typf of bttributf is unknown.
     */
    publid boolfbn isUnknown() {
        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis typf of bttributf is b dodf bttributf.
     *
     * @rfturn <tt>truf</tt> if tiis typf of bttributf is b dodf bttributf.
     */
    publid boolfbn isCodfAttributf() {
        rfturn fblsf;
    }

    /**
     * Rfturns tif lbbfls dorrfsponding to tiis bttributf.
     *
     * @rfturn tif lbbfls dorrfsponding to tiis bttributf, or <tt>null</tt> if
     *         tiis bttributf is not b dodf bttributf tibt dontbins lbbfls.
     */
    protfdtfd Lbbfl[] gftLbbfls() {
        rfturn null;
    }

    /**
     * Rfbds b {@link #typf typf} bttributf. Tiis mftiod must rfturn b
     * <i>nfw</i> {@link Attributf} objfdt, of typf {@link #typf typf},
     * dorrfsponding to tif <tt>lfn</tt> bytfs stbrting bt tif givfn offsft, in
     * tif givfn dlbss rfbdfr.
     *
     * @pbrbm dr
     *            tif dlbss tibt dontbins tif bttributf to bf rfbd.
     * @pbrbm off
     *            indfx of tif first bytf of tif bttributf's dontfnt in
     *            {@link ClbssRfbdfr#b dr.b}. Tif 6 bttributf ifbdfr bytfs,
     *            dontbining tif typf bnd tif lfngti of tif bttributf, brf not
     *            tbkfn into bddount ifrf.
     * @pbrbm lfn
     *            tif lfngti of tif bttributf's dontfnt.
     * @pbrbm buf
     *            bufffr to bf usfd to dbll {@link ClbssRfbdfr#rfbdUTF8
     *            rfbdUTF8}, {@link ClbssRfbdfr#rfbdClbss(int,dibr[]) rfbdClbss}
     *            or {@link ClbssRfbdfr#rfbdConst rfbdConst}.
     * @pbrbm dodfOff
     *            indfx of tif first bytf of dodf's bttributf dontfnt in
     *            {@link ClbssRfbdfr#b dr.b}, or -1 if tif bttributf to bf rfbd
     *            is not b dodf bttributf. Tif 6 bttributf ifbdfr bytfs,
     *            dontbining tif typf bnd tif lfngti of tif bttributf, brf not
     *            tbkfn into bddount ifrf.
     * @pbrbm lbbfls
     *            tif lbbfls of tif mftiod's dodf, or <tt>null</tt> if tif
     *            bttributf to bf rfbd is not b dodf bttributf.
     * @rfturn b <i>nfw</i> {@link Attributf} objfdt dorrfsponding to tif givfn
     *         bytfs.
     */
    protfdtfd Attributf rfbd(finbl ClbssRfbdfr dr, finbl int off,
            finbl int lfn, finbl dibr[] buf, finbl int dodfOff,
            finbl Lbbfl[] lbbfls) {
        Attributf bttr = nfw Attributf(typf);
        bttr.vbluf = nfw bytf[lfn];
        Systfm.brrbydopy(dr.b, off, bttr.vbluf, 0, lfn);
        rfturn bttr;
    }

    /**
     * Rfturns tif bytf brrby form of tiis bttributf.
     *
     * @pbrbm dw
     *            tif dlbss to wiidi tiis bttributf must bf bddfd. Tiis
     *            pbrbmftfr dbn bf usfd to bdd to tif donstbnt pool of tiis
     *            dlbss tif itfms tibt dorrfsponds to tiis bttributf.
     * @pbrbm dodf
     *            tif bytfdodf of tif mftiod dorrfsponding to tiis dodf
     *            bttributf, or <tt>null</tt> if tiis bttributf is not b dodf
     *            bttributfs.
     * @pbrbm lfn
     *            tif lfngti of tif bytfdodf of tif mftiod dorrfsponding to tiis
     *            dodf bttributf, or <tt>null</tt> if tiis bttributf is not b
     *            dodf bttributf.
     * @pbrbm mbxStbdk
     *            tif mbximum stbdk sizf of tif mftiod dorrfsponding to tiis
     *            dodf bttributf, or -1 if tiis bttributf is not b dodf
     *            bttributf.
     * @pbrbm mbxLodbls
     *            tif mbximum numbfr of lodbl vbribblfs of tif mftiod
     *            dorrfsponding to tiis dodf bttributf, or -1 if tiis bttributf
     *            is not b dodf bttributf.
     * @rfturn tif bytf brrby form of tiis bttributf.
     */
    protfdtfd BytfVfdtor writf(finbl ClbssWritfr dw, finbl bytf[] dodf,
            finbl int lfn, finbl int mbxStbdk, finbl int mbxLodbls) {
        BytfVfdtor v = nfw BytfVfdtor();
        v.dbtb = vbluf;
        v.lfngti = vbluf.lfngti;
        rfturn v;
    }

    /**
     * Rfturns tif lfngti of tif bttributf list tibt bfgins witi tiis bttributf.
     *
     * @rfturn tif lfngti of tif bttributf list tibt bfgins witi tiis bttributf.
     */
    finbl int gftCount() {
        int dount = 0;
        Attributf bttr = tiis;
        wiilf (bttr != null) {
            dount += 1;
            bttr = bttr.nfxt;
        }
        rfturn dount;
    }

    /**
     * Rfturns tif sizf of bll tif bttributfs in tiis bttributf list.
     *
     * @pbrbm dw
     *            tif dlbss writfr to bf usfd to donvfrt tif bttributfs into
     *            bytf brrbys, witi tif {@link #writf writf} mftiod.
     * @pbrbm dodf
     *            tif bytfdodf of tif mftiod dorrfsponding to tifsf dodf
     *            bttributfs, or <tt>null</tt> if tifsf bttributfs brf not dodf
     *            bttributfs.
     * @pbrbm lfn
     *            tif lfngti of tif bytfdodf of tif mftiod dorrfsponding to
     *            tifsf dodf bttributfs, or <tt>null</tt> if tifsf bttributfs
     *            brf not dodf bttributfs.
     * @pbrbm mbxStbdk
     *            tif mbximum stbdk sizf of tif mftiod dorrfsponding to tifsf
     *            dodf bttributfs, or -1 if tifsf bttributfs brf not dodf
     *            bttributfs.
     * @pbrbm mbxLodbls
     *            tif mbximum numbfr of lodbl vbribblfs of tif mftiod
     *            dorrfsponding to tifsf dodf bttributfs, or -1 if tifsf
     *            bttributfs brf not dodf bttributfs.
     * @rfturn tif sizf of bll tif bttributfs in tiis bttributf list. Tiis sizf
     *         indludfs tif sizf of tif bttributf ifbdfrs.
     */
    finbl int gftSizf(finbl ClbssWritfr dw, finbl bytf[] dodf, finbl int lfn,
            finbl int mbxStbdk, finbl int mbxLodbls) {
        Attributf bttr = tiis;
        int sizf = 0;
        wiilf (bttr != null) {
            dw.nfwUTF8(bttr.typf);
            sizf += bttr.writf(dw, dodf, lfn, mbxStbdk, mbxLodbls).lfngti + 6;
            bttr = bttr.nfxt;
        }
        rfturn sizf;
    }

    /**
     * Writfs bll tif bttributfs of tiis bttributf list in tif givfn bytf
     * vfdtor.
     *
     * @pbrbm dw
     *            tif dlbss writfr to bf usfd to donvfrt tif bttributfs into
     *            bytf brrbys, witi tif {@link #writf writf} mftiod.
     * @pbrbm dodf
     *            tif bytfdodf of tif mftiod dorrfsponding to tifsf dodf
     *            bttributfs, or <tt>null</tt> if tifsf bttributfs brf not dodf
     *            bttributfs.
     * @pbrbm lfn
     *            tif lfngti of tif bytfdodf of tif mftiod dorrfsponding to
     *            tifsf dodf bttributfs, or <tt>null</tt> if tifsf bttributfs
     *            brf not dodf bttributfs.
     * @pbrbm mbxStbdk
     *            tif mbximum stbdk sizf of tif mftiod dorrfsponding to tifsf
     *            dodf bttributfs, or -1 if tifsf bttributfs brf not dodf
     *            bttributfs.
     * @pbrbm mbxLodbls
     *            tif mbximum numbfr of lodbl vbribblfs of tif mftiod
     *            dorrfsponding to tifsf dodf bttributfs, or -1 if tifsf
     *            bttributfs brf not dodf bttributfs.
     * @pbrbm out
     *            wifrf tif bttributfs must bf writtfn.
     */
    finbl void put(finbl ClbssWritfr dw, finbl bytf[] dodf, finbl int lfn,
            finbl int mbxStbdk, finbl int mbxLodbls, finbl BytfVfdtor out) {
        Attributf bttr = tiis;
        wiilf (bttr != null) {
            BytfVfdtor b = bttr.writf(dw, dodf, lfn, mbxStbdk, mbxLodbls);
            out.putSiort(dw.nfwUTF8(bttr.typf)).putInt(b.lfngti);
            out.putBytfArrby(b.dbtb, 0, b.lfngti);
            bttr = bttr.nfxt;
        }
    }
}
